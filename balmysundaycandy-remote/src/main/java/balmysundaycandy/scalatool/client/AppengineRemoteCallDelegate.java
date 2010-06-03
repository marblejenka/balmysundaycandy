package balmysundaycandy.scalatool.client;

import java.io.PrintStream;
import java.util.concurrent.Future;

import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;

import balmysundaycandy.core.future.ByteFuture;
import balmysundaycandy.scalatool.shared.RemoteCallRequest;
import balmysundaycandy.scalatool.shared.TimeLog;
import balmysundaycandy.scalatool.shared.RemoteCallRequest.CallRequest;
import balmysundaycandy.scalatool.shared.RemoteCallRequest.CallRequest.RequestType;
import balmysundaycandy.scalatool.shared.RemoteCallResponse.CallResponse;

import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.apphosting.api.ApiProxy.ApiProxyException;
import com.google.apphosting.api.ApiProxy.Delegate;
import com.google.apphosting.api.ApiProxy.Environment;
import com.google.apphosting.api.ApiProxy.LogRecord;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

public class AppengineRemoteCallDelegate implements Delegate<Environment> {

	private PrintStream out = System.out;

	@SuppressWarnings("unchecked")
	private static Delegate<Environment> originel = ApiProxy.getDelegate();

	@Override
	public void log(Environment environment, LogRecord logRecord) {
		originel.log(environment, logRecord);
	}

	@SuppressWarnings("deprecation")
	public URI uri() {
		ScalaEnvironment environment = (ScalaEnvironment) ApiProxy.getCurrentEnvironment();
		try {
			return new URI(environment.getUrl());
		} catch (URIException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void runInDelegate(Runnable runnable) {
		ApiProxy.setDelegate(this);
		try {
			runnable.run();
		} finally {
			ApiProxy.setDelegate(originel);
		}
	}

	@Override
	public byte[] makeSyncCall(Environment environment, String serviceName, String methodName, byte[] request) throws ApiProxyException {
		TimeLog start = new TimeLog();
		ByteString requestAsByteString = ByteString.copyFrom(request);

		CallRequest call = RemoteCallRequest.CallRequest.newBuilder().setRequestType(RequestType.RUN_SERVICE_CALL).setServiceName(serviceName).setMethodName(methodName).setRequestBytes(requestAsByteString).build();

		CallResponse callResponse = null;
		try {
			callResponse = CallResponse.newBuilder().mergeFrom(AppengineRemoteCallUtils.remoteCallBy(uri(), call.toByteArray())).build();
		} catch (InvalidProtocolBufferException e) {
			throw new ApiProxyException("balmysundaycandy remotecall cause exception." + e.getCause().toString());
		}
		TimeLog end = new TimeLog();

		if (callResponse.hasErrorLog()) {
			out.println("balmysundaycandy remotecall cause exception.");
			throw new RuntimeException(callResponse.getErrorLog().toString());
		}

		LogMode logMode = (LogMode) ApiProxy.getCurrentEnvironment().getAttributes().get(ScalaEnvironment.logmode);
		if (logMode.requresTimeLog()) {
			out.println("spend total ms:" + Long.valueOf(end.currenttime - start.currenttime));
		}
		if (logMode.requresRemoteLog()) {
			out.println(callResponse.getResponseLog());
		}

		return callResponse.getResponseBytes().toByteArray();
	}

	@Override
	public Future<byte[]> makeAsyncCall(Environment environment, String serviceName, String methodName, byte[] request, ApiConfig apiConfig) {
		return new ByteFuture(makeSyncCall(environment, serviceName, methodName, request));
	}
}

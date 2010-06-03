package balmysundaycandy.scalatool.server;

import balmysundaycandy.core.operations.ProtocolBufferObjectHolder;
import balmysundaycandy.scalatool.shared.LogEnrty;
import balmysundaycandy.scalatool.shared.TimeLog;
import balmysundaycandy.scalatool.shared.RemoteCallRequest.CallRequest;
import balmysundaycandy.scalatool.shared.RemoteCallResponse.CallResponse;
import balmysundaycandy.scalatool.shared.RemoteCallResponse.CallResponse.RequestType;

import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

public class RemoteCallExecutor {

	public static CallResponse runServiceCall(CallRequest callRequest) throws InvalidProtocolBufferException {
		TimeLog start = new TimeLog();
		byte[] response = ExecutionSupport.execute(callRequest);
		TimeLog end = new TimeLog();

		LogEnrty logEnrty = snatch(callRequest.getServiceName(), callRequest.getMethodName(), callRequest.getRequestBytes().toByteArray(),
				response, start, end, null);

		CallResponse callResponse = CallResponse.newBuilder().setRequestType(RequestType.RUN_SERVICE_CALL).setResponseBytes(
				ByteString.copyFrom(response)).setResponseLog(logEnrty.toString()).build();
		return callResponse;
	}

	public static CallResponse runRemoteCall(CallRequest callRequest) throws InvalidProtocolBufferException {
		// TODO run local function remote
		return runServiceCall(callRequest);
	}

	/**
	 * snatch rpc call and logs its detail to datastore.
	 * 
	 * @param serviceName
	 *            rpc call service name.
	 * @param methodName
	 *            rpc call method name.
	 * @param request
	 *            rpc call request as byte array.
	 * @param response
	 *            rpc call response as byte array.
	 * @param apiConfig
	 *            rpc call config if required.
	 */
	static LogEnrty snatch(final String serviceName, final String methodName, final byte[] request, final byte[] response,
			final TimeLog start, final TimeLog end, final ApiConfig apiConfig) {
		LogEnrty logEnrty = new LogEnrty();
		logEnrty.setServiceName(serviceName);
		logEnrty.setMethodName(methodName);
		logEnrty.setRequest(ProtocolBufferObjectHolder.requestAsString(serviceName, methodName, request));
		logEnrty.setResponse(ProtocolBufferObjectHolder.responseAsString(serviceName, methodName, response));
		logEnrty.setCallStart(start.date);
		logEnrty.setCallEnd(end.date);
		logEnrty.setSpendTimeMilles(end.currenttime - start.currenttime);

		return logEnrty;
	}
}

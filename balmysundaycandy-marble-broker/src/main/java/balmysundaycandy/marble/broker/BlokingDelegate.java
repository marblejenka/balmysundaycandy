package balmysundaycandy.marble.broker;

import java.util.Date;
import java.util.concurrent.Future;

import balmysundaycandy.core.operations.ProtocolBufferObjectHolder;
import balmysundaycandy.marble.broker.model.LogEnrty;

import com.google.appengine.api.datastore.Text;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.apphosting.api.ApiProxy.ApiProxyException;
import com.google.apphosting.api.ApiProxy.Environment;
import com.google.apphosting.api.ApiProxy.LogRecord;

/**
 * delegate snatch rpc call.
 * 
 * @author marblejenka
 * 
 */
public class BlokingDelegate implements ApiProxy.Delegate<Environment> {

	/**
	 * line separator.
	 */
	private static final String separator = System.getProperty("line.separator");
	
	/**
	 * original rpc call deligate.
	 */
	@SuppressWarnings("unchecked")
	private static final ApiProxy.Delegate<Environment> original = ApiProxy.getDelegate();

	private final ApiProxy.Delegate<Environment> self = this;
	
	/**
	 * returns original delegate.
	 * 
	 * @return original delegate.
	 */
	public ApiProxy.Delegate<Environment> getOriginal() {
		return original;
	}

	/**
	 * defalut constructor.
	 */
	public BlokingDelegate() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void log(Environment environment, LogRecord logRecord) {
		original.log(environment, logRecord);
	}

	/**
	 * {@inheritDoc}
	 */
	public byte[] makeSyncCall(Environment environment, String serviceName, String methodName, byte[] request) throws ApiProxyException {
		Times start = new Times();
		byte[] response = original.makeSyncCall(environment, serviceName, methodName, request);
		Times end = new Times();
		snatch(serviceName, methodName, request, response, start, end, null);
		return response;
	}

	/**
	 * {@inheritDoc}
	 */
	public Future<byte[]> makeAsyncCall(Environment environment, String serviceName, String methodName, byte[] request, ApiConfig apiConfig) {
		return original.makeAsyncCall(environment, serviceName, methodName, request, apiConfig);
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
	void snatch(final String serviceName, final String methodName, final byte[] request, final byte[] response, final Times start, final Times end, final ApiConfig apiConfig) {
		new InOriginalDeligate() {
			@Override
			public void executeWithOriginalDeligate() {
				LogEnrty logEnrty = new LogEnrty();
				logEnrty.setServiceName(serviceName);
				logEnrty.setMethodName(methodName);
				logEnrty.setRequest(new Text(ProtocolBufferObjectHolder.requestAsString(serviceName, methodName, request)));
				logEnrty.setResponse(new Text(ProtocolBufferObjectHolder.responseAsString(serviceName, methodName, response)));
				logEnrty.setCallTrace(new Text(callTrace()));
				logEnrty.setCallStart(start.date);
				logEnrty.setCallEnd(end.date);
				logEnrty.setSpendTimeMilles(end.currenttime - start.currenttime);

	//			Datastore.putWithoutTx(logEnrty);
			}
		}.run();
	}

	String callTrace() {
		StringBuilder stringBuilder = new StringBuilder();
		StackTraceElement[] element = Thread.currentThread().getStackTrace();
		for (int i = 0; i < element.length; i++) {
			StackTraceElement stackTraceElement = element[i];
			stringBuilder.append(stackTraceElement.getFileName());
			stringBuilder.append(" ");
			stringBuilder.append(stackTraceElement.getLineNumber());
			stringBuilder.append(" ");
			stringBuilder.append(stackTraceElement.getClassName());
			stringBuilder.append(" ");
			stringBuilder.append(stackTraceElement.getMethodName());
			stringBuilder.append(separator);
		}
		return stringBuilder.toString();
	}

	/**
	 * support for execute rpc call with original derigate.
	 * 
	 * @author marblejenka
	 * 
	 */
	private abstract class InOriginalDeligate {
		void swap2original() {
			ApiProxy.setDelegate(original);
		}

		public abstract void executeWithOriginalDeligate();

		void swap2bloker() {
			ApiProxy.setDelegate(self);
		}

		public void run() {
			swap2original();
			executeWithOriginalDeligate();
			swap2bloker();
		}
	}

	private class Times {
		Date date = new Date();
		long currenttime = System.currentTimeMillis();
	}
}

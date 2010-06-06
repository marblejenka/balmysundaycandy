package balmysundaycandy.core.delegate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.apphosting.api.ApiProxy.ApiProxyException;
import com.google.apphosting.api.ApiProxy.Delegate;
import com.google.apphosting.api.ApiProxy.Environment;
import com.google.apphosting.api.ApiProxy.LogRecord;

public class OperationFetchDelegate implements Delegate<Environment> {

	@SuppressWarnings("unchecked")
	private static Delegate<Environment> originel = ApiProxy.getDelegate();

	private static List<CalledOperation> calledOperations = new ArrayList<CalledOperation>();

	public static List<CalledOperation> getCalledOperations() {
		return calledOperations;
	}

	public static void clearCalledOpeartions() {
		calledOperations.clear();
	}

	@Override
	public void log(Environment environment, LogRecord logRecord) {
		originel.log(environment, logRecord);
	}

	@Override
	public byte[] makeSyncCall(Environment environment, String serviceName, String methodName, byte[] request) throws ApiProxyException {
		CalledOperation calledOperation = new CalledOperation(serviceName, methodName, request);
		calledOperations.add(calledOperation);
		return null;
	}

	@Override
	public Future<byte[]> makeAsyncCall(Environment environment, String serviceName, String methodName, byte[] request, ApiConfig apiConfig) {
		return originel.makeAsyncCall(environment, serviceName, methodName, request, apiConfig);
	}
}

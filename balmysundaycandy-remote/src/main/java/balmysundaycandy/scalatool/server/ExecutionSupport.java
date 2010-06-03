package balmysundaycandy.scalatool.server;

import balmysundaycandy.core.operations.OperationNamespaces.datastore_v3;
import balmysundaycandy.scalatool.shared.RemoteCallRequest.CallRequest;

import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.DatastorePb;

public class ExecutionSupport {
	public static byte[] execute(CallRequest callRequest) {
		if (datastore_v3.packageName.equals(callRequest.getServiceName()) && datastore_v3.methodName.BeginTransaction.equals(callRequest.getMethodName())){
			byte[] result = ApiProxy.makeSyncCall(callRequest.getServiceName(), callRequest.getMethodName(), parseOrRebuildByteArryaFromRequest(callRequest));
			DatastorePb.Transaction tx = new DatastorePb.Transaction();
			tx.mergeFrom(result);
			
			ApiProxy.makeSyncCall(datastore_v3.packageName, datastore_v3.methodName.Commit, tx.toByteArray());
			
			return result;
		}
		
		return ApiProxy.makeSyncCall(callRequest.getServiceName(), callRequest.getMethodName(), parseOrRebuildByteArryaFromRequest(callRequest));
	}

	static boolean isCommitOperation(String serviceName, String methodName) {
		return datastore_v3.packageName.equals(serviceName) && datastore_v3.methodName.Commit.equals(methodName);
	}

	static boolean isRollbackOperation(String serviceName, String methodName) {
		return datastore_v3.packageName.equals(serviceName) && datastore_v3.methodName.Rollback.equals(methodName);
	}

	static byte[] parseOrRebuildByteArryaFromRequest(CallRequest callRequest) {
		if (isCommitOperation(callRequest.getServiceName(), callRequest.getMethodName()) || isRollbackOperation(callRequest.getServiceName(), callRequest.getMethodName())) {
			return rebuildTransaction(callRequest.getRequestBytes().toByteArray());
		}

		return callRequest.getRequestBytes().toByteArray();
	}

	static byte[] rebuildTransaction(byte[] request) {
		DatastorePb.Transaction original = new DatastorePb.Transaction();
		original.mergeFrom(request);

		DatastorePb.Transaction tx = new DatastorePb.Transaction();
		tx.mergeFrom(original.toByteArray());
		tx.clearHandle();
		tx.setHandle(Long.valueOf(original.getHandle()));

		return tx.toByteArray();
	}

}

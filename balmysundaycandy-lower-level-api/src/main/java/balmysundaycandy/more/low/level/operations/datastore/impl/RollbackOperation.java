package balmysundaycandy.more.low.level.operations.datastore.impl;

import java.util.concurrent.Future;

import balmysundaycandy.core.future.ProtocolMessageFuture;
import balmysundaycandy.core.operations.OperationNamespaces.datastore_v3;
import balmysundaycandy.more.low.level.operations.datastore.DatastoreOperation;

import com.google.apphosting.api.ApiBasePb;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiBasePb.VoidProto;
import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.apphosting.api.DatastorePb.Transaction;

/**
 * rollback operation.
 * 
 * @author marblejenka
 */
public final class RollbackOperation extends DatastoreOperation<Transaction, VoidProto> {

	@Override
	public ApiBasePb.VoidProto call(Transaction request) {
		VoidProto voidProto = new VoidProto();
		voidProto.mergeFrom(ApiProxy.makeSyncCall(datastore_v3.packageName, datastore_v3.methodName.Rollback, request.toByteArray()));
		return voidProto;
	}

	@Override
	public Future<VoidProto> callAsync(Transaction request, ApiConfig apiConfig) {
		VoidProto response = new VoidProto();
		return new ProtocolMessageFuture<VoidProto>(ApiProxy.makeAsyncCall(datastore_v3.packageName, datastore_v3.methodName.Rollback, request.toByteArray(), apiConfig), response);
	}
}
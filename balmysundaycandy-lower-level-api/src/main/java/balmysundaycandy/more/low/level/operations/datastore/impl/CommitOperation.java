package balmysundaycandy.more.low.level.operations.datastore.impl;

import java.util.concurrent.Future;

import balmysundaycandy.core.future.ProtocolMessageFuture;
import balmysundaycandy.core.operations.OperationNamespaces.datastore_v3;
import balmysundaycandy.more.low.level.operations.datastore.DatastoreOperation;

import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.apphosting.api.DatastorePb.CommitResponse;
import com.google.apphosting.api.DatastorePb.Transaction;

/**
 * commit operation.
 * 
 * @author marblejenka
 */
public final class CommitOperation extends DatastoreOperation<Transaction, CommitResponse> {

	@Override
	public CommitResponse call(Transaction request) {
		CommitResponse commitResponse = new CommitResponse();
		commitResponse.mergeFrom(ApiProxy.makeSyncCall(datastore_v3.packageName, datastore_v3.methodName.Commit, request.toByteArray()));
		return commitResponse;
	}

	@Override
	public Future<CommitResponse> callAsync(Transaction request, ApiConfig apiConfig) {
		CommitResponse response = new CommitResponse();
		return new ProtocolMessageFuture<CommitResponse>(ApiProxy.makeAsyncCall(datastore_v3.packageName, datastore_v3.methodName.Commit, request.toByteArray(), apiConfig),
				response);
	}
}
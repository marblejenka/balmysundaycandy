package balmysundaycandy.more.low.level.operations.datastore.impl;

import java.util.concurrent.Future;

import balmysundaycandy.core.future.ProtocolMessageFuture;
import balmysundaycandy.core.operations.OperationNamespaces.datastore_v3;
import balmysundaycandy.more.low.level.operations.datastore.DatastoreOperation;

import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.apphosting.api.DatastorePb.DeleteRequest;
import com.google.apphosting.api.DatastorePb.DeleteResponse;

/**
 * delete operation.
 * 
 * @author marblejenka
 */
public final class DeleteOperation extends DatastoreOperation<DeleteRequest, DeleteResponse> {

	@Override
	public DeleteResponse call(DeleteRequest request) {
		DeleteResponse deleteResponse = new DeleteResponse();
		deleteResponse.mergeFrom(ApiProxy.makeSyncCall(datastore_v3.packageName, datastore_v3.methodName.Delete, request.toByteArray()));
		return deleteResponse;
	}

	@Override
	public Future<DeleteResponse> callAsync(DeleteRequest request, ApiConfig apiConfig) {
		DeleteResponse response = new DeleteResponse();
		return new ProtocolMessageFuture<DeleteResponse>(ApiProxy.makeAsyncCall(datastore_v3.packageName, datastore_v3.methodName.Delete, request.toByteArray(), apiConfig),
				response);
	}
}
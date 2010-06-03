package balmysundaycandy.more.low.level.operations.datastore.impl;

import java.util.concurrent.Future;

import balmysundaycandy.core.future.ProtocolMessageFuture;
import balmysundaycandy.core.operations.OperationNamespaces.datastore_v3;
import balmysundaycandy.more.low.level.operations.datastore.DatastoreOperation;

import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.apphosting.api.DatastorePb.AddActionsRequest;
import com.google.apphosting.api.DatastorePb.AddActionsResponse;

/**
 * add action operation.
 * 
 * @author marblejenka
 */
public final class AddActionOperation extends DatastoreOperation<AddActionsRequest, AddActionsResponse> {

	@Override
	public AddActionsResponse call(AddActionsRequest request) {
		AddActionsResponse actionResponse = new AddActionsResponse();
		actionResponse.mergeFrom(ApiProxy.makeSyncCall(datastore_v3.packageName, datastore_v3.methodName.AddAction, request.toByteArray()));
		return actionResponse;
	}

	@Override
	public Future<AddActionsResponse> callAsync(AddActionsRequest request, ApiConfig apiConfig) {
		AddActionsResponse response = new AddActionsResponse();
		return new ProtocolMessageFuture<AddActionsResponse>(ApiProxy.makeAsyncCall(datastore_v3.packageName, datastore_v3.methodName.AddAction, request.toByteArray(), apiConfig),
				response);
	}
}
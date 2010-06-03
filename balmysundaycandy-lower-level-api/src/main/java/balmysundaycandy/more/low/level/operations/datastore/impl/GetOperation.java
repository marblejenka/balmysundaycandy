package balmysundaycandy.more.low.level.operations.datastore.impl;

import java.util.concurrent.Future;

import balmysundaycandy.core.future.ProtocolMessageFuture;
import balmysundaycandy.core.operations.OperationNamespaces.datastore_v3;
import balmysundaycandy.more.low.level.operations.datastore.DatastoreOperation;

import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.apphosting.api.DatastorePb.GetRequest;
import com.google.apphosting.api.DatastorePb.GetResponse;

/**
 * get operation.
 * 
 * @author marblejenka
 */
public final class GetOperation extends DatastoreOperation<GetRequest, GetResponse> {

	@Override
	public GetResponse call(GetRequest request) {
		GetResponse getResponse = new GetResponse();
		getResponse.mergeFrom(ApiProxy.makeSyncCall(datastore_v3.packageName, datastore_v3.methodName.Get, request.toByteArray()));
		return getResponse;
	}

	@Override
	public Future<GetResponse> callAsync(GetRequest request, ApiConfig apiConfig) {
		GetResponse response = new GetResponse();
		return new ProtocolMessageFuture<GetResponse>(ApiProxy.makeAsyncCall(datastore_v3.packageName, datastore_v3.methodName.Get, request.toByteArray(), apiConfig), response);
	}
}
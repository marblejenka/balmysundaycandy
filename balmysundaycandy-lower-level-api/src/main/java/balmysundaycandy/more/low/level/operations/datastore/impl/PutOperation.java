package balmysundaycandy.more.low.level.operations.datastore.impl;

import java.util.concurrent.Future;

import balmysundaycandy.core.future.ProtocolMessageFuture;
import balmysundaycandy.core.operations.OperationNamespaces.datastore_v3;
import balmysundaycandy.more.low.level.operations.datastore.DatastoreOperation;

import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.apphosting.api.DatastorePb.PutRequest;
import com.google.apphosting.api.DatastorePb.PutResponse;

/**
 * put operation.
 * 
 * @author marblejenka
 */
public final class PutOperation extends DatastoreOperation<PutRequest, PutResponse> {

	@Override
	public PutResponse call(PutRequest request) {
		PutResponse putResponse = new PutResponse();
		putResponse.mergeFrom(ApiProxy.makeSyncCall(datastore_v3.packageName, datastore_v3.methodName.Put, request.toByteArray()));
		return putResponse;
	}

	@Override
	public Future<PutResponse> callAsync(PutRequest request, ApiConfig apiConfig) {
		PutResponse response = new PutResponse();
		return new ProtocolMessageFuture<PutResponse>(ApiProxy.makeAsyncCall(datastore_v3.packageName, datastore_v3.methodName.Put, request.toByteArray(), apiConfig), response);
	}
}
package balmysundaycandy.more.low.level.operations.datastore.impl;

import java.util.concurrent.Future;

import balmysundaycandy.core.future.ProtocolMessageFuture;
import balmysundaycandy.core.operations.OperationNamespaces.datastore_v3;
import balmysundaycandy.more.low.level.operations.datastore.DatastoreOperation;

import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiBasePb.Integer64Proto;
import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.storage.onestore.v3.OnestoreEntity.CompositeIndex;

/**
 * create index operation.
 * 
 * @author marblejenka
 */
public final class CreateIndexOperation extends DatastoreOperation<CompositeIndex, Integer64Proto> {

	@Override
	public Integer64Proto call(CompositeIndex request) {
		Integer64Proto integer64Proto = new Integer64Proto();
		integer64Proto.mergeFrom(ApiProxy.makeSyncCall(datastore_v3.packageName, datastore_v3.methodName.CreateIndex, request.toByteArray()));
		return integer64Proto;
	}

	@Override
	public Future<Integer64Proto> callAsync(CompositeIndex request, ApiConfig apiConfig) {
		Integer64Proto response = new Integer64Proto();
		return new ProtocolMessageFuture<Integer64Proto>(ApiProxy.makeAsyncCall(datastore_v3.packageName, datastore_v3.methodName.CreateIndex, request.toByteArray(), apiConfig),
				response);
	}
}
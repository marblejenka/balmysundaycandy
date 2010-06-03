package balmysundaycandy.more.low.level.operations.datastore.impl;

import java.util.concurrent.Future;

import balmysundaycandy.core.future.ProtocolMessageFuture;
import balmysundaycandy.core.operations.OperationNamespaces.datastore_v3;
import balmysundaycandy.more.low.level.operations.datastore.DatastoreOperation;

import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.apphosting.api.DatastorePb.GetSchemaRequest;
import com.google.apphosting.api.DatastorePb.Schema;

/**
 * get schema operation.
 * 
 * @author marblejenka
 */
public final class GetSchemaOperation extends DatastoreOperation<GetSchemaRequest, Schema> {

	@Override
	public Schema call(GetSchemaRequest request) {
		Schema schema = new Schema();
		boolean apiproxycallsucceed = schema.mergeFrom(ApiProxy.makeSyncCall(datastore_v3.packageName, datastore_v3.methodName.GetSchema, request.toByteArray()));
		if (!apiproxycallsucceed) {
			throw new RuntimeException("call " + getClass().getName() + " failed.");
		}
		return schema;
	}

	@Override
	public Future<Schema> callAsync(GetSchemaRequest request, ApiConfig apiConfig) {
		Schema response = new Schema();
		return new ProtocolMessageFuture<Schema>(ApiProxy.makeAsyncCall(datastore_v3.packageName, datastore_v3.methodName.GetSchema, request.toByteArray(), apiConfig), response);
	}
}
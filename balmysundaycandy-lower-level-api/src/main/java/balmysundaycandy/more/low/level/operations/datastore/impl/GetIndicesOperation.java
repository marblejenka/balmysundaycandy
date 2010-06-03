package balmysundaycandy.more.low.level.operations.datastore.impl;

import java.util.concurrent.Future;

import balmysundaycandy.core.future.ProtocolMessageFuture;
import balmysundaycandy.core.operations.OperationNamespaces.datastore_v3;
import balmysundaycandy.more.low.level.operations.datastore.DatastoreOperation;

import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiBasePb.StringProto;
import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.apphosting.api.DatastorePb.CompositeIndices;

/**
 * get indices operation.
 * 
 * @author marblejenka
 */
public final class GetIndicesOperation extends DatastoreOperation<StringProto, CompositeIndices> {

	@Override
	public CompositeIndices call(StringProto request) {
		CompositeIndices compositeIndices = new CompositeIndices();
		compositeIndices.mergeFrom(ApiProxy.makeSyncCall(datastore_v3.packageName, datastore_v3.methodName.GetIndices, request.toByteArray()));
		return compositeIndices;
	}

	@Override
	public Future<CompositeIndices> callAsync(StringProto request, ApiConfig apiConfig) {
		CompositeIndices response = new CompositeIndices();
		return new ProtocolMessageFuture<CompositeIndices>(ApiProxy.makeAsyncCall(datastore_v3.packageName, datastore_v3.methodName.GetIndices, request.toByteArray(), apiConfig),
				response);
	}
}
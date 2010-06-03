package balmysundaycandy.more.low.level.operations.datastore.impl;

import java.util.concurrent.Future;

import balmysundaycandy.core.future.ProtocolMessageFuture;
import balmysundaycandy.core.operations.OperationNamespaces.datastore_v3;
import balmysundaycandy.more.low.level.operations.datastore.DatastoreOperation;

import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.apphosting.api.DatastorePb.NextRequest;
import com.google.apphosting.api.DatastorePb.QueryResult;

/**
 * next operation.
 * 
 * @author marblejenka
 */
public final class NextOperation extends DatastoreOperation<NextRequest, QueryResult> {

	@Override
	public QueryResult call(NextRequest request) {
		QueryResult queryResult = new QueryResult();
		queryResult.mergeFrom(ApiProxy.makeSyncCall(datastore_v3.packageName, datastore_v3.methodName.Next, request.toByteArray()));
		return queryResult;
	}

	@Override
	public Future<QueryResult> callAsync(NextRequest request, ApiConfig apiConfig) {
		QueryResult response = new QueryResult();
		return new ProtocolMessageFuture<QueryResult>(ApiProxy.makeAsyncCall(datastore_v3.packageName, datastore_v3.methodName.Next, request.toByteArray(), apiConfig), response);
	}
}
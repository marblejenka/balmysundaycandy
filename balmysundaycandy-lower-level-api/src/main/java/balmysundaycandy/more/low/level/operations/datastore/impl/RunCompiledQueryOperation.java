package balmysundaycandy.more.low.level.operations.datastore.impl;

import java.util.concurrent.Future;

import balmysundaycandy.core.future.ProtocolMessageFuture;
import balmysundaycandy.core.operations.OperationNamespaces.datastore_v3;
import balmysundaycandy.more.low.level.operations.datastore.DatastoreOperation;

import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.apphosting.api.DatastorePb.QueryResult;
import com.google.apphosting.api.DatastorePb.RunCompiledQueryRequest;

/**
 * run compiled query operation.
 * 
 * @author marblejenka
 */
public final class RunCompiledQueryOperation extends DatastoreOperation<RunCompiledQueryRequest, QueryResult> {

	@Override
	public QueryResult call(RunCompiledQueryRequest request) {
		QueryResult queryResult = new QueryResult();
		queryResult.mergeFrom(ApiProxy.makeSyncCall(datastore_v3.packageName, datastore_v3.methodName.RunCompiledQuery, request.toByteArray()));
		return queryResult;
	}

	@Override
	public Future<QueryResult> callAsync(RunCompiledQueryRequest request, ApiConfig apiConfig) {
		QueryResult response = new QueryResult();
		return new ProtocolMessageFuture<QueryResult>(ApiProxy.makeAsyncCall(datastore_v3.packageName, datastore_v3.methodName.RunCompiledQuery, request.toByteArray(), apiConfig),
				response);
	}
}
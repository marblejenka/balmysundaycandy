package balmysundaycandy.more.low.level.operations.memcached.impl;

import balmysundaycandy.core.future.GeneratedMessageFuture;
import balmysundaycandy.core.operations.OperationNamespaces.memcache;
import balmysundaycandy.more.low.level.operations.memcached.MemcacheOperation;

import com.google.appengine.api.memcache.MemcacheServicePb.MemcacheBatchIncrementRequest;
import com.google.appengine.api.memcache.MemcacheServicePb.MemcacheBatchIncrementResponse;
import com.google.appengine.repackaged.com.google.protobuf.InvalidProtocolBufferException;
import com.google.appengine.repackaged.com.google.protobuf.GeneratedMessage.Builder;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;

public class BatchIncrementOperation extends MemcacheOperation<MemcacheBatchIncrementRequest, MemcacheBatchIncrementResponse> {

	@Override
	public MemcacheBatchIncrementResponse call(MemcacheBatchIncrementRequest request) {
		MemcacheBatchIncrementResponse.Builder response = MemcacheBatchIncrementResponse.newBuilder();
		try {
			response.mergeFrom(ApiProxy.makeSyncCall(memcache.packageName, memcache.methodName.BatchIncrement, request.toByteArray()));
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
		return response.build();
	}

	@SuppressWarnings("unchecked")
	@Override
	public GeneratedMessageFuture<MemcacheBatchIncrementResponse, Builder<?>> callAsync(MemcacheBatchIncrementRequest request, ApiConfig apiConfig) {
		MemcacheBatchIncrementResponse.Builder builder = MemcacheBatchIncrementResponse.newBuilder();
		return new GeneratedMessageFuture(ApiProxy.makeAsyncCall(memcache.packageName, memcache.methodName.BatchIncrement, request.toByteArray(), apiConfig), builder);
	}

}

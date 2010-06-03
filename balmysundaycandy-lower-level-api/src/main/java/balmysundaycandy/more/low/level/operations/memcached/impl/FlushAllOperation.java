/**
 * 
 */
package balmysundaycandy.more.low.level.operations.memcached.impl;

import balmysundaycandy.core.future.GeneratedMessageFuture;
import balmysundaycandy.core.operations.OperationNamespaces.memcache;
import balmysundaycandy.more.low.level.operations.memcached.MemcacheOperation;

import com.google.appengine.api.memcache.MemcacheServicePb.MemcacheFlushRequest;
import com.google.appengine.api.memcache.MemcacheServicePb.MemcacheFlushResponse;
import com.google.appengine.repackaged.com.google.protobuf.InvalidProtocolBufferException;
import com.google.appengine.repackaged.com.google.protobuf.GeneratedMessage.Builder;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;

public final class FlushAllOperation extends MemcacheOperation<MemcacheFlushRequest, MemcacheFlushResponse> {

	@Override
	public MemcacheFlushResponse call(MemcacheFlushRequest request) {
		MemcacheFlushResponse.Builder response = MemcacheFlushResponse.newBuilder();
		try {
			response.mergeFrom(ApiProxy.makeSyncCall(memcache.packageName, memcache.methodName.FlashAll, request.toByteArray()));
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
		return response.build();
	}

	@SuppressWarnings("unchecked")
	@Override
	public GeneratedMessageFuture<MemcacheFlushResponse, Builder<?>> callAsync(MemcacheFlushRequest request, ApiConfig apiConfig) {
		MemcacheFlushResponse.Builder builder = MemcacheFlushResponse.newBuilder();
		return new GeneratedMessageFuture(ApiProxy.makeAsyncCall(memcache.packageName, memcache.methodName.FlashAll, request.toByteArray(), apiConfig), builder);
	}
}
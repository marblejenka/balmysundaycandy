package balmysundaycandy.more.low.level.operations.memcached.impl;

import balmysundaycandy.core.future.GeneratedMessageFuture;
import balmysundaycandy.core.operations.OperationNamespaces.memcache;
import balmysundaycandy.more.low.level.operations.memcached.MemcacheOperation;

import com.google.appengine.api.memcache.MemcacheServicePb.MemcacheGrabTailRequest;
import com.google.appengine.api.memcache.MemcacheServicePb.MemcacheGrabTailResponse;
import com.google.appengine.repackaged.com.google.protobuf.InvalidProtocolBufferException;
import com.google.appengine.repackaged.com.google.protobuf.GeneratedMessage.Builder;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;

public class GrabTailOperation extends MemcacheOperation<MemcacheGrabTailRequest, MemcacheGrabTailResponse> {

	@Override
	public MemcacheGrabTailResponse call(MemcacheGrabTailRequest request) {
		MemcacheGrabTailResponse.Builder response = MemcacheGrabTailResponse.newBuilder();
		try {
			response.mergeFrom(ApiProxy.makeSyncCall(memcache.packageName, memcache.methodName.GrabTail, request.toByteArray()));
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
		return response.build();
	}

	@SuppressWarnings("unchecked")
	@Override
	public GeneratedMessageFuture<MemcacheGrabTailResponse, Builder<?>> callAsync(MemcacheGrabTailRequest request, ApiConfig apiConfig) {
		MemcacheGrabTailResponse.Builder builder = MemcacheGrabTailResponse.newBuilder();
		return new GeneratedMessageFuture(ApiProxy.makeAsyncCall(memcache.packageName, memcache.methodName.GrabTail, request.toByteArray(), apiConfig), builder);
	}

}

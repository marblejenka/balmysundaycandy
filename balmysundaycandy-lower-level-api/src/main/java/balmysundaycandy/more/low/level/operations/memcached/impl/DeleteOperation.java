/**
 * 
 */
package balmysundaycandy.more.low.level.operations.memcached.impl;

import balmysundaycandy.core.future.GeneratedMessageFuture;
import balmysundaycandy.core.operations.OperationNamespaces.memcache;
import balmysundaycandy.more.low.level.operations.memcached.MemcacheOperation;

import com.google.appengine.api.memcache.MemcacheServicePb.MemcacheDeleteRequest;
import com.google.appengine.api.memcache.MemcacheServicePb.MemcacheDeleteResponse;
import com.google.appengine.repackaged.com.google.protobuf.InvalidProtocolBufferException;
import com.google.appengine.repackaged.com.google.protobuf.GeneratedMessage.Builder;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;

public final class DeleteOperation extends MemcacheOperation<MemcacheDeleteRequest, MemcacheDeleteResponse> {

	@Override
	public MemcacheDeleteResponse call(MemcacheDeleteRequest request) {
		MemcacheDeleteResponse.Builder response = MemcacheDeleteResponse.newBuilder();
		try {
			response.mergeFrom(ApiProxy.makeSyncCall(memcache.packageName, memcache.methodName.Delete, request.toByteArray()));
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
		return response.build();
	}

	@SuppressWarnings("unchecked")
	@Override
	public GeneratedMessageFuture<MemcacheDeleteResponse, Builder<?>> callAsync(MemcacheDeleteRequest request, ApiConfig apiConfig) {
		MemcacheDeleteResponse.Builder builder = MemcacheDeleteResponse.newBuilder();
		return new GeneratedMessageFuture(ApiProxy.makeAsyncCall(memcache.packageName, memcache.methodName.Delete, request.toByteArray(), apiConfig), builder);
	}

}
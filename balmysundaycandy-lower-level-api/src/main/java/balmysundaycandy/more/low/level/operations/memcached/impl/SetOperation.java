/**
 * 
 */
package balmysundaycandy.more.low.level.operations.memcached.impl;

import balmysundaycandy.core.future.GeneratedMessageFuture;
import balmysundaycandy.core.operations.OperationNamespaces.memcache;
import balmysundaycandy.more.low.level.operations.memcached.MemcacheOperation;

import com.google.appengine.api.memcache.MemcacheServicePb.MemcacheSetRequest;
import com.google.appengine.api.memcache.MemcacheServicePb.MemcacheSetResponse;
import com.google.appengine.repackaged.com.google.protobuf.InvalidProtocolBufferException;
import com.google.appengine.repackaged.com.google.protobuf.GeneratedMessage.Builder;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;

public final class SetOperation extends MemcacheOperation<MemcacheSetRequest, MemcacheSetResponse> {

	@Override
	public MemcacheSetResponse call(MemcacheSetRequest request) {
		MemcacheSetResponse.Builder response = MemcacheSetResponse.newBuilder();
		try {
			response.mergeFrom(ApiProxy.makeSyncCall(memcache.packageName, memcache.methodName.Set, request.toByteArray()));
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response.build();
	}

	@SuppressWarnings("unchecked")
	@Override
	public GeneratedMessageFuture<MemcacheSetResponse, Builder<?>> callAsync(MemcacheSetRequest request, ApiConfig apiConfig) {
		MemcacheSetResponse.Builder builder = MemcacheSetResponse.newBuilder();
		return new GeneratedMessageFuture(ApiProxy.makeAsyncCall(memcache.packageName, memcache.methodName.Set, request.toByteArray(), apiConfig), builder);
	}

}
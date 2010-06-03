/**
 * 
 */
package balmysundaycandy.more.low.level.operations.memcached.impl;

import balmysundaycandy.core.future.GeneratedMessageFuture;
import balmysundaycandy.core.operations.OperationNamespaces.memcache;
import balmysundaycandy.more.low.level.operations.memcached.MemcacheOperation;

import com.google.appengine.api.memcache.MemcacheServicePb.MemcacheGetRequest;
import com.google.appengine.api.memcache.MemcacheServicePb.MemcacheGetResponse;
import com.google.appengine.repackaged.com.google.protobuf.InvalidProtocolBufferException;
import com.google.appengine.repackaged.com.google.protobuf.GeneratedMessage.Builder;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;

public final class GetOperation extends MemcacheOperation<MemcacheGetRequest, MemcacheGetResponse> {

	@Override
	public MemcacheGetResponse call(MemcacheGetRequest request) {
		MemcacheGetResponse.Builder response = MemcacheGetResponse.newBuilder();
		try {
			response.mergeFrom(ApiProxy.makeSyncCall(memcache.packageName, memcache.methodName.Get, request.toByteArray()));
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response.build();
	}

	@SuppressWarnings("unchecked")
	@Override
	public GeneratedMessageFuture<MemcacheGetResponse, Builder<?>> callAsync(MemcacheGetRequest request, ApiConfig apiConfig) {
		MemcacheGetResponse.Builder builder = MemcacheGetResponse.newBuilder();
		return new GeneratedMessageFuture(ApiProxy.makeAsyncCall(memcache.packageName, memcache.methodName.Get, request.toByteArray(), apiConfig), builder);
	}
}
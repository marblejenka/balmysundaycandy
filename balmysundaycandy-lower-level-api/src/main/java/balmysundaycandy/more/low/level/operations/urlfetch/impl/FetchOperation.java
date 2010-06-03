/**
 * 
 */
package balmysundaycandy.more.low.level.operations.urlfetch.impl;

import balmysundaycandy.core.future.GeneratedMessageFuture;
import balmysundaycandy.core.operations.OperationNamespaces.urlfetch;
import balmysundaycandy.more.low.level.operations.urlfetch.UrlfetchOperation;

import com.google.appengine.api.urlfetch.URLFetchServicePb.URLFetchRequest;
import com.google.appengine.api.urlfetch.URLFetchServicePb.URLFetchResponse;
import com.google.appengine.repackaged.com.google.protobuf.InvalidProtocolBufferException;
import com.google.appengine.repackaged.com.google.protobuf.GeneratedMessage.Builder;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.apphosting.api.ApiProxy.ApiProxyException;

public final class FetchOperation extends UrlfetchOperation<URLFetchRequest, URLFetchResponse> {

	@Override
	public URLFetchResponse call(URLFetchRequest request) {
		URLFetchResponse.Builder response = URLFetchResponse.newBuilder();
		try {
			response.mergeFrom(ApiProxy.makeSyncCall(urlfetch.packageName, urlfetch.methodName.Fetch, request.toByteArray()));
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		} catch (ApiProxyException e) {
			e.printStackTrace();
		}
		return response.build();
	}

	@SuppressWarnings("unchecked")
	@Override
	public GeneratedMessageFuture<URLFetchResponse, Builder<?>> callAsync(URLFetchRequest request, ApiConfig apiConfig) {
		URLFetchResponse.Builder builder = URLFetchResponse.newBuilder();
		return new GeneratedMessageFuture(ApiProxy.makeAsyncCall(urlfetch.packageName, urlfetch.methodName.Fetch, request.toByteArray(), apiConfig), builder);
	}
}
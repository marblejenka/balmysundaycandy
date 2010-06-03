/**
 * 
 */
package balmysundaycandy.more.low.level.operations.image.impl;

import java.util.concurrent.Future;

import balmysundaycandy.core.future.ProtocolMessageFuture;
import balmysundaycandy.core.operations.OperationNamespaces.images;
import balmysundaycandy.more.low.level.operations.image.ImageOperation;

import com.google.appengine.api.images.ImagesServicePb.ImagesTransformRequest;
import com.google.appengine.api.images.ImagesServicePb.ImagesTransformResponse;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;

public final class TransformOperation extends ImageOperation<ImagesTransformRequest, ImagesTransformResponse> {

	@Override
	public ImagesTransformResponse call(ImagesTransformRequest request) {
		ImagesTransformResponse response = new ImagesTransformResponse();
		response.mergeFrom(ApiProxy.makeSyncCall(images.packageName, images.methodName.Transform, request.toByteArray()));
		return response;
	}

	@Override
	public Future<ImagesTransformResponse> callAsync(ImagesTransformRequest request, ApiConfig apiConfig) {
		ImagesTransformResponse response = new ImagesTransformResponse();
		return new ProtocolMessageFuture<ImagesTransformResponse>(ApiProxy.makeAsyncCall(images.packageName, images.methodName.Transform, request.toByteArray(), apiConfig),
				response);
	}
}
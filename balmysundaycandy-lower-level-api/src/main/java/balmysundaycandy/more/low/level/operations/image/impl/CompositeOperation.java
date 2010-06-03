/**
 * 
 */
package balmysundaycandy.more.low.level.operations.image.impl;

import java.util.concurrent.Future;

import balmysundaycandy.core.future.ProtocolMessageFuture;
import balmysundaycandy.core.operations.OperationNamespaces.images;
import balmysundaycandy.more.low.level.operations.image.ImageOperation;

import com.google.appengine.api.images.ImagesServicePb.ImagesCompositeRequest;
import com.google.appengine.api.images.ImagesServicePb.ImagesCompositeResponse;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;

public final class CompositeOperation extends ImageOperation<ImagesCompositeRequest, ImagesCompositeResponse> {

	@Override
	public ImagesCompositeResponse call(ImagesCompositeRequest request) {
		ImagesCompositeResponse response = new ImagesCompositeResponse();
		response.mergeFrom(ApiProxy.makeSyncCall(images.packageName, images.methodName.Composite, request.toByteArray()));
		return response;
	}

	@Override
	public Future<ImagesCompositeResponse> callAsync(ImagesCompositeRequest request, ApiConfig apiConfig) {
		ImagesCompositeResponse response = new ImagesCompositeResponse();
		return new ProtocolMessageFuture<ImagesCompositeResponse>(ApiProxy.makeAsyncCall(images.packageName, images.methodName.Composite, request.toByteArray(), apiConfig),
				response);
	}
}
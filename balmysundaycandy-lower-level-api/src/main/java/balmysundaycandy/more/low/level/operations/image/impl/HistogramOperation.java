/**
 * 
 */
package balmysundaycandy.more.low.level.operations.image.impl;

import java.util.concurrent.Future;

import balmysundaycandy.core.future.ProtocolMessageFuture;
import balmysundaycandy.core.operations.OperationNamespaces.images;
import balmysundaycandy.more.low.level.operations.image.ImageOperation;

import com.google.appengine.api.images.ImagesServicePb.ImagesHistogramRequest;
import com.google.appengine.api.images.ImagesServicePb.ImagesHistogramResponse;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;

public final class HistogramOperation extends ImageOperation<ImagesHistogramRequest, ImagesHistogramResponse> {

	@Override
	public ImagesHistogramResponse call(ImagesHistogramRequest request) {
		ImagesHistogramResponse response = new ImagesHistogramResponse();
		response.mergeFrom(ApiProxy.makeSyncCall(images.packageName, images.methodName.Histogram, request.toByteArray()));
		return response;
	}

	@Override
	public Future<ImagesHistogramResponse> callAsync(ImagesHistogramRequest request, ApiConfig apiConfig) {
		ImagesHistogramResponse response = new ImagesHistogramResponse();
		return new ProtocolMessageFuture<ImagesHistogramResponse>(ApiProxy.makeAsyncCall(images.packageName, images.methodName.Histogram, request.toByteArray(), apiConfig),
				response);
	}
}
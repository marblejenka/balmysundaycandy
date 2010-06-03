package balmysundaycandy.more.low.level.operations.blobstore.impl;

import java.util.concurrent.Future;

import balmysundaycandy.core.future.ProtocolMessageFuture;
import balmysundaycandy.core.operations.OperationNamespaces.blobstore;
import balmysundaycandy.more.low.level.operations.blobstore.BlobstoreOperation;

import com.google.appengine.api.blobstore.BlobstoreServicePb.CreateUploadURLRequest;
import com.google.appengine.api.blobstore.BlobstoreServicePb.CreateUploadURLResponse;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;

/**
 * create upload url operatin.
 * 
 * @author marblejenka
 * 
 */
public class CreateUploadURLOperation extends BlobstoreOperation<CreateUploadURLRequest, CreateUploadURLResponse> {

	@Override
	public CreateUploadURLResponse call(CreateUploadURLRequest request) {
		CreateUploadURLResponse response = new CreateUploadURLResponse();
		response.mergeFrom(ApiProxy.makeSyncCall(blobstore.packageName, blobstore.methodName.CreateUploadURL, request.toByteArray()));
		return response;
	}

	@Override
	public Future<CreateUploadURLResponse> callAsync(CreateUploadURLRequest request, ApiConfig apiConfig) {
		CreateUploadURLResponse response = new CreateUploadURLResponse();
		return new ProtocolMessageFuture<CreateUploadURLResponse>(ApiProxy.makeAsyncCall(blobstore.packageName, blobstore.methodName.CreateUploadURL, request.toByteArray(),
				apiConfig), response);
	}
}

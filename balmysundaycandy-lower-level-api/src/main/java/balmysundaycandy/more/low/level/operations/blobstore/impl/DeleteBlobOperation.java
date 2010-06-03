package balmysundaycandy.more.low.level.operations.blobstore.impl;

import java.util.concurrent.Future;

import balmysundaycandy.core.future.ProtocolMessageFuture;
import balmysundaycandy.core.operations.OperationNamespaces.blobstore;
import balmysundaycandy.more.low.level.operations.blobstore.BlobstoreOperation;

import com.google.appengine.api.blobstore.BlobstoreServicePb.DeleteBlobRequest;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiBasePb.VoidProto;
import com.google.apphosting.api.ApiProxy.ApiConfig;

/**
 * delete blob operation.
 * 
 * @author marblejenka
 * 
 */
public class DeleteBlobOperation extends BlobstoreOperation<DeleteBlobRequest, VoidProto> {

	@Override
	public VoidProto call(DeleteBlobRequest request) {
		VoidProto response = new VoidProto();
		response.mergeFrom(ApiProxy.makeSyncCall(blobstore.packageName, blobstore.methodName.DeleteBlob, request.toByteArray()));
		return response;
	}

	@Override
	public Future<VoidProto> callAsync(DeleteBlobRequest request, ApiConfig apiConfig) {
		VoidProto response = new VoidProto();
		return new ProtocolMessageFuture<VoidProto>(ApiProxy.makeAsyncCall(blobstore.packageName, blobstore.methodName.DeleteBlob, request.toByteArray(), apiConfig), response);
	}
}

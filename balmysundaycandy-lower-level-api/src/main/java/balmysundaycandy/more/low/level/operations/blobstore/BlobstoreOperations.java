package balmysundaycandy.more.low.level.operations.blobstore;

import balmysundaycandy.more.low.level.operations.blobstore.impl.CreateUploadURLOperation;
import balmysundaycandy.more.low.level.operations.blobstore.impl.DeleteBlobOperation;

public class BlobstoreOperations {
	public static final CreateUploadURLOperation createUploadUrl = new CreateUploadURLOperation();
	public static final DeleteBlobOperation deleteBlob = new DeleteBlobOperation();
}

package balmysundaycandy.more.low.level.operations.blobstore.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.concurrent.*;

import org.junit.*;

import balmysundaycandy.more.low.level.operations.blobstore.*;

import com.google.appengine.api.blobstore.BlobstoreServicePb.*;
import com.google.apphosting.api.ApiBasePb.*;
import com.google.apphosting.api.ApiProxy.*;

public class DeleteBlobOperationTest {

	@Test
	public void testCallDeleteBlobRequest() {
		DeleteBlobRequest request = new DeleteBlobRequest();
		request.addBlobKey("TEST");

		VoidProto response = BlobstoreOperations.deleteBlob.call(request);

		assertThat(response, is(not(nullValue())));
	}

	@Test
	public void testCallAsyncDeleteBlobRequestApiConfig() throws InterruptedException, ExecutionException {
		DeleteBlobRequest request = new DeleteBlobRequest();
		request.addBlobKey("TEST");

		Future<VoidProto> response = BlobstoreOperations.deleteBlob.callAsync(request, new ApiConfig());

		assertThat(response, is(not(nullValue())));
		assertThat(response.get(), is(not(nullValue())));
	}
}

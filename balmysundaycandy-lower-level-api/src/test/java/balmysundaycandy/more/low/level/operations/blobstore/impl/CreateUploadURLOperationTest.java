package balmysundaycandy.more.low.level.operations.blobstore.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.concurrent.*;

import org.junit.*;

import balmysundaycandy.core.test.*;
import balmysundaycandy.more.low.level.operations.blobstore.*;

import com.google.appengine.api.blobstore.BlobstoreServicePb.*;
import com.google.apphosting.api.ApiProxy.*;

public class CreateUploadURLOperationTest extends BlobstoreTestCase {

	@Test
	public void testCallCreateUploadURLRequest() {
		CreateUploadURLRequest request = new CreateUploadURLRequest();

		CreateUploadURLResponse response = BlobstoreOperations.createUploadUrl.call(request);

		assertThat(response, is(not(nullValue())));
	}

	@Test
	public void testCallAsyncCreateUploadURLRequestApiConfig() throws InterruptedException, ExecutionException {
		CreateUploadURLRequest request = new CreateUploadURLRequest();

		Future<CreateUploadURLResponse> response = BlobstoreOperations.createUploadUrl.callAsync(request, new ApiConfig());

		assertThat(response, is(not(nullValue())));
		assertThat(response.get(), is(not(nullValue())));
	}
}

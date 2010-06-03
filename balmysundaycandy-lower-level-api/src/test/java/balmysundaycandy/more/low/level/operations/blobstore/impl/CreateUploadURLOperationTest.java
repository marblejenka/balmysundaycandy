package balmysundaycandy.more.low.level.operations.blobstore.impl;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import balmysundaycandy.core.test.EnvironmentConfiguration;
import balmysundaycandy.core.test.TestEnvironmentUtils;
import balmysundaycandy.more.low.level.operations.blobstore.BlobstoreOperations;

import com.google.appengine.api.blobstore.BlobstoreServicePb.CreateUploadURLRequest;
import com.google.appengine.api.blobstore.BlobstoreServicePb.CreateUploadURLResponse;
import com.google.apphosting.api.ApiProxy.ApiConfig;

public class CreateUploadURLOperationTest {
	EnvironmentConfiguration environmentConfiguration = new EnvironmentConfiguration("", false, true);

	@Before
	public void setup() {
		TestEnvironmentUtils.setupEnvironment(environmentConfiguration);
	}

	@After
	public void teardown() {
		TestEnvironmentUtils.teardownEnvironment(environmentConfiguration);
	}

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

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

import com.google.appengine.api.blobstore.BlobstoreServicePb.DeleteBlobRequest;
import com.google.apphosting.api.ApiBasePb.VoidProto;
import com.google.apphosting.api.ApiProxy.ApiConfig;

public class DeleteBlobOperationTest {
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
	public void testCallDeleteBlobRequest() {
		DeleteBlobRequest request = new DeleteBlobRequest();
		request.addBlobKey("TEST");
		
		VoidProto response =  BlobstoreOperations.deleteBlob.call(request);
		
		assertThat(response, is(not(nullValue())));
	}

	@Test
	public void testCallAsyncDeleteBlobRequestApiConfig() throws InterruptedException, ExecutionException {
		DeleteBlobRequest request = new DeleteBlobRequest();
		request.addBlobKey("TEST");
		
		Future<VoidProto> response =  BlobstoreOperations.deleteBlob.callAsync(request, new ApiConfig());
		
		assertThat(response, is(not(nullValue())));
		assertThat(response.get(), is(not(nullValue())));
	}
}

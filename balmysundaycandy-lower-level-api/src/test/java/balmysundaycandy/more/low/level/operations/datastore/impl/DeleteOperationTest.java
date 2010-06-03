package balmysundaycandy.more.low.level.operations.datastore.impl;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import balmysundaycandy.core.test.EnvironmentConfiguration;
import balmysundaycandy.core.test.TestEnvironmentUtils;
import balmysundaycandy.more.low.level.operations.datastore.DatastoreOperations;

import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.apphosting.api.DatastorePb.DeleteRequest;
import com.google.apphosting.api.DatastorePb.DeleteResponse;

public class DeleteOperationTest {
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
	public void testCallDeleteRequest() {
		DeleteRequest request = new DeleteRequest();

		DeleteResponse response = DatastoreOperations.DELETE.call(request);

		assertThat(response, is(not(nullValue())));
	}

	@Test
	public void testCallAsyncDeleteRequestApiConfig() throws InterruptedException, ExecutionException {
		DeleteRequest request = new DeleteRequest();

		Future<DeleteResponse> response = DatastoreOperations.DELETE.callAsync(request, new ApiConfig());

		assertThat(response.get(), is(not(nullValue())));
	}

}

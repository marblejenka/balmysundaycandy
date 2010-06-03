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
import com.google.apphosting.api.DatastorePb.AllocateIdsRequest;
import com.google.apphosting.api.DatastorePb.AllocateIdsResponse;

public class AllocateIdsOperationTest {
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
	public void testCallAllocateIdsRequest() {
		AllocateIdsRequest request = new AllocateIdsRequest();
		request.setSize(10);

		AllocateIdsResponse response = DatastoreOperations.ALLOCATE_IDS.call(request);

		assertThat(response, is(not(nullValue())));
		assertThat(response.getStart(), is(11L));
		assertThat(response.getEnd(), is(20L));
	}

	@Test
	public void testCallAsyncAllocateIdsRequestApiConfig() throws InterruptedException, ExecutionException {
		AllocateIdsRequest request = new AllocateIdsRequest();
		request.setSize(10);

		Future<AllocateIdsResponse> response = DatastoreOperations.ALLOCATE_IDS.callAsync(request, new ApiConfig());

		assertThat(response, is(not(nullValue())));
		assertThat(response.get().getStart(), is(11L));
		assertThat(response.get().getEnd(), is(20L));
	}
}

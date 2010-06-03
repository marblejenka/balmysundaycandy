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

import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiBasePb.VoidProto;
import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.storage.onestore.v3.OnestoreEntity.CompositeIndex;

public class DeleteIndexOperationTest {
	EnvironmentConfiguration environmentConfiguration = new EnvironmentConfiguration("", false, true);

	@Before
	public void setup() {
		TestEnvironmentUtils.setupEnvironment(environmentConfiguration);
	}

	@After
	public void teardown() {
		TestEnvironmentUtils.teardownEnvironment(environmentConfiguration);
	}

	@SuppressWarnings("deprecation")
	@Test(expected = UnsupportedOperationException.class)
	public void testCallCompositeIndex() {
		CompositeIndex request = new CompositeIndex();
		request.setAppId(ApiProxy.getCurrentEnvironment().getAppId());

		VoidProto response = DatastoreOperations.DELETE_INDEX.call(request);

		assertThat(response, is(not(nullValue())));
	}

	@SuppressWarnings("deprecation")
	@Test(expected = ExecutionException.class) // cause execution exception, because of unsupported operation exception thrown in future.get method.
	public void testCallAsyncCompositeIndexApiConfig() throws InterruptedException, ExecutionException {
		CompositeIndex request = new CompositeIndex();
		request.setAppId(ApiProxy.getCurrentEnvironment().getAppId());

		Future<VoidProto> response = DatastoreOperations.DELETE_INDEX.callAsync(request, new ApiConfig());

		assertThat(response.get(), is(not(nullValue())));
	}

}

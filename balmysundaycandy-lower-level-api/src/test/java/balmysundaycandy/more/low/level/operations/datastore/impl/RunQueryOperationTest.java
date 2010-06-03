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
import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.apphosting.api.DatastorePb.QueryResult;

public class RunQueryOperationTest {
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
	public void testCallQuery() {
		com.google.apphosting.api.DatastorePb.Query request = new com.google.apphosting.api.DatastorePb.Query();
		request.setApp(ApiProxy.getCurrentEnvironment().getAppId());
		request.setKind("Foo");

		QueryResult response = DatastoreOperations.RUN_QUERY.call(request);

		assertThat(response, is(not(nullValue())));
	}

	@Test
	public void testCallAsyncQueryApiConfig() throws InterruptedException, ExecutionException {
		com.google.apphosting.api.DatastorePb.Query request = new com.google.apphosting.api.DatastorePb.Query();
		request.setApp(ApiProxy.getCurrentEnvironment().getAppId());
		request.setKind("Foo");

		Future<QueryResult> response = DatastoreOperations.RUN_QUERY.callAsync(request, new ApiConfig());

		assertThat(response, is(not(nullValue())));
		assertThat(response.get(), is(not(nullValue())));
	}

}

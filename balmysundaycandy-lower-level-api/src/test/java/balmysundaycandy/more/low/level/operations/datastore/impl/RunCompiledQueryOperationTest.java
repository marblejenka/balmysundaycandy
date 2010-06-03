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
import com.google.apphosting.api.ApiProxy.CallNotFoundException;
import com.google.apphosting.api.DatastorePb.QueryResult;
import com.google.apphosting.api.DatastorePb.RunCompiledQueryRequest;

public class RunCompiledQueryOperationTest {
	EnvironmentConfiguration environmentConfiguration = new EnvironmentConfiguration("", false, true);

	@Before
	public void setup() {
		TestEnvironmentUtils.setupEnvironment(environmentConfiguration);
	}

	@After
	public void teardown() {
		TestEnvironmentUtils.teardownEnvironment(environmentConfiguration);
	}

	@Test(expected = CallNotFoundException.class)
	public void testCallRunCompiledQueryRequest() {
		com.google.apphosting.api.DatastorePb.Query query = new com.google.apphosting.api.DatastorePb.Query();
		query.setApp(ApiProxy.getCurrentEnvironment().getAppId());
		query.setKind("Foo");

		RunCompiledQueryRequest request = new RunCompiledQueryRequest();
		request.setApp(ApiProxy.getCurrentEnvironment().getAppId());
		request.setOriginalQuery(query);

		QueryResult response = DatastoreOperations.RUN_COMPILES_QUERY.call(request);

		assertThat(response, is(not(nullValue())));
	}

	@Test(expected = ExecutionException.class)
	public void testCallAsyncRunCompiledQueryRequestApiConfig() throws InterruptedException, ExecutionException {
		com.google.apphosting.api.DatastorePb.Query query = new com.google.apphosting.api.DatastorePb.Query();
		query.setApp(ApiProxy.getCurrentEnvironment().getAppId());
		query.setKind("Foo");

		RunCompiledQueryRequest request = new RunCompiledQueryRequest();
		request.setApp(ApiProxy.getCurrentEnvironment().getAppId());
		request.setOriginalQuery(query);

		Future<QueryResult> response = DatastoreOperations.RUN_COMPILES_QUERY.callAsync(request, new ApiConfig());

		assertThat(response, is(not(nullValue())));
		assertThat(response.get(), is(not(nullValue())));
	}
}

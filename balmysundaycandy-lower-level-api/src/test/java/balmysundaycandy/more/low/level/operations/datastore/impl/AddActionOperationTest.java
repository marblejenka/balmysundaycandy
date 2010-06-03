package balmysundaycandy.more.low.level.operations.datastore.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.concurrent.Future;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import balmysundaycandy.core.test.EnvironmentConfiguration;
import balmysundaycandy.core.test.TestEnvironmentUtils;
import balmysundaycandy.more.low.level.operations.datastore.DatastoreOperations;

import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.apphosting.api.DatastorePb.AddActionsRequest;
import com.google.apphosting.api.DatastorePb.AddActionsResponse;

public class AddActionOperationTest {
	EnvironmentConfiguration environmentConfiguration = new EnvironmentConfiguration("", false, true);

	@Before
	public void setup() {
		TestEnvironmentUtils.setupEnvironment(environmentConfiguration);
	}

	@After
	public void teardown() {
		TestEnvironmentUtils.teardownEnvironment(environmentConfiguration);
	}

	@Test(expected = NoClassDefFoundError.class)
	public void testCallActionRequest() {
		AddActionsResponse response = DatastoreOperations.ADD_ACTION.call(new AddActionsRequest());
		assertThat(response, is(not(nullValue()))); // cannnot get here
	}

	@Test(expected = NoClassDefFoundError.class)
	public void testCallAsyncActionRequestApiConfig() {
		Future<AddActionsResponse> response = DatastoreOperations.ADD_ACTION.callAsync(new AddActionsRequest(), new ApiConfig());
		assertThat(response, is(not(nullValue()))); // cannnot get here
	}
}

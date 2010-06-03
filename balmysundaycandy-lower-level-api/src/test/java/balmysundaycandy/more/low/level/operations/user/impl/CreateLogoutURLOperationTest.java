package balmysundaycandy.more.low.level.operations.user.impl;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import balmysundaycandy.core.test.EnvironmentConfiguration;
import balmysundaycandy.core.test.TestEnvironmentUtils;
import balmysundaycandy.more.low.level.operations.user.UserOperations;

import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.apphosting.api.UserServicePb.CreateLogoutURLRequest;
import com.google.apphosting.api.UserServicePb.CreateLogoutURLResponse;

public class CreateLogoutURLOperationTest {
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
	public void testCallCreateLogoutURLRequest() {
		CreateLogoutURLRequest request = new CreateLogoutURLRequest();
		{
			request.setAuthDomain(""); // 適当でも動く希有な存在
			request.setDestinationUrl("");
		}
		CreateLogoutURLResponse response = UserOperations.CREATE_LOGOUT_URL.call(request);

		assertThat(response, is(not(nullValue())));
	}

	@Test
	public void testCallAsyncCreateLogoutURLRequestApiConfig() throws InterruptedException, ExecutionException {
		CreateLogoutURLRequest request = new CreateLogoutURLRequest();
		{
			request.setAuthDomain(""); // 適当でも動く希有な存在
			request.setDestinationUrl("");
		}
		Future<CreateLogoutURLResponse> response = UserOperations.CREATE_LOGOUT_URL.callAsync(request, new ApiConfig());

		assertThat(response, is(not(nullValue())));
		assertThat(response.get(), is(not(nullValue())));
	}
}

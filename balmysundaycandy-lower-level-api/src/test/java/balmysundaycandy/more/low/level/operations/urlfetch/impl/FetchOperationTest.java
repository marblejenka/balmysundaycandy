package balmysundaycandy.more.low.level.operations.urlfetch.impl;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import balmysundaycandy.core.test.EnvironmentConfiguration;
import balmysundaycandy.core.test.TestEnvironmentUtils;
import balmysundaycandy.more.low.level.operations.urlfetch.UrlfetchOperations;

import com.google.appengine.api.urlfetch.URLFetchServicePb.URLFetchRequest;
import com.google.appengine.api.urlfetch.URLFetchServicePb.URLFetchResponse;
import com.google.appengine.api.urlfetch.URLFetchServicePb.URLFetchRequest.RequestMethod;
import com.google.apphosting.api.ApiProxy.ApiConfig;

public class FetchOperationTest {
	private final String url = "http://code.google.com/p/balmysundaycandy/";
	
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
	public void testCallURLFetchRequest() {
		URLFetchRequest request = URLFetchRequest.newBuilder().setUrl(url).setMethod(RequestMethod.POST).build();

		URLFetchResponse response = UrlfetchOperations.FETCH.call(request);

		assertThat(response, is(not(nullValue())));
	}

	@Test
	public void testCallAsyncURLFetchRequestApiConfig() throws InterruptedException, ExecutionException {
		ApiConfig apiConfig = new ApiConfig();
		apiConfig.setDeadlineInSeconds(10d);

		URLFetchRequest request = URLFetchRequest.newBuilder().setUrl(url).setMethod(RequestMethod.POST).build();

		Future<URLFetchResponse> response = UrlfetchOperations.FETCH.callAsync(request, apiConfig);

		assertThat(response, is(not(nullValue())));
		assertThat(response.get(), is(not(nullValue())));
	}
}

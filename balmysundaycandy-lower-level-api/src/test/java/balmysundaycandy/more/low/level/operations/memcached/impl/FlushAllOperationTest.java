package balmysundaycandy.more.low.level.operations.memcached.impl;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import balmysundaycandy.core.test.EnvironmentConfiguration;
import balmysundaycandy.core.test.TestEnvironmentUtils;
import balmysundaycandy.more.low.level.operations.memcached.MemcacheOperations;

import com.google.appengine.api.memcache.MemcacheServicePb.MemcacheFlushRequest;
import com.google.appengine.api.memcache.MemcacheServicePb.MemcacheFlushResponse;
import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.apphosting.api.ApiProxy.CallNotFoundException;

public class FlushAllOperationTest {
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
	public void testCallMemcacheFlushRequest() {
		MemcacheFlushRequest request = MemcacheFlushRequest.newBuilder().build();

		MemcacheFlushResponse response = MemcacheOperations.FLUSH_ALL.call(request);

		assertThat(response, is(not(nullValue())));
	}

	@Test(expected = ExecutionException.class)
	public void testCallAsyncMemcacheFlushRequestApiConfig() throws InterruptedException, ExecutionException {
		MemcacheFlushRequest request = MemcacheFlushRequest.newBuilder().build();

		Future<MemcacheFlushResponse> response = MemcacheOperations.FLUSH_ALL.callAsync(request, new ApiConfig());

		assertThat(response, is(not(nullValue())));
		assertThat(response.get(), is(not(nullValue())));
	}
}

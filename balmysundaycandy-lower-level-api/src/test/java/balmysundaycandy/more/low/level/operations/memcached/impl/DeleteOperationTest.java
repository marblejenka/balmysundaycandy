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

import com.google.appengine.api.memcache.MemcacheServicePb.MemcacheDeleteRequest;
import com.google.appengine.api.memcache.MemcacheServicePb.MemcacheDeleteResponse;
import com.google.apphosting.api.ApiProxy.ApiConfig;

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
	public void testCallMemcacheDeleteRequest() {
		MemcacheDeleteRequest request = MemcacheDeleteRequest.newBuilder().build();
		
		MemcacheDeleteResponse response = MemcacheOperations.DELETE.call(request);
		
		assertThat(response, is(not(nullValue())));
	}

	@Test
	public void testCallAsyncMemcacheDeleteRequestApiConfig() throws InterruptedException, ExecutionException {
		MemcacheDeleteRequest request = MemcacheDeleteRequest.newBuilder().build();
		
		Future<MemcacheDeleteResponse> response = MemcacheOperations.DELETE.callAsync(request, new ApiConfig());
		
		assertThat(response, is(not(nullValue())));
		assertThat(response.get(), is(not(nullValue())));
	}
}

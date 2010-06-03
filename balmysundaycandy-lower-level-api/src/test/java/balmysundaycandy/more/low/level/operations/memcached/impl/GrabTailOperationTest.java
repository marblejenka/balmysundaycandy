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

import com.google.appengine.api.memcache.MemcacheServicePb.MemcacheGrabTailRequest;
import com.google.appengine.api.memcache.MemcacheServicePb.MemcacheGrabTailResponse;
import com.google.apphosting.api.ApiProxy.ApiConfig;

public class GrabTailOperationTest {
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
	public void testCallMemcacheGrabTailRequest() {
		MemcacheGrabTailRequest request = MemcacheGrabTailRequest.newBuilder().setItemCount(10).build();
		
		MemcacheGrabTailResponse response = MemcacheOperations.GRAB_TAIL.call(request);
		
		assertThat(response, is(not(nullValue())));
	}

	@Test
	public void testCallAsyncMemcacheGrabTailRequestApiConfig() throws InterruptedException, ExecutionException {
		MemcacheGrabTailRequest request = MemcacheGrabTailRequest.newBuilder().setItemCount(10).build();
		
		Future<MemcacheGrabTailResponse> response = MemcacheOperations.GRAB_TAIL.callAsync(request, new ApiConfig());
		
		assertThat(response, is(not(nullValue())));
		assertThat(response.get(), is(not(nullValue())));
	}
}

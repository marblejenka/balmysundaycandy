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

import com.google.appengine.api.memcache.MemcacheServicePb.MemcacheGetRequest;
import com.google.appengine.api.memcache.MemcacheServicePb.MemcacheGetResponse;
import com.google.appengine.repackaged.com.google.protobuf.ByteString;
import com.google.apphosting.api.ApiProxy.ApiConfig;

public class GetOperationTest {
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
	public void testCallMemcacheGetRequest() {
		MemcacheGetRequest request = MemcacheGetRequest.newBuilder().addKey(ByteString.EMPTY).build();
		
		MemcacheGetResponse response = MemcacheOperations.GET.call(request);
		
		assertThat(response, is(not(nullValue())));
	}

	@Test
	public void testCallAsyncMemcacheGetRequestApiConfig() throws InterruptedException, ExecutionException {
		MemcacheGetRequest request = MemcacheGetRequest.newBuilder().addKey(ByteString.EMPTY).build();
		
		Future<MemcacheGetResponse> response = MemcacheOperations.GET.callAsync(request, new ApiConfig());
		
		assertThat(response, is(not(nullValue())));
		assertThat(response.get(), is(not(nullValue())));
	}
}

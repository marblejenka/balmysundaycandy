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

import com.google.appengine.api.memcache.MemcacheServicePb.MemcacheBatchIncrementRequest;
import com.google.appengine.api.memcache.MemcacheServicePb.MemcacheBatchIncrementResponse;
import com.google.appengine.api.memcache.MemcacheServicePb.MemcacheIncrementRequest;
import com.google.appengine.repackaged.com.google.protobuf.ByteString;
import com.google.apphosting.api.ApiProxy.ApiConfig;

public class BatchIncrementOperationTest {
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
	public void testCallMemcacheBatchIncrementRequest() {
		MemcacheIncrementRequest value = MemcacheIncrementRequest.newBuilder().setKey(ByteString.EMPTY).build();

		MemcacheBatchIncrementRequest request = MemcacheBatchIncrementRequest.newBuilder().addItem(value).build();

		MemcacheBatchIncrementResponse response = MemcacheOperations.BATCH_INVREMENT.call(request);

		assertThat(response, is(not(nullValue())));
	}

	@Test
	public void testCallAsyncMemcacheBatchIncrementRequestApiConfig() throws InterruptedException, ExecutionException {
		MemcacheIncrementRequest value = MemcacheIncrementRequest.newBuilder().setKey(ByteString.EMPTY).build();

		MemcacheBatchIncrementRequest request = MemcacheBatchIncrementRequest.newBuilder().addItem(value).build();

		Future<MemcacheBatchIncrementResponse> response = MemcacheOperations.BATCH_INVREMENT.callAsync(request, new ApiConfig());

		assertThat(response, is(not(nullValue())));
		assertThat(response.get(), is(not(nullValue())));
	}
}

package balmysundaycandy.more.low.level.operations.memcached.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.concurrent.*;

import org.junit.*;

import balmysundaycandy.core.test.*;
import balmysundaycandy.more.low.level.operations.memcached.*;

import com.google.appengine.api.memcache.MemcacheServicePb.*;
import com.google.appengine.repackaged.com.google.protobuf.*;
import com.google.apphosting.api.ApiProxy.*;

public class BatchIncrementOperationTest extends MemcachedTestCase {

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

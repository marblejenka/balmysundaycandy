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
import com.google.apphosting.api.ApiProxy.*;

public class FlushAllOperationTest extends MemcachedTestCase {

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

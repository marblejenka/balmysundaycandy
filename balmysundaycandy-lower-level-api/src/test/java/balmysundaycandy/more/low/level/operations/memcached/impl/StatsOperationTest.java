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

public class StatsOperationTest extends MemcachedTestCase {

	@Test
	public void testCallMemcacheStatsRequest() {
		MemcacheStatsRequest request = MemcacheStatsRequest.newBuilder().build();

		MemcacheStatsResponse response = MemcacheOperations.STATS.call(request);

		assertThat(response, is(not(nullValue())));
	}

	@Test
	public void testCallAsyncMemcacheStatsRequestApiConfig() throws InterruptedException, ExecutionException {
		MemcacheStatsRequest request = MemcacheStatsRequest.newBuilder().build();

		Future<MemcacheStatsResponse> response = MemcacheOperations.STATS.callAsync(request, new ApiConfig());

		assertThat(response, is(not(nullValue())));
		assertThat(response.get(), is(not(nullValue())));
	}
}

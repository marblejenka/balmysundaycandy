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

public class GrabTailOperationTest extends MemcachedTestCase {

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

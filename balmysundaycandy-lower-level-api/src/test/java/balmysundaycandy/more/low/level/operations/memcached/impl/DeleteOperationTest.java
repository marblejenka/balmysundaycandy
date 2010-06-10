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

public class DeleteOperationTest extends MemcachedTestCase {

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

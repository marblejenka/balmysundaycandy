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

public class GetOperationTest extends MemcachedTestCase {

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

package balmysundaycandy.more.low.level.operations.urlfetch.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.concurrent.*;

import org.junit.*;

import balmysundaycandy.core.test.*;
import balmysundaycandy.more.low.level.operations.urlfetch.*;

import com.google.appengine.api.urlfetch.URLFetchServicePb.*;
import com.google.appengine.api.urlfetch.URLFetchServicePb.URLFetchRequest.*;
import com.google.apphosting.api.ApiProxy.*;

public class FetchOperationTest extends UrlfetchTestCase {
	private final String url = "http://code.google.com/p/balmysundaycandy/";

	@Test
	public void testCallURLFetchRequest() {
		URLFetchRequest request = URLFetchRequest.newBuilder().setUrl(url).setMethod(RequestMethod.POST).build();

		URLFetchResponse response = UrlfetchOperations.FETCH.call(request);

		assertThat(response, is(not(nullValue())));
	}

	@Test
	public void testCallAsyncURLFetchRequestApiConfig() throws InterruptedException, ExecutionException {
		ApiConfig apiConfig = new ApiConfig();
		apiConfig.setDeadlineInSeconds(10d);

		URLFetchRequest request = URLFetchRequest.newBuilder().setUrl(url).setMethod(RequestMethod.POST).build();

		Future<URLFetchResponse> response = UrlfetchOperations.FETCH.callAsync(request, apiConfig);

		assertThat(response, is(not(nullValue())));
		assertThat(response.get(), is(not(nullValue())));
	}
}

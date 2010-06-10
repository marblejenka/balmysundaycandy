package balmysundaycandy.more.low.level.operations.datastore.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.concurrent.*;

import org.junit.*;

import balmysundaycandy.core.test.*;
import balmysundaycandy.more.low.level.operations.datastore.*;

import com.google.apphosting.api.*;
import com.google.apphosting.api.ApiBasePb.*;
import com.google.apphosting.api.ApiProxy.*;
import com.google.apphosting.api.DatastorePb.*;

public class CountOperationTest extends DatastoreTestCase {
	private static final String kind = "test";

	@Test
	public void testCallQuery() {
		Query request = new Query();
		request.setApp(ApiProxy.getCurrentEnvironment().getAppId());
		request.setKind(kind);

		Integer64Proto response = DatastoreOperations.COUNT.call(request);

		assertThat(response, is(not(nullValue())));
		assertThat(response.getValue(), is(0L));
	}

	@Test
	public void testCallAsyncQueryApiConfig() throws InterruptedException, ExecutionException {
		Query request = new Query();
		request.setApp(ApiProxy.getCurrentEnvironment().getAppId());
		request.setKind(kind);

		Future<Integer64Proto> response = DatastoreOperations.COUNT.callAsync(request, new ApiConfig());

		assertThat(response.get(), is(not(nullValue())));
		assertThat(response.get().getValue(), is(0L));
	}
}

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
import com.google.apphosting.api.ApiProxy.*;
import com.google.apphosting.api.DatastorePb.*;

public class RunQueryOperationTest extends DatastoreTestCase {

	@Test
	public void testCallQuery() {
		com.google.apphosting.api.DatastorePb.Query request = new com.google.apphosting.api.DatastorePb.Query();
		request.setApp(ApiProxy.getCurrentEnvironment().getAppId());
		request.setKind("Foo");

		QueryResult response = DatastoreOperations.RUN_QUERY.call(request);

		assertThat(response, is(not(nullValue())));
	}

	@Test
	public void testCallAsyncQueryApiConfig() throws InterruptedException, ExecutionException {
		com.google.apphosting.api.DatastorePb.Query request = new com.google.apphosting.api.DatastorePb.Query();
		request.setApp(ApiProxy.getCurrentEnvironment().getAppId());
		request.setKind("Foo");

		Future<QueryResult> response = DatastoreOperations.RUN_QUERY.callAsync(request, new ApiConfig());

		assertThat(response, is(not(nullValue())));
		assertThat(response.get(), is(not(nullValue())));
	}

}

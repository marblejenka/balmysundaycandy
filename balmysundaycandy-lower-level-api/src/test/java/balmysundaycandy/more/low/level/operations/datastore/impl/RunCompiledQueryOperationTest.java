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

public class RunCompiledQueryOperationTest extends DatastoreTestCase {

	@Test(expected = CallNotFoundException.class)
	public void testCallRunCompiledQueryRequest() {
		com.google.apphosting.api.DatastorePb.Query query = new com.google.apphosting.api.DatastorePb.Query();
		query.setApp(ApiProxy.getCurrentEnvironment().getAppId());
		query.setKind("Foo");

		RunCompiledQueryRequest request = new RunCompiledQueryRequest();
		request.setApp(ApiProxy.getCurrentEnvironment().getAppId());
		request.setOriginalQuery(query);

		QueryResult response = DatastoreOperations.RUN_COMPILES_QUERY.call(request);

		assertThat(response, is(not(nullValue())));
	}

	@Test(expected = ExecutionException.class)
	public void testCallAsyncRunCompiledQueryRequestApiConfig() throws InterruptedException, ExecutionException {
		com.google.apphosting.api.DatastorePb.Query query = new com.google.apphosting.api.DatastorePb.Query();
		query.setApp(ApiProxy.getCurrentEnvironment().getAppId());
		query.setKind("Foo");

		RunCompiledQueryRequest request = new RunCompiledQueryRequest();
		request.setApp(ApiProxy.getCurrentEnvironment().getAppId());
		request.setOriginalQuery(query);

		Future<QueryResult> response = DatastoreOperations.RUN_COMPILES_QUERY.callAsync(request, new ApiConfig());

		assertThat(response, is(not(nullValue())));
		assertThat(response.get(), is(not(nullValue())));
	}
}

package balmysundaycandy.more.low.level.operations.datastore.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.concurrent.*;

import org.junit.*;

import balmysundaycandy.core.test.*;
import balmysundaycandy.more.low.level.operations.datastore.*;

import com.google.apphosting.api.*;
import com.google.apphosting.api.ApiBasePb.*;
import com.google.apphosting.api.ApiProxy.*;
import com.google.storage.onestore.v3.OnestoreEntity.*;

public class CreateIndexOperationTest extends DatastoreTestCase {

	@SuppressWarnings("deprecation")
	@Test(expected = UnsupportedOperationException.class)
	public void testCallCompositeIndex() {
		CompositeIndex request = new CompositeIndex();
		request.setAppId(ApiProxy.getCurrentEnvironment().getAppId());

		Integer64Proto response = DatastoreOperations.CREATE_INDEX.call(request);
		fail("create index cause unsupported opeation exception");

		assertThat(response, is(not(nullValue())));
	}

	@SuppressWarnings("deprecation")
	@Test(expected = ExecutionException.class)
	// cause execution exception, because of unsupported operation exception
	// thrown in future.get method.
	public void testCallAsyncCompositeIndexApiConfig() throws InterruptedException, ExecutionException {
		CompositeIndex request = new CompositeIndex();
		request.setAppId(ApiProxy.getCurrentEnvironment().getAppId());

		Future<Integer64Proto> response = DatastoreOperations.CREATE_INDEX.callAsync(request, new ApiConfig());

		assertThat(response, is(not(nullValue())));
		assertThat(response.get(), is(not(nullValue())));
		fail("when get cause executionexception");
	}
}

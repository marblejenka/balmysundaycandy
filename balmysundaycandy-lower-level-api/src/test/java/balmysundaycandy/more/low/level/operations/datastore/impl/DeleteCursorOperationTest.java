package balmysundaycandy.more.low.level.operations.datastore.impl;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import balmysundaycandy.core.test.EnvironmentConfiguration;
import balmysundaycandy.core.test.TestEnvironmentUtils;
import balmysundaycandy.more.low.level.operations.datastore.DatastoreOperations;

import com.google.apphosting.api.ApiBasePb.VoidProto;
import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.apphosting.api.DatastorePb.Cursor;

public class DeleteCursorOperationTest {
	EnvironmentConfiguration environmentConfiguration = new EnvironmentConfiguration("", false, true);

	@Before
	public void setup() {
		TestEnvironmentUtils.setupEnvironment(environmentConfiguration);
	}

	@After
	public void teardown() {
		TestEnvironmentUtils.teardownEnvironment(environmentConfiguration);
	}

	@Test
	public void testCallCursor() {
		Cursor request = new Cursor();
		request.setCursor(10L);

		VoidProto response = DatastoreOperations.DELETE_CURSOR.call(request);

		assertThat(response, is(not(nullValue())));
	}

	@Test
	public void testCallAsyncCursorApiConfig() throws InterruptedException, ExecutionException {
		Cursor request = new Cursor();
		request.setCursor(10L);

		Future<VoidProto> response = DatastoreOperations.DELETE_CURSOR.callAsync(request, new ApiConfig());

		assertThat(response.get(), is(not(nullValue())));
	}

}

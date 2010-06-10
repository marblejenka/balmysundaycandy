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

public class DeleteCursorOperationTest extends DatastoreTestCase {

	@Test
	public void testCallCursor() {
		Cursor request = new Cursor();
		request.setApp(ApiProxy.getCurrentEnvironment().getAppId());
		request.setCursor(10L);

		VoidProto response = DatastoreOperations.DELETE_CURSOR.call(request);

		assertThat(response, is(not(nullValue())));
	}

	@Test
	public void testCallAsyncCursorApiConfig() throws InterruptedException, ExecutionException {
		Cursor request = new Cursor();
		request.setApp(ApiProxy.getCurrentEnvironment().getAppId());
		request.setCursor(10L);

		Future<VoidProto> response = DatastoreOperations.DELETE_CURSOR.callAsync(request, new ApiConfig());

		assertThat(response.get(), is(not(nullValue())));
	}

}

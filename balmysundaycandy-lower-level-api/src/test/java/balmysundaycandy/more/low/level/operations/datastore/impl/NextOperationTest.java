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

public class NextOperationTest extends DatastoreTestCase {

	@Test
	public void testCallNextRequest() {
		Query query = new Query();
		query.setApp("test");
		QueryResult queryResult = DatastoreOperations.RUN_QUERY.call(query);

		Cursor cursor = new Cursor();
		cursor.setCursor(queryResult.getCursor().getCursor());

		NextRequest request = new NextRequest();
		request.setCursor(cursor);

		QueryResult response = DatastoreOperations.NEXT.call(request);

		assertThat(response, is(not(nullValue())));
	}

	@Test
	public void testCallAsyncNextRequestApiConfig() throws InterruptedException, ExecutionException {
		Query query = new Query();
		query.setApp(ApiProxy.getCurrentEnvironment().getAppId());
		QueryResult queryResult = DatastoreOperations.RUN_QUERY.call(query);

		Cursor cursor = new Cursor();
		cursor.setCursor(queryResult.getCursor().getCursor());

		NextRequest request = new NextRequest();
		request.setCursor(cursor);

		Future<QueryResult> response = DatastoreOperations.NEXT.callAsync(request, new ApiConfig());

		assertThat(response, is(not(nullValue())));
		assertThat(response.get(), is(not(nullValue())));
	}

}

package balmysundaycandy.more.low.level.operations.datastore.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.concurrent.*;

import org.junit.*;

import balmysundaycandy.core.test.*;
import balmysundaycandy.more.low.level.operations.datastore.*;

import com.google.apphosting.api.ApiProxy.*;
import com.google.apphosting.api.DatastorePb.*;

public class DeleteOperationTest extends DatastoreTestCase {

	@Test
	public void testCallDeleteRequest() {
		DeleteRequest request = new DeleteRequest();

		DeleteResponse response = DatastoreOperations.DELETE.call(request);

		assertThat(response, is(not(nullValue())));
	}

	@Test
	public void testCallAsyncDeleteRequestApiConfig() throws InterruptedException, ExecutionException {
		DeleteRequest request = new DeleteRequest();

		Future<DeleteResponse> response = DatastoreOperations.DELETE.callAsync(request, new ApiConfig());

		assertThat(response.get(), is(not(nullValue())));
	}

}

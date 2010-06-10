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

public class AllocateIdsOperationTest extends DatastoreTestCase {

	@Test
	public void testCallAllocateIdsRequest() {
		AllocateIdsRequest request = new AllocateIdsRequest();
		request.setSize(10);

		AllocateIdsResponse response = DatastoreOperations.ALLOCATE_IDS.call(request);

		assertThat(response, is(not(nullValue())));
		assertThat(response.getStart(), is(1L));
		assertThat(response.getEnd(), is(10L));
	}

	@Test
	public void testCallAsyncAllocateIdsRequestApiConfig() throws InterruptedException, ExecutionException {
		AllocateIdsRequest request = new AllocateIdsRequest();
		request.setSize(10);

		Future<AllocateIdsResponse> response = DatastoreOperations.ALLOCATE_IDS.callAsync(request, new ApiConfig());

		assertThat(response, is(not(nullValue())));
		assertThat(response.get().getStart(), is(1L));
		assertThat(response.get().getEnd(), is(10L));
	}
}

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

public class AddActionOperationTest extends DatastoreTestCase {

	@Test(expected = NoClassDefFoundError.class)
	public void testCallActionRequest() {
		AddActionsResponse response = DatastoreOperations.ADD_ACTION.call(new AddActionsRequest());
		assertThat(response, is(not(nullValue()))); // cannnot get here
	}

	@Test(expected = NoClassDefFoundError.class)
	public void testCallAsyncActionRequestApiConfig() {
		Future<AddActionsResponse> response = DatastoreOperations.ADD_ACTION.callAsync(new AddActionsRequest(), new ApiConfig());
		assertThat(response, is(not(nullValue()))); // cannnot get here
	}
}

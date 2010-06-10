package balmysundaycandy.more.low.level.operations.datastore.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.*;

import org.junit.*;

import balmysundaycandy.core.test.*;
import balmysundaycandy.more.low.level.operations.datastore.*;

import com.google.apphosting.api.*;
import com.google.apphosting.api.ApiProxy.*;
import com.google.apphosting.api.DatastorePb.*;

public class CommitOperationTest extends DatastoreTestCase {

	@Test
	public void testCallTransaction() {
		// prepare
		BeginTransactionRequest request = new BeginTransactionRequest();
		request.setApp(ApiProxy.getCurrentEnvironment().getAppId());
		Transaction transaction = DatastoreOperations.BEGIN_TRANSACTION.call(request);

		// execute
		CommitResponse commitResponse = DatastoreOperations.COMMIT.call(transaction);

		// will not cause error
		assertTrue(true);
		assertThat(commitResponse, is(not(nullValue())));
	}

	@Test
	public void testCallAsyncTransactionApiConfig() throws InterruptedException, ExecutionException {
		// prepare
		BeginTransactionRequest request = new BeginTransactionRequest();
		request.setApp(ApiProxy.getCurrentEnvironment().getAppId());
		Transaction transaction = DatastoreOperations.BEGIN_TRANSACTION.call(request);

		// execute
		Future<CommitResponse> commitResponse = DatastoreOperations.COMMIT.callAsync(transaction, new ApiConfig());

		// will not cause error
		assertTrue(true);
		assertThat(commitResponse.get(), is(not(nullValue())));
	}

}

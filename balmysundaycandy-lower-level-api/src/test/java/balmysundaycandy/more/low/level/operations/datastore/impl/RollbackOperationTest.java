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
import com.google.apphosting.api.ApiBasePb.*;
import com.google.apphosting.api.ApiProxy.*;
import com.google.apphosting.api.DatastorePb.*;

public class RollbackOperationTest extends DatastoreTestCase {

	@Test
	public void testCallTransaction() {
		// prepare
		BeginTransactionRequest request = new BeginTransactionRequest();
		request.setApp(ApiProxy.getCurrentEnvironment().getAppId());
		Transaction transaction = DatastoreOperations.BEGIN_TRANSACTION.call(request);

		// execute
		VoidProto voidProto = DatastoreOperations.ROLLBACK.call(transaction);

		// will not cause error
		assertTrue(true);
		assertThat(voidProto, is(not(nullValue())));
	}

	@Test
	public void testCallAsyncTransactionApiConfig() throws InterruptedException, ExecutionException {
		// prepare
		BeginTransactionRequest request = new BeginTransactionRequest();
		request.setApp(ApiProxy.getCurrentEnvironment().getAppId());
		Transaction transaction = DatastoreOperations.BEGIN_TRANSACTION.call(request);

		// execute
		Future<VoidProto> voidProto = DatastoreOperations.ROLLBACK.callAsync(transaction, new ApiConfig());

		// will not cause error
		assertTrue(true);
		assertThat(voidProto, is(not(nullValue())));
		assertThat(voidProto.get(), is(not(nullValue())));
	}

}

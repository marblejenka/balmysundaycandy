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

import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiBasePb.VoidProto;
import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.apphosting.api.DatastorePb.BeginTransactionRequest;
import com.google.apphosting.api.DatastorePb.Transaction;

public class RollbackOperationTest {
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

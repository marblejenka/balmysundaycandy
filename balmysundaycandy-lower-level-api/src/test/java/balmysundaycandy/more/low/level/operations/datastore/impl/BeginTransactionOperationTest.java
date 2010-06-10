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

public class BeginTransactionOperationTest extends DatastoreTestCase {

	@Test
	public void testCallVoidProto() {
		BeginTransactionRequest request = new BeginTransactionRequest();
		request.setApp(ApiProxy.getCurrentEnvironment().getAppId());

		Transaction transaction = DatastoreOperations.BEGIN_TRANSACTION.call(request);

		assertThat(transaction, is(not(nullValue())));
		assertThat(transaction.getHandle(), is(not(nullValue())));
	}

	@Test
	public void testCallAsyncVoidProtoApiConfig() throws InterruptedException, ExecutionException {
		BeginTransactionRequest request = new BeginTransactionRequest();
		request.setApp(ApiProxy.getCurrentEnvironment().getAppId());

		Future<Transaction> transaction = DatastoreOperations.BEGIN_TRANSACTION.callAsync(request, new ApiConfig());

		assertThat(transaction.get(), is(not(nullValue())));
		assertThat(transaction.get().getHandle(), is(not(nullValue())));
	}
}

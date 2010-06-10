package balmysundaycandy.more.low.level.operations.taskqueue.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.concurrent.*;

import org.junit.*;

import balmysundaycandy.core.test.*;
import balmysundaycandy.more.low.level.operations.taskqueue.*;

import com.google.appengine.api.labs.taskqueue.TaskQueuePb.*;
import com.google.apphosting.api.*;
import com.google.apphosting.api.ApiProxy.*;

public class FetchQueuesOperationTest extends TaskqueueTestCase {
	@SuppressWarnings("deprecation")
	@Test(expected = CallNotFoundException.class)
	public void testCallTaskQueueFetchQueuesRequest() {
		TaskQueueFetchQueuesRequest request = new TaskQueueFetchQueuesRequest();
		{
			request.setAppId(ApiProxy.getCurrentEnvironment().getAppId());
			request.setMaxRows(10);
		}
		TaskQueueFetchQueuesResponse response = TaskqueueOperations.FETCH_QUEUES.call(request);

		assertThat(response, is(not(nullValue())));
	}

	@SuppressWarnings("deprecation")
	@Test(expected = ExecutionException.class)
	public void testCallAsyncTaskQueueFetchQueuesRequestApiConfig() throws InterruptedException, ExecutionException {
		TaskQueueFetchQueuesRequest request = new TaskQueueFetchQueuesRequest();
		{
			request.setAppId(ApiProxy.getCurrentEnvironment().getAppId());
			request.setMaxRows(10);
		}
		Future<TaskQueueFetchQueuesResponse> response = TaskqueueOperations.FETCH_QUEUES.callAsync(request, new ApiConfig());

		assertThat(response, is(not(nullValue())));
		assertThat(response.get(), is(not(nullValue())));
	}
}

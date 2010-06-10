package balmysundaycandy.more.low.level.operations.taskqueue.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.concurrent.*;

import org.junit.*;

import balmysundaycandy.core.test.*;
import balmysundaycandy.more.low.level.operations.taskqueue.*;

import com.google.appengine.api.labs.taskqueue.*;
import com.google.appengine.api.labs.taskqueue.TaskQueuePb.*;
import com.google.apphosting.api.*;
import com.google.apphosting.api.ApiProxy.*;

public class FetchQueueStatsOperationTest extends TaskqueueTestCase {

	@SuppressWarnings("deprecation")
	@Test(expected = CallNotFoundException.class)
	public void testCallTaskQueueFetchQueueStatsRequest() {
		TaskQueueFetchQueueStatsRequest request = new TaskQueueFetchQueueStatsRequest();
		{
			request.setAppId(ApiProxy.getCurrentEnvironment().getAppId());
			request.setMaxNumTasks(10);
			request.addQueueName(Queue.DEFAULT_QUEUE);
		}
		TaskQueueFetchQueueStatsResponse response = TaskqueueOperations.FETCH_QUEUE_STATS.call(request);

		assertThat(response, is(not(nullValue())));
	}

	@SuppressWarnings("deprecation")
	@Test(expected = ExecutionException.class)
	public void testCallAsyncTaskQueueFetchQueueStatsRequestApiConfig() throws InterruptedException, ExecutionException {
		TaskQueueFetchQueueStatsRequest request = new TaskQueueFetchQueueStatsRequest();
		{
			request.setAppId(ApiProxy.getCurrentEnvironment().getAppId());
			request.setMaxNumTasks(10);
			request.addQueueName(Queue.DEFAULT_QUEUE);
		}
		Future<TaskQueueFetchQueueStatsResponse> response = TaskqueueOperations.FETCH_QUEUE_STATS.callAsync(request, new ApiConfig());

		assertThat(response, is(not(nullValue())));
		assertThat(response.get(), is(not(nullValue())));
	}

}

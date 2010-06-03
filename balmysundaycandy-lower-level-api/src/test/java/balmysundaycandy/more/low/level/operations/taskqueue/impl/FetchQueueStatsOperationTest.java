package balmysundaycandy.more.low.level.operations.taskqueue.impl;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import balmysundaycandy.core.test.EnvironmentConfiguration;
import balmysundaycandy.core.test.TestEnvironmentUtils;
import balmysundaycandy.more.low.level.operations.taskqueue.TaskqueueOperations;

import com.google.appengine.api.labs.taskqueue.Queue;
import com.google.appengine.api.labs.taskqueue.TaskQueuePb.TaskQueueFetchQueueStatsRequest;
import com.google.appengine.api.labs.taskqueue.TaskQueuePb.TaskQueueFetchQueueStatsResponse;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.apphosting.api.ApiProxy.CallNotFoundException;

public class FetchQueueStatsOperationTest {
	EnvironmentConfiguration environmentConfiguration = new EnvironmentConfiguration("", false, true);

	@Before
	public void setup() {
		TestEnvironmentUtils.setupEnvironment(environmentConfiguration);
	}

	@After
	public void teardown() {
		TestEnvironmentUtils.teardownEnvironment(environmentConfiguration);
	}

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

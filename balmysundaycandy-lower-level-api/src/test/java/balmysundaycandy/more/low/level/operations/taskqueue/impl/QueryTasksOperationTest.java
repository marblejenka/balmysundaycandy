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
import com.google.appengine.api.labs.taskqueue.TaskQueuePb.TaskQueueQueryTasksRequest;
import com.google.appengine.api.labs.taskqueue.TaskQueuePb.TaskQueueQueryTasksResponse;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.apphosting.api.ApiProxy.CallNotFoundException;

public class QueryTasksOperationTest {
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
	public void testCallTaskQueueQueryTasksRequest() {
		TaskQueueQueryTasksRequest request = new TaskQueueQueryTasksRequest();
		{
			request.setAppId(ApiProxy.getCurrentEnvironment().getAppId());
			request.setMaxRows(10);
			request.setQueueName(Queue.DEFAULT_QUEUE);
			request.setStartTaskName("TEST");
		}
		TaskQueueQueryTasksResponse response = TaskqueueOperations.QUERY_TASKS.call(request);

		assertThat(response, is(not(nullValue())));
	}

	@SuppressWarnings("deprecation")
	@Test(expected = ExecutionException.class)
	public void testCallAsyncTaskQueueQueryTasksRequestApiConfig() throws InterruptedException, ExecutionException {
		TaskQueueQueryTasksRequest request = new TaskQueueQueryTasksRequest();
		{
			request.setAppId(ApiProxy.getCurrentEnvironment().getAppId());
			request.setMaxRows(10);
			request.setQueueName(Queue.DEFAULT_QUEUE);
			request.setStartTaskName("TEST");
		}
		Future<TaskQueueQueryTasksResponse> response = TaskqueueOperations.QUERY_TASKS.callAsync(request, new ApiConfig());

		assertThat(response, is(not(nullValue())));
		assertThat(response.get(), is(not(nullValue())));
	}

}

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
import com.google.appengine.api.labs.taskqueue.TaskQueuePb.TaskQueueDeleteQueueRequest;
import com.google.appengine.api.labs.taskqueue.TaskQueuePb.TaskQueueDeleteQueueResponse;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.apphosting.api.ApiProxy.CallNotFoundException;

public class DeleteQueueOperationTest {
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
	public void testCallTaskQueueDeleteQueueRequest() {
		TaskQueueDeleteQueueRequest request = new TaskQueueDeleteQueueRequest();
		{
			request.setAppId(ApiProxy.getCurrentEnvironment().getAppId());
			request.setQueueName(Queue.DEFAULT_QUEUE);
		}
		TaskQueueDeleteQueueResponse response = TaskqueueOperations.DELETE_QUEUE.call(request);

		assertThat(response, is(not(nullValue())));
	}

	@SuppressWarnings("deprecation")
	@Test(expected = ExecutionException.class)
	public void testCallAsyncTaskQueueDeleteQueueRequestApiConfig() throws InterruptedException, ExecutionException {
		TaskQueueDeleteQueueRequest request = new TaskQueueDeleteQueueRequest();
		{
			request.setAppId(ApiProxy.getCurrentEnvironment().getAppId());
			request.setQueueName(Queue.DEFAULT_QUEUE);
		}
		Future<TaskQueueDeleteQueueResponse> response = TaskqueueOperations.DELETE_QUEUE.callAsync(request, new ApiConfig());

		assertThat(response, is(not(nullValue())));
		assertThat(response.get(), is(not(nullValue())));
	}
}

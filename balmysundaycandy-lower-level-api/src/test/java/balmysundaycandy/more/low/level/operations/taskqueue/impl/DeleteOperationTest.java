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
import com.google.appengine.api.labs.taskqueue.TaskQueuePb.TaskQueueDeleteRequest;
import com.google.appengine.api.labs.taskqueue.TaskQueuePb.TaskQueueDeleteResponse;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.apphosting.api.ApiProxy.CallNotFoundException;

public class DeleteOperationTest {
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
	public void testCallTaskQueueDeleteRequest() {
		TaskQueueDeleteRequest request = new TaskQueueDeleteRequest();
		{
			request.setAppId(ApiProxy.getCurrentEnvironment().getAppId());
			request.setQueueName(Queue.DEFAULT_QUEUE);

			request.addTaskName("TEST");
		}
		TaskQueueDeleteResponse response = TaskqueueOperations.DELETE.call(request);

		assertThat(response, is(not(nullValue())));
	}

	@SuppressWarnings("deprecation")
	@Test(expected = ExecutionException.class)
	public void testCallAsyncTaskQueueDeleteRequestApiConfig() throws InterruptedException, ExecutionException {
		TaskQueueDeleteRequest request = new TaskQueueDeleteRequest();
		{
			request.setAppId(ApiProxy.getCurrentEnvironment().getAppId());
			request.setQueueName(Queue.DEFAULT_QUEUE);

			request.addTaskName("TEST");
		}
		Future<TaskQueueDeleteResponse> response = TaskqueueOperations.DELETE.callAsync(request, new ApiConfig());

		assertThat(response, is(not(nullValue())));
		assertThat(response.get(), is(not(nullValue())));
	}
}

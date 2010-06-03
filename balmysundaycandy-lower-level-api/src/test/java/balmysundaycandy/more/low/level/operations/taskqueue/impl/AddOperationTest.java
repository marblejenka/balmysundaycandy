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
import com.google.appengine.api.labs.taskqueue.TaskQueuePb.TaskQueueAddRequest;
import com.google.appengine.api.labs.taskqueue.TaskQueuePb.TaskQueueAddResponse;
import com.google.appengine.api.labs.taskqueue.TaskQueuePb.TaskQueueAddRequest.RequestMethod;
import com.google.apphosting.api.ApiProxy.ApiConfig;

public class AddOperationTest {
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
	public void testCallTaskQueueAddRequest() {
		TaskQueueAddRequest request = new TaskQueueAddRequest();
		{
			request.setQueueName(Queue.DEFAULT_QUEUE);
			request.setTaskName("TEST");
			request.setUrl(Queue.DEFAULT_QUEUE_PATH);

			request.setMethod(RequestMethod.POST);
		}
		TaskQueueAddResponse response = TaskqueueOperations.ADD.call(request);

		assertThat(response, is(not(nullValue())));
	}

	@Test
	public void testCallAsyncTaskQueueAddRequestApiConfig() throws InterruptedException, ExecutionException {
		TaskQueueAddRequest request = new TaskQueueAddRequest();
		{
			request.setQueueName(Queue.DEFAULT_QUEUE);
			request.setTaskName("TEST-ASYNC");
			request.setUrl(Queue.DEFAULT_QUEUE_PATH);

			request.setMethod(RequestMethod.POST);
		}
		Future<TaskQueueAddResponse> response = TaskqueueOperations.ADD.callAsync(request, new ApiConfig());

		assertThat(response, is(not(nullValue())));
		assertThat(response.get(), is(not(nullValue())));
	}
}

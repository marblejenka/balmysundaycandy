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
import com.google.appengine.api.labs.taskqueue.TaskQueuePb.TaskQueueUpdateQueueRequest;
import com.google.appengine.api.labs.taskqueue.TaskQueuePb.TaskQueueUpdateQueueResponse;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.apphosting.api.ApiProxy.CallNotFoundException;

public class UpdateOperationTest {
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
	public void testCallTaskQueueUpdateQueueRequest() {
		TaskQueueUpdateQueueRequest request = new TaskQueueUpdateQueueRequest();
		{
			request.setAppId(ApiProxy.getCurrentEnvironment().getAppId());
			request.setQueueName(Queue.DEFAULT_QUEUE + "_APPEND");
			request.setUserSpecifiedRate("5.00/s");
			request.setBucketCapacity(2);
		}
		TaskQueueUpdateQueueResponse response = TaskqueueOperations.UPDATE.call(request);

		assertThat(response, is(not(nullValue())));
	}

	@SuppressWarnings("deprecation")
	@Test(expected = ExecutionException.class)
	public void testCallAsyncTaskQueueUpdateQueueRequestApiConfig() throws InterruptedException, ExecutionException {
		TaskQueueUpdateQueueRequest request = new TaskQueueUpdateQueueRequest();
		{
			request.setAppId(ApiProxy.getCurrentEnvironment().getAppId());
			request.setQueueName(Queue.DEFAULT_QUEUE + "_APPEND");
			request.setUserSpecifiedRate("5.00/s");
			request.setBucketCapacity(2);
		}
		Future<TaskQueueUpdateQueueResponse> response = TaskqueueOperations.UPDATE.callAsync(request, new ApiConfig());

		assertThat(response, is(not(nullValue())));
		assertThat(response.get(), is(not(nullValue())));
	}
}

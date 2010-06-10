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

public class DeleteOperationTest extends TaskqueueTestCase {

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

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

public class DeleteQueueOperationTest extends TaskqueueTestCase {

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

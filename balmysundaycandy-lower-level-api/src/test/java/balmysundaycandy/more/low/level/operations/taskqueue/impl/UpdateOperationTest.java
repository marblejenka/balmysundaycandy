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

public class UpdateOperationTest extends TaskqueueTestCase {

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

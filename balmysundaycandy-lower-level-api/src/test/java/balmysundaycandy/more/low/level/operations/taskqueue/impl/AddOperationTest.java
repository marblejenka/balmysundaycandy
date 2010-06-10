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
import com.google.appengine.api.labs.taskqueue.TaskQueuePb.TaskQueueAddRequest.*;
import com.google.apphosting.api.ApiProxy.*;

public class AddOperationTest extends TaskqueueTestCase {

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

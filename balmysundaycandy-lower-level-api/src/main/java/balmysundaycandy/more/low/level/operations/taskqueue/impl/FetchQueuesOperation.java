/**
 * 
 */
package balmysundaycandy.more.low.level.operations.taskqueue.impl;

import java.util.concurrent.Future;

import balmysundaycandy.core.future.ProtocolMessageFuture;
import balmysundaycandy.core.operations.OperationNamespaces.taskqueue;
import balmysundaycandy.more.low.level.operations.taskqueue.TaskqueueOperation;

import com.google.appengine.api.labs.taskqueue.TaskQueuePb.TaskQueueFetchQueuesRequest;
import com.google.appengine.api.labs.taskqueue.TaskQueuePb.TaskQueueFetchQueuesResponse;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;

public final class FetchQueuesOperation extends TaskqueueOperation<TaskQueueFetchQueuesRequest, TaskQueueFetchQueuesResponse> {

	@Override
	public TaskQueueFetchQueuesResponse call(TaskQueueFetchQueuesRequest request) {
		TaskQueueFetchQueuesResponse response = new TaskQueueFetchQueuesResponse();
		response.mergeFrom(ApiProxy.makeSyncCall(taskqueue.packageName, taskqueue.methodName.FetchQueues, request.toByteArray()));
		return response;
	}

	@Override
	public Future<TaskQueueFetchQueuesResponse> callAsync(TaskQueueFetchQueuesRequest request, ApiConfig apiConfig) {
		TaskQueueFetchQueuesResponse response = new TaskQueueFetchQueuesResponse();
		return new ProtocolMessageFuture<TaskQueueFetchQueuesResponse>(ApiProxy.makeAsyncCall(taskqueue.packageName, taskqueue.methodName.FetchQueues, request.toByteArray(),
				apiConfig), response);
	}
}
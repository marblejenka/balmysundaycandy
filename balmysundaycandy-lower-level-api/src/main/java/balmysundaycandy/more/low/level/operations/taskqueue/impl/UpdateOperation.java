/**
 * 
 */
package balmysundaycandy.more.low.level.operations.taskqueue.impl;

import java.util.concurrent.Future;

import balmysundaycandy.core.future.ProtocolMessageFuture;
import balmysundaycandy.core.operations.OperationNamespaces.taskqueue;
import balmysundaycandy.more.low.level.operations.taskqueue.TaskqueueOperation;

import com.google.appengine.api.labs.taskqueue.TaskQueuePb.TaskQueueUpdateQueueRequest;
import com.google.appengine.api.labs.taskqueue.TaskQueuePb.TaskQueueUpdateQueueResponse;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;

public final class UpdateOperation extends TaskqueueOperation<TaskQueueUpdateQueueRequest, TaskQueueUpdateQueueResponse> {

	@Override
	public TaskQueueUpdateQueueResponse call(TaskQueueUpdateQueueRequest request) {
		TaskQueueUpdateQueueResponse response = new TaskQueueUpdateQueueResponse();
		response.mergeFrom(ApiProxy.makeSyncCall(taskqueue.packageName, taskqueue.methodName.UpdateQueue, request.toByteArray()));
		return response;
	}

	@Override
	public Future<TaskQueueUpdateQueueResponse> callAsync(TaskQueueUpdateQueueRequest request, ApiConfig apiConfig) {
		TaskQueueUpdateQueueResponse response = new TaskQueueUpdateQueueResponse();
		return new ProtocolMessageFuture<TaskQueueUpdateQueueResponse>(ApiProxy.makeAsyncCall(taskqueue.packageName, taskqueue.methodName.UpdateQueue, request.toByteArray(),
				apiConfig), response);
	}
}
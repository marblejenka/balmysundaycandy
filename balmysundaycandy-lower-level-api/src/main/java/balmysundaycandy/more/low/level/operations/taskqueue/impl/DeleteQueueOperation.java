/**
 * 
 */
package balmysundaycandy.more.low.level.operations.taskqueue.impl;

import java.util.concurrent.Future;

import balmysundaycandy.core.future.ProtocolMessageFuture;
import balmysundaycandy.core.operations.OperationNamespaces.taskqueue;
import balmysundaycandy.more.low.level.operations.taskqueue.TaskqueueOperation;

import com.google.appengine.api.labs.taskqueue.TaskQueuePb.TaskQueueDeleteQueueRequest;
import com.google.appengine.api.labs.taskqueue.TaskQueuePb.TaskQueueDeleteQueueResponse;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;

public final class DeleteQueueOperation extends TaskqueueOperation<TaskQueueDeleteQueueRequest, TaskQueueDeleteQueueResponse> {

	@Override
	public TaskQueueDeleteQueueResponse call(TaskQueueDeleteQueueRequest request) {
		TaskQueueDeleteQueueResponse response = new TaskQueueDeleteQueueResponse();
		response.mergeFrom(ApiProxy.makeSyncCall(taskqueue.packageName, taskqueue.methodName.DeleteQueue, request.toByteArray()));
		return response;
	}

	@Override
	public Future<TaskQueueDeleteQueueResponse> callAsync(TaskQueueDeleteQueueRequest request, ApiConfig apiConfig) {
		TaskQueueDeleteQueueResponse response = new TaskQueueDeleteQueueResponse();
		return new ProtocolMessageFuture<TaskQueueDeleteQueueResponse>(ApiProxy.makeAsyncCall(taskqueue.packageName, taskqueue.methodName.DeleteQueue, request.toByteArray(),
				apiConfig), response);
	}
}
/**
 * 
 */
package balmysundaycandy.more.low.level.operations.taskqueue.impl;

import java.util.concurrent.Future;

import balmysundaycandy.core.future.ProtocolMessageFuture;
import balmysundaycandy.core.operations.OperationNamespaces.taskqueue;
import balmysundaycandy.more.low.level.operations.taskqueue.TaskqueueOperation;

import com.google.appengine.api.labs.taskqueue.TaskQueuePb.TaskQueueDeleteRequest;
import com.google.appengine.api.labs.taskqueue.TaskQueuePb.TaskQueueDeleteResponse;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;

public final class DeleteOperation extends TaskqueueOperation<TaskQueueDeleteRequest, TaskQueueDeleteResponse> {

	@Override
	public TaskQueueDeleteResponse call(TaskQueueDeleteRequest request) {
		TaskQueueDeleteResponse response = new TaskQueueDeleteResponse();
		response.mergeFrom(ApiProxy.makeSyncCall(taskqueue.packageName, taskqueue.methodName.Delete, request.toByteArray()));
		return response;
	}

	@Override
	public Future<TaskQueueDeleteResponse> callAsync(TaskQueueDeleteRequest request, ApiConfig apiConfig) {
		TaskQueueDeleteResponse response = new TaskQueueDeleteResponse();
		return new ProtocolMessageFuture<TaskQueueDeleteResponse>(ApiProxy.makeAsyncCall(taskqueue.packageName, taskqueue.methodName.Delete, request.toByteArray(), apiConfig),
				response);
	}
}
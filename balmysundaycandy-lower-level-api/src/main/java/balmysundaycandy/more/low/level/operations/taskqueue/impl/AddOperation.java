/**
 * 
 */
package balmysundaycandy.more.low.level.operations.taskqueue.impl;

import java.util.concurrent.Future;

import balmysundaycandy.core.future.ProtocolMessageFuture;
import balmysundaycandy.core.operations.OperationNamespaces.taskqueue;
import balmysundaycandy.more.low.level.operations.taskqueue.TaskqueueOperation;

import com.google.appengine.api.labs.taskqueue.TaskQueuePb.TaskQueueAddRequest;
import com.google.appengine.api.labs.taskqueue.TaskQueuePb.TaskQueueAddResponse;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;

public final class AddOperation extends TaskqueueOperation<TaskQueueAddRequest, TaskQueueAddResponse> {

	@Override
	public TaskQueueAddResponse call(TaskQueueAddRequest request) {
		TaskQueueAddResponse response = new TaskQueueAddResponse();
		response.mergeFrom(ApiProxy.makeSyncCall(taskqueue.packageName, taskqueue.methodName.Add, request.toByteArray()));
		return response;
	}

	@Override
	public Future<TaskQueueAddResponse> callAsync(TaskQueueAddRequest request, ApiConfig apiConfig) {
		TaskQueueAddResponse response = new TaskQueueAddResponse();
		return new ProtocolMessageFuture<TaskQueueAddResponse>(ApiProxy.makeAsyncCall(taskqueue.packageName, taskqueue.methodName.Add, request.toByteArray(), apiConfig), response);
	}
}
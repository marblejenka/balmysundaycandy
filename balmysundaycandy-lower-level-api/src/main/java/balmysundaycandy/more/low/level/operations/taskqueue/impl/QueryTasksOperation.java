/**
 * 
 */
package balmysundaycandy.more.low.level.operations.taskqueue.impl;

import java.util.concurrent.Future;

import balmysundaycandy.core.future.ProtocolMessageFuture;
import balmysundaycandy.core.operations.OperationNamespaces.taskqueue;
import balmysundaycandy.more.low.level.operations.taskqueue.TaskqueueOperation;

import com.google.appengine.api.labs.taskqueue.TaskQueuePb.TaskQueueQueryTasksRequest;
import com.google.appengine.api.labs.taskqueue.TaskQueuePb.TaskQueueQueryTasksResponse;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;

public final class QueryTasksOperation extends TaskqueueOperation<TaskQueueQueryTasksRequest, TaskQueueQueryTasksResponse> {

	@Override
	public TaskQueueQueryTasksResponse call(TaskQueueQueryTasksRequest request) {
		TaskQueueQueryTasksResponse response = new TaskQueueQueryTasksResponse();
		response.mergeFrom(ApiProxy.makeSyncCall(taskqueue.packageName, taskqueue.methodName.QueryTasks, request.toByteArray()));
		return response;
	}

	@Override
	public Future<TaskQueueQueryTasksResponse> callAsync(TaskQueueQueryTasksRequest request, ApiConfig apiConfig) {
		TaskQueueQueryTasksResponse response = new TaskQueueQueryTasksResponse();
		return new ProtocolMessageFuture<TaskQueueQueryTasksResponse>(ApiProxy.makeAsyncCall(taskqueue.packageName, taskqueue.methodName.QueryTasks, request.toByteArray(),
				apiConfig), response);
	}
}
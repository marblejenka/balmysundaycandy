/**
 * 
 */
package balmysundaycandy.more.low.level.operations.taskqueue.impl;

import java.util.concurrent.Future;

import balmysundaycandy.core.future.ProtocolMessageFuture;
import balmysundaycandy.core.operations.OperationNamespaces.taskqueue;
import balmysundaycandy.more.low.level.operations.taskqueue.TaskqueueOperation;

import com.google.appengine.api.labs.taskqueue.TaskQueuePb.TaskQueueFetchQueueStatsRequest;
import com.google.appengine.api.labs.taskqueue.TaskQueuePb.TaskQueueFetchQueueStatsResponse;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;

public final class FetchQueueStatsOperation extends TaskqueueOperation<TaskQueueFetchQueueStatsRequest, TaskQueueFetchQueueStatsResponse> {

	@Override
	public TaskQueueFetchQueueStatsResponse call(TaskQueueFetchQueueStatsRequest request) {
		TaskQueueFetchQueueStatsResponse response = new TaskQueueFetchQueueStatsResponse();
		response.mergeFrom(ApiProxy.makeSyncCall(taskqueue.packageName, taskqueue.methodName.FetchQueueStats, request.toByteArray()));
		return response;
	}

	@Override
	public Future<TaskQueueFetchQueueStatsResponse> callAsync(TaskQueueFetchQueueStatsRequest request, ApiConfig apiConfig) {
		TaskQueueFetchQueueStatsResponse response = new TaskQueueFetchQueueStatsResponse();
		return new ProtocolMessageFuture<TaskQueueFetchQueueStatsResponse>(ApiProxy.makeAsyncCall(taskqueue.packageName, taskqueue.methodName.FetchQueueStats, request
				.toByteArray(), apiConfig), response);
	}
}
/**
 * 
 */
package balmysundaycandy.more.low.level.operations.user.impl;

import java.util.concurrent.Future;

import balmysundaycandy.core.future.ProtocolMessageFuture;
import balmysundaycandy.core.operations.OperationNamespaces.user;
import balmysundaycandy.more.low.level.operations.user.UserOperation;

import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.apphosting.api.UserServicePb.CreateLoginURLRequest;
import com.google.apphosting.api.UserServicePb.CreateLoginURLResponse;

public final class CreateLoginURLOperation extends UserOperation<CreateLoginURLRequest, CreateLoginURLResponse> {

	@Override
	public CreateLoginURLResponse call(CreateLoginURLRequest request) {
		CreateLoginURLResponse response = new CreateLoginURLResponse();
		response.mergeFrom(ApiProxy.makeSyncCall(user.packageName, user.methodName.CreateLoginURL, request.toByteArray()));
		return response;
	}

	@Override
	public Future<CreateLoginURLResponse> callAsync(CreateLoginURLRequest request, ApiConfig apiConfig) {
		CreateLoginURLResponse response = new CreateLoginURLResponse();
		return new ProtocolMessageFuture<CreateLoginURLResponse>(ApiProxy.makeAsyncCall(user.packageName, user.methodName.CreateLoginURL, request.toByteArray(), apiConfig),
				response);
	}
}
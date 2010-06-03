/**
 * 
 */
package balmysundaycandy.more.low.level.operations.xmpp.impl;

import java.util.concurrent.Future;

import balmysundaycandy.core.future.ProtocolMessageFuture;
import balmysundaycandy.core.operations.OperationNamespaces.xmpp;
import balmysundaycandy.more.low.level.operations.xmpp.XmppOperation;

import com.google.appengine.api.xmpp.XMPPServicePb.PresenceRequest;
import com.google.appengine.api.xmpp.XMPPServicePb.PresenceResponse;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;

public final class GetPresenceOperation extends XmppOperation<PresenceRequest, PresenceResponse> {

	@Override
	public PresenceResponse call(PresenceRequest request) {
		PresenceResponse response = new PresenceResponse();
		response.mergeFrom(ApiProxy.makeSyncCall(xmpp.packageName, xmpp.methodName.GetPresence, request.toByteArray()));
		return response;
	}

	@Override
	public Future<PresenceResponse> callAsync(PresenceRequest request, ApiConfig apiConfig) {
		PresenceResponse response = new PresenceResponse();
		return new ProtocolMessageFuture<PresenceResponse>(ApiProxy.makeAsyncCall(xmpp.packageName, xmpp.methodName.GetPresence, request.toByteArray(), apiConfig), response);
	}
}
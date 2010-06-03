/**
 * 
 */
package balmysundaycandy.more.low.level.operations.xmpp.impl;

import java.util.concurrent.Future;

import balmysundaycandy.core.future.ProtocolMessageFuture;
import balmysundaycandy.core.operations.OperationNamespaces.xmpp;
import balmysundaycandy.more.low.level.operations.xmpp.XmppOperation;

import com.google.appengine.api.xmpp.XMPPServicePb.XmppInviteRequest;
import com.google.appengine.api.xmpp.XMPPServicePb.XmppInviteResponse;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;

public final class SendInviteOperation extends XmppOperation<XmppInviteRequest, XmppInviteResponse> {

	@Override
	public XmppInviteResponse call(XmppInviteRequest request) {
		XmppInviteResponse response = new XmppInviteResponse();
		response.mergeFrom(ApiProxy.makeSyncCall(xmpp.packageName, xmpp.methodName.SendInvite, request.toByteArray()));
		return response;
	}

	@Override
	public Future<XmppInviteResponse> callAsync(XmppInviteRequest request, ApiConfig apiConfig) {
		XmppInviteResponse response = new XmppInviteResponse();
		return new ProtocolMessageFuture<XmppInviteResponse>(ApiProxy.makeAsyncCall(xmpp.packageName, xmpp.methodName.SendInvite, request.toByteArray(), apiConfig), response);
	}
}
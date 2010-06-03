/**
 * 
 */
package balmysundaycandy.more.low.level.operations.xmpp.impl;

import java.util.concurrent.Future;

import balmysundaycandy.core.future.ProtocolMessageFuture;
import balmysundaycandy.core.operations.OperationNamespaces.xmpp;
import balmysundaycandy.more.low.level.operations.xmpp.XmppOperation;

import com.google.appengine.api.xmpp.XMPPServicePb.XmppMessageRequest;
import com.google.appengine.api.xmpp.XMPPServicePb.XmppMessageResponse;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;

public final class SendMessageOperation extends XmppOperation<XmppMessageRequest, XmppMessageResponse> {

	@Override
	public XmppMessageResponse call(XmppMessageRequest request) {
		XmppMessageResponse response = new XmppMessageResponse();
		response.mergeFrom(ApiProxy.makeSyncCall(xmpp.packageName, xmpp.methodName.SendMessage, request.toByteArray()));
		return response;
	}

	@Override
	public Future<XmppMessageResponse> callAsync(XmppMessageRequest request, ApiConfig apiConfig) {
		XmppMessageResponse response = new XmppMessageResponse();
		return new ProtocolMessageFuture<XmppMessageResponse>(ApiProxy.makeAsyncCall(xmpp.packageName, xmpp.methodName.SendMessage, request.toByteArray(), apiConfig), response);
	}
}
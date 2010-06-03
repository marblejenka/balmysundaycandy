/**
 * 
 */
package balmysundaycandy.more.low.level.operations.mail.impl;

import java.util.concurrent.Future;

import balmysundaycandy.core.future.ProtocolMessageFuture;
import balmysundaycandy.core.operations.OperationNamespaces.mail;
import balmysundaycandy.more.low.level.operations.mail.MailOperation;

import com.google.appengine.api.mail.MailServicePb.MailMessage;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiBasePb.VoidProto;
import com.google.apphosting.api.ApiProxy.ApiConfig;

public final class SendToAdminsOperation extends MailOperation<MailMessage, VoidProto> {

	@Override
	public VoidProto call(MailMessage request) {
		VoidProto response = new VoidProto();
		response.mergeFrom(ApiProxy.makeSyncCall(mail.packageName, mail.methodName.SendToAdmins, request.toByteArray()));
		return response;
	}

	@Override
	public Future<VoidProto> callAsync(MailMessage request, ApiConfig apiConfig) {
		VoidProto response = new VoidProto();
		return new ProtocolMessageFuture<VoidProto>(ApiProxy.makeAsyncCall(mail.packageName, mail.methodName.SendToAdmins, request.toByteArray(), apiConfig), response);
	}
}
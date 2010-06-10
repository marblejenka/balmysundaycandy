package balmysundaycandy.more.low.level.operations.xmpp.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.concurrent.*;

import org.junit.*;

import balmysundaycandy.core.test.*;
import balmysundaycandy.more.low.level.operations.xmpp.*;

import com.google.appengine.api.xmpp.XMPPServicePb.*;
import com.google.apphosting.api.*;
import com.google.apphosting.api.ApiProxy.*;

public class SendInviteOperationTest extends XmppTestCase {
	@Test
	public void testCallXmppInviteRequest() {
		XmppInviteRequest request = new XmppInviteRequest();
		{
			request.setJid(ApiProxy.getCurrentEnvironment().getEmail());
			request.setFromJid(ApiProxy.getCurrentEnvironment().getEmail());
		}

		XmppInviteResponse response = XmppOperations.SEND_INVITE.call(request);

		assertThat(response, is(not(nullValue())));
	}

	@Test
	public void testCallAsyncXmppInviteRequestApiConfig() throws InterruptedException, ExecutionException {
		XmppInviteRequest request = new XmppInviteRequest();
		{
			request.setJid(ApiProxy.getCurrentEnvironment().getEmail());
			request.setFromJid(ApiProxy.getCurrentEnvironment().getEmail());
		}

		Future<XmppInviteResponse> response = XmppOperations.SEND_INVITE.callAsync(request, new ApiConfig());

		assertThat(response, is(not(nullValue())));
		assertThat(response.get(), is(not(nullValue())));
	}

}

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

public class SendMessageOperationTest extends XmppTestCase {

	@Test
	public void testCallXmppMessageRequest() {
		XmppMessageRequest request = new XmppMessageRequest();
		{
			request.addJid(ApiProxy.getCurrentEnvironment().getEmail());
			request.setFromJid(ApiProxy.getCurrentEnvironment().getEmail());

			request.setBody("xmpp body");
			request.setType("test");
		}

		XmppMessageResponse response = XmppOperations.SEND_MESSAGE.call(request);

		assertThat(response, is(not(nullValue())));
	}

	@Test
	public void testCallAsyncXmppMessageRequestApiConfig() throws InterruptedException, ExecutionException {
		XmppMessageRequest request = new XmppMessageRequest();
		{
			request.addJid(ApiProxy.getCurrentEnvironment().getEmail());
			request.setFromJid(ApiProxy.getCurrentEnvironment().getEmail());

			request.setBody("xmpp body");
			request.setType("test");
		}

		Future<XmppMessageResponse> response = XmppOperations.SEND_MESSAGE.callAsync(request, new ApiConfig());

		assertThat(response, is(not(nullValue())));
		assertThat(response.get(), is(not(nullValue())));
	}

}

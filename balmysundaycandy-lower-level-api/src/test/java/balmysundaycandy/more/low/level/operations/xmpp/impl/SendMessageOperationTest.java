package balmysundaycandy.more.low.level.operations.xmpp.impl;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import balmysundaycandy.core.test.EnvironmentConfiguration;
import balmysundaycandy.core.test.TestEnvironmentUtils;
import balmysundaycandy.more.low.level.operations.xmpp.XmppOperations;

import com.google.appengine.api.xmpp.XMPPServicePb.XmppMessageRequest;
import com.google.appengine.api.xmpp.XMPPServicePb.XmppMessageResponse;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;

public class SendMessageOperationTest {
	EnvironmentConfiguration environmentConfiguration = new EnvironmentConfiguration("", false, true);

	@Before
	public void setup() {
		TestEnvironmentUtils.setupEnvironment(environmentConfiguration);
	}

	@After
	public void teardown() {
		TestEnvironmentUtils.teardownEnvironment(environmentConfiguration);
	}

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

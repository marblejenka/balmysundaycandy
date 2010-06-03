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

import com.google.appengine.api.xmpp.XMPPServicePb.XmppInviteRequest;
import com.google.appengine.api.xmpp.XMPPServicePb.XmppInviteResponse;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;

public class SendInviteOperationTest {
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

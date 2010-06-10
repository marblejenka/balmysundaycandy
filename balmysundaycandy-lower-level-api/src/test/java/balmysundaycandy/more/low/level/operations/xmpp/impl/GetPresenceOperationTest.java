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

public class GetPresenceOperationTest extends XmppTestCase {
	@Test
	public void testCallPresenceRequest() {
		PresenceRequest request = new PresenceRequest();
		{
			request.setJid(ApiProxy.getCurrentEnvironment().getEmail());
			request.setFromJid(ApiProxy.getCurrentEnvironment().getEmail());
		}

		PresenceResponse response = XmppOperations.GET_PRESENCE.call(request);

		assertThat(response, is(not(nullValue())));
	}

	@Test
	public void testCallAsyncPresenceRequestApiConfig() throws InterruptedException, ExecutionException {
		PresenceRequest request = new PresenceRequest();
		{
			request.setJid(ApiProxy.getCurrentEnvironment().getEmail());
			request.setFromJid(ApiProxy.getCurrentEnvironment().getEmail());
		}

		Future<PresenceResponse> response = XmppOperations.GET_PRESENCE.callAsync(request, new ApiConfig());

		assertThat(response, is(not(nullValue())));
		assertThat(response.get(), is(not(nullValue())));
	}
}

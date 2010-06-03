package balmysundaycandy.more.low.level.operations.mail.impl;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import balmysundaycandy.core.test.EnvironmentConfiguration;
import balmysundaycandy.core.test.TestEnvironmentUtils;
import balmysundaycandy.more.low.level.operations.mail.MailOperations;

import com.google.appengine.api.mail.MailServicePb.MailMessage;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiBasePb.VoidProto;
import com.google.apphosting.api.ApiProxy.ApiConfig;

public class SendToAdminsOperationTest {
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
	public void testCallMailMessage() {
		MailMessage request = new MailMessage();
		{
			request.setSender(ApiProxy.getCurrentEnvironment().getEmail());

			request.setSubject("this is unit test from appengine, to admins");
			request.setHtmlBody("body");
		}
		VoidProto response = MailOperations.SEND_TO_ADMINS.call(request);
		assertThat(response, is(not(nullValue())));
	}

	@Test
	public void testCallAsyncMailMessageApiConfig() throws InterruptedException, ExecutionException {
		MailMessage request = new MailMessage();
		{
			request.setSender(ApiProxy.getCurrentEnvironment().getEmail());

			request.setSubject("this is unit test from appengine, to admins");
			request.setHtmlBody("body");
		}
		Future<VoidProto> response = MailOperations.SEND_TO_ADMINS.callAsync(request, new ApiConfig());

		assertThat(response, is(not(nullValue())));
		assertThat(response.get(), is(not(nullValue())));
	}

}

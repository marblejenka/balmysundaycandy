package balmysundaycandy.more.low.level.operations.mail.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.concurrent.*;

import org.junit.*;

import balmysundaycandy.core.test.*;
import balmysundaycandy.more.low.level.operations.mail.*;

import com.google.appengine.api.mail.MailServicePb.*;
import com.google.apphosting.api.*;
import com.google.apphosting.api.ApiBasePb.*;
import com.google.apphosting.api.ApiProxy.*;

public class SendToAdminsOperationTest extends MailTestCase {

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

package balmysundaycandy.more.low.level.operations.user.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.concurrent.*;

import org.junit.*;

import balmysundaycandy.core.test.*;
import balmysundaycandy.more.low.level.operations.user.*;

import com.google.apphosting.api.ApiProxy.*;
import com.google.apphosting.api.UserServicePb.*;

public class CreateLoginURLOperationTest extends UserTestCase {

	@Test
	public void testCallCreateLoginURLRequest() {
		CreateLoginURLRequest request = new CreateLoginURLRequest();
		{
			request.setAuthDomain("");
			request.setDestinationUrl("");
		}
		CreateLoginURLResponse response = UserOperations.CREATE_LOGIN_URL.call(request);

		assertThat(response, is(not(nullValue())));
	}

	@Test
	public void testCallAsyncCreateLoginURLRequestApiConfig() throws InterruptedException, ExecutionException {
		CreateLoginURLRequest request = new CreateLoginURLRequest();
		{
			request.setAuthDomain("");
			request.setDestinationUrl("");
		}
		Future<CreateLoginURLResponse> response = UserOperations.CREATE_LOGIN_URL.callAsync(request, new ApiConfig());

		assertThat(response, is(not(nullValue())));
		assertThat(response.get(), is(not(nullValue())));
	}
}

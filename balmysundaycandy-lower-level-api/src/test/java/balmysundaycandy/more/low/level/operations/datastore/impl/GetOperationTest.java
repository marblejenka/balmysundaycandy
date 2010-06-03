package balmysundaycandy.more.low.level.operations.datastore.impl;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import balmysundaycandy.core.test.EnvironmentConfiguration;
import balmysundaycandy.core.test.TestEnvironmentUtils;
import balmysundaycandy.more.low.level.operations.datastore.DatastoreOperations;

import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.apphosting.api.DatastorePb.GetRequest;
import com.google.apphosting.api.DatastorePb.GetResponse;
import com.google.storage.onestore.v3.OnestoreEntity.Path;
import com.google.storage.onestore.v3.OnestoreEntity.Reference;
import com.google.storage.onestore.v3.OnestoreEntity.Path.Element;

public class GetOperationTest {
	EnvironmentConfiguration environmentConfiguration = new EnvironmentConfiguration("", false, true);

	@Before
	public void setup() {
		TestEnvironmentUtils.setupEnvironment(environmentConfiguration);
	}

	@After
	public void teardown() {
		TestEnvironmentUtils.teardownEnvironment(environmentConfiguration);
	}
	
	private GetRequest buildGetRequest(){
		GetRequest request = new GetRequest();
		{
			long id = 1;
			String kind = "Foo";

			Element element = new Element();
			element.setId(id);
			element.setType(kind);

			Path path = new Path();
			path.addElement(element);

			Reference reference = new Reference();
			reference.setApp(ApiProxy.getCurrentEnvironment().getAppId());
			reference.setPath(path);

			request.addKey(reference);
		}
		
		return request;
	}
	

	@Test
	public void testCallGetRequest() {
		GetRequest request = buildGetRequest();

		GetResponse response = DatastoreOperations.GET.call(request);
		
		assertThat(response, is(not(nullValue())));
	}

	@Test
	public void testCallAsyncGetRequestApiConfig() throws InterruptedException, ExecutionException {
		GetRequest request = buildGetRequest();

		Future<GetResponse> response = DatastoreOperations.GET.callAsync(request, new ApiConfig());
		
		assertThat(response, is(not(nullValue())));
		assertThat(response.get(), is(not(nullValue())));
	}
}

package balmysundaycandy.more.low.level.operations.datastore.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.concurrent.*;

import org.junit.*;

import balmysundaycandy.core.test.*;
import balmysundaycandy.more.low.level.operations.datastore.*;

import com.google.apphosting.api.*;
import com.google.apphosting.api.ApiProxy.*;
import com.google.apphosting.api.DatastorePb.*;
import com.google.storage.onestore.v3.OnestoreEntity.*;
import com.google.storage.onestore.v3.OnestoreEntity.Path.*;

public class GetOperationTest extends DatastoreTestCase {

	private GetRequest buildGetRequest() {
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

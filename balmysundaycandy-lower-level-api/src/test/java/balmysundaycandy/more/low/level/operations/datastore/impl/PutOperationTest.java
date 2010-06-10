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

public class PutOperationTest extends DatastoreTestCase {

	private PutRequest buildPutRequest() {
		PutRequest request = new PutRequest();

		Element element = new Element();
		element.setId(1);
		element.setType("HOGE");
		element.setName("HOGE");

		Path path = new Path();
		path.addElement(element);

		Reference reference = new Reference();
		reference.setApp(ApiProxy.getCurrentEnvironment().getAppId());
		reference.setPath(path);

		EntityProto entityProto = new EntityProto();
		Property property = new Property();
		property.setName("name");
		PropertyValue propertyValue = new PropertyValue();
		propertyValue.setStringValue("String");
		property.setValue(propertyValue);
		entityProto.addProperty(property);
		entityProto.setKey(reference);

		request.addEntity(entityProto);

		return request;
	}

	@Test
	public void testCallPutRequest() {
		PutRequest request = buildPutRequest();

		PutResponse response = DatastoreOperations.PUT.call(request);

		assertThat(response, is(not(nullValue())));
	}

	@Test
	public void testCallAsyncPutRequestApiConfig() throws InterruptedException, ExecutionException {
		PutRequest request = buildPutRequest();

		Future<PutResponse> response = DatastoreOperations.PUT.callAsync(request, new ApiConfig());

		assertThat(response, is(not(nullValue())));
		assertThat(response.get(), is(not(nullValue())));
	}
}

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
import com.google.apphosting.api.DatastorePb.PutRequest;
import com.google.apphosting.api.DatastorePb.PutResponse;
import com.google.storage.onestore.v3.OnestoreEntity.EntityProto;
import com.google.storage.onestore.v3.OnestoreEntity.Path;
import com.google.storage.onestore.v3.OnestoreEntity.Property;
import com.google.storage.onestore.v3.OnestoreEntity.PropertyValue;
import com.google.storage.onestore.v3.OnestoreEntity.Reference;
import com.google.storage.onestore.v3.OnestoreEntity.Path.Element;

public class PutOperationTest {
	EnvironmentConfiguration environmentConfiguration = new EnvironmentConfiguration("", false, true);

	@Before
	public void setup() {
		TestEnvironmentUtils.setupEnvironment(environmentConfiguration);
	}

	@After
	public void teardown() {
		TestEnvironmentUtils.teardownEnvironment(environmentConfiguration);
	}
	
	private PutRequest buildPutRequest(){
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

package balmysundaycandy.more.low.level.operations.datastore.impl;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
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
import com.google.apphosting.api.DatastorePb.GetSchemaRequest;
import com.google.apphosting.api.DatastorePb.Schema;
import com.google.storage.onestore.v3.OnestoreEntity.EntityProto;
import com.google.storage.onestore.v3.OnestoreEntity.Path.Element;

public class GetSchemaOperationTest {
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
	public void testCallGetSchemaRequest() {
		GetSchemaRequest request = new GetSchemaRequest();
		request.setApp(ApiProxy.getCurrentEnvironment().getAppId());
		
		Schema schema = DatastoreOperations.GET_SCHEMA.call(request);
		
		assertThat(schema, is(not(nullValue())));
	}

	@Test
	public void testCallAsyncGetSchemaRequestApiConfig() throws InterruptedException, ExecutionException {
		GetSchemaRequest request = new GetSchemaRequest();
		request.setApp(ApiProxy.getCurrentEnvironment().getAppId());
		
		Future<Schema> schema = DatastoreOperations.GET_SCHEMA.callAsync(request, new ApiConfig());
		
		assertThat(schema, is(not(nullValue())));
		assertThat(schema.get(), is(not(nullValue())));
	}
	
	public void testCallGetSchemaRequest_() {
		GetSchemaRequest request = new GetSchemaRequest();
		request.setApp(ApiProxy.getCurrentEnvironment().getAppId());
		
		Schema schema = DatastoreOperations.GET_SCHEMA.call(request);
		
		List<EntityProto> entityProtoList = schema.kinds();
		List<String> kindList = new ArrayList<String>(entityProtoList.size());
		for (EntityProto entityProto : entityProtoList) {
			List<?> path = entityProto.getKey().getPath().elements();
			Element element = (Element) path.get(path.size() - 1);
			kindList.add(element.getType());
		}

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("kind size = ").append(schema.kindSize()).append("<br><br>");
		for (String string : kindList) {
			stringBuilder.append(string).append("<br>");
		}

		System.out.println(stringBuilder.toString());
	}
}

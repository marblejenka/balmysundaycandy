package balmysundaycandy.more.low.level.operations.datastore.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.*;
import java.util.concurrent.*;

import org.junit.*;

import balmysundaycandy.core.test.*;
import balmysundaycandy.more.low.level.operations.datastore.*;

import com.google.apphosting.api.*;
import com.google.apphosting.api.ApiProxy.*;
import com.google.apphosting.api.DatastorePb.*;
import com.google.storage.onestore.v3.OnestoreEntity.*;
import com.google.storage.onestore.v3.OnestoreEntity.Path.*;

public class GetSchemaOperationTest extends DatastoreTestCase {

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

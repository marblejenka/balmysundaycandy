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

import com.google.appengine.api.datastore.*;
import com.google.apphosting.api.*;
import com.google.apphosting.api.ApiBasePb.*;
import com.google.apphosting.api.ApiProxy.*;
import com.google.apphosting.api.DatastorePb.*;
import com.google.storage.onestore.v3.OnestoreEntity.*;

public class GetIndicesOperationTest extends DatastoreTestCase {
	@Before
	public void setup() {
		super.setup();

		Key key = KeyFactory.createKey("TEST", 5L);
		Entity entity = new Entity(key);
		entity.setProperty("name", "name");
		entity.setProperty("age", "sage");
		datastoreService.put(entity);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testCallStringProto() {
		StringProto request = new StringProto();
		request.setValue(ApiProxy.getCurrentEnvironment().getAppId());

		// execution
		CompositeIndices response = DatastoreOperations.GET_INDICES.call(request);

		assertThat(response, is(not(nullValue())));

		List<CompositeIndex> compositeIndexs = response.mutableIndexs();
		for (CompositeIndex compositeIndex : compositeIndexs) {
			System.out.println("=======================");
			System.out.println("state:" + compositeIndex.getState());
			System.out.println(compositeIndex.getMutableDefinition());
			System.out.println(compositeIndex.getDefaultPropertyIndex());
			System.out.println("=======================");
		}
	}

	@Test(expected = ExecutionException.class)
	public void testCallAsyncStringProtoApiConfig() throws InterruptedException, ExecutionException {
		StringProto request = new StringProto();
		request.setValue(ApiProxy.getCurrentEnvironment().getAppId());

		// execution
		Future<CompositeIndices> response = DatastoreOperations.GET_INDICES.callAsync(request, new ApiConfig());

		assertThat(response.get(), is(not(nullValue())));
		List<CompositeIndex> compositeIndexs = response.get().mutableIndexs();
		for (CompositeIndex compositeIndex : compositeIndexs) {
			System.out.println("=======================");
			System.out.println("state:" + compositeIndex.getState());
			System.out.println(compositeIndex.getMutableDefinition());
			System.out.println(compositeIndex.getDefaultPropertyIndex());
			System.out.println("=======================");
		}
	}

}

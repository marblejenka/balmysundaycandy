package balmysundaycandy.more.low.level.operations.datastore.impl;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import balmysundaycandy.core.test.EnvironmentConfiguration;
import balmysundaycandy.core.test.TestEnvironmentUtils;
import balmysundaycandy.more.low.level.operations.datastore.DatastoreOperations;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiBasePb.StringProto;
import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.apphosting.api.DatastorePb.CompositeIndices;
import com.google.storage.onestore.v3.OnestoreEntity.CompositeIndex;

public class GetIndicesOperationTest {
	EnvironmentConfiguration environmentConfiguration = new EnvironmentConfiguration("", false, true);
	
	DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();

	@Before
	public void setup() {
		TestEnvironmentUtils.setupEnvironment(environmentConfiguration);
		

		Key key = KeyFactory.createKey("TEST", 5L);
		Entity entity = new Entity(key);
		entity.setProperty("name", "name");
		entity.setProperty("age", "sage");
		datastoreService.put(entity);
	}

	@After
	public void teardown() {
		TestEnvironmentUtils.teardownEnvironment(environmentConfiguration);
	}
	

	@Test(expected=UnsupportedOperationException.class)
	public void testCallStringProto() {
		StringProto request = new StringProto();
		request.setValue(ApiProxy.getCurrentEnvironment().getAppId());
		
		// execution
		CompositeIndices response = DatastoreOperations.GET_INDICES.call(request);
		
		assertThat(response, is(not(nullValue())));
		
		// ‚¨‚Ü‚¯
		List<CompositeIndex> compositeIndexs = response.mutableIndexs();
		for (CompositeIndex compositeIndex : compositeIndexs) {
			System.out.println("=======================");
			System.out.println("state:" + compositeIndex.getState());
			System.out.println(compositeIndex.getMutableDefinition());
			System.out.println(compositeIndex.getDefaultPropertyIndex());
			System.out.println("=======================");
		}
	}


	@Test(expected=ExecutionException.class)
	public void testCallAsyncStringProtoApiConfig() throws InterruptedException, ExecutionException {
		StringProto request = new StringProto();
		request.setValue(ApiProxy.getCurrentEnvironment().getAppId());
		
		// execution
		Future<CompositeIndices> response = DatastoreOperations.GET_INDICES.callAsync(request, new ApiConfig());
		
		assertThat(response.get(), is(not(nullValue())));
		
		// ‚¨‚Ü‚¯
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

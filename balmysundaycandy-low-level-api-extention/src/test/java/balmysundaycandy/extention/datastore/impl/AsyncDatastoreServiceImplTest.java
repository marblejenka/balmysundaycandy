package balmysundaycandy.extention.datastore.impl;

import static org.junit.Assert.fail;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class AsyncDatastoreServiceImplTest {

	DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();

	AsyncDatastoreServiceImpl asyncDatastoreServiceImpl = new AsyncDatastoreServiceImpl();

	LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

	@Before
	public void setup() {
		helper.setUp();
	}

	public void teardown() {
		helper.tearDown();
	}

	@Test
	public void testGetKey() throws EntityNotFoundException, InterruptedException, ExecutionException {
		Entity e = new Entity(KeyFactory.createKey("test", 1));
		Key key = datastoreService.put(e);

		Future<Entity> future = asyncDatastoreServiceImpl.get(key);
		Entity entity = future.get();
		System.out.println(entity);
	}

	@Test
	public void testGetTransactionKey() throws EntityNotFoundException, InterruptedException, ExecutionException {
		Entity e = new Entity(KeyFactory.createKey("test", 1));
		Key key = datastoreService.put(e);

		Future<Entity> future = asyncDatastoreServiceImpl.get(datastoreService.beginTransaction(), key);
		Entity entity = future.get();
		System.out.println(entity);
	}

	@Test
	public void testPutEntity() throws InterruptedException, ExecutionException {
		Entity e = new Entity(KeyFactory.createKey("test", 1));
		Future<Key> future = asyncDatastoreServiceImpl.put(e);
		System.out.println(future.get());
	}

	@Test
	public void testGetIterableOfKey() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTransactionIterableOfKey() {
		fail("Not yet implemented");
	}

	@Test
	public void testAllocateIdsStringLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testAllocateIdsKeyStringLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testBeginTransaction() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteKeyArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteTransactionKeyArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteIterableOfKey() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteTransactionIterableOfKey() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetActiveTransactions() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCurrentTransaction() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCurrentTransactionTransaction() {
		fail("Not yet implemented");
	}

	@Test
	public void testPrepareQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testPrepareTransactionQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testPutTransactionEntity() {
		fail("Not yet implemented");
	}

	@Test
	public void testPutIterableOfEntity() {
		fail("Not yet implemented");
	}

	@Test
	public void testPutTransactionIterableOfEntity() {
		fail("Not yet implemented");
	}

}

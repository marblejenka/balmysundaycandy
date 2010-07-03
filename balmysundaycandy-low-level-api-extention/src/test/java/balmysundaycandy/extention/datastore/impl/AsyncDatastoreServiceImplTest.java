package balmysundaycandy.extention.datastore.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.*;
import java.util.concurrent.*;

import org.junit.*;

import balmysundaycandy.core.test.*;

import com.google.appengine.api.datastore.*;
import com.google.apphosting.api.ApiBasePb.*;

public class AsyncDatastoreServiceImplTest extends DatastoreTestCase {

	AsyncDatastoreServiceImpl asyncDatastoreServiceImpl = new AsyncDatastoreServiceImpl();

	public Key prepare() {
		Transaction transaction = datastoreService.beginTransaction();
		Entity e = new Entity(KeyFactory.createKey("test", 1));
		Key key = datastoreService.put(transaction, e);
		transaction.commit();

		return key;
	}
	
	@Test
	public void testGetKey() throws EntityNotFoundException, InterruptedException, ExecutionException {
		Key key = prepare();

		Future<Entity> future = asyncDatastoreServiceImpl.get(key);
		Entity entity = future.get();

		assertThat(entity, is(not(nullValue())));
		assertThat(entity.getKey(), is(key));
	}

	@Test
	public void testGetTransactionKey() throws EntityNotFoundException, InterruptedException, ExecutionException {
		Key key = prepare();
		Transaction transaction = datastoreService.beginTransaction();

		Future<Entity> future = asyncDatastoreServiceImpl.get(transaction, key);
		Entity entity = future.get();

		transaction.commit();

		assertThat(entity, is(not(nullValue())));
		assertThat(entity.getKey(), is(key));
	}

	@Test
	public void testGetIterableOfKey() throws InterruptedException, ExecutionException, EntityNotFoundException {
		Key key = prepare();
		List<Key> list = new ArrayList<Key>();
		list.add(key);

		Future<Map<Key, Entity>> future = asyncDatastoreServiceImpl.get(list);
		Map<Key, Entity> entites = future.get();

		assertThat(entites, is(not(nullValue())));
		assertThat(entites.size(), is(1));
		assertThat(entites.get(key), is(not(nullValue())));
	}

	@Test
	public void testGetTransactionIterableOfKey() throws InterruptedException, ExecutionException {
		Key key = prepare();
		List<Key> list = new ArrayList<Key>();
		list.add(key);
		Transaction transaction = datastoreService.beginTransaction();

		Future<Map<Key, Entity>> future = asyncDatastoreServiceImpl.get(transaction, list);
		Map<Key, Entity> entites = future.get();
		transaction.commit();

		assertThat(entites, is(not(nullValue())));
		assertThat(entites.size(), is(1));
		assertThat(entites.get(key), is(not(nullValue())));
	}

	@Test
	public void testPutEntity() throws InterruptedException, ExecutionException {
		Entity e = new Entity(KeyFactory.createKey("test", 1));

		Future<Key> result = asyncDatastoreServiceImpl.put(e);

		Key key = result.get();
		assertThat(key, is(not(nullValue())));
		assertThat(key.getKind(), is("test"));
	}

	@Test
	public void testPutTransactionEntity() throws InterruptedException, ExecutionException {
		Entity e = new Entity(KeyFactory.createKey("test", 1));
		Transaction transaction = datastoreService.beginTransaction();

		Future<Key> result = asyncDatastoreServiceImpl.put(transaction, e);
		transaction.commit();

		Key key = result.get();
		assertThat(key, is(not(nullValue())));
		assertThat(key.getKind(), is("test"));
	}

	@Test
	public void testPutIterableOfEntity() throws InterruptedException, ExecutionException {
		Entity e = new Entity(KeyFactory.createKey("test", 1));
		List<Entity> list = new ArrayList<Entity>();
		list.add(e);

		Future<List<Key>> result = asyncDatastoreServiceImpl.put(list);

		List<Key> key = result.get();
		assertThat(key, is(not(nullValue())));
		assertThat(key.size(), is(1));
		assertThat(key.get(0).getKind(), is("test"));
	}

	@Test
	public void testPutTransactionIterableOfEntity() throws InterruptedException, ExecutionException {
		Entity e = new Entity(KeyFactory.createKey("test", 1));
		List<Entity> list = new ArrayList<Entity>();
		list.add(e);
		Transaction transaction = datastoreService.beginTransaction();

		Future<List<Key>> result = asyncDatastoreServiceImpl.put(transaction, list);
		transaction.commit();

		List<Key> key = result.get();
		assertThat(key, is(not(nullValue())));
		assertThat(key.size(), is(1));
		assertThat(key.get(0).getKind(), is("test"));
	}

	@Test(expected = EntityNotFoundException.class)
	public void testDeleteKeyArray() throws InterruptedException, ExecutionException, EntityNotFoundException {
		Key key = prepare();

		Future<VoidProto> future = asyncDatastoreServiceImpl.delete(key);
		VoidProto result = future.get();
		asyncDatastoreServiceImpl.realizeAll();

		assertThat(result, is(not(nullValue())));

		// cause EntityNotFoundException
		datastoreService.get(key);
	}

	@Test(expected = EntityNotFoundException.class)
	public void testDeleteTransactionKeyArray() throws InterruptedException, ExecutionException, EntityNotFoundException {
		Key key = prepare();
		Transaction transaction = datastoreService.beginTransaction();

		Future<VoidProto> future = asyncDatastoreServiceImpl.delete(transaction, key);
		VoidProto result = future.get();
		transaction.commit();

		assertThat(result, is(not(nullValue())));

		// cause EntityNotFoundException
		datastoreService.get(key);
	}

	@Test(expected = EntityNotFoundException.class)
	public void testDeleteIterableOfKey() throws InterruptedException, ExecutionException, EntityNotFoundException {
		Key key = prepare();
		List<Key> list = new ArrayList<Key>();
		list.add(key);

		Future<VoidProto> future = asyncDatastoreServiceImpl.delete(list);
		VoidProto result = future.get();
		asyncDatastoreServiceImpl.realizeAll();

		assertThat(result, is(not(nullValue())));

		// cause EntityNotFoundException
		datastoreService.get(key);
	}

	@Test(expected = EntityNotFoundException.class)
	public void testDeleteTransactionIterableOfKey() throws InterruptedException, ExecutionException, EntityNotFoundException {
		Key key = prepare();
		List<Key> list = new ArrayList<Key>();
		list.add(key);
		Transaction transaction = datastoreService.beginTransaction();

		Future<VoidProto> future = asyncDatastoreServiceImpl.delete(transaction, list);
		VoidProto result = future.get();
		transaction.commit();

		assertThat(result, is(not(nullValue())));

		// cause EntityNotFoundException
		datastoreService.get(key);
	}

	@Test
	public void testBeginTransaction() throws InterruptedException, ExecutionException {
		Transaction result = asyncDatastoreServiceImpl.beginTransaction();
		result.commit();
	}

	@Test
	public void testGetActiveTransactions() {
		Assume.assumeTrue(false);
		fail("Not yet implemented");
	}

	@Test
	public void testGetCurrentTransaction() {
		Assume.assumeTrue(false);
		fail("Not yet implemented");
	}

	@Test
	public void testGetCurrentTransactionTransaction() {
		Assume.assumeTrue(false);
		fail("Not yet implemented");
	}

	@Test
	public void testPrepareQuery() {
		Assume.assumeTrue(false);
		fail("Not yet implemented");
	}

	@Test
	public void testPrepareTransactionQuery() {
		Assume.assumeTrue(false);
		fail("Not yet implemented");
	}

	@Test
	public void testAllocateIdsStringLong() throws InterruptedException, ExecutionException {
		Future<KeyRange> result = asyncDatastoreServiceImpl.allocateIds("test", 10);
		KeyRange keyRange = result.get();

		assertThat(result, is(not(nullValue())));
		assertThat(keyRange.getSize(), is(10L));
	}

	@Test
	public void testAllocateIdsKeyStringLong() throws InterruptedException, ExecutionException {
		Key parent = prepare();

		Future<KeyRange> result = asyncDatastoreServiceImpl.allocateIds(parent, "test", 10);
		KeyRange keyRange = result.get();

		assertThat(result, is(not(nullValue())));
		assertThat(keyRange.getSize(), is(10L));
	}
}

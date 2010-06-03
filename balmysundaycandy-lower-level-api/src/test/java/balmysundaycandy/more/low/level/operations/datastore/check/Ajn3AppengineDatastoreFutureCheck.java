package balmysundaycandy.more.low.level.operations.datastore.check;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import balmysundaycandy.core.test.EnvironmentConfiguration;
import balmysundaycandy.core.test.TestEnvironmentUtils;
import balmysundaycandy.more.low.level.operations.datastore.DatastoreOperations;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.KeyRange;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.DatastorePb.GetRequest;
import com.google.apphosting.api.DatastorePb.GetResponse;
import com.google.storage.onestore.v3.OnestoreEntity.Path;
import com.google.storage.onestore.v3.OnestoreEntity.Reference;
import com.google.storage.onestore.v3.OnestoreEntity.Path.Element;

/**
 * #ajn3 datastore futeure check. original source is written by @shin1ogawa.
 * 
 * @author marblejenka
 * 
 */
public class Ajn3AppengineDatastoreFutureCheck {
	EnvironmentConfiguration environmentConfiguration = new EnvironmentConfiguration("", false, true);

	@Before
	public void setup() {
		TestEnvironmentUtils.setupEnvironment(environmentConfiguration);
	}

	@After
	public void teardown() {
		TestEnvironmentUtils.teardownEnvironment(environmentConfiguration);
	}

	public GetRequest createGetRequest(Long id, String kind) {
		GetRequest getRequest = new GetRequest();
		{
			Element element = new Element();
			element.setId(id);
			element.setType(kind);

			Path path = new Path();
			path.addElement(element);

			Reference reference = new Reference();
			reference.setApp(ApiProxy.getCurrentEnvironment().getAppId());
			reference.setPath(path);

			getRequest.addKey(reference);
		}
		return getRequest;
	}

	@Test
	public void testParentLessChildPutWithoutTransaction() {
		DatastoreService service = DatastoreServiceFactory.getDatastoreService();
		KeyRange parentKeyRange = service.allocateIds("parent", 1);
		Key parentKey = parentKeyRange.getStart();
		KeyRange childKeyRange = service.allocateIds(parentKey, "child", 1);
		Key childKey = childKeyRange.getStart();

		Entity parent = new Entity(parentKey);
		Entity child = new Entity(childKey);

		service.put(parent);
		service.put(child);

		// more low level‚É‚æ‚éget
		GetRequest request = createGetRequest(1L, "child");
		GetResponse response = DatastoreOperations.GET.call(request);

		System.out.println(response);
	}

	@Test
	public void testParentLessChildPutWithTransaction() throws EntityNotFoundException {
		DatastoreService service = DatastoreServiceFactory.getDatastoreService();
		KeyRange parentKeyRange = service.allocateIds("parent", 1);
		Key parentKey = parentKeyRange.getStart();
		KeyRange childKeyRange = service.allocateIds(parentKey, "child", 1);
		Key childKey = childKeyRange.getStart();

		@SuppressWarnings("unused")
		Entity parent = new Entity(parentKey);
		Entity child = new Entity(childKey);

		Transaction tx = service.beginTransaction();
		service.put(tx, child);
		tx.commit();

		assertThat(service.get(childKey), is(not(nullValue())));
		assertThat(service.prepare(new Query("child")).countEntities(), is(equalTo(1)));
		assertThat(service.prepare(new Query("parent")).countEntities(), is(equalTo(0)));

		Entity child2 = service.get(childKey);
		tx = service.beginTransaction();
		child2.setProperty("prop", "a");
		service.put(tx, child2);
		tx.commit();

		// more low level‚É‚æ‚éget
		GetRequest request = createGetRequest(1L, "child");
		GetResponse response = DatastoreOperations.GET.call(request);

		System.out.println(response);
	}

	@Test
	public void testParentLessChildPutWithTransactionCreateKeyByKeyFactory() throws EntityNotFoundException {
		DatastoreService service = DatastoreServiceFactory.getDatastoreService();
		Key parentKey = KeyFactory.createKey("parent", "a");
		Key childKey = KeyFactory.createKey(parentKey, "child", "a");

		@SuppressWarnings("unused")
		Entity parent = new Entity(parentKey);
		Entity child = new Entity(childKey);

		Transaction tx = service.beginTransaction();
		service.put(tx, child);
		tx.commit();

		assertThat(service.get(childKey), is(not(nullValue())));
		assertThat(service.prepare(new Query("child")).countEntities(), is(equalTo(1)));
		assertThat(service.prepare(new Query("parent")).countEntities(), is(equalTo(0)));

		Entity child2 = service.get(childKey);
		tx = service.beginTransaction();
		child2.setProperty("prop", "a");
		service.put(tx, child2);
		tx.commit();

		// more low level‚É‚æ‚éget
		GetRequest request = createGetRequest(1L, "child");
		GetResponse response = DatastoreOperations.GET.call(request);

		System.out.println(response);

	}
}

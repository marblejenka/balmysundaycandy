package balmysundaycandy.core.test;

import org.junit.*;

import com.google.appengine.api.datastore.*;
import com.google.appengine.tools.development.testing.*;


public class DatastoreTestCase {
	protected DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();

	protected LocalServiceTestHelper helper;

	@Before
	public void setup() {
		helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
		helper.setUp();
	}

	@After
	public void teardown() {
		helper.tearDown();
	}
}

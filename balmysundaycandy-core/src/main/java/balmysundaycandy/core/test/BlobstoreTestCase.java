package balmysundaycandy.core.test;

import org.junit.*;

import com.google.appengine.tools.development.testing.*;


public class BlobstoreTestCase {

	protected LocalServiceTestHelper helper;

	@Before
	public void setup() {
		helper = new LocalServiceTestHelper(new LocalBlobstoreServiceTestConfig());
		helper.setUp();
	}

	@After
	public void teardown() {
		helper.tearDown();
	}
}

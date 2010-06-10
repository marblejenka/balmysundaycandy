package balmysundaycandy.core.test;

import org.junit.*;

import com.google.appengine.tools.development.testing.*;


public class MailTestCase {

	protected LocalServiceTestHelper helper;

	@Before
	public void setup() {
		helper = new LocalServiceTestHelper(new LocalMailServiceTestConfig());
		helper.setUp();
	}

	@After
	public void teardown() {
		helper.tearDown();
	}
}

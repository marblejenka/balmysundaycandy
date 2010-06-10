package balmysundaycandy.core.test;

import org.junit.*;

import com.google.appengine.tools.development.testing.*;


public class XmppTestCase {

	protected LocalServiceTestHelper helper;

	@Before
	public void setup() {
		helper = new LocalServiceTestHelper(new LocalXMPPServiceTestConfig());
		helper.setUp();
	}

	@After
	public void teardown() {
		helper.tearDown();
	}
}

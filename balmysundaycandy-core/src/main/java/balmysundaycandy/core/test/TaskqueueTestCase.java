package balmysundaycandy.core.test;

import org.junit.*;

import com.google.appengine.tools.development.testing.*;


public class TaskqueueTestCase {

	protected LocalServiceTestHelper helper;

	@Before
	public void setup() {
		helper = new LocalServiceTestHelper(new LocalTaskQueueTestConfig());
		helper.setUp();
	}

	@After
	public void teardown() {
		helper.tearDown();
	}
}

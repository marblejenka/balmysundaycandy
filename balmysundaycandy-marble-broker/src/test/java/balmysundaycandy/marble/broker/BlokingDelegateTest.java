package balmysundaycandy.marble.broker;

import org.junit.Test;

public class BlokingDelegateTest {

	@Test
	public void testCallTrace() {
		BlokingDelegate blokingDelegate = new BlokingDelegate();
		System.out.println(blokingDelegate.callTrace());
	}

}

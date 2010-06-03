package balmysundaycandy.more.low.level.operations.urlfetch;

import balmysundaycandy.core.anntations.WorkOnAppengineEnvironment;
import balmysundaycandy.core.anntations.WorkOnLocalEnvironment;
import balmysundaycandy.more.low.level.operations.urlfetch.impl.FetchOperation;

/**
 * urlfetch operations.
 * 
 * @author marblejenka
 * 
 */
public class UrlfetchOperations {
	@WorkOnAppengineEnvironment
	@WorkOnLocalEnvironment
	public static final FetchOperation FETCH = new FetchOperation();
}

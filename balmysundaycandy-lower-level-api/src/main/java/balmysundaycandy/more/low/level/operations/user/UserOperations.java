package balmysundaycandy.more.low.level.operations.user;

import balmysundaycandy.core.anntations.WorkOnAppengineEnvironment;
import balmysundaycandy.core.anntations.WorkOnLocalEnvironment;
import balmysundaycandy.more.low.level.operations.user.impl.CreateLoginURLOperation;
import balmysundaycandy.more.low.level.operations.user.impl.CreateLogoutURLOperation;

/**
 * user operations.
 * 
 * @author marblejenka
 * 
 */
public class UserOperations {
	@WorkOnAppengineEnvironment
	@WorkOnLocalEnvironment
	public static final CreateLoginURLOperation CREATE_LOGIN_URL = new CreateLoginURLOperation();

	@WorkOnAppengineEnvironment
	@WorkOnLocalEnvironment
	public static final CreateLogoutURLOperation CREATE_LOGOUT_URL = new CreateLogoutURLOperation();
}

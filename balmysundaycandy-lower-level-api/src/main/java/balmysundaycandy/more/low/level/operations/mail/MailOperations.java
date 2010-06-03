package balmysundaycandy.more.low.level.operations.mail;

import balmysundaycandy.core.anntations.WorkOnAppengineEnvironment;
import balmysundaycandy.core.anntations.WorkOnLocalEnvironment;
import balmysundaycandy.more.low.level.operations.mail.impl.SendOperation;
import balmysundaycandy.more.low.level.operations.mail.impl.SendToAdminsOperation;

/**
 * mail operations.
 * 
 * @author marblejenka
 * 
 */
public class MailOperations {
	@WorkOnAppengineEnvironment
	@WorkOnLocalEnvironment
	public static final SendOperation SEND = new SendOperation();

	@WorkOnAppengineEnvironment
	@WorkOnLocalEnvironment
	public static final SendToAdminsOperation SEND_TO_ADMINS = new SendToAdminsOperation();
}

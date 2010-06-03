package balmysundaycandy.more.low.level.operations.xmpp;

import balmysundaycandy.core.anntations.UnderExploration;
import balmysundaycandy.core.anntations.WorkOnLocalEnvironment;
import balmysundaycandy.more.low.level.operations.xmpp.impl.GetPresenceOperation;
import balmysundaycandy.more.low.level.operations.xmpp.impl.SendInviteOperation;
import balmysundaycandy.more.low.level.operations.xmpp.impl.SendMessageOperation;

/**
 * xmpp operations.
 * 
 * @author marblejenka
 * 
 */
public class XmppOperations {
	@UnderExploration
	@WorkOnLocalEnvironment
	public static final GetPresenceOperation GET_PRESENCE = new GetPresenceOperation();

	@UnderExploration
	@WorkOnLocalEnvironment
	public static final SendInviteOperation SEND_INVITE = new SendInviteOperation();

	@UnderExploration
	@WorkOnLocalEnvironment
	public static final SendMessageOperation SEND_MESSAGE = new SendMessageOperation();
}

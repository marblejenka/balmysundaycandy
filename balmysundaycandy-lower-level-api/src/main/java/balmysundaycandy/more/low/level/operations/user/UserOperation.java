package balmysundaycandy.more.low.level.operations.user;

import java.util.concurrent.Future;

import com.google.appengine.repackaged.com.google.io.protocol.ProtocolMessage;
import com.google.apphosting.api.ApiProxy.ApiConfig;

/**
 * user operations.
 * 
 * @author marblejenka
 * 
 * @param <Request>
 * @param <Response>
 */
public abstract class UserOperation<Request extends ProtocolMessage<?>, Response extends ProtocolMessage<?>> {
	protected static final String packageName = "user";

	public abstract Response call(Request request);

	public abstract Future<Response> callAsync(Request request, ApiConfig apiConfig);
}

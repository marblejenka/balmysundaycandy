package balmysundaycandy.more.low.level.operations.taskqueue;

import java.util.concurrent.Future;

import com.google.appengine.repackaged.com.google.io.protocol.ProtocolMessage;
import com.google.apphosting.api.ApiProxy.ApiConfig;

/**
 * taskqueue operation definition
 * 
 * @author marblejenka
 * 
 * @param <Request>
 *            request protocol buffer
 * @param <Response>
 *            response protocol buffer
 */
public abstract class TaskqueueOperation<Request extends ProtocolMessage<?>, Response extends ProtocolMessage<?>> {
	protected static final String packageName = "taskqueue";

	public abstract Response call(Request request);

	public abstract Future<Response> callAsync(Request request, ApiConfig apiConfig);
}

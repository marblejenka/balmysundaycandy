package balmysundaycandy.more.low.level.operations.memcached;

import balmysundaycandy.core.future.GeneratedMessageFuture;

import com.google.appengine.repackaged.com.google.protobuf.AbstractMessage;
import com.google.appengine.repackaged.com.google.protobuf.GeneratedMessage.Builder;
import com.google.apphosting.api.ApiProxy.ApiConfig;

/**
 * @author marblejenka
 * 
 * @param <Request>
 * @param <Response>
 */
public abstract class MemcacheOperation<Request extends AbstractMessage, Response extends AbstractMessage> {
	protected static final String packageName = "memcache";

	public abstract Response call(Request request);

	public abstract GeneratedMessageFuture<Response, Builder<?>> callAsync(Request request, ApiConfig apiConfig);
}

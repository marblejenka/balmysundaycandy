package balmysundaycandy.more.low.level.operations.urlfetch;

import balmysundaycandy.core.future.GeneratedMessageFuture;

import com.google.appengine.repackaged.com.google.protobuf.GeneratedMessage;
import com.google.appengine.repackaged.com.google.protobuf.GeneratedMessage.Builder;
import com.google.apphosting.api.ApiProxy.ApiConfig;

public abstract class UrlfetchOperation<Request extends GeneratedMessage, Response extends GeneratedMessage> {
	protected static final String packageName = "urlfetch";

	public abstract Response call(Request request);

	public abstract GeneratedMessageFuture<Response, Builder<?>> callAsync(Request request, ApiConfig apiConfig);
}

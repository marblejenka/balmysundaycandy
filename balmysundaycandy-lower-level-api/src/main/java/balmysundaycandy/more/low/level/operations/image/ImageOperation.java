package balmysundaycandy.more.low.level.operations.image;

import java.util.concurrent.Future;

import com.google.appengine.repackaged.com.google.io.protocol.ProtocolMessage;
import com.google.apphosting.api.ApiProxy.ApiConfig;

/**
 * image operations.
 * 
 * @author marblejenka
 * 
 * @param <Request>
 * @param <Response>
 */
public abstract class ImageOperation<Request extends ProtocolMessage<?>, Response extends ProtocolMessage<?>> {
	protected static final String packageName = "images";

	public abstract Response call(Request request);

	public abstract Future<Response> callAsync(Request request, ApiConfig apiConfig);
}

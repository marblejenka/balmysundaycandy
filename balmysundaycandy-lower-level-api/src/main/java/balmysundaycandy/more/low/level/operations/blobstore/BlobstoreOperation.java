package balmysundaycandy.more.low.level.operations.blobstore;

import java.util.concurrent.Future;

import com.google.appengine.repackaged.com.google.io.protocol.ProtocolMessage;
import com.google.apphosting.api.ApiProxy.ApiConfig;

/**
 * blobstore operation.
 * 
 * @author marblejenka
 *
 */
public abstract class BlobstoreOperation<Request extends ProtocolMessage<?>, Response extends ProtocolMessage<?>> {
	protected static final String packagename = "blobstore";

	public abstract Response call(Request request);

	public abstract Future<Response> callAsync(Request request, ApiConfig apiConfig);

}

package balmysundaycandy.more.low.level.operations.datastore;

import java.util.concurrent.Future;


import com.google.appengine.repackaged.com.google.io.protocol.ProtocolMessage;
import com.google.apphosting.api.ApiProxy.ApiConfig;

/**
 * datastore operation.
 * 
 * @author marblejenka
 */
public abstract class DatastoreOperation<Request extends ProtocolMessage<?>, Response extends ProtocolMessage<?>> {
	protected static final String DATASTORE_VERSION = "datastore_v3";

	public abstract Response call(Request request);

	public abstract Future<Response> callAsync(Request request, ApiConfig apiConfig);
}

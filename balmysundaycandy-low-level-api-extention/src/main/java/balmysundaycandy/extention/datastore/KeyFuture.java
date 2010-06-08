package balmysundaycandy.extention.datastore;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.ReferenceTranslator;
import com.google.apphosting.api.DatastorePb.PutResponse;

public class KeyFuture implements Future<Key> {

	Future<PutResponse> protocolMessageFuture;

	public KeyFuture(Future<PutResponse> protocolMessageFuture) {
		this.protocolMessageFuture = protocolMessageFuture;
	}

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		return protocolMessageFuture.cancel(mayInterruptIfRunning);
	}

	@Override
	public Key get() throws InterruptedException, ExecutionException {
		PutResponse response = protocolMessageFuture.get();
		return putresponse2key(response);
	}

	@Override
	public Key get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		PutResponse response = protocolMessageFuture.get(timeout, unit);
		return putresponse2key(response);
	}

	@Override
	public boolean isCancelled() {
		return protocolMessageFuture.isCancelled();
	}

	@Override
	public boolean isDone() {
		return protocolMessageFuture.isDone();
	}
	
	// TODO low level apiと実装があってないかも
	private Key putresponse2key(PutResponse putResponse) {
		return ReferenceTranslator.reference2key(putResponse.getKey(0));
	}
}

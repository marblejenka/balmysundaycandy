package balmysundaycandy.extention.datastore;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.google.appengine.api.datastore.GetRequestTransralator;
import com.google.appengine.api.datastore.Key;
import com.google.apphosting.api.DatastorePb.PutResponse;
import com.google.storage.onestore.v3.OnestoreEntity.Reference;

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
		Reference reference = response.getKey(0);
		return GetRequestTransralator.reference2key(reference);
	}

	@Override
	public Key get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		return null;
	}

	@Override
	public boolean isCancelled() {
		return protocolMessageFuture.isCancelled();
	}

	@Override
	public boolean isDone() {
		return protocolMessageFuture.isDone();
	}

}

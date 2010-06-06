package balmysundaycandy.extention.datastore;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.apphosting.api.DatastorePb.GetResponse;

public class EnittyMapFuture implements Future<Map<Key, Entity>> {

	Future<GetResponse> protocolMessageFuture;

	public EnittyMapFuture(Future<GetResponse> protocolMessageFuture) {
		this.protocolMessageFuture = protocolMessageFuture;
	}

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		return protocolMessageFuture.cancel(mayInterruptIfRunning);
	}

	@Override
	public Map<Key, Entity> get() throws InterruptedException, ExecutionException {
		GetResponse response = protocolMessageFuture.get();
		
		
		return null;
	}

	@Override
	public Map<Key, Entity> get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		GetResponse response = protocolMessageFuture.get(timeout, unit);
		
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

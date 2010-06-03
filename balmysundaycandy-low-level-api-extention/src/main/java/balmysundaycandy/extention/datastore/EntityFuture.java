package balmysundaycandy.extention.datastore;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityTranslator;
import com.google.apphosting.api.DatastorePb.GetResponse;

public class EntityFuture implements Future<Entity> {

	Future<GetResponse> protocolMessageFuture;

	public EntityFuture(Future<GetResponse> protocolMessageFuture) {
		this.protocolMessageFuture = protocolMessageFuture;
	}

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		return protocolMessageFuture.cancel(mayInterruptIfRunning);
	}

	@Override
	public Entity get() throws InterruptedException, ExecutionException {
		GetResponse response = protocolMessageFuture.get();
		return EntityTranslator.createFromPb(response.getEntity(0).getEntity());
	}

	@Override
	public Entity get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		GetResponse response = protocolMessageFuture.get(timeout, unit);
		return EntityTranslator.createFromPb(response.getEntity(0).getEntity());
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

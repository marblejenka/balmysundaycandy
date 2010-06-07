package balmysundaycandy.extention.datastore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityTranslator;
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
		return getresponse2keymap(response);
	}

	@Override
	public Map<Key, Entity> get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		GetResponse response = protocolMessageFuture.get(timeout, unit);
		return getresponse2keymap(response);
	}

	@Override
	public boolean isCancelled() {
		return protocolMessageFuture.isCancelled();
	}

	@Override
	public boolean isDone() {
		return protocolMessageFuture.isDone();
	}
	
	private Map<Key, Entity> getresponse2keymap(GetResponse getResponse) {
		Map<Key, Entity> result = new HashMap<Key, Entity>();
		for (com.google.apphosting.api.DatastorePb.GetResponse.Entity e : getResponse.entitys()) {
			Entity entity = EntityTranslator.createFromPb(e.getEntity());
			result.put(entity.getKey(), entity);
		}
		return result;
	}
}

package balmysundaycandy.extention.datastore;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyRange;
import com.google.apphosting.api.DatastorePb.AllocateIdsResponse;

public class KeyRangeFuture implements Future<KeyRange>{

	Key parent;
	
	String kind;
	
	Future<AllocateIdsResponse> protocolMessageFuture;

	public KeyRangeFuture(Key parent, String kind, Future<AllocateIdsResponse> protocolMessageFuture) {
		this.parent = parent;
		this.kind = kind;
		this.protocolMessageFuture = protocolMessageFuture;
	}

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		return protocolMessageFuture.cancel(mayInterruptIfRunning);
	}

	@Override
	public KeyRange get() throws InterruptedException, ExecutionException {
		AllocateIdsResponse response = protocolMessageFuture.get();
		return new KeyRange(parent, kind, response.getStart(), response.getEnd());
	}

	@Override
	public KeyRange get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		AllocateIdsResponse response = protocolMessageFuture.get(timeout, unit);
		return new KeyRange(parent, kind, response.getStart(), response.getEnd());
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

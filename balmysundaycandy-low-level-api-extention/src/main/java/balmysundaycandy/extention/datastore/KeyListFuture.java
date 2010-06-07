package balmysundaycandy.extention.datastore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.google.appengine.api.datastore.GetRequestTransralator;
import com.google.appengine.api.datastore.Key;
import com.google.apphosting.api.DatastorePb.PutResponse;

public class KeyListFuture implements Future<List<Key>> {
	Future<PutResponse> protocolMessageFuture;

	public KeyListFuture(Future<PutResponse> protocolMessageFuture) {
		this.protocolMessageFuture = protocolMessageFuture;
	}

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		return protocolMessageFuture.cancel(mayInterruptIfRunning);
	}

	@Override
	public List<Key> get() throws InterruptedException, ExecutionException {
		PutResponse response = protocolMessageFuture.get();
		return putresponse2keylist(response);
	}

	@Override
	public List<Key> get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		PutResponse response = protocolMessageFuture.get(timeout, unit);
		return putresponse2keylist(response);
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
	private List<Key> putresponse2keylist(PutResponse putResponse) {
		int keySize = putResponse.keySize();
		List<Key> result = new ArrayList<Key>(keySize);
		for (int i = 0; i < keySize; i++) {
			result.add(GetRequestTransralator.reference2key(putResponse.getKey(i)));
		}
		return result;
	}
}

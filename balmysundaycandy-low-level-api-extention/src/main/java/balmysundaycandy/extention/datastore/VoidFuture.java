package balmysundaycandy.extention.datastore;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.google.apphosting.api.ApiBasePb.VoidProto;

public class VoidFuture implements Future<VoidProto> {

	Future<VoidProto> protocolMessageFuture;

	public VoidFuture(Future<VoidProto> protocolMessageFuture) {
		this.protocolMessageFuture = protocolMessageFuture;
	}

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		return protocolMessageFuture.cancel(mayInterruptIfRunning);
	}

	@Override
	public VoidProto get() throws InterruptedException, ExecutionException {
		return protocolMessageFuture.get();
	}

	@Override
	public VoidProto get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		return protocolMessageFuture.get(timeout, unit);
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

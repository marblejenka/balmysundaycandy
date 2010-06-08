package balmysundaycandy.extention.datastore;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.google.apphosting.api.ApiBasePb.VoidProto;
import com.google.apphosting.api.DatastorePb.DeleteResponse;

public class DeleteResponseFuture implements Future<VoidProto> {

	Future<DeleteResponse> protocolMessageFuture;

	public DeleteResponseFuture(Future<DeleteResponse> protocolMessageFuture) {
		this.protocolMessageFuture= protocolMessageFuture;
	}

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		return protocolMessageFuture.cancel(mayInterruptIfRunning);
	}

	@Override
	public VoidProto get() throws InterruptedException, ExecutionException {
		protocolMessageFuture.get();
		return VoidProto.IMMUTABLE_DEFAULT_INSTANCE;
	}

	@Override
	public VoidProto get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		protocolMessageFuture.get(timeout, unit);
		return VoidProto.IMMUTABLE_DEFAULT_INSTANCE;
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

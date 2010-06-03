package balmysundaycandy.core.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.google.appengine.repackaged.com.google.io.protocol.ProtocolMessage;

/**
 * wrap "makeAsyncCall" Future.
 * 
 * @author marblejenka
 * 
 * @param <P>
 *            protocol buffer object
 */
public class ProtocolMessageFuture<P extends ProtocolMessage<P>> implements Future<P> {

	private Future<byte[]> future;
	private P protocolbuffer;

	public ProtocolMessageFuture(Future<byte[]> future, P protocolbuffer) {
		this.future = future;
		this.protocolbuffer = protocolbuffer;
	}

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		return future.cancel(mayInterruptIfRunning);
	}

	@Override
	public P get() throws InterruptedException, ExecutionException {
		protocolbuffer.mergeFrom(future.get());
		return protocolbuffer;
	}

	@Override
	public P get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		protocolbuffer.mergeFrom(future.get(timeout, unit));
		return protocolbuffer;
	}

	@Override
	public boolean isCancelled() {
		return future.isCancelled();
	}

	@Override
	public boolean isDone() {
		return future.isDone();
	}
}

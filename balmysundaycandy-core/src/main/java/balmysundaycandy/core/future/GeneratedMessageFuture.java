package balmysundaycandy.core.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.google.appengine.repackaged.com.google.protobuf.GeneratedMessage;
import com.google.appengine.repackaged.com.google.protobuf.InvalidProtocolBufferException;

/**
 * wrap "makeAsyncCall" Future.
 * 
 * @author marblejenka
 * 
 * @param <P>
 *            protocol buffer object
 */
public class GeneratedMessageFuture<P,B extends GeneratedMessage.Builder<?>> implements Future<P> {

	private Future<byte[]> future;
	private B builder;

	public GeneratedMessageFuture(Future<byte[]> future, B builder) {
		this.future = future;
		this.builder = builder;
	}

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		return future.cancel(mayInterruptIfRunning);
	}

	@SuppressWarnings("unchecked")
	@Override
	public P get() throws InterruptedException, ExecutionException {
		try {
			builder.mergeFrom(future.get());
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
		return (P) builder.build();
	}

	@SuppressWarnings("unchecked")
	@Override
	public P get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		try {
			builder.mergeFrom(future.get(timeout, unit));
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
		return (P) builder.build();
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

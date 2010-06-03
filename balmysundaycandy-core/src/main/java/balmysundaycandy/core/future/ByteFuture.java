package balmysundaycandy.core.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * simple warpper to use byte[] as Future<byte[]>
 * 
 * @author marblejenka
 *
 */
public class ByteFuture implements Future<byte[]>{

	byte[] value = null;
	
	public ByteFuture(byte[] value){
		this.value = value;
	}

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		return false;
	}

	@Override
	public byte[] get() throws InterruptedException, ExecutionException {
		return this.value;
	}

	@Override
	public byte[] get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		return this.value;
	}

	@Override
	public boolean isCancelled() {
		return false;
	}

	@Override
	public boolean isDone() {
		return false;
	}
}

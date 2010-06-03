package balmysundaycandy.extention.urlfetch;

import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import balmysundaycandy.core.future.GeneratedMessageFuture;

import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.HttpProtocolBufferTranslator;
import com.google.appengine.api.urlfetch.URLFetchServicePb.URLFetchResponse;
import com.google.appengine.repackaged.com.google.protobuf.GeneratedMessage.Builder;

/**
 * future represent httpresponse, apprication object.
 * 
 * @author marblejenka
 * 
 */
public class HttpResponseFuture implements Future<HTTPResponse> {
	private GeneratedMessageFuture<URLFetchResponse, Builder<?>> future;

	public HttpResponseFuture(GeneratedMessageFuture<URLFetchResponse, Builder<?>> future) {
		this.future = future;
	}

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		return future.cancel(mayInterruptIfRunning);
	}

	@Override
	public HTTPResponse get() throws InterruptedException, ExecutionException {
		try {
			return HttpProtocolBufferTranslator.translate(future.get());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public HTTPResponse get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		try {
			return HttpProtocolBufferTranslator.translate(future.get(timeout, unit));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
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

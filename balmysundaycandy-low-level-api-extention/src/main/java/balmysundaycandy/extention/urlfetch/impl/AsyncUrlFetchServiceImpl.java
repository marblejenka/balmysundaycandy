package balmysundaycandy.extention.urlfetch.impl;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.Future;

import balmysundaycandy.extention.urlfetch.AsyncUrlFetchService;
import balmysundaycandy.extention.urlfetch.HttpResponseFuture;
import balmysundaycandy.more.low.level.operations.urlfetch.UrlfetchOperations;

import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.HttpProtocolBufferTranslator;
import com.google.apphosting.api.ApiProxy.ApiConfig;

/**
 * urlfetch service work async.
 * 
 * @author marblejenka
 *
 */
public class AsyncUrlFetchServiceImpl implements AsyncUrlFetchService{
	private static ApiConfig apiConfig = new ApiConfig();
	static {
		// XXX ‚»‚Æ‚©‚çdeadline‚ðŽw’è‚Å‚«‚é‚æ‚¤‚É‚·‚é
		apiConfig.setDeadlineInSeconds(10D);
	}

	@Override
	public Future<HTTPResponse> fetch(URL url) throws IOException {
		return fetch(new HTTPRequest(url));
	}

	@Override
	public Future<HTTPResponse> fetch(HTTPRequest httpRequest) throws IOException {
		return new HttpResponseFuture(UrlfetchOperations.FETCH.callAsync(HttpProtocolBufferTranslator.translate(httpRequest), apiConfig));
	}
}

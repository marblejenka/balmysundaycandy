package balmysundaycandy.extention.urlfetch;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.Future;

import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;

public interface AsyncUrlFetchService {
	Future<HTTPResponse> fetch(URL url) throws IOException;

	Future<HTTPResponse> fetch(HTTPRequest httprequest) throws IOException;
}

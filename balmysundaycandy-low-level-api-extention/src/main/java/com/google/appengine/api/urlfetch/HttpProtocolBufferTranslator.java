package com.google.appengine.api.urlfetch;

import java.net.MalformedURLException;
import java.net.URL;

import com.google.appengine.api.urlfetch.URLFetchServicePb.URLFetchRequest;
import com.google.appengine.api.urlfetch.URLFetchServicePb.URLFetchResponse;
import com.google.appengine.api.urlfetch.URLFetchServicePb.URLFetchRequest.RequestMethod;
import com.google.appengine.api.urlfetch.URLFetchServicePb.URLFetchResponse.Header;

/**
 * translrate urlfetch service's input/output.
 * 
 * @author marblejenka
 * 
 */
public class HttpProtocolBufferTranslator {
	/**
	 * convert httprequest, an urlfetch service input, to protocol buffer.
	 * 
	 * @param httpRequest
	 *            application object
	 * @return protocol buffer object
	 */
	public static URLFetchRequest translate(HTTPRequest httpRequest) {
		// FIXME すべてのパラメーターを移し替えるようにする
		return URLFetchRequest.newBuilder().setUrl(httpRequest.getURL().getPath()).setMethod(RequestMethod.POST).build();
	}

	/**
	 * convert urlfetchrequest, rpc call input, to apprication object.
	 * 
	 * @param urlFetchRequest
	 *            protocol buffer object
	 * @return application object
	 * @throws MalformedURLException 
	 */
	public static HTTPRequest translate(URLFetchRequest urlFetchRequest) throws MalformedURLException {
		// FIXME すべてのパラメーターを移し替えるようにする
		return new HTTPRequest(new URL(urlFetchRequest.getUrl()));
	}

	/**
	 * convert httpresponse, an urlfetch service output, to protocol buffer.
	 * 
	 * @param httpResponse
	 *            application object
	 * @return protocol buffer object
	 */
	public static URLFetchResponse translate(HTTPResponse httpResponse) {
		// FIXME すべてのパラメーターを移し替えるようにする
		return null;
	}

	/**
	 * convert urlfetchresponse, rpc call output, to apprication object.
	 * 
	 * @param httpRequest
	 *            protocol buffer object
	 * @return application object
	 * @throws MalformedURLException 
	 */
	public static HTTPResponse translate(URLFetchResponse urlFetchResponse) throws MalformedURLException {
		HTTPResponse result = new HTTPResponse(urlFetchResponse.getStatusCode());
		result.setFinalUrl(new URL(urlFetchResponse.getFinalUrl()));
		for (Header header : urlFetchResponse.getHeaderList()) {
			result.addHeader(header.getKey(), header.getValue());
		}
		result.setContent(urlFetchResponse.getContent().toByteArray());
		return result;
	}
}

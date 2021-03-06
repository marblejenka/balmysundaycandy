package balmysundaycandy.scalatool.client;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.io.IOUtils;



/**
 * provide appengine rpc utililies.
 * 
 * @author marblejenka
 * 
 */
public class AppengineRemoteCallUtils {
	/**
	 * singletone
	 */
	private AppengineRemoteCallUtils() {
	}

	public static final HttpClient httpClient = new HttpClient();

	public static byte[] remoteCallBy(final URI uri, final byte[] request) {
		PostMethod method = new PostMethod();
		try {
			method.setURI(uri);
		} catch (URIException e) {
			throw new AppengineRemoteCallException(e);
		}

		method.setRequestEntity(new RequestEntity() {

			public void writeRequest(OutputStream outputstream) throws IOException {
				outputstream.write(request);
			}

			public boolean isRepeatable() {
				return false;
			}

			public String getContentType() {
				return "application/octet-stream";
			}

			public long getContentLength() {
				return request.length;
			}
		});
		try {
			httpClient.executeMethod(method);
			int statusCode = method.getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				throw new AppengineRemoteCallException(HttpStatus.getStatusText(statusCode));
			}
			return IOUtils.toByteArray(method.getResponseBodyAsStream());
		} catch (HttpException e) {
			throw new AppengineRemoteCallException(e);
		} catch (IOException e) {
			throw new AppengineRemoteCallException(e);
		} finally {
			method.releaseConnection();
		}
	}
}

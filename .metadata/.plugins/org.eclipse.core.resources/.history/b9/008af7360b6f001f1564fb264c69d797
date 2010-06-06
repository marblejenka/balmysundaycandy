package balmysundaycandy.scalatool.server;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import balmysundaycandy.scalatool.shared.RemoteCallRequest.CallRequest;
import balmysundaycandy.scalatool.shared.RemoteCallResponse.CallResponse;

import com.google.protobuf.ByteString;

/**
 * accept remote call.
 * 
 * @author marblejenka
 */
public class RemoteCallServlet extends HttpServlet {
	/**
	 * uid
	 */
	private static final long serialVersionUID = 6125284524750139854L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String exception = null;
		CallResponse callResponse = null;
		try {
			byte[] requestBytes = IOUtils.toByteArray(req.getInputStream());
			CallRequest callRequest = CallRequest.newBuilder().mergeFrom(requestBytes).build();

			if (CallRequest.RequestType.RUN_REMOTE_CALL.equals(callRequest.getRequestType())) {
				callResponse = RemoteCallExecutor.runRemoteCall(callRequest);
			} else if (CallRequest.RequestType.RUN_SERVICE_CALL.equals(callRequest.getRequestType())) {
				callResponse = RemoteCallExecutor.runServiceCall(callRequest);
			}

		} catch (Throwable t) {
			exception = t.getMessage();
			throw new IllegalStateException(t);
		} finally {
			if (callResponse == null) {
				callResponse = CallResponse.newBuilder().setResponseBytes(ByteString.copyFromUtf8("")).setRequestType(CallResponse.RequestType.RUN_REMOTE_CALL).setErrorLog("cannnnot execute request.").build();
			}
			if (exception != null) {
				callResponse = CallResponse.newBuilder(callResponse).setResponseBytes(ByteString.copyFromUtf8("")).setRequestType(CallResponse.RequestType.RUN_REMOTE_CALL).setErrorLog(exception).build();
			}
			resp.setContentType("application/octet-stream");
			resp.getOutputStream().write(callResponse.toByteArray());
			resp.getOutputStream().flush();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().println("<html><head><title>remote call servlet</title></head><body>");
		resp.getWriter().println("<p>hello, i am a remote call servlet.</p>");
		resp.getWriter().println("<p>" + new Date(System.currentTimeMillis()) + "</p>");
		resp.getWriter().println("</body></html>");
		resp.getWriter().flush();
	}
}

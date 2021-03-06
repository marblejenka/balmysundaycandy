package balmysundaycandy.marble.broker.model;

import java.util.Date;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

//@Model
public class LogEnrty {
	//@Attribute(primaryKey = true)
	private Key key;

	private String serviceName;

	private String methodName;

	private Text request;

	private Text response;

	private Text callTrace;

	private Date callStart;

	private Date callEnd;

	private long spendTimeMilles;

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Text getRequest() {
		return request;
	}

	public void setRequest(Text request) {
		this.request = request;
	}

	public Text getResponse() {
		return response;
	}

	public void setResponse(Text response) {
		this.response = response;
	}

	public Text getCallTrace() {
		return callTrace;
	}

	public void setCallTrace(Text callTrace) {
		this.callTrace = callTrace;
	}

	public Date getCallStart() {
		return callStart;
	}

	public void setCallStart(Date callStart) {
		this.callStart = callStart;
	}

	public Date getCallEnd() {
		return callEnd;
	}

	public void setCallEnd(Date callEnd) {
		this.callEnd = callEnd;
	}

	public long getSpendTimeMilles() {
		return spendTimeMilles;
	}

	public void setSpendTimeMilles(long spendTimeMilles) {
		this.spendTimeMilles = spendTimeMilles;
	}

	// method
	public String getRequestString() {
		return request.getValue();
	}

	public String getResponseString() {
		return response.getValue();
	}
	
	public String getCallTraceString(){
		return callTrace.getValue();
	}
}

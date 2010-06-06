package balmysundaycandy.core.delegate;

public class CalledOperation {
	private String serviceName;

	private String methodName;

	private byte[] requestAsByte;

	public CalledOperation(String serviceName, String methodName, byte[] requestAsByte) {
		this.serviceName = serviceName;
		this.methodName = methodName;
		this.requestAsByte = requestAsByte;
	}

	public String getServiceName() {
		return serviceName;
	}

	public String getMethodName() {
		return methodName;
	}

	public byte[] getRequestAsByte() {
		return requestAsByte;
	}

}

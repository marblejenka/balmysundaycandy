package balmysundaycandy.core.operations;

/**
 * key object to get protocol buffer object.
 * 
 * @author marblejenka
 * 
 */
public class ProtocolBufferKey {

	private String servicename;
	private String methodname;

	ProtocolBufferKey(String servicename, String methodname) {
		this.servicename = servicename;
		this.methodname = methodname;
	}

	public String getServicename() {
		return servicename;
	}

	public String getMethodname() {
		return methodname;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (!(obj instanceof ProtocolBufferKey)) {
			return false;
		}
		ProtocolBufferKey other = (ProtocolBufferKey) obj;
		return this.servicename.equals(other.servicename) && this.methodname.equals(other.methodname);
	}

	@Override
	public int hashCode() {
		return servicename.hashCode() * methodname.hashCode() * 27;
	}

}

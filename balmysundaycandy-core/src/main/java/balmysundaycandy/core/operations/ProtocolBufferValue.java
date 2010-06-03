package balmysundaycandy.core.operations;

import com.google.appengine.repackaged.com.google.io.protocol.ProtocolMessage;
import com.google.appengine.repackaged.com.google.protobuf.GeneratedMessage;

/**
 * protocol buffer value object.
 * 
 * @author marblejenka
 * 
 */
public class ProtocolBufferValue {

	private ProtocolMessage<?> protocolMessageRequest;
	private ProtocolMessage<?> protocolMessageResponse;

	private GeneratedMessage.Builder<?> generatedMessageRequest;
	private GeneratedMessage.Builder<?> generatedMessageResponse;

	ProtocolBufferValue(ProtocolMessage<?> request, ProtocolMessage<?> response) {
		this.protocolMessageRequest = request;
		this.protocolMessageResponse = response;
	}

	ProtocolBufferValue(GeneratedMessage.Builder<?> request, GeneratedMessage.Builder<?> response) {
		this.generatedMessageRequest = request;
		this.generatedMessageResponse = response;
	}

	public ProtocolMessage<?> cloneProtocolMessageRequest() {
		return protocolMessageRequest.clone();
	}

	public ProtocolMessage<?> cloneProtocolMessageResponse() {
		return protocolMessageResponse.clone();
	}

	public GeneratedMessage.Builder<?> cloneGeneratedMessageRequest() {
		return generatedMessageRequest.clone();
	}

	public GeneratedMessage.Builder<?> cloneGeneratedMessageResponse() {
		return generatedMessageResponse.clone();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (!(obj instanceof ProtocolBufferValue)) {
			return false;
		}
		ProtocolBufferValue other = (ProtocolBufferValue) obj;
		return this.protocolMessageRequest.equals(other.protocolMessageRequest) && this.protocolMessageResponse.equals(other.protocolMessageResponse);
	}

	@Override
	public int hashCode() {
		return protocolMessageRequest.hashCode() * protocolMessageResponse.hashCode() * 27;
	}

}

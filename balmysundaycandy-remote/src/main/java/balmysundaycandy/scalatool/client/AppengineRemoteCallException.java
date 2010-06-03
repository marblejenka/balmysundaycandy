package balmysundaycandy.scalatool.client;

public class AppengineRemoteCallException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * the constructor.
	 * 
	 * @param message
	 * @category constructor
	 */
	public AppengineRemoteCallException(String message) {
		super(message);
	}

	/**
	 * the constructor.
	 * 
	 * @param cause
	 * @category constructor
	 */
	public AppengineRemoteCallException(Throwable cause) {
		super(cause);
	}

}

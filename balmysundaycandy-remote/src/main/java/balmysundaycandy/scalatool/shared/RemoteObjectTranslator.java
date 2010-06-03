package balmysundaycandy.scalatool.shared;

import java.io.IOException;
import java.io.ObjectOutputStream;


/**
 * translator which remote call object to byte, and byte to remote call object.
 * 
 * @author marblejenka
 *
 */
public class RemoteObjectTranslator {
	public static byte[] toByte(Remote remote) {
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(null);
			objectOutputStream.writeObject(remote);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Remote toRemote(byte[] bytearray) {
		return null;
	}
}

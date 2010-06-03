package balmysundaycandy.scalatool.client;

import java.io.ObjectStreamClass;
import java.util.HashSet;
import java.util.Set;

public class RemoteObject {
	public byte[] object;
	public Set<ObjectStreamClass> classes = new HashSet<ObjectStreamClass>();
}

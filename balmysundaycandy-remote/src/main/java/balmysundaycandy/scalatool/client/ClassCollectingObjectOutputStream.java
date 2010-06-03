package balmysundaycandy.scalatool.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * サーバーでロードしたいクラスをのこしておく
 * 
 * 
 * @author marblejenka
 *
 */
public class ClassCollectingObjectOutputStream extends ObjectOutputStream{
	
	Set<ObjectStreamClass> classes = new HashSet<ObjectStreamClass>();
	
	public ClassCollectingObjectOutputStream(OutputStream outputStream) throws IOException, SecurityException {
		super(outputStream);
	}
	
	@Override
	protected void writeClassDescriptor(ObjectStreamClass objectStreamClass) throws IOException {
		super.writeClassDescriptor(objectStreamClass);
		classes.add(objectStreamClass);
	}
	
	public Set<ObjectStreamClass> getClasses() {
		return classes;
	}
}

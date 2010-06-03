package balmysundaycandy.scalatool.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

/**
 * 
 * TODO rename
 * 
 * 
 */
public class MyObjectInputStream extends ObjectInputStream {
	public MyObjectInputStream(InputStream in) throws IOException {
		super(in);
	}

	@Override
	protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
		// use contextclassloader
		return Class.forName(desc.getName(), false, Thread.currentThread().getContextClassLoader());
	}
}

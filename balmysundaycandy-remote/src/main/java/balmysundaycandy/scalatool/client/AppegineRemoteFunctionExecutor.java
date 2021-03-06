package balmysundaycandy.scalatool.client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.HashSet;
import java.util.Set;

import balmysundaycandy.scalatool.shared.RemoteCallRequest;
import balmysundaycandy.scalatool.shared.RemoteCallRequest.CallRequest;
import balmysundaycandy.scalatool.shared.RemoteCallRequest.CallRequest.RequestType;

import com.google.protobuf.ByteString;

public class AppegineRemoteFunctionExecutor {

	public static CallRequest generateCallRequest(Object function) {
		RemoteObject functionAsRemoteObject = object2remote(function);
		Set<ObjectStreamClass> classes = functionAsRemoteObject.classes;
		byte[] functionAsByte = functionAsRemoteObject.object;
		
		ByteString functionAsByteString = ByteString.copyFrom(functionAsByte);
		Set<ByteString> classesAsByteStringSet = new HashSet<ByteString>();
		for (ObjectStreamClass objectStreamClass : classes) {
			System.out.println(objectStreamClass.forClass());
			RemoteObject objectStreamClassAsRemoteObject = object2remote(objectStreamClass.forClass());
			classesAsByteStringSet.add(ByteString.copyFrom(objectStreamClassAsRemoteObject.object));
		}

		CallRequest callRequest = RemoteCallRequest.CallRequest.newBuilder().setRequestType(RequestType.RUN_REMOTE_CALL).setRemoteFunction(functionAsByteString).addAllRemoteFunctionDependsClasses(classesAsByteStringSet).build();

		return callRequest;
	}

	public static Object getFunction(CallRequest callRequest) {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(callRequest.getRemoteFunction().toByteArray());
		Object function = null;
		try {
			ObjectInputStream objectInputStream = new MyObjectInputStream(byteArrayInputStream);
			function = objectInputStream.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return function;
	}

	public static RemoteObject object2remote(Object object) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ClassCollectingObjectOutputStream classCollectingObjectOutputStream = null;
		try {
			classCollectingObjectOutputStream = new ClassCollectingObjectOutputStream(byteArrayOutputStream);
			classCollectingObjectOutputStream.writeObject(object);
			classCollectingObjectOutputStream.close();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		RemoteObject result = new RemoteObject();
		result.object = byteArrayOutputStream.toByteArray();
		result.classes.addAll(classCollectingObjectOutputStream.getClasses());
		return result;
	}
}

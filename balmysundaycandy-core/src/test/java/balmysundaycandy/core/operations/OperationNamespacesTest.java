package balmysundaycandy.core.operations;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Field;

import org.junit.Test;

import com.google.appengine.repackaged.com.google.protobuf.InvalidProtocolBufferException;
import com.google.appengine.repackaged.com.google.protobuf.TextFormat;
import com.google.appengine.tools.appstats.InternalProtos;
import com.google.apphosting.api.DatastorePb.GetRequest;
import com.google.storage.onestore.v3.OnestoreEntity.Path;
import com.google.storage.onestore.v3.OnestoreEntity.Reference;
import com.google.storage.onestore.v3.OnestoreEntity.Path.Element;

public class OperationNamespacesTest {

	@SuppressWarnings("unchecked")
	@Test
	public void desclibeAll() {
		int count = 0;

		Class[] classes = OperationNamespaces.class.getDeclaredClasses();
		for (int i = 0; i < classes.length; i++) {
			Class clazz = classes[i];
			Class[] innerclasses = clazz.getDeclaredClasses();
			for (int j = 0; j < innerclasses.length; j++) {
				Class innnerclass = innerclasses[j];

				Field[] fields = innnerclass.getFields();
				for (int k = 0; k < fields.length; k++) {
					Field field = fields[k];
					++count;

					System.out.println("result.add(summeryTo(" + clazz.getSimpleName() + ".packageName, " + clazz.getSimpleName() + ".methodName." + field.getName() + "));");
				}
			}
		}

		assertThat(count, is(54));
	}

	@Test
	public void toStringContents() {
		byte[] unknownProto = null;
		GetRequest request = new GetRequest();
		{
			long id = 1;
			String kind = "Foo";

			Element element = new Element();
			element.setId(id);
			element.setType(kind);

			Path path = new Path();
			path.addElement(element);

			Reference reference = new Reference();
			reference.setApp("test");
			reference.setPath(path);

			request.addKey(reference);
		}

		unknownProto = request.toByteArray();

		try {
			// appstats
			InternalProtos.EmptyProto proto = ((InternalProtos.EmptyProto.Builder) InternalProtos.EmptyProto.newBuilder().mergeFrom(unknownProto)).build();
			System.out.println(TextFormat.printToString(proto.getUnknownFields()));
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}

		// balmysundaycandy
		System.out.println(ProtocolBufferObjectHolder.requestAsString("datastore_v3", "Get", unknownProto));
	}
}

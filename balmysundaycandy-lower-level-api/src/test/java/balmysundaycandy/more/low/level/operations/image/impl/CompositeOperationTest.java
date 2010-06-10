package balmysundaycandy.more.low.level.operations.image.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.*;

import balmysundaycandy.more.low.level.operations.image.*;

import com.google.appengine.api.images.ImagesServicePb.*;

public class CompositeOperationTest {

	@Test
	@Ignore
	public void testCallImagesCompositeRequest() {
		fail("Not yet implemented");
		ImagesCompositeRequest request = new ImagesCompositeRequest();

		ImageData imageData = new ImageData();
		imageData.setBlobKey("key");

		request.addImage(imageData);
		request.getCanvas().setWidth(100);
		request.getCanvas().setHeight(100);
		request.getCanvas().setColor(1);
		request.getCanvas().getOutput().setMimeType(1);

		ImagesCompositeResponse response = ImgaeOperations.COMPOSIT.call(request);

		assertThat(response, is(not(nullValue())));
	}

	@Test
	@Ignore
	public void testCallAsyncImagesCompositeRequestApiConfig() {
		fail("Not yet implemented");
	}

}

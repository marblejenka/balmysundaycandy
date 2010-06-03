package balmysundaycandy.more.low.level.operations.image.impl;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import balmysundaycandy.core.test.EnvironmentConfiguration;
import balmysundaycandy.core.test.TestEnvironmentUtils;
import balmysundaycandy.more.low.level.operations.image.ImgaeOperations;

import com.google.appengine.api.images.ImagesServicePb.ImageData;
import com.google.appengine.api.images.ImagesServicePb.ImagesCompositeRequest;
import com.google.appengine.api.images.ImagesServicePb.ImagesCompositeResponse;

public class CompositeOperationTest {
	EnvironmentConfiguration environmentConfiguration = new EnvironmentConfiguration("", false, true);

	@Before
	public void setup() {
		TestEnvironmentUtils.setupEnvironment(environmentConfiguration);
	}

	@After
	public void teardown() {
		TestEnvironmentUtils.teardownEnvironment(environmentConfiguration);
	}

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

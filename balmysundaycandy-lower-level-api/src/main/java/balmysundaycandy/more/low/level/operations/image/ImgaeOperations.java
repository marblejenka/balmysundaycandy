package balmysundaycandy.more.low.level.operations.image;

import balmysundaycandy.core.anntations.UnderExploration;
import balmysundaycandy.more.low.level.operations.image.impl.CompositeOperation;
import balmysundaycandy.more.low.level.operations.image.impl.HistogramOperation;
import balmysundaycandy.more.low.level.operations.image.impl.TransformOperation;

/**
 * image operations.
 * 
 * @author marblejenka
 * 
 */
public class ImgaeOperations {
	@UnderExploration
	public static final TransformOperation TRANSFORM = new TransformOperation();

	@UnderExploration
	public static final CompositeOperation COMPOSIT = new CompositeOperation();

	@UnderExploration
	public static final HistogramOperation HISTGRAM = new HistogramOperation();
}

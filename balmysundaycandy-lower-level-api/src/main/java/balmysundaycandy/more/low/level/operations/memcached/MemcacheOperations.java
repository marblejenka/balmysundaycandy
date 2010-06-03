package balmysundaycandy.more.low.level.operations.memcached;

import balmysundaycandy.core.anntations.UnderExploration;
import balmysundaycandy.more.low.level.operations.memcached.impl.BatchIncrementOperation;
import balmysundaycandy.more.low.level.operations.memcached.impl.DeleteOperation;
import balmysundaycandy.more.low.level.operations.memcached.impl.FlushAllOperation;
import balmysundaycandy.more.low.level.operations.memcached.impl.GetOperation;
import balmysundaycandy.more.low.level.operations.memcached.impl.GrabTailOperation;
import balmysundaycandy.more.low.level.operations.memcached.impl.IncrementOperation;
import balmysundaycandy.more.low.level.operations.memcached.impl.SetOperation;
import balmysundaycandy.more.low.level.operations.memcached.impl.StatsOperation;

/**
 * memcache opertions.
 * 
 * @author marblejenka
 * 
 */
public class MemcacheOperations {
	@UnderExploration
	public static final GetOperation GET = new GetOperation();

	@UnderExploration
	public static final SetOperation SET = new SetOperation();

	@UnderExploration
	public static final DeleteOperation DELETE = new DeleteOperation();

	@UnderExploration
	public static final IncrementOperation INCREMENT = new IncrementOperation();

	@UnderExploration
	public static final FlushAllOperation FLUSH_ALL = new FlushAllOperation();

	@UnderExploration
	public static final StatsOperation STATS = new StatsOperation();

	@UnderExploration
	public static final BatchIncrementOperation BATCH_INVREMENT = new BatchIncrementOperation();

	@UnderExploration
	public static final GrabTailOperation GRAB_TAIL = new GrabTailOperation();
}

package balmysundaycandy.more.low.level.operations.taskqueue;

import balmysundaycandy.core.anntations.NotWorkOnAppengineEnvironment;
import balmysundaycandy.core.anntations.NotWorkOnLocalEnvironment;
import balmysundaycandy.core.anntations.WorkOnAppengineEnvironment;
import balmysundaycandy.core.anntations.WorkOnLocalEnvironment;
import balmysundaycandy.more.low.level.operations.taskqueue.impl.AddOperation;
import balmysundaycandy.more.low.level.operations.taskqueue.impl.DeleteOperation;
import balmysundaycandy.more.low.level.operations.taskqueue.impl.DeleteQueueOperation;
import balmysundaycandy.more.low.level.operations.taskqueue.impl.FetchQueueStatsOperation;
import balmysundaycandy.more.low.level.operations.taskqueue.impl.FetchQueuesOperation;
import balmysundaycandy.more.low.level.operations.taskqueue.impl.QueryTasksOperation;
import balmysundaycandy.more.low.level.operations.taskqueue.impl.UpdateOperation;


/**
 * taskqueue operations
 * 
 * @author marblejenka
 *
 */
public class TaskqueueOperations {
	@WorkOnAppengineEnvironment
	@WorkOnLocalEnvironment
	public static final AddOperation ADD = new AddOperation();
	
	@Deprecated
	@NotWorkOnAppengineEnvironment
	@NotWorkOnLocalEnvironment
	public static final DeleteOperation DELETE = new DeleteOperation();
	
	@Deprecated
	@NotWorkOnAppengineEnvironment
	@NotWorkOnLocalEnvironment
	public static final UpdateOperation UPDATE = new UpdateOperation();
	
	@Deprecated
	@NotWorkOnAppengineEnvironment
	@NotWorkOnLocalEnvironment
	public static final FetchQueuesOperation FETCH_QUEUES = new FetchQueuesOperation();
	
	@Deprecated
	@NotWorkOnAppengineEnvironment
	@NotWorkOnLocalEnvironment
	public static final FetchQueueStatsOperation FETCH_QUEUE_STATS = new FetchQueueStatsOperation();
	
	@Deprecated
	@NotWorkOnAppengineEnvironment
	@NotWorkOnLocalEnvironment
	public static final DeleteQueueOperation DELETE_QUEUE = new DeleteQueueOperation();
	
	@Deprecated
	@NotWorkOnAppengineEnvironment
	@NotWorkOnLocalEnvironment
	public static final QueryTasksOperation QUERY_TASKS = new QueryTasksOperation();
}

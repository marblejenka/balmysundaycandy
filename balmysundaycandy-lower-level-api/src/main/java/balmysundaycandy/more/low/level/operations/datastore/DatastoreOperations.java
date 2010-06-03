package balmysundaycandy.more.low.level.operations.datastore;

import balmysundaycandy.core.anntations.NotWorkOnAppengineEnvironment;
import balmysundaycandy.core.anntations.NotWorkOnLocalEnvironment;
import balmysundaycandy.core.anntations.UnderExploration;
import balmysundaycandy.core.anntations.WorkOnAppengineEnvironment;
import balmysundaycandy.core.anntations.WorkOnLocalEnvironment;
import balmysundaycandy.more.low.level.operations.datastore.impl.AddActionOperation;
import balmysundaycandy.more.low.level.operations.datastore.impl.AllocateIdsOperation;
import balmysundaycandy.more.low.level.operations.datastore.impl.BeginTransactionOperation;
import balmysundaycandy.more.low.level.operations.datastore.impl.CommitOperation;
import balmysundaycandy.more.low.level.operations.datastore.impl.CountOperation;
import balmysundaycandy.more.low.level.operations.datastore.impl.CreateIndexOperation;
import balmysundaycandy.more.low.level.operations.datastore.impl.DeleteCursorOperation;
import balmysundaycandy.more.low.level.operations.datastore.impl.DeleteIndexOperation;
import balmysundaycandy.more.low.level.operations.datastore.impl.DeleteOperation;
import balmysundaycandy.more.low.level.operations.datastore.impl.GetIndicesOperation;
import balmysundaycandy.more.low.level.operations.datastore.impl.GetOperation;
import balmysundaycandy.more.low.level.operations.datastore.impl.GetSchemaOperation;
import balmysundaycandy.more.low.level.operations.datastore.impl.NextOperation;
import balmysundaycandy.more.low.level.operations.datastore.impl.PutOperation;
import balmysundaycandy.more.low.level.operations.datastore.impl.RollbackOperation;
import balmysundaycandy.more.low.level.operations.datastore.impl.RunCompiledQueryOperation;
import balmysundaycandy.more.low.level.operations.datastore.impl.RunQueryOperation;
import balmysundaycandy.more.low.level.operations.datastore.impl.UpdateIndexOperation;

/**
 * datastore operations.
 * 
 * @author marblejenka
 * 
 */
public class DatastoreOperations {
	@WorkOnAppengineEnvironment
	@WorkOnLocalEnvironment
	public static final GetOperation GET = new GetOperation();

	@UnderExploration
	public static final PutOperation PUT = new PutOperation();

	@UnderExploration
	public static final DeleteOperation DELETE = new DeleteOperation();

	@WorkOnAppengineEnvironment
	@WorkOnLocalEnvironment
	public static final RunQueryOperation RUN_QUERY = new RunQueryOperation();

	@UnderExploration
	public static final RunCompiledQueryOperation RUN_COMPILES_QUERY = new RunCompiledQueryOperation();

	@UnderExploration
	public static final AddActionOperation ADD_ACTION = new AddActionOperation();

	@UnderExploration
	public static final NextOperation NEXT = new NextOperation();

	@WorkOnAppengineEnvironment
	@WorkOnLocalEnvironment
	public static final CountOperation COUNT = new CountOperation();

	@UnderExploration
	public static final DeleteCursorOperation DELETE_CURSOR = new DeleteCursorOperation();

	@UnderExploration
	public static final BeginTransactionOperation BEGIN_TRANSACTION = new BeginTransactionOperation();

	@UnderExploration
	public static final CommitOperation COMMIT = new CommitOperation();

	@UnderExploration
	public static final RollbackOperation ROLLBACK = new RollbackOperation();

	@NotWorkOnAppengineEnvironment
	@WorkOnLocalEnvironment
	public static final GetSchemaOperation GET_SCHEMA = new GetSchemaOperation();

	@UnderExploration
	public static final AllocateIdsOperation ALLOCATE_IDS = new AllocateIdsOperation();

	@Deprecated
	@NotWorkOnAppengineEnvironment
	@NotWorkOnLocalEnvironment
	public static final CreateIndexOperation CREATE_INDEX = new CreateIndexOperation();

	@Deprecated
	@NotWorkOnAppengineEnvironment
	@NotWorkOnLocalEnvironment
	public static final UpdateIndexOperation UPDATE_INDEX = new UpdateIndexOperation();

	@WorkOnAppengineEnvironment
	@WorkOnLocalEnvironment
	public static final GetIndicesOperation GET_INDICES = new GetIndicesOperation();

	@Deprecated
	@NotWorkOnAppengineEnvironment
	@NotWorkOnLocalEnvironment
	public static final DeleteIndexOperation DELETE_INDEX = new DeleteIndexOperation();
}

package balmysundaycandy.extention.datastore.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import balmysundaycandy.extention.datastore.AsyncDatastoreService;
import balmysundaycandy.extention.datastore.AsyncTransaction;
import balmysundaycandy.extention.datastore.DeleteResponseFuture;
import balmysundaycandy.extention.datastore.EnittyMapFuture;
import balmysundaycandy.extention.datastore.EntityFuture;
import balmysundaycandy.extention.datastore.KeyFuture;
import balmysundaycandy.extention.datastore.KeyListFuture;
import balmysundaycandy.extention.datastore.KeyRangeFuture;
import balmysundaycandy.more.low.level.operations.datastore.DatastoreOperations;

import com.google.appengine.api.datastore.DeleteRequestTranslator;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.GetRequestTransralator;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyRange;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.PutRequestTranslator;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.ReferenceTranslator;
import com.google.appengine.api.datastore.Transaction;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiBasePb.VoidProto;
import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.apphosting.api.DatastorePb.AllocateIdsRequest;
import com.google.apphosting.api.DatastorePb.BeginTransactionRequest;

/**
 * "async" datastore service
 * 
 * @author marblejenka
 * 
 */
public class AsyncDatastoreServiceImpl implements AsyncDatastoreService {

	/**
	 * unrealized operations.
	 * @see realizeAll
	 */
	private static final Stack<Future<?>> futures = new Stack<Future<?>>();

	/**
	 * uncommited operations.
	 * @see realizeAll
	 */
	private static final Stack<AsyncTransaction> transactions = new Stack<AsyncTransaction>();
	

	public static final ApiConfig apiConfig = new ApiConfig();
	static {
		apiConfig.setDeadlineInSeconds(Double.MAX_VALUE);
	}

	@Override
	public Future<Entity> get(Key key) throws EntityNotFoundException {
		AsyncTransaction transaction = beginTransaction();
		return get(transaction, key);
	}

	@Override
	public Future<Entity> get(Transaction transaction, Key key) throws EntityNotFoundException {
		Future<Entity> result = new EntityFuture(DatastoreOperations.GET.callAsync(GetRequestTransralator.request2pb(transaction, key), apiConfig));
		enmanageFuture(result);
		return result;
	}

	@Override
	public Future<Map<Key, Entity>> get(Iterable<Key> keys) {
		AsyncTransaction transaction = beginTransaction();
		return get(transaction, keys);
	}

	@Override
	public Future<Map<Key, Entity>> get(Transaction transaction, Iterable<Key> keys) {
		Future<Map<Key, Entity>> result = new EnittyMapFuture(DatastoreOperations.GET.callAsync(GetRequestTransralator.request2pb(transaction, keys), apiConfig));
		enmanageFuture(result);
		return result;
	}

	@Override
	public Future<Key> put(Entity entity) {
		AsyncTransaction transaction = beginTransaction();
		return put(transaction, entity);
	}

	@Override
	public Future<Key> put(Transaction transaction, Entity entity) {
		Future<Key> result = new KeyFuture(DatastoreOperations.PUT.callAsync(PutRequestTranslator.request2bp(transaction, entity), apiConfig));
		enmanageFuture(result);
		return result;
	}

	@Override
	public Future<List<Key>> put(Iterable<Entity> entities) {
		AsyncTransaction transaction = beginTransaction();
		return put(transaction, entities);
	}

	@Override
	public Future<List<Key>> put(Transaction transaction, Iterable<Entity> entities) {
		Future<List<Key>> result = new KeyListFuture(DatastoreOperations.PUT.callAsync(PutRequestTranslator.request2bp(transaction, entities), apiConfig));
		enmanageFuture(result);
		return result;
	}

	@Override
	public Future<VoidProto> delete(Key... keys) {
		AsyncTransaction transaction = beginTransaction();
		return delete(transaction, keys);
	}

	@Override
	public Future<VoidProto> delete(Transaction transaction, Key... keys) {
		Future<VoidProto> result = new DeleteResponseFuture(DatastoreOperations.DELETE.callAsync(DeleteRequestTranslator.keys2request(transaction, keys), apiConfig));
		enmanageFuture(result);
		return result;
	}

	@Override
	public Future<VoidProto> delete(Iterable<Key> keys) {
		AsyncTransaction transaction = beginTransaction();
		return delete(transaction, keys);
	}

	@Override
	public Future<VoidProto> delete(Transaction transaction, Iterable<Key> keys) {
		Future<VoidProto> result = new DeleteResponseFuture(DatastoreOperations.DELETE.callAsync(DeleteRequestTranslator.keys2request(transaction, keys), apiConfig));
		enmanageFuture(result);
		return result;
	}

	@Override
	public Future<PreparedQuery> prepare(Query query) {
		// TODO 実装方針を決める
		throw new UnsupportedOperationException();
	}

	@Override
	public Future<PreparedQuery> prepare(Transaction txn, Query query) {
		// TODO 実装方針を決める
		throw new UnsupportedOperationException();
	}

	@Override
	public AsyncTransaction beginTransaction() {
		BeginTransactionRequest request = new BeginTransactionRequest();
		request.setApp(ApiProxy.getCurrentEnvironment().getAppId());
		AsyncTransaction result = new AsyncTransaction(DatastoreOperations.BEGIN_TRANSACTION.callAsync(request, apiConfig));
		enmanageAsyncTransaction(result);
		return result;
	}

	@Override
	public AsyncTransaction getCurrentTransaction() {
		if (transactions.size() == 0) {
			return beginTransaction();
		}
		return transactions.pop();
	}

	@Override
	public AsyncTransaction getCurrentTransaction(AsyncTransaction returnedIfNoTxn) {
		if (transactions.size() == 0) {
			return returnedIfNoTxn;
		}
		return transactions.pop();
	}

	@Override
	public Collection<AsyncTransaction> getActiveTransactions() {
		// TODO should check active or not?
		return transactions;
	}

	@Override
	public Future<KeyRange> allocateIds(String kind, long num) {
		AllocateIdsRequest request = new AllocateIdsRequest();
		request.setSize(num);
		request.setModelKey(ReferenceTranslator.kind2reference(kind));
		KeyRangeFuture result = new KeyRangeFuture(null, kind, DatastoreOperations.ALLOCATE_IDS.callAsync(request, apiConfig));
		enmanageFuture(result);
		return result;
	}

	@Override
	public Future<KeyRange> allocateIds(Key parent, String kind, long num) {
		AllocateIdsRequest request = new AllocateIdsRequest();
		request.setSize(num);
		request.setModelKey(ReferenceTranslator.path2reference(parent, kind));
		KeyRangeFuture result = new KeyRangeFuture(parent, kind, DatastoreOperations.ALLOCATE_IDS.callAsync(request, apiConfig));
		enmanageFuture(result);
		return result;
	}
	
	/**
	 * ensure execute all operations.
	 */
	public void realizeAll() throws InterruptedException, ExecutionException{
		ensureFutures();
		commitAllTransactions();
	}
	
	public void ensureFutures() throws InterruptedException, ExecutionException {
		for (AsyncTransaction transaction : transactions) {
			transaction.get();
		}
		for (Future<?> future : futures) {
			future.get();
		}
	}
	
	public void commitAllTransactions() {
		for (AsyncTransaction transaction : transactions) {
			transaction.commit();
		}
	}
	
	/**
	 * a policy that manage transaction.
	 * 
	 * @param asyncTransaction emmanage object.
	 */
	 private void enmanageAsyncTransaction(AsyncTransaction asyncTransaction) {
		 transactions.add(asyncTransaction);
	 }
	 
	/**
	 * a policy that manage future.
	 * 
	 * @param future emmanage obuject.
	 */
	 private void enmanageFuture(Future<?> future) {
		 futures.add(future);
	 }
}

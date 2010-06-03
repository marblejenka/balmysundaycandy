package balmysundaycandy.extention.datastore.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import balmysundaycandy.extention.datastore.AsyncDatastoreService;
import balmysundaycandy.extention.datastore.EntityFuture;
import balmysundaycandy.extention.datastore.KeyFuture;
import balmysundaycandy.more.low.level.operations.datastore.DatastoreOperations;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyRange;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiBasePb.VoidProto;
import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.apphosting.api.DatastorePb.GetRequest;
import com.google.apphosting.api.DatastorePb.PutRequest;
import com.google.storage.onestore.v3.OnestoreEntity.Path;
import com.google.storage.onestore.v3.OnestoreEntity.Reference;
import com.google.storage.onestore.v3.OnestoreEntity.Path.Element;

/**
 * "async" datastore service
 * 
 * @author marblejenka
 * 
 */
public class AsyncDatastoreServiceImpl implements AsyncDatastoreService {

	/**
	 * low level api datastore service for use transaction stack.
	 */
	private static final DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
	
	private static final ApiConfig apiConfig = new ApiConfig();
	static {
		apiConfig.setDeadlineInSeconds(Double.MAX_VALUE);
	}
	
	
	@Override
	public Future<Entity> get(Key key) throws EntityNotFoundException {
		Transaction transaction = datastoreService.getCurrentTransaction();
		return get(transaction, key);
	}

	@Override
	public Future<Entity> get(Transaction txn, Key key) throws EntityNotFoundException {
		GetRequest request = new GetRequest();
		{
			Element element = new Element();
			element.setId(key.getId());
			element.setType(key.getKind());

			Path path = new Path();
			path.addElement(element);

			Reference reference = new Reference();
			reference.setApp(ApiProxy.getCurrentEnvironment().getAppId());
			reference.setPath(path);

			request.addKey(reference);

			com.google.apphosting.api.DatastorePb.Transaction transaction = new com.google.apphosting.api.DatastorePb.Transaction();
			transaction.setHandle(Long.parseLong(txn.getId()));

			request.setTransaction(transaction);
		}

		return new EntityFuture(DatastoreOperations.GET.callAsync(request, apiConfig));
	}

	@Override
	public Future<Key> put(Entity entity) {
		PutRequest request = new PutRequest();
		return new KeyFuture(DatastoreOperations.PUT.callAsync(request, apiConfig));
	}
	
	// -------unimplemented
	
	
	
	@Override
	public Future<Map<Key, Entity>> get(Iterable<Key> keys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<Map<Key, Entity>> get(Transaction txn, Iterable<Key> keys) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Future<KeyRange> allocateIds(String kind, long num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<KeyRange> allocateIds(Key parent, String kind, long num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<Transaction> beginTransaction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<VoidProto> delete(Key... keys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<VoidProto> delete(Transaction transaction, Key... keys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<VoidProto> delete(Iterable<Key> keys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<VoidProto> delete(Transaction txn, Iterable<Key> keys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<Collection<Transaction>> getActiveTransactions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<Transaction> getCurrentTransaction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<Transaction> getCurrentTransaction(Transaction returnedIfNoTxn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<PreparedQuery> prepare(Query query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<PreparedQuery> prepare(Transaction txn, Query query) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Future<Key> put(Transaction transaction, Entity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<List<Key>> put(Iterable<Entity> iterable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<List<Key>> put(Transaction transaction, Iterable<Entity> iterable) {
		// TODO Auto-generated method stub
		return null;
	}

}

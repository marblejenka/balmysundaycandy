package balmysundaycandy.extention.datastore;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyRange;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.google.apphosting.api.ApiBasePb.VoidProto;

/**
 * async datastore service.
 * 
 * @author marblejenka
 *
 */
public interface AsyncDatastoreService {
	Future<Entity> get(Key key) throws EntityNotFoundException;

	Future<Entity> get(Transaction txn, Key key) throws EntityNotFoundException;

	Future<Map<Key, Entity>> get(Iterable<Key> keys);

	Future<Map<Key, Entity>> get(Transaction txn, Iterable<Key> keys);

	Future<Key> put(Entity entity);

	Future<Key> put(Transaction transaction, Entity entity);

	Future<List<Key>> put(Iterable<Entity> iterable);

	Future<List<Key>> put(Transaction transaction, Iterable<Entity> iterable);

	Future<VoidProto> delete(Key... keys);

	Future<VoidProto> delete(Transaction transaction, Key... keys);

	Future<VoidProto> delete(Iterable<Key> keys);

	Future<VoidProto> delete(Transaction txn, Iterable<Key> keys);

	Future<PreparedQuery> prepare(Query query);

	Future<PreparedQuery> prepare(Transaction txn, Query query);

	Future<Transaction> beginTransaction();

	Future<Transaction> getCurrentTransaction();

	Future<Transaction> getCurrentTransaction(Transaction returnedIfNoTxn);

	Future<Collection<Transaction>> getActiveTransactions();

	Future<KeyRange> allocateIds(String kind, long num);

	Future<KeyRange> allocateIds(Key parent, String kind, long num);
}

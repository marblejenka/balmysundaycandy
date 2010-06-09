package balmysundaycandy.extention.datastore;

import java.util.ConcurrentModificationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import balmysundaycandy.extention.datastore.impl.AsyncDatastoreServiceImpl;
import balmysundaycandy.more.low.level.operations.datastore.DatastoreOperations;

import com.google.appengine.api.datastore.Transaction;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.DatastorePb;
import com.google.apphosting.api.ApiBasePb.VoidProto;
import com.google.apphosting.api.ApiProxy.ApiConfig;
import com.google.apphosting.api.DatastorePb.CommitResponse;

/**
 * future of transaction.
 * 
 * TODO 状態遷移を整理する
 * 
 * @author marblejenka
 * 
 */
public class AsyncTransaction implements Transaction, Future<Transaction> {

	private static ApiConfig apiConfig = AsyncDatastoreServiceImpl.apiConfig;

	private static final String app = ApiProxy.getCurrentEnvironment().getAppId();

	private Long handle = null;

	// transaction which invoked async
	Future<com.google.apphosting.api.DatastorePb.Transaction> protocolMessageFuture;

	// commit response if comited
	Future<CommitResponse> commited;

	// rollback response if rolled back
	Future<VoidProto> rollbacked;

	public AsyncTransaction(Future<com.google.apphosting.api.DatastorePb.Transaction> transaction) {
		this.protocolMessageFuture = transaction;
	}

	// Transaction
	@Override
	public String getApp() {
		return app;
	}

	@Override
	public String getId() {
		try {
			get();
			return Long.toString(this.handle);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean isActive() {
		// TODO 実装
		return false;
	}

	@Override
	public void commit() {
		if (operated()) {
			throw new ConcurrentModificationException("you cannnot invoke duprecate operation.");
		}

		Future<CommitResponse> result = DatastoreOperations.COMMIT.callAsync(createTransaction(), apiConfig);
		this.commited = result;
	}

	@Override
	public void rollback() {
		if (operated()) {
			throw new ConcurrentModificationException("you cannnot invoke duprecate operation.");
		}

		Future<VoidProto> result = DatastoreOperations.ROLLBACK.callAsync(createTransaction(), apiConfig);
		this.rollbacked = result;
	}

	public boolean commited() {
		return this.commited != null;
	}

	public boolean rollbacked() {
		return this.rollbacked != null;
	}

	private boolean operated() {
		return commited() || rollbacked();
	}

	private DatastorePb.Transaction createTransaction() {
		if (this.handle == null) {
			try {
				get();
			} catch (InterruptedException e) {
				throw new ConcurrentModificationException("you cannnot invoke duprecate operation.");
			} catch (ExecutionException e) {
				throw new ConcurrentModificationException("you cannnot invoke duprecate operation.");
			}
		}

		DatastorePb.Transaction txn = new DatastorePb.Transaction();
		txn.setApp(app);
		txn.setHandle(handle);
		return txn;
	}

	// Furure<Transaction>
	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		return protocolMessageFuture.cancel(mayInterruptIfRunning);
	}

	@Override
	public Transaction get() throws InterruptedException, ExecutionException {
		com.google.apphosting.api.DatastorePb.Transaction transaction = protocolMessageFuture.get();
		this.handle = transaction.getHandle();
		return this;
	}

	@Override
	public Transaction get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		com.google.apphosting.api.DatastorePb.Transaction transaction = protocolMessageFuture.get(timeout, unit);
		this.handle = transaction.getHandle();
		return this;
	}

	@Override
	public boolean isCancelled() {
		return protocolMessageFuture.isCancelled();
	}

	@Override
	public boolean isDone() {
		return protocolMessageFuture.isDone();
	}
}

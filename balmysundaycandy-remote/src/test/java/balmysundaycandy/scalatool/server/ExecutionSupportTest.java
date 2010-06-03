package balmysundaycandy.scalatool.server;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.google.apphosting.api.DatastorePb;

public class ExecutionSupportTest {

	@Test
	public void testIsCommitOperation() {
		assertThat(ExecutionSupport.isCommitOperation("datastore_v3", "Commit"), is(true));
		assertThat(ExecutionSupport.isCommitOperation("datastore_v4", "Commit"), is(false));
		assertThat(ExecutionSupport.isCommitOperation("datastore_v3", "Put"), is(false));
	}

	@Test
	public void testIsRollbackOperation() {
		assertThat(ExecutionSupport.isRollbackOperation("datastore_v3", "Rollback"), is(true));
		assertThat(ExecutionSupport.isRollbackOperation("datastore_v4", "Rollback"), is(false));
		assertThat(ExecutionSupport.isRollbackOperation("datastore_v3", "Put"), is(false));
	}
	
	@Test
	public void testRebuildTransaction() {
		DatastorePb.Transaction tx = new DatastorePb.Transaction();
		tx.setApp("app");
		tx.setHandle(0x30af24f4b70a0d10L);
		System.out.println(tx);
		
		DatastorePb.Transaction actual = new DatastorePb.Transaction();
		actual.mergeFrom(ExecutionSupport.rebuildTransaction(tx.toByteArray()));
		
		assertThat(actual.getHandle(), is(0x30af24f4b70a0d10L));
		System.out.println(actual);
	}

	@Test
	public void testRebuildTransactionLong() {
		DatastorePb.Transaction tx = new DatastorePb.Transaction();
		tx.setApp("app");
		tx.setHandle(3508063268206415120L);
		System.out.println(tx);
		
		DatastorePb.Transaction actual = new DatastorePb.Transaction();
		actual.mergeFrom(ExecutionSupport.rebuildTransaction(tx.toByteArray()));
		
		assertThat(actual.getHandle(), is(0x30af24f4b70a0d10L));
		System.out.println(actual);
	}
}

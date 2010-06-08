package com.google.appengine.api.datastore;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityTranslator;
import com.google.appengine.api.datastore.Transaction;
import com.google.apphosting.api.DatastorePb.PutRequest;
import com.google.storage.onestore.v3.OnestoreEntity.EntityProto;

// rename
public class PutRequestTranslator {

	public static PutRequest request2bp(Transaction txn, Entity... entities) {
		PutRequest request = new PutRequest();

		for (Entity entity : entities) {
			EntityProto proto = entity2entityproto(entity);
			request.addEntity(proto);
		}
		if (txn != null) {
			com.google.apphosting.api.DatastorePb.Transaction transaction = new com.google.apphosting.api.DatastorePb.Transaction();
			transaction.setHandle(Long.parseLong(txn.getId()));
			request.setTransaction(transaction);
		}

		return request;
	}

	public static EntityProto entity2entityproto(Entity entity) {
		return EntityTranslator.convertToPb(entity);
	}

	public static PutRequest request2bp(Transaction transaction, Iterable<Entity> entities) {
		// TODO Auto-generated method stub
		return null;
	}
}

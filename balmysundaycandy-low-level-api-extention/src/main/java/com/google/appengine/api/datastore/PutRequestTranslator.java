package com.google.appengine.api.datastore;

import java.util.*;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityTranslator;
import com.google.appengine.api.datastore.Transaction;
import com.google.apphosting.api.*;
import com.google.apphosting.api.DatastorePb.PutRequest;
import com.google.storage.onestore.v3.OnestoreEntity.EntityProto;

// rename
public class PutRequestTranslator {

	public static PutRequest request2bp(Transaction transaction, Entity... entities) {
		return request2bp(transaction, Arrays.asList(entities));

	}

	public static EntityProto entity2entityproto(Entity entity) {
		return EntityTranslator.convertToPb(entity);
	}

	public static PutRequest request2bp(Transaction transaction, Iterable<Entity> entities) {
		PutRequest request = new PutRequest();

		for (Entity entity : entities) {
			EntityProto proto = entity2entityproto(entity);
			request.addEntity(proto);
		}
		if (transaction != null) {
			DatastorePb.Transaction remote = new DatastorePb.Transaction();
			remote.setHandle(Long.parseLong(transaction.getId()));
			request.setTransaction(remote);
		}
		return request;
	}
}

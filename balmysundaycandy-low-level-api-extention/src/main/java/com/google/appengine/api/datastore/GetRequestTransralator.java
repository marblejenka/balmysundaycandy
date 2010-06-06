package com.google.appengine.api.datastore;

import com.google.apphosting.api.DatastorePb.GetRequest;
import com.google.storage.onestore.v3.OnestoreEntity.Reference;

// TOTO rename
public class GetRequestTransralator {
	
	public static GetRequest request2pb(Transaction txn, Key... keys) {
		GetRequest request = new GetRequest();

		for (Key key : keys) {
			request.addKey(key2reference(key));
		}
		if (txn != null) {
			com.google.apphosting.api.DatastorePb.Transaction transaction = new com.google.apphosting.api.DatastorePb.Transaction();
			transaction.setHandle(Long.parseLong(txn.getId()));
			request.setTransaction(transaction);
		}

		return request;
	}
		
	public static Reference key2reference(Key key) {
		return KeyTranslator.convertToPb(key);
	}
	
	public static Key reference2key(Reference reference){
		return KeyTranslator.createFromPb(reference);
	}
}

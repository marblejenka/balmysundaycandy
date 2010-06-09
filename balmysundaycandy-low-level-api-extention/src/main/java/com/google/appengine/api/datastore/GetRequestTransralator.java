package com.google.appengine.api.datastore;

import java.util.*;

import com.google.apphosting.api.*;
import com.google.apphosting.api.DatastorePb.*;

// TOTO rename
public class GetRequestTransralator {

	public static GetRequest request2pb(Transaction transaction, Key... keys) {
		return request2pb(transaction, Arrays.asList(keys));
	}

	public static GetRequest request2pb(Transaction transaction, Iterable<Key> keys) {
		GetRequest request = new GetRequest();

		for (Key key : keys) {
			request.addKey(ReferenceTranslator.key2reference(key));
		}
		if (transaction != null) {
			DatastorePb.Transaction remote = new DatastorePb.Transaction();
			remote.setHandle(Long.parseLong(transaction.getId()));
			request.setTransaction(remote);
		}

		return request;
	}
}

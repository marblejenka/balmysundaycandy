package com.google.appengine.api.datastore;

import java.util.*;

import com.google.apphosting.api.*;
import com.google.apphosting.api.DatastorePb.*;

public class DeleteRequestTranslator {

	public static DeleteRequest keys2request(Transaction transaction, Key... keys) {
		return keys2request(transaction, Arrays.asList(keys));
	}

	public static DeleteRequest keys2request(Key... keys) {
		return keys2request(null, Arrays.asList(keys));
	}

	public static DeleteRequest keys2request(Transaction transaction, Iterable<Key> keys) {
		DeleteRequest request = new DeleteRequest();
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

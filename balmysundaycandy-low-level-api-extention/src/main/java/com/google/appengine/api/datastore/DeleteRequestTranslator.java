package com.google.appengine.api.datastore;

import com.google.apphosting.api.DatastorePb.DeleteRequest;

public class DeleteRequestTranslator {

	public static DeleteRequest keys2request(Transaction transaction, Key... keys) {
		return keys2request(keys);
	}

	public static DeleteRequest keys2request(Key... keys) {
		DeleteRequest request = new DeleteRequest();
		for (Key key : keys) {
			request.addKey(ReferenceTranslator.key2reference(key));
		}
		return request;
	}

	public static DeleteRequest keys2request(Transaction transaction, Iterable<Key> keys) {
		// TODO Auto-generated method stub
		return null;
	}
}

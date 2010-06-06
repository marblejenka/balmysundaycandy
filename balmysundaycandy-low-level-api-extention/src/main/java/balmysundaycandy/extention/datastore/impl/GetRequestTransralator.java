package balmysundaycandy.extention.datastore.impl;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.DatastorePb.GetRequest;
import com.google.storage.onestore.v3.OnestoreEntity.Path;
import com.google.storage.onestore.v3.OnestoreEntity.Reference;
import com.google.storage.onestore.v3.OnestoreEntity.Path.Element;

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
		Element element = new Element();
		element.setId(key.getId());
		element.setType(key.getKind());

		Path path = new Path();
		path.addElement(element);

		Reference reference = new Reference();
		reference.setApp(ApiProxy.getCurrentEnvironment().getAppId());
		reference.setPath(path);
		
		return reference;
	}

}

package balmysundaycandy.extention.datastore;

import balmysundaycandy.extention.datastore.impl.AsyncDatastoreServiceImpl;

/**
 * A Factory class for AsyncDatastoreService.
 * 
 * @author marblejenka
 *
 */
public class AsyncDatastoreServiceFactory {
	private static final AsyncDatastoreService instance = new AsyncDatastoreServiceImpl();
	
	public static AsyncDatastoreService getDatastoreService(){
		return instance;
	}

}

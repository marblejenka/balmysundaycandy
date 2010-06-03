package balmysundaycandy.core.test;

import balmysundaycandy.core.environment.NocontainerEnvironment;

import com.google.appengine.api.datastore.dev.LocalDatastoreService;
import com.google.appengine.tools.development.ApiProxyLocal;
import com.google.appengine.tools.development.ApiProxyLocalFactory;
import com.google.appengine.tools.development.LocalServerEnvironment;
import com.google.apphosting.api.ApiProxy;

public class LocalEnvironmentTestSupport {

	static void setupNocontainerEnvironment(EnvironmentConfiguration environmentConfiguration) {
		NocontainerEnvironment environment = new NocontainerEnvironment();
		LocalServerEnvironment localServerEnvironment = new MyLocalServerEnvironment();

		ApiProxyLocal apiProxyLocal = new ApiProxyLocalFactory().create(localServerEnvironment);

		if (!environmentConfiguration.willUseStrageOnDatastoreTest()) {
			apiProxyLocal.setProperty(LocalDatastoreService.NO_STORAGE_PROPERTY, Boolean.TRUE.toString());
		}
		ApiProxy.setEnvironmentForCurrentThread(environment);
		ApiProxy.setDelegate(apiProxyLocal);
	}

	static void teardownNocontainerEnvironment(EnvironmentConfiguration environmentConfiguration) {
		if (environmentConfiguration.willCreanupDatastoreFile()) {
			LocalDatastoreService datastoreService = (LocalDatastoreService) ((ApiProxyLocal) ApiProxy.getDelegate()).getService(LocalDatastoreService.PACKAGE);
			datastoreService.clearProfiles();
		}
		ApiProxy.setDelegate(null);
		ApiProxy.setEnvironmentForCurrentThread(null);
	}
}

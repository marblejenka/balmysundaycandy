package balmysundaycandy.marble.broker;

import java.util.ArrayList;
import java.util.List;

import balmysundaycandy.core.operations.OperationNamespaces.blobstore;
import balmysundaycandy.core.operations.OperationNamespaces.datastore_v3;
import balmysundaycandy.core.operations.OperationNamespaces.images;
import balmysundaycandy.core.operations.OperationNamespaces.mail;
import balmysundaycandy.core.operations.OperationNamespaces.memcache;
import balmysundaycandy.core.operations.OperationNamespaces.taskqueue;
import balmysundaycandy.core.operations.OperationNamespaces.urlfetch;
import balmysundaycandy.core.operations.OperationNamespaces.user;
import balmysundaycandy.core.operations.OperationNamespaces.xmpp;
import balmysundaycandy.marble.broker.model.LogEnrty;
import balmysundaycandy.marble.broker.model.LogSummery;

public class LogEntryService {
//	private static LogEnrtyMeta logEnrtyMeta = new LogEnrtyMeta();

	public static List<LogEnrty> findAll() {
		return null;
//		return Datastore.query(logEnrtyMeta).asList();
	}
	
	public static List<LogSummery> summeryAll(){
		List<LogSummery> result = new ArrayList<LogSummery>();
		
		result.add(summeryTo(blobstore.packageName, blobstore.methodName.CreateUploadURL));
		result.add(summeryTo(blobstore.packageName, blobstore.methodName.DeleteBlob));
		result.add(summeryTo(blobstore.packageName, blobstore.methodName.DecodeBlobKey));
		result.add(summeryTo(datastore_v3.packageName, datastore_v3.methodName.AddAction));
		result.add(summeryTo(datastore_v3.packageName, datastore_v3.methodName.AddActions));
		result.add(summeryTo(datastore_v3.packageName, datastore_v3.methodName.AllocateIds));
		result.add(summeryTo(datastore_v3.packageName, datastore_v3.methodName.BeginTransaction));
		result.add(summeryTo(datastore_v3.packageName, datastore_v3.methodName.Commit));
		result.add(summeryTo(datastore_v3.packageName, datastore_v3.methodName.Count));
		result.add(summeryTo(datastore_v3.packageName, datastore_v3.methodName.CreateIndex));
		result.add(summeryTo(datastore_v3.packageName, datastore_v3.methodName.DeleteCursor));
		result.add(summeryTo(datastore_v3.packageName, datastore_v3.methodName.DeleteIndex));
		result.add(summeryTo(datastore_v3.packageName, datastore_v3.methodName.Delete));
		result.add(summeryTo(datastore_v3.packageName, datastore_v3.methodName.Explain));
		result.add(summeryTo(datastore_v3.packageName, datastore_v3.methodName.GetIndices));
		result.add(summeryTo(datastore_v3.packageName, datastore_v3.methodName.Get));
		result.add(summeryTo(datastore_v3.packageName, datastore_v3.methodName.GetSchema));
		result.add(summeryTo(datastore_v3.packageName, datastore_v3.methodName.Next));
		result.add(summeryTo(datastore_v3.packageName, datastore_v3.methodName.Put));
		result.add(summeryTo(datastore_v3.packageName, datastore_v3.methodName.Rollback));
		result.add(summeryTo(datastore_v3.packageName, datastore_v3.methodName.RunCompiledQuery));
		result.add(summeryTo(datastore_v3.packageName, datastore_v3.methodName.RunQuery));
		result.add(summeryTo(datastore_v3.packageName, datastore_v3.methodName.UpdateIndex));
		result.add(summeryTo(images.packageName, images.methodName.Composite));
		result.add(summeryTo(images.packageName, images.methodName.Histogram));
		result.add(summeryTo(images.packageName, images.methodName.Transform));
		result.add(summeryTo(mail.packageName, mail.methodName.Send));
		result.add(summeryTo(mail.packageName, mail.methodName.SendToAdmins));
		result.add(summeryTo(memcache.packageName, memcache.methodName.BatchIncrement));
		result.add(summeryTo(memcache.packageName, memcache.methodName.Delete));
		result.add(summeryTo(memcache.packageName, memcache.methodName.FlashAll));
		result.add(summeryTo(memcache.packageName, memcache.methodName.Get));
		result.add(summeryTo(memcache.packageName, memcache.methodName.GrabTail));
		result.add(summeryTo(memcache.packageName, memcache.methodName.Increment));
		result.add(summeryTo(memcache.packageName, memcache.methodName.Set));
		result.add(summeryTo(memcache.packageName, memcache.methodName.Stats));
		result.add(summeryTo(taskqueue.packageName, taskqueue.methodName.Add));
		result.add(summeryTo(taskqueue.packageName, taskqueue.methodName.Delete));
		result.add(summeryTo(taskqueue.packageName, taskqueue.methodName.DeleteQueue));
		result.add(summeryTo(taskqueue.packageName, taskqueue.methodName.FetchQueues));
		result.add(summeryTo(taskqueue.packageName, taskqueue.methodName.FetchQueueStats));
		result.add(summeryTo(taskqueue.packageName, taskqueue.methodName.PurgeQueue));
		result.add(summeryTo(taskqueue.packageName, taskqueue.methodName.QueryTasks));
		result.add(summeryTo(taskqueue.packageName, taskqueue.methodName.UpdateQueue));
		result.add(summeryTo(urlfetch.packageName, urlfetch.methodName.Fetch));
		result.add(summeryTo(user.packageName, user.methodName.CreateLoginURL));
		result.add(summeryTo(user.packageName, user.methodName.CreateLogoutURL));
//		result.add(summeryTo(user.packageName, user.methodName.GetOAuthUser));
//		result.add(summeryTo(user.packageName, user.methodName.CheckOAuthSignature));
		result.add(summeryTo(user.packageName, user.methodName.CreateFederatedLoginURL));
		result.add(summeryTo(user.packageName, user.methodName.CreateFederatedLogoutURL));
		result.add(summeryTo(xmpp.packageName, xmpp.methodName.GetPresence));
		result.add(summeryTo(xmpp.packageName, xmpp.methodName.SendInvite));
		result.add(summeryTo(xmpp.packageName, xmpp.methodName.SendMessage));
		
		return result;
	}

	public static LogSummery summeryTo(String serviceName, String methodName) {
		LogSummery logSummery = new LogSummery();

		logSummery.serviceName = serviceName;
		logSummery.methodName = methodName;

//		logSummery.count = Datastore.query(logEnrtyMeta).filter(logEnrtyMeta.serviceName.equal(serviceName), logEnrtyMeta.methodName.equal(methodName)).count();

		return logSummery;
	}
}

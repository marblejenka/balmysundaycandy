package balmysundaycandy.core.operations;

/**
 * operation namespaces. this class hold all of 54 appengine rpc call qualifier,
 * packagename and methodname.
 * 
 * this definition is based on appengine sdk 1.3.2
 * 
 * @author marblejenka
 * 
 */
public final class OperationNamespaces {

	/**
	 * blobstore 4 operation namespace.
	 * 
	 * @author marblejenka
	 * 
	 */
	public static final class blobstore {
		public static final String packageName = "blobstore";

		public static final class methodName {
			public static final String CreateUploadURL = "CreateUploadURL";
			public static final String DeleteBlob = "DeleteBlob";
			public static final String DecodeBlobKey = "DecodeBlobKey";
			public static final String FetchData = "FetchData";
		}
	}

	/**
	 * datastore_v3 20 operation namespace.
	 * 
	 * @author marblejenka
	 * 
	 */
	public static final class datastore_v3 {
		public static final String packageName = "datastore_v3";

		public static final class methodName {
			public static final String AddAction = "AddAction";
			public static final String AddActions = "AddActions";
			public static final String AllocateIds = "AllocateIds";
			public static final String BeginTransaction = "BeginTransaction";
			public static final String Commit = "Commit";
			public static final String Count = "Count";
			public static final String CreateIndex = "CreateIndex";
			public static final String DeleteCursor = "DeleteCursor";
			public static final String DeleteIndex = "DeleteIndex";
			public static final String Delete = "Delete";
			public static final String Explain = "Explain";
			public static final String GetIndices = "GetIndices";
			public static final String Get = "Get";
			public static final String GetSchema = "GetSchema";
			public static final String Next = "Next";
			public static final String Put = "Put";
			public static final String Rollback = "Rollback";
			public static final String RunCompiledQuery = "RunCompiledQuery";
			public static final String RunQuery = "RunQuery";
			public static final String UpdateIndex = "UpdateIndex";
		}
	}

	/**
	 * images 3 operation namespace.
	 * 
	 * @author marblejenka
	 * 
	 */
	public static final class images {
		public static final String packageName = "images";

		public static final class methodName {
			public static final String Composite = "Composite";
			public static final String Histogram = "Histogram";
			public static final String Transform = "Transform";
		}
	}

	/**
	 * mail 2 operation namespace.
	 * 
	 * @author marblejenka
	 * 
	 */
	public static final class mail {
		public static final String packageName = "mail";

		public static final class methodName {
			public static final String Send = "Send";
			public static final String SendToAdmins = "SendToAdmins";
		}
	}

	/**
	 * memcache 8 operation namespace.
	 * 
	 * @author marblejenka
	 * 
	 */
	public static final class memcache {
		public static final String packageName = "memcache";

		public static final class methodName {
			public static final String BatchIncrement = "BatchIncrement";
			public static final String Delete = "Delete";
			public static final String FlashAll = "FlashAll";
			public static final String Get = "Get";
			public static final String GrabTail = "GrabTail";
			public static final String Increment = "Increment";
			public static final String Set = "Set";
			public static final String Stats = "Stats";
		}
	}

	/**
	 * quota 0 operation namespace.
	 * 
	 * @author marblejenka
	 * 
	 */
	public static final class quota {
		public static final String packageName = "quota";

		public static final class methodName {
			// none, appengine sdk 1.3.2
		}
	}

	/**
	 * taskqueue 9 operation namespace.
	 * 
	 * @author marblejenka
	 * 
	 */
	public static final class taskqueue {
		public static final String packageName = "taskqueue";

		public static final class methodName {
			public static final String Add = "Add";
			public static final String BulkAdd = "BulkAdd";
			public static final String Delete = "Delete";
			public static final String DeleteQueue = "DeleteQueue";
			public static final String FetchQueues = "FetchQueues";
			public static final String FetchQueueStats = "FetchQueueStats";
			public static final String PurgeQueue = "PurgeQueue";
			public static final String QueryTasks = "QueryTasks";
			public static final String UpdateQueue = "UpdateQueue";
		}
	}

	/**
	 * urlfetch 1 operation namespace.
	 * 
	 * @author marblejenka
	 * 
	 */
	public static final class urlfetch {
		public static final String packageName = "urlfetch";

		public static final class methodName {
			public static final String Fetch = "Fetch";
		}
	}

	/**
	 * user 4 operation namespace.
	 * 
	 * @author marblejenka
	 * 
	 */
	public static final class user {
		public static final String packageName = "user";

		public static final class methodName {
			public static final String CreateLoginURL = "CreateLoginURL";
			public static final String CreateLogoutURL = "CreateLogoutURL";
//			public static final String GetOAuthUser = "GetOAuthUser";
//			public static final String CheckOAuthSignature = "CheckOAuthSignature";
			public static final String CreateFederatedLoginURL = "CreateFederatedLoginURL";
			public static final String CreateFederatedLogoutURL = "CreateFederatedLogoutURL";
		}
	}

	/**
	 * xmpp 3 operation namespace.
	 * 
	 * @author marblejenka
	 * 
	 */
	public static final class xmpp {
		public static final String packageName = "xmpp";

		public static final class methodName {
			public static final String GetPresence = "GetPresence";
			public static final String SendInvite = "SendInvite";
			public static final String SendMessage = "SendMessage";
		}
	}
}

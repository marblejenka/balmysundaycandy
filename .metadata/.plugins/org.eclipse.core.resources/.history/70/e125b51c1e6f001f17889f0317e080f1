// environment initial setup
import balmysundaycandy.scalatool.client.ScalaEnvironment
import com.google.apphosting.api.ApiProxy

object remote extends ScalaEnvironment() {
  override def getAppId() = "balmysundaycandy-scala"
  override def getVersionId() = "1"
  override def getEmail() = ""
  override def getUrl() = "http://balmysundaycandy-scala.appspot.com/sys/remotecall"
}

ApiProxy.setEnvironmentForCurrentThread(remote)

// api initial setup
// import com.google.appengine.api.blobstore._
import com.google.appengine.api.datastore._
import com.google.appengine.api.datastore.Query._
// import com.google.appengine.api.images._
// import com.google.appengine.api.mail._
import com.google.appengine.api.memcache._
import com.google.appengine.api.memcache.stdimpl._
// import com.google.appengine.api.quota._
// import com.google.appengine.api.urlfetch._
// import com.google.appengine.api.users._
import com.google.appengine.api.utils._
// import com.google.appengine.api.xmpp._
// val blobstore = BlobstoreServiceFactory.getBlobstoreService()
val datastore = DatastoreServiceFactory.getDatastoreService()
// val images = ImagesServiceFactory.getImagesService()
// val mail = MailServiceFactory.getMailService()
val memcache = MemcacheServiceFactory.getMemcacheService()
// val quota = QuotaServiceFactory.getQuotaService()
// val urlfetch = URLFetchServiceFactory.getURLFetchService()
// val user = UserServiceFactory.getUserService()
// val xmpp = XMPPServiceFactory.getXMPPService()

import org.slim3.datastore._
System.setProperty("com.google.appengine.runtime.environment", "Production")

import scala.collection.jcl.Conversions._ 

implicit def asEntity(obj : Object) : Entity = {
  obj.asInstanceOf[Entity]
}
implicit def asKey(obj : Object) : Key = {
  obj.asInstanceOf[Key]
}

// proxy setup
// import balmysundaycandy.scalatool.client.AppengineRemoteCallUtils
// val host = "hostname"
// val port = 0
// AppengineRemoteCallUtils.httpClient.getHostConfiguration().setProxy(host, port);


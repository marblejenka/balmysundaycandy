package balmysundaycandy.scalatool.client

import java.io.File
import java.io.FileWriter

/**
 * support for call function in appengine.
 * 
 * @author marblejenka
 *
 */
object ProfilePublisher {
  def input(value : String) : String  = {
    Console print value + ":"
    val result = Console readLine;
    Console println result
    return result 
  }
  
  def publish() = {
    val name = input("profile name")
    val appid = input("appid")
    val version = input("version")
    val email = input("email")
    val url = input("url")
    
    val result = """|// environment initial setup
                    |import balmysundaycandy.scalatool.client.ScalaEnvironment
                    |import com.google.apphosting.api.ApiProxy
                    |
                    |object @{name} extends ScalaEnvironment() {
                    |  override def getAppId() = @{appid}
                    |  override def getVersionId() = @{version}
                    |  override def getEmail() = @{email}
                    |  override def getUrl() = @{url}
                    |}
                    |
                    |ApiProxy.setEnvironmentForCurrentThread(@{name})
                    |
                    |// api initial setup
                    |// import com.google.appengine.api.blobstore._
                    |import com.google.appengine.api.datastore._
                    |import com.google.appengine.api.datastore.Query._
                    |// import com.google.appengine.api.images._
                    |// import com.google.appengine.api.mail._
                    |import com.google.appengine.api.memcache._
                    |import com.google.appengine.api.memcache.stdimpl._
                    |// import com.google.appengine.api.quota._
                    |// import com.google.appengine.api.urlfetch._
                    |// import com.google.appengine.api.users._
                    |import com.google.appengine.api.utils._
                    |// import com.google.appengine.api.xmpp._
                    |// val blobstore = BlobstoreServiceFactory.getBlobstoreService()
                    |val datastore = DatastoreServiceFactory.getDatastoreService()
                    |// val images = ImagesServiceFactory.getImagesService()
                    |// val mail = MailServiceFactory.getMailService()
                    |val memcache = MemcacheServiceFactory.getMemcacheService()
                    |// val quota = QuotaServiceFactory.getQuotaService()
                    |// val urlfetch = URLFetchServiceFactory.getURLFetchService()
                    |// val user = UserServiceFactory.getUserService()
                    |// val xmpp = XMPPServiceFactory.getXMPPService()
                    |
                    |import org.slim3.datastore._
                    |System.setProperty("com.google.appengine.runtime.environment", "Production")
                    |
                    |import scala.collection.jcl.Conversions._ 
                    |
                    |implicit def asEntity(obj : Object) : Entity = {
                    |  obj.asInstanceOf[Entity]
                    |}
                    |implicit def asKey(obj : Object) : Key = {
                    |  obj.asInstanceOf[Key]
                    |}
                    |"""
                    .stripMargin
                    .replace("@{name}", name)
                    .replace("@{appid}", "\"" + appid + "\"")
                    .replace("@{version}", "\"" + version + "\"")
                    .replace("@{email}", "\"" + email + "\"")
                    .replace("@{url}", "\"" + url + "\"")
    
    val file = new File(name + ".profile")
    val writer = new FileWriter(file)
    try {
      writer.write(result)
    } finally {
      writer.close
    }
  }
}


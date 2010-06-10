package balmysundaycandy.scalatool.client

import com.google.apphosting.api.ApiProxy

/**
 * support for call function in appengine.
 * 
 * @author marblejenka
 *
 */
object LocalEnviromentLoader {
  def setup() = { 
	  ApiProxy.setEnvironmentForCurrentThread(new ScalaEnvironment)
	  ApiProxy.setDelegate(new AppengineRemoteCallDelegate())
  }
}

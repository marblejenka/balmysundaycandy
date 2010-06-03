package balmysundaycandy.scalatool.client

import com.google.apphosting.api.ApiProxy;

import balmysundaycandy.scalatool.shared.Remote

/**
 * support for call function in appengine.
 * 
 * @author marblejenka
 *
 */
object RemoteCaller {

  def call(func : () => Unit) {
	ApiProxy.getDelegate().asInstanceOf[AppengineRemoteCallDelegate].runInDelegate(asRunnable(func))
  }
  
  def callRemote(func : () => Unit) {
    ApiProxy.getDelegate().asInstanceOf[AppengineRemoteCallDelegate].runInDelegate(asRemote(func))
  }
  
  implicit def asRunnable(func : () => Unit) : Runnable = {
    new Runnable() {
      def run() {
        func()
      }
    }
  }
  
  implicit def asRemote(func : () => Unit) : Remote = {
    new Remote() {
      def run() {
        func()
      }
    }
  }
}

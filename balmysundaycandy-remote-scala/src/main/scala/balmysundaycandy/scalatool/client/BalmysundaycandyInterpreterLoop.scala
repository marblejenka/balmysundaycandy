package balmysundaycandy.scalatool.client

import scala.tools.nsc._
import interpreter._
import java.io._
import scala.reflect._

/**
 * intepreter extention, this is able to access appengine through BalmysundaycandyRemoteServlet.
 * 
 * @author marblejenka
 *
 */
class BalmysundaycandyInterpreterLoop(out : PrintWriter) extends InterpreterLoop(None, out) {
  override val prompt = "balmysundaycandy-scala>"
  
  override def printHelp {
    printWelcome
    out.println("Type :load followed by a filename to load a Scala file, and which is able to load .profile.")
    out.println("Type :replay to reset execution and replay all previous commands.")
    out.println("Type :quit to exit the interpreter.")
    out.println
    out.println("Known problems:")
    out.println("* you cannnot use commit and rollback datastore operations in production environment.")
    out.println("* you cannnot use https. so that you should not use some production service.")
  }
  
  override def printWelcome() {
    out.println("Welcome to balmysundaycandy-scala beta, Scala " + Properties.versionString + " (" + System.getProperty("java.vm.name") + ", Java " + System.getProperty("java.version") + ")." )
    out.println("Type in expressions to have them evaluated.")
    out.println("Type :help for more information.")
    out.flush()
  }
  
   def bindSettings() {       
  //  super.bindSettings() TODO 2.8版対応
    interpreter beQuietDuring {
      interpreter.interpret("""
    	import balmysundaycandy.scalatool.client._
    	LocalEnviromentLoader.setup
        """)
    }
  }
}
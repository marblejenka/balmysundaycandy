package balmysundaycandy.scalatool.client

import java.io.File
import java.io.PrintWriter
import java.io.BufferedWriter
import java.io.OutputStreamWriter

import scala.tools.nsc.GenericRunnerSettings

object BalmysundaycandyInterpreterRunner {
  val classpathargument = "-classpath"
  val classpathdelimiter = File.pathSeparator
	
  def main(args : Array[String]) : Unit = {
	  run(args)
  }
  
  def run(args : Array[String]) {
    val out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)))
    val settings = new GenericRunnerSettings(out.println _)
    settings.classpath.value = settings.classpath.value + classpathdelimiter + findAdditionalClasspath(args)

    new BalmysundaycandyInterpreterLoop(out).main(settings)
    return
  }
  
  def findFromArgument(args : Array[String], finder : String) : String = {
	if (args.length == 0)
	    return ""

    for (i <- 0 to args.length - 1) {
	    if (finder.equals(args(i)) && i < args.length) {
        return args(i + 1)
      }
    }
    return ""
  }
  
  def findAdditionalClasspath(args : Array[String]) : String  = {
    findFromArgument(args, classpathargument)
  }
}


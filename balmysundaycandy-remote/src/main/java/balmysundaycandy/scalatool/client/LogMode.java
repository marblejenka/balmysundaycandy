package balmysundaycandy.scalatool.client;

import com.google.apphosting.api.ApiProxy;

/**
 * log mode.
 * 
 * <pre>
 * NONE: noting to provide.
 * TIMELOG: provide request time log. 
 * VERBOSE: provide request deteil logs.
 * </pre>
 * 
 * @author marblejenka
 * 
 */
public enum LogMode {
	NONE, TIMELOG, VERBOSE;

	public static void setLogMode(LogMode logMode) {
		environment().setLogMode(logMode);
	}
	
	public static void none() {
		NONE.setCurrent();
	}
	
	public static void timelog() {
		TIMELOG.setCurrent();
	}
	
	public static void verbose() {
		VERBOSE.setCurrent();
	}

	public void setCurrent() {
		environment().setLogMode(this);
	}
	
	public static LogMode current(){
		return (LogMode) ApiProxy.getCurrentEnvironment().getAttributes().get(ScalaEnvironment.logmode);
	}
	
	public boolean requresTimeLog(){
		return this.equals(TIMELOG) || this.equals(VERBOSE);
	}
	
	public boolean requresRemoteLog(){
		return this.equals(VERBOSE);
	}
	
	private static ScalaEnvironment environment() {
		return (ScalaEnvironment) ApiProxy.getCurrentEnvironment();
	}

}

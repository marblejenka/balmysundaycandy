package balmysundaycandy.scalatool.client;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ScalaEnvironment extends DefaultEnvironment {
	public static final String logmode = "balmysundaycandy.scalatool.logmode";

	private static Map<String, Object> attributes = new ConcurrentHashMap<String, Object>();
	static {
		attributes.put(logmode, LogMode.NONE);
	}

	@Override
	public String getAppId() {
		return "";
	}

	@Override
	public String getVersionId() {
		return "";
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public LogMode getLogMode() {
		return (LogMode) attributes.get(logmode);
	}

	public void setLogMode(LogMode logMode) {
		attributes.put(logmode, logMode);
	}
	
	public String getUrl(){
		return "";
	}

}

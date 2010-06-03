package balmysundaycandy.core.test;

import java.io.File;

import com.google.appengine.tools.development.LocalServerEnvironment;

public class MyLocalServerEnvironment implements LocalServerEnvironment {
	
	@Override
	public String getAddress() {
		return "localshost";
	}

	@Override
	public File getAppDir() {
		return new File("");
	}

	@Override
	public int getPort() {
		return 8080;
	}

	@Override
	public void waitForServerToStart() throws InterruptedException {
		System.out.println("ばーかばーか");
	}

}

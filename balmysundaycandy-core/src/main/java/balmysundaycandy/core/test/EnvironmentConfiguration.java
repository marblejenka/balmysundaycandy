package balmysundaycandy.core.test;

/**
 * テスト環境の設定
 * 
 * TODO もっと抽象的に設定できるようにする
 * 
 * @author marblejenka
 *
 */
public class EnvironmentConfiguration {

	private String applicationDirectoryName = "";

	private boolean willCreanupDatastoreFile = false;
	
	private boolean willUseStrageOnDatastoreTest = false;

	EnvironmentConfiguration() {
	}

	public EnvironmentConfiguration(String applicationDirectoryName, boolean willCreanupDatastoreFile, boolean willUseStrageOnDatastoreTest) {
		this.applicationDirectoryName = applicationDirectoryName;
		this.willCreanupDatastoreFile = willCreanupDatastoreFile;
		this.willUseStrageOnDatastoreTest = willUseStrageOnDatastoreTest;
	}

	public String getApplicationDirectoryName() {
		return applicationDirectoryName;
	}

	public boolean willCreanupDatastoreFile() {
		return willCreanupDatastoreFile;
	}

	public boolean willUseStrageOnDatastoreTest() {
		return willUseStrageOnDatastoreTest;
	}
}

package balmysundaycandy.core.environment;

/**
 * current working environment.
 * 
 * @author marblejenka
 * 
 */
public enum WorkingEnvironment {
	NOSERVER(null), DEVLOPMENT("Development"), PRODUCTION("Production");

	private static final String current = System.getProperty("com.google.appengine.runtime.environment");

	private String value;

	WorkingEnvironment(String value) {
		this.value = value;
	}

	public boolean is(WorkingEnvironment environment) {
		if (environment == null) {
			return false;
		}
		return environment.equals(this);
	}

	public static boolean isNocontainer() {
		return current().is(NOSERVER);
	}

	public static boolean isDevelopment() {
		return current().is(DEVLOPMENT);
	}

	public static boolean isProduction() {
		return current().is(PRODUCTION);
	}

	public static WorkingEnvironment current() {
		if (NOSERVER.value == current) { // NA value is null.
			return NOSERVER;
		} else if (DEVLOPMENT.value.equals(current)) {
			return DEVLOPMENT;
		} else if (PRODUCTION.value.equals(current)) {
			return PRODUCTION;
		} else {
			throw new IllegalStateException(current + " is unknown environment");
		}
	}
}

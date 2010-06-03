package balmysundaycandy.core.test;

import balmysundaycandy.core.environment.WorkingEnvironment;

public class TestEnvironmentUtils {
	public static void setupEnvironment(EnvironmentConfiguration environmentConfiguration) {
		if (WorkingEnvironment.isNocontainer()) {
			LocalEnvironmentTestSupport.setupNocontainerEnvironment(environmentConfiguration);

		} else if (WorkingEnvironment.isDevelopment()) {
			DevlopmentEnvironmentTestSupport.setupDevlopmentEnvironment(environmentConfiguration);

		} else if (WorkingEnvironment.isProduction()) {
			ProductionEnvironmentTestSupport.setupProductionEnvironment(environmentConfiguration);

		}
	}

	public static void teardownEnvironment(EnvironmentConfiguration environmentConfiguration) {
		if (WorkingEnvironment.isNocontainer()) {
			LocalEnvironmentTestSupport.teardownNocontainerEnvironment(environmentConfiguration);

		} else if (WorkingEnvironment.isDevelopment()) {
			DevlopmentEnvironmentTestSupport.teardownDevlopmentEnvironment(environmentConfiguration);

		} else if (WorkingEnvironment.isProduction()) {
			ProductionEnvironmentTestSupport.teardownProductionEnvironment(environmentConfiguration);

		}
	}
}

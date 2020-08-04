package libs;

import org.aeonbits.owner.Config;

@Config.Sources({"file:src/main/java/libs/ConfigProperties.properties"})
public interface ConfigProperties extends Config {
	long TIME_FOR_DEFAULT_WAIT();

	long TIME_FOR_EXPLICIT_WAIT_LOW();
	long TIME_FOR_EXPLICIT_WAIT_MIDDLE();
	long TIME_FOR_EXPLICIT_WAIT_HIGH();

	long EXPLICITLY_WAIT();
	long IMPLICITLY_WAIT();

	String BASE_URL();
	String TEST_API();

	String DEFAULT_BROWSER();
	String DEFAULT_VERSION();

	String IE();
	String FIREFOX();
	String CHROME();
	String REMOTE();

	String REMOTE_HUB();

    String PASSWORD();
    String LOGIN();
}

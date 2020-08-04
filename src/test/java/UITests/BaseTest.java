package UITests;

import io.github.bonigarcia.wdm.WebDriverManager;
import libs.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.AuthPage;
import pages.MainPage;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    private WebDriver _driver;

    public static ConfigProperties confProp = ConfigFactory.create(ConfigProperties.class);

    protected AuthPage authPage;
    protected MainPage mainPage;

    protected Logger logger = Logger.getLogger(getClass());

    private void initializePage() {
        authPage = new AuthPage(_driver);
        mainPage = new MainPage(_driver);
    }

    private void driverSettings() {
        _driver.manage().window().maximize();
        _driver.manage().timeouts().implicitlyWait(confProp.IMPLICITLY_WAIT(), TimeUnit.SECONDS);
        _driver.manage().timeouts().pageLoadTimeout(confProp.TIME_FOR_DEFAULT_WAIT(), TimeUnit.SECONDS);
    }

    @Before
    public void setUp() throws Exception {
        _driver = driverInit(confProp.DEFAULT_BROWSER());
        driverSettings();
        initializePage();
    }

    private WebDriver driverInit(String browser) throws Exception {
        if ((browser == null) || (confProp.CHROME().equalsIgnoreCase(browser))) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        } else if(confProp.FIREFOX().equalsIgnoreCase(browser)) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        } else if (confProp.IE().equalsIgnoreCase(browser)){
            WebDriverManager.iedriver().arch32().setup();
            return new InternetExplorerDriver();
        } else if (confProp.REMOTE().equals(browser)){
            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setBrowserName(confProp.CHROME());
            cap.setPlatform(Platform.WINDOWS);
            cap.setVersion(confProp.DEFAULT_VERSION());
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.merge(cap);
            return new RemoteWebDriver(new URL(confProp.REMOTE_HUB()), chromeOptions);
        } else {
            logger.error("Check browser version or path to browser");
            throw new Exception("Check browser version or path to browser");
        }
    }

    @After
    public void tearDown(){
        _driver.quit();
        logger.info("Driver was been quit");
    }
}

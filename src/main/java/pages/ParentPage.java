package pages;

import libs.ActionsWithElements;
import libs.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public abstract class ParentPage {
    protected WebDriver _driver;
    protected ActionsWithElements actionsWithElements;
    public static ConfigProperties confProp = ConfigFactory.create(ConfigProperties.class);
    protected Logger logger = Logger.getLogger(getClass());

    public ParentPage(WebDriver webDriver) {
        _driver = webDriver;
        actionsWithElements = new ActionsWithElements(_driver);
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(_driver)), this);
    }
    public String getCurrentUrl() { return _driver.getCurrentUrl(); }

    public void openPage(String partUrl) {
        String page = confProp.BASE_URL() + partUrl;
        try {
            _driver.get(page);
        } catch (Exception exc) {
            Assert.fail("Can't open this -> [" + page + "] page");
        }
    }

    public void checkCurrentUrl(String expectedUrl) {
        try {
            Assert.assertEquals("URL is not expected", expectedUrl, getCurrentUrl());
        }catch (Exception exc) {
            logger.error("Can't work get " + getCurrentUrl() + exc);
            Assert.fail("Can't work get " + getCurrentUrl() + exc);
        }
    }
}

package libs;

import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.util.ArrayList;
import java.util.List;

public class ActionsWithElements {
    WebDriver _driver;
    WebDriverWait webDriverWait_3, webDriverWait_5, webDriverWait_10, webDriverWait_15;
    ConfigProperties confProp = ConfigFactory.create(ConfigProperties.class);
    Logger logger = Logger.getLogger(getClass());

    public ActionsWithElements(WebDriver webDriver) {

        _driver = webDriver;
        webDriverWait_3 = new WebDriverWait(_driver, confProp.IMPLICITLY_WAIT());
        webDriverWait_5 = new WebDriverWait(_driver, confProp.TIME_FOR_EXPLICIT_WAIT_MIDDLE());
        webDriverWait_10 = new WebDriverWait(_driver, confProp.TIME_FOR_EXPLICIT_WAIT_LOW());
        webDriverWait_15 = new WebDriverWait(_driver, confProp.TIME_FOR_EXPLICIT_WAIT_HIGH());
    }

    public void enterTextIntoInput(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
        } catch (Exception exc) {
            stopTestAndPrintMessage(webElement);
        }
    }

    public void clickOnElement(WebElement webElement) {
        try {
            webDriverWait_3.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info("Click element: -> " + webElement);
        } catch (Exception exc) {
            Assert.fail("Can not work with -> " + webElement.getText());
        }
    }

    public boolean isElementDisplayed(WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (Exception exc) {
            exc.getStackTrace();
            return false;
        }
    }

    private void stopTestAndPrintMessage(WebElement element) {
        Assert.fail("We can't work this element -> " + element);
    }

    public void selectValueInDDByJava(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
        } catch (Exception exc) {
            stopTestAndPrintMessage(dropDown);
        }
    }

    public List<String> getList() {
        List<String> arrayList = new ArrayList<>();
        List<WebElement> listOfElements = _driver.findElements(By.xpath("//a[contains(@href, '/user/player/details?id=') and not (@title='Details')]"));
        for (WebElement element : listOfElements)
            arrayList.add(element.getText());
        return arrayList;
    }

    public void waitForLoadingGrid() throws InterruptedException { Thread.sleep(5000); }
}

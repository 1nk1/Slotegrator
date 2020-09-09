package libs;

import interfaces.IElement;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class Elements implements IElement {
    WebDriver _driver;
    WebDriverWait wait_3, wait_5, wait_10, wait_15;
    ConfigProperties confProp = ConfigFactory.create(ConfigProperties.class);
    Logger logger = Logger.getLogger(getClass());

    public Elements(WebDriver webDriver){
        _driver = webDriver;
        wait_3 = new WebDriverWait(_driver, confProp.IMPLICITLY_WAIT());
        wait_5 = new WebDriverWait(_driver, confProp.TIME_FOR_EXPLICIT_WAIT_MIDDLE());
        wait_10 = new WebDriverWait(_driver, confProp.TIME_FOR_EXPLICIT_WAIT_LOW());
        wait_15 = new WebDriverWait(_driver, confProp.TIME_FOR_EXPLICIT_WAIT_HIGH());
    }

    public IElement enterText(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
        } catch (Exception exc) {
            stopTestAndPrintMessage(webElement);
        }
        return this;
    }

    public void click(WebElement webElement) {
        try {
            wait_3.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info("Click element: -> " + webElement);
        } catch (Exception exc) {
            Assert.fail("Can not work with -> " + webElement.getText());
        }
    }

    public IElement isDisplayed(WebElement webElement) {
        try {
            webElement.isDisplayed();
        } catch (Exception exc) {
            exc.getStackTrace();
        }
        return this;
    }

    private void stopTestAndPrintMessage(WebElement element) {
        Assert.fail("We can't work this element -> " + element);
    }

    public IElement selectDropDown(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
        } catch (Exception exc) {
            stopTestAndPrintMessage(dropDown);
        }
        return this;
    }

    public List<String> getList() {
        List<String> arrayList;
        List<WebElement> elements = _driver.findElements(By.xpath("//a[contains(@href, '/user/player/details?id=') and not (@title='Details')]"));
        arrayList = elements.stream().map(WebElement::getText).collect(Collectors.toList());
        return arrayList;
    }

    public void waitForLoadingGrid() throws InterruptedException { Thread.sleep(5000); }
}

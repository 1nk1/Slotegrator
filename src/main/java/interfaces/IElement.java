package interfaces;

import org.openqa.selenium.WebElement;

import java.util.List;

public interface IElement {
    void click(WebElement webElement);
    IElement isDisplayed(WebElement element);
    IElement selectDropDown(WebElement element, String option);
    IElement enterText(WebElement element, String text);
    List<String> getList();
    void waitForLoadingGrid() throws InterruptedException;
}

package pages;

import interfaces.IMainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends ParentPage implements IMainPage {
    public MainPage(WebDriver _driver) { super(_driver); }

    @FindBy(id = "nav-wrapper") private WebElement adminPanel;
    @FindBy(id = "pageSizePlayers") private WebElement countOfPlayers;
    @FindBy(id = "payment-system-transaction-grid") private WebElement playerGrid;
    @FindBy(xpath = "//span[.='Users']") private WebElement userNameLeftSideBar;
    @FindBy(xpath = "//a[.='Username']") private WebElement userNameSortLink;
    @FindBy(xpath = "//a[@data-toggle='dropdown']/span") private WebElement userNameAdminPanel;
    @FindBy(xpath = "//a[@href='/user/player/admin']") private WebElement playersLeftSideBar;

    public boolean existAdminPanel() { return actionsWithElements.isElementDisplayed(adminPanel); }
    private void clickUsers() { actionsWithElements.clickOnElement(userNameLeftSideBar); }
    private void clickPlayers() { actionsWithElements.clickOnElement(playersLeftSideBar); }
    public boolean existPlayersGrid() { return actionsWithElements.isElementDisplayed(playerGrid); }
    public void selectCountUsers(int count) throws InterruptedException { actionsWithElements.selectValueInDDByJava(countOfPlayers, Integer.toString(count)); actionsWithElements.waitForLoadingGrid();}
    public void clickUserNameSort() throws InterruptedException { actionsWithElements.clickOnElement(userNameSortLink); actionsWithElements.waitForLoadingGrid(); }
    public List<String> getList() { return actionsWithElements.getList();}

    public void openPlayerList() {
        checkCurrentUrl(confProp.BASE_URL() + "/configurator/dashboard/index");
        clickUsers();
        clickPlayers();
    }
}

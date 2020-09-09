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

    public boolean existAdminPanel() { return adminPanel.isDisplayed(); }
    private void clickUsers() { _el.click(userNameLeftSideBar); }
    private void clickPlayers() { _el.click(playersLeftSideBar); }
    public boolean existPlayersGrid() { return playerGrid.isDisplayed(); }
    public void selectCountUsers(int count) throws InterruptedException { _el.selectDropDown(countOfPlayers, Integer.toString(count)); _el.waitForLoadingGrid();}
    public void clickUserNameSort() throws InterruptedException { _el.click(userNameSortLink); _el.waitForLoadingGrid(); }
    public List<String> getList() { return _el.getList();}

    public void openPlayerList() {
        checkCurrentUrl(confProp.BASE_URL() + "/configurator/dashboard/index");
        clickUsers();
        clickPlayers();
    }
}

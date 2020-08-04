package pages;

import interfaces.IAuthorizePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthPage extends ParentPage implements IAuthorizePage {

    @FindBy(id = "UserLogin_username") private WebElement inputLogin;
    @FindBy(id = "UserLogin_password") private WebElement inputPass;
    @FindBy(xpath = "//input[@value='Sign in']") private WebElement inputSignIn;

    public AuthPage(WebDriver _driver) { super(_driver); }

    private void enterLoginIntoInputLogin(String login) { actionsWithElements.enterTextIntoInput(inputLogin, login); }
    private void enterPassIntoInputPassword(String password) { actionsWithElements.enterTextIntoInput(inputPass, password); }
    private void clickButtonSignIn() { actionsWithElements.clickOnElement(inputSignIn); }

    public void authorize(String login, String password) {
        openPage("/admin/login");
        enterLoginIntoInputLogin(login);
        enterPassIntoInputPassword(password);
        clickButtonSignIn();
    }
}

package UITests;

import org.junit.Assert;
import org.junit.Test;

public class AuthorizeTest extends BaseTest {

    @Test
    public void Authorize() {
        authPage.authorize(confProp.LOGIN(), confProp.PASSWORD());
        Assert.assertTrue("Admin panel is visible", mainPage.existAdminPanel());
    }
}

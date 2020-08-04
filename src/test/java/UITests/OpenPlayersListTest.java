package UITests;

import org.junit.Assert;
import org.junit.Test;


public class OpenPlayersListTest extends BaseTest {

    @Test
    public void OpenListPlayers() {

        authPage.authorize(confProp.LOGIN(), confProp.PASSWORD());
        mainPage.openPlayerList();

        Assert.assertTrue("Players Grid is visible", mainPage.existPlayersGrid());
    }
}

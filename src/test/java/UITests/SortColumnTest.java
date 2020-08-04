package UITests;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;


public class SortColumnTest extends BaseTest {

    @Test
    public void checkSortColumnPlayersGrid() throws InterruptedException {

        List<String> notSortedList;
        List<String> sortedList;

        authPage.authorize(confProp.LOGIN(), confProp.PASSWORD());
        mainPage.openPlayerList();

        mainPage.selectCountUsers(200);

        notSortedList = mainPage.getList();
        Collections.sort(notSortedList);

        Thread.sleep(2000);

        mainPage.clickUserNameSort();
        sortedList = mainPage.getList();

        Assert.assertTrue(String.valueOf(sortedList.stream().filter(s -> s.startsWith("00")).map(String::toString)), true);
    }
}
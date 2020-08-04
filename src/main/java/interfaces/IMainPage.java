package interfaces;

import java.util.List;

public interface IMainPage {
    void openPlayerList();
    List<String> getList() throws InterruptedException;
}

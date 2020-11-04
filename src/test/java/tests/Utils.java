package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

    public WebDriverWait getWaiter(WebDriver driver, int time) {
        return new WebDriverWait(driver, time);
    }

}

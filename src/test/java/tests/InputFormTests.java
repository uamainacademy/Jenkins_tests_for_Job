package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class InputFormTests {

    private WebDriver driver;
    private By noThanksButton = By.xpath("//a[text()='No, thanks!']");
    private By inputFormLink = By.xpath("//a[text()='Input Forms']");
    private By simpleFormDemoLink = By.xpath("//li[@class='tree-branch']//a[text()='Simple Form Demo']");
    private By enterMessageField = By.xpath("//input[@id='user-message']");
    private By showMessageButton = By.xpath("//button[text()='Show Message']");
    private By resultText = By.xpath("//span[@id='display']");

    Utils utils = new Utils();

    @Before
    public void beforeEachTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.seleniumeasy.com/test/");
        try {
            utils.getWaiter(driver, 6).until(visibilityOfElementLocated(noThanksButton)).click();
        } catch (NoSuchElementException e) {
            System.out.println("Modal window not appear");
        }
    }

    @Test
    public void SimpleInputFormTest1() {
        String expectedText = "Hello";
        driver.findElement(inputFormLink).click();
        utils.getWaiter(driver, 3)
                .until(visibilityOfElementLocated(simpleFormDemoLink)).click();
        driver.findElement(enterMessageField).sendKeys(expectedText);
        driver.findElement(showMessageButton).click();
        String actualResult = driver.findElement(resultText).getText();
        assertEquals(expectedText, actualResult);
    }

    @After
    public void afterEachTest() {
        driver.quit();
    }

}

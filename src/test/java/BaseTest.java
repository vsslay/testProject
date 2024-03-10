import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pages.MainPage;

import static constants.constant.Urls.MAIN_PAGE;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    protected WebDriver driver = setUp();
    protected MainPage mainPage = new MainPage(driver);

    @Step("Setting up driver")
    public WebDriver setUp() {

        WebDriver driver = null;
        String browserType = System.getProperty("browser", "chrome");
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        switch (browserType) {
            case "chrome" -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                }
                driver = new ChromeDriver(chromeOptions);
                driver.manage().window().maximize();
            }
            case "edge" -> {
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) {
                    edgeOptions.addArguments("--headless=new");
                }
                driver = new EdgeDriver(edgeOptions);
                driver.manage().window().maximize();
            }
            case "firefox" -> {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) {
                    firefoxOptions.addArguments("--headless=new");
                }
                driver = new FirefoxDriver(firefoxOptions);
                driver.manage().window().maximize();
            }
        }
        return driver;
    }

    @Step("Navigating to main page")
    @BeforeAll
    public void navigate() {
        driver.get(MAIN_PAGE);
        mainPage.checkTittleDisplayed();
    }

    @Step("Quiting driver")
    @AfterAll
    public void tearDown() {
        driver.quit();
    }

}

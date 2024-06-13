package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;
import java.util.function.Function;

public class Topic_30_Wait_09_Fluent {
    WebDriver driver;
    WebDriverWait explicitWait;
    FluentWait<WebDriver> fluentDriver;
    FluentWait<WebElement> fluentElement;
    FluentWait<String> fluentString;


    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        fluentDriver = new FluentWait<WebDriver>(driver);

    }

    @Test
    public void TC_01_Url() {

        // Khoi tao
        fluentDriver = new FluentWait<WebDriver>(driver);

        fluentElement = new FluentWait<WebElement>(driver.findElement(By.cssSelector("")));

        fluentString = new FluentWait<String>("abcca");

        // Setting
        fluentDriver.withTimeout(Duration.ofSeconds(30));

        fluentDriver.pollingEvery(Duration.ofMillis(300));

        fluentDriver.ignoring(NoSuchElementException.class);

        // ignore timeoutException
        fluentDriver.ignoring(TimeoutException.class);


        // Condition
        fluentDriver.until(new Function<WebDriver, String>() {
            @Override
            public String apply(WebDriver webDriver) {
                return null;
            }
        });

        fluentDriver.withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(400))
                .ignoring(NoSuchElementException.class, TimeoutException.class)
                .until(new Function<WebDriver, Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        return driver.findElement(By.cssSelector("")).isDisplayed();
                    }
                });

    }

    @Test
    public void TC_02_Hello_World() {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        waitAndFindElement(By.cssSelector("div#start>button")).click();
        fluentDriver.withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(NoSuchElementException.class);

        Assert.assertTrue(waitAndFindElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed());
    }

    @Test
    public void TC_03_CountDown() {
        driver.get("https://automationfc.github.io/fluent-wait/");
        WebElement countDown = driver.findElement(By.cssSelector("div#javascript_countdown_time"));
        fluentElement = new FluentWait<WebElement>(countDown);
        fluentElement.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        fluentElement.until(new Function<WebElement, Boolean>() {

            @Override
            public Boolean apply(WebElement webElement) {
                return webElement.getText().endsWith("00");
            }
        });
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public WebElement waitAndFindElement (By locator){
        FluentWait<WebDriver> fluentDriver = new FluentWait<WebDriver>(driver);
        fluentDriver.withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(NoSuchElementException.class);
        return fluentDriver.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(locator);
            }
        });
    }

    public String getEmailAddress(){
        Random rand = new Random();
        return "uy.pham" + rand.nextInt(99999) + "@gmail.com";
    }
}
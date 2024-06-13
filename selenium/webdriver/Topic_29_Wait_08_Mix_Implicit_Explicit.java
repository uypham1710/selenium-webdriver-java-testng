package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_29_Wait_08_Mix_Implicit_Explicit {
    WebDriver driver;
    WebDriverWait explicitWait;
    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();


    }

    @Test
    public void TC_01_Only_Implicit_Found() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.facebook.com/");
        driver.findElement(By.cssSelector("input#email")).sendKeys("abc");

    }

    @Test
    public void TC_02_Only_Implicit_Not_Found() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.facebook.com/");
        driver.findElement(By.cssSelector("input#azb")).sendKeys("abc");
    }

    @Test
    public void TC_03_Only_Explicit_Found() {
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(5));
        driver.get("https://www.facebook.com/");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email"))).sendKeys("abc");
    }

    @Test
    public void TC_04_Only_Explicit_Not_Found() {
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(5));
        driver.get("https://www.facebook.com/");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#avc"))).sendKeys("abc");
    }

    @Test
    public void TC_05_Mix_Explicit_Implicit_Found() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(5));
        driver.get("https://www.facebook.com/");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email"))).sendKeys("abc");
    }

    @Test
    public void TC_06_Mix_Explicit_Implicit_Not_Found() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.get("https://www.facebook.com/");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#azz"))).sendKeys("abc");
    }

    @Test
    public void TC_06_Mix_Explicit_Implicit_Not_Found_WebElement() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.get("https://www.facebook.com/");
        explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#azz")))).sendKeys();
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }


    public String getEmailAddress(){
        Random rand = new Random();
        return "uy.pham" + rand.nextInt(99999) + "@gmail.com";
    }
}
package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_22_Wait_01_Element_Status {
    WebDriver driver;
    WebDriverWait explicitWait;
    By reconfirmEmail = By.cssSelector("input[name='reg_email_confirmation__']");
    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.get("https://www.facebook.com/");
    }

    @Test
    public void TC_01_Visible() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);


        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("uy@gmail.com");
        sleepInSeconds(3);

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(reconfirmEmail));
        Assert.assertTrue(driver.findElement(reconfirmEmail).isDisplayed());

    }

    @Test
    public void TC_02_Invisible_In_DOM() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);
        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("uy@gmail.com");
        sleepInSeconds(3);

        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        sleepInSeconds(3);

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reconfirmEmail));
        Assert.assertFalse(driver.findElement(reconfirmEmail).isDisplayed());


    }

    @Test
    public void TC_02_Invisible_Not_In_DOM() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);
        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("uy@gmail.com");
        sleepInSeconds(3);
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleepInSeconds(3);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reconfirmEmail));
    }

    @Test
    public void TC_03_Presence() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);
        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("uy@gmail.com");
        sleepInSeconds(3);
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(reconfirmEmail));


    }

    @Test
    public void TC_04_Staleness() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);
        WebElement confirmEmail = driver.findElement(reconfirmEmail);
        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("uy@gmail.com");
        sleepInSeconds(3);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(reconfirmEmail));
        Assert.assertTrue(driver.findElement(reconfirmEmail).isDisplayed());
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleepInSeconds(3);
        explicitWait.until(ExpectedConditions.stalenessOf(confirmEmail));

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
    public void sleepInSeconds(long timeInSecond){
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getEmailAddress(){
        Random rand = new Random();
        return "uy.pham" + rand.nextInt(99999) + "@gmail.com";
    }
}
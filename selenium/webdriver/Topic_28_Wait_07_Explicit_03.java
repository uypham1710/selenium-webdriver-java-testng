package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.Random;

public class Topic_28_Wait_07_Explicit_03 {
    WebDriver driver;
    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");
    String exName1 = "image01.jpg";
    String exName2 = "image02.jpg";
    String exName3 = "image03.jpg";

    String exFilePath1 = projectPath + File.separator + "uploadFiles" + File.separator + exName1;
    String exFilePath2 = projectPath + File.separator + "uploadFiles" + File.separator + exName2;
    String exFilePath3 = projectPath + File.separator + "uploadFiles" + File.separator + exName3;
    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_Date_Time() {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table.rcMainTable")));
        Assert.assertEquals(driver.findElement(By.cssSelector("span.label")).getText(),"No Selected Dates to display.");
        driver.findElement(By.xpath("//a[text()='28']")).click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1']>div.raDiv")));
        Assert.assertEquals(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.label"))).getText(),"Sunday, April 28, 2024");

    }

    @Test
    public void TC_02_Upload_File() {

        driver.get("https://gofile.io/welcome");
        Assert.assertTrue(isPageLoadedSuccess());
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner-border")));
        explicitWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("a.ajaxLink>button"))));
        driver.findElement(By.cssSelector("a.ajaxLink>button")).click();

        Assert.assertTrue(isPageLoadedSuccess());
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner-border")));
        explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input[type='file']"))));
        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(exFilePath1 + "\n" + exFilePath2 + "\n" + exFilePath3);




    }

    @Test
    public void TC_03_Url() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
    public boolean isPageLoadedSuccess() {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    public String getEmailAddress(){
        Random rand = new Random();
        return "uy.pham" + rand.nextInt(99999) + "@gmail.com";
    }
}
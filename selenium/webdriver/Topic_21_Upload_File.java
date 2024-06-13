package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Topic_21_Upload_File {
    WebDriver driver;

    String projectPath = System.getProperty("user.dir");
    String exName1 = "image01.jpg";
    String exName2 = "image02.jpg";
    String exName3 = "image03.jpg";

    String exFilePath1 = projectPath + File.separator + "uploadFiles" + File.separator + exName1;
    String exFilePath2 = projectPath + File.separator + "uploadFiles" + File.separator + exName2;
    String exFilePath3 = projectPath + File.separator + "uploadFiles" + File.separator + exName3;

    @BeforeClass
    public void beforeClass() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));


    }

    @Test
    public void TC_01_Upload_File() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        By uploadBy = By.cssSelector("input[type='file']");
        driver.findElement(uploadBy).sendKeys(exFilePath1);
        driver.findElement(uploadBy).sendKeys(exFilePath2);
        driver.findElement(uploadBy).sendKeys(exFilePath3);

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + exName1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + exName2 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + exName3 + "']")).isDisplayed());

        List<WebElement> startButtons = driver.findElements(By.cssSelector("td>button.start"));
        for (WebElement start : startButtons){
            start.click();
            sleepInSeconds(2);
        }

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + exName1 +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + exName2 +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + exName3 +"']")).isDisplayed());
    }

    @Test
    public void TC_02_Multiple_File() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        By uploadBy = By.cssSelector("input[type='file']");
        driver.findElement(uploadBy).sendKeys(exFilePath1 + "\n" + exFilePath2 + "\n" + exFilePath3);

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + exName1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + exName2 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + exName3 + "']")).isDisplayed());

        List<WebElement> startButtons = driver.findElements(By.cssSelector("td>button.start"));
        for (WebElement start : startButtons){
            start.click();
            sleepInSeconds(2);
        }

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + exName1 +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + exName2 +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + exName3 +"']")).isDisplayed());
    }

    @Test
    public void TC_03_Url() {

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
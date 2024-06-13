package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_17_Frame_iFrame {
    WebDriver driver;
    JavascriptExecutor javascriptExecutor;
    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        javascriptExecutor = (JavascriptExecutor) driver;
    }

    @Test
    public void TC_01_iFrame() {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        if (driver.toString().contains("firefox")){
            javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.cssSelector("div#imageTemplateContainer>img")));
            sleepInSeconds(2);
        }
        driver.findElement(By.cssSelector("div#imageTemplateContainer>img")).click();
        sleepInSeconds(3);
        driver.switchTo().frame("frame-one85593366");
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Sophomore");
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-3"))).selectByVisibleText("East Dorm");
        //driver.findElement(By.cssSelector("td.highlight")).click();
        sleepInSeconds(2);
        driver.findElement(By.cssSelector("input#FSsubmit")).click();
        sleepInSeconds(2);
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("nav.header--desktop-floater a.menu-item-login")).click();
        sleepInSeconds(2);
        driver.findElement(By.cssSelector("button#login")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(), "Username and password are both required.");


    }

    @Test
    public void TC_02_bank() {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");
        driver.switchTo().frame("login_page");
        driver.findElement(By.cssSelector("input.form-control")).sendKeys("uypham123");
        driver.findElement(By.cssSelector("a.login-btn")).click();
        sleepInSeconds(3);
        driver.findElement(By.cssSelector("input#keyboard")).sendKeys("12345678");
        sleepInSeconds(3);

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
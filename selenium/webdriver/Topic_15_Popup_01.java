package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_15_Popup_01 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {



        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(new FirefoxProfile());
        options.addPreference("dom.webnotifications.enabled", false);
        driver = new FirefoxDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Fixed_Dom_Ngoaingu() {
        driver.get("https://ngoaingu24h.vn/");
        By loginPopup = By.cssSelector("div#modal-login-v1 div.modal-content");
        Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
        driver.findElement(By.cssSelector("button.login_")).click();
        sleepInSeconds(1);
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
        driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationfc");
        driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationfc");
        driver.findElement(By.cssSelector("button.btn-login-v1")).click();
        sleepInSeconds(1);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.error-login-panel")).getText(),"Tài khoản không tồn tại!");
        driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();
        Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
    }

    @Test
    public void TC_02_Fixed_Dom_Kyna() {
        driver.get("https://skills.kynaenglish.vn/");
        driver.findElement(By.cssSelector("a.login-btn")).click();
        sleepInSeconds(1);
        Assert.assertTrue(driver.findElement(By.cssSelector("div.k-popup-account-mb-content")).isDisplayed());
        driver.findElement(By.cssSelector("input#user-login")).sendKeys("automation@gmail.com");
        driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#btn-submit-login")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(),"Sai tên đăng nhập hoặc mật khẩu");
        driver.findElement(By.cssSelector("button.k-popup-account-close.close")).click();
        sleepInSeconds(1);
        Assert.assertFalse(driver.findElement(By.cssSelector("div.k-popup-account-mb-content")).isDisplayed());

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
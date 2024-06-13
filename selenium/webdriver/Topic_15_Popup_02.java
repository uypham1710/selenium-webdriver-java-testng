package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Driver;
import java.time.Duration;
import java.util.Random;

public class Topic_15_Popup_02 {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_Random_Not_In_Dom() {
        driver.get("https://www.javacodegeeks.com/");
        By newLetterPopup = By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");
        if(driver.findElements(newLetterPopup).size() > 0 && driver.findElements(newLetterPopup).get(0).isDisplayed()){
            System.out.println("Popup có hiển thị");
            driver.findElement(By.cssSelector(
                    "div.lepopup-popup-container>div:not([style^='display:none']) div.lepopup-element-html-content>a")).click();
            sleepInSeconds(3);
        } else {
            System.out.println("Popup không hiển thị");
        }
        driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");
        driver.findElement(By.cssSelector("button#search-submit")).click();
        sleepInSeconds(5);
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Agile Testing Explained']")).isDisplayed());
    }

    @Test
    public void TC_02_Random_In_Dom() {
        driver.get("https://vnk.edu.vn/");
        sleepInSeconds(30);
        By marketingPopup = By.cssSelector("div.thrv_wrapper.thrv-columns");
        if (driver.findElement(marketingPopup).isDisplayed()){
            System.out.println("Popup có hiển thị");
            driver.findElement(By.cssSelector("div.tve_ea_thrive_leads_form_close")).click();
            sleepInSeconds(3);
        }else{
            System.out.println("Popup không hiển thị");
        }
        driver.findElement(By.xpath("//button[text()='Danh sách khóa học']")).click();
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.title-content>h1")).getText(), "Lịch Khai Giảng Tháng 04");

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
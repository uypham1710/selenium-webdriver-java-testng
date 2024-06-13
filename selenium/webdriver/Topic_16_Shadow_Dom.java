 package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_16_Shadow_Dom {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_Shadow_Dom_1() {
        driver.get("https://automationfc.github.io/shadow-dom");
        sleepInSeconds(2);
        WebElement shadowHost = driver.findElement(By.cssSelector("#shadow_host"));
        SearchContext shadowRootContext = shadowHost.getShadowRoot();
        String someText = shadowRootContext.findElement(By.cssSelector("span.info")).getText();
        System.out.println(someText);
        Assert.assertEquals(someText,"some text");

        //checkbox
        Assert.assertFalse(shadowRootContext.findElement(By.cssSelector("input[type='checkbox']")).isSelected());

        WebElement nestedShadow = shadowRootContext.findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext nestedShadowRootContext = nestedShadow.getShadowRoot();
        String nestedText = nestedShadowRootContext.findElement(By.cssSelector("div#nested_shadow_content")).getText();
        System.out.println(nestedText);
        Assert.assertEquals(nestedText,"nested text");


    }

    @Test
    public void TC_02_Shadow_Dom_Shopee() {
        driver.get("https://shopee.vn");
        WebElement shadowHost = driver.findElement(By.cssSelector("shopee-banner-popup-stateful"));
        SearchContext popupContext = shadowHost.getShadowRoot();
        if (popupContext.findElements(By.cssSelector("div.home-popup__content")).size() > 0 &&
                popupContext.findElements(By.cssSelector("div.home-popup__content")).get(0).isDisplayed()){
            popupContext.findElement(By.cssSelector("div.shopee-popup__close-btn")).click();
            sleepInSeconds(2);
        }

        driver.findElement(By.cssSelector("input.shopee-searchbar-input__input")).sendKeys("Iphone 15 promax");
        driver.findElement(By.cssSelector("button.shopee-searchbar__search-button")).click();

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
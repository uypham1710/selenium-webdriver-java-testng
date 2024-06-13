package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;
import java.util.Set;

public class Topic_18_Window_Tab {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_AutomationFC() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        SwitchToWindowByTitle("Google");
        sleepInSeconds(3);

        Assert.assertEquals(driver.getTitle(),"Google");

        driver.findElement(By.cssSelector("textarea#APjFqb")).sendKeys("automation test");
        sleepInSeconds(3);
        SwitchToWindowByTitle("Selenium WebDriver");
        sleepInSeconds(2);

        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        sleepInSeconds(2);
        SwitchToWindowByTitle("Facebook – log in or sign up");
        driver.findElement(By.cssSelector("button[name='login']")).click();
        sleepInSeconds(1);

        SwitchToWindowByTitle("Selenium WebDriver");
        sleepInSeconds(1);
        driver.findElement(By.xpath("//a[text()='TIKI']")).click();
        sleepInSeconds(1);
        SwitchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
        Assert.assertEquals(driver.getTitle(),"Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

        CloseAllWindowExceptParent("Selenium WebDriver");
        sleepInSeconds(2);

    }

    @Test
    public void TC_02_Selenium_Version_4() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(),"The product Sony Xperia has been added to comparison list.");

        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(),"The product Samsung Galaxy has been added to comparison list.");
        driver.switchTo().newWindow(WindowType.WINDOW).get("http://live.techpanda.org/index.php/catalog/product_compare/index/");
        Assert.assertEquals(driver.getTitle(),"Products Comparison List - Magento Commerce");
        driver.findElement(By.cssSelector("button[title='Close Window']")).click();
        sleepInSeconds(2);

        SwitchToWindowByTitle("Mobile");
        driver.findElement(By.xpath("//a[text()='Clear All']")).click();
        driver.switchTo().alert().accept();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(),"The comparison list was cleared.");

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

    public void SwitchToWindowByTitle(String expectedTitle){
        Set<String> allIds = driver.getWindowHandles();
        for (String id : allIds){
            driver.switchTo().window(id);
            if(driver.getTitle().equals(expectedTitle)){
                break;
            }
        }
    }

    public void CloseAllWindowExceptParent(String expectedTitle){
        Set<String> allIds = driver.getWindowHandles();
        for (String id : allIds){
            driver.switchTo().window(id);
            if(!driver.getTitle().equals(expectedTitle)){
                driver.close();
            }
        }
    }
}
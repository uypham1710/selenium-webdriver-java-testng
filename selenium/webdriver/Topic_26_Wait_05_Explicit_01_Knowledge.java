package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_26_Wait_05_Explicit_01_Knowledge {
    WebDriver driver;
    WebDriverWait explicitWait;
    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(10));
        // customer roll = 0.3s
        //explicitWait = new WebDriverWait(driver,Duration.ofSeconds(10),Duration.ofMillis(300));


    }

    @Test
    public void TC_01_Url() {
        // Chờ cho 1 alert presence trước khi thao tác lên
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        // Cho cho element ko còn trong DOM nữa
        explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector(""))));

        // Chờ cho element có ở trong DOM, ko quan tâm có trên UI k
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("explicitWait.until(ExpectedConditions.")));

        // Chờ cho list element có ở trong DOM, ko quan tâm có trên UI k
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")));
        // chờ cho 1-n element hiển thị trên UI
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));
        explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector("")),driver.findElement(By.cssSelector(""))));

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("")));

        // CHờ page hiện tại có title như mong đợi
        explicitWait.until(ExpectedConditions.titleIs("avc"));
//        //explicitWait.until(ExpectedConditions.and(
//                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(""),
//                ExpectedConditions.presenceOfElementLocated(By.cssSelector("")))));
        // Chờ cho element có attribute chứa giá trị mong đợi (tương đối)
        explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector(""),"",""));

        // Chờ cho element có attribute chứa giá trị mong đợi (tuyệt đối)
        explicitWait.until(ExpectedConditions.attributeToBe(By.cssSelector(""),"",""));

        // Chờ cho element có attribute khác null
        explicitWait.until(ExpectedConditions.attributeToBeNotEmpty(driver.findElement(By.cssSelector("")),"" ));

        explicitWait.until(ExpectedConditions.domAttributeToBe(driver.findElement(By.cssSelector("input#search")),"namespaceURI" ,"http://www.w3.org/1999/xhtml"));

        explicitWait.until(ExpectedConditions.domPropertyToBe(driver.findElement(By.cssSelector("input#search")),"namespaceURI" ,"http://www.w3.org/1999/xhtml"));

        explicitWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("")));

        //chờ cho element được selected
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""),true));

        //chờ cho 1 frame/iframe available và switch qua
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(""));

        // Chờ cho 1 element biến mất (k hiển thị trên giao diện)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));

        // Chờ cho 1 đoạn code JS cần trả về giá trị
        explicitWait.until(ExpectedConditions.jsReturnsValue("return argument[0].validationMessage;"));

        // Chờ cho 1 đoạn code JS thực thi ko ném ra ngoại lệ nào hết
        // k có ném ra true
        // có ném ra false
        explicitWait.until(ExpectedConditions.javaScriptThrowsNoExceptions("return argument[0].validationMessage;"));

        Assert.assertTrue(explicitWait.until(ExpectedConditions.javaScriptThrowsNoExceptions("return argument[0].validationMessage;")));

        explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(""),6));
        explicitWait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(""),6));
        explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(""),6));

        explicitWait.until(ExpectedConditions.numberOfWindowsToBe(3));

        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector(""),""));

        explicitWait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(""),""));

        explicitWait.until(ExpectedConditions.urlToBe(""));
        explicitWait.until(ExpectedConditions.urlContains(""));
        explicitWait.until(ExpectedConditions.urlMatches(""));

        //chờ cho 1 điều kiện àm element này bị update trạng thái - load lại HTML
        explicitWait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(By.cssSelector(""))));


    }

    @Test
    public void TC_02_Url() {

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
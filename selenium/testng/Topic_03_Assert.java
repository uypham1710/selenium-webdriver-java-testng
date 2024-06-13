package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Topic_03_Assert {
    WebDriver driver;
    public void test01(){
        String fullName = "Automation FC";
        Assert.assertEquals(fullName,"Automation FC");

        Assert.assertTrue(isElementDisplayed(By.cssSelector("")));
    }

    private boolean isElementDisplayed(By locator){
        return driver.findElement(locator).isDisplayed();

    }
}

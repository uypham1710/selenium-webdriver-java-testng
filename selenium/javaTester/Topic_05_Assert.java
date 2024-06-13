package javaTester;

import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class Topic_05_Assert {
    WebDriver driver;
    @Test
    public void verifyTestNG() {
        driver = new FirefoxDriver();
        driver.get("https://www.facebook.com/");
        // Trong Java có nhiều thư viện để verify dữ liệu
        // Testing Framework (Unit/ Integration/ UI Automation test)
        // JUnit 4 / TestNG/ JUnit 5/ Hamcrest/ AssertJ/...

        // Kiểu dữ liệu nhận vào là boolean true/false
        // KHi mong muốn điều kiện trả về là đúng thì dùng assertTrue để verify
        Assert.assertTrue(driver.getPageSource().contains("Facebook helps you connect and share with the people in your life."));

        // Mong muon dieu kien tra ve la sai
        Assert.assertFalse(driver.getPageSource().contains("Create a new account"));

        // Các hàm trả về kiểu dữ liệu boolean
        // Quy tắc: bắt đầu với tiền tố isXXX
        // WebElement
        driver.findElement(By.id("")).isDisplayed();
        driver.findElement(By.id("")).isEnabled();
        driver.findElement(By.id("")).isSelected();


        // Mong đợi 1 điều kiện nó giống như thực tế (Tuyệt đối)
        // Actual = Expect

        Object name = null;
        Assert.assertNull(name);



    }
}

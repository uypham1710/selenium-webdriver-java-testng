package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_07_WebElement_Commands_01 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");
    }

    @Test
    public void TC_01_Element() {
        // Tìm và trả về 1 element
        // Tìm chưa tương tác lên
        driver.findElement(By.id(""));

       // Tìm và tương tác lên
        driver.findElement(By.id("")).click();
        driver.findElement(By.id("")).sendKeys("");

        // Tìm và lưu vào 1 biến WebElement
        WebElement fullNameTextBox = driver.findElement(By.id(""));
        fullNameTextBox.clear();
        fullNameTextBox.sendKeys("Test");
        fullNameTextBox.getAttribute("value");

        // Dùng để clear trong 1 field cho phép nhập
        // Thường đc dùng trước sendKeys
        driver.findElement(By.id("")).clear();

        // Dùng để nhập liệu
        driver.findElement(By.id("")).sendKeys("abc");


        // Dùng để click lên element
        driver.findElement(By.id("")).click();

        // Tìm từ node cha vafo node con
        driver.findElement(By.id("")).findElement(By.id(""));

        // Trả về nhiều element khớp với điều kiện
        List<WebElement> textBoxes = driver.findElements(By.cssSelector(""));

        // Dùng để verify 1 checkbox/radio/dropdown đã đc chọn hay chưa
        Assert.assertTrue(driver.findElement(By.id("")).isSelected());
        Assert.assertFalse(driver.findElement(By.id("")).isSelected());

        // Dùng để verify 1 element bất kỳ có hiển th hay không
        Assert.assertFalse(driver.findElement(By.id("")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("")).isDisplayed());

        // Dùng để verify 1 elemtn  có được thao tác lên hay không
        Assert.assertFalse(driver.findElement(By.id("")).isEnabled());
        Assert.assertTrue(driver.findElement(By.id("")).isEnabled());

        driver.findElement(By.id("")).getAttribute("title");


        // Tab Accessibility/ Properties trong Element
        driver.findElement(By.id("")).getAccessibleName();
        driver.findElement(By.id("")).getDomAttribute("checked");
        driver.findElement(By.id("")).getDomProperty("outerHTML");

        // Font/size/color...
        driver.findElement(By.id("")).getCssValue("background");

        // Vị trí element so với ộ phân giải màn hình
        Point nameTextBoxLocation = driver.findElement(By.id("")).getLocation();

        // Location + Size
        driver.findElement(By.id("")).getRect();

        // Shadow Element (JavascriptExecutor)
        driver.findElement(By.id("")).getShadowRoot();

        driver.findElement(By.id("")).getSize();

        // Từ id/class/name/css/xpath có thể truy ra ngược lại tagname HTML
        driver.findElement(By.id("")).getTagName();
        driver.findElement(By.cssSelector("")).getTagName();
        driver.findElement(By.xpath("")).getTagName();

        driver.findElement(By.id("")).getText();


        //Chụp hình bị lỗi và lưu dưới dạng nào
        // BYTE
        // FILE (lưu thành 1 hình có kích thước ở trong ổ cứng: .png/ .jpg/
        // BASE64 ( hình dạng text)
        driver.findElement(By.cssSelector("")).getScreenshotAs(OutputType.BASE64);
        driver.findElement(By.cssSelector("")).getScreenshotAs(OutputType.FILE);

        // Element nào là thẻ form hoặc nằm trong thẻ form
        // Hành vi giống phím Enter
        // Register/ Login / Search
        driver.findElement(By.cssSelector("")).submit();


    }

    @Test
    public void TC_02_Logo() {
        Assert.assertTrue(driver.findElement(By.cssSelector("img.fb_logo")).isDisplayed());
    }

    @Test
    public void TC_03_Form() {
        Assert.assertTrue(driver.findElement(By.xpath("//form[@data-testid='royal_login_form']")).isDisplayed());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
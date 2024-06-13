package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_06_WebBrowser_Commands_01 {

    //Các câu lệnh để thao tác vs Browser
    //driver.
    WebDriver driver;

    //Các câu lệnh thao tác vs Element
    //element.
    WebElement element;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {

        // Muốn dùng được phải khởi tạo
        // Không khởi tạo sẽ bị lỗi: NullPointerException
        driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver = new EdgeDriver();
        driver = new InternetExplorerDriver();

        System.out.println(driver.toString());
        //FirefoxDriver: firefox on windows (ee55c103-677e-4ba1-8101-210cbd38b9e4)
        //GUID: Global Unique Identify

        // Selenium ver 3
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        // Selenium ver 4
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_() throws MalformedURLException {
        //Set trực tiếp vào
        //Mở ra 1 page URL bất kì
        driver.get("https://znews.vn/");
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().setSize(new Dimension(1366,768));
        System.out.println("Window/ tab ID = " + driver.getWindowHandle());

        //Khai báo biến rồi gán vào
        //Nếu như biến này chỉ dùng duy nhất 1 lần thì không nên tạo biến
//        String homePageUrl = "https://znews.vn/";
//        driver.get(homePageUrl);


        // Nếu như có 1 tab/window tính năng tương tự quit
        // Nhiều hơn 1 tab -> đóng tab đang active
        driver.close();

        // Đóng browser ko care bao nhiêu tab/window
        driver.quit();

        // 2 hàm này sẽ bị ảnh hưởng timeout của implicitWait (hàm chờ cho 1 element đc tìm thấy
        // findElement/ findElements

        // Nó sẽ đi tìm vs loại By nào và trả về element nếu như được tìm thấy
        // ko được tìm thấy : Fail tại step này - throw exception: NoSuchElementException
        // Trả về 1 element - nếu như tìm thấy nhiều hơn 1 thì cũng lấy 1 (thao tác với cái đầu tiên)
        WebElement emailAddressTextbox = driver.findElement(By.id("email"));

        // Nó sẽ đi tìm vs loại By nào và trả về 1 danh sách các element nếu như được tìm thấy
        // ko được tìm thấy - ko bị fail = trả về 1 list rỗng (0 element)
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
        checkboxes.get(1).click();

        driver.findElement(By.cssSelector("button#login")).click();

        // Tại sao lại cần lấy dữ liệu ra làm gì?
        // Lấy để verify
        driver.getCurrentUrl();

        // Lấy page source code
        // Verify 1 cách tương đối
        driver.getPageSource();
        driver.getCurrentUrl().contains("Facebook helps you connect and share with the people in your life.");
        Assert.assertTrue(driver.getCurrentUrl().contains("Facebook helps you connect and share with the people in your life."));

        // Lấy ra title của page hiện tại
        driver.getTitle();

        // Lấy ra id của cửa sổ/tab hiện tại
        driver.getWindowHandle();

        driver.getWindowHandles();

        // Nếu chỉ dùng 1 lần thì không cần khai báo biến
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");

        // Cookies  - Framework
        driver.manage().getCookies();

        // get ra những log ở Dev Tool - Framework
        driver.manage().logs().get(LogType.DRIVER);

        // Apply cho viêc tìm element (findElement)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        // Chờ cho page load xong
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));


        // set trước khi dùng vs thư viện JavascriptExecutor
        // Inject 1 đoạn code JS vào trong browser/ Element
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));


        // Selenium 4 trở lên mới có
        driver.manage().timeouts().getImplicitWaitTimeout();
        driver.manage().timeouts().getPageLoadTimeout();
        driver.manage().timeouts().getScriptTimeout();


        // Chạy full màn hình
        driver.manage().window().fullscreen();
        driver.manage().window().maximize();
        driver.manage().window().minimize();

        //Test GUI
        //Test Responsive (resolution)
        driver.manage().window().setSize(new Dimension(1366,768));
        driver.manage().window().setSize(new Dimension(1920,1080));

        driver.manage().window().getSize();

        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().getPosition();

        // Điều huướng trang web
        driver.navigate().back();
        driver.navigate().refresh();
        driver.navigate().forward();

        // Thao tác vs history của web page (back/forward)
        driver.navigate().to("https://www.facebook.com/");
        driver.navigate().to(new URL("https://www.facebook.com/"));

        // Alert/Window(Tab) / Frame (iFrame)
        driver.switchTo().alert().accept();
        driver.switchTo().alert().dismiss();
        driver.switchTo().alert().getText();
        driver.switchTo().alert().sendKeys("Test");

        String homePageWindowId = driver.getWindowHandle();
        driver.switchTo().window(homePageWindowId);

        // Switch / handle frame (iFrame)
        // Index / ID (name) / Element
        driver.switchTo().frame(0);
        driver.switchTo().frame("213232");
        driver.switchTo().frame(driver.findElement(By.id("")));

        // Switch về HTML chưa frame trước đó
        driver.switchTo().defaultContent();

        // Tu frame trong đi ra frame ngoài chwa nó
        driver.switchTo().parentFrame();
    }
    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}

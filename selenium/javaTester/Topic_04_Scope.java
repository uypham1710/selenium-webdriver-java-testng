package javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Scope {
    // Các biến đc khai báo ở bên ngoài hà, => phạm vi là class
    // Biến Global (toàn cục)
    // Có thể dùng cho tất cả các hàm ở trong 1 Class đó
    WebDriver driver;

    String homePageUrl = "https://www.facebook.com/"; // Khai báo: Declare

    String fullName = "Automation FC"; // Khai báo + khởi tạo (Initial)


    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();

    }

    @Test
    public void TC_01_Url() {
        // Các biến được khai báo ở trong 1 hàm / block code -> phạm vi cục bộ (Local)
        // Dùng trong cái hàm nó được khai báo / Block code được sinh ra

        String homePageUrl = "https://www.facebook.com/";

        // trong 1 hàm nếu như có 2 biến cùng tên (Global / Local) thì nó sẽ ưu tiên lấy biến Local dùng
        // 1 biến Local nếu như gọi tới dùng mà chưa được khởi tạo sẽ bị lỗi
        // Biến Local chưa khởi tạo àm gọi ra dùng sẽ báo lỗi ngay (compile code)
        driver.get(homePageUrl);

        // Nếu trong 1 hàm có 2 biến cùng tên (Global / Local) mà mình muốn lấy biến Global dùng
        // Từ khóa this
        // Biến Global chưa khởi tạo mà gọi ra dùng thì k báo lỗi (compile code)
        // Level runtime sẽ lỗi
        driver.get(this.homePageUrl);
    }
    @Test
    public void TC_02_Url() {

    }

    @Test
    public void TC_03_Url() {

    }
}

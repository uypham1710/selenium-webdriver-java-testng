package testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_13_Dependencies {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {


        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }
    @Test
    public void Login_01_CreateNewUser() {

    }

    @Test(dependsOnMethods = "Login_01_CreateNewUser")
    public void Login_02_ViewUser() {

    }

    @Test(dependsOnMethods = "Login_01_CreateNewUser")
    public void Login_03_UpdateUser() {

    }

    @Test
    public void Login_04_MoveUser() {

    }
}

package javaTester;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_12_Driver_Info {
    WebDriver driver;

    @Test
    public void testDriverInformation(){
        driver = new FirefoxDriver();
        System.out.println(driver.toString());
        //FirefoxDriver: firefox on windows (1e672929-abce-4f1d-b252-c2b8386e802a)

        if (driver.toString().contains("firefox")){
            //Scroll
        }
        driver.quit();

    }
}

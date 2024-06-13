package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Topic_10_Custom_Dropdown {
    WebDriver driver;
    // Tường minh: trạng thái cụ thể cho element
    // visible/Invisible/Presence/ Number/ Clickable
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_Jquery() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        selectItemInDropdown("span#speed-button", "ul#speed-menu div", "Slower");
        sleepInSeconds(2);
        selectItemInDropdown("span#files-button", "ul#files-menu div", "Some unknown file");
        sleepInSeconds(2);
        selectItemInDropdown("span#number-button", "ul#number-menu div", "14");
        sleepInSeconds(2);
        selectItemInDropdown("span#salutation-button", "ul#salutation-menu div", "Prof.");
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Slower");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button>span.ui-selectmenu-text")).getText(), "Some unknown file");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "14");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Prof.");

    }

    @Test
    public void TC_02_React() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectItemInDropdown("div.dropdown", "span.text", "Stevie Feliciano");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(),"Stevie Feliciano");
        sleepInSeconds(2);

        selectItemInDropdown("div.dropdown", "span.text", "Matt");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(),"Matt");
        sleepInSeconds(2);

        selectItemInDropdown("div.dropdown", "span.text", "Elliot Fu");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(),"Elliot Fu");
        sleepInSeconds(2);
    }

    @Test
    public void TC_03_VueJS() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"Second Option");
        sleepInSeconds(1);

        selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Third Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"Third Option");
        sleepInSeconds(1);
    }

    @Test
    public void TC_04_Editable() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        selectInputItemInDropdown("input.search", "span.text", "Argentina");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Argentina");
        sleepInSeconds(1);

        selectInputItemInDropdown("input.search", "span.text", "Bangladesh");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Bangladesh");
        sleepInSeconds(1);
    }

    @Test
    public void TC_05_Nocommerce() {
        driver.get("https://demo.nopcommerce.com/register");
        selectItemInDropdown("select[name='DateOfBirthDay']", "select[name='DateOfBirthDay']>option", "17");
        Assert.assertTrue(driver.findElement(By.cssSelector("select.valid>option[value='17']")).isSelected());
        sleepInSeconds(1);

        selectItemInDropdown("select[name='DateOfBirthMonth']", "select[name='DateOfBirthMonth']>option", "October");
        Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']>option[value='10']")).isSelected());
        sleepInSeconds(1);

        selectItemInDropdown("select[name='DateOfBirthYear']", "select[name='DateOfBirthYear']>option", "1993");
        Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']>option[value='1993']")).isSelected());
        sleepInSeconds(1);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getEmailAddress() {
        Random rand = new Random();
        return "uy.pham" + rand.nextInt(99999) + "@gmail.com";
    }
    public void selectItemInDropdown(String parentCSs, String childCss, String itemTextExpected){
        driver.findElement(By.cssSelector(parentCSs)).click();
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCss)));
        for (WebElement item : allItems){
            if (item.getText().equals(itemTextExpected)){
                item.click();
                break;
            }
        }
    }

    public void selectInputItemInDropdown(String parentCSs, String childCss, String itemTextExpected){
        driver.findElement(By.cssSelector(parentCSs)).clear();
        driver.findElement(By.cssSelector(parentCSs)).sendKeys(itemTextExpected);
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCss)));
        for (WebElement item : allItems){
            if (item.getText().equals(itemTextExpected)){
                item.click();
                break;
            }
        }
    }
}
package webdriver;

import com.beust.ah.A;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Topic_14_Actions {
    WebDriver driver;
    Actions actions;

    JavascriptExecutor javascriptExecutor;


    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        javascriptExecutor = (JavascriptExecutor) driver;

    }

    @Test
    public void TC_01_Tooltips() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        WebElement ageTextbox = driver.findElement(By.cssSelector("input#age"));
        actions.moveToElement(ageTextbox).perform();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),
                "We ask for your age only for statistical purposes.");

    }

    @Test
    public void TC_02_Hover_Menu() {
        driver.get("https://www.myntra.com/");
        WebElement kids = driver.findElement(By.xpath("//a[@class='desktop-main' and @data-group='kids']"));
        actions.moveToElement(kids).perform();
        sleepInSeconds(2);
        actions.click(driver.findElement(By.xpath("//a[text()='Home & Bath']"))).perform();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.cssSelector(".title-title")).getText(),"Kids Home Bath");

    }

    @Test
    public void TC_03_Fahasa_Hover_Menu() {
        driver.get("https://www.fahasa.com/");
        WebElement icon = driver.findElement(By.cssSelector("span.icon_menu"));
        actions.moveToElement(icon).perform();
        sleepInSeconds(2);
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Sách Trong Nước']")).isDisplayed());
        actions.moveToElement(driver.findElement(By.xpath("//a[@title='Bách Hóa Online - Lưu Niệm']"))).perform();
        driver.findElement(By.xpath(
                "//div[contains(@class,'fhs_menu_content')]//a[text()='Thiết Bị Số - Phụ Kiện Số']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//ol[@class='breadcrumb']//strong[text()='Thiết Bị Số - Phụ Kiện Số']")).isDisplayed());
    }

    @Test
    public void TC_04_Click_And_Hold(){
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allNumbers = driver.findElements(By.cssSelector("li.ui-state-default"));
        Assert.assertEquals(allNumbers.size(),20);
        // chọn từ 1-15
        actions.clickAndHold(allNumbers.get(0)).pause(1000).moveToElement(allNumbers.get(14)).pause(1000).release().perform();
        sleepInSeconds(2);
        List<String> allNumberTextExpected = new ArrayList<String>();
        allNumberTextExpected.add("1");
        allNumberTextExpected.add("2");
        allNumberTextExpected.add("3");
        allNumberTextExpected.add("5");
        allNumberTextExpected.add("6");
        allNumberTextExpected.add("7");
        allNumberTextExpected.add("9");
        allNumberTextExpected.add("10");
        allNumberTextExpected.add("11");
        allNumberTextExpected.add("13");
        allNumberTextExpected.add("14");
        allNumberTextExpected.add("15");

        List<WebElement> allSelected = driver.findElements(By.cssSelector("li.ui-selected"));
        Assert.assertEquals(allSelected.size(), 12);

        List<String> allNumberTextActual = new ArrayList<String>();
        for (WebElement element : allSelected){
            allNumberTextActual.add(element.getText());
        }
        Assert.assertEquals(allNumberTextExpected, allNumberTextActual);
    }

    @Test
    public void TC_05_Click_And_Hold(){
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allNumbers = driver.findElements(By.cssSelector("li.ui-state-default"));
        Assert.assertEquals(allNumbers.size(),20);
        actions.clickAndHold(allNumbers.get(0)).moveToElement(allNumbers.get(11)).release().perform();
        actions.keyDown(Keys.CONTROL).perform();
        actions.clickAndHold(allNumbers.get(12)).moveToElement(allNumbers.get(14)).release().perform();
        actions.keyUp(Keys.CONTROL);
        sleepInSeconds(2);

    }

    @Test
    public void TC_06_Click_Random(){
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allNumbers = driver.findElements(By.cssSelector("li.ui-state-default"));
        actions.keyDown(Keys.CONTROL);
        actions.click(allNumbers.get(0)).
                click(allNumbers.get(2)).
                click(allNumbers.get(5)).
                click(allNumbers.get(10)).perform();
        actions.keyUp(Keys.CONTROL);
        List<WebElement> allSelected = driver.findElements(By.cssSelector("li.ui-selected"));
        Assert.assertEquals(allSelected.size(),4);
    }

    @Test
    public void TC_07_Double_Click(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement doubleClick = driver.findElement(By.xpath("//button[text()='Double click me']"));
        if (driver.toString().contains("firefox")){
            javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);",doubleClick);
            sleepInSeconds(2);
        }
        actions.doubleClick(doubleClick).perform();
        sleepInSeconds(1);
        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(),"Hello Automation Guys!");

    }
    @Test
    public void TC_08_Right_Click() {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        WebElement rightButton = driver.findElement(By.cssSelector("span.context-menu-one"));
        actions.contextClick(rightButton).perform();
        WebElement quitButton = driver.findElement(By.cssSelector("li.context-menu-icon-quit"));

        Assert.assertTrue(quitButton.isDisplayed());

        actions.moveToElement(quitButton).perform();
        sleepInSeconds(1);

        Assert.assertTrue(driver.findElement(By.cssSelector(
                "li.context-menu-icon-quit.context-menu-hover.context-menu-visible")).isDisplayed());
        actions.click(quitButton).perform();
        sleepInSeconds(1);
        driver.switchTo().alert().accept();
        sleepInSeconds(1);

        Assert.assertFalse(quitButton.isDisplayed());

    }

    @Test
    public void TC_09_DragAndHover() {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        WebElement smallCircle = driver.findElement(By.cssSelector("div#draggable"));
        WebElement bigCircle = driver.findElement(By.cssSelector("div#droptarget"));
        actions.dragAndDrop(smallCircle,bigCircle).perform();
        sleepInSeconds(1);

        Assert.assertEquals(bigCircle.getText(),"You did great!");
        Assert.assertEquals(Color.fromString(bigCircle.getCssValue("background-color")).asHex(),"#03a9f4");


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
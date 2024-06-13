package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_20_JavascriptExecutor {
    WebDriver driver;
    JavascriptExecutor jsExecutor;
    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        // ép kiểu tường minh
        // từ kiểu dữ liệu này sang kiểu khác
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_LivePanda() {
        navigateToUrlByJS("http://live.techpanda.org/");
        String pandaDomain = (String) executeForBrowser("return document.domain");
        Assert.assertEquals(pandaDomain ,"live.techpanda.org");

        Assert.assertEquals((String) executeForBrowser("return document.URL") ,"http://live.techpanda.org/");

        hightlightElement("//a[text()='Mobile']");
        clickToElementByJS("//a[text()='Mobile']");
        sleepInSeconds(2);

        hightlightElement("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button[@title='Add to Cart']");
        clickToElementByJS("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button[@title='Add to Cart']");

        isExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart.");

        hightlightElement("//a[text()='Customer Service']");
        clickToElementByJS("//a[text()='Customer Service']");

        Assert.assertEquals((String) executeForBrowser("return document.title") ,"Customer Service");

        scrollToElementOnDown("//input[@id='newsletter']");
        hightlightElement("//input[@id='newsletter']");
        sendkeyToElementByJS("//input[@id='newsletter']","uy@gmail.com");

        hightlightElement("//button[@title='Subscribe']");
        clickToElementByJS("//button[@title='Subscribe']");

        isExpectedTextInInnerText("Thank you for your subscription.");

        navigateToUrlByJS("https://www.facebook.com/");
        Assert.assertEquals((String) executeForBrowser("return document.domain") ,"facebook.com");

    }

    @Test
    public void TC_02_AutomationFC() {
        driver.get("https://automationfc.github.io/html5/index.html");
        driver.findElement(By.cssSelector("input.btn")).click();
        sleepInSeconds(1);
        Assert.assertEquals(getElementValidationMessage("//input[@id='fname']"),"Please fill out this field.");

        hightlightElement("//input[@id='fname']");
        sendkeyToElementByJS("//input[@id='fname']","uyuy");
        driver.findElement(By.cssSelector("input.btn")).click();
        sleepInSeconds(1);
        Assert.assertEquals(getElementValidationMessage("//input[@id='pass']"),"Please fill out this field.");

        hightlightElement("//input[@id='pass']");
        sendkeyToElementByJS("//input[@id='pass']","123456");
        driver.findElement(By.cssSelector("input.btn")).click();
        sleepInSeconds(1);
        Assert.assertEquals(getElementValidationMessage("//input[@id='em']"),"Please fill out this field.");

        hightlightElement("//input[@id='em']");
        sendkeyToElementByJS("//input[@id='em']","u@#!#");
        driver.findElement(By.cssSelector("input.btn")).click();
        sleepInSeconds(1);
        Assert.assertEquals(getElementValidationMessage("//input[@id='em']"),"Please enter an email address.");

        hightlightElement("//input[@id='em']");
        sendkeyToElementByJS("//input[@id='em']","uy@gmail.com");
        driver.findElement(By.cssSelector("input.btn")).click();
        sleepInSeconds(1);
        Assert.assertEquals(getElementValidationMessage("//select"),"Please select an item in the list.");

    }

    @Test
    public void TC_03_Create_Account() {
        navigateToUrlByJS("http://live.techpanda.org/");
        sleepInSeconds(3);

        hightlightElement("//div[@id='header-account']//a[@title='My Account']");
        clickToElementByJS("//div[@id='header-account']//a[@title='My Account']");

        //hightlightElement("//a[@title='Create an Account']");
        clickToElementByJS("//a[@title='Create an Account']");

        hightlightElement("//input[@id='firstname']");
        sendkeyToElementByJS("//input[@id='firstname']","automation");

        hightlightElement("//input[@id='middlename']");
        sendkeyToElementByJS("//input[@id='middlename']","fc");

        hightlightElement("//input[@id='lastname']");
        sendkeyToElementByJS("//input[@id='lastname']","testing");

        hightlightElement("//input[@id='email_address']");
        sendkeyToElementByJS("//input[@id='email_address']",getEmailAddress());

        hightlightElement("//input[@id='password']");
        sendkeyToElementByJS("//input[@id='password']","123456");

        hightlightElement("//input[@id='confirmation']");
        sendkeyToElementByJS("//input[@id='confirmation']","123456");

        hightlightElement("//button[@title='Register']");
        clickToElementByJS("//button[@title='Register']");

        isExpectedTextInInnerText("Thank you for registering with Main Website Store.");

        clickToElementByJS("//a[@title='Log Out']");

        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']")).isDisplayed());
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
    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
        sleepInSeconds(3);
    }

    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSeconds(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
        sleepInSeconds(3);
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }
}
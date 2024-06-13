package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Topic_12_Radio_Checkbox {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Telerik_Checkbox() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        By dualZone = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
        By rearSide = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input");


        checkToElement(dualZone);
        Assert.assertTrue(driver.findElement(dualZone).isSelected());

        checkToElement(rearSide);
        Assert.assertTrue(driver.findElement(rearSide).isSelected());

        uncheckToElement(dualZone);
        Assert.assertFalse(driver.findElement(dualZone).isSelected());
    }

    @Test
    public void TC_02_Telerik_Radio() {
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        By twoPetrol = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
        By diesel = By.xpath("//label[text()='1.6 Diesel, 77kW']/preceding-sibling::input");

        checkToElement(twoPetrol);
        Assert.assertTrue(driver.findElement(twoPetrol).isSelected());
        sleepInSeconds(2);

        checkToElement(diesel);
        Assert.assertTrue(driver.findElement(diesel).isSelected());
        Assert.assertFalse(driver.findElement(twoPetrol).isSelected());
    }

    @Test
    public void TC_03_Material_Radio() {
        driver.get("https://material.angular.io/components/radio/examples");
        By summerRadio = By.xpath("//input[@value='Summer']");
        checkToElement(summerRadio);
        Assert.assertTrue(driver.findElement(summerRadio).isSelected());

    }


    @Test
    public void TC_04_Material_Checkbox() {
        driver.get("https://material.angular.io/components/checkbox/examples");
        By checkedBox = By.xpath("//input[@id='mat-mdc-checkbox-1-input']");
        By indeterminateBox = By.xpath("//input[@id='mat-mdc-checkbox-2-input']");

        checkToElement(checkedBox);
        checkToElement(indeterminateBox);

        Assert.assertTrue(driver.findElement(checkedBox).isSelected());
        Assert.assertTrue(driver.findElement(indeterminateBox).isSelected());

        uncheckToElement(checkedBox);
        uncheckToElement(indeterminateBox);
        Assert.assertFalse(driver.findElement(checkedBox).isSelected());
        Assert.assertFalse(driver.findElement(indeterminateBox).isSelected());

    }

    @Test
    public void TC_05_All_Checkbox() {
        driver.get("https://automationfc.github.io/multiple-fields/");
        List<WebElement> allCheckboxes = driver.findElements(By.xpath("//input[@class='form-checkbox']"));
        for (WebElement checkbox : allCheckboxes){
            if(!checkbox.isSelected()) {
                checkbox.click();
            }
        }

        for (WebElement checkbox : allCheckboxes){
                Assert.assertTrue(checkbox.isSelected());
        }

        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        allCheckboxes = driver.findElements(By.xpath("//input[@class='form-checkbox']"));
        for(WebElement checkbox : allCheckboxes){
            if(checkbox.getAttribute("value").equals("Heart Attack") && !checkbox.isSelected()){
                checkbox.click();
                sleepInSeconds(1);
            }
        }

        for(WebElement checkbox : allCheckboxes){
            if(checkbox.getAttribute("value").equals("Heart Attack")){
                Assert.assertTrue(checkbox.isSelected());
            }else {
                Assert.assertFalse(checkbox.isSelected());
            }
        }
    }

    @Test
    public void TC_06_Google_Form() {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        By canThoRadio = By.xpath("//div[@aria-label='Cần Thơ']");
        By quangNamCheckbox = By.xpath("//div[@aria-label='Quảng Nam']");
        By quangBinhCheckbox = By.xpath("//div[@aria-label='Quảng Bình']");

        Assert.assertEquals(driver.findElement(canThoRadio).getAttribute("aria-checked"),"false");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='false']")).isDisplayed());

        driver.findElement(canThoRadio).click();
        sleepInSeconds(1);

        Assert.assertEquals(driver.findElement(canThoRadio).getAttribute("aria-checked"), "true");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='true']")).isDisplayed());

        driver.findElement(quangNamCheckbox).click();
        driver.findElement(quangBinhCheckbox).click();
        sleepInSeconds(1);

        Assert.assertEquals(driver.findElement(quangBinhCheckbox).getAttribute("aria-checked"),"true");
        Assert.assertEquals(driver.findElement(quangNamCheckbox).getAttribute("aria-checked"),"true");
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

    public void checkToElement(By byXpath){
        if (!driver.findElement(byXpath).isSelected()){
            driver.findElement(byXpath).click();
            sleepInSeconds(1);
        }
    }

    public void uncheckToElement(By byXpath){
        if (driver.findElement(byXpath).isSelected()){
            driver.findElement(byXpath).click();
            sleepInSeconds(1);
        }
    }
}
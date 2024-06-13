package testng;

import org.testng.annotations.*;

public class Topic_02_Annotation {
    @BeforeClass
    public void beforeClass(){
        System.out.println("Before class");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("After class");
    }

    @BeforeGroups
    public void beforeGroups(){
        System.out.println("Before groups");
    }

    @AfterGroups
    public void afterGroup(){
        System.out.println("After groups");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("Before Method");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("After method");
    }

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("Before suite");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("After suite");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("Before test");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("After Test");
    }

    @Test
    public void test01(){
        System.out.println("Testcase 01");
    }
    @Test
    public void test02(){
        System.out.println("Testcase 02");
    }
}

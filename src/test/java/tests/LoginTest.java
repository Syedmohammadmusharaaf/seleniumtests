package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import pages.LoginPage;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.Log;

import java.io.IOException;

public class LoginTest extends BaseTest {
    private LoginPage loginPage ;


    @DataProvider(name = "LoginData")
    public Object[][] getData() throws IOException {
        String filePath = System.getProperty("user.dir")+"/testdata/tdd.xlsx";
        ExcelUtils.loadExcel(filePath,"Sheet1");
        int totalRows = ExcelUtils.getTotalRows();
        Object[][] data = new Object[totalRows-1][2];

        for (int i=1;i<totalRows;i++){
            data[i-1][0]=ExcelUtils.getCellValue(i,0);//username
            data[i-1][1]=ExcelUtils.getCellValue(i,1);//password
        }

        return data;


    }


//    @Test(dataProvider = "LoginData")
    @Test
//    @Parameters({"username","password"})
//    public void testWithValidCredentials(String userName,String password){
    public void testWithValidCredentials(){

        loginPage=new LoginPage(driver);
        test = ExtentReportManager.createTest("Login test");
        Log.info("user entering the credentials");
        test.info("user entering the credentails");
        loginPage.setEmail("admin@yourstore.com");
        loginPage.setPassword("admin");
//        loginPage.setEmail(userName);
//        loginPage.setPassword(password);
        loginPage.clickOnSignInButton();
        test.info("user clicks on submit button");
        Assert.assertEquals(driver.getTitle(),"Just a moment...");
        test.pass("test case passed");


    }

//    @Test
//    public void testWithInValidCredentials(){
//
//        loginPage=new LoginPage(driver);
//        test = ExtentReportManager.createTest("Login test with Invalid credentials");
//        Log.info("user entering the credentials");
//        test.info("user entering the credentails");
//        loginPage.setEmail("admin@yourstore.com");
//        loginPage.setPassword("admin@1234");
//        loginPage.clickOnSignInButton();
//        test.info("user clicks on submit button");
//        Assert.assertEquals(driver.getTitle(),"nopCommerce demo store");
//        test.pass("test case passed");
//
//
//    }


}

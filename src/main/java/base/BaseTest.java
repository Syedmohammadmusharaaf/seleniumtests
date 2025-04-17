package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.EmailUtil;
import utils.ExtentReportManager;
import utils.Log;

import java.io.File;

public class BaseTest {

    protected WebDriver driver;
    protected ExtentReports report;
    protected ExtentTest test;


    @BeforeSuite
    public void setUpReportInstance() {
        report = ExtentReportManager.getReportInstance();
    }

    @AfterSuite
    public void tearDownReport() {
        report.flush();
        String reportPath = ExtentReportManager.reportPath;
        EmailUtil.sendReports(reportPath);

    }

    @BeforeMethod
    public void setUp() {
        Log.info("initilaizing with chrome driver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        Log.info("navigating to the url");
        driver.get("https://admin-demo.nopcommerce.com/login");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ExtentReportManager.captureScreenshot(driver, "Login Test");
            try {

                test.fail("Test Failed.. Check Screenshot",
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

            } catch (Exception e) {
                e.printStackTrace();
            }
            if (driver != null) {
                Log.info("quiting the driver");
                driver.quit();

            }
        }

    }

}
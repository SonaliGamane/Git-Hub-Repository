package Listeners;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
 
public class newtestngclass {
 public WebDriver driver;
 public ExtentHtmlReporter htmlReporter;
 public ExtentReports extent;
 public ExtentTest logger;
 
 @BeforeTest
 public void startReport() {
 htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/STMExtentReport.html");
        	// Create an object of Extent Reports
 extent = new ExtentReports();  
 extent.attachReporter(htmlReporter);
 extent.setSystemInfo("Host Name", "SoftwareTestingMaterial");
        	extent.setSystemInfo("Environment", "Production");
 extent.setSystemInfo("User Name", "Sonali");
 htmlReporter.config().setDocumentTitle("Title of the Report Comes here "); 
                // Name of the report
 htmlReporter.config().setReportName("Name of the Report Comes here "); 
                // Dark Theme
 htmlReporter.config().setTheme(Theme.STANDARD); 
 }
 
 //This method is to capture the screenshot and return the path of the screenshot.
 public static String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
 String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
 TakesScreenshot ts = (TakesScreenshot) driver;
 File source = ts.getScreenshotAs(OutputType.FILE);
 // after execution, you could see a folder "FailedTestsScreenshots" under src folder
 String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + "new1.png";
 File finalDestination = new File(destination);
 FileUtils.copyFile(source, finalDestination);
 return destination;
 }
 
 @BeforeMethod
 public void setup() {
 System.setProperty("webdriver.chrome.driver","C:\\Users\\gamanesonali.sukha\\Desktop\\chromedriver.exe");
 driver = new ChromeDriver();
 driver.manage().window().maximize();
 driver.get("https://www.google.com/");
 }
 
 @Test
 public void verifyTitle() {
 logger = extent.createTest("To verify Google Title");
 Assert.assertEquals(driver.getTitle(),"Google");
 }
 @AfterMethod
 public void getResult(ITestResult result) throws Exception{
 if(result.getStatus() == ITestResult.FAILURE){
  logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
 logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
  String screenshotPath = getScreenShot(driver, result.getName());
  logger.fail("Test Case Failed Snapshot is below " + logger.addScreenCaptureFromPath(screenshotPath));
 }
 else if(result.getStatus() == ITestResult.SKIP){
 logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE)); 
 } 
 else if(result.getStatus() == ITestResult.SUCCESS)
 {
 logger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
 }
 driver.quit();
 }
 
 @AfterTest
 public void endReport() {
 extent.flush();
 }
}
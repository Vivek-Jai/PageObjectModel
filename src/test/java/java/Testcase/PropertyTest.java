package java.Testcase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Base.Base1;
import Pages.SearchProperty;
import Utils.TestUtils;

public class PropertyTest extends Base1{
	
	SearchProperty property;
	public ExtentReports extent;
	public ExtentTest extentTest;
	
	public PropertyTest()
	{
		super();
		
		
	}
	
	@BeforeTest
	public void setExtent(){
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html", true);
		extent.addSystemInfo("Host Name", "Vivek Windows");
		extent.addSystemInfo("User Name", "Vivek Automation");
		extent.addSystemInfo("Environment", "QA");
	}
	
	@AfterTest
	public void endReport(){
		extent.flush();
		extent.close();
	}
	
	@BeforeMethod
	public void setup()
	{
		
		browserStart();
		property = new SearchProperty();
		
	}
	
	@Test(enabled = false)
	public void searchCounty(){
		
		property.search();
		
		}
	@Test(priority=1)
	public  void sortpriceDesending()
	{
		extentTest = extent.startTest("sortpriceDesending");
		property.search();
		
		ArrayList<String> cost=property.propertyPrice();
		
		Collections.sort(cost, Collections.reverseOrder());
		
		System.out.println(cost);
		
		
	}
	@Test (priority=2)
	public void verifyfifthproperty()
	{
		extentTest = extent.startTest("verifyfifthproperty");
		property.search();
		String actualtext= property.fifthProperty();
		String expect=property.AgentName.getText();
		Assert.assertEquals(actualtext, expect);
		System.out.println("agentname verified");
	
	}
	
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException{
		
		if(result.getStatus()==ITestResult.FAILURE){
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getName()); //to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getThrowable()); //to add error/exception in extent report
			
			String screenshotPath = TestUtils.getScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); //to add screenshot in extent report
			//extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath)); //to add screencast/video in extent report
		}
		else if(result.getStatus()==ITestResult.SKIP){
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS){
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());

		}
		
		
		extent.endTest(extentTest); //ending test and ends the current test and prepare to create html report
		driver.quit();

}
	
	

}

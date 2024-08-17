package prematest.config;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import prematest.resources.ExtentReporterNG;

public class Listeners extends basetest implements ITestListener {

	ExtentTest test;

	ExtentReports extent = ExtentReporterNG.getReportObject();
	
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	

	@Override
	public void onTestStart(ITestResult result) {

		test = extent.createTest(result.getMethod().getMethodName());
		
		extentTest.set(test);

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Case Passed");

	}

	@Override
	public void onTestFailure(ITestResult result) {

		extentTest.get().fail(result.getThrowable());

		String filepath = null;

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			filepath = Screenshot((result.getMethod().getMethodName()), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		extentTest.get().addScreenCaptureFromPath(filepath, (result.getMethod().getMethodName()));

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.get().log(Status.SKIP, "Test Case Skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();

	}

}

package com.assignment_NewsLetter;




import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class LoadURLTest 
{
	WebDriver driver;
	
	
	//Report classes initialization
	ExtentSparkReporter htmlReporter;
	ExtentReports reports;
	ExtentTest test;
	
  
//  
//  
  @BeforeTest
  public void startReport()
	{
		htmlReporter = new ExtentSparkReporter("ExtentReports\\ExtRprtOfURL_Loads_Successfully.html");
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
		//test = new ExtentTest("My Test");
				
		
		//Environment details
		reports.setSystemInfo("Machine", "pc1");
		reports.setSystemInfo("OS", "Windows 10 pro");
		reports.setSystemInfo("Browser", "Chrome");
		reports.setSystemInfo("Tool", "Eclipse IDE");
		reports.setSystemInfo("Build Management tool", "Maven");
		reports.setSystemInfo("Testing framework", "TestNG");
		
		
		
		htmlReporter.config().setDocumentTitle("Extent Report");
		htmlReporter.config().setReportName("Test Report");
			
		
	}
  
  
  @Test
  public void VerifyURLLods()
  {
	  test = reports.createTest("Open URL");
	  
	  driver.findElement(By.xpath("//div[@class='cmplz-close']")).click();

      // Create a WebDriver object
      driver = new ChromeDriver();

      //maximize window
      driver.manage().window().maximize();
      
      // Open the URL
      driver.get("https://www.ve3.global/news/");
      
      //add wait
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
  }
  
  
  
  
  @AfterMethod
  public void getTestResult(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAIL ", ExtentColor.RED));
		}
		else if(result.getStatus()==ITestResult.SUCCESS)
		{
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASS ", ExtentColor.GREEN));
			
		}
		else if(result.getStatus()==ITestResult.SKIP)
		{
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIP ", ExtentColor.GREY));
		}
	}
  
  
  //Mandatory to write reports
  @AfterTest
  public void flushReport()
  {
	  reports.flush();
  }
  
  
  @AfterClass
  public void tearDown()
  {
	  driver.quit();
  }
  
}

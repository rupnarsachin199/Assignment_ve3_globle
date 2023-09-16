package com.assignment_NewsLetter;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class NameFieldVlidation 
{
	WebDriver driver;
	
	WebDriverWait wait;
	
	//Report classes initialization
			ExtentSparkReporter htmlReporter;
			ExtentReports reports;
			ExtentTest test;
			
		  
		//  
		//  
		  @BeforeTest
		  public void startReport()
			{
				htmlReporter = new ExtentSparkReporter("ExtentReports\\ExtRprtOfURL_NewsletterFormTestCaseFour.html");
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
				reports.setSystemInfo("NewsletterFormTesting", "Through the Data Drivern testing Framework");
				
				
				
				htmlReporter.config().setDocumentTitle("Extent Report");
				htmlReporter.config().setReportName("Test Report");
					
				
			}
	
	
	
	
	
	//test
  @Test
  public void verifyNameWithNumber() throws Exception 
  {
	  driver= new ChromeDriver();
	  wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	  driver.get("https://www.ve3.global/news/");
	  
	  //maximize the window
	  driver.manage().window().maximize();
	  
	  //add wait
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	  
	  
	  
	  //create a test
	  test = reports.createTest("Validate name with numbers");
	  
	  driver.findElement(By.xpath("//div[@class='cmplz-close']")).click();
	  
	  WebElement nameField = driver.findElement(By.name("your-name"));
      nameField.sendKeys(String.valueOf(1234));

      WebElement emailField = driver.findElement(By.name("your-email"));
      emailField.sendKeys("abc11@gmail.com");
      
      try
      {
    	  WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit']"));
          submitButton.click();
      }catch (ElementClickInterceptedException e) 
      {
    	  WebElement submitButton1 = driver.findElement(By.xpath("//input[@type='submit']"));
          submitButton1.click();
	}
      
      
      	
      
      WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='wpcf7-response-output']")));

      System.out.println(element.getText());
      
      
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

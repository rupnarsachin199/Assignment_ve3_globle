package com.assignment_NewsLetter;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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

public class ElementsDisplay 
{
	WebDriver driver;
	
	
	//Report classes initialization
		ExtentSparkReporter htmlReporter;
		ExtentReports reports;
		ExtentTest test;
	
	
	@BeforeTest
	  public void startReport()
		{
			htmlReporter = new ExtentSparkReporter("ExtentReports\\ExtRprtOfElementsPresent.html");
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
			reports.setSystemInfo("All elements are present?", "Yes");
			
			
			htmlReporter.config().setDocumentTitle("Extent Report");
			htmlReporter.config().setReportName("Test Report");
				
			
		}
	
	
  @Test
  public void verifyElementsPresent() 
  {
	  
	  test = reports.createTest("Check all elements are displayed");
	  
	  driver.findElement(By.xpath("//div[@class='cmplz-close']")).click();

      // Create a WebDriver object
      driver = new ChromeDriver();

      //maximize window
      driver.manage().window().maximize();
      
      // Open the URL
      driver.get("https://www.ve3.global/news/");
      
      //add wait
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	  
	  
	  
	  WebElement logo = driver.findElement(By.xpath("//img[@class='attachment-full size-full wp-image-12613 "
				+ "cf-image-auto-resize']"));
		WebElement services = driver.findElement(By.xpath("//span[normalize-space()='Services']"));
		WebElement experties = driver.findElement(By.xpath("//*[@id=\"menu-item-3575\"]/a"));
		WebElement platforms = driver.findElement(By.xpath("//span[normalize-space()='Platforms']"));
		WebElement industries = driver.findElement(By.xpath("//span[normalize-space()='Industries']"));
		WebElement insights = driver.findElement(By.xpath("//span[normalize-space()='Insights']"));
		WebElement about = driver.findElement(By.xpath("//li[@id='menu-item-29089']//a[@class='wpr-menu-item"
				+ " wpr-pointer-item']"));
		WebElement contact = driver.findElement(By.xpath("//span[normalize-space()='Contact']"));
		WebElement newsHeading = driver.findElement(By.xpath("//h1"));
		WebElement knowMoreBtn = driver.findElement(By.xpath("//span[normalize-space()='Know More']"));
		WebElement paymentsassociationBtn = driver.findElement(By.xpath("//div[@class='elementor-column "
				+ "elementor-col-33 elementor-inner-column elementor-element elementor-element-49de4a9']"
				+ "//div[@class='elementor-widget-wrap elementor-element-populated']"));
		WebElement viewMoreBtn = driver.findElement(By.xpath("//a[@class='elementor-button elementor-button-link "
				+ "elementor-size-md elementor-animation-wobble-vertical']"));
		WebElement digitalInsiderForm = driver.findElement(By.xpath("//div[@class='elementor-column elementor-col-50"
				+ " elementor-top-column elementor-element elementor-element-ce51bb0']"));
		WebElement footerLinks = driver.findElement(By.xpath("//ul[@id='menu-1-cf228af']"));
		WebElement footerSocialMediaIcons = driver.findElement(By.xpath("(//div[@class='elementor-social-icons-wrapper "
				+ "elementor-grid'])[2]"));
		
		
		logo.isDisplayed();
		services.isDisplayed();
		experties.isDisplayed();
		platforms.isDisplayed();
		industries.isDisplayed();
		insights.isDisplayed();
		about.isDisplayed();
		contact.isDisplayed();
		newsHeading.isDisplayed();
		knowMoreBtn.isDisplayed();
		paymentsassociationBtn.isDisplayed();
		viewMoreBtn.isDisplayed();
		digitalInsiderForm.isDisplayed();
		footerLinks.isDisplayed();
		footerSocialMediaIcons.isDisplayed();

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

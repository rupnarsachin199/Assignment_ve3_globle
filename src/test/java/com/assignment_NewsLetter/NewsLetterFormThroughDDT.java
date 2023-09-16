package com.assignment_NewsLetter;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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


import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class NewsLetterFormThroughDDT 
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
			htmlReporter = new ExtentSparkReporter("ExtentReports\\ExtRprtOfURL_NewsletterForm1.html");
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
	
  @Test
  public void verifyNewsletterFormThroughtDDt() throws IOException 
  {
	  test = reports.createTest("Open URL");
	  

	// Read the test data from the Excel file

      FileInputStream fis = new FileInputStream("C:\\Users\\admin\\eclipse-workspace\\Assignment_Ve3.global\\TestData\\Data.xlsx");
      @SuppressWarnings("resource")
	XSSFWorkbook workbook = new XSSFWorkbook(fis);

      XSSFSheet sheet = workbook.getSheetAt(0);

      int rowCount = sheet.getLastRowNum();

      // Iterate over the rows in the Excel file and execute the test cases

      for (int i = 0; i <= rowCount; i++) {

          Row row = sheet.getRow(i);

          // Get the name and email from the Excel file

          String name = row.getCell(0).getStringCellValue();
          String email = row.getCell(1).getStringCellValue();

          
          
          

          // Navigate to the newsletter form

          driver= new ChromeDriver();
          driver.manage().window().maximize();
          driver.get("https://www.ve3.global/news/");
          driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
          
          
          //close cookies notification
    	  driver.findElement(By.xpath("//div[@class='cmplz-close']")).click();
          // Fill in the form


          WebElement nameField = driver.findElement(By.name("your-name"));
          nameField.sendKeys(name);

          WebElement emailField = driver.findElement(By.name("your-email"));
          emailField.sendKeys(email);

          // Submit the form

          try {
        	  WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit']"));
              submitButton.click();
			
		} 
          catch (ElementClickInterceptedException e) 
          {
        	  WebElement submitButton1 = driver.findElement(By.xpath("//input[@type='submit']"));
              submitButton1.click();
		}

  
}
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

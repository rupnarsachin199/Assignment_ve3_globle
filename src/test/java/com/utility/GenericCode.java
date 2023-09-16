package com.utility;


import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GenericCode 
{
	WebDriver driver;
	
	//screenshot
	public static void takeScreenshotOfElement( WebElement element, String fileName) throws Exception {
        // Cast the element to a TakesScreenshot.
        TakesScreenshot screenshotTaker = (TakesScreenshot) element;

        // Take a screenshot of the element and save it to the specified file.
        File screenshotFile = screenshotTaker.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File(fileName));
    }

}

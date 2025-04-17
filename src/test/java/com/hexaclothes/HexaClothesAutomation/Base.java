package com.hexaclothes.HexaClothesAutomation;//declares package name
//importing java utility classes
import java.io.File;//handle file operations(e.g,saving screenshots)
import java.io.IOException;//handles input and output errors
import java.nio.file.Files;//provides methods for file copying and manipulation
import java.text.SimpleDateFormat;//helps format dates(used for naming screenshots with timestamps)
import java.util.Date;//represents current date and time
//importing selenium webdriver components
import org.openqa.selenium.OutputType;//define the format in which screenshots are saved
import org.openqa.selenium.TakesScreenshot;//interface for capturing screenshot
import org.openqa.selenium.WebDriver;//main interface to control a browser
import org.openqa.selenium.chrome.ChromeDriver;//specific driver for chrome
import org.openqa.selenium.edge.EdgeDriver;//specific driver for edge
import org.openqa.selenium.firefox.FirefoxDriver;//Specific driver for firefox

import org.testng.ITestResult;//provides test result status(used for checking test pass/fail)
import org.testng.annotations.AfterClass;//runs after all test methods in the class
import org.testng.annotations.AfterMethod;//runs after each test method
import org.testng.annotations.BeforeClass;//runs before all test methods in the class
import org.testng.annotations.Optional;//specifies a default value for the parameter if not provided in tesng.xml
import org.testng.annotations.Parameters;//allows passing parameters from testng.xml


public class Base {
	
	WebDriver driver;//declaring webdriver instance driver globally
	
	@BeforeClass//runs before any test cases are executed
	@Parameters("browser") // Fetches the browser parameter from testng.xml
	public void initializeBrowser(@Optional("chrome")String browser){//@Optional("chrome")-if no browser is provided in testng.xml,chrome is used by default
		//creating webdriver instance based on browser selection
		if (browser.equalsIgnoreCase("chrome"))//equalsignoreCase-compares strings case-insensitively(so chrome and Chrome both work)
		{ 
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("edge"))
		{ 
			driver = new EdgeDriver();
		}
		else
		{ 
			throw new IllegalArgumentException("Invalid browser: "+ browser);//throws an error if invalid browser name is provided.IllegalArgumentException in Java is an unchecked exception that occurs when an illegal or inappropriate argument is passed to a method.
		}
		driver.manage().window().maximize();//maximize the browser window to ensure elements are visible.
		
		driver.get("https://hexaclothes.netlify.app/");//launches url in selected browse.
	}
	@AfterMethod//runs after every test case execution
    public void takeScreenshotOnFailure(ITestResult result) {//ITestResult result-stores the status of executed test.ITestResult is a testNG interface that gives information about the test execution(whether it passed,failed,skipped,etc).result - variable name.
        //take screenshot on test failure
		if (ITestResult.FAILURE == result.getStatus()) {//checking if test failed.ITestResult-interface;FAILURE-Constant
            
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());// Get timestamp for unique screenshot name
            
            TakesScreenshot ts = (TakesScreenshot) driver;// Capture screenshot
            File source = ts.getScreenshotAs(OutputType.FILE);//outputType.FILE-> stores screenshot as file
            
            File destination = new File("Screenshots/" + result.getName() + "_" + timestamp + ".png");// define destination path & Set dynamic file path (avoids overwriting).result.getName()->retrieves name of failed test method
            
            try {
                 Files.copy(source.toPath(), destination.toPath());//copies screeshot from source to destination.
                 System.out.println("Screenshot saved at: " + destination.getAbsolutePath());
            } catch (IOException e) {//handles errors if file cannot be copied
                    e.printStackTrace();//method to print detailed error message when an exception occurs.
            }
                         
         }
    }
	@AfterClass//runs after all tests in class are completed
	public void tearDown() {
		if (driver != null)//checks if driver is not null before quitting the browser
		{
			driver.quit();//closes all browser windows and terminates webdriver session
		}
	}

}

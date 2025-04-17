package com.hexaclothes.HexaClothesAutomation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Login extends Base {
	
	@BeforeMethod
	public void clickOnLoginButtonToOpenLoginPage() { 
		driver.findElement(By.xpath("//button[text()= 'Login']")).click();// Click on the login button to open the login page
		}
	
	public  boolean isLoginSuccessful() {// method to check if login was successful
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			WebElement homePageIdentifier = driver.findElement(By.xpath("//h1[text()='Testimonials']"));// an element that ensures we landed on the homepage
			try {
				wait.until(ExpectedConditions.visibilityOf(homePageIdentifier));
				return true;
			} catch (TimeoutException e) {
				return false;
			}
		}
	
	@Test
	public void validUsernameAndValidPassword() { 
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("abcde@gmail.com");//valid username
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("pass123");//valid password
		driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();// Click login button
		
		Assert.assertTrue(isLoginSuccessful(),"Test Failed: Homepage did not load after login.");// Check that login is successful.If login fails, the test will fail and show this message. 
		System.out.println("Test Passed: Successfully landed on homepage.");// Print a message confirming that login was successful and the homepage is displayed.
	}
		@Test
		public void invalidEmailAndValidPassword() {
			
		    driver.findElement(By.xpath("//input[@id='email']")).sendKeys("wrong@gmail.com");
		    driver.findElement(By.xpath("//input[@id='password']")).sendKeys("pass123");
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		   
		    Assert.assertFalse(isLoginSuccessful(),"Test Failed:Login should have failed,but hompage loaded.");// Verify that login did not succeed.If login is successful by mistake, the test will fail and show this message.  
		    System.out.println("Test Passed:Invalid credentials prevented login.");// Display a message confirming that login was correctly blocked.
		}
		@Test
		public void validEmailAndInvalidPassword() {
			
		    driver.findElement(By.xpath("//input[@id='email']")).sendKeys("abcde@gmail.com");
		    driver.findElement(By.xpath("//input[@id='password']")).sendKeys("wrongpass");
		    driver.findElement(By.xpath("//button[@type='submit']")).click();

		     Assert.assertFalse(isLoginSuccessful(),"Test Failed:Login should have failed,but homepage loaded.");// Verify that login did not succeed.If login is successful by mistake, the test will fail and show this message.  
		     System.out.println("Test Passed: Invalid credentials prevented login.");// Display a message confirming that login was correctly blocked.
		}
		    @Test
			public void invalidEmailAndInvalidPassword() {
		    	
			    driver.findElement(By.xpath("//input[@id='email']")).sendKeys("wrong@gmail.com");
			    driver.findElement(By.xpath("//input[@id='password']")).sendKeys("wrongpass");
			    driver.findElement(By.xpath("//button[@type='submit']")).click();

			    Assert.assertFalse(isLoginSuccessful(),"Test Failed:Login should have failed,but homepage loaded.");// Verify that login did not succeed.If login is successful by mistake, the test will fail and show this message.  
			     System.out.println("Test Passed: Invalid credentials prevented login.");// Display a message confirming that login was correctly blocked. 
		    
		}
		@AfterMethod
		public void logoutIfLoggedin() { 
			 try {
			        WebElement logoutButton = driver.findElement(By.xpath("//button[text()='Logout']"));
			        if (logoutButton.isDisplayed()) {
			            logoutButton.click();
			             System.out.println("Logged out successfully.");
			        }
			    } catch (NoSuchElementException e) {
			        System.out.println("Logout button not found, already on login page.");
			    }
			
		}
		
	}



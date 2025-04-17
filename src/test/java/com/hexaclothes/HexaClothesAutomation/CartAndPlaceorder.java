package com.hexaclothes.HexaClothesAutomation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartAndPlaceorder extends Login {
	@Test
	
	public void loginWithValidCredentials() { 
				
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));// Creating an instance of WebDriverWait with a timeout of 5 seconds
		WebElement login = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()= 'Login']")));// Waiting until the "Login" button is clickable and storing it in a WebElement variable
		JavascriptExecutor js = (JavascriptExecutor) driver;// Creating an instance of JavascriptExecutor to execute JavaScript commands
	    js.executeScript("arguments[0].click();", login);// Using JavaScript to click on the "Login" button (useful if the button is not clickable in the usual way)
		
	    
	    
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("abcde@gmail.com");//valid username
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("pass123");//valid password
		driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();// Click login button
	}
	@Test
	public void addProductsToCartAndPlaceorder() { 
		
		driver.findElement(By.xpath("//a[@href='/women']")).click();//click Women's Wear
		driver.findElement(By.xpath("(//button[contains(@class, 'bg-orange-600') and contains(text(), 'Add to Cart')])[1]")).click();//locate 1 item from cart and click
		driver.findElement(By.xpath("(//button[contains(@class, 'bg-orange-600') and contains(text(), 'Add to Cart')])[2]")).click();//locate 1 item from cart and click
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement cartIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/cart']")));//wait until cart icon to be clickable
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cartIcon);//Tried clicking directly, but it didn't work due to element being out of view.Scrolling the cart icon into view before clicking
	    cartIcon.click();
		
	    driver.findElement(By.xpath("//button[text()='Proceed to Checkout']")).click();	//click proceed to checkout button	
		//fill shipping information
	    driver.findElement(By.xpath("//input[@placeholder=\"First Name\"]")).sendKeys("john");//shipping information-first name
		driver.findElement(By.xpath("//input[@placeholder=\"Last Name\"]")).sendKeys("Thomas");//shipping information-lastname
		driver.findElement(By.xpath("//input[@placeholder=\"Email Address\"]")).sendKeys("abc@gmail.com");//shipping information-email address
		driver.findElement(By.xpath("//input[@placeholder=\"Address\"]")).sendKeys("11161 camino ruiz");//shipping information-address
		driver.findElement(By.xpath("//input[@placeholder='City']")).sendKeys("Sandiego");//shipping information-city
		driver.findElement(By.xpath("//input[@placeholder='Postal Code']")).sendKeys("680682");//shipping information-postal code
		//select country
		WebElement dropdownElement =  driver.findElement(By.xpath("//select[contains(@class, 'border-gray-300')]"));//select country
		Select select = new Select(dropdownElement);
		select.selectByIndex(1);
		//fill payment information
		driver.findElement(By.xpath("//input[@placeholder='Card Number']")).sendKeys("123456789");//Payment Information-card number
		driver.findElement(By.xpath("//input[@placeholder='Cardholder Name']")).sendKeys("john");//Payment Information-cardholder number
		driver.findElement(By.xpath("//input[@placeholder='MM/YY']")).sendKeys("09/25");//Payment Information-mm/yy
		driver.findElement(By.xpath("//input[@placeholder='CVC']")).sendKeys("123");//Payment Information-cvc
		driver.findElement(By.xpath("//button[text()='Place Order']")).click();//
		
		WebElement homePageIdentifier = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[text()='Testimonials']")));

		Assert.assertFalse(homePageIdentifier.isDisplayed(),"Test Failed: Order placement should have failed because the homepage loaded.");
		System.out.println("Test Passed: Order placement successful");
	}

}

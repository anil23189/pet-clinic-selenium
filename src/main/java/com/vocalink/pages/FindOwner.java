package com.vocalink.pages;

import java.util.Random;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.vocalink.utility.UIdata;

public class FindOwner {
	public static Logger log = Logger.getLogger(FindOwner.class.getName());
	public static String FirstName;
	public static String LastName;
	
	
	public String GetName(String name){
		Random randomNumber = new Random(); 
		int number = randomNumber.nextInt(10000);
		return (name+number);
	}
	
	public boolean addowner(WebDriver driver, String firstname, String lastname) throws Exception {
			
		boolean isownerAdded = false;

	        log.info("Started data adding into owner form");	
			WebDriverWait wait = new WebDriverWait(driver, 70L);
			
			FirstName = GetName(firstname);
			LastName = GetName(lastname);
			
			
			//Clicking on find owners menu
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//A[@href='/owners/find']")));
			driver.findElement(By.xpath("//A[@href='/owners/find']")).click();
			log.info("Clicked on Find Owner menu");
			
			Thread.sleep(5000);
			
			//Clicking on Add button
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/owners/new']")));
			driver.findElement(By.xpath("//a[@href='/owners/new']")).click();
			log.info("Clicked on Add Owner Button");
			
			Thread.sleep(5000);
			
			//Entering data into first name field
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName")));
			driver.findElement(By.id("firstName")).sendKeys(FirstName);
			log.info("Owner first name entered");
			
			Thread.sleep(2000);
			
			//Entering data into last name field
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lastName")));
			driver.findElement(By.id("lastName")).sendKeys(LastName);
			log.info("Owner last name entered");
			
			Thread.sleep(2000);
			
			//Entering data into address field
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("address")));
			driver.findElement(By.id("address")).sendKeys("Address 0012 Test");
			log.info("Entered owner address");
			
			Thread.sleep(2000);
			
			//Entering data into city field
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("city")));
			driver.findElement(By.id("city")).sendKeys("London");
			log.info("Entered owner city");
			
			Thread.sleep(2000);
			
			//Entering data into telephone field
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("telephone")));
			driver.findElement(By.id("telephone")).sendKeys("950325698");
			log.info("Entered telephone number");
			
			Thread.sleep(3000);
			
			//Clicking on submit button
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			log.info("Clicked on submit button");
			
	        Thread.sleep(4000);
	        
	        //Navigating user to home page
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='home page']")));
			driver.findElement(By.xpath("//a[@title='home page']")).click();
			log.info("Navigated to Home page");
			
			Thread.sleep(4000);
			
			isownerAdded = true;
	
		return isownerAdded;
	}
	
	public boolean SearchUser (WebDriver driver) throws Exception
	{

       	WebDriverWait wait = new WebDriverWait(driver, 70L);
		
		//Clicking on find owners menu
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//A[@href='/owners/find']")));
		driver.findElement(By.xpath("//A[@href='/owners/find']")).click();
		log.info("Clicked on Find Owner menu");
		
		Thread.sleep(5000);
		
		//Entering Lastname in searchbox
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='lastName']")));		
		driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys(LastName);
		log.info("Entered LastName into searchBox");
				
		Thread.sleep(5000);
		//Clicking on submit button
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		log.info("Clicked on submit button");
		
        Thread.sleep(4000);
        
        //Verifying UserDetails
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[1]")));
		String ActualUserName = driver.findElement(By.xpath("//table[1]/tbody/tr[1]/td/b")).getText();
		String ExpectedUserName = FirstName.concat(" ").concat(LastName);
		log.info("Verifying user details"); 
		if(ActualUserName.equals(ExpectedUserName))
		{
			log.info("User details are found");
			return true;
		}
		
		else {
			log.info("User details are not found");
			return false;
		}
		
}
}

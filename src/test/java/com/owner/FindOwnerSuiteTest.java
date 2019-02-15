package com.owner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.google.common.io.Files;
import com.vocalink.utility.PropertyFile;
import com.vocalink.utility.UIdata;

public class FindOwnerSuiteTest {
	//Static Block
			{
				PropertyConfigurator.configure(UIdata.startuppath+"//src//main//resources//Log4j.properties");
			}
			public static RemoteWebDriver driver;
			
			public static Logger log = Logger.getLogger(FindOwnerSuiteTest.class.getName());
			
			@Parameters("sBrowser")
			@BeforeSuite
			public void init(String sBrowser) throws FileNotFoundException, IOException
			{
				if(sBrowser.equalsIgnoreCase("Firefox"))
				{
					DesiredCapabilities capabilities = DesiredCapabilities.firefox();
					capabilities.setPlatform(Platform.WIN10);
					//driver = new RemoteWebDriver(new URL("http://192.168.46.20/wd/hub"), capabilities);
					driver = new RemoteWebDriver(new URL("http://192.168.46.74:5454/wd/hub"), capabilities);
				
				}else if(sBrowser.equalsIgnoreCase("Chrome"))
				{
					log.info("Chrome Browser");
					//System.setProperty("webdriver.chrome.driver","/usr/bin/chromedriver.exe");
				   //driver= new ChromeDriver();
					/*System.setProperty("webdriver.chrome.driver",
				            "/usr/bin/chromedriver");
					ChromeOptions options = new ChromeOptions();
			        options.addArguments("--no-sandbox");
			        options.addArguments("--disable-dev-shm-usage");
					driver=new ChromeDriver(options);*/
					
					DesiredCapabilities cap = DesiredCapabilities.chrome();
					cap = DesiredCapabilities.chrome();
					cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
					driver = new RemoteWebDriver(new URL("http://192.168.46.74:5454/wd/hub"),cap);
					
					
					
				}
				log.info("Browser has been launched");
				
				driver.get(PropertyFile.read_testdata("URL"));
				
			}
			
			@AfterMethod
			public void endTestCase(ITestResult result) {
				if (result.getStatus() == ITestResult.FAILURE) {
					try {
						TakesScreenshot ts = (TakesScreenshot) driver;
						File screen_src = ts.getScreenshotAs(OutputType.FILE);
						String path = UIdata.startuppath + "\\target\\ErrorScreenshots\\" + System.currentTimeMillis() + ".png";
						File destination = new File(path);
						try {
							Files.copy(screen_src, destination);
							System.out.println("Screenshot Taken");
						} catch (Exception e) {
							System.out.println("Exception while taking screen shot" + e.getMessage());
						}
					} catch (Exception e) {
						System.out.println("Exception while taking screen shot" + e.getMessage());
					}
				}
			}
			
			@AfterSuite
			public void killBrowser()
			{
				driver.quit();
				log.info("Browser is closed");
			}
}

package com.owner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.vocalink.utility.PropertyFile;
import com.vocalink.utility.UIdata;

public class FindOwnerSuiteTest {
	//Static Block
			{
				PropertyConfigurator.configure(UIdata.startuppath+"//src//main//resources//Log4j.properties");
			}
			public static WebDriver driver;
			
			public static Logger log = Logger.getLogger(FindOwnerSuiteTest.class.getName());
			
			@Parameters("sBrowser")
			@BeforeSuite
			public void init(String sBrowser) throws FileNotFoundException, IOException
			{
				if(sBrowser.equalsIgnoreCase("Firefox"))
				{
					DesiredCapabilities capabilities = DesiredCapabilities.firefox();
					capabilities.setPlatform(Platform.LINUX);
					driver = new RemoteWebDriver(new URL("http://192.168.35.230/wd/hub"), capabilities);
				
				}else if(sBrowser.equalsIgnoreCase("Chrome"))
				{
					System.setProperty("webdriver.chrome.driver",UIdata.startuppath+"\\BrowseDrivers\\chromedriver.exe" );
					driver=new ChromeDriver();
					
					/*DesiredCapabilities capabilities = DesiredCapabilities.chrome();
					capabilities.setPlatform(Platform.LINUX);
					driver = new RemoteWebDriver(new URL("http://192.168.35.230:4444/wd/hub"), capabilities);
					*/
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
							FileUtils.copyFile(screen_src, destination);
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

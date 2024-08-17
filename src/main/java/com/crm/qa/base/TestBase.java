package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;

import com.crm.qa.utilities.TestUtil;
import com.crm.qa.utilities.WebEventListener;

public class TestBase{
	
	public static WebDriver driver;
	public static Properties prop;
	public static WebDriver e_driver;
	public static WebDriverListener eventListener;
	
	
	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("D:\\EclipseWorkstationForHome\\JavaTraining\\FreeCRMTest03\\"
			                            +"src\\main\\java\\com\\crm\\qa\\config\\config.properties");
			prop.load(ip);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public static void initialization() {
		String browserName = prop.getProperty("browser");
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "D:\\EclipseWorkstationForHome\\chromedriver-win64_126.0.6478.126\\chromedriver.exe");
			e_driver = new ChromeDriver();
		} else if(browserName.equals("FF")) {
			System.setProperty("webdriver.gecko.driver", "D:\\EclipseWorkstationForHome\\geckodriver-v0.30.0-win64\\geckodriver.exe");
			e_driver = new FirefoxDriver();
		}
		eventListener = new WebEventListener();
		driver = new EventFiringDecorator<WebDriver>(eventListener).decorate(e_driver);
		
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
		
		driver.get(prop.getProperty("url"));
	}

}

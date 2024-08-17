package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class HomePageTest extends TestBase{

	LoginPage loginpage;
	HomePage homepage;
	ContactsPage contactpage;
	
	public HomePageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		loginpage = new LoginPage();
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		//contactpage = new ContactsPage();
	}
	
	@Test(priority=1)
	public void HomePageTitleTest() {
		String title = homepage.verifyHomePageTitle();
		Assert.assertEquals(title, "Cogmento CRM", "Home page title not matched");
	}
	
	@Test(priority=2)
	public void userNameTest() {
		boolean loggedInUser = homepage.verifyCorrectUsername();
		Assert.assertEquals(loggedInUser, true, "User is not logged in!");
		//Another Way
		//Assert.assertTrue(loggedInUser, "User is not logged in!");
	}
	
	@Test(priority=3)
	public void contactLinkTest() {
		contactpage = homepage.clickOnContactLink();
		boolean contactlabel = contactpage.verifyContactLabel();
		Assert.assertEquals(contactlabel, true, "Incoorect Contact Lable");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}

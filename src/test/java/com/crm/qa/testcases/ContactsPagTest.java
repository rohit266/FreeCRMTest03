package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.utilities.TestUtil;

public class ContactsPagTest extends TestBase{
	
	LoginPage loginpage;
	HomePage homepage;
	ContactsPage contactpage;
	TestUtil testutil;
	
	String sheetName = "contacts";

	public ContactsPagTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		loginpage = new LoginPage();
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		contactpage = homepage.clickOnContactLink();
		testutil = new TestUtil();
		contactpage.VerifyShowFilterButton();
		
	}
	
	@Test(priority=1)
	public void verifyContactLabel() {
		boolean contactlabel = contactpage.verifyContactLabel();
		Assert.assertEquals(contactlabel, true, "contacts label is missing on the page");
	}
	
	@Test(priority=2)
	public void selectSigleContactTest() {
		String contacecheckbox= contactpage.selectContactByName("Sanjay Verma");
		Assert.assertEquals(contacecheckbox, "ui checked fitted read-only checkbox", "Contact Checkbox is not selected");
	}
	
	@Test(priority=3)
	public void selectMultipleContactTest() {
		String contacecheckbox= contactpage.selectContactByName("Ashu Gagan");
		Assert.assertEquals(contacecheckbox, "ui checked fitted read-only checkbox", "Contact Checkbox is not selected");
		
		String contacecheckbox1= contactpage.selectContactByName("Atul Kumar");
		Assert.assertEquals(contacecheckbox1, "ui checked fitted read-only checkbox", "Contact Checkbox is not selected");
	}
	
	@DataProvider
	public Object[][] getCRMTestData() {
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=4, dataProvider="getCRMTestData")
	public void validateCreateNewContact(String ftName, String ltName, String email) {
		//contactpage.createNewContact("Manju", "Sharma", "manju123@yopmail.com");
		contactpage.createNewContact(ftName, ltName, email);
		
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}

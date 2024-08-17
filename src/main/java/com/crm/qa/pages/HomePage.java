package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase{

	@FindBy(xpath="//span[contains(text(), 'Rohit Kumar')]")
	WebElement userNameLabel;
	
	@FindBy(xpath="//span[contains(text(), 'Contacts')]")
	WebElement contactlinks;
	
	@FindBy(xpath="//span[contains(text(),'Deals')]")
	WebElement dealsLink;
	
	@FindBy(xpath="//span[contains(text(), 'Tasks')]")
	WebElement tasksLinks;
	
	//Initializing the Page Objects:
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyCorrectUsername() {
		return userNameLabel.isDisplayed();
	}
	
	public String verifyHomePageTitle() {
		return driver.getTitle();
	}
	
	public ContactsPage clickOnContactLink() {
		Actions action = new Actions(driver);
		action.moveToElement(contactlinks).build().perform();
		contactlinks.click();
		return new ContactsPage();
	}
	
	
}

package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase{

	@FindBy(xpath="//i[@class='users icon']/following-sibling::span[@class='item-text']")
	WebElement contactsLabel;
	
	@FindBy(xpath="//button[contains(text(), 'Show Filters')]")
	WebElement contactfilter;
	
	@FindBy(xpath = "//button[contains(text(), 'Create')]")
	WebElement createContactButton;
	
	@FindBy(name="first_name")
	WebElement firstName;
	
	@FindBy(name="last_name")
	WebElement lastName;
	
	@FindBy(xpath = "//input[@placeholder='Email address']")
	WebElement emailAddress;
	
	@FindBy(xpath = "//button[@class='ui linkedin button']")
	WebElement saveBtn;
	
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String verifyContactPageTitle() {
		return driver.getTitle();
	}
	
	public void VerifyShowFilterButton() {
		Actions action = new Actions(driver);
		action.moveToElement(contactfilter).build().perform();
	}
	
	public boolean verifyContactLabel() {
		System.out.println("AssertTrue Result======="+contactsLabel.isDisplayed());
		return contactsLabel.isDisplayed();
	}
	
	public String selectContactByName(String name) {
		WebElement element = driver.findElement(By.xpath("//a[contains(text(), '"+name+"')]/parent::td//"
	            +"preceding-sibling::td//div[@class='ui fitted read-only checkbox']"));
		System.out.println("Before Clicked======="+element.getAttribute("class"));
		element.click();
		System.out.println("After Clicked======="+element.getAttribute("class"));
		System.out.println("Is checkbox selected======="+element.isSelected());
		return element.getAttribute("class");
		
	}
	
	public void createNewContact(String ftName, String ltName, String email) {
		createContactButton.click();
		firstName.sendKeys(ftName);
		lastName.sendKeys(ltName);
		emailAddress.sendKeys(email);
		saveBtn.click();
	}
}

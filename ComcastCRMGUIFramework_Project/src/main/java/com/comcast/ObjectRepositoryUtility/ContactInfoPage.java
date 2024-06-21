package com.comcast.ObjectRepositoryUtility;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.cr.generic.webdriverutility.WebDriverUtility;

public class ContactInfoPage extends WebDriverUtility{
	
	WebDriver driver=null;
	public ContactInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	@FindBy(className = "dvHeaderText")
	private WebElement ContactHeaderText;
	
	@FindBy(className = "dvlview_Last Name")
	private WebElement lastNameText;
	
	@FindBy(className = "dvlview_Support Start Date")
	private WebElement actualStartDate;
	
	@FindBy(className = "dvlview_Support End Date")
	private WebElement actualEndDate;
	
	public WebElement getContactHeaderText() {
		return ContactHeaderText;
	}

	public WebElement getLastNameText() {
		return lastNameText;
	}

	public WebElement getActualStartDate() {
		return actualStartDate;
	}

	public WebElement getActualEndDate() {
		return actualEndDate;
	}
	
	
}

package com.comcast.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.cr.generic.webdriverutility.WebDriverUtility;

public class ContactPage extends WebDriverUtility{
	WebDriver driver;
	public ContactPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement createContactLookUpImg;
	
	@FindBy(className = "dvHeaderText")
	private WebElement headermsg;
	
	@FindBy(id = "dtlview_Last Name")
	private WebElement lastname;
	
	

	public WebElement getHeadermsg() {
		return headermsg;
	}



	public WebElement getLastname() {
		return lastname;
	}



	public WebElement getcreateContactLookUpImg() {
		return createContactLookUpImg;
	}

}

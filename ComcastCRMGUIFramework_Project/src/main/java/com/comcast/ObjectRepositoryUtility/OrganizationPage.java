package com.comcast.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage {
	WebDriver driver;
	public OrganizationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath = "//img[@title='Create Organization...']")
	private WebElement createNewOrgBtn;
	
	@FindBy(name = "search_text")
	private WebElement searchtxt;
	
	@FindBy(name = "submit")
	private WebElement searchBtn;
	
	public WebElement getSearchBtn() {
		return searchBtn;
	}
	@FindBy(name = "search_field")
	private WebElement orgSearchDropDown;
	
	public WebElement getSearchtxt() {
		return searchtxt;
	}

	public WebElement getOrgSearchDropDown() {
		return orgSearchDropDown;
	}

	public WebElement getCreateNewOrgBtn() {
		return createNewOrgBtn;
	}
	

}

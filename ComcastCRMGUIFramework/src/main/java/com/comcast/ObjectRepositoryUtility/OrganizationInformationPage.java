package com.comcast.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformationPage {
	WebDriver driver;
	public OrganizationInformationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(className = "dvHeaderText")
	private WebElement orgHeaderInfo;
	
	@FindBy(id = "mouseArea_Organization Name")
	private WebElement actalOrgNameTxt;
	
	@FindBy(id = "mouseArea_Industry")
	private WebElement industryDropDwTxt;
	
	@FindBy(id = "dtlview_Industry")
	private WebElement actalIndustryTxt;
	
	@FindBy(id = "dtlview_Phone")
	private WebElement actalPhoneTxt;
	
	@FindBy(id = "dtlview_Type")
	private WebElement actalIndType;
	
	public WebElement getActalIndustryTxt() {
		return actalIndustryTxt;
	}

	public WebElement getActalIndType() {
		return actalIndType;
	}

	public WebElement getorgHeaderInfo() {
		return orgHeaderInfo;
	}

	public WebElement getOrgHeaderInfo() {
		return orgHeaderInfo;
	}

	public WebElement getActalOrgNameTxt() {
		return actalOrgNameTxt;
	}

	public WebElement getIndustryDropDwTxt() {
		return industryDropDwTxt;
	}

	public WebElement getActalPhoneTxt() {
		return actalPhoneTxt;
	}
	
	
}

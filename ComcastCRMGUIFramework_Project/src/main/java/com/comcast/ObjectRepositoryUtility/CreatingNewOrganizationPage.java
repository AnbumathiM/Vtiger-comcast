package com.comcast.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.comcast.cr.generic.webdriverutility.WebDriverUtility;

public class CreatingNewOrganizationPage extends WebDriverUtility{
	WebDriver driver;
	public CreatingNewOrganizationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(name="accountname")
	private WebElement orgNameEdit;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name="industry")
	private WebElement industryDropdw;
	
	@FindBy(name="accounttype")
	private WebElement typeDropdw;
	
	@FindBy(id="phone")
	private WebElement phoneEdit;
	
	public WebElement getOrgNameEdit() {
		return orgNameEdit;
	}
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	public void createOrg(String orgName) {
		orgNameEdit.sendKeys(orgName);
		
	}
	public void createOrg(String orgName,String industry, String type) {
		orgNameEdit.sendKeys(orgName);
		Select select=new Select(industryDropdw);
		select.selectByVisibleText(industry);
		Select select1=new Select(typeDropdw);
		select1.selectByVisibleText(type);
		saveBtn.click();
	}
	public void createOrgWithPhone(String orgName, String phone) {
		orgNameEdit.sendKeys(orgName);
		phoneEdit.sendKeys(phone);
		saveBtn.click();
	}
	public WebElement getIndustryDropdw() {
		return industryDropdw;
	}
	public WebElement getPhoneEdit() {
		return phoneEdit;
	}
	
}

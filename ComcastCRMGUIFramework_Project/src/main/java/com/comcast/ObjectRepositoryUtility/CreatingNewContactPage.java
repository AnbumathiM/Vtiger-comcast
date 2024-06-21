package com.comcast.ObjectRepositoryUtility;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.cr.generic.webdriverutility.WebDriverUtility;

public class CreatingNewContactPage extends WebDriverUtility {
	WebDriver driver;
	public CreatingNewContactPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy (name = "lastname")
	private WebElement lastNameEdit;
	
	@FindBy (name = "support_start_date")
	private WebElement startDateTxt;
	
	@FindBy (name = "support_end_date")
	private WebElement endDateTxt;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy (xpath = "//input[@name='account_name']/following-sibling::img")
	private WebElement OrgLookUpImg;
	
	@FindBy (id = "search_txt")
	private WebElement searchTxt;
	
	@FindBy (name = "search")
	private WebElement searchBtn;
	
	public void newContactCreate(String lastName) {
		lastNameEdit.sendKeys(lastName);
		saveBtn.click();
	}
	public void createContactWithSupportDate(String lastName, String startDate, String endDate) {
		lastNameEdit.sendKeys(lastName);
		startDateTxt.clear();
		startDateTxt.sendKeys(startDate);
		endDateTxt.clear();
		endDateTxt.sendKeys(endDate);
		saveBtn.click();
	}
	public void createContact(String lastName, String orgName) {
		lastNameEdit.sendKeys(lastName);
		System.out.println(lastName);
		String parentWindow=driver.getWindowHandle();
		OrgLookUpImg.click();
		switchToTabOnURL(driver, "module=Accounts");
		System.out.println("switch to child");
		searchTxt.sendKeys(orgName);
		searchBtn.click();
		System.out.println("search btn");
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		driver.switchTo().window(parentWindow);
		System.out.println("back to parent window");
		saveBtn.click();
	}
	public WebElement getLastNameEdit() {
		return lastNameEdit;
	}
	public WebElement getStartDateTxt() {
		return startDateTxt;
	}
	public WebElement getEndDateTxt() {
		return endDateTxt;
	}
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	public WebElement getOrgLookUpImg() {
		return OrgLookUpImg;
	}
	public WebElement getSearchTxt() {
		return searchTxt;
	}
	public WebElement getSearchBtn() {
		return searchBtn;
	}
	
}

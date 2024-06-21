package com.comcast.ObjectRepositoryUtility;

import org.openqa.selenium.SearchContext;
/**
 * Contains login page elements and business library like login()
 * @author user
 * 
 */
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.cr.generic.webdriverutility.WebDriverUtility;
/**
 * 
 */
public class LoginPage extends WebDriverUtility{
	//Rule-1: create a separate java class
	//Rule-2: Object Creation--> Identify all the elements using @findBy annotation
	WebDriver driver;
	@FindBy(name="user_name")
	private WebElement usernameEdit;
	
	@FindBy(name="user_password")
	private WebElement passwordEdit;
	
	@FindBy(id="submitButton")
	private WebElement LoginBtn;
	
	//Rule-3: Object Initialization
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//Rule-4: Object Encapsulation
	public WebElement getUsernameEdit() {
		return usernameEdit;
	}
	public WebElement getPasswordEdit() {
		return passwordEdit;
	}
	public WebElement getLoginBtn() {
		return LoginBtn;
	}
/**
 * Login to application based on credentials(url, username, password)
 * @param url
 * @param username
 * @param password
 */
	public void loginToApp(String url,String username,String password) {
		waitForPageToLoad(driver);
		driver.get(url);
		driver.manage().window().maximize();
		usernameEdit.sendKeys(username);
		passwordEdit.sendKeys(password);
		LoginBtn.click();
	}
}

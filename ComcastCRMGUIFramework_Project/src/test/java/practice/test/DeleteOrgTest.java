package practice.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.comcast.ObjectRepositoryUtility.CreatingNewOrganizationPage;
import com.comcast.ObjectRepositoryUtility.HomePage;
import com.comcast.ObjectRepositoryUtility.LoginPage;
import com.comcast.ObjectRepositoryUtility.OrganizationInformationPage;
import com.comcast.ObjectRepositoryUtility.OrganizationPage;
import com.comcast.cr.generic.webdriverutility.JavaUtility;
import com.comcast.cr.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;

public class DeleteOrgTest {
	public static void main(String[] args) throws Throwable {
		/* Create FileUtilityObject*/
		FileUtility fileLib=new FileUtility();
		ExcelUtility excelLib=new ExcelUtility();
		JavaUtility javaLib=new JavaUtility();
		WebDriverUtility webLib=new WebDriverUtility();
		//read data from property file
		String browser = fileLib.getDataFromPropertiesFile("browser");
		String url = fileLib.getDataFromPropertiesFile("url");
		String username = fileLib.getDataFromPropertiesFile("username");
		String password = fileLib.getDataFromPropertiesFile("password");
		//read testscript data from excel file
		String orgName = excelLib.getDataFromExcel("org",15,2)+javaLib.getRandomNumber();
		WebDriver driver=null;
		if(browser.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}else if(browser.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
		}else {
			driver=new ChromeDriver();
		}
		webLib.waitForPageToLoad(driver);
		driver.get(url);
		//Login
		LoginPage login=new LoginPage(driver);
		login.loginToApp(url, username, password);
		// Navigate to organization module
		HomePage home=new HomePage(driver);
		home.getOrgLink().click();
		//Navigate to create organization button
		OrganizationPage orgpage=new OrganizationPage(driver);
		orgpage.getCreateNewOrgBtn().click();
		//Creating new organization
		CreatingNewOrganizationPage createNewOrg=new CreatingNewOrganizationPage(driver);
		createNewOrg.createOrg(orgName);	                                                
	//Go back to the organization page
		webLib.waitForPageToLoad(driver);
		driver.get(url);
		// Navigate to organization module
		home=new HomePage(driver);
		webLib.waitForElementPresent(driver,home.getOrgLink());
		home.getOrgLink().click();
	//Search for organization
	orgpage.getSearchtxt().sendKeys(orgName);
	webLib.select(orgpage.getOrgSearchDropDown(), "Organization Name");
	orgpage.getSearchBtn().click();
	
	//In dynamic webtable select & delete org
	driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();
	//Logout
		webLib.switchToAlertAndAccept(driver);
			home.logOut();
			driver.quit(); 
	}
}

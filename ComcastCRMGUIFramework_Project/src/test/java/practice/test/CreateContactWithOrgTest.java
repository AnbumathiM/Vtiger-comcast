package practice.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.comcast.ObjectRepositoryUtility.ContactPage;
import com.comcast.ObjectRepositoryUtility.CreatingNewContactPage;
import com.comcast.ObjectRepositoryUtility.CreatingNewOrganizationPage;
import com.comcast.ObjectRepositoryUtility.HomePage;
import com.comcast.ObjectRepositoryUtility.LoginPage;
import com.comcast.ObjectRepositoryUtility.OrganizationPage;
import com.comcast.cr.generic.webdriverutility.JavaUtility;
import com.comcast.cr.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;

public class CreateContactWithOrgTest extends BaseClass{
	@Test
	public void CreateContactWithOrgTest() throws Throwable {
	//read testscript data from excel file
		String orgName = excelLib.getDataFromExcel("org",13,2)+jLib.getRandomNumber();
		String lastName = excelLib.getDataFromExcel("org",13,3)+jLib.getRandomNumber();
		
	// Navigate to organization module
		HomePage hp=new HomePage(driver);
		hp.getOrgLink().click(); // create new org
		
	//Navigate to create organization button
		OrganizationPage orgpage=new OrganizationPage(driver);
		orgpage.getCreateNewOrgBtn().click();
		
	//Creating new organization
		CreatingNewOrganizationPage createNewOrg = new CreatingNewOrganizationPage(driver);
		createNewOrg.createOrg(orgName);
		createNewOrg.getSaveBtn().click();
		System.out.println(orgName);
		Thread.sleep(5000);
	//	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(50));
	// wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Contacts")));
		System.out.println("home page");
		hp.getContactLink().click();
		System.out.println("ContactLink().click()");
		ContactPage cp = new ContactPage(driver);
		cp.getcreateContactLookUpImg().click();
		
		CreatingNewContactPage newContact = new CreatingNewContactPage(driver);
		newContact.createContact(lastName, orgName);

		// verify expected result of header msg after creation of org
		String headerinfo = driver.findElement(By.className("dvHeaderText")).getText();
		if (headerinfo.contains(lastName)) {
			System.out.println(lastName + "org is created");
		} else {
			System.out.println(lastName + "org is not created");
		}
		// verify the dataflow after integrating org name in contact module
		String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		if (actOrgName.contains(orgName)) {
			System.out.println(orgName + "org is created");
		} else {
			System.out.println(orgName + "org is not created");
		}
	
}
}





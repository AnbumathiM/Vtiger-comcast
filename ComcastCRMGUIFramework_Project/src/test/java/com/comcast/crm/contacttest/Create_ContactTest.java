package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.ObjectRepositoryUtility.ContactInfoPage;
import com.comcast.ObjectRepositoryUtility.ContactPage;
import com.comcast.ObjectRepositoryUtility.CreatingNewContactPage;
import com.comcast.ObjectRepositoryUtility.CreatingNewOrganizationPage;
import com.comcast.ObjectRepositoryUtility.HomePage;
import com.comcast.ObjectRepositoryUtility.OrganizationInformationPage;
import com.comcast.ObjectRepositoryUtility.OrganizationPage;
import com.comcast.cr.generic.webdriverutility.JavaUtility;
import com.comcast.cr.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
/**
 * contact module test scripts
 * @author user
 */
public class Create_ContactTest extends BaseClass {
	@Test(groups="SmokeTest")
	
	public void createConatctTest() throws Throwable {
		UtilityClassObject.getTest().log(Status.INFO, "Read data frm excel");
		String lastName = excelLib.getDataFromExcel("org", 10, 2) + jLib.getRandomNumber();
		
		UtilityClassObject.getTest().log(Status.INFO, "navigate to contact module");
		HomePage hpLib = new HomePage(driver);
		hpLib.getContactLink().click();
		// Click on Create Contact Button
		ContactPage cpLib = new ContactPage(driver);
		cpLib.getcreateContactLookUpImg().click();
		// Enter all the details and create new contact
		UtilityClassObject.getTest().log(Status.INFO, "create new contact");
		CreatingNewContactPage newContact = new CreatingNewContactPage(driver);
		newContact.newContactCreate(lastName);

		// validating header after contact creation
		String actcontactheader = cpLib.getHeadermsg().getText();
		boolean status=actcontactheader.contains(lastName);
		Assert.assertTrue(status);
		String actLastName = cpLib.getLastname().getText();
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(actLastName, lastName);
	}

	@Test(groups="RegressionTest")
	public void createContactWithSupportDate() throws Throwable {
		// read testscript data from excel file
		String lastName = excelLib.getDataFromExcel("org", 10, 2) + jLib.getRandomNumber();

		// Navigate to Contact module
		HomePage hpLib = new HomePage(driver);
		hpLib.getContactLink().click();

		// Click on Create Contact Button
		ContactPage cpLib = new ContactPage(driver);
		cpLib.getcreateContactLookUpImg().click();

		// Start and End Date
		String startDate = jLib.getSystemDateYYYYMMDD();
		String endDate = jLib.getRequiredDateYYYYDDMM(30);

		CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
		ccp.createContactWithSupportDate(lastName, startDate, endDate);

		// validating header after contact creation
		ContactInfoPage contactInfo=new ContactInfoPage(driver);
		String actStartDate = contactInfo.getActualStartDate().getText();
		Assert.assertEquals(actStartDate , startDate);;
		String actEndDate = contactInfo.getActualEndDate().getText();
		Assert.assertEquals(actEndDate, endDate);
	}

	@Test(groups="RegressionTest")
	public void CreateContactWithOrgTest() throws Throwable {
		String orgName = excelLib.getDataFromExcel("org", 13, 2) + jLib.getRandomNumber();
		String lastName = excelLib.getDataFromExcel("org", 13, 3) + jLib.getRandomNumber();

		HomePage hp = new HomePage(driver);
		webLib.waitForElementPresent(driver, hp.getOrgLink());
		hp.getOrgLink().click(); // create new org

		OrganizationPage orgpage = new OrganizationPage(driver);
		orgpage.getCreateNewOrgBtn().click();

		CreatingNewOrganizationPage createNewOrg = new CreatingNewOrganizationPage(driver);
		createNewOrg.createOrg(orgName);
		createNewOrg.getSaveBtn().click();
		System.out.println(orgName);
		/*
		 * ==>try with some other methods (don't use thread.sleep)
		 * 
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(50));
		wait.until(ExpectedConditions.elementToBeSelected(By.linkText("Contacts")));
		*/
		Thread.sleep(5000);
		System.out.println("home page");
		hp.getContactLink().click();
		System.out.println("ContactLink().click()");
		ContactPage cp = new ContactPage(driver);
		cp.getcreateContactLookUpImg().click();

		CreatingNewContactPage newContact = new CreatingNewContactPage(driver);
		newContact.createContact(lastName, orgName);

		// verify expected result of header msg after creation of org
		OrganizationInformationPage orgInfo=new OrganizationInformationPage(driver);
		String verifyheaderinfo = orgInfo.getorgHeaderInfo().getText();
		boolean actheaderinfo = verifyheaderinfo.contains(orgName);
		Assert.assertTrue(actheaderinfo);
		
		// verify the dataflow after integrating org name in contact module
		String actOrgName = orgInfo.getActalOrgNameTxt().getText();
		boolean verifyOrgName = actOrgName.contains(orgName);
		Assert.assertTrue(verifyOrgName);
	}
}

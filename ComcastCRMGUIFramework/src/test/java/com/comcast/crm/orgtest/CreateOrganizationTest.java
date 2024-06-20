package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.tools.ant.filters.LineContains.Contains;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.ObjectRepositoryUtility.CreatingNewOrganizationPage;
import com.comcast.ObjectRepositoryUtility.HomePage;
import com.comcast.ObjectRepositoryUtility.LoginPage;
import com.comcast.ObjectRepositoryUtility.OrganizationInformationPage;
import com.comcast.ObjectRepositoryUtility.OrganizationPage;
import com.comcast.cr.generic.webdriverutility.JavaUtility;
import com.comcast.cr.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
/**
 * Organization module test scripts
 * @author user
 */
@Listeners(com.comcast.crm.generic.listenerUtility.ListenerImplementsClass.class)
public class CreateOrganizationTest extends BaseClass{
	@Test(groups="SmokeTest")
	public void createOrgTest()throws Throwable{
		//read testscript data from excel file
		String orgName = excelLib.getDataFromExcel("org",1,2)+jLib.getRandomNumber();

		// Navigate to organization module
		HomePage home=new HomePage(driver);
		webLib.waitForElementPresent(driver,home.getOrgLink());
		home.getOrgLink().click();
		//Navigate to create organization button
		OrganizationPage orgpage=new OrganizationPage(driver);
		orgpage.getCreateNewOrgBtn().click();
		//Creating new organization
		CreatingNewOrganizationPage createNewOrg=new CreatingNewOrganizationPage(driver);
		createNewOrg.createOrg(orgName);
		createNewOrg.getSaveBtn().click();
		System.out.println(orgName);
		//verify expected result of header msg
		OrganizationInformationPage OrgInfoPage=new OrganizationInformationPage(driver);
		System.out.println(OrgInfoPage);
		String actOrgName=OrgInfoPage.getorgHeaderInfo().getText();
		System.out.println(actOrgName);
		boolean verifyOrgName = actOrgName.contains(orgName);
		Assert.assertTrue(verifyOrgName);		                                             
	}
	@Test(groups="RegressionTest")
	public void createOrgWithIndustry() throws Throwable{
		
		String orgName = excelLib.getDataFromExcel("org",4,2)+jLib.getRandomNumber();
		String industry=excelLib.getDataFromExcel("org",4,3);
		String industryType=excelLib.getDataFromExcel("org",4,4);
		
		// Navigate to organization module
		HomePage hp=new HomePage(driver);
		hp.getOrgLink().click();
	
		OrganizationPage op=new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();
		
		CreatingNewOrganizationPage newOrg=new CreatingNewOrganizationPage(driver);
		newOrg.createOrg(orgName, industry, industryType);
		
		//validating indutry dropdown
		OrganizationInformationPage infoPage=new OrganizationInformationPage(driver);
		String actindustry=infoPage.getActalIndustryTxt().getText();
		Assert.assertEquals(actindustry, industry);
		//validating type dropdown
		String actType=infoPage.getActalIndType().getText();
		Assert.assertEquals(actType, industryType);
	}
	
	@Test(groups="RegressionTest")
	public void createOrgWithPhNo()throws Throwable{
			//read testscript data from excel file
			String orgName=excelLib.getDataFromExcel("org",7,2)+jLib.getRandomNumber();
			String phoneNumber=excelLib.getDataFromExcel("org",7,3);
		
			// Navigate to organization module
			HomePage hp=new HomePage(driver);
			hp.getOrgLink().click();
			// Create organization
			OrganizationPage orgPage=new OrganizationPage(driver);
			orgPage.getCreateNewOrgBtn().click();
			
			CreatingNewOrganizationPage newOrg=new CreatingNewOrganizationPage(driver);
			newOrg.createOrgWithPhone(orgName, phoneNumber);
		
			//validating phoneNumber
			OrganizationInformationPage orgInfo=new OrganizationInformationPage(driver);
			String actphoneNumber=orgInfo.getActalPhoneTxt().getText();
			Assert.assertEquals(actphoneNumber, phoneNumber);	
	}

}

package practice.test;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.comcast.ObjectRepositoryUtility.CreatingNewOrganizationPage;
import com.comcast.ObjectRepositoryUtility.HomePage;
import com.comcast.ObjectRepositoryUtility.OrganizationInformationPage;
import com.comcast.ObjectRepositoryUtility.OrganizationPage;
import com.comcast.cr.generic.webdriverutility.JavaUtility;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;

public class CreateOrganizationWithPhoneNumber extends BaseClass{
	@Test
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
			if(actphoneNumber.equals(phoneNumber)) {
				System.out.println(phoneNumber+ "PhoneNumber info is verified");
			 }else {
				System.out.println(phoneNumber+"industry info is not verified");
			}
	}

}

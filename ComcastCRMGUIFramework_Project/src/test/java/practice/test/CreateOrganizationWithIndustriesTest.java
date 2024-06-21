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
	import org.openqa.selenium.interactions.Actions;
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
	public class CreateOrganizationWithIndustriesTest extends BaseClass{
		@Test
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
			System.out.println(orgName);
			System.out.println(industry);
			System.out.println(industryType);
			
			//validating indutry dropdown
			OrganizationInformationPage infoPage=new OrganizationInformationPage(driver);
			String actindustry=infoPage.getActalIndustryTxt().getText();
			if(actindustry.equalsIgnoreCase(industry)) {
				System.out.println(industry+"industry info is verified");
			}else {
				System.out.println(industry+"industry info is not verified");
			}
			//validating type dropdown
			String actType=infoPage.getActalIndType().getText();
			if(actType.equalsIgnoreCase(industryType)) {
				System.out.println(industryType+"industry info is verified");
			}else {
				System.out.println(industryType+"industry info is not verified");
			}
			
		}

	}



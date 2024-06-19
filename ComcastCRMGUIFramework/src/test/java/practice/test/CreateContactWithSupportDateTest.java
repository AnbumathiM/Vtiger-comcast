package practice.test;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.comcast.ObjectRepositoryUtility.ContactPage;
import com.comcast.ObjectRepositoryUtility.CreatingNewContactPage;
import com.comcast.ObjectRepositoryUtility.HomePage;
import com.comcast.cr.generic.webdriverutility.JavaUtility;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;

public class CreateContactWithSupportDateTest extends BaseClass{
	@Test
	public void CreateContactWithSupportDateTest() throws Throwable {
		//read testscript data from excel file
		String lastName=excelLib.getDataFromExcel("org",10,2)+jLib.getRandomNumber();
		
		// Navigate to Contact module
				HomePage hpLib=new HomePage(driver);
				hpLib.getContactLink().click();
				
		//Click on Create Contact Button
				ContactPage cpLib = new ContactPage(driver);
				cpLib.getcreateContactLookUpImg().click();
				
		//Start and End Date
		String startDate = jLib.getSystemDateYYYYMMDD();
		String endDate=jLib.getRequiredDateYYYYDDMM(30); 
		
		CreatingNewContactPage ccp=new CreatingNewContactPage(driver);
		ccp.createContactWithSupportDate(lastName, startDate, endDate);
		
		//validating header after contact creation
		String actStartDate=driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if(actStartDate.equals(startDate)) {
			System.out.println(startDate+ "LastName info is verified");
		 }else {
			System.out.println(startDate+ "LastName info is not verified");
		}
		String actEndDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		if(actEndDate.contains(endDate)) {
			System.out.println(endDate+ "header info is verified");
		 }else {
			System.out.println(endDate+ "header info is not verified");
		}

	}

}

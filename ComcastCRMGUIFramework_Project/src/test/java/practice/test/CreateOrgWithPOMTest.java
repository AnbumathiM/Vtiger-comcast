package practice.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.comcast.ObjectRepositoryUtility.LoginPage;
import com.comcast.cr.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;

public class CreateOrgWithPOMTest {
	public static void main(String[] args) throws Throwable {
		/* Create FileUtilityObject*/
		FileUtility fileLib=new FileUtility();
		ExcelUtility excelLib=new ExcelUtility();
		JavaUtility javaLib=new JavaUtility();
		//read data from property file
		String browser = fileLib.getDataFromPropertiesFile("browser");
		String url = fileLib.getDataFromPropertiesFile("url");
		String username = fileLib.getDataFromPropertiesFile("username");
		String password = fileLib.getDataFromPropertiesFile("password");
		//read testscript data from excel file
		String orgName = excelLib.getDataFromExcel("org",1,3)+javaLib.getRandomNumber();
		WebDriver driver=null;
		if(browser.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}else if(browser.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
		}else {
			driver=new ChromeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);
		//Login
		LoginPage login = PageFactory.initElements(driver, LoginPage.class);
		login.getUsernameEdit().sendKeys("admin");
		login.getPasswordEdit().sendKeys("manager");
		login.getLoginBtn().click();
		// Navigate to organization module
		driver.findElement(By.linkText("Organizations")).click(); // create new org
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		//verify expected result of header msg
	String headerinfo = driver.findElement(By.className("dvHeaderText")).getText();
	if(headerinfo.contains(orgName)) {
		System.out.println(orgName+"org is created");
	}else {
		System.out.println(orgName+"org is not created");
	}
		//verify expected result
		String createdOrg = driver.findElement(By.id("dtlview_Organization Name")).getText();
		if(createdOrg.equals(orgName)){
			System.out.println(orgName+"org is created");
		}else {
			System.out.println(orgName+"org is not created");
		}
		//logout
		driver.quit();
	}
}

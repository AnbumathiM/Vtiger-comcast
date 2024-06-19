package com.comcast.crm.basetest;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.ObjectRepositoryUtility.HomePage;
import com.comcast.ObjectRepositoryUtility.LoginPage;
import com.comcast.cr.generic.webdriverutility.JavaUtility;
import com.comcast.cr.generic.webdriverutility.UtilityClassObject;
import com.comcast.cr.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.fileutility.JSON_Utility;

public class BaseClass {
	public DataBaseUtility dbLib=new DataBaseUtility();
	public FileUtility fileLib=new FileUtility();
	public ExcelUtility excelLib=new ExcelUtility();
	public JSON_Utility jsonLib=new JSON_Utility();
	public JavaUtility jLib=new JavaUtility();
	public WebDriverUtility webLib=new WebDriverUtility();
	public WebDriver driver=null;
	public static WebDriver sdriver=null;
	
	//@BeforeSuite(alwaysRun = true)
	@BeforeSuite(groups= {("SomkeTest"),("RegressionTest")})
	public void configBS() throws SQLException {
		System.out.println("===connect to DB, Report config===");
		dbLib.getDBconnection();
	}
	//@Parameters("Browser")
	//@BeforeClass(alwaysRun = true)
	@BeforeClass(groups= {("SomkeTest"),("RegressionTest")})
	//public void configBC(String browser) throws IOException {
	public void configBC()throws IOException{
		System.out.println("==Launch the browser==");
	   String browser = fileLib.getDataFromPropertiesFile("browser");
		//String Browser=browser;
		if(browser.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}else if(browser.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
		}else {
			driver=new ChromeDriver();
		}
		sdriver=driver;
		UtilityClassObject.setDriver(driver);
	}
	//@BeforeMethod(alwaysRun = true)
	@BeforeMethod(groups= {("SomkeTest"),("RegressionTest")})
	public void configBM() throws IOException {
		System.out.println("Login");
		String url=fileLib.getDataFromPropertiesFile("url");
		String username=fileLib.getDataFromPropertiesFile("username");
		String password=fileLib.getDataFromPropertiesFile("password");
		LoginPage lpLib=new LoginPage(driver);
		lpLib.loginToApp(url, username, password);
	}
	//@AfterMethod(alwaysRun = true)
	@AfterMethod(groups= {("SomkeTest"),("RegressionTest")})
	public void configAM() {
		System.out.println("Logout");
		HomePage hpLib=new HomePage(driver);
		//hpLib.logOut();
	}
	//@AfterClass(alwaysRun = true)
	@AfterClass(groups= {("SomkeTest"),("RegressionTest")})
	public void configAC() {
		System.out.println("==Close the Browser==");
		driver.quit();
	}
	//@AfterSuite(alwaysRun = true)
	@AfterSuite(groups= {("SomkeTest"),("RegressionTest")})
	public void configAS() throws SQLException {
		System.out.println("===close DB, Report bacup===");
		dbLib.closeDBconnection();
	}
}

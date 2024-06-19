package practice.test;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class GetProductInfoTest{
	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandName,String ProdName) throws InterruptedException  {
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		driver.get("https://www.amazon.in/");
		
		//Search Product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName,Keys.ENTER);
		//Capture the product Info
		String xpath="(//span[text()='"+ProdName+"'])[1]/../../../../div[3]/div[1]/div/div[1]/div[1]/div[1]/a/span[1]/span[2]/span[2]";
		String price = driver.findElement(By.xpath(xpath)).getText();
		System.out.println(price);
		driver.quit();
	}
	@DataProvider
	public Object[][] getData() throws Throwable{
		ExcelUtility excelLib=new ExcelUtility();
		int rowCount = excelLib.getRowCount("product");
		System.out.println(rowCount);
		Object[][] objArr=new Object[rowCount][2];
		for(int i=0;i<rowCount;i++) {
			objArr[i][0]=excelLib.getDataFromExcel("product", i+1, 0);
			objArr[i][0]=excelLib.getDataFromExcel("product", i+1, 1);
		}
		
		return objArr;
	}
}

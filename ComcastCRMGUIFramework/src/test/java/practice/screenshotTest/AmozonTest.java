package practice.screenshotTest;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;

public class AmozonTest {
	@Test
	public void amazonTest() throws IOException {
		WebDriver driver=new ChromeDriver();
		driver.get("http://amazon.com");
		
	//Typecast
		TakesScreenshot t=(TakesScreenshot) driver;
		File src = t.getScreenshotAs(OutputType.FILE);
		File dest=new File("./screenshot/test.png");
		FileUtils.copyFile(src, dest);
		
	}
}

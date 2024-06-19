package com.comcast.cr.generic.webdriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	public void waitForPageToLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}
	public void waitForElementPresent(WebDriver driver,WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void switchToTabOnURL(WebDriver driver,String partialURL) {
		Set<String> set=driver.getWindowHandles();
		Iterator<String> it=set.iterator();
		while(it.hasNext()) {
			String windowID=it.next();
			driver.switchTo().window(windowID);
			String child_URL=driver.getCurrentUrl();
			if(child_URL.contains(partialURL)) {
				break;
			}
		}
	}
		public void switchToTabOnTitle(WebDriver driver,String partialTitle) {
			Set<String> set=driver.getWindowHandles();
			Iterator<String> it=set.iterator();
			while(it.hasNext()) {
				String windowID=it.next();
				driver.switchTo().window(windowID);
				String child_Title=driver.getTitle();
				if(child_Title.contains(partialTitle)) {
					break;
				}
			}
	}
		public void switchToFrame(WebDriver driver,int index) {
			driver.switchTo().frame(index);
		}
		public void switchToFrame(WebDriver driver,String nameID) {
			driver.switchTo().frame(nameID);
		}
		public void switchToFrame(WebDriver driver,WebElement element) {
			driver.switchTo().frame(element);
		}
		public void switchToAlertAndAccept(WebDriver driver) {
			driver.switchTo().alert().accept();
		}
		public void switchToAlertAndDismiss(WebDriver driver) {
			driver.switchTo().alert().dismiss();
		}
		public void select(WebElement element, String text) {
			Select sel=new Select(element);
			sel.selectByVisibleText(text);
		}
		public void select(WebElement element, int index) {
			Select sel=new Select(element);
			sel.selectByIndex(index);
		}
		public void mouseMoveOnElement(WebDriver driver,WebElement element) {
			Actions act=new Actions(driver);
			act.moveToElement(element).perform();
		}
		public void doubleClick(WebDriver driver, WebElement element) {
			Actions act=new Actions(driver);
			act.doubleClick(element).perform();
		}
		
		

		
		
}

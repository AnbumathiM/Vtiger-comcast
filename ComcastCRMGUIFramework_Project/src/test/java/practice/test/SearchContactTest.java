package practice.test;
/**
 * Test case for contact module
 * @author user
 */

import org.testng.annotations.Test;

import com.comcast.ObjectRepositoryUtility.LoginPage;
import com.comcast.crm.basetest.BaseClass;

public class SearchContactTest extends BaseClass{
	/**
	 * Scenario:-1 login()==> navigateContact==>CreateContact==>searchContact	
	 */
	@Test
	public void searchContactTest() {
		/*Step-1: Login to app */
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp("url", "username", "password");
	}
}

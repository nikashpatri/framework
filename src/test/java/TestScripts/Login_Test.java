package TestScripts;
import org.testng.Assert;
import org.testng.annotations.Test;

import GCR.VShop.BaseUtility.ActionDriver;
import GCR.VShop.PageLibrary.AccountRepo;
import GCR.VShop.PageLibrary.LoginRepo;

public class Login_Test extends ActionDriver {
	LoginRepo loginrepo = new LoginRepo();

	@Test
	public void verifyLogin() throws Exception {
		openBrowser();
		openUrl("http://www.gcrit.com/build3/login.php");
		loginrepo.enterEmailAddress("nikumarpatri1993@gmail.com");
		loginrepo.enterPassword("nikashpatr");
		AccountRepo account = loginrepo.clickSignIn();
		//screenShot_path = utility.captureScreenShot(driver, "verifylogin");
		Assert.assertEquals(true, driver.findElement(account.accountpage.Logoff).isDisplayed());

	}

}

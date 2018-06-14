package GCR.VShop.PageLibrary;

import GCR.VShop.BaseUtility.ActionDriver;
import GCR.VShop.ObjectRepo.LoginPage;

public class LoginRepo extends ActionDriver {
	
	public LoginPage loginpage = new LoginPage();
	public void enterEmailAddress(String value) throws Exception {
		try {
			type(loginpage.Email_Address, value);
		} catch (Exception e) {
			screenShot_path=utility.captureScreenShot(driver, "clearEmailAddress");
			throw e;
		}

	}

	public void clearEmailAddress() throws Exception {
		try {
			clear(loginpage.Email_Address);
		} catch (Exception e) {
			screenShot_path=utility.captureScreenShot(driver, "clearEmailAddress");
			throw e;
		}
	}

	public void enterPassword(String value) throws Exception {
		try {
			type(loginpage.Password, value);
		} catch (Exception e) {
			screenShot_path=utility.captureScreenShot(driver, "enterpassword");
			throw e;
		}
	}

	public void clearPassword() throws Exception {
		try {
			clear(loginpage.Password);
		} catch (Exception e) {
			throw e;
		}
	}

	public AccountRepo clickSignIn() throws Exception {
		try {
			click(loginpage.Sign_In,5);
			return new AccountRepo();
		} catch (Exception e) {
			throw e;
		}
	}

}

package GCR.VShop.PageLibrary;

import GCR.VShop.BaseUtility.ActionDriver;
import GCR.VShop.ObjectRepo.AccountPage;

public class AccountRepo extends ActionDriver {
	
	public AccountPage accountpage = new AccountPage();
	//public ActionDriver actiondriver = new ActionDriver();

	
	 // public AccountRepo(WebDriver driver) { this.driver = driver; }
	 
	public void clickLogoff() throws Exception {
		try {
			click(accountpage.Logoff,5);
		} catch (Exception e) {
			throw e;
		}

	}

	public void clickCheckOut() throws Exception {
		try {
			click(accountpage.checkOut,5);
		} catch (Exception e) {
			throw e;
		}
	}

	public void clickMyAccount() throws Exception {
		try {
			click(accountpage.MyAccount,5);
		} catch (Exception e) {
			throw e;
		}
	}

	public void clickCart() throws Exception {
		try {
			click(accountpage.Cart,5);
		} catch (Exception e) {
			throw e;
		}
	}

}

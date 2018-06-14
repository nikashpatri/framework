package GCR.VShop.PageLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import GCR.VShop.BaseUtility.ActionDriver;
import GCR.VShop.ObjectRepo.CartPage;

public class CartRepo extends ActionDriver {
	public CartPage cartpage = new CartPage();

	public ShippingRepo clickSecureCheckOut() throws Exception {
		try {
			click(cartpage.secureCheckout, 5);
			return new ShippingRepo();
		} catch (Exception e) {
			throw e;
		}
	}

	public HomeRepo clickContinueShopping() throws Exception {
		try {
			click(cartpage.continueShopping, 5);
			return new HomeRepo();
		} catch (Exception e) {
			throw e;
		}
	}

	public void clickRemoveFromCart() throws Exception {
		try {
			click(cartpage.removeFromCart, 5);
		} catch (Exception e) {
			throw e;
		}
	}

	public void clickRemoveFromCart(String name) throws Exception {
		try {
			click(By.xpath("//div[p[contains(text(),'" + name + "')]]/form/input[4]"), 5);
		} catch (NoSuchElementException e) {
			throw e;
		}
	}

	public MemberLoginRepo clickSignIn() throws Exception {
		try {
			click(cartpage.signIn, 5);
			return new MemberLoginRepo();
		} catch (NoSuchElementException e) {
			throw e;
		}
	}

	public UserRegistrationRepo clickSignUp() throws Exception {
		try {
			click(cartpage.signUp, 5);
			return new UserRegistrationRepo();
		} catch (NoSuchElementException e) {
			throw e;
		}
	}
}

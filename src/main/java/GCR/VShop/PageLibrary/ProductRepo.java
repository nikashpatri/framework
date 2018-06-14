package GCR.VShop.PageLibrary;

import org.openqa.selenium.By;

import GCR.VShop.BaseUtility.ActionDriver;
import GCR.VShop.ObjectRepo.ProductDetailsPage;

public class ProductRepo extends ActionDriver {

	ProductDetailsPage productdetailspage = new ProductDetailsPage();

	public void clickAddToCart() throws Exception {
		try {
			click(productdetailspage.AddToCart, 5);
			isVisible(By.xpath("//a[text()='COMPLETE PURCHASE']"), 5);
		} catch (Exception e) {
			throw e;
		}
	}

	public CartRepo clickOnCartIcon() throws Exception {
		try {
			click(productdetailspage.cartIcon, 5);
			return new CartRepo();
		} catch (Exception e) {
			throw e;
		}
	}
}

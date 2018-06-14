package GCR.VShop.PageLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import GCR.VShop.BaseUtility.ActionDriver;
import GCR.VShop.ObjectRepo.HomePage;

public class HomeRepo extends ActionDriver {

	public HomePage homepage = new HomePage();

	public void clickLogin() throws Exception {
		try {
			click(homepage.login, 0);
		} catch (Exception e) {
			throw e;
		}
	}

	public void clickCreateAnAccount() throws Exception {
		try {
			click(homepage.createAccount, 0);
		} catch (Exception e) {
			throw e;
		}

	}

	public ProductRepo selectProduct(By productlocator) throws Exception {
		try {
			click(productlocator, 10);

		} catch (Exception e) {
			click(By.xpath("//div[@class='subscribe_popup_head']/div"), 5);
			click(productlocator, 10);
		}
		return new ProductRepo();
	}

	public CartRepo clickOnCartIcon() throws Exception {
		try {
			click(homepage.cartIcon, 5);
		} catch (Exception e) {
			click(By.xpath("//div[@class='subscribe_popup_head']/div"), 5);
			click(homepage.cartIcon, 5);

		}
		return new CartRepo();
	}

	public int getPrice(By locator) throws Exception {
		String str2 = "";
		try {
			String str1 = getText(locator);
			if (str1.contains("र")) {
				str2 = str1.substring(1).replaceAll(",", "");
			} else {
				str2 = str1.replaceAll(",", "");
			}
			return Integer.parseInt(str2);
		} catch (Exception e) {
			throw e;
		}

	}

	public int getPrice(WebElement element) throws Exception {
		String str2 = "";
		try {
			String str1 = getText(element);
			if (str1.contains("र")) {
				str2 = str1.substring(1).replaceAll(",", "");
			} else {
				str2 = str1.replaceAll(",", "");
			}
			return Integer.parseInt(str2);
		} catch (Exception e) {
			throw e;
		}
	}

	public void selectSubcategory(By locator) {
		try {
			click(locator, 5);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public void selectBrand(By locator) {
		try {
			click(locator, 5);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public void setPrice(String minprice, String maxprice) {
		try {
			click(homepage.price, 5);
			/*clear(homepage.minPrice);
			type(homepage.minPrice, minprice);
			waitForPageLoadComplete();
			clear(homepage.maxPrice);
			type(homepage.maxPrice, maxprice);
			waitForPageLoadComplete();*/
			((JavascriptExecutor)driver).executeScript("arguments[0].value="+minprice+";", driver.findElement(homepage.minPrice));
			((JavascriptExecutor)driver).executeScript("arguments[0].value="+maxprice+";", driver.findElement(homepage.maxPrice));
			type(homepage.minPrice, " ");
			Thread.sleep(2000);
			waitForPageLoadComplete();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}
 
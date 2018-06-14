package TestScripts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import GCR.VShop.BaseUtility.ActionDriver;
import GCR.VShop.PageLibrary.HomeRepo;

public class Home_Test extends ActionDriver {

	HomeRepo homerepo = new HomeRepo();
    SoftAssert soft = new SoftAssert();

	@Test(enabled = false)
	public void verifyBrandFilter() throws Exception {
		openUrl("https://global.awok.com");
		mouseHover(driver, homerepo.homepage.categoryIcon);
		click(homerepo.homepage.mobileIcon, 5);
		waitForPageLoadComplete();
		Assert.assertEquals(true, driver.getTitle().contains("Mobile Phones & Accessories"));
		homerepo.selectBrand(By.xpath("//label[span[text()='Samsung']]/span[1]"));
		waitForPageLoadComplete();
		List<WebElement> titles = driver.findElements(By.xpath("//a[@class='productslist_item_link']/div[1]/p"));
		for (WebElement element : titles) {
			if (!element.getText().contains("Samsung")) {
				Assert.assertTrue(false);
			}

		}
	}
	
	@Test
	public void verifyPriceFilter() throws Exception {

		openUrl("https://global.awok.com");
		waitForPageLoadComplete();
		mouseHover(driver, homerepo.homepage.categoryIcon);
		click(homerepo.homepage.mobileIcon, 5);
		waitForPageLoadComplete();
		soft.assertEquals(true, driver.getTitle().contains("Mobile Phones & Accessories"));
		homerepo.setPrice("5000", "10000");
		List<WebElement> prices = driver.findElements(By.xpath("//a[@class='productslist_item_link']/div[3]/div/div[1]"));
		int count = 0;
		for (WebElement element : prices) {
			int price = homerepo.getPrice(element);
			System.out.println(price);
			if (price <= 10000 && price >= 5000) {
				count++;
			}
		}
		soft.assertEquals(count, prices.size());
		soft.assertAll();
	}
}

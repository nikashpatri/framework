package TestScripts;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import GCR.VShop.BaseUtility.ActionDriver;
import GCR.VShop.PageLibrary.CartRepo;
import GCR.VShop.PageLibrary.HomeRepo;
import GCR.VShop.PageLibrary.MemberLoginRepo;
import GCR.VShop.PageLibrary.ProductRepo;
import GCR.VShop.PageLibrary.UserRegistrationRepo;

public class Cart_Test extends ActionDriver {
	HomeRepo homerepo = new HomeRepo();
	CartRepo cartrepo;
	ProductRepo productrepo;
	MemberLoginRepo memberloginrepo;
	UserRegistrationRepo userregrepo;

	@Test
	public void verifyProductAddedToCart() throws Exception {
		//openBrowser();
		openUrl("https://global.awok.com");
		productrepo = homerepo.selectProduct(homerepo.homepage.productName1);
		switchWindow();
		productrepo.clickAddToCart();
		cartrepo = productrepo.clickOnCartIcon();
		Assert.assertEquals(true,
				driver.findElement(By.xpath("//p[contains(text(),'HTC Ceramic Plated Mini Hair')]")).isDisplayed());
	}

	@Test
	public void verifyPriceIsMatchingInCart() throws Exception {
		// openBrowser();
		openUrl("https://global.awok.com");
		scrollToView(homerepo.homepage.productName1);
		int homePagePrice = homerepo.getPrice(
				By.xpath("//div[a[div[div[p[contains(text(),'HTC Ceramic Plated Mini Hair')]]]]]/div/div/span[1]"));
		productrepo = homerepo.selectProduct(homerepo.homepage.productName1);
		switchWindow();
		productrepo.clickAddToCart();
		cartrepo = productrepo.clickOnCartIcon();
		int cartPrice = homerepo.getPrice(By.xpath("//span[@class='num_price']"));
		Assert.assertEquals(cartPrice, homePagePrice);

	}

	@Test
	public void verifyRemoveFromCart() throws Exception {
		// openBrowser();
		openUrl("https://global.awok.com");
		productrepo = homerepo.selectProduct(homerepo.homepage.productName1);
		switchWindow();
		productrepo.clickAddToCart();
		cartrepo = productrepo.clickOnCartIcon();
		cartrepo.clickRemoveFromCart();
		waitForPageLoadComplete();
		try {
			driver.findElement(By.xpath("p[contains(text(),'HTC Ceramic Plated Mini Hair')]"));
			Assert.assertTrue(false);
		} catch (NoSuchElementException e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void verifyMultipleItemAddedToCart() throws Exception {
		Set<String> set = new HashSet<String>();
		// openBrowser();
		openUrl("https://global.awok.com");
		set.add(driver.findElement(By.xpath("//p[contains(text(),'HTC Ceramic Plated Mini Hair')]")).getText());
		productrepo = homerepo.selectProduct(homerepo.homepage.productName1);
		switchWindow();
		productrepo.clickAddToCart();
		driver.close();
		switchToParentWindow();
		set.add(driver.findElement(By.xpath("//p[contains(text(),'Mic (HBQ-i7)')]")).getText());
		productrepo = homerepo.selectProduct(homerepo.homepage.productName2);
		switchWindow();
		productrepo.clickAddToCart();
		driver.close();
		switchToParentWindow();
		cartrepo = homerepo.clickOnCartIcon();
		List<WebElement> cartItems = driver.findElements(By.xpath("//ul[@class='car_items_list']/descendant::p"));
		ArrayList<String> list = new ArrayList<String>(set);
		for (int i = 0; i < list.size(); i++) {
			boolean flag = false;
			for (int j = 0; j < cartItems.size(); j++) {
				if (list.get(i).equals(cartItems.get(j).getText())) {
					flag = true;
					break;
				}
			}
			if (flag == false)
				Assert.assertTrue(false, "Item missing in the cart: " + list.get(i));
		}
	}

	@Test
	public void verifyTotalPriceUpdatedAfterRemoveFromCart() throws Exception {
		// openBrowser();
		openUrl("https://global.awok.com");
		productrepo = homerepo.selectProduct(homerepo.homepage.productName1);
		switchWindow();
		productrepo.clickAddToCart();
		driver.close();
		switchToParentWindow();
		productrepo = homerepo.selectProduct(homerepo.homepage.productName2);
		switchWindow();
		productrepo.clickAddToCart();
		driver.close();
		switchToParentWindow();
		cartrepo = homerepo.clickOnCartIcon();
		int removedProductPrice = homerepo
				.getPrice(By.xpath("//div[div[p[contains(text(),'Mic (HBQ-i7)')]]]/div[5]/span"));
		int totalPrice1 = homerepo.getPrice(cartrepo.cartpage.totalPrice);
		cartrepo.clickRemoveFromCart("Mic (HBQ-i7)");
		waitForPageLoadComplete();
		Assert.assertEquals(homerepo.getPrice(cartrepo.cartpage.totalPrice), totalPrice1 - removedProductPrice);

	}

	@Test
	public void verifyCartCountValueDisplayedCorrectly() throws Exception {
		// openBrowser();
		openUrl("https://global.awok.com");
		productrepo = homerepo.selectProduct(homerepo.homepage.productName1);
		switchWindow();
		productrepo.clickAddToCart();
		driver.close();
		switchToParentWindow();
		productrepo = homerepo.selectProduct(homerepo.homepage.productName2);
		switchWindow();
		productrepo.clickAddToCart();
		driver.close();
		switchToParentWindow();
		cartrepo = homerepo.clickOnCartIcon();
		List<WebElement> dropdowns = driver.findElements(cartrepo.cartpage.quantityDropDown);
		int productCount = 0;
		for (WebElement element : dropdowns) {
			String value = getFirstSelectedValue(element);
			int count = Integer.parseInt(value);
			productCount = productCount + count;
		}
		Assert.assertEquals(productCount,
				Integer.parseInt(driver.findElement(cartrepo.cartpage.commonCount).getText()));
	}

	@Test
	public void verifyQuantityOfItemCanBeUpdatedInCart() throws Exception {
		// openBrowser();
		openUrl("https://global.awok.com");
		productrepo = homerepo.selectProduct(homerepo.homepage.productName2);
		switchWindow();
		productrepo.clickAddToCart();
		switchToParentWindow();
		cartrepo = homerepo.clickOnCartIcon();
		List<WebElement> dropdowns = driver.findElements(cartrepo.cartpage.quantityDropDown);
		String initialValue = getFirstSelectedValue(dropdowns.get(0));
		select(cartrepo.cartpage.quantityDropDown, "3");
		waitForPageLoadComplete();
		String finalValue = getFirstSelectedValue(driver.findElements(cartrepo.cartpage.quantityDropDown).get(0));
		Assert.assertNotEquals(finalValue, initialValue);
	}

	@Test
	public void verifySubtotalPriceChangedAfterQuantityChanged() throws Exception {
		// openBrowser();
		openUrl("https://global.awok.com");
		productrepo = homerepo.selectProduct(homerepo.homepage.productName1);
		switchWindow();
		productrepo.clickAddToCart();
		switchToParentWindow();
		cartrepo = homerepo.clickOnCartIcon();
		int initialPrice = homerepo.getPrice(By.xpath("//span[@class='num_sub']"));
		select(cartrepo.cartpage.quantityDropDown, "4");
		waitForPageLoadComplete();
		int finalPrice = homerepo.getPrice(By.xpath("//span[@class='num_sub']"));
		Assert.assertEquals(finalPrice, initialPrice * 4);

	}

	@Test
	public void verifyContinueShoppingNavigatesToHomepage() throws Exception {
		// openBrowser();
		openUrl("https://global.awok.com");
		productrepo = homerepo.selectProduct(homerepo.homepage.productName1);
		switchWindow();
		productrepo.clickAddToCart();
		cartrepo = productrepo.clickOnCartIcon();
		waitForPageLoadComplete();
		cartrepo.clickContinueShopping();
		waitForPageLoadComplete();
		// Assert.assertEquals(driver.getTitle(), "Online Shopping India | Shop
		// online best offers & daily deals in IN | Awok.com");
		Assert.assertEquals(driver.getCurrentUrl(), "https://global.awok.com/");
	}

	@Test
	public void verifyCheckOutBeforeSignIn() throws Exception {
		// openBrowser();
		openUrl("https://global.awok.com");
		productrepo = homerepo.selectProduct(homerepo.homepage.productName1);
		switchWindow();
		productrepo.clickAddToCart();
		cartrepo = productrepo.clickOnCartIcon();
		cartrepo.clickSecureCheckOut();
		waitForPageLoadComplete();
		Assert.assertEquals(driver.getCurrentUrl(), "https://global.awok.com/checkout/");
		String actual = driver.findElement(By.xpath("//h3[contains(@class,'checkout_heading1')]")).getText();
		Assert.assertEquals(actual, "Before you place your order ! Sign In");
	}

	@Test
	public void verifySignInLinkInCartNavigatesToMemberLogin() throws Exception {
		// openBrowser();
		openUrl("https://global.awok.com");
		productrepo = homerepo.selectProduct(homerepo.homepage.productName1);
		switchWindow();
		productrepo.clickAddToCart();
		cartrepo = productrepo.clickOnCartIcon();
		waitForPageLoadComplete();
		memberloginrepo = cartrepo.clickSignIn();
		waitForPageLoadComplete();
		Assert.assertEquals(true, driver.findElement(memberloginrepo.memberloginpage.enterYourPassword).isDisplayed());

	}

	@Test
	public void verifySignUpLinkInCartNavigatesToUserRegistration() throws Exception {
		// openBrowser();
		openUrl("https://global.awok.com");
		productrepo = homerepo.selectProduct(homerepo.homepage.productName1);
		switchWindow();
		productrepo.clickAddToCart();
		cartrepo = productrepo.clickOnCartIcon();
		waitForPageLoadComplete();
		userregrepo = cartrepo.clickSignUp();
		waitForPageLoadComplete();
		Assert.assertEquals(true, driver.findElement(userregrepo.userRegPage.fullName).isDisplayed());
	}

	@Test
	public void verifyTheTotalPriceDisplayedCorrectly() throws Exception {
		// openBrowser();
		openUrl("https://global.awok.com");
		productrepo = homerepo.selectProduct(homerepo.homepage.productName1);
		switchWindow();
		productrepo.clickAddToCart();
		driver.close();
		switchToParentWindow();
		productrepo = homerepo.selectProduct(homerepo.homepage.productName2);
		switchWindow();
		productrepo.clickAddToCart();
		driver.close();
		switchToParentWindow();
		cartrepo = homerepo.clickOnCartIcon();
		List<WebElement> list = driver.findElements(By.xpath("//span[@class='num_sub']"));
		int expectedPrice = 0;
		for (WebElement element : list) {
			int price = homerepo.getPrice(element);
			expectedPrice = expectedPrice + price;
		}
		Assert.assertEquals(homerepo.getPrice(cartrepo.cartpage.totalPrice), expectedPrice);
	}

	@Test
	public void verifyQantityDropDownNotExceedsMaxLimit() throws Exception {
		// openBrowser();
		openUrl("https://global.awok.com");
		productrepo = homerepo.selectProduct(homerepo.homepage.productName1);
		switchWindow();
		productrepo.clickAddToCart();
		driver.close();
		switchToParentWindow();
		cartrepo = homerepo.clickOnCartIcon();
		waitForPageLoadComplete();
		WebElement element = driver.findElement(cartrepo.cartpage.quantityDropDown);
		List<WebElement> options = getAllDropDownOptions(element);
		Assert.assertTrue(options.size() <= 9, "Dropdown Max Limit Exceeded >>> Should not exceed 9");
	}

	@Test
	public void verifyNeedHelpLinkNavigatesToAboutPage() throws Exception {
		// openBrowser();
		openUrl("https://global.awok.com");
		productrepo = homerepo.selectProduct(homerepo.homepage.productName1);
		switchWindow();
		productrepo.clickAddToCart();
		driver.close();
		switchToParentWindow();
		cartrepo = homerepo.clickOnCartIcon();
		click(cartrepo.cartpage.needHelp, 5);
		switchWindow();
		waitForPageLoadComplete();
		Assert.assertEquals(true, driver.getCurrentUrl().contains("https://global.awok.com/about/"));
		Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='How to reach Us?']")).isDisplayed(),
				"How to reach Us?>> Not Visible");
	}

	@Test(enabled = false)
	public void verifyCartPageFooterLinksNavigateToTermsPage() throws Exception {
		CartRepo cartrepo = new CartRepo();
		SoftAssert soft = new SoftAssert();
		openBrowser();
		openUrl("https://global.awok.com/cart/");
		click(cartrepo.cartpage.privacyPolicy, 5);
		switchWindow();
		waitForPageLoadComplete();
		soft.assertEquals(true, driver.getCurrentUrl().contains("https://global.awok.com/help-info/privacy-policy/"),
				"privacypolicy failed");
		driver.close();
		switchToParentWindow();
		click(cartrepo.cartpage.termsAndServices, 5);
		switchWindow();
		waitForPageLoadComplete();
		soft.assertEquals(true, driver.getCurrentUrl().contains("https://global.awok.com/help-info/terms/"),
				"terms failed");
		driver.close();
		switchToParentWindow();
		click(cartrepo.cartpage.WarrantyAndReturns, 5);
		switchWindow();
		waitForPageLoadComplete();
		soft.assertEquals(true, driver.getCurrentUrl().contains("https://global.awok.com/help-info/terms/"),
				"warranty failed");
		driver.close();
		switchToParentWindow();
		soft.assertAll();
	}

	@Test
	public void VerifyFooterTextsInCartAreDisplayedCorrectly() throws Exception {
		ArrayList<String> texts = new ArrayList<String>();
		texts.add("Multiple Payment Methods");
		texts.add("Quick & Efficient Delivery");
		texts.add("14 Days Return Policy");
		texts.add("100% Genuine Products");
		openUrl("https://global.awok.com");
		waitForPageLoadComplete();
		cartrepo=homerepo.clickOnCartIcon();
		waitForPageLoadComplete();
		List<WebElement> list1 = findElements(cartrepo.cartpage.FooterTexts);
		boolean  teststatus=false;
		for (String str : texts) {
			boolean flag = false;
			for (WebElement element : list1) {
				if (str.equals(element.getText())) {
					flag = true;
					break;
				}
			}
			if (flag == false) {
				System.out.println(str + "  is Not Displayed in the footer correctly");
				teststatus=true;
			}
		}
         if(teststatus==true)  
        	 Assert.assertTrue(false);
	}
	
	@Test
	public void verifyErrorMessageDisplayedForInvalidCoupon() throws Exception{
		openUrl("https://global.awok.com");
		waitForPageLoadComplete();
		productrepo=homerepo.selectProduct(homerepo.homepage.productName1);
		switchWindow();
		productrepo.clickAddToCart();
		driver.close();
		switchToParentWindow();
		cartrepo=homerepo.clickOnCartIcon();
		waitForPageLoadComplete();
		Assert.assertEquals(true, driver.findElement(cartrepo.cartpage.enterCouponCode).isEnabled());
		type(cartrepo.cartpage.enterCouponCode, "4356"); //invalid coupon
		click(cartrepo.cartpage.useCouponButton, 5);
		waitForPageLoadComplete();
		String errorMessage=getText(cartrepo.cartpage.couponErrorMessage);
		Assert.assertEquals(errorMessage, "Coupon invalid or expired");
		Assert.assertEquals(false, driver.findElement(cartrepo.cartpage.enterCouponCode).isEnabled(),"coupon code block is not disabled");
		click(cartrepo.cartpage.remove, 5);
		waitForPageLoadComplete();
		String value=driver.findElement(cartrepo.cartpage.enterCouponCode).getAttribute("value");
		Assert.assertEquals(true, value.isEmpty());
	}
	
	
	@Test
	public void verify_ViewMyCouponLink_BeforeSignIn() throws Exception{
		openUrl("https://global.awok.com");
		productrepo=homerepo.selectProduct(homerepo.homepage.productName1);
		switchWindow();
		productrepo.clickAddToCart();
		driver.close();
		switchToParentWindow();
		cartrepo=homerepo.clickOnCartIcon();
		click(cartrepo.cartpage.viewMyCoupons, 5);
		waitForPageLoadComplete();
		Assert.assertTrue(driver.getCurrentUrl().contains("https://global.awok.com/user/login/"), "navigated to a different url");
	}
	
	@Test
	public void verify_MouseHoverOnCartIcon_BeforeSignIn() throws Exception{
	   openUrl("https://global.awok.com");
	   productrepo= homerepo.selectProduct(homerepo.homepage.productName1);
	   switchWindow();
	   productrepo.clickAddToCart();
	   driver.close();
	   switchToParentWindow();
	   driver.navigate().refresh();
	   waitForPageLoadComplete();
	   mouseHover(driver, homerepo.homepage.cartIcon);
	   isVisible(homerepo.homepage.completePurchase, 5);
	   click(homerepo.homepage.completePurchase, 5);
	   waitForPageLoadComplete();
	   Assert.assertEquals(true, driver.getCurrentUrl().contains("global.awok.com/checkout"));
	}
	
	@Test
	public void verify_MouseHoverOnCart_WhenCartEmpty() throws Exception{
		 openUrl("https://global.awok.com");
		 waitForPageLoadComplete();
		 mouseHover(driver, homerepo.homepage.cartIcon);
		 isVisible(homerepo.homepage.cartEmptyDescription, 5);
		 Assert.assertEquals(true, getText(homerepo.homepage.cartEmptyDescription).contains("Your shopping cart is currently empty"));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

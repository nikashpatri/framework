package GCR.VShop.ObjectRepo;

import org.openqa.selenium.By;

public class CartPage {
	public By secureCheckout = By.xpath("//a[text()='Secure Checkout']");
	public By continueShopping = By.xpath("//a[text()='Continue Shopping']");
	public By removeFromCart = By.xpath("//input[@name='Update']");
	public By totalPrice=By.xpath("//span[@class='value']/em");
	public By quantityDropDown=By.xpath("//select[@class='field_count']");
	public By commonCount=By.xpath("//p[@class='count_common']/b");
	public By signIn=By.xpath("//a[text()='SIGN IN']");
	public By signUp=By.xpath("//a[text()='SIGN UP']");
	public By needHelp=By.xpath("//a[text()='Need Help?']");
	public By privacyPolicy=By.xpath("//a[text()='Privacy Policy']");
	public By termsAndServices=By.xpath("//a[text()='Terms of Service']");
	public By WarrantyAndReturns=By.xpath("//a[text()='Warranty & Returns']");
	public By FooterTexts=By.xpath("//div[@class!='item customer_support']/div/p");
	public By enterCouponCode=By.xpath("//input[contains(@placeholder,'Enter your coupon')]");
	public By useCouponButton=By.xpath("//input[@name='SUBMIT_COUPON']");
	public By remove=By.xpath("//input[@name='SUBMIT_COUPON' and @value='Remove']");
	public By couponErrorMessage=By.xpath("//div[@class='coupen_error_msg']/span");
	public By viewMyCoupons=By.xpath("//a[text()='View my coupons']");
}

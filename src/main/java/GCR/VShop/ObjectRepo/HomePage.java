package GCR.VShop.ObjectRepo;

import org.openqa.selenium.By;

public class HomePage {

	public By login = By.xpath("//div[@class='contentContainer']/div[1]/a[1]/u");
	public By createAccount = By.linkText("create an account");
	public By productName1 = By.xpath("//a[div[div[p[contains(text(),'Cooking Plastic Kitchen')]]]]/div[1]/img");
	public By productName2 = By.xpath("//a[div[div[p[contains(text(),'Mic (HBQ-i7)')]]]]/div[1]");
	public By cartIcon = By.xpath("//span[@class='cart_icon']");
	public By completePurchase=By.xpath("//div[@class='header_cartbox']/descendant::a[text()='COMPLETE PURCHASE']");
	public By cartEmptyDescription=By.xpath("//div[@class='header_cartbox']/div/p[2]");
	public By categoryIcon=By.xpath("//a[@class='drop_link']");
	public By mobileIcon=By.xpath("//li[contains(@class,'mobile_icon')]");
	
	public By mobilePhones=By.xpath("//label[text()='Mobile Phones']");
	public By mobilePhoneAccessories=By.xpath("//label[text()='Mobile Phone Accessories']");
	public By smartWatchesWearables=By.xpath("//label[text()='Smart Watches & Wearables']");
	
	public By minPrice=By.xpath("//input[@name='min-price']");
	public By maxPrice=By.xpath("//input[@name='max-price']");
	public By price=By.xpath("//a[h5[text()='Price']]");
	
}

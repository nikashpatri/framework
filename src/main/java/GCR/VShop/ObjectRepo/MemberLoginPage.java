package GCR.VShop.ObjectRepo;

import org.openqa.selenium.By;

public class MemberLoginPage {
        
	public By logIn=By.xpath("//input[@value='Log In']");
	public By forgotPassword=By.xpath("//a[text()='Click here']");
	public By enterYourEmailAddress=By.xpath("//div[label[text()='Enter your email address*']]/input");
	public By enterYourPassword=By.xpath("//div[label[text()='Enter your password*']]/input");
}

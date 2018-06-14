package GCR.VShop.ObjectRepo;

import org.openqa.selenium.By;

public class LoginPage {
	public By Email_Address = By.xpath(".//*[@id='bodyContent']/div[2]/div/form/table/tbody/tr[1]/td[2]/input");
	public By Password = By.xpath(".//*[@id='bodyContent']/div[2]/div/form/table/tbody/tr[2]/td[2]/input");
	public By Sign_In = By.xpath(".//*[@id='tdb5']");
}

package GCR.VShop.BaseUtility;

import org.openqa.selenium.WebDriver;

public class LocalDriverManager {
	private static ThreadLocal<WebDriver> webdriver = new ThreadLocal<WebDriver>();

	public static WebDriver getDriver() {
		return webdriver.get();
	}

	public static void setDriver(WebDriver driver) {
		webdriver.set(driver);
	}
}
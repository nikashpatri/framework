package GCR.VShop.BaseUtility;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.http.protocol.HttpRequestInterceptorList;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.TestNG;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ActionDriver {

	public static WebDriver driver = null;
	public Utility utility = new Utility();
	public ExtentReports extentreport;
	public ExtentTest extenttest;
	public SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
	public String screenShot_path = null;
	WebDriverWait wait = null;
	FluentWait<WebDriver> fluentwait;
	String parentwindow = "";

	/*@AfterSuite
	public void afterSuite(){
		TestNG runner=new TestNG();
		List<String> list=new ArrayList<String>();
		File file=new File(System.getProperty("user.dir")+"/test-output/testng-failed.xml");
		if(file.exists()){
		list.add(file.toString());
		runner.setTestSuites(list);
		runner.run();
		}
	}*/
	
	@BeforeTest
	public void beforTest() throws Exception {
		File file = new File(System.getProperty("user.dir") + "/ExtentReport");
		if (!file.exists())
			file.mkdirs();

		extentreport = new ExtentReports(file + "/" + sdf.format(new Date()) + ".html", true);
	}

	@AfterTest
	public void afterTest() {

		extentreport.flush();
		extentreport.close();
	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		extenttest = extentreport.startTest(method.getName());
		openBrowser();
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if (result.getStatus() == result.SUCCESS) {
			extenttest.log(LogStatus.PASS, result.getName() + " test passed");
		} else if (result.getStatus() == result.FAILURE) {
			
			screenShot_path = utility.captureScreenShot(driver, result.getName());
			extenttest.log(LogStatus.FAIL, result.getName() + " test failed >>> Reason: " + result.getThrowable(),
					extenttest.addScreenCapture(screenShot_path));
		} else if (result.getStatus() == result.SKIP) {
			extenttest.log(LogStatus.SKIP, result.getName() + " test Skipped >>> Reason: " + result.getThrowable());
		}

		extentreport.endTest(extenttest);
       //  driver.close();
	}

	public void openBrowser() {
		
			System.setProperty("webdriver.chrome.driver", "D:\\selenium\\lib\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		/*else if (browsername.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", "D:\\selenium\\lib\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}*/
	}

	public void openUrl(String url) throws Exception {
		try {
			driver.get(url);
		} catch (Exception e) {
			throw e;
		}
	}

	public void click(By locator, int timeout) throws Exception {
		try {
			WebElement element = isVisible(locator, timeout);
			highlightElement(driver.findElement(locator));
			element.click();
		} catch (Exception e) {
			scrollToView(locator);
			WebElement element = isVisible(locator, timeout);
			highlightElement(driver.findElement(locator));
			element.click();
		}
	}

	public void type(By locator, String value) throws Exception {
		try {
			driver.findElement(locator).sendKeys(value);
		} catch (Exception e) {
			throw e;
		}

	}

	public void mouseHover(WebDriver driver, By locator) throws Exception {
		Actions action = new Actions(driver);
		try {
			action.moveToElement(driver.findElement(locator)).build().perform();
		} catch (Exception e) {
			throw e;
		}
	}

	public void clear(By locator) throws Exception {
		try {
			WebElement element=isVisible(locator, 5);
			element.clear();
		} catch (Exception e) {
			throw e;
		}
	}

	public void select(By locator, String value) throws Exception {
		try {
			new Select(driver.findElement(locator)).selectByVisibleText(value);
		} catch (Exception e) {
			throw e;
		}

	}

	public String getText(By locator) throws Exception {
		try {
			String value = driver.findElement(locator).getText();
			return value;
		} catch (Exception e) {
			throw e;
		}
	}

	public void scrollToView(By locator) throws Exception {
		try {
			WebElement element = driver.findElement(locator);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			throw e;
		}

	}

	public void switchWindow() {
		parentwindow = driver.getWindowHandle();
		ArrayList<String> list = new ArrayList<String>(driver.getWindowHandles());
		for (int i = 0; i < list.size(); i++) {
			if (!list.get(i).equals(parentwindow)) {
				driver.switchTo().window(list.get(i));
				break;
			}
		}
		// System.out.println(list.size());
	}

	public void switchToParentWindow() {
		driver.switchTo().window(parentwindow);
	}

	public WebElement isVisible(By locator, int timeout) {
		try {
			WebElement element = waitFor(ExpectedConditions.visibilityOfElementLocated(locator), timeout);
			return element;
		} catch (TimeoutException e) {
			System.out.println(e.toString());
		}
          return null;
	}

	public String getFirstSelectedValue(WebElement element) throws Exception {
		try {
			WebElement firstElement = new Select(element).getFirstSelectedOption();
			return firstElement.getText();
		} catch (Exception e) {
			throw e;
		}
	}

	public ArrayList<String> checkBrokenLinks(List<WebElement> links) throws IOException {
		ArrayList<String> brokenLinks = new ArrayList<String>();
		for (WebElement element : links) {
			URL url = new URL(element.getText());
			HttpURLConnection httpconnection = (HttpURLConnection) url.openConnection();
			if (httpconnection.getResponseCode() != 200) {
				brokenLinks.add(element.getText());
			}
		}
		return brokenLinks;
	}

	public String getText(WebElement element) {
		String value = "";
		try {
			value = element.getText();
			return value;
		} catch (NoSuchElementException e) {
			throw e;
		}

	}

	public List<WebElement> getAllDropDownOptions(WebElement element) throws Exception {
		try {
			List<WebElement> options = new Select(element).getOptions();
			return options;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<WebElement> findElements(By commonlocator) throws Exception{
		try{
		List<WebElement> elements=driver.findElements(commonlocator);
		return elements;
		}
		catch(Exception e){
			throw e;
		}
	}

	public void highlightElement(WebElement element){
		try{
		((JavascriptExecutor) driver).executeScript("arguments[0].style.backgroundColor='blue';",element);
		}
		catch(Exception e){
			e.getMessage();
		}
	}
	
	public void waitForPageLoadComplete() {
		Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				String state = ((JavascriptExecutor) driver).executeScript("return document.readyState").toString();
				if (state.equalsIgnoreCase("complete")) {
					return true;
				}
				return false;
			}
		};

		new FluentWait<WebDriver>(driver).until(function);
	}

	private WebElement waitFor(ExpectedCondition<WebElement> condition, int timeout) {
		wait = new WebDriverWait(driver, timeout);
		return wait.until(condition);
	}

}

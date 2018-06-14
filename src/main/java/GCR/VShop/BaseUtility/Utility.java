package GCR.VShop.BaseUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utility {

	public String captureScreenShot(WebDriver driver, String methodname) throws IOException {
		String path = System.getProperty("user.dir") + "/Screenshot";
		File file = new File(path);
		if (!file.exists())
			file.mkdirs();
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String date = sdf.format(new Date());
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(file + "/" + methodname + "_" + date + ".png");
		FileUtils.copyFile(src, dest);
		return path + "/" + methodname + "_" + date + ".png";

	}

	public String getTranslation(String key, String language) throws IOException {

		Properties prop = new Properties();
		File file = new File(System.getProperty("user.dir") + "src/resources/languages" + language + ".properties");
		FileInputStream fis = new FileInputStream(file);
		prop.load(fis);
		fis.close();
		return prop.getProperty(key);
	}

	public Properties readPropertiesFile(String filepath) throws IOException{
		
		Properties prop=new Properties();
		File file=new File(filepath);
		FileInputStream fis=new FileInputStream(file);
		prop.load(fis);
		return prop;
	}
}










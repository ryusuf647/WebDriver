package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class baseclass {
	protected static WebDriver cd;
	private static final String PROP_PATH;
	private static FileInputStream FIS;
	protected static Properties PROP;
	protected static JavascriptExecutor js;
	protected static List<String> prodnames;
	protected static List<Double> prodprices;
	
	static	{
		PROP_PATH = System.getProperty("user.dir")+"\\src\\main\\java\\resources\\global.properties";
		try {
			FIS = new FileInputStream(PROP_PATH);
			PROP = new Properties();
			try {
				PROP.load(FIS);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	protected static WebDriver initializeDriver()	{
		switch ((String)PROP.get("browser")) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver",PROP.getProperty("cdpath"));
			cd = new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver",PROP.getProperty("ffdpath"));
			cd = new FirefoxDriver();
			break;
		case "edge":
			System.setProperty("webdriver.edge.driver",PROP.getProperty("edpath"));
			cd = new EdgeDriver();
			break;
		}
		js = (JavascriptExecutor)cd;
		cd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return cd;
	}
	
	public static String getScreenCapture(String test)	{
		String path = System.getProperty("user.dir")+PROP.getProperty("screencaps")+test+".jpg";
		TakesScreenshot scrShot =((TakesScreenshot)cd);
		File srcf=scrShot.getScreenshotAs(OutputType.FILE);		
		try {
			FileUtils.copyFile(srcf, new File(path));
		} catch (IOException e) {}
		return path;
	}
}
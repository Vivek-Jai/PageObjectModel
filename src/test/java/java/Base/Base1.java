package java.Base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Utils.TestUtils;

public class Base1 {
	public static Properties prop;
	public static FileInputStream fis ;
	public static WebDriver driver;
	
	public Base1()
	{
		try {
                prop = new Properties();
			    fis = new FileInputStream("C:\\sel\\POM\\src\\main\\java\\Configfile\\Config");
			prop.load(fis);
			} catch (IOException e) {
			e.printStackTrace();
			}
		
		}
	
	public static void browserStart()
	{
		String browsername= prop.getProperty("browser");
		
		if(browsername.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");	
			driver = new ChromeDriver(); 
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		
	}
}

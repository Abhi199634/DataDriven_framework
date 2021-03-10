package Resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {
	public static WebDriver driver;
	public static String baseurl="https://staging.letsventure.com/signin";

public WebDriver initializedriver() throws IOException {
	
	Properties prop = new Properties();
	FileInputStream fis= new FileInputStream("C:\\eclipse-workspace\\Venture\\src\\main\\java\\resources\\java\\data.properties");
	prop.load(fis);
	String browsername= prop.getProperty("browser");
	System.out.println(browsername);
	if(browsername.equals("chrome")) {
		System.setProperty("webdriver.chrome.driver", "C:\\eclipse-workspace\\Venture\\executablefiles\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	else if(browsername.equals("firefox")) {
		driver = new FirefoxDriver();
		
	}
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//return driver;
	return driver;
	
}


public void getScreenshot(String result) throws NullPointerException {
	
	File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	try {
		FileUtils.copyFile(src,new File("C:\\eclipse-workspace\\Venture\\screenshots\\"+result+".png"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}


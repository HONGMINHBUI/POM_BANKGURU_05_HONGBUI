package commons;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AbstractTest {
	WebDriver driver;

	public WebDriver openMultiBrowser(String browserName, String url) {  
		  if (browserName.equals("chrome")) {
			  driver = new ChromeDriver();
		  } else if (browserName.equals("firefox")) {
			  driver = new FirefoxDriver();
		  } else {
			  ChromeOptions options = new ChromeOptions();
			  options.addArguments("headless");
			  options.addArguments("window-size=1440x900");
			  driver = new ChromeDriver(options);
		  }	  
		  driver.get(url);
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  return driver;
	}
}
package commons;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;

public class AbstractTest {
	WebDriver driver;
	protected final Log log; 
	
	protected AbstractTest() {
		log = LogFactory.getLog(getClass());
	}
	
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
	
	public int randomNumber() {
		Random random = new Random();
		int number = random.nextInt(999999);
		return number;
	}
	
	protected void closeBrowser(WebDriver driver) {
    	try {
			// Detect OS (Windows/ Linux/ MAC)
			String osName = System.getProperty("os.name").toLowerCase();
			String cmd = "";
			driver.quit();
			if (driver.toString().toLowerCase().contains("chrome")) {
				// Kill process
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill chromedriver";
				} else {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				}
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			}
			if (driver.toString().toLowerCase().contains("internetexplorer")) {
				cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			}
			// log.info("---------- QUIT BROWSER SUCCESS ----------");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private boolean checkPassed(boolean condition) {
    	boolean pass = true;
		try {
			if (condition == true)
				log.info("===PASSED===");
			else
				log.info("===FAILED===");
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;
			log.info(e.getMessage());
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
//			 Add status (true/ false) to Report (ReportNG)
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkPassed(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			if (condition == true)
				log.info("===PASSED===");
			else
				log.info("===FAILED===");
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			log.info(e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
		} catch (Throwable e) {
			pass = false;
			log.info(e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}
}
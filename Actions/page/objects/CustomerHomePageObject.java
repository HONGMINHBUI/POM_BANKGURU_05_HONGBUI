package page.objects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import page.ui.CustomerHomePageUI;;

public class CustomerHomePageObject extends AbstractPage {
	WebDriver driver;

	public CustomerHomePageObject(WebDriver mapdriver) {
		driver = mapdriver;
	}
	
	public boolean isCustomerHomePageDisplayed() {
		waitForVisible(driver, CustomerHomePageUI.WELCOME_TEXT);
		return isControlDisplayed(driver, CustomerHomePageUI.WELCOME_TEXT);
	}
	
}
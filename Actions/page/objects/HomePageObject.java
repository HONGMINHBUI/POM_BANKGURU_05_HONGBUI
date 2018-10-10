package page.objects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import page.ui.HomePageUI;

public class HomePageObject extends AbstractPage {
	WebDriver driver;

	public HomePageObject(WebDriver mapdriver) {
		driver = mapdriver;
	}
	
	public boolean isHomePageDisplayed() {
		waitForVisible(driver, HomePageUI.WELCOME_TEXT);
		return isControlDisplayed(driver, HomePageUI.WELCOME_TEXT);
	}
	
}
package page.objects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import page.ui.DepositPageUI;

public class DepositPageObject extends AbstractPage {
	WebDriver driver;

	public DepositPageObject(WebDriver mapdriver) {
		driver = mapdriver;
	}
	
	public boolean isDepositTitlePageDisplayed(String AccountIDValue) {
		waitForVisible(driver, DepositPageUI.DEPOSIT_TITLE_PAGE_TEXT, AccountIDValue);
		return isControlDisplayed(driver, DepositPageUI.DEPOSIT_TITLE_PAGE_TEXT, AccountIDValue);
	}

}

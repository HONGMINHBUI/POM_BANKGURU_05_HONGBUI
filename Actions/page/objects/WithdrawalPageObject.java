package page.objects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import page.ui.WithdrawalPageUI;

public class WithdrawalPageObject extends AbstractPage {
	WebDriver driver;

	public WithdrawalPageObject(WebDriver mapdriver) {
		driver = mapdriver;
	}

	public boolean isWithdrawalTitlePageDisplayed(String AccountIDValue) {
		waitForVisible(driver, WithdrawalPageUI.WITHDRAWAL_TITLE_PAGE_TEXT, AccountIDValue);
		return isControlDisplayed(driver, WithdrawalPageUI.WITHDRAWAL_TITLE_PAGE_TEXT, AccountIDValue);
	}
}

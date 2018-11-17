package page.objects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import page.ui.BalanceEnquiryPageUI;

public class BalanceEnquiryPageObject extends AbstractPage {
	WebDriver driver;

	public BalanceEnquiryPageObject(WebDriver mapdriver) {
		driver = mapdriver;
	}

	public boolean isBalanceEnquiryTitlePageDisplayed(String AccountIDValue) {
		waitForVisible(driver, BalanceEnquiryPageUI.BALANCE_ENQUIRY_TITLE_PAGE_TEXT, AccountIDValue);
		return isControlDisplayed(driver, BalanceEnquiryPageUI.BALANCE_ENQUIRY_TITLE_PAGE_TEXT, AccountIDValue);
	}
	
	public String getTextBalance() {
		waitForVisible(driver, BalanceEnquiryPageUI.BALANCE);
		return getTextElement(driver, BalanceEnquiryPageUI.BALANCE);
	}
}

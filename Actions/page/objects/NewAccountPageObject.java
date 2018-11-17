package page.objects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import page.ui.NewAccountPageUI;

public class NewAccountPageObject extends AbstractPage {
	WebDriver driver;

	public NewAccountPageObject(WebDriver mapdriver) {
		driver = mapdriver;
	}
	
	public void selectAccountType(String accType) {
		selectItemInDropdown(driver, NewAccountPageUI.ACCOUNT_TYPE, accType);
	}
	
	public boolean isNewAccountGeneratedSuccess() {
		waitForVisible(driver, NewAccountPageUI.NEWACCOUNT_SUCCESS_TEXT);
		return isControlDisplayed(driver, NewAccountPageUI.NEWACCOUNT_SUCCESS_TEXT);
	}
	
	public String getTextAccountID() {
		waitForVisible(driver, NewAccountPageUI.ACCOUNT_ID);
		return getTextElement(driver, NewAccountPageUI.ACCOUNT_ID);
	}
	
	public String getTextCurrentAmount() {
		waitForVisible(driver, NewAccountPageUI.CURRENT_AMOUNT);
		return getTextElement(driver, NewAccountPageUI.CURRENT_AMOUNT);
	}

}

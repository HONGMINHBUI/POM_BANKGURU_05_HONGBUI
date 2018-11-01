package page.objects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import page.ui.AbstractPageUI;
import page.ui.NewCustomerPageUI;

public class NewCustomerPageObject extends AbstractPage {
	WebDriver driver;

	public NewCustomerPageObject(WebDriver mapdriver) {
		driver = mapdriver;
	}
	
	public void clickToGenderCheckBox(WebDriver driver, String value) {
		waitForVisible(driver, AbstractPageUI.DYNAMIC_GENDER_CHECKBOX, value);
		clickToElement(driver, AbstractPageUI.DYNAMIC_GENDER_CHECKBOX, value);
	}
	
	public void sendKeyToTextAreaField(WebDriver driver, String value) {
		sendKeyToElement(driver, NewCustomerPageUI.ADDRESS_TEXTAREA, value);
	}
	
	public boolean isNewCustomerRegisteredSuccessfully() {
		waitForVisible(driver, NewCustomerPageUI.NEWCUSTOMER_REGISTER_SUCCESS_TEXT);
		return isControlDisplayed(driver, NewCustomerPageUI.NEWCUSTOMER_REGISTER_SUCCESS_TEXT);
	}
	
	public String getNewCustomerIdText() {
		waitForVisible(driver, NewCustomerPageUI.NEWCUSTOMER_ID);
		return getTextElement(driver, NewCustomerPageUI.NEWCUSTOMER_ID);
	}

}

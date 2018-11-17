package page.objects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import page.ui.EditCustomerPageUI;

public class EditCustomerPageObject extends AbstractPage {
	WebDriver driver;

	public EditCustomerPageObject(WebDriver mapdriver) {
		driver = mapdriver;
	}
	
	public void sendKeyToEditAddressTextAreaField(WebDriver driver, String value) {
		sendKeyToElement(driver, EditCustomerPageUI.EDIT_ADDRESS_TEXTAREA, value);
	}
	
	public boolean isEditCustomerSuccessfully() {
		waitForVisible(driver, EditCustomerPageUI.EDITCUSTOMER_SUCCESS_TEXT);
		return isControlDisplayed(driver, EditCustomerPageUI.EDITCUSTOMER_SUCCESS_TEXT);
	}

}

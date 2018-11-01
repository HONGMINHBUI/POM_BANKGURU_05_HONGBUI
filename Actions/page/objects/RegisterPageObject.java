package page.objects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import page.ui.AbstractPageUI;
import page.ui.RegisterPageUI;

public class RegisterPageObject extends AbstractPage{
	WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}
	//input email address
	public void inputEmailAddress(String emailAddress) {
		waitForVisible(driver, AbstractPageUI.DYNAMIC_INPUT_FIELD, emailAddress);
		sendKeyToDynamicInputElement(driver, "emailid", emailAddress);
	}
	//click Submit button
	public void clickSubmitButton() {
		clickToDynamicElement(driver, "btnLogin");
	}
	//get UserID text
	public String getUserIdText() {
		waitForVisible(driver, RegisterPageUI.USER_ID);
		return getTextElement(driver, RegisterPageUI.USER_ID);
	}
	//get Password text
	public String getPasswordText() {
		return getTextElement(driver, RegisterPageUI.PASSWORD);
	}
}
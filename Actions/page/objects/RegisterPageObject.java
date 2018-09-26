package page.objects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import page.ui.RegisterPageUI;

public class RegisterPageObject extends AbstractPage{
	WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}
	//input email address
	public void inputEmailAddress(String emailAddress) {
		waitForVisible(driver, RegisterPageUI.EMAIL_ADDRESS);
		sendKeyToElement(driver, RegisterPageUI.EMAIL_ADDRESS, emailAddress);
	}
	//click Submit button
	public void clickSubmitButton() {
		clickToElement(driver, RegisterPageUI.SUBMIT_BUTTON);
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

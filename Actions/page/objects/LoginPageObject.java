package page.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import commons.AbstractPage;
import page.ui.LoginPageUI;

public class LoginPageObject extends AbstractPage {
	WebDriver driver;

	public LoginPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}

	public void openURL(String url) {
		openAnyUrl(driver, url);
	}
	
	public String getLoginPageURL() {
		return getCurrentUrl(driver);
	}
	
	//click here link
	public void clickHereLink() {
		clickToElement(driver, LoginPageUI.HERE_LINK);
	}
	
	public void inputUserIDTextbox(String userID) {
		waitForVisible(driver, LoginPageUI.USER_ID_TEXTBOX); 
		sendKeyToElement(driver, LoginPageUI.USER_ID_TEXTBOX, userID );
	}
	
	public void inputPasswordTextbox(String password) {
		sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
	}
	
	public void clickLoginButton() {
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
	}
}

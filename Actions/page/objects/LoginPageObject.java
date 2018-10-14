package page.objects;

import org.openqa.selenium.WebDriver;
import commons.AbstractPage;
import page.ui.LoginPageUI;

public class LoginPageObject extends AbstractPage {
	WebDriver driver;

	public LoginPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}

	public LoginPageObject openURL(String url) {
		openAnyUrl(driver, url);
		return PageManageDriver.getLoginPage(driver);
	}
	
	public String getLoginPageURL() {
		return getCurrentUrl(driver);
	}

	public void clickHereLink() {
		clickToElement(driver, LoginPageUI.HERE_LINK);
	}
	
	public RegisterPageObject clickHereLinkByJavaS() {
		executeForWebElement(driver, LoginPageUI.HERE_LINK);
		return PageManageDriver.getRegisterPage(driver);
	}
	
	public void inputUserIDTextbox(String userID) {
		waitForVisible(driver, LoginPageUI.USER_ID_TEXTBOX); 
		sendKeyToElement(driver, LoginPageUI.USER_ID_TEXTBOX, userID );
	}
	
	public void inputPasswordTextbox(String password) {
		sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
	}
	
	public HomePageObject clickLoginButton() {
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageManageDriver.getHomePage(driver);
	}
}
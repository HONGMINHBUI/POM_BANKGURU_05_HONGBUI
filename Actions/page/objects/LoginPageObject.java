package page.objects;

import org.openqa.selenium.WebDriver;
import commons.AbstractPage;
import page.ui.AbstractPageUI;
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

	public RegisterPageObject clickHereLink() {
		clickToDynamicElement(driver, "here");
		return PageManageDriver.getRegisterPage(driver);
	}
	
	public RegisterPageObject clickHereLinkByJavaS() {
		executeForWebElement(driver, LoginPageUI.HERE_LINK);
		return PageManageDriver.getRegisterPage(driver);
	}
	
	public void inputUserIDTextbox(String userID) {
		waitForVisible(driver, AbstractPageUI.DYNAMIC_INPUT_FIELD, userID); 
		sendKeyToDynamicInputElement(driver, "uid", userID);
	}
	
	public void inputPasswordTextbox(String password) {
		waitForVisible(driver, AbstractPageUI.DYNAMIC_INPUT_FIELD, password); 
		sendKeyToDynamicInputElement(driver, "password", password);
	}
	
	public HomePageObject clickLoginButton() {
		clickToDynamicElement(driver, "btnLogin");
		return PageManageDriver.getHomePage(driver);
	}
	
	public CustomerHomePageObject clickLogin() {
		clickToDynamicElement(driver, "btnLogin");
		return PageManageDriver.getCustomerHomePage(driver);
	}
}
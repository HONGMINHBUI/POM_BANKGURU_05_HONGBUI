package page.objects;

import org.openqa.selenium.WebDriver;

public class PageManageDriver {
	private static LoginPageObject loginPage;
	private static RegisterPageObject registerPage;
	private static HomePageObject homePage;
	private static NewCustomerPageObject newCustomerPage;
	private static EditCustomerPageObject editCustomerPage;
	private static DeleteCustomerPageObject deleteCustomerPage;
	private static NewAccountPageObject newAccountPage;
	
	public static LoginPageObject getLoginPage(WebDriver driver) {
		if (loginPage == null) {
			loginPage = new LoginPageObject(driver);
		} return loginPage;
	}

	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		if (registerPage == null) {
			registerPage = new RegisterPageObject(driver);
		} return registerPage;
	}
	
	public static HomePageObject getHomePage(WebDriver driver) {
		if (homePage == null) {
			homePage = new HomePageObject(driver);
		} return homePage;
	}
	
	public static NewCustomerPageObject getNewCustomerPage(WebDriver driver) {
		if (newCustomerPage == null) {
			newCustomerPage = new NewCustomerPageObject(driver);
		} return newCustomerPage;
	}
	
	public static EditCustomerPageObject getEditCustomerPage(WebDriver driver) {
		if (editCustomerPage == null) {
			editCustomerPage = new EditCustomerPageObject(driver);
		} return editCustomerPage;
	}
	
	public static DeleteCustomerPageObject getDeleteCustomerPage(WebDriver driver) {
		if (deleteCustomerPage == null) {
			deleteCustomerPage = new DeleteCustomerPageObject(driver);
		} return deleteCustomerPage;
	}
	
	public static NewAccountPageObject getNewAccountPage(WebDriver driver) {
		if (newAccountPage == null) {
			newAccountPage = new NewAccountPageObject(driver);
		} return newAccountPage;
	}
}

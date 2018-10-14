package com.bankguru.user;

import java.util.Random;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import commons.AbstractTest;
import page.objects.DeleteCustomerPageObject;
import page.objects.EditCustomerPageObject;
import page.objects.HomePageObject;
import page.objects.LoginPageObject;
import page.objects.NewAccountPageObject;
import page.objects.NewCustomerPageObject;
import page.objects.PageManageDriver;
import page.objects.RegisterPageObject;

public class user_01_RegisterAndLogin_V4 extends AbstractTest {
	WebDriver driver;
	private String userID, password, loginPageURL;
	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;
	private HomePageObject homePage;
	private NewCustomerPageObject newCustomerPage;
	private EditCustomerPageObject editCustomerPage;
	private DeleteCustomerPageObject deleteCustomerPage;
	private NewAccountPageObject newAccountPage;

	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String urlName) {
		driver = openMultiBrowser(browserName, urlName);
		loginPage = PageManageDriver.getLoginPage(driver);
	}

	@Test
	public void TC01_Register() {
		loginPageURL = loginPage.getLoginPageURL();
		registerPage = loginPage.clickHereLinkByJavaS();
		registerPage.inputEmailAddress("hong" + randomNumber() + "@gmail.com");
		registerPage.clickSubmitButton();
		userID = registerPage.getUserIdText();
		password = registerPage.getPasswordText();
	}

	@Test
	public void TC02_Login() {
		loginPage = loginPage.openURL(loginPageURL);
		loginPage.inputUserIDTextbox(userID);
		loginPage.inputPasswordTextbox(password);
		homePage = loginPage.clickLoginButton();
		Assert.assertTrue(homePage.isHomePageDisplayed());
	}
	
	@Test
	public void TC03_PageNavigation() {
		newCustomerPage = homePage.openNewCustomerPage(driver);
		editCustomerPage = newCustomerPage.openEditCustomerPage(driver);
		deleteCustomerPage = editCustomerPage.openDeleteCustomerPage(driver);
		newAccountPage = deleteCustomerPage.openNewAccountPage(driver);
		homePage = newAccountPage.openHomePage(driver);
	}
	
	public int randomNumber() {
		Random random = new Random();
		int number = random.nextInt(999999);
		return number;
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
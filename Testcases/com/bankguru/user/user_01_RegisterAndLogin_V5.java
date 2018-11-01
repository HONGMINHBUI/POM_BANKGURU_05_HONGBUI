package com.bankguru.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import commons.AbstractTest;
import page.objects.CustomerHomePageObject;
import page.objects.HomePageObject;
import page.objects.LoginPageObject;
import page.objects.NewCustomerPageObject;
import page.objects.PageManageDriver;
import page.objects.RegisterPageObject;

public class user_01_RegisterAndLogin_V5 extends AbstractTest {
	WebDriver driver;
	private String userID, password, loginPageURL;
	private String customerName, DOB, address, city, state, pin, mobileNumber, email, pass, newCustomerID;
	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;
	private HomePageObject homePage;
	private NewCustomerPageObject newCustomerPage;
	private CustomerHomePageObject customerHomePage;

	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String urlName) {
		driver = openMultiBrowser(browserName, urlName);
		loginPage = PageManageDriver.getLoginPage(driver);
		customerName = "Hong Bui";
		DOB = "03/05/1986";
		address = "Cogges";
		city = "Witney";
		state = "Oxfordshire";
		pin = "123789";
		mobileNumber = "09435224378";
		email = "hong" + randomNumber() + "@gmail.com";
		pass = "123456";
	}
	
	@Test
	public void TC01_Register() {
		loginPageURL = loginPage.getLoginPageURL();
		registerPage = loginPage.clickHereLinkByJavaS();
		registerPage.sendKeyToDynamicInputElement(driver, "emailid", email);
		registerPage.clickToDynamicElement(driver, "btnLogin");
		userID = registerPage.getUserIdText();
		password = registerPage.getPasswordText();
	}

	@Test
	public void TC02_LoginAsAdmin() {
		loginPage = loginPage.openURL(loginPageURL);
		loginPage.sendKeyToDynamicInputElement(driver, "uid", userID);
		loginPage.sendKeyToDynamicInputElement(driver, "password", password);
		homePage = loginPage.clickLoginButton();
		Assert.assertTrue(homePage.isHomePageDisplayed());
	}
	
	@Test
	public void TC03_CreatANewCustomer_DynamicElement() throws Exception {
		newCustomerPage = homePage.openNewCustomerPage(driver);
		newCustomerPage.sendKeyToDynamicInputElement(driver, "name", customerName);
		newCustomerPage.clickToGenderCheckBox(driver, "f");
		newCustomerPage.sendKeyToDynamicInputElement(driver, "dob", DOB);
		newCustomerPage.sendKeyToTextAreaField(driver, address);
		newCustomerPage.sendKeyToDynamicInputElement(driver, "city", city);
		newCustomerPage.sendKeyToDynamicInputElement(driver, "state", state);
		newCustomerPage.sendKeyToDynamicInputElement(driver, "pinno", pin);
		newCustomerPage.sendKeyToDynamicInputElement(driver, "telephoneno", mobileNumber);
		newCustomerPage.sendKeyToDynamicInputElement(driver, "emailid", email);
		newCustomerPage.sendKeyToDynamicInputElement(driver, "password", pass);
		newCustomerPage.clickToDynamicElement(driver, "sub");
		Thread.sleep(2000);
		Assert.assertTrue(newCustomerPage.isNewCustomerRegisteredSuccessfully());
		newCustomerID = newCustomerPage.getNewCustomerIdText();
		loginPage = newCustomerPage.openLogOutPage(driver);
	}

	@Test
	public void TC04_LoginAsNewCustomer() {
		loginPage.sendKeyToDynamicInputElement(driver, "uid", newCustomerID);
		loginPage.sendKeyToDynamicInputElement(driver, "password", pass);
		customerHomePage = loginPage.clickLogin();
		Assert.assertTrue(customerHomePage.isCustomerHomePageDisplayed());
	}
	
	@AfterClass
	public void afterClass() {
		closeBrowser(driver);
	}
}
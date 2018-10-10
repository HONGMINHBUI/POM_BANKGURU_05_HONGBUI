package com.bankguru.user;

import java.util.Random;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import commons.AbstractTest;
import page.objects.HomePageObject;
import page.objects.LoginPageObject;
import page.objects.RegisterPageObject;

public class user_01_RegisterAndLogin_V3 extends AbstractTest {
	WebDriver driver;
	private String userID, password, loginPageURL;
	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;
	private HomePageObject homePage;

	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String urlName) {
		driver = openMultiBrowser(browserName, urlName);
		loginPage = new LoginPageObject(driver);
		registerPage = new RegisterPageObject(driver);
		homePage = new HomePageObject(driver);
	}

	@Test
	public void TC01_Register() {
		loginPageURL = loginPage.getLoginPageURL();
		loginPage.clickHereLinkByJavaS();
		registerPage.inputEmailAddress("hong" + randomNumber() + "@gmail.com");
		registerPage.clickSubmitButton();
		userID = registerPage.getUserIdText();
		password = registerPage.getPasswordText();
	}

	@Test
	public void TC02_Login() {
		loginPage.openURL(loginPageURL);
		loginPage.inputUserIDTextbox(userID);
		loginPage.inputPasswordTextbox(password);
		loginPage.clickLoginButton();
		Assert.assertTrue(homePage.isHomePageDisplayed());
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
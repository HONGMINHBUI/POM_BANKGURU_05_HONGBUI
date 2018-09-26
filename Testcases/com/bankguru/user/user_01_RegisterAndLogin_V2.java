package com.bankguru.user;

import java.util.Random;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.objects.LoginPageObject;
import page.objects.RegisterPageObject;

public class user_01_RegisterAndLogin_V2 {
	WebDriver driver;
	private String userID, password, loginPageURL;
	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		loginPage = new LoginPageObject(driver);
		registerPage = new RegisterPageObject(driver);
		driver.manage().window().maximize();
		loginPage.openURL("http://demo.guru99.com/v4");
		loginPageURL = loginPage.getLoginPageURL();
	}

	@Test
	public void TC01_Register() {
		loginPage.clickHereLink();
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

		// Assert.assertTrue(abtractPage.isControlDisplayed(driver,
		// "//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]"));
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

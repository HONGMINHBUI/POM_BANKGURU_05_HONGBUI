package com.bankguru.user;

import java.util.Random;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.AbstractPage;

public class user_01_RegisterAndLogin_V1 {
	WebDriver driver;
	private String userID, password, LoginPageURL;
	private AbstractPage abtractPage;
	  
	@BeforeClass
  public void beforeClass() {
		driver = new FirefoxDriver();
		abtractPage = new AbstractPage();
		driver.manage().window().maximize();
		abtractPage.openAnyUrl(driver, "http://demo.guru99.com/v4");
	  }	
 
	@Test
  public void TC01_Register() {
		LoginPageURL = abtractPage.getCurrentUrl(driver);
		abtractPage.clickToElement(driver, "//a[text()='here']");
		abtractPage.sendKeyToElement(driver, "//input[@name='emailid']", "hong" + randomNumber() + "@gmail.com");
		abtractPage.clickToElement(driver, "//input[@name='btnLogin']");
		
		userID = abtractPage.getTextElement(driver, "//td[text()='User ID :']/following-sibling::td");
		password = abtractPage.getTextElement(driver, "//td[text()='Password :']/following-sibling::td");
  }
  
  @Test
  public void TC02_Login() {
	  abtractPage.openAnyUrl(driver, LoginPageURL);
	  abtractPage.sendKeyToElement(driver, "//input[@name='uid']", userID);
	  abtractPage.sendKeyToElement(driver, "//input[@name='password']", password);
	  abtractPage.clickToElement(driver, "//input[@name='btnLogin']");

	  Assert.assertTrue(abtractPage.isControlDisplayed(driver, "//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]"));
  }
  
  public int randomNumber() {
		 Random random = new Random();
		 int number = random.nextInt(999999);
		 return number;	
		 }

  @AfterClass
  public void afterClass() {
	  abtractPage.quitAllBroswer(driver);
  }

}

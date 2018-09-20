package com.bankguru.user;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class user_01_RegisterAndLoginToSystem {
	WebDriver driver;
	WebDriverWait wait;
	String userID, password, LoginPageURL;
	  
	@BeforeClass
  public void beforeClass() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");
	  }	
 
	@Test
  public void TC01_Register() {
		LoginPageURL = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys("hong" + randomNumber() + "@gmail.com");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
  }
  
  @Test
  public void TC02_Login() {
	  driver.get(LoginPageURL);
	  driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userID);
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
	  driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	  Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
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

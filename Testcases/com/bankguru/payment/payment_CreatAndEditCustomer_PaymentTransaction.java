package com.bankguru.payment;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import commons.AbstractTest;
import page.objects.BalanceEnquiryPageObject;
import page.objects.CustomerHomePageObject;
import page.objects.DeleteAccountPageObject;
import page.objects.DeleteCustomerPageObject;
import page.objects.DepositPageObject;
import page.objects.EditCustomerPageObject;
import page.objects.FundTransferPageObject;
import page.objects.HomePageObject;
import page.objects.LoginPageObject;
import page.objects.NewAccountPageObject;
import page.objects.NewCustomerPageObject;
import page.objects.PageManageDriver;
import page.objects.RegisterPageObject;
import page.objects.WithdrawalPageObject;

public class payment_CreatAndEditCustomer_PaymentTransaction extends AbstractTest {
	WebDriver driver;
	private String userID, password, loginPageURL;
	private String customerName, DOB, address, city, state, pin, mobileNumber, email, pass, newCustomerID, editAddress, editCity, editState, editPin, editMobileNumber, editEmail; 
	private String intialDeposit, addedDeposit, withdrawDeposit, transferAmount, accountID, accountID2, description, sum1, sum2, sum3;
	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;
	private HomePageObject homePage;
	private NewCustomerPageObject newCustomerPage;
	private EditCustomerPageObject editCustomerPage;
	private CustomerHomePageObject customerHomePage;
	private DepositPageObject depositPage;
	private FundTransferPageObject fundTransferPage;
	private WithdrawalPageObject withdrawalPage;
	private NewAccountPageObject newAccountPage;
	private BalanceEnquiryPageObject balanceEnquiryPage;
	private DeleteCustomerPageObject deleteCustomerPage;
	private DeleteAccountPageObject deleteAccountPage;

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
		
		editAddress = "Stanlake";
		editCity = "Carterton";
		editState = "New Hamshire";
		editPin = "666999";
		editMobileNumber = "078340348555";
		editEmail = "automation" + randomNumber() + "@gmail.com";
		
		intialDeposit = "50000";
		addedDeposit = "10000";
		withdrawDeposit = "5000";
		transferAmount = "15000";
		description = "automation";
		sum1 = "60000";
		sum2 = "55000";
		sum3 = "40000";
	}
	
	@Test
	public void TC01_RegisterAndLogIn() {
		log.info("TC01_RegisterAndLogIn - Step01: Login");
		loginPageURL = loginPage.getLoginPageURL();
		registerPage = loginPage.clickHereLinkByJavaS();
		registerPage.sendKeyToDynamicInputElement(driver, "emailid", email);
		registerPage.clickToDynamicElement(driver, "btnLogin");
		userID = registerPage.getUserIdText();
		password = registerPage.getPasswordText();
		loginPage = loginPage.openURL(loginPageURL);
		loginPage.sendKeyToDynamicInputElement(driver, "uid", userID);
		loginPage.sendKeyToDynamicInputElement(driver, "password", password);
		homePage = loginPage.clickLoginButton();
		Assert.assertTrue(homePage.isHomePageDisplayed());
	}
	
	@Test
	public void TC02_CreatANewCustomer_DynamicElement() throws Exception {
		log.info("TC02_CreatANewCustomer_DynamicElement - Step01: Create a New Customer");
		newCustomerPage = (NewCustomerPageObject) homePage.openDynamicPage(driver, "New Customer");
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
		loginPage = (LoginPageObject) newCustomerPage.openDynamicPage(driver, "Log out");
	}

	@Test
	public void TC03_LoginAsNewCustomer() {
		loginPage.sendKeyToDynamicInputElement(driver, "uid", newCustomerID);
		loginPage.sendKeyToDynamicInputElement(driver, "password", pass);
		customerHomePage = loginPage.clickLogin();
		Assert.assertTrue(customerHomePage.isCustomerHomePageDisplayed());
		loginPage = (LoginPageObject) customerHomePage.openDynamicPage(driver, "Log out");
	}
	
	@Test
	public void TC04_EditCustomer() throws Exception {
		loginPage.sendKeyToDynamicInputElement(driver, "uid", userID);
		loginPage.sendKeyToDynamicInputElement(driver, "password", password);
		homePage = loginPage.clickLoginButton();
		Assert.assertTrue(homePage.isHomePageDisplayed());
		editCustomerPage = (EditCustomerPageObject) homePage.openDynamicPage(driver, "Edit Customer");
		editCustomerPage.sendKeyToDynamicInputElement(driver, "cusid", newCustomerID);
		editCustomerPage.clickToDynamicElement(driver, "AccSubmit");		
		editCustomerPage.sendKeyToEditAddressTextAreaField(driver, editAddress);
		editCustomerPage.sendKeyToDynamicInputElement(driver, "city", editCity);
		editCustomerPage.sendKeyToDynamicInputElement(driver, "state", editState);
		editCustomerPage.sendKeyToDynamicInputElement(driver, "pinno", editPin);
		editCustomerPage.sendKeyToDynamicInputElement(driver, "telephoneno", editMobileNumber);
		editCustomerPage.sendKeyToDynamicInputElement(driver, "emailid", editEmail);
		editCustomerPage.clickToDynamicElement(driver, "sub");
		Thread.sleep(2000);
		Assert.assertTrue(editCustomerPage.isEditCustomerSuccessfully());
	}
	
	@Test
	public void TC05_AddANewAccount() {		
		newAccountPage = (NewAccountPageObject) editCustomerPage.openDynamicPage(driver, "New Account");
		newAccountPage.sendKeyToDynamicInputElement(driver, "cusid", newCustomerID);
		newAccountPage.selectAccountType("Current");
		newAccountPage.sendKeyToDynamicInputElement(driver, "inideposit", intialDeposit);
		newAccountPage.clickToDynamicElement(driver, "button2");
		Assert.assertTrue(newAccountPage.isNewAccountGeneratedSuccess());
		Assert.assertEquals(newAccountPage.getTextCurrentAmount(), intialDeposit);
		accountID = newAccountPage.getTextAccountID();
	}
	
	@Test
	public void TC06_TransferMoneyIntoAccount() {		
		depositPage = (DepositPageObject) newAccountPage.openDynamicPage(driver, "Deposit");
		depositPage.sendKeyToDynamicInputElement(driver, "accountno", accountID);
		depositPage.sendKeyToDynamicInputElement(driver, "ammount", addedDeposit);
		depositPage.sendKeyToDynamicInputElement(driver, "desc", description);
		depositPage.clickToDynamicElement(driver, "AccSubmit");
		Assert.assertTrue(depositPage.isDepositTitlePageDisplayed(accountID));
		Assert.assertEquals(depositPage.getTextCurrentBalance(driver), sum1);
	}
	
	@Test
	public void TC07_WithdrawMoneyFromAccount() {
		withdrawalPage = (WithdrawalPageObject) depositPage.openDynamicPage(driver, "Withdrawal");
		withdrawalPage.sendKeyToDynamicInputElement(driver, "accountno", accountID);
		withdrawalPage.sendKeyToDynamicInputElement(driver, "ammount", withdrawDeposit);
		withdrawalPage.sendKeyToDynamicInputElement(driver, "desc", description);
		withdrawalPage.clickToDynamicElement(driver, "AccSubmit");
		Assert.assertTrue(withdrawalPage.isWithdrawalTitlePageDisplayed(accountID));
		Assert.assertEquals(withdrawalPage.getTextCurrentBalance(driver), sum2);
	}
	
	@Test
	public void TC08_TransferMoneyToAnotherAccount() {
		newAccountPage = (NewAccountPageObject) withdrawalPage.openDynamicPage(driver, "New Account");
		newAccountPage.sendKeyToDynamicInputElement(driver, "cusid", newCustomerID);
		newAccountPage.selectAccountType("Current");
		newAccountPage.sendKeyToDynamicInputElement(driver, "inideposit", intialDeposit);
		newAccountPage.clickToDynamicElement(driver, "button2");
		Assert.assertTrue(newAccountPage.isNewAccountGeneratedSuccess());
		Assert.assertEquals(newAccountPage.getTextCurrentAmount(), intialDeposit);
		accountID2 = newAccountPage.getTextAccountID();
			
		fundTransferPage = (FundTransferPageObject) newAccountPage.openDynamicPage(driver, "Fund Transfer");
		fundTransferPage.sendKeyToDynamicInputElement(driver, "payersaccount", accountID);
		fundTransferPage.sendKeyToDynamicInputElement(driver, "payeeaccount", accountID2);
		fundTransferPage.sendKeyToDynamicInputElement(driver, "ammount", transferAmount);
		fundTransferPage.sendKeyToDynamicInputElement(driver, "desc", description);
		fundTransferPage.clickToDynamicElement(driver, "AccSubmit");
		Assert.assertTrue(fundTransferPage.isFundTransferedSuccess());
		Assert.assertEquals(fundTransferPage.getTextTransferedAmount(), transferAmount);
	}
	
	@Test
	public void TC09_CheckCurrentAccount() {
		balanceEnquiryPage = (BalanceEnquiryPageObject) fundTransferPage.openDynamicPage(driver, "Balance Enquiry");
		balanceEnquiryPage.sendKeyToDynamicInputElement(driver, "accountno", accountID);
		balanceEnquiryPage.clickToDynamicElement(driver, "AccSubmit");
		Assert.assertTrue(balanceEnquiryPage.isBalanceEnquiryTitlePageDisplayed(accountID));
		Assert.assertEquals(balanceEnquiryPage.getTextBalance(), sum3);
	}
	
	@Test
	public void TC10_DeleteAllAccount() {
		deleteAccountPage = (DeleteAccountPageObject) balanceEnquiryPage.openDynamicPage(driver, "Delete Account");
		deleteAccountPage.sendKeyToDynamicInputElement(driver, "accountno", accountID);
		deleteAccountPage.clickToDynamicElementWithAlert(driver, "AccSubmit");
		Assert.assertEquals(deleteAccountPage.isDeleteAccountSuccessfully(), "Account Deleted Sucessfully");
		homePage = deleteAccountPage.acceptDeleteAlert(driver);
		
		deleteAccountPage = (DeleteAccountPageObject) homePage.openDynamicPage(driver, "Delete Account");
		deleteAccountPage.sendKeyToDynamicInputElement(driver, "accountno", accountID2);
		deleteAccountPage.clickToDynamicElementWithAlert(driver, "AccSubmit");
		Assert.assertEquals(deleteAccountPage.isDeleteAccountSuccessfully(), "Account Deleted Sucessfully");
		homePage = deleteAccountPage.acceptDeleteAlert(driver);
		Assert.assertTrue(homePage.isHomePageDisplayed());
	}
	
	@Test
	public void TC11_DeleteCustomer() {
		deleteCustomerPage = (DeleteCustomerPageObject) homePage.openDynamicPage(driver, "Delete Customer");
		deleteCustomerPage.sendKeyToDynamicInputElement(driver, "cusid", newCustomerID);
		deleteCustomerPage.clickToDynamicElementWithAlert(driver, "AccSubmit");
		Assert.assertEquals(deleteCustomerPage.isDeleteCustomerSuccessfully(), "Customer deleted Successfully");
		homePage = deleteCustomerPage.acceptDeleteAlert(driver);
		Assert.assertTrue(homePage.isHomePageDisplayed());
	}
	
	@AfterClass
	public void afterClass() {
		closeBrowser(driver);
	}
}
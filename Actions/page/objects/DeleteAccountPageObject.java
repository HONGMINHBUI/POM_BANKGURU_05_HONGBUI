package page.objects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class DeleteAccountPageObject extends AbstractPage {
	WebDriver driver;

	public DeleteAccountPageObject(WebDriver mapdriver) {
		driver = mapdriver;
	}
	
	public String isDeleteAccountSuccessfully() {
		return getTextAlert(driver);
	}
	
	public void acceptDeleteAlert() {
		acceptAlert(driver);
	}
}

package page.objects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class DeleteCustomerPageObject extends AbstractPage {
	WebDriver driver;

	public DeleteCustomerPageObject(WebDriver mapdriver) {
		driver = mapdriver;
	}

	public String isDeleteCustomerSuccessfully() {
		return getTextAlert(driver);
	}
}

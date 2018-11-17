package page.objects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import page.ui.FundTransferPageUI;

public class FundTransferPageObject extends AbstractPage {
	WebDriver driver;

	public FundTransferPageObject(WebDriver mapdriver) {
		driver = mapdriver;
	}
	
	public boolean isFundTransferedSuccess() {
		waitForVisible(driver, FundTransferPageUI.FUNDTRANSFER_SUCCESS_TEXT);
		return isControlDisplayed(driver, FundTransferPageUI.FUNDTRANSFER_SUCCESS_TEXT);
	}
	
	public String getTextTransferedAmount() {
		waitForVisible(driver, FundTransferPageUI.TRANSFERED_AMOUNT);
		return getTextElement(driver, FundTransferPageUI.TRANSFERED_AMOUNT);
	}

}

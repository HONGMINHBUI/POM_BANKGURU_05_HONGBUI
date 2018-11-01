package commons;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import page.objects.DeleteCustomerPageObject;
import page.objects.EditCustomerPageObject;
import page.objects.HomePageObject;
import page.objects.LoginPageObject;
import page.objects.NewAccountPageObject;
import page.objects.NewCustomerPageObject;
import page.objects.PageManageDriver;
import page.ui.AbstractPageUI;

public class AbstractPage {
	
	private int longTimeOut = 30;
	private long shortTimeOut = 5;

	public void openAnyUrl(WebDriver driver, String url) {
		driver.get(url);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPreviousPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToNextPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void clickToElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();
	}
	
	public void clickToDynamicElement(WebDriver driver, String fieldName) {
		waitForVisible(driver, AbstractPageUI.DYNAMIC_INPUT_FIELD, fieldName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_INPUT_FIELD, fieldName);
	}
	
	public void clickToElement(WebDriver driver, String locator, String... value) {
		locator = String.format(locator, (Object[]) value);
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();
	}

	public void sendKeyToElement(WebDriver driver, String locator, String value) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.clear();
		element.sendKeys(value);
	}
	
	public void sendKeyToElement(WebDriver driver, String locator, String inputValue, String... value) {
		locator = String.format(locator, (Object[]) value);
		WebElement element = driver.findElement(By.xpath(locator));
		element.clear();
		element.sendKeys(inputValue);
	}
	
	public void sendKeyToDynamicInputElement(WebDriver driver, String inputFieldName, String value) {
		waitForVisible(driver, AbstractPageUI.DYNAMIC_INPUT_FIELD, inputFieldName);
		sendKeyToElement(driver, AbstractPageUI.DYNAMIC_INPUT_FIELD, value, inputFieldName);
	}

	public void selectItemInDropdown(WebDriver driver, String locator, String value) {
		Select select = new Select(driver.findElement(By.xpath(locator)));
		select.selectByVisibleText(value);
	}
	
	public void selectItemInCustomDropdown(WebDriver driver, WebDriverWait wait, String dropdown, String listItems, String valueItem) throws Exception {
		  WebElement dropdownElement = driver.findElement(By.xpath(dropdown));
		  wait = new WebDriverWait(driver, longTimeOut);
		  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",dropdownElement);
		  dropdownElement.click();
		  List<WebElement> allItems = driver.findElements(By.xpath(listItems));
		  wait.until(ExpectedConditions.visibilityOfAllElements(allItems));		  
		  for (WebElement item : allItems) {
			  if (item.getText().trim().equals(valueItem)) {
				  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",item);
				  Thread.sleep(3000);	
				  item.click();
				  Thread.sleep(3000);
				  break;
			  }
		  }
	  }

	public void getFirstItemSelected(WebDriver driver, String locator) {
		Select select = new Select(driver.findElement(By.xpath(locator)));
		select.getFirstSelectedOption();
	}

	public String getAttributeValue(WebDriver driver, String locator, String attributeName) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getAttribute(attributeName);
	}

	public String getTextElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getText();
	}
	
	public String getTextElement(WebDriver driver, String locator, String... value) {
		locator = String.format(locator, (Object[]) value);
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getText();
	}

	public int getSizeElement(WebDriver driver, String locator) {
		List<WebElement> elements = driver.findElements(By.xpath(locator));
		return elements.size();
	}

	public void checkTheCheckBox(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void unCheckTheCheckBox(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isControlDisplayed(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isDisplayed();
	}
	
	public boolean isControlDisplayed(WebDriver driver, String locator, String... value) {
		locator = String.format(locator, (Object[]) value);
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isDisplayed();
	}

	public boolean isControlSelected(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isSelected();
	}
	
	public boolean isControlSelected(WebDriver driver, String locator, String... value) {
		locator = String.format(locator, (Object[]) value);
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isSelected();
	}

	public boolean isControlEnabled(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isEnabled();
	}
	
	public boolean isControlEnabled(WebDriver driver, String locator, String... value) {
		locator = String.format(locator, (Object[]) value);
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isEnabled();
	}

	public void acceptAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public void cancelAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public String getTextAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}

	public void sendkeyToAlert(WebDriver driver, String value) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(value);
	}

	public void switchWindowByID(WebDriver driver, String ParentGUID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(ParentGUID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	public boolean closeAllWithoutParentWindows(WebDriver driver, String parentGUID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentGUID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentGUID);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}

	public void switchToIframe(WebDriver driver, String locator) {
		WebElement iframe = driver.findElement(By.xpath(locator));
		driver.switchTo().frame(iframe);
	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		WebElement button = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.doubleClick(button).perform();
	}

	public void moveMouseToElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	public void rightClickToElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.contextClick(element).perform();
	}

	public void dragAndDrop(WebDriver driver, String locatorSource, String locatorTarget) {
		WebElement source = driver.findElement(By.xpath(locatorSource));
		WebElement target = driver.findElement(By.xpath(locatorTarget));
		Actions action = new Actions(driver);
		action.dragAndDrop(source, target).perform();
	}
	
	public void keyPress(WebDriver driver, String locator, Keys keyName) {
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.sendKeys(element, keyName);
	}

	public void clickAndHold(WebDriver driver, String locator) {
		List<WebElement> selectable = driver.findElements(By.xpath(locator));
		Actions action = new Actions(driver);
		action.clickAndHold(selectable.get(0)).moveToElement(selectable.get(3)).release().build().perform();
	}

	public void upload1File(WebDriver driver, String fileName) {
		String projectDirectory = System.getProperty("user.dir");
		String linkUpLoad = projectDirectory + "/images/" + fileName;
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(linkUpLoad);
	}

	public void uploadMultipleFiles(WebDriver driver, String fileName01, String fileName02) {
		String projectDirectory = System.getProperty("user.dir");
		String linkUpLoad01 = projectDirectory + "/images/" + fileName01;
		String linkUpLoad02 = projectDirectory + "/images/" + fileName02;
		String MultipleUploadLink[] = { linkUpLoad01, linkUpLoad02 };
		for (int i = 0; i < MultipleUploadLink.length; i++) {
			WebElement AddFileButton = driver.findElement(By.xpath("//input[@type='file']"));
			AddFileButton.sendKeys(MultipleUploadLink[i]);
		}
	}

	public Object executeForBrowserElement(WebDriver driver, String javaSript) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(javaSript);
	}

	public Object executeForWebElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].click();", element);
	}
	
	public Object executeForWebElement(WebDriver driver, String locator, String... value) {
		locator = String.format(locator, (Object[]) value);
		WebElement element = driver.findElement(By.xpath(locator));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].click();", element);
	}

	public Object scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void highlightElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='6px groove red'", element);
	}

	public Object removeAttributeInDOM(WebDriver driver, String locator, String attribute) {
		WebElement element = driver.findElement(By.xpath(locator));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
	}
	
	public Object scrollToElement (WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void waitForPresence (WebDriver driver, String locator) {
		WebDriverWait wait = new WebDriverWait(driver, longTimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
	}
	
	public void waitForPresence (WebDriver driver, String locator, String... value) {
		locator = String.format(locator, (Object[]) value);
		WebDriverWait wait = new WebDriverWait(driver, longTimeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
	}
	
	public void waitForVisible (WebDriver driver, String locator) {
		WebDriverWait wait = new WebDriverWait(driver, longTimeOut);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	}
	
	public void waitForVisible (WebDriver driver, String locator, String... value) {
		locator = String.format(locator, (Object[]) value);
		WebDriverWait wait = new WebDriverWait(driver, longTimeOut);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	}
	
	public void waitForInvisible (WebDriver driver, String locator) {
		WebDriverWait wait = new WebDriverWait(driver, longTimeOut);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
	}
	
	public void waitForInvisible (WebDriver driver, String locator, String... value) {
		locator = String.format(locator, (Object[]) value);
		WebDriverWait wait = new WebDriverWait(driver, longTimeOut);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
	}
	
	public void waitForClickAble (WebDriver driver, String locator) {
		WebDriverWait wait = new WebDriverWait(driver, longTimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
	}
	
	public void waitForClickAble (WebDriver driver, String locator, String... value) {
		locator = String.format(locator, (Object[]) value);
		WebDriverWait wait = new WebDriverWait(driver, longTimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
	}
	
	public void waitForAlertPresence (WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, longTimeOut);
		wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public boolean isControlUndisplayed(WebDriver driver, String locator) {
    	Date date = new Date();
		System.out.println("Started time = " + date.toString());
		overrideGlobalTimeout(driver, shortTimeOut);
		List<WebElement> elements = driver.findElements(By.xpath(locator));
		if (elements.size() == 0) {
			date = new Date();
			System.out.println("End time = " + date.toString());
			overrideGlobalTimeout(driver, longTimeOut);
			return true;
		} else {
			date = new Date();
			System.out.println("End time = " + date.toString());
			overrideGlobalTimeout(driver, longTimeOut);
			return false;
		}
	}

	public boolean isControlUndisplayed(WebDriver driver, String locator, String... value) {
		Date date = new Date();
		System.out.println("Started time = " + date.toString());
		overrideGlobalTimeout(driver, shortTimeOut);
		locator = String.format(locator, (Object[]) value);
		List<WebElement> elements = driver.findElements(By.xpath(locator));
		if (elements.size() == 0) {
			date = new Date();
			System.out.println("End time = " + date.toString());
			overrideGlobalTimeout(driver, longTimeOut);
			return true;
		} else {
			date = new Date();
			System.out.println("End time = " + date.toString());
			overrideGlobalTimeout(driver, longTimeOut);
			return false;
		}
	}

	public void overrideGlobalTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}
	
	public void quitAllBroswer (WebDriver driver) {
		driver.quit();
	}
	
	public HomePageObject openHomePage(WebDriver driver) {
		waitForVisible(driver, AbstractPageUI.DYNAMIC_LINK, "Manager");
		clickToElement(driver, AbstractPageUI.DYNAMIC_LINK, "Manager");
		return PageManageDriver.getHomePage(driver);
	}
	
	public NewCustomerPageObject openNewCustomerPage(WebDriver driver) {
		waitForVisible(driver, AbstractPageUI.DYNAMIC_LINK, "New Customer");
		clickToElement(driver, AbstractPageUI.DYNAMIC_LINK, "New Customer");
		return PageManageDriver.getNewCustomerPage(driver);
	}

	public EditCustomerPageObject openEditCustomerPage(WebDriver driver) {
		waitForVisible(driver, AbstractPageUI.DYNAMIC_LINK, "Edit Customer");
		clickToElement(driver, AbstractPageUI.DYNAMIC_LINK, "Edit Customer");
		return PageManageDriver.getEditCustomerPage(driver);
	}

	public NewAccountPageObject openNewAccountPage(WebDriver driver) {
		waitForVisible(driver, AbstractPageUI.DYNAMIC_LINK, "New Account");
		clickToElement(driver, AbstractPageUI.DYNAMIC_LINK, "New Account");
		return PageManageDriver.getNewAccountPage(driver);
	}
	
	public DeleteCustomerPageObject openDeleteCustomerPage(WebDriver driver) {
		waitForVisible(driver, AbstractPageUI.DYNAMIC_LINK, "Delete Customer");
		clickToElement(driver, AbstractPageUI.DYNAMIC_LINK, "Delete Customer");
		return PageManageDriver.getDeleteCustomerPage(driver);
	}
	
	public LoginPageObject openLogOutPage(WebDriver driver) {
		waitForVisible(driver, AbstractPageUI.DYNAMIC_LINK, "Log out");
		clickToElement(driver, AbstractPageUI.DYNAMIC_LINK, "Log out");
		acceptAlert(driver);
		return PageManageDriver.getLoginPage(driver);
	}
	
}
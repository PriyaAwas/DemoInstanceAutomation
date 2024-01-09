package sew.ai.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import sew.ai.config.Configuration;
import sew.ai.helpers.reporters.ExtentLogger;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.*;

public class Page {
	private static final Logger log = LogManager.getLogger(Page.class);
	private static int EXPLICIT_WAIT = 5000;

	public static WebDriver driver;

	public Page(WebDriver driver) {
		this.driver = driver;
		EXPLICIT_WAIT = Integer.parseInt(Configuration.toString("explicitTimeout"));
	}

	public static void pause(int timeOut) {
		try {
			Thread.sleep(timeOut);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static String getCurrentUrl() {
		log.info("Fetching the current URL.");
		log.info("The Current URL is: " + driver.getCurrentUrl());
		return driver.getCurrentUrl().toLowerCase();
	}

	public static String getCurrentTitle() {
		log.info("Fetching the current title.");
		log.info("The Current Title is: " + driver.getTitle());
		return driver.getTitle();
	}

	public static void click(By locator) {
		log.info("Preparing for element to click.");
		waitForElementToBeClickable(locator);
		getWebElement(locator).click();
		log.info("Element is clicked successfully.");
	}

	public static void click(WebElement element) {
		log.info("Preparing for element to click.");
		waitForElementToBeClickable(element);
		element.click();
		log.info("Element is clicked successfully.");
	}

	/**
	 * This method is used to enter the value into the text input field using
	 * javascript executor.
	 *
	 * @param inputField
	 * @param inputValue
	 */
	public void enterTextUsingJsExecutor(WebElement inputField, String inputValue) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='" + inputValue + "';", inputField);
	}

	public static void check(WebElement element) {
		log.info("Preparing for element to click.");
		waitForElementToBeVisible(element);
		if (!element.isSelected()) {
			// element.click();
			waitForElementToBeClickable(element);
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].click();", element);
		}
		log.info("Element is clicked successfully.");
	}

	public static void unCheck(WebElement element) {
		log.info("Preparing for element to click.");
		waitForElementToBeVisible(element);
		if (element.isSelected()) {
			// element.click();
			waitForElementToBeClickable(element);
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].click();", element);
		}
		log.info("Element is clicked successfully.");
	}

	public static void clickWithoutWait(WebElement element) {
		log.info("Preparing for element to click.");
		element.click();
		log.info("Element is clicked successfully.");
	}

	public static void clickWithJSExecutor(WebElement element) {
		log.info("Clicking element {} " + element + "using Javascript executor.");
		waitForElementToBeClickable(element);
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].click();", element);
		log.info("Element {} " + element + " clicked successfully.");
	}

	public static void scrollPageToElement(WebElement element) throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", element);
	}

	public static void sendKeys(By locator, String keys) {
		log.info("Wait for element {} " + locator + " to be present in the DOM for duration of {} " + EXPLICIT_WAIT);
		WebElement element = waitForElementPresence(locator);
		log.info("Clearing the element {} " + locator + " before sending the keys.");
		element.clear();
		log.info("Sending the following keys {} " + keys + " to the element {} " + locator);
		element.sendKeys(keys);
	}

	public static void sendKeys(WebElement element, String keys) {
		log.info("Wait for element {} " + element + " to be present in the DOM for duration of {} " + EXPLICIT_WAIT);
		waitForElementToBeVisible(element);
		log.info("Clearing the element {} " + element + " before sending the keys.");
		element.clear();
		log.info("Sending the following keys {} " + keys + " to the element {} " + element);
		element.sendKeys(keys);
	}

	public static void sendKeysWithoutCheckingVisibility(WebElement element, String keys) {
		log.info("Wait for element {} " + element + " to be present in the DOM for duration of {} " + EXPLICIT_WAIT);
		log.info("Clearing the element {} " + element + " before sending the keys.");
		element.clear();
		log.info("Sending the following keys {} " + keys + " to the element {} " + element);
		element.sendKeys(keys);
	}

	public static void clear(WebElement element) {
		log.info("Wait for element {} " + element + " to be present in the DOM for duration of {} " + EXPLICIT_WAIT);
		waitForElementToBeVisible(element);
		log.info("Clearing the element {} " + element);
		element.clear();
	}

	public static String getText(By locator) {
		log.info("Wait for the element {} " + locator + " to be visible on the page for duration of {} "
				+ EXPLICIT_WAIT);
		WebElement element = waitForElementToBeVisible(locator);
		log.info("Text got from the locator {} " + locator + " is " + element.getText().trim());
		return element.getText().trim();
	}

	public static String getText(WebElement element) {
		log.info("Wait for the element {} " + element + " to be visible on the page for duration of {} "
				+ EXPLICIT_WAIT);
		waitForElementToBeVisible(element);
		log.info("Text got from the locator {} " + element + " is " + element.getText().trim());
		return element.getText().trim();
	}

	public static String getTextWithoutCheckingVisibility(WebElement element) {
		log.info("Text got from the locator {} " + element + " is " + element.getText().trim());
		return element.getText().trim();

	}

	public static String getAttributeInnerHtmlWithoutCheckingVisibility(WebElement element) {
		log.info("Text got from the locator {} " + element + " is " + element.getText().trim());
		System.out.println("Fetching the inner Html Attribute " + element.getAttribute("innerHTML"));
		System.out.println("Featching the Inner Text" + element.getAttribute("innerText"));
		// return element.getText().trim();
		return element.getAttribute("innerHTML").trim();
	}

	public static String getAttribute(WebElement element, String attribute) {
		log.info("Wait for the element {} " + element + " to be visible on the page for duration of {} "
				+ EXPLICIT_WAIT);
		waitForElementToBeVisible(element);
		if(element.getAttribute(attribute)!=null) {
			log.info("Text got from the locator {} " + element + " is " + element.getAttribute(attribute).trim());
			return element.getAttribute(attribute).trim();
		}
		else
			return "";
	}

	public void mouseHover(WebElement element) {
		try {
			Actions actions = new Actions(driver);
			if (element.isDisplayed()) {
				actions.moveToElement(element).build().perform();
				log.info("The element is found and user scrolls it");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			log.info("The element is not found");
		}
	}

	public static String getAttributeWithoutWait(WebElement element, String attribute) {
		log.info("Wait for the element {} " + element + " to be visible on the page for duration of {} "
				+ EXPLICIT_WAIT);
		log.info("Text got from the locator {} " + element + " is " + element.getAttribute(attribute).trim());
		return element.getAttribute(attribute).trim();
	}

	public static WebElement getWebElement(By locator) {
		log.info("Returning web element for by type locator : " + locator);
		return driver.findElement(locator);
	}

	public static List<WebElement> getWebElements(By locator) {
		log.info("Returning web elements for the given by type locator : " + locator);
		return driver.findElements(locator);
	}

	public static void waitForElementToBeClickable(By locator) {
		log.info("Waiting for element to be clickable for " + EXPLICIT_WAIT + " seconds.");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		log.info("Element is clickable now.");
	}

	public static void waitForElementToBeClickable(WebElement element) {
		log.info("Waiting for element to be clickable for " + EXPLICIT_WAIT + " seconds.");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		log.info("Element is clickable now.");
	}

	public static WebElement waitForElementPresence(By locator) {
		WebElement element;
		log.info("Waiting for element to be present for " + EXPLICIT_WAIT + " seconds.");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
		element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		log.info("Element is present in the DOM.");
		return element;
	}

	public static WebElement waitForElementToBeVisible(By locator) {
		WebElement element;
		log.info("Waiting for element to be visible for " + EXPLICIT_WAIT + " secondZs.");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		log.info("Element is visible on the page.");
		return element;
	}

	public static void waitForElementToBeVisible(WebElement element) {
		log.info("Waiting for element to be visible for " + EXPLICIT_WAIT + " seconds.");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("Element is visible on the page.");
	}

	public static void waitForElementToBeInVisible(By locator) {
		log.info("Waiting for element to be invisible for " + EXPLICIT_WAIT + " seconds.");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		log.info("Element is invisible from the page.");
	}

	public static void waitForElementToBeInVisible(WebElement element) {
		log.info("Waiting for element to be invisible for " + EXPLICIT_WAIT + " seconds.");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
		wait.until(ExpectedConditions.invisibilityOf(element));
		log.info("Element is invisible from the page.");
	}

	public static void switchToFrame(WebElement element) {
		log.info("Switching to the iFrame with the locator : " + element);
		driver.switchTo().frame(element);
		log.info("Switched to the iFrame successfully.");
	}

	public static void switchToFrame(int frameIndex) {
		log.info("Switching to the iFrame with the Index : " + frameIndex);
		driver.switchTo().frame(frameIndex);
		log.info("Switched to the iFrame successfully.");
	}

	public static void switchToFrame(String id) {
		log.info("Switching to the iFrame with the Index : " + id);
		driver.switchTo().frame(id);
		log.info("Switched to the iFrame successfully.");
	}

	public static void switchToDefaultContent() {
		log.info("Switching back to the default content.");
		driver.switchTo().defaultContent();
		log.info("Switched to the default content successfully.");
	}

	public static boolean isElementVisible(WebElement element) {
		boolean isVisible = false;
		if (element.isDisplayed()) {
			isVisible = true;
			log.info("Element {} : " + element + " is visible on the page.");
		}
		return isVisible;
	}

	public static boolean isAllElementVisible(List<WebElement> elements) {
		boolean isVisible = false;
		for (WebElement element : elements) {
			if (element.isDisplayed()) {
				isVisible = true;
				log.info("Element {} : " + element + " is visible on the page.");
			}
		}
		return isVisible;
	}

	public static boolean isElementNotVisible(WebElement element) {
		boolean isNotVisible = false;
		if (!element.isDisplayed()) {
			isNotVisible = true;
			log.info("Element {} : " + element + " is visible on the page.");
		}
		return isNotVisible;
	}

	public static boolean isElementVisibleAlt(List<WebElement> element) {
		boolean isVisible = false;
		if (element.size() > 0) {
			isVisible = true;
			log.info("Element {} : " + element + " is visible on the page.");
		}
		return isVisible;
	}

	public static boolean isElementEnabled(WebElement element) {
		boolean isEnabled = false;
		if (element.isEnabled()) {
			isEnabled = true;
			log.info("Element {} : " + element + " is in enabled state.");
		}
		return isEnabled;
	}

	public void pressEnterKeyAct() {
		log.info("Creating action class instance.");
		Actions actions = new Actions(driver);
		log.info("Pressing TAB key using action class instance.");
		actions.sendKeys(Keys.ENTER).build().perform();
	}

	public static boolean isElementSelected(WebElement element) {
		boolean isSelected = false;
		if (element.isSelected()) {
			isSelected = true;
			log.info("Element {} : " + element + " is in selected state.");
		}
		return isSelected;
	}

	public static void copyPasteUsingActionClass(WebElement element) {
		Actions actions = new Actions(driver);
		log.info("Moving control to the given element.");
		actions.moveToElement(element).click().perform();
		log.info("Press Ctrl + V into the given element.");
		actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();
	}

	public static void scrollToTheBottomOfPage() {
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		log.info("Scrolling page to the very bottom.");
		javascriptExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
		log.info("Page scrolled to the bottom.");
	}

	public static void scrollToTheTopOfPage() {
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		log.info("Scrolling page to the very bottom.");
		javascriptExecutor.executeScript("window.scrollBy(document.body.scrollHeight, 0)", "");
		log.info("Page scrolled to the bottom.");
	}

	public static void selectByVisibleText(WebElement element, String visibleText) {
		log.info("Selecting the option by visible text {}: " + visibleText);
		Select select = new Select(element);
		log.info("Selecting the given option to the web element {}: " + element);
		select.selectByVisibleText(visibleText);
		log.info("Given text is selected as the option to the given select web element.");
	}

	public static void selectByValue(WebElement element, String value) {
		log.info("Selecting the option by value {}: " + value);
		Select select = new Select(element);
		log.info("Selecting the given option to the web element {}: " + element);
		select.selectByValue(value);
		log.info("Given value is selected as the option to the given select web element.");
	}

	public static void selectByIndex(WebElement element, int index) {
		log.info("Selecting the option by index {}: " + index);
		Select select = new Select(element);
		log.info("Selecting the given option to the web element {}: " + element);
		select.selectByIndex(index);
		log.info("Given index is selected as the option to the given select web element.");
	}

	public List<String> getAllSelectOptionsInListBox(WebElement element) {
		List<String> optionList = new ArrayList<String>();
		Select select = new Select(element);
		List<WebElement> options = select.getAllSelectedOptions();
		for (WebElement option : options) {
			pause(100);
			if (option.getAttribute("value") != "")
				optionList.add(option.getText());
		}
		return optionList;
	}

	public List<String> getAllOptionsInListBox(WebElement element) {
		List<String> optionList = new ArrayList<String>();
		Select select = new Select(element);
		List<WebElement> options = select.getOptions();
		for (WebElement option : options) {
			pause(100);
			if (option.getAttribute("value") != "" || option.getAttribute("value") != "--Select--")
				optionList.add(option.getText());
		}
		return optionList;
	}

	public void explicitWaitForAlertPopUp(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException Ex) {
			return false;
		}
	}

	public static void acceptAlert() {
		String parentWinHandle = driver.getWindowHandle();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		// driver.switchTo().window(parentWinHandle);
	}

	public static void dismissAlert() {
		String parentWinHandle = driver.getWindowHandle();
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
		driver.switchTo().window(parentWinHandle);
	}

	public String getAlertMessage() {
		String alertText = null;
		boolean isAlertAvailable = isAlertPresent();
		if (isAlertAvailable) {
			Alert alert = driver.switchTo().alert();
			alertText = alert.getText().trim();
			// System.out.println(alertText);
		}
		return alertText;
	}

	public void verifyAlertMessage(String expectedMessage) {
		Assert.assertEquals(getAlertMessage(), expectedMessage);
		log.info("the expected value: " + expectedMessage + " matches the actual value: " + getAlertMessage());
	}

	public static String getObjectlLabel(By locator) {
		String sTextVal = null;
		try {
			driver.findElement(locator).isDisplayed();
			sTextVal = driver.findElement(locator).getText().trim();
			log.info("User gets the test object Label as: " + sTextVal);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
		}
		return sTextVal;
	}

	public static void verifyObjectLabel(By locator, String expectedObjectLabel) {
		String actualObjectLabel = getObjectlLabel(locator).trim(); // return
		actualObjectLabel.contains(expectedObjectLabel.trim());
		Assert.assertEquals(actualObjectLabel, expectedObjectLabel.trim());
		log.info("User verifies the test object Label as: " + expectedObjectLabel);
		if (actualObjectLabel.equals(expectedObjectLabel.trim())) {
		} else {
		}
	}

	public void verifyStringValueContains(String expected, String actual) {
		if (actual.contains(expected.trim())) {
			ExtentLogger.logPass("The expected value: " + expected + " available in the actual value: " + actual);
		} else {
			ExtentLogger.logFail("The expected value: " + expected + " is not available in the actual value:" + actual);
		}
	}

	public List<String> getExpectedElementsTextList(String expectedEle) {
		ArrayList<String> eleList = new ArrayList<String>();
		String[] expEle = expectedEle.split(";");
		for (String value : expEle) {
			eleList.add(value);
		}
		return eleList;
	}

	/**
	 * This method is used to hover the mouse over the element.
	 *
	 * @param locator
	 */
	public void mouseHover(By locator) {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(locator)).build().perform();
	}

	/**
	 * This method is used to scroll to the element on the page.
	 *
	 * @param locator
	 */
	public void scrollToElement(By locator) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(locator));
		} catch (NoSuchElementException e) {
			System.err.format("No Element Found: " + e);
		}
	}

	/**
	 * This method is used to scroll to the Top of the page.
	 */
	public void scrollToTop() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight, 0)");
	}

	/**
	 * This method is used to scroll to the bottom of the page.
	 */
	public void scrollToBottom() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	/**
	 * This method is used to scroll to the element on the page.
	 *
	 * @param element
	 */
	public static void scrollToElement(WebElement element) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (NoSuchElementException e) {
			System.err.format("No Element Found: " + e);
		}
	}

	public static void clickElementUsingJsExecutor(WebElement element) {
		log.info("Preparing for element to click.");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			js.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("Element is clicked successfully.");
	}

	public static boolean verifyCheck(WebElement element) {
		log.info("verify check box is selected");
		waitForElementToBeVisible(element);
		return element.isSelected();
	}

	public List<String> getAllElementsTextInList(List<WebElement> eleList) {
		List<String> options = new ArrayList<String>();
		for (WebElement e : eleList) {
			if (!e.getText().isEmpty())
				options.add(e.getText().trim());
		}
		log.info("The list contains the following values : " + options);
		return options;
	}

	public static String getSelectedValueInDropBox(WebElement element) {
		WebElement selectedOption = null;
		String selectedText = null;
		try {
			if (element.isDisplayed()) {
				Select selObj = new Select(element);
				selectedOption = selObj.getFirstSelectedOption();
				selectedText = selectedOption.getText();
				log.info("User gets the selection as  " + selectedText + " from Dropdown");

			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.err.format("No Element Found to perform selection" + e);
			log.info("No Element Found to perform selection in the dropdown" + e);
		}
		return selectedText;
	}

	public void switchToWindow(String windowHandle) {
		log.info("Navigating to window : " + windowHandle);
		driver.switchTo().window(windowHandle);
		log.info("Navigated to window : " + windowHandle + " successfully.");
	}

	public static boolean closeAllOtherWindows(WebDriver driver, String openWindowHandle) {
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (String currentWindowHandle : allWindowHandles) {
			if (!currentWindowHandle.equals(openWindowHandle)) {
				driver.switchTo().window(currentWindowHandle);
				driver.close();
			}
		}
		driver.switchTo().window(openWindowHandle);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}

	public void pressTab() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.TAB).build().perform();
		// action.sendKeys(Keys.RETURN).build().perform();
	}

	public void navigateToUrl(String activationUrl) {

		try {
			driver.get(activationUrl);
		} catch (Exception exception) {
			System.err.format("No Element Found: " + exception);
			log.info("No Element Found after wait" + exception);
		}
	}

	public void pressSpace() {
		log.info("Creating action class instance.");
		Actions actions = new Actions(driver);
		log.info("Pressing TAB key using action class instance.");
		actions.sendKeys(Keys.SPACE).build().perform();
	}

	public void pageRefresh() {
		driver.navigate().refresh();
		pause(4000);
		log.info("User refreshes the page");

	}

	public void dragAndDropElement(WebElement source, WebElement destination) {
		try {
			Actions action = new Actions(driver);
			action.clickAndHold(source).moveToElement(destination).release(destination).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteCookies() {
		driver.manage().deleteAllCookies();
	}

	public void expWaitForEleAttributeToChange(WebElement ele, String attributeName, String attributeValue) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
			wait.until((ExpectedCondition<Boolean>) driver -> {
				String enabled = ele.getAttribute(attributeName);
				if (enabled.equals(attributeValue))
					return true;
				else
					return false;
			});
		} catch (NoSuchElementException e) {
			System.err.format("No Element Found: " + e);
		}
	}

	public boolean isAttributePresent(WebElement element, String attribute) {
		Boolean result = false;
		try {
			String value = element.getAttribute(attribute);
			if (value != null) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void verifyCurrentPageTitle(String expectedTitle) {
		pause(1000);
		String actualTitle = getCurrentTitle();
		if (actualTitle.equals(expectedTitle)) {
			ExtentLogger
					.logPass("The expected Title: " + expectedTitle + " matches with the actual Title: " + actualTitle);
		} else {
			ExtentLogger.logFail(
					"The expected Title: " + expectedTitle + " does not match with the actual Title: " + actualTitle);
		}
		Assert.assertEquals(expectedTitle, actualTitle);
	}

	public void assertCurrentPageUrl(String expectedUrl) {
		String actualUrl = getCurrentUrl().trim();
		if (actualUrl.toLowerCase().contains(expectedUrl.toLowerCase().trim())) {
			ExtentLogger.logPass("The expected URL: " + expectedUrl + " is present in the actual URL: " + actualUrl);
		} else {
			ExtentLogger
					.logFail("The expected URL: " + expectedUrl + " is not present in the actual URL: " + actualUrl);
		}
		Assert.assertTrue(actualUrl.toLowerCase().contains(expectedUrl.toLowerCase().trim()));
	}

	public void switchToChildWindow() {
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> I1 = s1.iterator();
		while (I1.hasNext()) {
			String child_window = I1.next();
			// Here we will compare if parent window is not equal to child
			// window then we will close
			if (!driver.getWindowHandles().equals(child_window)) {
				driver.switchTo().window(child_window);

			}
		}
	}

	public static String symbolConverterForUI(String number) {

		String balance;

		if (number.contains("+")) {
			balance = number.replace("$", "").replace("₹", "").replace(",", "").replace("+", "").replace("A", "")
					.replace("ED", "").trim();

		}

		else if (number.contains("-")) {
			balance = number.replace("$", "").replace("₹", "").replace(",", "").replace(" ", "").replace("A", "")
					.replace("ED", "").trim();

		}

		else {

			balance = number.replace("$", "").replace("₹", "").replace(",", "").replace("A", "").replace("ED", "")
					.trim();

		}
		System.out.println(balance);
		return balance;

	}

	public String getCssValue(WebElement element, String sPropertyName) {
		String propertyValue = null;
		try {
			if (element.isDisplayed()) {
				propertyValue = element.getCssValue(sPropertyName);
				// System.out.println(propertyValue);
				log.info("User gets the value as  " + propertyValue + "for the webelement");

			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.err.format("No Element Found to perform selection" + e);

		}
		return propertyValue;

	}

	public void verifyObjectLabels(WebElement element, String expectedObjectLabel) {
		String actualObjectLabel = getObjectlLabel(element); // return
		getObjectlLabel(element).contains(expectedObjectLabel);
		Assert.assertEquals(actualObjectLabel, expectedObjectLabel);
		log.info("User verifies the test object Label as: " + expectedObjectLabel);
		if (actualObjectLabel.equals(expectedObjectLabel.trim())) {
		} else {
			log.info("The expected object label: " + expectedObjectLabel + " matches with the actual object label: "
					+ actualObjectLabel);
		}
	}

	public String getObjectlLabel(WebElement element) {
		String sTitle = null;
		try {
			element.isDisplayed();
			sTitle = element.getText().trim();
			log.info("User gets the test object Label as: " + sTitle);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("User gets a blank test object Label");
		}
		return sTitle;
	}
	public boolean getCheckBoxState(List<WebElement> eleList) {
		boolean value = false;
		try {
			if (eleList.size() > 0) {
				value = eleList.get(0).isSelected();
				// System.out.println(value);
				log.info("User gets the value as  " + value + " from the check box");
				ExtentLogger.logPass("User gets the value as  " + value + " from the check box");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.err.format("No Element Found to perform selection" + e);
			ExtentLogger.logFail("No Element Found to perform selection in the dropdown" + e);
		}
		return value;

	}

}

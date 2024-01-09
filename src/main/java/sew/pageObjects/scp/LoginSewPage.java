package sew.pageObjects.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.pageObjects.scp.LoginPage;

public class LoginSewPage extends LoginPage {

	private static final Logger log = LogManager.getLogger(LoginSewPage.class);

	public LoginSewPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	// ***************Payment***********************//

	// For SEW DEMO ADDED BY Priya Awasthi

	@FindBy(css = "#txtLogin")
	private WebElement typeUserName;

	public void populateUsername(String Username) {
		log.info("Populating User Name {} :" + Username);
		sendKeys(typeUserName, Username);
		log.info("User Username populated successfully.");
	}

	@FindBy(css = "#txtpwd")
	private WebElement txtBoxpassword;

	public void populatpassword(String password) {
		log.info("Populating Password {} :" + password);
		sendKeys(txtBoxpassword, password);
		log.info("Password populated successfully.");
	}

	@FindBy(css = "#btnlogin")
	private WebElement btnSignIn;

	public void clickSignInButton() {
		click(btnSignIn);
		log.info("SignIn Button clicked successfully.");
	}

	@FindBy(css = "#BtnPayBill")
	private WebElement btnMakePayment;

	public void clickPaymentButton() {
		click(btnMakePayment);
		log.info("Payment Button clicked successfully.");
	}

	@FindBy(xpath = "//*[@id='option-2']")
	private WebElement otheramountraidiobtn;

	public void ScrollAndSelect() throws InterruptedException {
		waitForElementToBeVisible(otheramountraidiobtn);
		scrollPageToElement(otheramountraidiobtn);
		clickElementUsingJsExecutor(otheramountraidiobtn);

		log.info("Selected Other Ammount ");
	}

	@FindBy(xpath = "//*[@id='otheramount']")
	private WebElement OtheramounttextBox;

	public void EnterOtherAmmount(String amount) {
		sendKeys(OtheramounttextBox, amount);
		log.info("Entered Other Ammount");
	}
	@FindBy(xpath = "//*[@id=\"containerDiv\"]/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[1]/div/div[2]/section/div[2]/div[2]/label/span")

	private WebElement ClickPaymentMethodIcon;

	public void ClickOnPaymentMethod() {
		click(ClickPaymentMethodIcon);
	}

	@FindBy(css = "#tokenize_payment")
	private WebElement ButtonNext;

	public void clickNextPayementButton() {
		click(ButtonNext);
		log.info("Clicked on Payment Next Button");
	}

	@FindBy(xpath = "//*[@id=\"containerDiv\"]/div[2]/div[1]/div[1]/div/div[1]/ol/li[2]/span")
	private WebElement lbl_PaymentStep2;

	public String getPaymentStep2Text() {
		log.info("Fetching Step 2 Text.");
		String stepText = getText(lbl_PaymentStep2);
		log.info("Payemet Step2 Text {}: " + stepText);
		return stepText;
	}

	@FindBy(xpath = ("//input[@type='button' and @class='submit-button stepperbtns stsubmitt' "
			+ "and @value='Submit']"))
	private WebElement btnSubmitPayment;

	public void clickPaymentSubmitBtn() {
		scrollToElement(btnSubmitPayment);
		clickElementUsingJsExecutor(btnSubmitPayment);
		log.info("Submit Button Clicked Sucessfully .");
	}

	@FindBy(xpath = "/html/body/div[10]/div/div[1]")
	private WebElement lbl_Confirmation;

	public String getConfirmationText() {
		log.info("Fetching Confirmation Text.");
		String ConfirmationText = getText(lbl_Confirmation);
		log.info("Confirmation dialogbox Text {}: " + ConfirmationText);
		return ConfirmationText;
	}

	@FindBy(xpath = "//button[@class='dlg-action ok-action' and text()='Yes']")
	private WebElement BtnYes;

	public void clickYesButton() {
		waitForElementToBeVisible(BtnYes);
		click(BtnYes);
		log.info("Clicked on Yes Button");
	}

	@FindBy(xpath = "//a[@class='submit-button st1_ok' and text()='Done']")
	private WebElement done_btn;

	public void clickDoneButton() {
		log.info("click on payment sucessfull done button.");
		scrollToElement(done_btn);
		clickElementUsingJsExecutor(done_btn);
		log.info("Done Button Clicked Sucessfully .");
	}

//***************Usage Module ***********************//

	@FindBy(xpath = "//*[@id='module3']/a")
	private WebElement usageDropdown;

	public void clickUsageDropdown() {
		waitForElementToBeVisible(usageDropdown);
		clickElementUsingJsExecutor(usageDropdown);
		log.info("Clicked used module from Header");
	}

	@FindBy(css = "#HeaderMenu_PUMenu")
	private WebElement usageOverviewHeader;

	public void clickUsageOverviewHeader() {
		waitForElementToBeVisible(usageOverviewHeader);
		clickElementUsingJsExecutor(usageOverviewHeader);
		log.info("Clicked on usage overview");

	}

	@FindBy(css = "a[id*='HeaderUsageUI_PU']")
	private WebElement lnkPowerSideMenu;

	public boolean isPowerLinkVisible() {
		log.info("Checking the Visibility of Power link on the Usages Page." + lnkPowerSideMenu.isDisplayed());
		return isElementVisible(lnkPowerSideMenu);
	}

	@FindBy(css = "select#ddlMultiMeter")
	private WebElement lstMeterNumber;

	public Boolean isMeterDropDownVisible() {
		Boolean status = isElementVisible(lstMeterNumber);
		log.info("Meter DropDown visibility status {}: " + status);
		ExtentLogger.logInfo("Meter DropDown visibility status {}: " + status);
		return status;
	}

	@FindBy(xpath = "//*[@id='typeK']")
	private WebElement KwhUnit;

	public void clickKwhUnit() {
		click(KwhUnit);
		log.info("Clicked on Kwh Unit");
	}

	public String getKwhUnitToggleLabel() {
		String label = getText(KwhUnit);
		log.info("KwhUnit toggle label is : " + label);
		return label;
	}
	
	@FindBy(xpath = "//a[contains(@class, 'active') and @id='typeK']")
	private WebElement KwhUnitactive;
	public Boolean isKwhUnitToggleSelected() {
		Boolean status = isElementVisible(KwhUnitactive);
		log.info("KwhUnit usage toggle selected status : " + status);
		return status;
	}
	// ***************Billing query***********************//

	@FindBy(xpath = "//*[@id='module2']/a")
	private WebElement billingDropdown;

	public void clickBillingDropdown() {
		waitForElementToBeVisible(billingDropdown);
		clickElementUsingJsExecutor(billingDropdown);
		log.info("Clicked billing module from Header");
	}

	@FindBy(xpath = "//*[@id='billQuery']a")
	private WebElement billingQueriesoption;

	public void clickOnBillingQueries() {
		waitForElementToBeVisible(billingQueriesoption);
		clickElementUsingJsExecutor(billingQueriesoption);
		log.info("Clicked billing Queries option from billing Header");
			}
	
	@FindBy (xpath = "/html/body/form/section/div/div/div[2]/div[4]/div[3]/div[1]/div/div[1]/div[1]/div[1]/div[4]/div[4]/div/input")
	private WebElement EnterSubject;
	public void populateSubject(String Subject) {
	sendKeys(EnterSubject, Subject);
	log.info("Password populated successfully.");
}
	
	
	
	
	
	
	
	

}
package demo.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sew.ai.pageObjects.scp.HomePage;

public class PreLoginPayACHPage extends HomePage {
	private static final Logger log = LogManager.getLogger(PreLoginPayACHPage.class);

	public PreLoginPayACHPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//*[@id='preloginPayBillModule']")
	private WebElement lnk_paybill;

	public void clickPayBillLink() {
		// click(lnk_billing);
		clickWithJSExecutor(lnk_paybill);
		log.info("Pay Bill link clicked successfully.");
	}

	@FindBy(css = "#labelheader")
	private WebElement labelPayBill;

	public boolean isPayBillHeaderVisible() {
		return isElementVisible(labelPayBill);
	}

	public String getPayBillLabel() {
		String label = getText(labelPayBill);
		return label;
	}

	@FindBy(xpath = "//*[@id='stepperDiv']/ol")
	private WebElement labelPayBillStepOne;

	public boolean isPayBillStepOneTitleVisible() {
		return isElementVisible(labelPayBillStepOne);
	}

	public String getPayBillStepOne() {
		String label = getText(labelPayBillStepOne);
		return label;
	}

	@FindBy(css = "#txtAccountNumber")
	private WebElement accField;

	public boolean isAccountNoFieldVisible() {
		log.info("Checking that Account No field is visible on the PayBill Step 1 page." + accField.isDisplayed());
		return isElementVisible(accField);
	}

	public void clickAccNoFld() {
		click(accField);
		log.info("Click on Acc No Field Successfully .");

	}

	public void enterAccNoInTheField(String accountNumber) {
		sendKeys(accField, accountNumber);
		log.info("Entered account no in the field.");
	}

	public void clearAccountNo() {
		log.info("clear the account no.");
		scrollToElement(accField);
		clear(accField);

	}

	@FindBy(css = "#txtPhoneNumber")
	private WebElement primaryPhnField;

	public boolean isPrimaryPhoneNoField() {
		log.info("Checking that Primary Phone No field is visible on the PayBill Step 1 page."
				+ primaryPhnField.isDisplayed());
		return isElementVisible(primaryPhnField);
	}

	public void clickPrimaryPhnFld() {
		click(primaryPhnField);
		log.info("Click on Primary Phone Field Successfully .");

	}

	public void enterPrimayPhTheField(String primaryPh) {
		sendKeys(primaryPhnField, primaryPh);
		log.info("Entered primary phone no in the field.");
	}

	@FindBy(xpath = "//*[@id='btnCancelPayment']")
	private WebElement cancelStpOneField;

	public boolean isCancelBttnVisible() {
		log.info("Checking that Cancel Bttn is visible on the PayBill Step 1 page." + cancelStpOneField.isDisplayed());
		return isElementVisible(cancelStpOneField);
	}

	public void clickCanclStpOneBttn() {
		click(cancelStpOneField);
		log.info("Click on Cancel Bttn Successfully .");

	}

	@FindBy(css = "#btnSubmitPayment")
	private WebElement nextStpOneField;

	public boolean isNextBttnVisible() {
		log.info("Checking that Next Bttn is visible on the PayBill Step 1 page." + nextStpOneField.isDisplayed());
		return isElementVisible(nextStpOneField);
	}

	public void clickSNextButton() {
		click(nextStpOneField);
		log.info("Click on next button Successfully .");
	}

	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/h2")
	private WebElement labelPayBillTwo;

	public boolean isPayBillHeaderVisibleTwo() {
		return isElementVisible(labelPayBillTwo);
	}

	public String getPayBillLabelTwo() {
		String label = getText(labelPayBillTwo);
		return label;
	}

	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/div/div[1]/ol")
	private WebElement labelPayBillStepTwo;

	public boolean isPayBillStepTwoTitleVisible() {
		return isElementVisible(labelPayBillStepOne);
	}

	public String getPayBillStepTwo() {
		String label = getText(labelPayBillStepOne);
		return label;
	}

	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/div/div[2]/div[1]/div[1]/div/div[1]/ul/li[1]/label")
	private WebElement nameField;

	public boolean isNameFieldVisible() {
		log.info("Checking that Name field is visible on the PayBill Step 2 page." + nameField.isDisplayed());
		return isElementVisible(nameField);

	}

	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/div/div[2]/div[1]/div[1]/div/div[1]/ul/li[2]/label")
	private WebElement accountTwoField;

	public boolean isAccTwoFieldVisible() {
		log.info("Checking that Account field is visible on the PayBill Step 2 page." + accountTwoField.isDisplayed());
		return isElementVisible(accountTwoField);
	}

	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/div/div[2]/div[1]/div[1]/div/div[1]/ul/li[3]/label")
	private WebElement amtDueTwoField;

	public boolean isAmtDueTwoFieldVisible() {
		log.info(
				"Checking that Amount Due field is visible on the PayBill Step 2 page." + amtDueTwoField.isDisplayed());
		return isElementVisible(amtDueTwoField);
	}

	@FindBy(css = "#st2_phonenumber")
	private WebElement primPhoneTwoField;

	public boolean isPrimPhTwoFieldVisible() {
		log.info("Checking that Primary Ph field is visible on the PayBill Step 2 page."
				+ primPhoneTwoField.isDisplayed());
		return isElementVisible(primPhoneTwoField);
	}

	@FindBy(css = "#st2_email")
	private WebElement emailAddTwoField;

	public boolean isEmailAddTwoFieldVisible() {
		log.info("Checking that Email Add field is visible on the PayBill Step 2 page."
				+ emailAddTwoField.isDisplayed());
		return isElementVisible(emailAddTwoField);
	}

	@FindBy(css = "#st2_amount")
	private WebElement payAmtTwoField;

	public boolean isPayAmtTwoFieldVisible() {
		log.info("Checking that Payment Amt field is visible on the PayBill Step 2 page."
				+ payAmtTwoField.isDisplayed());
		return isElementVisible(payAmtTwoField);
	}

	public void clickPayAmtFld() {
		scrollToElement(payAmtTwoField);
		click(payAmtTwoField);
		log.info("Click on Payment Amount Field Successfully .");
	}

	public void clearPayAmtFld() {
		log.info("clear the payment amount.");
		clear(payAmtTwoField);
	}

	public void enterPayAmtInTheField(String paymentAmt) {
		sendKeys(payAmtTwoField, paymentAmt);
		log.info("Entered payment amount in the field.");
	}

	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/div/div[2]/div[1]/div[1]/div/div[2]/h3[3]")
	private WebElement labelPayInfoStepTwo;

	public boolean isPayInfoStepTwoLableVisible() {
		return isElementVisible(labelPayInfoStepTwo);
	}

	public String getPayInfoStepTwo() {
		String label = getText(labelPayInfoStepTwo);
		return label;
	}

	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/div/div[2]/div[1]/div[1]/div/div[2]/section/div[1]/label")
	private WebElement bankAccOptTwoField;

	public boolean isBankAccOptTwoVisible() {
		scrollToElement(bankAccOptTwoField);
		log.info("Checking that Bank Acc optn is visible on the PayBill Step 2 page."
				+ bankAccOptTwoField.isDisplayed());
		return isElementVisible(bankAccOptTwoField);
	}

	public void clickBankAccOptTwoStp() {
		scrollToElement(bankAccOptTwoField);
		clickWithJSExecutor(bankAccOptTwoField);
		log.info("Click on Bank Acc Opt Successfully .");
	}

	@FindBy(css = "#accholrnm")
	private WebElement accHoldNameField;

	public boolean isAccHoldNameField() {
		log.info("Checking that Acc Holder Name field is visible on the PayBill Step 2 page."
				+ accHoldNameField.isDisplayed());
		return isElementVisible(accHoldNameField);
	}

	public void clickAccHoldNameFld() {
		click(accHoldNameField);
		log.info("Click on Acc Holder Name Field Successfully .");

	}

	public void enterAccHolderNameFld(String accHoldName) {
		sendKeys(accHoldNameField, accHoldName);
		log.info("Entered Acc Holder Name in the field.");
	}

	@FindBy(css = "#rtno")
	private WebElement routingNoField;

	public boolean isRoutNoField() {
		log.info(
				"Checking that Routing No field is visible on the PayBill Step 2 page." + routingNoField.isDisplayed());
		return isElementVisible(routingNoField);
	}

	public void clickRoutNoFld() {
		click(routingNoField);
		log.info("Click on Routing No Field Successfully .");

	}

	public void enterRoutNoFld(String routNo) {
		sendKeys(routingNoField, routNo);
		log.info("Entered Routing No in the field.");
	}

	@FindBy(css = "#crtno")
	private WebElement confRoutingNoField;

	public boolean isCongRoutNoField() {
		log.info("Checking that Confirming Routing No field is visible on the PayBill Step 2 page."
				+ confRoutingNoField.isDisplayed());
		return isElementVisible(confRoutingNoField);
	}

	public void clickConfRoutNoFld() {
		click(confRoutingNoField);
		log.info("Click on Confirming Routing No Field Successfully .");

	}

	public void enterConfRoutNoFld(String confRoutNo) {
		sendKeys(confRoutingNoField, confRoutNo);
		log.info("Entered Confirm Routing No in the field.");
	}

	@FindBy(css = "#bnkaccno")
	private WebElement bnkAccNoField;

	public boolean isBnkAccNoField() {
		log.info("Checking that Bank Account No field is visible on the PayBill Step 2 page."
				+ bnkAccNoField.isDisplayed());
		return isElementVisible(bnkAccNoField);
	}

	public void clickBnkAccNoFld() {
		click(bnkAccNoField);
		log.info("Click on Bank Account No Field Successfully .");

	}

	public void enterBnkAccNoFld(String bnkAccNo) {
		sendKeys(bnkAccNoField, bnkAccNo);
		log.info("Entered Bank Account No in the field.");
	}

	@FindBy(css = "#bnkaccnomask")
	private WebElement confBnkAccNoField;

	public boolean isConfBnkAccNoField() {
		log.info("Checking that  Confirm Bank Account No field is visible on the PayBill Step 2 page."
				+ confBnkAccNoField.isDisplayed());
		return isElementVisible(confBnkAccNoField);
	}

	public void clickConfBnkAccNoFld() {
		click(confBnkAccNoField);
		log.info("Click on Confirm Bank Account No Field Successfully .");

	}

	public void enterConfBnkAccNoFld(String confBnkAccNo) {
		sendKeys(confBnkAccNoField, confBnkAccNo);
		log.info("Entered Confirm Bank Account No in the field.");
	}

	@FindBy(css = "#st2_accountType")
	private WebElement sltAccTypField;

	public boolean isSelectAccTypField() {
		log.info("Checking that Select Account Type field is visible on the PayBill Step 2 page."
				+ sltAccTypField.isDisplayed());
		return isElementVisible(sltAccTypField);
	}

	public void clickSelectAccTypFld() {
		click(sltAccTypField);
		log.info("Click on Select Account Type Field Successfully .");

	}

	@FindBy(css = "#st2_accountType > option:nth-child(2)")
	private WebElement optAccTypField;

	public boolean isOptAccTypField() {
		log.info("Checking that Option Account Type field is visible on the PayBill Step 2 page."
				+ optAccTypField.isDisplayed());
		return isElementVisible(optAccTypField);
	}

	public void clickOptAccTypFld() {
		click(optAccTypField);
		log.info("Click on Option Account Type Field Successfully .");
	}

	@FindBy(css = "#st2_firstname")
	private WebElement frstNameField;

	public boolean isFirstNameField() {
		log.info("Checking that First Name field is visible on the PayBill Step 2 page." + frstNameField.isDisplayed());
		return isElementVisible(frstNameField);
	}

	public void clickFirstNameFld() {
		click(frstNameField);
		log.info("Click on First Name Field Successfully .");

	}

	public void enterFirstNameFld(String frstName) {
		sendKeys(frstNameField, frstName);
		log.info("Entered First Name in the field.");
	}

	@FindBy(css = "#st2_lastname")
	private WebElement lastNameField;

	public boolean isLastNameField() {
		log.info("Checking that Last Name field is visible on the PayBill Step 2 page." + lastNameField.isDisplayed());
		return isElementVisible(lastNameField);
	}

	public void clickLastNameFld() {
		click(lastNameField);
		log.info("Click on Last Name Field Successfully .");

	}

	public void enterLastNameFld(String lastName) {
		sendKeys(lastNameField, lastName);
		log.info("Entered Last Name in the field.");
	}

	@FindBy(css = "#st2_address")
	private WebElement addressField;

	public boolean isAddressField() {
		log.info("Checking that Address field is visible on the PayBill Step 2 page." + addressField.isDisplayed());
		return isElementVisible(addressField);
	}

	public void clickAddressFld() {
		click(addressField);
		log.info("Click on Address Field Successfully .");

	}

	public void enterAddressFld(String addRess) {
		sendKeys(addressField, addRess);
		log.info("Entered Address in the field.");
	}

	@FindBy(css = "#st2_city")
	private WebElement cityField;

	public boolean isCityField() {
		log.info("Checking that City field is visible on the PayBill Step 2 page." + cityField.isDisplayed());
		return isElementVisible(cityField);
	}

	public void clickCityFld() {
		click(cityField);
		log.info("Click on City Field Successfully .");

	}

	public void enterCityFld(String city) {
		sendKeys(cityField, city);
		log.info("Entered City in the field.");
	}

	@FindBy(css = "#st2_state")
	private WebElement stateField;

	public boolean isStateField() {
		log.info("Checking that State field is visible on the PayBill Step 2 page." + stateField.isDisplayed());
		return isElementVisible(stateField);
	}

	public void clickStateFld() {
		click(stateField);
		log.info("Click on State Field Successfully .");

	}

	public void enterStateFld(String state) {
		sendKeys(stateField, state);
		log.info("Entered State in the field.");
	}

	@FindBy(css = "#st2_zipcode")
	private WebElement zipcodeField;

	public boolean isZipCodeField() {
		log.info("Checking that Zip Code field is visible on the PayBill Step 2 page." + zipcodeField.isDisplayed());
		return isElementVisible(zipcodeField);
	}

	public void clickZipCodeFld() {
		click(zipcodeField);
		log.info("Click on Zip Code Field Successfully .");

	}

	public void enterZipCodeFld(String zipCode) {
		sendKeys(zipcodeField, zipCode);
		log.info("Entered Zip Code in the field.");
	}

	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/div/div[2]/div[1]/div[2]/input[2]")
	private WebElement cancelStpTwoField;

	public boolean isCnclTwoStpBttnVisible() {
		log.info("Checking that Cancel Bttn is visible on the PayBill Step 2 page." + cancelStpTwoField.isDisplayed());
		return isElementVisible(cancelStpTwoField);
	}

	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/div/div[2]/div[1]/div[2]/input[1]")
	private WebElement nextStpTwoField;

	public boolean isNextBttnTwoVisible() {
		log.info("Checking that Next Bttn is visible on the PayBill Step 2 page." + nextStpTwoField.isDisplayed());
		return isElementVisible(nextStpTwoField);
	}

	public void clickNxtTwoBttn() {
		click(nextStpTwoField);
		log.info("Click on Next Bttn Successfully .");
	}

	@FindBy(css = "#toast-container > div > div")
	private WebElement allMandErrorMessage;

	public String getallMandErrorMessage() {
		log.info("Fetching the all mandatory fields error message.");
		String label = getText(allMandErrorMessage);
		log.info("All Mandatory error message is {}: " + label);
		return label;
	}

	@FindBy(css = "#logincredentials > div:nth-child(2) > div > span.error_messagecommon")
	private WebElement primPhErrMessage;

	public String getprimaryPhErrorMessage() {
		log.info("Fetching the primary Ph error message.");
		String label = getText(primPhErrMessage);
		log.info("Primary Ph Error is {}: " + label);
		return label;
	}

	@FindBy(css = "#logincredentials > div:nth-child(1) > div > span.error_messagecommon")
	private WebElement accNoErrMessage;

	public String getacctNoErrorMessage() {
		log.info("Fetching the account no. error message.");
		String label = getText(accNoErrMessage);
		log.info("Account No. Error is {}: " + label);
		return label;
	}

	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/div/div[2]/div[2]/div[1]/div[1]/div/h5")
	private WebElement accNoThreeField;

	public boolean isAccNoThreeFldVisible() {
		log.info("Checking that Account Number field is visible on the PayBill Step 3 page."
				+ accNoThreeField.isDisplayed());
		return isElementVisible(accNoThreeField);

	}

	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/div/div[2]/div[2]/div[1]/div[2]/div/h5")
	private WebElement servaddThreeField;

	public boolean isServAddThreeFldVisible() {
		log.info("Checking that Service Address field is visible on the PayBill Step 3 page."
				+ servaddThreeField.isDisplayed());
		return isElementVisible(servaddThreeField);
	}

	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/div/div[2]/div[2]/div[1]/div[3]/div/h5")
	private WebElement billAmtThreeField;

	public boolean isBillAmtThreeFldVisible() {
		log.info("Checking that Bill Amount field is visible on the PayBill Step 3 page."
				+ billAmtThreeField.isDisplayed());
		return isElementVisible(billAmtThreeField);
	}

	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/div/div[2]/div[2]/div[1]/div[4]/div/h5")
	private WebElement transFeeThreeField;

	public boolean isTransFeeThreeFldVisible() {
		log.info("Checking that Transaction Fees field is visible on the PayBill Step 3 page."
				+ transFeeThreeField.isDisplayed());
		return isElementVisible(transFeeThreeField);
	}

	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/div/div[2]/div[2]/div[1]/div[5]/div/h5")
	private WebElement transAmtThreeField;

	public boolean isTransAmtThreeFldVisible() {
		log.info("Checking that Transaction Amount field is visible on the PayBill Step 3 page."
				+ transAmtThreeField.isDisplayed());
		return isElementVisible(transAmtThreeField);
	}

	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/div/div[2]/div[2]/div[1]/div[6]/div/h5")
	private WebElement payDateThreeField;

	public boolean ispayDateThreeFldVisible() {
		log.info("Checking that Payment Date field is visible on the PayBill Step 3 page."
				+ payDateThreeField.isDisplayed());
		return isElementVisible(payDateThreeField);
	}

	@FindBy(xpath = "//*[@id='payment_type']")
	private WebElement bnkThreeField;

	public boolean isBankThreeFldVisible() {
		log.info("Checking that Bank field is visible on the PayBill Step 3 page." + bnkThreeField.isDisplayed());
		return isElementVisible(bnkThreeField);
	}

	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/div/div[2]/div[2]/div[3]/input[2]")
	private WebElement backStpThreeBtn;

	public boolean isBackThreeStpBtnVisible() {
		log.info("Checking that Back Bttn is visible on the PayBill Step 3 page." + backStpThreeBtn.isDisplayed());
		return isElementVisible(backStpThreeBtn);
	}

	public void clickBackThreeBtn() {
		click(backStpThreeBtn);
		log.info("Click on Back Button Successfully .");
	}

	@FindBy(xpath = "//*[@id='containerDiv']/div[3]/div/div[1]/div/div[2]/div[2]/div[3]/input[1]")
	private WebElement submitStpThreeBtn;

	public boolean isSubmitBtnThreeVisible() {
		log.info("Checking that Submit Bttn is visible on the PayBill Step 2 page." + submitStpThreeBtn.isDisplayed());
		return isElementVisible(submitStpThreeBtn);
	}

	public void clickSubmitThreeBtn() {
		click(submitStpThreeBtn);
		log.info("Click on Submit Button Successfully .");
	}

	@FindBy(xpath = "//*[@id='transFeeLimit']")
	private WebElement discOneLableThreeStp;

	public String getDiscOneLblThreeMessage() {
		log.info("Fetching the disclaimer 1 lable message.");
		String label = getText(discOneLableThreeStp);
		log.info("Disclaimer 1 message is {}: " + label);
		return label;

	}

	@FindBy(xpath = "/html/body/form/section[1]/div/div/div[1]/div/section/div/div[3]/div/div[2]/div/div[1]/div[1]/span")
	private WebElement labelPaySuccessFinal;

	public boolean isPaySuccessVisible() {
		return isElementVisible(labelPaySuccessFinal);
	}

	public String getPaySuccessLabel() {
		String label = getText(labelPaySuccessFinal);
		return label;
	}
}
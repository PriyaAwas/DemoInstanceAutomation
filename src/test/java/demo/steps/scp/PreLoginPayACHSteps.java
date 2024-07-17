package demo.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import sew.ai.config.Configuration;
import sew.ai.config.ModelsConfiguration;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.models.Bank;
import demo.pageobjects.PreLoginPayACHPage;
import sew.ai.steps.scp.LoginSteps;
import sew.ai.utils.PropertiesUtil;

import static sew.ai.steps.scp.LoginSteps.loginTextProp;

public class PreLoginPayACHSteps extends PreLoginPayACHPage {
	private static final Logger log = LogManager.getLogger(PreLoginPayACHSteps.class);

	public static PropertiesUtil PreLogPaymentProp;
	String accNo = Configuration.toString("accountNumber");

	String phNo = Configuration.toString("phoneNo");

	public PreLoginPayACHSteps(WebDriver driver) {
		super(driver);
		PreLogPaymentProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.PRE_LOG_PAYMENT_TEXT_FILENAME);
		loginTextProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.LOGIN_TXT_FILENAME);

	}

	public void verifyPrePayBillUIAndObjtsStepOne(SoftAssert softAssert) throws Exception {
		ExtentLogger.logInfo("To verify objects on Pre-Login Pay Bill Step 1 page.");
		// click on PayBill link from login page
		clickPayBillLink();

		pause(3000);
		// verify the "Pay Bill" Label
		softAssert.assertEquals(getPayBillLabel(), PreLogPaymentProp.getPropValue("preLoginPageTitle"),
				"Pay Bill lable is not matchng.");
		// Verify the "Step 1: PayBill" Label
		softAssert.assertEquals(getPayBillStepOne(), PreLogPaymentProp.getPropValue("payBillStepOneTitle"),
				"Pay Bill Step 1 lable is not matchng.");
		// Verify the "Account Number" field on Step 1
		softAssert.assertTrue(isAccountNoFieldVisible(), "Account No field is not visible");
		// Verify the "Primary Phone No" field on Step 1
		softAssert.assertTrue(isPrimaryPhoneNoField(), "Primary Phone No field is not visible");
		// Verify the "Cancel" button on Step 1
		softAssert.assertTrue(isCancelBttnVisible(), "Cancel Option is not visible");
		// Verify the "Next" button on Step 1
		softAssert.assertTrue(isNextBttnVisible(), "Next option is not visible");
		ExtentLogger.logInfo("Test Case execution for - verifyPrePayBillUIAndObjtsStepOne - is Completed");

	}

	public void verifyMoveToStpTwoAndUIVal(SoftAssert softAssert) throws Exception {
		ExtentLogger.logInfo("To move to Step 2 of pre-login page successfully and perfom UI Vald.");
		// click on PayBill link from login page
		clickPayBillLink();

		pause(3000);
		// Click on the Account No Field and enter Account No
		clickAccNoFld();
		enterAccNoInTheField(accNo);
		// Click on the primary phone no. field an enter the no.
		clickPrimaryPhnFld();
		enterPrimayPhTheField(phNo);
		// Click on the Next BBt
		clickSNextButton();
		pause(3000);

	}

	public void verifyUIOnStepTwoPreLogPayBill(SoftAssert softAssert) throws Exception {
		ExtentLogger.logInfo("To perform UI Vald. on Step 2 of PayBill Page");

		// Fetching the Bank Details

		Bank bank = ModelsConfiguration.readBankAccounts().getBankByAccountHolderName("Henry Jacob Bank Account");

		// click on PayBill link from login page
		clickPayBillLink();
		pause(3000);
		// Click on the Account No Field and enter Account No
		clickAccNoFld();
		enterAccNoInTheField(accNo);
		// Click on the primary phone no. field an enter the no.
		clickPrimaryPhnFld();
		enterPrimayPhTheField(phNo);
		// Click on the Next BBt
		clickSNextButton();
		pause(3000);

		// verify the "Pay Bill" Label
		softAssert.assertEquals(getPayBillLabelTwo(), PreLogPaymentProp.getPropValue("preLoginPageTitle"),
				"Pay Bill lable is not matchng.");
		// Verify the "Name" field on Step 2
		softAssert.assertTrue(isNameFieldVisible(), "Name field is not visible");
		// Verify the "Account" field on Step 2
		softAssert.assertTrue(isAccTwoFieldVisible(), "Account field is not visible");
		// Verify the "Amount Due" field on Step 2
		softAssert.assertTrue(isAmtDueTwoFieldVisible(), "Amount Due field is not visible");
		// Verify the "Primary Phone" field on Step 2
		softAssert.assertTrue(isPrimPhTwoFieldVisible(), "Primary Phone Number field is not visible");
		// Verify the "Email Address" field on Step 2
		softAssert.assertTrue(isEmailAddTwoFieldVisible(), "Email field is not visible");
		pause(2000);
		// Verify the "Payment Amount" field on Step 2, clear the existing amount and
		// add $10
		softAssert.assertTrue(isPayAmtTwoFieldVisible(), "Payment Amount field is not visible");
		pause(5000);
		clickPayAmtFld();
		clearPayAmtFld();
		String amt = "1";
		enterPayAmtInTheField(amt);
		pause(1000);
		// Verify the "Payment Information" Label
		softAssert.assertEquals(getPayInfoStepTwo(), PreLogPaymentProp.getPropValue("payInfoStepTwoTitle"),
				"Payment Information lable is not matchng.");
		// Verify the "Bank Account" option on Step 2
		softAssert.assertTrue(isPayAmtTwoFieldVisible(), "Payment Amount field is not visible");
		pause(10000);
		// Click on Bank Account Option
		clickBankAccOptTwoStp();
		pause(10000);

		// Verify the Account Holder Name field is visible
		isAccHoldNameField();
		// Click the Account Holder Name field
		clickAccHoldNameFld();
		// Enter Account Holder Name in the field
		enterAccHolderNameFld(bank.getAccountHolderName());

		// Verify the Routing Number field is visible
		isRoutNoField();
		// Click the Routing Number field
		clickRoutNoFld();
		// Enter Routing Number in the field
		enterRoutNoFld(bank.getRoutingNumber());

		// Verify the Confirming Routing Number field is visible
		isCongRoutNoField();
		// Click the Confirming Routing Number field
		clickConfRoutNoFld();
		// Enter Confirming Routing Number in the field
		enterConfRoutNoFld(bank.getRoutingNumber());
		pause(5000);

		// Verify the Bank Account Number field is visible
		isBnkAccNoField();
		// Click the Bank Account Number field
		clickBnkAccNoFld();
		// Enter Bank Account Number in the field
		enterBnkAccNoFld(bank.getBankAccountNumber());

		// Verify the Confirm Bank Account Number field is visible
		isConfBnkAccNoField();
		// Click the Confirm Bank Account Number field
		clickConfBnkAccNoFld();
		// Enter Confirm Bank Account Number in the field
		enterConfBnkAccNoFld(bank.getBankAccountNumber());

		// Select Account Type from Drop down
		Select ddAccountType = new Select(elementDDBankAccountType());
		ddAccountType.selectByVisibleText("Personal Checking");

		// Verify the First Name field is visible
		isFirstNameField();
		// Click the First Name field
		clickFirstNameFld();
		// Enter First Name in the field
		enterFirstNameFld(bank.getFirstName());

		// Verify the Last Name field is visible
		isLastNameField();
		// Click the Last Name field
		clickLastNameFld();
		// Enter Last Name in the field
		enterLastNameFld(bank.getLastName());

		// Verify the Address field is visible
		isAddressField();
		// Click the Address field
		clickAddressFld();
		// Enter Address in the field
		enterAddressFld(bank.getAddress());

		// Verify the City field is visible
		isCityField();
		// Click the City field
		clickCityFld();
		// Enter City in the field
		enterCityFld(bank.getCity());

		// Verify the State field is visible
		isStateField();
		// Click the State field
		clickStateFld();
		// Enter State in the field
		enterStateFld(bank.getState());

		// Verify the Zip Code field is visible
		isZipCodeField();
		// Click the Zip Code field
		clickZipCodeFld();
		// Enter Zip Code in the field
		enterZipCodeFld(bank.getZipCode());

		// Verify the "Cancel" button on Step 2
		softAssert.assertTrue(isCnclTwoStpBttnVisible(), "Cancel option is not visible");
		// Verify the "Next" button on Step 2
		softAssert.assertTrue(isNextBttnTwoVisible(), "Next option is not visible");
		// Click the Next field
		clickNxtTwoBttn();
		ExtentLogger.logInfo("Test Case execution for - verifyUIOnStepTwoPreLogPayBill - is Completed");

	}

	public void verifyStpOneErrorMssgs(SoftAssert softAssert) throws Exception {
		ExtentLogger.logInfo("To perform error message validation on Step 1 of PayBill Page");
		// click on PayBill link from login page
		clickPayBillLink();

		pause(3000);
		// Click on the Account No Field and enter Account No
		clickAccNoFld();
		enterAccNoInTheField(accNo);
		clickSNextButton();
		// Verify Error Message for Primary Phone No. Field
		softAssert.assertEquals(getprimaryPhErrorMessage(), PreLogPaymentProp.getPropValue("stepOnePrimPhError"),
				"Primary Phone No Error Mssg is not matchng.");
		pause(7000);
		clickPrimaryPhnFld();
		enterPrimayPhTheField(phNo);
		clearAccountNo();
		// Click on the Next BBt
		clickSNextButton();
		// Verify Error Message for Account No. Field
		softAssert.assertEquals(getacctNoErrorMessage(), PreLogPaymentProp.getPropValue("stepOneAccNoError"),
				"Account No Error Mssg matchng.");
		pause(3000);

	}

	public void verifyCancelBttnFunStepOne(SoftAssert softAssert) throws Exception {
		ExtentLogger.logInfo("To perform and validate cancel bttn functionality on step 1");
		// click on PayBill link from login page
		clickPayBillLink();

		pause(3000);
		// Click on the Account No Field and enter Account No
		clickAccNoFld();
		enterAccNoInTheField(accNo);
		// Click on the primary phone no. field an enter the no.
		clickPrimaryPhnFld();
		enterPrimayPhTheField(phNo);
		// Click on the Next BBt
		clickCanclStpOneBttn();
		LoginSteps loginSteps;
		loginSteps = new LoginSteps(driver);
		Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"),
				loginTextProp.getPropValue("loginPageTitle")));
		ExtentLogger.logInfo("checkCancelFunctnUsername Passed");
		pause(3000);

	}

	public void verifyBlnkErrorMsgStepTwoPreLogPayBill(SoftAssert softAssert) throws Exception {
		ExtentLogger.logInfo("To perform error message validation on step 2");

		Bank bank = ModelsConfiguration.readBankAccounts().getBankByAccountHolderName("Henry Jacob Bank Account");

		// click on PayBill link from login page
		clickPayBillLink();
		pause(3000);
		// Click on the Account No Field and enter Account No
		clickAccNoFld();
		enterAccNoInTheField(accNo);
		// Click on the primary phone no. field an enter the no.
		clickPrimaryPhnFld();
		enterPrimayPhTheField(phNo);
		// Click on the Next BBt
		clickSNextButton();
		pause(3000);

		// verify the "Pay Bill" Label
		softAssert.assertEquals(getPayBillLabelTwo(), PreLogPaymentProp.getPropValue("preLoginPageTitle"),
				"Pay Bill lable is not matchng.");
		// Verify the "Name" field on Step 2
		softAssert.assertTrue(isNameFieldVisible(), "Name field is not visible");
		// Verify the "Account" field on Step 2
		softAssert.assertTrue(isAccTwoFieldVisible(), "Account field is not visible");
		// Verify the "Amount Due" field on Step 2
		softAssert.assertTrue(isAmtDueTwoFieldVisible(), "Amount Due field is not visible");
		// Verify the "Primary Phone" field on Step 2
		softAssert.assertTrue(isPrimPhTwoFieldVisible(), "Primary Phone Number field is not visible");
		// Verify the "Email Address" field on Step 2
		softAssert.assertTrue(isEmailAddTwoFieldVisible(), "Email field is not visible");
		pause(2000);
		// Verify the "Payment Amount" field on Step 2, clear the existing amount and
		// add $10
		softAssert.assertTrue(isPayAmtTwoFieldVisible(), "Payment Amount field is not visible");
		pause(5000);
		clickPayAmtFld();
		clearPayAmtFld();
		String amt = "1";
		enterPayAmtInTheField(amt);
		pause(1000);
		// Verify the "Payment Information" Label
		softAssert.assertEquals(getPayInfoStepTwo(), PreLogPaymentProp.getPropValue("payInfoStepTwoTitle"),
				"Payment Information lable is not matchng.");
		// Verify the "Bank Account" option on Step 2
		softAssert.assertTrue(isPayAmtTwoFieldVisible(), "Payment Amount field is not visible");
		pause(10000);
		// Click on Bank Account Option
		clickBankAccOptTwoStp();
		pause(10000);

		// Verify the Account Holder Name field is visible
		isAccHoldNameField();
		// Click the Account Holder Name field
		clickAccHoldNameFld();
		// Enter Account Holder Name in the field
		enterAccHolderNameFld(bank.getAccountHolderName());

		// Verify the Routing Number field is visible
		isRoutNoField();
		// Click the Routing Number field
		clickRoutNoFld();
		// Enter Routing Number in the field
		enterRoutNoFld(bank.getRoutingNumber());

		// Verify the Confirming Routing Number field is visible
		isCongRoutNoField();
		// Click the Confirming Routing Number field
		clickConfRoutNoFld();
		// Enter Confirming Routing Number in the field
		enterConfRoutNoFld(bank.getRoutingNumber());
		pause(5000);

		// Verify the Bank Account Number field is visible
		isBnkAccNoField();
		// Click the Bank Account Number field
		clickBnkAccNoFld();
		// Enter Bank Account Number in the field
		enterBnkAccNoFld(bank.getBankAccountNumber());

		// Verify the Confirm Bank Account Number field is visible
		isConfBnkAccNoField();
		// Click the Confirm Bank Account Number field
		clickConfBnkAccNoFld();
		// Enter Confirm Bank Account Number in the field
		enterConfBnkAccNoFld(bank.getBankAccountNumber());

		// Select Account Type from Drop down
		Select ddAccountType = new Select(elementDDBankAccountType());
		ddAccountType.selectByVisibleText("Personal Checking");

		// Verify the First Name field is visible
		isFirstNameField();
		// Click the First Name field
		clickFirstNameFld();
		// Enter First Name in the field
		enterFirstNameFld(bank.getFirstName());

		// Verify the "Back" button on Step 3
		softAssert.assertTrue(isBackThreeStpBtnVisible(), "Back option is not visible");
		// Verify the "Submit" button on Step 3
		softAssert.assertTrue(isSubmitBtnThreeVisible(), "Submit option is not visible");
		// Click the Next field
		clickNxtTwoBttn();
		// Verify the "Step 2 Ent All Info Validation" Label
		softAssert.assertEquals(getallMandErrorMessage(), PreLogPaymentProp.getPropValue("stepTwoEntAllMandDetalError"),
				"All Mandatory Field Error is not matchng.");
		ExtentLogger.logInfo("Test Case execution for - verifyBlnkErrorMsgStepTwoPreLogPayBill - is Completed");

	}

	public void verifyUIOnStepThreePreLogPayBill(SoftAssert softAssert) throws Exception {
		ExtentLogger.logInfo("To perform UI Vald. on Step 3 of PayBill Page");
		// Fetching the Bank Details

		Bank bank = ModelsConfiguration.readBankAccounts().getBankByAccountHolderName("Henry Jacob Bank Account");

		// click on PayBill link from login page
		clickPayBillLink();
		pause(3000);
		// Click on the Account No Field and enter Account No
		clickAccNoFld();
		enterAccNoInTheField(accNo);
		// Click on the primary phone no. field an enter the no.
		clickPrimaryPhnFld();
		enterPrimayPhTheField(phNo);
		// Click on the Next BBt
		clickSNextButton();
		pause(3000);

		// verify the "Pay Bill" Label
		softAssert.assertEquals(getPayBillLabelTwo(), PreLogPaymentProp.getPropValue("preLoginPageTitle"),
				"Pay Bill lable is not matchng.");
		// Verify the "Name" field on Step 2
		softAssert.assertTrue(isNameFieldVisible(), "Name field is not visible");
		// Verify the "Account" field on Step 2
		softAssert.assertTrue(isAccTwoFieldVisible(), "Account field is not visible");
		// Verify the "Amount Due" field on Step 2
		softAssert.assertTrue(isAmtDueTwoFieldVisible(), "Amount Due field is not visible");
		// Verify the "Primary Phone" field on Step 2
		softAssert.assertTrue(isPrimPhTwoFieldVisible(), "Primary Phone Number field is not visible");
		// Verify the "Email Address" field on Step 2
		softAssert.assertTrue(isEmailAddTwoFieldVisible(), "Email field is not visible");
		pause(2000);
		// Verify the "Payment Amount" field on Step 2, clear the existing amount and
		// add $10
		softAssert.assertTrue(isPayAmtTwoFieldVisible(), "Payment Amount field is not visible");
		pause(5000);
		clickPayAmtFld();
		clearPayAmtFld();
		String amt = "1";
		enterPayAmtInTheField(amt);
		pause(1000);
		// Verify the "Payment Information" Label
		softAssert.assertEquals(getPayInfoStepTwo(), PreLogPaymentProp.getPropValue("payInfoStepTwoTitle"),
				"Payment Information lable is not matchng.");
		// Verify the "Bank Account" option on Step 2
		softAssert.assertTrue(isPayAmtTwoFieldVisible(), "Payment Amount field is not visible");
		pause(10000);
		// Click on Bank Account Option
		clickBankAccOptTwoStp();
		pause(10000);

		// Verify the Account Holder Name field is visible
		isAccHoldNameField();
		// Click the Account Holder Name field
		clickAccHoldNameFld();
		// Enter Account Holder Name in the field
		enterAccHolderNameFld(bank.getAccountHolderName());

		// Verify the Routing Number field is visible
		isRoutNoField();
		// Click the Routing Number field
		clickRoutNoFld();
		// Enter Routing Number in the field
		enterRoutNoFld(bank.getRoutingNumber());

		// Verify the Confirming Routing Number field is visible
		isCongRoutNoField();
		// Click the Confirming Routing Number field
		clickConfRoutNoFld();
		// Enter Confirming Routing Number in the field
		enterConfRoutNoFld(bank.getRoutingNumber());
		pause(5000);

		// Verify the Bank Account Number field is visible
		isBnkAccNoField();
		// Click the Bank Account Number field
		clickBnkAccNoFld();
		// Enter Bank Account Number in the field
		enterBnkAccNoFld(bank.getBankAccountNumber());

		// Verify the Confirm Bank Account Number field is visible
		isConfBnkAccNoField();
		// Click the Confirm Bank Account Number field
		clickConfBnkAccNoFld();
		// Enter Confirm Bank Account Number in the field
		enterConfBnkAccNoFld(bank.getBankAccountNumber());

		// Select Account Type from Drop down
		Select ddAccountType = new Select(elementDDBankAccountType());
		ddAccountType.selectByVisibleText("Personal Checking");

		// Verify the First Name field is visible
		isFirstNameField();
		// Click the First Name field
		clickFirstNameFld();
		// Enter First Name in the field
		enterFirstNameFld(bank.getFirstName());

		// Verify the Last Name field is visible
		isLastNameField();
		// Click the Last Name field
		clickLastNameFld();
		// Enter Last Name in the field
		enterLastNameFld(bank.getLastName());

		// Verify the Address field is visible
		isAddressField();
		// Click the Address field
		clickAddressFld();
		// Enter Address in the field
		enterAddressFld(bank.getAddress());

		// Verify the City field is visible
		isCityField();
		// Click the City field
		clickCityFld();
		// Enter City in the field
		enterCityFld(bank.getCity());

		// Verify the State field is visible
		isStateField();
		// Click the State field
		clickStateFld();
		// Enter State in the field
		enterStateFld(bank.getState());

		// Verify the Zip Code field is visible
		isZipCodeField();
		// Click the Zip Code field
		clickZipCodeFld();
		// Enter Zip Code in the field
		enterZipCodeFld(bank.getZipCode());

		// Verify the "Cancel" button on Step 2
		softAssert.assertTrue(isCnclTwoStpBttnVisible(), "Cancel Option is not visible");
		// Verify the "Next" button on Step 2
		softAssert.assertTrue(isNextBttnTwoVisible(), "Next Option is not visible");
		// Click the Next field
		clickNxtTwoBttn();
		// verify the "Pay Bill" Label
		softAssert.assertEquals(getPayBillLabelTwo(), PreLogPaymentProp.getPropValue("preLoginPageTitle"),
				"Pay Bill lable is not matchng.");
		// Verify the "Account Number" field on Step 3
		softAssert.assertTrue(isAccNoThreeFldVisible(), "Account No field is not visible");
		// Verify the "Service Address" field on Step 3
		softAssert.assertTrue(isServAddThreeFldVisible(), "Service Address field is not visible");
		// Verify the "Bill Amount" field on Step 3
		softAssert.assertTrue(isBillAmtThreeFldVisible(), "Bill Amount field is not visible");
		// Verify the "Transaction Fees" field on Step 3
		softAssert.assertTrue(isTransFeeThreeFldVisible(), "Transaction Fee field is not visible");
		// Verify the "Transaction Amount" field on Step 3
		softAssert.assertTrue(isTransAmtThreeFldVisible(), "Transaction Amount field is not visible");
		// Verify the "Payment Date" field on Step 3
		softAssert.assertTrue(ispayDateThreeFldVisible(), "Pay Date field is not visible");
		// Verify the "Bank" field on Step 3
		softAssert.assertTrue(isBankThreeFldVisible(), "Bank field is not visible");
		// Verify the "Step 3 Disclaimer 1" Label
		softAssert.assertEquals(getDiscOneLblThreeMessage(), PreLogPaymentProp.getPropValue("disclaimerOneMssg"),
				"All Mandatory Field Error is not matchng.");
		// Verify the "Back" button on Step 3
		softAssert.assertTrue(isBackThreeStpBtnVisible(), "Back field is not visible");
		// Verify the "Submit" button on Step 3
		softAssert.assertTrue(isSubmitBtnThreeVisible(), "Submit field is not visible");
		// Click on Back Button to go back to Step 2
		clickBackThreeBtn();
		// verify the "Pay Bill" Label
		softAssert.assertEquals(getPayBillLabelTwo(), PreLogPaymentProp.getPropValue("preLoginPageTitle"),
				"Pay Bill lable is not matchng.");
		// Verify the "Name" field on Step 2
		softAssert.assertTrue(isNameFieldVisible(), "Name field is not visible");
		// Verify the "Account" field on Step 2
		softAssert.assertTrue(isAccTwoFieldVisible(), "Account field is not visible");
		ExtentLogger.logInfo("Test Case execution for - verifyUIOnStepThreePreLogPayBill - is Completed");

	}

	public void verifyUIOnStepFourPreLogPayBill(SoftAssert softAssert) throws Exception {
		ExtentLogger.logInfo("To perform UI Vald. on Step 4 of PayBill Page");
		Bank bank = ModelsConfiguration.readBankAccounts().getBankByAccountHolderName("Henry Jacob Bank Account");

		// click on PayBill link from login page
		clickPayBillLink();
		pause(3000);
		// Click on the Account No Field and enter Account No
		clickAccNoFld();
		enterAccNoInTheField(accNo);
		// Click on the primary phone no. field an enter the no.
		clickPrimaryPhnFld();
		enterPrimayPhTheField(phNo);
		// Click on the Next BBt
		clickSNextButton();
		pause(3000);

		// verify the "Pay Bill" Label
		softAssert.assertEquals(getPayBillLabelTwo(), PreLogPaymentProp.getPropValue("preLoginPageTitle"),
				"Pay Bill lable is not matchng.");
		// Verify the "Name" field on Step 2
		softAssert.assertTrue(isNameFieldVisible(), "Name field is not visible");
		// Verify the "Account" field on Step 2
		softAssert.assertTrue(isAccTwoFieldVisible(), "Account field is not visible");
		// Verify the "Amount Due" field on Step 2
		softAssert.assertTrue(isAmtDueTwoFieldVisible(), "Amount Due field is not visible");
		// Verify the "Primary Phone" field on Step 2
		softAssert.assertTrue(isPrimPhTwoFieldVisible(), "Primary Phone Number field is not visible");
		// Verify the "Email Address" field on Step 2
		softAssert.assertTrue(isEmailAddTwoFieldVisible(), "Email field is not visible");
		pause(2000);
		// Verify the "Payment Amount" field on Step 2, clear the existing amount and
		// add $10
		softAssert.assertTrue(isPayAmtTwoFieldVisible(), "Payment Amount field is not visible");
		pause(5000);
		clickPayAmtFld();
		clearPayAmtFld();
		String amt = "1";
		enterPayAmtInTheField(amt);
		pause(1000);
		// Verify the "Payment Information" Label
		softAssert.assertEquals(getPayInfoStepTwo(), PreLogPaymentProp.getPropValue("payInfoStepTwoTitle"),
				"Payment Information lable is not matchng.");
		// Verify the "Bank Account" option on Step 2
		softAssert.assertTrue(isPayAmtTwoFieldVisible(), "Payment Amount field is not visible");
		pause(10000);
		// Click on Bank Account Option
		clickBankAccOptTwoStp();
		pause(10000);

		// Verify the Account Holder Name field is visible
		isAccHoldNameField();
		// Click the Account Holder Name field
		clickAccHoldNameFld();
		// Enter Account Holder Name in the field
		enterAccHolderNameFld(bank.getAccountHolderName());

		// Verify the Routing Number field is visible
		isRoutNoField();
		// Click the Routing Number field
		clickRoutNoFld();
		// Enter Routing Number in the field
		enterRoutNoFld(bank.getRoutingNumber());

		// Verify the Confirming Routing Number field is visible
		isCongRoutNoField();
		// Click the Confirming Routing Number field
		clickConfRoutNoFld();
		// Enter Confirming Routing Number in the field
		enterConfRoutNoFld(bank.getRoutingNumber());
		pause(5000);

		// Verify the Bank Account Number field is visible
		isBnkAccNoField();
		// Click the Bank Account Number field
		clickBnkAccNoFld();
		// Enter Bank Account Number in the field
		enterBnkAccNoFld(bank.getBankAccountNumber());

		// Verify the Confirm Bank Account Number field is visible
		isConfBnkAccNoField();
		// Click the Confirm Bank Account Number field
		clickConfBnkAccNoFld();
		// Enter Confirm Bank Account Number in the field
		enterConfBnkAccNoFld(bank.getBankAccountNumber());

		// Select Account Type from Drop down
		Select ddAccountType = new Select(elementDDBankAccountType());
		ddAccountType.selectByVisibleText("Personal Checking");

		// Verify the First Name field is visible
		isFirstNameField();
		// Click the First Name field
		clickFirstNameFld();
		// Enter First Name in the field
		enterFirstNameFld(bank.getFirstName());

		// Verify the Last Name field is visible
		isLastNameField();
		// Click the Last Name field
		clickLastNameFld();
		// Enter Last Name in the field
		enterLastNameFld(bank.getLastName());

		// Verify the Address field is visible
		isAddressField();
		// Click the Address field
		clickAddressFld();
		// Enter Address in the field
		enterAddressFld(bank.getAddress());

		// Verify the City field is visible
		isCityField();
		// Click the City field
		clickCityFld();
		// Enter City in the field
		enterCityFld(bank.getCity());

		// Verify the State field is visible
		isStateField();
		// Click the State field
		clickStateFld();
		// Enter State in the field
		enterStateFld(bank.getState());

		// Verify the Zip Code field is visible
		isZipCodeField();
		// Click the Zip Code field
		clickZipCodeFld();
		// Enter Zip Code in the field
		enterZipCodeFld(bank.getZipCode());

		// Verify the "Cancel" button on Step 2
		softAssert.assertTrue(isCnclTwoStpBttnVisible(), "Cancel Option is not visible");
		// Verify the "Next" button on Step 2
		softAssert.assertTrue(isNextBttnTwoVisible(), "Next Option is not visible");
		// Click the Next field
		clickNxtTwoBttn();
		// verify the "Pay Bill" Label
		softAssert.assertEquals(getPayBillLabelTwo(), PreLogPaymentProp.getPropValue("preLoginPageTitle"),
				"Pay Bill lable is not matchng.");
		// Verify the "Account Number" field on Step 3
		softAssert.assertTrue(isAccNoThreeFldVisible(), "Account No field is not visible");
		// Verify the "Service Address" field on Step 3
		softAssert.assertTrue(isServAddThreeFldVisible(), "Service Address field is not visible");
		// Verify the "Bill Amount" field on Step 3
		softAssert.assertTrue(isBillAmtThreeFldVisible(), "Bill Amount field is not visible");
		// Verify the "Transaction Fees" field on Step 3
		softAssert.assertTrue(isTransFeeThreeFldVisible(), "Transaction Fee field is not visible");
		// Verify the "Transaction Amount" field on Step 3
		softAssert.assertTrue(isTransAmtThreeFldVisible(), "Transaction Amount field is not visible");
		// Verify the "Payment Date" field on Step 3
		softAssert.assertTrue(ispayDateThreeFldVisible(), "Pay Date field is not visible");
		// Verify the "Bank" field on Step 3
		softAssert.assertTrue(isBankThreeFldVisible(), "Bank field is not visible");
		// Verify the "Step 3 Disclaimer 1" Label
		softAssert.assertEquals(getDiscOneLblThreeMessage(), PreLogPaymentProp.getPropValue("disclaimerOneMssg"),
				"All Mandatory Field Error is not matchng.");
		// Verify the "Back" button on Step 3
		softAssert.assertTrue(isBackThreeStpBtnVisible(), "Back field is not visible");
		// Verify the "Submit" button on Step 3
		softAssert.assertTrue(isSubmitBtnThreeVisible(), "Submit field is not visible");
		// Click on Submit Button to go to Step 4
		clickSubmitThreeBtn();
		// verify the "Payment Successful" Label
		softAssert.assertEquals(getPaySuccessLabel(), PreLogPaymentProp.getPropValue("paymentSuccessMessage"),
				"Pay Bill Success Message is not matchng.");
		ExtentLogger.logInfo("Test Case execution for - verifyUIOnStepFourPreLogPayBill - is Completed");

	}

}
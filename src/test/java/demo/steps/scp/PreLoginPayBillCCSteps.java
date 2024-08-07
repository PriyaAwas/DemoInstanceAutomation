package demo.steps.scp;

import demo.pageobjects.PreLoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import sew.ai.config.Configuration;
import sew.ai.config.ModelsConfiguration;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.models.Bank;
import sew.ai.models.Card;
import sew.ai.steps.scp.DashboardSteps;
import sew.ai.steps.scp.SignOutSteps;
import sew.ai.utils.PropertiesUtil;

import java.sql.SQLException;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static sew.ai.steps.scp.DashboardSteps.dashboardTextProp;

public class PreLoginPayBillCCSteps extends PreLoginPage {

	private static final Logger log = LogManager.getLogger(PreLoginPayBillCCSteps.class);
	public static PropertiesUtil loginTextProp;
	public static PropertiesUtil preLoginConnectMeProp;
	public static PropertiesUtil signOutTextProp;
	public static PropertiesUtil preLoginPaymentLocationsProp;
	public static PropertiesUtil preLoginPaymentProp;
	public static PropertiesUtil preLoginWaysToSaveProp;

	public PreLoginPayBillCCSteps(WebDriver driver) {
		super(driver);
		loginTextProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.LOGIN_TXT_FILENAME);
		preLoginConnectMeProp = new PropertiesUtil(
				FilePaths.SCP_TEXT_PROPERTIES + Constants.PRE_LOG_CONNECT_ME_TXT_FILENAME);
		signOutTextProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.SIGNOUT_TXT_FILENAME);
		preLoginPaymentLocationsProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.SCM_PAYMENT_LOCATIONS_TXT_FILENAME);
		preLoginPaymentProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.SCM_PAYMENT_TXT_FILENAME);
		preLoginWaysToSaveProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.SCM_WAYSTOSAVE_TXT_FILENAME);
	}
	
	public void populatePaymentFormStepOne(String accountNumber, String phoneNumber) {
		populateAccountNumber(accountNumber);
		populatePhoneNumber(phoneNumber);
		ExtentLogger.logInfo("account number and phone number populated");
	}
	
	public String payWithInvalidDetails() {
		populateAccountNumber("123456789012");
		populatePhoneNumber("1234567890");
		clickNextBtn();
		String errMsg = getToastMessage();
		ExtentLogger.logInfo("cannot pay with invalid details");
		return errMsg;
	}
	
	public String payWithBlankDetails() {
		clickNextBtn();
		String errMsg = getToastMessageWithoutWait();
		clickToastCloseBtn();
		ExtentLogger.logInfo("cannot pay with blank details");
		return errMsg;
	}
	
	public String payWithBlankAccountNumber() {
		populatePhoneNumber("1234567890");
		clickNextBtn();		
		String errMsg = getErrorMessage();
		clearPhoneNumberField();
		ExtentLogger.logInfo("cannot pay with blank account number");
		return errMsg;
	}
	
	public String payWithBlankPhoneNumber() {
		populateAccountNumber("123456789012");
		clickNextBtn();
		String errMsg = getErrorMessage();
		clearAccountNumberField();
		ExtentLogger.logInfo("cannot pay with blank phone number");
		return errMsg;
	}
	
	public void payTheApplicationWrongCreds(SoftAssert softAssert) {
		clickPaymentsLnk();
		// Verify login with blank creds
		softAssert.assertEquals(payWithBlankDetails(), preLoginPaymentProp.getPropValue("payWithBlankCredsErrMsg"),
				"Pay with Blank creds error message not matched.");
		// Verify login with blank account number
		softAssert.assertEquals(payWithBlankAccountNumber(),
				preLoginPaymentProp.getPropValue("payWithBlankAccountNumber"),
				"Blank username field validation not correct.");
		
		// Verify login with blank phone number
		softAssert.assertEquals(payWithBlankPhoneNumber(),
				preLoginPaymentProp.getPropValue("payWithBlankPhoneNumber"),
				"Blank username field validation not correct.");
		// Verify login with wrong username and password.
		softAssert.assertEquals(payWithInvalidDetails(), preLoginPaymentProp.getPropValue("payWithInvalidsCredsErrMsg"),
				"Wrong toast when paying using invalid creds.");
	}

	public void verifyMoveToStpTwoAndUIVal(SoftAssert softAssert) {
		ExtentLogger.logInfo("To move to Step 2 of pre-login page successfully and perfom UI Vald.");
		// click on PayBill link from login page
		clickPaymentsLnk();
		pause(3000);
		// Click on the Account No Field and enter Account No
		clickAccNoFld();
		enterAccNoInTheField("2711765963");
		// Click on the primary phone no. field an enter the no.
		clickPrimaryPhnFld();
		enterPrimayPhTheField("(424) 271-4361");
		// Click on the Next BBt
		clickNextBtn();
		pause(3000);

	}
	public void pageTwoCCPageObjects(SoftAssert softAssert) {
		// verify the "Pay Bill" Label
		softAssert.assertEquals(getPayBillLabelTwo(), preLoginPaymentProp.getPropValue("preLoginPageTitle"),
				"Pay Bill label is not matching.");
		// Verify the "Name" field on Step 2
		softAssert.assertTrue(isNameFieldVisible(), "Name field is not visible");
		// Verify the "Account" field on Step 2
		softAssert.assertTrue(isAccTwoFieldVisible(), "Account field is not visible");
		// Verify the "Amount Due" field on Step 2
		softAssert.assertTrue(isAmtDueTwoFieldVisible(), "Amount Due is not visible");
		// Verify the "Primary Phone" field on Step 2
		softAssert.assertTrue(isPrimPhTwoFieldVisible(), "Primary phone number is not visible");
		// Verify the "Email Address" field on Step 2
		softAssert.assertTrue(isEmailAddTwoFieldVisible(), "Email is not visible");
		pause(2000);
		// Verify the "Payment Amount" field on Step 2, clear the existing amount and
		// add $10
		softAssert.assertTrue(isPayAmtTwoFieldVisible(), "Payment amount is not visible");
		pause(2000);
		clickPayAmtFld();
		clearPayAmtFld();
		enterPayAmtInTheField("10");
		pause(1000);
		// Verify the "Payment Information" Label
		softAssert.assertEquals(getPayInfoStepTwo(), preLoginPaymentProp.getPropValue("payInfoStepTwoTitle"),
				"Payment Information label is not matching.");
		// Verify the "Bank Account" option on Step 2
		softAssert.assertTrue(isCardOptionVisible(), "Card option is not visible");
		clickCardOption();
		pause(5000);
		// Card Details
		softAssert.assertTrue(isCardHolderNameFieldVisible(), "Card holder name is not visible");
		enterCardHolderNameInTheField("Test");
		softAssert.assertTrue(isCardNumberFieldVisible(), "Card number is not visible");
		enterCardNumberInTheField("4111111111111111");
		softAssert.assertTrue(isMonthFieldVisible(), "Month field is not visible");
		clickMonthFld();
		pause(1000);
		clickMonthOption();
		pause(1000);
		softAssert.assertTrue(isYearFieldVisible(), "Year field is not visible");
		clickYearFld();
		pause(1000);
		clickYearOption();
		pause(1000);
		softAssert.assertTrue(isSecurityCodeFieldVisible(), "Sec Code is not visible");
		enterSecurityCodeInTheField("123");
		// Billing Address
		softAssert.assertTrue(isFirstNameFieldVisible(), "First Name is not visible");
		enterFirstNameInTheField("Test");
		softAssert.assertTrue(isLastNameFieldVisible(), "Last Name is not visible");
		enterLastNameInTheField("Test");
		softAssert.assertTrue(isAddressFieldVisible(), "Address field is not visible");
		enterAddressInTheField("Test");
		softAssert.assertTrue(isCityFieldVisible(), "City Field is not visible");
		enterCityInTheField("TE");
		softAssert.assertTrue(isStateFieldVisible(), "State Field is not visible");
		enterStateInTheField("Test");
		softAssert.assertTrue(isZipCodeFieldVisible(), "Zip code field is not visible");
		enterZipCodeInTheField("12345");
		clickNextBtn2();
	}

	public void zeroPaymentValue(SoftAssert softAssert) {
		// verify the "Pay Bill" Label
		softAssert.assertEquals(getPayBillLabelTwo(), preLoginPaymentProp.getPropValue("preLoginPageTitle"),
				"Pay Bill label is not matching.");
		pause(2000);
		// Verify the "Payment Amount" field on Step 2, clear the existing amount and
		// add $10
		softAssert.assertTrue(isPayAmtTwoFieldVisible(), "Payment amount is not visible");
		pause(2000);
		clickPayAmtFld();
		clearPayAmtFld();
		enterPayAmtInTheField("0");
		pause(1000);
		// Verify the "Payment Information" Label
		softAssert.assertEquals(getPayInfoStepTwo(), preLoginPaymentProp.getPropValue("payInfoStepTwoTitle"),
				"Payment Information label is not matching.");
		// Verify the "Bank Account" option on Step 2
		softAssert.assertTrue(isCardOptionVisible(), "Card option is not visible");
		clickCardOption();
		pause(5000);
		// Card Details
		softAssert.assertTrue(isCardHolderNameFieldVisible(), "Card holder name is not visible");
		enterCardHolderNameInTheField("Test");
		softAssert.assertTrue(isCardNumberFieldVisible(), "Card number is not visible");
		enterCardNumberInTheField("4111111111111111");
		softAssert.assertTrue(isMonthFieldVisible(), "Month field is not visible");
		clickMonthFld();
		pause(1000);
		clickMonthOption();
		pause(1000);
		softAssert.assertTrue(isYearFieldVisible(), "Year field is not visible");
		clickYearFld();
		pause(1000);
		clickYearOption();
		pause(1000);
		softAssert.assertTrue(isSecurityCodeFieldVisible(), "Sec code is not visible");
		enterSecurityCodeInTheField("123");
		// Billing Address
		softAssert.assertTrue(isFirstNameFieldVisible(), "First name is not visible");
		enterFirstNameInTheField("Test");
		softAssert.assertTrue(isLastNameFieldVisible(), "Last name is not visible");
		enterLastNameInTheField("Test");
		softAssert.assertTrue(isAddressFieldVisible(), "Address field is not visible");
		enterAddressInTheField("Test");
		softAssert.assertTrue(isCityFieldVisible(), "City field is not visible");
		enterCityInTheField("TE");
		softAssert.assertTrue(isStateFieldVisible(), "State field is not visible");
		enterStateInTheField("Test");
		softAssert.assertTrue(isZipCodeFieldVisible(), "Zip code field is not visible");
		enterZipCodeInTheField("12345");
		clickNextBtn2();
		softAssert.assertEquals(getPayBillLabelForZeroTwo(), preLoginPaymentProp.getPropValue("payWithZeroAmount"),
				"Pay Bill label is not matching.");
	}

	public void decimalPaymentValue(SoftAssert softAssert) {
		// verify the "Pay Bill" Label
		softAssert.assertEquals(getPayBillLabelTwo(), preLoginPaymentProp.getPropValue("preLoginPageTitle"),
				"Pay Bill label is not matching.");
		pause(2000);
		// Verify the "Payment Amount" field on Step 2, clear the existing amount and
		// add $10
		softAssert.assertTrue(isPayAmtTwoFieldVisible(), "Payment amount is not visible");
		pause(2000);
		clickPayAmtFld();
		clearPayAmtFld();
		enterPayAmtInTheField("10.12");
		pause(1000);
		// Verify the "Payment Information" Label
		softAssert.assertEquals(getPayInfoStepTwo(), preLoginPaymentProp.getPropValue("payInfoStepTwoTitle"),
				"Payment Information label is not matching.");
		// Verify the "Bank Account" option on Step 2
		softAssert.assertTrue(isCardOptionVisible(), "Card option is not visible");
		clickCardOption();
		pause(5000);
		// Card Details
		Card card = ModelsConfiguration.readCards().getCardByNameOnCard("John Wick Visa Card");
		softAssert.assertTrue(isCardHolderNameFieldVisible(), "Card holder name is not visible");
		enterCardHolderNameInTheField(card.getNameOnCard());
		softAssert.assertTrue(isCardNumberFieldVisible(), "Card number field is not visible");
		enterCardNumberInTheField(card.getCardNumber());
		softAssert.assertTrue(isMonthFieldVisible(), "Month field is not visible");
		clickMonthFld();
		pause(1000);
		clickMonthOption();
		pause(1000);
		softAssert.assertTrue(isYearFieldVisible(), "Year field is not visible");
		clickYearFld();
		pause(1000);
		clickYearOption();
		pause(1000);
		softAssert.assertTrue(isSecurityCodeFieldVisible(), "Sec code field is not visible");
		enterSecurityCodeInTheField(card.getCvv());
		// Billing Address
		softAssert.assertTrue(isFirstNameFieldVisible(), "First name field is not visible");
		enterFirstNameInTheField(card.getFirstName());
		softAssert.assertTrue(isLastNameFieldVisible(), "Last name field is not visible");
		enterLastNameInTheField(card.getLastName());
		softAssert.assertTrue(isAddressFieldVisible(), "Address field is not visible");
		enterAddressInTheField(card.getAddress());
		softAssert.assertTrue(isCityFieldVisible(), "City field is not visible");
		enterCityInTheField(card.getCity());
		softAssert.assertTrue(isStateFieldVisible(), "State field is not visible");
		enterStateInTheField(card.getState());
		softAssert.assertTrue(isZipCodeFieldVisible(), "Zip code field is not visible");
		enterZipCodeInTheField(card.getState());
		clickNextBtn2();
		pause(3000);
	}

	public void fieldSpecificValidationMessage(SoftAssert softAssert) {
		// verify the "Pay Bill" Label
		softAssert.assertEquals(getPayBillLabelTwo(), preLoginPaymentProp.getPropValue("preLoginPageTitle"),
				"Pay Bill label is not matching.");
		pause(2000);
		// Verify the "Payment Amount" field on Step 2, clear the existing amount and
		// add $10
		softAssert.assertTrue(isPayAmtTwoFieldVisible(), "Payment amount field is not visible");
		pause(2000);
		clickPayAmtFld();
		clearPayAmtFld();
		enterPayAmtInTheField("10");
		pause(1000);
		// Verify the "Payment Information" Label
		softAssert.assertEquals(getPayInfoStepTwo(), preLoginPaymentProp.getPropValue("payInfoStepTwoTitle"),
				"Payment Information label is not matching.");
		// Verify the "Bank Account" option on Step 2
		softAssert.assertTrue(isCardOptionVisible(), "Card option is not visible");
		clickCardOption();
		pause(5000);
		// Card Details
		Card card = ModelsConfiguration.readCards().getCardByNameOnCard("John Wick Visa Card");
		softAssert.assertTrue(isCardHolderNameFieldVisible(), "Card holder name field is not visible");
		enterCardHolderNameInTheField(card.getNameOnCard());
		softAssert.assertTrue(isCardNumberFieldVisible(), "Card number field is not visible");
		enterCardNumberInTheField(card.getCardNumber());
		softAssert.assertTrue(isMonthFieldVisible(), "Month field is not visible");
		clickMonthFld();
		pause(1000);
		clickMonthOption();
		pause(1000);
		softAssert.assertTrue(isYearFieldVisible(), "Year field is not visible");
		clickYearFld();
		pause(1000);
		clickYearOption();
		pause(1000);
		softAssert.assertTrue(isSecurityCodeFieldVisible(), "Sec code field is not visible");
		enterSecurityCodeInTheField(card.getCvv());
		// Billing Address
		softAssert.assertTrue(isFirstNameFieldVisible(), "First name is not visible");
		enterFirstNameInTheField(card.getFirstName());
		softAssert.assertTrue(isLastNameFieldVisible(), "Last name is not visible");
		enterLastNameInTheField(card.getLastName());
		softAssert.assertTrue(isAddressFieldVisible(), "Address is not visible");
		enterAddressInTheField(card.getAddress());
		softAssert.assertTrue(isCityFieldVisible(), "City is not visible");
		enterCityInTheField(card.getCity());
		softAssert.assertTrue(isStateFieldVisible(), "State is not visible");
		enterStateInTheField(card.getState());
		softAssert.assertTrue(isZipCodeFieldVisible(), "Zip code is not visible");
		enterZipCodeInTheField(card.getZipCode());

		clearCardHolderNameInTheField();
		clickNextBtn2();
		softAssert.assertEquals(getBlankCardName(), preLoginPaymentProp.getPropValue("payWithNoCardName"),
				"Blank card name label is not matching.");
		enterCardHolderNameInTheField("Test");

		clearCardHolderNameInTheField();
		enterCardHolderNameInTheField("test-test");
		clearCardNumberInTheField();
		softAssert.assertEquals(getCardHolderName(), preLoginPaymentProp.getPropValue("payWithHyphenCardName"),
				"Card holder name is not matching.");
		enterCardHolderNameInTheField("Test");

		clearCardNumberInTheField();
		clickNextBtn2();
		softAssert.assertEquals(getBlankCardNumber(), preLoginPaymentProp.getPropValue("payWithNoCardNumber"),
				"Blank card number label is not matching.");
		enterCardNumberInTheField("4111111111111111");

		clearMonthOption();
		clickNextBtn2();
		softAssert.assertEquals(getBlankMonth(), preLoginPaymentProp.getPropValue("payWithNoMonth"),
				"Blank month label is not matching.");
		clickMonthFld();
		pause(1000);
		clickMonthOption();
		pause(1000);

		clearYearOption();
		clickNextBtn2();
		softAssert.assertEquals(getBlankYear(), preLoginPaymentProp.getPropValue("payWithNoYear"),
				"Blank year label is not matching.");
		clickYearFld();
		pause(1000);
		clickYearOption();
		pause(1000);

		clearSecCodeInTheField();
		clickNextBtn2();
		softAssert.assertEquals(getBlankSecCode(), preLoginPaymentProp.getPropValue("payWithNoSecCode"),
				"Blank Sec code label is not matching.");
		enterSecurityCodeInTheField("123");

		clearLastNameInTheField();
		clickNextBtn2();
		softAssert.assertEquals(getBlankLastName(), preLoginPaymentProp.getPropValue("payWithNoLastName"),
				"Blank last name label is not matching.");
		enterLastNameInTheField("Test");

		clearAddressInTheField();
		clickNextBtn2();
		softAssert.assertEquals(getBlankAddress(), preLoginPaymentProp.getPropValue("payWithNoAddress"),
				"Blank address label is not matching.");
		enterAddressInTheField("Test");

		clearCityInTheField();
		clickNextBtn2();
		softAssert.assertEquals(getBlankCity(), preLoginPaymentProp.getPropValue("payWithNoCity"),
				"Blank city label is not matching.");
		enterCityInTheField("Test");

		clearStateInTheField();
		clickNextBtn2();
		softAssert.assertEquals(getBlankState(), preLoginPaymentProp.getPropValue("payWithNoState"),
				"Blank state label is not matching.");
		enterStateInTheField("TE");

		clearZipCodeInTheField();
		clickNextBtn2();
		softAssert.assertEquals(getBlankZipCode(), preLoginPaymentProp.getPropValue("payWithNoZip"),
				"Blank zip code label is not matching.");
		enterZipCodeInTheField("12345");

		clearEmailInTheField();
		enterEmailInTheField("test");
		clickNextBtn2();
		softAssert.assertEquals(getInvalidEmailLabelTwo(), preLoginPaymentProp.getPropValue("invalidEmail"),
				"Invalid email address label is not matching.");
		enterEmailInTheField("test@test.com");

		clearPhoneInTheField();
		enterPhoneInTheField("423");
		clickNextBtn2();
		softAssert.assertEquals(getInvalidPhoneLabelTwo(), preLoginPaymentProp.getPropValue("invalidPhone"),
				"Invalid phone number label is not matching.");
		enterPhoneInTheField("1234567890");
		pause(2000);
	}

	public void creditCardIcons(SoftAssert softAssert) {
		// verify the "Pay Bill" Label
		softAssert.assertEquals(getPayBillLabelTwo(), preLoginPaymentProp.getPropValue("preLoginPageTitle"),
				"Pay Bill label is not matching.");
		pause(2000);
		// Verify the "Payment Amount" field on Step 2, clear the existing amount and
		// add $10
		softAssert.assertTrue(isPayAmtTwoFieldVisible(), "Payment amount field is not visible");
		pause(2000);
		clickPayAmtFld();
		clearPayAmtFld();
		enterPayAmtInTheField("10.12");
		pause(1000);

		// Verify the "Payment Information" Label
		softAssert.assertEquals(getPayInfoStepTwo(), preLoginPaymentProp.getPropValue("payInfoStepTwoTitle"),
				"Payment Information label is not matching.");
		// Verify the "Bank Account" option on Step 2
		softAssert.assertTrue(isCardOptionVisible(), "Card is not visible");
		clickCardOption();
		pause(3000);
		// Card Details
		softAssert.assertTrue(isCardNumberFieldVisible(), "Card number is not visible");

		// Visa
		Card card = ModelsConfiguration.readCards().getCardByNameOnCard("John Wick Visa Card");
		enterCardNumberInTheField(card.getCardNumber());
		softAssert.assertTrue(isCardNumberVisa(), "Card number is not visible");
		clearCardNumberInTheField();
		pause(2000);
		ExtentLogger.logInfo("Visa Complete");

		// Mastercard
		Card card2 = ModelsConfiguration.readCards().getCardByNameOnCard("Terrance Luis Master Card");
		enterCardNumberInTheField(card2.getCardNumber());
		softAssert.assertTrue(isCardNumberMc(), "Card number is not visible");
		clearCardNumberInTheField();
		pause(2000);
		ExtentLogger.logInfo("Mastercard Complete");

		// Discovery
		Card card3 = ModelsConfiguration.readCards().getCardByNameOnCard("John Doe Discover Card");
		enterCardNumberInTheField(card3.getCardNumber());
		softAssert.assertTrue(isCardNumberDiscover(), "Card number is not visible");
		clearCardNumberInTheField();
		pause(2000);
		ExtentLogger.logInfo("Discovery Complete");

		// Amex
		Card card4 = ModelsConfiguration.readCards().getCardByNameOnCard("Hanna Jones AMEX");
		enterCardNumberInTheField(card4.getCardNumber());
		softAssert.assertTrue(isCardNumberAmex(), "Card number is not visible");
		clearCardNumberInTheField();
		pause(2000);
		ExtentLogger.logInfo("Amex Complete");

		// Jcb
		enterCardNumberInTheField("3530111333300000");
		softAssert.assertTrue(isCardNumberJcb(), "Card number is not visible");
		clearCardNumberInTheField();
		pause(2000);
		ExtentLogger.logInfo("JCB Complete");

		// Cup
		enterCardNumberInTheField("8171999927660000");
		softAssert.assertTrue(isCardNumberCup(), "Card number is not visible");
		clearCardNumberInTheField();
		pause(2000);
		ExtentLogger.logInfo("CUP Complete");
	}

	public void verifyUIOnStepFourPreLogPayBill(SoftAssert softAssert) {
		waitForPageToLoad();
		// Verify the "Payment Amount" field on Step 2, clear the existing amount and
		// add $10
		softAssert.assertTrue(isPayAmtTwoFieldVisible(), "Payment amount is not visible");
		pause(3000);
		clickPayAmtFld();
		pause(2000);
		clearPayAmtFld();
		pause(1000);
		enterPayAmtInTheField("10.25");
		pause(1000);
		// Verify the "card" option on Step 2
		softAssert.assertTrue(isCardOptionVisible(), "Card is not visible");
		clickCardOption();
		pause(2000);
		// Card Details
		softAssert.assertTrue(isCardHolderNameFieldVisible(), "Card holder name is not visible");
		enterCardHolderNameInTheField("Test");
		softAssert.assertTrue(isCardNumberFieldVisible(), "Card number is not visible");
		Card card = ModelsConfiguration.readCards().getCardByNameOnCard("John Wick Visa Card");
		enterCardNumberInTheField(card.getCardNumber());
		softAssert.assertTrue(isMonthFieldVisible(), "Month is not visible");
		clickMonthFld();
		clickMonthOption();
		softAssert.assertTrue(isYearFieldVisible(), "Year is not visible");
		clickYearFld();
		clickYearOption();
		softAssert.assertTrue(isSecurityCodeFieldVisible(), "Sec Code is not visible");
		enterSecurityCodeInTheField(card.getCvv());
		// Billing Address
		softAssert.assertTrue(isFirstNameFieldVisible(), "First name is not visible");
		enterFirstNameInTheField(card.getFirstName());
		softAssert.assertTrue(isLastNameFieldVisible(), "Last name is not visible");
		enterLastNameInTheField(card.getLastName());
		softAssert.assertTrue(isAddressFieldVisible(), "Address is not visible");
		enterAddressInTheField(card.getAddress());
		softAssert.assertTrue(isCityFieldVisible(), "City is not visible");
		enterCityInTheField(card.getCity());
		softAssert.assertTrue(isStateFieldVisible(), "State is not visible");
		enterStateInTheField(card.getState());
		softAssert.assertTrue(isZipCodeFieldVisible(), "Zip code is not visible");
		enterZipCodeInTheField(card.getZipCode());

		// Verify the "Cancel" button on Step 2
		softAssert.assertTrue(isCnclTwoStpBttnVisible(), "Cancel is not visible");
		// Verify the "Next" button on Step 2
		softAssert.assertTrue(isNextBttnTwoVisible(), "Next is not visible");
		// Click the Next field
		clickNextBtn2();
		ExtentLogger.logInfo("Step-2 Complete");
		// verify the "Pay Bill" Label
		softAssert.assertEquals(getPayBillLabelTwo(), preLoginPaymentProp.getPropValue("preLoginPageTitle"),
				"Pay Bill label is not matching.");
		// Verify the "Account Number" field on Step 3
		softAssert.assertTrue(isAccNoThreeFldVisible(), "Acc number is not visible");
		// Verify the "Service Address" field on Step 3
		softAssert.assertTrue(isServAddThreeFldVisible(), "Service address is not visible");
		// Verify the "Bill Amount" field on Step 3
		softAssert.assertTrue(isBillAmtThreeFldVisible(), "Bill amount is not visible");
		// Verify the "Transaction Fees" field on Step 3
		softAssert.assertTrue(isTransFeeThreeFldVisible(), "Transaction fees is not visible");
		// Verify the "Transaction Amount" field on Step 3
		softAssert.assertTrue(isTransAmtThreeFldVisible(), "Transaction amount is not visible");
		// Verify the "Payment Date" field on Step 3
		softAssert.assertTrue(ispayDateThreeFldVisible(), "Payment date is not visible");
		// Verify the "Bank" field on Step 3
		softAssert.assertTrue(isBankThreeFldVisible(), "Bank is not visible");
		// Verify the "Step 3 Disclaimer 1" Label
		softAssert.assertEquals(getDiscOneLblThreeMessage(), preLoginPaymentProp.getPropValue("disclaimerOneMssg"),
				"All Mandatory Field Error is not matching.");

		// Verify the "Back" button on Step 3
		softAssert.assertTrue(isBackThreeStpBtnVisible(), "Back is not visible");
		// Verify the "Submit" button on Step 3
		softAssert.assertTrue(isSubmitBtnThreeVisible(), "Submit is not visible");
		// Click on Submit Button to go to Step 4
		clickSubmitThreeBtn();
		ExtentLogger.logInfo("Step-3 Complete");
		pause(5000);
		clickYesAlertBtn();
		// verify the "Payment Successful" Label
		softAssert.assertEquals(getPaySuccessLabel(), preLoginPaymentProp.getPropValue("paymentSuccessMessage"),
				"Pay Bill Success Message is not matching.");
	}

	public void exceedingPaymentValidationMessage(SoftAssert softAssert) {
		// verify the "Pay Bill" Label
		softAssert.assertEquals(getPayBillLabelTwo(), preLoginPaymentProp.getPropValue("preLoginPageTitle"),
				"Pay Bill label is not matching.");
		pause(2000);
		// Verify the "Payment Amount" field on Step 2, clear the existing amount and
		// add $10
		softAssert.assertTrue(isPayAmtTwoFieldVisible(), "    is not visible");
		pause(2000);
		clickPayAmtFld();
		clearPayAmtFld();
		enterPayAmtInTheField("10000");
		pause(1000);
		// Verify the "Payment Information" Label
		softAssert.assertEquals(getPayInfoStepTwo(), preLoginPaymentProp.getPropValue("payInfoStepTwoTitle"),
				"Payment Information label is not matching.");
		// Verify the "Bank Account" option on Step 2
		softAssert.assertTrue(isCardOptionVisible(), "Card is not visible");
		clickCardOption();
		pause(5000);
		// Card Details
		Card card = ModelsConfiguration.readCards().getCardByNameOnCard("John Wick Visa Card");
		softAssert.assertTrue(isCardHolderNameFieldVisible(), "Card holder name is not visible");
		enterCardHolderNameInTheField(card.getNameOnCard());
		softAssert.assertTrue(isCardNumberFieldVisible(), "Card number is not visible");
		enterCardNumberInTheField(card.getCardNumber());
		softAssert.assertTrue(isMonthFieldVisible(), "Month is not visible");
		clickMonthFld();
		pause(1000);
		clickMonthOption();
		pause(1000);
		softAssert.assertTrue(isYearFieldVisible(), "Year is not visible");
		clickYearFld();
		pause(1000);
		clickYearOption();
		pause(1000);
		softAssert.assertTrue(isSecurityCodeFieldVisible(), "Sec code is not visible");
		enterSecurityCodeInTheField(card.getCvv());
		// Billing Address
		softAssert.assertTrue(isFirstNameFieldVisible(), "First name is not visible");
		enterFirstNameInTheField(card.getFirstName());
		softAssert.assertTrue(isLastNameFieldVisible(), "Last name is not visible");
		enterLastNameInTheField(card.getLastName());
		softAssert.assertTrue(isAddressFieldVisible(), "Address is not visible");
		enterAddressInTheField(card.getAddress());
		softAssert.assertTrue(isCityFieldVisible(), "City is not visible");
		enterCityInTheField(card.getCity());
		softAssert.assertTrue(isStateFieldVisible(), "State is not visible");
		enterStateInTheField(card.getState());
		softAssert.assertTrue(isZipCodeFieldVisible(), "Zip code is not visible");
		enterZipCodeInTheField(card.getZipCode());

		clickNextBtn2();
		softAssert.assertEquals(getExceedingPaymentAmount(), preLoginPaymentProp.getPropValue("ExceedingPaymentMessage"),
				"Pay Bill label is not matching.");
		pause(3000);
	}

	public void pastYearExpiry(SoftAssert softAssert) {
		// verify the "Pay Bill" Label
		softAssert.assertEquals(getPayBillLabelTwo(), preLoginPaymentProp.getPropValue("preLoginPageTitle"),
				"Pay Bill label is not matching.");
		pause(2000);
		// Verify the "Payment Amount" field on Step 2, clear the existing amount and
		// add $10
		softAssert.assertTrue(isPayAmtTwoFieldVisible(), "Payment amount is not visible");
		pause(2000);
		clickPayAmtFld();
		clearPayAmtFld();
		enterPayAmtInTheField("10.12");
		pause(1000);
		// Verify the "Payment Information" Label
		softAssert.assertEquals(getPayInfoStepTwo(), preLoginPaymentProp.getPropValue("payInfoStepTwoTitle"),
				"Payment Information label is not matching.");
		// Verify the "Bank Account" option on Step 2
		softAssert.assertTrue(isCardOptionVisible(), "Card is not visible");
		clickCardOption();
		pause(5000);
		// Card Details
		Card card = ModelsConfiguration.readCards().getCardByNameOnCard("John Wick Visa Card");
		softAssert.assertTrue(isCardHolderNameFieldVisible(), "Card holder name is not visible");
		enterCardHolderNameInTheField(card.getNameOnCard());
		softAssert.assertTrue(isCardNumberFieldVisible(), "Card number is not visible");
		enterCardNumberInTheField(card.getCardNumber());
		softAssert.assertTrue(isMonthFieldVisible(), "Month is not visible");
		clickMonthFld();
		pause(1000);
		clickMonthOption();
		pause(1000);
		softAssert.assertTrue(isYearFieldVisible(), "Year is not visible");
		clickPastYearOption();
		pause(1000);
		clickYearOption();
		pause(1000);
		softAssert.assertTrue(isSecurityCodeFieldVisible(), "Sec code is not visible");
		enterSecurityCodeInTheField(card.getCvv());
		// Billing Address
		softAssert.assertTrue(isFirstNameFieldVisible(), "First name is not visible");
		enterFirstNameInTheField(card.getFirstName());
		softAssert.assertTrue(isLastNameFieldVisible(), "Last name is not visible");
		enterLastNameInTheField(card.getLastName());
		softAssert.assertTrue(isAddressFieldVisible(), "Address is not visible");
		enterAddressInTheField(card.getAddress());
		softAssert.assertTrue(isCityFieldVisible(), "City is not visible");
		enterCityInTheField(card.getState());
		softAssert.assertTrue(isStateFieldVisible(), "State is not visible");
		enterStateInTheField(card.getState());
		softAssert.assertTrue(isZipCodeFieldVisible(), "Zip code is not visible");
		enterZipCodeInTheField(card.getZipCode());
		clearCardHolderNameInTheField();
		clickNextBtn2();
	}

	public void pageClearWhileToggle(SoftAssert softAssert) {
		// verify the "Pay Bill" Label
		softAssert.assertEquals(getPayBillLabelTwo(), preLoginPaymentProp.getPropValue("preLoginPageTitle"),
				"Pay Bill label is not matching.");
		// Verify the "Name" field on Step 2
		softAssert.assertTrue(isNameFieldVisible(), "Name is not visible");
		// Verify the "Account" field on Step 2
		softAssert.assertTrue(isAccTwoFieldVisible(), "Account is not visible");
		// Verify the "Amount Due" field on Step 2
		softAssert.assertTrue(isAmtDueTwoFieldVisible(), "Amount due is not visible");
		// Verify the "Primary Phone" field on Step 2
		softAssert.assertTrue(isPrimPhTwoFieldVisible(), "Primary phone number is not visible");
		// Verify the "Email Address" field on Step 2
		softAssert.assertTrue(isEmailAddTwoFieldVisible(), "Email is not visible");
		pause(2000);
		// Verify the "Payment Amount" field on Step 2, clear the existing amount and
		// add $10
		softAssert.assertTrue(isPayAmtTwoFieldVisible(), "Payment amount is not visible");
		pause(2000);
		clickPayAmtFld();
		clearPayAmtFld();
		enterPayAmtInTheField("10");
		pause(1000);
		// Verify the "Payment Information" Label
		softAssert.assertEquals(getPayInfoStepTwo(), preLoginPaymentProp.getPropValue("payInfoStepTwoTitle"),
				"Payment Information label is not matching.");
		// Verify the "Bank Account" option on Step 2
		softAssert.assertTrue(isCardOptionVisible(), "Card is not visible");
		clickCardOption();
		pause(5000);
		// Card Details
		Card card = ModelsConfiguration.readCards().getCardByNameOnCard("John Wick Visa Card");
		softAssert.assertTrue(isCardHolderNameFieldVisible(), "Card holder name is not visible");
		enterCardHolderNameInTheField(card.getNameOnCard());
		softAssert.assertTrue(isCardNumberFieldVisible(), "Card number is not visible");
		enterCardNumberInTheField(card.getCardNumber());
		softAssert.assertTrue(isMonthFieldVisible(), "Month is not visible");
		clickMonthFld();
		pause(1000);
		clickMonthOption();
		pause(1000);
		softAssert.assertTrue(isYearFieldVisible(), "Year is not visible");
		clickYearFld();
		pause(1000);
		clickYearOption();
		pause(1000);
		softAssert.assertTrue(isSecurityCodeFieldVisible(), "Sec code is not visible");
		enterSecurityCodeInTheField(card.getCvv());
		// Billing Address
		softAssert.assertTrue(isFirstNameFieldVisible(), "First name is not visible");
		enterFirstNameInTheField(card.getFirstName());
		softAssert.assertTrue(isLastNameFieldVisible(), "Last name is not visible");
		enterLastNameInTheField(card.getLastName());
		softAssert.assertTrue(isAddressFieldVisible(), "Address is not visible");
		enterAddressInTheField(card.getAddress());
		softAssert.assertTrue(isCityFieldVisible(), "City is not visible");
		enterCityInTheField(card.getCity());
		softAssert.assertTrue(isStateFieldVisible(), "State is not visible");
		enterStateInTheField(card.getState());
		softAssert.assertTrue(isZipCodeFieldVisible(), "Zip code is not visible");
		enterZipCodeInTheField(card.getZipCode());

		//Switch to Bank
		clickBankOption();
		pause(3000);
		softAssert.assertEquals(getAccountHolderName(), preLoginPaymentProp.getPropValue("BankHolderName"),
				"Payment Information label is not matching.");
		pause(2000);
		ExtentLogger.logInfo("Switch to Bank Successful");
		//Switch to Card
		clickCardOption();
		pause(3000);
		softAssert.assertEquals(getCardHolderName(), preLoginPaymentProp.getPropValue("CardHolderName"),
				"Payment Information label is not matching.");
		pause(3000);
		ExtentLogger.logInfo("Switch to Card Successful");
	}
}
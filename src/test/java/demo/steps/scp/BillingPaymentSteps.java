package demo.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import sew.ai.config.ModelsConfiguration;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.models.Card;
import sew.ai.steps.scp.HomeSteps;
import sew.ai.utils.DateUtil;
import sew.ai.utils.PropertiesUtil;
import sew.ai.utils.RandomUtil;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class BillingPaymentSteps extends PaymentInformationSteps {

	private static final Logger log = LogManager.getLogger(BillingPaymentSteps.class);
	public static PropertiesUtil paymentInformationTextProp;
	HomeSteps homeSteps = new HomeSteps(driver);

	public BillingPaymentSteps(WebDriver driver) {
		super(driver);
		paymentInformationTextProp = new PropertiesUtil(
				FilePaths.PAY_TEXT_PROPERTIES + Constants.PAYMENT_INFORMATION_TEXT_FILENAME);
	}

	public void verifyMakePaymentWithNewCC(SoftAssert softAssert) throws InterruptedException {

		Card card = ModelsConfiguration.readCards().getCardByNameOnCard("John Wick Visa Card");
		clickLnkAddPaymentMethod();
		pause(20000); // Clicking on Bank Radio Button
		clickrdoBtnCard(); // Validation of Empty Form Submit clickbtnAdd();

		softAssert.assertEquals(getToastMessage(), paymentInformationTextProp.getPropValue("txtErrMsgBlank"),
				"Blank Error Msg do not match");

		populateCardDetails(card);
		clickbtnAdd();
		softAssert.assertEquals(getToastMessage(),
				paymentInformationTextProp.getPropValue("txtErrMsgDuplicatePaymentProfile"),
				"payment method already added");

		homeSteps.navigateToCurrentBill();
		clickBtnMakpayment();
		EnterOtherAmmount("1.00");
		clickrdoBtnCardCBill();
		Select Card = new Select(elementDDCardAccountCurrentBill());
		Card.selectByVisibleText("VISA - 1111 - Exp. (12/24)");
		clickNextPayementBtn();
		softAssert.assertEquals(getPaymentStep2Text(), "STEP 2: REVIEW & CONFIRM");
		String billamount = getBillAmmount();
		String transfee = getTranFee();
		String tottlefee = getTotleFee();
		String paymentdate = getPayementDate();
		softAssert.assertEquals("1.00", billamount);
		softAssert.assertEquals(Double.parseDouble(tottlefee),
				(Double.parseDouble(transfee) + Double.parseDouble(billamount)));
		softAssert.assertEquals(DateUtil.changeStringToDateInFormat(paymentdate, "yyyy-MM-dd"),
				DateUtil.getCurrentDateInFormat("yyyy-MM-dd"));
		clickPaymentSubmitBtn();
		ExtentLogger.logInfo("Payement Done from Credit Card");
	}

	public void verifyCreditCardPaymentFormFields(SoftAssert softAssert) throws InterruptedException {
		ExtentLogger.logInfo("To Verify the Credit Card PAyment form Fields");
		Assert.assertTrue(
				isPaymentInformationPage(paymentInformationTextProp.getPropValue("expectedPaymentInformationPageUrl"),
						paymentInformationTextProp.getPropValue("expectedPaymentInformationPageHeader")),
				"Page URL and Header is not Matching");
		// Verifying Payment Information Page Header
		softAssert.assertEquals(getLblPaymentInfoPageHeadingText(),
				paymentInformationTextProp.getPropValue("expectedPaymentInformationPageHeader"),
				"Expected Page Title do not match");
		// Clicking on Add Payment Link
		clickLnkAddPaymentMethod();
		waitUntilCompletePageLoad();
		// Clicking on Bank Radio Button
		clickrdoBtnCard();
		// Name on Card
		softAssert.assertTrue(isTxtBoxCardHolderNameVisible(), "Name on card Feild is Visible");
		softAssert.assertEquals(getLblTxtBoxCardHolderName(), paymentInformationTextProp.getPropValue("lblNameOnCard"),
				"Card Holder Name Lable matched");
		// Checking The fields are not pre populated
		softAssert.assertEquals(getText(elementCardHolderName()), "", "Card Holder name feild is pre-populated");
		// Checking Copy past is not allowed
		copyPasteUsingActionClassInTextFeild("Hanna", elementCardHolderName());
		softAssert.assertEquals(getText(elementCardHolderName()), "", "Card Holder Name Copy Paste is allowed");
		// Card Number
		softAssert.assertTrue(isTxtBoxCardNumberVisible(), "Card Number Feild is Not Visible");
		softAssert.assertEquals(getLblTxtBoxCardNumber(), paymentInformationTextProp.getPropValue("lblCardNumber"),
				"Card Number Name Lable do not match");
		// Month Drop Down
		softAssert.assertTrue(isDDExpMonth(), "Month Feild is Not Visible");
		softAssert.assertEquals(getLblExpMonth(), paymentInformationTextProp.getPropValue("lblExpMonth"),
				"Exp Month Dropdown is not present");
		// Year Drop Down
		softAssert.assertTrue(isDDExpYear(), "Year Feild is Not Visible");
		softAssert.assertEquals(getLblExpYear(), paymentInformationTextProp.getPropValue("lblExpYear"),
				"Year Dropdown is not present");
		// Security Code Drop Down
		softAssert.assertTrue(isTxtBoxSecurityPin(), "Security code Feild is Not Visible");
		softAssert.assertEquals(getLblTxtBoxSecurityPin(), paymentInformationTextProp.getPropValue("lblSecurityCode"),
				"Security code lable is not present");
		// Checking The fields are not pre populated
		softAssert.assertEquals(getText(elementCardNumber()), "", "Card Number is pre-populated");
		// Checking Copy past is not allowed
		copyPasteUsingActionClassInTextFeild("123456555789", elementCardNumber());
		softAssert.assertEquals(getText(elementCardNumber()), "", "Card Number Copy Paste is allowed");

		// Billing Address
		softAssert.assertTrue(isTxtLblBillingAddressVisible(), "Billing Address Header Not Displayed");
		softAssert.assertEquals(getTxtLblBillingAddress(), paymentInformationTextProp.getPropValue("lblBillingAddress"),
				"Billing address Header is not present");
		// First Name
		softAssert.assertTrue(isTxtBoxFirstNameVisible(), "First Name Feild not Visible");
		softAssert.assertEquals(getLblTxtBoxFirstName(), paymentInformationTextProp.getPropValue("lblFirstName"),
				"First Name Lable do not match");
		// Last Name
		softAssert.assertTrue(isTxtBoxLastNameVisible(), "Last Name Feild is Not Visible");
		softAssert.assertEquals(getLblTxtBoxLastName(), paymentInformationTextProp.getPropValue("lblLastName"),
				"Last Name Lable do not match");
		// Address
		softAssert.assertTrue(isTxtBoxAddressVisible(), "Address Feild is Not Visible");
		softAssert.assertEquals(getLblTxtBoxAddress(), paymentInformationTextProp.getPropValue("lblAddress"),
				"Address Lable do not match");
		// City
		softAssert.assertTrue(isTxtBoxCityVisible(), "City Feild is Not Visible");
		softAssert.assertEquals(getLblTxtBoxCity(), paymentInformationTextProp.getPropValue("lblCity"),
				"City Lable do not match");
		// State
		softAssert.assertTrue(isTxtBoxStateVisible(), "State Feild is Not Visible");
		softAssert.assertEquals(getLblTxtBoxState(), paymentInformationTextProp.getPropValue("lblState"),
				"State Name Lable do not match");
		// Zip Code
		softAssert.assertTrue(isTxtBoxZipVisible(), "Zip Code Feild is Not Visible");
		softAssert.assertEquals(getLblTxtBoxZip(), paymentInformationTextProp.getPropValue("lblZIPCode"),
				"Zip Code Lable do not match");
		// Add Button
		softAssert.assertTrue(isBtnAddVisible(), "ADD Button is Not Visible");
		softAssert.assertEquals(getTxtBtnAdd(), paymentInformationTextProp.getPropValue("lblAddButton"),
				"ADD Button do not match");
		// Cancel Button
		softAssert.assertTrue(isBtnCancelVisible(), "Cancel Button Feild is Not Visible");
		softAssert.assertEquals(getTxtBtnCancel(), paymentInformationTextProp.getPropValue("lblCancelButton"),
				"Cancel Button do not match");
		ExtentLogger.logInfo("Verified that all the Feilds are present in Credit Card Form");

	}

	public void verifyCeditCardPaymentFormFieldsValidation(SoftAssert softAssert) throws InterruptedException {
		ExtentLogger.logInfo("To Verify the Credit card Payment form Fields Validation");
		Card card = ModelsConfiguration.readCards().getCardByNameOnCard("John Wick Visa Card");
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToPaymentInfo();
		Assert.assertTrue(
				isPaymentInformationPage(paymentInformationTextProp.getPropValue("expectedPaymentInformationPageUrl"),
						paymentInformationTextProp.getPropValue("expectedPaymentInformationPageHeader")),
				"Page URL and Header is not Matching");
		// Verifying Payment Information Page Header
		softAssert.assertEquals(getLblPaymentInfoPageHeadingText(),
				paymentInformationTextProp.getPropValue("expectedPaymentInformationPageHeader"),
				"Expected Page Title do not match");
		// Clicking on Add Payment Link
		clickLnkAddPaymentMethod();
		pause(20000);
		// Clicking on Bank Radio Button
		clickrdoBtnCard();
		ExtentLogger.logInfo("Validation of Empty Form Submit");
		clickbtnAdd();
		softAssert.assertEquals(getToastMessage(), paymentInformationTextProp.getPropValue("txtErrMsgBlank"),
				"Empty Form Error Msg do not match");
		ExtentLogger.logInfo("Fetching Card Details");
		populateCardDetails(card);
		ExtentLogger.logInfo("Verifying The validation on Card Holder Name");
		String CardName = getAttribute(elementCardHolderName(), "value");
		softAssert.assertEquals(CardName, card.getNameOnCard(), "Card Holder Name Error Msg do not match");
		clear(elementCardHolderName());
		clickbtnAdd();
		softAssert.assertEquals(getErrMsgAccountHolderName(),

				paymentInformationTextProp.getPropValue("txtErrMsgBlankCardHolderName"),
				"Credit Holder Name Error Msg do not match");
		populateTxtBoxCardHolderName(card.getNameOnCard() + "+_-&*^" + RandomUtil.generateInteger());
		softAssert.assertEquals(getAttribute(elementCardHolderName(), "maxlength"),
				paymentInformationTextProp.getPropValue("maxLengthCardHolderName"),
				"Max length of Card Holder Name do not match");
		clear(elementCardHolderName());
		populateTxtBoxLastName(card.getNameOnCard());

		ExtentLogger.logInfo("Verifying The validation on Card Number");
		// Verification of card MaxLength
		softAssert.assertEquals(getAttribute(elementCardNumber(), "maxlength"),
				paymentInformationTextProp.getPropValue("maxLengthCardNumber"),
				"Max length of card number do not match");
		// Validating Blank Card Number
		clear(elementCardNumber());
		clickbtnAdd();
		softAssert.assertEquals(getErrMsgCardNumber(), paymentInformationTextProp.getPropValue("txtErrMsgBlankCardNum"),
				"Blank Card Number Error Msg do not match");
		populateTxtBoxLastName(card.getCardNumber());
		ExtentLogger.logInfo("Verifying The validation on Security code");
		// Validating Blank Security code
		clear(elementSecurityPin());
		clickbtnAdd();
		softAssert.assertEquals(getErrMsgSecurityPin(), paymentInformationTextProp.getPropValue("txtErrSecuritycode"),
				"Blank Security Pin Error Msg do not match");
		populateTxtBoxSecurityPin(card.getCvv());

		ExtentLogger.logInfo("Verifying The validation on Last Name field");
		// Validating Last Name field
		clear(elementLastName());
		clickbtnAdd();
		softAssert.assertEquals(getErrMsgLastName(), paymentInformationTextProp.getPropValue("txtErrMsgLastName"),
				"Blank Last name Error Msg do not match");
		populateTxtBoxLastName(card.getLastName());

		ExtentLogger.logInfo("Verifying The validation on Address field");
		// Validating Address field
		clear(elementAddress());
		clickbtnAdd();
		softAssert.assertEquals(getErrMsgAddress(), paymentInformationTextProp.getPropValue("txtErrMsgAddress"),
				"Blank Address Error Msg do not match");
		populateTxtBoxAddress(card.getAddress());

		ExtentLogger.logInfo("Verifying The validation on city field");
		// Validating City field
		clear(elementCity());
		clickbtnAdd();
		softAssert.assertEquals(getErrMsgCity(), paymentInformationTextProp.getPropValue("txtErrMsgCity"),
				"Blank City Error Msg do not match");
		populateTxtCity(card.getCity());

		// Validating State field
		ExtentLogger.logInfo("Verifying The validation on state field");
		clear(elementState());
		clickbtnAdd();
		softAssert.assertEquals(getErrMsgState(), paymentInformationTextProp.getPropValue("txtErrMsgState"),
				"Blank State Error Msg do not match");
		populateTxtState(card.getState());
		ExtentLogger.logInfo("Verifying The validation on Zip code field");
		// Validating ZIP Code field
		clear(elementZip());
		clickbtnAdd();
		softAssert.assertEquals(getErrMsgZip(), paymentInformationTextProp.getPropValue("txtErrMsgZip"),
				"Blank Zip code Error Msg do not match");
		populateTxtZip(card.getZipCode());
	}

	public void populateCardDetails(Card card) throws InterruptedException {

		populateTxtBoxCardHolderName(card.getNameOnCard());
		populateTxtBoxCardNumber(card.getCardNumber());
		Select month = new Select(elementDDExpMonth());
		month.selectByVisibleText("Dec");
		Select year = new Select(elementDDExpYear());
		year.selectByVisibleText("2024");
		populateTxtBoxSecurityPin(card.getCvv());
		populateTxtBoxFirstName(card.getFirstName());
		populateTxtBoxLastName(card.getLastName());
		populateTxtBoxAddress(card.getAddress());
		populateTxtCity(card.getCity());
		populateTxtState(card.getState());
		populateTxtZip(card.getZipCode());
	}
}

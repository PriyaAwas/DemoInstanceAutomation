package demo.steps.scp;

import java.awt.print.PrinterException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import demo.pageobjects.PaymentInformationsPage;
import sew.ai.config.ModelsConfiguration;
import sew.ai.config.SCPConfiguration;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.models.Bank;
import sew.ai.models.Card;
import sew.ai.models.User;
import sew.ai.steps.scp.DashboardSteps;
import sew.ai.steps.scp.HomeSteps;
import sew.ai.steps.scp.LoginSteps;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.DateUtil;
import sew.ai.utils.PropertiesUtil;
import sew.ai.utils.RandomUtil;

public class PaymentInformationSteps extends PaymentInformationsPage {

    private static final Logger log = LogManager.getLogger(PaymentInformationSteps.class);
    public static PropertiesUtil paymentInformationTextProp;

    public PaymentInformationSteps(WebDriver driver) {
        super(driver);
        paymentInformationTextProp = new PropertiesUtil(
                FilePaths.PAY_TEXT_PROPERTIES + Constants.PAYMENT_INFORMATION_TEXT_FILENAME);
    }

    public void verifyThePaymentInformationPageObjects(SoftAssert softAssert) {
        log.info("To Verify the Payment Information Page Fields");
        // Verifying Payment Information Page
        Assert.assertTrue(
                isPaymentInformationPage(paymentInformationTextProp.getPropValue("expectedPaymentInformationPageUrl"),
                        paymentInformationTextProp.getPropValue("expectedPaymentInformationPageHeader")),
                "Page URL and Header is not Matching");
        // Verifying Payment Information Page Header
        softAssert.assertEquals(getLblPaymentInfoPageHeadingText(),
                paymentInformationTextProp.getPropValue("expectedPaymentInformationPageHeader"),
                "Expected Page Title do not match");
        // Verifying Add Payment Method is Visible
        softAssert.assertEquals(getLnkAddPaymentMethodText(),
                paymentInformationTextProp.getPropValue("lblAddPaymentMethod"),
                "Expected Add Payment Label do not match");

        // Checking Different Payment Methods in Add Payment
        clickLnkAddPaymentMethod();
        pause(20000);
        // Checking For Visibility of Radio Button Bank and Fields.
        softAssert.assertTrue(isRdobtnBankVisible(), "Radio Button Bank Is Not Visible");
        softAssert.assertTrue(isrdoBtnCardVisible(), "Radio Button Card Is Not Visible");
        softAssert.assertTrue(isrdoBtnPaypalVisible(), "Radio Button Pay-Pal Is Not Visible");
        softAssert.assertTrue(isrdoBtnVenmoVisible(), "Radio Button Venmo Is Not Visible");
        softAssert.assertTrue(isrdoBtnGpayVisible(), "Radio Button G-Pay Is Not Visible");

        // Checking Cancel Button Functionality
        clickbtnCancel();

        // Checking For FAQ Section.
        softAssert.assertTrue(isFaqFieldVisible(), "FAQ field Is Not Visible");
    }


    public Boolean isPaymentInformationPage(String url, String title) {
        Boolean isaccountInformationPage = false;
        log.info("Checking that the current page is account information page.");
        if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
            isaccountInformationPage = true;
        log.info("The current page is account information page {}: " + isaccountInformationPage);
        return isaccountInformationPage;
    }

    public void verifyTheBankPaymentFormFields(SoftAssert softAssert) throws InterruptedException {
        log.info("To Verify the Bank PAyment form Fields");
        // Verifying Payment Information Page
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
        //clickRdoBtnBank();
        //Clicking on Bank Option
        clickBnkOpt();
        // Account Holder Name
        softAssert.assertTrue(isTxtBoxAccountHolderNameVisible(), "Account Holder Name Field is Not Visible");
        softAssert.assertEquals(getLblTxtBoxAccountHolderName(),
                paymentInformationTextProp.getPropValue("lblAccountHolderName"),
                "Account Holder Name Label do not match");
        // Checking The fields are not pre populated
        softAssert.assertEquals(getText(elementAccountHolderName()), "", "Account Holder name feild is pre-populated");
        // Checking Copy past is not allowed
        copyPasteUsingActionClassInTextFeild("Hanna", elementAccountHolderName());
        softAssert.assertEquals(getText(elementAccountHolderName()), "", "Account Holder Name Copy Paste is allowed");

        // Routing Number
        softAssert.assertTrue(isTxtBoxRoutingNumberVisible(), "Routing Number Feild is Not Visible");
        softAssert.assertEquals(getLblTxtBoxRoutingNumber(),
                paymentInformationTextProp.getPropValue("lblRoutingNumber"), "Routing Number Name Lable do not match");
        // Checking The fields are not pre populated
        softAssert.assertEquals(getText(elementRoutingNumber()), "", "Routing Number is pre-populated");
        // Checking Copy past is not allowed
        copyPasteUsingActionClassInTextFeild("123456789", elementRoutingNumber());
        softAssert.assertEquals(getText(elementRoutingNumber()), "", "Routing Number Copy Paste is allowed");
        // Checking If routing number field is masked
        softAssert.assertEquals(getAttribute(elementRoutingNumber(), "type"), "password",
                "Routing Number Feild is not of type password");

        // Confirm Routing Number
        softAssert.assertTrue(isTxtBoxConfirmRoutingNumberVisible(), "Confirm Routing Number Feild is Not Visible");
        softAssert.assertEquals(getLblTxtBoxConfirmRoutingNumber(),
                paymentInformationTextProp.getPropValue("lblConfirmRoutingNumber"),
                "Confirm Routing Number Name Lable do not match");
        // Checking The fields are not pre populated
        softAssert.assertEquals(getText(elementConfirmRoutingNumber()), "", "Confirm Routing Number is pre-populated");
        // Checking Copy past is not allowed
        copyPasteUsingActionClassInTextFeild("123456789", elementConfirmRoutingNumber());
        softAssert.assertEquals(getText(elementConfirmRoutingNumber()), "",
                "Confirm Routing Number Copy Paste is allowed");

        // Bank Name
        softAssert.assertTrue(isTxtBoxBankNameVisible(), "Bank Name Feild is Not Visible");
        softAssert.assertEquals(getLblTxtBankName(), paymentInformationTextProp.getPropValue("lblBankName"),
                "Bank Name Lable do not match");
        // Checking The fields are not pre populated
        softAssert.assertEquals(getText(elementBankName()), "", "Bank Name is pre-populated");
        // Checking Copy past is not allowed
        copyPasteUsingActionClassInTextFeild("ABC Bank", elementBankName());
        softAssert.assertEquals(getText(elementBankName()), "", "Bank Name Copy Paste is allowed");

        // Bank Account Number
        softAssert.assertTrue(isTxtBoxBankAccountNoVisible(), "Bank Account Number Feild is Not Visible");
        softAssert.assertEquals(getLblTxtBoxBankAccountNo(),
                paymentInformationTextProp.getPropValue("lblBankAccountNumber"),
                "Bank Account Number Name Lable do not match");
        // Checking The fields are not pre populated
        softAssert.assertEquals(getText(elementAccountNumber()), "", "Account Number is pre-populated");
        // Checking Copy past is not allowed
        copyPasteUsingActionClassInTextFeild("23452345", elementAccountNumber());
        softAssert.assertEquals(getText(elementAccountNumber()), "", "Account Number Copy Paste is allowed");
        // Checking If Bank number field is masked
        softAssert.assertEquals(getAttribute(elementAccountNumber(), "type"), "password",
                "Bank Account Number Feild is not of type password");

        // Confirm Bank Account Number
        softAssert.assertTrue(isTxtBoxConfirmBankAccountNoVisible(), "Confirm Account Number Feild is Not Visible");
        softAssert.assertEquals(getLblTxtBoxConfirmBankAccountNo(),
                paymentInformationTextProp.getPropValue("lblConfirmBankAccountNumber"),
                "Confirm Account Number Feild Name Lable do not match");
        // Checking The fields are not pre populated
        softAssert.assertEquals(getText(elementConfirmAccountNumber()), "",
                "Confirm Account Number Feild is pre-populated");
        // Checking Copy past is not allowed
        copyPasteUsingActionClassInTextFeild("23452345", elementConfirmAccountNumber());
        softAssert.assertEquals(getText(elementConfirmAccountNumber()), "",
                "Confirm Account Number Feild Copy Paste is allowed");

        // Account Type Drop Down
        softAssert.assertTrue(isTxtBoxBankNameVisible(), "Routing Number Feild is Not Visible");
        Select ddAccountType = new Select(elementDDBankAccountType());
        List<WebElement> ddAccountTypeOptions = ddAccountType.getOptions();
        softAssert.assertEquals(getText(ddAccountTypeOptions.get(1)),
                paymentInformationTextProp.getPropValue("lblPersonalChecking"),
                "Routing Number Name Lable do not match");
        softAssert.assertEquals(getText(ddAccountTypeOptions.get(2)),
                paymentInformationTextProp.getPropValue("lblPersonalSavings"),
                "Routing Number Name Lable do not match");
        softAssert.assertEquals(getText(ddAccountTypeOptions.get(3)),
                paymentInformationTextProp.getPropValue("lblBusinessChecking"),
                "Routing Number Name Lable do not match");
        softAssert.assertEquals(getText(ddAccountTypeOptions.get(4)),
                paymentInformationTextProp.getPropValue("lblBusinessSavings"),
                "Routing Number Name Lable do not match");

        // Billing Address
        softAssert.assertTrue(isTxtLblBillingAddressVisible(), "Routing Number Feild is Not Visible");
        softAssert.assertEquals(getTxtLblBillingAddress(), paymentInformationTextProp.getPropValue("lblBillingAddress"),
                "Routing Number Name Lable do not match");
        // First Name
        softAssert.assertTrue(isTxtBoxFirstNameVisible(), "Routing Number Feild is Not Visible");
        softAssert.assertEquals(getLblTxtBoxFirstName(), paymentInformationTextProp.getPropValue("lblFirstName"),
                "Routing Number Name Lable do not match");
        // Last Name
        softAssert.assertTrue(isTxtBoxLastNameVisible(), "Routing Number Feild is Not Visible");
        softAssert.assertEquals(getLblTxtBoxLastName(), paymentInformationTextProp.getPropValue("lblLastName"),
                "Routing Number Name Lable do not match");
        // Address
        softAssert.assertTrue(isTxtBoxAddressVisible(), "Routing Number Feild is Not Visible");
        softAssert.assertEquals(getLblTxtBoxAddress(), paymentInformationTextProp.getPropValue("lblAddress"),
                "Routing Number Name Lable do not match");
        // City
        softAssert.assertTrue(isTxtBoxCityVisible(), "Routing Number Feild is Not Visible");
        softAssert.assertEquals(getLblTxtBoxCity(), paymentInformationTextProp.getPropValue("lblCity"),
                "Routing Number Name Lable do not match");
        // State
        softAssert.assertTrue(isTxtBoxStateVisible(), "Routing Number Feild is Not Visible");
        softAssert.assertEquals(getLblTxtBoxState(), paymentInformationTextProp.getPropValue("lblState"),
                "Routing Number Name Lable do not match");
        // Zip Code
        softAssert.assertTrue(isTxtBoxZipVisible(), "Routing Number Feild is Not Visible");
        softAssert.assertEquals(getLblTxtBoxZip(), paymentInformationTextProp.getPropValue("lblZIPCode"),
                "Routing Number Name Lable do not match");
        // Add Button
        softAssert.assertTrue(isBtnAddVisible(), "Routing Number Feild is Not Visible");
        softAssert.assertEquals(getTxtBtnAdd(), paymentInformationTextProp.getPropValue("lblAddButton"),
                "Routing Number Name Lable do not match");
        // Cancel Button
        softAssert.assertTrue(isBtnCancelVisible(), "Routing Number Feild is Not Visible");
        softAssert.assertEquals(getTxtBtnCancel(), paymentInformationTextProp.getPropValue("lblCancelButton"),
                "Routing Number Name Lable do not match");
    }

    public static String copyPasteUsingActionClassInTextFeild(String Text, WebElement element) throws InterruptedException {
        Actions actions = new Actions(driver);
        scrollPageToElement(element);
        //clear(element);
        click(element);
        // Copy the value from the string variable
        actions.sendKeys(Text).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
        actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();

        // Paste the copied value into the target element
        actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();

        String pastedValue = element.getAttribute("value");

        return pastedValue;

    }

    public void verifyTheBankPaymentFormFieldValidation(SoftAssert softAssert) throws InterruptedException {
        log.info("To Verify the Bank Payment form Fields Validation");
        Bank bank = ModelsConfiguration.readBankAccounts().getBankByAccountHolderName("Henry Jacob Bank Account");
        HomeSteps homeSteps = new HomeSteps(driver);
        // UnEnrolling of AutoPay or Text Pay
        //homeSteps.navigateToAutoPayment(); unEnrollFromAutoTextPay();
        //homeSteps.navigateToTextToPay(); unEnrollFromAutoTextPay();
        //homeSteps.navigateToAutoPayment();
        //unEnrollFromAutoTextPay();
        //homeSteps.navigateToAutoPayment(); unEnrollFromAutoTextPay();
        //homeSteps.navigateToTextToPay(); unEnrollFromAutoTextPay();
        homeSteps.navigateToPaymentInfo();
        // Deleting Payment Profiles
        //deletePaymentProfiles();
        // Verifying Payment Information Page
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

        pause(20000);        // Clicking on Bank Radio Button
        //clickRdoBtnBank();
        //clickBnkOpt();
        //waitUntilCompletePageLoad();
        clickBnkOpt();
        //Validation of Empty Form Submit
        clickbtnAdd();
        softAssert.assertEquals(getToastMessage(), paymentInformationTextProp.getPropValue("txtErrMsgBlank"),"Blank Error Msg do not match");
        //Populating Bank payment form
        populateBankPaymentForm(bank);
        // Verifying The Bank Name is pre-populated
        String bankName = getAttribute(elementBankName(), "value");
        softAssert.assertEquals(bankName, bank.getBankName(), "Blank Account Holder Name Error Msg do not match");
        // Validating Account Holder Name Field
        clear(elementAccountHolderName());
        clickbtnAdd();
        softAssert.assertEquals(getErrMsgAccountHolderName(),
                paymentInformationTextProp.getPropValue("txtErrMsgBlankAccountHolderName"),
                "Blank Account Holder Name Error Msg do not match");
        populateTxtBoxAccountHolderName(bank.getAccountHolderName() + "+_-&*^" + RandomUtil.generateInteger());
        softAssert.assertEquals(getAttribute(elementAccountHolderName(), "maxlength"),
                paymentInformationTextProp.getPropValue("maxLengthAccountHolderName"),
                "Max length of Account Holder Name do not match");
        // Validating Routing Number Field and Confirm Routing Number Field
        // Verification of Routing MaxLength
        softAssert.assertEquals(getAttribute(elementRoutingNumber(), "maxlength"),
                paymentInformationTextProp.getPropValue("maxLengthRoutingNumber"),
                "Max length of routing number do not match");
        softAssert.assertEquals(getAttribute(elementConfirmRoutingNumber(), "maxlength"),
                paymentInformationTextProp.getPropValue("maxLengthRoutingNumber"),
                "Max length of Confirm routing number do not match");
        // Validating Blank Routing Number
        clear(elementRoutingNumber());
        clickbtnAdd();
        softAssert.assertEquals(getErrMsgRoutingNumber(),
                paymentInformationTextProp.getPropValue("txtErrMsgRoutingNumber"),
                "Blank Routing Number Error Msg do not match");
        populateTxtBoxRoutingNumber(bank.getRoutingNumber());
        // Validating Blank Confirm Routing Number
//		clear(elementConfirmRoutingNumber());
//		clickbtnAdd();
//		softAssert.assertEquals(getErrMsgConfirmRoutingNumber(),
//				paymentInformationTextProp.getPropValue("txtErrMsgRoutingNumber"),
//				"Blank Confirm Routing Number Error Msg do not match");
        // Validating Routing Number Miss-Match
        populateTxtBoxRoutingNumber(bank.getRoutingNumber());
        populateTxtBoxConfirmRoutingNumber("011000391");
        elementConfirmRoutingNumber().sendKeys(Keys.TAB);
        softAssert.assertEquals(getToastMessage(),
                paymentInformationTextProp.getPropValue("txtErrMsgRoutingNumberMissMatch"),
                "Routing Number Miss Match Error Msg do not match");
        // Validating Invalid Routing Number
        populateTxtBoxRoutingNumber("1234");
        populateTxtBoxConfirmRoutingNumber("1234");
        elementConfirmRoutingNumber().sendKeys(Keys.TAB);
        softAssert.assertEquals(getErrMsgRoutingNumber(),
                paymentInformationTextProp.getPropValue("txtErrMsgInvalidRoutingNumber"),
                "Invalid Routing Number Error Msg do not match");
        // Populating with Valid Details
        populateTxtBoxRoutingNumber(bank.getRoutingNumber());
        populateTxtBoxConfirmRoutingNumber(bank.getRoutingNumber());

        // Validating Account Number Field and Confirm Account Number Field
        // Validating Blank Account Number
//		clear(elementAccountNumber());
//		clickbtnAdd();
//		softAssert.assertEquals(getErrMsgAccountNumber(),
//				paymentInformationTextProp.getPropValue("txtErrMsgAccountNumber"),
//				"Blank Account Number Error Msg do not match");
        populateTxtBoxBankAccountNo(bank.getBankAccountNumber());
        // Validating Blank Confirm Account Number
//		clear(elementConfirmAccountNumber());
//		clickbtnAdd();
//		softAssert.assertEquals(getErrMsgConfirmAccountNumber(),
//				paymentInformationTextProp.getPropValue("txtErrMsgAccountNumber"),
//				"Blank Confirm Account Number Error Msg do not match");
        // Validating Account Number Miss-Match
        populateTxtBoxBankAccountNo(bank.getBankAccountNumber());
        populateTxtBoxConfirmBankAccountNo(bank.getBankAccountNumber() + "1");
//		clickbtnAdd();
//		softAssert.assertEquals(getToastMessage(),
//				paymentInformationTextProp.getPropValue("txtErrMsgAccountNumberMissMatch"),
//				"Account Number Miss Match Error Msg do not match");
        // Validating Invalid Account Number
        populateTxtBoxBankAccountNo("1");
        populateTxtBoxConfirmBankAccountNo(bank.getBankAccountNumber());
//		clickbtnAdd();
//		softAssert.assertEquals(getErrMsgAccountNumber(),
//				paymentInformationTextProp.getPropValue("txtErrMsgAccountNumber"),
//				"Invalid Routing Number Error Msg do not match");
        populateTxtBoxBankAccountNo(bank.getBankAccountNumber());
        populateTxtBoxConfirmBankAccountNo("1");
//		clickbtnAdd();
//		softAssert.assertEquals(getErrMsgConfirmAccountNumber(),
//				paymentInformationTextProp.getPropValue("txtErrMsgAccountNumber"),
//				"Invalid Routing Number Error Msg do not match");
        // Populating with Valid Details
        populateTxtBoxBankAccountNo(bank.getBankAccountNumber());
        populateTxtBoxConfirmBankAccountNo(bank.getBankAccountNumber());
        // Validating Last Name field
//		clear(elementLastName());
//		clickbtnAdd();
//		softAssert.assertEquals(getErrMsgLastName(), paymentInformationTextProp.getPropValue("txtErrMsgLastName"),
//				"Blank Last name Error Msg do not match");
        populateTxtBoxLastName(bank.getLastName());
        // Validating Address field
//		clear(elementAddress());
//		clickbtnAdd();
//		softAssert.assertEquals(getErrMsgAddress(), paymentInformationTextProp.getPropValue("txtErrMsgAddress"),
//				"Blank Address Error Msg do not match");
        populateTxtBoxAddress(bank.getAddress());
    }

    public void unEnrollFromAutoTextPay() {
        if (isLblAutoPayEnrollmentStatusVisible()) {
            clickBtnThreeDot();
            clickBtnUnEnroll();
            clickBtnUpdate();
            try {
                closeToastMandatoryMsg();
            } catch (Exception e) {
                e.printStackTrace();
            }
            pause(3000);
        }
    }

    public void populateBankPaymentForm(Bank bank) throws InterruptedException {

        populateTxtBoxAccountHolderName(bank.getAccountHolderName());
        populateTxtBoxRoutingNumber(bank.getRoutingNumber());
        populateTxtBoxConfirmRoutingNumber(bank.getRoutingNumber());
        populateTxtBoxBankAccountNo(bank.getBankAccountNumber());
        populateTxtBoxConfirmBankAccountNo(bank.getBankAccountNumber());
        Select ddAccountType = new Select(elementDDBankAccountType());
        ddAccountType.selectByVisibleText("Personal Checking");
        populateTxtBoxFirstName(bank.getFirstName());
        populateTxtBoxLastName(bank.getLastName());
        populateTxtBoxAddress(bank.getAddress());
        populateTxtCity(bank.getCity());
        populateTxtState(bank.getState());
        populateTxtZip(bank.getZipCode());
    }

    public void deletePaymentProfiles() {
        // Deleting payment profile
        int paymentProfiles = listPaymentProfile().size();
        for (int i = 0; i < paymentProfiles; i++) {
            clickBtnThreeDots();
            clickBtnRemove();
            clickBtnConfirmRemove();
            pause(5000);
        }
    }

    public void verifyTheAddingBankPaymentProfile(SoftAssert softAssert) throws SQLException, InterruptedException {
        log.info("To Verify Adding the Bank Payment Profile");
        User user = SCPConfiguration.user;
        Bank bank = ModelsConfiguration.readBankAccounts().getBankByAccountHolderName("Henry Jacob Bank Account");
        Map<String, String> payProfileDataMap = new HashMap<>();
        HomeSteps homeSteps = new HomeSteps(driver);
        // UnEnrolling of AutoPay or Text Pay
        //homeSteps.navigateToAutoPayment();
        //unEnrollFromAutoTextPay();
        //homeSteps.navigateToTextToPay();
        //unEnrollFromAutoTextPay();
        homeSteps.navigateToPaymentInfo();
        // Deleting Payment Profiles
        deletePaymentProfiles();
        // Verifying Payment Information Page
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
        clickRdoBtnBank();
        // Populating Bank profile from with valid details
        populateBankPaymentForm(bank);
        // Adding Payment Profile
        clickbtnAdd();
        pause(5000);
        softAssert.assertEquals(getToastMessage(), paymentInformationTextProp.getPropValue("txtSuccessAddMsg"),
                "Blank First Name, successful Msg do not match");
        // Verifying Bank Payment profile is visible
        String bankAccNum = bank.getBankAccountNumber();
        softAssert.assertTrue(isBankPaymentProfileVisible(bankAccNum.substring(bankAccNum.length() - 4)),
                "Bank Payment profile is not visible");

        // Validating if the payment method is available in AutoPay
        softAssert.assertTrue(isPaymentMethodVisibleInAutoPay(bank.getBankAccountNumber(), "BANK"),
                "The payment method is not visible in Auto Pay");
        // Validating if the payment method is available in Current
        softAssert.assertTrue(isPaymentMethodVisibleInCurrentBill(bank.getBankAccountNumber(), "BANK"),
                "The payment method is not visible in Current Bill");
        // Validating if the payment method is available in Text to Pay
        //softAssert.assertTrue(isPaymentMethodVisibleInTextToPay(bank.getBankAccountNumber(), "BANK"),
        //"The payment method is not visible in Current Bill");

    }

    public boolean isPaymentMethodVisibleInAutoPay(String num, String paymentType) {
        boolean method = false;
        HomeSteps homeSteps = new HomeSteps(driver);
        homeSteps.navigateToAutoPayment();
        clickBtnEnrollAutoPay();
        pause(3000);
        if (paymentType.equalsIgnoreCase("Bank")) {
            clickrdoBtnBankCBill();
            Select ddBankAccount = new Select(elementDDBankAccount());
            List<WebElement> ddBankAccountOptions = ddBankAccount.getOptions();
            for (WebElement option : ddBankAccountOptions) {
                if (option.getText().contains(num.substring(num.length() - 4))) {
                    log.info("Payment Method is available in AutoPay");
                    method = true;
                }
            }
        } else if (paymentType.equalsIgnoreCase("Card")) {
            clickcardRdoBtn();
            Select ddBankAccount = new Select(elementDDCardAccount());
            List<WebElement> ddBankAccountOptions = ddBankAccount.getOptions();
            for (WebElement option : ddBankAccountOptions) {
                if (option.getText().contains(num.substring(num.length() - 4))) {
                    log.info("Payment Method is available in AutoPay");
                    method = true;
                }
            }
        }
        return method;
    }

    public boolean isPaymentMethodVisibleInCurrentBill(String num, String paymentType) {
        boolean method = false;
        HomeSteps homeSteps = new HomeSteps(driver);
        homeSteps.navigateToCurrentBill();
        waitUntilCompletePageLoad();
        clickBtnMakpayment();
        pause(5000);
        waitUntilCompletePageLoad();
        if (paymentType.equalsIgnoreCase("Bank")) {
            clickrdoBtnBankCBill();
            Select ddBankAccount = new Select(elementDDBankAccountCurrentBill());
            List<WebElement> ddBankAccountOptions = ddBankAccount.getOptions();
            for (WebElement option : ddBankAccountOptions) {
                if (option.getText().contains(num.substring(num.length() - 2))) {
                    log.info("Payment Method is available in AutoPay");
                    method = true;
                }
            }
        } else if (paymentType.equalsIgnoreCase("Card")) {
            clickrdoBtnCardCBill();
            Select ddBankAccount = new Select(elementDDCardAccountCurrentBill());
            List<WebElement> ddBankAccountOptions = ddBankAccount.getOptions();
            for (WebElement option : ddBankAccountOptions) {
                if (option.getText().contains(num.substring(num.length() - 2))) {
                    log.info("Payment Method is available in AutoPay");
                    method = true;
                }
            }
        }
        return method;
    }

    public boolean isPaymentMethodVisibleInTextToPay(String num, String paymentType) throws InterruptedException {
        boolean method = false;
        HomeSteps homeSteps = new HomeSteps(driver);
        homeSteps.navigateToTextToPay();
        clickBtnEnrollTextToPay();
        pause(3000);
        if (paymentType.equalsIgnoreCase("Bank")) {
            //scrollPageToElement(elementDDBankAccountTextToPay());
            clickrdoBtnBankCBill();
            Select ddBankAccount = new Select(elementDDBankAccountTextToPay());
            List<WebElement> ddBankAccountOptions = ddBankAccount.getOptions();
            for (WebElement option : ddBankAccountOptions) {
                if (option.getText().contains(num.substring(num.length() - 4))) {
                    log.info("Payment Method is available in AutoPay");
                    method = true;
                }
            }
        } else if (paymentType.equalsIgnoreCase("Card")) {
            clickrdoBtnCardTextToPay();
            Select ddBankAccount = new Select(elementDDCardAccountTextToPay());
            List<WebElement> ddBankAccountOptions = ddBankAccount.getOptions();
            for (WebElement option : ddBankAccountOptions) {
                if (option.getText().contains(num.substring(num.length() - 4))) {
                    log.info("Payment Method is available in AutoPay");
                    method = true;
                }
            }
        }
        return method;
    }

    public void verifyTheMultipleBankPaymentProfileAndDeletion(SoftAssert softAssert)
            throws SQLException, InterruptedException {
        log.info("To Verify Adding Multiple Payment Profiles and Deletion ");
        Bank bank = ModelsConfiguration.readBankAccounts().getBankByAccountHolderName("Henry Jacob Bank Account");
        User user = SCPConfiguration.user;
        // Verifying Payment Information Page
        Assert.assertTrue(
                isPaymentInformationPage(paymentInformationTextProp.getPropValue("expectedPaymentInformationPageUrl"),
                        paymentInformationTextProp.getPropValue("expectedPaymentInformationPageHeader")),
                "Page URL and Header is not Matching");
        // Verifying Payment Information Page Header
        softAssert.assertEquals(getLblPaymentInfoPageHeadingText(),
                paymentInformationTextProp.getPropValue("expectedPaymentInformationPageHeader"),
                "Expected Page Title do not match");
        // Deleting Payment Profiles
        deletePaymentProfiles();
        // Bank Payment Profile 1
        // Clicking on Add Payment Link
        clickLnkAddPaymentMethod();
        //pause(20000);
        // Clicking on Bank Radio Button
        //clickRdoBtnBank();
        // Populate Bank Payment form and Add
        waitUntilCompletePageLoad();
        clickBnkOpt();
        populateBankPaymentForm(bank);
        clickbtnAdd();
        pause(10000);

        // Validating Duplicate Bank Payment Profile
        // Clicking on Add Payment Link
        clickLnkAddPaymentMethod();
        //pause(20000);
        // Clicking on Bank Radio Button
        //clickRdoBtnBank();
        // Populate Bank Payment form and Add
        waitUntilCompletePageLoad();
        clickBnkOpt();
        populateBankPaymentForm(bank);
        clickbtnAdd();
        pause(2000);
        softAssert.assertEquals(getToastMessage(),
                paymentInformationTextProp.getPropValue("txtErrMsgDuplicatePaymentProfile"),
                "Duplicate Error Toast Message Do Not Match");
        clickbtnCancel();
        pause(2000);

        // Bank Payment Profile 2
        // Clicking on Add Payment Link
        clickLnkAddPaymentMethod();
        pause(20000);
        // Clicking on Bank Radio Button
        clickRdoBtnBank();
        // Populate Bank Payment form and Add
        populateBankPaymentForm(bank);
        populateTxtBoxBankAccountNo(bank.getBankAccountNumber() + "0");
        populateTxtBoxConfirmBankAccountNo(bank.getBankAccountNumber() + "0");
        clickbtnAdd();
        pause(10000);

        // Checking Both Payment Profile with Same Routing is visible
        // Bank Profile 1
        String bankAccNum1 = bank.getBankAccountNumber();
        softAssert.assertTrue(isBankPaymentProfileVisible(bankAccNum1.substring(bankAccNum1.length() - 4)),
                "Bank Payment profile 1 is not visible");
        softAssert.assertTrue(
                getBankNameofPaymentProfile(bankAccNum1.substring(bankAccNum1.length() - 4))
                        .contains(paymentInformationTextProp.getPropValue("txtBankName")),
                "Bank Payment profile 1 name do not match");
        // Bank Profile 2
        String bankAccNum2 = bank.getBankAccountNumber() + "0";
        softAssert.assertTrue(isBankPaymentProfileVisible(bankAccNum2.substring(bankAccNum2.length() - 4)),
                "Bank Payment profile 2 is not visible");
        softAssert.assertTrue(
                getBankNameofPaymentProfile(bankAccNum2.substring(bankAccNum2.length() - 4))
                        .contains(paymentInformationTextProp.getPropValue("txtBankName")),
                "Bank Payment profile 2 name do not match");
        // Validating Default Payment
        clickBtnThreeDots();
        clickBtnSetDefault();
        clickBtnMakeDefault();
        pause(5000);

        // Deleting Bank payment Profile
        clickBtnThreeDots();
        clickBtnRemove();
        clickBtnConfirmRemove();
        pause(3000);
        softAssert.assertEquals(getTostMessageWithWait(), paymentInformationTextProp.getPropValue("txtDeleteProfile"),
                "Expected Delete payment profile tost-Msg Do not match");


    }


}

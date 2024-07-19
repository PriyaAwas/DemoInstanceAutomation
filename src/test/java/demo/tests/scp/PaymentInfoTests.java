package demo.tests.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import sew.ai.config.Configuration;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.runner.TestRunner;
import sew.ai.steps.scp.DashboardSteps;
import sew.ai.steps.scp.HomeSteps;
import sew.ai.steps.scp.LoginSteps;
import demo.steps.scp.BillingPaymentSteps;
import demo.steps.scp.PaymentInformationSteps;
import java.sql.SQLException;

public class PaymentInfoTests extends TestRunner {

	private static final Logger log = LogManager.getLogger(PaymentInfoTests.class);
	private PaymentInformationSteps paymentInformationSteps;
	private BillingPaymentSteps billingPaymentSteps;

	String accountType = null;

	@FrameworkAnnotations(author = { "SatyaTiwari" }, category = { CategoryType.SANITY, CategoryType.SCP_PAYMENT_INFO })
	@Test(priority = 1, description = "To verify the Payment information page objects after login.")
	public void verifyPaymentInformationPageObjects() {
		log.info("To Verify Payment information fields and Add payment method pop-up");
		SoftAssert softAssert = new SoftAssert();
		// SCP- Application Login
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToPaymentInfo();
		paymentInformationSteps = new PaymentInformationSteps(driver);
		// Verifying Payment Information page
		paymentInformationSteps.verifyThePaymentInformationPageObjects(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyPaymentInformationPageObjects - is Completed.");
	}

	@FrameworkAnnotations(author = { "SatyaTiwari" }, category = { CategoryType.SMOKE, CategoryType.SCP_PAYMENT_INFO })
	@Test(priority = 2, description = "To verify the Bank Payment form fields.")
	public void verifyBankPaymentFormFields() throws InterruptedException {
		log.info("To Verify Bank Payment form fields");
		SoftAssert softAssert = new SoftAssert();
		// SCP- Application Login & Navigating to Payment information Page
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToPaymentInfo();
		// Verifying Bank Payment form fields
		paymentInformationSteps = new PaymentInformationSteps(driver);
		paymentInformationSteps.verifyTheBankPaymentFormFields(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyPaymentInformationPageObjects - is Completed.");
	}
	

	@FrameworkAnnotations(author = { "SatyaTiwari" }, category = { CategoryType.SANITY, CategoryType.SCP_PAYMENT_INFO })
	@Test(priority = 3, description = "To verify the Bank Payment form fields.")
	public void verifyBankPaymentFormFieldsValidation() throws InterruptedException {
		log.info("To Verify Payment Bank Payment form fields validation");
		SoftAssert softAssert = new SoftAssert();
		// SCP- Application Login & Navigating to Payment information Page
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToPaymentInfo();
		// Verifying Bank Payment form field Validation
		paymentInformationSteps = new PaymentInformationSteps(driver);
		paymentInformationSteps.verifyTheBankPaymentFormFieldValidation(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyPaymentInformationPageObjects - is Completed.");
	}

	@FrameworkAnnotations(author = { "SatyaTiwari" }, category = { CategoryType.SMOKE, CategoryType.SCP_PAYMENT_INFO })
	@Test(priority = 4, description = "To verify the Bank Payment form fields.")
	public void verifyAddingBankPaymentProfile() throws SQLException, InterruptedException {
		log.info("To Verify Payment Bank Payment form fields validation");
		SoftAssert softAssert = new SoftAssert();
		// SCP- Application Login & Navigating to Payment information Page
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToPaymentInfo();
		// Verifying Bank Payment form field Validation
		paymentInformationSteps = new PaymentInformationSteps(driver);
		paymentInformationSteps.verifyTheAddingBankPaymentProfile(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyPaymentInformationPageObjects - is Completed.");
	}

	@FrameworkAnnotations(author = { "SatyaTiwari" }, category = { CategoryType.SANITY, CategoryType.SCP_PAYMENT_INFO })
	@Test(priority = 5, description = "To verify the Bank Payment form fields.")
	public void verifyMultipleBankPaymentProfileAndDeletion() throws SQLException, InterruptedException {
		log.info("To Verify Adding Multiple Bank Payment profiles and deletion validation");
		SoftAssert softAssert = new SoftAssert();
		// SCP- Application Login & Navigating to Payment information Page
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToPaymentInfo();
		// Verifying Bank Payment form field Validation
		paymentInformationSteps = new PaymentInformationSteps(driver);
		paymentInformationSteps.verifyTheMultipleBankPaymentProfileAndDeletion(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyPaymentInformationPageObjects - is Completed.");
	}

	@FrameworkAnnotations(author = { "PriyaAwasthi" }, category = { CategoryType.SMOKE,
			CategoryType.SCP_PAYMENT_INFO })
	@Test(priority = 6, description = "To verify the Bank Payment form fields.")
	public void verifyMakePaymentWithNewCC() throws SQLException, InterruptedException {
		log.info("To Verify Adding CC Payement and making payment");
		SoftAssert softAssert = new SoftAssert();
		// SCP- Application Login & Navigating to Payment information Page
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToPaymentInfo();
		// Verifying Bank Payment form field Validation
		billingPaymentSteps = new BillingPaymentSteps(driver);
		billingPaymentSteps.verifyMakePaymentWithNewCC(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyMakePaymentWithNewCC - is Completed.");
	}

	@FrameworkAnnotations(author = { "Priya Awasthi" }, category = { CategoryType.SANITY,
			CategoryType.SCP_PAYMENT_INFO })
	@Test(priority = 7, description = "To verify the CreditCardPayment form fields.")
	public void verifyCreditCardPaymentFormFields() throws SQLException, InterruptedException {
		log.info("To Verify Adding Multiple Bank Payment profiles and deletion validation");
		SoftAssert softAssert = new SoftAssert();
		// SCP- Application Login & Navigating to Payment information Page
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToPaymentInfo();
		// Verifying Bank Payment form field Validation
		billingPaymentSteps = new BillingPaymentSteps(driver);
		billingPaymentSteps.verifyCreditCardPaymentFormFields(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyCreditCardPaymentFormFields - is Completed.");
	}

	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SANITY,
			CategoryType.SCP_PAYMENT_INFO })
	@Test(priority = 7, description = "To verify the payment with existing bank account")
	public void verifyBankPayment() throws SQLException, InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		// SCP- Application Login & Navigating to Payment information Page
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		ExtentLogger.logInfo("Login done");
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToPaymentInfo();
		ExtentLogger.logInfo("Added bank account");
		// Verifying Bank Payment form field Validation
		billingPaymentSteps = new BillingPaymentSteps(driver);
		billingPaymentSteps.verifyMakePaymentWithBank(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		ExtentLogger.logInfo("Test Case execution for - verifyBankPayment - is Completed.");
	}

	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SANITY,
			CategoryType.SCP_PAYMENT_INFO })
	@Test(priority = 7, description = "To verify making a payment with new bank account")
	public void verifyNewBankPayment() throws SQLException, InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		// SCP- Application Login & Navigating to Payment information Page
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		ExtentLogger.logInfo("Login done");
		// Verifying Bank Payment form field Validation
		billingPaymentSteps = new BillingPaymentSteps(driver);
		billingPaymentSteps.verifyMakePaymentWithNewBank(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		ExtentLogger.logInfo("Test Case execution for - verifyNewBankPayment - is Completed.");
	}

	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SANITY,
			CategoryType.SCP_PAYMENT_INFO })
	@Test(priority = 7, description = "To Verify negative scenarios with new bank detail fields")
	public void verifyNegNewBankPayment() throws SQLException, InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		// SCP- Application Login & Navigating to Payment information Page
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		ExtentLogger.logInfo("Login done");
		// Verifying Bank Payment form field Validation
		billingPaymentSteps = new BillingPaymentSteps(driver);
		billingPaymentSteps.verifyNegMakePaymentWithNewBank(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		ExtentLogger.logInfo("Test Case execution for - verifyNegNewBankPayment - is Completed.");
	}

	@FrameworkAnnotations(author = { "Priya Awasthi" }, category = { CategoryType.SANITY,
			CategoryType.SCP_PAYMENT_INFO })
	@Test(priority = 7, description = "To verify the CeditCardPayment Form Fields Validation .")
	public void verifyCeditCardPaymentFormFieldsValidation() throws SQLException, InterruptedException {
		log.info("To Verify Adding Multiple Bank Payment profiles and deletion validation");
		SoftAssert softAssert = new SoftAssert();
		// SCP- Application Login & Navigating to Payment information Page
		LoginSteps loginSteps = new LoginSteps(driver);
	   loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		// Verifying Bank Payment form field Validation
		billingPaymentSteps = new BillingPaymentSteps(driver);
		billingPaymentSteps.verifyTheMultipleCCPaymentProfileAndDeletion(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyCeditCardPaymentFormFieldsValidation - is Completed.");
	}
	
	@FrameworkAnnotations(author = { "Priya Awasthi" }, category = { CategoryType.SANITY,
			CategoryType.SCP_PAYMENT_INFO })
	@Test(priority = 7, description = "To verify the CeditCardPayment Form Fields Validation .")
	public void verifyAddNewPaymentBasedOnCustomerID() throws SQLException, InterruptedException {
		log.info("To verify Add New Payment BasedOnCustomerID");
		SoftAssert softAssert = new SoftAssert();
		// SCP- Application Login & Navigating to Payment information Page
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToPaymentInfo();
		// Verifying Bank Payment form field Validation
		billingPaymentSteps = new BillingPaymentSteps(driver);
		billingPaymentSteps.verifyAddNewPaymentBasedOnSelectedAcc(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyAddNewPaymentBasedOnSelectedAcc - is Completed.");
	}
	

}

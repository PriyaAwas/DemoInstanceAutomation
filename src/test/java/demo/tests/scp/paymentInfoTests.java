package demo.tests.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import sew.ai.config.Configuration;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.runner.TestRunner;
import sew.ai.steps.scp.DashboardSteps;
import sew.ai.steps.scp.HomeSteps;
import sew.ai.steps.scp.LoginSteps;
import alectra.steps.scp.LoginAlectraSteps;
import alectra.tests.scp.LoginAlectraTests;
import demo.steps.scp.PaymentInformationSteps;
import demo.steps.scp.paymentInfoSteps;

import java.io.IOException;
import java.sql.SQLException;

public class paymentInfoTests extends TestRunner {
	
	private static final Logger log = LogManager.getLogger(paymentInfoTests.class);
	private paymentInfoSteps paymentInfoSteps;
	private PaymentInformationSteps paymentInformationSteps;
	String accountType = null;
	
	@FrameworkAnnotations(author = { "Priya" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "To perform sanity on Payment Information with credit card")
	public void accPaymentInformation() throws SQLException, IOException, InterruptedException {
		log.info("To perform sanity on Payment Information Submodule");
		SoftAssert softAssert = new SoftAssert();
		paymentInfoSteps = new paymentInfoSteps(driver);
		paymentInfoSteps.accPaymentInformation();
		softAssert.assertAll();
	}
	
	@FrameworkAnnotations(author = { "Priya" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "To perform sanity on Payment Information with credit card")
	public void makePaymentwithExistingCreditCard() throws SQLException, IOException, InterruptedException {
		log.info("To perform payement with existing CC ");
		SoftAssert softAssert = new SoftAssert();
		paymentInfoSteps = new paymentInfoSteps(driver);
		paymentInfoSteps.makePaymentwithExistingCreditCard();
		softAssert.assertAll();
	}
	

	@FrameworkAnnotations(author = { "SatyaTiwari" }, category = { CategoryType.SANITY,CategoryType.SCP_PAYMENT_INFO })
	@Test(priority = 1, description = "To verify the Payment information page objects after login.")
	public void verifyPaymentInformationPageObjects() {
		log.info("To Verify Payment information feilds and Add payment method pop-up");
		SoftAssert softAssert = new SoftAssert();
		//SCP- Application Login
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToPaymentInfo();
		paymentInformationSteps = new PaymentInformationSteps(driver);
		//Verifying Payment Information page
		paymentInformationSteps.verifyThePaymentInformationPageObjects(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyPaymentInformationPageObjects - is Completed.");
	}
	
	@FrameworkAnnotations(author = { "SatyaTiwari" }, category = { CategoryType.SANITY,CategoryType.SCP_PAYMENT_INFO })
	@Test(priority = 2, description = "To verify the Bank Payment form fields.")
	public void verifyBankPaymentFormFields() throws InterruptedException {
		log.info("To Verify Bank Payment form fields");
		SoftAssert softAssert = new SoftAssert();
		//SCP- Application Login & Navigating to Payment information Page
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToPaymentInfo();
		//Verifying Bank Payment form fields
		paymentInformationSteps = new PaymentInformationSteps(driver);
		paymentInformationSteps.verifyTheBankPaymentFormFields(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyPaymentInformationPageObjects - is Completed.");
	}

	@FrameworkAnnotations(author = { "SatyaTiwari" }, category = { CategoryType.SANITY,CategoryType.SCP_PAYMENT_INFO })
	@Test(priority = 3, description = "To verify the Bank Payment form fields.")
	public void verifyBankPaymentFormFieldsValidation() throws InterruptedException{
		log.info("To Verify Payment Bank Payment form fields validation");
		SoftAssert softAssert = new SoftAssert();
		//SCP- Application Login & Navigating to Payment information Page
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToPaymentInfo();
		//Verifying Bank Payment form field Validation
		paymentInformationSteps = new PaymentInformationSteps(driver);
		paymentInformationSteps.verifyTheBankPaymentFormFieldValidation(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyPaymentInformationPageObjects - is Completed.");
	}

}

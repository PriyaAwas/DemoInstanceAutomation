package demo.tests.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import demo.steps.scp.PostLogContactUsSteps;
import sew.ai.config.Configuration;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.runner.TestRunner;
import sew.ai.steps.scp.DashboardSteps;
import sew.ai.steps.scp.LoginSteps;

public class PostLogContactUsTests extends TestRunner {

	private static final Logger log = LogManager.getLogger(PostLogContactUsTests.class);
	private PostLogContactUsSteps postLogContactUsSteps;

	@FrameworkAnnotations(author = { "Priya Awasthi" }, category = { CategoryType.SMOKE, CategoryType.SCP_CONNECTME, })
	@Test(priority = 1, description = "To verify the Post Login ContactUs Page objects.")
	public void verifyPostLoginContactUsPage() throws InterruptedException {

		ExtentLogger.logInfo("Test Case execution for - verifyPostLoginConnectMePage - "
				+ "is Initiate TC_ID -->" + "Contact_Us_01" + "Contact_Us_02" + "Contact_Us_03");
		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		// Init user model
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		// Init Post Log Connect Me Steps
		postLogContactUsSteps = new PostLogContactUsSteps(driver);
		// verify objects on Connect me page
		postLogContactUsSteps.verifyContactUsObject(softAssert);
		// Submit Blank Form
		//postLogContactUsSteps.verifySubmitblankForm();
		postLogContactUsSteps.verifyCreateContactRequest(softAssert);
		// Preview Contact Us Form
		postLogContactUsSteps.verifyPreviewYourFormDetails(softAssert);
		// Submit Form
		postLogContactUsSteps.verifySubmitForm(softAssert);
		log.info("Test Case execution for - verifyPostLoginConnectMePage - is Completed.");
	}


	@FrameworkAnnotations(author = { "Priya Awasthi" }, category = { CategoryType.SANITY, CategoryType.SCP_CONNECTME })
	@Test(priority = 1, description = "To verify the login page objects.")

	public void verifyPostLogCreateTrackContactReq() throws InterruptedException {
		log.info("Test Case execution for - verifyPostLogCreateTrackContactReq - is Initiated TC_ID -->" + "Contact_Us_04");
		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		// Init user model
		// Login into the application
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		// Init Post Log Connect Me Steps
		postLogContactUsSteps = new PostLogContactUsSteps(driver);
		// Verify Connect me form
		postLogContactUsSteps.verifyContactUsObject(softAssert);
		// Create Request
		postLogContactUsSteps.verifyCreateContactRequest(softAssert);
		// Preview Contact Us Form
		postLogContactUsSteps.verifyPreviewYourFormDetails(softAssert);
		// Submit Form
		postLogContactUsSteps.verifySubmitForm(softAssert);
		// Verify Track Request Form
		postLogContactUsSteps.verifyTrackRequestForm();
		//
		postLogContactUsSteps.verifyTrackContactRequests();
		log.info("Test Case execution for - verifyPostLoginConnectMePage - is Completed.");
	}

	@FrameworkAnnotations(author = { "Priya Awasthi" }, category = { CategoryType.SANITY, CategoryType.SCP_CONNECTME })
	@Test(priority = 1, description = "To verify the login page objects.")

	public void verifyPostLoginSavedFormsPage() throws InterruptedException {
		log.info("Test Case execution for - verifyPostLoginSavedFormsPage - is Initiated TC_ID -->" + "Contact_Us_05");
		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		// Init user model
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		// Init Post Log Connect Me Steps
		postLogContactUsSteps = new PostLogContactUsSteps(driver);
		// Verify Connect me form
		postLogContactUsSteps.verifyContactUsObject(softAssert);

		// Verify Objects of Saved Form
		postLogContactUsSteps.verifySavedFormObject(softAssert);

		log.info("Test Case execution for - verifyPostLoginConnectMePage - is Completed.");
	}

	@FrameworkAnnotations(author = { "Priya Awasthi" }, category = { CategoryType.SANITY, CategoryType.SCP_CONNECTME })
	@Test(priority = 1, description = "To verifySubmitBillingQueryWithValidAttachment.")
	public void verifySubmitBillingQueryWithValidAttachment() throws InterruptedException {
		log.info("Test Case execution for - verifySubmitBillingQueryWithValidAttachment - is Initiated TC_ID -->" + "Contact_Us_07");
		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		// Login into the application
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		// Init Post Log Connect Me Steps
		postLogContactUsSteps = new PostLogContactUsSteps(driver);
		// Verify Connect me form
		postLogContactUsSteps.verifyContactUsObject(softAssert);
		// Verify create billing request with valid attachment
		postLogContactUsSteps.verifyCreateBillingRequestWithAttachment(softAssert);
		// Submit Form
		postLogContactUsSteps.verifySubmitForm(softAssert);
		log.info("Test Case execution for - verifyPostLoginConnectMePage - is Completed.");
	}

	@FrameworkAnnotations(author = { "Priya Awasthi" }, category = { CategoryType.SANITY, CategoryType.SCP_CONNECTME })
	@Test(priority = 1, description = "To verify the login page objects.")

	public void verifySubmitBillingQueryWithInValidAttachment() throws InterruptedException {
		log.info("Test Case execution for - verifySubmitBillingQueryWithInValidAttachment - is Initiated TC_ID -->" + "Contact_Us_08");

		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		// Login into the application
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		// Init Post Log Connect Me Steps
		postLogContactUsSteps = new PostLogContactUsSteps(driver);
		// Verify Connect me form
		postLogContactUsSteps.verifyContactUsObject(softAssert);
		// Verify creat billing request with valid attachment
		postLogContactUsSteps.verifyCreateRequestsWithAttachments(softAssert);
		// Submit Form
		log.info("Test Case execution for - verifyPostLoginConnectMePage - is Completed.");
	}

	@FrameworkAnnotations(author = { "Priya Awasthi" }, category = { CategoryType.SANITY, CategoryType.SCP_CONNECTME })
	@Test(priority = 1, description = "To verifyPostLoginSocialMediaPage.")

	public void verifyPostLoginSocialMediaPage() throws InterruptedException {
		log.info("Test Case execution for - verifyPostLoginConnectMePage - is Initiated TC_ID -->" + "Contact_Us_06");
		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		// Login into the application
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		// Init Post Log Connect Me Steps
		postLogContactUsSteps = new PostLogContactUsSteps(driver);
		// Verify Connect me form
		postLogContactUsSteps.verifyContactUsObject(softAssert);
		// verify objects on Connect me page
		postLogContactUsSteps.verifySocialMediaObject(softAssert);
		// verify objects on Social media page
		log.info("Test Case execution for - verifyPostLoginConnectMePage - is Completed.");
	}
	@FrameworkAnnotations(author = { "Priya Awasthi" }, category = { CategoryType.SANITY, CategoryType.SCP_CONNECTME })
	@Test(priority = 1, description = "To verifyPostLoginRaiseReportWithDifferentTopics.")
	public void verifyPostLoginRaiseReportWithWaterwasteReport() throws InterruptedException {
		log.info("Test Case execution for verifyPostLoginRaiseReportWithWaterwasteReport");
		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		// Login into the application
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		// Init Post Log Connect Me Steps
		postLogContactUsSteps = new PostLogContactUsSteps(driver);
		// Verify Connect me form
		// verify objects on Connect me page
		postLogContactUsSteps.createReportForWaterwasteReport(softAssert);
		// verify objects on Social media page
		log.info("Test Case execution for - verifyPostLoginRaiseReportWithDifferentTopics - is Completed.");
	}
	
	@FrameworkAnnotations(author = { "Priya Awasthi" }, category = { CategoryType.SANITY, CategoryType.SCP_CONNECTME })
	@Test(priority = 1, description = "To verifyPostLoginRaiseReportWithWaterTheft.")
	public void verifyPostLoginRaiseReportWithWaterTheft() throws InterruptedException {
		log.info("Test Case execution for verifyPostLoginRaiseReportWithWaterTheft");
		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		// Login into the application
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		// Init Post Log Connect Me Steps
		postLogContactUsSteps = new PostLogContactUsSteps(driver);
		// Verify Connect me form
		// verify objects on Connect me page
		postLogContactUsSteps.createReportForWaterTheft(softAssert);
		
		// verify objects on Social media page
		log.info("Test Case execution for - verifyPostLoginRaiseReportWithWaterTheft - is Completed.");
	}
	
	@FrameworkAnnotations(author = { "Priya Awasthi" }, category = { CategoryType.SANITY, CategoryType.SCP_CONNECTME })
	@Test(priority = 1, description = "To VerifyCustomerServiceDetailsOnContactUsPage.")

	public void verifyCustomerServiceDetailsOnContactUsPage() throws InterruptedException {
		log.info("Test Case execution for verifyCustomerServiceDetails");
		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		// Login into the application
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		// Init Post Log Connect Me Steps
		postLogContactUsSteps = new PostLogContactUsSteps(driver);
		// Verify Connect me form
		// verify objects on Connect me page
		postLogContactUsSteps.verifyCustomerServiceDetails(softAssert);
		// verify objects on Social media page
		log.info("Test Case execution for - VerifyCustomerServiceDetailsOnContactUsPage - is Completed.");
	}
	
	
	@FrameworkAnnotations(author = { "Priya Awasthi" }, category = { CategoryType.SANITY, CategoryType.SCP_CONNECTME })
	@Test(priority = 1, description = "To verify Edit Saved Form Functionality.")

	public void verifyEditSavedFormFunctionality() throws InterruptedException {
		log.info("Test Case execution for verifyEditSavedFormFunctionality");
		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		// Login into the application
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		// Init Post Log Connect Me Steps
		postLogContactUsSteps = new PostLogContactUsSteps(driver);
		// Verify Connect me form
		// verify objects on Connect me page
		postLogContactUsSteps.verifyEditSavedForm(softAssert);
		// verify objects on Social media page
		log.info("Test Case execution for - verifyEditSavedFormFunctionality - is Completed.");
	}
	
	@FrameworkAnnotations(author = { "Priya Awasthi" }, category = { CategoryType.SANITY, CategoryType.SCP_CONNECTME })
	@Test(priority = 1, description = "To verifyPostLoginRaiseReportForOutageNotification.")
	public void verifyPostLoginRaiseReportForContactUs() throws InterruptedException {
		log.info("Test Case execution for verifyPostLoginRaiseReportForContactUs");
		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		// Login into the application
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		// Init Post Log Connect Me Steps
		postLogContactUsSteps = new PostLogContactUsSteps(driver);
		// Verify Connect me form
		// verify objects on Connect me page
		postLogContactUsSteps.createReportForContactUs(softAssert);
		
		// verify objects on Social media page
		log.info("Test Case execution for - verifyPostLoginRaiseReportForContactUs - is Completed.");
	}
}

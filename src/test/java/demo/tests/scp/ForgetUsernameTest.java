package demo.tests.scp;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.runner.TestRunner;
import demo.steps.scp.ForgetUsernameSteps;
import demo.steps.scp.PreLoginSteps;

public class ForgetUsernameTest extends TestRunner {
	private static final Logger log = LogManager.getLogger(ForgetUsernameTest.class);
	private ForgetUsernameSteps forgetUsernameSteps;

	@FrameworkAnnotations(author = { "Priya Awasthi" }, category = { CategoryType.SMOKE, CategoryType.SCP_LOGINHELP })
	@Test(priority = 1, description = "To verify the Forget Username page objects.")
	public void validateResetUsername() throws Exception {
		log.info("To verify that following should be displayed on validate Forgot Username.");
		SoftAssert softAssert = new SoftAssert();
		// Init Forget Username Steps
		forgetUsernameSteps = new ForgetUsernameSteps(driver);
		// Redirect to Forget Username Steps
		forgetUsernameSteps.verifyForgotUsernameInObject(softAssert);
		log.info("Test Case execution for - validateForgotUsername - is Completed.");

	}

	@FrameworkAnnotations(author = { "Priya Awasthi" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGINHELP })
	@Test(priority = 2, description = "To verify the Forget Username name with valid and invalid email.")
	public void resetUsernameFieldVerification() {
		log.info("To verify that following should be displayed on validate Forgot Username.");
		SoftAssert softAssert = new SoftAssert();
		// Init Forget Username Steps
		forgetUsernameSteps = new ForgetUsernameSteps(driver);
		// Redirect to Forget Username Steps
		forgetUsernameSteps.verifyForgotUsernameInObject(softAssert);
		// invalid Email verification
		forgetUsernameSteps.emailFeildVerification();
		log.info("Test Case execution for - resetUsernameInvalidAccountOrEmail - is Completed.");
	}

	@FrameworkAnnotations(author = { "Priya Awasthi" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGINHELP })
	@Test(priority = 3, description = "To verify the Forget Username name with valid email.")
	public void resetUsernameValidEmailId() {
		log.info("To verify that following should be displayed on validate Forgot Username.");
		SoftAssert softAssert = new SoftAssert();
		// Init Forget Username Steps
		forgetUsernameSteps = new ForgetUsernameSteps(driver);
		// Redirect to Forget Username Steps
		forgetUsernameSteps.verifyForgotUsernameInObject(softAssert);
		// invalid Email verification
		forgetUsernameSteps.validEmailFeildVerification();
		log.info("Test Case execution for - resetUsernameInvalidAccountOrEmail - is Completed.");
	}

	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGINHELP })
	@Test(priority = 4, description = "To check the functionality of Cancel button on Forget Username page")
	public void checkCancelFunctnUsername() {
		log.info("To verify the functionality of Cancel button on Forget Username page");
		SoftAssert softAssert = new SoftAssert();
		// Init Forget Username Steps
		forgetUsernameSteps = new ForgetUsernameSteps(driver);
		// Redirect to Forget Username Steps
		forgetUsernameSteps.verifyForgotUsernameInObject(softAssert);
		// cancel button function verification
		forgetUsernameSteps.cancelBttnVerification();
		log.info("Test Case execution for - checkCancelFunctnUsername - is Completed.");
	}

	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGINHELP })
	@Test(priority = 4, description = "To check the functionality of Home icon on Forget Username page")
	public void checkHomeIconFunctnUsername() {
		log.info("To verify the functionality of Home icon on Forget Username page");
		SoftAssert softAssert = new SoftAssert();
		// Init Forget Username Steps
		forgetUsernameSteps = new ForgetUsernameSteps(driver);
		// Redirect to Forget Username Steps
		forgetUsernameSteps.verifyForgotUsernameInObject(softAssert);
		// Home icon function verification
		forgetUsernameSteps.homeIconVerification();
		log.info("Test Case execution for - checkHomeIconFunctnUsername - is Completed.");
	}

	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGINHELP })
	@Test(priority = 4, description = "To check the IP Lock functionality")
	public void checkIPLockFunctionality() {
		log.info("To verify the the IP Lock functionality");
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		forgetUsernameSteps = new ForgetUsernameSteps(driver);
//		forgetUsernameSteps.loginIntoTheApplicationWrongCreds(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - checkHomeIconFunctnUsername - is Completed.");
	}
	
	@FrameworkAnnotations(author = { "priya" }, category = { CategoryType.SMOKE, CategoryType.SCP_WAYSTOSAVE })
	@Test(priority = 3, description = "To verify the ways to save page.")
	public void verifyAccountBlockOnLogin() {
		String[] tc_id = { "Pre_Login_12" };
		ExtentLogger.logInfo(
				"Test Case execution for - verifyWaysToSavePage. Test Case id's -->  " + Arrays.toString(tc_id));
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		forgetUsernameSteps = new ForgetUsernameSteps(driver);	
		  
		String toastErrMsg = forgetUsernameSteps.verifyAccountIPLockFunctionality(softAssert);
//	        softAssert.assertEquals(toastErrMsg, loginTextProp.getPropValue("accountIPBlockedErrMsg"),
//	"Invalid credential error message not matched.");
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyWaysToSavePage - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyWaysToSavePage - is Completed.");
	}


	@FrameworkAnnotations(author = { "Priya Awasthi" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGINHELP })
    @Test(priority = 3, description = "To verify the Forget Username name with valid email.")
    public void max() {
        log.info("To verify that following should be displayed on validate Forgot Username.");
        SoftAssert softAssert = new SoftAssert();
        // Init Forget Username Steps
        forgetUsernameSteps = new ForgetUsernameSteps(driver);
        forgetUsernameSteps.validEmailFeildVerification();
        log.info("Test Case execution for - resetUsernameInvalidAccountOrEmail - is Completed.");                  
      }

	
  }

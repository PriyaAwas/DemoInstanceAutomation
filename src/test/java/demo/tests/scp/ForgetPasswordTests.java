package demo.tests.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.runner.TestRunner;
import demo.steps.scp.ForgetPasswordSteps;

public class ForgetPasswordTests extends TestRunner {
	private static final Logger log = LogManager.getLogger(ForgetPasswordTests.class);
	private ForgetPasswordSteps forgetPasswordSteps;

	@FrameworkAnnotations(author = { "Priya Awasthi" }, category = { CategoryType.SMOKE ,CategoryType.SCP_LOGINHELP})
	@Test(priority = 1, description = "To verify the Forget Password page objects.")
	public void validateForgotPasswordObjects() {
		log.info("To verify that following should be displayed on Forget Password In page.");
		SoftAssert softAssert = new SoftAssert();
		forgetPasswordSteps = new ForgetPasswordSteps(driver); // Verify Forget Password Object
		forgetPasswordSteps.verifyForgetPasswordObject(softAssert);
		softAssert.assertAll();
		log.info("Test Case execution for - validateForgotPasswordObjects - is Completed.");
	}

	@FrameworkAnnotations(author = { "Priya Awasthi" }, category = { CategoryType.SANITY ,CategoryType.SCP_LOGINHELP})

	@Test(priority = 2, description = "To verify the Forget Password page objects.")
	public void validateForgotPassword() {
		log.info("To verify that following should be displayed on Forget Password In page.");
		SoftAssert softAssert = new SoftAssert();
		forgetPasswordSteps = new ForgetPasswordSteps(driver); // Submit Blank UserName
		forgetPasswordSteps.verifySubmitBlankUserName(softAssert);
		softAssert.assertAll();
		log.info("Test Case execution for - validateForgotPassword - is Completed.");
	}

	@FrameworkAnnotations(author = { "Priya Awasthi" }, category = { CategoryType.SANITY,CategoryType.SCP_LOGINHELP })
	@Test(priority = 3, description = "To verify the Forget Password page objects.")
	public void validateForgotPasswordWithValidandInvalidData() {
		log.info("To verify that following should be displayed on Forget Password In page.");
		SoftAssert softAssert = new SoftAssert();
		forgetPasswordSteps = new ForgetPasswordSteps(driver);
		// Submit Invalid UserName
		forgetPasswordSteps.verifyInvalidUserName(softAssert);
		softAssert.assertAll();
		log.info("Test Case execution for - validateForgotPassword - is Completed.");
	}
}

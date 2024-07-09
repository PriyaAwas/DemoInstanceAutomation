package demo.tests.scp;


import java.sql.SQLException;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
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
import sew.ai.utils.PropertiesUtil;
import demo.steps.scp.PaymentLocationSteps;
import demo.steps.scp.PreLoginSteps;

public class PaymentLocationTests extends TestRunner {

	private static final Logger log = LogManager.getLogger(PaymentLocationTests.class);
	private PaymentLocationSteps preLoginSteps;
	public static PropertiesUtil loginTextProp;

	@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SMOKE, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "Verify Payment information Page.")

	public void verifyPreLoginPaymentLocationPage() throws InterruptedException {
		String[] tc_id = { "Pre_Login_03" };
		ExtentLogger.logInfo(
				"Test Case execution for - verifyLoginPageObjects. Test Case id's -->  " + Arrays.toString(tc_id));
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		preLoginSteps = new PaymentLocationSteps(driver);
		// Go to the application and verify objects
		preLoginSteps.verifyPreLoginPaymentLocationPage(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyPreLoginPaymentLocationPage - is Completed.");
		ExtentLogger.logInfo("Test Case execution for - verifyPaymentLocationPage - is Completed.");
		
	}
		
		@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SMOKE, CategoryType.SCP_LOGIN })
		@Test(priority = 1, description = "Verify Payment information Page.")

		public void verifyPostloginPaymentlocation() throws InterruptedException {
			String[] tc_id = { "Pre_Login_02" };
			ExtentLogger.logInfo(
					"Test Case execution for - verifyPostLoginPaymentLocationPage. Test Case id's -->  " + Arrays.toString(tc_id));
			SoftAssert softAssert = new SoftAssert();
			// Init login steps
			LoginSteps loginSteps = new LoginSteps(driver);
			DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
					Configuration.toString("password"));
			HomeSteps homeSteps = new HomeSteps(driver);
			homeSteps.navigateToPaymentLocations();
			preLoginSteps = new PaymentLocationSteps(driver);
			// Go to the application and verify objects
			preLoginSteps.verifyPostloginPaymentlocation(softAssert);
			// Assert all the soft verification.
			
		
	}}
	
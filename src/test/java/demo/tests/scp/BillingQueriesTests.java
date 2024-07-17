package demo.tests.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import demo.pageobjects.BillingQueriesPage;
import demo.steps.scp.BillingQueriesSteps;
import demo.steps.scp.PostLogContactUsSteps;
import demo.steps.scp.UserDashboardSteps;
import sew.ai.config.Configuration;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.runner.TestRunner;
import sew.ai.steps.scp.HomeSteps;
import sew.ai.steps.scp.LoginSteps;
import sew.ai.utils.PropertiesUtil;

public class BillingQueriesTests extends TestRunner{
	private static final Logger log = LogManager.getLogger(BillingQueriesSteps.class);
	private BillingQueriesSteps billingQueriesSteps;

	@FrameworkAnnotations(author = {"Prashant Kumar"}, category = { CategoryType.SANITY,CategoryType.SCP_DASHBOARD })

	@Test(priority = 1, description = "To Verify that application will not allow Customer to submit Request without providing Comment Field value")
	public void verifyBillQueriesContactUSpageerrormsg() throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToBillinQueries();
		billingQueriesSteps = new BillingQueriesSteps(driver);
		billingQueriesSteps.verifyBillinqueruiesContactUs(softAssert);
		softAssert.assertAll();
		log.info("Test Case execution for - verifyBillingQueriesContactUSpage - is Completed.");
	}
	
	@FrameworkAnnotations(author = {"Prashant Kumar"}, category = { CategoryType.SANITY,CategoryType.SCP_DASHBOARD })

	@Test(priority = 1, description = "To Verify that application will not allow Customer to submit Request without providing Comment Field value")
	public void verifysubmitBillinTrackreq() throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToBillinQueries();
		billingQueriesSteps = new BillingQueriesSteps(driver);
		billingQueriesSteps.submitBillingqueries(softAssert);
		softAssert.assertAll();
		log.info("Test Case execution for - verifyBillingQueriesContactUSpage - is Completed.");
	}
	
	@FrameworkAnnotations(author = {"Prashant Kumar"}, category = { CategoryType.SANITY,CategoryType.SCP_DASHBOARD })

	@Test(priority = 1, description = "To Verify that application will not allow Customer to submit Request without providing Comment Field value")
	public void verifyInvalidfileupload() throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToBillinQueries();
		billingQueriesSteps = new BillingQueriesSteps(driver);
		billingQueriesSteps.submitInvalidAttachment(softAssert);
		softAssert.assertAll();
		log.info("Test Case execution for - verifyBillingQueriesContactUSpage - is Completed.");
	}
}



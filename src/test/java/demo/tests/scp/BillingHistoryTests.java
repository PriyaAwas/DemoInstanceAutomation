package demo.tests.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import demo.steps.scp.BillingHistorySteps;
import sew.ai.config.Configuration;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.helpers.testrail.TestRail;
import sew.ai.runner.TestRunner;
import sew.ai.steps.scp.DashboardSteps;
import sew.ai.steps.scp.HomeSteps;
import sew.ai.steps.scp.LoginSteps;

import java.sql.SQLException;
import java.text.ParseException;

public class BillingHistoryTests extends TestRunner {
	private static final Logger log = LogManager.getLogger(BillingHistoryTests.class);
	private BillingHistorySteps billingHistorySteps;


	@FrameworkAnnotations(author = { "SatyaTiwari" }, category = { CategoryType.SANITY,CategoryType.SCP_BILLING })
	@Test(priority = 1, description = "To verify 'Export To Excel' link and Contents in download file for Billing and Payment history.")
	public void verifyExportToExcelAndItsContents() throws ParseException {
		SoftAssert softAssert = new SoftAssert();
		//SCP- Application Login
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToBillingAndPaymentHistory();
		billingHistorySteps = new BillingHistorySteps(driver);
		billingHistorySteps.verifyExportToExcelAndItsContents(softAssert);
		softAssert.assertAll();
	}
	
	@FrameworkAnnotations(author = { "SatyaTiwari" }, category = { CategoryType.SMOKE,CategoryType.SCP_BILLING })
	@Test(priority = 2, description = "To verify Billing History page UI and Objects.")
	public void verifyBillHistoryUIAndObjects() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		//SCP- Application Login
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToBillingAndPaymentHistory();
		billingHistorySteps = new BillingHistorySteps(driver);
		billingHistorySteps.verifyBillHistoryUIAndObjects(softAssert);
		softAssert.assertAll();
	}



}

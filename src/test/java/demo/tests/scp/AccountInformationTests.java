package demo.tests.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import sew.ai.config.Configuration;
import sew.ai.config.SCPConfiguration;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.models.User;
import sew.ai.runner.TestRunner;
import demo.steps.scp.AccountInformationSteps;
import sew.ai.steps.scp.DashboardSteps;
import sew.ai.steps.scp.HomeSteps;
import sew.ai.steps.scp.LoginSteps;

public class AccountInformationTests extends TestRunner {
	private static final Logger log = LogManager.getLogger(AccountInformationTests.class);
	private AccountInformationSteps accountInformationSteps;
	private DashboardSteps dashboardSteps;

	public AccountInformationTests() {
		accountInformationSteps = new AccountInformationSteps(driver);

	}

	@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the dashboard page objects after login.")
	public void verifyLinkAccountUIAndValidation() {
		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		accountInformationSteps = new AccountInformationSteps(driver);
		dashboardSteps = new DashboardSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		accountInformationSteps.linkAccountUIAndValidation(softAssert);
		softAssert.assertAll();
		log.info("Test Case execution for - verifyLinkAccountUIAndValidation - is Completed.");
	}

	@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SANITY })
	@Test(priority = 2, description = "To verify the dashboard page objects after login.")
	public void verifyAccountInfoPageValidation() {
		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		accountInformationSteps = new AccountInformationSteps(driver);
		dashboardSteps = new DashboardSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		accountInformationSteps.verifyAccountInfoPageValidation(softAssert);
		softAssert.assertAll();
		log.info("Test Case execution for - Accountinfopagevalidation - is Completed.");
	}

	@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SANITY })
	@Test(priority = 3,description = "To verify the dashboard page objects after login.")
	public void verifyLinkAccFormFieldValidation() {
		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		accountInformationSteps = new AccountInformationSteps(driver);
		dashboardSteps = new DashboardSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		accountInformationSteps.verifyLinkAccFormFieldValidation(softAssert);
		softAssert.assertAll();
		log.info("Test Case execution for - verifyLinkAccountUIAndValidation - is Completed.");

	}

	@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the dashboard page objects after login.")
	public void verifyEditFunctionality() {
		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		accountInformationSteps = new AccountInformationSteps(driver);
		dashboardSteps = new DashboardSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
	accountInformationSteps.verifyEditFunctionality(softAssert);
	log.info("Test Case execution for - verifyEditfunctionality - is Completed.");

	}
//
//	@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SANITY })
//	@Test(priority = 1, description = "To verify the dashboard page objects after login.")
//	public void verifyEditNickNameBlankValidation() {
//		SoftAssert softAssert = new SoftAssert();
//		LoginSteps loginSteps = new LoginSteps(driver);
//		accountInformationSteps = new AccountInformationSteps(driver);
//		dashboardSteps = new DashboardSteps(driver);
//		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
//				Configuration.toString("password"));
//		accountInformationSteps.verifyEditNickNameBlankValidation(softAssert);
//		log.info("Test Case execution for - verifyEditNickNameBlankValidation - is Completed.");
//	}

	@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the dashboard page objects after login.")
	public void verifyUnlinkDefaultAccFunctionality() {
		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		accountInformationSteps = new AccountInformationSteps(driver);
		dashboardSteps = new DashboardSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		accountInformationSteps.unlinkDefaultAcc(softAssert);
		log.info("Test Case execution for - VerifyDeaultaccUnlikingerrormsg - is Completed.");
	}


//	@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SANITY })
//	@Test(priority = 1, description = "To verify the dashboard page objects after login.")
//	public void editBillType() {
//		SoftAssert softAssert = new SoftAssert();
//		LoginSteps loginSteps = new LoginSteps(driver);
//		accountInformationSteps = new AccountInformationSteps(driver);
//		dashboardSteps = new DashboardSteps(driver);
//		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
//				Configuration.toString("password"));
//		accountInformationSteps.editBillType(softAssert);
//		log.info("Test Case execution for - Edit Bill Type - is Completed.");
//	}

@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SANITY })
@Test(priority = 1, description = "To verify the dashboard page objects after login.")
public void verifyEditMailingAddressFunctionality() {
	SoftAssert softAssert = new SoftAssert();
	LoginSteps loginSteps = new LoginSteps(driver);
	accountInformationSteps = new AccountInformationSteps(driver);
	dashboardSteps = new DashboardSteps(driver);
	DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
			Configuration.toString("password"));
accountInformationSteps.verifyErrorMsg(softAssert);
log.info("Test Case execution for - verifyEditMailingAddressFunctionality - is Completed.");

}

//@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SANITY })
//@Test(priority = 1, description = "To verify the dashboard page objects after login.")
//public void verifyEditMailingAddressNegativeFunctionality() {
//	SoftAssert softAssert = new SoftAssert();
//	LoginSteps loginSteps = new LoginSteps(driver);
//	accountInformationSteps = new AccountInformationSteps(driver);
//	dashboardSteps = new DashboardSteps(driver);
//	DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
//			Configuration.toString("password"));
//accountInformationSteps.verifyedit(softAssert);
//log.info("Test Case execution for - verifyEditMailingAddressNegativeFunctionality - is Completed.");
//
//}
}

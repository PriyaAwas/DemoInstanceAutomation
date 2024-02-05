package demo.tests.scp;

import java.util.Arrays;

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
	@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SMOKE,CategoryType.SCP_MYACCOUNT })
	@Test(priority = 1, description = "To verify the dashboard page objects after login.")
	public void verifyLinkAccountUIAndValidation() {
		String[] tc_id = {"Account Information_01"};
        ExtentLogger.logInfo("Test Case execution for - verifyLinkAccountPopUpelements. Test Case id's -->  " + Arrays.toString(tc_id));
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

	@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SANITY,CategoryType.SCP_MYACCOUNT })
	@Test(priority = 2, description = "To verify the dashboard page objects after login.")
	public void verifyAccountInfoPageValidation() {
		String[] tc_id = {"Account Information_02"};
        ExtentLogger.logInfo("Test Case execution for - VerifyVariousElementsOnAcctInfoPage. Test Case id's -->  " + Arrays.toString(tc_id));
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

	@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SANITY,CategoryType.SCP_MYACCOUNT })
	@Test(priority = 3,description = "To verify the dashboard page objects after login.")
	public void verifyLinkAccFormFieldValidation() {
		String[] tc_id = {"Account Information_03"};
        ExtentLogger.logInfo("Test Case execution for - VerifyErrorMsgForEmptyLinkAccFormSubmission. Test Case id's -->  " + Arrays.toString(tc_id));
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

	@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SANITY,CategoryType.SCP_MYACCOUNT })
	@Test(priority = 1, description = "To verify the dashboard page objects after login.")
	public void verifyEditFunctionality() {
		String[] tc_id = {"Account Information_04"};
        ExtentLogger.logInfo("Test Case execution for - VerifyEditFunctionalityForThreeDotOptions. Test Case id's -->  " + Arrays.toString(tc_id));
		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		accountInformationSteps = new AccountInformationSteps(driver);
		dashboardSteps = new DashboardSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
	accountInformationSteps.verifyEditFunctionality(softAssert);
	log.info("Test Case execution for - verifyEditfunctionality - is Completed.");

	}

	@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SANITY,CategoryType.SCP_MYACCOUNT})
	@Test(priority = 1, description = "To verify the dashboard page objects after login.")
	public void verifyUnlinkDefaultAccFunctionality() {
		String[] tc_id = {"Account Information_05"};
        ExtentLogger.logInfo("Test Case execution for - VerifyErrorMsgForUnlinkAccActivity. Test Case id's -->  " + Arrays.toString(tc_id));
		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		accountInformationSteps = new AccountInformationSteps(driver);
		dashboardSteps = new DashboardSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		accountInformationSteps.unlinkDefaultAcc(softAssert);
		log.info("Test Case execution for - VerifyDeaultaccUnlikingerrormsg - is Completed.");
	}


@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SANITY })
@Test(priority = 1, description = "To verify the dashboard page objects after login.")
public void verifyEditMailingAddressFunctionality() {
	String[] tc_id = {"Account Information_06"};
    ExtentLogger.logInfo("Test Case execution for - VerifyErrosMsgForVariousBlankMailingAddressFields. Test Case id's -->  " + Arrays.toString(tc_id));
	SoftAssert softAssert = new SoftAssert();
	LoginSteps loginSteps = new LoginSteps(driver);
	accountInformationSteps = new AccountInformationSteps(driver);
	dashboardSteps = new DashboardSteps(driver);
	DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
			Configuration.toString("password"));
accountInformationSteps.verifyErrorMsg(softAssert);
log.info("Test Case execution for - verifyEditMailingAddressFunctionality - is Completed.");

}

}

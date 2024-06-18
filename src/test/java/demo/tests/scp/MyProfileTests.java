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
import sew.ai.steps.scp.DashboardSteps;
import sew.ai.steps.scp.HomeSteps;
import sew.ai.steps.scp.LoginSteps;
import demo.steps.scp.MyProfileSteps;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

public class MyProfileTests extends TestRunner {
	private static final Logger log = LogManager.getLogger(MyProfileTests.class);

	private MyProfileSteps MyProfileSteps;

	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SANITY, CategoryType.SCP_MYACCOUNT })
	@Test(description = "To verify Negative Validation on the My Account-> Profile Page.")
	public void verifyNegativeValidationForMyProfilePage() throws SQLException, InterruptedException {
		String[] tc_id = {"My_Profile_01"};
		ExtentLogger.logInfo("Test Case execution for - verifyNegativeValidationForMyProfilePage. Test Case id's -->  " + Arrays.toString(tc_id));
		
		SoftAssert softAssert = new SoftAssert();

		// Login into The Application and Navigate to My Profile Page
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToMyProfilePage();

		// inti MyProfileSteps
		MyProfileSteps = new MyProfileSteps(driver);
		// Call verifyNegativeValidationForMyProfile Method
		MyProfileSteps.verifyNegativeValidationForMyProfile(softAssert);

		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyNegativeValidationForMyProfilePage - is Completed.");
	}

	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SMOKE, CategoryType.SCP_MYACCOUNT })
	@Test(description = "To verify that UI And Objects on the My Account-> Profile Page.")
	public void verifyMyProfileInformationPageUI() throws SQLException, InterruptedException {
		String[] tc_id = {"My_Profile_02"};
		ExtentLogger.logInfo("Test Case execution for - verifyMyProfileInformationPageUI. Test Case id's -->  " + Arrays.toString(tc_id));
		
		SoftAssert softAssert = new SoftAssert();

		// Login into The Application and Navigate to My Profile Page
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToMyProfilePage();

		// inti MyProfileSteps
		MyProfileSteps = new MyProfileSteps(driver);
		// Call verifyProfileInformationPageUI Method
		MyProfileSteps.verifyMyProfileInformationPageUI(softAssert);

		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyMyProfileInformationPageUI - is Completed.");
	}

	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SANITY, CategoryType.SCP_MYACCOUNT })
	@Test(description = "Verify that User is able to download personal information or Data.")
	public void verifyDownloadMyProfilePersonalData() throws SQLException, InterruptedException, IOException {
		String[] tc_id = {"My_Profile_03"};
		ExtentLogger.logInfo("Test Case execution for - verifyDownloadMyProfilePersonalData. Test Case id's -->  " + Arrays.toString(tc_id));
		
		SoftAssert softAssert = new SoftAssert();

		// Login into The Application and Navigate to My Profile Page
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToMyProfilePage();

		// inti MyProfileSteps
		MyProfileSteps = new MyProfileSteps(driver);
		// Call verifyDownloadMyProfileData Method
		MyProfileSteps.verifyDownloadMyProfilePersonalData(softAssert);

		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test Case execution for - verifyDownloadMyProfileData - is Completed.");
	}

}
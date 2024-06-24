package demo.tests.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import demo.steps.scp.AboutMyHomeSteps;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

public class AboutMyHomeTests extends TestRunner {
	private static final Logger log = LogManager.getLogger(AboutMyHomeTests.class);

	private AboutMyHomeSteps AboutMyHomeSteps;
	
	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SMOKE,CategoryType.SCP_MYACCOUNT })
	@Test(description = "To verify the UI And Objects on the My Account-> About My Home Page.")

	public void verifyAboutMyHomePageUI() throws SQLException, InterruptedException {
		
		String[] tc_id = {"About_My_Home_01,"};
	
		ExtentLogger.logInfo("Test Case execution for - verifyAboutMyHomePageUI. Test Case id's -->  " + Arrays.toString(tc_id));
		SoftAssert softAssert = new SoftAssert();
		// Login into The Application and Navigate to My Profile Page
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplicationWithResAcc(Configuration.toString("userNameRes"),
				Configuration.toString("passwordRes"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToAboutMyHomePage();
		// into AboutMyHomePageSteps
		AboutMyHomeSteps = new AboutMyHomeSteps(driver);
		// Call verifyAboutMyHomePageUI Method
		AboutMyHomeSteps.verifyAboutMyHomePageUI(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test case execution for - verifyAboutMyHomePageUI - is completed.");
	}
	
	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SANITY,CategoryType.SCP_MYACCOUNT })
	@Test(description = "To verify that customer is able to submit the About My Home form successfully.")

	public void verifyAboutMyHomeFormSubmission() throws SQLException, InterruptedException, IOException {
		String[] tc_id = {"About_My_Home_02"};
		ExtentLogger.logInfo("Test Case execution for - verifyAboutMyHomeFormSubmission. Test Case id's -->  " + Arrays.toString(tc_id));
		SoftAssert softAssert = new SoftAssert();
		// Login into The Application and Navigate to My Profile Page
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplicationWithResAcc(Configuration.toString("userNameRes"),
				Configuration.toString("passwordRes"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToAboutMyHomePage();
		// into AboutMyHomePageSteps
		AboutMyHomeSteps = new AboutMyHomeSteps(driver);
		// Call verifyAboutMyHomeFormSubmission Method
		AboutMyHomeSteps.verifyAboutMyHomeFormSubmission(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test case execution for - verifyAboutMyHomeFormSubmission - is completed.");

	}
	
	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SANITY,CategoryType.SCP_MYACCOUNT })
	@Test(description = "To verify that customer is unable to submit the About My Home form without filling mandatory field.")
	
	public void verifyMandatoryFieldForFormSubmission() throws SQLException, InterruptedException, IOException {
		String[] tc_id = {"About_My_Home_03"};
		ExtentLogger.logInfo("Test Case execution for - verifyMandatoryFieldForFormSubmission. Test Case id's -->  " + Arrays.toString(tc_id));
		SoftAssert softAssert = new SoftAssert();
		// Login into The Application and Navigate to My Profile Page
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplicationWithResAcc(Configuration.toString("userNameRes"),
				Configuration.toString("passwordRes"));
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToAboutMyHomePage();
		// into AboutMyHomePageSteps
		AboutMyHomeSteps = new AboutMyHomeSteps(driver);
		// Call verifyAboutMyHomeFormSubmission Method
		AboutMyHomeSteps.verifyMandatoryFieldForFormSubmission(softAssert);
		// Assert all the soft verification.
		softAssert.assertAll();
		log.info("Test case execution for - verifyMandatoryFieldForFormSubmission - is completed.");
}
}
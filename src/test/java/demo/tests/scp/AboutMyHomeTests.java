package demo.tests.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import sew.ai.config.Configuration;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.runner.TestRunner;
import sew.ai.steps.scp.DashboardSteps;
import sew.ai.steps.scp.HomeSteps;
import sew.ai.steps.scp.LoginSteps;
import demo.steps.scp.AboutMyHomeSteps;
import java.io.IOException;
import java.sql.SQLException;

public class AboutMyHomeTests extends TestRunner {
	private static final Logger log = LogManager.getLogger(AboutMyHomeTests.class);

	private AboutMyHomeSteps AboutMyHomeSteps;

	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SANITY })
	@Test(description = "To verify the UI And Objects on the My Account-> About My Home Page.")

	public void verifyAboutMyHomePageUI() throws SQLException, InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		// Login into The Application and Navigate to My Profile Page
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
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

	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SANITY })
	@Test(description = "To verify that customer is able to submit the About My Home form successfully.")

	public void verifyAboutMyHomeFormSubmission() throws SQLException, InterruptedException, IOException {
		SoftAssert softAssert = new SoftAssert();
		// Login into The Application and Navigate to My Profile Page
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
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
	
	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SANITY })
	@Test(description = "To verify that customer is unable to submit the About My Home form without filling mandatory field.")
	
	public void verifyMandatoryFieldForFormSubmission() throws SQLException, InterruptedException, IOException {
		SoftAssert softAssert = new SoftAssert();
		// Login into The Application and Navigate to My Profile Page
		LoginSteps loginSteps = new LoginSteps(driver);
		DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
				Configuration.toString("password"));
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
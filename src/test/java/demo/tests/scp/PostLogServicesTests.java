package demo.tests.scp;


import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import demo.steps.scp.PostLogServicesSteps;
import demo.steps.scp.PreLoginServiceRequestSteps;
import sew.ai.config.Configuration;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.runner.TestRunner;
import sew.ai.steps.scp.DashboardSteps;
import sew.ai.steps.scp.HomeSteps;
import sew.ai.steps.scp.LoginSteps;

public class PostLogServicesTests extends TestRunner {
	private static final Logger log = LogManager.getLogger(PostLogServicesTests.class);
	private PostLogServicesSteps postloginservices;
	private DashboardSteps dashboardSteps;
	

@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SANITY })
@Test(priority = 1, description = "To verify the service page objects after login.")
public void validatePostLogServiceReqPage() throws SQLException {
	log.info("To verify the Post Login Service page objects");
	SoftAssert softAssert = new SoftAssert();
	// Init login steps
	LoginSteps loginSteps = new LoginSteps(driver);
	DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
			Configuration.toString("password"));
	HomeSteps homeSteps = new HomeSteps(driver);
	homeSteps.navigateToPaymentLocations();
	postloginservices = new PostLogServicesSteps(driver);
	postloginservices.PostLogServiceReqObject(softAssert);
	// Assert all the soft verification.
	softAssert.assertAll();
	log.info("Test Case execution for - PostLogServiceReqObject - is Completed.");
}

@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SANITY })
@Test(priority = 1, description = "To verify the Move In page objects and create MoveIn Request.")

public void verifyPostMoveInPageObjectAndCreateReq() throws InterruptedException {
	log.info("To verify the Post Login  page objects");
	SoftAssert softAssert = new SoftAssert();
	LoginSteps loginSteps = new LoginSteps(driver);
	DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
			Configuration.toString("password"));
	HomeSteps homeSteps = new HomeSteps(driver);
	homeSteps.navigateToPaymentLocations();
	postloginservices = new PostLogServicesSteps(driver);
	postloginservices.verifyPostMoveInPageObjectAndCreateReq(softAssert);// Assert all the soft verification.
	softAssert.assertAll();
	log.info("Test Case execution for - verifyMoveInPageObjectAndCreateReq - is Completed.");

}
@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SANITY })
@Test(priority = 1, description = "To verify the Move In page objects and create MoveIn Request.")

public void verifyPostMoveInPageSavedforms() throws InterruptedException {
	log.info("To verify the Post Login  page objects");
	SoftAssert softAssert = new SoftAssert();
	LoginSteps loginSteps = new LoginSteps(driver);
	DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
			Configuration.toString("password"));
	HomeSteps homeSteps = new HomeSteps(driver);
	homeSteps.navigateToPaymentLocations();
	postloginservices = new PostLogServicesSteps(driver);
	postloginservices.verifyPostMoveInPageSavedforms(softAssert);
}
@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SANITY })
@Test(priority = 1, description = "To verify the Move In page objects and create MoveIn Request.")

public void verifyMoveInFeildsValidation() throws InterruptedException {
	log.info("verifyMoveInFeildLevelValidation");
	SoftAssert softAssert = new SoftAssert();
	LoginSteps loginSteps = new LoginSteps(driver);
	DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
			Configuration.toString("password"));
	HomeSteps homeSteps = new HomeSteps(driver);
	homeSteps.navigateToPaymentLocations();
	postloginservices = new PostLogServicesSteps(driver);
	postloginservices.verifyMoveOutFeildLevelValidation(softAssert);

	// Assert all the soft verification.
	softAssert.assertAll();
	log.info("Test Case execution for - verifyMoveInFeildLevelValidation - is Completed.");
}

@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SANITY })
@Test(priority = 1, description = "To verify the Move In page objects and create MoveIn Request.")

public void verifyPostMoveOutPageObjectAndCreateReq() throws InterruptedException {
	log.info("To verify verifyMoveOutPageObjectAndCreateReq");
	SoftAssert softAssert = new SoftAssert();
	LoginSteps loginSteps = new LoginSteps(driver);
	DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
			Configuration.toString("password"));
	HomeSteps homeSteps = new HomeSteps(driver);
	homeSteps.navigateToPaymentLocations();
	postloginservices = new PostLogServicesSteps(driver);
	postloginservices.verifyMoveOutFeildLevelValidation(softAssert);
	postloginservices.verifyMoveOutPageObjectAndCreateReq(softAssert);
	// Assert all the soft verification.
	softAssert.assertAll();
	log.info("Test Case execution for - verifyMoveOutPageObjectAndCreateReq - is Completed.");

}

@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SANITY })
@Test(priority = 1, description = "To verify the Move In page objects and create MoveIn Request.")

public void validatePostMoveOutSavedformFunctionality() throws InterruptedException {
	log.info("To verify the validateMoveOutSavedformFunctionality");
	SoftAssert softAssert = new SoftAssert();
	LoginSteps loginSteps = new LoginSteps(driver);
	DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
			Configuration.toString("password"));
	HomeSteps homeSteps = new HomeSteps(driver);
	homeSteps.navigateToPaymentLocations();
	postloginservices = new PostLogServicesSteps(driver);
	postloginservices.verifySavedForm(softAssert);
	// Assert all the soft verification.
	softAssert.assertAll();
	log.info("Test Case execution for - validateMoveOutSavedformFunctionality - is Completed.");
}

@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SANITY })
@Test(priority = 1, description = "To verify the Move In page objects and create MoveIn Request.")

public void verifyMoveOutFeildsValidation() throws InterruptedException {
	log.info("verifyMoveInFeildLevelValidation");
	SoftAssert softAssert = new SoftAssert();
	LoginSteps loginSteps = new LoginSteps(driver);
	DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
			Configuration.toString("password"));
	HomeSteps homeSteps = new HomeSteps(driver);
	homeSteps.navigateToPaymentLocations();
	postloginservices = new PostLogServicesSteps(driver);
	postloginservices.verifyMoveOutFeildLevelValidation(softAssert);

	// Assert all the soft verification.
	softAssert.assertAll();
	log.info("Test Case execution for - verifyMoveInFeildLevelValidation - is Completed.");
}

@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SANITY })
@Test(priority = 1, description = "To verify the Move In page objects and create MoveIn Request.")

public void validatePostServiceTransferPageObjectAndCreateReq() throws InterruptedException {
	log.info("To verify the Post Login  page objects");
	SoftAssert softAssert = new SoftAssert();
	LoginSteps loginSteps = new LoginSteps(driver);
	DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
			Configuration.toString("password"));
	HomeSteps homeSteps = new HomeSteps(driver);
	homeSteps.navigateToPaymentLocations();
	postloginservices = new PostLogServicesSteps(driver);
	postloginservices.PostLogServiceServiceTransferReq(softAssert);

	// Assert all the soft verification.
	softAssert.assertAll();
	log.info("Test Case execution for - verifyMoveInPageObjectAndCreateReq - is Completed.");

}

@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SANITY })
@Test(priority = 1, description = "To verify the Move In page objects and create MoveIn Request.")
public void validatePostOthersform() throws InterruptedException {
	log.info("To verify the Post Login  page objects");
	SoftAssert softAssert = new SoftAssert();
	LoginSteps loginSteps = new LoginSteps(driver);
	DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"),
			Configuration.toString("password"));
	HomeSteps homeSteps = new HomeSteps(driver);
	homeSteps.navigateToPaymentLocations();
	postloginservices = new PostLogServicesSteps(driver);
	postloginservices.PostLogServiceOtherFeildsVeification(softAssert);

	// Assert all the soft verification.
	softAssert.assertAll();
	log.info("Test Case execution for - validateFieldObjectsInOthersform - is Completed.");
}
}


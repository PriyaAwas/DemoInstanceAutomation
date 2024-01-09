package sew.test.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.runner.TestRunner;
import sew.steps.scp.LoginSewSteps;

import java.io.IOException;
import java.sql.SQLException;

public class LoginSewTests extends TestRunner {
	private static final Logger log = LogManager.getLogger(LoginSewTests.class);
	private LoginSewSteps loginSewSteps;
	String accountType = null;

	@FrameworkAnnotations(author = { "Priya" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "To Perform the Login and performed payment")
	public void makePayment() throws SQLException, IOException, InterruptedException {
		log.info("Test to login in application and perform Payment");
		SoftAssert softAssert = new SoftAssert();
		loginSewSteps = new LoginSewSteps(driver);
		loginSewSteps.makePayment();
		softAssert.assertAll();
	}
	
	@FrameworkAnnotations(author = { "Priya" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "Verify Usage OverView")
	public void usageOverview() throws SQLException, IOException, InterruptedException {
		log.info("Test to login in application and check usage");
		SoftAssert softAssert = new SoftAssert();
		loginSewSteps = new LoginSewSteps(driver);
		loginSewSteps.usageOverview();
		
		softAssert.assertAll();
	}
	@FrameworkAnnotations(author = { "Priya" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "Verify the Billing Query is submitted ")
	public void BillingQuery() throws SQLException, IOException, InterruptedException {
		log.info("Test to login in application and check usage");
		SoftAssert softAssert = new SoftAssert();
		loginSewSteps = new LoginSewSteps(driver);
		loginSewSteps.usageOverview();
		
		softAssert.assertAll();
	}
}

package alectra.tests.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.runner.TestRunner;
import alectra.steps.scp.LoginAlectraSteps;

import java.io.IOException;
import java.sql.SQLException;

public class LoginAlectraTests extends TestRunner {
	private static final Logger log = LogManager.getLogger(LoginAlectraTests.class);
	private LoginAlectraSteps loginAlectraSteps;
	String accountType = null;

	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "To Perform Login")
	public void verifyValidLogin() throws SQLException, IOException, InterruptedException {
		log.info("Test to login in application");
		SoftAssert softAssert = new SoftAssert();
		loginAlectraSteps = new LoginAlectraSteps(driver);
		loginAlectraSteps.validLogin();
		softAssert.assertAll();
	}
	
}

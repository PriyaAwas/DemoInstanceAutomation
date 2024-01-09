package sew.ai.tests.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.runner.TestRunner;
import sew.ai.steps.scp.RegistrationUGISteps;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class RegistrationUGITests extends TestRunner {
	private static final Logger log = LogManager.getLogger(RegistrationUGITests.class);
	private RegistrationUGISteps registrationSteps;
	HashMap<String, String> customerData = new HashMap<>();
	String accountType = null;

	@FrameworkAnnotations(author = { "Priya" }, category = { CategoryType.SANITY, CategoryType.SCP_REGISTRATION })
	@Test(priority = 1, description = "To Perform the Registration ")
	public void fillRegFormWithPaperLessBilling() throws SQLException, IOException {
		log.info("Test Case execution for - verifyCustomerRegistrationPage - is Initiated");
		SoftAssert softAssert = new SoftAssert();
		registrationSteps = new RegistrationUGISteps(driver);
		registrationSteps.fillRegFormWithPaperLessBilling();
		softAssert.assertAll();
	}
}

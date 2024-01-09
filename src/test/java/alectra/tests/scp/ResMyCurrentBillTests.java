package alectra.tests.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import alectra.steps.scp.LoginAlectraSteps;
import alectra.steps.scp.ResMyCurrentBillSteps;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.runner.TestRunner;

import java.io.IOException;
import java.sql.SQLException;

public class ResMyCurrentBillTests extends TestRunner {
	private static final Logger log = LogManager.getLogger(ResMyCurrentBillTests.class);
	private LoginAlectraSteps loginAlectraSteps;
	private ResMyCurrentBillSteps myCurrentBillSteps;
	String accountType = null;

	
	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "Verify My Current Bill Page")
	public void verifyMyCurrentBill() throws SQLException, IOException, InterruptedException {
		log.info("Test to login in application and check my current bill");
		SoftAssert softAssert = new SoftAssert();
		loginAlectraSteps = new LoginAlectraSteps(driver);
		myCurrentBillSteps = new ResMyCurrentBillSteps(driver);
		loginAlectraSteps.validLogin();
		myCurrentBillSteps.MyCurrentBill(softAssert);
		softAssert.assertAll();
	}
}

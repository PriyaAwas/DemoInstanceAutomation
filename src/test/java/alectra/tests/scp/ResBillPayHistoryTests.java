package alectra.tests.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import alectra.steps.scp.LoginAlectraSteps;
import alectra.steps.scp.ResBillPayHistorySteps;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.runner.TestRunner;
import sew.steps.scp.LoginSewSteps;

import java.io.IOException;
import java.sql.SQLException;

public class ResBillPayHistoryTests extends TestRunner {
	private static final Logger log = LogManager.getLogger(ResBillPayHistoryTests.class);
	private LoginAlectraSteps loginAlectraSteps;
	private ResBillPayHistorySteps resBillPayHistorySteps;
	String accountType = null;

	
	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "Verify Billing and Payment History Page")
	public void verifyBillAndPaymentHistory() throws SQLException, IOException, InterruptedException {
		log.info("Test to login in application and check billing and payment history");
		SoftAssert softAssert = new SoftAssert();
		loginAlectraSteps = new LoginAlectraSteps(driver);
		resBillPayHistorySteps = new ResBillPayHistorySteps(driver);
		loginAlectraSteps.validLogin();
		resBillPayHistorySteps.BillAndPayHistory(softAssert);
		softAssert.assertAll();
	}
}

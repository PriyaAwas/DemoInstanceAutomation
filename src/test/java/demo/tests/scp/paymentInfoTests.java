package demo.tests.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.runner.TestRunner;
import alectra.steps.scp.LoginAlectraSteps;
import alectra.tests.scp.LoginAlectraTests;
import demo.steps.scp.paymentInfoSteps;

import java.io.IOException;
import java.sql.SQLException;

public class paymentInfoTests extends TestRunner {
	
	private static final Logger log = LogManager.getLogger(paymentInfoTests.class);
	private paymentInfoSteps paymentInfoSteps;
	String accountType = null;
	
	@FrameworkAnnotations(author = { "Priya" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "To perform sanity on Payment Information with credit card")
	public void accPaymentInformation() throws SQLException, IOException, InterruptedException {
		log.info("To perform sanity on Payment Information Submodule");
		SoftAssert softAssert = new SoftAssert();
		paymentInfoSteps = new paymentInfoSteps(driver);
		paymentInfoSteps.accPaymentInformation();
		softAssert.assertAll();
	}
	
	@FrameworkAnnotations(author = { "Priya" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "To perform sanity on Payment Information with credit card")
	public void makePaymentwithExistingCreditCard() throws SQLException, IOException, InterruptedException {
		log.info("To perform payement with existing CC ");
		SoftAssert softAssert = new SoftAssert();
		paymentInfoSteps = new paymentInfoSteps(driver);
		paymentInfoSteps.makePaymentwithExistingCreditCard();
		softAssert.assertAll();
	}

}

package alectra.tests.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import alectra.steps.scp.LoginAlectraSteps;
import alectra.steps.scp.ResUsageOverviewSteps;
import alectra.steps.scp.ToNeethuSteps;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.runner.TestRunner;

import java.io.IOException;
import java.sql.SQLException;

public class ToNeethuTests extends TestRunner {
	private static final Logger log = LogManager.getLogger(ToNeethuTests.class);
	private LoginAlectraSteps loginAlectraSteps;
	private ToNeethuSteps toNeethuSteps;
	String accountType = null;

	
	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "Verify Green Button Connect Page")
	public void verifyGreenButtonConnectPage() throws SQLException, IOException, InterruptedException {
		log.info("Test to login in application and check green button connect page");
		SoftAssert softAssert = new SoftAssert();
		loginAlectraSteps = new LoginAlectraSteps(driver);
		toNeethuSteps = new ToNeethuSteps(driver);
		loginAlectraSteps.validLogin();
		toNeethuSteps.GreenButtonConnectPage(softAssert);
		softAssert.assertAll();
	}
	
	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "Verify Water Usage Overview Page")
	public void verifySettingDefaultCard() throws SQLException, IOException, InterruptedException {
		log.info("Test to login in application and set defaut card");
		SoftAssert softAssert = new SoftAssert();
		loginAlectraSteps = new LoginAlectraSteps(driver);
		toNeethuSteps = new ToNeethuSteps(driver);
		loginAlectraSteps.validLogin();
		toNeethuSteps.SettingDefaultCard(softAssert);
		softAssert.assertAll();
	}
	
}

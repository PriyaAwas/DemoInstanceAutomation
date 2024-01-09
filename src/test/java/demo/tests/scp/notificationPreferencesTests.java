package demo.tests.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.runner.TestRunner;
import demo.steps.scp.notificationPreferencesSteps;

import java.io.IOException;
import java.sql.SQLException;

public class notificationPreferencesTests extends TestRunner {
	
	private static final Logger log = LogManager.getLogger(notificationPreferencesTests.class);
	private notificationPreferencesSteps notificationPreferencesSteps;
	String accountType = null;
	
	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "To perform sanity testing on Notification Preference Sub-Module")
	public void notifiPrefModule() throws SQLException, IOException, InterruptedException {
		log.info("To perform sanity testing on Notification Preference Sub-Module");
		SoftAssert softAssert = new SoftAssert();
		notificationPreferencesSteps = new notificationPreferencesSteps(driver);
		notificationPreferencesSteps.notifiprefmodule();
		softAssert.assertAll();
	}

}

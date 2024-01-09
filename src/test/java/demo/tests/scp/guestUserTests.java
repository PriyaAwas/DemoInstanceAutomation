package demo.tests.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.runner.TestRunner;
import demo.steps.scp.guestUserSteps;

import java.io.IOException;
import java.sql.SQLException;

public class guestUserTests extends TestRunner {
	
	private static final Logger log = LogManager.getLogger(guestUserTests.class);
	private guestUserSteps guestUserSteps;
	String accountType = null;
	
	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "To perform sanity testing on Guest User Sub-Module")
	public void guestUserModule() throws SQLException, IOException, InterruptedException {
		log.info("To perform sanity testing on Guest User Sub-Module");
		SoftAssert softAssert = new SoftAssert();
		guestUserSteps = new guestUserSteps(driver);
		guestUserSteps.guestusermodule();
		softAssert.assertAll();
	}

}

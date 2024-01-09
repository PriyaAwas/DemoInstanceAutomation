package demo.tests.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.runner.TestRunner;
import demo.steps.scp.loginSteps;

import java.io.IOException;
import java.sql.SQLException;

public class loginTests extends TestRunner {
	
	private static final Logger log = LogManager.getLogger(loginTests.class);
	private loginSteps loginSteps;
	String accountType = null;
	
	@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "Login with valid Username and Password")
	public void MyAlectra() throws SQLException, IOException, InterruptedException {
		log.info("Validate My Alectra Page Functionality ");
		SoftAssert softAssert = new SoftAssert();
		loginSteps=new loginSteps(driver);
		loginSteps.myAlectra();				
		softAssert.assertAll();
	}

@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SANITY, CategoryType.SCP_MYACCOUNT })
@Test(priority = 1, description = "Unlink and link account in My Account Information")

public void linkandunlinkaccount() throws SQLException, IOException, InterruptedException {
	log.info("Unlink and link account in My Account Information");
	SoftAssert softAssert = new SoftAssert();
	loginSteps=new loginSteps(driver);
	loginSteps.linkandunlinkaccount();				
	softAssert.assertAll();
	}
}

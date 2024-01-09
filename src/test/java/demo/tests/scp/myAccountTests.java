package demo.tests.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.runner.TestRunner;
import demo.steps.scp.myAccountSteps;

import java.io.IOException;
import java.sql.SQLException;

public class myAccountTests extends TestRunner {
	
	private static final Logger log = LogManager.getLogger(myAccountTests.class);
	private myAccountSteps myAccountSteps;
	String accountType = null;
	
	@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "To perform sanity testing on Guest User Sub-Module")
	public void guestUserModule() throws SQLException, IOException, InterruptedException {
		log.info("To perform sanity testing on Guest User Sub-Module");
		SoftAssert softAssert = new SoftAssert();
		myAccountSteps = new myAccountSteps(driver);
		myAccountSteps.guestusermodule();
		softAssert.assertAll();
	}
	
	@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGIN })
	@Test(priority = 2, description = "Login with valid Username and Password")
	public void MyAlectra() throws SQLException, IOException, InterruptedException {
		log.info("To perform sanity testing on My Alectra Sub-Module");
		SoftAssert softAssert = new SoftAssert();
		myAccountSteps=new myAccountSteps(driver);
		myAccountSteps.myAlectra();				
		softAssert.assertAll();
	}

@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SANITY, CategoryType.SCP_MYACCOUNT })
@Test(priority = 3, description = "To perform sanity testing on Unlink and link account in My Account Information")

public void linkandunlinkaccount() throws SQLException, IOException, InterruptedException {
	log.info("To perform sanity testing on My Alectra Sub-Module");
	SoftAssert softAssert = new SoftAssert();
	myAccountSteps=new myAccountSteps(driver);
	myAccountSteps.linkandunlinkaccount();				
	softAssert.assertAll();
	}

@FrameworkAnnotations(author = { "Neethu" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGIN })
@Test(priority = 4, description = "To perform sanity testing on Notification Preference Sub-Module")
public void notifiPrefModule() throws SQLException, IOException, InterruptedException {
	log.info("To perform sanity testing on Notification Preference Sub-Module");
	SoftAssert softAssert = new SoftAssert();
	myAccountSteps = new myAccountSteps(driver);
	myAccountSteps.notifiprefmodule();
	softAssert.assertAll();
}

@FrameworkAnnotations(author = { "Kavya" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGIN })
@Test(priority = 5, description = "To perform sanity on Payment Information Submodule")
public void accPaymentInformation() throws SQLException, IOException, InterruptedException {
	log.info("To perform sanity on Payment Information Submodule");
	SoftAssert softAssert = new SoftAssert();
	myAccountSteps = new myAccountSteps(driver);
	myAccountSteps.accPaymentInformation();
	softAssert.assertAll();
}

@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGIN })
@Test(priority = 6, description = "Verify Green Button Connect Page")
public void verifyGreenButtonConnectPage() throws SQLException, IOException, InterruptedException {
	log.info("To perform sanity on green button connect page");
	SoftAssert softAssert = new SoftAssert();
	myAccountSteps = new myAccountSteps(driver);
	myAccountSteps.GreenButtonConnectPage(softAssert);
	softAssert.assertAll();
}

}

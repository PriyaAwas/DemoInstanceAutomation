package alectra.tests.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import alectra.steps.scp.LoginAlectraSteps;
import alectra.steps.scp.ResUsageOverviewSteps;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.runner.TestRunner;

import java.io.IOException;
import java.sql.SQLException;

public class ResUsageOverviewTests extends TestRunner {
	private static final Logger log = LogManager.getLogger(ResUsageOverviewTests.class);
	private LoginAlectraSteps loginAlectraSteps;
	private ResUsageOverviewSteps resUsageOverviewSteps;
	String accountType = null;

	
	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "Verify Electric Usage Overview Page")
	public void verifyElectricUsageOverview() throws SQLException, IOException, InterruptedException {
		log.info("Test to login in application and check billing and payment history");
		SoftAssert softAssert = new SoftAssert();
		loginAlectraSteps = new LoginAlectraSteps(driver);
		resUsageOverviewSteps = new ResUsageOverviewSteps(driver);
		loginAlectraSteps.validLogin();
		resUsageOverviewSteps.ElectricUsageOverview(softAssert);
		softAssert.assertAll();
	}
	
	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "Verify Water Usage Overview Page")
	public void verifyWaterUsageOverview() throws SQLException, IOException, InterruptedException {
		log.info("Test to login in application and check billing and payment history");
		SoftAssert softAssert = new SoftAssert();
		loginAlectraSteps = new LoginAlectraSteps(driver);
		resUsageOverviewSteps = new ResUsageOverviewSteps(driver);
		loginAlectraSteps.validLogin();
		resUsageOverviewSteps.WaterUsageOverview(softAssert);
		softAssert.assertAll();
	}
	
	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "Verify Der Usage Overview Page")
	public void verifyDerUsageOverview() throws SQLException, IOException, InterruptedException {
		log.info("Test to login in application and check billing and payment history");
		SoftAssert softAssert = new SoftAssert();
		loginAlectraSteps = new LoginAlectraSteps(driver);
		resUsageOverviewSteps = new ResUsageOverviewSteps(driver);
		loginAlectraSteps.validLogin();
		resUsageOverviewSteps.DerUsageOverview(softAssert);
		softAssert.assertAll();
	}
	
	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "Verify Energy Rates Overview Page")
	public void verifyEnergyRatesUsageOverview() throws SQLException, IOException, InterruptedException {
		log.info("Test to login in application and check billing and payment history");
		SoftAssert softAssert = new SoftAssert();
		loginAlectraSteps = new LoginAlectraSteps(driver);
		resUsageOverviewSteps = new ResUsageOverviewSteps(driver);
		loginAlectraSteps.validLogin();
		resUsageOverviewSteps.EnergyRatesUsageOverview(softAssert);
		softAssert.assertAll();
	}
}

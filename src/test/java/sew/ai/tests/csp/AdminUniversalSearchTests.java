package sew.ai.tests.csp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import sew.ai.config.Configuration;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.helpers.testrail.TestRail;
import sew.ai.runner.TestRunner;
import sew.ai.steps.csp.AdminCustomerSteps;
import sew.ai.steps.csp.AdminLoginSteps;
import sew.ai.steps.csp.AdminUniversalSearchSteps;

public class AdminUniversalSearchTests extends TestRunner{

	private static final Logger log = LogManager.getLogger(AdminUniversalSearchTests.class);
	private AdminLoginSteps adminLoginSteps;
	private AdminUniversalSearchSteps adminUniversalSearchSteps;
	
	@TestRail(testCaseId = { 84042, 84043, 84045, 84046, 84047, 84048, 84049, 84050, 84052, 84053 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C84042,C84043,C84045,C84046,C84047,C84048,C84049,C84050,C84052,C84053-verifyUniversalSearchField")
    public void verifyUniversalSearchField() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminUniversalSearchSteps =  new AdminUniversalSearchSteps(driver);
        adminUniversalSearchSteps.verifyUniversalSearchField();
        
        log.info("Test Case execution for - verifyUniversalSearchField - is Completed.");
    }
	
	@TestRail(testCaseId = { 84044 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C84044-verifyBlankSearch")
    public void verifyBlankSearch() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminUniversalSearchSteps =  new AdminUniversalSearchSteps(driver);
        adminUniversalSearchSteps.verifyBlankSearch();
        
        log.info("Test Case execution for - verifyBlankSearch - is Completed.");
    }
	
	@TestRail(testCaseId = { 84054, 84055 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C84054, C84055-verifyHyperLinkCustomerName")
    public void verifyHyperLinkCustomerName() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminUniversalSearchSteps =  new AdminUniversalSearchSteps(driver);
        adminUniversalSearchSteps.verifyHyperLinkCustomerName();
        
        log.info("Test Case execution for - verifyHyperLinkCustomerName - is Completed.");
    }
	
	@TestRail(testCaseId = { 102745, 102746, 102747, 102748, 102749 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "verfying the Universal search fileds and report info after clicking submit button, without entering the data")
    public void verifyUniversalSearchFieldsAndReportGridInfo() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminUniversalSearchSteps =  new AdminUniversalSearchSteps(driver);
        adminUniversalSearchSteps.verifyUniversalSearchFieldsAndReportGridInfo();
        
        log.info("Test Case execution for - verifyUniversalSearchFieldsAndReportGridInfo - is Completed.");
    }
	
	
	
	
	
	

}

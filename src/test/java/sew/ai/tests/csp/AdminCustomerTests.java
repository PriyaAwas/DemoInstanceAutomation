package sew.ai.tests.csp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import sew.ai.config.Configuration;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.helpers.testrail.TestRail;
import sew.ai.pageObjects.csp.AdminCustomerPage;
import sew.ai.runner.TestRunner;
import sew.ai.steps.csp.AdminCustomerSteps;
import sew.ai.steps.csp.AdminLoginSteps;
import sew.ai.steps.csp.AdminUniversalSearchSteps;

public class AdminCustomerTests extends TestRunner{
	
	private static final Logger log = LogManager.getLogger(AdminCustomerTests.class);
    private AdminCustomerSteps adminCsrSteps;
    private AdminLoginSteps adminLoginSteps;

    @TestRail(testCaseId = {72561, 72562})
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C37099,C37103- Verify the verifyCustomerDetailsPopUpTabs 360 view ")
    public void verifyCustomerDetailsPopUpTabs() {
        log.info("To verify the tests with the below test case id's: " +
                "C72381");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyCustomerDetailsPopUpTabs();
        
        log.info("Test Case execution for - verifyCustomerDetailsPopUpTabs - is Completed.");
    }
    
    @TestRail(testCaseId = {72565, 101429})
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C72565, C101429 Verify the Profile Tab Customer information for Customer in CSR")
    public void verifyCustomerInformation360View() {
        log.info("To verify the tests with the below test case id's: " + "C72565, C101429");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyCustomerInformation360View();
        
        log.info("Test Case execution for - verifyCustomerDetailsPopUpTabs - is Completed.");
    }
    
    
    @TestRail(testCaseId = {106832})
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C106832  Verify the Profile Tab Customer information for different BillType Statues in CSR")
    public void verifyBillTypeStatusesDetailsView() {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyBillTypeStatusesDetailsView();
        
        log.info("Test Case execution for - verifyCustomerDetailsPopUpTabs - is Completed.");
    }
    
    @TestRail(testCaseId = {107377, 107378, 107379, 107380, 107381, 107386, 107387, 107389, 101436})
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C107377, C107378, C107379, C107380, C107381, C107386, C107387, C107389, C101436 Verify the Billing Tab in CSR in CSR")
    public void verifyBilling360View() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyBilling360View();
        
        log.info("Test Case execution for - verifyBilling360View - is Completed.");
    }
    
    @TestRail(testCaseId = {107382, 107383, 107384, 107385, 107388})
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C107382, C107383, C107384, C107385, C107388 Payment Tab")
    public void verifyPayment360View() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyPayment360View();
        
        log.info("Test Case execution for - verifyPayment360View - is Completed.");
    }
    
    @TestRail(testCaseId = {72517, 72518, 72519, 72520})
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "72517, 72518, 72519, 72520 -Verify user redirect to the User in New tab from SCM Tab")
    public void verifyLoginToCustomerPortal() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyLoginToCustomerPortal();
        
        log.info("Test Case execution for - verifyLoginToCustomerPortal - is Completed.");
    }
    
    
    @TestRail(testCaseId = {119698, 120048})
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "72517, 72518, 72519, 72520 -Verify user redirect to the User in New tab from SCM Tab")
    public void verifyMarketingPreferences() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyMarketingPreferences();
        
        log.info("Test Case execution for - verifyMarketingPreferences - is Completed.");
    }
    
    @TestRail(testCaseId = {114269})
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "verifyUserDetails360View")
    public void verifyUserDetails360View() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyUserDetails360View();
        
        log.info("Test Case execution for - verifyUserDetails360View - is Completed.");
    }
    
    @TestRail(testCaseId = {119748})
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "verifyLastLogin")
    public void verifyLastLogin() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyLastLogin();
        
        log.info("Test Case execution for - verifyLastLogin - is Completed.");
    }

    @TestRail(testCaseId = {121806})
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "verifyLastLogin")
    public void verifyBillTypeDetails360View() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyBillTypeDetails360View();
        
        log.info("Test Case execution for - verifyBillTypeDetails360View - is Completed.");
    }
    
    @TestRail(testCaseId = {72573})
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C72573 Verify the Efficiency Tab Details for Customer in CSR")
    public void verifyEfficiency360View() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyEfficiency360View();
        
        log.info("Test Case execution for - verifyEfficiency360View - is Completed.");
    }
    
    @TestRail(testCaseId = {72639, 72640, 72641, 72642})
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C37104-verifyGuest360View")
    public void verifyGuest360View() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyGuest360View();
        
        log.info("Test Case execution for - verifyGuest360View - is Completed.");
    }
    
    @TestRail(testCaseId = {72629, 72630, 72631, 72632})
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "72629, 72630, 72631, 72632 -verifyOutage360View")
    public void verifyOutage360View() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyOutage360View();
        
        log.info("Test Case execution for - verifyOutage360View - is Completed.");
    }
    
    @TestRail(testCaseId = {72568})
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C72568  Verify the Service Plans for Customer in C360 Profile Tab in CSR")
    public void verifyServicePlanProfileView() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyServicePlanProfileView();
        
        log.info("Test Case execution for - verifyOutage360View - is Completed.");
    }
    
    @TestRail(testCaseId = {107373})
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C107373 Verify the Profile Tab Customer information for Postpaid User in CSR")
    public void verifyPostpaidUserDetailsView() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyPostpaidUserDetailsView();
        
        log.info("Test Case execution for - verifyPostpaidUserDetailsView - is Completed.");
    }
    
    @TestRail(testCaseId = {107374})
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C107374 Verify the Profile Tab Customer information for Prepaid User in CSR")
    public void verifyPrepaidUserDetailsView() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyPrepaidUserDetailsView();
        
        log.info("Test Case execution for - verifyPrepaidUserDetailsView - is Completed.");
    }
    
    @TestRail(testCaseId = { 72498, 72499, 78681 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C72499,C72498,C78681,C72512-Verify Advance Search filter fields in the Advance")
    public void verifyAdvanceSearchFilterServiceAccountTab() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyAdvanceSearchFilterServiceAccountTab();
        
        log.info("Test Case execution for - verifyAdvanceSearchFilterServiceAccountTab - is Completed.");
    }
    
    @TestRail(testCaseId = { 72500 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C72500-Verify Advance Search filter fields in the Advance search pop up for Customer Tab")
    public void verifyAdvanceSearchFilterSCMCustomerTab() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyAdvanceSearchFilterSCMCustomerTab();
        
        log.info("Test Case execution for - verifyAdvanceSearchFilterSCMCustomerTab - is Completed.");
    }
    
    @TestRail(testCaseId = { 72501 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C72501-Verify Advance Search filter fields in the Advance search pop up for SCM Users Tab")
    public void verifyAdvanceSearchFilterSCMUserTab() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyAdvanceSearchFilterSCMUserTab();
        
        log.info("Test Case execution for - verifyAdvanceSearchFilterSCMUserTab - is Completed.");
    }
    
    @TestRail(testCaseId = { 72502, 72503, 72505, 72508, 72510 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C72502,C72503,C72505,C72508,C72510 - verifyActionsAdvSearchFilterServiceAcc")
    public void verifyActionsAdvSearchFilterServiceAcc() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyActionsAdvSearchFilterServiceAcc();
        
        log.info("Test Case execution for - verifyActionsAdvSearchFilterServiceAcc - is Completed.");
    }
    
    @TestRail(testCaseId = { 72504, 72506 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C72506, C72504, C72507, C72509 - verifyActionAdvSearchFilterForCustomerTab")
    public void verifyActionAdvSearchFilterForCustomerTab() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyActionAdvSearchFilterForCustomerTab();
        
        log.info("Test Case execution for - verifyActionAdvSearchFilterForCustomerTab - is Completed.");
    }
    
    @TestRail(testCaseId = { 72507 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C72507-Verify Advance Search filter results in CSR workbench for customer Tab with Account Type")
    public void verifyAdvanceFilterCustomerWithAccountType() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyAdvanceFilterCustomerWithAccountType();
        
        log.info("Test Case execution for - verifyAdvanceFilterCustomerWithAccountType - is Completed.");
    }
    
    @TestRail(testCaseId = { 133397, 133401 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C133397,C133401 - Verify registered Service Account Details as N/A in C360 Page and in SCP with DB")
    public void verifyRegisteredServiceAccountDetailsC360AndSCPWithDB() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyRegisteredServiceAccountDetailsC360AndSCPWithDB();
        
        log.info("Test Case execution for - verifyRegisteredServiceAccountDetailsC360AndSCPWithDB - is Completed.");
    }
    
    @TestRail(testCaseId = { 72509 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C72509-Verify Advance Search filter results in CSR workbench for customer Tab with Zip code and EmailID")
    public void verifyAdvanceFilterCustomerWithZipAndEmail() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyAdvanceFilterCustomerWithZipAndEmail();
        
        log.info("Test Case execution for - verifyAdvanceFilterCustomerWithZipAndEmail - is Completed.");
    }
    
    @TestRail(testCaseId = { 72511, 78679 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C72511, C78679 - verifyActionAdvSearchFilterForUsersTab")
    public void verifyActionAdvSearchFilterForUsersTab() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyActionAdvSearchFilterForUsersTab();
        
        log.info("Test Case execution for - verifyActionAdvSearchFilterForUsersTab - is Completed.");
    }
    
    @TestRail(testCaseId = { 72512, 72513 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C34025-Verify Advance Search filter results in CSR workbench for Service Account Tab with Service Account Number")
    public void verifyAdvFilterServiceAccTab() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyAdvFilterServiceAccTab();
        
        log.info("Test Case execution for - verifyAdvFilterServiceAccTab - is Completed.");
    }
    
    @TestRail(testCaseId = { 72514 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C72514-Verify Reset button on the Advance Filter Customer Tab")
    public void verifyResetButtonAdvanceSearchCustomer() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyResetButtonAdvanceSearchCustomer();
        
        log.info("Test Case execution for - verifyResetButtonAdvanceSearchCustomer - is Completed.");
    }
    
    @TestRail(testCaseId = { 72515 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C72515-Verify Reset button on the Advance Filter Service Account Tab")
    public void verifyResetButtonAdvanceSearchServiceAccountTab() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyResetButtonAdvanceSearchServiceAccountTab();
        
        log.info("Test Case execution for - verifyResetButtonAdvanceSearchServiceAccountTab - is Completed.");
    }
    
    @TestRail(testCaseId = { 72516 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C72516-Verify Reset button on the Advance Filter User Tab")
    public void verifyResetButtonAdvanceSearchUser() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyResetButtonAdvanceSearchUser();
        
        log.info("Test Case execution for - verifyResetButtonAdvanceSearchUser - is Completed.");
    }
    
    @TestRail(testCaseId = { 133398, 133399 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C133398,C133398-Verify Registered and Unregistered search results from Advance Search with the DB.")
    public void verifyCountRegUnRegAccountsAdvSearchWithDB() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyCountRegUnRegAccountsAdvSearchWithDB();
        
        log.info("Test Case execution for - verifyCountRegUnRegAccountsAdvSearchWithDB - is Completed.");
    }
    
    @TestRail(testCaseId = { 133400 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C133400 - Verify Unregistered Service Account Details as N/A in C360 Page and in SCP as blank")
    public void verifyUnRegisteredServiceAccountDetailsC360AndSCP() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyUnRegisteredServiceAccountDetailsC360AndSCP();
        
        log.info("Test Case execution for - verifyUnRegisteredServiceAccountDetailsC360AndSCP - is Completed.");
    }
    
    @TestRail(testCaseId = { 72488, 72489, 72490, 72491, 72492, 72493, 72494, 72495 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C72488, C72489, C72490, C72491, C72492, C72493, C72494, C72495-Search by Entering Service Account Number, Zipcode in Service Account Tab")
    public void verifySearchServiceAccountTab() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifySearchServiceAccountTab();
        
        log.info("Test Case execution for - verifySearchServiceAccountTab - is Completed.");
    }
    
    @TestRail(testCaseId = { 92048, 92049, 92050 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C92048, C92049-Verify the Add Customer Link in CSR")
    public void verifyAddCustomerLinkCSR() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyAddCustomerLinkCSR();
        
        log.info("Test Case execution for - verifyAddCustomerLinkCSR - is Completed.");
    }
    
    @TestRail(testCaseId = { 72569 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C72569 Verify the Property Tab Details for Customer in CSR")
    public void verifyPropertyTab360View() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyPropertyTab360View();
        
        log.info("Test Case execution for - verifyPropertyTab360View - is Completed.");
    }
    
    @TestRail(testCaseId = { 72449 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C72449 -Verify the UI for CSR workbench")
    public void verifyCSRworkbenchUI() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyCSRworkbenchUI();
        
        log.info("Test Case execution for - verifyCSRworkbenchUI - is Completed.");
    }
    
    @TestRail(testCaseId = { 72459,72460,72461,110576 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C72459,C72460,C72461,C72462,C72464,C72466-Verify the UI for CSR workbench")
    public void verifyCSRTabFunctionality() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyCSRTabFunctionality();
        
        log.info("Test Case execution for - verifyCSRTabFunctionality - is Completed.");
    }
    
    @TestRail(testCaseId = { 72462 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C72462 - Verify the Sorting for CSR workbench")
    public void verifyCSRTabSortingFeature() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyCSRTabSortingFeature();
        
        log.info("Test Case execution for - verifyCSRTabSortingFeature - is Completed.");
    }
    
    @TestRail(testCaseId = { 72464,72466 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C72464,C72466-Verify the Pagination for CSR workbench")
    public void verifyCSRTabPaginationFeature() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyCSRTabPaginationFeature();
        
        log.info("Test Case execution for - verifyCSRTabPaginationFeature - is Completed.");
    }
    
    @TestRail(testCaseId = { 91423, 80063, 80060 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C91423-Verify the Export Report excel for Customer from CSR Workbench Customer Tab")
    public void verifyExportCustomerFile() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyExportCustomerFile();
        
        log.info("Test Case execution for - verifyExportCustomerFile - is Completed.");
    }
    
    @TestRail(testCaseId = { 72481, 72484, 80059 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C72484 , C72482, C72481-Verify the Export Report excel for Customer from CSR Workbench Service Account Tab")
    public void verifyExportCustomerFileServiceTab() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyExportCustomerFileServiceTab();
        
        log.info("Test Case execution for - verifyExportCustomerFileServiceTab - is Completed.");
    }
    
    @TestRail(testCaseId = { 80061 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C33935-Verify the Export Report excel for Customer from CSR Workbench SCM Tab")
    public void verifyExportSCMUserFile() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyExportSCMUserFile();
        
        log.info("Test Case execution for - verifyExportSCMUserFile - is Completed.");
    }
    
    @TestRail(testCaseId = { 72483, 80064, 92282, 92283 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C72483,C80065,80064-Verify Send Notification Functionality")
    public void verifySendNotification() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifySendNotification();
        
        log.info("Test Case execution for - verifySendNotification - is Completed.");
    }
    
    @TestRail(testCaseId = { 72538 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C72538 - Verify the Sub Tabs for Registered User Tab")
    public void verifyRegisteredUsertabsAndCount() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyRegisteredUsertabsAndCount();
        
        log.info("Test Case execution for - verifyRegisteredUsertabsAndCount - is Completed.");
    }
    
    @TestRail(testCaseId = { 72539 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C72539 - Verify the Fields under the Registered User tab for SCM user")
    public void verifyRegisterUserGridUIFields() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyRegisterUserGridUIFields();
        
        log.info("Test Case execution for - verifyRegisterUserGridUIFields - is Completed.");
    }
    
    @TestRail(testCaseId = { 72543, 72470 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "Verify linked accounts in SCM user tab")
    public void verifyUserLinkedAccount() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyUserLinkedAccount();
        
        log.info("Test Case execution for - verifyUserLinkedAccount - is Completed.");
    }
    
    @TestRail(testCaseId = { 72455 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C72455 - Verify the Customer Display Capacity from page size dropdown")
    public void verifyUserPageSize() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyUserPageSize();
        
        log.info("Test Case execution for - verifyUserPageSize - is Completed.");
    }
    
    @TestRail(testCaseId = { 73855 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "Verify status along with its color representation and DB values")
    public void verifyUserStatusDBValueAndColor() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyUserStatusDBValueAndColor();
        
        log.info("Test Case execution for - verifyUserStatusDBValueAndColor - is Completed.");
    }
    
    @TestRail(testCaseId = { 99938,99939 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C99938,C99939 - Verify the fields in the Active/Inactive Users Tab")
    public void verifyFieldsCSRActiveInactiveUsersTab() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyFieldsCSRActiveInactiveUsersTab();
        
        log.info("Test Case execution for - verifyFieldsCSRActiveInactiveUsersTab - is Completed.");
    }
    
    @TestRail(testCaseId = { 72451,72541,99940,99941 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "Verify Lock/UnLock user in CSR workbench for SCM user")
    public void verifyLockUnLockUser() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyLockUnLockUser();
        
        log.info("Test Case execution for - verifyLockUnLockUser - is Completed.");
    }
    
    @TestRail(testCaseId = { 107206 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C107206 - Verify Sorting of Data in Users Tab")
    public void verifyUsersDataGridSorting() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyUsersDataGridSorting();
        
        log.info("Test Case execution for - verifyUsersDataGridSorting - is Completed.");
    }
    
    @TestRail(testCaseId = { 72540, 80117, 80119, 80120, 80128 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "Verify the edit customer functionality")
    public void verifySCMUserEditRecord() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifySCMUserEditRecord();
        
        log.info("Test Case execution for - verifySCMUserEditRecord - is Completed.");
    }
    
    @TestRail(testCaseId = { 80121, 80123, 80124, 80127, 102717, 119701, 119702 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C80121, C80123, C80124, C80127, C102717-Verify Edit User Tab for Customer edit")
    public void verifyEditCustomer() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyEditCustomer();
        
        log.info("Test Case execution for - verifyEditCustomer - is Completed.");
    }
    
    @TestRail(testCaseId = { 80207, 80208, 80209 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "Verify Pending Activation Link for Registration from CSR")
    public void verifyRegistrationPendingActivationLink() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyRegistrationPendingActivationLink();
        
        log.info("Test Case execution for - verifyRegistrationPendingActivationLink - is Completed.");
    }
    
    @TestRail(testCaseId = { 92062, 92063, 92051, 92052, 92053, 92057, 92058, 92059, 92060, 92061, 92473, 101620, 101621, 101622, 101623, 101624, 101625, 101626, 101627, 101628, 101629, 101630, 101631, 101632, 101633, 101634 })
    @FrameworkAnnotations(author = {"Satya Tiwari"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "This test method verifies below test cases : \"\r\n" + 
    		"			+ \"C92062, C92063, C92051, C92052, C92053, C92057, C92058, C92059, C92060, C92061, C92473, C101620, C101621, C101622, C101623, C101624, C101625, C101626, C101627, C101628, C101629, C101630, C101631, C101632, C101633, C101634")
    public void verifyResetTemporaryPasswordCSP() throws Exception {
        log.info("To verify the tests with the below test case id's: " + "C106832");
        // Init login steps
        adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        
        adminCsrSteps =  new AdminCustomerSteps(driver);
        adminCsrSteps.verifyResetTemporaryPasswordCSP();
        
        log.info("Test Case execution for - verifyResetTemporaryPasswordCSP - is Completed.");
    }
    
    
    

}

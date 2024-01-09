package sew.ai.tests.csp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import sew.ai.config.Configuration;
import sew.ai.config.ModelsConfiguration;
import sew.ai.driver.Page;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.helpers.testrail.TestRail;
import sew.ai.models.AdminWaysToSave;
import sew.ai.pageObjects.csp.WaysToSavePage;
import sew.ai.pageObjects.scp.PostLogEfficiencyPage;
import sew.ai.runner.TestRunner;
import sew.ai.steps.csp.AdminHomeSteps;
import sew.ai.steps.csp.AdminLoginSteps;
import sew.ai.steps.csp.WaysToSaveSteps;
import sew.ai.utils.RandomUtil;

import static sew.ai.steps.csp.WaysToSaveSteps.getCategoryId;
import static sew.ai.steps.csp.WaysToSaveSteps.waysToSaveTextProp;

public class WaysToSaveTests extends TestRunner {

    public enum Category {
        SAVING_TIPS("Saving Tips"),
        EDUCATIONAL_TIPS("Educational Tips"),
        PROGRAMS("Programs"),
        REBATES("Rebates");

        private String resource;

        Category(String resource) {
            this.resource = resource;
        }
    }

    public enum MeterType {Power, Water, Gas, Solar}

    public enum AccountType {
        Residential("Residential"),
        Commercial("Commercial"),
        Multi_Family("Multi-Family");
        private String resource;

        AccountType(String resource) {
            this.resource = resource;
        }
    }

    private static final Logger log = LogManager.getLogger(WaysToSaveTests.class);

    private WaysToSaveSteps waysToSaveSteps;

    @TestRail(testCaseId = {80131, 80132, 80133})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C80131, C80132, C80133 - Verify the Ways To Save Dashboard UI and page objects")
    public void verifyWaysToSaveDashboard() {
        log.info("To verify the tests with the below test case id's: " +
                "C80131, C80132, C80133");
        SoftAssert softAssert = new SoftAssert();
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Ways To Save Page
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        adminHomeSteps.navigateToWaysToSave();
        //Verify the Ways To Save Landing Page.
        waysToSaveSteps = new WaysToSaveSteps(driver);
        Assert.assertTrue(waysToSaveSteps.isWaysToSavePage(waysToSaveTextProp.getPropValue("waysToSavePageUrl"),
                waysToSaveTextProp.getPropValue("waysToSavePageTitle")), "Ways To Save Page Url and Title is not as Expected.");
        //Verify the Ways To Save Dashboard Objects
        waysToSaveSteps.verifyWayToSaveObjects(softAssert);
        //Verify the Total Ways To Save UI Count with the DB.
        waysToSaveSteps.verifyWayToSaveGridTotalCountWithDB(softAssert, Category.SAVING_TIPS.resource);
        waysToSaveSteps.verifyWayToSaveGridTotalCountWithDB(softAssert, Category.EDUCATIONAL_TIPS.resource);
        waysToSaveSteps.verifyWayToSaveGridTotalCountWithDB(softAssert, Category.REBATES.resource);
        waysToSaveSteps.verifyWayToSaveGridTotalCountWithDB(softAssert, Category.PROGRAMS.resource);
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyWaysToSaveDashboard - is Completed.");
    }

    @TestRail(testCaseId = {80134, 80135, 80136, 80137, 80138, 80139, 80161, 80162, 80164})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C80134, C80135, C80136, C80137, C80138, C80139, C80161, C80162, C80164 - Verify the Add Ways To Save Dashboard Popup Page Objects and Field Validations")
    public void verifyAddEfficiencyForm() {
        log.info("To verify the tests with the below test case id's: " +
                "C80134, C80135, C80136, C80137, C80138, C80139, C80161, C80162, C80164");
        SoftAssert softAssert = new SoftAssert();
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Ways To Save Page
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        adminHomeSteps.navigateToWaysToSave();
        //Verify the Ways To Save Landing Page.
        waysToSaveSteps = new WaysToSaveSteps(driver);
        Assert.assertTrue(waysToSaveSteps.isWaysToSavePage(waysToSaveTextProp.getPropValue("waysToSavePageUrl"),
                waysToSaveTextProp.getPropValue("waysToSavePageTitle")), "Ways To Save Page Url and Title is not as Expected.");
        //Verify the Add Ways To Save Popup Objects
        waysToSaveSteps.verifyAddWaysToSavePopupObjects(softAssert, Category.SAVING_TIPS.resource);
        //Verify the Add Ways To Save Field Level Validations
        waysToSaveSteps.verifyAddWaysToSavePopupFieldValidations(softAssert);
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyAddEfficiencyForm - is Completed.");
    }

    @TestRail(testCaseId = {80140, 80141, 80142, 80143})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C80140, C80141, C80142, C80143 - Verify Add Ways To Save Form Fields For different Categories")
    public void verifyFieldsAfterSelectingCategory() {
        log.info("To verify the tests with the below test case id's: " +
                "C80140, C80141, C80142, C80143");
        SoftAssert softAssert = new SoftAssert();
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Ways To Save Page
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        adminHomeSteps.navigateToWaysToSave();
        //Verify the Ways To Save Landing Page.
        waysToSaveSteps = new WaysToSaveSteps(driver);
        Assert.assertTrue(waysToSaveSteps.isWaysToSavePage(waysToSaveTextProp.getPropValue("waysToSavePageUrl"),
                waysToSaveTextProp.getPropValue("waysToSavePageTitle")), "Ways To Save Page Url and Title is not as Expected.");
        //Verify the Add Ways To Save Form Fields for Categories.
        waysToSaveSteps.verifyAddWaysToSavePopupObjects(softAssert, Category.SAVING_TIPS.resource);
        waysToSaveSteps.verifyAddWaysToSavePopupObjects(softAssert, Category.EDUCATIONAL_TIPS.resource);
        waysToSaveSteps.verifyAddWaysToSavePopupObjects(softAssert, Category.PROGRAMS.resource);
        waysToSaveSteps.verifyAddWaysToSavePopupObjects(softAssert, Category.REBATES.resource);
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyFieldsAfterSelectingCategory - is Completed.");
    }

    @TestRail(testCaseId = {80144, 80146, 80147, 80159})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C80144, C80146, C80147, C80159 - Verify Add Update Delete For CSP Ways To Save Module.")
    public void verifyAddUpdateDeleteEfficiency() {
        log.info("To verify the tests with the below test case id's: " +
                "C80144, C80146, C80147, C80159");
        SoftAssert softAssert = new SoftAssert();
        AdminWaysToSave adminWaysToSave;
        String savingTipsWaysToSave = null;
        String programsWaysToSave = null;
        String rebatesWaysToSave = null;
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Ways To Save Page
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        adminHomeSteps.navigateToWaysToSave();
        //Verify the Ways To Save Landing Page.
        waysToSaveSteps = new WaysToSaveSteps(driver);
        Assert.assertTrue(waysToSaveSteps.isWaysToSavePage(waysToSaveTextProp.getPropValue("waysToSavePageUrl"),
                waysToSaveTextProp.getPropValue("waysToSavePageTitle")), "Ways To Save Page Url and Title is not as Expected.");
        try {
            //Initialize the Add Ways To Save Model
            adminWaysToSave = ModelsConfiguration.readAdminWaysToSave().getAdminWaysToSaveByScenario("Saving Tips");
            adminWaysToSave.setTopicNameEnglish("SavingTipsTopic " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setDescEnglish("SavingTipsDescription " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setAnnualSavings(RandomUtil.generateRandomString(2, RandomUtil.Mode.NUMERIC));
            //Add Ways To Save.
            waysToSaveSteps.addEfficiencyForCategory(adminWaysToSave);
            //Verify the Created Ways To Save topic details with the DB.
            waysToSaveSteps.verifyWaysToSaveDataWithDB(softAssert, adminWaysToSave);
            //Update Ways to Save Popup Fields
            waysToSaveSteps.updateWaysToSavePopupFieldValues(adminWaysToSave);
            savingTipsWaysToSave = adminWaysToSave.getTopicNameEnglish();
            //Verify the updated Ways to Save data with the DB
            waysToSaveSteps.verifyUpdatedWaysToSavePopupFieldValuesWithDB(softAssert, adminWaysToSave);
            //Delete Ways To Save
            waysToSaveSteps.deleteWaysToSaveName(softAssert, adminWaysToSave);
            //Verify Deleted Ways To Save On DB.
            waysToSaveSteps.verifyDeletedWaysToSaveOnDB(adminWaysToSave);
            //Verify Validation for Enrolled Ways To Save Deletion
            waysToSaveSteps.verifyValidationMsgDeleteEnrolledWaysToSave(softAssert, adminWaysToSave);

            //Programs
            adminWaysToSave = ModelsConfiguration.readAdminWaysToSave().getAdminWaysToSaveByScenario("Programs");
            adminWaysToSave.setTopicNameEnglish("ProgramsTopic " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setDescEnglish("ProgramsDescription " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            waysToSaveSteps.addEfficiencyForCategory(adminWaysToSave);
            waysToSaveSteps.verifyWaysToSaveDataWithDB(softAssert, adminWaysToSave);
            waysToSaveSteps.updateWaysToSavePopupFieldValues(adminWaysToSave);
            programsWaysToSave = adminWaysToSave.getTopicNameEnglish();
            waysToSaveSteps.verifyUpdatedWaysToSavePopupFieldValuesWithDB(softAssert, adminWaysToSave);
            waysToSaveSteps.deleteWaysToSaveName(softAssert, adminWaysToSave);
            waysToSaveSteps.verifyDeletedWaysToSaveOnDB(adminWaysToSave);
            waysToSaveSteps.verifyValidationMsgDeleteEnrolledWaysToSave(softAssert, adminWaysToSave);

            //Rebates
            adminWaysToSave = ModelsConfiguration.readAdminWaysToSave().getAdminWaysToSaveByScenario("Rebates");
            adminWaysToSave.setTopicNameEnglish("RebatesTopic " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setDescEnglish("RebatesDescription " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setIncentiveRate(RandomUtil.generateRandomString(2, RandomUtil.Mode.NUMERIC));
            waysToSaveSteps.addEfficiencyForCategory(adminWaysToSave);
            waysToSaveSteps.verifyWaysToSaveDataWithDB(softAssert, adminWaysToSave);
            waysToSaveSteps.updateWaysToSavePopupFieldValues(adminWaysToSave);
            rebatesWaysToSave = adminWaysToSave.getTopicNameEnglish();
            waysToSaveSteps.verifyUpdatedWaysToSavePopupFieldValuesWithDB(softAssert, adminWaysToSave);
            waysToSaveSteps.deleteWaysToSaveName(softAssert, adminWaysToSave);
            waysToSaveSteps.verifyDeletedWaysToSaveOnDB(adminWaysToSave);
            waysToSaveSteps.verifyValidationMsgDeleteEnrolledWaysToSave(softAssert, adminWaysToSave);

        } finally {
                waysToSaveSteps.tearDown(savingTipsWaysToSave);
                waysToSaveSteps.tearDown(programsWaysToSave);
                waysToSaveSteps.tearDown(rebatesWaysToSave);
        }

        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyAddUpdateDeleteEfficiency - is Completed.");
    }

    @TestRail(testCaseId = {80145, 80166})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C80145, C80166 - Verify Fields on Update Ways To Save Page and validation messages.")
    public void verifyUpdateWaysToSaveFieldsAndValidationMsg() {
        log.info("To verify the tests with the below test case id's: " +
                "C80145, C80166 ");
        SoftAssert softAssert = new SoftAssert();
        String waysToSaveName;
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Ways To Save Page
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        adminHomeSteps.navigateToWaysToSave();
        //Verify the Ways To Save Landing Page.
        waysToSaveSteps = new WaysToSaveSteps(driver);
        Assert.assertTrue(waysToSaveSteps.isWaysToSavePage(waysToSaveTextProp.getPropValue("waysToSavePageUrl"),
                waysToSaveTextProp.getPropValue("waysToSavePageTitle")), "Ways To Save Page Url and Title is not as Expected.");
        //Verify the Update Admin Ways To Save Page Fields
        //Saving Tips
        waysToSaveName = waysToSaveSteps.getFirstWaysToSaveRecordFromDB(String.valueOf(getCategoryId(Category.SAVING_TIPS.resource)));
        waysToSaveSteps.verifyUpdateWaysToSavePopupFields(softAssert, Category.SAVING_TIPS.resource, waysToSaveName);
        //Educational Tips
        waysToSaveName = waysToSaveSteps.getFirstWaysToSaveRecordFromDB(String.valueOf(getCategoryId(Category.EDUCATIONAL_TIPS.resource)));
        waysToSaveSteps.verifyUpdateWaysToSavePopupFields(softAssert, Category.EDUCATIONAL_TIPS.resource, waysToSaveName);
        //Programs
        waysToSaveName = waysToSaveSteps.getFirstWaysToSaveRecordFromDB(String.valueOf(getCategoryId(Category.PROGRAMS.resource)));
        waysToSaveSteps.verifyUpdateWaysToSavePopupFields(softAssert, Category.PROGRAMS.resource, waysToSaveName);
        //Rebates
        waysToSaveName = waysToSaveSteps.getFirstWaysToSaveRecordFromDB(String.valueOf(getCategoryId(Category.REBATES.resource)));
        waysToSaveSteps.verifyUpdateWaysToSavePopupFields(softAssert, Category.REBATES.resource, waysToSaveName);
        //Verify Validation Message for Blank Submission fields on Update Ways To Save Popup
        waysToSaveSteps.verifyUpdateWaysToSavePopUpBlankSubmitValidation(softAssert, Category.REBATES.resource, waysToSaveName);
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyUpdateWaysToSaveFieldsAndValidationMsg - is Completed.");
    }

    @TestRail(testCaseId = {80157})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C80157 - Verify on DragAndDrop for the order and title of Saving Tips on CSP and SCP")
    public void verifySavingTipsOrderAndTitle() {
        log.info("To verify the tests with the below test case id's: " +
                "C80157");
        SoftAssert softAssert = new SoftAssert();
        AdminWaysToSave adminWaysToSave;
        WaysToSavePage waysToSavePage;
        PostLogEfficiencyPage postLogEfficiencyPage;
        String savingTipsWaysToSaveName = null;
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Ways To Save Page
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        adminHomeSteps.navigateToWaysToSave();
        //Verify the Ways To Save Landing Page.
        waysToSaveSteps = new WaysToSaveSteps(driver);
        Assert.assertTrue(waysToSaveSteps.isWaysToSavePage(waysToSaveTextProp.getPropValue("waysToSavePageUrl"),
                waysToSaveTextProp.getPropValue("waysToSavePageTitle")), "Ways To Save Page Url and Title is not as Expected.");
        try {
            //Add Saving Tips
            adminWaysToSave = ModelsConfiguration.readAdminWaysToSave().getAdminWaysToSaveByScenario("Saving Tips");
            adminWaysToSave.setTopicNameEnglish("SavingTipsTopic " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setDescEnglish("SavingTipsDescription " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setAnnualSavings(RandomUtil.generateRandomString(2, RandomUtil.Mode.NUMERIC));
            waysToSaveSteps.addEfficiencyForCategory(adminWaysToSave);
            savingTipsWaysToSaveName = adminWaysToSave.getTopicNameEnglish();
            //Verify the Created Ways to Save present on Top of the Grid.
            waysToSaveSteps.verifyWaysToSaveCreatedPresentOnTopGrid(softAssert, adminWaysToSave);
            //Drag and Drop Ways To Save
            waysToSaveSteps.dragAndDropWaysToSave(savingTipsWaysToSaveName, 6);//todo Open Issue SCM10-11594
            //Get Index and Title of WaysToSave From CSP.
            waysToSavePage = new WaysToSavePage(driver);
            int indexCSP = waysToSaveSteps.getIndexOfWaysToSaveNameOnGridCSP(savingTipsWaysToSaveName);
            String titleCSP = waysToSavePage.getLstWebEleWaysToSaveName().get(5).getText();
            //Get Index and Title of WaysToSave From SCP
            postLogEfficiencyPage = new PostLogEfficiencyPage(driver);
            waysToSaveSteps.loginToScpAndNavigateToWaysToSave(Configuration.toString("userName"), Configuration.toString("password"));
            waysToSaveSteps.navToScpPostLogWaysToSaveTab(Category.SAVING_TIPS.resource);
            int indexSCP = waysToSaveSteps.getIndexOfWaysToSaveNameFromSCP(savingTipsWaysToSaveName);
            String titleSCP = postLogEfficiencyPage.getLstPostLoginEfficiencyHeading().get(5);
            //Assertion
            Assert.assertEquals(indexCSP, indexSCP, "Order of WaysToSave from CSP is not matching with the SCP.");
            Assert.assertEquals(titleCSP, titleSCP, "WaysToSave Title from CSP is not matching with the SCP.");
        } finally {
            waysToSaveSteps.tearDown(savingTipsWaysToSaveName);
        }
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifySavingTipsOrderAndTitle - is Completed.");
    }

    @TestRail(testCaseId = {107755})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C107755 - Verify on DragAndDrop for the order and title of Educational Tips on CSP and SCP")
    public void verifyEducationalTipsOrderAndTitle() {
        log.info("To verify the tests with the below test case id's: " +
                "C107755");
        SoftAssert softAssert = new SoftAssert();
        AdminWaysToSave adminWaysToSave;
        WaysToSavePage waysToSavePage;
        PostLogEfficiencyPage postLogEfficiencyPage;
        String educationalTipsWaysToSaveName = null;
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Ways To Save Page
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        adminHomeSteps.navigateToWaysToSave();
        //Verify the Ways To Save Landing Page.
        waysToSaveSteps = new WaysToSaveSteps(driver);
        Assert.assertTrue(waysToSaveSteps.isWaysToSavePage(waysToSaveTextProp.getPropValue("waysToSavePageUrl"),
                waysToSaveTextProp.getPropValue("waysToSavePageTitle")), "Ways To Save Page Url and Title is not as Expected.");
        try {
            //Add Educational Tips
            adminWaysToSave = ModelsConfiguration.readAdminWaysToSave().getAdminWaysToSaveByScenario("Educational Tips");
            adminWaysToSave.setTopicNameEnglish("EducationalTipsTopic " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setDescEnglish("EducationalTipsDescription " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            waysToSaveSteps.addEfficiencyForCategory(adminWaysToSave);
            educationalTipsWaysToSaveName = adminWaysToSave.getTopicNameEnglish();
            //Verify the Created Ways to Save present on Top of the Grid.
            waysToSaveSteps.verifyWaysToSaveCreatedPresentOnTopGrid(softAssert, adminWaysToSave);
            //Drag and Drop Ways To Save
            waysToSaveSteps.dragAndDropWaysToSave(educationalTipsWaysToSaveName, 6);//todo Open Issue SCM10-11594
            //Get Index and Title of WaysToSave From CSP.
            waysToSavePage = new WaysToSavePage(driver);
            int indexCSP = waysToSaveSteps.getIndexOfWaysToSaveNameOnGridCSP(educationalTipsWaysToSaveName);
            String titleCSP = waysToSavePage.getLstWebEleWaysToSaveName().get(5).getText();
            //Get Index and Title of WaysToSave From SCP
            postLogEfficiencyPage = new PostLogEfficiencyPage(driver);
            waysToSaveSteps.loginToScpAndNavigateToWaysToSave(Configuration.toString("userName"), Configuration.toString("password"));
            waysToSaveSteps.navToScpPostLogWaysToSaveTab(Category.EDUCATIONAL_TIPS.resource);
            int indexSCP = waysToSaveSteps.getIndexOfWaysToSaveNameFromSCP(educationalTipsWaysToSaveName);
            String titleSCP = postLogEfficiencyPage.getLstPostLoginEfficiencyHeading().get(5);
            //Assertion
            Assert.assertEquals(indexCSP, indexSCP, "Order of WaysToSave from CSP is not matching with the SCP.");
            Assert.assertEquals(titleCSP, titleSCP, "WaysToSave Title from CSP is not matching with the SCP.");
        } finally {
            waysToSaveSteps.tearDown(educationalTipsWaysToSaveName);
        }
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyEducationalTipsOrderAndTitle - is Completed.");
    }

    @TestRail(testCaseId = {107757})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C107757 - Verify on DragAndDrop for the order and title of Programs on CSP and SCP")
    public void verifyProgramsOrderAndTitle() {
        log.info("To verify the tests with the below test case id's: " +
                "C107757");
        SoftAssert softAssert = new SoftAssert();
        AdminWaysToSave adminWaysToSave;
        WaysToSavePage waysToSavePage;
        PostLogEfficiencyPage postLogEfficiencyPage;
        String programsWaysToSaveName = null;
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Ways To Save Page
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        adminHomeSteps.navigateToWaysToSave();
        //Verify the Ways To Save Landing Page.
        waysToSaveSteps = new WaysToSaveSteps(driver);
        Assert.assertTrue(waysToSaveSteps.isWaysToSavePage(waysToSaveTextProp.getPropValue("waysToSavePageUrl"),
                waysToSaveTextProp.getPropValue("waysToSavePageTitle")), "Ways To Save Page Url and Title is not as Expected.");
        try {
            //Add Programs
            adminWaysToSave = ModelsConfiguration.readAdminWaysToSave().getAdminWaysToSaveByScenario("Programs");
            adminWaysToSave.setTopicNameEnglish("ProgramsTopic " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setDescEnglish("ProgramsDescription " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            waysToSaveSteps.addEfficiencyForCategory(adminWaysToSave);
            programsWaysToSaveName = adminWaysToSave.getTopicNameEnglish();
            //Verify the Created Ways to Save present on Top of the Grid.
            waysToSaveSteps.verifyWaysToSaveCreatedPresentOnTopGrid(softAssert, adminWaysToSave);
            //Drag and Drop Ways To Save
            waysToSaveSteps.dragAndDropWaysToSave(programsWaysToSaveName, 6);//todo Open Issue SCM10-11594
            //Get Index and Title of WaysToSave From CSP.
            waysToSavePage = new WaysToSavePage(driver);
            int indexCSP = waysToSaveSteps.getIndexOfWaysToSaveNameOnGridCSP(programsWaysToSaveName);
            String titleCSP = waysToSavePage.getLstWebEleWaysToSaveName().get(5).getText();
            //Get Index and Title of WaysToSave From SCP
            postLogEfficiencyPage = new PostLogEfficiencyPage(driver);
            waysToSaveSteps.loginToScpAndNavigateToWaysToSave(Configuration.toString("userName"), Configuration.toString("password"));
            waysToSaveSteps.navToScpPostLogWaysToSaveTab(Category.PROGRAMS.resource);
            int indexSCP = waysToSaveSteps.getIndexOfWaysToSaveNameFromSCP(programsWaysToSaveName);
            String titleSCP = postLogEfficiencyPage.getLstPostLoginEfficiencyHeading().get(5);
            //Assertion
            Assert.assertEquals(indexCSP, indexSCP, "Order of WaysToSave from CSP is not matching with the SCP.");
            Assert.assertEquals(titleCSP, titleSCP, "WaysToSave Title from CSP is not matching with the SCP.");
        } finally {
            waysToSaveSteps.tearDown(programsWaysToSaveName);
        }
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyProgramsOrderAndTitle - is Completed.");
    }

    @TestRail(testCaseId = {107756})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C107756 - Verify on DragAndDrop for the order and title of Rebates on CSP and SCP")
    public void verifyRebatesOrderAndTitle() {
        log.info("To verify the tests with the below test case id's: " +
                "C107756");
        SoftAssert softAssert = new SoftAssert();
        AdminWaysToSave adminWaysToSave;
        WaysToSavePage waysToSavePage;
        PostLogEfficiencyPage postLogEfficiencyPage;
        String rebatesWaysToSaveName = null;
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Ways To Save Page
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        adminHomeSteps.navigateToWaysToSave();
        //Verify the Ways To Save Landing Page.
        waysToSaveSteps = new WaysToSaveSteps(driver);
        Assert.assertTrue(waysToSaveSteps.isWaysToSavePage(waysToSaveTextProp.getPropValue("waysToSavePageUrl"),
                waysToSaveTextProp.getPropValue("waysToSavePageTitle")), "Ways To Save Page Url and Title is not as Expected.");
        try {
            //Add Rebates
            adminWaysToSave = ModelsConfiguration.readAdminWaysToSave().getAdminWaysToSaveByScenario("Rebates");
            adminWaysToSave.setTopicNameEnglish("RebatesTopic " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setDescEnglish("RebatesDescription " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setIncentiveRate(RandomUtil.generateRandomString(2, RandomUtil.Mode.NUMERIC));
            waysToSaveSteps.addEfficiencyForCategory(adminWaysToSave);
            rebatesWaysToSaveName = adminWaysToSave.getTopicNameEnglish();
            //Verify the Created Ways to Save present on Top of the Grid.
            waysToSaveSteps.verifyWaysToSaveCreatedPresentOnTopGrid(softAssert, adminWaysToSave);
            //Drag and Drop Ways To Save
            waysToSaveSteps.dragAndDropWaysToSave(rebatesWaysToSaveName, 6);//todo Open Issue SCM10-11594
            //Get Index and Title of WaysToSave From CSP.
            waysToSavePage = new WaysToSavePage(driver);
            int indexCSP = waysToSaveSteps.getIndexOfWaysToSaveNameOnGridCSP(rebatesWaysToSaveName);
            String titleCSP = waysToSavePage.getLstWebEleWaysToSaveName().get(5).getText();
            //Get Index and Title of WaysToSave From SCP
            postLogEfficiencyPage = new PostLogEfficiencyPage(driver);
            waysToSaveSteps.loginToScpAndNavigateToWaysToSave(Configuration.toString("userName"), Configuration.toString("password"));
            waysToSaveSteps.navToScpPostLogWaysToSaveTab(Category.REBATES.resource);
            int indexSCP = waysToSaveSteps.getIndexOfWaysToSaveNameFromSCP(rebatesWaysToSaveName);
            String titleSCP = postLogEfficiencyPage.getLstPostLoginEfficiencyHeading().get(5);
            //Assertion
            Assert.assertEquals(indexCSP, indexSCP, "Order of WaysToSave from CSP is not matching with the SCP.");
            Assert.assertEquals(titleCSP, titleSCP, "WaysToSave Title from CSP is not matching with the SCP.");
        } finally {
            waysToSaveSteps.tearDown(rebatesWaysToSaveName);
        }
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyRebatesOrderAndTitle - is Completed.");
    }

    @TestRail(testCaseId = {80167})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C80167 - Add Update Delete Saving Tips in CSP and verify same on SCP.")
    public void verifyAddUpdateDeleteSavingsTipsEfficiency() {
        log.info("To verify the tests with the below test case id's: " +
                "C80167");
        SoftAssert softAssert = new SoftAssert();
        AdminWaysToSave adminWaysToSave = null;
        Page page;
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Ways To Save Page
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        adminHomeSteps.navigateToWaysToSave();
        //Verify the Ways To Save Landing Page.
        waysToSaveSteps = new WaysToSaveSteps(driver);
        Assert.assertTrue(waysToSaveSteps.isWaysToSavePage(waysToSaveTextProp.getPropValue("waysToSavePageUrl"),
                waysToSaveTextProp.getPropValue("waysToSavePageTitle")), "Ways To Save Page Url and Title is not as Expected.");
        try {
            //Add Ways To Save on CSP
            adminWaysToSave = ModelsConfiguration.readAdminWaysToSave().getAdminWaysToSaveByScenario(Category.SAVING_TIPS.resource);
            adminWaysToSave.setTopicNameEnglish("SavingTipsTopic " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setDescEnglish("SavingTipsDescription " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setAnnualSavings(RandomUtil.generateRandomString(2, RandomUtil.Mode.NUMERIC));
            waysToSaveSteps.addEfficiencyForCategory(adminWaysToSave);
            //Verify Added Ways To Save on SCP.
            waysToSaveSteps.loginToScpAndNavigateToWaysToSave(Configuration.toString("userName"), Configuration.toString("password"));
            waysToSaveSteps.navToScpPostLogWaysToSaveTab(Category.SAVING_TIPS.resource);
            Assert.assertTrue(waysToSaveSteps.isWaysToSaveNamePresentOnScp(adminWaysToSave.getTopicNameEnglish()),
                    "Created WaysToSave On CSP with Topic Name " + adminWaysToSave.getTopicNameEnglish() + " is not present on SCP for the Category " + adminWaysToSave.getCategory());

            //Update Ways to Save on CSP.
            page = new Page(driver);
            page.deleteCookies();
            adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
            adminHomeSteps.navigateToWaysToSave();
            String oldWaysToSaveName = adminWaysToSave.getTopicNameEnglish();
            waysToSaveSteps.updateWaysToSavePopupFieldValues(adminWaysToSave);
            String newWaysToSaveName = adminWaysToSave.getTopicNameEnglish();
            //Verify Updated Ways to Save on SCP
            waysToSaveSteps.loginToScpAndNavigateToWaysToSave(Configuration.toString("userName"), Configuration.toString("password"));
            waysToSaveSteps.navToScpPostLogWaysToSaveTab(Category.SAVING_TIPS.resource);
            Assert.assertFalse(waysToSaveSteps.isWaysToSaveNamePresentOnScp(oldWaysToSaveName),
                    "Old WaysToSave from CSP with Topic Name " + adminWaysToSave.getTopicNameEnglish() + " is present on SCP for the Category " + adminWaysToSave.getCategory());
            Assert.assertTrue(waysToSaveSteps.isWaysToSaveNamePresentOnScp(newWaysToSaveName),
                    "Updated WaysToSave from CSP with Topic Name " + adminWaysToSave.getTopicNameEnglish() + " is not present on SCP for the Category " + adminWaysToSave.getCategory());

            //Delete Ways to Save on CSP.
            page = new Page(driver);
            page.deleteCookies();
            adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
            adminHomeSteps.navigateToWaysToSave();
            waysToSaveSteps.selectWaysToSaveFilter(adminWaysToSave.getCategory());
            waysToSaveSteps.deleteWaysToSaveName(softAssert, adminWaysToSave);
            //Verify Deleted Ways to Save on SCP.
            waysToSaveSteps.loginToScpAndNavigateToWaysToSave(Configuration.toString("userName"), Configuration.toString("password"));
            waysToSaveSteps.navToScpPostLogWaysToSaveTab(Category.SAVING_TIPS.resource);
            Assert.assertFalse(waysToSaveSteps.isWaysToSaveNamePresentOnScp(adminWaysToSave.getTopicNameEnglish()),
                    "Deleted WaysToSave from CSP with Topic Name " + adminWaysToSave.getTopicNameEnglish() + " is present on SCP for the Category " + adminWaysToSave.getCategory());
        } finally {
            waysToSaveSteps.tearDown(adminWaysToSave.getTopicNameEnglish());
        }


        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyAddUpdateDeleteSavingsTipsEfficiency - is Completed.");
    }

    @TestRail(testCaseId = {80169})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C80169 - Add Update Delete Educational Tips in CSP and verify same on SCP.")
    public void verifyAddUpdateDeleteEducationalTipsEfficiency() {
        log.info("To verify the tests with the below test case id's: " +
                "C80169");
        SoftAssert softAssert = new SoftAssert();
        AdminWaysToSave adminWaysToSave = null;
        Page page;
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Ways To Save Page
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        adminHomeSteps.navigateToWaysToSave();
        //Verify the Ways To Save Landing Page.
        waysToSaveSteps = new WaysToSaveSteps(driver);
        Assert.assertTrue(waysToSaveSteps.isWaysToSavePage(waysToSaveTextProp.getPropValue("waysToSavePageUrl"),
                waysToSaveTextProp.getPropValue("waysToSavePageTitle")), "Ways To Save Page Url and Title is not as Expected.");
        try {
            //Add Ways To Save on CSP
            adminWaysToSave = ModelsConfiguration.readAdminWaysToSave().getAdminWaysToSaveByScenario(Category.EDUCATIONAL_TIPS.resource);
            adminWaysToSave.setTopicNameEnglish("EducationalTipsTopic " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setDescEnglish("EducationalTipsDescription " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            waysToSaveSteps.addEfficiencyForCategory(adminWaysToSave);
            //Verify Added Ways To Save on SCP.
            waysToSaveSteps.loginToScpAndNavigateToWaysToSave(Configuration.toString("userName"), Configuration.toString("password"));
            waysToSaveSteps.navToScpPostLogWaysToSaveTab(Category.EDUCATIONAL_TIPS.resource);
            Assert.assertTrue(waysToSaveSteps.isWaysToSaveNamePresentOnScp(adminWaysToSave.getTopicNameEnglish()),
                    "Created WaysToSave On CSP with Topic Name " + adminWaysToSave.getTopicNameEnglish() + " is not present on SCP for the Category " + adminWaysToSave.getCategory());

            //Update Ways to Save on CSP.
            page = new Page(driver);
            page.deleteCookies();
            adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
            adminHomeSteps.navigateToWaysToSave();
            String oldWaysToSaveName = adminWaysToSave.getTopicNameEnglish();
            waysToSaveSteps.updateWaysToSavePopupFieldValues(adminWaysToSave);
            String newWaysToSaveName = adminWaysToSave.getTopicNameEnglish();
            //Verify Updated Ways to Save on SCP.
            waysToSaveSteps.loginToScpAndNavigateToWaysToSave(Configuration.toString("userName"), Configuration.toString("password"));
            waysToSaveSteps.navToScpPostLogWaysToSaveTab(Category.EDUCATIONAL_TIPS.resource);
            Assert.assertFalse(waysToSaveSteps.isWaysToSaveNamePresentOnScp(oldWaysToSaveName),
                    "Old WaysToSave from CSP with Topic Name " + adminWaysToSave.getTopicNameEnglish() + " is present on SCP for the Category " + adminWaysToSave.getCategory());
            Assert.assertTrue(waysToSaveSteps.isWaysToSaveNamePresentOnScp(newWaysToSaveName),
                    "Updated WaysToSave from CSP with Topic Name " + adminWaysToSave.getTopicNameEnglish() + " is not present on SCP for the Category " + adminWaysToSave.getCategory());

            //Delete Ways to Save on CSP.
            page = new Page(driver);
            page.deleteCookies();
            adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
            adminHomeSteps.navigateToWaysToSave();
            waysToSaveSteps.selectWaysToSaveFilter(adminWaysToSave.getCategory());
            waysToSaveSteps.deleteWaysToSaveName(softAssert, adminWaysToSave);
            //Verify Deleted Ways to Save on SCP.
            waysToSaveSteps.loginToScpAndNavigateToWaysToSave(Configuration.toString("userName"), Configuration.toString("password"));
            waysToSaveSteps.navToScpPostLogWaysToSaveTab(Category.EDUCATIONAL_TIPS.resource);
            Assert.assertFalse(waysToSaveSteps.isWaysToSaveNamePresentOnScp(adminWaysToSave.getTopicNameEnglish()),
                    "Deleted WaysToSave from CSP with Topic Name " + adminWaysToSave.getTopicNameEnglish() + " is present on SCP for the Category " + adminWaysToSave.getCategory());
        } finally {
            waysToSaveSteps.tearDown(adminWaysToSave.getTopicNameEnglish());
        }

        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyAddUpdateDeleteEducationalTipsEfficiency - is Completed.");
    }

    @TestRail(testCaseId = {80168})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C80168 - Add Update Delete Programs in CSP and verify same on SCP.")
    public void verifyAddUpdateDeleteProgramsEfficiency() {
        log.info("To verify the tests with the below test case id's: " +
                "C80168");
        SoftAssert softAssert = new SoftAssert();
        AdminWaysToSave adminWaysToSave = null;
        Page page;
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Ways To Save Page
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        adminHomeSteps.navigateToWaysToSave();
        //Verify the Ways To Save Landing Page.
        waysToSaveSteps = new WaysToSaveSteps(driver);
        Assert.assertTrue(waysToSaveSteps.isWaysToSavePage(waysToSaveTextProp.getPropValue("waysToSavePageUrl"),
                waysToSaveTextProp.getPropValue("waysToSavePageTitle")), "Ways To Save Page Url and Title is not as Expected.");
        try {
            //Add Ways To Save on CSP
            adminWaysToSave = ModelsConfiguration.readAdminWaysToSave().getAdminWaysToSaveByScenario(Category.PROGRAMS.resource);
            adminWaysToSave.setTopicNameEnglish("ProgramsTopic " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setDescEnglish("ProgramsDescription " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            waysToSaveSteps.addEfficiencyForCategory(adminWaysToSave);
            //Verify Added Ways To Save on SCP.
            waysToSaveSteps.loginToScpAndNavigateToWaysToSave(Configuration.toString("userName"), Configuration.toString("password"));
            waysToSaveSteps.navToScpPostLogWaysToSaveTab(Category.PROGRAMS.resource);
            Assert.assertTrue(waysToSaveSteps.isWaysToSaveNamePresentOnScp(adminWaysToSave.getTopicNameEnglish()),
                    "Created WaysToSave On CSP with Topic Name " + adminWaysToSave.getTopicNameEnglish() + " is not present on SCP for the Category " + adminWaysToSave.getCategory());

            //Update Ways to Save on CSP.
            page = new Page(driver);
            page.deleteCookies();
            adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
            adminHomeSteps.navigateToWaysToSave();
            String oldWaysToSaveName = adminWaysToSave.getTopicNameEnglish();
            waysToSaveSteps.updateWaysToSavePopupFieldValues(adminWaysToSave);
            String newWaysToSaveName = adminWaysToSave.getTopicNameEnglish();
            //Verify Updated Ways to Save on SCP.
            waysToSaveSteps.loginToScpAndNavigateToWaysToSave(Configuration.toString("userName"), Configuration.toString("password"));
            waysToSaveSteps.navToScpPostLogWaysToSaveTab(Category.PROGRAMS.resource);
            Assert.assertFalse(waysToSaveSteps.isWaysToSaveNamePresentOnScp(oldWaysToSaveName),
                    "Old WaysToSave from CSP with Topic Name " + adminWaysToSave.getTopicNameEnglish() + " is present on SCP for the Category " + adminWaysToSave.getCategory());
            Assert.assertTrue(waysToSaveSteps.isWaysToSaveNamePresentOnScp(newWaysToSaveName),
                    "Updated WaysToSave from CSP with Topic Name " + adminWaysToSave.getTopicNameEnglish() + " is not present on SCP for the Category " + adminWaysToSave.getCategory());

            //Delete Ways to Save on CSP.
            page = new Page(driver);
            page.deleteCookies();
            adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
            adminHomeSteps.navigateToWaysToSave();
            waysToSaveSteps.selectWaysToSaveFilter(adminWaysToSave.getCategory());
            waysToSaveSteps.deleteWaysToSaveName(softAssert, adminWaysToSave);
            //Verify Deleted Ways to Save on SCP.
            waysToSaveSteps.loginToScpAndNavigateToWaysToSave(Configuration.toString("userName"), Configuration.toString("password"));
            waysToSaveSteps.navToScpPostLogWaysToSaveTab(Category.PROGRAMS.resource);
            Assert.assertFalse(waysToSaveSteps.isWaysToSaveNamePresentOnScp(adminWaysToSave.getTopicNameEnglish()),
                    "Deleted WaysToSave from CSP with Topic Name " + adminWaysToSave.getTopicNameEnglish() + " is present on SCP for the Category " + adminWaysToSave.getCategory());
        } finally {
            waysToSaveSteps.tearDown(adminWaysToSave.getTopicNameEnglish());
        }

        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyAddUpdateDeleteProgramsEfficiency - is Completed.");
    }

    @TestRail(testCaseId = {80173})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C80173 - Add Update Delete Rebates in CSP and verify same on SCP.")
    public void verifyAddUpdateDeleteRebatesEfficiency() {
        log.info("To verify the tests with the below test case id's: " +
                "C80173");
        SoftAssert softAssert = new SoftAssert();
        AdminWaysToSave adminWaysToSave = null;
        Page page;
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Ways To Save Page
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        adminHomeSteps.navigateToWaysToSave();
        //Verify the Ways To Save Landing Page.
        waysToSaveSteps = new WaysToSaveSteps(driver);
        Assert.assertTrue(waysToSaveSteps.isWaysToSavePage(waysToSaveTextProp.getPropValue("waysToSavePageUrl"),
                waysToSaveTextProp.getPropValue("waysToSavePageTitle")), "Ways To Save Page Url and Title is not as Expected.");
        try {
            //Add Ways To Save on CSP
            adminWaysToSave = ModelsConfiguration.readAdminWaysToSave().getAdminWaysToSaveByScenario("Rebates");
            adminWaysToSave.setTopicNameEnglish("RebatesTopic " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setDescEnglish("RebatesDescription " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setIncentiveRate(RandomUtil.generateRandomString(2, RandomUtil.Mode.NUMERIC));
            waysToSaveSteps.addEfficiencyForCategory(adminWaysToSave);
            //Verify Added Ways To Save on SCP.
            waysToSaveSteps.loginToScpAndNavigateToWaysToSave(Configuration.toString("userName"), Configuration.toString("password"));
            waysToSaveSteps.navToScpPostLogWaysToSaveTab(Category.REBATES.resource);
            Assert.assertTrue(waysToSaveSteps.isWaysToSaveNamePresentOnScp(adminWaysToSave.getTopicNameEnglish()),
                    "Created WaysToSave On CSP with Topic Name " + adminWaysToSave.getTopicNameEnglish() + " is not present on SCP for the Category " + adminWaysToSave.getCategory());

            //Update Ways to Save on CSP.
            page = new Page(driver);
            page.deleteCookies();
            adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
            adminHomeSteps.navigateToWaysToSave();
            String oldWaysToSaveName = adminWaysToSave.getTopicNameEnglish();
            waysToSaveSteps.updateWaysToSavePopupFieldValues(adminWaysToSave);
            String newWaysToSaveName = adminWaysToSave.getTopicNameEnglish();
            //Verify Updated Ways to Save on SCP.
            waysToSaveSteps.loginToScpAndNavigateToWaysToSave(Configuration.toString("userName"), Configuration.toString("password"));
            waysToSaveSteps.navToScpPostLogWaysToSaveTab(Category.REBATES.resource);
            Assert.assertFalse(waysToSaveSteps.isWaysToSaveNamePresentOnScp(oldWaysToSaveName),
                    "Old WaysToSave from CSP with Topic Name " + adminWaysToSave.getTopicNameEnglish() + " is present on SCP for the Category " + adminWaysToSave.getCategory());
            Assert.assertTrue(waysToSaveSteps.isWaysToSaveNamePresentOnScp(newWaysToSaveName),
                    "Updated WaysToSave from CSP with Topic Name " + adminWaysToSave.getTopicNameEnglish() + " is not present on SCP for the Category " + adminWaysToSave.getCategory());

            //Delete Ways to Save on CSP.
            page = new Page(driver);
            page.deleteCookies();
            adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
            adminHomeSteps.navigateToWaysToSave();
            waysToSaveSteps.selectWaysToSaveFilter(adminWaysToSave.getCategory());
            waysToSaveSteps.deleteWaysToSaveName(softAssert, adminWaysToSave);
            //Verify Deleted Ways to Save on SCP.
            waysToSaveSteps.loginToScpAndNavigateToWaysToSave(Configuration.toString("userName"), Configuration.toString("password"));
            waysToSaveSteps.navToScpPostLogWaysToSaveTab(Category.REBATES.resource);
            Assert.assertFalse(waysToSaveSteps.isWaysToSaveNamePresentOnScp(adminWaysToSave.getTopicNameEnglish()),
                    "Deleted WaysToSave from CSP with Topic Name " + adminWaysToSave.getTopicNameEnglish() + " is present on SCP for the Category " + adminWaysToSave.getCategory());
        } finally {
            waysToSaveSteps.tearDown(adminWaysToSave.getTopicNameEnglish());
        }

        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyAddUpdateDeleteRebatesEfficiency - is Completed.");
    }

    @TestRail(testCaseId = {80148, 80149, 80150, 80170, 80171})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C80148, C80149, C80150,C80170, C80171 -  Verify updating Ways To Save Active or inactive Status from CSP reflected on SCP.")
    public void verifyInactiveActiveWaysToSave() {
        log.info("To verify the tests with the below test case id's: " +
                "C80148, C80149, C80150,C80170, C80171");
        SoftAssert softAssert = new SoftAssert();
        AdminWaysToSave adminWaysToSave = null;
        Page page;
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Ways To Save Page
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        adminHomeSteps.navigateToWaysToSave();
        //Verify the Ways To Save Landing Page.
        waysToSaveSteps = new WaysToSaveSteps(driver);
        Assert.assertTrue(waysToSaveSteps.isWaysToSavePage(waysToSaveTextProp.getPropValue("waysToSavePageUrl"),
                waysToSaveTextProp.getPropValue("waysToSavePageTitle")), "Ways To Save Page Url and Title is not as Expected.");
        try {
            //Add Ways To Save on CSP
            adminWaysToSave = ModelsConfiguration.readAdminWaysToSave().getAdminWaysToSaveByScenario("Educational Tips");
            adminWaysToSave.setTopicNameEnglish("EducationalTipsTopic " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setDescEnglish("EducationalTipsDescription " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setMeterType(MeterType.Gas.name());
            adminWaysToSave.setAccountType(AccountType.Residential.resource);
            waysToSaveSteps.addEfficiencyForCategory(adminWaysToSave);
            String inactiveWaysToSave = adminWaysToSave.getTopicNameEnglish();

            //Updating Ways To Save to Inactive Status On CSP
            waysToSaveSteps.verifyChangeStatusConfirmationPopupObjects(softAssert, inactiveWaysToSave);
            waysToSaveSteps.verifyCloseButtonChangeStatusConfirmationPopup(inactiveWaysToSave);
            waysToSaveSteps.updateWaysToSaveStatus(inactiveWaysToSave);
            waysToSaveSteps.verifyStatusOfWaysToSave(inactiveWaysToSave, "Inactive");

            //Verify Inactive Ways To Save on SCP.
            waysToSaveSteps.loginToScpAndNavigateToWaysToSave(Configuration.toString("userName"), Configuration.toString("password"));
            waysToSaveSteps.navToScpPostLogWaysToSaveTab(Category.EDUCATIONAL_TIPS.resource);
            Assert.assertFalse(waysToSaveSteps.isWaysToSaveNamePresentOnScp(inactiveWaysToSave),
                    "Inactive WaysToSave On CSP with Topic Name " + inactiveWaysToSave + " is present on SCP for the Category " + adminWaysToSave.getCategory());

            //Updating Ways To Save to Active Status On CSP
            page = new Page(driver);
            page.deleteCookies();
            adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
            adminHomeSteps.navigateToWaysToSave();
            waysToSaveSteps.selectWaysToSaveFilter(Category.EDUCATIONAL_TIPS.resource);
            waysToSaveSteps.updateWaysToSaveStatus(inactiveWaysToSave);
            waysToSaveSteps.verifyStatusOfWaysToSave(inactiveWaysToSave, "Active");

            //Verify Active Ways To Save on SCP.
            waysToSaveSteps.loginToScpAndNavigateToWaysToSave(Configuration.toString("userName"), Configuration.toString("password"));
            waysToSaveSteps.navToScpPostLogWaysToSaveTab(Category.EDUCATIONAL_TIPS.resource);
            Assert.assertTrue(waysToSaveSteps.isWaysToSaveNamePresentOnScp(inactiveWaysToSave),
                    "Active WaysToSave On CSP with Topic Name " + inactiveWaysToSave + " is present on SCP for the Category " + adminWaysToSave.getCategory());
        } finally {
            waysToSaveSteps.tearDown(adminWaysToSave.getTopicNameEnglish());
        }

        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyInactiveActiveWaysToSave - is Completed.");
    }

    @TestRail(testCaseId = {80160, 80163})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C80160, C80163 - Verify Image functionality in Add Ways To Save Page for CSP.")
    public void verifyImageFunctionality() {
        log.info("To verify the tests with the below test case id's: " +
                "C80160, C80163");
        SoftAssert softAssert = new SoftAssert();
        AdminWaysToSave adminWaysToSave = null;
        Page page;
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Ways To Save Page
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        adminHomeSteps.navigateToWaysToSave();
        //Verify the Ways To Save Landing Page.
        waysToSaveSteps = new WaysToSaveSteps(driver);
        Assert.assertTrue(waysToSaveSteps.isWaysToSavePage(waysToSaveTextProp.getPropValue("waysToSavePageUrl"),
                waysToSaveTextProp.getPropValue("waysToSavePageTitle")), "Ways To Save Page Url and Title is not as Expected.");
        //Navigate to Add Ways To Save Popup
        waysToSaveSteps.navigateAddWaysToSavePopup();
        waysToSaveSteps.verifyUploadFileUIObjectsOnPopup(softAssert);
        //Verify the Upload File Validations
        waysToSaveSteps.verifyUploadFileValidationsAddPopup(softAssert);
        //SignOut Application
        adminHomeSteps.SignOutAdminApplication();
        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyImageFunctionality - is Completed.");
    }

    @TestRail(testCaseId = {104523})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C104523 - Verify WayToSave Creation for Multi-Account Types on CSP and verify same on SCP")
    public void verifyAddWaysToSaveMultiAcctType() {
        log.info("To verify the tests with the below test case id's: " +
                "C104523");
        SoftAssert softAssert = new SoftAssert();
        AdminWaysToSave adminWaysToSave = null;
        String savingTipsWaysToSave = null;
        String programsWaysToSave = null;
        String rebatesWaysToSave = null;
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Ways To Save Page
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        adminHomeSteps.navigateToWaysToSave();
        //Verify the Ways To Save Landing Page.
        waysToSaveSteps = new WaysToSaveSteps(driver);
        Assert.assertTrue(waysToSaveSteps.isWaysToSavePage(waysToSaveTextProp.getPropValue("waysToSavePageUrl"),
                waysToSaveTextProp.getPropValue("waysToSavePageTitle")), "Ways To Save Page Url and Title is not as Expected.");

        try {
            //Add Ways to Save with Multiple Account Type as Residential and Commercial
            adminWaysToSave = ModelsConfiguration.readAdminWaysToSave().getAdminWaysToSaveByScenario("Saving Tips Multi");
            adminWaysToSave.setTopicNameEnglish("SavingTipsMultiTopic " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setDescEnglish("SavingTipsMultiDescription " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setMeterType(MeterType.Power.name());
            savingTipsWaysToSave = adminWaysToSave.getTopicNameEnglish();
            waysToSaveSteps.addEfficiencyMultiAcctForCategory(adminWaysToSave);
            // verification in the SCP as Residential User after Saving Tips create .
            waysToSaveSteps.loginToScpAndNavigateToWaysToSave(Configuration.toString("userName"), Configuration.toString("password"));
            waysToSaveSteps.navToScpPostLogWaysToSaveTab(Category.SAVING_TIPS.resource);
            Assert.assertTrue(waysToSaveSteps.isWaysToSaveNamePresentOnScp(savingTipsWaysToSave),
                    "Multi-Account WaysToSave On CSP with Topic Name " + savingTipsWaysToSave + " is present on SCP for the Category "
                            + adminWaysToSave.getCategory() + " as a residential user.");
            // verification in the SCP as Commercial User after Saving Tips create .
            waysToSaveSteps.loginToScpAndNavigateToWaysToSave(Configuration.toString("commercialUserName"), Configuration.toString("commercialPassword"));
            waysToSaveSteps.navToScpPostLogWaysToSaveTab(Category.SAVING_TIPS.resource);
            Assert.assertTrue(waysToSaveSteps.isWaysToSaveNamePresentOnScp(savingTipsWaysToSave),
                    "Multi-Account WaysToSave On CSP with Topic Name " + savingTipsWaysToSave + " is present on SCP for the Category "
                            + adminWaysToSave.getCategory() + " as a commercial user.");

            //Add Ways to Save with Multiple Account Type as Commercial and Multi-Family Acct Type
            adminWaysToSave = ModelsConfiguration.readAdminWaysToSave().getAdminWaysToSaveByScenario("Programs Multi");
            adminWaysToSave.setTopicNameEnglish("ProgramsMultiTopic " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setDescEnglish("ProgramsMultiDescription " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setMeterType(MeterType.Power.name());
            programsWaysToSave = adminWaysToSave.getTopicNameEnglish();
            adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
            adminHomeSteps.navigateToWaysToSave();
            waysToSaveSteps.addEfficiencyMultiAcctForCategory(adminWaysToSave);
            // verification in the SCP as Commercial User after Programs create .
            waysToSaveSteps.loginToScpAndNavigateToWaysToSave(Configuration.toString("commercialUserName"), Configuration.toString("commercialPassword"));
            waysToSaveSteps.navToScpPostLogWaysToSaveTab(Category.PROGRAMS.resource);
            Assert.assertTrue(waysToSaveSteps.isWaysToSaveNamePresentOnScp(programsWaysToSave),
                    "Multi-Account WaysToSave On CSP with Topic Name " + programsWaysToSave + " is present on SCP for the Category "
                            + adminWaysToSave.getCategory() + " as a commercial user.");
            // verification in the SCP as Multi-Family User after Programs create .
            waysToSaveSteps.loginToScpAndNavigateToWaysToSave(Configuration.toString("multiFamilyUserName"), Configuration.toString("multiFamilyPassword"));
            waysToSaveSteps.navToScpPostLogWaysToSaveTab(Category.PROGRAMS.resource);
            Assert.assertTrue(waysToSaveSteps.isWaysToSaveNamePresentOnScp(programsWaysToSave),
                    "Multi-Account WaysToSave On CSP with Topic Name " + programsWaysToSave + " is present on SCP for the Category "
                            + adminWaysToSave.getCategory() + " as a Multi-Family user.");

            //Add Ways to Save with Multiple Account Type as Residential and Multi-Family Acct Type
            adminWaysToSave = ModelsConfiguration.readAdminWaysToSave().getAdminWaysToSaveByScenario("Rebates Multi");
            adminWaysToSave.setTopicNameEnglish("RebatesMultiTopic " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setDescEnglish("RebatesMultiDescription " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setMeterType(MeterType.Power.name());
            rebatesWaysToSave = adminWaysToSave.getTopicNameEnglish();
            adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
            adminHomeSteps.navigateToWaysToSave();
            waysToSaveSteps.addEfficiencyMultiAcctForCategory(adminWaysToSave);
            // verification in the SCP as Residential User after Rebates create .
            waysToSaveSteps.loginToScpAndNavigateToWaysToSave(Configuration.toString("userName"), Configuration.toString("password"));
            waysToSaveSteps.navToScpPostLogWaysToSaveTab(Category.REBATES.resource);
            Assert.assertTrue(waysToSaveSteps.isWaysToSaveNamePresentOnScp(rebatesWaysToSave),
                    "Multi-Account WaysToSave On CSP with Topic Name " + rebatesWaysToSave + " is present on SCP for the Category "
                            + adminWaysToSave.getCategory() + " as a Residential user.");
            // verification in the SCP as Multi-Family User after Rebates create .
            waysToSaveSteps.loginToScpAndNavigateToWaysToSave(Configuration.toString("multiFamilyUserName"), Configuration.toString("multiFamilyPassword"));
            waysToSaveSteps.navToScpPostLogWaysToSaveTab(Category.REBATES.resource);
            Assert.assertTrue(waysToSaveSteps.isWaysToSaveNamePresentOnScp(rebatesWaysToSave),
                    "Multi-Account WaysToSave On CSP with Topic Name " + rebatesWaysToSave + " is present on SCP for the Category "
                            + adminWaysToSave.getCategory() + " as a Multi-Family user.");
        } finally {
            waysToSaveSteps.tearDown(savingTipsWaysToSave);
            waysToSaveSteps.tearDown(programsWaysToSave);
            waysToSaveSteps.tearDown(rebatesWaysToSave);
        }

        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyAddWaysToSaveMultiAcctType - is Completed.");
    }

    @TestRail(testCaseId = {104524})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C104524 - Verify WayToSave Updation for Multi-Account Types on CSP and verify same on SCP")
    public void verifyUpdateWaysToSaveMultiAcctType() {
        log.info("To verify the tests with the below test case id's: " +
                "C104524");
        SoftAssert softAssert = new SoftAssert();
        AdminWaysToSave adminWaysToSave = null;
        String savingTipsUpdatedWaysToSave = null;
        String rebatesUpdatedWaysToSave = null;
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Ways To Save Page
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        adminHomeSteps.navigateToWaysToSave();
        //Verify the Ways To Save Landing Page.
        waysToSaveSteps = new WaysToSaveSteps(driver);
        Assert.assertTrue(waysToSaveSteps.isWaysToSavePage(waysToSaveTextProp.getPropValue("waysToSavePageUrl"),
                waysToSaveTextProp.getPropValue("waysToSavePageTitle")), "Ways To Save Page Url and Title is not as Expected.");

        try {
            //Add Ways to Save
            adminWaysToSave = ModelsConfiguration.readAdminWaysToSave().getAdminWaysToSaveByScenario("Saving Tips");
            adminWaysToSave.setTopicNameEnglish("SavingTipsMultiTopic " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setDescEnglish("SavingTipsMultiDescription " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setMeterType(MeterType.Power.name());
            waysToSaveSteps.addEfficiencyForCategory(adminWaysToSave);
            //Update Ways To Save with Multi - Account Types as Commercial and Multi-Family
            waysToSaveSteps.selectWaysToSaveFilter(adminWaysToSave.getCategory());
            waysToSaveSteps.navUpdateWaysToSavePopupPageOfName(adminWaysToSave.getTopicNameEnglish());
            adminWaysToSave = ModelsConfiguration.readAdminWaysToSave().getAdminWaysToSaveByScenario("Saving Tips Multi2");
            waysToSaveSteps.updateWaysToSavePopupFieldValuesForMultiAcct(adminWaysToSave);
            savingTipsUpdatedWaysToSave = adminWaysToSave.getTopicNameEnglish();
            //Verification in the SCP as Commercial User after Saving Tips Update.
            waysToSaveSteps.loginToScpAndNavigateToWaysToSave(Configuration.toString("commercialUserName"), Configuration.toString("commercialPassword"));
            waysToSaveSteps.navToScpPostLogWaysToSaveTab(Category.SAVING_TIPS.resource);
            Assert.assertTrue(waysToSaveSteps.isWaysToSaveNamePresentOnScp(savingTipsUpdatedWaysToSave),
                    "Multi-Account Updated WaysToSave On CSP with Topic Name " + savingTipsUpdatedWaysToSave + " is not present on SCP for the Category "
                            + adminWaysToSave.getCategory() + " as a commercial user.");
            // verification in the SCP as Multi-Family User after Saving Tips Update.
            waysToSaveSteps.loginToScpAndNavigateToWaysToSave(Configuration.toString("multiFamilyUserName"), Configuration.toString("multiFamilyPassword"));
            waysToSaveSteps.navToScpPostLogWaysToSaveTab(Category.SAVING_TIPS.resource);
            Assert.assertTrue(waysToSaveSteps.isWaysToSaveNamePresentOnScp(savingTipsUpdatedWaysToSave),
                    "Multi-Account Updated WaysToSave On CSP with Topic Name " + savingTipsUpdatedWaysToSave + " is not present on SCP for the Category "
                            + adminWaysToSave.getCategory() + " as a Multi-Family user.");

            // Scenario 2: Adding Rebates as Commercial
            adminWaysToSave = ModelsConfiguration.readAdminWaysToSave().getAdminWaysToSaveByScenario("Rebates");
            adminWaysToSave.setTopicNameEnglish("RebatesMultiTopic " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setDescEnglish("RebatesMultiDescription " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setMeterType(MeterType.Power.name());
            adminWaysToSave.setAccountType(AccountType.Commercial.resource);
            adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
            adminHomeSteps.navigateToWaysToSave();
            waysToSaveSteps.addEfficiencyForCategory(adminWaysToSave);
            //Update Ways To Save with Multi - Account Types as Residential and Multi-Family
            waysToSaveSteps.selectWaysToSaveFilter(adminWaysToSave.getCategory());
            waysToSaveSteps.navUpdateWaysToSavePopupPageOfName(adminWaysToSave.getTopicNameEnglish());
            adminWaysToSave = ModelsConfiguration.readAdminWaysToSave().getAdminWaysToSaveByScenario("Rebates Multi2");
            waysToSaveSteps.updateWaysToSavePopupFieldValuesForMultiAcct(adminWaysToSave);
            rebatesUpdatedWaysToSave = adminWaysToSave.getTopicNameEnglish();
            //Verification in the SCP as Residential User after Rebates Update.
            waysToSaveSteps.loginToScpAndNavigateToWaysToSave(Configuration.toString("userName"), Configuration.toString("password"));
            waysToSaveSteps.navToScpPostLogWaysToSaveTab(Category.REBATES.resource);
            Assert.assertTrue(waysToSaveSteps.isWaysToSaveNamePresentOnScp(rebatesUpdatedWaysToSave),
                    "Multi-Account Updated WaysToSave On CSP with Topic Name " + rebatesUpdatedWaysToSave + " is not present on SCP for the Category "
                            + adminWaysToSave.getCategory() + " as a Residential user.");
            // verification in the SCP as Multi-Family User after Rebates Update.
            waysToSaveSteps.loginToScpAndNavigateToWaysToSave(Configuration.toString("multiFamilyUserName"), Configuration.toString("multiFamilyPassword"));
            waysToSaveSteps.navToScpPostLogWaysToSaveTab(Category.REBATES.resource);
            Assert.assertTrue(waysToSaveSteps.isWaysToSaveNamePresentOnScp(rebatesUpdatedWaysToSave),
                    "Multi-Account Updated WaysToSave On CSP with Topic Name " + rebatesUpdatedWaysToSave + " is not present on SCP for the Category "
                            + adminWaysToSave.getCategory() + " as a Multi-Family user.");

        } finally {
            waysToSaveSteps.tearDown(savingTipsUpdatedWaysToSave);
            waysToSaveSteps.tearDown(rebatesUpdatedWaysToSave);
        }

        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyUpdateWaysToSaveMultiAcctType - is Completed.");
    }

    @TestRail(testCaseId = {80151, 80152})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C80151, C80152 - Verify WayToSave View Popup Details Displayed on the CSP.")
    public void verifyWaysToSaveViewPopupDetailsCSP() {
        log.info("To verify the tests with the below test case id's: " +
                "C80151, C80152");
        SoftAssert softAssert = new SoftAssert();
        AdminWaysToSave adminWaysToSave = null;
        String savingTipsWaysToSave = null;
        String educationalTipsWaysToSave = null;
        String programsWaysToSave = null;
        String rebatesUpdatedWaysToSave = null;
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Ways To Save Page
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        adminHomeSteps.navigateToWaysToSave();
        //Verify the Ways To Save Landing Page.
        waysToSaveSteps = new WaysToSaveSteps(driver);
        Assert.assertTrue(waysToSaveSteps.isWaysToSavePage(waysToSaveTextProp.getPropValue("waysToSavePageUrl"),
                waysToSaveTextProp.getPropValue("waysToSavePageTitle")), "Ways To Save Page Url and Title is not as Expected.");

        try {
            //Saving Tips
            adminWaysToSave = ModelsConfiguration.readAdminWaysToSave().getAdminWaysToSaveByScenario(Category.SAVING_TIPS.resource);
            adminWaysToSave.setTopicNameEnglish("SavingTipsTopic " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setDescEnglish("SavingTipsDescription " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setAnnualSavings(RandomUtil.generateRandomString(2, RandomUtil.Mode.NUMERIC));
            waysToSaveSteps.addEfficiencyForCategory(adminWaysToSave);
            savingTipsWaysToSave = adminWaysToSave.getTopicNameEnglish();
            waysToSaveSteps.navigateViewPopupWaysToSave(softAssert, adminWaysToSave);
            waysToSaveSteps.verifyVewPopupUIObjects(softAssert, adminWaysToSave);

            //Educational Tips
            adminWaysToSave = ModelsConfiguration.readAdminWaysToSave().getAdminWaysToSaveByScenario(Category.EDUCATIONAL_TIPS.resource);
            adminWaysToSave.setTopicNameEnglish("EducationalTipsTopic " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setDescEnglish("EducationalTipsDescription " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            waysToSaveSteps.addEfficiencyForCategory(adminWaysToSave);
            educationalTipsWaysToSave = adminWaysToSave.getTopicNameEnglish();
            waysToSaveSteps.navigateViewPopupWaysToSave(softAssert, adminWaysToSave);
            waysToSaveSteps.verifyVewPopupUIObjects(softAssert, adminWaysToSave);

            //Programs
            adminWaysToSave = ModelsConfiguration.readAdminWaysToSave().getAdminWaysToSaveByScenario(Category.PROGRAMS.resource);
            adminWaysToSave.setTopicNameEnglish("ProgramsTopic " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setDescEnglish("ProgramsDescription " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            waysToSaveSteps.addEfficiencyForCategory(adminWaysToSave);
            programsWaysToSave = adminWaysToSave.getTopicNameEnglish();
            waysToSaveSteps.navigateViewPopupWaysToSave(softAssert, adminWaysToSave);
            waysToSaveSteps.verifyVewPopupUIObjects(softAssert, adminWaysToSave);

            //Rebates
            adminWaysToSave = ModelsConfiguration.readAdminWaysToSave().getAdminWaysToSaveByScenario("Rebates");
            adminWaysToSave.setTopicNameEnglish("RebatesTopic " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setDescEnglish("RebatesDescription " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setIncentiveRate(RandomUtil.generateRandomString(2, RandomUtil.Mode.NUMERIC));
            waysToSaveSteps.addEfficiencyForCategory(adminWaysToSave);
            rebatesUpdatedWaysToSave = adminWaysToSave.getTopicNameEnglish();
            waysToSaveSteps.navigateViewPopupWaysToSave(softAssert, adminWaysToSave);
            waysToSaveSteps.verifyVewPopupUIObjects(softAssert, adminWaysToSave);
        } finally {
            waysToSaveSteps.tearDown(savingTipsWaysToSave);
            waysToSaveSteps.tearDown(educationalTipsWaysToSave);
            waysToSaveSteps.tearDown(programsWaysToSave);
            waysToSaveSteps.tearDown(rebatesUpdatedWaysToSave);
        }

        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyWaysToSaveViewPopupDetailsCSP - is Completed.");
    }

    @TestRail(testCaseId = {80172})
    @FrameworkAnnotations(author = {"Krishna"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "C80172 - Verify navigation to External Link on SCP for CSP Added Ways To Save.")
    public void verifyExternalLinkEfficiency() {
        log.info("To verify the tests with the below test case id's: " +
                "C80172");
        SoftAssert softAssert = new SoftAssert();
        AdminWaysToSave adminWaysToSave;
        String educationalTipsWaysToSave = null;
        // Init steps
        AdminLoginSteps adminLoginSteps = new AdminLoginSteps(driver);
        // Login into the application
        adminLoginSteps.loginIntoAdminApplication(Configuration.toString("adminUsername"), Configuration.toString("adminPassword"));
        //Navigate to Ways To Save Page
        AdminHomeSteps adminHomeSteps = new AdminHomeSteps(driver);
        adminHomeSteps.navigateToWaysToSave();
        //Verify the Ways To Save Landing Page.
        waysToSaveSteps = new WaysToSaveSteps(driver);
        Assert.assertTrue(waysToSaveSteps.isWaysToSavePage(waysToSaveTextProp.getPropValue("waysToSavePageUrl"),
                waysToSaveTextProp.getPropValue("waysToSavePageTitle")), "Ways To Save Page Url and Title is not as Expected.");

        try {
            //Add Educational Tips on CSP.
            adminWaysToSave = ModelsConfiguration.readAdminWaysToSave().getAdminWaysToSaveByScenario("Educational Tips");
            adminWaysToSave.setTopicNameEnglish("EducationalTipsTopic " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setDescEnglish("EducationalTipsDescription " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
            adminWaysToSave.setInternal(false);
            adminWaysToSave.setExternalLink(waysToSaveTextProp.getPropValue("externalLink2"));
            waysToSaveSteps.addEfficiencyForCategory(adminWaysToSave);
            educationalTipsWaysToSave = adminWaysToSave.getTopicNameEnglish();

            //Verify External Link on SCP.
            waysToSaveSteps.loginToScpAndNavigateToWaysToSave(Configuration.toString("userName"),
                    Configuration.toString("password"));
            waysToSaveSteps.navToScpPostLogWaysToSaveTab(Category.EDUCATIONAL_TIPS.resource);
            Assert.assertTrue(waysToSaveSteps.isWaysToSaveNamePresentOnScp(educationalTipsWaysToSave),
                    "EducationalTips WaysToSave On CSP with Topic Name " + educationalTipsWaysToSave + " is not present on SCP for the Category "
                            + adminWaysToSave.getCategory() + " as a Residential user.");
            waysToSaveSteps.verifyReadMoreExternalLinkFromScp(educationalTipsWaysToSave);
        } finally {
            waysToSaveSteps.tearDown(educationalTipsWaysToSave);
        }

        //Asserting all
        softAssert.assertAll();
        log.info("Test Case execution for - verifyExternalLinkEfficiency - is Completed.");
    }


}

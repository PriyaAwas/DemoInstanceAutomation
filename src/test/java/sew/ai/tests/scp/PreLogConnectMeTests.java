package sew.ai.tests.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import sew.ai.config.SCPConfiguration;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.helpers.testrail.TestRail;
import sew.ai.models.User;
import sew.ai.runner.TestRunner;
import sew.ai.steps.scp.LoginSteps;
import sew.ai.steps.scp.PostLogConnectMeSteps;
import sew.ai.steps.scp.PreLogConnectMeSteps;

public class PreLogConnectMeTests extends TestRunner {
	
    private static final Logger log = LogManager.getLogger(PostLogConnectMeTests.class);   
    private PreLogConnectMeSteps preLogConnectMeSteps;
    
    
    @TestRail(testCaseId = {78214, 78200, 124159, 101605, 124838, 124839})
    @FrameworkAnnotations(author = {"Varad N"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the login page objects.")
    public void verifyPreLogConnectMePage() {
    	log.info("Test Case execution for - verifyPostLoginConnectMePage - is Initiated");
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
       //  Init Post Log Connect Me Steps
        preLogConnectMeSteps = new PreLogConnectMeSteps(driver);
       // verify objects on Connect me page
        preLogConnectMeSteps.verifyPreLogConnectMeObject(softAssert);
       // Submit Blanl Form
        preLogConnectMeSteps.verifyPreConnectMeSubmitblankForm();
       // Creat Request
        preLogConnectMeSteps.verifyCreatePreContactRequest(softAssert);
       // Preview Contact Us Form
        preLogConnectMeSteps.verifyPreConnectMePreviewYourFormDetails(softAssert);
       // Submit Form
        preLogConnectMeSteps.verifyPreConnectMeSubmitForm(softAssert);       
       log.info("Test Case execution for - verifyPostLoginConnectMePage - is Completed.");
     }
     
    @TestRail(testCaseId = {78254, 124830, 78255})
    @FrameworkAnnotations(author = {"Varad N"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the login page objects.")
    public void verifyPreLoginSocialMediaPage() {
    	log.info("Test Case execution for - verifyPostLoginConnectMePage - is Initiated");
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
       //  Init Post Log Connect Me Steps
        preLogConnectMeSteps = new PreLogConnectMeSteps(driver);
     // Verify Connect me form
        preLogConnectMeSteps.verifyPreLogConnectMeObject(softAssert);
       // verify objects on Connect me page
        preLogConnectMeSteps.verifyPreConnectMeSocialMediaObject(softAssert);
       // verify objects on Social media page        
       log.info("Test Case execution for - verifyPostLoginConnectMePage - is Completed.");
     }
    
    @TestRail(testCaseId = {124833, 124834, 124835, 124836, 78202, 78207, 78208, 78206, 78213, 78204, 79632, 79394,
    		120248, 120249, 120250, 120251, 120253, 121356, 121358, 121359, 121360, 121361, 100463, 120252, 121553, 121554, 78211, 
    		121357, 121362, 121362, 121552})
    @FrameworkAnnotations(author = {"Varad N"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the login page objects.")
    public void verifyPreLogTrackContactReq() {
    	log.info("Test Case execution for - verifyPostLoginConnectMePage - is Initiated");
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);
        String referenceId = null;
        // Init user model
        User user = SCPConfiguration.user;
        preLogConnectMeSteps = new PreLogConnectMeSteps(driver);
        //  Init Post Log Connect Me Steps
        preLogConnectMeSteps.verifyPreLogConnectMeObject(softAssert);
        // Creat Request
        preLogConnectMeSteps.verifyCreatePreContactRequest(softAssert);
        // Preview Contact Us Form
         preLogConnectMeSteps.verifyPreConnectMePreviewYourFormDetails(softAssert);
        // Submit Form
         referenceId = preLogConnectMeSteps.verifyPreConnectMeSubmitForm(softAssert);       
        // Verify Track Request Form
        preLogConnectMeSteps.verifyPreTrackRequestForm();
        // verify and submit track request form
        preLogConnectMeSteps.verifyPreTrackRequestForm(referenceId);
       log.info("Test Case execution for - verifyPostLoginConnectMePage - is Completed.");
     }
    
    @TestRail(testCaseId = {78212, 78209, 124824, 78210})
    @FrameworkAnnotations(author = {"Varad N"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the login page objects.")
    public void verifyPreLoginSavedFormsPage() {
    	log.info("Test Case execution for - verifyPostLoginConnectMePage - is Initiated");
        SoftAssert softAssert = new SoftAssert();            
        // Init user model
        preLogConnectMeSteps = new PreLogConnectMeSteps(driver);
        //  Init Post Log Connect Me Steps
        preLogConnectMeSteps.verifyPreLogConnectMeObject(softAssert);
        // Verify Objects of Saved Form 
        preLogConnectMeSteps.verifyPreSavedFormObject(softAssert);  
       log.info("Test Case execution for - verifyPostLoginConnectMePage - is Completed.");
     }
    
    @TestRail(testCaseId = {124825, 124826, 124827, 124828, 124829})
    @FrameworkAnnotations(author = {"Varad N"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the login page objects.")
    public void verifyPreLoginConnectMeFaqModule() {
    	log.info("Test Case execution for - verifyPostLoginConnectMePage - is Initiated");
        SoftAssert softAssert = new SoftAssert();        
        preLogConnectMeSteps = new PreLogConnectMeSteps(driver);
        //  Init Post Log Connect Me Steps
        preLogConnectMeSteps.verifyPreLogConnectMeObject(softAssert);
        // Verify FAQ Objects
        preLogConnectMeSteps.verifyPreConnectMeFAQObject(softAssert);        
       log.info("Test Case execution for - verifyPostLoginConnectMePage - is Completed.");
     }
}

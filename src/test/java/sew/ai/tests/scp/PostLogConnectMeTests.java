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
import sew.ai.steps.scp.ProblemSigningInSteps;

public class PostLogConnectMeTests extends TestRunner {
	
    private static final Logger log = LogManager.getLogger(PostLogConnectMeTests.class);   
    private PostLogConnectMeSteps postLogConnectMeSteps;
    
    
//    @TestRail(testCaseId = {124831, 78215, 124816, 124158, 124775, 124776, 78216, 124815, 78217, 78219, 78219, 124813, 124814, 110560})
//    @FrameworkAnnotations(author = {"Varad N"}, category = {CategoryType.SANITY})
//    @Test(priority = 1, description = "To verify the login page objects.")
//    public void verifyPostLoginConnectMePage() {
//    	log.info("Test Case execution for - verifyPostLoginConnectMePage - is Initiated");
//        SoftAssert softAssert = new SoftAssert();        
//        LoginSteps loginSteps = new LoginSteps(driver);
//        // Init user model
//        User user = SCPConfiguration.user;
//        // Login into the application
//        loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
//       //  Init Post Log Connect Me Steps
//        postLogConnectMeSteps = new PostLogConnectMeSteps(driver);
//       // verify objects on Connect me page
//        postLogConnectMeSteps.verifyConnectMeObject(softAssert);
//       // Submit Blanl Form
//        postLogConnectMeSteps.verifySubmitblankForm();
//       // Creat Request
//        postLogConnectMeSteps.verifyCreateContactRequest(softAssert);
//       // Preview Contact Us Form
//        postLogConnectMeSteps.verifyPreviewYourFormDetails(softAssert);
//       // Submit Form
//        postLogConnectMeSteps.verifySubmitForm(softAssert);       
//       log.info("Test Case execution for - verifyPostLoginConnectMePage - is Completed.");
//     }
    
    //Modified by Ajit
    @TestRail(testCaseId = {124831, 78215, 124816, 124158, 124775, 124776, 78216, 124815, 78217, 78219, 78219, 124813, 124814, 110560})
    @FrameworkAnnotations(author = {"Varad N"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the login page objects.")
    public void verifyPostLoginConnectMePage() {
    	log.info("Test Case execution for - verifyPostLoginConnectMePage - is Initiated");
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        loginSteps.loginIntoTheApplication("patricupdate","Demo@123");
       //  Init Post Log Connect Me Steps
        postLogConnectMeSteps = new PostLogConnectMeSteps(driver);
       // verify objects on Connect me page
        postLogConnectMeSteps.verifyConnectMeObject(softAssert);
       // Submit Blanl Form
        postLogConnectMeSteps.verifySubmitblankForm();
       // Creat Request
        postLogConnectMeSteps.verifyCreateContactRequest(softAssert);
       // Preview Contact Us Form
        postLogConnectMeSteps.verifyPreviewYourFormDetails(softAssert);
       // Submit Form
        postLogConnectMeSteps.verifySubmitForm(softAssert);       
       log.info("Test Case execution for - verifyPostLoginConnectMePage - is Completed.");
     }
    
    @TestRail(testCaseId = {124770, 78257, 78258, 124779, 124774})
    @FrameworkAnnotations(author = {"Varad N"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the login page objects.")
    public void verifyPostLoginSocialMediaPage() {
    	log.info("Test Case execution for - verifyPostLoginConnectMePage - is Initiated");
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
       //  Init Post Log Connect Me Steps
        postLogConnectMeSteps = new PostLogConnectMeSteps(driver);
     // Verify Connect me form
        postLogConnectMeSteps.verifyConnectMeObject(softAssert);
       // verify objects on Connect me page
        postLogConnectMeSteps.verifySocialMediaObject(softAssert);
       // verify objects on Social media page        
       log.info("Test Case execution for - verifyPostLoginConnectMePage - is Completed.");
     }
    
    @TestRail(testCaseId = {78260, 124817, 78259, 79395, 124796, 78251, 78225})
    @FrameworkAnnotations(author = {"Varad N"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the login page objects.")
    public void verifyPostLogCreateTrackContactReq() {
    	log.info("Test Case execution for - verifyPostLoginConnectMePage - is Initiated");
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);
        String referenceId = null;
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        //  Init Post Log Connect Me Steps
        postLogConnectMeSteps = new PostLogConnectMeSteps(driver);
        // Verify Connect me form
        postLogConnectMeSteps.verifyConnectMeObject(softAssert);
        // Creat Request
        postLogConnectMeSteps.verifyCreateContactRequest(softAssert);
        // Preview Contact Us Form
        postLogConnectMeSteps.verifyPreviewYourFormDetails(softAssert);
        // Submit Form
        postLogConnectMeSteps.verifySubmitForm(softAssert);
        // Verify Track Request Form
        postLogConnectMeSteps.verifyTrackRequestForm();
        // 
        postLogConnectMeSteps.verifyTrackContactRequests(referenceId);
       log.info("Test Case execution for - verifyPostLoginConnectMePage - is Completed.");
     }
    
    @TestRail(testCaseId = {124777, 80055, 124798, 124799, 78222, 124778, 78224, 124820})
    @FrameworkAnnotations(author = {"Varad N"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the login page objects.")
    public void verifyPostLoginSavedFormsPage() {
    	log.info("Test Case execution for - verifyPostLoginConnectMePage - is Initiated");
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);     
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        //  Init Post Log Connect Me Steps
        postLogConnectMeSteps = new PostLogConnectMeSteps(driver);
        // Verify Connect me form
        postLogConnectMeSteps.verifyConnectMeObject(softAssert);
        // Verify Objects of Saved Form 
       postLogConnectMeSteps.verifySavedFormObject(softAssert);
  
       log.info("Test Case execution for - verifyPostLoginConnectMePage - is Completed.");
     }
    
    @TestRail(testCaseId = {119788, 124821, 78220})
    @FrameworkAnnotations(author = {"Varad N"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the login page objects.")
    public void verifySubmitBillingQueryWithValidAttachment() {
    	log.info("Test Case execution for - verifyPostLoginConnectMePage - is Initiated");
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);     
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        //  Init Post Log Connect Me Steps
        postLogConnectMeSteps = new PostLogConnectMeSteps(driver);
        // Verify Connect me form
        postLogConnectMeSteps.verifyConnectMeObject(softAssert);
        // Verify creat billing request with valid attachment
        postLogConnectMeSteps.verifyCreateBillingRequestWithAttachment(softAssert);
       // Submit Form
        postLogConnectMeSteps.verifySubmitForm(softAssert);  
       log.info("Test Case execution for - verifyPostLoginConnectMePage - is Completed.");
     }
    
    @TestRail(testCaseId = {129283, 129326, 139165, 139234, 139234})
    @FrameworkAnnotations(author = {"Varad N"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the login page objects.")
    public void verifyAttachmentsForSubmitingForm() {
    	log.info("Test Case execution for - verifyPostLoginConnectMePage - is Initiated");
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);     
        // Init user model
        User user = SCPConfiguration.user;
        // Login into the application
        loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
        //  Init Post Log Connect Me Steps
        postLogConnectMeSteps = new PostLogConnectMeSteps(driver);
        // Verify Connect me form
        postLogConnectMeSteps.verifyConnectMeObject(softAssert);
        // Verify creat billing request with valid attachment
        postLogConnectMeSteps.verifyCreateBillingRequestWithAttachment(softAssert);
       // Submit Form
       log.info("Test Case execution for - verifyPostLoginConnectMePage - is Completed.");
     }
}

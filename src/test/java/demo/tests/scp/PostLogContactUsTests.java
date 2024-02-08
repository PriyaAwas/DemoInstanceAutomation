package demo.tests.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import demo.steps.scp.PostLogContactUsSteps;
import sew.ai.config.Configuration;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.ExcelTestCases;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.runner.TestRunner;
import sew.ai.steps.scp.DashboardSteps;
import sew.ai.steps.scp.LoginSteps;

public class PostLogContactUsTests extends TestRunner {
	
    private static final Logger log = LogManager.getLogger(PostLogContactUsTests.class);   
    private PostLogContactUsSteps postLogContactUsSteps;
    @ExcelTestCases(Tc_id = {"Contact_Us_01","Contact_Us_02"})
    @FrameworkAnnotations(author = {"Priya Awasthi"}, category = {CategoryType.SMOKE,CategoryType.SCP_CONNECTME,})
    @Test(priority = 1, description = "To verify the Post Login ContactUs Page objects.")

    public void verifyPostLoginContactUsPage() throws InterruptedException {
    	log.info("Test Case execution for - verifyPostLoginConnectMePage - is Initiated");
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init user model
        DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
        //  Init Post Log Connect Me Steps
        postLogContactUsSteps = new PostLogContactUsSteps(driver);
       // verify objects on Connect me page
        postLogContactUsSteps.verifyContactUsObject(softAssert);
         // Submit Blank Form
        postLogContactUsSteps.verifySubmitblankForm();
        postLogContactUsSteps.verifyCreateContactRequest(softAssert);
       // Preview Contact Us Form
        postLogContactUsSteps.verifyPreviewYourFormDetails(softAssert);
       // Submit Form
        postLogContactUsSteps.verifySubmitForm(softAssert);       
       log.info("Test Case execution for - verifyPostLoginConnectMePage - is Completed.");
     }
    
    @FrameworkAnnotations(author = {"Priya Awasthi"}, category = {CategoryType.SANITY,CategoryType.SCP_CONNECTME})
    @Test(priority = 1, description = "To verifyPostLoginSocialMediaPage.")

    public void verifyPostLoginSocialMediaPage() throws InterruptedException {
    	log.info("Test Case execution for - verifyPostLoginConnectMePage - is Initiated");
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);
        // Login into the application
        DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
       //  Init Post Log Connect Me Steps
        postLogContactUsSteps = new PostLogContactUsSteps(driver);
     // Verify Connect me form
        //postLogContactUsSteps.verifyContactUsObject(softAssert);
       // verify objects on Connect me page
        postLogContactUsSteps.verifySocialMediaObject(softAssert);
       // verify objects on Social media page        
       log.info("Test Case execution for - verifyPostLoginConnectMePage - is Completed.");
     }
    
    @FrameworkAnnotations(author = {"Priya Awasthi"}, category = {CategoryType.SANITY,CategoryType.SCP_CONNECTME})
    @Test(priority = 1, description = "To verify the login page objects.")

    public void verifyPostLogCreateTrackContactReq() throws InterruptedException {
    	log.info("Test Case execution for - verifyPostLogCreateTrackContactReq - is Initiated");
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);
        // Init user model
        // Login into the application
        DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
        //  Init Post Log Connect Me Steps
        postLogContactUsSteps = new PostLogContactUsSteps(driver);
        // Verify Connect me form
        postLogContactUsSteps.verifyContactUsObject(softAssert);
        // Creat Request
        postLogContactUsSteps.verifyCreateContactRequest(softAssert);
        // Preview Contact Us Form
        postLogContactUsSteps.verifyPreviewYourFormDetails(softAssert);
        // Submit Form
        postLogContactUsSteps.verifySubmitForm(softAssert);
        // Verify Track Request Form
        postLogContactUsSteps.verifyTrackRequestForm();
        // 
        postLogContactUsSteps.verifyTrackContactRequests();
       log.info("Test Case execution for - verifyPostLoginConnectMePage - is Completed.");
     }
    
    @FrameworkAnnotations(author = {"Priya Awasthi"}, category = {CategoryType.SANITY,CategoryType.SCP_CONNECTME})
    @Test(priority = 1, description = "To verify the login page objects.")

    public void verifyPostLoginSavedFormsPage() throws InterruptedException {
    	log.info("Test Case execution for - verifyPostLoginSavedFormsPage - is Initiated");
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);     
        // Init user model
        DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
        //  Init Post Log Connect Me Steps
        postLogContactUsSteps = new PostLogContactUsSteps(driver);
        // Verify Connect me form
        postLogContactUsSteps.verifyContactUsObject(softAssert);
        // Verify Objects of Saved Form 
       postLogContactUsSteps.verifySavedFormObject(softAssert);
  
       log.info("Test Case execution for - verifyPostLoginConnectMePage - is Completed.");
     }
    
    @FrameworkAnnotations(author = {"Priya Awasthi"}, category = {CategoryType.SANITY,CategoryType.SCP_CONNECTME})
    @Test(priority = 1, description = "To verifySubmitBillingQueryWithValidAttachment.")
    public void verifySubmitBillingQueryWithValidAttachment() throws InterruptedException {
    	log.info("Test Case execution for - verifyPostLoginConnectMePage - is Initiated");
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);     
        // Login into the application
        DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
        //  Init Post Log Connect Me Steps
        postLogContactUsSteps = new PostLogContactUsSteps(driver);
        // Verify Connect me form
        postLogContactUsSteps.verifyContactUsObject(softAssert);
        // Verify create billing request with valid attachment
        postLogContactUsSteps.verifyCreateBillingRequestWithAttachment(softAssert);
       //Submit Form
       postLogContactUsSteps.verifySubmitForm(softAssert);  
       log.info("Test Case execution for - verifyPostLoginConnectMePage - is Completed.");
     }
    
    @FrameworkAnnotations(author = {"Priya Awasthi"}, category = {CategoryType.SANITY,CategoryType.SCP_CONNECTME})
    @Test(priority = 1, description = "To verify the login page objects.")

    public void verifySubmitBillingQueryWithInValidAttachment() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();        
        LoginSteps loginSteps = new LoginSteps(driver);     
        // Login into the application
        DashboardSteps dashboardSteps = loginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
        //  Init Post Log Connect Me Steps
        postLogContactUsSteps = new PostLogContactUsSteps(driver);
        // Verify Connect me form
        postLogContactUsSteps.verifyContactUsObject(softAssert);
        // Verify creat billing request with valid attachment
        postLogContactUsSteps.verifyCreateRequestsWithAttachments(softAssert);
       // Submit Form
       log.info("Test Case execution for - verifyPostLoginConnectMePage - is Completed.");
     }
}

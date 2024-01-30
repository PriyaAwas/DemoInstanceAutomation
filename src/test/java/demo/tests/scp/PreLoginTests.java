package demo.tests.scp;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import sew.ai.config.Configuration;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.runner.TestRunner;
import sew.ai.steps.scp.DashboardSteps;
import sew.ai.utils.PropertiesUtil;
import demo.steps.scp.PreLoginSteps;

public class PreLoginTests extends TestRunner {
	
	private static final Logger log = LogManager.getLogger(PreLoginTests.class);
	private PreLoginSteps preLoginSteps;
	public static PropertiesUtil loginTextProp;
	
	@FrameworkAnnotations(author = { "Eujin" }, category = { CategoryType.SMOKE, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "User is able to Login with valid credentials.")
	public void verifyValidLogin() {
		log.info("To Verify Payment information fields and Add payment method pop-up");
		SoftAssert softAssert = new SoftAssert();
		//SCP- Application Login
		preLoginSteps = new PreLoginSteps(driver);
		preLoginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
		softAssert.assertAll();
        ExtentLogger.logInfo("Test Case execution for - verifyValidLogin - is Completed.");
	}
	

    @FrameworkAnnotations(author = {"Eujin"}, category = {CategoryType.SMOKE, CategoryType.SCP_LOGIN})
    @Test(priority = 3, description = "To verify the login functionality with invalid credentials.")
    public void verifyInvalidLogin() {
        log.info("To verify the tests with the below test case id's: " +
                "C75087, C75088");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        preLoginSteps = new PreLoginSteps(driver);
        preLoginSteps.loginIntoTheApplicationWrongCreds(softAssert);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyInvalidLogin - is Completed.");
        ExtentLogger.logInfo("Test Case execution for - verifyInvalidLogin - is Completed.");
    }
    
   
    @FrameworkAnnotations(author = {"Eujin"}, category = {CategoryType.SMOKE, CategoryType.SCP_LOGIN})
    @Test(priority = 1, description = "To verify the login page objects.")
    public void verifyLoginPageObjects() {
        log.info("To verify the tests with the below test case id's: " +
                "C75080, C101604");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        preLoginSteps = new PreLoginSteps(driver);
        // Go to the application and verify objects
        preLoginSteps.verifyTheLoginPageObject(softAssert);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyLoginPageObjects - is Completed.");
        ExtentLogger.logInfo("Test Case execution for - verifyLoginPageObjects - is Completed.");
    }
    
    
    @FrameworkAnnotations(author = {"Eujin"}, category = {CategoryType.SANITY, CategoryType.SCP_LOGIN})
    @Test(priority = 4, description = "To verify the remember me login functionality.")
    public void verifyRememberMeLoginFunctionality() {
   
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        preLoginSteps = new PreLoginSteps(driver);
        // Init user model
        String user = Configuration.toString("userName");
        String password = Configuration.toString("password");
        // Logging in to the application by checking remember me checkbox.
        DashboardSteps dashboardSteps = preLoginSteps.loginIntoTheAppByCheckingRememberMe(
                user,
                password
        );
        // Verify remember me functionality
        Boolean isRememberMeChecked = preLoginSteps.verifyUsernameAndRememberMeCheckboxStatus(
                dashboardSteps,
                user
        );
        Assert.assertTrue(isRememberMeChecked);
        // Assert all the soft verification.
        softAssert.assertAll();
        log.info("Test Case execution for - verifyRememberMeLoginFunctionality - is Completed.");
        ExtentLogger.logInfo("Test Case execution for - verifyRememberMeLoginFunctionality - is Completed.");
    }
    
    
    @FrameworkAnnotations(author = {"Eujin"}, category = {CategoryType.SANITY, CategoryType.SCP_CONNECTME})
    @Test(priority = 5, description = "To verify the Pre-Login Connect Me page.")
    public void verifyPreLogTrackContactReq() {
    	log.info("Test Case execution for - verifyPreLoginConnectMePage - is Initiated");
        SoftAssert softAssert = new SoftAssert();        
        PreLoginSteps preLoginSteps = new PreLoginSteps(driver);
        String referenceId = null;
        // Verify Objects
        preLoginSteps.verifyPreLogConnectMeObject(softAssert);
        // Create Request
        preLoginSteps.verifyCreatePreContactRequest(softAssert);
        // Preview Contact Us Form
        preLoginSteps.verifyPreConnectMePreviewYourFormDetails(softAssert);
        // Submit Form
        referenceId = preLoginSteps.verifyPreConnectMeSubmitForm(softAssert);
        log.info("reference ID: " + referenceId);
       log.info("Test Case execution for - verifyPreLogTrackContactReq - is Completed.");
        ExtentLogger.logInfo("Test Case execution for - verifyPreLogTrackContactReq - is Completed.");
     }
    
    @FrameworkAnnotations(author = {"Eujin"}, category = {CategoryType.SANITY, CategoryType.SCP_CONNECTME})
    @Test(priority = 5, description = "Verify User is able to Submit a Contact Us Request form for Water waste, theft without logging in.")
    public void verifyPreLogTrackContactReqForWaterWaste() {
    	log.info("Test Case execution for - verifyPreLoginConnectMePageForWaterWaste - is Initiated");
        SoftAssert softAssert = new SoftAssert();        
        PreLoginSteps preLoginSteps = new PreLoginSteps(driver);
        String referenceId = null;
        // Verify Objects
//        preLoginSteps.verifyPreLogConnectMeObjectForWaterLeaks(softAssert);
        // Create Request
        preLoginSteps.verifyCreatePreContactRequestForWaterLeaks(softAssert);
        // Preview Contact Us Form
        preLoginSteps.verifyPreConnectMePreviewYourFormDetailsForWaterLeaks(softAssert);
        // Submit Form
        referenceId = preLoginSteps.verifyPreConnectMeSubmitForm(softAssert);
        log.info("reference ID: " + referenceId);
        ExtentLogger.logInfo("Reference Id: " + referenceId);
       log.info("Test Case execution for - verifyPreLogTrackContactReqForWaterWaste - is Completed.");
        ExtentLogger.logInfo("Test Case execution for - verifyPreLogTrackContactReqForWaterWaste - is Completed.");
     }
    
    @FrameworkAnnotations(author = { "Kavya BR" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "To verify chat functionality.")
	public void verifyPreLogChatBox()throws SQLException {
		log.info("To Verify Payment information feilds and Add payment method pop-up");
		SoftAssert softAssert = new SoftAssert();
		//SCP- Application Login
		preLoginSteps = new PreLoginSteps(driver);
		preLoginSteps.verifyPreLogChatBox(softAssert);
		softAssert.assertAll();
        ExtentLogger.logInfo("Test Case execution for - verifyPreLogChatBox - is Completed.");
	}

    @FrameworkAnnotations(author = { "Kavya BR" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGIN })
	@Test(priority = 1, description = "User is able to Login with valid credentials.")
	public void verifySignOutPage() {
		log.info("To Verify Payment information fields and Add payment method pop-up");
		SoftAssert softAssert = new SoftAssert();
		//SCP- Application Login
		preLoginSteps = new PreLoginSteps(driver);
		preLoginSteps.loginIntoTheApplication(Configuration.toString("userName"), Configuration.toString("password"));
		softAssert.assertAll();
        ExtentLogger.logInfo("Test Case execution for - verifySignOutPage - is Completed.");
	}
}
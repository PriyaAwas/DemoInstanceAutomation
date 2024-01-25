package demo.tests.scp;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.runner.TestRunner;
import demo.steps.scp.ForgetUsernameSteps;

public class ForgetUsernameTest extends TestRunner {
    private static final Logger log = LogManager.getLogger(ForgetUsernameTest.class);
    private ForgetUsernameSteps forgetUsernameSteps;
	@FrameworkAnnotations(author = { "Priya" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGIN })
    @Test(priority = 1, description = "To verify the Forget Username page objects.")
    public void validateResetUsername() throws Exception {
        log.info("To verify that following should be displayed on validate Forgot Username.");
        SoftAssert softAssert = new SoftAssert();
        // Init Forget Username Steps
        forgetUsernameSteps = new ForgetUsernameSteps(driver);
        // Redirect to Forget Username Steps
       forgetUsernameSteps.verifyForgotUsernameInObject(softAssert);        
        log.info("Test Case execution for - validateForgotUsername - is Completed.");
                  
      }
	
	@FrameworkAnnotations(author = { "Priya" }, category = { CategoryType.SANITY, CategoryType.SCP_LOGIN })
    @Test(priority = 2, description = "To verify the Forget Username name with valid and invalid email.")
    public void resetUsernameUsingWrongEmail() {
        log.info("To verify that following should be displayed on validate Forgot Username.");
        SoftAssert softAssert = new SoftAssert();
        // Init Forget Username Steps
        forgetUsernameSteps = new ForgetUsernameSteps(driver);
        // Redirect to Forget Username Steps
        forgetUsernameSteps.verifyForgotUsernameInObject(softAssert);
        // invalid Email verification 
        forgetUsernameSteps.enterInvalidEmail();
        log.info("Test Case execution for - resetUsernameInvalidAccountOrEmail - is Completed.");                  
      }
	
  }
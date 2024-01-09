package sew.ai.tests.scp;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.helpers.testrail.TestRail;
import sew.ai.runner.TestRunner;
import sew.ai.steps.scp.LoginSteps;
import sew.ai.steps.scp.ProblemSigningInSteps;
import sew.ai.utils.DataBaseUtils;

public class ProblemSigningInTest extends TestRunner {
	
    private static final Logger log = LogManager.getLogger(ProblemSigningInTest.class);   
    private ProblemSigningInSteps problemSigningInSteps;
    
    
    @TestRail(testCaseId = {74989, 74990, 74991, 74992, 74993, 74996 })
    @FrameworkAnnotations(author = {"Varad N"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the login page objects.")
    public void validateProblemSignInPage() {
    	log.info("Test Case execution for - validateProblemSignInPage - is Initiated");
        SoftAssert softAssert = new SoftAssert();
       //  Init login steps
        problemSigningInSteps = new ProblemSigningInSteps(driver);
      //   Redirect to Problem Signing In
        problemSigningInSteps.verifyProblemSigningInObject(softAssert);
       //  Submit Blank Problem Signing In page 
        problemSigningInSteps.verifyProblemSigningInForm();        
        log.info("Test Case execution for - validateProblemSignInPage - is Completed.");
     }
    
    @TestRail(testCaseId = {75001 ,75002 ,75003 ,74994})
    @FrameworkAnnotations(author = {"Varad N"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the login page objects.")
    public void verifyProblemSignInFieldValidations() {
    	log.info("Test Case execution for - verifyProblemSignInFieldValidations - is Initiated");
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        problemSigningInSteps = new ProblemSigningInSteps(driver);
        // Redirect to Problem Signing In
        problemSigningInSteps.verifyProblemSigningInObject(softAssert);
       //Submit Blank Problem Signing In page 
        problemSigningInSteps.verifyProblemSigningInFormwithValidData();       
        log.info("Test Case execution for - verifyProblemSignInFieldValidations - is Completed.");
     }
        
    @TestRail(testCaseId = {74997})
    @FrameworkAnnotations(author = {"Varad N"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the login page objects.")
    public void verifyProbSignInReqForInactiveUser() {
    	log.info("Test Case execution for - verifyProbSignInReqForInactiveUser - is Initiated");
    	
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        problemSigningInSteps = new ProblemSigningInSteps(driver);
        // Redirect to Problem Signing In
        problemSigningInSteps.verifyProblemSigningInObject(softAssert);
        // Get Inactive account Info
        problemSigningInSteps.verirySubmitInactiveAccountInfo();
        log.info("Test Case execution for - verifyProbSignInReqForInactiveUser - is Completed.");
     }
        
    @TestRail(testCaseId = {74998, 74999})
    @FrameworkAnnotations(author = {"Varad N"}, category = {CategoryType.SANITY})
    @Test(priority = 1, description = "To verify the login page objects.")
    public void verifyMinMaxValueProblemsSignIn() {
    	log.info("Test Case execution for - verifyMinMaxValueProblemsSignIn - is Initiated");    	
        SoftAssert softAssert = new SoftAssert();
        // Init login steps
        problemSigningInSteps = new ProblemSigningInSteps(driver);
        // Redirect to Problem Signing In
        problemSigningInSteps.verifyProblemSigningInObject(softAssert);
        // Verify 
        problemSigningInSteps.verifyMinMaxValueOfTextBox(softAssert);        
        log.info("Test Case execution for - verifyMinMaxValueProblemsSignIn - is Completed.");
     }
}

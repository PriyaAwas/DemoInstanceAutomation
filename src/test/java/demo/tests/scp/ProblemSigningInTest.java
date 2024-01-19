package demo.tests.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.runner.TestRunner;
import demo.steps.scp.ProblemSigningInSteps;

public class ProblemSigningInTest extends TestRunner {

	private static final Logger log = LogManager.getLogger(ProblemSigningInTest.class);
	private ProblemSigningInSteps problemSigningInSteps;

	
	  @FrameworkAnnotations(author = {"Priya Awasthi"}, category =
	  {CategoryType.SANITY})
	  
	  @Test(priority = 1, description = "To verify the Problem SignIn Objects.")
	  public void validateProblemSignInPage() {
	  log.info("Test Case execution for - validateProblemSignInPage - is Initiated"
	  ); SoftAssert softAssert = new SoftAssert(); // Init login steps
	  problemSigningInSteps = new ProblemSigningInSteps(driver); // Redirect to Problem Signing In
	  problemSigningInSteps.verifyProblemSigningInObject(softAssert); log.
	  info("Test Case execution for - validateProblemSignInPage - is Completed.");
	  }
		  @FrameworkAnnotations(author = {"Priya Awasthi"}, category =
		  {CategoryType.SANITY})
		  
		  @Test(priority = 2, description =
		  "To verify the Problem SignIn form with InvalidData.") public void
		  verifyProblemSignInFeildInvalidValidations() {
		  log.info("Test Case execution for - validateProblemSignInPage - is Initiated"
		  ); SoftAssert softAssert = new SoftAssert(); // Init login steps
		  problemSigningInSteps = new ProblemSigningInSteps(driver); // Redirect to Problem Signing In
		  problemSigningInSteps.verifyProblemSigningInObject(softAssert); // Submit
		  //Blank Problem Signing In page
		  problemSigningInSteps.verifyProblemSigningInFormwithInValidData(); log.
		  info("Test Case execution for - validateProblemSignInPage - is Completed.");
		  }
		 

	@FrameworkAnnotations(author = { "Priya Awasthi" }, category = { CategoryType.SANITY })
	@Test(priority = 3, description = "To verify the Problem SignIn form with Valid Data.")
	public void verifyProblemSignInFieldValidations() {
		log.info("Test Case execution for - verifyProblemSignInFieldValidations - is Initiated");
		SoftAssert softAssert = new SoftAssert();
		// Init login steps
		problemSigningInSteps = new ProblemSigningInSteps(driver);
		// Redirect to Problem Signing In
		problemSigningInSteps.verifyProblemSigningInObject(softAssert);
		// Submit Blank Problem Signing In page
		problemSigningInSteps.verifyProblemSigningInFormwithValidData();
		log.info("Test Case execution for - verifyProblemSignInFieldValidations - is Completed.");
	}

	@FrameworkAnnotations(author = {"Priya Awasthi"}, category ={CategoryType.SANITY})
		  
		  @Test(priority = 4, description ="To verify the MinMaxValue for Problem SignIn form ")
	public void verifyMinMaxValueProblemsSignIn() { 

		  log.info("Test Case execution for - verifyMinMaxValueProblemsSignIn - is Initiated");
		  SoftAssert softAssert = new SoftAssert(); // Init login steps
		  problemSigningInSteps = new ProblemSigningInSteps(driver); // Redirect to Problem Signing In
		  problemSigningInSteps.verifyProblemSigningInObject(softAssert); // Verify
		  problemSigningInSteps.verifyMinMaxValueOfTextBox(softAssert); 
		  log.info("Test Case execution for - verifyMinMaxValueProblemsSignIn - is Completed."
		  );	 
}

}

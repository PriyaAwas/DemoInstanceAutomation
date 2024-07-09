package demo.tests.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import demo.steps.scp.PreLogContactUsSteps;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.runner.TestRunner;


public class PreLogContactUsTests extends TestRunner {

	private static final Logger log = LogManager.getLogger(PreLogContactUsTests.class);
	private PreLogContactUsSteps preLogContactUsSteps;
	
	@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SMOKE,CategoryType.SANITY, CategoryType.SCP_CONNECTME, })
	@Test(priority = 1, description = "To verify the Post Login ContactUs Page objects.")

	public void verifyPreLogConnectMeObject() throws InterruptedException {
		log.info("Verify Contact us page objects");
		SoftAssert softAssert = new SoftAssert();
		preLogContactUsSteps = new PreLogContactUsSteps(driver);
		preLogContactUsSteps.verifyPreLogConnectMeObject(softAssert);
		log.info("Contact Us Page Objects Validated Successfully");
	}

	@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SMOKE, CategoryType.SCP_CONNECTME, })
	@Test(priority = 2, description = "To verify the Post Login ContactUs Page objects.")

	public void verifyPreConnectMeFAQObject() throws InterruptedException {
		log.info("Verify Contact us page FAQ objects");
		SoftAssert softAssert = new SoftAssert();
		preLogContactUsSteps = new PreLogContactUsSteps(driver);
		preLogContactUsSteps.verifyPreConnectMeFAQObject(softAssert);
		log.info("Contact Us Page FQA Objects Validated Successfully");
	}

	@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SMOKE, CategoryType.SCP_CONNECTME, })
	@Test(priority = 3, description = "To verify the Post Login ContactUs Page objects.")

	public void verifySocialMediaObject() throws InterruptedException {
		log.info("Verify Social Media tab Objects");
		SoftAssert softAssert = new SoftAssert();
		preLogContactUsSteps = new PreLogContactUsSteps(driver);
		preLogContactUsSteps.verifySocialMediaObject(softAssert);
		log.info("Social Media Tab Objects Validated Successfully");
	}
	
	@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SMOKE, CategoryType.SCP_CONNECTME, })
	@Test(priority = 4, description = "To verify the Post Login ContactUs Page objects.")

	public void verifyCreatePreContactRequest() throws InterruptedException {
		log.info("Verify Create Request form functionality");
		SoftAssert softAssert = new SoftAssert();
		preLogContactUsSteps = new PreLogContactUsSteps(driver);
		preLogContactUsSteps.verifyCreatePreContactRequest(softAssert);
		log.info("Contact Request form submitted successfully");
	}
	
	@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SANITY, CategoryType.SCP_CONNECTME, })
	@Test(priority = 5, description = "To verify the Post Login ContactUs Page objects.")

	public void verifySubmitBlankFormOnContactUs() throws InterruptedException {
		log.info("Verify Submit blank request form Functionality");
		SoftAssert softAssert = new SoftAssert();
		preLogContactUsSteps = new PreLogContactUsSteps(driver);
		preLogContactUsSteps.verifySubmitBlankForm();
		log.info("Submit Request form succesfully submitted");
	}

	@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SANITY, CategoryType.SCP_CONNECTME, })
	@Test(priority = 7, description = "To verify the Post Login ContactUs Page objects.")

	public void verifyCreateBillingRequestWithAttachment() throws InterruptedException {
		log.info("Verify Billing rquest submission");
		SoftAssert softAssert = new SoftAssert();
		preLogContactUsSteps = new PreLogContactUsSteps(driver);
		preLogContactUsSteps.verifyCreateBillingRequestWithAttachment(softAssert);
		log.info("Billing request form submitted successfully");
	}

	@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SANITY, CategoryType.SCP_CONNECTME, })
	@Test(priority = 8, description = "To verify the Post Login ContactUs Page objects.")

	public void verifySavedFormObject() throws InterruptedException {
		log.info("Verify Saved forms object.");
		SoftAssert softAssert = new SoftAssert();
		preLogContactUsSteps = new PreLogContactUsSteps(driver);
		preLogContactUsSteps.verifySavedFormObject(softAssert);
		log.info("Billing request form submitted successfully");
	}
	@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SANITY, CategoryType.SCP_CONNECTME, })
	@Test(priority = 9, description = "To verify the Post Login ContactUs Page objects.")

	public void verifyDropdowntopics() throws InterruptedException {
		log.info("Verify Drop down topics");
		SoftAssert softAssert = new SoftAssert();
		preLogContactUsSteps = new PreLogContactUsSteps(driver);
		preLogContactUsSteps.verifyDropdowntopics(softAssert);
		log.info("Drop down topics validated successfully succesfully");
	}

	@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SANITY, CategoryType.SCP_CONNECTME, })
	@Test(priority = 10, description = "To verify the Post Login ContactUs Page objects.")

	public void verifyTrackContactRequests() throws InterruptedException {
		log.info("Verify Track requests functionality");
		SoftAssert softAssert = new SoftAssert();
		preLogContactUsSteps = new PreLogContactUsSteps(driver);
		preLogContactUsSteps.verifyTrackContactRequests(softAssert);
		log.info("Track Request validated succesfully");
	}	
	
	@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SANITY, CategoryType.SCP_CONNECTME, })
	@Test(priority = 10, description = "To verify the Post Login ContactUs Page objects.")

	public void verifySavedformsfunctionality() throws InterruptedException {
		log.info("Verify saved forms topics");
		SoftAssert softAssert = new SoftAssert();
		preLogContactUsSteps = new PreLogContactUsSteps(driver);
		preLogContactUsSteps.verifySavedformsfunctionality(softAssert);
		log.info("Saved forms functionality validated successfully ");
	}


	

}

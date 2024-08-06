package demo.tests.scp;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import demo.steps.scp.ReportWastageSteps;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.runner.TestRunner;



public class ReportwastageTests extends TestRunner {

	private static final Logger log = LogManager.getLogger(ReportwastageTests.class);
	private ReportWastageSteps reportwastages;
	
	@FrameworkAnnotations(author = { "Aradhana" }, category = { CategoryType.SMOKE,CategoryType.SANITY, CategoryType.SCP_CONNECTME, })
	@Test(priority = 1, description = "To verify the Post Login ContactUs Page objects.")
	
	
	public void VerifyReportwasteformsubmission() throws InterruptedException {
		log.info("Verify Contact us page objects");
		SoftAssert softAssert = new SoftAssert();
		reportwastages = new ReportWastageSteps(driver);
		reportwastages.VerifyReportwasteformsubmission(softAssert);
		log.info("Contact Us Page Objects Validated Successfully");
	}}
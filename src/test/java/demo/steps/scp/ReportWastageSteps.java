package demo.steps.scp;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import demo.pageobjects.PostLogConnectUsPage;
import demo.pageobjects.PreLogContactUsPage;
import demo.pageobjects.ReportwastagePage;
import demo.pageobjects.PreLogContactUsPage;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.models.User;
import sew.ai.config.Configuration;
import sew.ai.config.SCPConfiguration;
import sew.ai.helpers.props.Constants;
import sew.ai.utils.PropertiesUtil;
import static sew.ai.helpers.props.FilePaths.FILE_UPLOAD_PATH;

public class ReportWastageSteps extends ReportwastagePage {

	private static final Logger log = LogManager.getLogger(ProblemSigningInSteps.class);
	public static PropertiesUtil PostLogConnectMeTextProp;
	String referenceId = null;
	String accountNo = null;
	String custmerName = null;
	String email = null;

	public ReportWastageSteps(WebDriver driver) {
		super(driver);
		PostLogConnectMeTextProp = new PropertiesUtil(
				FilePaths.SCP_TEXT_PROPERTIES + Constants.POST_LOG_CONNECT_ME_TXT_FILENAME);
	}
	
	public void VerifyReportwasteformsubmission(SoftAssert softAssert) throws InterruptedException {
		clickReportLink();
		isDropdownIsVisible("Report Water Waste");
			isDropdownIsVisible("Report Water Theft");

		if (selectlstConnectMeOptions("Report Water Waste")) {
		}
		if (isCustomerNameTxtVisible()) {
			populateCustomername("John");
			

		if (isServiceAccNoTxtVisible()) {
			String acc = Configuration.toString("accountNumber");
			populateAccNum(acc);
	}
		if (isEmailAddressTxtVisible()) {
			populateEmailadd("DemoInstance@mailinator.com");

		}
		if (isAddressVisible()) {
			populateaddress(" Jber,99505");
			
		}
		if (istxtSubjectTxtVisible()) {
			populateSubject("Testing");
		}
		if (isCommentsTxtVisible()) {
			populateComments("Testing");
		}
		if (isChooseFileBtnVisible()) {
			String validAttachmentFileName = "index.html";
			addAttachmentToChooseFile(FILE_UPLOAD_PATH + validAttachmentFileName);
		softAssert.assertEquals(PostLogConnectMeTextProp.getPropValue("blankTrackRequest"), getToastErrorMessage());
		}
		if (isChooseFileBtnVisible()) {
			String validAttachmentFileName = "meter-reading.jpg";
			addAttachmentToChooseFile(FILE_UPLOAD_PATH + validAttachmentFileName);
			addAttachmentToChooseFile(FILE_UPLOAD_PATH + validAttachmentFileName);
		}
			
		}
		btnClickNext();
		isReviewPageHeaderVisible();
		scrollToElement(Submitbtn);
		clickSubmit();
		isConfirmationtxtVisible();
		
		ExtentLogger.logInfo("Report Wastage page succefully validated and submitted a form with invalid file error step");
	// 
	

	}}
package demo.steps.scp;

import static sew.ai.helpers.props.FilePaths.FILE_UPLOAD_PATH;

import java.util.ArrayList;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import demo.pageobjects.BillingQueriesPage;
import net.bytebuddy.agent.builder.AgentBuilder.Identified.Extendable;
import sew.ai.config.Configuration;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.utils.PropertiesUtil;

public class BillingQueriesSteps extends BillingQueriesPage {
	private static final Logger log = LogManager.getLogger(BillingQueriesSteps.class);
	public static PropertiesUtil PostLogConnectMeTextProp;
	private PostLogContactUsSteps postLogContactUsSteps;

	public BillingQueriesSteps(WebDriver driver) {

		super(driver);
		PostLogConnectMeTextProp = new PropertiesUtil(
				FilePaths.SCP_TEXT_PROPERTIES + Constants.POST_LOG_CONNECT_ME_TXT_FILENAME);

		// TODO Auto-generated constructor stub
	}

	/**
	 * This method verifies Billing dropdown elements.
	 *
	 * @param softAssert verify all the assertions
	 * @throws InterruptedException
	 */
	public void verifyBillinqueruiesContactUs(SoftAssert softAssert) throws InterruptedException {

		pause(5000);
		// Verify the ContactUs form is visible
		softAssert.assertTrue(isContactUsVisible(), "Contact us page is not visible");

		postLogContactUsSteps = new PostLogContactUsSteps(driver);
		String topic = postLogContactUsSteps.getSelectedValueInDropBox();
		softAssert.assertEquals(topic, "Billing");
		softAssert.assertTrue(postLogContactUsSteps.isAccountNumberVisible());
		softAssert.assertTrue(postLogContactUsSteps.isCustomerNameTxtVisible());
		softAssert.assertTrue(istxtEmailFieldVisible(), "Email field isnt available");
		clearEmailField();
		populateEmail(Configuration.toString("demoEmailId"));
		softAssert.assertTrue(istxtSubjectFieldVisible(), "Subject field is not visible");
		populateSubject("testing");
		softAssert.assertTrue(isCommentsFieldVisible(), "Comment field is not visible");
		clickNext();
		// validated error msg for the comment
		String errMsgComment = getlblGenericErrorMessage();
		Assert.assertEquals(errMsgComment, PostLogConnectMeTextProp.getPropValue("txtCommentBoxErrorMsg"));

		ExtentLogger.logInfo("Error message validated for Blank Comment feild ");

		// validated error msg for the Subject
		clearSubjectField();
		populateComments("Billing Query Created");

		clickNext();
		Assert.assertEquals(getlblGenericErrorMessage(),
				PostLogConnectMeTextProp.getPropValue("txtSubjectBoxErrorMsg"));

		ExtentLogger.logInfo("Error message validated for Blank Subject feild ");

	}

	public void submitBillingqueries(SoftAssert softAssert) throws InterruptedException {
		postLogContactUsSteps = new PostLogContactUsSteps(driver);
		String Topic = postLogContactUsSteps.getSelectedValueInDropBox();
		softAssert.assertEquals(Topic, "Billing");
		softAssert.assertTrue(postLogContactUsSteps.isAccountNumberVisible());
		String Accno = postLogContactUsSteps.getAccountNumber();
		softAssert.assertTrue(postLogContactUsSteps.isCustomerNameVisible());
		String Custname = postLogContactUsSteps.getCustomerName();
		softAssert.assertTrue(istxtEmailFieldVisible(), "Email field isnt visible");
		clearEmailField();
		populateEmail(Configuration.toString("demoEmailId"));
		String Email = postLogContactUsSteps.getEmailAddressLabel();
		softAssert.assertTrue(istxtSubjectFieldVisible(), "Subject field isnt visible");
		clearSubjectField();
		String Sub = populateSubject("Testing");
		softAssert.assertTrue(isCommentsFieldVisible(), "Comments field isnt visible");
		clearCommentsField();
		String Comment = populateComments("Billing");
		String validAttachmentFileName = "meter-reading.jpg";
		addAttachmentToChooseFile(FILE_UPLOAD_PATH + validAttachmentFileName);
		validatecurrentdate();
		clickNext();

		pause(5000);
		ArrayList<String> Submittedvalues = new ArrayList<String>(8);
		Submittedvalues.add(Topic.trim());
		Submittedvalues.add(Accno.trim());
		Submittedvalues.add(Custname.trim());
		Submittedvalues.add(Email.trim());
		Submittedvalues.add(Sub.trim());
		Submittedvalues.add(Comment.trim());
		Submittedvalues.add(validAttachmentFileName.trim());

		pause(5000);
		String revTopic = ReviewTopicVal().trim();
		String revAcc = ReviewAccVal().trim();
		String revCustname = ReviewCustname().trim();
		String revEmail = ReviewEmail().trim();
		String revSub = ReviewSub().trim();
		String revComment = ReviewComments().trim();

		String revAttachment = ReviewAttachment().trim();
		ArrayList<String> RevValues = new ArrayList<String>(8);
		RevValues.add(revTopic.trim());
		RevValues.add(revAcc.trim());
		RevValues.add(revCustname.trim());
		RevValues.add(revEmail.trim());
		RevValues.add(revSub.trim());
		RevValues.add(revComment.trim());
		RevValues.add(revAttachment.trim());

		if (Submittedvalues.equals(RevValues) == true) {
			ExtentLogger.logInfo("Field values are validated ");
		}
		pause(2000);
		clickSubmit();
		String popupContent = postLogContactUsSteps.getlblPopupThankYou().trim();
		String referenceId = popupContent.substring(popupContent.indexOf(":") + 1).trim();
		String submsnmsg = getSubmsnMsg();
		Assert.assertEquals(submsnmsg, PostLogConnectMeTextProp.getPropValue("lblThankYouForWriting"));
		pause(2000);
		clickOK();
		pause(5000);
		clickTrackReq();
		ExtentLogger.logInfo("Navigated to Track Req tab successfully");
		pause(2000);
		populateTrackReq(referenceId);
		pause(2000);
		clickTrackReq();
		ExtentLogger.logInfo("Reqid is populated in the search field");
	}

	public void submitInvalidAttachment(SoftAssert softAssert) throws InterruptedException {
		postLogContactUsSteps = new PostLogContactUsSteps(driver);
		String Topic = postLogContactUsSteps.getSelectedValueInDropBox();
		softAssert.assertEquals(Topic, "Billing");
		softAssert.assertTrue(postLogContactUsSteps.isAccountNumberVisible());
		softAssert.assertTrue(postLogContactUsSteps.isCustomerNameVisible());
		softAssert.assertTrue(istxtEmailFieldVisible(), "Email field isnt visible");
		clearEmailField();
		populateEmail(Configuration.toString("demoEmailId"));
		postLogContactUsSteps.getEmailAddressLabel();
		softAssert.assertTrue(istxtSubjectFieldVisible(), "Subject field isnt visible");
		clearSubjectField();
		populateSubject("Testing");
		softAssert.assertTrue(isCommentsFieldVisible(), "Comments field isnt visible");
		clearCommentsField();
		populateComments("Billing");
		String validAttachmentFileName = "Flower 6MB.gif";
		addAttachmentToChooseFile(FILE_UPLOAD_PATH + validAttachmentFileName);
		clickNext();
		String uploadInvalidfilesize = getToastMessage();
		Assert.assertEquals(uploadInvalidfilesize, PostLogConnectMeTextProp.getPropValue("invalidFileType"));
		waitForToastMessageInvisibility();
		ExtentLogger.logInfo("Validated error message for invalid file upload");
	}
}

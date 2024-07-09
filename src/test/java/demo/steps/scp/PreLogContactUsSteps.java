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
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.models.User;
import sew.ai.config.Configuration;
import sew.ai.config.SCPConfiguration;
import sew.ai.helpers.props.Constants;
import sew.ai.utils.PropertiesUtil;
import static sew.ai.helpers.props.FilePaths.FILE_UPLOAD_PATH;

public class PreLogContactUsSteps extends PreLogContactUsPage {

	private static final Logger log = LogManager.getLogger(ProblemSigningInSteps.class);
	public static PropertiesUtil PostLogConnectMeTextProp;
	String referenceId = null;
	String accountNo = null;
	String custmerName = null;
	String email = null;

	public PreLogContactUsSteps(WebDriver driver) {
		super(driver);
		PostLogConnectMeTextProp = new PropertiesUtil(
				FilePaths.SCP_TEXT_PROPERTIES + Constants.POST_LOG_CONNECT_ME_TXT_FILENAME);
	}

	public void verifySubmitBlankForm() throws InterruptedException {
		clickContactusLink();
		scrollPageToElement(btnContactUsNext);
		btnClickNext();
		String SuccessToasterMsg = getToastMessage();
		Assert.assertEquals(SuccessToasterMsg, PostLogConnectMeTextProp.getPropValue("toasterMsgBlankForm"));
		populateSubject("Testing");
		btnClickNext();
		String errMsgComment = getToastErrorMessage();
		Assert.assertEquals(errMsgComment, PostLogConnectMeTextProp.getPropValue("toasterMsgBlankForm"));
		clearSubjectField();
		populateComments("Testing");
		btnClickNext();
		String errMsgSubject = getToastErrorMessage();
		Assert.assertEquals(errMsgSubject, PostLogConnectMeTextProp.getPropValue("toasterMsgBlankForm"));
		ExtentLogger.logInfo("Blank form submission and error msg validated successfully ");
	}

	public void verifyPreLogConnectMeObject(SoftAssert softAssert) throws InterruptedException {
		clickContactusLink();
		pause(1000);
		selectlstConnectMeOptions("Rebates");
		if (selectlstConnectMeOptions("Rebates")) {
		}
		softAssert.assertTrue(
				isPreLogConnectMePage(PostLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
						(PostLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),
				"Page Title & URL does not Match");
		softAssert.assertEquals(isPageHeaderPostVisible(), "Contact Us Page Header is not visible");
		softAssert.assertTrue(isSocialmediaVisible(), "Social Media Tab is not visibility");
		softAssert.assertTrue(isContactusVisible(), "Contact Us Tab is not visibility");
		softAssert.assertTrue(isTrackRequestVisible(), "Track Request Tab is not visibility");
		softAssert.assertTrue(isSavedFormVisible(), "Saved Form tab is not visibility");
		softAssert.assertTrue(isSubmitBtnVisible(), "Submit button is not visibility");
		softAssert.assertTrue(isNextBtnVisible(), "Next button is not visibility");
		softAssert.assertTrue(isCustomerNameTxtVisible(), "Customer Name text Box is not visibility");
		softAssert.assertTrue(isServiceAccNoTxtVisible(), "Service Acc No text Box is not visibility");
		softAssert.assertTrue(isEmailAddressTxtVisible(), "Email Address text Box is not visibility");
		scrollPageToElement(txtSubject);
		softAssert.assertTrue(istxtSubjectTxtVisible(), "Subject button is text Box visibility");
		softAssert.assertTrue(isCommentsTxtVisible(), "Comments text Box is not visibility");
		softAssert.assertTrue(isChooseFileBtnVisible(), "Choose File text Box is not visibility");
		List<String> list = getdropdownOptions();
		ExtentLogger.logInfo("Contact Request page contents validated successfully ");
	}

	public void verifyCreatePreContactRequest(SoftAssert softAssert) throws InterruptedException {
		clickContactusLink();
		selectlstConnectMeOptions("Rebates");
		if (selectlstConnectMeOptions("Rebates")) {
		}

		if (isServiceAccNoTxtVisible()) {
			String acc = Configuration.toString("accountNumber");
			populateAccNum(acc);

		}
		if (isCustomerNameTxtVisible()) {
			populateCustomername("John");

		}
		if (isEmailAddressTxtVisible()) {
			populateEmailadd("DemoInstance@mailinator.com");

		}
		if (isChooseFileBtnVisible()) {
			String validAttachmentFileName = "meter-reading.jpg";
			addAttachmentToChooseFile(FILE_UPLOAD_PATH + validAttachmentFileName);
			addAttachmentToChooseFile(FILE_UPLOAD_PATH + validAttachmentFileName);
		}
		if (istxtSubjectTxtVisible()) {
			populateSubject("Testing");
		}
		if (isCommentsTxtVisible()) {
			populateComments("Testing");
		}
		btnClickNext();
		isReviewPageHeaderVisible();
		clickSubmit();
		isConfirmationtxtVisible();
		ExtentLogger.logInfo("Request form submission functionality validated successfully ");
	}

	public void verifyPreConnectMeFAQObject(SoftAssert softAssert) throws InterruptedException {
		clickContactusLink();
		scrollToTheTopOfPage();
		softAssert.assertTrue(isfaqAccountTabVisible(), "Account Tab is not visible");
		softAssert.assertTrue(isfaqBillingTabVisible(), "Billing Tab is not visibility");
		softAssert.assertTrue(isfaqregistrationTabVisible(), "Registration Tab is not visibility");
		softAssert.assertTrue(isfaqHomeTabVisible(), "Home Tab is not visibility");
		softAssert.assertTrue(isfaqOutageTabVisible(), "Outage Tab is not visibility");
		// softAssert.assertTrue(isfaqServiceTabVisible(), "Service Tab is not
		// visibility");
		softAssert.assertTrue(isfaqTopTabVisible(), "Top Tab is not visibility");
		softAssert.assertTrue(isfaqUsageTabVisible(), "Usage Tab is not visibility");
		// softAssert.assertTrue(isfaqWaysToSaveTabVisible(), "Ways To Save Tab is not
		// visibility");
		// Click on Account FAQ
		clickfaqAccountTab();
		pause(1000);
		isFAQPageTopicVisible();
		String lblaccount = getFAQPageTopic();
		Assert.assertEquals(lblaccount, "Account");
		clickAccPaneldropdown();
		clickFAQPageHelp();
		pause(1000);
		softAssert.assertTrue(
				isPreLogConnectMePage(PostLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
						(PostLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),
				"Page Title & URL does not Match");
		ExtentLogger.logInfo("Account FAQ cards validated successfully ");
		// Click on Blling FAQ
		clickfaqBillingTab();
		pause(1000);
		isFAQPageTopicVisible();
		String lblBilling = getFAQPageTopic();
		Assert.assertEquals(lblBilling, "Billing");
		clickBillingPaneldropdown();
		clickFAQPageHelp();
		pause(1000);
		softAssert.assertTrue(
				isPreLogConnectMePage(PostLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
						(PostLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),
				"Page Title & URL does not Match");
		ExtentLogger.logInfo("Billing FAQ cards validated successfully ");

		// Click on Registration FAQ
		clickfaqregistrationTab();
		pause(1000);
		isFAQPageTopicVisible();
		String lblRegistration = getFAQPageTopic();
		Assert.assertEquals(lblRegistration, "Customer Registration");
		clickCustRegdropdown();
		clickFAQPageHelp();
		pause(1000);
		softAssert.assertTrue(
				isPreLogConnectMePage(PostLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
						(PostLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),
				"Page Title & URL does not Match");
		ExtentLogger.logInfo("Customer Registration FAQ cards validated successfully ");

		// Click on Home FAQ
		clickfaqHomeTab();
		pause(1000);
		isFAQPageTopicVisible();
		String lblHome = getFAQPageTopic();
		Assert.assertEquals(lblHome, "Home");
		clickPaneldropdown();
		clickFAQPageHelp();
		pause(1000);
		softAssert.assertTrue(
				isPreLogConnectMePage(PostLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
						(PostLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),
				"Page Title & URL does not Match");
		ExtentLogger.logInfo("Home FAQ cards validated successfully ");

		// Click on Outage FAQ
		clickfaqOutageTab();
		pause(1000);
		isFAQPageTopicVisible();
		String lblOutage = getFAQPageTopic();
		Assert.assertEquals(lblOutage, "Outage");
		clickOutagedropdown();
		clickFAQPageHelp();
		pause(1000);
		softAssert.assertTrue(
				isPreLogConnectMePage(PostLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
						(PostLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),
				"Page Title & URL does not Match");
		ExtentLogger.logInfo("Outage FAQ cards validated successfully ");

		// Click on Top FAQ
		clickfaqTopTab();
		pause(1000);
		isFAQPageTopicVisible();
		String lblTop = getFAQPageTopic();
		Assert.assertEquals(lblTop, "Top FAQs");
		clickFQAPaneldropdown();
		clickFAQPageHelp();
		pause(1000);
		softAssert.assertTrue(
				isPreLogConnectMePage(PostLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
						(PostLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),
				"Page Title & URL does not Match");
		ExtentLogger.logInfo("Top FAQ cards validated successfully ");

		// Click on Usage FAQ
		clickfaqUsageTab();
		pause(1000);
		isFAQPageTopicVisible();
		String lblUsage = getFAQPageTopic();
		Assert.assertEquals(lblUsage, "Usage");
		clickUsagePaneldropdown();
		clickFAQPageHelp();
		pause(1000);
		softAssert.assertTrue(
				isPreLogConnectMePage(PostLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
						(PostLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),
				"Page Title & URL does not Match");
		ExtentLogger.logInfo("Usage FAQ cards validated successfully ");
		scrollPageToElement(CustomerServHeader);
		isCustomerservVisible();
		isHelplineemailaddVisible();
		isHelplineNoVisible();
		ExtentLogger.logInfo("Help line FAQ cards validated successfully ");

	}

	public void verifySocialMediaObject(SoftAssert softAssert) throws InterruptedException {
		clickContactusLink();
		softAssert.assertTrue(
				isPreLogConnectMePage(PostLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
						(PostLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),
				"Page Title & URL does not Match");
		softAssert.assertEquals(isPageHeaderPostVisible(), "Contact Us Page Header is not visible");
		clickSocialMedia();
		softAssert.assertTrue(isFacebookTabVisible(), "Facebook Tab is not visible");
		softAssert.assertTrue(isYouTubeTabVisible(), "YouTube Tab is not visibility");
		softAssert.assertTrue(isTwitterTabVisible(), "Twitter Tab is not visibility");
		softAssert.assertTrue(isInstagramTabVisible(), "Instagram Tab is not visibility");
		ExtentLogger.logInfo("Visibility of Social media tabs validated successfully ");
		// Click on Facebook Tab
		clickFacebookTab();
		// Click on Twitter Tab
		clickTwitterTab();
		// Click on Youtube Tab
		clickYouTubeTab();
		clickInstagramTab();
		scrollToTheTopOfPage();
		ExtentLogger.logInfo("Clicked Social media successfully ");
		softAssert.assertTrue(isfaqAccountTabVisible(), "Account Tab is not visible");
		softAssert.assertTrue(isfaqBillingTabVisible(), "Billing Tab is not visibility");
		softAssert.assertTrue(isfaqregistrationTabVisible(), "Registration Tab is not visibility");
		softAssert.assertTrue(isfaqHomeTabVisible(), "Home Tab is not visibility");
		softAssert.assertTrue(isfaqOutageTabVisible(), "Outage Tab is not visibility");
		softAssert.assertTrue(isfaqTopTabVisible(), "Top Tab is not visibility");
		softAssert.assertTrue(isfaqUsageTabVisible(), "Usage Tab is not visibility");

		// Click on Account FAQ
		clickfaqAccountTab();
		pause(3000);
		isFAQPageTopicVisible();
		String lblaccount = getFAQPageTopic();
		Assert.assertEquals(lblaccount, "Account");
		clickFAQPageHelp();
		pause(3000);
		softAssert.assertTrue(
				isPreLogConnectMePage(PostLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
						(PostLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),
				"Page Title & URL does not Match");
		clickfaqBillingTab();
		pause(3000);
		isFAQPageTopicVisible();
		String lblBilling = getFAQPageTopic();
		Assert.assertEquals(lblBilling, "Billing");
		clickFAQPageHelp();
		pause(3000);
		softAssert.assertTrue(
				isPreLogConnectMePage(PostLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
						(PostLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),
				"Page Title & URL does not Match");
		clickfaqregistrationTab();
		pause(3000);
		isFAQPageTopicVisible();
		String lblRegistration = getFAQPageTopic();
		Assert.assertEquals(lblRegistration, "Customer Registration");
		clickFAQPageHelp();
		pause(3000);
		softAssert.assertTrue(
				isPreLogConnectMePage(PostLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
						(PostLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),
				"Page Title & URL does not Match");
		// Click on Home FAQ
		clickfaqHomeTab();
		pause(3000);
		isFAQPageTopicVisible();
		String lblHome = getFAQPageTopic();
		Assert.assertEquals(lblHome, "Home");
		clickFAQPageHelp();
		pause(3000);
		softAssert.assertTrue(
				isPreLogConnectMePage(PostLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
						(PostLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),
				"Page Title & URL does not Match");
		// Click on Outage FAQ
		clickfaqOutageTab();
		pause(3000);
		isFAQPageTopicVisible();
		String lblOutage = getFAQPageTopic();
		Assert.assertEquals(lblOutage, "Outage");
		clickFAQPageHelp();
		pause(3000);
		softAssert.assertTrue(
				isPreLogConnectMePage(PostLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
						(PostLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),
				"Page Title & URL does not Match");
		// Click on Top FAQ
		clickfaqTopTab();
		pause(3000);
		isFAQPageTopicVisible();
		String lblTop = getFAQPageTopic();
		Assert.assertEquals(lblTop, "Top FAQs");
		pause(3000);
		softAssert.assertTrue(
				isPreLogConnectMePage(PostLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
						(PostLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),
				"Page Title & URL does not Match");
		ExtentLogger.logInfo("FAQ cards validated successfully ");

	}

	public void verifyCreateBillingRequestWithAttachment(SoftAssert softAssert) throws InterruptedException {
		clickContactusLink();
		selectlstConnectMeOptions("Billing");
		if (selectlstConnectMeOptions("Billing")) {
		}
		if (isServiceAccNoTxtVisible()) {
			String acc = Configuration.toString("accountNumber");
			populateAccNum(acc);

		}
		if (isCustomerNameTxtVisible()) {
			populateCustomername("John Wick");

		}
		if (isEmailAddressTxtVisible()) {
			populateEmailadd("DemoInstance@mailinator.com");

		}
		if (isChooseFileBtnVisible()) {
			String validAttachmentFileName = "meter-reading.jpg";
			addAttachmentToChooseFile(FILE_UPLOAD_PATH + validAttachmentFileName);
		}
		if (istxtSubjectTxtVisible()) {
			populateSubject("Testing");
		}
		if (isCommentsTxtVisible()) {
			populateComments("Testing");
		}
		btnClickNext();
		isReviewPageHeaderVisible();
		scrollToElement(Submitbtn);
		clickSubmit();
		isConfirmationtxtVisible();
		softAssert.assertEquals(isConfirmationtxtVisible(),
				PostLogConnectMeTextProp.getPropValue("lblThankYouForWriting"));
		clickOkBtn();
		ExtentLogger.logInfo("Billing request form submitted successfully ");
	}

	public void verifySavedFormObject(SoftAssert softAssert) {
		pause(1000);
		clickContactusLink();

		clickSavedForms();
		pause(1000);

		List<WebElement> savedFormsGridHeadersElements = listSavedFormsHeaders();
		int counter = 0;
		for (WebElement gridHeaderEle : savedFormsGridHeadersElements) {
			softAssert.assertEquals(gridHeaderEle.getText().toString().trim().contains(
					PostLogConnectMeTextProp.getPropValue("savedFormsGridHeaders").trim().toUpperCase()), true);
			counter++;
		}
		// Verifying sorting functionality.
		List<WebElement> gridHeaderTextElements = listSavedFormsHeaders();
		for (WebElement gridHeaderElement : gridHeaderTextElements) {
			String actGridHeaderClass = gridHeaderElement.getAttribute("class").trim();
			pause(200);
			if (!actGridHeaderClass.equals("sorting_disabled")) {

				pause(200);
				assertTrue(actGridHeaderClass.equals("sorting"));
				if (actGridHeaderClass.equals("sorting")) {
					click(gridHeaderElement);
					pause(1000);
					actGridHeaderClass = gridHeaderElement.getAttribute("class").trim();
					assertTrue(actGridHeaderClass != null && (actGridHeaderClass.equals("sorting sorting_asc")
							|| actGridHeaderClass.equals("sorting sorting_desc")));

					// assertTrue(actGridHeaderClass.equals("sorting_asc") ||
					// actGridHeaderClass.equals("sorting_desc"));
				}
				if (actGridHeaderClass.equals("sorting sorting_desc")) {
					pause(200);
					click(gridHeaderElement);
					pause(200);
					actGridHeaderClass = gridHeaderElement.getAttribute("class").trim();
					assertTrue(actGridHeaderClass.equals("sorting_asc"));
					click(gridHeaderElement);
					pause(200);
					actGridHeaderClass = gridHeaderElement.getAttribute("class").trim();
					assertTrue(actGridHeaderClass.equals("sorting_desc"));
				} else if (actGridHeaderClass.equals("sorting sorting_asc")) {
					click(gridHeaderElement);
					pause(200);
					actGridHeaderClass = gridHeaderElement.getAttribute("class").trim();
					assertTrue(actGridHeaderClass.equals("sorting sorting_desc"));
					click(gridHeaderElement);
					pause(200);
					actGridHeaderClass = gridHeaderElement.getAttribute("class").trim();
					assertTrue(actGridHeaderClass.equals("sorting sorting_asc"));
				} else {
					assertTrue(false, "Sorting is not working on column : " + gridHeaderElement.getText().trim());
					ExtentLogger.logInfo("Saved forms grid validated successfully ");
				}
			}
		}

	}

	public void verifyTrackContactRequests(SoftAssert softAssert) throws InterruptedException {
		clickContactusLink();
		pause(2000);
		clickTrackRequest();
		clickTrack();
		softAssert.assertEquals(PostLogConnectMeTextProp.getPropValue("blankTrackRequest"), getToastErrorMessage());
		populateReqID("CM-492208389");
		clickTrack();
		softAssert.assertEquals(getCasenum(), "CM-492208389");
		clickPrograms();
		isSubjectVisible();
		isDescriptionVisible();
		clickAttachments();
		isattachment1Visible();
		isattachment2Visible();
		isTextboxVisible();
		clickCloseIcon();
		ExtentLogger.logInfo("Track Request Page validated successfully ");
	}

	public void verifyDropdowntopics(SoftAssert softAssert) throws InterruptedException {
		clickContactusLink();
		waitForPageToLoad();
		selectlstConnectMeOptions("Billing");
		if (selectlstConnectMeOptions("Billing")) {
			Assert.assertTrue(isServiceAccNoTxtVisible());
			Assert.assertTrue(isCustomerNameTxtVisible());
			Assert.assertTrue(isEmailAddressTxtVisible());
			waitForPageToLoad();
			Assert.assertTrue(isChooseFilebtnVisible());
			Assert.assertTrue(istxtSubjectTxtVisible());
			Assert.assertTrue(isCommentsTxtVisible());
			ExtentLogger.logInfo("Billing Page validated successfully");

			selectlstConnectMeOptions("Programs");
			if (selectlstConnectMeOptions("Programs")) {
			}
			Assert.assertTrue(isServiceAccNoTxtVisible());
			Assert.assertTrue(isCustomerNameTxtVisible());
			Assert.assertTrue(isEmailAddressTxtVisible());
			Assert.assertTrue(isChooseFilebtnVisible());
			Assert.assertTrue(istxtSubjectTxtVisible());
			Assert.assertTrue(isCommentsTxtVisible());
			ExtentLogger.logInfo("Programs Page validated successfully ");

			scrollToElement(dropdown);
			selectlstConnectMeOptions("Others");
			if (selectlstConnectMeOptions("Others")) {
			}
			Assert.assertTrue(isServiceAccNoTxtVisible());
			Assert.assertTrue(isCustomerNameTxtVisible());
			Assert.assertTrue(isEmailAddressTxtVisible());
			Assert.assertTrue(isChooseFilebtnVisible());
			Assert.assertTrue(istxtSubjectTxtVisible());
			Assert.assertTrue(isCommentsTxtVisible());
			ExtentLogger.logInfo("Others Page validated successfully ");

			scrollToElement(dropdown);
			selectlstConnectMeOptions("Rebates");
			if (selectlstConnectMeOptions("Rebates")) {
			}
			Assert.assertTrue(isServiceAccNoTxtVisible());
			Assert.assertTrue(isCustomerNameTxtVisible());
			Assert.assertTrue(isEmailAddressTxtVisible());
			Assert.assertTrue(isChooseFilebtnVisible());
			Assert.assertTrue(istxtSubjectTxtVisible());
			Assert.assertTrue(isCommentsTxtVisible());
			ExtentLogger.logInfo("Rebates Page validated successfully ");

			scrollToElement(dropdown);
			selectlstConnectMeOptions("Contact Us");
			if (selectlstConnectMeOptions("Contact Us")) {
			}

			Assert.assertTrue(isServiceAccNoTxtVisible());
			Assert.assertTrue(isCustomerNameTxtVisible());
			Assert.assertTrue(isEmailAddressTxtVisible());
			Assert.assertTrue(isChooseFilebtnVisible());
			Assert.assertTrue(istxtSubjectTxtVisible());
			Assert.assertTrue(isCommentsTxtVisible());
			ExtentLogger.logInfo("Contact Us Page validated successfully ");

			scrollToElement(dropdown);
			selectlstConnectMeOptions("Outage Notification");
			if (selectlstConnectMeOptions("Outage Notification")) {
			}
			Assert.assertTrue(isServiceAccNoTxtVisible());
			Assert.assertTrue(isCustomerNameTxtVisible());
			Assert.assertTrue(isEmailAddressTxtVisible());

			Assert.assertTrue(istxtSubjectTxtVisible());

			scrollToElement(lbl_ContactInfo);
			getContactInfolbl();

			Assert.assertTrue(isFirstNameTxtVisible());
			Assert.assertTrue(isLastNametxtVisible());
			Assert.assertTrue(isPrimaryNoVisible());

			selectOutageOptions("Downed Wire");
			selectOutageOptions("Flickering Power");
			selectOutageOptions("No Power");
			selectOutageOptions("Partial Power");
			selectOutageOptions("Voltage Problem");

			scrollToElement(txtlbl);
			getTxtlbl();
			scrollToElement(txtStreetno);
			Assert.assertTrue(isStreetNoTxtVisible());
			Assert.assertTrue(isApptUnitTxtVisible());
			Assert.assertTrue(isZipTxtVisible());
			Assert.assertTrue(isCityTxtVisible());
			scrollToElement(State);
			Assert.assertTrue(isStateTxtVisible());
			Assert.assertTrue(isNearestcrssTxtVisible());
			Assert.assertTrue(isDescriptionTxtVisible());
			Assert.assertTrue(isDescriptionboxVisible());
			Assert.assertTrue(isChooseFilebtnVisible());
			Assert.assertTrue(isDateTxtVisible());
			ExtentLogger.logInfo("Outage Notifications Page validated successfully ");

			scrollToElement(dropdown);
			selectlstConnectMeOptions("Delete My Profile");

			if (selectlstConnectMeOptions("Delete My Profile")) {
			}
			Assert.assertTrue(isServiceAccNoTxtVisible());
			Assert.assertTrue(isCustomerNameTxtVisible());
			Assert.assertTrue(isEmailAddressTxtVisible());
			Assert.assertTrue(isChooseFilebtnVisible());
			Assert.assertTrue(istxtSubjectTxtVisible());
			Assert.assertTrue(isCommentsTxtVisible());
			ExtentLogger.logInfo("Delete My Profile Page validated successfully ");

			scrollToElement(dropdown);
			selectlstConnectMeOptions("Smart City Requests");
			if (selectlstConnectMeOptions("Smart City Requests")) {

				isDropdownIsVisible("Police Complaint");
				isDropdownIsVisible("License Request");
				isDropdownIsVisible("Get a parking permit");
				ExtentLogger.logInfo("Drop options validated ");
				isDropdownIsVisible("New Vendor Registration");
				isDropdownIsVisible("Code Voilation");

			}
			Assert.assertTrue(isFirstNameTxtVisible());
			Assert.assertTrue(isLastNameTxtVisible());
			Assert.assertTrue(isPrimaryNoVisible());
			Assert.assertTrue(isEmailaddVisible());

			Assert.assertTrue(isChooseFilebtnVisible());
			Assert.assertTrue(isAddtionalCommentsVisible());
			ExtentLogger.logInfo("Smart City Requests Page validated successfully ");

		}
	}

	public void verifySavedformsfunctionality(SoftAssert softAssert) throws InterruptedException {

		// save a form
		clickContactusLink();
		selectlstConnectMeOptions("Programs");

		if (isServiceAccNoTxtVisible()) {
			String acc = Configuration.toString("accountNo");
			populateAccNum(acc);

//	 					Assert.assertEquals(Configuration.toString("accountNumber"), accountNo);
		}
		if (isCustomerNameTxtVisible()) {
			populateCustomername("John");

//	 					 Assert.assertEquals(Configuration.toString("customerName"),custmerName);
		}
		if (isEmailAddressTxtVisible()) {
			populateEmailadd("DemoInstance@mailinator.com");

//	 					
		}
		scrollToElement(btnChooseFile);
		if (isChooseFileBtnVisible()) {
			String validAttachmentFileName = "meter-reading.jpg";
			addAttachmentToChooseFile(FILE_UPLOAD_PATH + validAttachmentFileName);
			addAttachmentToChooseFile(FILE_UPLOAD_PATH + validAttachmentFileName);
		}
		if (istxtSubjectTxtVisible()) {
			populateSubject("Testing");
		}
		if (isCommentsTxtVisible()) {
			populateComments("Testing");
		}
		clickSave();
		isSavedconfirmationVisible();
		softAssert.assertEquals(isSavedconfirmationVisible(),
				PostLogConnectMeTextProp.getPropValue("lblThankYouForWriting"));
		clickOkBtn();
		ExtentLogger.logInfo("Form saved successfully ");

		// View page objects and edit Savedforms Functionality
		clickContactusLink();
		pause(5000);
		clickSavedform();
		clickSubmitBtn();
		if (isErrVisible()) {
			scrollToElement(Topiclbl);
			clickSavedform();
			populateRequestID("CM-1265797249");
			clickSubmitBtn();
			scrollToElement(TopiclbL);
			isTopicvisible();
			if (isServiceAccNoTxtVisible()) {
				if (isCustomerNameTxtVisible()) {
					if (isEmailAddressTxtVisible()) {
						scrollToElement(btnChooseFile);
						if (isChooseFileBtnVisible()) {
							clearSubject();
							if (istxtSubjectTxtVisible()) {
								repopulateSubject("Test");
							}
							if (isCommentsTxtVisible()) {
							}
						}

						clickSave();
						softAssert.assertEquals(isSavedconfirmationVisible(),
								PostLogConnectMeTextProp.getPropValue("lblThankYouForWriting"));
						clickOkBtn();
						ExtentLogger.logInfo("Updated saved forms and resaved successfully ");
					}
					// Submit saved forms

//	 				clickContactusLink();
					// scrollToElement(Topiclbl);
					clickSavedform();
					populateRequestID(" CM-1265797249");
					clickSubmitBtn();
					scrollToElement(TopiclbL);
					isTopicvisible();
					clearSubject();
					if (istxtSubjectTxtVisible()) {
						repopulateSubject("Test");
						btnClickNext();
						isReviewPageheaderVisible();
						clickSubmit();
						scrollToElement(Submitbtn);
						isconfirmationmsgVisible();
						softAssert.assertEquals(isconfirmationmsgVisible(),
								PostLogConnectMeTextProp.getPropValue("lblThankYouForWriting"));
						clickOkBtn();
						ExtentLogger.logInfo("Submitted saved forms and resaved successfully ");
					}
				}
			}
		}
	}
}

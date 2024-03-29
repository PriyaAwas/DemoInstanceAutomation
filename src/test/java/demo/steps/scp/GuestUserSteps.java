package demo.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import sew.ai.config.Configuration;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.reporters.ExtentLogger;
import demo.pageobjects.GuestUserPage;
import sew.ai.utils.PropertiesUtil;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GuestUserSteps extends GuestUserPage {
	private static final Logger log = LogManager.getLogger(GuestUserSteps.class);
	public static PropertiesUtil guestuserTextProp;

	public GuestUserSteps(WebDriver driver) {
		super(driver);
		guestuserTextProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.GUEST_USER_TEXT_FILENAME);
	}

	public Boolean isGuestUserPage(String url, String title) {
		Boolean isguestuserPage = false;
		log.info("Checking that the current page is guest user page.");
		if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
			isguestuserPage = true;
		log.info("The current page is guest user page {}: " + isguestuserPage);
		return isguestuserPage;
	}

	public void verifyGuestUserPageUI(SoftAssert softAssert) throws SQLException {
		pause(5000);
		if (getListTabsGuestUserDetails().size() > 0) {
			ExtentLogger.logInfo("Verify Guest User Page UI");
			softAssert.assertTrue(isPageHeadVisible(), "Page Title is not visible");
			softAssert.assertTrue(isInviteGuestUserSecVisible(), "Invite Guest User Section is not visible");
			softAssert.assertTrue(isExistGuestUserSecVisible(), "Existing Guest User Section is not visible");
			softAssert.assertTrue(isGuestUserBannerVisible(), "Guest User Banner is not visible");
			softAssert.assertTrue(isContactUsBannerVisible(), "Contact Us Banner is not visible");

			ExtentLogger.logInfo("Test Case execution for - verifyGuestUserPageUI - is Completed");

			log.info("Test Case execution for - verifyGuestUserPageUI - is Completed");

			ExtentLogger.logInfo("Guest User Page Objects are validated sucessfully");

		}
	}

	public void verifyInviteNewGuestUserFunctionnality(SoftAssert softAssert) throws IOException {

		deleteGuestUser(softAssert);

		ExtentLogger.logInfo("Verify that customer is able to click invite new guest user section successfully.");
		clickOnInviteUser();
		ExtentLogger
				.logInfo("Verify that customer is able to click on the account number drop down field successfully.");
		clickAccountDropDown();
		ExtentLogger.logInfo("Verify that customer is able to select the account number successfully.");
		selectServiceAccountnumber();
		pause(2000);
		ExtentLogger.logInfo("Verify that customer is able to populate the guest user name field successfully.");
		populateGuestUserName("Guest One");
		pause(500);
		ExtentLogger.logInfo("Verify that customer is able to select the guest user role successfully.");
		selectGuestUserRole();
		pause(500);
		ExtentLogger.logInfo("Verify that customer is able to populate the guest user emailid field successfully.");
		populateGuestUserEmailId("guest.one@testmail.com");
		ExtentLogger.logInfo("Verify that customer is able to select access period successfully.");
		isAccessPeriodClickable();
		ExtentLogger.logInfo("Verify that customer is able to select check box successfully.");
		checkchkBoxAgreeTeBtn();
		ExtentLogger.logInfo("Verify that customer is able to click on submit button successfully.");
		clickSubmitButton();
		softAssert.assertEquals(getInviteGuestUserSuccessLabel(),
				guestuserTextProp.getPropValue("txtMsgSuccessfulGuestInvite"));
		log.info("Test Case execution for - inviteNewGuestUserFunctionality - is Completed");
	}

	public void deleteGuestUser(SoftAssert softAssert) {
		pause(1000);
		List<WebElement> btnToggleThreeDots = getListBtnToggleThreeDots();
		int size = btnToggleThreeDots.size();
		for (int i = 0; i < size; i++) {
			// Verifying delete/revoke guest access functionality.
			pause(2000);
			clickWithJSExecutor(getListBtnToggleThreeDots().get(0));
			clickWithJSExecutor(getBtnRemoveGuestAccount().get(0));
			pause(5000);
			clickContinueConfirmationMsgBtn();
			String expRevokeConfirmationMsg = guestuserTextProp.getPropValue("txtLblRevokeConfirmationMsg");
			String actRevokeConfirmationMsg = getConfirmationPopupMsg();
			softAssert.assertEquals(actRevokeConfirmationMsg, expRevokeConfirmationMsg,
					"Revoke confirmation message is not matched.");
			pause(10000);
		}
	}

	public void verifyEditGuestDetailsFunction(SoftAssert softAssert) throws IOException, SQLException {

		deleteGuestUser(softAssert);

		verifyInviteNewGuestUserFunctionnality(softAssert);
		pause(5000);

		String accountNo = Configuration.toString("scmAccountNumber");
		getBtnThreeDots(accountNo);

		softAssert.assertTrue(btnEditGuestuserIcon(accountNo).isDisplayed(),
				"Edit link is not displaying before approving guest user request.");
		if (btnEditGuestuserIcon(accountNo).isDisplayed()) {
			pause(500);
			if (isexistinguserdisplayed()) {
				pause(500);

				ExtentLogger.logInfo("Verify that customer is able to select edit option successfully.");
				pause(5000);
				clickEditGuestButton();
				ExtentLogger.logInfo("Verify that customer is able to edit the guest user role successfully.");
				selectGuestUserRole();
				pause(500);
				ExtentLogger.logInfo("Verify that customer is able to select check box successfully.");
				selectchkBoxOptn();
				ExtentLogger.logInfo("Verify that customer is able to click on submit button successfully.");
				clickSubmitButton();
				softAssert.assertEquals(getDetailsUpdatedLabel(),
						guestuserTextProp.getPropValue("txtMsgSuccessfullUpdateGup"));
			} else {
				softAssert.assertTrue(btnResendInvitationIcon(accountNo).isDisplayed(),
						"Edit User link is not displaying before approving guest user request.");
			}
			log.info("To verify edit guest user functionality from their guest user tab.");
		}
	}

	public void verifyResendActivationLinkFunction(SoftAssert softAssert) throws IOException, SQLException {

		deleteGuestUser(softAssert);
		verifyInviteNewGuestUserFunctionnality(softAssert);
		pause(5000);
		String accountNo = Configuration.toString("scmAccountNumber");
		getBtnThreeDots(accountNo);
		pause(500);
		if (btnResendInvitationIcon(accountNo).isDisplayed()) {
			String invitationStatus = getStatusOfInvitation(accountNo);
			String expInvitationStatus = guestuserTextProp.getPropValue("txtLblInvitationPendingStatus");
			softAssert.assertEquals(invitationStatus, expInvitationStatus, "Invitation status not matched.");
			String expToastMsg = guestuserTextProp.getPropValue("txtLblResendInvitationToastMsg");
			String expToastMsg1 = guestuserTextProp.getPropValue("txtLblResendInvitationToastMsgTwoGup");
			clickBtnResendInvitationIcon(accountNo);
			String actToastMsg = getToastMessage();
			if (actToastMsg == "A link with instructions has been sent to the Email Address you provided. You will be notified once user accepts the invitation.") {
				softAssert.assertEquals(actToastMsg, expToastMsg, "Resend activation link toast message not matched");
			} else {
				softAssert.assertEquals(actToastMsg, expToastMsg1, "Resend activation link toast message not matched");
			}
		} else {
			softAssert.assertTrue(btnResendInvitationIcon(accountNo).isDisplayed(),
					"Resend activation link is not displaying before approving guest user request.");
		}
		log.info("To verify resend the activation functionality from their guest user tab.");
	}

}
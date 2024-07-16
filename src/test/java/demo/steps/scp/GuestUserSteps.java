package demo.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import sew.ai.config.CSPConfiguration;
import sew.ai.config.Configuration;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.models.RegistrationConfig;
import sew.ai.models.User;
import sew.ai.steps.scp.HomeSteps;
import demo.pageobjects.GuestUserPage;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.DateUtil;
import sew.ai.utils.JsonUtil;
import sew.ai.utils.PropertiesUtil;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GuestUserSteps extends GuestUserPage {
	private static final Logger log = LogManager.getLogger(GuestUserSteps.class);
	public static PropertiesUtil guestuserTextProp;
	Map<String, Integer> registrationConfig = new HashMap<>();
	public static Map<String, RegistrationConfig> getRegistrationConfig = new LinkedHashMap<>();
	Map<String, String> accountWithRoleId;
	String role, username, guestPassword;
	String name = null;
	String accountNumber = null;
	private JsonUtil testDataJson;
	String email = null, guestUserInviteSub;
	String activationUrl = null;
	Map<String, String> guestCustomer = new LinkedHashMap<>();
	Map<String, WebElement> guestRegLabelsFieldsMap = new LinkedHashMap<>();
	Map<String, String> loginDetails = new HashMap<>();
	String pwdOfGivenUser = null;
	Map<String, String> minValueRegFields = new HashMap<>();
	Map<String, String> maxValueRegFields = new HashMap<>();
	String verifyCase = null, lastCase = null;

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

		verifyInviteUserSubmitSuccessfully(softAssert, accountNumber, accountNumber);
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
		// delete existing user and adding new guest user
		verifyInviteUserSubmitSuccessfully(softAssert, accountNumber, accountNumber);
		pause(5000);
		String accountNo = Configuration.toString("accountNumber");
		getBtnThreeDots(accountNo);
		pause(500);
		if (btnResendInvitationIcon(accountNo).isDisplayed()) {
			// validate the guest user status
			softAssert.assertEquals(getStatusOfInvitation(accountNo),
					guestuserTextProp.getPropValue("txtLblInvitationPendingStatus"), "Invitation status not matched.");
			clickBtnResendInvitationIcon(accountNo);
			softAssert.assertEquals(getToastMessage(), guestuserTextProp.getPropValue("txtLblResendInvitationToastMsg"),
					"Resend activation link toast message not matched");
		} else {
			log.info("Resend activation functionality is not working");
		}

	}

	public void errorMsgForInviteUserPopup(SoftAssert sAssert, User user) {
		log.info("Test Case execution for - verifyErrorMsgForInviteUserPopup - is Initiated");
		SoftAssert softAssert = new SoftAssert();
		registrationConfig = CSPConfiguration.initMCspConfig();
		HomeSteps homeSteps = new HomeSteps(driver);
		String sTestDataScpJsonFP = FilePaths.SCP_JSON_TEXT_PROPERTIES;
		String testDataJsonName = "TestGuestUser.json";
		String accountNumber = null;
		testDataJson = new JsonUtil(sTestDataScpJsonFP, testDataJsonName);
		List<String> accNoHavingOwnerAccess = new ArrayList<>();

		if (accNoHavingOwnerAccess.size() > 0) {

			clickOnInviteUser();
			driver.switchTo().activeElement();
			pause(2000);
			sAssert.assertEquals(getLabelInviteUser(), guestuserTextProp.getPropValue("txtLblMsgPopUpInviteUser"));

			testDataJson = new JsonUtil(sTestDataScpJsonFP, testDataJsonName);
			String futureDate = DateUtil.getFutureDate(6);
			// Populating invite guest user invite form.
			ExtentLogger.logInfo(
					"Verify that customer is able to click on the account number drop down field successfully.");
			clickAccountDropDown();
			ExtentLogger.logInfo("Verify that customer is able to select the account number successfully.");
			selectServiceAccountnumber();
			pause(2000);
			role = testDataJson.getStringJsonValue("roleGuest");

			if (registrationConfig.get("MyAccount") == 1) {
				if (registrationConfig.get("GuestUser.Invite") == 1) {
					// Navigate to the guest user page.
					homeSteps.navigateToGuestUser();
					// Deleting guest user if any.
					deleteGuestUser(softAssert);
					// Verify error message for mandatory fields
					verifyErrMsgForMandatoryFields(softAssert);
					// Verify assign multiple roles of same account to same user
					verifyErrMsgAssignMultipleRoleToSameUser(softAssert);
				} else {
					log.info("Guest user link is not available on the MyAccount Page");
				}
			} else {
				log.info("My Account link is not available on the header");
			}
		} else {
			log.info("Given user have no account with owner or property manager access");
		}

		log.info("Test Case execution for - verifyErrorMsgForInviteUserPopup - is Completed.");
	}

	public Map<String, String> getAllLinkedAccountsWithRoleId(String userName) {
		Map<String, String> accWithRoleId = new HashMap<>();
		String sqlQuery = SQLQueries.getAllLinkedAccountsWithRoleId(userName);
		try {
			ResultSet rs = DataBaseUtils.getResultSet(sqlQuery);
			while (rs.next()) {
				accWithRoleId.put(rs.getString("UtilityAccountNumber"), rs.getString("RoleID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accWithRoleId;
	}

	public void verifyErrMsgAssignMultipleRoleToSameUser(SoftAssert sAssert) {
		log.info("C74764 - Verify that system shall not allow the same "
				+ "guest user to be assigned multiple roles for the same account.");
		log.info("Test Case execution for - verifyErrMsgAssignMultipleRoleToSameUser - is Initiated");
		String expErrMsg = guestuserTextProp.getPropValue("txtLblErrMsgEmailAlreadyRegisteredGup");
		deleteGuestUser(sAssert);
		for (int i = 0; i < 2; i++) {
			clickOnInviteUser();
			driver.switchTo().activeElement();
			pause(500);
			// Verifying invite user popup.
			sAssert.assertEquals(getLabelInviteUser(), guestuserTextProp.getPropValue("txtLblMsgPopUpInviteUser"));

			String sTestDataScpJsonFP = FilePaths.SCP_JSON_TEXT_PROPERTIES;
			String testDataJsonName = "TestGuestUser.json";
			testDataJson = new JsonUtil(sTestDataScpJsonFP, testDataJsonName);
			String guestUserName = testDataJson.getStringJsonValue("guestUserName");

			String guestEmailAddress = testDataJson.getStringJsonValue("guestUserEmail");
			String futureDate = DateUtil.getFutureDate(6);

			// Populating invite user popup
			selectServiceAccountnumberByIndex(1);
			populateGuestUserName(guestUserName);

			if (i == 0) {
				selectGuestUserRoleByIndex(1);

			} else {
				selectGuestUserRoleByIndex(2);

			}
			populateGuestUserEmailId(guestEmailAddress);
			populateAccessPeriod(futureDate);
			checkchkBoxAgreeTeBtn();
			clickSubmitButton();
			String sActToastMsg = getToastMessage();
			if (i != 0) {
				sAssert.assertEquals(sActToastMsg, expErrMsg, "Toast message email already registered is not matched.");
			}
			pause(2000);
		}
		clickCloseIfDisplayed();
		log.info("C74764 - Verify that system shall not allow the same "
				+ "guest user to be assigned multiple roles for the same account.");
		log.info("Test Case execution for - verifyErrMsgAssignMultipleRoleToSameUser - " + "is Completed");
	}

	public void clickCloseIfDisplayed() {
		if (isCloseInviteUserPopupVisible() == true) {
			clickCloseInviteUserPopupBtn();
		}
	}

	public void verifyOwnerAccInAccNoDrpDown(SoftAssert softAssert) throws IOException {
		// Get user account and User Role
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

	public void verifyCloseInviteUserPopup() {
		log.info("C74759 - Verify that clicking either the close or cancel button "
				+ "on the invite guest user popup will close the popup.");
		log.info("Test Case execution for - verifyCloseInviteUserPopup - is Initiated");
		ExtentLogger.logInfo("Verify that customer is able to click invite new guest user section successfully.");
		clickOnInviteUser();
		pause(1000);
		clickCancelButton();
		pause(1000);
		log.info("C74759 - Verify that clicking either the close or cancel button "
				+ "on the invite guest user popup will close the popup.");
		log.info("Test Case execution for - verifyCloseInviteUserPopup - is Completed");
	}

	public void verifyInviteUserSubmitSuccessfully(SoftAssert sAssert, String accountNumber, String role) {
		log.info("Verify that the user is able to submit the guest user popup.");
		log.info("Test Case execution for - verifyInviteUserSubmitSuccessfully - is Initiated");
		deleteGuestUser(sAssert);
		pause(5000);
		clickOnInviteUser();
		driver.switchTo().activeElement();
		pause(2000);
		sAssert.assertEquals(getLabelInviteUser(), guestuserTextProp.getPropValue("txtLblMsgPopUpInviteUser"));
		String sTestDataScpJsonFP = FilePaths.SCP_JSON_TEXT_PROPERTIES;
		String testDataJsonName = "TestGuestUser.json";
		testDataJson = new JsonUtil(sTestDataScpJsonFP, testDataJsonName);
		String guestUserName = testDataJson.getStringJsonValue("guestUserName");

		// testDataJson = new JsonUtil(sTestDataScpJsonFP, testDataJsonName);
		String guestEmailAddress = testDataJson.getStringJsonValue("guestUserEmail");
		String futureDate = DateUtil.getFutureDate(6);
		// Populating invite guest user invite form.
		ExtentLogger
				.logInfo("Verify that customer is able to click on the account number drop down field successfully.");
		clickAccountDropDown();
		ExtentLogger.logInfo("Verify that customer is able to select the account number successfully.");
		selectServiceAccountnumber();
		pause(2000);
		// selectServiceAccountnumber(accountNumber);
		populateGuestUserName(guestUserName);
		// selectGuestUserRole(role);
		ExtentLogger.logInfo("Verify that customer is able to select the guest user role successfully.");
		selectGuestUserRole();
		pause(500);
		populateGuestUserEmailId(guestEmailAddress);
		// populateAccessPeriod(futureDate);
		checkchkBoxAgreeTeBtn();
		clickSubmitButton();
		// Verifying successful toast message for guest user invite
		String toastMsg = getToastMessage();
		sAssert.assertEquals(toastMsg, guestuserTextProp.getPropValue("txtMsgSuccessfulGuestInvite"));
		// Deleting guest user after verification.
		pause(3000);
		//deleteGuestUser(sAssert);
		log.info("Verify that the user is able to submit the guest user popup.");
		log.info("Test Case execution for - verifyInviteUserSubmitSuccessfully - is Completed");
	}

	public void verifyErrMsgForMandatoryFields(SoftAssert sAssert) {
		log.info("C74761 - Verify the error messages for all mandatory "
				+ "fields on guest user pop up while submission.");
		log.info("Test Case execution for - verifyErrMsgForMandatoryFields - is Initiated");
		// Delete existing guest user if displayed.
		deleteGuestUser(sAssert);
		// Invite guest user.
		clickOnInviteUser();
		pause(500);
		String expErrMsg = guestuserTextProp.getPropValue("txtLblErrMsgMandatoryFields");
		clickSubmitButton();

		// sAssert.assertEquals(getToastMessage(), expErrMsg);
		// if (isToastCloseVisible() == true)
		clickToastCloseBtn();
		pause(2000);
		// Logging
		log.info("C74761 - Verify the error messages for all mandatory "
				+ "fields on guest user pop up while submission.");
		log.info("Test Case execution for - verifyErrMsgForMandatoryFields - is Completed");
	}

}

package sew.ai.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import sew.ai.config.CSPConfiguration;
import sew.ai.config.SCPConfiguration;
import sew.ai.driver.DriverFactory;
import sew.ai.driver.Page;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.models.RegistrationConfig;
import sew.ai.models.User;
import sew.ai.pageObjects.scp.AccountInformationPage;
import sew.ai.pageObjects.scp.BillDashboardPage;
import sew.ai.pageObjects.scp.DashboardPage;
import sew.ai.pageObjects.scp.GuestUserPage;
import sew.ai.pageObjects.scp.LoginPage;
import sew.ai.pageObjects.scp.MultiFactorAuthPage;
import sew.ai.pageObjects.scp.MyProfilePage;
import sew.ai.pageObjects.scp.RegistrationPage;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.DateUtil;
import sew.ai.utils.JsonUtil;
import sew.ai.utils.PropertiesUtil;
import sew.ai.utils.RandomUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GuestuserSteps extends GuestUserPage {
	private static final Logger log = LogManager.getLogger(GuestuserSteps.class);
	public static PropertiesUtil guestuserTextProp;
	Map<String, Integer> registrationConfig = new HashMap<>();
	public static Map<String, RegistrationConfig> getRegistrationConfig = new LinkedHashMap<>();
	Map<String, String> accountWithRoleId;
	String role, username, guestPassword;
	private String pass, sPass;
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

	public GuestuserSteps(WebDriver driver) {
		super(driver);
		guestuserTextProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.GUEST_USER_TEXT_FILENAME);
	}

	/**
	 * This method verifies the objects on the Notification Preference page.
	 *
	 * @param softAssert verify all the soft verification.
	 * @param user       this models contains all the user details.
	 */

	public Boolean isGuestUserPage(String url, String title) {
		Boolean isguestuserPage = false;
		log.info("Checking that the current page is account information page.");
		if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
			isguestuserPage = true;
		log.info("The current page is account information page {}: " + isguestuserPage);
		return isguestuserPage;
	}

	public void guestUserPageObject(SoftAssert sAssert, User user) {

		log.info("Test Case execution for - verifyGuestUserPage - is Initiated");
		registrationConfig = CSPConfiguration.initMCspConfig();
		HomeSteps homeSteps = new HomeSteps(driver);
		accountWithRoleId = getAllLinkedAccountsWithRoleId(user.getUserName());
		String sTestDataScpJsonFP = FilePaths.SCP_JSON_TEXT_PROPERTIES;
		String testDataJsonName = "TestGuestUser.json";
		String accountNumber;
		testDataJson = new JsonUtil(sTestDataScpJsonFP, testDataJsonName);
		// Get the account number from the database which have owner access.
		List<String> accNoHavingOwnerAccess = new ArrayList<>();
		for (Map.Entry<String, String> entry : accountWithRoleId.entrySet()) {
			if (entry.getValue().equals("3")) {
				accNoHavingOwnerAccess.add(entry.getKey());
			}
		}
		if (accNoHavingOwnerAccess.size() > 0) {
			// Getting the account number
			accountNumber = accNoHavingOwnerAccess.get(0);
			// Getting guest role.
			role = testDataJson.getStringJsonValue("roleGuest");
			// Login into the application.

			if (registrationConfig.get("MyAccount") == 1) {
				if (registrationConfig.get("GuestUser.Invite") == 1) {
					// Navigate to the guest user page.
					homeSteps.navigateToGuestUser();
					// Verifying content on guest user page
					verifyObjectsOnGuestUserPage(sAssert);

					// Deleting guest user if any.
					deleteGuestUser(sAssert);
					// Verifying content on guest user page
					verifyObjectsOnGuestUserPage(sAssert);
					// Verifying content on invite user popup
					verifyInviteUserPopup(sAssert);
					// Verifying service account number drop-down options
					verifyServiceAccountNumberDropDown(sAssert, accNoHavingOwnerAccess);
					// Verifying close invite user popup functionality
					verifyCloseInviteUserPopup();
					// Verifying submit invite user popup successfully.
					verifyInviteUserSubmitSuccessfully(sAssert, accountNumber, role);
				} else {
					log.info("Guest user link is not available on the MyAccount page.");
				}
			} else {
				log.info("My Account link is not available on the header");
			}
		} else {
			log.info("Given user have no account with owner or property manager access.");
		}
		log.info("Test Case execution for - verifyGuestUserPage - is Completed.");

	}

	public void errorMsgForInviteUserPopup(SoftAssert sAssert, User user) {
		log.info("C74761, C74762, C74764, C74817 - To verify the guest user page.");
		log.info("Test Case execution for - verifyErrorMsgForInviteUserPopup - is Initiated");
		SoftAssert softAssert = new SoftAssert();
		registrationConfig = CSPConfiguration.initMCspConfig();
		HomeSteps homeSteps = new HomeSteps(driver);
		accountWithRoleId = getAllLinkedAccountsWithRoleId(user.getUserName());
		String sTestDataScpJsonFP = FilePaths.SCP_JSON_TEXT_PROPERTIES;
		String testDataJsonName = "TestGuestUser.json";
		String accountNumber;
		testDataJson = new JsonUtil(sTestDataScpJsonFP, testDataJsonName);
		// Get the account number from the database which have owner access.
		List<String> accNoHavingOwnerAccess = new ArrayList<>();
		for (Map.Entry<String, String> entry : accountWithRoleId.entrySet()) {
			if (entry.getValue().equals("3")) {
				accNoHavingOwnerAccess.add(entry.getKey());
			}
		}
		if (accNoHavingOwnerAccess.size() > 0) {
			// Getting user account number
			accountNumber = accNoHavingOwnerAccess.get(0);
			// Getting role type
			role = testDataJson.getStringJsonValue("roleGuest");
			// Login into the application.

			if (accNoHavingOwnerAccess.size() > 0) {
				// Getting the account number
				accountNumber = accNoHavingOwnerAccess.get(0);
				// Getting guest role.
				role = testDataJson.getStringJsonValue("roleGuest");

				if (registrationConfig.get("MyAccount") == 1) {
					if (registrationConfig.get("GuestUser.Invite") == 1) {
						// Navigate to the guest user page.
						homeSteps.navigateToGuestUser();
						// Deleting guest user if any.
						// deleteGuestUser(softAssert);
						// Verify error message for mandatory fields
						// verifyErrMsgForMandatoryFields(softAssert);
						// Verify error message user already have owner access
						// verifyErrMsgUserHaveOwnerAccess(softAssert, accountNumber, role,user);
						// Verify number of guest user invite is as per CSP configuration.
						// verifyNumberOfGuestInviteIsAsPerCsp(softAssert, accountNumber, role);
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

	}

	public void inviteNewUserAsGuest(SoftAssert sAssert, User user) throws InterruptedException {
		log.info("C74765, C74767, C74772, C74760, C74789, C74797 - "
				+ "Verify that guest user can choose to create a new username password to access guest account.");
		log.info("Test Case execution for - verifyInviteNewUserAsGuest - is Initiated");
		registrationConfig = CSPConfiguration.initMCspConfig();
		HomeSteps homeSteps = new HomeSteps(driver);
		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		RegistrationSteps registrationsteps = new RegistrationSteps(driver);
		String sTestDataScpJsonFP = FilePaths.SCP_JSON_TEXT_PROPERTIES;
		String testDataJsonName = "TestGuestUser.json";
		accountWithRoleId = getAllLinkedAccountsWithRoleId(user.getUserName());
		testDataJson = new JsonUtil(sTestDataScpJsonFP, testDataJsonName);

		// Get the account number from the database which have owner access.
		List<String> accNoHavingOwnerAccess = new ArrayList<>();
		for (Map.Entry<String, String> entry : accountWithRoleId.entrySet()) {
			if (entry.getValue().equals("3")) {
				accNoHavingOwnerAccess.add(entry.getKey());
			}
		}
		// Fetching mail and name of the guest user.
		name = testDataJson.getStringJsonValue("inviteNewUserName");
		email = testDataJson.getStringJsonValue("inviteNewUserEmail");
		if (accNoHavingOwnerAccess.size() > 0) {
			// Getting user account number
			accountNumber = accNoHavingOwnerAccess.get(0);
			role = testDataJson.getStringJsonValue("roleGuest");
			// Login into the application.
			// loginSteps.loginIntoTheApplication(userId, pass);

			if (registrationConfig.get("MyAccount") == 1) {
				if (registrationConfig.get("GuestUser.Invite") == 1) {
					// Navigate to the guest user page.
					homeSteps.navigateToGuestUser();
					// Delete all guest users
					deleteGuestUser(softAssert);
					// Inviting guest user
					homeSteps.navigateToGuestUser();
					inviteNonScmUserAsGuest(softAssert, accountNumber, email, role);
					// Verify resend activation link functionality
					verifyResendActivationLinkFunctionality(softAssert, accountNumber);
					// Waiting for mail template to become into the database
					pause(5000);
					// navigate new user registration url using database.
					navigateActivationUrl("newUser", sAssert);
					// Register new guest user
					username = registerNewGuestUser();
					// Verify first login after registering new guest user
					// Verify Guest Username at Account owner side at Guest User Page
					verifyNewRegisterGuestUserAbleToLogin(softAssert, username, sPass);
					// Verify Guest Username at Account owner side at Guest User Page
					// Verify revoking guest access of guest user
					SignOutAndBackToLoginPage(driver);
					loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
					// Verify revoking guest access of guest user
					verifyRevokeGuestAccess(softAssert, user);
				} else {
					log.info("Guest user link is not available on the" + " MyAccount page.");
				}
			} else {
				log.info("My account link is not available on the header");
			}
		} else {
			log.info("Given user have no account with owner or property" + " manager access");
		}
	}

	public void guestUserRegistrationFieldValidations(SoftAssert sAssert, User user) {

		log.info("Test Case execution for - verifyGuestUserRegistrationFieldValidations - is Initiated");
		SoftAssert softAssert = new SoftAssert();
		// Get the account number from the database which have owner access.
		HomeSteps homeSteps = new HomeSteps(driver);
		LoginSteps loginSteps = new LoginSteps(driver);
		registrationConfig = CSPConfiguration.initMCspConfig();
		RegistrationSteps registrationsteps = new RegistrationSteps(driver);
		String sTestDataScpJsonFP = FilePaths.SCP_JSON_TEXT_PROPERTIES;
		String testDataJsonName = "TestGuestUser.json";
		accountWithRoleId = getAllLinkedAccountsWithRoleId(user.getUserName());
		testDataJson = new JsonUtil(sTestDataScpJsonFP, testDataJsonName);

		// Get the account number from the database which have owner access.
		List<String> accNoHavingOwnerAccess = new ArrayList<>();
		for (Map.Entry<String, String> entry : accountWithRoleId.entrySet()) {
			if (entry.getValue().equals("3")) {
				accNoHavingOwnerAccess.add(entry.getKey());
			}
		}
		// Fetching mail and name of the guest user.
		name = testDataJson.getStringJsonValue("inviteNewUserName");
		email = testDataJson.getStringJsonValue("inviteNewUserEmail");
		if (accNoHavingOwnerAccess.size() > 0) {
			// Getting user account number
			accountNumber = accNoHavingOwnerAccess.get(0);
			role = testDataJson.getStringJsonValue("roleGuest");
			// Login into the application.
			// loginSteps.loginIntoTheApplication(userId, pass);

			if (registrationConfig.get("MyAccount") == 1) {
				if (registrationConfig.get("GuestUser.Invite") == 1) {
					// Navigate to the guest user page.
					homeSteps.navigateToGuestUser();
					// Delete all guest users
					deleteGuestUser(softAssert);
					// Inviting guest user
					homeSteps.navigateToGuestUser();
					// Inviting guest user
					inviteNonScmUserAsGuest(softAssert, accountNumber, email, role);
					// Waiting for mail template to become into the database
					pause(4000);
					// navigate new user registration url using database.
					navigateActivationUrl("newUser", sAssert);
					// Verify guest registration field validation
					verifyGuestUserRegistrationFieldsValidation(softAssert);

					log.info("Verify that if the user registers with an already "
							+ "registered username, the system shall display 'username is already in use'.");
				} else {
					log.info("Guest user link is not available on the my account page.");
				}
			} else {
				log.info("My account link is not available on the header");
			}
		} else {
			log.info("Given user have no account with owner or property manager access");
		}
	}

	public void editAndResendGuestUserInvites(SoftAssert sAssert, User user) {

		log.info("Test Case execution for - verifyGuestUserRegistrationFieldValidations - is Initiated");
		LoginSteps loginsteps = new LoginSteps(driver);
		HomeSteps homeSteps = new HomeSteps(driver);
		registrationConfig = CSPConfiguration.initMCspConfig();
		String sTestDataScpJsonFP = FilePaths.SCP_JSON_TEXT_PROPERTIES;
		String testDataJsonName = "TestGuestUser.json";
		accountWithRoleId = getAllLinkedAccountsWithRoleId(user.getUserName());
		testDataJson = new JsonUtil(sTestDataScpJsonFP, testDataJsonName);
		SoftAssert softAssert = new SoftAssert();
		// Get the account number from the database which have owner access.
		List<String> accNoHavingOwnerAccess = new ArrayList<>();
		for (Map.Entry<String, String> entry : accountWithRoleId.entrySet()) {
			if (entry.getValue().equals("3")) {
				accNoHavingOwnerAccess.add(entry.getKey());
			}
		}
		// Fetching mail and name of the guest user.
		name = testDataJson.getStringJsonValue("inviteNewUserName");
		email = testDataJson.getStringJsonValue("inviteNewUserEmail");
		if (accNoHavingOwnerAccess.size() > 0) {
			// Getting user account number
			accountNumber = accNoHavingOwnerAccess.get(0);
			// Login into the application.
			// loginsteps.loginIntoTheApplication(userId, pass);

			role = testDataJson.getStringJsonValue("roleGuest");
			// Check whether the MyAccount module is enabled from csp end or not.
			if (registrationConfig.get("MyAccount") == 1) {
				// Check whether the guest user module is enabled from csp end or not.
				if (registrationConfig.get("GuestUser.Invite") == 1) {
					// Navigate to the guest user page.
					homeSteps.navigateToGuestUser();
					// Delete all guest users
					deleteGuestUser(softAssert);
					// Inviting guest user
					inviteNonScmUserAsGuest(softAssert, accountNumber, email, role);
					// Verify resend activation link
					verifyResendActivationLink(softAssert, accountNumber);
					// Verify edit button functionality
					// verifyEditButtonFunctionality(softAssert, accountNumber);
					// Verify close and cancel functionality of edit user info popup.
					// verifyCloseCancelEditUserInfoPopup();
				} else {
					log.info("Guest user link is not available on the my account page.");
				}
			} else {
				log.info("My account link is not available on the header");
			}
		} else {
			log.info("Given user have no account with owner or property manager access.");
		}

		log.info("Test Case execution for - verifyGuestUserRegistrationFieldValidations - is Completed");
	}

	/**
	 * Test 7: This test method verifies below tests: 1). Verify that If the guest
	 * updates primary email address, same shall be updated in the owner’s guest
	 * grid as well.
	 * 
	 * @throws InterruptedException
	 */

	public void guestUserEditPrimaryEmail(SoftAssert sAssert, User user) throws InterruptedException {
		log.info("C74785 - Verify that If the guest updates primary email"
				+ " address, same shall be updated in the owner’s guest grid as well.");
		log.info("Test Case execution for - verifyGuestUserEditPrimaryEmail - is Initiated");
		registrationConfig = CSPConfiguration.initMCspConfig();
		HomeSteps homeSteps = new HomeSteps(driver);
		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		RegistrationSteps registrationsteps = new RegistrationSteps(driver);
		String sTestDataScpJsonFP = FilePaths.SCP_JSON_TEXT_PROPERTIES;
		String testDataJsonName = "TestGuestUser.json";
		accountWithRoleId = getAllLinkedAccountsWithRoleId(user.getUserName());
		testDataJson = new JsonUtil(sTestDataScpJsonFP, testDataJsonName);

		// Get the account number from the database which have owner access.
		List<String> accNoHavingOwnerAccess = new ArrayList<>();
		for (Map.Entry<String, String> entry : accountWithRoleId.entrySet()) {
			if (entry.getValue().equals("3")) {
				accNoHavingOwnerAccess.add(entry.getKey());
			}
		}
		// Fetching mail and name of the guest user.
		name = testDataJson.getStringJsonValue("inviteNewUserName");
		email = testDataJson.getStringJsonValue("inviteNewUserEmail");
		if (accNoHavingOwnerAccess.size() > 0) {
			// Getting user account number
			accountNumber = accNoHavingOwnerAccess.get(0);
			role = testDataJson.getStringJsonValue("roleGuest");
			// Login into the application.
			// loginSteps.loginIntoTheApplication(userId, pass);

			if (registrationConfig.get("MyAccount") == 1) {
				if (registrationConfig.get("GuestUser.Invite") == 1) {
					// Navigate to the guest user page.
					homeSteps.navigateToGuestUser();
					// Delete all guest users
					deleteGuestUser(softAssert);
					// Inviting guest user
					inviteNonScmUserAsGuest(softAssert, accountNumber, email, role);

					SignOutAndBackToLoginPage(driver);
					// navigate new user registration url using database.
					navigateActivationUrl("newUser", sAssert);

					// registrationsteps.verifyGenericMsgForMandatoryFields(softAssert);
					// Register new guest user
					username = registerNewGuestUser();
					// Verify first login after registering new guest user
					verifyGuestUsersPrimaryEmailUpdate(softAssert, username, sPass, user);
				} else {
					log.info("Guest user link is not available on the my account page.");
				}
			} else {
				log.info("My account link is not available on the header");
			}
		} else {
			log.info("Given user have no account with owner or property manager access");
		}

		log.info("C74785 - Verify that If the guest updates primary "
				+ "email address, same shall be updated in the owner’s guest grid as well.");
		log.info("Test Case execution for - verifyGuestUserEditPrimaryEmail - is Completed");

	}

	/**
	 * Test 7.1: This test method verifies below tests: 1). Verify that If the guest
	 * updates primary email address, same shall be updated in the owner’s guest
	 * grid as well.
	 * 
	 * @throws InterruptedException
	 */
	public void verifyGuestUsersPrimaryEmailUpdate(SoftAssert softAssert, String userName, String _password, User user)
			throws InterruptedException {
		log.info("Verify that If the guest updates primary "
				+ "email address, same shall be updated in the owner’s guest grid as well.");
		log.info("Test Case execution for - verifyGuestUsersPrimaryEmailUpdate - is Initiated");
		HomeSteps homeSteps = new HomeSteps(driver);
		LoginSteps loginSteps = new LoginSteps(driver);
		MyProfilePage profilepage = new MyProfilePage(driver);
		// Login in to the application for the very first time.
		// SignOutAndBackToLoginPage(driver);
		loginIntoTheApplication(userName, _password);

		// Getting my account module is enabled or not from csp end.
		if (registrationConfig.get("MyAccount") == 1) {
			homeSteps.navigateToMyProfilePage();
			profilepage.clickPrimaryEmailIdEditButton();

			String previousPrEmail = profilepage.getValuePrimaryEmail("value").trim();
			String changedPrEmail = testDataJson.getStringJsonValue("changeEmailAddress");
			profilepage.clearValuePrimaryEmailField();
			profilepage.populateValuePrimaryEmailField(changedPrEmail);
			String expToastMsg = guestuserTextProp.getPropValue("txtLblProfileSavedSuccess");
			profilepage.populatePwdForChangeUserName(_password);
			profilepage.clickPrimaryEmailIdSaveButton();
			pause(10000);
			// Login into the application.
			SignOutAndBackToLoginPage(driver);
			loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());

			// Getting my account module is enabled or not from csp end.
			if (registrationConfig.get("MyAccount") == 1) {
				if (registrationConfig.get("GuestUser.Invite") == 1) {
					// Navigate to the guest user page.
					homeSteps.navigateToGuestUser();
					pause(2000);
					// Fetching all the accounts with the delete button on guest
					// user landing grid
					LinkedHashMap<String, String> accountWithEmail = new LinkedHashMap<>();
					List<WebElement> emailId = getObjecttdEmailAddress();
					List<WebElement> serviceAccountNumber = getObjecttdServiceAccountNo();
					int count = 0;
					for (WebElement ele : serviceAccountNumber) {
						String accountNo = ele.getText().trim();
						String emailAdd = emailId.get(count).getText().trim();
						accountWithEmail.put(accountNo, emailAdd);
						count++;
					}
					String emailOnGuestUserPage = accountWithEmail.get(accountNumber);
					softAssert.assertEquals(emailOnGuestUserPage, previousPrEmail);
					softAssert.assertNotEquals(emailOnGuestUserPage, changedPrEmail,
							"Primary changed email of guest user is reflected to the owners guest user page");
				}
			}
			// Login into the guest user account again to change back the
			// previous email.
			SignOutAndBackToLoginPage(driver);
			loginSteps.loginIntoTheApplication(userName, _password);

			if (registrationConfig.get("MyAccount") == 1) {
				homeSteps.navigateToMyProfilePage();
				profilepage.clickPrimaryEmailIdEditButton();
				profilepage.clearValuePrimaryEmailField();
				profilepage.populateValuePrimaryEmailField(previousPrEmail);
				profilepage.populatePwdForChangeUserName(_password);
				profilepage.clickPrimaryEmailIdSaveButton();
				pause(2000);
			}
		} else {
			log.info("My account link is not available on the header");
		}
		log.info("Verify that If the guest updates primary "
				+ "email address, same shall be updated in the owner’s guest grid as well.");
		log.info("Test Case execution for - verifyGuestUsersPrimaryEmailUpdate - is Completed");
	}

	public void updateNickNameForGuestAccountCard(SoftAssert sAssert, User user) throws Exception {
		log.info("C99074 - Verify the guest user is able to change the nickname for a property");
		registrationConfig = CSPConfiguration.initMCspConfig();
		HomeSteps homeSteps = new HomeSteps(driver);
		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		RegistrationSteps registrationsteps = new RegistrationSteps(driver);
		String sTestDataScpJsonFP = FilePaths.SCP_JSON_TEXT_PROPERTIES;
		String testDataJsonName = "TestGuestUser.json";
		accountWithRoleId = getAllLinkedAccountsWithRoleId(user.getUserName());
		testDataJson = new JsonUtil(sTestDataScpJsonFP, testDataJsonName);

		// Get the account number from the database which have owner access.
		List<String> accNoHavingOwnerAccess = new ArrayList<>();
		for (Map.Entry<String, String> entry : accountWithRoleId.entrySet()) {
			if (entry.getValue().equals("3")) {
				accNoHavingOwnerAccess.add(entry.getKey());
			}
		}
		// Fetching mail and name of the guest user.
		name = testDataJson.getStringJsonValue("inviteNewUserName");
		email = testDataJson.getStringJsonValue("inviteNewUserEmail");
		if (accNoHavingOwnerAccess.size() > 0) {
			// Getting user account number
			accountNumber = accNoHavingOwnerAccess.get(0);
			role = testDataJson.getStringJsonValue("roleGuest");
			// Login into the application.
			// loginSteps.loginIntoTheApplication(userId, pass);

			if (registrationConfig.get("MyAccount") == 1) {
				if (registrationConfig.get("GuestUser.Invite") == 1) {
					// Navigate to the guest user page.
					homeSteps.navigateToGuestUser();
					// Delete all guest users
					deleteGuestUser(softAssert);
					// Inviting guest user
					String updatedNickNameforOwnerAccount = verifyUpdateNickNameForAccountCard(accountNumber);
					homeSteps.navigateToGuestUser();
					inviteNonScmUserAsGuest(softAssert, accountNumber, email, role);
					// Verify resend activation link functionality
					verifyResendActivationLinkFunctionality(softAssert, accountNumber);
					// Waiting for mail template to become into the database
					pause(5000);
					// navigate new user registration url using database.
					navigateActivationUrl("newUser", sAssert);
					// Register new guest user
					username = registerNewGuestUser();
					// Verify first login after registering new guest user
					verifyNewRegisterGuestUserAbleToLogin(softAssert, username, sPass);
					// Verify Guest Username at Account owner side at Guest User Page
					String updatedNickNameforGuestAccount = verifyUpdateNickNameForAccountCard(accountNumber);
					// Verify revoking guest access of guest user
					Assert.assertNotEquals(updatedNickNameforOwnerAccount, updatedNickNameforGuestAccount);
					SignOutAndBackToLoginPage(driver);
					loginSteps.loginIntoTheApplication(user.getUserName(), user.getPassword());
					updatedNickNameforGuestAccount = verifyUpdateNickNameForAccountCard(accountNumber);
					// Verify revoking guest access of guest user
					Assert.assertNotEquals(updatedNickNameforOwnerAccount, updatedNickNameforGuestAccount);
					verifyRevokeGuestAccess(softAssert, user);
				} else {
					log.info("Guest user link is not available on the" + " MyAccount page.");
				}
			} else {
				log.info("My account link is not available on the header");
			}
		} else {
			log.info("Given user have no account with owner or property" + " manager access");
		}

	}

	public void linkExpirationForNewUserAsGuest(SoftAssert sAssert, User user) {

		log.info("Test Case execution for - verifyInviteNewUserAsGuest - is Initiated");
		registrationConfig = CSPConfiguration.initMCspConfig();
		HomeSteps homeSteps = new HomeSteps(driver);
		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		RegistrationSteps registrationsteps = new RegistrationSteps(driver);
		String sTestDataScpJsonFP = FilePaths.SCP_JSON_TEXT_PROPERTIES;
		String testDataJsonName = "TestGuestUser.json";
		accountWithRoleId = getAllLinkedAccountsWithRoleId(user.getUserName());
		testDataJson = new JsonUtil(sTestDataScpJsonFP, testDataJsonName);

		// Get the account number from the database which have owner access.
		List<String> accNoHavingOwnerAccess = new ArrayList<>();
		for (Map.Entry<String, String> entry : accountWithRoleId.entrySet()) {
			if (entry.getValue().equals("3")) {
				accNoHavingOwnerAccess.add(entry.getKey());
			}
		}
		// Fetching mail and name of the guest user.
		name = testDataJson.getStringJsonValue("inviteNewUserName");
		email = testDataJson.getStringJsonValue("inviteNewUserEmail");
		if (accNoHavingOwnerAccess.size() > 0) {
			// Getting user account number
			accountNumber = accNoHavingOwnerAccess.get(0);
			role = testDataJson.getStringJsonValue("roleGuest");
			// Login into the application.
			// loginSteps.loginIntoTheApplication(userId, pass);

			if (registrationConfig.get("MyAccount") == 1) {
				if (registrationConfig.get("GuestUser.Invite") == 1) {
					// Navigate to the guest user page.
					homeSteps.navigateToGuestUser();
					// Delete all guest users
					deleteGuestUser(softAssert);
					// Inviting guest user
					inviteNonScmUserAsGuest(softAssert, accountNumber, email, role);
					// Verify resend activation link functionality
					verifyResendActivationLinkFunctionality(softAssert, accountNumber);
					// Waiting for mail template to become into the database
					pause(5000);
					// navigate new user registration url using database.
					validateExpirationLink("newUser", email, 7);
					Assert.assertTrue(
							getExpiryUserLabel().contains(guestuserTextProp.getPropValue("txtMsgLinkExpiry")));

				}
			}
		}
	}

	public void validateExpirationLink(String user, String email, int days) {
		guestUserInviteSub = guestuserTextProp.getPropValue("txtSubjectGuestUserInvite");
		String emailBody = getGuestUserInviteEmailBody(email, guestUserInviteSub);
		String startString;
		String endString;
		if (user.equals("newUser")) {
			startString = "you wish to create separate credentials from your existing then please <a target='_blank' "
					+ "href='";
			endString = "'>register here </a>";
		} else {
			startString = "You can link this service account to your existing credentials on Smart Energy Water "
					+ "application. If you wish to proceed, please <a target='_blank' href='";
			endString = "'>click here </a>";
		}
		int lenOfStartStr = startString.length();
		activationUrl = emailBody.substring(emailBody.indexOf(startString) + lenOfStartStr,
				emailBody.indexOf(endString));
		// Verifying activation url is not empty.
		Assert.assertTrue(!activationUrl.isEmpty(), "Activation url is empty");
		// Navigating to the fetched url.
		System.out.println("Activation url=> " + activationUrl);
		// Assert.assertTrue(emailBody.contains(Utils.getRbTextValue("txtLblGuestuserMailingApp")));
//		DataBaseUtils.updateSqlComm(SQLQueries.UpdateGuestUserLinkExpire(email, days));
		navigateToUrl(activationUrl);
		pause(2000);
	}

	public String verifyUpdateNickNameForAccountCard(String utilityaccountNumber) throws Exception {
		log.info("C99073 - Verify the nickname for the property gets updated if user" + " clicks on CONTINUE button");
		registrationConfig = CSPConfiguration.initMCspConfig();
		HomeSteps homeSteps = new HomeSteps(driver);
		AccountInformationPage accountinformationpage = new AccountInformationPage(driver);
		String updatedNickName = "";
		if (registrationConfig.get("MyAccount") == 1) {
			homeSteps.navigateToAccountInformation();
			// JsExecutorUtil.ScrollPageToElement(driver,
			// (accountProfilePage.getDefaultBtnToggleThreeDots()));

			List<String> allUtilityAccountNolist = accountinformationpage.getLabelLinkedAccountNumList();
			List<String> list = accountinformationpage.getLabelNickNameAllAccountCardList();

			for (int utilityAccountno = 0; utilityAccountno < allUtilityAccountNolist.size(); utilityAccountno++) {

				if (allUtilityAccountNolist.get(utilityAccountno).equals(utilityaccountNumber))

				{
					String oldNickName = accountinformationpage.getLabelNickNameAllAccountCardList()
							.get(utilityAccountno);
					System.out.println("Old Nick Name:" + oldNickName);
					accountinformationpage.clickListAllLinkAccountKebabMenu(utilityAccountno);
					pause(2000);
					Assert.assertTrue(accountinformationpage.isbtnEditNickNameGuestAccountVisible(utilityAccountno),
							"Edit button is not displayed");
					// pageUtil.click(accountProfilePage.getBtnEditNickNameApp());
					accountinformationpage.clickBtnEditNickNameGuestAccount(utilityAccountno);
					pause(2000);
					accountinformationpage.isNickNameTitletextVisible();
					accountinformationpage.isNickNamebtnCancelVisible();
					accountinformationpage.isNickNamebtnsaveVisible();
					String newNickName = oldNickName.replaceAll("[0-9]", "")
							+ RandomUtil.generateRandomString(4, RandomUtil.Mode.NUMERIC);
					accountinformationpage.gettextAccountNickName(newNickName);
					accountinformationpage.clickNickNamebtnsaveBtn();
					pause(5000);
					// Page getting refreshed--- so commenting below line
					// pageUtil.verifyObjectLabelEquals(Utils.getRbTextValue("txtBlankEditNickNameFieldValidationApp"),
					// myAccountProfilePage.getToastMsg());
					updatedNickName = accountinformationpage.getLabelNickNameAllAccountCardList().get(utilityAccountno);
					System.out.println("Updated Nick Name:" + updatedNickName);
					Assert.assertEquals(updatedNickName.trim(), newNickName.trim());
					Assert.assertNotEquals(updatedNickName.trim(), oldNickName.trim());
					break;
				}

			}
			log.info("C99073 - Verify the nickname for the property gets updated if user "
					+ "clicks on CONTINUE button");
		} else {
			log.info("My Account link is not available on the header");
		}
		return updatedNickName.trim();
	}

	public void SignOutAndBackToLoginPage(WebDriver driver) {
		DashboardPage dashboardpage = new DashboardPage(driver);
		LoginPage loginpage = new LoginPage(driver);
		pause(5000);
		dashboardpage.clickSignOutDdLink();
		pause(5000);
		dashboardpage.clickSignOutLnk();
		pause(2000);
		loginpage.clickLinkHomeButton();
		pause(1000);
	}

	/**
	 * Test 5.1: This test method verifies below tests: 1). Verify that the resend
	 * activation link shall be visible for any guest till the time the guest has
	 * not activated their account.
	 */
	public void verifyResendActivationLink(SoftAssert softAssert, String sAccountNumber) {
		log.info("Verify that the resend activation "
				+ "link shall be visible for any guest till the time the guest has not " + "activated their account.");
		log.info("Test Case execution for - verifyResendActivationLink - is Initiated");
		// Verifying resend activation icon visibility.
		getBtnThreeDots(sAccountNumber);
		clickBtnResendInvitationIcon(sAccountNumber);
		String expToastMessage = guestuserTextProp.getPropValue("txtLblResendInvitationToastMsg");
		String sActToastMsg = getToastMessage();
		softAssert.assertEquals(sActToastMsg, expToastMessage,
				"Toast message resend activation link is not not matched.");
		pause(3000);
		log.info("Verify that the resend activation link "
				+ "shall be visible for any guest till the time the guest has not activated " + "their account.");
		log.info("Test Case execution for - verifyResendActivationLink - is Completed");
	}

	/**
	 * Test 4.6: This test method verifies below tests: 1). Verify that owner shall
	 * be able to revoke guest access for any user by clicking on the delete icon
	 * for the desired guest user. 2). Verify that after the account owner revokes a
	 * guest user access from the list by clicking on the trash icon, the respective
	 * guest user shall not be able to view the same account under their login id
	 * upon their next login.
	 */
	public void verifyRevokeGuestAccess(SoftAssert softAssert, User user) {
		log.info("Verify that owner shall be able to revoke guest access for "
				+ "any user by clicking on the delete icon for the desired guest user.");
		log.info("Test Case execution for - verifyRevokeGuestAccess - is Initiated");
		LoginSteps loginsteps = new LoginSteps(driver);
		HomeSteps homeSteps = new HomeSteps(driver);
		registrationConfig = CSPConfiguration.initMCspConfig();
		// Login into the application.
		SignOutAndBackToLoginPage(driver);
		loginsteps.loginIntoTheApplication(user.getUserName(), user.getPassword());

		// Check whether the MyAccount module is enabled from csp end or not.
		if (registrationConfig.get("MyAccount") == 1) {
			// Check whether the guest user module is enabled from csp end or not.
			if (registrationConfig.get("GuestUser.Invite") == 1) {
				// Navigate to the guest user page.
				homeSteps.navigateToGuestUser();
				deleteGuestUser(softAssert);
			} else {
				log.info("Guest user link is not available on the my account page.");
			}
		} else {
			log.info("My account link is not available on the header");
		}
		log.info("Verify that owner shall be able to revoke guest access for any "
				+ "user by clicking on the delete icon for the desired guest user.");
		log.info("Test Case execution for - verifyRevokeGuestAccess - is Completed");
	}

	/**
	 * Test 4.7: This test method verifies below tests: 1). Verify that the 'resend
	 * activation link' icon shall be disabled for already activated guest users.
	 */
	public void verifyResendActivationLinkDisabled(String accountNumber) {
		log.info("Verify that the 'resend activation link' "
				+ "icon shall be disabled for already activated guest users.");
		log.info("Test Case execution for - verifyResendActivationLinkDisabled - is Initiated");
		// Verifying resend activation icon visibility.
		getBtnThreeDots(accountNumber);
		pause(1000);
		Boolean flag = btnResendInvitationIcon(accountNumber).isDisplayed();
		Assert.assertTrue(!btnResendInvitationIcon(accountNumber).isDisplayed(),
				"Resend activation link is not displaying.");
		pause(1000);
		log.info("Verify that the 'resend activation link' icon shall be disabled "
				+ "for already activated guest users.");
		log.info("Test Case execution for - verifyResendActivationLinkDisabled - is Completed");
	}

	public void verifyGuestUserNameAfterRegAtOwnerSide(SoftAssert softAssert, String userId, String password,
			String guestEmail, String accountNum) {
		log.info("Verify that owner shall be able to revoke "
				+ "guest access for any user by clicking on the delete icon for the desired guest " + "user.");
		log.info("Test Case execution for - verifyRevokeGuestAccess - is Initiated");
		// Login into the application.
		LoginSteps loginsteps = new LoginSteps(driver);
		HomeSteps homeSteps = new HomeSteps(driver);
		registrationConfig = CSPConfiguration.initMCspConfig();
		// Check whether the MyAccount module is enabled from csp end or not.
		if (registrationConfig.get("MyAccount") == 1) {
			// Check whether the guest user module is enabled from csp end or not.
			if (registrationConfig.get("GuestUser.Invite") == 1) {
				// Navigate to the guest user page.
				homeSteps.navigateToGuestUser();
				verifyResendActivationLinkDisabled(accountNumber);
				// Verify Guest Username at Account Owner side at Guest User page
				// String fName = guestCustomer.get("First Name");
				// String lName = guestCustomer.get("Last Name");
				// By locator = By.xpath("//label[text() = '" + guestEmail +
				// "']//preceding::h5[1]/label");
				// pageUtil.verifyObjectLabelEquals(fName + " " + lName,
				// pageUtil.getObjectLabel(locator));
			} else {
				log.info("Guest user link is not available on the " + "my account page.");
			}
		} else {
			log.info("My account link is not available on the header");
		}
		log.info("Verify that owner shall be able to revoke "
				+ "guest access for any user by clicking on the delete icon for the desired guest " + "user.");
		log.info("Test Case execution for - verifyRevokeGuestAccess - is Completed");
	}

	/**
	 * Test 3.2: This test method verifies below tests: 1). Verify that guest user
	 * can choose to create a new username password to access guest account. 2).
	 * Verify that the property manager/guest user can not see the guest user tab.
	 * 
	 * @throws InterruptedException
	 */
	public void verifyNewRegisterGuestUserAbleToLogin(SoftAssert softAssert, String userName, String password)
			throws InterruptedException {
		log.info("Verify that guest user can choose to create a new username password " + "to access guest account.");
		log.info("Test Case execution for - registerNewGuestUser - is Initiated");
		LoginSteps loginSteps = new LoginSteps(driver);
		HomeSteps homeSteps = new HomeSteps(driver);
		MyProfilePage profilepage = new MyProfilePage(driver);
		// Login in to the application for the very first time.
		SignOutAndBackToLoginPage(driver);
		loginIntoTheApplication(userName, password);
		if (registrationConfig.get("MyAccount") == 1) {
			int count = 0;
			homeSteps.navigateToAccountInformation();
			HashMap<String, String> roleAccount = new HashMap<>();
			List<WebElement> roles = TdMyAccountRole();
			List<WebElement> accountNumber = TdServiceAccountNomap();
			for (WebElement ele : roles) {
				String role = ele.getText().trim();
				String accountNo = accountNumber.get(count).getText().trim();
				roleAccount.put(role, accountNo);
				count = count + 1;
			}
			System.out.println(roleAccount);
			String actAccountNo = roleAccount.get("Guest User Access").trim();
			softAssert.assertEquals(actAccountNo, this.accountNumber,
					"Account number is not matched with the account whom " + "guest access is given.");
			// Verify guest user side menu link is not displaying.
			// softAssert.assertTrue(pageUtil.isElementDisplayed(guestUserPage.getLnkGuestUserSideMenuGup()),
			// "Guest user access account is displaying guest user side bar link.");
			// Verify Guest User name at Profile page
			String fName = guestCustomer.get("First Name");
			String lName = guestCustomer.get("Last Name");
			String guestEmail = testDataJson.getStringJsonValue("inviteNewUserEmail");
			// pageUtil.verifyObjectLabelEquals(fName + " " + lName, pageUtil
			// .getObjectLabel(accountProfilePage.getValueUsername()));
			// pageUtil.verifyObjectLabelEquals(guestEmail,
			// pageUtil.getObjectLabel(accountProfilePage
			// .getValuePrimaryEmail()));
		} else {
			log.info("My account link is not available on the header");
		}
		log.info("Verify that guest user can choose to create a new username password " + "to access guest account.");
		log.info("Test Case execution for - registerNewGuestUser - is Completed");
	}

	public String registerNewGuestUser() {
		log.info("C23432 - Verify that guest user can choose to create "
				+ "a new username password to access guest account.");
		log.info("Test Case execution for - registerNewGuestUser - is Initiated");
		// Getting the residential registration data.
		guestCustomer = testDataJson.getMapJsonObject("newGuestRegistrationData");
		// Getting fields label and their fields elements
		guestRegLabelsFieldsMap = getFieldsLabelElementsOnRegistrationPage();
		// Populating form present over the multiple pages.
		username = populateNewGuestRegForm(guestRegLabelsFieldsMap, guestCustomer);
		// Check terms and conditions if unchecked.
		checkTermsCondIfUnchecked();
		// Click next or registration button whichever displayed on the registration
		// screen
		clickNextOrRegisterBtn();
		// Wait for the page loader.
		pause(10000);

		log.info("Verify that guest user can choose to create " + "a new username password to access guest account.");
		log.info("Test Case execution for - registerNewGuestUser - is Completed");
		return username;
	}

	/**
	 * This method is used to fill the registration form fields present on either
	 * single page or multiple pages.
	 *
	 * @param regLabelsFields
	 * @param customer
	 */
	public String populateNewGuestRegForm(Map<String, WebElement> regLabelsFields, Map<String, String> customer) {
		scrollToTop();
		for (Map.Entry<String, WebElement> entry : regLabelsFields.entrySet()) {
			switch (entry.getKey()) {
			case "First Name":
				// Fill first name if data set have value for it
				sendKeys(entry.getValue(), customer.get("First Name"));
				break;
			case "Last Name":
				// Fill last name if data set have value for it
				sendKeys(entry.getValue(), customer.get("Last Name"));
				break;
			case "Username":
				// Fill username if data set have value for it
				;
				RandomUtil.Mode mode = RandomUtil.Mode.NUMERIC;
				String randomNo = null;
				try {
					randomNo = RandomUtil.generateRandomString(4, mode);
				} catch (Exception e) {
					e.printStackTrace();
				}
				username = customer.get("Username") + randomNo;
				sendKeys(entry.getValue(), username);
				break;
			case "Password":
				// Fill password if data set have value for it

				sPass = customer.get("Password");
				sendKeys(entry.getValue(), sPass);
				break;
			case "Confirm Password":
				// Fill confirm password if data set have value for it
				sendKeys(entry.getValue(), customer.get("Confirm Password"));
				break;
			case "Security Question 1:":
				// Select security question 1 if data set have value for it
				selectByVisibleText(entry.getValue(), customer.get("Security Question 1:"));
				break;
			case "Security Answer 1":
				// Fill security answer 1 if data set have value for it
				sendKeys(entry.getValue(), customer.get("Security Answer 1"));
				break;
			case "Security Question 2:":
				// Select security question 2 if data set have value for it
				selectByVisibleText(entry.getValue(), customer.get("Security Question 2:"));
				break;
			case "Security Answer 2":
				// Fill security answer 2 if data set have value for it
				sendKeys(entry.getValue(), customer.get("Security Answer 2"));
				break;
			case "Primary Phone Number":
				// Fill primary contact number if data set have value for it
				sendKeys(entry.getValue(), customer.get("Primary Phone"));
				break;
			case "Contact Type":
				// Select contact type if data set have value for it
				selectByVisibleText(entry.getValue(), customer.get("Contact Type"));
				break;
			default:
				log.info("All field on the page are not populated");
			}
		}
		return username;
	}

	/**
	 * This method is used to check the terms and condition checkbox if it is
	 * visible or unchecked.
	 */
	public void checkTermsCondIfUnchecked() {
		// Scroll down to the end of the page.
		scrollToBottom();
		checkCheckBoxTermsAndConditions();

	}

	/**
	 * This method is used to click next or register button depending upon the
	 * visibility.
	 */
	public void clickNextOrRegisterBtn() {
		// Scroll down to the end of the page.
		scrollToBottom();
		// Click next button if present.
		if (isNextNewGuestRegVisible() == true) {
			clickNextNewGuestRegistrationBtn();
			pause(2000);
		}
		// Click register button if present.
		else if (isRegisterNewGuestVisible() == true) {
			clickRegisterNewGuestBtn();
			pause(100);
		} else {
			log.info("Neither next button is displaying nor register button.");
		}
	}

	/**
	 * This method is used to get the registration fields present on the page.
	 *
	 * @return mRegistrationLabelsAndFields
	 */
	public Map<String, WebElement> getFieldsLabelElementsOnRegistrationPage() {
		String selectSQ1 = "Security Question 1:";
		String selectSQ2 = "Security Question 2:";
		String selectContactType = "Contact Type";
		Map<String, WebElement> regLabelsFields = new LinkedHashMap<>();
		List<WebElement> regFieldLabels = getObjectsLabelNewGuestUserRegFields();
		List<WebElement> regFieldsInput = getObjectsTextNewGuestUserRegFields();
		List<WebElement> regFieldsSelect = getObjectsddNewGuestUserRegFields();
		int j = 0, k = 0;
		for (int i = 0; i < regFieldLabels.size(); i++) {
			WebElement element = null;
			String fieldLabel = regFieldLabels.get(i).getText().trim();
			if (fieldLabel.equals(selectSQ1) || fieldLabel.equals(selectSQ2) || fieldLabel.equals(selectContactType)) {
				element = regFieldsSelect.get(j);
				j++;
			} else {
				element = regFieldsInput.get(k);
				k++;
			}
			regLabelsFields.put(fieldLabel, element);
		}
		return regLabelsFields;
	}

	public void navigateActivationUrl(String user, SoftAssert sAssert) {

		guestUserInviteSub = guestuserTextProp.getPropValue("txtSubjectGuestUserInvite");
		String emailBody = getGuestUserInviteEmailBody(email, guestUserInviteSub);
		String startString;
		String endString;
		if (user.equals("newUser")) {
			startString = "you wish to create separate credentials from your existing then please <a target='_blank' "
					+ "href='";
			endString = "'>register here </a>";
		} else {
			startString = "You can link this service account to your existing credentials on Smart Energy Water "
					+ "application. If you wish to proceed, please <a target='_blank' href='";
			endString = "'>click here </a>";
		}
		int lenOfStartStr = startString.length();
		activationUrl = emailBody.substring(emailBody.indexOf(startString) + lenOfStartStr,
				emailBody.indexOf(endString));
		// Verifying activation url is not empty.
		sAssert.assertTrue(!activationUrl.isEmpty(), "Activation url is empty");
		// Navigating to the fetched url.
		System.out.println("Activation url=> " + activationUrl);
		DriverFactory.navigateToPage(activationUrl);

		// Assert.assertTrue(emailBody.contains(Utils.getRbTextValue("txtLblGuestuserMailingApp")));
		pause(2000);
	}

	/**
	 * This method is to get the email body for guest user invitation from the
	 * database.
	 *
	 * @param email
	 * @param guestUserInviteSub
	 * @return
	 * @throws SQLException
	 */
	public String getGuestUserInviteEmailBody(String email, String guestUserInviteSub) {
		pause(2000);
		String emailBody = null;
		String isEmailSent = null;
		String emailBodyQuery = SQLQueries.getGuestUserInviteMailContentQuery(email, guestUserInviteSub);
		ResultSet rsEmailBody;
		try {
			rsEmailBody = DataBaseUtils.getResultSet(emailBodyQuery);
			while (rsEmailBody.next()) {
				emailBody = rsEmailBody.getString("Message");
				isEmailSent = rsEmailBody.getString("IsNotify");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emailBody;
	}

	/**
	 * This method is used to invite non scm user as guest.
	 *
	 * @param accountNo
	 * @param email
	 */
	public void inviteNonScmUserAsGuest(SoftAssert sAssert, String accountNo, String email, String role) {
		log.info("Inviting user with name : " + accountNo + " and email : " + email);
		log.info("Test Case execution for - inviteNonScmUserAsGuestUser - is Initiated");
		pause(3000);
		// Open invite user pop-up
		clickOnInviteUser();
		driver.switchTo().activeElement();
		pause(500);
		sAssert.assertEquals(getLabelInviteUser(), guestuserTextProp.getPropValue("txtLblMsgPopUpInviteUser"));
		String futureDate = DateUtil.getFutureDate(6);
		// Populating invite user pop-up
		selectServiceAccountnumber(accountNumber);
		pause(2000);
		populateGuestUserName("Guest User Access");
		selectGuestUserRole(role);
		pause(500);
		populateGuestUserEmailId(email);
		populateAccessPeriod(futureDate);
		checkchkBoxAgreeTeBtn();
		clickSubmitButton();
		//String actToastMsg = getToastMessage();
		//sAssert.assertEquals(actToastMsg, guestuserTextProp.getPropValue("txtMsgSuccessfulGuestInvite"),
			//	"Successful guest invite toast message is not matched.");
		pause(10000);
	}

	/**
	 * Test 3.3: This method is used to verify the below tests: 1). To verify the
	 * resends the activation functionality from their guest user tab. 2). Verify
	 * that the resends activation link shall be visible for any guest till the time
	 * the guest has not activated their account.
	 *
	 * @param softAssert
	 * @param accountNumber
	 */
	public void verifyResendActivationLinkFunctionality(SoftAssert sAssert, String accountNumber) {
		log.info("To verify resend the activation functionality from their guest user tab.");
		log.info("Test Case execution for - verifyResendActivationLinkFunctionality - is Initiated");
		// Verifying resend activation icon visibility.
		getBtnThreeDots(accountNumber);
		sAssert.assertTrue(btnResendInvitationIcon(accountNumber).isDisplayed(),
				"Resend activation link is not displaying before approving guest user request.");
		if (btnResendInvitationIcon(accountNumber).isDisplayed()) {
			String invitationStatus = getStatusOfInvitation(accountNumber);
			String expInvitationStatus = guestuserTextProp.getPropValue("txtLblInvitationPendingStatus");
			sAssert.assertEquals(invitationStatus, expInvitationStatus, "Invitation status not matched.");
			String expToastMsg = guestuserTextProp.getPropValue("txtLblResendInvitationToastMsg");
			clickBtnResendInvitationIcon(accountNumber);
			String actToastMsg = getToastMessage();
			sAssert.assertEquals(actToastMsg, expToastMsg, "Resend activation link toast message not matched");
		} else {
			sAssert.assertTrue(btnResendInvitationIcon(accountNumber).isDisplayed(),
					"Resend activation link is not displaying before approving guest user request.");
		}
		log.info("To verify resend the activation functionality from their guest user tab.");
		log.info("Test Case execution for - verifyResendActivationLinkFunctionality - is Completed");
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

	/**
	 * Test 2.2: This test method verifies below tests: 1). C74762 - Verify the
	 * error messages when user enters their own primary email address in the email
	 * field.
	 */
	public void verifyErrMsgUserHaveOwnerAccess(SoftAssert sAssert, String accountNumber, String role, User user) {
		log.info("C74762 - Verify the error messages when user enters their own "
				+ "primary email address in the email field.");
		log.info("Test Case execution for - verifyErrMsgUserHaveOwnerAccess - is Initiated");
		sAssert.assertEquals(isLabelInviteUserVisible(), guestuserTextProp.getPropValue("txtLblMsgPopUpInviteUser"));
		String guestUserName = testDataJson.getStringJsonValue("guestUserNameGup");
		String futureDate = DateUtil.getFutureDate(6);
		String guestEmailAddress = null;
		clickOnInviteUser();
		ResultSet resultSet;
		try {
			resultSet = DataBaseUtils.getResultSet(SQLQueries.getLoggedInUsersEmail(user.getUserName()));
			resultSet.next();
			guestEmailAddress = resultSet.getString("EmailID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Populating invite user popup
		selectServiceAccountnumber(accountNumber);
		populateGuestUserName(guestUserName);
		pause(200);
		selectGuestUserRole(role);
		populateGuestUserEmailId(guestEmailAddress);
		pause(200);
		populateAccessPeriod(futureDate);
		pause(200);
		checkchkBoxAgreeTeBtn();
		pause(200);
		clickSubmitButton();
		// pause(200);
		// String toastMsg = getToastMessage();
		// sAssert.assertEquals(toastMsg,
		// guestuserTextProp.getPropValue("txtLblErrMsgEmailOwnerAccessGup"));
		// if (isToastCloseVisible() == true)
		clickToastCloseBtn();
		pause(2000);

		// Close invite guest user popup if opened
		clickCloseIfDisplayed();
		// Delete existing guest users if displayed.
		deleteGuestUser(sAssert);
		log.info("C74762 - Verify the error messages when user enters"
				+ " their own primary email address in the email field.");
		log.info("Test Case execution for - verifyErrMsgUserHaveOwnerAccess - is Completed");
	}

	/**
	 * This method is used to click close button if displayed.
	 */
	public void clickCloseIfDisplayed() {
		if (isCloseInviteUserPopupVisible() == true) {
			clickCloseInviteUserPopupBtn();
		}
	}

	/**
	 * Test 2.3: This test method verifies below tests: 1)Verify user cannot request
	 * guests more than configured in CSP.
	 */
	public void verifyNumberOfGuestInviteIsAsPerCsp(SoftAssert sAssert, String accountNumber, String role) {
		log.info("Verify user can not request guests more than" + " configured in CSP.");
		log.info("Test Case execution for - verifyNumberOfGuestInviteIsAsPerCsp - is Initiated");
		Map<String, String> cspGuestUserConfig = CSPConfiguration.initCspGuestUserConfig();
		int iMaxCountInvitesResidential = Integer.parseInt(cspGuestUserConfig.get("GuestUser"));
		int iMaxCountInvitesCommercial = Integer.parseInt(cspGuestUserConfig.get("GuestUser_C"));
		String sAddressType = getAddressType(accountNumber);
		int iMaxNoOfUserInvite = 0;
		if (sAddressType.equals("Residential")) {
			iMaxNoOfUserInvite = iMaxCountInvitesResidential;
		} else if (sAddressType.equals("Commercial")) {
			iMaxNoOfUserInvite = iMaxCountInvitesCommercial;
		}
		String expErrMsg = guestuserTextProp.getPropValue("txtLblErrMsgMaxNoUserInvitedGup");
		expErrMsg = expErrMsg.replace("noUsers", String.valueOf(iMaxNoOfUserInvite));
		for (int i = 1; i < iMaxNoOfUserInvite; i++) {

			pause(500);
			clickOnInviteUser();
			driver.switchTo().activeElement();
			pause(2000);
			// Verifying invite guest user
			getLabelInviteUser();
			sAssert.assertEquals(getLabelInviteUser(), guestuserTextProp.getPropValue("txtLblMsgPopUpInviteUser"));
			String guestUserName = testDataJson.getStringJsonValue("guestUserNameGup");
			RandomUtil.Mode mode = RandomUtil.Mode.NUMERIC;
			String randomNo = null;
			try {
				randomNo = RandomUtil.generateRandomString(4, mode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String guestEmailAddress = "guest.user" + randomNo + "@mailinator.com";
			String futureDate = DateUtil.getFutureDate(6);
			// Populating invite user popup
			selectServiceAccountnumber(accountNumber);
			populateGuestUserName(guestUserName);
			selectGuestUserRole(role);
			populateGuestUserEmailId(guestEmailAddress);
			populateAccessPeriod(futureDate);
			checkchkBoxAgreeTeBtn();
			clickSubmitButton();

			if (i == iMaxNoOfUserInvite) {
				String sActToastMaxLimitReached = getToastMessage();
				sAssert.assertEquals(sActToastMaxLimitReached, expErrMsg,
						"Toast message for max invite limit reached not matched.");
			}
		}
		// Close invite guest user popup if opened
		clickCloseIfDisplayed();
		// Pause
		pause(1000);
		// Delete the guest user
		deleteGuestUser(sAssert);
		// Logging
		log.info("Verify user can not request guests more than " + "configured in CSP.");
		log.info("Test Case execution for - verifyNumberOfGuestInviteIsAsPerCsp - is Completed");
	}

	public String getAddressType(String sUtilityAccountNumber) {
		String sAddressType = null;
		try {
			ResultSet resultSet = DataBaseUtils.getResultSet(SQLQueries.getAddressTypeQuery(sUtilityAccountNumber));
			resultSet.next();
			sAddressType = resultSet.getString("AddressType");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sAddressType;
	}

	/**
	 * Test 2.4: This test method verifies below tests: 1). C74764 - Verify that
	 * system shall not allow the same guest user to be assigned multiple roles for
	 * the same account.
	 */
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

			String guestUserName = testDataJson.getStringJsonValue("guestUserNameGup");
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

	public void deleteGuestUser(SoftAssert sAssert) {
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
			sAssert.assertEquals(actRevokeConfirmationMsg, expRevokeConfirmationMsg,
					"Revoke confirmation message is not matched.");
			// pause(1000);
			// clickContinueConfirmationMsgBtn();
			pause(10000);
		}
	}

	public void verifyObjectsOnGuestUserPage(SoftAssert sAssert) {
		log.info("C74755 | C74756 - Verify the objects on the invite guest user page.");
		log.info("Test Case execution for - verifyObjectsOnGuestUserPage - is Initiated");
		if (getListTabsGuestUserDetails().size() > 0) {

			// sAssert.assertTrue(isClmnActionVisible(),"Action field is not visible");
			sAssert.assertTrue(isClmnStatusVisible(), "Status field is not visible");
			sAssert.assertTrue(isClmnServiceAccountNumberVisible(), "Service account number field is not visible");
			sAssert.assertTrue(isClmnNameVisible(), "Name field is not visible");
			sAssert.assertTrue(isClmnRoleVisible(), "Role field is not visible");
			sAssert.assertTrue(isClmnEmailVisible(), "Email field is not visible");
			sAssert.assertTrue(isClmnInvitationDateVisible(), "Invitation date field is not visible");
			sAssert.assertTrue(isClmnExpirationDateVisible(), "Expiration date field is not visible");
		}

		log.info("Test Case execution for - verifyObjectsOnGuestUserPage - is Completed");
	}

	public void verifyInviteUserPopup(SoftAssert sAssert) {
		log.info("C74757 - Verify that the invite guest user button"
				+ " opens a popup for the user to provide information to request a guest user.");
		log.info("Test Case execution for - verifyInviteUserPopup - is Initiated");
		clickOnInviteUser();
		driver.switchTo().activeElement();
		sAssert.assertTrue(getLabelInviteUser().contains(guestuserTextProp.getPropValue("txtLblMsgPopUpInviteUser")));
		pause(1000);
		sAssert.assertTrue(isAccountDropdownVisible(), "Account Dropdown is not visible");
		sAssert.assertTrue(isInviteUserNameVisible(), "Invite user name  is not visible");
		sAssert.assertTrue(isInviteRoleVisible(), "role name  is not visible");
		sAssert.assertTrue(isGuestUserEmailIdVisible(), "Invite user email is not visible");
		sAssert.assertTrue(isAccessPeriodVisible(), "Invite user access period is not visible");
		sAssert.assertTrue(isCancelBtnVisible(), "Invite user cancel button  is not visible");
		sAssert.assertTrue(isInviteUserBtnVisible(), "Invite user submit button  is not visible");
		log.info("Test Case execution for - verifyInviteUserPopup - is Completed");
	}

	/**
	 * Test 1.3 : This test method verifies below tests: 1). C74758 - Verify that on
	 * the invite guest pop up, the system shall display only owner accounts in the
	 * account number drop-down.
	 */
	public void verifyServiceAccountNumberDropDown(SoftAssert sAssert, List<String> accNoHavingOwnerAccess) {
		log.info("Test Case execution for - verifyServiceAccountNumber - is Initiated");
		List<String> actServiceAccNumberOptions = getAllOptionsinListBox();
		sAssert.assertTrue(actServiceAccNumberOptions.containsAll(accNoHavingOwnerAccess),
				"Accounts displayed in the service account number drop-down are not "
						+ "matched with all owner accounts.");

		log.info("Test Case execution for - verifyServiceAccountNumber - is Completed");
	}

	/**
	 * Test 1.4 : This test method verifies below tests: 1). C74759 - Verify that
	 * clicking either the close or cancel button on the invite guest user pop-up
	 * will close the pop-up.
	 */
	public void verifyCloseInviteUserPopup() {
		log.info("C74759 - Verify that clicking either the close or cancel button "
				+ "on the invite guest user popup will close the popup.");
		log.info("Test Case execution for - verifyCloseInviteUserPopup - is Initiated");
		clickCancelButton();
		pause(1000);
		log.info("C74759 - Verify that clicking either the close or cancel button "
				+ "on the invite guest user popup will close the popup.");
		log.info("Test Case execution for - verifyCloseInviteUserPopup - is Completed");
	}

	/**
	 * Test 1.5 : This test method verifies below tests: 1). Verify that the user is
	 * able to submit the guest user pop-up.
	 */
	public void verifyInviteUserSubmitSuccessfully(SoftAssert sAssert, String accountNumber, String role) {
		log.info("Verify that the user is able to submit the guest user popup.");
		log.info("Test Case execution for - verifyInviteUserSubmitSuccessfully - is Initiated");
		clickOnInviteUser();
		driver.switchTo().activeElement();
		pause(2000);
		sAssert.assertEquals(getLabelInviteUser(), guestuserTextProp.getPropValue("txtLblMsgPopUpInviteUser"));

		String guestUserName = testDataJson.getStringJsonValue("guestUserName");
		String guestEmailAddress = testDataJson.getStringJsonValue("guestUserEmail");
		String futureDate = DateUtil.getFutureDate(6);
		// Populating invite guest user invite form.
		selectServiceAccountnumber(accountNumber);
		populateGuestUserName(guestUserName);
		selectGuestUserRole(role);
		populateGuestUserEmailId(guestEmailAddress);
		// populateAccessPeriod(futureDate);
		checkchkBoxAgreeTeBtn();
		clickSubmitButton();
		// Verifying successful toast message for guest user invite
		String toastMsg = getToastMessage();
		sAssert.assertEquals(toastMsg, guestuserTextProp.getPropValue("txtMsgSuccessfulGuestInvite"));
		// Deleting guest user after verification.
		pause(3000);
		deleteGuestUser(sAssert);
		log.info("Verify that the user is able to submit the guest user popup.");
		log.info("Test Case execution for - verifyInviteUserSubmitSuccessfully - is Completed");
	}

	/**
	 * This method is used to get all the account having owner access.
	 *
	 * @return
	 */

	public void inviteExistingUserWithGuestAccess(SoftAssert sAssert, User user) {

		log.info("C74761, C74762, C74764, C74817 - To verify the guest user page.");
		log.info("Test Case execution for - verifyErrorMsgForInviteUserPopup - is Initiated");
		SoftAssert softAssert = new SoftAssert();
		registrationConfig = CSPConfiguration.initMCspConfig();
		HomeSteps homeSteps = new HomeSteps(driver);
		LoginSteps loginsteps = new LoginSteps(driver);
		accountWithRoleId = getAllLinkedAccountsWithRoleId(user.getUserName());
		String sTestDataScpJsonFP = FilePaths.SCP_JSON_TEXT_PROPERTIES;
		String testDataJsonName = "TestGuestUser.json";
		testDataJson = new JsonUtil(sTestDataScpJsonFP, testDataJsonName);
		// Get the account number from the database which have owner access.
		List<String> accNoHavingOwnerAccess = new ArrayList<>();
		for (Map.Entry<String, String> entry : accountWithRoleId.entrySet()) {
			if (entry.getValue().equals("3")) {
				accNoHavingOwnerAccess.add(entry.getKey());
			}
		}
		name = testDataJson.getStringJsonValue("existingUsername");
		email = testDataJson.getStringJsonValue("inviteExistingUserEmail");
		pass = testDataJson.getStringJsonValue("existingUserPassword");
		if (accNoHavingOwnerAccess.size() > 0) {
			// Getting user account number
			accountNumber = accNoHavingOwnerAccess.get(0);
			// Getting role type
			role = testDataJson.getStringJsonValue("roleGuest");
			// Login into the application.

			if (accNoHavingOwnerAccess.size() > 0) {
				// Getting the account number

				// Getting guest role.
				role = testDataJson.getStringJsonValue("roleGuest");
				// Check whether the MyAccount module is enabled from csp level.

				if (registrationConfig.get("MyAccount") == 1) {
					if (registrationConfig.get("GuestUser.Invite") == 1) {
						// Navigate to the guest user page.
						homeSteps.navigateToGuestUser();
						// Delete existing guest user
						deleteGuestUser(softAssert);
						// Inviting guest user
						inviteNonScmUserAsGuest(softAssert, accountNumber, email, role);
						// Waiting for mail template to become into the database
						pause(5000);
						SignOutAndBackToLoginPage(driver);
						// navigate existing user login url using database.
						// DriverFactory.goToPage("existingUser");
						navigateActivationUrl("existingUser", sAssert);
						// Verify first login for existing user.
						verifyFirstLoginUserGuestAccess(softAssert, email, name, pass);
						// Verify that with guest user access user is not able to
						// enroll and enroll for budget my bill.
						verifyUserWithGuestAccessNotEditBudgetBill(softAssert, accountNumber);
						// Verify that with guest user access user is not able to enroll and enroll for
						// level pay.
						verifyWithGuestAccessNotEditLevelPay(softAssert, accountNumber);
						// Verify that with guest user access user is able to send the billing queries.
						verifyWithGuestAccessBillingQuerySubmitted(softAssert, accountNumber);
						// Verify that the guest access not able to edit the billing preferences.
						// Need to check this---> verifyWithGuestAccessNotEditBillingPref(softAssert,
						// sAccountNumber);
						// Verify revoking guest access of guest user
						SignOutAndBackToLoginPage(driver);
						loginsteps.loginIntoTheApplication(user.getUserName(), user.getPassword());

						// Navigate to the guest user page.
						homeSteps.navigateToGuestUser();
						// Delete existing guest user.
						deleteGuestUser(softAssert);
						// verifyRevokeGuestAccess(softAssert, sUserId, sPassword);
					} else {
						log.info("Guest user link is not available on the MyAccount page.");
					}
				} else {
					log.info("MyAccount link is not available on the header.");
				}
			} else {
				log.info("Given user have no account with owner or property manager access.");
			}
			log.info("Test Case execution for - verifyErrorMsgForInviteUserPopup - is Completed.");
		}
	}

	/**
	 * Test 8.4: This test method verifies below tests: 1). Guest User | To verify
	 * that user is able to submit billing query successfully for guest property.
	 *
	 * @param softAssert
	 * @param sAccountNumber
	 */
	public void verifyWithGuestAccessBillingQuerySubmitted(SoftAssert softAssert, String sAccountNumber) {
		log.info("Guest User | To verify that user is able to submit "
				+ "billing query successfully for guest property.");
		log.info("Test Case execution for - verifyWithGuestAccessNotEditLevelPay - is Initiated");
		// Checking whether the billing module is enabled or not.
		registrationConfig = CSPConfiguration.initMCspConfig();
		DashboardPage dashboardpage = new DashboardPage(driver);

		if (registrationConfig.get("Billing") == 1) {
			// Select given account for which we need to verify
			// dashboardpage.clickAccountAddress();
			// getSelectAccountOption(sAccountNumber);
			BillDashboardPage billdashboardpage = new BillDashboardPage(driver);
			clickBillingLink();
			// boolean isBillingQueriesVisible =
			// billdashboardpage.islnkBudgetMyBillSideMenuVisible();
			// softAssert.assertTrue(isBillingQueriesVisible,
			// "Billing query is not visible to the " + "account having guest access.");
			// clickBillingLink();
		} else {
			log.info("Billing link is not available on the header");
		}
		log.info("Guest User | To verify that user is able to submit "
				+ "billing query successfully for guest property.");
		log.info("Test Case execution for - verifyWithGuestAccessNotEditLevelPay - is Completed");
	}

	/**
	 * Test 8.3: This test method verifies below tests: 1). Guest Access | Verify
	 * that for guest access account, level pay page is non-editable.
	 *
	 * @param softAssert
	 */
	public void verifyWithGuestAccessNotEditLevelPay(SoftAssert sAssert, String accountNumber) {
		log.info("Guest Access | Verify that for guest access account, level pay page is non-editable.");
		log.info("Test Case execution for - verifyWithGuestAccessNotEditLevelPay - is Initiated");
		registrationConfig = CSPConfiguration.initMCspConfig();

		// Checking whether the billing module is enabled or not.
		if (registrationConfig.get("Billing") == 1) {
			clickBillingLink();
			// sAssert.assertTrue(IsLevelPayLinkVisible());

		} else {
			log.info("Billing link is not available on the header");
		}
		log.info("Guest Access | Verify that for guest access account, " + "level pay page is non-editable.");
		log.info("Test Case execution for - verifyWithGuestAccessNotEditLevelPay - is Initiated");
	}

	/**
	 * Test 8.2: This test method verifies below tests: 1). Verify that on the
	 * budget my bill page, guest user having 'guest access' should not be able to
	 * edit the 'monthly budget'.
	 *
	 * @param softAssert
	 */
	public void verifyUserWithGuestAccessNotEditBudgetBill(SoftAssert softAssert, String accountNumber) {
		log.info("Verify that on the budget my bill page, guest user "
				+ "having 'guest access' should not be able to edit the 'monthly budget'");
		log.info("Test Case execution for - verifyBudgetMyBillNonEditable - is Initiated");
		registrationConfig = CSPConfiguration.initMCspConfig();
		HomeSteps homeSteps = new HomeSteps(driver);

		// Checking whether the billing module is enabled or not.
		if (registrationConfig.get("Billing") == 1) {

			// Getting budget my bill enabled from csp end.
			if (registrationConfig.get("Billing.BudgetMyBill") == 1) {
				// Navigating to the budget my bill page and validating
				homeSteps.navigateToBudgetMyBill();
				// String actLabel =
				// pageUtil.getObjectlLabel(billDashboardPage.getLblBudgetBillPageHeading());
				// softAssert.assertEquals(actLabel,
				// guestuserTextProp.getPropValue("lblTxtBudgetBillPageHeadingBdp"));
			} else {
				log.info("Budget My Bill link is not available on the Billing sidebar menu");
			}
		} else {
			log.info("Billing link is not available on the header");
		}
		log.info("Verify that on the budget my bill page, guest user "
				+ "having 'guest access' should not be able to edit the 'monthly budget'");
		log.info("Test Case execution for - verifyBudgetMyBillNonEditable - is Completed");
	}

	public void propertyManagerAccessExpireOnEndDate(SoftAssert sAssert, User user) throws InterruptedException {
		HomeSteps homeSteps = new HomeSteps(driver);
		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		String sTestDataScpJsonFP = FilePaths.SCP_JSON_TEXT_PROPERTIES;
		String testDataJsonName = "TestGuestUser.json";
		accountWithRoleId = getAllLinkedAccountsWithRoleId(user.getUserName());
		testDataJson = new JsonUtil(sTestDataScpJsonFP, testDataJsonName);
		registrationConfig = CSPConfiguration.initMCspConfig();
		// Get the account number from the database which have owner access.
		List<String> accNoHavingOwnerAccess = new ArrayList<>();
		for (Map.Entry<String, String> entry : accountWithRoleId.entrySet()) {
			if (entry.getValue().equals("3")) {
				accNoHavingOwnerAccess.add(entry.getKey());
			}
		}
		// Fetching mail and name of the guest user.
		name = testDataJson.getStringJsonValue("existingUsername");
		email = testDataJson.getStringJsonValue("inviteExistingUserEmail");
		pass = testDataJson.getStringJsonValue("existingUserPassword");
		if (accNoHavingOwnerAccess.size() > 0) {
			// Getting user account number
			accountNumber = accNoHavingOwnerAccess.get(0);
			role = testDataJson.getStringJsonValue("rolePropertyManager");
			// Check whether the MyAccount module is enabled from csp level.
			if (registrationConfig.get("MyAccount") == 1) {
				if (registrationConfig.get("GuestUser.Invite") == 1) {
					// Navigate to the guest user page.
					homeSteps.navigateToGuestUser();
					// Delete existing guest user.
					deleteGuestUser(softAssert);
					// Verify no guest user added
					verifyNoGuestUserAdded(softAssert);
					// Inviting guest user

					inviteNonScmUserAsGuest(softAssert, accountNumber, email, role);
					// Waiting for mail template to become into the database
					pause(5000);
					SignOutAndBackToLoginPage(driver);
					// navigate existing user login url using database.
					navigateActivationUrl("existingUser", sAssert);
					// Verify first login for existing user
					verifyFirstLoginUserGuestAccess(softAssert, email, name, pass);

					SignOutAndBackToLoginPage(driver);
					// Getting past date for updating access expiry period.
					String sBackDate = DateUtil.getPastDate(2);
					// Updating access expiry period to the back date
					updateGuestAccExpDate(accountNumber, sBackDate);
					// Login in to the application for the very first time.
					loginSteps.loginIntoTheApplication(name, pass);
					// Check whether the MyAccount module is enabled or not.
					if (registrationConfig.get("MyAccount") == 1) {
						homeSteps.navigateToAccountInformation();
						LinkedHashMap<String, String> roleAccount = new LinkedHashMap<>();
						List<WebElement> roles = TdMyAccountRole();
						List<WebElement> accountNumber = TdServiceAccountNomap();
						int count = 0;
						for (WebElement ele : roles) {
							String role = ele.getText().trim();
							if (role.contains("Property Manager Access")) {
								String accountNo = accountNumber.get(count).getText().trim();
								roleAccount.put(role, accountNo);
							}
							count++;
						}
						if (roleAccount.size() > 0) {
							String actAccountNo = roleAccount.get("Property Manager Access").trim();
							softAssert.assertEquals(actAccountNo, this.accountNumber,
									"Account number is not matched with the account whom guest access is given.");
						}
					} else {
						log.info("My account link is not available on the header");
					}

				} else {
					log.info("Guest user link is not available on the MyAccount page.");
				}
			} else {
				log.info("My Account link is not available on the header");
			}
		} else {
			log.info("Given user have no account with owner or property manager access");
		}

		log.info("Test Case execution for - verifyGuestAccessExpireOnEndDate - is Completed.");
	}

	public void guestAccessExpireOnEndDate(SoftAssert sAssert, User user) throws InterruptedException {
		log.info("C74798, C74800, C74793 - Guest User | Verification of guest user "
				+ "access post end date defined in the period.");
		log.info("Test Case execution for - verifyGuestAccessExpireOnEndDate - is Initiated");
		HomeSteps homeSteps = new HomeSteps(driver);
		SoftAssert softAssert = new SoftAssert();
		LoginSteps loginSteps = new LoginSteps(driver);
		String sTestDataScpJsonFP = FilePaths.SCP_JSON_TEXT_PROPERTIES;
		String testDataJsonName = "TestGuestUser.json";
		accountWithRoleId = getAllLinkedAccountsWithRoleId(user.getUserName());
		testDataJson = new JsonUtil(sTestDataScpJsonFP, testDataJsonName);
		registrationConfig = CSPConfiguration.initMCspConfig();
		// Get the account number from the database which have owner access.
		List<String> accNoHavingOwnerAccess = new ArrayList<>();
		for (Map.Entry<String, String> entry : accountWithRoleId.entrySet()) {
			if (entry.getValue().equals("3")) {
				accNoHavingOwnerAccess.add(entry.getKey());
			}
		}
		// Fetching mail and name of the guest user.
		name = testDataJson.getStringJsonValue("existingUsername");
		email = testDataJson.getStringJsonValue("inviteExistingUserEmail");
		pass = testDataJson.getStringJsonValue("existingUserPassword");
		if (accNoHavingOwnerAccess.size() > 0) {
			// Getting user account number
			accountNumber = accNoHavingOwnerAccess.get(0);
			role = testDataJson.getStringJsonValue("roleGuestUser");
			// Check whether the MyAccount module is enabled from csp level.
			if (registrationConfig.get("MyAccount") == 1) {
				if (registrationConfig.get("GuestUser.Invite") == 1) {
					// Navigate to the guest user page.
					homeSteps.navigateToGuestUser();
					// Delete existing guest user.
					deleteGuestUser(softAssert);
					// Verify no guest user added
					verifyNoGuestUserAdded(softAssert);
					// Inviting guest user

					inviteNonScmUserAsGuest(softAssert, accountNumber, email, role);
					// Waiting for mail template to become into the database
					pause(5000);
					SignOutAndBackToLoginPage(driver);
					// navigate existing user login url using database.
					navigateActivationUrl("existingUser", sAssert);
					// Verify first login for existing user
					verifyFirstLoginUserGuestAccess(softAssert, email, name, pass);

					SignOutAndBackToLoginPage(driver);
					// Getting past date for updating access expiry period.
					String sBackDate = DateUtil.getPastDate(2);
					// Updating access expiry period to the back date
					updateGuestAccExpDate(accountNumber, sBackDate);
					// Login in to the application for the very first time.
					loginSteps.loginIntoTheApplication(name, pass);
					// Check whether the MyAccount module is enabled or not.
					if (registrationConfig.get("MyAccount") == 1) {
						homeSteps.navigateToAccountInformation();
						LinkedHashMap<String, String> roleAccount = new LinkedHashMap<>();
						List<WebElement> roles = TdMyAccountRole();
						List<WebElement> accountNumber = TdServiceAccountNomap();
						int count = 0;
						for (WebElement ele : roles) {
							String role = ele.getText().trim();
							if (role.contains("Guest User Access")) {
								String accountNo = accountNumber.get(count).getText().trim();
								roleAccount.put(role, accountNo);
							}
							count++;
						}
						if (roleAccount.size() > 0) {
							String actAccountNo = roleAccount.get("Guest User Access").trim();
							softAssert.assertEquals(actAccountNo, this.accountNumber,
									"Account number is not matched with the account whom guest access is given.");
						}
					} else {
						log.info("My account link is not available on the header");
					}

				} else {
					log.info("Guest user link is not available on the MyAccount page.");
				}
			} else {
				log.info("My Account link is not available on the header");
			}
		} else {
			log.info("Given user have no account with owner or guest user access");
		}

		log.info("Test Case execution for - verifyGuestAccessExpireOnEndDate - is Completed.");
	}

	public void maxNoGuestUserInvite(SoftAssert sAssert, User user) {

		log.info("C74763, C74803 - To verify account owner shall be allowed to invite "
				+ "<x> number of residential and <y> number of business users as configured from CSP.");
		log.info("Test Case execution for - verifyMaxNoGuestUserInvite - is Initiated");
		SoftAssert softAssert = new SoftAssert();
		Map<String, String> cspGuestUserConfig = CSPConfiguration.initCspGuestUserConfig();
		Map<String, String> accountWithAddType = getAllLinkedAccountWithAddressType(user.getUserName());
		registrationConfig = CSPConfiguration.initMCspConfig();
		HomeSteps homeSteps = new HomeSteps(driver);
		LoginSteps loginsteps = new LoginSteps(driver);
		String sTestDataScpJsonFP = FilePaths.SCP_JSON_TEXT_PROPERTIES;
		String testDataJsonName = "TestGuestUser.json";
		accountWithRoleId = getAllLinkedAccountsWithRoleId(user.getUserName());
		testDataJson = new JsonUtil(sTestDataScpJsonFP, testDataJsonName);
		int iMaxCountInvitesResidential = Integer.parseInt(cspGuestUserConfig.get("GuestUser"));
		int iMaxCountInvitesCommercial = Integer.parseInt(cspGuestUserConfig.get("GuestUser_C"));
		// Get the account number from the database which have owner access.
		List<String> accNoHavingOwnerAccess = new ArrayList<>();
		for (Map.Entry<String, String> entry : accountWithRoleId.entrySet()) {
			if (entry.getValue().equals("3")) {
				accNoHavingOwnerAccess.add(entry.getKey());
			}
		}
		name = testDataJson.getStringJsonValue("inviteExistingUserName");
		email = testDataJson.getStringJsonValue("inviteExistingUserEmail");
		if (accNoHavingOwnerAccess.size() > 0) {
			// Getting user account number
			accountNumber = accNoHavingOwnerAccess.get(0);
			// Login into the application.

			// loginsteps.loginIntoTheApplication(userId, pass);
			// Check whether the MyAccount module is enabled from csp level.
			if (registrationConfig.get("MyAccount") == 1) {
				if (registrationConfig.get("GuestUser.Invite") == 1) {
					// Navigate to the guest user page.

					homeSteps.navigateToGuestUser();
					if (accountWithAddType.get(accountNumber).equals("Residential")) {
						// Getting role type
						role = testDataJson.getStringJsonValue("rolePropertyManager");
						// Delete existing guest user.
						deleteGuestUser(softAssert);
						int count = 0;
						for (int i = 0; i < iMaxCountInvitesResidential; i++) {
							email = "testguestuser" + i + "@mailinator.com";
							// Inviting guest user
							inviteNonScmUserAsGuest(softAssert, accountNumber, email, role);
							// Waiting for mail template to be come into the
							// database
							pause(5000);
							count = i + 1;
						}
						email = "testguestuser" + count + "@mailinator.com";
						// Inviting guest user
						pause(3000);
						clickOnInviteUser();
						// pageUtil.click(guestUserPage.getLnkInviteUser());
						driver.switchTo().activeElement();
						pause(500);
						sAssert.assertEquals(getLabelInviteUser(),
								guestuserTextProp.getPropValue("txtLblMsgPopUpInviteUser"));
						String futureDate = DateUtil.getFutureDate(6);
						// Populating invite user pop-up
						selectServiceAccountnumber(accountNumber);
						pause(2000);
						populateGuestUserName("Guest User");
						selectGuestUserRole(role);
						pause(500);
						populateGuestUserEmailId(email);
						populateAccessPeriod(futureDate);
						getselectServiceAccountnumber();
						checkchkBoxAgreeTeBtn();
						clickSubmitButton();
						String actToastMsg = getToastMessage();
						sAssert.assertEquals(actToastMsg, guestuserTextProp.getPropValue("txtMsgErrorMaxGuestInvite"),
								"Successful guest invite toast message is not matched.");
						pause(2000);
						String expToastMsg = guestuserTextProp.getPropValue("txtMsgErrorMaxGuestInvite");
						expToastMsg = expToastMsg.replace("maxNum", String.valueOf(iMaxCountInvitesResidential));
						softAssert.assertEquals(actToastMsg, expToastMsg,
								"You have already send the maximum number of invites configured as per csp");
						pause(2000);
					} else if (accountWithAddType.get(accountNumber).equals("Commercial")) {
						// Getting role type
						role = testDataJson.getStringJsonValue("rolePropertyManager");
						// Delete existing guest user.
						deleteGuestUser(softAssert);
						int count = 0;
						for (int i = 0; i < iMaxCountInvitesCommercial; i++) {
							email = "testguestuser" + i + "@mailinator.com";
							// Inviting guest user
							inviteNonScmUserAsGuest(softAssert, accountNumber, email, role);
							// Waiting for mail template to be come into the database
							pause(5000);
						}
						email = "testguestuser" + count + "@mailinator.com";
						// Inviting guest user
						pause(3000);
						clickOnInviteUser();
						// pageUtil.click(guestUserPage.getLnkInviteUser());
						driver.switchTo().activeElement();
						pause(500);
						sAssert.assertEquals(getLabelInviteUser(),
								guestuserTextProp.getPropValue("txtLblMsgPopUpInviteUser"));
						String futureDate = DateUtil.getFutureDate(6);
						// Populating invite user pop-up
						selectServiceAccountnumber(accountNumber);
						pause(2000);
						populateGuestUserName("Guest User");
						selectGuestUserRole(role);
						pause(500);
						populateGuestUserEmailId(email);
						populateAccessPeriod(futureDate);
						getselectServiceAccountnumber();
						checkchkBoxAgreeTeBtn();
						clickSubmitButton();
						String actToastMsg = getToastMessage();
						sAssert.assertEquals(actToastMsg, guestuserTextProp.getPropValue("txtMsgErrorMaxGuestInvite"),
								"Successful guest invite toast message is not matched.");
						pause(2000);
						String expToastMsg = guestuserTextProp.getPropValue("txtMsgErrorMaxGuestInvite");
						expToastMsg = expToastMsg.replace("maxNum", String.valueOf(iMaxCountInvitesCommercial));
						softAssert.assertEquals(actToastMsg, expToastMsg,
								"You have already send the maximum number of invites configured as per csp");
						pause(2000);
					} else {
						log.info("Address type of given account not matched.");
					}
				} else {
					log.info("Guest user link is not available on the My Account page");
				}
			} else {
				log.info("My Account link is not available on the header");
			}
		} else {
			log.info("Given user have no account with owner or property manager access");
		}

		log.info("C74763, C74803 - To verify account owner shall be allowed to invite "
				+ "<x> number of residential and <y> number of business users as configured from CSP.");
		log.info("Test Case execution for - verifyMaxNoGuestUserInvite - is Completed");

	}

	/**
	 * This method is used to get all the linked account with address type.
	 *
	 * @param userName
	 * @return
	 */
	public Map<String, String> getAllLinkedAccountWithAddressType(String userName) {
		Map<String, String> accWithRoleId = new HashMap<>();
		String sqlQuery = SQLQueries.getAllLinkedAccountsWithRoleId(userName);
		try {
			ResultSet rs = DataBaseUtils.getResultSet(sqlQuery);
			while (rs.next()) {
				accWithRoleId.put(rs.getString("UtilityAccountNumber"), rs.getString("AddressType"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accWithRoleId;
	}

	/**
	 * Test 8.1: This test method verifies below tests: 1). Verify that guest user
	 * can choose to create a new username password to access guest account.
	 * 
	 * @throws InterruptedException
	 */
	public void verifyFirstLoginUserGuestAccess(SoftAssert softAssert, String email, String name, String pass) {
		log.info("Verify that guest user can choose to create a new username" + " password to access guest account.");
		log.info("Test Case execution for - verifyFirstLoginForExistingUser - is Initiated");
		registrationConfig = CSPConfiguration.initMCspConfig();
		HomeSteps homeSteps = new HomeSteps(driver);
		LoginSteps loginsteps = new LoginSteps(driver);

		// Fetching login details to whom guest user id is send.
		loginsteps.loginIntoTheApplication(name, pass);
		// Check whether the MyAccount module is enabled or not.
		if (registrationConfig.get("MyAccount") == 1) {

			homeSteps.navigateToAccountInformation();
			LinkedHashMap<String, String> lMRoleAccount = new LinkedHashMap<>();
			List<WebElement> lRoles = TdMyAccountRole();
			List<WebElement> lAccountNumber = TdServiceAccountNomap();
			int count = 0;
			for (WebElement ele : lRoles) {
				String role = ele.getText().trim();
				String accountNo = lAccountNumber.get(count).getText().trim();
				lMRoleAccount.put(role, accountNo);
				count++;
			}
			String sActAccountNo = lMRoleAccount.get("Guest User Access").trim();
			softAssert.assertEquals(sActAccountNo, accountNumber,
					"Account number is not matched with the account whom guest access is given.");
		} else {
			log.info("My account link is not available on the header");
		}
	}

	/**
	 * This method is used to get the username password to whom email id is linked.
	 *
	 * @param emailId
	 * @param userName
	 * @return
	 */
	public Map<String, String> getUsernamePassToWhomEmailIdLinked(String emailId, String userName) {
		Map<String, String> loginCredOfEmailLinked = new HashMap<>();
		String sqlQuery = SQLQueries.getUserToWhomEmailIdIsLinked(emailId, userName);
		try {
			ResultSet rs = DataBaseUtils.getResultSet(sqlQuery);
			while (rs.next()) {
				loginCredOfEmailLinked.put(rs.getString("UserName"), rs.getString("Password"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginCredOfEmailLinked;
	}

	/**
	 * This method is used to get the username password to whom email id is linked.
	 *
	 * @param userName
	 * @return
	 */
	public String getPwdOfGivenUserDb(String userName) {
		String password = null;
		String sqlQuery = SQLQueries.getEncryptedPasswordOfUser(userName);
		try {
			ResultSet rs = DataBaseUtils.getResultSet(sqlQuery);
			while (rs.next()) {
				password = rs.getString("Password");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return password;
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

	/**
	 * Test 10.1: This test method verifies below tests: 1). To verify If no guest
	 * users are added, system shall display an inline message “No Guest user has
	 * been added. Click on Invite Guest button to add a guest user.”
	 *
	 * @param softAssert
	 */
	public void verifyNoGuestUserAdded(SoftAssert softAssert) {
		log.info("To verify If no guest users are added, system shall "
				+ "display an inline message 'No Guest user has been added. Click on Invite Guest button to"
				+ "add a guest user.'");
		log.info("Test Case execution for - verifyNoGuestUserAdded - is Instantiated.");
		if (getListBtnToggleThreeDots().size() != 0) {
			log.info("To verify If no guest users are added, system shall "
					+ "display an inline message 'No Guest user has been added. Click on Invite Guest button to"
					+ " add a guest user.'");
		} else {
			log.info("To verify If no guest users are added, system shall "
					+ "display an inline message 'No Guest user has been added. Click on Invite Guest button to"
					+ " add a guest user.'");
		}
		log.info("Test Case execution for - verifyNoGuestUserAdded - is Completed.");
	}

	/**
	 * This method is used to set the password to the given user.
	 */
	public void setPasswordToTheGivenUser(String userName, String password) {
		String sqlQuery = SQLQueries.getQueryToUpdatePasswordForUser(userName, password);
		try {
			DataBaseUtils.executeUpdateDeleteSQLQuery(sqlQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loginIntoTheApplication(String userName, String password) throws InterruptedException {
		ExtentLogger.logInfo("Logging into the application.");
		LoginSteps loginsteps = new LoginSteps(driver);
		ResultSet resultSet;
		String otp = "";

		loginsteps.populateLoginForm(userName, password);
		loginsteps.clickSignInBtn();
		submitOTP();
		// waitForPageLoader();
		pause(10000);

	}

	public void submitOTP() throws InterruptedException {
		MultiFactorAuthPage multiAuth = new MultiFactorAuthPage(driver);
		multiAuth.clickbtnNext();
		ResultSet resultSet;
		String otp = "";
		pause(10000);
		try {

			resultSet = DataBaseUtils.getResultSet(SQLQueries.getLoginMFAOtp());
			while (resultSet.next()) {
				otp = resultSet.getString("token");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<WebElement> otpTxtBoxs = multiAuth.getInputOTPFields();
		int i = 0;
		for (WebElement el : otpTxtBoxs) {
			String val = Character.toString(otp.charAt(i));
			sendKeys(el, val);
			i++;
		}
		multiAuth.clickbtnSubmitOTP();
		pause(5000);
	}

	/**
	 * This method is used to update the guest access expiry date of the given
	 * account.
	 *
	 * @param accountNumber
	 * @param date
	 */
	public void updateGuestAccExpDate(String accountNumber, String date) {
		String query = SQLQueries.getQueryToUpdateAccessExpDate(accountNumber, date);
		try {
			DataBaseUtils.executeUpdateDeleteSQLQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test 6.1: This test method verifies below tests: 1). Verify validations while
	 * minimum and maximum lengths are different for fields in the guest
	 * registration page. 2). Verify validations while minimum and maximum lengths
	 * are the same for fields on the guest registration page.
	 */
	public void verifyGuestUserRegistrationFieldsValidation(SoftAssert softAssert) {
		log.info("Verify that if the user registers with an already registered username, "
				+ "the system shall display 'username is already in use'.");
		log.info("Test Case execution for - verifyGuestUserRegistrationFieldsValidation - " + "is Initiated");
		getRegistrationConfig = CSPConfiguration.initMRegistrationData();
		RegistrationPage registrationpage = new RegistrationPage(driver);
		RegistrationSteps registrationSteps = new RegistrationSteps(driver);
		// Fetching the min and max field.
		int minLengthOfField = 0;
		int maxLengthOfField = 0;
		// Getting the residential registration data.
		guestCustomer = testDataJson.getMapJsonObject("newGuestRegistrationData");
		// Getting fields label and their fields elements
		guestRegLabelsFieldsMap = getFieldsLabelElementsOnRegistrationPage();
		// Populating form present over the multiple pages.
		username = populateNewGuestRegForm(guestRegLabelsFieldsMap, guestCustomer);
		// Initializing minimum and maximum of all registration fields
		getMinMaxOfRegistrationFieldsFromDB();
		String expErrMsg = null;
		// Iterating for populating multiple fields on a page.
		for (Map.Entry<String, WebElement> entry : guestRegLabelsFieldsMap.entrySet()) {
			switch (entry.getKey()) {
			case "First Name":
				if (getRegistrationConfig.get("First Name").getFieldDisplayStatus().equals("1")
						&& getRegistrationConfig.get("First Name").getFieldMandatoryProperty().equals("true")) {
					System.out.println(entry.getKey());
					verifyCase = entry.getKey().replace(":", "");
					minLengthOfField = Integer.parseInt(minValueRegFields.get(verifyCase));
					maxLengthOfField = Integer.parseInt(maxValueRegFields.get(verifyCase));
					registrationSteps.verifyBlankErrorMessage(softAssert, entry.getValue(),
							guestuserTextProp.getPropValue("txtErrMsgFirstNameBlank"));
					if (minLengthOfField != maxLengthOfField) {
						expErrMsg = guestuserTextProp.getPropValue("txtErrMsgMinMaxNotEqualsFirstName");
						expErrMsg = expErrMsg.replace("minSize", String.valueOf(minLengthOfField));
						expErrMsg = expErrMsg.replace("maxSize", String.valueOf(maxLengthOfField));
						registrationSteps.verifyInvalidErrorMessage(entry.getValue(), expErrMsg, softAssert);
					} else {
						expErrMsg = guestuserTextProp.getPropValue("txtErrMsgMinMaxEqualsFirstName");
						expErrMsg = expErrMsg.replace("size", String.valueOf(minLengthOfField));
						registrationSteps.verifyInvalidErrorMessage(entry.getValue(), expErrMsg, softAssert);
					}
					lastCase = verifyCase;
				}
				break;
			case "Last Name":
				if (getRegistrationConfig.get("Last Name").getFieldDisplayStatus().equals("1")
						&& getRegistrationConfig.get("Last Name").getFieldMandatoryProperty().equals("true")) {
					verifyCase = entry.getKey().replace(":", "");
					minLengthOfField = Integer.parseInt(minValueRegFields.get(verifyCase));
					maxLengthOfField = Integer.parseInt(maxValueRegFields.get(verifyCase));
					registrationSteps.verifyBlankErrorMessage(softAssert, entry.getValue(),
							guestuserTextProp.getPropValue("txtErrMsgLastNameBlank"));
					if (minLengthOfField != maxLengthOfField) {
						expErrMsg = guestuserTextProp.getPropValue("txtErrMsgMinMaxNotEqualLastName");
						expErrMsg = expErrMsg.replace("minSize", String.valueOf(minLengthOfField));
						expErrMsg = expErrMsg.replace("maxSize", String.valueOf(maxLengthOfField));
						registrationSteps.verifyInvalidErrorMessage(entry.getValue(), expErrMsg, softAssert);
					} else {
						expErrMsg = guestuserTextProp.getPropValue("txtErrMsgMinMaxEqualLastName");
						expErrMsg = expErrMsg.replace("size", String.valueOf(minLengthOfField));
						registrationSteps.verifyInvalidErrorMessage(entry.getValue(), expErrMsg, softAssert);
					}
					lastCase = verifyCase;
				}
				break;
			case "Username":
				if (getRegistrationConfig.get("Username").getFieldDisplayStatus().equals("1")
						&& getRegistrationConfig.get("Username").getFieldMandatoryProperty().equals("true")) {
					verifyCase = entry.getKey().replace(":", "");
					minLengthOfField = Integer.parseInt(minValueRegFields.get(verifyCase));
					maxLengthOfField = Integer.parseInt(maxValueRegFields.get(verifyCase));
					registrationSteps.verifyBlankErrorMessage(softAssert, entry.getValue(),
							guestuserTextProp.getPropValue("txtErrorMsgBlankUserName"));
					if (minLengthOfField != maxLengthOfField) {
						expErrMsg = guestuserTextProp.getPropValue("txtErrMsgMinMaxNotEqualUsername");
						/*
						 * sExpErrMsg = sExpErrMsg.replace("minSize", String.valueOf(minLengthOfField));
						 * sExpErrMsg = sExpErrMsg.replace("maxSize", String.valueOf(maxLengthOfField));
						 */
						// registrationSteps.verifyErrMsgForUsernameField(entry.getValue(), expErrMsg,
						// softAssert);
					} else {
						expErrMsg = guestuserTextProp.getPropValue("txtErrMsgMinMaxEqualUsername");
						expErrMsg.replace("size", String.valueOf(minLengthOfField));
						// registrationSteps.verifyErrMsgForUsernameField(entry.getValue(), expErrMsg,
						// softAssert);
					}
					lastCase = verifyCase;
				}
				break;
			case "Password":
				if (getRegistrationConfig.get("Password").getFieldDisplayStatus().equals("1")
						&& getRegistrationConfig.get("Password").getFieldMandatoryProperty().equals("true")) {
					verifyCase = entry.getKey().replace(":", "");
					registrationSteps.verifyBlankErrorMessage(softAssert, entry.getValue(),
							guestuserTextProp.getPropValue("txtErrorMsgBlankPassword"));
					expErrMsg = guestuserTextProp.getPropValue("txtErrorMsgInvalidPassword");
					// registrationSteps.verifyErrMsgForPasswordField(entry.getValue(), expErrMsg,
					// softAssert);
					lastCase = verifyCase;
				}
				break;
			case "Confirm Password":
				if (getRegistrationConfig.get("Confirm Password").getFieldDisplayStatus().equals("1")
						&& getRegistrationConfig.get("Confirm Password").getFieldMandatoryProperty().equals("true")) {
					verifyCase = entry.getKey().replace(":", "");
					registrationSteps.verifyBlankErrorMessage(softAssert, entry.getValue(),
							guestuserTextProp.getPropValue("txtErrorMsgConfirmPassword"));
					lastCase = verifyCase;
				}
				break;
			case "Security Question 1:":
				if (getRegistrationConfig.get("Security Question 1").getFieldDisplayStatus().equals("1")
						&& getRegistrationConfig.get("Security Question 1").getFieldMandatoryProperty()
								.equals("true")) {
					verifyCase = entry.getKey().replace(":", "");
					registrationSteps.verifyBlankErrorMessage(softAssert, entry.getValue(),
							guestuserTextProp.getPropValue("txtErrorMsgSecurityQuestion"));
					lastCase = verifyCase;
					registrationpage.populateTxtBoxSecurityAnswer1("Serco Wick");
				}
				break;
			case "Security Answer 1":
				if (getRegistrationConfig.get("Security Question 1").getFieldDisplayStatus().equals("1")
						&& getRegistrationConfig.get("Security Question 1").getFieldMandatoryProperty()
								.equals("true")) {
					verifyCase = entry.getKey().replace(":", "");
					registrationSteps.verifyBlankErrorMessage(softAssert, entry.getValue(),
							guestuserTextProp.getPropValue("txtErrorMsgSecurityAnswer"));
					lastCase = verifyCase;
				}
				break;
			case "Security Question 2:":
				if (getRegistrationConfig.get("Security Question 2").getFieldDisplayStatus().equals("1")
						&& getRegistrationConfig.get("Security Question 2").getFieldMandatoryProperty()
								.equals("true")) {
					verifyCase = entry.getKey().replace(":", "");
					registrationSteps.verifyBlankErrorMessage(softAssert, entry.getValue(),
							guestuserTextProp.getPropValue("txtErrorMsgSecurityQuestion2"));
					lastCase = verifyCase;
					registrationpage.populateTxtBoxSecurityAnswer2("Serco Wick");

				}
				break;
			case "Security Answer 2":
				if (getRegistrationConfig.get("Security Question 2").getFieldDisplayStatus().equals("1")
						&& getRegistrationConfig.get("Security Question 2").getFieldMandatoryProperty()
								.equals("true")) {
					verifyCase = entry.getKey().replace(":", "");
					registrationSteps.verifyBlankErrorMessage(softAssert, entry.getValue(),
							guestuserTextProp.getPropValue("txtErrorMsgSecurityAnswer"));
					lastCase = verifyCase;
				}
				break;
			case "Primary Phone":
				if (getRegistrationConfig.get("Primary Contact Number").getFieldDisplayStatus().equals("1")
						&& getRegistrationConfig.get("Primary Contact Number").getFieldMandatoryProperty()
								.equals("true")) {
					verifyCase = entry.getKey().replace(":", "");
					minLengthOfField = Integer.parseInt(minValueRegFields.get("Primary Contact Number"));
					maxLengthOfField = Integer.parseInt(maxValueRegFields.get("Primary Contact Number"));
					registrationSteps.verifyBlankErrorMessage(softAssert, entry.getValue(),
							guestuserTextProp.getPropValue("txtErrMsgBlankContactNumber"));
					if (minLengthOfField != maxLengthOfField) {
						expErrMsg = guestuserTextProp.getPropValue("txtErrMsgInvalidPrContactNo");
						registrationSteps.verifyInvalidErrorMessage(entry.getValue(), expErrMsg, softAssert);
					} else {
						expErrMsg = guestuserTextProp.getPropValue("txtErrMsgInvalidPrContactNo");
						registrationSteps.verifyInvalidErrorMessage(entry.getValue(), expErrMsg, softAssert);
					}
					lastCase = verifyCase;
				}
				break;
			case "Contact Type:":
				if (getRegistrationConfig.get("Contact Type").getFieldDisplayStatus().equals("1")
						&& getRegistrationConfig.get("Contact Type").getFieldMandatoryProperty().equals("true")) {
					verifyCase = entry.getKey().replace(":", "");
					registrationSteps.verifyBlankErrorMessage(softAssert, entry.getValue(),
							guestuserTextProp.getPropValue("txtErrMsgBlankContactType"));
					lastCase = verifyCase;
				}
				break;
			default:
				log.info("All field on the page are not populated");
			}
		}
		log.info("Verify that if the user registers with an already registered username, "
				+ "the system shall display 'username is already in use'.");
		log.info("Test Case execution for - verifyGuestUserRegistrationFieldsValidation - is Completed");
	}

	/**
	 * This method is used to get the min-max length of all the registration fields.
	 */
	public void getMinMaxOfRegistrationFieldsFromDB() {
		String query = SQLQueries.getRegistrationTemplateConfig();
		try {
			ResultSet rs = DataBaseUtils.getResultSet(query);
			while (rs.next()) {
				minValueRegFields.put(rs.getString("ParentHead"), rs.getString("Min Length"));
				maxValueRegFields.put(rs.getString("ParentHead"), rs.getString("Max Length"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

package sew.ai.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import sew.ai.api.endpoints.auth.LoginEndpoint;
import sew.ai.api.utils.AuthUtils;
import sew.ai.config.CSPConfiguration;
import sew.ai.config.Configuration;
import sew.ai.config.SCPConfiguration;
import sew.ai.driver.Page;
import sew.ai.enums.RegistrationPreference;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.props.ResourcePaths;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.models.Customer;
import sew.ai.models.RegistrationConfig;
import sew.ai.models.RegistrationMailsConfig;
import sew.ai.models.User;
import sew.ai.pageObjects.scp.HomePage;
import sew.ai.pageObjects.scp.LoginPage;
import sew.ai.pageObjects.scp.RegistrationPage;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.PropertiesUtil;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class RegistrationSteps extends RegistrationPage {
	private static final Logger log = LogManager.getLogger(RegistrationSteps.class);
	public static PropertiesUtil registrationTextProp;
	String isPaperlessOpted = null;
	Map<String, RegistrationMailsConfig> registrationMails = new HashMap<>();
	Map<String, RegistrationConfig> registrationConfig = new HashMap<>();
	 HashMap<String,Map>  accountDetails = new HashMap<>();
	Map<String, String> fieldsMinLength = new HashMap<>();
	Map<String, String> fieldsMaxLength = new HashMap<>();
	ArrayList OtpList = new ArrayList<>();
	String verifyCase = null, lastCase = null;
	String subject = null, actValidation = null;
	String subActivation, subNotification, subPaperLess, subBothPaperPref;
	public static User user;
	public static String loginToken;
	public static String jwtToken;
	Boolean isFieldVisible, isFieldMandatory, isFieldValidateCis;

	public RegistrationSteps(WebDriver driver) {
		super(driver);
		registrationTextProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.REGISTRATION_TXT_FILENAME);
	}

	public Boolean isRegistrationPage(String url, String title) {
		Boolean isRegistrationPage = false;
		log.info("Checking that the current page is Registration page.");
		if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
			isRegistrationPage = true;
		log.info("The current page is registration page {}: " + isRegistrationPage);
		return isRegistrationPage;
	}

	/**
	 * This test case verifies navigating the registration page and clicking the
	 * register button without any entry.
	 */

	public void verifyRegistrationPageObjectsSteps(SoftAssert sAssert, Customer customer) {
		// LoginSteps loginSteps = new LoginSteps(driver);
		log.info("verify registration page objects");
		LoginSteps loginSteps = new LoginSteps(driver);
		// loginSteps.navigationToRegistrationPage();
		// Verify current title of the page.
		Assert.assertTrue(isRegistrationPage(registrationTextProp.getPropValue("RegistrationPageUrl"),
				registrationTextProp.getPropValue("RegistrationPageTitle")));

		registrationByOptingGivenPref(sAssert, customer, RegistrationPreference.NOPREF.name());
		// Activate user by fetching the mail from contract account notify table and
		// validate.
		loginSteps.loginWithInvalidCreds(customer.getUserName(), customer.getPassword());
		verifyAccountActivation(sAssert, customer);
		// Verifying email and there status while opting both preferences.
		verifyEmailsWhileOptingPref(sAssert, customer.getEmailAddress());
		// Verifying registered user data against data base.
		verifyRegUserDataInDB(sAssert, customer);
		// Verify first login after activation.
		loginSteps.loginIntoTheApplication(customer.getUserName(), customer.getPassword());

	}

	public void verifyRegByOptingBothPreferences(SoftAssert sAssert, Customer customer) {
		LoginSteps loginSteps = new LoginSteps(driver);
		log.info("verify registration page objects");
		Assert.assertTrue(isRegistrationPage(registrationTextProp.getPropValue("RegistrationPageUrl"),
				registrationTextProp.getPropValue("RegistrationPageTitle")));
		registrationByOptingGivenPref(sAssert, customer, RegistrationPreference.BOTH.name());
		// Activate user by fetching the mail from contract account notify table and
		// validate.
		loginSteps.loginWithInvalidCreds(customer.getUserName(), customer.getPassword());
		verifyAccountActivation(sAssert, customer);
		// Verifying email and there status while opting both preferences.
		verifyEmailsWhileOptingPref(sAssert, customer.getEmailAddress());
		// Verifying registered user data against data base.
		verifyRegUserDataInDB(sAssert, customer);
		//user.setUserName(customer.getUserName());
		//user.setPassword(customer.getPassword());
		loginSteps.loginIntoTheApplication(customer.getUserName(), customer.getPassword());
		sAssert.assertAll();

	}

	public void verifyRegByOptingPaperlessPreferences(SoftAssert sAssert, Customer customer) {
		LoginSteps loginSteps = new LoginSteps(driver);
		log.info("verify registration page objects");
		Assert.assertTrue(isRegistrationPage(registrationTextProp.getPropValue("RegistrationPageUrl"),
				registrationTextProp.getPropValue("RegistrationPageTitle")));
		registrationByOptingGivenPref(sAssert, customer, RegistrationPreference.PAPERLESS.name());
		// Activate user by fetching the mail from contract account notify table and
		// validate.
		loginSteps.loginWithInvalidCreds(customer.getUserName(), customer.getPassword());
		verifyAccountActivation(sAssert, customer);
		// Verifying email and there status while opting both preferences.
		verifyEmailsWhileOptingPref(sAssert, customer.getEmailAddress());
		// Verifying registered user data against data base.
		verifyRegUserDataInDB(sAssert, customer);
		// Verify first login after activation.
		loginSteps.loginIntoTheApplication(customer.getUserName(), customer.getPassword());
		sAssert.assertAll();

	}

	public void verifyRegByOptingPaperBillPreferences(SoftAssert sAssert, Customer customer) {
		LoginSteps loginSteps = new LoginSteps(driver);
		log.info("verify registration page objects");
		Assert.assertTrue(isRegistrationPage(registrationTextProp.getPropValue("RegistrationPageUrl"),
				registrationTextProp.getPropValue("RegistrationPageTitle")));
		registrationByOptingGivenPref(sAssert, customer, RegistrationPreference.PAPER.name());
		// Activate user by fetching the mail from contract account notify table and
		// validate.
		loginSteps.loginWithInvalidCreds(customer.getUserName(), customer.getPassword());
		verifyAccountActivation(sAssert, customer);
		// Verifying email and there status while opting both preferences.
		verifyEmailsWhileOptingPref(sAssert, customer.getEmailAddress());
		// Verifying registered user data against data base.
		verifyRegUserDataInDB(sAssert, customer);
		// Verify first login after activation.
		loginSteps.loginIntoTheApplication(customer.getUserName(), customer.getPassword());
		sAssert.assertAll();

	}

	public void fieldValidationsResidentialTemp(SoftAssert sAssert, Customer customer) {
		int minLengthOfField;
		int maxLengthOfField;
		// Navigate to residential registration button.
		//navResidentialRegTemp();
		// Getting registration page count.
		int pageCount = getDivRegistrationPageCount().size();
		// Iterating for populating fields over the different pages.
		for (int i = 0; i < pageCount; i++) {
			// Getting fields label and their fields elements
			Map<String, WebElement> labelAndFieldsMap = getFldLblAndInputsOnRegPage();
			// Populating form present over the multiple pages.
			populateRegistrationForm(labelAndFieldsMap, customer);
			// Initializing minimum and maximum of all registration fields
			getMinMaxLenOfRegFldFromDB();
			String expErrMsg;
			boolean isFieldVisible, isFieldMandatory;
			// Iterating for populating multiple fields on a page.
			for (Map.Entry<String, WebElement> entry : labelAndFieldsMap.entrySet()) {
				switch (entry.getKey()) {
				case "Account Number":
					verifyCase = entry.getKey();
					isFieldVisible = registrationConfig.get(verifyCase).getFieldDisplayStatus().equals("1");
					isFieldMandatory = registrationConfig.get(verifyCase).getFieldMandatoryProperty().equals("true");
					minLengthOfField = Integer.parseInt(fieldsMinLength.get(verifyCase));
					maxLengthOfField = Integer.parseInt(fieldsMaxLength.get(verifyCase));
					if (isFieldVisible && isFieldMandatory) {
						verifyBlankErrorMessage(sAssert, entry.getValue(),
								registrationTextProp.getPropValue("txtErrMsgBlankServiceAccount"));

					} else if (isFieldVisible) {
						if (minLengthOfField != maxLengthOfField) {
							expErrMsg = registrationTextProp.getPropValue("txtErrMsgMinMaxNotEqualAccount");
							expErrMsg = expErrMsg.replace("minSize", String.valueOf(minLengthOfField));
							expErrMsg = expErrMsg.replace("maxSize", String.valueOf(maxLengthOfField));
							verifyInvalidErrorMessage(entry.getValue(), expErrMsg, sAssert);
						} else {
							expErrMsg = registrationTextProp.getPropValue("txtErrMsgMinMaxEqualAccount");
							expErrMsg = expErrMsg.replace("size", String.valueOf(minLengthOfField));
							verifyInvalidErrorMessage(entry.getValue(), expErrMsg, sAssert);
						}
					}
					lastCase = verifyCase;
					populateUtilityAccountNumber(customer.getUtilityAccountNumber());
					break;
				case "First Name":
					verifyCase = entry.getKey();
					isFieldVisible = registrationConfig.get(verifyCase).getFieldDisplayStatus().equals("1");
					isFieldMandatory = registrationConfig.get(verifyCase).getFieldMandatoryProperty().equals("true");
					minLengthOfField = Integer.parseInt(fieldsMinLength.get(verifyCase));
					maxLengthOfField = Integer.parseInt(fieldsMaxLength.get(verifyCase));
					if (isFieldVisible && isFieldMandatory) {
						verifyBlankErrorMessage(sAssert, entry.getValue(),
								registrationTextProp.getPropValue("txtErrMsgFirstNameBlank"));
					} else if (isFieldVisible) {
						if (minLengthOfField != maxLengthOfField) {
							expErrMsg = registrationTextProp.getPropValue("txtErrMsgMinMaxNotEqualsFirstName");
							expErrMsg = expErrMsg.replace("minSize", String.valueOf(minLengthOfField));
							expErrMsg = expErrMsg.replace("maxSize", String.valueOf(maxLengthOfField));
							verifyInvalidErrorMessage(entry.getValue(), expErrMsg, sAssert);
						} else {
							expErrMsg = registrationTextProp.getPropValue("txtErrMsgMinMaxEqualsFirstName");
							expErrMsg = expErrMsg.replace("size", String.valueOf(minLengthOfField));
							verifyInvalidErrorMessage(entry.getValue(), expErrMsg, sAssert);
						}
					}
					lastCase = verifyCase;
					populateFirstName(customer.getCustomerName());
					break;
				case "Last Name":
					verifyCase = entry.getKey();
					isFieldVisible = registrationConfig.get(verifyCase).getFieldDisplayStatus().equals("1");
					isFieldMandatory = registrationConfig.get(verifyCase).getFieldMandatoryProperty().equals("true");
					minLengthOfField = Integer.parseInt(fieldsMinLength.get(verifyCase));
					maxLengthOfField = Integer.parseInt(fieldsMaxLength.get(verifyCase));
					if (isFieldVisible && isFieldMandatory) {
						verifyBlankErrorMessage(sAssert, entry.getValue(),
								registrationTextProp.getPropValue("txtErrMsgLastNameBlank"));
					} else if (isFieldVisible) {
						if (minLengthOfField != maxLengthOfField) {
							expErrMsg = registrationTextProp.getPropValue("txtErrMsgMinMaxNotEqualLastName");
							expErrMsg = expErrMsg.replace("minSize", String.valueOf(minLengthOfField));
							expErrMsg = expErrMsg.replace("maxSize", String.valueOf(maxLengthOfField));
							verifyInvalidErrorMessage(entry.getValue(), expErrMsg, sAssert);
						} else {
							expErrMsg = registrationTextProp.getPropValue("txtErrMsgMinMaxEqualLastName");
							expErrMsg = expErrMsg.replace("size", String.valueOf(minLengthOfField));
							verifyInvalidErrorMessage(entry.getValue(), expErrMsg, sAssert);
						}
					}
					lastCase = verifyCase;
					populateLastName(customer.getLastName());
					break;
				case "Email Address":
					verifyCase = entry.getKey();
					isFieldVisible = registrationConfig.get(verifyCase).getFieldDisplayStatus().equals("1");
					isFieldMandatory = registrationConfig.get(verifyCase).getFieldMandatoryProperty().equals("true");
					minLengthOfField = Integer.parseInt(fieldsMinLength.get(verifyCase));
					maxLengthOfField = Integer.parseInt(fieldsMaxLength.get(verifyCase));
					if (isFieldVisible && isFieldMandatory) {
						verifyBlankErrorMessage(sAssert, entry.getValue(),
								registrationTextProp.getPropValue("txtErrorMsgBlankEmail"));
					} else if (isFieldVisible) {
						if (minLengthOfField != maxLengthOfField) {
							expErrMsg = registrationTextProp.getPropValue("txtErrorMsgInvalidEmailCrp");
							verifyInvalidErrorMessage(entry.getValue(), expErrMsg, sAssert);
						} else {
							expErrMsg = registrationTextProp.getPropValue("txtErrorMsgInvalidEmailCrp");
							verifyInvalidErrorMessage(entry.getValue(), expErrMsg, sAssert);
						}
					}
					lastCase = verifyCase;
					populateTxtBoxEmail(customer.getEmailAddress());
					break;
				case "ZIP code":
					verifyCase = entry.getKey();
					isFieldVisible = registrationConfig.get("Zip Code").getFieldDisplayStatus().equals("1");
					isFieldMandatory = registrationConfig.get("Zip Code").getFieldMandatoryProperty().equals("true");
					minLengthOfField = Integer.parseInt(fieldsMinLength.get("Zip Code"));
					maxLengthOfField = Integer.parseInt(fieldsMaxLength.get("Zip Code"));
					if (isFieldVisible && isFieldMandatory) {
						verifyBlankErrorMessage(sAssert, entry.getValue(),
								registrationTextProp.getPropValue("txtErrorMsgBlankZipCode"));
					} else if (isFieldVisible) {
						if (minLengthOfField != maxLengthOfField) {
							expErrMsg = registrationTextProp.getPropValue("txtErrMsgMinMaxNotEqualZipCode");
							expErrMsg = expErrMsg.replace("minSize", String.valueOf(minLengthOfField));
							expErrMsg = expErrMsg.replace("maxSize", String.valueOf(maxLengthOfField));
							verifyInvalidErrorMessage(entry.getValue(), expErrMsg, sAssert);
						} else {
							expErrMsg = registrationTextProp.getPropValue("txtErrMsgMinMaxEqualZipCode");
							expErrMsg = expErrMsg.replace("size", String.valueOf(minLengthOfField));
							verifyInvalidErrorMessage(entry.getValue(), expErrMsg, sAssert);
						}
					}
					lastCase = verifyCase;
					populatePostalCode(customer.getZipCode());
					break;
				case "Username":
					verifyCase = entry.getKey();
					isFieldVisible = registrationConfig.get(verifyCase).getFieldDisplayStatus().equals("1");
					isFieldMandatory = registrationConfig.get(verifyCase).getFieldMandatoryProperty().equals("true");
					minLengthOfField = Integer.parseInt(fieldsMinLength.get(verifyCase));
					maxLengthOfField = Integer.parseInt(fieldsMaxLength.get(verifyCase));
					if (isFieldVisible && isFieldMandatory) {
						verifyBlankErrorMessage(sAssert, entry.getValue(),
								registrationTextProp.getPropValue("txtErrorMsgBlankUserName"));
					} else if (isFieldVisible) {
						if (minLengthOfField != maxLengthOfField) {
							expErrMsg = registrationTextProp.getPropValue("txtErrMsgMinMaxNotEqualUsername");
							expErrMsg = expErrMsg.replace("minSize", String.valueOf(minLengthOfField));
							expErrMsg = expErrMsg.replace("maxSize", String.valueOf(maxLengthOfField));
							// verifyErrMsgForUsernameField(entry.getValue(), expErrMsg, sAssert);
						} else {
							expErrMsg = registrationTextProp.getPropValue("txtErrMsgMinMaxEqualUsername");
							expErrMsg.replace("size", String.valueOf(minLengthOfField));
							// verifyErrMsgForUsernameField(entry.getValue(), expErrMsg, sAssert);
						}
					}
					lastCase = verifyCase;
					populateUserName(customer.getUserName());
					break;
				case "Password":
					verifyCase = entry.getKey();
					isFieldVisible = registrationConfig.get(verifyCase).getFieldDisplayStatus().equals("1");
					isFieldMandatory = registrationConfig.get(verifyCase).getFieldMandatoryProperty().equals("true");
					if (isFieldVisible && isFieldMandatory) {
						verifyBlankErrorMessage(sAssert, entry.getValue(),
								registrationTextProp.getPropValue("txtErrorMsgBlankPassword"));
					} else if (isFieldVisible) {
						expErrMsg = registrationTextProp.getPropValue("txtErrorMsgInvalidPassword");
						// verifyErrMsgForPasswordField(entry.getValue(), expErrMsg, sAssert);
					}
					lastCase = verifyCase;
					populatePassword(customer.getPassword());
					break;
				case "Confirm Password":
					verifyCase = entry.getKey();
					isFieldVisible = registrationConfig.get(verifyCase).getFieldDisplayStatus().equals("1");
					isFieldMandatory = registrationConfig.get(verifyCase).getFieldMandatoryProperty().equals("true");
					if (isFieldVisible && isFieldMandatory) {
						verifyBlankErrorMessage(sAssert, entry.getValue(),
								registrationTextProp.getPropValue("txtErrorMsgConfirmPasswordCrp"));
					}
					lastCase = verifyCase;
					populateConfirmPassword(customer.getPassword());
					break;
				case "Meter Number":
					verifyCase = entry.getKey();
					isFieldVisible = registrationConfig.get(verifyCase).getFieldDisplayStatus().equals("1");
					isFieldMandatory = registrationConfig.get(verifyCase).getFieldMandatoryProperty().equals("true");
					minLengthOfField = Integer.parseInt(fieldsMinLength.get(verifyCase));
					maxLengthOfField = Integer.parseInt(fieldsMaxLength.get(verifyCase));
					if (isFieldVisible && isFieldMandatory) {
						verifyBlankErrorMessage(sAssert, entry.getValue(),
								registrationTextProp.getPropValue("txtErrMsgBlankMeterNumber"));
					} else if (isFieldVisible) {
						if (minLengthOfField != maxLengthOfField) {
							expErrMsg = registrationTextProp.getPropValue("txtErrMsgMinMaxNotEqualMeterNo");
							expErrMsg = expErrMsg.replace("minSize", String.valueOf(minLengthOfField));
							expErrMsg = expErrMsg.replace("maxSize", String.valueOf(maxLengthOfField));
							verifyInvalidErrorMessage(entry.getValue(), expErrMsg, sAssert);
						} else {
							expErrMsg = registrationTextProp.getPropValue("txtErrMsgMinMaxEqualMeterNo");
							expErrMsg = expErrMsg.replace("size", String.valueOf(minLengthOfField));
							verifyInvalidErrorMessage(entry.getValue(), expErrMsg, sAssert);
						}
					}
					lastCase = verifyCase;
					populateTxtBoxMeterNumber(customer.getMeterNumber());
					break;
				case "Primary Phone Number":
					verifyCase = "Primary Contact Number";
					isFieldVisible = registrationConfig.get(verifyCase).getFieldDisplayStatus().equals("1");
					isFieldMandatory = registrationConfig.get(verifyCase).getFieldMandatoryProperty().equals("true");
					minLengthOfField = Integer.parseInt(fieldsMinLength.get(verifyCase));
					maxLengthOfField = Integer.parseInt(fieldsMaxLength.get(verifyCase));
					if (isFieldVisible && isFieldMandatory) {
						verifyBlankErrorMessage(sAssert, entry.getValue(),
								registrationTextProp.getPropValue("txtErrMsgBlankContactNumber"));
					} else if (isFieldVisible) {
						if (minLengthOfField != maxLengthOfField) {
							expErrMsg = registrationTextProp.getPropValue("txtErrMsgInvalidPrContactNo");
							verifyInvalidErrorMessage(entry.getValue(), expErrMsg, sAssert);
						} else {
							expErrMsg = registrationTextProp.getPropValue("txtErrMsgInvalidPrContactNo");
							verifyInvalidErrorMessage(entry.getValue(), expErrMsg, sAssert);
						}
					}
					lastCase = verifyCase;
					populatetxtBoxPrimaryContactNumber(customer.getPhoneNumber());
					break;
				case "Contact Type":
					verifyCase = entry.getKey();
					isFieldVisible = registrationConfig.get(verifyCase).getFieldDisplayStatus().equals("1");
					isFieldMandatory = registrationConfig.get(verifyCase).getFieldMandatoryProperty().equals("true");
					if (isFieldVisible && isFieldMandatory) {
						verifyBlankErrorMessage(sAssert, entry.getValue(),
								registrationTextProp.getPropValue("txtErrMsgBlankContactType"));
					}
					lastCase = verifyCase;
					selectDdlContactType("Mobile");
					break;
				case "Driving License Number":
					verifyCase = entry.getKey();
					isFieldVisible = registrationConfig.get(verifyCase).getFieldDisplayStatus().equals("1");
					isFieldMandatory = registrationConfig.get(verifyCase).getFieldMandatoryProperty().equals("true");
					minLengthOfField = Integer.parseInt(fieldsMinLength.get(verifyCase));
					maxLengthOfField = Integer.parseInt(fieldsMaxLength.get(verifyCase));
					if (isFieldVisible && isFieldMandatory) {
						verifyBlankErrorMessage(sAssert, entry.getValue(),
								registrationTextProp.getPropValue("txtErrorMsgBlankDrvngLicCrp"));
					} else if (isFieldVisible) {
						if (minLengthOfField != maxLengthOfField) {
							expErrMsg = registrationTextProp.getPropValue("txtErrMsgInvalidDrivingLicenseCrp");
							expErrMsg = expErrMsg.replace("minSize", String.valueOf(minLengthOfField));
							expErrMsg = expErrMsg.replace("maxSize", String.valueOf(maxLengthOfField));
							verifyInvalidErrorMessage(entry.getValue(), expErrMsg, sAssert);
						} else {
							expErrMsg = registrationTextProp.getPropValue("txtErrorMsgBlankDrvngLicCrp");
							verifyInvalidErrorMessage(entry.getValue(), expErrMsg, sAssert);
						}
					}
					lastCase = verifyCase;
					populateDrivingLicence(customer.getDrivingLicenseNo());
					break;
				case "Street Address":
					verifyCase = entry.getKey();
					isFieldVisible = registrationConfig.get(verifyCase).getFieldDisplayStatus().equals("1");
					isFieldMandatory = registrationConfig.get(verifyCase).getFieldMandatoryProperty().equals("true");
					minLengthOfField = Integer.parseInt(fieldsMinLength.get(verifyCase));
					maxLengthOfField = Integer.parseInt(fieldsMaxLength.get(verifyCase));
					if (isFieldVisible && isFieldMandatory) {
						verifyBlankErrorMessage(sAssert, entry.getValue(),
								registrationTextProp.getPropValue("txtErrorMsgBlankStreetAddCrp"));
					} else if (isFieldVisible) {
						if (minLengthOfField != maxLengthOfField) {
							expErrMsg = registrationTextProp.getPropValue("txtErrMsgInvalidStreetAddCrp");
							verifyInvalidErrorMessage(entry.getValue(), expErrMsg, sAssert);
						} else {
							expErrMsg = registrationTextProp.getPropValue("txtErrMsgInvalidStreetAddCrp");
							verifyInvalidErrorMessage(entry.getValue(), expErrMsg, sAssert);
						}
					}
					lastCase = verifyCase;
					populateStreetNumber(customer.getStreetAddress());
					break;
				default:
					System.out.println("Mentioned case not present on ui : " + entry.getKey());
				}
			}
			if (pageCount == 2 && i == 0) {
				checkTermsCondIfUnchecked();
				clickNextOrRegisterBtn();
				waitForPageLoader();

			}
		}
		verifyCase = null;
		lastCase = null;
		log.info("Test Case execution for - verifyFieldValidationsResidentialTemp - is " + "initiated");
	}

	public void registrationStepsfieldValidationsCommercialTemp(SoftAssert sAssert, Customer customer) {

	}

	public void emailFieldValidation(SoftAssert sAssert, Customer customer) {
		//navResidentialRegTemp();
		String invalidEmailAddress = registrationTextProp.getPropValue("txtErrMsgInvalidEmailAddCrp");
		int pageCount = getDivRegistrationPageCount().size();
		// Iterating for populating fields over the different pages.
		for (int i = 0; i < pageCount; i++) {
			// Getting fields label and their fields elements
			Map<String, WebElement> labelAndFieldsMap = getFldLblAndInputsOnRegPage();
			// Populating form present over the multiple pages.
			populateRegistrationForm(labelAndFieldsMap, customer);
			// Iterating for populating multiple fields on a page.
			if (labelAndFieldsMap.get("Email Address") != null) {
				WebElement element = labelAndFieldsMap.get("Email Address");
				String expErrMsg = registrationTextProp.getPropValue("txtLblErrorInvalidMailCrp");
				String previousValue = Page.getAttribute(element, "value");
				Page.clear(element);
				//Page.sendKeys(element, invalidEmailAddress);
				checkTermsCondIfUnchecked();
				clickNextOrRegisterBtn();
				//waitForPageLoader();
				String actErrMsg = getLblRegistrationErrormessage2();
				sAssert.assertEquals(actErrMsg, expErrMsg, "Error message is not matched for invalid email.");
			}
			// Verifying terms and conditions link.
			verifyTermsConditionsLink(sAssert);
			checkTermsCondIfUnchecked();
			clickNextOrRegisterBtn();
			//waitForPageLoader();
		}
		// Logging
		log.info("This test method used to verify the following test cases: " + "C75206, C75176, C75175");
		log.info("Test Case execution for - verifyInvalidEmailValidation - is Completed.");
	}

	public void passwordFieldValidations(SoftAssert sAssert, Customer customer) {

		//navResidentialRegTemp();
		String invalidPassword = registrationTextProp.getPropValue("txtErrorMsgBlankPassword");
		String password = customer.getPassword();
		int pageCount = getDivRegistrationPageCount().size();
		// Iterating for populating fields over the different pages.
		for (int i = 0; i < pageCount; i++) {
			// Getting fields label and their fields elements
			Map<String, WebElement> labelAndFieldsMap = getFldLblAndInputsOnRegPage();
			// Populating form present over the multiple pages.
			populateRegistrationForm(labelAndFieldsMap, customer);
			// Iterating for populating multiple fields on a page.
			if (labelAndFieldsMap.get("Password") != null) {
				WebElement element = labelAndFieldsMap.get("Password");
				// String previousValue = pageUtil.getAttributeValue(element,
				// "value");
				Page.clear(element);
				Page.sendKeys(element, invalidPassword);
			}
			if (labelAndFieldsMap.get("Confirm Password") != null) {
				WebElement element = labelAndFieldsMap.get("Confirm Password");
				// String previousValue = pageUtil.getAttributeValue(element,
				// "value");
				Page.clear(element);
				Page.sendKeys(element, invalidPassword);
				checkTermsCondIfUnchecked();
				clickNextOrRegisterBtn();
				//waitForPageLoader();
				String actErrMsg = getLblRegistrationErrormessage2();
				String sExpErrMsg = registrationTextProp.getPropValue("txtErrorMsgInvalidPasswordCrp");
				sAssert.assertTrue(actErrMsg.contains(sExpErrMsg),
						"Error message is not matched for invalid password.");

				break;
			}
			checkTermsCondIfUnchecked();
			clickNextOrRegisterBtn();
			//waitForPageLoader();
		}

		log.info("Test Case execution for - verifyInvalidPassword - is Completed.");

	}

	public void passwordMismatch(SoftAssert sAssert, Customer customer) {
		log.info("Test Case execution for - verifyPasswordMismatch - is Initiated");
		// Navigate to the residential registration page.
		//navResidentialRegTemp();
		String password = null;
		String confirmPassword = registrationTextProp.getPropValue("passwordMismatch2");
		password = "Password";
		int pageCount = getDivRegistrationPageCount().size();
		// Iterating for populating fields over the different pages.
		for (int i = 0; i < pageCount; i++) {
			// Getting fields label and their fields elements
			Map<String, WebElement> labelAndFieldsMap = getFldLblAndInputsOnRegPage();
			// Populating form present over the multiple pages.
			populateRegistrationForm(labelAndFieldsMap, customer);
			// Iterating for populating multiple fields on a page.
			if (labelAndFieldsMap.get("Password") != null) {
				WebElement element = labelAndFieldsMap.get("Password");
				// String previousValue = pageUtil.getAttributeValue(element, "value");
				Page.clear(element);
				Page.sendKeys(element, password);
			}
			if (labelAndFieldsMap.get("Confirm Password") != null) {
				WebElement element = labelAndFieldsMap.get("Confirm Password");
				// String previousValue = pageUtil.getAttributeValue(element,
				// "value");
				Page.clear(element);
				Page.sendKeys(element, confirmPassword);
				checkTermsCondIfUnchecked();
				clickNextOrRegisterBtn();
				String actErrMsg = getToastMessage();
				String expErrMsg = registrationTextProp.getPropValue("txtErrorMsgMismatchPasswordCrp");
				sAssert.assertTrue(actErrMsg.contains(expErrMsg), "Error message is not matched for invalid password.");
				break;
			}
			checkTermsCondIfUnchecked();
			clickNextOrRegisterBtn();
			//waitForPageLoader();
			//labelAndFieldsMap = getFldLblAndInputsOnRegPage();
		}

		log.info("Test Case execution for - verifyPasswordMismatch - is Completed");

	}

	public void registrationTemplate(SoftAssert sAssert, Customer customer) {
		// Initializing number of pages
		int pageCount = getDivRegistrationPageCount().size();

		// Iterating for populating fields over the different pages.
		for (int i = 0; i < pageCount; i++) {
			// Getting fields label and their fields elements
			Map<String, WebElement> labelAndFieldsMap = getFldLblAndInputsOnRegPage();
			// Populating form present over the multiple pages.
			populateRegistrationForm(labelAndFieldsMap, customer);
			// Verifying fields visibility and titles
			verifyFieldsTitleAndVisibility(labelAndFieldsMap);
		}

	}

	public void regByInActiveAccount(SoftAssert sAssert, Customer customer) {
		String actToastMsg;
		String expToastMsg = registrationTextProp.getPropValue("errMsgInactiveAccReg");

		if (customer.getUtilityAccountNumber() != null) {
			int pageCount = getDivRegistrationPageCount().size();
			// Iterating for populating fields over the different pages.
			for (int i = 0; i < pageCount; i++) {
				// Getting fields label and their fields elements
				Map<String, WebElement> labelAndFieldsMap = getFldLblAndInputsOnRegPage();
				// Populating form present over the multiple pages.
				populateRegistrationForm(labelAndFieldsMap, customer);
				if (i == 0) {
					checkTermsCondIfUnchecked();
					clickNextOrRegisterBtn();
					actToastMsg = getToastMessage();
					sAssert.assertEquals(actToastMsg, expToastMsg,
							"Toast not matched on registering with inactive account.");
					break;
				}
				break;
			}

		} else {

			log.info("Test Case execution for - verifyRegByInActiveAccount - is SKIPPED");
		}

		log.info("Test Case execution for - verifyRegByInActiveAccount - is Initiated");

	}

	public void tabOrderForRegForm(SoftAssert sAssert, Customer customer) {

	}

	public void fieldNotRetainValuesOnRegFail(SoftAssert sAssert, Customer customer) {

	}

	public void residentialRegistrationCommercialTemp(SoftAssert sAssert, Customer customer) {
		// LoginSteps loginSteps = new LoginSteps(driver);
		log.info("verify registration page objects");
		LoginSteps loginSteps = new LoginSteps(driver);
		// loginSteps.navigationToRegistrationPage();
		// Verify current title of the page.
		Assert.assertTrue(isRegistrationPage(registrationTextProp.getPropValue("RegistrationPageUrl"),
				registrationTextProp.getPropValue("RegistrationPageTitle")));

		registrationByOptingGivenPref(sAssert, customer, RegistrationPreference.NOPREF.name());
		// Activate user by fetching the mail from contract account notify table and
		// validate.
		loginSteps.loginWithInvalidCreds(customer.getUserName(), customer.getPassword());
		verifyAccountActivation(sAssert, customer);
		// Verifying email and there status while opting both preferences.
		verifyEmailsWhileOptingPref(sAssert, customer.getEmailAddress());
		// Verifying registered user data against data base.
		verifyRegUserDataInDB(sAssert, customer);
		// Verify first login after activation.
		
		loginSteps.loginIntoTheApplication(customer.getUserName(), customer.getUserName());

	}

	public void commercialRegistrationResidentialTemp(SoftAssert sAssert, Customer customer) {
		// LoginSteps loginSteps = new LoginSteps(driver);
		log.info("verify registration page objects");
		LoginSteps loginSteps = new LoginSteps(driver);
		// loginSteps.navigationToRegistrationPage();
		// Verify current title of the page.
		Assert.assertTrue(isRegistrationPage(registrationTextProp.getPropValue("RegistrationPageUrl"),
				registrationTextProp.getPropValue("RegistrationPageTitle")));

		registrationByOptingGivenPref(sAssert, customer, RegistrationPreference.NOPREF.name());
		// Activate user by fetching the mail from contract account notify table and
		// validate.
		loginSteps.loginWithInvalidCreds(customer.getUserName(), customer.getPassword());
		verifyAccountActivation(sAssert, customer);
		// Verifying email and there status while opting both preferences.
		verifyEmailsWhileOptingPref(sAssert, customer.getEmailAddress());
		// Verifying registered user data against data base.
		verifyRegUserDataInDB(sAssert, customer);
		// Verify first login after activation.
		LoginEndpoint loginEndpoint = new LoginEndpoint();
		loginEndpoint.buildRequestSpecForUserLogin(customer.getUserName(), customer.getPassword(),
				ResourcePaths.SCP_COMMON_PATH_URI);

		loginEndpoint.hitLoginAPI();

		if (!loginEndpoint.isTwoFactorAuthRequired()) {
			log.info("Get MFA enabled for user.");
			loginEndpoint.buildRequestForGetMFA(customer.getUserName(), ResourcePaths.SCP_COMMON_PATH_URI);
			log.info("Hit get MFA API.");
			loginEndpoint.hitGetMFAAPI();
			Assert.assertEquals(loginEndpoint.getAPIResponseCode(), 200);
			log.info("Generate OTP for the user.");
			loginEndpoint.buildRequestForCreateMFA(customer.getUserName(), ResourcePaths.SCP_COMMON_PATH_URI);
			log.info("Hit generate OTP API.");
			loginEndpoint.hitCreateMFAAPI();
			Assert.assertEquals(loginEndpoint.getAPIResponseCode(), 200);
			log.info("Validate OTP received on the selected medium.");
			loginEndpoint.buildRequestForValidateMFA(customer.getUserName(), ResourcePaths.SCP_COMMON_PATH_URI);
			log.info("Hit validate OTP API.");
			loginEndpoint.hitValidateMFAAPI();
			Assert.assertEquals(loginEndpoint.getAPIResponseCode(), 200);

		}

		loginSteps.loginIntoTheApplication(customer.getUserName(), customer.getPassword());

	}
	
	public void multipleAccountsAfterResidentialRegistration(SoftAssert sAssert, Customer customer)
	{
		
        log.info("Test Case execution is initiated");
       
        accountDetails = collectAllAccountsInfo(customer.getCustomerName());
        statusCodeValidation(accountDetails,"3","0");
        accountDetails.clear();
        // Verify the navigation to the residential registration page.
        //verifyNavBusinessRegPage(sAssert);
        // Registering opting paperless preference
        registrationByOptingGivenPref(sAssert, customer, RegistrationPreference.PAPERLESS.name());
        // Activate user and validate.
        accountDetails = collectAllAccountsInfo(customer.getCustomerName());
     //  statusCodeValidation(accountDetails,"0","1");
        accountDetails.clear();
        verifyAccountActivation(sAssert, customer);
        // Verifying email and there status while opting both preferences.
        accountDetails = collectAllAccountsInfo(customer.getCustomerName());
        statusCodeValidation(accountDetails,"1","1");
        accountDetails.clear();
        verifyEmailsWhileOptingPref(sAssert, customer.getEmailAddress());
        // Verifying registered user data against data base.
        verifyRegUserDataInDB(sAssert, customer);
        // Verify first login after activation.

       // verifyLoginAfterAct(customer);
        //accountDetails = collectAllAccountsInfo(customer.getCustomerName());
        //verifyMultipleAccountnumbers(customer,accountDetails);
        log.info("Test Case execution for - verifyRegByOptingPaperLessPref - is initiated");
	}
	
	public void multipleAccountsAfterCommercialRegistration(SoftAssert sAssert, Customer customer)
	{
		// Logging
       
        log.info("Test Case execution for - verifyRegByOptingPaperLessPref - is initiated");
       
        accountDetails = collectAllAccountsInfo(customer.getCustomerName());
        statusCodeValidation(accountDetails,"3","0");
        accountDetails.clear();
        // Verify the navigation to the residential registration page.
       // verifyNavBusinessRegPage(sAssert);
        // Registering opting paperless preference
        registrationByOptingGivenPref(sAssert, customer, RegistrationPreference.PAPERLESS.name());
        // Activate user and validate.
        accountDetails = collectAllAccountsInfo(customer.getCustomerName());
       // statusCodeValidation(accountDetails,"0","1");
        accountDetails.clear();
        verifyAccountActivation(sAssert, customer);
        // Verifying email and there status while opting both preferences.
        accountDetails = collectAllAccountsInfo(customer.getCustomerName());
        statusCodeValidation(accountDetails,"1","1");
        accountDetails.clear();
        verifyEmailsWhileOptingPref(sAssert, customer.getEmailAddress());
        // Verifying registered user data against data base.
        verifyRegUserDataInDB(sAssert, customer);
        // Verify first login after activation.

       // verifyLoginAfterAct(customer);
       // accountDetails = collectAllAccountsInfo(regAccountInfo.get("customerID"));
        //verifyMultipleAccountnumbers(customer,accountDetails);
       
      }
	
	
	public void RegMFA_Validations(SoftAssert sAssert, Customer customer)
	{
		// Logging
       
        log.info("Test Case execution for - verify regression MFA validation - is initiated");
        accountDetails = collectAllAccountsInfo(customer.getCustomerName());
        statusCodeValidation(accountDetails,"3","0");
        accountDetails.clear();
        // Verify the navigation to the residential registration page.
       // verifyNavBusinessRegPage(sAssert);
        // Registering opting paperless preference
        registrationByOptingGivenPref(sAssert, customer, RegistrationPreference.PAPERLESS.name());
        }
	/**
	 * Test 4.2: This test method verifies below tests: 1. This test method is to
	 * verify the toast message when a non activated user try to perform login. 2.
	 * To verify that application will not allow user to login user try to log in
	 * without activating it's account by clicking on registration link.
	 */
	public void verifyLoginWithNotActivatedUser(SoftAssert softAssert, Customer customer) {
		// Logging
		log.info("Verifying successful registration toast message.");
		log.info("Test Case execution for - verifyErrorMsgOnLoginWithNonActivatedUser - " + "is Initiated");
		LoginSteps loginSteps = new LoginSteps(driver);
		loginSteps.populateLoginForm(customer.getUserName(), customer.getPassword());
		// Getting toast message
		String actErrMsg = getToastMessage();
		softAssert.assertEquals(actErrMsg, registrationTextProp.getPropValue("RegistrationPageUrl"),
				"Error message for non activated user while login is not matched.");
		loginSteps.clearUsernameField();
		loginSteps.clearPasswordField();
		// Logging
		log.info("Registration toast message verified successfully.");
		log.info("Test Case execution for - verifyToastMsgOnSuccessfulRegistration - " + "is Completed");
	}

	/**
	 * This method activated the registered user with the given username and
	 * validate the activation by login with the user's credentials.
	 *
	 * @param customer
	 * @param softAssert
	 */
	public void verifyAccountActivation(SoftAssert softAssert, Customer customer) {
		// Initialize CIS data after registration
		Map<String, RegistrationMailsConfig> registrationMails = new HashMap<>();
		LoginSteps loginSteps = new LoginSteps(driver);
		SCPConfiguration.initCisDataBeforeActivation(customer.getUtilityAccountNumber());
		String activationUrl = null;
		registrationTextProp.getPropValue("accountActivationMailSubject");
		// Initializing the activation mail contents from the database.
		registrationMails = SCPConfiguration.getRegistrationMailsConfig(customer.getEmailAddress());
		// Get registration related mails.
		RegistrationMailsConfig getRegistrationMailsConfig;
		// getRegistrationMailsConfig = registrationMails.get(subject);
		getRegistrationMailsConfig = registrationMails
				.get(registrationTextProp.getPropValue("accountActivationMailSubject"));
		// Fetch the mail content from the database.
		String mailContent = getRegistrationMailsConfig.getEmailMsg();
		String uniqueStartStr = registrationTextProp.getPropValue("uniqueStartStr");
		String uniqueEndStr = registrationTextProp.getPropValue("uniqueEndStr");
		int size = uniqueStartStr.length();
		int beginIndex = mailContent.indexOf(uniqueStartStr) + size;
		int endIndex = mailContent.indexOf(uniqueEndStr);
		// Extract the activation url from the activation mail content
		activationUrl = mailContent.substring(beginIndex, endIndex).trim();
		// Open the activation url.
		navigateToUrl(activationUrl);
		// Verifying page title and url.
		Assert.assertTrue(isRegistrationPage(registrationTextProp.getPropValue("accountActivationPageUrl"),
				registrationTextProp.getPropValue("accountActivationPageTitle")));
		clickLnkAccActivationClickHereToLogin();
		// Verifying navigation to the login page.
		loginSteps.loginWithBlankCreds();

	}
	
	public void verifyAccountActivationAfterResendActivationLink(SoftAssert softAssert, Customer customer) {
        
        String activationUrl = null;
        //subject = textPropJson.getStringJsonValue("SCM - Account Activation");
        // Initializing the activation mail contents from the database.
        Map<String, String> resendActivationRegistrationMails = getRegistrationMailsResendActivationLinkConfig(customer.getEmailAddress(), customer.getUtilityAccountNumber());
        // Get registration related mails.
//        GetRegMailsConfig getRegistrationMailsConfig;
//        getRegistrationMailsConfig = registrationMails.get(subject);
        // Fetch the mail content from the database.
        String mailContent = resendActivationRegistrationMails.get("Message");
        String uniqueStartStr = "Thank you for registering with Smart Energy Water, <a href='";
        String uniqueEndStr = "'>Click here</a> to activate your account.";
        int size = uniqueStartStr.length();
        int beginIndex = mailContent.indexOf(uniqueStartStr)+size;
        int endIndex = mailContent.indexOf(uniqueEndStr);
        // Extract the activation url from the activation mail content
        activationUrl = mailContent.substring(beginIndex, endIndex).trim();
        // Open the activation url.
        navigateToUrl(activationUrl);
        waitForPageToLoad();
        // pageUtil.expWaitForEleVisibility(locator);
        
        Assert.assertTrue(isRegistrationPage(registrationTextProp.getPropValue("accountActivationPageUrl"),
				registrationTextProp.getPropValue("accountActivationPageTitle")));
		clickLnkAccActivationClickHereToLogin();
        
        verifyAccountStatusInDB(softAssert, customer.getUserName());
    }
	
	public void verifyAccountStatusInDB(SoftAssert softAssert, String userName) {
        ResultSet resultSet;
        
        String status = null;
        try {
            resultSet = DataBaseUtils.getResultSet(SQLQueries.getAccountStatus(userName));
            resultSet.next();
            status = resultSet.getString("Status");
        } catch (Exception e) {
            e.printStackTrace();
        }
        softAssert.assertEquals(status, "1", "Account is not active in database.");
    }
	
	public Map<String, String> getRegistrationMailsResendActivationLinkConfig(String emailId, String account) {
        Map<String, String> contAccountNotifyEmail = new HashMap<>();
        String query = SQLQueries.getRegistrationEmailContentResendActivationLinkCSP(emailId, account);
        ResultSet resultSet;
        try {
            resultSet = DataBaseUtils.getResultSet(query);
            while (resultSet.next()) {
            	contAccountNotifyEmail.put("EmailID", resultSet.getString("EmailID"));
            	contAccountNotifyEmail.put("Subject", resultSet.getString("Subject"));
            	contAccountNotifyEmail.put("Message", resultSet.getString("Message"));
            	contAccountNotifyEmail.put("IsNotify", resultSet.getString("IsNotify"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contAccountNotifyEmail;
    }

	public void verifyEmailsWhileOptingPref(SoftAssert softAssert, String emailId) {
		RegistrationMailsConfig registrationMailsConfig = null;

		registrationMails = SCPConfiguration.getRegistrationMailsConfig(emailId,
				"Smart Energy Water Account Activation");
		softAssert.assertTrue(registrationMails.containsKey("Smart Energy Water Account Activation"),
				"Registration activation mail not received.");
		registrationMailsConfig = registrationMails.get("Smart Energy Water Account Activation");
		if (registrationMailsConfig.getIsEmailSent().equals("0")) {

			registrationMails = SCPConfiguration.getRegistrationMailsConfig(emailId);
			registrationMailsConfig = registrationMails.get("Smart Energy Water Account Activation");
		}
		softAssert.assertEquals(registrationMailsConfig.getIsEmailSent(), "1",
				"Registration activation mail not sent.");
	}

	
	public void verifyRegUserDataInDB(SoftAssert softAssert, Customer customer) {
		Map<String, String> actRegisteredUserData;
		actRegisteredUserData = getRegisteredUserDataDB(customer.getUserName());
		for (Map.Entry<String, String> entry : actRegisteredUserData.entrySet()) {
			switch (entry.getKey()) {
			case "Account Number":
				softAssert.assertEquals(entry.getValue(), customer.getUtilityAccountNumber(),
						"Account number not matched as per DB.");
				break;
			case "First Name":
				// softAssert.assertEquals(entry.getValue(), customer.getFirstName(), "First
				// name not matched as per DB.");
				break;
			case "Last Name":
				//softAssert.assertEquals(entry.getValue(), customer.getLastName(), "Last name not matched as per DB.");
				break;
			case "Email Address":
				softAssert.assertEquals(entry.getValue(), customer.getEmailAddress(),
						"Email address not matched as per DB.");
				break;
			case "ZIP code":
				/*
				 * softAssert.assertEquals(entry.getValue(), mRegistrationDataSet
				 * .get(entry.getKey()), "Zip code not matched as per DB.");
				 */
				break;
			case "Username":
				softAssert.assertEquals(entry.getValue(), customer.getUserName(), "User name not matched as per DB.");
				break;
			case "Meter Number":
				softAssert.assertEquals(entry.getValue(), customer.getMeterNumber(),
						"Meter number not matched as per DB.");
				break;
			case "Primary Contact Number":
				softAssert.assertEquals(entry.getValue(), customer.getPhoneNumber(),
						"Primary contact number not matched as per DB.");
				break;
			case "Contact Type":
				softAssert.assertEquals(entry.getValue(), customer.getContactType(),
						"Contact type not matched as per DB.");
				break;
			case "Driving License Number":
				softAssert.assertEquals(entry.getValue(), customer.getDrivingLicenseNo(),
						"Driving license number not matched as per DB.");
				break;
			case "Street Address":
				softAssert.assertEquals(entry.getValue(), customer.getStreetAddress(),
						"Street address not matched as per DB.");
				break;
			default:
				log.info("All field on the page are not populated");
			}
		}
	}

	public Map<String, String> getRegisteredUserDataDB(String userName) {
		String query = SQLQueries.getRegisteredUserDataQuery(userName);
		Map<String, String> regUserDataMap = new HashMap<>();
		try {
			ResultSet rs = DataBaseUtils.getResultSet(query);
			while (rs.next()) {
				regUserDataMap.put("UserID", rs.getString("UserName"));
				regUserDataMap.put("Username", rs.getString("UserName"));
				regUserDataMap.put("Email Address", rs.getString("EmailID"));
				regUserDataMap.put("Status", rs.getString("Status"));
				regUserDataMap.put("ZIP code", rs.getString("Zipcode"));
				regUserDataMap.put("Primary Phone Number", rs.getString("MobilePhone"));
				regUserDataMap.put("First Name", rs.getString("FirstName"));
				regUserDataMap.put("Last Name", rs.getString("LastName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return regUserDataMap;
	}

	public void verifyDiffFieldsForRespectiveAddType(SoftAssert softAssert, String addType) {
		if (addType.equals("Residential")) {
			softAssert.assertTrue(isSSNNumberVisible(), "SSN field is not displayed.");
		} else if (addType.equals("Commercial")) {
			softAssert.assertTrue(isTxtBoxFidTinVisible(), "FID/TIN field is not displayed.");
		}
	}

	public void selectPreferencesAsGiven(SoftAssert softAssert, String preferencesOpted) {
		switch (preferencesOpted) {
		case "ALLPREF":
			isPaperlessOpted = "1";
			break;
		case "PAPERLESS":
			isPaperlessOpted = "1";
			if (isRdbPaperlessBillVisible() == true) {
				checkRdbPaperlessBill();
			}
			break;
		case "PAPER":
			isPaperlessOpted = "0";
			if (isRdbPaperBillVisible() == true) {
				checkRdbPaperBill();
			}
			break;
		case "BOTH":
			isPaperlessOpted = "2";
			if (isRdbPaperPaperlessBothVisible() == true) {
				checkRdbPaperPaperlessBoth();
			}
			break;
		case "NOPREF":
			isPaperlessOpted = "NULL";
			break;
		}
	}

	public void checkTermsCondIfUnchecked() {
		// Check terms and condition if present on the first page.
		if (isChkBoxTermsConditionsVisible() == true) {
			checkChkBoxTermsConditions();
		}
	}

	public Map<String, WebElement> getFldLblAndInputsOnRegPage() {
		String selectContactType = "Contact Type";
		Map<String, WebElement> regLabelsAndFields = new LinkedHashMap<>();
		getLblAllRegActiveFields().clear();

		int i = 0;
		int k = 0;
		for (int ObjectListCount = 0; ObjectListCount < getLblAllRegActiveFields().size(); ObjectListCount++) {
			WebElement element;
			String sFieldLabel = getLblAllRegActiveFields().get(ObjectListCount);
			if (sFieldLabel.equals(selectContactType)) {
				element = getDdAllActiveRegFields().get(i);
				i++;

			} else {
				element = getTxtBoxAllActiveRegFields().get(k);
				k++;

			}
			regLabelsAndFields.put(sFieldLabel, element);
		}
		return regLabelsAndFields;
	}

	public void verifyGenericMsgForMandatoryFields(SoftAssert sAssert) {

		log.info("Test Case execution for - verifyMsgForAllMandatoryFields - is Initiated");
		LoginSteps loginSteps = new LoginSteps(driver);
		loginSteps.navigationToRegistrationPage();
		// Click register button.
		clickNextOrRegisterBtn();
		sAssert.assertEquals(getLblRegistrationErrormessage(),
				registrationTextProp.getPropValue("txtErrMsgBlankServiceAccount"));
		log.info("Test Case execution for - verifyMsgForAllMandatoryFields - is Completed.");
	}

	public void clickNextOrRegisterBtn() {
		// Scroll down to the end of the page.

		// Click next button if present.
		if (isNextBtnVisible() == true) {
			
			clickNextBtn();
			//waitForPageLoader();
			
		}
		// Click register button if present.
		else if (isRegisterBtnVisible() == true) {
			
			RegisterBtn();
			//waitForPageLoader();
			
		} else {

			log.info("Neither next button is displaying nor register button.");
		}
	}

	public void registrationByOptingGivenPref(SoftAssert sAssert, Customer customer, String preferencesOpted) {
		int pageCount = getDivRegistrationPageCount().size();
		// Iterating for populating fields over the different pages.
		for (int i = 0; i < pageCount; i++) {
			Map<String, WebElement> labelAndFieldsMap = getFldLblAndInputsOnRegPage();
			for (Map.Entry<String, WebElement> entry : labelAndFieldsMap.entrySet()) {
				switch (entry.getKey()) {
				case "Account Number":
					// Fill account number if data set have value for it
					if (isUtilityAccountNumberVisible() == true) {
						populateUtilityAccountNumber(customer.getUtilityAccountNumber());
					}
					break;
				case "First Name":
					// Fill first name if data set have value for it
					if (isFirstNameVisible() == true) {
						populateFirstName(customer.getFirstName());
					}
					break;
				case "Last Name":
					// Fill last name if data set have value for it
					if (isLastNameVisible() == true) {
						populateLastName(customer.getLastName());
					}
					break;
				case "Email Address":
					// Fill email address if data set have value for it
					if (isTxtBoxEmail() == true) {
						populateTxtBoxEmail(customer.getEmailAddress());
					}
					break;
				case "ZIP code":
					// Fill zip code if data set have value for it
					if (isPostalCodeVisible() == true) {
						populatePostalCode(customer.getZipCode());
					}
					break;
				case "Username":
					// Fill username if data set have value for it
					if (isUserNameVisible() == true) {
						populateUserName(customer.getUserName());
					}
					break;
				case "Password":
					// Fill password if data set have value for it
					if (isPasswordVisible() == true) {
						populatePassword(customer.getPassword());
					}
					break;
				case "Confirm Password":
					// Fill confirm password if data set have value for it
					if (isConfirmPasswordVisible() == true) {
						populateConfirmPassword(customer.getPassword());
					}
					break;
				case "Meter Number":
					// Fill meter number if data set have value for it
					if (isTxtBoxMeterNumberVisible() == true) {
						populateTxtBoxMeterNumber(customer.getMeterNumber());
					}
					break;
				case "Primary Phone Number":
					// Fill primary contact number if data set have value for it
					if (isTxtBoxPrimaryContactNumber() == true) {
						populatetxtBoxPrimaryContactNumber(customer.getPhoneNumber());
					}
					break;
				case "Contact Type":
					// Select contact type if data set have value for it
					if (isDdlContactType() == true) {
						selectDdlContactType("Mobile");
					}
					break;
				case "Last 4 Digits of SSN":
					// Fill primary ssn number if data set have value for it
					if (isSSNNumberVisible() == true) {
						populateSSNNumber(customer.getSsn());
					}
					break;
				case "Driving License Number":
					// Fill driving number if data set have value for it
					if (isDrivingLicenceVisible() == true) {
						//populateDrivingLicence(customer.getDrivingLicenseNo());
					}
					break;
				case "Street Address":
					// Fill street address if data set have value for it
					if (isStreetNumberVisible() == true) {
						populateStreetNumber(customer.getStreetAddress());
					}
					break;
				case "Last 4 digit of FID/TIN":
					if (isTxtBoxFidTinVisible() == true) {
						populateTxtBoxFidTin(customer.getFidTin());
					}
					break;
				default:
					log.info("All field on the page are not populated");
				}

			}

			if (pageCount == 1) {
				// Select the preference
				selectPreferencesAsGiven(sAssert, preferencesOpted);
				verifyDiffFieldsForRespectiveAddType(sAssert, preferencesOpted);
			} else if (pageCount == 2 && i == 1) {
				selectPreferencesAsGiven(sAssert, preferencesOpted);
				verifyDiffFieldsForRespectiveAddType(sAssert, preferencesOpted);
			}
			// Check terms and conditions if unchecked.
			checkTermsCondIfUnchecked();
			// Click next or registration button whichever displayed on the registration
			// screen
			clickNextOrRegisterBtn();
			pause(9000);
            if (isbtnRegNextVisible()==true)
            {
            	if(isPhoneNumberTermandConVisible()==true)
            	{
            		checkChkPhoneNumberTermandCon();
            	}
            	registrationPageMFAOtpsubmit(customer);
            	
            }
			
			if (pageCount == 1) {

				LnkDoneRegistrationSucessBtn();
				isUsernameTxtVisible();
			} else if (pageCount == 2 && i == 0) {
				// Explicit wait for login page.

			} else if (pageCount == 2 && i == 1) {
				LnkDoneRegistrationSucessBtn();
				isUsernameTxtVisible();
			}

		}
	}

	public void populateRegistrationForm(Map<String, WebElement> regFormLblAndFields, Customer customer) {
		for (Map.Entry<String, WebElement> entry : regFormLblAndFields.entrySet()) {
			CSPConfiguration cspConfiguration = new CSPConfiguration();
			registrationConfig = cspConfiguration.initRegistrationConfig();
			switch (entry.getKey()) {
			case "Account Number":
				// Fill account number if data set have value for it
				isFieldVisible = registrationConfig.get(entry.getKey()).getFieldDisplayStatus().equals("1");
				if (isFieldVisible) {
					Page.scrollToElement(entry.getValue());
					isUtilityAccountNumberVisible();
					populateUtilityAccountNumber(customer.getUtilityAccountNumber());
				}
				break;
			case "First Name":
				// Fill first name if data set have value for it
				isFieldVisible = registrationConfig.get(entry.getKey()).getFieldDisplayStatus().equals("1");
				if (isFieldVisible) {
					isFirstNameVisible();
					populateFirstName(customer.getCustomerName());

				}
				break;
			case "Last Name":
				// Fill last name if data set have value for it
				isFieldVisible = registrationConfig.get(entry.getKey()).getFieldDisplayStatus().equals("1");
				if (isFieldVisible) {
					isLastNameVisible();
					populateLastName(customer.getLastName());
				}
				break;
			case "Email Address":
				// Fill email address if data set have value for it
				isFieldVisible = registrationConfig.get(entry.getKey()).getFieldDisplayStatus().equals("1");
				if (isFieldVisible) {
					isTxtBoxEmail();
					populateTxtBoxEmail(customer.getEmailAddress());
				}
				break;
			case "ZIP code":
				// Fill zip code if data set have value for it
				isFieldVisible = registrationConfig.get("Zip Code").getFieldDisplayStatus().equals("1");
				if (isFieldVisible) {
					isPostalCodeVisible();
					populatePostalCode(customer.getZipCode());
				}
				break;
			case "Username":
				// Fill username if data set have value for it
				isFieldVisible = registrationConfig.get(entry.getKey()).getFieldDisplayStatus().equals("1");
				if (isFieldVisible) {
					isUserNameVisible();
					populateUserName(customer.getUserName());
				}
				break;
			case "Password":
				// Fill password if data set have value for it
				isFieldVisible = registrationConfig.get(entry.getKey()).getFieldDisplayStatus().equals("1");
				if (isFieldVisible) {
					isPasswordVisible();
					populatePassword(customer.getPassword());
				}
				break;
			case "Confirm Password":
				// Fill confirm password if data set have value for it
				isFieldVisible = registrationConfig.get(entry.getKey()).getFieldDisplayStatus().equals("1");
				if (isFieldVisible) {
					isConfirmPasswordVisible();
					populateConfirmPassword(customer.getPassword());
				}
				break;
			case "Meter Number":
				// Fill meter number if data set have value for it
				isFieldVisible = registrationConfig.get(entry.getKey()).getFieldDisplayStatus().equals("1");
				if (isFieldVisible) {
					isTxtBoxMeterNumberVisible();
					populateTxtBoxMeterNumber(customer.getMeterNumber());
				}
				break;
			case "Primary Phone Number":
				// Fill primary contact number if data set have value for it
				isFieldVisible = registrationConfig.get("Primary Contact Number").getFieldDisplayStatus().equals("1");
				if (isFieldVisible) {
					isTxtBoxPrimaryContactNumber();
					populatetxtBoxPrimaryContactNumber(customer.getPhoneNumber());
				}
				break;
			case "Contact Type":
				// Select contact type if data set have value for it
				isFieldVisible = registrationConfig.get(entry.getKey()).getFieldDisplayStatus().equals("1");
				if (isFieldVisible) {
					isDdlContactType();
					selectDdlContactType("Mobile");
				}
				break;
			case "Last 4 Digits of SSN":
				// Fill primary ssn number if data set have value for it
				isFieldVisible = registrationConfig.get("SSN").getFieldDisplayStatus().equals("1");
				if (isFieldVisible) {
					isSSNNumberVisible();
					populateSSNNumber(customer.getSsn());
				}
				break;
			case "Driving License Number":
				// Fill driving number if data set have value for it
				isFieldVisible = registrationConfig.get(entry.getKey()).getFieldDisplayStatus().equals("1");
				if (isFieldVisible) {
					isDrivingLicenceVisible();
					//populateDrivingLicence(customer.getDrivingLicenseNo());
				}
				break;
			case "Street Address":
				// Fill street address if data set have value for it
				isFieldVisible = registrationConfig.get(entry.getKey()).getFieldDisplayStatus().equals("1");
				if (isFieldVisible) {
					isStreetNumberVisible();
					populateStreetNumber(customer.getStreetAddress());
				}
				break;
			case "Last 4 digit of FID/TIN":
				isFieldVisible = registrationConfig.get("FID/TIN").getFieldDisplayStatus().equals("1");
				if (isFieldVisible) {
					isTxtBoxFidTinVisible();
					populateTxtBoxFidTin(customer.getFidTin());
				}
				break;
			default:
				log.info("All field on the page are not populated");
			}
		}
	}

	/**
	 * This method is to click register button by selecting residential option in
	 * header.
	 */
	public void navResidentialRegTemp() {
		try {

			clickRegisterBtn();
			isUtilityAccountNumberVisible();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertTrue(isRegistrationPage(registrationTextProp.getPropValue("expRegPageUrl"),
				registrationTextProp.getPropValue("expRegPageTitle")));
		checkResidentialRdo();
	}

	public void getMinMaxLenOfRegFldFromDB() {
		String query = SQLQueries.getRegistrationTemplateConfig();
		try {
			ResultSet rs = DataBaseUtils.getResultSet(query);
			while (rs.next()) {
				fieldsMinLength.put(rs.getString("ParentHead"), rs.getString("Min Length"));
				fieldsMaxLength.put(rs.getString("ParentHead"), rs.getString("Max Length"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is to verify the blank field validations.
	 *
	 * @param element
	 * @param expValidationMsg
	 */
	public void verifyBlankErrorMessage(SoftAssert softAssert, WebElement element, String expValidationMsg) {
		String lastValue = null;
		if (element.getTagName().equals("input")) {
			lastValue = element.getAttribute("value");
			Page.clear(element);
		} else if (element.getTagName().equals("select")) {
			lastValue = Page.getSelectedValueInDropBox(element);
			Page.selectByValue(element, "--Select--");
		}
		checkTermsCondIfUnchecked();
		clickNextOrRegisterBtn();
		actValidation = getLblRegistrationErrormessage();
		softAssert.assertEquals(actValidation, expValidationMsg, "The field validation not matched.");
		if (element.getTagName().equals("input")) {
			if (lastValue.equals("")) {
				Page.sendKeys(element, "default");

			} else
				Page.sendKeys(element, lastValue);

		} else if (element.getTagName().equals("select")) {
			Page.sendKeys(element, lastValue);
		}
	}

	public void verifyInvalidErrorMessage(WebElement element, String expValidationMsg, SoftAssert softAssert) {
		String lastValue = null;
		HomePage homePage = new HomePage(driver);
		if (element.getTagName().equals("input")) {
			lastValue = element.getAttribute("value");
			Page.clear(element);
			Page.sendKeys(element, "0000");
			checkTermsCondIfUnchecked();
			clickNextOrRegisterBtn();
			//waitForPageLoader();
			actValidation = getLblRegistrationErrormessage();
			softAssert.assertEquals(actValidation, expValidationMsg,
					"Error message: " + expValidationMsg + " not matched.");
			Page.sendKeys(element, lastValue);
			Page.clear(element);
			Page.sendKeys(element, "1");
			checkTermsCondIfUnchecked();
			clickNextOrRegisterBtn();
			actValidation = getLblRegistrationErrormessage();
			softAssert.assertEquals(actValidation, expValidationMsg,
					"Error message: " + expValidationMsg + " not matched.");
			Page.sendKeys(element, lastValue);

		}
	}

	public void verifyTermsConditionsLink(SoftAssert softAssert) {
		// Logging
		log.info("C11104 - To verify that if click on Terms and condition link. "
				+ "It should open in pop up as updated in CSP.");
		log.info("Test Case execution for - verifyTermsConditionsLink - is Initiated");
		if (isTermsNconditionVisible() == true) {
			clickTermsNcondition();
			String expTermsHead = registrationTextProp.getPropValue("txtLblTermsDialogueHeadingCrp");
			String actTermsHead = getLblTermsDialogueHeading();
			softAssert.assertEquals(actTermsHead, expTermsHead, "Terms and conditions dialogue heading not matched.");
			String expTermsTitle1 = registrationTextProp.getPropValue("txtLblTermsDialogueBodyTitle1Crp");
			//getLblTermsDialogueBodyTitle1();
			String expTermsTitle2 = registrationTextProp.getPropValue("txtLblTermsDialogueBodyTitle2Crp");
			String actTermsTitle2 = getLblTermsDialogueBodyTitle2();
			softAssert.assertEquals(expTermsTitle2, actTermsTitle2, "Terms and dialogue body title 2 not matched.");
			clickBtnCloseTermsDialogue();

		}
		// Logging

		log.info("Test Case execution for - verifyTermsConditionsLink - is Completed");
	}

	public void verifyFieldsTitleAndVisibility(Map<String, WebElement> regFldLblMap) {

		log.info("Test Case execution for - verifyObjectsInAdvancedResidentialRegPage - is Initiated");
		SoftAssert softAssert = new SoftAssert();
		for (Map.Entry<String, WebElement> entry : regFldLblMap.entrySet()) {
			switch (entry.getKey()) {
			case "Account Number":
				// Verifying service account number visibility and title.
				isUtilityAccountNumberVisible();
				String expTitleAccountNo = registrationTextProp.getPropValue("txtTitleAccountNoCrp");
				String actTitleAccountNo = getAttributeUtilityAccountNumber("title");
				softAssert.assertEquals(actTitleAccountNo, expTitleAccountNo, "Account number title not matched");
				break;
			case "First Name":
				// Verifying first name visibility and title.
				isFirstNameVisible();
				String expTitleFirstName = registrationTextProp.getPropValue("txtTitleFirstNameCrp");
				String actTitleFirstName = getAttributeFirstName("title");
				softAssert.assertEquals(actTitleFirstName, expTitleFirstName, "First name title not matched");
				break;
			case "Last Name":
				// Verifying last name visibility and title.
				isLastNameVisible();
				String expTitleLastName = registrationTextProp.getPropValue("txtTitleLastNameCrp");
				String actTitleLastName = getAtrributeLastName("title");
				softAssert.assertEquals(actTitleLastName, expTitleLastName, "Last name title not matched");
				break;
			case "Email Address":
				// Verifying email field visibility and title.
				isTxtBoxEmail();
				String expTitleEmail = registrationTextProp.getPropValue("txtTitleEmailCrp");
				String actTitleEmail = getTxtBoxEmail("title");
				softAssert.assertEquals(actTitleEmail, expTitleEmail, "Email title not matched");
				break;
			case "ZIP code":
				// Verifying postal code field visibility and title
				isPostalCodeVisible();
				String expTitleZip = registrationTextProp.getPropValue("txtTitleZipCrp");
				String actTitleZip = getPostalCode("title");
				softAssert.assertEquals(actTitleZip, expTitleZip, "Zip title not matched.");
				break;
			case "Username":
				isUserNameVisible();
				break;
			case "Password":
				// Verifying password field visibility and title
				isPasswordVisible();
				String expTitlePassword = registrationTextProp.getPropValue("txtTitlePasswordCrp");
				String actTitlePassword = getAttributePassword("title");
				softAssert.assertEquals(actTitlePassword, expTitlePassword, "Password title not matched.");
				break;
			case "Confirm Password":
				// Verifying confirm password field visibility and title
				isConfirmPasswordVisible();
				String expTitleConfirmPassword = registrationTextProp.getPropValue("txtTitleConfirmPasswordCrp");
				String actTitleConfirmPassword = getConfirmPassword("title");
				softAssert.assertEquals(actTitleConfirmPassword, expTitleConfirmPassword,
						"Confirm Password title not matched.");
				break;
			case "Meter Number":
				isTxtBoxMeterNumberVisible();
				String expTitleMeterNo = registrationTextProp.getPropValue("txtTitleDrivingLicenseNoCrp");
				String actTitleMeterNo = getAttributetxtBoxMeterNumber("title");
				softAssert.assertEquals(actTitleMeterNo, expTitleMeterNo, "meter number title not matched.");
				break;
			case "Primary Phone Number":
				// Verifying primary contact number field visibility and title
				isTxtBoxPrimaryContactNumber();
				String expTitleContactNo = registrationTextProp.getPropValue("txtTitleContactNoCrp");
				String actTitleContactNo = getAttributeTxtBoxPrimaryContactNumber("title");
				softAssert.assertEquals(actTitleContactNo, expTitleContactNo, "Contact number title not matched.");
				break;
			case "Contact Type":
				// Verifying contact type field visibility and title
				isDdlContactType();
				break;
			case "Last 4 Digits of SSN":
				// Verifying ssn field visibility and title
				isSSNNumberVisible();
				String expTitleSsn = registrationTextProp.getPropValue("txtTitleSsnCrp");
				String actTitleSsn = getAttributeSSNNumber("title");
				softAssert.assertEquals(actTitleSsn, expTitleSsn, "Ssn title not matched.");
				break;
			case "Driving License Number":
				// Verifying driving license number field visibility and title
				isDrivingLicenceVisible();
				String expTitleDrivingLicenseNo = registrationTextProp.getPropValue("txtTitleStreetAddCrp");
				String actTitleDrivingLicenseNo = getAttributeDrivingLicence("title");
				softAssert.assertEquals(actTitleDrivingLicenseNo, expTitleDrivingLicenseNo,
						"Driving license number title not matched.");
				break;
			case "Street Address":
				// Verifying text box street address field visibility and title
				isStreetNumberVisible();
				String expTitleAddress = registrationTextProp.getPropValue("txtTitleStreetAddCrp");
				String actTitleAddress = getAttributeStreetNumber("title");
				softAssert.assertEquals(actTitleAddress, expTitleAddress, "Address title not matched.");
				break;
			case "Last 4 digit of FID/TIN":
				// Verifying ssn field visibility and title
				isTxtBoxFidTinVisible();
				String expTitleFid = registrationTextProp.getPropValue("txtTitleFidCrp");
				String actTitleFid = getAttributeTxtBoxFidTin("title");
				softAssert.assertEquals(actTitleFid, expTitleFid, "Fid/Tin title not matched.");
				break;
			default:
				log.info("All field on the page are not populated");
			}
		}
		log.info("Test Case execution for - verifyObjectsInAdvancedResidentialRegPage - is Completed.");
	}

	public void verifyErrMsgForUsernameField(SoftAssert softAssert, WebElement element, String input,
			String expValidation) {
		String previousValue = getAttributeUserName("value");
		clearUsernameField();
		element.clear();
		populateUserName(input);
		checkTermsCondIfUnchecked();
		clickNextOrRegisterBtn();
		clickPreviousBtn();
		/*
		 * pageUtil.expWaitForEleVisibility(customerRegPage.getLblFieldErrMsgRightBsp())
		 * ; actValidation =
		 * pageUtil.getObjectLabel(customerRegPage.getLblFieldErrMsgRightBsp());
		 * softAssert.assertEquals(actValidation, expValidation,
		 * "Username validation not matched actual message : \n" + actValidation +
		 * "\n expected message : \n" + expValidation);
		 */
		populateUserName(previousValue);
	}
	 public HashMap<String, Map> collectAllAccountsInfo(String cutomerid) {
	    	
	    	HashMap <String,Map>accountInfoCount = new HashMap<>();
	    	
	    	
	    	String registrationDataQuery = SQLQueries.getAccountsDetails(cutomerid);
	        System.out.println(registrationDataQuery);
	        ResultSet rsRegData;
	        try {
	        	
	            rsRegData = DataBaseUtils.getResultSet(registrationDataQuery);
	            while (rsRegData.next()) {
	            	int count =0;
	            	HashMap <String,String>accountInfo  = new HashMap<>();
					
	            	
	            	accountInfo.put("AccountStatusID",rsRegData.getString("AccountStatusID"));
	            	accountInfo.put("MobilePhone",rsRegData.getString("MobilePhone"));
	            	accountInfo.put("Address1",rsRegData.getString("Address1"));
	            	accountInfo.put("UtilityAccountNumber",rsRegData.getString("UtilityAccountNumber"));
	            	accountInfo.put("BillType",rsRegData.getString("Paperless"));
	            	accountInfo.put("EmailID",rsRegData.getString("EmailID"));
	            	
	            	accountInfoCount.put(rsRegData.getString("UtilityAccountNumber"),accountInfo);
	            	
	            	
	            	
	            	//accountInfo.clear();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        
	        }
	        return accountInfoCount;
	    }
	 
	 public void statusCodeValidation(HashMap<String, Map> accountDetails,String accountStatusID,String billType)
	 {
	 	
	 	for ( Map.Entry<String,Map> entry : accountDetails.entrySet()) {
	 	    String key = entry.getKey();
	 	    System.out.println(key);
	 	   System.out.println(entry.getValue().get("AccountStatusID"));
	 	   Assert.assertEquals((entry.getValue().get("AccountStatusID")),accountStatusID);
	 	   Assert.assertEquals((entry.getValue().get("BillType")),billType);
	 	   System.out.println(entry.getValue().get("MobilePhone"));
	 	   System.out.println(entry.getValue().get("Address1"));
	 	   
	     }
	 }
	 /**
	     * Test 3.1: This test method verify below tests:
	     * 1. C75163 - This test case verifies navigating the business registration
	     * page and clicking the register button without any entry.
	     */
	    public void verifyNavBusinessRegPage(SoftAssert softAssert) {
	        // Logging
	       log.info("Test Case execution for - verifyNavBusinessRegPage - is Initiated");
	        // Verify current title of the page.
	        Assert.assertTrue(isRegistrationPage(registrationTextProp.getPropValue("expectedLoginPageUrl"),
					registrationTextProp.getPropValue("expectedLoginPageTitle")));
	        // Click residential registration page.
	        registerCommercialBtn();
	        // Logging
	       
	        log.info("Test Case execution for - verifyNavToResidentialRegistrationPage - is Completed.");
	    }

		public void registrationPageMFAOtpsubmit(Customer customer) {
			ResultSet resultSet;
			pause(5000);
			clickRegisterNextBtn();
			pause(15000);
			try {
				String customerAccountnumber = customer.getUtilityAccountNumber();
				resultSet = DataBaseUtils.getResultSet(SQLQueries.getRegistrationMFAOtp(customerAccountnumber));
				while (resultSet.next()) {
					OtpList.add(resultSet.getString("token"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			populateBoxMfaOtp(OtpList.get(0).toString());
			clickSubmitOtp();
			OtpList.clear();
		}

		public void clickRegisterNextBtn() {

			// Click next button if present.
			if (isbtnRegNext1Visible() == true) {
				clickbtnRegNextBtn();
				//pause(10000);
			} else {
				log.info("Previous button is displaying.");
			}
		}

	}

package sew.ai.tests.scp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import sew.ai.config.SCPConfiguration;
import sew.ai.enums.CategoryType;
import sew.ai.helpers.anotations.FrameworkAnnotations;
import sew.ai.helpers.testrail.TestRail;
import sew.ai.models.Customer;
import sew.ai.runner.TestRunner;
import sew.ai.steps.scp.LoginSteps;
import sew.ai.steps.scp.RegistrationSteps;


public class RegistrationTests extends TestRunner {
	private static final Logger log = LogManager.getLogger(RegistrationTests.class);
	private RegistrationSteps registrationSteps;
	HashMap<String, String> customerData = new HashMap<>();
	
	@TestRail(testCaseId = { 75149, 75174, 75184, 75203,75162, 75189, 75190, 75192, 75212, 75233, 75236, 75242, 75251, 102654,75094,75195 })
	@FrameworkAnnotations(author = { "" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the residential registration page objects.")
	public void verifyRegistrationPageObjects() throws SQLException {
		log.info("To verify the tests with the below test case id's: "
				+ "C77192, C77193, C77083, C77084, C77085, C77086, C77091, C77093, C77285, C77087");
		log.info("Test Case execution for - verifyCustomerRegistrationPage - is Initiated");
		SoftAssert softAssert = new SoftAssert();
		registrationSteps = new RegistrationSteps(driver);
		Customer customer = SCPConfiguration.initCustomerConfig("John Doe","1");
		// Verify generic validation message for all the mandatory fields
		registrationSteps.verifyGenericMsgForMandatoryFields(softAssert);
	   // verify objects of registration page.
		registrationSteps.verifyRegistrationPageObjectsSteps(softAssert, customer);
		softAssert.assertAll();
	}
	 /**
     * Test 4: This test method verifies below tests:
     * C75160, C75185, C75186, C75205, C75207, C75208, C75101
     * C75211, C75214, C75218, C75219, C75227, C75228,
     * C77196, C75185, C102487
	 * @throws SQLException 
     */
	@FrameworkAnnotations(author = { "" }, category = { CategoryType.SANITY })
	@TestRail(testCaseId = {75160, 75185, 75186, 75205, 75207, 75208, 75211, 75214, 75218, 75219,
            75227, 75228, 102487,77196,75101})
	@Test(priority = 1, description = "To verify the residential registration with both preference")

    public void verifyRegByOptingBothPreferences() throws SQLException {
		log.info("To verify the tests with the below test case id's: "
				+ "C75160,C75185,C75186,C75205,C75207,C75208,C75211,C75214,C75218,C75219"
				+ "C75227,C75228,C102487,C77196,C75101");
		log.info("Test Case execution for -verifyRegByOptingBothPreferences - is Initiated");
		SoftAssert softAssert = new SoftAssert();
		registrationSteps = new RegistrationSteps(driver);
		Customer customer = SCPConfiguration.initCustomerConfig("John Doe","1");
		// Verify generic validation message for all the mandatory fields
		registrationSteps.verifyGenericMsgForMandatoryFields(softAssert);
        registrationSteps.verifyRegByOptingBothPreferences(softAssert, customer);
    }
	
	@FrameworkAnnotations(author = { "Ranjit Biswal" }, category = { CategoryType.SANITY })
	@TestRail(testCaseId = {75215, 75217, 75220, 75229, 75230, 75234, 75241, 75093,
            75237, 75239, 75240, 75246, 106337, 106295, 105138, 105134,107170,120090})
	@Test(priority = 1, description = "To verify the residential registration with Paper less pref")

    public void verifyRegByOptingPaperLessPref() throws SQLException {
		log.info("To verify the tests with the below test case id's: "
				+ "C75160,C75185,C75186,C75205,C75207,C75208,C75211,C75214,C75218,C75219"
				+ "C75227,C75228,C102487,C77196,C75101");
		log.info("Test Case execution for -verifyRegByOptingBothPreferences - is Initiated");
		SoftAssert softAssert = new SoftAssert();
		registrationSteps = new RegistrationSteps(driver);
		Customer customer = SCPConfiguration.initCustomerConfig("John Doe","1");
		// Verify generic validation message for all the mandatory fields
		registrationSteps.verifyGenericMsgForMandatoryFields(softAssert);
        registrationSteps.verifyRegByOptingPaperlessPreferences(softAssert, customer);
    }
	
	@FrameworkAnnotations(author = { "Ranjit Biswal" }, category = { CategoryType.SANITY })
	@TestRail(testCaseId = {105131, 105132, 105134, 105138, 106335})
	@Test(priority = 1, description = "To verify the residential registration with Paper less pref")

    public void verifyRegByOptingPaperBillPref() throws SQLException {
		log.info("To verify the tests with the below test case id's: "
				+ "C75160,C75185,C75186,C75205,C75207,C75208,C75211,C75214,C75218,C75219"
				+ "C75227,C75228,C102487,C77196,C75101");
		log.info("Test Case execution for -verifyRegByOptingBothPreferences - is Initiated");
		SoftAssert softAssert = new SoftAssert();
		registrationSteps = new RegistrationSteps(driver);
		Customer customer = SCPConfiguration.initCustomerConfig("John Doe","1");
		// Verify generic validation message for all the mandatory fields
		registrationSteps.verifyGenericMsgForMandatoryFields(softAssert);
        registrationSteps.verifyRegByOptingPaperBillPreferences(softAssert, customer);
    }
	@TestRail(testCaseId = {75151, 75152, 75153, 75154, 75155, 75156, 75178, 75204, 75222, 75223,
            75224, 75225})
	@FrameworkAnnotations(author = { "" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the residential registration page objects.")
	public void verifyFieldValidationsResidentialTemp() throws SQLException {
		log.info("To verify the tests with the below test case id's: "
				+ "C77192, C77193, C77083, C77084, C77085, C77086, C77091, C77093, C77285, C77087");
		log.info("Test Case execution for - verifyCustomerRegistrationPage - is Initiated");
		SoftAssert softAssert = new SoftAssert();
		registrationSteps = new RegistrationSteps(driver);
		Customer customer = SCPConfiguration.initCustomerConfig("John Doe","1");
		// Verify generic validation message for all the mandatory fields
		registrationSteps.verifyGenericMsgForMandatoryFields(softAssert);
	   // verify objects of registration page.
		registrationSteps.fieldValidationsResidentialTemp(softAssert, customer);
		softAssert.assertAll();
	}
	@TestRail(testCaseId = {75166, 75167, 75168, 75169})
	@FrameworkAnnotations(author = { "" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the residential registration page objects.")
	public void verifyFieldValidationsCommercialTemp() throws SQLException {
		log.info("To verify the tests with the below test case id's: "
				+ "C77192, C77193, C77083, C77084, C77085, C77086, C77091, C77093, C77285, C77087");
		log.info("Test Case execution for - verifyCustomerRegistrationPage - is Initiated");
		SoftAssert softAssert = new SoftAssert();
		registrationSteps = new RegistrationSteps(driver);
		Customer customer = SCPConfiguration.initCustomerConfig("John Doe","2");
		// Verify generic validation message for all the mandatory fields
		registrationSteps.verifyGenericMsgForMandatoryFields(softAssert);
	   // verify objects of registration page.
		registrationSteps.registrationStepsfieldValidationsCommercialTemp(softAssert, customer);
		softAssert.assertAll();
	}
	
	@TestRail(testCaseId = {75206, 75176, 75175})
	@FrameworkAnnotations(author = { "" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the residential registration page objects.")
	public void verifyEmailFieldValidation() throws SQLException {
		log.info("To verify the tests with the below test case id's: "
				+ "C77192, C77193, C77083, C77084, C77085, C77086, C77091, C77093, C77285, C77087");
		log.info("Test Case execution for - verifyCustomerRegistrationPage - is Initiated");
		SoftAssert softAssert = new SoftAssert();
		registrationSteps = new RegistrationSteps(driver);
		Customer customer = SCPConfiguration.initCustomerConfig("John Doe","1");
		// Verify generic validation message for all the mandatory fields
		registrationSteps.verifyGenericMsgForMandatoryFields(softAssert);
	   // verify objects of registration page.
		registrationSteps.emailFieldValidation(softAssert, customer);
		softAssert.assertAll();
	}
	@TestRail(testCaseId = {75179, 75252})
	@FrameworkAnnotations(author = { "" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the residential registration page objects.")
	public void verifyPasswordFieldValidations() throws SQLException {
		log.info("To verify the tests with the below test case id's: "
				+ "C77192, C77193, C77083, C77084, C77085, C77086, C77091, C77093, C77285, C77087");
		log.info("Test Case execution for - verifyCustomerRegistrationPage - is Initiated");
		SoftAssert softAssert = new SoftAssert();
		registrationSteps = new RegistrationSteps(driver);
		Customer customer = SCPConfiguration.initCustomerConfig("John Doe","1");
		// Verify generic validation message for all the mandatory fields
		registrationSteps.verifyGenericMsgForMandatoryFields(softAssert);
	   // verify objects of registration page.
		registrationSteps.passwordFieldValidations(softAssert, customer);
		softAssert.assertAll();
	}
	
	@TestRail(testCaseId = {75244, 75180})
	@FrameworkAnnotations(author = { "" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the residential registration page objects.")
	public void verifyPasswordMismatch() throws SQLException {
		log.info("To verify the tests with the below test case id's: "
				+ "C77192, C77193, C77083, C77084, C77085, C77086, C77091, C77093, C77285, C77087");
		log.info("Test Case execution for - verifyCustomerRegistrationPage - is Initiated");
		SoftAssert softAssert = new SoftAssert();
		registrationSteps = new RegistrationSteps(driver);
		Customer customer = SCPConfiguration.initCustomerConfig("John Doe","1");
		// Verify generic validation message for all the mandatory fields
		registrationSteps.verifyGenericMsgForMandatoryFields(softAssert);
	   // verify objects of registration page.
		registrationSteps.passwordMismatch(softAssert, customer);
		softAssert.assertAll();
	}
	
	@TestRail(testCaseId = {75150, 75158, 75159, 75196, 75202, 75172})
	@FrameworkAnnotations(author = { "" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the residential registration page objects.")
	public void verifyRegistrationTemplate() throws SQLException {
		log.info("To verify the tests with the below test case id's: "
				+ "C77192, C77193, C77083, C77084, C77085, C77086, C77091, C77093, C77285, C77087");
		log.info("Test Case execution for - verifyCustomerRegistrationPage - is Initiated");
		SoftAssert softAssert = new SoftAssert();
		registrationSteps = new RegistrationSteps(driver);
		Customer customer = SCPConfiguration.initCustomerConfig("John Doe","1");
		// Verify generic validation message for all the mandatory fields
		registrationSteps.verifyGenericMsgForMandatoryFields(softAssert);
	   // verify objects of registration page.
		registrationSteps.registrationTemplate(softAssert, customer);
		softAssert.assertAll();
	}
	@TestRail(testCaseId = {75232, 75248})
	@FrameworkAnnotations(author = { "" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the residential registration page objects.")
	public void verifyRegByInActiveAccount() throws SQLException {
		log.info("To verify the tests with the below test case id's: "
				+ "C77192, C77193, C77083, C77084, C77085, C77086, C77091, C77093, C77285, C77087");
		log.info("Test Case execution for - verifyCustomerRegistrationPage - is Initiated");
		SoftAssert softAssert = new SoftAssert();
		registrationSteps = new RegistrationSteps(driver);
		Customer customer = SCPConfiguration.initCustomerConfig("John Doe","1");
		// Verify generic validation message for all the mandatory fields
		registrationSteps.verifyGenericMsgForMandatoryFields(softAssert);
	   // verify objects of registration page.
		registrationSteps.regByInActiveAccount(softAssert, customer);
		softAssert.assertAll();
	}
	
	@TestRail(testCaseId = {75197})
	@FrameworkAnnotations(author = { "" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the residential registration page objects.")
	public void verifyTabOrderForRegForm() throws SQLException {
		log.info("To verify the tests with the below test case id's: "
				+ "C77192, C77193, C77083, C77084, C77085, C77086, C77091, C77093, C77285, C77087");
		log.info("Test Case execution for - verifyCustomerRegistrationPage - is Initiated");
		SoftAssert softAssert = new SoftAssert();
		registrationSteps = new RegistrationSteps(driver);
		Customer customer = SCPConfiguration.initCustomerConfig("John Doe","1");
		// Verify generic validation message for all the mandatory fields
		registrationSteps.verifyGenericMsgForMandatoryFields(softAssert);
	   // verify objects of registration page.
		registrationSteps.tabOrderForRegForm(softAssert, customer);
		softAssert.assertAll();
	}
	@TestRail(testCaseId = {75182})
	@FrameworkAnnotations(author = { "" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the residential registration page objects.")
	public void verifyFieldNotRetainValuesOnRegFail() throws SQLException {
		log.info("To verify the tests with the below test case id's: "
				+ "C77192, C77193, C77083, C77084, C77085, C77086, C77091, C77093, C77285, C77087");
		log.info("Test Case execution for - verifyCustomerRegistrationPage - is Initiated");
		SoftAssert softAssert = new SoftAssert();
		registrationSteps = new RegistrationSteps(driver);
		Customer customer = SCPConfiguration.initCustomerConfig("John Doe","1");
		// Verify generic validation message for all the mandatory fields
		registrationSteps.verifyGenericMsgForMandatoryFields(softAssert);
	   // verify objects of registration page.
		registrationSteps.fieldNotRetainValuesOnRegFail(softAssert, customer);
		softAssert.assertAll();
	}
	
	
	@TestRail(testCaseId = {102476, 102477, 102478, 102480})
	@FrameworkAnnotations(author = { "" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the residential registration page objects.")
	public void verifyResidentialRegistrationCommercialTemp() throws SQLException {
		log.info("To verify the tests with the below test case id's: "
				+ "C77192, C77193, C77083, C77084, C77085, C77086, C77091, C77093, C77285, C77087");
		log.info("Test Case execution for - verifyCustomerRegistrationPage - is Initiated");
		SoftAssert softAssert = new SoftAssert();
		registrationSteps = new RegistrationSteps(driver);
		Customer customer = SCPConfiguration.initCustomerConfig("John Doe","1");
		// Verify generic validation message for all the mandatory fields
		registrationSteps.verifyGenericMsgForMandatoryFields(softAssert);
	   // verify objects of registration page.
		registrationSteps.residentialRegistrationCommercialTemp(softAssert, customer);
		softAssert.assertAll();
	}
	
	@TestRail(testCaseId = {102479, 102481, 102483})
	@FrameworkAnnotations(author = { "" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the residential registration page objects.")
	public void verifyCommercialRegistrationResidentialTemp() throws SQLException {
		log.info("To verify the tests with the below test case id's: "
				+ "C77192, C77193, C77083, C77084, C77085, C77086, C77091, C77093, C77285, C77087");
		log.info("Test Case execution for - verifyCustomerRegistrationPage - is Initiated");
		SoftAssert softAssert = new SoftAssert();
		registrationSteps = new RegistrationSteps(driver);
		Customer customer = SCPConfiguration.initCustomerConfig("John Doe","1");
		// Verify generic validation message for all the mandatory fields
		registrationSteps.verifyGenericMsgForMandatoryFields(softAssert);
	   // verify objects of registration page.
		registrationSteps.commercialRegistrationResidentialTemp(softAssert, customer);
		softAssert.assertAll();
	}
	
	@TestRail(testCaseId = {107170,120090,107192,107195,108200,108204,108220,75215, 75217, 75220, 75229, 75230, 75234, 75241, 75093,
            75237, 75239, 75240, 75246, 106337, 106295, 105138, 105134,107170,120090})
	@FrameworkAnnotations(author = { "" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the residential registration page objects.")
	public void verifyMultipleAccountsAfterResidentialRegistration() throws SQLException {
		log.info("To verify the tests with the below test case id's: "
				+ "C77192, C77193, C77083, C77084, C77085, C77086, C77091, C77093, C77285, C77087");
		log.info("Test Case execution for - verifyCustomerRegistrationPage - is Initiated");
		SoftAssert softAssert = new SoftAssert();
		registrationSteps = new RegistrationSteps(driver);
		Customer customer = SCPConfiguration.initCustomerConfig("John Doe","1");
		// Verify generic validation message for all the mandatory fields
		registrationSteps.verifyGenericMsgForMandatoryFields(softAssert);
	   // verify objects of registration page.
		registrationSteps.multipleAccountsAfterResidentialRegistration(softAssert, customer);
		softAssert.assertAll();
	}
	
	@TestRail(testCaseId = {107170,120090,107192,107195,108200,108204,108220})
	@FrameworkAnnotations(author = { "" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the residential registration page objects.")
	public void verifyMultipleAccountsAfterCommercialRegistration() throws SQLException {
		log.info("To verify the tests with the below test case id's: "
				+ "C77192, C77193, C77083, C77084, C77085, C77086, C77091, C77093, C77285, C77087");
		log.info("Test Case execution for - verifyCustomerRegistrationPage - is Initiated");
		SoftAssert softAssert = new SoftAssert();
		registrationSteps = new RegistrationSteps(driver);
		Customer customer = SCPConfiguration.initCustomerConfig("John Doe","2");
		// Verify generic validation message for all the mandatory fields
		registrationSteps.verifyGenericMsgForMandatoryFields(softAssert);
	   // verify objects of registration page.
		registrationSteps.multipleAccountsAfterCommercialRegistration(softAssert, customer);
		softAssert.assertAll();
	}
	
	@TestRail(testCaseId = {98696,98697,133090,133091,133092,133093,133094,133096,98699,133098,133099,133100,98707,98710,98711,98714,98715,98716,98717,98725,133097,98708})
	@FrameworkAnnotations(author = { "" }, category = { CategoryType.SANITY })
	@Test(priority = 1, description = "To verify the residential registration page objects.")
	public void verifyRegMFA_Validations() throws SQLException {
		log.info("To verify the tests with the below test case id's: "
				+ "C77192, C77193, C77083, C77084, C77085, C77086, C77091, C77093, C77285, C77087");
		log.info("Test Case execution for - verifyCustomerRegistrationPage - is Initiated");
		SoftAssert softAssert = new SoftAssert();
		registrationSteps = new RegistrationSteps(driver);
		Customer customer = SCPConfiguration.initCustomerConfig("John Doe","2");
		// Verify generic validation message for all the mandatory fields
		registrationSteps.verifyGenericMsgForMandatoryFields(softAssert);
	   // verify objects of registration page.
		registrationSteps.RegMFA_Validations(softAssert, customer);
		softAssert.assertAll();
	}
	
	
}

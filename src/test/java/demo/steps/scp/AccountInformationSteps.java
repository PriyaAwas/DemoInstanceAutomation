package demo.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import sew.ai.config.SCPConfiguration;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.models.RegistrationConfig;
import sew.ai.models.User;
import demo.pageobjects.AccountInformationPage;
import sew.ai.steps.scp.HomeSteps;
import sew.ai.utils.PropertiesUtil;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class AccountInformationSteps extends AccountInformationPage {
	private static final Logger log = LogManager.getLogger(AccountInformationSteps.class);
	public static PropertiesUtil accontInformationTextProp;
	Map<String, Integer> registrationConfig = new HashMap<>();
	public static Map<String, RegistrationConfig> getRegistrationConfig = new LinkedHashMap<>();
	User user = SCPConfiguration.user;

	public AccountInformationSteps(WebDriver driver) {
		super(driver);
		accontInformationTextProp = new PropertiesUtil(
				FilePaths.SCP_TEXT_PROPERTIES + Constants.ACCOUNT_PROFILE_TXT_FILENAME);
	}

	public Boolean isAccountInformationPage(String url, String title) {
		Boolean isaccountInformationPage = false;
		log.info("Checking that the current page is account information page.");
		if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
			isaccountInformationPage = true;
		log.info("The current page is account information page {}: " + isaccountInformationPage);
		return isaccountInformationPage;
	}

	public void linkAccountUIAndValidation(SoftAssert sAssert) {
		log.info("To verify the UI and validation message of Link Account popup "
				+ "fields for Residential and Commercial Accounts.");
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToAccountInformation();

		Assert.assertTrue(
				isAccountInformationPage(accontInformationTextProp.getPropValue("expectedAccountInformationPageUrl"),
						accontInformationTextProp.getPropValue("expectedAccountInformationPageTitle")));
		clickBtnaccount();

		clickRdbLinkAccResidentialPopup();

		sAssert.assertEquals(getAddlblZipCodePopup(), accontInformationTextProp.getPropValue("textLblZipCodePopupApp"));
		sAssert.assertTrue(isAddlblZipCodePopupVisible(), "Add label zip code popup is not visible");

		sAssert.assertEquals(getAddAccountPopup(),
				accontInformationTextProp.getPropValue("textLblAccountNumberPopupApp"),
				"account number label is not displayed");
		sAssert.assertTrue(istxtAddAccountPopupVisible(), "Add account popup test is not visible");

		sAssert.assertEquals(getLblStreetAddPopupApp(),
				accontInformationTextProp.getPropValue("textLblStreetAddrPopupApp"), "Street add popup is not matched");
		sAssert.assertTrue(isTxtStreetAddPopupAppVisible(), "Street address text not matched");

		sAssert.assertEquals(getAddlblMeterNumberPopup(),
				accontInformationTextProp.getPropValue("textLblMeterNumberPopupApp"));
		sAssert.assertTrue(isAddlblMeterNumberPopupVisible(), "Add label meter number popup is not visible");

		Assert.assertEquals(getBtnLinkAccPopupSubmit("value"),
				accontInformationTextProp.getPropValue("textBtnLinkAccountPopupSubmitApp"),
				"link account submit button value is not matched");

		Assert.assertEquals(getBtnLinkAccPopupCancel("value"),
				accontInformationTextProp.getPropValue("textBtnCancelLinkAccountApp"),
				"link account cancel button is not visible");

		sAssert.assertEquals(getAccountmodal(),
				accontInformationTextProp.getPropValue("textLblLinkAccountPopupHeadingApp"),
				"link account popup heading is not displayed");
		sAssert.assertEquals(getRdbLinkAccResidentialPopup(),
				accontInformationTextProp.getPropValue("textRbtnLinkAccountResidentialPopupApp"),
				"link account residential popup is not displayed");

		Assert.assertEquals(getBtnLinkAccPopupSubmit("value"),
				accontInformationTextProp.getPropValue("textBtnLinkAccountPopupSubmitApp"),
				"link account submit button value is not matched");
		Assert.assertEquals(getBtnLinkAccPopupCancel("value"),
				accontInformationTextProp.getPropValue("textBtnCancelLinkAccountApp"),
				"link account cancel button is not visible");

		sAssert.assertTrue(isbtnCloseCrossPopupVisible(), "close Cross popup button is not visible");
		sAssert.assertTrue(isRdbLinkAccResidentialPopupVisible(), "residential Cross popup button is not visible");
		sAssert.assertTrue(islnkRdbLinkAccCommercialPopupVisible(), "commerical Cross popup button is not visible");
		sAssert.assertTrue(isclickbtnLinkAccPopupSubmitVisible(), "link account popup submit button is not visible");
		sAssert.assertTrue(isclickBtnCancelLinkAccountVisible(), "link account popup submit button is not visible");
	}

	public void verifyAccountInfoPageValidation(SoftAssert sAssert) {
		log.info("To verify the UI and validation message of Link Account popup "
				+ "fields for Residential and Commercial Accounts.");
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToAccountInformation();

		ExtentLogger.logInfo("Verify Text Account Linkage message");
		sAssert.assertTrue(isAcctlnkdMsgVisible(),
				"Account Linkage Message and Link Account hyper link is not visible");

		ExtentLogger.logInfo("Verify Text My Home");
		sAssert.assertTrue(isMyhomelblvisible(), "My Home tile title is not visible");

		ExtentLogger.logInfo("Verify Text Service Addresses Label");
		sAssert.assertTrue(isSrvAddTxtVisible(), "Service Address is not visible");

		ExtentLogger.logInfo("Verify Text Mailing Addresses Label");
		sAssert.assertTrue(isMlgAddTxtvisible(), "Mailing Address is not visible");

		ExtentLogger.logInfo("Verify Text Role Label");
		sAssert.assertTrue(isRoleTxtvisible(), "Role is not visible");

		ExtentLogger.logInfo("Verify Text Bill Type Label");
		sAssert.assertTrue(isBillTypeTxtvisible(), "Bill Type is not visible");

		ExtentLogger.logInfo("Verify Text Account Information side Panel Label");
		sAssert.assertTrue(isAcctInfoBannerVisible(), "Account information Page is not visible");

		ExtentLogger.logInfo("Verify Text Default Label");
		sAssert.assertTrue(isDefaultlblvisible(), "Default is not visible");

		ExtentLogger.logInfo("Verify Text FAQ side Panel Label");
		sAssert.assertTrue(isFAQBannerVisible(), "FAQ is not visible");

		ExtentLogger.logInfo("Verify Text Contact Us Info side Panel Label");
		sAssert.assertTrue(isContactUsVisible(), "Contact Us Tile is not visible");

	}

	public void verifyLinkAccFormFieldValidation(SoftAssert softAssert) {

		log.info("To verify the UI and validation message of Link Account popup "
				+ "fields for Residential and Commercial Accounts.");
		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToAccountInformation();
		Assert.assertTrue(
				isAccountInformationPage(accontInformationTextProp.getPropValue("expectedAccountInformationPageUrl"),
					accontInformationTextProp.getPropValue("expectedAccountInformationPageTitle")));
		clickBtnaccount();
		clickRdbLinkAccResidentialPopup();
		clickbtnLinkAccPopupSubmit();
		pause(3000);
		isErrormsgdisplayed();
		softAssert.assertEquals(getLblErrorMsg(), accontInformationTextProp.getPropValue("txtMsgBlankInfoApp"),
				"Mandatory fields error msg does not match");

	}

	public void verifyEditNickName(SoftAssert softAssert) {

		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToAccountInformation();

		ExtentLogger.logInfo("Click three dot");
		clickThreeDotIcon();

		ExtentLogger.logInfo("Click Edit Nick Name Option");
		clickEditNickNameOption();

		ExtentLogger.logInfo("Verify edit nickname popup");
		softAssert.assertTrue(isNicknameTitletextVisible(), "Edit Nick name did not displayed");

		ExtentLogger.logInfo("Enter message in the message body.");
		isAccountNickNameTxtBoxMessageBodyVisible();
		populateTxtMessageBody("Home Test");
		elementTextMessageBody().sendKeys(Keys.TAB);

		ExtentLogger.logInfo("Click save button");
		clickNickNamebtnsaveBtn();
		pause(3000);
	}

	public void verifyEditNickNameBlankValidation(SoftAssert softAssert) {

		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToAccountInformation();

		ExtentLogger.logInfo("Click three dot");
		clickThreeDotIcon();

		ExtentLogger.logInfo("Click Edit Nick Name Option");
		clickEditNickNameOption();

		ExtentLogger.logInfo("Click save button");
		clickNickNamebtnsaveBtn();

	}

	public void unlinkDefaultAcc(SoftAssert softAssert) {

		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToAccountInformation();

		ExtentLogger.logInfo("Click three dot");
		clickThreeDotIcon();

		clickUnlinkAcc();

		clickCtnBtn();

		pause(3000);
		isDftErrormsgdisplayed();
		softAssert.assertEquals(getDftErrorMsg(),
				accontInformationTextProp.getPropValue("txtMsgForDeleteDefaultAccountApp"),
				"Deafult Account Unlink Message does not match");

	}

	public void editBillType(SoftAssert softAssert) {

		HomeSteps homeSteps = new HomeSteps(driver);
		homeSteps.navigateToAccountInformation();

		ExtentLogger.logInfo("Click three dot");
		clickThreeDotIcon();

		clickEditBillType();

		clickBothrdbtn();

		clickCancelbtn();

	}

}
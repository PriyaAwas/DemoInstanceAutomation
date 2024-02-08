package demo.steps.scp;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import demo.pageobjects.MyProfilePage;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.utils.*;
import java.io.IOException;
import java.sql.SQLException;

public class MyProfileSteps extends MyProfilePage {
	public static PropertiesUtil myprofileTextProp;

	public MyProfileSteps(WebDriver driver) {
		super(driver);
		myprofileTextProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.MYPROFILE_TXT_FILENAME);
	}

	public void verifyNegativeValidationForMyProfile(SoftAssert softAssert) throws SQLException {

		//Click email edit Icon
		clickPrimaryEmailIdEditButton();
		//Clear primary email textbox
		clearPrimaryEmailTextBox();
	    //Save primary email textbox
		clickPrimaryEmailIdSaveButton();
		isMandatoryToastMessageDisplayed();
		softAssert.assertEquals(getMandatoryToastMessage(), myprofileTextProp.getPropValue("mandatormailmsg"),
				"Warning message do not match");
		//("Click username edit Icon.");
		clickUserNameEditButton();
		//("Clear username textbox");
		clearUserNameTextField();
		//("Save username textbox");
		clickChangeUserNameSaveButton();
		isMandatoryToastMessageDisplayed();
		softAssert.assertEquals(getMandatoryToastMessage(), myprofileTextProp.getPropValue("mandatormailmsg"),
				"Warning message do not match");
		//("Click primary phone edit Icon.");
		clickPrimaryPhoneNumberEditButton();
		//("Clear primary phone textbox");
		clearPrimaryPhoneInputField();
		//("Save primary phone textbox");
		clickPrimaryPhoneNumberSaveButton();
		isPrimaryPhoneErrorMssgDisplayed();
		softAssert.assertEquals(getSecondaryPhoneToastmsgLabel(), myprofileTextProp.getPropValue("PrimayphoneToastMsg"),
				"Warning message do not match");
	   //("Click password edit Icon.");
		clickPasswordEditButton();
		//("Entering password in the password field.");
		enterPasswordInExistingPassField("scmuser@2022");
		//("Entering password in the password field.");
		enterPasswordInNewPassField("scmuser@2020");
		//("Entering password in the password field.");
		enterPasswordInVerifyNewPassField("scmuser@2021");
		//("Submit password button");
		clickSubmitPassButton();
		isDifferentPassErrorMssgDisplayed();
		softAssert.assertEquals(getPwdToastmsgLabel(), myprofileTextProp.getPropValue("differentpassmssg"),
				"Warning message do not match");
		ExtentLogger.logInfo("Test Case execution for - verifyNegativeValidationForMyProfile - is Completed");
	}

	public void verifyMyProfileInformationPageUI(SoftAssert softAssert) throws SQLException {
		pause(5000);

		ExtentLogger.logInfo("Verify My Profile Information Page UI");
		softAssert.assertTrue(isTimeZoneVisible(), "    is not visible");
		softAssert.assertTrue(isTimeZoneValueVisible(), "    is not visible");
		softAssert.assertTrue(isTimeZoneEditButtonVisible(), "    is not visible");
		softAssert.assertTrue(isSecondaryPhoneNumberVisible(), "    is not visible");
		softAssert.assertTrue(isPrimaryEmailVisible(), "    is not visible");
		softAssert.assertTrue(isProfilePasswordVisible(), "    is not visible");
		softAssert.assertTrue(isPrimaryPhoneNumberEditButtonVisible(), "    is not visible");
		softAssert.assertTrue(isSecondaryPhoneNumberEditButtonVisible(), "    is not visible");
		softAssert.assertTrue(isPrimaryEmailIdEditButtonVisible(), "    is not visible");
		softAssert.assertTrue(isSecondaryEmailIdEditButtonVisible(), "    is not visible");
		softAssert.assertTrue(isUserNameVisible(), "    is not visible");
		softAssert.assertTrue(isMyAccountPageBannerVisible(), "    is not visible");
		ExtentLogger.logInfo("Test Case execution for - verifyMyProfileInformationPageUI - is Completed");
	}

	public void verifyDownloadMyProfilePersonalData(SoftAssert softAssert) throws IOException, SQLException {

		ExtentLogger.logInfo("Verify My Profile Information Page UI for download data");
		softAssert.assertTrue(isDownloadMyDataVisible(), "Download My data link is not visible on My Profile Page.");
		softAssert.assertEquals(getDownloadMyDataLabel(), myprofileTextProp.getPropValue("downloadMyDatalbl"));
		clickDownloadMyData();

		softAssert.assertEquals(getDownloadMyDataPopUpLabel(), myprofileTextProp.getPropValue("downloadmydatapoplbl"));
		softAssert.assertEquals(getDownloadMydataVerificationLabel(),
				myprofileTextProp.getPropValue("DownloadMydataVerifilabel"));
		softAssert.assertEquals(getPwdForDownloadMyData1Label(),
				myprofileTextProp.getPropValue("pwdDownloadmyDatalbl"));
		softAssert.assertTrue(isSubmitDownloadEnable(),
				"Submit Password Button is not Enable for Download My Data from My profile page");
		ExtentLogger.logInfo("Download my profile data");
		clickDownloadMyDataSubmitbtn();
		ExtentLogger.logInfo("Test Case execution for - verifyDownloadMyProfilePersonalData - is Completed");
	}
}
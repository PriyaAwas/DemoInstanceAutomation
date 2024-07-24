package demo.steps.scp;

import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import demo.pageobjects.MyProfilePage;
import sew.ai.config.Configuration;
import sew.ai.driver.DriverFactory;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.utils.*;

import static sew.ai.config.SCPConfiguration.user;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyProfileSteps extends MyProfilePage {
	public static PropertiesUtil myprofileTextProp;
	public static PropertiesUtil marketingPreferenceTextProp;

	ArrayList<String> alProfileDataName = new ArrayList<>();

	public MyProfileSteps(WebDriver driver) {
		super(driver);
		myprofileTextProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.MYPROFILE_TXT_FILENAME);

		marketingPreferenceTextProp = new PropertiesUtil(
				FilePaths.SCP_TEXT_PROPERTIES + Constants.MARKETING_PREF_TXT_FILENAME);
	}

	public void verifyMyProfileInformationPageUI(SoftAssert softAssert) {
		ExtentLogger.logInfo("Verify My Profile Information Page UI");

		pause(5000);
		List<WebElement> profileDataName = getProfileDataName();

		for (int profileDataNamecount = 0; profileDataNamecount < profileDataName.size(); profileDataNamecount++) {
			alProfileDataName.add(profileDataName.get(profileDataNamecount).getText());
		}
		Collections.sort(alProfileDataName);
		ArrayList<String> al = new ArrayList<>();
		al.add("NAME");
		al.add("USERNAME");
		al.add("PASSWORD");
		al.add("PRIMARY PHONE");
		al.add("SECONDARY PHONE");
		al.add("PRIMARY EMAIL");
		al.add("SECONDARY EMAIL");
		Collections.sort(al);
		softAssert.assertEquals(alProfileDataName, al, "Profile Data Name label is not matching");
		softAssert.assertTrue(isTimeZoneVisible(), "    is not visible");
		softAssert.assertTrue(isTimeZoneValueVisible(), "    is not visible");
		softAssert.assertTrue(isTimeZoneEditButtonVisible(), "    is not visible");
		softAssert.assertTrue(isSecondaryPhoneNumberVisible(), "    is not visible");
		softAssert.assertTrue(isPrimaryEmailVisible(), "    is not visible");
		softAssert.assertTrue(getProfilePasswordLabel().contains("*"), "Password is not Matching");
		softAssert.assertTrue(isProfilePasswordVisible(), "    is not visible");
		softAssert.assertTrue(isPrimaryPhoneNumberEditButtonVisible(), "    is not visible");
		softAssert.assertTrue(isSecondaryPhoneNumberEditButtonVisible(), "    is not visible");
		softAssert.assertTrue(isPrimaryEmailIdEditButtonVisible(), "    is not visible");
		softAssert.assertTrue(isSecondaryEmailIdEditButtonVisible(), "    is not visible");
		softAssert.assertTrue(isUserNameVisible(), "    is not visible");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 500)");
		softAssert.assertTrue(isMyAccountPageBannerVisible(), "My Account Page Banner is not Visbile");
		ExtentLogger.logInfo("Test Case execution for - verifyMyProfileInformationPageUI - is Completed");
	}

	public void verifyPersonalInfoSection(SoftAssert softAssert) {
		// ******************Customer Personal Information***********
		// Verify Personal Information section label text and controls
		Assert.assertTrue(isMyProfilePage(myprofileTextProp.getPropValue("myAccountPageUrl"),
				myprofileTextProp.getPropValue("myAccountPageTitle")));
		softAssert.assertEquals(getMyProfilePageHeaderLabel(), myprofileTextProp.getPropValue("MyProfilePageHeader"),
				"My Profile Page Title is not Matching");
		softAssert.assertEquals(getLblNameLabel(), myprofileTextProp.getPropValue("name"));
		softAssert.assertEquals(getPasswordLabel(), myprofileTextProp.getPropValue("pass"));
		softAssert.assertEquals(getPrimaryContactNumLabel(), myprofileTextProp.getPropValue("primaryNumLabel"));
		softAssert.assertEquals(getAltEmailIdLabel(), myprofileTextProp.getPropValue("altEmailId"));
		softAssert.assertEquals(getValueNameLabel(), Configuration.toString("customerName"),
				"User Value Name is not Matching with Pro");
		// Verify Primary Email Address is mandatory but Alternate Email Address is not

		clickPrimaryEmailIdEditButton();
		String val1 = getPrimaryEmailAttribute("mandatory");
		softAssert.assertTrue(val1.equals("1"), "Primary Email Address is not mandatory");
		clickSecondaryEmailIdEditButton();
		String val2 = getAlternateEmailAttributewithoutTrim("mandatory");
		softAssert.assertTrue(val2 == null, "Alternate Email Address is mandatory");

		// Verify Primary Email Address is mandatory but Alternate Email Address is not
		clickPrimaryEmailIdEditButton();
		clearPrimaryEmailTextBox();
		clickPrimaryEmailIdSaveButton();
		String mandatorymsg = getMandatoryToastMessage();
		Assert.assertEquals(mandatorymsg, myprofileTextProp.getPropValue("mandatormailmsg").trim());
	}

	public void verifyValidationsProfileInformation(SoftAssert softAssert) {
		// Primary Contact Type and Input Field validation when Save without any Input
		clickPrimaryPhoneNumberEditButton();
		// Contact Type validation
		ArrayList<String> actcontacttype = new ArrayList<>();
		List<WebElement> contacttypeWebElement = getListContactType();
		for (int contacttypeindex = 0; contacttypeindex < contacttypeWebElement.size(); contacttypeindex++) {
			String contactType = contacttypeWebElement.get(contacttypeindex).getText();
			actcontacttype.add(contactType);
		}
		Collections.sort(actcontacttype);
		String expContactType = myprofileTextProp.getPropValue("contactType");
		ArrayList<String> expContactType2 = new ArrayList<>();
		String[] expContactType1 = expContactType.split(",");
		for (int contacttypeindex1 = 0; contacttypeindex1 < expContactType1.length; contacttypeindex1++) {
			expContactType2.add(expContactType1[contacttypeindex1]);
		}
		Collections.sort(expContactType2);
		softAssert.assertEquals(actcontacttype, expContactType2, "Contact Type is not Matching");
		// Primary Email Id Toaster msg validation
		softAssert.assertEquals(getPrimaryEmailIdLabel(), Configuration.toString("demoEmailId"),
				"Primary Email Id is not Matching");

		// Update P EMail id with Empty Input Field

		clickPrimaryEmailIdEditButton();
		clearPrimaryEmailTextBox();
		clickPrimaryEmailIdSaveButton();
		softAssert.assertEquals(getMandatoryToastMessage(), myprofileTextProp.getPropValue("mandatormailmsg"));
		DriverFactory.refreshBrowser();
		pause(5000);
		// update P Email without Pwd
		clickPrimaryEmailIdEditButton();

		String pEmailid = Configuration.toString("demoEmailId");
		String newPEmail = RandomUtil.generateRandomString(3, RandomUtil.Mode.ALPHA) + pEmailid;
		populatePrimaryEmailId(newPEmail);
		clickPrimaryEmailIdSaveButton();
		softAssert.assertEquals(getPwdToastmsgLabel(), myprofileTextProp.getPropValue("pwdToastMsg"));
		DriverFactory.refreshBrowser();

		// Update P Email id With Wrong Pwd
		clickPrimaryEmailIdEditButton();
		clearPrimaryEmailTextBox();
		populatePrimaryEmailId(newPEmail);
		populatePwdForUpdateEmailid(RandomUtil.generateRandomString(8, RandomUtil.Mode.ALPHANUMERIC));
		clickPrimaryEmailIdSaveButton();
		softAssert.assertEquals(getMandatoryToastMessage(), myprofileTextProp.getPropValue("primaryEmailInvalidToast"));
		// Update P EMail ID with Empty primary input field and Correct PWD
		DriverFactory.refreshBrowser();
		pause(2000);
		clickPrimaryEmailIdEditButton();
		clearPrimaryEmailTextBox();
		populatePwdForUpdateEmailid(Configuration.toString("password"));
		clickPrimaryEmailIdSaveButton();
		String mandatoryToastMsg = getEmailToastMessage();
		System.out.println(mandatoryToastMsg);
		softAssert.assertEquals(mandatoryToastMsg, myprofileTextProp.getPropValue("primaryEmailToast"),
				"Mandatory Toast Msg is not Matching.");
	}

	public void verifyContactTypeProfileInformation(SoftAssert softAssert) {
		// Verify Primary Contact type available options
		clickPrimaryPhoneNumberEditButton();
		List<WebElement> PrimaryContactType = getAllPrimaryPOptionsInListBox();
		softAssert.assertTrue(PrimaryContactType.size() == 5,
				"Some Contact Type is either add or Remove from existing");
		softAssert.assertTrue(PrimaryContactType.get(0).getText().equals("--Select--"),
				"Contact type at index 0 is not Matching");
		softAssert.assertTrue(PrimaryContactType.get(1).getText().equals("Other"),
				"Contact type at index 1 is not Matching");
		softAssert.assertTrue(PrimaryContactType.get(2).getText().equals("Mobile"),
				"Contact type at index 2 is not Matching");
		softAssert.assertTrue(PrimaryContactType.get(2).getText().equals("Mobile"),
				"Contact type at index 2 is not Matching");
		softAssert.assertTrue(PrimaryContactType.get(3).getText().equals("Work"),
				"Contact type at index 3 is not Matching");
		softAssert.assertTrue(PrimaryContactType.get(4).getText().equals("Emergency"),
				"Contact type at index 4 is not Matching");
		// Verify Secondary Contact type available options
		DriverFactory.refreshBrowser();
		// Verify Secondary Phone Contact Type Validation using For Loop
		clickSecondaryPhoneNumberEditButton();
		List<WebElement> SecContactType = getAllSecondaryPOptionsInListBox();
		softAssert.assertTrue(SecContactType.size() == 5, "Some Contact Type is either add or Remove from existing");
		softAssert.assertTrue(SecContactType.get(0).getText().equals("--Select--"),
				"Contact type1 at index 0 is not Matching");
		softAssert.assertTrue(SecContactType.get(1).getText().equals("Landline"),
				"Contact type1 at index 1 is not Matching");
		softAssert.assertTrue(SecContactType.get(2).getText().equals("Mobile"),
				"Contact type1 at index 2 is not Matching");
		softAssert.assertTrue(SecContactType.get(3).getText().equals("Work"),
				"Contact type1 at index 3 is not Matching");
		softAssert.assertTrue(SecContactType.get(4).getText().equals("Emergency"),
				"Contact type1 at index 4 is not matching");
	}

	public void validateChangeUsername(SoftAssert softAssert) {
		// Validation Change UserName
		Assert.assertTrue(isMyProfilePage(myprofileTextProp.getPropValue("myAccountPageUrl"),
				myprofileTextProp.getPropValue("myAccountPageTitle")));
		softAssert.assertTrue(isUserNameVisible(), "user Name Label is not Visible");
		softAssert.assertTrue(isEditUserNameVisible(), "User Name Edit label is not Visible ");
		softAssert.assertTrue(isUserNameEditButtonVisible(), "User Name Edit Kebab Meau is not Visible");
		clickUserNameEditButton();
		softAssert.assertTrue(isUserNameInputFieldVisible(), "User Name Input Field is not Visible");
		softAssert.assertTrue(isPwdForUpdateUserNameVisible(), "User Name PWD input Field is not Visible");
		softAssert.assertTrue(isChangeUserNameSaveBtnVisible(), "Save Button for Chnage User Name is not Visible");
		softAssert.assertTrue(isChangeUserNameCancelBtnVisible(), "Cancel Button for Change User Name is not Visible");
		softAssert.assertTrue(isUserTipIconVisible(), "User Tip Icon is not Visible");
		clickChangeUserNameCancelButton();

		clickUserNameEditButton();
		populatePwdForUpdateUserName(Configuration.toString("password"));
		clickChangeUserNameSaveButton();
		softAssert.assertEquals(getUserNameToastMessage(), myprofileTextProp.getPropValue("userNameNotSameMsg"));
		clearUserNameTextField();
		clickChangeUserNameSaveButton();
		softAssert.assertEquals(getUserNameToastMessage(), myprofileTextProp.getPropValue("userNameValidation"));
		pause(500);
		clearUserNameTextField();
		clearPwdForChangeUserNameEditField();
		clickChangeUserNameSaveButton();
		softAssert.assertEquals(getMandatoryToastMessage(), myprofileTextProp.getPropValue("mandatormailmsg"));
		softAssert.assertTrue(getMandatoryToastMessage().contains(myprofileTextProp.getPropValue("mandatormailmsg")),
				"Mandatory Msg is not matching.");
		String username = Configuration.toString("userName");

		String newUserName = username + "1";
		clearUserNameTextField();
		populateUserName(newUserName);
		clickChangeUserNameSaveButton();
		softAssert.assertEquals(getPwdToastmsgLabel(), myprofileTextProp.getPropValue("pwdToastMsg"),
				"Password is not entered.");
		clearUserNameTextField();
		populatePwdForUpdateUserName(RandomUtil.generateRandomString(8, RandomUtil.Mode.ALPHANUMERIC));
		clickChangeUserNameSaveButton();
		softAssert.assertEquals(getUserNameToastMessage(), myprofileTextProp.getPropValue("userNameValidation"));

		// Update new User Name
		DriverFactory.refreshBrowser();
		clickUserNameEditButton();
		populateUserName(newUserName);
		populatePwdForUpdateUserName(RandomUtil.generateRandomString(8, RandomUtil.Mode.ALPHANUMERIC));
		clickChangeUserNameSaveButton();
		softAssert.assertEquals(getMandatoryToastMessage(), myprofileTextProp.getPropValue("wrongPassValidateMsg"));
		pause(1000);
		softAssert.assertEquals(getEditUserNameLabel(), username, "UserName is not Matching.");
		DriverFactory.refreshBrowser();
		// Restore the previous UserName
		pause(3000);
		ExtentLogger.logPass("Test Case execution for - verifyChangeUsername - is Completed");
	}

	public void verifyChangeUsernameAcceptanceCriteria(SoftAssert softAssert) {
		Assert.assertTrue(isMyProfilePage(myprofileTextProp.getPropValue("myAccountPageUrl"),
				myprofileTextProp.getPropValue("myAccountPageTitle")));

		String username = Configuration.toString("userName");

		softAssert.assertTrue(isUserNameVisible(), "user Name Label is not Visible");
		softAssert.assertTrue(isEditUserNameVisible(), "User Name Edit label is not Visible ");
		softAssert.assertTrue(isUserNameEditButtonVisible(), "User Name Edit Kebab Meau is not Visible");
		softAssert.assertEquals(getUserNameLabel(), myprofileTextProp.getPropValue("UserNameLabel"),
				"User name label is not matching");
		softAssert.assertEquals(getEditUserNameLabel(), username, "User Name is not Matching");
		clickUserNameEditButton();

		String actMaxLengthOfField = getTxtBoxUserNameMaxLength();
		String actMinLengthOfField = getTxtBoxUserNameMinLength();

		String expMaxLengthOfField = "30";
		String expMinLengthOfField = "5";

		softAssert.assertEquals(getTxtBoxChangePassMaxLength(), "32", "Max length of User Name is not Matching");

		softAssert.assertEquals(actMaxLengthOfField, expMaxLengthOfField, "Max length of User Name is not Matching");
		softAssert.assertEquals(actMinLengthOfField, expMinLengthOfField, "Min length of user name is not matching");
	}

	public void verifyDownloadMyProfilePersonalData(SoftAssert softAssert) throws IOException {

		ExtentLogger.logInfo("Verify My Profile Information Page UI for download data");
		softAssert.assertTrue(isDownloadMyDataVisible(), "Download My data link is not visible on My Profile Page.");
		softAssert.assertEquals(getDownloadMyDataLabel(), myprofileTextProp.getPropValue("downloadMyDatalbl"));
		clickDownloadMyData();

		softAssert.assertEquals(getDownloadMydataTxt(), myprofileTextProp.getPropValue("downloadmydatapoplbl"));

		softAssert.assertEquals(getDownloadMydataVerificationLabel(),
				myprofileTextProp.getPropValue("DownloadMydataVerifilabel"));

		softAssert.assertEquals(getPwdForDownloadMyData1Label(),
				myprofileTextProp.getPropValue("pwdDownloadmyDatalbl"));

		softAssert.assertTrue(isSubmitDownloadEnable(),
				"Submit Password Button is not Enable for Download My Data from My profile page");
		softAssert.assertEquals(getHideShowEyelbl(), myprofileTextProp.getPropValue("hideShowEye"));
		clickHideShowEys();
		softAssert.assertEquals(getHideShowEyelbl(), myprofileTextProp.getPropValue("unhideShowEye"));

		// Download My Data With Invalid password
		populatePwdForDownloadMyData(RandomUtil.generateRandomString(8, RandomUtil.Mode.ALPHANUMERIC));
		clickDownloadMyDataSubmitbtn();
		softAssert.assertEquals(getInvalidPwdMandatoryFieldsLabel(),
				myprofileTextProp.getPropValue("invalidPwdMandatorymsg"), "invalid pwd toaster msg is not matching.");

		// Download My Data With valid password
		DriverFactory.refreshBrowser();
		pause(2000);
		// Delete File from Download Folder

		FileUtil.deleteFile(FilePaths.DOWNLOAD_FOLDER_PATH);
		pause(5000);
		// Download My Personal Data Process
		clickDownloadMyData();
		populatePwdForDownloadMyData(Configuration.toString("password"));
		clickDownloadMyDataSubmitbtn();

		// Repeate the Steps Again

		DriverFactory.refreshBrowser();
		pause(2000);
		FileUtil.deleteFile(FilePaths.DOWNLOAD_FOLDER_PATH);
		pause(5000);
		clickDownloadMyData();
		populatePwdForDownloadMyData(Configuration.toString("password"));
		clickDownloadMyDataSubmitbtn();
		ExtentLogger.logInfo("DownLoad My Profile execution is completed ");
		pause(5000);

	}

	public void verifyDownloadMyDataPersonalInfoValidation(SoftAssert softAssert, String sheetname) throws IOException {
		// Load Download File on File Class
		File files = new File(FilePaths.DOWNLOAD_FOLDER_PATH);

		// TODo ExcelUtil method for load Excel with the help of ApachePoi
		String downloadmydataFilePath = FilePaths.DOWNLOAD_FOLDER_PATH + Constants.DOWNLOAD_MY_DATA_EXCEL_FILENAME;
		pause(5000);
		ExcelUtils.openExcelFile(downloadmydataFilePath);
		Sheet sheet = ExcelUtils.getSheetName(0);
		int totlaRow = ExcelUtils.getRowCount(sheet);
		int totalCell = sheet.getRow(0).getLastCellNum();

		// Validation Column Name with Db and Excel File-- properties file to split for
		// loop
		String colname = myprofileTextProp.getPropValue("downloadMyDataPersonalInfoColumnName");
		String[] colname1 = colname.split(",");
		// Validation Personal inforamtion Column Name in Excel from Properties File
		// Hard Coded column name
		for (int colname2 = 0; colname2 < colname1.length; colname2++) {
			String exepCol = colname1[colname2];
			String actColName = sheet.getRow(0).getCell(colname2).getStringCellValue();
			softAssert.assertEquals(actColName, exepCol, "Column Name of the sheet is not matching as per order.");
		}
		// Validation the excel Data value for Personal Information
		try {
			String actUserName = ExcelUtils.getCellValue(sheet, 1, 0);
			softAssert.assertEquals(actUserName, user.getUserName(),
					"Username is not Matching in DMD PersonalInfo Sheet from DB");
			String actFirstName = ExcelUtils.getCellValue(sheet, 1, 1);
			softAssert.assertEquals(actFirstName, user.getFirstName(),
					"User's First Name is not matching in DMD PersonalInfo Sheet from DB");
			String actLastName = ExcelUtils.getCellValue(sheet, 1, 3);
			softAssert.assertEquals(actLastName, user.getLastName(),
					"User's Last Name is not matching in DMD PersonalInfo Sheet from DB");
			String actEmailId = ExcelUtils.getCellValue(sheet, 1, 4);
			softAssert.assertEquals(actEmailId, user.getEmailId(),
					"User's Primary Email id is not matching in DMD PersonalInfo Sheet from DB");
			String actMobilePhone = ExcelUtils.getCellValue(sheet, 1, 5);
			softAssert.assertEquals(actMobilePhone, user.getMobilePhone(),
					"User's Primary Phone no is not matching in DMD PersonalInfo Sheet from DB");
			String actAlterEmail = ExcelUtils.getCellValue(sheet, 1, 7);
			softAssert.assertEquals(actAlterEmail, user.getAlternateEmailID(),
					"User's Secondary Email id  is not matching in DMD PersonalInfo Sheet from DB");
			String actHomePhone = ExcelUtils.getCellValue(sheet, 1, 8);
			softAssert.assertEquals(actHomePhone, user.getHomePhone(),
					"User's Secondary Phone no is not matching in DMD PersonalInfo Sheet from DB");
			String actMobilePType = ExcelUtils.getCellValue(sheet, 1, 6);
			// softAssert.assertEquals(actMobilePType, user.getMobilePhoneType(),"User's
			// Primary Phone Type is not matching in DMD PersonalInfo Sheet from DB");
			String actHomePType = ExcelUtils.getCellValue(sheet, 1, 9);
			// softAssert.assertEquals(actHomePType, user.getHomePhoneType(),"User's
			// Secondary Phone Type is not matching in DMD PersonalInfo Sheet from DB");
			String actMiddleNmae = ExcelUtils.getCellValue(sheet, 1, 2);
			softAssert.assertEquals(actMiddleNmae, user.getMiddleName(),
					"User's Middle Name is not matching in DMD PersonalInfo Sheet from DB");
		} catch (Exception e) {
			System.out.println(
					"CellString Value getting blank from Excel , It should have either some value or Null String ");
		}
		ExcelUtils.closeConnectionWithExcel();

	}

	public void verifyNegativeValidationForMyProfile(SoftAssert softAssert)   {

		// Click email edit Icon
		clickPrimaryEmailIdEditButton();
		// Clear primary email textbox
		clearPrimaryEmailTextBox();
		// Save primary email textbox
		clickPrimaryEmailIdSaveButton();
		isMandatoryToastMessageDisplayed();
		softAssert.assertEquals(getMandatoryToastMessage(), myprofileTextProp.getPropValue("mandatormailmsg"),
				"Warning message do not match");
		// ("Click username edit Icon.");
		clickUserNameEditButton();
		// ("Clear username textbox");
		clearUserNameTextField();
		// ("Save username textbox");
		clickChangeUserNameSaveButton();
		isMandatoryToastMessageDisplayed();
		softAssert.assertEquals(getMandatoryToastMessage(), myprofileTextProp.getPropValue("mandatormailmsg"),
				"Warning message do not match");
		// ("Click primary phone edit Icon.");
		clickPrimaryPhoneNumberEditButton();
		// ("Clear primary phone textbox");
		clearPrimaryPhoneInputField();
		// ("Save primary phone textbox");
		clickPrimaryPhoneNumberSaveButton();
		isPrimaryPhoneErrorMssgDisplayed();
		softAssert.assertEquals(getSecondaryPhoneToastmsgLabel(), myprofileTextProp.getPropValue("PrimayphoneToastMsg"),
				"Warning message do not match");
		// ("Click password edit Icon.");
		clickPasswordEditButton();
		// ("Entering password in the password field.");
		enterPasswordInExistingPassField("scmuser@2022");
		// ("Entering password in the password field.");
		enterPasswordInNewPassField("scmuser@2020");
		// ("Entering password in the password field.");
		enterPasswordInVerifyNewPassField("scmuser@2021");
		// ("Submit password button");
		clickSubmitPassButton();
		isDifferentPassErrorMssgDisplayed();
		softAssert.assertEquals(getPwdToastmsgLabel(), myprofileTextProp.getPropValue("differentpassmssg"),
				"Warning message do not match");
		ExtentLogger.logInfo("Test Case execution for - verifyNegativeValidationForMyProfile - is Completed");
	}

	public void verifyDeleteMyProfile(SoftAssert softAssert) {

		Assert.assertTrue(isMyProfilePage(myprofileTextProp.getPropValue("myAccountPageUrl"),
				myprofileTextProp.getPropValue("myAccountPageTitle")));

		softAssert.assertEquals(getDeleteMyProfileLable(), myprofileTextProp.getPropValue("deleteMyProfileLbl"));
		clickDeleteMyProfile();
		pause(2000);
		softAssert.assertTrue(isDeleteMyProfileCancelWarnbtnVisible(), "DMP Warning cancel button is not visible");
		softAssert.assertTrue(isSubmitDPisVisible(), "DMP submit button is not visible");
		softAssert.assertTrue(isDMPCrossBtnVisible(), "DMP cross Btn is not Visible.");
		softAssert.assertEquals(getDeleteMyProfilePopupLable(), myprofileTextProp.getPropValue("deleteMyProfileLbl"),
				"Header is not matching.");
		clickDMPCrossBtn();
		pause(5000);
	}

	public void verifyTimeZoneSaveFunctionality(SoftAssert softAssert) {
		Assert.assertTrue(isMyProfilePage(myprofileTextProp.getPropValue("myAccountPageUrl"),
				myprofileTextProp.getPropValue("myAccountPageTitle")));
		softAssert.assertEquals(getTimeZoneLabel(), myprofileTextProp.getPropValue("LblTimeZoneHeading"),
				"Time Zone Heading is not matching");
		softAssert.assertEquals(getEditTimeZoneLabel(), myprofileTextProp.getPropValue("EditTimeZone"),
				"Edit Time Zone Label is not matching");
		softAssert.assertTrue(isTimeZoneValueVisible(), "Time Zone Value is not Visible");
		ExtentLogger.logInfo("Verification  done for Timezone Object on My Profile Page");
		clickEditTimeZone();
		softAssert.assertEquals(getLblTimeZonePopupHeading(), myprofileTextProp.getPropValue("timeZonePopUpHeading"),
				"Time Zone Heading label om pop up is not matching");
		softAssert.assertEquals(getLblQuiteHoursTimeZonePopUpApp(),
				myprofileTextProp.getPropValue("AffectQuiteHoursTimeZonePopUp"),
				"LblQuiteHoursTimeZonePopUpApp is not Matching");
		softAssert.assertEquals(getbtnCancelTimezonelabel(), myprofileTextProp.getPropValue("inputCancelTimeZonePopup"),
				"Time Zone Cancel button label is not Matching ");
		softAssert.assertEquals(getBtnInputSaveTimeZonePopupLabel(),
				myprofileTextProp.getPropValue("InputSaveTimeZonePopup"),
				"Time Zone Save button label is not matching");
		softAssert.assertTrue(isTimeZonePopupHeadingVisible(), "Time Zone Popup Heading is not Visible");

		softAssert.assertTrue(isCloseTimeZonePopupVisible(), "Close button on TimeZone Popup is not Visible");

		ExtentLogger.logInfo("Verification  done for Set TimeZone Popup");

		getTimeZoneLabelOnPopup();
		clickCancelTimeZonePopup();

		clickEditTimeZone();

		// Update Time zone
		String existingTimeZone = getTimeZoneValueLabel();

		selectTimeZone("(UTC+05:30)India Standard Time");
		clicksaveTimeZonePopup();
		String updatedTimeZone = getTimeZoneValueLabel();
		// softAssert.assertNotEquals(updatedTimeZone, existingTimeZone);
		softAssert.assertEquals(getTimeZoneValueLabel(), "(UTC+05:30)India Standard Time");
		ExtentLogger.logInfo("Time Zone is updated successfully as (UTC+05:30)India Standard Time");

	}

	public void verifyMarketingPreferencesObjects(SoftAssert softAssert) {
		Assert.assertTrue(isMarketingPreferencesPage(marketingPreferenceTextProp.getPropValue("marketingPageUrl"),
				marketingPreferenceTextProp.getPropValue("marketingPageTitle")));
		// Marketing Preference Object Visibility
		softAssert.assertTrue(isNewsReleasesVisible(), "News Releases is not Visible");
		softAssert.assertTrue(isServiceOfferingsVisible(), "Service Offering is not Visible");
		softAssert.assertTrue(isNewslettersVisible(), "Newsletter Check Box is not Visible");
		softAssert.assertTrue(isEnergySavingsToolkitsVisible(), "Energy Saving Toolkits is not Visible");
		softAssert.assertTrue(isCoolTipsBrochuresVisible(), "Cool Tips Brochures is not Visible");
		softAssert.assertTrue(isCommunityBenefitProgramsVisible(), "Community Benefit Programs is not Visible");
		softAssert.assertTrue(isNewsReleasesCheckBoxVisible(), "News Releases Check Box is not Visible");
		softAssert.assertTrue(isServiceOfferingsCheckBoxVisible(), "Service Offering Check Box is not Visible");
		softAssert.assertTrue(isNewslettersCheckBoxVisible(), "Newsletter Check Box is not Visible");
		softAssert.assertTrue(isEnergySavingsToolkitsCheckBoxVisible(),
				"Energy Saving Toolkits Check Box is not Visible");
		softAssert.assertTrue(isCoolTipsBrochuresCheckBoxVisible(), "Cool Tips Brochures Check Box is not Visible");
		softAssert.assertTrue(isCommunityBenefitProgramsCheckBoxVisible(),
				"Community Benefit Programs Check Box is not Visible");
		// Marketing Preference Object Label Verification
		softAssert.assertEquals(getMarketingPreferenceLabel(),
				marketingPreferenceTextProp.getPropValue("marketingPageHeader"),
				"Marketing Preference Title on My Profile Page is not Matched");
		softAssert.assertEquals(getMarketingPreferenceDescLabel(),
				marketingPreferenceTextProp.getPropValue("marketingPreferenceDesc"),
				"Marketing Preference Description is Not Matching");
		softAssert.assertEquals(getNewsReleasesLabel(), marketingPreferenceTextProp.getPropValue("lblNewsRelease"),
				"News Release Label is not  Matching");
		softAssert.assertEquals(getServiceOfferingsLabel(),
				marketingPreferenceTextProp.getPropValue("lblServiceOffering"),
				"Service Offering Label is not Matching");
		softAssert.assertEquals(getNewslettersLabel(), marketingPreferenceTextProp.getPropValue("lblNewsLetter"),
				"News Letter Label is not matching");
		softAssert.assertEquals(getEnergySavingsToolkitsLabel(),
				marketingPreferenceTextProp.getPropValue("lblEnergySavingsToolkits"),
				"Energy Saving Toolkits Label is not matching");
		softAssert.assertEquals(getCoolTipsBrochuresLabel(),
				marketingPreferenceTextProp.getPropValue("lblCoolTipsBrochures"),
				"Cool Tips Brochures is not matching");
		softAssert.assertEquals(getCommunityBenefitProgramsLabel(),
				marketingPreferenceTextProp.getPropValue("lblCommunityBenefitPrograms"),
				"Community Benefit Program Label is not matching");
		ExtentLogger.logInfo("Marketing Preference Objects are validated");
		
		// Marketing Preference Object's CheckBox Validation Checked Or Not
		softAssert.assertTrue(isNewsReleasesBoxChecked(), "News Releases Check Box is not checked");
		softAssert.assertTrue(isServiceOfferingsBoxChecked(), "Service Offering Check Box is not checked");
		softAssert.assertTrue(isNewslettersBoxChecked(), "Newsletter Check Box is not checked");
		softAssert.assertTrue(isEnergySavingsToolkitsBoxChecked(), "Energy Saving Toolkits Check Box is not checked");
		softAssert.assertTrue(isCoolTipsBrochuresBoxChecked(), "Cool Tips Brochures Check Box is not checked");
		softAssert.assertTrue(isCommunityBenefitProgramsBoxChecked(),
				"Community Benefit Programs Check Box is not checked");
		
		softAssert.assertTrue(isSetPreferenceBtnClickable(), "Set Preference Button is not Clickable");
		ExtentLogger.logInfo("Validated that Marketing Preference Object's CheckBox Validation Checked Or Not");

		// Verify the number of Marketing Pref visible
		int marketingPrefCount = getMarketingPrefElem().size();
		softAssert.assertEquals(isMarketingPrefElemVisible(), marketingPrefCount, "All marketing pref is not Visible");

		ExtentLogger.logInfo("Validated that the number of Marketing Pref visible");

		// UnChecked All Marketing Preferences
		unCheckAllMarketingPrefBox();
		clickSetPreferenceButton();
		waitForPageLoader();
		softAssert.assertTrue(isAllMarketingPrefBoxCheked() == 0,
				"Doing uncheck all marketing Pref but Some are not able to uncheck ");
		ExtentLogger.logInfo("UnChecked All Marketing Preferences and Set Preference");

		pause(3000);
		// Checked All Marketing Preference
		checkAllMarketingPrefBox();
		clickSetPreferenceButton();
		ExtentLogger.logInfo("Checked All Marketing Preferences and Set Preference");	
	}

	public int isAllMarketingPrefBoxCheked() {
		int numberOfCheckMarketingPrefBox = 0;
		for (WebElement marketingprefCheckBox1 : getmarketingprefCheckBox()) {
			if (marketingprefCheckBox1.isSelected() == true) {
				numberOfCheckMarketingPrefBox++;
			}
		}
		return numberOfCheckMarketingPrefBox;
	}

	public int isMarketingPrefElemVisible() {
		int numberOfVisibleMarketingPrefElem = 0;
		for (WebElement getMarketingPrefElem1 : getMarketingPrefElem()) {
			if (isElementVisible(getMarketingPrefElem1) == true) {
				numberOfVisibleMarketingPrefElem++;
			}
		}
		return numberOfVisibleMarketingPrefElem;
	}
	
	public void verifyProfileInfoBanner(SoftAssert softAssert) {
		String ques11 = getProfileInfoBanner().get(0).getText();
		String ques2 = getProfileInfoBanner().get(1).getText();
		String ques3 = getProfileInfoBanner().get(2).getText();
		String ques4 = getProfileInfoBanner().get(3).getText();
		getProfileInfoBanner().get(1).click();
		softAssert.assertEquals(getMailingAddUpdateQues(),ques2);
		softAssert.assertEquals(getMailingAddUpdateQues(),myprofileTextProp.getPropValue("txtUpdateMailingAddQues"));
		softAssert.assertEquals(getMailingAddUpdateAns(),myprofileTextProp.getPropValue("txtUpdateMailingAddAns"));
		ExtentLogger.logInfo("All the questions are displyed on my profile page");	

	}
	
	public void verifyContactUsDetails(SoftAssert softAssert) {
		String call = getContactUsDetail().get(0).getText();
        String supportPhoneNo = call.replaceAll("\\D", "");
		String email = getContactUsDetail().get(1).getText();
        String supportEmailId = email.substring(13, 26);
        softAssert.assertEquals(supportPhoneNo,Configuration.toString("SupportEmailId"));
        softAssert.assertEquals(supportEmailId,Configuration.toString("SupportPhnNo"));
        ExtentLogger.logInfo("Validated that Customer contact Details are displying on the My Profile Page ");	
       

	}
	

}
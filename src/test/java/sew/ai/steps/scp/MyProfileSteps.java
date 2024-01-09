package sew.ai.steps.scp;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import sew.ai.config.CSPConfiguration;
import sew.ai.config.Configuration;
import sew.ai.config.SCPConfiguration;
import sew.ai.driver.DriverFactory;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.models.User;
import sew.ai.pageObjects.scp.AppOnBoardingPage;
import sew.ai.pageObjects.scp.MyProfilePage;
import sew.ai.pageObjects.scp.SignOutPage;
import sew.ai.utils.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static sew.ai.config.SCPConfiguration.user;

public class MyProfileSteps extends MyProfilePage {
	public static PropertiesUtil myprofileTextProp;
	Map<String, String> minLenRegFieldsMap = new HashMap<>();
	Map<String, String> utilityDefaultTimeZoneMetricsConfig = new HashMap<>();
	Map<String, String> maxLenRegFieldsMap = new HashMap<>();
	public static Map<String, Integer> registrationConfig = new HashMap<>();
	ArrayList<String> alProfileDataName = new ArrayList<>();
	// public static Map<String, GetRegistrationConfig> registrationConfig = new
	// LinkedHashMap<>();

	public MyProfileSteps(WebDriver driver) {
		super(driver);
		myprofileTextProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.MYPROFILE_TXT_FILENAME);
	}

	public void verifyProfileInformationPageUI(SoftAssert softAssert) {
		// Verify page heading
		Assert.assertTrue(isMyProfilePage(myprofileTextProp.getPropValue("myAccountPageUrl"),
				myprofileTextProp.getPropValue("myAccountPageTitle")));
		//
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
		// Verify page object label in all input fields
		softAssert.assertEquals(getUserNameLabel(), myprofileTextProp.getPropValue("UserNameLabel"),
				"User name label is not matching");
		// validation my profile page object labels
		User user = SCPConfiguration.user;
		//softAssert.assertEquals(getEditUserNameLabel(), user.getUserName(), "User Name is not Matching");
		softAssert.assertEquals(getEditUserNameLabel(), "patricupdate", "User Name is not Matching");
		String primaryPhoneNo = getPrimaryPhoneLabel();
		primaryPhoneNo.replaceAll("[^0-9]", "");
		//softAssert.assertEquals(primaryPhoneNo, user.getMobilePhone(),"Primary Phone no of the User is not Matching from DB");
		softAssert.assertEquals(primaryPhoneNo, "2121212155","Primary Phone no of the User is not Matching from DB");
//		String SecondaryPhoneNo = "";
//		SecondaryPhoneNo = getSecondaryPhoneNumberLabel();
//		System.out.println(SecondaryPhoneNo);
//		if (SecondaryPhoneNo != "") {
//			SecondaryPhoneNo = getSecondaryPhoneNumberLabel().replaceAll("[^0-9]", "");
//			String SecondaryPhoneNoOnDB = user.getHomePhone();
//			softAssert.assertEquals(SecondaryPhoneNo, SecondaryPhoneNoOnDB,
//					"Alternate Mobile No is not matching from DB");
//		}
		//softAssert.assertEquals(getPrimaryEmailIdLabel(), user.getEmailId(), "Primary Email is not matching from DB");
		softAssert.assertEquals(getPrimaryEmailIdLabel(), "patricturner@yopmail.com", "Primary Email is not matching from DB");
		/*
		 * if(getSecondaryEmailLabel()!="") {
		 * softAssert.assertEquals(getSecondaryEmailLabel(),user.getAlternateEmailID(),
		 * "Secondary Email is not matching from DB"); }
		 */
		//softAssert.assertEquals(getValueNameLabel(), user.getFullName(),"User Value Name is not Matching with Profile");
		softAssert.assertEquals(getValueNameLabel(), "PATRICIA TURNER","User Value Name is not Matching with Profile");
		String pass = "*";
		softAssert.assertTrue(getProfilePasswordLabel().contains("*"), "Password is not Matching from DB");
		// Verify Banner at My Account-> Profile Page
		softAssert.assertTrue(isMyAccountPageBannerVisible(), "My Account Page Banner is not Visbile");
		// My Profile Object Visibilty
		//softAssert.assertTrue(isDownloadMyDataVisible(), "    is not visible");
		softAssert.assertTrue(isTimeZoneVisible(), "    is not visible");
		softAssert.assertTrue(isTimeZoneValueVisible(), "    is not visible");
		softAssert.assertTrue(isTimeZoneEditButtonVisible(), "    is not visible");
		//softAssert.assertTrue(isSecondaryPhoneNumberVisible(), "    is not visible");
		softAssert.assertTrue(isPrimaryEmailVisible(), "    is not visible");
		softAssert.assertTrue(isSecondaryEmailTxtVisible(), "    is not visible");
		softAssert.assertTrue(isValueUserNameVisible(), "    is not visible");
		softAssert.assertTrue(isProfilePasswordVisible(), "    is not visible");
		softAssert.assertTrue(isPrimaryPhoneNumberEditButtonVisible(), "    is not visible");
		softAssert.assertTrue(isSecondaryPhoneNumberEditButtonVisible(), "    is not visible");
		softAssert.assertTrue(isPrimaryEmailIdEditButtonVisible(), "    is not visible");
		//softAssert.assertTrue(isSecondaryEmailIdEditButtonVisible(), "    is not visible");
		softAssert.assertTrue(isUserNameVisible(), "    is not visible");
		softAssert.assertTrue(isMyAccountPageBannerVisible(), "    is not visible");
		// Verify all page Menu on my account page-- it will be on HomeSteps .list-group
		// li span
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
		// softAssert.assertEquals(getSecondaryPhoneNumberAttributeByValue(),myprofileTextProp.getPropValue("secondaryPhone"));
		softAssert.assertEquals(getAltEmailIdLabel(), myprofileTextProp.getPropValue("altEmailId"));
		softAssert.assertEquals(getValueNameLabel(), user.getFullName(), "User Value Name is not Matching with Pro");
		// Verify details present on Personal Info section from DB
		String userNameOnUI=getEditUserNameLabel();
		softAssert.assertEquals(userNameOnUI, user.getUserName(), "User Name is not Matching");
		softAssert.assertEquals(getPrimaryPhoneLabel().replaceAll("[^0-9]", ""),
				user.getMobilePhone().replaceAll("[^0-9]", ""), "Primary Phone no of the User is not Matching from DB");
		/*
		 * if (getSecondaryPhoneNumberLabel() != "") {
		 * softAssert.assertEquals(getSecondaryPhoneNumberLabel().replaceAll("[^0-9]",
		 * ""), user.getHomePhone().replaceAll("[^0-9]",
		 * ""),"Alternate Mobile No is not matching from DB"); }
		 */
		softAssert.assertEquals(getPrimaryEmailIdLabel(), user.getEmailId(), "Primary Email is not matching from DB");
		// softAssert.assertEquals(getSecondaryEmailLabel(),user.getAlternateEmailID(),
		// "Secondary Email is not matching from DB");
		softAssert.assertEquals(getValueNameLabel(), user.getFullName(),
				"User Value Name is not Matching with Profile");
		// Verify Primary Email Address is mandatory but Alternate Email
		// Address is not
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
		softAssert.assertEquals(getPrimaryEmailIdLabel(), user.getEmailId(), "Primary Email Id is not Matching");
		// Update P EMail id with Empty Input Field
		clickPrimaryEmailIdEditButton();
		clearPrimaryEmailTextBox();
		clickPrimaryEmailIdSaveButton();
		softAssert.assertEquals(getMandatoryToastMessage(), myprofileTextProp.getPropValue("mandatormailmsg"));
		DriverFactory.refreshBrowser();
		pause(5000);
		// update P Email without Pwd
		clickPrimaryEmailIdEditButton();
		String pEmailid = user.getEmailId();
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
		populatePwdForUpdateEmailid(user.getPassword());
		clickPrimaryEmailIdSaveButton();
		String mandatoryToastMsg = getEmailToastMessage();
		System.out.println(mandatoryToastMsg);
		softAssert.assertEquals(mandatoryToastMsg, myprofileTextProp.getPropValue("primaryEmailToast"),
				"Mandatory Toast Msg is not Matching.");
		user.setEmailId(pEmailid);
	}

	public void verifyContactTypeProfileInformation(SoftAssert softAssert) {
		// Verify Primary Contact type available options
		clickPrimaryPhoneNumberEditButton();
		List<WebElement> ContactType = getAllPrimaryPOptionsInListBox();
		//softAssert.assertTrue(ContactType.size() == 5, "Some Contact Type is either add or Remove from existing");
		softAssert.assertTrue(ContactType.size() == 3, "Some Contact Type is either add or Remove from existing");
		softAssert.assertTrue(ContactType.get(0).getText().equals("--Select--"),"Contact type at index 0 is not Matching");
		softAssert.assertTrue(ContactType.get(1).getText().equals("Other"), "Contact type at index 1 is not Matching");
		softAssert.assertTrue(ContactType.get(2).getText().equals("Mobile"), "Contact type at index 2 is not Matching");
//		softAssert.assertTrue(ContactType.get(2).getText().equals("Mobile"), "Contact type at index 2 is not Matching");
//		softAssert.assertTrue(ContactType.get(3).getText().equals("Work"), "Contact type at index 3 is not Matching");
//		softAssert.assertTrue(ContactType.get(4).getText().equals("Emergency"), "Contact type at index 4 is not Matching");
		// Verify Secondary Contact type available options
		DriverFactory.refreshBrowser();
		// Verify Secondary Phone Contact Type Validation using For Loop
//		clickSecondaryPhoneNumberEditButton();
//		List<WebElement> ContactType1 = getAllSecondaryPOptionsInListBox();
//		softAssert.assertTrue(ContactType1.size() == 5, "Some Contact Type is either add or Remove from existing");
//		softAssert.assertTrue(ContactType1.get(0).getText().equals("--Select--"),
//				"Contact type1 at index 0 is not Matching");
//		softAssert.assertTrue(ContactType1.get(1).getText().equals("Landline"),
//				"Contact type1 at index 1 is not Matching");
//		softAssert.assertTrue(ContactType1.get(2).getText().equals("Mobile"),
//				"Contact type1 at index 2 is not Matching");
//		softAssert.assertTrue(ContactType1.get(3).getText().equals("Work"), "Contact type1 at index 3 is not Matching");
//		softAssert.assertTrue(ContactType1.get(4).getText().equals("Emergency"),
//				"Contact type1 at index 4 is not matching");
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
		// C74725
		clickUserNameEditButton();
		populatePwdForUpdateUserName(user.getPassword());
		clickChangeUserNameSaveButton();
		softAssert.assertEquals(getUserNameToastMessage(), myprofileTextProp.getPropValue("userNameNotSameMsg"));
		clearUserNameTextField();
		clickChangeUserNameSaveButton();
		softAssert.assertEquals(getUserNameToastMessage(), myprofileTextProp.getPropValue("userNameValidation"));
		pause(500);
		clearUserNameTextField();
		clearPwdForChangeUserNameEditField();
		clickChangeUserNameSaveButton();
		// softAssert.assertEquals(getMandatoryToastMessage(),
		// myprofileTextProp.getPropValue("mandatormailmsg "));
		// softAssert.assertTrue(getMandatoryToastMessage().contains(myprofileTextProp.getPropValue("mandatormailmsg
		// ")),"Mandatory Msg is not matching.");
		String username = user.getUserName();
		// C74742
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
		//softAssert.assertEquals(getMandatoryToastMessage(), myprofileTextProp.getPropValue("wrongPassValidateMsg"));
		pause(1000);
		// updated username validation from DB
		username = user.getUserName();
		softAssert.assertEquals(getEditUserNameLabel(), username, "UserName is not Matching.");
		DriverFactory.refreshBrowser();
		// Restore the previous UserName
		pause(3000);
		// user.setUserName(username);
		/*
		 * clickUserNameEditButton(); clearUserNameTextField(); String
		 * oldUserName=getEditUserNameLabel().replace("new","");
		 * populateUserName(oldUserName);
		 * populatePwdForUpdateUserName(Configuration.toString("password"));
		 * clickChangeUserNameSaveButton();
		 */
		ExtentLogger.logPass("Test Case execution for - verifyChangeUsername - is Completed");
	}

	public void verifyChangeUsernameNotAvailable(SoftAssert softAssert) throws SQLException {
		ExtentLogger.logInfo("Verify change username link is not available if email id as " + "username is set in CSP");
		ExtentLogger.logInfo("Test Case execution for - verifyChangeUsernameNotAvailable - is Initiated");
		String query = SQLQueries.getUserNameTempalateDetail("93");
		String userIdAsEmailStatus = "false";
		ResultSet rs = DataBaseUtils.getResultSet(query);
		while (rs.next()) {
			userIdAsEmailStatus = rs.getString("value");
		}
		if (userIdAsEmailStatus.equals("true")) {
			softAssert.assertFalse(isUserNameEditButtonVisible(),
					"Change User name edit button for Change User name is visible. t should not be visible when username as emailid");
		}
		ExtentLogger.logPass("Verify Change Username link is not available if EmailId as Username is set in CSP");
		ExtentLogger.logInfo("Test Case execution for - verifyChangeUsernameNotAvailable - is Completed");
	}

	public void verifyChangeUsernameAcceptanceCriteria(SoftAssert softAssert) throws SQLException {
		Assert.assertTrue(isMyProfilePage(myprofileTextProp.getPropValue("myAccountPageUrl"),
				myprofileTextProp.getPropValue("myAccountPageTitle")));
		ResultSet resultSet = null;
		String changeUsername = user.getUserName() + "1";
		String userId = null;
		try {
			userId = user.getUserId();
			softAssert.assertTrue(isUserNameVisible(), "user Name Label is not Visible");
			softAssert.assertTrue(isEditUserNameVisible(), "User Name Edit label is not Visible ");
			softAssert.assertTrue(isUserNameEditButtonVisible(), "User Name Edit Kebab Meau is not Visible");
			softAssert.assertEquals(getUserNameLabel(), myprofileTextProp.getPropValue("UserNameLabel"),
					"User name label is not matching");
			softAssert.assertEquals(getEditUserNameLabel(), user.getUserName(), "User Name is not Matching");
			clickUserNameEditButton();
			// Verifying max length of the username and password field.
			String regQuery = SQLQueries.getRegistrationTemplateConfig();
			resultSet = DataBaseUtils.getResultSet(regQuery);
			while (resultSet.next()) {
				minLenRegFieldsMap.put(resultSet.getString("ParentHead"), resultSet.getString("Min Length"));
				maxLenRegFieldsMap.put(resultSet.getString("ParentHead"), resultSet.getString("Max Length"));
			}
			String expMaxLengthOfField = maxLenRegFieldsMap.get("Username");
			String expMinLengthOfField = minLenRegFieldsMap.get("Username");
			String actMaxLengthOfField = getTxtBoxUserNameMaxLength();
			String actMinLengthOfField = getTxtBoxUserNameMinLength();
			softAssert.assertEquals(actMaxLengthOfField, expMaxLengthOfField,
					"Max length of User Name is not Matching");
			softAssert.assertEquals(actMinLengthOfField, expMinLengthOfField,
					"Min length of user name is not matching");
		} catch (Exception e) {
			e.printStackTrace();
		}
		AppOnBoardingPage appOnBoardingPage = new AppOnBoardingPage(driver);
		
		String usedUserId = null;
		ResultSet resultSet1 = DataBaseUtils
				.getResultSet(SQLQueries.getAlreadyUserUserNameFromUserID(user.getUserId()));
		if (resultSet1.next()) {
			usedUserId = resultSet1.getString("UserName");
		}
		appOnBoardingPage.populateNewUserName(usedUserId);
		pause(1000);
		appOnBoardingPage.clickbtnSubmitUserId();

		// Update Username
		String username = user.getUserName();
		appOnBoardingPage.populateNewUserName(changeUsername);
		pause(1000);
		populatePwdForUpdateUserName(user.getPassword());
		clickChangeUserNameSaveButton();
		// appOnBoardingPage.clickbtnSubmitUserId();
		pause(2000);
		String actToastMsg = getMandatoryToastMessage();
		String expToastMsg = myprofileTextProp.getPropValue("txtLblUsernameChanged");
		softAssert.assertEquals(actToastMsg, expToastMsg, "Toast message for successful change username not matched.");
		// User navigate toSignout page
		waitForPageLoader();
		SignOutSteps signOutSteps = new SignOutSteps(driver);
		String SignedOutlbl = signOutSteps.getSignOutSuccessfullyLbl();
		myprofileTextProp.getPropValue("signedOutmsg");
		myprofileTextProp.getPropValue("signoutUrl");
		softAssert.assertEquals(signOutSteps.getSignOutSuccessfullyLbl(),
				myprofileTextProp.getPropValue("SignedOutLbl"), "Sign out lbl not matching");
		signOutSteps.clickSignInAgainBtn();
		//
		LoginSteps loginSteps = new LoginSteps(driver);
		loginSteps.populateLoginForm(changeUsername, user.getPassword());
		loginSteps.clickSignInBtn();
		waitForPageLoader();

		// Verify Username get updated in DB
		ResultSet resultSet2 = DataBaseUtils.getResultSet(SQLQueries.getUserNameFromUserID(user.getUserId()));
		resultSet2.next();
		String updatedUsernameDB = resultSet2.getString("Username");
		System.out.println(updatedUsernameDB);
		softAssert.assertEquals(changeUsername.toLowerCase(), updatedUsernameDB);
		// Update username in [user] table using updateUsernameQuery
		DataBaseUtils.updateSqlComm(SQLQueries.updateUsernameQuery(user.getUserId(), username));

		ResultSet rs2 = DataBaseUtils.getResultSet(SQLQueries.getUserNameFromUserID(user.getUserId()));
		if (resultSet2.next()) {
			username = resultSet2.getString("usreName");
		}
		System.out.println(username);

	}

	public void verifyDefaultServiceAddress(SoftAssert softAssert) {
		
		//boolean elementPresence = isBoxAccountAddresPresent();
		String banner=getBannerTitleLabel();
		softAssert.assertEquals(banner, myprofileTextProp.getPropValue("txtTitleBanner"),
				"Banner Title Label Is not Matching");
		 // Default address selected in Select Account Dropdown
		DashboardSteps dashboardSteps = new DashboardSteps(driver);
		ExtentLogger.logInfo("To verify that Default Service Address, On changing default " + "address it gets updated.");
       
		softAssert.assertFalse(dashboardSteps.isAccountDropDownBtnVisible(), "Account drop down is visble.");
		
		clickAccountInfoLink();
        AccountInformationSteps accountInformation=new AccountInformationSteps(driver);
		Assert.assertEquals(accountInformation.getAccountInformationPageTitleLabel(),"Account Information");
		 softAssert.assertFalse(dashboardSteps.isAccountDropDownBtnVisible(), "Account drop down is visble.");
            
          
            ExtentLogger.logPass("To verify that Default Service Address, On changing default " + "address it gets updated.");
	}


	public void verifyDownloadMyPersonalData(SoftAssert softAssert) throws IOException, SQLException {
		String userName = user.getUserName();
		// Verify page heading
		Assert.assertTrue(isMyProfilePage(myprofileTextProp.getPropValue("myAccountPageUrl"),
				myprofileTextProp.getPropValue("myAccountPageTitle")));
		softAssert.assertTrue(isDownloadMyDataVisible(), "Download My data link is not visible on My Profile Page.");
		softAssert.assertEquals(getDownloadMyDataLabel(), myprofileTextProp.getPropValue("downloadMyDatalbl"));
		clickDownloadMyData();
		// label Validate Download my Data on popup
		
		softAssert.assertEquals(getDownloadMyDataPopUpLabel(), myprofileTextProp.getPropValue("downloadmydatapoplbl"));
		softAssert.assertEquals(getDownloadMydataVerificationLabel(),
				myprofileTextProp.getPropValue("DownloadMydataVerifilabel"));
		softAssert.assertEquals(getPwdForDownloadMyData1Label(),
				myprofileTextProp.getPropValue("pwdDownloadmyDatalbl"));
		softAssert.assertTrue(isSubmitDownloadEnable(),
				"Submit Password Button is not Enable for Download My Data from My profile page");
		softAssert.assertTrue(isCancelDownloadEnable(), "Cancel Password Button is not Enable for Download My Data");
		softAssert.assertEquals(getHideShowEyelbl(), myprofileTextProp.getPropValue("hideShowEye"));
		clickHideShowEys();
		softAssert.assertEquals(getHideShowEyelbl(), myprofileTextProp.getPropValue("unhideShowEye"));
		// Download My Data With Invalid password
		DriverFactory.refreshBrowser();
		pause(2000);
		clickDownloadMyData();
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
		// Validation the Downloaded Data With DB
		
		// Repeate the Steps Again

		DriverFactory.refreshBrowser();
		pause(2000);
		FileUtil.deleteFile(FilePaths.DOWNLOAD_FOLDER_PATH);
		pause(5000);
		clickDownloadMyData();
	    populatePwdForDownloadMyData(Configuration.toString("password"));
		clickDownloadMyDataSubmitbtn();
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

		// Validation Column Name with Db and Excel File-- properties file to split for loop
		String colname = myprofileTextProp.getPropValue("downloadMyDataPersonalInfoColumnName");
		String[] colname1 = colname.split(",");
		// Validation Personal inforamtion Column Name in Excel from Properties File
		// Hard Coded column name
		for (int colname2 = 0; colname2 < colname1.length; colname2++) {
			String exepCol = colname1[colname2];
			String actColName = sheet.getRow(0).getCell(colname2).getStringCellValue();
			softAssert.assertEquals(actColName, exepCol, "Column Name of the sheet is not matching as per order.");
		}
		// Validation the excel Data value from Db for Personal Information
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

	public void verifyDownloadMyDataMailingAddressValidation(SoftAssert softAssert, String sheetname)
			throws IOException, SQLException {
		File files = new File(FilePaths.DOWNLOAD_FOLDER_PATH);
		
		// TODo ExcelUtil method for load Excel with the help of ApachePoi
		String downloadmydataFilePath = FilePaths.DOWNLOAD_FOLDER_PATH + Constants.DOWNLOAD_MY_DATA_EXCEL_FILENAME;
		pause(5000);
		FileInputStream fis = new FileInputStream(downloadmydataFilePath);
		ExcelUtils.openExcelFile(downloadmydataFilePath);
		Sheet sheet = ExcelUtils.getSheetName(sheetname);
		int totlaRow = ExcelUtils.getRowCount(sheet);
		int totalCell = sheet.getRow(0).getLastCellNum();

		// Validation Column Name with Db and Excel File-- properties file to split for loop
		String colname = myprofileTextProp.getPropValue("downloadMyDataMailingAddColumnName");
		String[] colname1 = colname.split(",");
		// Validation Mailing Address Column Name in Excel from Properties File Hard
		// Coded column name
		for (int colname2 = 0; colname2 < colname1.length; colname2++) {
			String exepCol = colname1[colname2];
			softAssert.assertEquals(sheet.getRow(0).getCell(colname2).getStringCellValue(), exepCol);
		}

		ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.getServiceAccountNumberCount());
		// String totalUtiltuAccNum = rs.getString("TotalServiceAccountNumberCount");

		// Validation Utilitity ACcount number in the excel Data value from Db for
		// Personal Information
		String accountDetailquery = SQLQueries.getUserDetailsWithUsername(user.getUserName());
		ResultSet resultSet = DataBaseUtils.getResultSet(accountDetailquery);
		while (resultSet.next()) {
			String serviceAccountNumber = resultSet.getString("utilityaccountnumber");

			ResultSet resultSetServiceAdd = DataBaseUtils
					.getResultSet(SQLQueries.getServiceAddressOfAccountNumber(serviceAccountNumber));
			String serviceAddress = "";
			while (resultSetServiceAdd.next()) {

				String Address1 = resultSetServiceAdd.getString("Address1");
				String cityName = resultSetServiceAdd.getString("CityName");
				String stateName = resultSetServiceAdd.getString("StateName");
				String zipCode = resultSetServiceAdd.getString("zipCode");
				serviceAddress = Address1 + ", " + cityName + ", " + stateName + ", " + zipCode;

				if (!resultSetServiceAdd.getString("Address2").equals("")) {
					String Address2 = resultSetServiceAdd.getString("Address2");
					serviceAddress = Address1 + " " + Address2 + ", " + cityName + ", " + stateName + ", " + zipCode;
				}
			}

			ResultSet resultSetMailingAdd = DataBaseUtils
					.getResultSet(SQLQueries.getCurrentMailingAddressOfAnAccount(serviceAccountNumber));
			String mailingAddress = "";
			while (resultSetMailingAdd.next()) {
				String mAddress1 = resultSetMailingAdd.getString("Address1");
				String mCityName = resultSetMailingAdd.getString("CityName");
				String mStateName = resultSetMailingAdd.getString("StateName");
				String mZipCode = resultSetMailingAdd.getString("ZipCode");
				mailingAddress = mAddress1 + ", " + mCityName + ", " + mStateName + ", " + mZipCode;
				if (!resultSetMailingAdd.getString("Address2").equals("")) {
					String mAddress2 = resultSetMailingAdd.getString("Address2");
					mailingAddress = mAddress1 + ", " + mAddress2 + ", " + mCityName + ", " + mStateName + ", "
							+ mZipCode;
				}
			}

			ResultSet resultSetRoleId = DataBaseUtils
					.getResultSet(SQLQueries.getRoleIdForAccountNumber(serviceAccountNumber, user.getUserId()));
			resultSetRoleId.next();
			String roleId = resultSetRoleId.getString("roleId");

			String role = "";
			switch (roleId) {
			case "1":
				role = "GuestUser";
				break;
			case "2":
				role = "PropertyManager";
				break;
			default:
				role = "Owner";
			}

			for (int row = 1; row < totlaRow; row++) {
				String actServiceAccountNumber = ExcelUtils.getCellValue(sheet, row, 0);
				if (actServiceAccountNumber.equals(serviceAccountNumber)) {

					for (int col = 1; col < totalCell; col++) {
						switch (col) {
						case 1:
							softAssert.assertEquals(ExcelUtils.getCellValue(sheet, row, col), role,
									" Role id for Service Account no--->>" + serviceAccountNumber
											+ "in Excel is not Matching from DB");
							break;
						case 2:
							softAssert.assertEquals(ExcelUtils.getCellValue(sheet, row, col), serviceAddress,
									"Service Address for Service Account no--->>" + serviceAccountNumber
											+ "in Excel is not Matching from DB");
							break;
						default:
							softAssert.assertEquals(ExcelUtils.getCellValue(sheet, row, col), mailingAddress,
									"Mailing Address for Service Account no--->>" + serviceAccountNumber
											+ "in Excel is not Matching from DB");
						}
					}
				}
			}
		}
		ExcelUtils.closeConnectionWithExcel();
	}

	public static String getStringCellValue(XSSFSheet sheet, int rowIndex, int colIndex) {
		String cellValue = sheet.getRow(rowIndex).getCell(colIndex).getStringCellValue();
		return cellValue;
	}

	public void verifyNewUserDownloadMyPersonalData(SoftAssert softAssert, String newUserName, String newPass)
			throws Exception {

		// Script is pending for New user - First DownloadMy PersonalData should be
		// working properly
		ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.getAllLinkedAccountAccounts(newUserName));
		int rsSize = 0;
		try {
			while (rs.next()) {
				rsSize++;
			}
		} catch (Exception e) {
			rsSize = 0;
			System.out.println("Account not FOUND in DB for this user.");
		}
		System.out.println(rsSize);
		pause(2000);
		waitForPageToLoad();
		registrationConfig = CSPConfiguration.initMCspConfig();
		if (registrationConfig.get("MyAccount") == 1) {
			HomeSteps homeSteps = new HomeSteps(driver);
			homeSteps.navigateToMyProfilePage();
			// Validate Download my Data
			Assert.assertTrue(isMyProfilePage(myprofileTextProp.getPropValue("myAccountPageUrl"),
					myprofileTextProp.getPropValue("myAccountPageTitle")));
			// Verify page heading
			softAssert.assertEquals(getMyProfilePageHeaderLabel(),
					myprofileTextProp.getPropValue("myAccountPageTitle"));
			waitForPageToLoad();

			softAssert.assertTrue(isDownloadMyDataVisible(),
					"Download My data link is not visible on My Profile Page.");
			softAssert.assertEquals(getDownloadMyDataLabel(), myprofileTextProp.getPropValue("downloadMyDatalbl"));
			clickDownloadMyData();
			// label Validate Download my Data on popup
			// softAssert.assertTrue(isCloseDownloadMyDataPopup(), "Close Button on Download
			// my data Popup is not vsisble");
			softAssert.assertEquals(getDownloadMyDataPopUpLabel(),
					myprofileTextProp.getPropValue("downloadmydatapoplbl"));
			softAssert.assertEquals(getDownloadMydataVerificationLabel(),
					myprofileTextProp.getPropValue("DownloadMydataVerifilabel"));
			softAssert.assertEquals(getPwdForDownloadMyData1Label(),
					myprofileTextProp.getPropValue("pwdDownloadmyDatalbl"));
			softAssert.assertTrue(isSubmitDownloadEnable(),
					"Submit Password Button is not Enable for Download My Data from My profile page");
			softAssert.assertTrue(isCancelDownloadEnable(),
					"Cancel Password Button is not Enable for Download My Data");
			softAssert.assertEquals(getHideShowEyelbl(), myprofileTextProp.getPropValue("hideShowEye"));
			clickHideShowEys();
			softAssert.assertEquals(getHideShowEyelbl(), myprofileTextProp.getPropValue("unhideShowEye"));
			// Download My Data With Invalid password
			DriverFactory.refreshBrowser();
			pause(2000);
			clickDownloadMyData();
			populatePwdForDownloadMyData(RandomUtil.generateRandomString(8, RandomUtil.Mode.ALPHANUMERIC));
			clickDownloadMyDataSubmitbtn();
			softAssert.assertEquals(getInvalidPwdMandatoryFieldsLabel(),
					myprofileTextProp.getPropValue("invalidPwdMandatorymsg"),
					"invalid pwd toaster msg is not matching.");
			// Download My Data With valid password
			DriverFactory.refreshBrowser();
			pause(2000);
			// Delete File from Download Folder
			FileUtil.deleteFile(FilePaths.DOWNLOAD_FOLDER_PATH);
			// Download My Personal Data Process
			clickDownloadMyData();
			populatePwdForDownloadMyData(newPass);
			clickDownloadMyDataSubmitbtn();
			String downloadmydataFilePath = FilePaths.DOWNLOAD_FOLDER_PATH + Constants.DOWNLOAD_MY_DATA_EXCEL_FILENAME;
			try {
				Workbook wb = ExcelUtils.openExcelFile(downloadmydataFilePath);
			} catch (Exception e) {
				ExtentLogger.logFail(
						"Test Case execution for - verifyNewUserDownloadMyPersonalData - File is not downloded");
			}
			ExtentLogger.logPass("Verify that New User is able to download personal information");

		} else {
			ExtentLogger.logPass("Verify that New User is able to download personal information");
		}

	}

	public void verifyDownloadMyPersonalDataForNewUser(SoftAssert softAssert) {

	}

	public void downloadMyDataExtra(String _userName, SoftAssert softAssert) throws Exception {
		ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.getMyAccountProfileInfo(_userName));
		if (rs.next()) {
			File Files = new File(FilePaths.DOWNLOAD_FOLDER_PATH);
			int count;
			// BrowserUtils.deleteFiles(BrowserUtil.downloadFilepath);
			pause(2000);
			count = Files.list().length;
			softAssert.assertEquals(getLnkDownloadDataLabel(), myprofileTextProp.getPropValue("downloadmydatapoplbl"),
					"Download My Data label is not visible.");
			clickDownloadMyData();
			softAssert.assertEquals(getDownloadMyDataPopUpLabel(),
					myprofileTextProp.getPropValue("txtMsgDownloadDataPopup"));
			softAssert.assertEquals(getDownloadMydataVerificationLabel(),
					myprofileTextProp.getPropValue("txtMsgDownloadDataPopupVer"));
			clickDMPCrossBtn();
			clickDownloadMyData();
			softAssert.assertEquals(getDownloadMyDataLabel(),
					myprofileTextProp.getPropValue("txtMsgDownloadDataPopup"));
			softAssert.assertEquals(getDownloadMydataVerificationLabel(),
					myprofileTextProp.getPropValue("txtMsgDownloadDataPopupVer"));
			clickDownloadMyDataCancelBtn();
			clickDownloadMyData();
			populatePwdForDownloadMyData(RandomUtil.generateRandomString(8, RandomUtil.Mode.ALPHANUMERIC));
			clickDownloadMyDataSubmitbtn();
			pause(10000);
			softAssert.assertEquals(Files.list().length, count + 1);
			// String _fileName = downloadFolderPath + "PersonalInformation" +
			// DataBaseUtil.getUserIdDB(_sUserName) + ".xlsx";
			String _fileName = FilePaths.DOWNLOAD_FOLDER_PATH + "PersonalInformation.xlsx";
			ExcelUtils.openExcelFile(_fileName);
			pause(5000);
			ExcelUtils.getSheetName("Personal Information");
			int rowCount = ExcelUtils.getRowCount();
			int columnCount = ExcelUtils.getColumnCount();
			softAssert.assertEquals(rowCount, 1);
			softAssert.assertEquals(columnCount, 10);
			softAssert.assertEquals("First Name", ExcelUtils.getCellStringValue(0, 1));
			softAssert.assertEquals("Middle Name", ExcelUtils.getCellStringValue(0, 2));
			softAssert.assertEquals("Last Name", ExcelUtils.getCellStringValue(0, 3));
			softAssert.assertEquals("Primary Email", ExcelUtils.getCellStringValue(0, 4));
			softAssert.assertEquals("Primary Phone", ExcelUtils.getCellStringValue(0, 5));
			softAssert.assertEquals("Primary Phone Type", ExcelUtils.getCellStringValue(0, 6));
			softAssert.assertEquals("Secondary Email", ExcelUtils.getCellStringValue(0, 7));
			softAssert.assertEquals("Secondary Phone", ExcelUtils.getCellStringValue(0, 8));
			softAssert.assertEquals("Secondary Phone Type", ExcelUtils.getCellStringValue(0, 9));
			softAssert.assertEquals("Username", ExcelUtils.getCellStringValue(0, 0));
			softAssert.assertEquals("", ExcelUtils.getCellStringValue(0, 10));
			softAssert.assertEquals(rs.getString("FirstName"), ExcelUtils.getCellStringValue(1, 1));
			softAssert.assertEquals("", ExcelUtils.getCellStringValue(1, 2));
			softAssert.assertEquals(rs.getString("LastName"), ExcelUtils.getCellStringValue(1, 3));
			softAssert.assertEquals(rs.getString("EmailID"), ExcelUtils.getCellStringValue(1, 4));
			softAssert.assertEquals(rs.getString("MobilePhone"), ExcelUtils.getCellStringValue(1, 5));
			softAssert.assertEquals(rs.getString("primaryContactType"), ExcelUtils.getCellStringValue(1, 6));
			String alternateEmail = rs.getString("AlternateEmailID");
			if (alternateEmail != null) {
				softAssert.assertEquals(alternateEmail, ExcelUtils.getCellStringValue(1, 7));
			} else {
				softAssert.assertEquals("", ExcelUtils.getCellStringValue(1, 7));
			}
			String secondaryContact = rs.getString("HomePhone");
			if (secondaryContact != null) {
				softAssert.assertEquals(secondaryContact, ExcelUtils.getCellStringValue(1, 8));
			} else {
				softAssert.assertEquals("", ExcelUtils.getCellStringValue(1, 8));
			}
			String secondaryContactType = rs.getString("getAlternateContactType");
			if (secondaryContactType != null) {
				softAssert.assertEquals(secondaryContactType, ExcelUtils.getCellStringValue(1, 9));
			} else {
				softAssert.assertEquals(secondaryContactType, ExcelUtils.getCellStringValue(1, 9));
			}
			softAssert.assertEquals(_userName.trim(), ExcelUtils.getCellStringValue(1, 0).trim());
			softAssert.assertEquals("", ExcelUtils.getCellStringValue(1, 10));
			// Mailing Address
			FileUtil.deleteFiles(FilePaths.DOWNLOAD_FOLDER_PATH);
			pause(2000);
			count = Files.list().length;
			clickDownloadMyData();
			populatePwdForDownloadMyData(user.getPassword());
			clickDownloadMyDataSubmitbtn();
			pause(5000);
			softAssert.assertEquals(Files.list().length, count + 1);
			ExcelUtils.openExcelFile(FilePaths.DOWNLOAD_FOLDER_PATH + "PersonalInformation.xlsx");
			pause(5000);
			ExcelUtils.getSheetName("Mailing Addresses");
			rowCount = ExcelUtils.getRowCount();
			columnCount = ExcelUtils.getColumnCount();
			softAssert.assertEquals(columnCount, 4);
			softAssert.assertEquals("Service Account Number", ExcelUtils.getCellStringValue(0, 0));
			softAssert.assertEquals("Role", ExcelUtils.getCellStringValue(0, 1));
			softAssert.assertEquals("Service Address", ExcelUtils.getCellStringValue(0, 2));
			softAssert.assertEquals("Mailing Address", ExcelUtils.getCellStringValue(0, 3));
			HashSet<String> serviceAddList = new HashSet<>();
			HashSet<String> mailingAddList = new HashSet<>();
			HashSet<String> roleList = new HashSet<>();
			Boolean serAdd1st = false, mailinAddlst = false, rolelst = false;
			for (int i = 0; i <= columnCount; i++) {
				for (int j = 0; j <= rowCount; j++) {
					try {
						if (ExcelUtils.getCellStringValue(0, i).equals("Service Address")) {
							serAdd1st = true;
							mailinAddlst = false;
							rolelst = false;

						} else if (ExcelUtils.getCellStringValue(0, i).equals("Mailing Address")) {
							serAdd1st = false;
							mailinAddlst = true;
							rolelst = false;

						} else if (ExcelUtils.getCellStringValue(0, i).equals("Role")) {
							serAdd1st = false;
							mailinAddlst = false;
							rolelst = true;
						}
						String value = ExcelUtils.getCellStringValue(j, i);
						if (value != "") {
							if (serAdd1st) {
								serviceAddList.add(value);
							} else if (mailinAddlst) {
								mailingAddList.add(value);
							} else if (rolelst) {
								roleList.add(value);
							}
						}

					} catch (Exception e) {
					}
				}
			}
			System.out.println("Excel service Address List: " + serviceAddList);
			System.out.println("Excel Mailing Address List: " + mailingAddList);
			System.out.println("Excel Role List: " + roleList);
			// System.out.println("DB service Address List:
			// "+DataBaseUtil.getAllServiceAddress(userId));
			// Notification Preferences sheet validation
			FileUtil.deleteFiles(FilePaths.DOWNLOAD_FOLDER_PATH);
			pause(2000);
			count = Files.list().length;
			clickDownloadMyData();
			populatePwdForDownloadMyData(user.getPassword());
			clickDownloadMyDataSubmitbtn();
			pause(5000);
			softAssert.assertEquals(Files.list().length, count + 1);
			ExcelUtils.openExcelFile(FilePaths.DOWNLOAD_FOLDER_PATH + "PersonalInformation.xlsx");
			pause(5000);
			ExcelUtils.getSheetName("Notification Preferences");
			rowCount = ExcelUtils.getRowCount();
			columnCount = ExcelUtils.getColumnCount();
			// assertEquals(columnCount, 1);
			softAssert.assertEquals("Module", ExcelUtils.getCellStringValue(0, 0));
			softAssert.assertEquals("Account", ExcelUtils.getCellStringValue(0, 1));
			softAssert.assertEquals("Email", ExcelUtils.getCellStringValue(0, 2));
			softAssert.assertEquals("Text", ExcelUtils.getCellStringValue(0, 3));
			softAssert.assertEquals("IVR", ExcelUtils.getCellStringValue(0, 4));
			softAssert.assertEquals("Whatsapp", ExcelUtils.getCellStringValue(0, 5));
			softAssert.assertEquals("", ExcelUtils.getCellStringValue(0, 6));
			HashSet<String> textList = new HashSet<>();
			HashSet<String> emailList = new HashSet<>();
			HashSet<String> ivrList = new HashSet<>();
			Boolean elst = false, tlst = false, ivrlst = false;
			Boolean wlst = false;
			for (int i = 0; i <= columnCount; i++) {
				for (int j = 0; j <= rowCount; j++) {
					try {
						if (ExcelUtils.getCellStringValue(0, i).equals("WhatsApp")) {
							elst = false;
							tlst = false;
							ivrlst = false;
							wlst = true;

						} else if (ExcelUtils.getCellStringValue(0, i).equals("Email")) {
							elst = true;
							tlst = false;
							ivrlst = false;
							wlst = false;

						} else if (ExcelUtils.getCellStringValue(0, i).equals("Text")) {
							elst = false;
							tlst = true;
							ivrlst = false;
							wlst = false;

						} else if (ExcelUtils.getCellStringValue(0, i).equals("IVR")) {
							elst = false;
							tlst = false;
							ivrlst = true;
							wlst = false;

						}
						String value = ExcelUtils.getCellStringValue(j, i);
						if (value != "") {
							if (elst) {
								emailList.add(value);
							} else if (tlst) {
								textList.add(value);
							} else if (ivrlst) {
								ivrList.add(value);
							}
						}
					} catch (Exception e) {
					}
				}
			}
			textList.remove("Text");
			emailList.remove("Email");
			ivrList.remove("IVR");
			textList.remove("OFF");
			emailList.remove("OFF");
			ivrList.remove("OFF");
			System.out.println("Excel Text Notification List: " + textList);
			System.out.println("Excel Email Notification List: " + emailList);
			System.out.println("Excel IVR Notification List: " + ivrList);
//			System.out.println("DB Text Notification List: " + DataBaseUtils.getAllTextsInNotificationPref(_userName));
//			System.out
//					.println("DB Email Notification List: " + DataBaseUtils.getAllEmailsInNotificationPref(_userName));
//			System.out.println("DB IVR Notification List: " + DataBaseUtils.getAllIVRsInNotificationPref(_userName));
//			System.out.println("emailList=>" + DataBaseUtils.getAllEmailsInNotificationPref(_userName).size());
//			System.out.println("textList=>" + DataBaseUtils.getAllTextsInNotificationPref(_userName).size());
//			System.out.println("ivrList=>" + DataBaseUtils.getAllIVRsInNotificationPref(_userName).size());
//			System.out.println("emailList=>" + emailList.size());
//			System.out.println("textList=>" + textList.size());
//			System.out.println("ivrList=>" + ivrList.size());
//			softAssert.assertEquals(textList.size(), DataBaseUtils.getAllTextsInNotificationPref(_userName).size());
//			softAssert.assertEquals(emailList.size(), DataBaseUtils.getAllEmailsInNotificationPref(_userName).size());
//			softAssert.assertEquals(ivrList.size(), DataBaseUtils.getAllIVRsInNotificationPref(_userName).size());

		} else {
			ExtentLogger.logFail("New User is not available in DB");
			softAssert.assertTrue(false, "New User " + _userName + " is not available in DB.");
		}
	}

	public void verifyDeleteMyProfile(SoftAssert softAssert) {
		// TODO Need to Rework on the Script -- Deleted code
		Assert.assertTrue(isMyProfilePage(myprofileTextProp.getPropValue("myAccountPageUrl"),
				myprofileTextProp.getPropValue("myAccountPageTitle")));
		softAssert.assertEquals(getDeleteMyProfileLable(), myprofileTextProp.getPropValue("deleteMyProfileLbl"));
		clickDeleteMyProfile();
		List<WebElement> dmpDataElement = getlxtDeleteMyProfileWarning();
		String actWarninglabel = "";
		List<String> actWarning = new ArrayList();
		for (int label1 = 0; label1 < dmpDataElement.size(); label1++) {
			actWarninglabel = actWarninglabel + dmpDataElement.get(label1).getText();
			actWarning.add(actWarninglabel);

		}
		pause(5000);
		// softAssert.assertTrue(actWarning.contains(myprofileTextProp.getPropValue("expWarninglabel")),"message
		// on Delete my Profile Popup is not matching");
		softAssert.assertTrue(isDeleteMyProfileCancelWarnbtnVisible(), "DMP Warning cancel button is not visible");
		softAssert.assertTrue(isDeleteMyProfileProceedWarnbtnVisible(), "DMP Warning Proceed button is not visible");
		softAssert.assertTrue(isDMPCrossBtnVisible(), "DMP cross Btn is not Visible.");
		String warnMgs = myprofileTextProp.getPropValue("deleteMyprofileWarnMsg");
		softAssert.assertEquals(getDeleteMyProfileLableWarningLabel(),
				myprofileTextProp.getPropValue("deleteMyprofileWarnMsg"), "Warning msg label is not matching .");
		softAssert.assertEquals(getDMPCancelButtonlabel(), myprofileTextProp.getPropValue("cancel"));
		softAssert.assertEquals(getDMPProceedButtonlabel(), myprofileTextProp.getPropValue("proceed"));
		clickDMPCrossBtn();
		pause(5000);
		softAssert.assertFalse(isDMPCrossBtnVisible(),
				"DMP Popup didn't close , it should be close after clicking cross btn");
		// 4TC Pending at CSP lavel validation and Finally Delete the user and
		// validation from [user] table and login

	}

	public void verifyTimeZoneSaveFunctionality(SoftAssert softAssert) throws SQLException {
		Assert.assertTrue(isMyProfilePage(myprofileTextProp.getPropValue("myAccountPageUrl"),
				myprofileTextProp.getPropValue("myAccountPageTitle")));
		softAssert.assertEquals(getTimeZoneLabel(), myprofileTextProp.getPropValue("LblTimeZoneHeading"),
				"Time Zone Heading is not matching");
		softAssert.assertEquals(getEditTimeZoneLabel(), myprofileTextProp.getPropValue("EditTimeZone"),
				"Edit Time Zone Label is not matching");
		softAssert.assertTrue(isTimeZoneValueVisible(), "Time Zone Value is not Visible");
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
		getTimeZoneLabelOnPopup();
		clickCancelTimeZonePopup();
		clickEditTimeZone();
		// softAssert.assertTrue(isTimeZonePopupHeadingVisible(), "Time Zone Popup
		// Heading is not Visible");
		softAssert.assertEquals(getLblTimeZonePopupHeading(), myprofileTextProp.getPropValue("timeZonePopUpHeading"));
		softAssert.assertTrue(isCloseTimeZonePopupVisible(), "Close button on TimeZone Popup is not Visible");
		
		// TODO validate timezone on ui with time Zone in DB---
		String existingTimeZone = getTimeZoneValueLabel();
		ResultSet resultSet = DataBaseUtils.getResultSet(SQLQueries.getMyAccountProfileInfo(user.getUserName()));
		resultSet.next();
		String oldTimeZone = resultSet.getString("TimeZoneId");
		
		if (!oldTimeZone.equals("81")) {
			selectTimeZone("(UTC+05:30)India Standard Time");
		} else {
			selectTimeZone("(UTC-08:00)Pacific Standard Time (US and Canada)");
		}
		clicksaveTimeZonePopup();
		waitForPageLoader();
		
		ResultSet resultSet1 = DataBaseUtils.getResultSet(SQLQueries.getMyAccountProfileInfo(user.getUserName()));
		resultSet1.next();
		String newTimeZone = resultSet1.getString("TimeZoneId");
		// Verify Updated TimeZone in UI and DB
		if (!newTimeZone.equals("81")) {
			softAssert.assertTrue(isTimeZoneValueVisible(), "Time Zone Value is not Visible");
			softAssert.assertEquals(getTimeZoneValueLabel(), "(UTC-08:00)Pacific Standard Time (US and Canada)");
			softAssert.assertTrue(!newTimeZone.equals(oldTimeZone), "New Time Zone is Matching with old Time Zone");
		} else {
			softAssert.assertEquals(getTimeZoneValueLabel(), "(UTC+05:30)India Standard Time");

			// softAssert.assertTrue(newTimeZone.equals("191"), "New Time Zone is not Matching ");
			softAssert.assertTrue(!newTimeZone.equals(oldTimeZone), "New Time Zone is Matching with old Time Zone");
		}
	}

	public void verifyDefaultTimeZoneOfNewRegisteredUser(SoftAssert softAssert) throws Exception {
		ExtentLogger.logInfo(
				"Verify that for newly registered user default time zone value will be same as Utility time zone set in CSP.");
		ExtentLogger.logInfo("Test Case execution for - verifyDefaultTimeZoneOfNewRegisteredUser - is Initiated");
		pause(2000);
		registrationConfig = CSPConfiguration.initMCspConfig();
		if (registrationConfig.get("MyAccount") == 1) {
			HomeSteps homeSteps = new HomeSteps(driver);
			homeSteps.navigateToMyProfilePage();
			String timeZoneForNewUser = getTimeZoneValueLabel();
			softAssert.assertEquals(getEditTimeZoneLabel(), "EDIT", "Time Zone Edit Label is not Matching.");
			softAssert.assertTrue(isTimeZoneValueVisible(), "Time Zone Label for Newly Registred User is not Visible.");
			// Validate the Default time zone of utility is set for new registred user on
			// time zone tab
			String scpDefaultTimeZone = getTimeZoneValueLabel().replace(" ", "");
			scpDefaultTimeZone = scpDefaultTimeZone.replaceAll("[^A-Za-z0-9]", "");
			// Write code to get Default Time Zone set in CSP for
			// Utility----------------------------
			utilityDefaultTimeZoneMetricsConfig = CSPConfiguration.initDateMetricsConfig();
			String cspTimeZone = (utilityDefaultTimeZoneMetricsConfig.get("Time Zone")).replaceAll("[^A-Za-z0-9]", "");
			softAssert.assertEquals(scpDefaultTimeZone, cspTimeZone,
					"Default time zone is not matching which is set by the utility or in CSP");

			clickEditTimeZone();
			softAssert.assertEquals(getLblTimeZonePopupHeading(),
					myprofileTextProp.getPropValue("timeZonePopUpHeading"),
					"Lable on Time zone Edit popup is not matching.");
			String defaultTimeZoneAtPopup = getSelectTimeZonePopupDropdown().replace(" ", "");
			softAssert.assertEquals(defaultTimeZoneAtPopup.replaceAll("[^A-Za-z0-9]", ""), cspTimeZone,
					"The Selected Default time zon is not set on edit time zone popup at 1St selected option");
			
			selectTimeZone("--Select--");
			clicksaveTimeZonePopup();
			softAssert.assertEquals(getCommanErrorToastMsg(), myprofileTextProp.getPropValue("timeZoneErrorToasterMsg"),"Toaster msg when edit timezone with out selecting any timezone.");
			clickCancelTimeZonePopup();

		} else {
			ExtentLogger.logInfo("My Account link is not available on the header");
		}
		ExtentLogger.logPass("Test Case execution for - verifyDefaultTimeZoneOfNewRegisteredUser - is Completed.");
	}

	public void validationMsgAndChangeUsername(SoftAssert softAssert) {
		ExtentLogger.logInfo("74726, 74727, 74728, 74729, 74730, 74731 - Verify validation "
				+ "message for change username and change username successfully.");
		ExtentLogger.logInfo("Test Case execution for - validationMsgAndChangeUsername - is Initiated");
		softAssert.assertTrue(isUserNameVisible(), "User Name is not Visible");
		softAssert.assertTrue(isEditUserNameVisible(), "Edit field User Name is not visible ");
		softAssert.assertTrue(isEditUserNameVisible(), "User Name Edit Button is not Visible");
		softAssert.assertEquals(getEditUserNameLabel(), user.getUserName(), "User Name is not Matching.");
		softAssert.assertEquals(getUserNameLabel(), myprofileTextProp.getPropValue("UserNameLabel"));
		clickUserNameEditButton();
		softAssert.assertTrue(isUserNameInputFieldVisible(), "UserName Input Text field is not visible");
		softAssert.assertTrue(isChangeUserNameSaveBtnVisible(), "user Name Save Button is not visible");
		softAssert.assertTrue(isChangeUserNameCancelBtnVisible(), "user Name Cancel Button is not visible");
		registrationConfig = CSPConfiguration.initMCspConfig();
		/*
		 * String minCount = registrationConfig.get("Username").getFieldMinLength();
		 * String maxCount = registrationConfig.get("Username").getFieldMaxLength();
		 * String validationMsg1 = null; if (minCount.equals(maxCount)) { validationMsg1
		 * = Utils.getRbTextValue("txtSameLengthUserNameValidationMsgDbp");
		 * validationMsg1 = String.format(validationMsg1, minCount); } else {
		 * validationMsg1 =
		 * Utils.getRbTextValue("txtDifLengthUserNameValidationMsgDbp"); validationMsg1
		 * = String.format(validationMsg1, minCount, maxCount); }
		 */
		DashboardSteps dashboardSteps = new DashboardSteps(driver);
		String validationMsg2 = dashboardSteps.dashboardTextProp.getPropValue("txtNumber");
		String validationMsg3 = dashboardSteps.dashboardTextProp.getPropValue("txtAlphabet");
		String validationMsg4 = dashboardSteps.dashboardTextProp.getPropValue("txtSpecialChar");
		String validationMsg5 = dashboardSteps.dashboardTextProp.getPropValue("txtNoSpace");
		String userName = user.getUserId();
		String userName1 = user.getUserName();
		populatePwdForUpdateUserName(user.getPassword());
		clickChangeUserNameSaveButton();
		softAssert.assertTrue(getLblProfilePageGenericErrorMsgForFieldLabel().contains(validationMsg2));
		softAssert.assertTrue(getLblProfilePageGenericErrorMsgForFieldLabel().contains(validationMsg3));
		softAssert.assertTrue(getLblProfilePageGenericErrorMsgForFieldLabel().contains(validationMsg4));
		softAssert.assertTrue(getLblProfilePageGenericErrorMsgForFieldLabel().contains(validationMsg5));
		// After Changes in csp need to app pool restart and find the table wher flag
		// change of username Type
		/*
		 * int status=registrationConfig.get("Username"); if
		 * (registrationConfig.get("Username").equals("2")) { String errorMsg =
		 * getLblProfilePageGenericErrorMsgForFieldLabel();
		 * System.out.println("Error Message: " + errorMsg); //
		 * softAssert.assertTrue(getLblProfilePageGenericErrorMsgForFieldLabel().
		 * contains(validationMsg1));
		 * softAssert.assertTrue(getLblProfilePageGenericErrorMsgForFieldLabel().
		 * contains(validationMsg2));
		 * softAssert.assertTrue(getLblProfilePageGenericErrorMsgForFieldLabel().
		 * contains(validationMsg3));
		 * softAssert.assertTrue(getLblProfilePageGenericErrorMsgForFieldLabel().
		 * contains(validationMsg4));
		 * softAssert.assertTrue(getLblProfilePageGenericErrorMsgForFieldLabel().
		 * contains(validationMsg5)); userName = userName + "aa2@#$%*!_-."; } else if
		 * (registrationConfig.get("Username").equals("1")) { String errorMsg =
		 * getLblProfilePageGenericErrorMsgForFieldLabel();
		 * System.out.println("Error Message: " + errorMsg); //
		 * softAssert.assertTrue(getLblProfilePageGenericErrorMsgForFieldLabel(), //
		 * validationMsg1);
		 * softAssert.assertTrue(getLblProfilePageGenericErrorMsgForFieldLabel().
		 * contains(validationMsg2));
		 * softAssert.assertTrue(getLblProfilePageGenericErrorMsgForFieldLabel().
		 * contains("Alphabet")); userName = userName + "aa2"; } else { String errorMsg
		 * = getLblProfilePageGenericErrorMsgForFieldLabel();
		 * System.out.println("Error Message: " + errorMsg);
		 * softAssert.assertTrue(errorMsg.contains(" "), " ");
		 * softAssert.assertTrue(errorMsg.contains("Number")); //
		 * pageUtil.assertObjectLabelContains(dashboardPage.getLblGenericErrorMessage(),
		 * // "Alphabet"); userName = "1234567"; }
		 */
		ExtentLogger.logInfo("Test Case execution for - validationMsgAndChangeUsername - is Completed.");
		ExtentLogger.logPass("Verify validation message for change username and Change Username successfully");

	}

	public void verifyUpdateProfileInformation(SoftAssert softAssert) throws SQLException {
// All the Object Label On UI
		// User name
		String UserProfileNameOnUI = getValueNameLabel();
		String userNameOnUI = getEditUserNameLabel();
		String pwdOnUI = getProfilePasswordLabel();
		//String actprimaryP = user.getMobilePhone();
		String primaryPOnUI = getPrimaryPhoneLabel();
		String primaryP = getPrimaryPhoneLabel().replaceAll("[^0-9]", "");
		//String actSecondaryP = user.getHomePhone();
//		String secondaryPOnUI = null;
//		secondaryPOnUI = getSecondaryPhoneNumberLabel();
//		if (secondaryPOnUI != null) {
//			String secondaryP = getSecondaryPhoneNumberLabel().replaceAll("[^0-9]", "");
//		}
		//String actPrimaryEmail = user.getEmailId();
		String primaryEmailOnUI = getPrimaryEmailIdLabel();
		//String actSecondaryEmail = user.getAlternateEmailID();
		String secondaryEmailOnUI = null;
		//secondaryEmailOnUI = getSecondaryEmailIdLabel();
		// Max lenght of each Field on Profile Page
		clickPrimaryPhoneNumberEditButton();
		String primaryPhoneMaxLen = getPrimaryPhoneNumberMaxLength();

		//clickSecondaryPhoneNumberEditButton();
		//String secondaryPhoneMaxLen = getSecondaryPhoneNumberMaxLength();
		// clickSecondaryPhoneCancelButton();
		//clickPrimaryEmailIdEditButton();
		//String primaryEmailMaxLen = getPrimaryEmailIdMaxLength();
		//clickSecondaryEmailIdEditButton();
		//String secondaryEmailMaxLen = getSecondaryEmailIdMaxLength();

		// Update the information and save it and verify the changes
		//clickPrimaryPhoneNumberEditButton();
		clearPrimaryPhoneInputField();
		String randomPMob = RandomUtil.generateRandomString(10, RandomUtil.Mode.NUMERIC);
		//populatePrimaryPhone(randomPMob); //2121212155
		populatePrimaryPhone("2121212155"); //2121212155
		clickPrimaryPhoneNumberSaveButton();
		pause(500);
		if (randomPMob.charAt(0) == '0') {
			pause(10000);
			softAssert.assertEquals(getPrimayPhoneToastmsgLabel(),
					myprofileTextProp.getPropValue("txtMsgInvalidPrimaryMobileNumber"),
					"Updated p mob no Toaster msg  not matching when phone no start with o");
		} else {
			pause(10000);
			
		}
		//user.getMobilePhone();
		// user.setMobilePhone(actprimaryP);
//		pause(500);
//		clickSecondaryPhoneNumberEditButton();
//		clearSecondaryPhoneTextField();
//		String randomSecMob = RandomUtil.generateRandomString(10, RandomUtil.Mode.NUMERIC);
//		populateSecondaryPhone(randomSecMob);
//		clickSecondaryPhoneNumberSaveButton();
//		pause(500);
//		if (randomSecMob.charAt(0) == '0') {
//			pause(10000);
//			softAssert.assertEquals(getSecondaryPhoneToastmsgLabel(),myprofileTextProp.getPropValue("txtMsgInvalidSecondaryMobileNumber"),
//					"Updated Secondary mob no Toaster msg not matching when phone no start with o");
//		} else {
//			pause(10000);
//			softAssert.assertEquals(getSecondaryPhoneNumberLabel().replaceAll("[^0-9]", ""), randomSecMob,
//					"Updated Secondary mob no is not matching on UI");
//		}
		/*
		 * // user.setHomePhone(actSecondaryP); clickPrimaryEmailIdEditButton();
		 * clearPrimaryEmailTextBox(); String newPrimaryEmailId =
		 * RandomUtil.generateRandomString(5, RandomUtil.Mode.ALPHA) + "@yopmail.com";
		 * populatePrimaryEmailId(newPrimaryEmailId);
		 * populatePwdForChngPEmail(Configuration.toString("password"));
		 * clickPrimaryEmailIdSaveButton(); pause(10000);
		 * softAssert.assertEquals(getPrimaryEmailIdLabel(), newPrimaryEmailId,
		 * "Updated Primary Email id  is not matching on UI");
		 */
		// user.setEmailId(actPrimaryEmail);
//		pause(2000);
//		clickPrimaryEmailIdEditButton();
//		populatePwdForChngPEmail(RandomUtil.generateRandomString(5, RandomUtil.Mode.ALPHANUMERIC));
//		clickPrimaryEmailIdSaveButton();
//		pause(2000);
//		String toastMsg = getPrimayEmailToastmsgLabel();
//		softAssert.assertEquals(getEmailToastMessage(), myprofileTextProp.getPropValue("primaryEmailToastMsg"));
//
//		pause(1000);
//		clickPrimaryEmailIdCancelButton();
//		clickPrimaryEmailIdEditButton();
//		clearPrimaryEmailTextBox();
//		populatePrimaryEmailId(RandomUtil.generateRandomString(1, RandomUtil.Mode.ALPHA) + primaryEmailOnUI);
//		populatePwdForChngPEmail(RandomUtil.generateRandomString(8, RandomUtil.Mode.ALPHANUMERIC));
//		clickPrimaryEmailIdSaveButton();
//		pause(3000);
//		softAssert.assertEquals(getExistingPwdToastMessage(), myprofileTextProp.getPropValue("wrongPassValidateMsg"),
//				"While enterting wrong password in p email address updation an toast msg.");
//
//		clickSecondaryEmailIdEditButton();
//		if (getSecondaryEmailIdLabel() != null) {
//			String newSecondaryEmailId = RandomUtil.generateRandomString(3, RandomUtil.Mode.ALPHA) + "@yopmail.com";
//			clearSecondaryEmailTextBox();
//			populateSecondaryEmailId(newSecondaryEmailId);
//			clickSecondaryEmailIdSaveButton();
//			pause(1000);
//
//		}
	}

	public void verifyDropdownOnLinkDeLinkAccounts(SoftAssert softAssert) {
		ExtentLogger.logInfo(
				"To verify that the user is not able to login into the application when all accounts linked to it getting closed.");
		DashboardSteps dashboardSteps = new DashboardSteps(driver);
		softAssert.assertTrue(dashboardSteps.isDefaultAccountAddressVisible(),"Select Account Dropdown on DashBaoad is not visible.");
		String defaultAccountOnUI = dashboardSteps.getDefaultAccountAddressLabel();
		String defaultUtilityAccOnDB = dashboardSteps.getDefaultAccountSelected(user.getUserName());
		//softAssert.assertTrue(defaultAccountOnUI.contains(defaultUtilityAccOnDB),"Default Account no is not matching on ui with DB.");
		dashboardSteps.clickAccountDropDownButton();
		softAssert.assertTrue(isAccountDropDownListVisible(), "Account Drop Down is not visible.");

		registrationConfig = CSPConfiguration.initMCspConfig();
		if (registrationConfig.get("MyAccount") == 1) {
			// Verify navigation to MyAccount profile page.
			HomeSteps homeSteps = new HomeSteps(driver);
			homeSteps.navigateToMyProfilePage();
			softAssert.assertFalse(isAccountDropDownListVisible(),
					"service Account dropdown shoulnot be visible on MyProfile page but it is visible.");

			homeSteps.navigateToAccountInformation();
			softAssert.assertFalse(isAccountDropDownListVisible(),
					"service Account dropdown shoulnot be visible on account informaion page but it is visible.");

			// Link a residential account to the logged in user.

			
			ExtentLogger.logInfo(
					"To verify that the user is not able to login into the application when all accounts linked to it getting closed.");
			ExtentLogger.logInfo(
					"To verify that the user is not able to login into the application when all accounts linked to it getting closed.");
		}
		/**
		 * This method link an account of given type to the logged in user.
		 *
		 * @param sAccountType Whether residential or commercial.
		 * @return sUtilityAccountNumber which is linked.
		 */
	}

	public String linkAccountSteps(String sAccountType) {
		String sUtilityAccountNumber = null;
		// Verify page heading
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(getAccountInformationPageHeaderLabel(),
				myprofileTextProp.getPropValue("LblAccountInformation"), "Account Information label is not Matching.");
		clickLinkAccountBtn();
		pause(1000);
		driver.switchTo().activeElement();
		softAssert.assertTrue(getLinkAccountPopupHeadingVisible(),
				"Link Account Label on popuu Heading is not Visibel.");

		// Residential for linking a residential account
		String query = SQLQueries.getInfoForLinkingAccountFromDB(sAccountType, "Not Registered");
		Map<String, String> linkResidentialAccountInfo = new HashMap<>();
		try {
			ResultSet rs = DataBaseUtils.getResultSet(query);
//			linkResidentialAccountInfo = DataBaseUtils.tableResultInMapForm(rs);
			// rs.next();
			linkResidentialAccountInfo.put("UtilityAccountNumber", rs.getString("UtilityAccountNumber"));
			linkResidentialAccountInfo.put("ZipCode", rs.getString("ZipCode"));
			linkResidentialAccountInfo.put("Meter Number", rs.getString("MeterNumber"));
			linkResidentialAccountInfo.put("SSN", rs.getString("SSNNumber"));
			// linkResidentialAccountInfo.put("MeterNumber", rs.getString("Address1,1234"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		// clickLnkAccResidentialPopup();

		if (registrationConfig.get("Account Number").equals("1")) {
			sUtilityAccountNumber = linkResidentialAccountInfo.get("UtilityAccountNumber");
			populateAccountNumberInLinkAccountPopup(sUtilityAccountNumber);
		}
		if (registrationConfig.get("Zip Code").equals("1"))
			populateZipCodeInLinkAccountPopup(linkResidentialAccountInfo.get("ZipCode"));

		if (registrationConfig.get("Meter Number").equals("1")) {
			populateMeterNumberInLinkAccountPopup(linkResidentialAccountInfo.get("MeterNumber"));
		}

		if (registrationConfig.get("SSN").equals("1")) {
			populateSSNInLinkAccountPopup("1234");

		}

		if (registrationConfig.get("Street Address").equals("1")) {
			populateStreetAddInLinkAccountPopup(linkResidentialAccountInfo.get("Address1"));
		}
		clickLinkAccountSubmitBtn();
		String successfulToastMessage = getSuccessfulToastMessage();
		softAssert.assertEquals(successfulToastMessage,
				myprofileTextProp.getPropValue("textSuccessfulLinkedAccountMsg"),
				"Toast message for link account not matched.");
		pause(2000);
		softAssert.assertTrue(getLinkAccountPopupHeadingVisible(), "Link Account Popup Heading is not Visible.");
		DriverFactory.refreshBrowser();
		waitForPageToLoad();
		return sUtilityAccountNumber;
	}

	public void deLinkAccountSteps(String sUtilityAccountNo) {
		// Deleting linked account

		WebElement element = getLinkAccountBtnElement();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", element);

		clickThreeDotsToggleBtn(sUtilityAccountNo);
		clickUnlinkAccountBtn(sUtilityAccountNo);
		clickContinueDeletePopup();

		// Unable to verify Delete account successful message with below code
		pause(2000);
		DriverFactory.refreshBrowser();

	}
}
package demo.steps.scp;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import sew.ai.config.Configuration;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.reporters.ExtentLogger;
import demo.pageobjects.AboutMyHomePage;
import sew.ai.utils.PropertiesUtil;
import java.io.IOException;
import java.sql.SQLException;
import org.openqa.selenium.support.ui.Select;

public class AboutMyHomeSteps extends AboutMyHomePage {
	public static PropertiesUtil myaccountAboutMyHomeTextProp;

	public AboutMyHomeSteps(WebDriver driver) {
		super(driver);
		myaccountAboutMyHomeTextProp = new PropertiesUtil(
				FilePaths.SCP_TEXT_PROPERTIES + Constants.MYACCOUNT_ABOUTMYHOME_TXT_FILENAME);
	}

	public void verifyAboutMyHomePageUI(SoftAssert softAssert) throws SQLException {
		pause(5000);

		ExtentLogger.logInfo("Verify About My Home Page UI");
		softAssert.assertTrue(isAccountNumberVisible(), "Account Number is not visible");
		softAssert.assertTrue(isHomeTypeFieldVisible(), "Home Type Field is not visible");
		softAssert.assertTrue(isHowManyPeopleFieldVisible(), "How Many People Field is not visible");
		softAssert.assertTrue(isHaveSolarPanelsVisible(), "Have Solar Panel Field is not visible");
		softAssert.assertTrue(isYourHomeSizeVisible(), "Your Home Size Field is not visible");
		softAssert.assertTrue(isHowManyFloorsFieldVisible(), "How Many Floors Field is not visible");
		softAssert.assertTrue(isDoYouOwnEVToggleVisible(), "Do You Own EV Toggle is not visible");
		softAssert.assertTrue(isYearOfHouseBuildFieldVisible(), "Year Of House Build Field is not visible");
		softAssert.assertTrue(isHowManyBathroomsFieldVisible(), "How Many Bathrooms Field is not visible");
		softAssert.assertTrue(isHowManyHighEfficiencyAppsFieldVisible(), "How Many High Efficiency Apps Field is not visible");
		softAssert.assertTrue(isLotSizeFieldVisible(), "Lot Size Field is not visible");
		softAssert.assertTrue(isLandscapeAreaFieldVisible(), "Landscape Area Field is not visible");
		softAssert.assertTrue(isSizeOfSpecialLandscapeFieldVisible(), "Size Of Special Landscape Field is not visible");
		softAssert.assertTrue(isSwimmingPoolToggleVisible(), "Swimming Pool Toggle is not visible");
		softAssert.assertTrue(isAboutMyHomeBannerVisible(), "About My Home Banner is not visible");
		softAssert.assertTrue(isContactUsBannerVisible(), "Contact Us Banner is not visible");
		log.info("Test Case execution for - verifyAboutMyHomePageUI - is Completed");
	}

	public void verifyAboutMyHomeFormSubmission(SoftAssert softAssert) throws IOException, SQLException {

		ExtentLogger.logInfo("Entering data in the do you have solar panels field.");
		enterDataInSolarPanelField("20");
		ExtentLogger.logInfo("Entering data in the what is your home size field.");
		enterDataInHomeSizeField("21");
		ExtentLogger.logInfo("Entering data in the how many floors does your home have field.");
		enterDataInFloorsField("3");
		ExtentLogger.logInfo("Verify that customer is able to submit the About My Home form successfully.");
		clickAboutMyHomeFormSubmitbtn();
		softAssert.assertEquals(getSubmitFormPopUpLabel(),
				myaccountAboutMyHomeTextProp.getPropValue("successfulformsubmitmssg"));
		log.info("Test Case execution for - verifyAboutMyHomeFormSubmission - is Completed");
	}

	public void verifyMandatoryFieldForFormSubmission(SoftAssert softAssert) throws IOException, SQLException {

		ExtentLogger.logInfo("Verify that customer is able to click home type field successfully.");
		clickHomeTypeField();
		ExtentLogger.logInfo("Verify that customer is able to choose 'Select' option for home type field successfully.");
	    chooseSelectOption();
		ExtentLogger.logInfo(
				"Verify that customer is unable to submit the About My Home form without making selection on manadatory field.");
		clickAboutMyHomeFormSubmitbtn();
		pause(5000);
		Assert.assertTrue(isMyAccountAboutMyHomePage(myaccountAboutMyHomeTextProp.getPropValue("expectedAboutMyHomePageUrl"),
                        myaccountAboutMyHomeTextProp.getPropValue("expectedAboutMyHomePageTitle")));
		log.info("Test Case execution for - verifyMandatoryFieldForFormSubmission - is Completed");

	}	
	
	public void verifyInfoIconInformation(SoftAssert softAssert) throws IOException, SQLException {

		ExtentLogger.logInfo("Verify that customer is able to see the info on i icon for solar panel field.");
		isIconSolarPanelsVisible();
		pause(1000);
		clickIconSolarPanels();
		softAssert.assertEquals(getSolarPanelInfoIcon(),
				myaccountAboutMyHomeTextProp.getPropValue("solarPanelInfoIcon"));
		ExtentLogger.logInfo("Verify that customer is able to see the info on i icon for High efficiency appliances field.");
		isIconHighEfficiencyVisible();
		pause(1000);
		clickIconHighEfficiency();
		softAssert.assertEquals(getHighEfficiencyInfoIcon(),
				myaccountAboutMyHomeTextProp.getPropValue("highEfficiencyInfoIcon"));
		ExtentLogger.logInfo("Verify that customer is able to see the info on i icon for Lot Size field.");
		isIconLotSizeVisible();
		pause(1000);
		clickIconLotSize();
		softAssert.assertEquals(getLotSizeInfoIcon(),
				myaccountAboutMyHomeTextProp.getPropValue("lotSizeInfoIcon"));
		ExtentLogger.logInfo("Verify that customer is able to see the info on i icon for Landscape Area field.");
		isIconLandscapeAreaVisible();
		pause(1000);
		clickIconLandscapeArea();
		softAssert.assertEquals(getLandscapeAreaInfoIcon(),
				myaccountAboutMyHomeTextProp.getPropValue("landscapeAreaInfoIcon"));
		ExtentLogger.logInfo("Verify that customer is able to see the info on i icon for Landscape Size field.");
		isIconLandscapeSizeVisible();
		pause(1000);
		clickIconLandscapeSize();
		softAssert.assertEquals(getLandscapeSizeInfoIcon(),
				myaccountAboutMyHomeTextProp.getPropValue("landSizeAreaInfoIcon"));
		log.info("Test Case execution for - verifyInfoIconInformation - is Completed");
	}
	
	public void verifyFieldLenghtVal(SoftAssert softAssert) {
		String solarPanelMaxLength = getTxtBoxSolarPanelMaxLength();
		String homeSizeMaxLength = getTxtBoxYourHomeSizeMaxLength();
		String floorNoMaxLength = getTxtBoxHowManyFloorsMaxLength();
		String houseBuildYrMaxLength = getTxtBoxHouseBuildYrMaxLength();
		String bathroomNoMaxLength = getTxtBoxHowManyBathMaxLength();
		String highEfficiencyAppMaxLength = getTxtBoxHowManyHighEfficiencyMaxLength();
		String lotSizeMaxLength = getTxtBoxLotSizeMaxLength();
		String landscaoeAreaMaxLength = getTxtBoxLandscapeAreaMaxLength();
		String landscapeSizeMaxLength = getTxtBoxLandscapeSizeMaxLength();
		softAssert.assertEquals(solarPanelMaxLength, "2", "Max length field not matched.");
		softAssert.assertEquals(homeSizeMaxLength, "5", "Max length field not matched.");
		softAssert.assertEquals(floorNoMaxLength, "2", "Max length field not matched.");
		softAssert.assertEquals(houseBuildYrMaxLength, "4", "Max length field not matched.");
		softAssert.assertEquals(bathroomNoMaxLength, "2", "Max length field not matched.");
		softAssert.assertEquals(highEfficiencyAppMaxLength, "2", "Max length field not matched.");
		softAssert.assertEquals(lotSizeMaxLength, "5", "Max length field not matched.");
		softAssert.assertEquals(landscaoeAreaMaxLength, "5", "Max length field not matched.");
		softAssert.assertEquals(landscapeSizeMaxLength, "5", "Max length field not matched.");
		ExtentLogger.logInfo("verifyFieldLenghtVal Passed");
	
}
	
	public void verifyFieldInputModeVal(SoftAssert softAssert) {
		String solarPanelInputMode = getTxtBoxSolarPanelInputMode();
		String homeSizeInputMode = getTxtBoxYourHomeSizeInputMode();
		String floorNoInputMode = getTxtBoxHowManyFloorsInputMode();
		String houseBuildYrInputMode = getTxtBoxHouseBuildYrInputMode();
		String bathroomNoInputMode = getTxtBoxHowManyBathInputMode();
		String highEfficiencyAppInputMode = getTxtBoxHowManyHighEfficiencyInputMode();
		String lotSizeInputMode = getTxtBoxLotSizeInputMode();
		String landscaoeAreaInputMode = getTxtBoxLandscapeAreaInputMode();
		String landscapeSizeInputMode = getTxtBoxLandscapeSizeInputMode();
		softAssert.assertEquals(solarPanelInputMode, "numeric", "Input Mode field not matched.");
		softAssert.assertEquals(homeSizeInputMode, "numeric", "Input Mode field not matched.");
		softAssert.assertEquals(floorNoInputMode, "numeric", "Input Mode field not matched.");
		softAssert.assertEquals(houseBuildYrInputMode, "numeric", "Input Mode field not matched.");
		softAssert.assertEquals(bathroomNoInputMode, "numeric", "Input Mode field not matched.");
		softAssert.assertEquals(highEfficiencyAppInputMode, "numeric", "Input Mode field not matched.");
		softAssert.assertEquals(lotSizeInputMode, "numeric", "Input Mode field not matched.");
		softAssert.assertEquals(landscaoeAreaInputMode, "numeric", "Input Mode field not matched.");
		softAssert.assertEquals(landscapeSizeInputMode, "numeric", "Input Mode field not matched.");
		ExtentLogger.logInfo("verifyFieldInputModeVal Passed");
}
	
	public void verifyFieldInputZeroVal(SoftAssert softAssert) throws IOException, SQLException {

		ExtentLogger.logInfo("Entering 0 in the do you have solar panels field.");
		enterDataInSolarPanelField("0");
		ExtentLogger.logInfo("Entering 0 in the what is your home size field.");
		enterDataInHomeSizeField("0");
		ExtentLogger.logInfo("Entering 0 in the how many floors does your home have field.");
		enterDataInFloorsField("0");
		ExtentLogger.logInfo("Entering 0 in the house built year field.");
		enterDataInYrOfBuiltField("0");
		ExtentLogger.logInfo("Entering 0 in the no. of bathrooms field.");
		enterDataInBathroomNoField("0");
		ExtentLogger.logInfo("Entering 0 in the hight efficiency application field.");
		enterDataInHighEfficiencyField("0");
		ExtentLogger.logInfo("Entering 0 in the lot size field.");
		enterDataInLotSizeField("0");
		ExtentLogger.logInfo("Entering 0 in the landscape area field.");
		enterDataInLandscapeAreaField("0");
		ExtentLogger.logInfo("Entering 0 in the landscape size field.");
		enterDataInLandscapeSizeField("0");
		ExtentLogger.logInfo("Verify that customer is able to submit the About My Home form successfully.");
		clickAboutMyHomeFormSubmitbtn();
		softAssert.assertEquals(getZeroValPopUpLabel(),
				myaccountAboutMyHomeTextProp.getPropValue("zerovalpopupmssg"));
		log.info("Test Case execution for - verifyFieldInputZeroVal - is Completed");
	}
	
	public void verifyValidHouseBuildYrVal(SoftAssert softAssert) throws IOException, SQLException {

		ExtentLogger.logInfo("Entering less than 1900 in the house built year field.");
		enterDataInYrOfBuiltField("1899");
		clickAboutMyHomeFormSubmitbtn();
		softAssert.assertEquals(get1900PopUpLabel(),
				myaccountAboutMyHomeTextProp.getPropValue("buildYearLessThan1900"));
		log.info("Test Case execution for - verifyValidHouseBuildYrVal - is Completed");
	}
			
}


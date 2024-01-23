package demo.steps.scp;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
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

	}	
}

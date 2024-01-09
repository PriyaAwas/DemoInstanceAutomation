package sew.ai.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import sew.ai.config.SCPConfiguration;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.models.User;
import sew.ai.pageObjects.scp.MarketingPreferencePage;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.PropertiesUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class MarketingPreferenceSteps extends MarketingPreferencePage {
    private static final Logger log = LogManager.getLogger(MarketingPreferenceSteps.class);
    public static PropertiesUtil marketingPreferenceTextProp;
    public static LinkedList<Integer> prefId =new LinkedList();
    public static Map<Integer, Integer> marketingPrefId = new LinkedHashMap<>();
    int  numberOfCheckMarketingPrefBox=0;
    public MarketingPreferenceSteps(WebDriver driver) {
        super(driver);
        marketingPreferenceTextProp = new PropertiesUtil(
                FilePaths.SCP_TEXT_PROPERTIES + Constants.MARKETING_PREF_TXT_FILENAME);
    }
    public void verifyMarketingPreferencesObjects(SoftAssert softAssert) {
        Assert.assertTrue(isMarketingPreferencesPage(
                marketingPreferenceTextProp.getPropValue("marketingPageUrl"),
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
        softAssert.assertTrue(isCoolTipsBrochuresCheckBoxVisible(),
                "Cool Tips Brochures Check Box is not Visible");
        softAssert.assertTrue(isCommunityBenefitProgramsCheckBoxVisible(),
                "Community Benefit Programs Check Box is not Visible");
        //Marketing Preference Object Label Verification
        softAssert.assertEquals(getMarketingPreferenceLabel(), marketingPreferenceTextProp
                        .getPropValue("marketingPageHeader"),
                "Marketing Preference Title on My Profile Page is not Matched");
        softAssert.assertEquals(getMarketingPreferenceDescLabel(), marketingPreferenceTextProp
                        .getPropValue("marketingPreferenceDesc"),
                "Marketing Preference Description is Not Matching");
        softAssert.assertEquals(getNewsReleasesLabel(), marketingPreferenceTextProp
                        .getPropValue("lblNewsRelease"),
                "News Release Label is not  Matching");
        softAssert.assertEquals(getServiceOfferingsLabel(), marketingPreferenceTextProp
                        .getPropValue("lblServiceOffering"),
                "Service Offering Label is not Matching");
        softAssert.assertEquals(getNewslettersLabel(), marketingPreferenceTextProp
                        .getPropValue("lblNewsLetter"),
                "News Letter Label is not matching");
        softAssert.assertEquals(getEnergySavingsToolkitsLabel(), marketingPreferenceTextProp
                        .getPropValue("lblEnergySavingsToolkits"),
                "Energy Saving Toolkits Label is not matching");
        softAssert.assertEquals(getCoolTipsBrochuresLabel(), marketingPreferenceTextProp
                        .getPropValue("lblCoolTipsBrochures"),
                "Cool Tips Brochures is not matching");
        softAssert.assertEquals(getCommunityBenefitProgramsLabel(), marketingPreferenceTextProp
                        .getPropValue("lblCommunityBenefitPrograms"),
                "Community Benefit Program Label is not matching");
        //Marketing Preference Object's CheckBox Validation Checked Or Not
        softAssert.assertTrue(isNewsReleasesBoxChecked(),
                "News Releases Check Box is not checked");
        softAssert.assertTrue(isServiceOfferingsBoxChecked(),
                "Service Offering Check Box is not checked");
        softAssert.assertTrue(isNewslettersBoxChecked(),
                "Newsletter Check Box is not checked");
        softAssert.assertTrue(isEnergySavingsToolkitsBoxChecked(),
                "Energy Saving Toolkits Check Box is not checked");
        softAssert.assertTrue(isCoolTipsBrochuresBoxChecked(),
                "Cool Tips Brochures Check Box is not checked");
        softAssert.assertTrue(isCommunityBenefitProgramsBoxChecked(),
                "Community Benefit Programs Check Box is not checked");
        softAssert.assertTrue(isSetPreferenceBtnClickable(),
                "Set Preference Button is not Clickable");
        // Verify the number of Marketing Pref visible
        int marketingPrefCount=getMarketingPrefElem().size();
        softAssert.assertEquals(isMarketingPrefElemVisible(),marketingPrefCount,
                "All marketing pref is not Visible");
        // Verify DB validation
    }
    public int isMarketingPrefElemVisible(){
        int numberOfVisibleMarketingPrefElem=0;
        for( WebElement getMarketingPrefElem1 :getMarketingPrefElem()) {
            if(isElementVisible(getMarketingPrefElem1)==true) {
                numberOfVisibleMarketingPrefElem++;}
        }
        return numberOfVisibleMarketingPrefElem;
    }
    public void verifyMarketingPreferencesNewUser(SoftAssert softAssert) throws SQLException {
        // if welcome screen appears then close it
        DashboardSteps dashboardSteps=new DashboardSteps(driver);
        User user= SCPConfiguration.user;
        try {
            if(dashboardSteps.isAboutMyHomePopupVisible()) {
                dashboardSteps.bypassTheAboutMyHomePage();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        HomeSteps homeSteps = new HomeSteps(driver);
        homeSteps.navigateToMarketingPreferencePage();
        Assert.assertTrue(isMarketingPreferencesPage(
                marketingPreferenceTextProp.getPropValue("marketingPageUrl"),
                marketingPreferenceTextProp.getPropValue("marketingPageTitle")),"Marketing Preference Page is not Found"
        );
        //Verify All 6 Marketing Pref is checked on UI as well in DB
       String marketingPrefQuery= SQLQueries.getMarketingPrefQuery(user.getUserName());
       int noOfMarkPrefCheckedOnDB=0;
           ResultSet resultSet = DataBaseUtils.getResultSet(marketingPrefQuery);
           while(resultSet.next()) {
               String prefId = resultSet.getString("PreferenceId");
               if(prefId.equals("1")){
                   softAssert.assertTrue(isNewsReleasesBoxChecked()," News Release is Showing in DB but not  selected on UI");
               noOfMarkPrefCheckedOnDB++;}
               else if (prefId.equals("2")){
                  softAssert.assertTrue(isServiceOfferingsBoxChecked(),"Service Offering is Showing in DB but not  selected on UI");
                   noOfMarkPrefCheckedOnDB++; }
               else if (prefId.equals("3")){
                   softAssert.assertTrue(isNewslettersBoxChecked(),"News Letter is Showing in DB but not  selected on UI");
                   noOfMarkPrefCheckedOnDB++; }
               else if (prefId.equals("4")){
                   softAssert.assertTrue(isEnergySavingsToolkitsBoxChecked(),"Energy Saving Tool kits is Showing in DB but not  selected on UI");
                   noOfMarkPrefCheckedOnDB++; }
               else if (prefId.equals("5")){
                   softAssert.assertTrue(isCoolTipsBrochuresBoxChecked(),"Cool Tips Brochure Box is Showing in DB but not  selected on UI");
                   noOfMarkPrefCheckedOnDB++; }
               else if (prefId.equals("6")){
                   softAssert.assertTrue(isCommunityBenefitProgramsBoxChecked(),"Community Benefit Programs  is Showing in DB but not  selected on UI");
                   noOfMarkPrefCheckedOnDB++; }
               }
           softAssert.assertEquals(noOfMarkPrefCheckedOnDB,6,
                   "There are"+noOfMarkPrefCheckedOnDB+"marketing Pref is showing in DB out of 6");

           // Validation all Marketing Preference Check Box for Newly Registered User should be Checked
        int marketingPrefCount=getmarketingprefCheckBox().size();
        Assert.assertEquals(isAllMarketingPrefBoxCheked(),marketingPrefCount,
                "ALl Marketing Preference Checked Box is Not Checked For Newly Registered User");
        // Verify that all 6 Marketing Preference are visible
        int marketingPrefCount1=getMarketingPrefElem().size();
        softAssert.assertEquals(isMarketingPrefElemVisible(),marketingPrefCount1,
                "All marketing pref is not Visible");
        //  UnChecked All Marketing Preferences
        unCheckAllMarketingPrefBox();
        clickSetPreferenceButton();
        waitForPageLoader();
        softAssert.assertTrue(isAllMarketingPrefBoxCheked()==0,"Doing uncheck all marketing Pref but Some are not able to uncheck ");
        pause(3000);
        //Checked All Marketing Preference
        checkAllMarketingPrefBox();
        clickSetPreferenceButton();}
    public int isAllMarketingPrefBoxCheked(){
        int numberOfCheckMarketingPrefBox=0;
        for( WebElement marketingprefCheckBox1 :getmarketingprefCheckBox()) {
            if(marketingprefCheckBox1.isSelected()==true) {
                numberOfCheckMarketingPrefBox++;}}
        return numberOfCheckMarketingPrefBox;}
}
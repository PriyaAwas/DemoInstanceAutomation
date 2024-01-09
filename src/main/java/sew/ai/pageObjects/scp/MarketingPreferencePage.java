package sew.ai.pageObjects.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MarketingPreferencePage extends HomePage {
    private static final Logger log = LogManager.getLogger(MarketingPreferencePage.class);

    public MarketingPreferencePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = "div.MarketingPrefContainer h5")
    private WebElement lblHeadingMarketingPreferences;
    public String getMarketingPreferenceLabel() {
        String label = getText(lblHeadingMarketingPreferences);
        log.info("Marketing Preferences Label  is : " + label);
        return label;
    }
    @FindBy(css = "div.lft_profile_sec>div>h4")
    private WebElement lblMarketingPreferencesDesc;
    public String getMarketingPreferenceDescLabel() {
        String label = getText(lblMarketingPreferencesDesc);
        log.info("Marketing Preferences Description Label  is : " + label);
        return label;
    }
    @FindBys(@FindBy(css = "ul.MPLists.MainConst input"))
    private List<WebElement> lstChkBoxMarketingPreferences;
     public List<WebElement> getLstChkBoxMarketingPreferences(){
         return lstChkBoxMarketingPreferences;
    }

    public void unCheckAllMarketingPrefBox(){
        for( WebElement lstChkBoxMarketingPreferences1 :getLstChkBoxMarketingPreferences())
         unCheck(lstChkBoxMarketingPreferences1);
        log.info("Marketing Preference Check Box has been Successfully UnChecked");
    }
    public void checkAllMarketingPrefBox(){
        for( WebElement lstChkBoxMarketingPreferences1 :getLstChkBoxMarketingPreferences())
            check(lstChkBoxMarketingPreferences1);
        log.info("Marketing Preference Check Box has been Successfully UnChecked");
    }
    @FindBys(@FindBy (css="div[class='radio-button-box chkboxCustomact']"))
    private List<WebElement> MarketingPrefElem;
    public List<WebElement> getMarketingPrefElem(){return MarketingPrefElem;}
    
    @FindBys(@FindBy (css="label[class='mtrl_chkbx_new'] span [type='checkbox']"))
    private List<WebElement> marketingprefCheckBox;
    public List<WebElement> getmarketingprefCheckBox(){return marketingprefCheckBox;}

    @FindBy(css = "div.MPButtonRightArea>input")
    private WebElement btnMarketingPreferencesSave;
    public void clickSetPreferenceButton(){
        click(btnMarketingPreferencesSave);
        log.info("Set Preference Button has been Successfully Clicked");
    }
    public boolean isSetPreferenceBtnClickable(){
     return isElementEnabled(btnMarketingPreferencesSave);
    }

    @FindBy(css = "#lblID1")
    private WebElement lblChkBoxNewsReleases;
    public String getNewsReleasesLabel() {
        String label = getText(lblChkBoxNewsReleases);
        log.info("News Releases  Label  is On Marketing Preference Page: " + label);
        return label;
    }
    public boolean isNewsReleasesVisible() {
        log.info("Checking the Visibility of News Releases Status ."+ lblChkBoxNewsReleases.isDisplayed());
        return isElementVisible(lblChkBoxNewsReleases);
    }
    @FindBy(css = "#lblID2")
    private WebElement lblChkBoxServiceOfferings;
    public String getServiceOfferingsLabel() {
        String label = getText(lblChkBoxServiceOfferings);
        log.info("Service Offerings  Label  is On Marketing Preference Page : " + label);
        return label;
    }
    public boolean isServiceOfferingsVisible() {
        log.info("Checking the Visibility of Service Offerings Status ."+ lblChkBoxServiceOfferings.isDisplayed());
        return isElementVisible(lblChkBoxServiceOfferings);
    }
    @FindBy(css = "#lblID3")
    private WebElement lblChkBoxNewsletters;
    public String getNewslettersLabel() {
        String label = getText(lblChkBoxNewsletters);
        log.info("News letters  Label  is On Marketing Preference Page : " + label);
        return label;
    }
    public boolean isNewslettersVisible() {
        log.info("Checking the Visibility of Newsletters Status ."+ lblChkBoxNewsletters.isDisplayed());
        return isElementVisible(lblChkBoxNewsletters);
    }
    @FindBy(css = "#lblID4")
    private WebElement lblChkBoxEnergySavingsToolkits;
    public String getEnergySavingsToolkitsLabel() {
        String label = getText(lblChkBoxEnergySavingsToolkits);
        log.info("Energy Savings Tool kits  Label  is On Marketing Preference Page : " + label);
        return label;
    }
    public boolean isEnergySavingsToolkitsVisible() {
        log.info("Checking the Visibility of Energy Savings Toolkits Status ."+ lblChkBoxEnergySavingsToolkits.isDisplayed());
        return isElementVisible(lblChkBoxEnergySavingsToolkits);
    }
    @FindBy(css = "#lblID5")
    private WebElement lblChkBoxCoolTipsBrochures;
    public String getCoolTipsBrochuresLabel() {
        String label = getText(lblChkBoxCoolTipsBrochures);
        log.info("Cool Tips Brochures  Label  is On Marketing Preference Page : " + label);
        return label;
    }
    public boolean isCoolTipsBrochuresVisible() {
        log.info("Checking the Visibility of Cool Tips Brochures Status ."+ lblChkBoxCoolTipsBrochures.isDisplayed());
        return isElementVisible(lblChkBoxCoolTipsBrochures);
    }
    @FindBy(css = "#lblID6")
    private WebElement lblChkBoxCommunityBenefitPrograms;
    public String getCommunityBenefitProgramsLabel() {
        String label = getText(lblChkBoxCommunityBenefitPrograms);
        log.info("Community Benefit Programs  Label  is On Marketing Preference Page : " + label);
        return label;
    }
    public boolean isCommunityBenefitProgramsVisible() {
        log.info("Checking the Visibility of Community Benefit Programs Status ."+ lblChkBoxCommunityBenefitPrograms.isDisplayed());
        return isElementVisible(lblChkBoxCommunityBenefitPrograms);
    }
    @FindBy(css = "input#chkID1")
    private WebElement chkBoxNewsReleases;
    public boolean isNewsReleasesBoxChecked(){
        // log.info("News Releases Check Box is Already Selected"+chkBoxNewsReleases.isSelected());
        return chkBoxNewsReleases.isSelected();

    }
    public boolean isNewsReleasesCheckBoxVisible() {
        log.info("Checking the Visibility of Newsletters Check Box ."+ chkBoxNewsReleases.isDisplayed());
        return isElementVisible(chkBoxNewsReleases);
    }

    @FindBy(css = "input#chkID2")
    private WebElement chkBoxServiceOfferings;
    public boolean isServiceOfferingsBoxChecked(){
        // log.info("Service Offerings Check Box is Already Selected"+chkBoxNewsReleases.isSelected());
        return chkBoxServiceOfferings.isSelected();

    }
    public boolean isServiceOfferingsCheckBoxVisible() {
        log.info("Checking the Visibility of Service Offerings Check Box ."+ chkBoxServiceOfferings.isDisplayed());
        return isElementVisible(chkBoxServiceOfferings);
    }
    @FindBy(css = "input#chkID3")
    private WebElement chkBoxNewsletters;
    public boolean isNewslettersBoxChecked(){
        // log.info("Service Offerings Check Box is Already Selected"+chkBoxNewsReleases.isSelected());
        return chkBoxNewsletters.isSelected();

    }
    public boolean isNewslettersCheckBoxVisible() {
        log.info("Checking the Visibility of Newsletters Check Box ."+ chkBoxNewsletters.isDisplayed());
        return isElementVisible(chkBoxNewsletters);
    }
    @FindBy(css = "input#chkID4")
    private WebElement chkBoxEnergySavingsToolkits;
    public boolean isEnergySavingsToolkitsBoxChecked(){
        // log.info("Service Offerings Check Box is Already Selected"+chkBoxNewsReleases.isSelected());
        return chkBoxEnergySavingsToolkits.isSelected();
    }
    public boolean isEnergySavingsToolkitsCheckBoxVisible() {
        log.info("Checking the Visibility of Energy Savings Toolkits Check Box ."+ chkBoxEnergySavingsToolkits.isDisplayed());
        return isElementVisible(chkBoxEnergySavingsToolkits);
    }
    @FindBy(css = "input#chkID5")
    private WebElement chkBoxCoolTipsBrochures;
    public boolean isCoolTipsBrochuresBoxChecked(){
        // log.info("Service Offerings Check Box is Already Selected"+chkBoxNewsReleases.isSelected());
        return chkBoxCoolTipsBrochures.isSelected();
    }
    public boolean isCoolTipsBrochuresCheckBoxVisible() {
        log.info("Checking the Visibility of Cool Tips Brochures Check Box ."+ chkBoxCoolTipsBrochures.isDisplayed());
        return isElementVisible(chkBoxCoolTipsBrochures);
    }
    @FindBy(css = "input#chkID6")
    private WebElement chkBoxCommunityBenefitPrograms;
    public boolean isCommunityBenefitProgramsBoxChecked(){
        // log.info("Service Offerings Check Box is Already Selected"+chkBoxNewsReleases.isSelected());
        return chkBoxCommunityBenefitPrograms.isSelected();
    }
    public boolean isCommunityBenefitProgramsCheckBoxVisible() {
        log.info("Checking the Visibility of Community Benefit Programs Check Box ."+ chkBoxCommunityBenefitPrograms.isDisplayed());
        return isElementVisible(chkBoxCommunityBenefitPrograms);
    }
    public boolean isMarketingPreferencesPage(String url, String title){
        boolean isMarketingPreferencesPage=false;
        log.info("Checking that the current page is My Profile/Marketing Preference page.");
        if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
            isMarketingPreferencesPage = true;
        log.info("The current page is My Profile/Marketing Preference {}: " + isMarketingPreferencesPage);
        return isMarketingPreferencesPage;
    }


    @FindBy(css = ".toast-message")
    private WebElement lblSuccessSaveMessage;
    @FindBy(css = "label[for='chkID1']")
    private WebElement chkNewsReleases;
    @FindBy(css = "label[for='chkID2']")
    private WebElement chkServiceOfferings;
    @FindBy(css = "label[for='chkID3']")
    private WebElement chkNewsletters;
    @FindBy(css = "label[for='chkID4']")
    private WebElement chkEnergySavingsToolkits;
    @FindBy(css ="label[for='chkID5']")
    private WebElement chkCoolTipsBrochures;
    @FindBy(css = "label[for='chkID6']")
    private WebElement chkCommunityBenefitPrograms;

    @FindBy(css = ".checkbox span[globalize='ML_MYACCOUNT_MktPrefer_NewsReleases']")
    private WebElement toolTipNewsReleases;
    @FindBy(css = ".checkbox span[globalize='ML_MYACCOUNT_MktPrefer_ServiceOfferings']")
    private WebElement toolTipServiceOfferings;
    @FindBy(css = ".checkbox span[globalize='ML_MYACCOUNT_MktPrefer_Newsletters']")
    private WebElement toolTipNewsletters;
    @FindBy(css = ".checkbox span[globalize='ML_MYACCOUNT_MktPrefer_ESToolkits']")
    private WebElement toolTipEnergySavingsToolkits;
    @FindBy(css = ".checkbox span[globalize='ML_MYACCOUNT_MktPrefer_CoolTipsBrochures']")
    private WebElement toolTipCoolTipsBrochures;
    @FindBy(css = ".checkbox span[globalize='ML_MYACCOUNT_MktPrefer_CBPrograms']")
    private WebElement toolTipCommunityBenefitPrograms;
    /*for(WebElement checkBox:checkBoxes) {
        verifyChkBoxSelected(checkBox);
    }
    public void verifyChkBoxSelected(WebElement element) {
        try {
            boolean chkboxValue = element.isSelected();
            log.info("The checkbox value is: " + chkboxValue);
            Assert.assertTrue(chkboxValue);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }*/
}

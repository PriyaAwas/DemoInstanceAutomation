package sew.ai.pageObjects.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AboutMyHomePage extends HomePage {
    private static final Logger log = LogManager.getLogger(AboutMyHomePage.class);

    public AboutMyHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#Span_HomeBusiness")
    private WebElement lblPageHeader;

    public Boolean isPageHeaderLabelVisible() {
        log.info("Page header label visibility status : " + lblPageHeader.isDisplayed());
        return lblPageHeader.isDisplayed();
    }

    public String getPageHeaderLabel() {
        String label = getText(lblPageHeader);
        log.info("About My Home page header is : " + label);
        return label;
    }

    public void waitPageHeaderLabel() {
        waitForElementToBeVisible(lblPageHeader);
        log.info("Page header label is visible now.");
    }

    @FindBy(css = "#Span_HomeBusiness")
    private WebElement lblStep1PageHeader;

    public Boolean isStep1PageHeaderLabelVisible() {
        log.info("Step 1 page header label visibility status : " + lblStep1PageHeader.isDisplayed());
        return lblStep1PageHeader.isDisplayed();
    }

    public String getStep1PageHeaderLabel() {
        String label = getText(lblStep1PageHeader);
        log.info("About My Home step 1 page header is : " + label);
        return label;
    }
    
    @FindBy(css = "#dashNotification.wlcmheadingfirst")
    private WebElement lblStep2PageHeader;

    public Boolean isStep2PageHeaderLabelVisible() {
        log.info("Step 2 page header label visibility status : " + lblStep2PageHeader.isDisplayed());
        return lblStep2PageHeader.isDisplayed();
    }

    public String getStep2PageHeaderLabel() {
        String label = getText(lblStep2PageHeader);
        log.info("About My Home step 2 page header is : " + label);
        return label;
    }
    
    @FindBy(css = "#accountdetails.wlcmheadingfirst")
    private WebElement lblStep3PageHeader;

    public Boolean isStep3PageHeaderLabelVisible() {
        log.info("Step 3 page header label visibility status : " + lblStep3PageHeader.isDisplayed());
        return lblStep3PageHeader.isDisplayed();
    }

    public String getStep3PageHeaderLabel() {
        String label = getText(lblStep3PageHeader);
        log.info("About My Home step 3 page header is : " + label);
        return label;
    }
    
    @FindBy(xpath = "//input[@globalize='ML_OTP_txt_AcctNo']/following-sibling::label")
    private WebElement lblServiceAccountNumber;
    
    public Boolean isServiceAccountNumberLabelVisible() {
        log.info("Service account number label visibility status : " + lblServiceAccountNumber.isDisplayed());
        return lblServiceAccountNumber.isDisplayed();
    }

    public String getServiceAccountNumberLabel() {
        String label = getText(lblServiceAccountNumber);
        log.info("Service account number label is : " + label);
        return label;
    }
    
    @FindBy(xpath = "//select[@globalize='ML_DynamicForm_DynamicKey_197']/following-sibling::label")
    private WebElement lblTypeOfHomeLiveIn;

    public Boolean isTypeOfHomeLabelVisible() {
        log.info("Type of home label visibility status : " + lblTypeOfHomeLiveIn.isDisplayed());
        return lblTypeOfHomeLiveIn.isDisplayed();
    }

    public String getTypeOfHomeLabel() {
        String label = getText(lblTypeOfHomeLiveIn);
        log.info("Type of home label is : " + label);
        return label;
    }
    
    @FindBy(xpath = "//select[@globalize='ML_DynamicForm_DynamicKey_198']/following-sibling::label")
    private WebElement lblNoOfPeoplesLiveIn;

    public Boolean isNoOfPeopleLabelVisible() {
        log.info("Number of people label visibility status : " + lblNoOfPeoplesLiveIn.isDisplayed());
        return lblNoOfPeoplesLiveIn.isDisplayed();
    }

    public String getNumberOfPeopleLabel() {
        String label = getText(lblNoOfPeoplesLiveIn);
        log.info("Number of people label is : " + label);
        return label;
    }
    
    @FindBy(xpath = "//input[@globalize='ML_DynamicForm_DynamicKey_185']/following-sibling::label")
    private WebElement lblHaveSolarPanels;

    public Boolean isHaveSolarPanelsLabelVisible() {
        log.info("Have solar panels label visibility status : " + lblHaveSolarPanels.isDisplayed());
        return lblHaveSolarPanels.isDisplayed();
    }

    public String getHaveSolarPanelsLabel() {
        String label = getText(lblHaveSolarPanels);
        log.info("Have solar panel label is : " + label);
        return label;
    }
    
    @FindBy(xpath = "//input[@globalize='ML_Default_Lbl_NoOfHomeSize']/following-sibling::label")
    private WebElement lblHomeSize;

    public Boolean isHomeSizeLabelVisible() {
        log.info("Home size label visibility status : " + lblHomeSize.isDisplayed());
        return lblHomeSize.isDisplayed();
    }

    public String getHomeSizeLabel() {
        String label = getText(lblHomeSize);
        log.info("Home size label is : " + label);
        return label;
    }
    
    @FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_floors']/following-sibling::label")
    private WebElement lblFloorsInHome;

    public Boolean isFloorsInHomeVisible() {
        log.info("Floors in home label visibility status : " + lblFloorsInHome.isDisplayed());
        return lblFloorsInHome.isDisplayed();
    }

    public String getFloorsInHomeLabel() {
        String label = getText(lblFloorsInHome);
        log.info("Floors in home label is : " + label);
        return label;
    }
    
    @FindBy(xpath = "//label[contains(text(), 'Do you have a swimming pool?')]")
    private WebElement lblHaveSwimmingPool;

    public Boolean isHaveSwimmingPoolLabelVisible() {
        log.info("Have swimming pool label visibility status : " + lblHaveSwimmingPool.isDisplayed());
        return lblHaveSwimmingPool.isDisplayed();
    }

    public String getHaveSwimmingPoolLabel() {
        String label = getText(lblHaveSwimmingPool);
        log.info("Have swimming pool label is : " + label);
        return label;
    }
    
    @FindBy(xpath = "//label[text()='Do you own an electric vehicle?']")
    private WebElement lblHaveElectricVehicle;

    public Boolean isHaveElectricVehicleLabelVisible() {
        log.info("Electric vehicle label visibility status : " + lblHaveElectricVehicle.isDisplayed());
        return lblHaveElectricVehicle.isDisplayed();
    }

    public String getHaveElectricVehicleLabel() {
        String label = getText(lblHaveElectricVehicle);
        log.info("Have electric vehicle label is : " + label);
        return label;
    }
    
    @FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_yearbuilt']/following-sibling::label")
    private WebElement lblYearOfHouseBuilt;

    public Boolean isYearOfHouseBuiltLabelVisible() {
        log.info("Years of house built label visibility status : " + lblYearOfHouseBuilt.isDisplayed());
        return lblYearOfHouseBuilt.isDisplayed();
    }

    public String getYearOfHouseBuiltLabel() {
        String label = getText(lblYearOfHouseBuilt);
        log.info("Years of house built is : " + label);
        return label;
    }
    
    @FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_numberofbathrooms']/following-sibling::label")
    private WebElement lblNoOfBathrooms;

    public Boolean isNoOfBathroomsLabelVisible() {
        log.info("Number of bathrooms label visibility status : " + lblNoOfBathrooms.isDisplayed());
        return lblNoOfBathrooms.isDisplayed();
    }

    public String getNoOfBathroomsLabel() {
        String label = getText(lblNoOfBathrooms);
        log.info("Number of bathrooms page header is : " + label);
        return label;
    }
    
    @FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_numberofhigheffiencyapp']/following-sibling::label")
    private WebElement lblNoOfEfficiencyAppliances;

    public Boolean isNoOfEfficiencyAppliancesLabelVisible() {
        log.info("No of efficiency appliance label visibility status : " + lblNoOfEfficiencyAppliances.isDisplayed());
        return lblNoOfEfficiencyAppliances.isDisplayed();
    }

    public String getNoOfEfficiencyAppliancesLabel() {
        String label = getText(lblNoOfEfficiencyAppliances);
        log.info("No of efficiency appliance page header is : " + label);
        return label;
    }
    
    @FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_lotsize']/following-sibling::label")
    private WebElement lblLotSize;

    public Boolean isLotSizeLabelVisible() {
        log.info("Lot size label visibility status : " + lblLotSize.isDisplayed());
        return lblLotSize.isDisplayed();
    }

    public String getLotSizeLabel() {
        String label = getText(lblLotSize);
        log.info("Lot size page header is : " + label);
        return label;
    }
    
    @FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_landscapearea']/following-sibling::label")
    private WebElement lblLandscapeArea;

    public Boolean isLandscapeAreaLabelVisible() {
        log.info("Landscape area label visibility status : " + lblLandscapeArea.isDisplayed());
        return lblLandscapeArea.isDisplayed();
    }

    public String getLandscapeAreaLabel() {
        String label = getText(lblLandscapeArea);
        log.info("Landscape area label is : " + label);
        return label;
    }
    
    @FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_Splandscapearea']/following-sibling::label")
    private WebElement lblSpecialLandscapeArea;

    public Boolean isSpecialLandscapeAreaLabelVisible() {
        log.info("Special landscape area label visibility status : " + lblSpecialLandscapeArea.isDisplayed());
        return lblSpecialLandscapeArea.isDisplayed();
    }

    public String getSpecialLandscapeAreaLabel() {
        String label = getText(lblSpecialLandscapeArea);
        log.info("Special landscape area label is : " + label);
        return label;
    }
    
    @FindBy(css = ".wlcm_text.wlcmheadingfirst")
    private WebElement lblWelcomePopupHeading;

    public Boolean isWelcomePopupLabelVisible() {
        log.info("Welcome popup label visibility status : " + lblWelcomePopupHeading.isDisplayed());
        return lblWelcomePopupHeading.isDisplayed();
    }

    public String getWelcomePopupLabel() {
        String label = getText(lblWelcomePopupHeading);
        log.info("Welcome popup heading page header is : " + label);
        return label;
    }
    
    @FindBy(css = ".wlcm_text p")
    private WebElement lblWelcomePopupContent;

    public Boolean isWelcomePopupContentLabelVisible() {
        log.info("Welcome popup label visibility status : " + lblWelcomePopupContent.isDisplayed());
        return lblWelcomePopupContent.isDisplayed();
    }

    public String getWelcomePopupContentLabel() {
        String label = getText(lblWelcomePopupContent);
        log.info("Welcome popup page header is : " + label);
        return label;
    }
    
    @FindBy(css = "h4#Span_HomeBusiness")
    private WebElement lblNotificationHeading;

    public Boolean isNotificationHeadingLabelVisible() {
        log.info("Notification page header label visibility status : " + lblNotificationHeading.isDisplayed());
        return lblNotificationHeading.isDisplayed();
    }

    public String getNotificationHeadingLabel() {
        String label = getText(lblNotificationHeading);
        log.info("Notification page header is : " + label);
        return label;
    }
    
    @FindBy(css = ".pro_info_footer_inner")
    private WebElement lblNotificationFooter;

    public Boolean isNotificationFooterVisible() {
        log.info("Notification footer visibility status : " + lblNotificationFooter.isDisplayed());
        return lblNotificationFooter.isDisplayed();
    }

    public String getNotificationErrorLabel() {
        String label = getText(lblNotificationFooter);
        log.info("Notification error label : " + label);
        return label;
    }

    @FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_Splandscapearea']/following-sibling::span[contains(@class, 'glyphicon')]")
    private WebElement icoSpecialLandscapeArea;

    public Boolean isSpecialLandscapeIconVisible() {
        log.info("Special landscape icon visibility status : " + icoSpecialLandscapeArea.isDisplayed());
        return icoSpecialLandscapeArea.isDisplayed();
    }
    
    @FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_landscapearea']/following-sibling::span[contains(@class, 'glyphicon')]")
    private WebElement icoLandscapeArea;

    public Boolean isLandscapeAreaIconVisible() {
        log.info("Landscape area icon visibility status : " + icoLandscapeArea.isDisplayed());
        return icoLandscapeArea.isDisplayed();
    }
    
    @FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_lotsize']/following-sibling::span[contains(@class, 'glyphicon')]")
    private WebElement icoLotSize;

    public Boolean isLotSizeIconVisible() {
        log.info("Lot size icon visibility status : " + icoLotSize.isDisplayed());
        return icoLotSize.isDisplayed();
    }
    
    @FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_numberofhigheffiencyapp']/following-sibling::span[contains(@class, 'glyphicon')]")
    private WebElement icoHighEfficiencyAppliances;

    public Boolean isHighEfficiencyAppliancesIconVisible() {
        log.info("High efficiency appliances icon visibility status : " + icoHighEfficiencyAppliances.isDisplayed());
        return icoHighEfficiencyAppliances.isDisplayed();
    }
    
    @FindBy(xpath = "//input[@globalize='ML_Default_Lbl_NoOfHomeSize']/following-sibling::span[contains(@class, 'glyphicon')]")
    private WebElement icoHomeSize;

    public Boolean isHomeSizeIconVisible() {
        log.info("Home size icon visibility status : " + icoHomeSize.isDisplayed());
        return icoHomeSize.isDisplayed();
    }
    
    @FindBy(xpath = "//input[@globalize='ML_DynamicForm_DynamicKey_185']/following-sibling::a")
    private WebElement icoSolarCellPanels;

    public Boolean isSolarCellPanelIconVisible() {
        log.info("Solar cell panel icon visibility status : " + icoSolarCellPanels.isDisplayed());
        return icoSolarCellPanels.isDisplayed();
    }

    @FindBy(xpath = "//input[@globalize='ML_AboutHome_txt_AcctNo']")
    private WebElement txtServiceAccountNumber;

    public Boolean isServiceAccountNumberTxtVisible() {
        log.info("Service account number text visibility status : " + txtServiceAccountNumber.isDisplayed());
        return txtServiceAccountNumber.isDisplayed();
    }
    
    public void populateServiceAccountNumber(String serviceAccountNumber) {
        sendKeys(txtServiceAccountNumber, serviceAccountNumber);
        log.info(serviceAccountNumber + " populated to service account number text field.");
    };
    
    @FindBy(xpath = "//input[@globalize='ML_DynamicForm_DynamicKey_185']")
    private WebElement txtHaveSolarPanels;

    public Boolean isHaveSolarPanelsTxtVisible() {
        log.info("Have solar cell panel visibility status : " + txtHaveSolarPanels.isDisplayed());
        return txtHaveSolarPanels.isDisplayed();
    }

    public void populateSolarPanelsTxt(String solarCellPanels) {
        sendKeys(txtHaveSolarPanels, solarCellPanels);
        log.info(solarCellPanels + " populated solar cells panel into the text.");
    };
    
    @FindBy(xpath = "//input[@globalize='ML_Default_Lbl_NoOfHomeSize']")
    private WebElement txtHomeSize;

    public Boolean isHomeSizeTxtVisible() {
        log.info("Home size text visibility status : " + txtHomeSize.isDisplayed());
        return txtHomeSize.isDisplayed();
    }

    public void populateHomeSizeTxt(String homeSize) {
        sendKeys(txtHomeSize, homeSize);
        log.info("Populated to Home Size text box successfully.");
    };
    
    @FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_floors']")
    private WebElement txtFloorsInHome;

    public Boolean isFloorsInHomeTxtVisible() {
        log.info("Floors in home text visibility status : " + txtFloorsInHome.isDisplayed());
        return txtFloorsInHome.isDisplayed();
    }

    public void populateFloorInHomeTxt(String floors) {
        sendKeys(txtFloorsInHome, floors);
        log.info("Populated Floors in Home successfully.");
    };
    
    @FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_yearbuilt']")
    private WebElement txtYearOfHouseBuilt;

    public Boolean isYearOfHouseTxtVisible() {
        log.info("Year of house built text visibility status : " + txtYearOfHouseBuilt.isDisplayed());
        return txtYearOfHouseBuilt.isDisplayed();
    }

    public void populateYearOfHouseTxt(String yearsOfHouse) {
        sendKeys(txtYearOfHouseBuilt, yearsOfHouse);
        log.info("Populated years of house text successfully.");
    };
    
    @FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_numberofbathrooms']")
    private WebElement txtNoOfBathrooms;

    public Boolean isNoOfBathroomsTxtVisible() {
        log.info("No of bathrooms text visibility status : " + txtNoOfBathrooms.isDisplayed());
        return txtNoOfBathrooms.isDisplayed();
    }

    public void populateNoOfBathroomsText(String noOfBathrooms) {
        sendKeys(txtNoOfBathrooms, noOfBathrooms);
        log.info("Populated number of bathrooms successfully.");
    };
    
    @FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_numberofhigheffiencyapp']")
    private WebElement txtNoOfEfficiencyAppliances;

    public Boolean isNoOfEfficiencyAppliancesTxtVisible() {
        log.info("No of efficiency appliances visibility status : " + txtNoOfEfficiencyAppliances.isDisplayed());
        return txtNoOfEfficiencyAppliances.isDisplayed();
    }

    public void populateNoOfEfficiencyText(String noOfEfficiencyAppliance) {
        sendKeys(txtNoOfEfficiencyAppliances, noOfEfficiencyAppliance);
        log.info("Populated number of efficiency appliance successfully.");
    };
    
    @FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_lotsize']")
    private WebElement txtLotSize;

    public Boolean isLotSizeTextVisible() {
        log.info("Lot size text visibility status : " + txtLotSize.isDisplayed());
        return txtLotSize.isDisplayed();
    }

    public void populateLotSizeText(String lotSize) {
        sendKeys(txtLotSize, lotSize);
        log.info("Populated lot size text successfully.");
    };
    
    @FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_landscapearea']")
    private WebElement txtLandscapeArea;

    public Boolean isLandscapeTextVisible() {
        log.info("Step 1 page header label visibility status : " + txtLandscapeArea.isDisplayed());
        return txtLandscapeArea.isDisplayed();
    }

    public void populateLandscapeArea(String landscapeArea) {
        sendKeys(txtLandscapeArea, landscapeArea);
        log.info("Populated landscape area text successfully.");
    };
    
    @FindBy(xpath = "//input[@globalize='ML_NewAboutmyhome_Lbl_Splandscapearea']")
    private WebElement txtSpecialLandscapeArea;

    public Boolean isSpecialLandscapeAreaTextVisible() {
        log.info("Special landscape area text visibility status : " + txtSpecialLandscapeArea.isDisplayed());
        return txtSpecialLandscapeArea.isDisplayed();
    }

    public void populateSpecialLandscapeAreaText(String specialLandscapeArea) {
        sendKeys(txtSpecialLandscapeArea, specialLandscapeArea);
        log.info("Populated special landscape area successfully.");
    };
    
    @FindBy(xpath = "//label[contains(text(),'Do you have a swimming pool?')]/preceding-sibling::input")
    private WebElement tglHaveSwimmingPool;
    
    
    @FindBy(xpath = "//label[text()='Do you own an electric vehicle?']/preceding-sibling::input")
    private WebElement tglHaveElectricVehicle;
    
    
    @FindBy(xpath = "//select[@globalize='ML_DynamicForm_DynamicKey_197']")
    private WebElement ddTypeOfHomeLiveIn;
    
    @FindBy(xpath = "//select[@globalize='ML_DynamicForm_DynamicKey_198']")
    private WebElement ddNoOfPeoplesLiveIn;
    @FindBy(css = "#btnSaveHomeInfo")
    private WebElement btn_Submit_Ahm_Dbp;
    @FindBy(css = "#btnCancelInfo")
    private WebElement btnCancelHomeAos;
    @FindBy(css = "#Yes")
    private WebElement btnOkNotificationPrefAos;
    @FindBy(css = "#No")
    private WebElement btnCancelNotificationPrefAos;
    @FindBy(css = "input#btnskipInfo")
    private WebElement btnSkipNotificationPreAos;
    @FindBy(xpath = "//select[@globalize='ML_AboutMyBusiness_Lbl_BusinessType']")
    private WebElement lstBusinessType;
    @FindBy(xpath = "//select[@globalize='ML_DynamicForm_DynamicKey_223']")
    private WebElement lstBusinessSize;
    @FindBy(css = "input[globalize ='ML_CONNECTME_txtbx_ServiceAcct']")
    private WebElement txtAccNoAboutMyBusinessAos;
    @FindBy(xpath = "//input[@globalize='ML_DynamicForm_DynamicKey_29']")
    private WebElement txtNoEmployeesWorking;
    @FindBy(xpath = "//input[@globalize='ML_AboutMyBusiness_Lbl_OfficeArea']")
    private WebElement txtOfficeSpaceArea;
    @FindBy(xpath = "//input[@globalize='ML_DynamicForm_DynamicKey_30']")
    private WebElement txtLotSizeAmb;
    @FindBy(xpath = "//input[@globalize='ML_DynamicForm_DynamicKey_31']")
    private WebElement txtNoFloors;
    @FindBy(xpath = "//input[@globalize='ML_AboutMyBusiness_Lbl_NoOfRestrooms']")
    private WebElement txtNoRestrooms;
    @FindBy(xpath = "//input[@globalize='ML_AboutMyBusiness_Lbl_LandcapeArea']")
    private WebElement txtLandscapeAreaAmb;
    @FindBy(xpath = "//input[@globalize='ML_DynamicForm_DynamicKey_185']")
    private WebElement txtSolarCellPanels;
    @FindBy(xpath = "//input[@globalize='ML_CONNECTME_txtbx_ServiceAcct']/following-sibling::label")
    private WebElement lblAccountNoAboutMyBusiness;
    @FindBy(xpath = "//select[@globalize='ML_DynamicForm_DynamicKey_223']/following-sibling::label")
    private WebElement lblBusinessSize;
    @FindBy(xpath = "//select[@globalize='ML_AboutMyBusiness_Lbl_BusinessType']/following-sibling::label")
    private WebElement lblBusinessType;
    @FindBy(xpath = "//input[@globalize='ML_DynamicForm_DynamicKey_29']/following-sibling::label")
    private WebElement lblNoEmployeesWorking;
    @FindBy(xpath = "//input[@globalize='ML_AboutMyBusiness_Lbl_OfficeArea']/following-sibling::label")
    private WebElement lblOfficeSpaceArea;
    @FindBy(xpath = "//input[@globalize='ML_DynamicForm_DynamicKey_30']/following-sibling::label")
    private WebElement lblLotSizeAmb;
    @FindBy(xpath = "//input[@globalize='ML_DynamicForm_DynamicKey_31']/following-sibling::label")
    private WebElement lblNoFloors;
    @FindBy(xpath = "//input[@globalize='ML_AboutMyBusiness_Lbl_NoOfRestrooms']/following-sibling::label")
    private WebElement lblNoRestrooms;
    @FindBy(xpath = "//input[@globalize='ML_AboutMyBusiness_Lbl_LandcapeArea']/following-sibling::label")
    private WebElement lblLandscapeAreaAmb;
    @FindBy(xpath = "//input[@globalize='ML_DynamicForm_DynamicKey_185']/following-sibling::label")
    private WebElement lblSolarCellPanels;
    @FindBy(xpath = "//label[contains(text(),'Does the workplace have Elevator?')]")
    private WebElement lblHasElevator;
    @FindBy(xpath = "//label[contains(text(),'Does the workplace have  HVAC System?')]")
    private WebElement lblHaveHVAC;
    @FindBy(xpath = "//label[contains(text(),'Does the workplace have Electrical System?')]")
    private WebElement lblElectricSystem;
    @FindBy(xpath = "//label[contains(text(),'Does the workplace have Plumbing/ Water System?')]")
    private WebElement lblPlumbingWaterSystem;
    @FindBy(xpath = "//label[contains(text(),'Does the workplace have Server Room?')]")
    private WebElement lblHaveServerRoom;
    @FindBy(xpath = "//label[contains(text(),'Does the workplace have swimming pool?')]")
    private WebElement lblHaveSwimmingPoolAmb;
    @FindBy(xpath = "//label[text()='Does the workplace have Elevator?']/preceding-sibling::input")
    private WebElement tglHasElevator;
    @FindBy(xpath = "//label[contains(text(),'Does the workplace have  HVAC System?')]/preceding-sibling::input")
    private WebElement tglHaveHVAC;
    @FindBy(xpath = "//label[contains(text(),'Does the workplace have Electrical System?')]/preceding-sibling::input")
    private WebElement tglElectricSystem;
    @FindBy(xpath = "//label[contains(text(),'Does the workplace have Plumbing/ Water System?')]/preceding-sibling::input")
    private WebElement tglPlumbingWaterSystem;
    @FindBy(xpath = "//label[contains(text(),'Does the workplace have Server Room?')]/preceding-sibling::input")
    private WebElement tglHaveServerRoom;
    @FindBy(xpath = "//label[contains(text(),'Does the workplace have swimming pool?')]/preceding-sibling::input")
    private WebElement tglHaveSwimmingPoolAmb;
}

package demo.steps.scp;

import static org.testng.Assert.assertEquals;
import static sew.ai.steps.scp.DashboardSteps.dashboardTextProp;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import demo.pageobjects.PaymentLocationPage;
import demo.pageobjects.PreLoginPage;
import sew.ai.config.Configuration;
import sew.ai.config.ModelsConfiguration;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.models.Card;
import sew.ai.steps.scp.DashboardSteps;
import sew.ai.steps.scp.SignOutSteps;
import sew.ai.utils.PropertiesUtil;

public class PaymentLocationSteps extends PaymentLocationPage {

	private static final Logger log = LogManager.getLogger(PaymentLocationSteps.class);
	public static PropertiesUtil loginTextProp;
	public static PropertiesUtil preLoginConnectMeProp;
	public static PropertiesUtil signOutTextProp;
	public static PropertiesUtil preLoginPaymentLocationsProp;
	public static PropertiesUtil preLoginPaymentProp;
	public static PropertiesUtil preLoginWaysToSaveProp;

	public PaymentLocationSteps(WebDriver driver) {
		super(driver);
		loginTextProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.LOGIN_TXT_FILENAME);
//		preLoginConnectMeProp = new PropertiesUtil(
//				FilePaths.SCP_TEXT_PROPERTIES + Constants.PRE_LOG_PAYMENT_LOCATION_FILENAME);
		signOutTextProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.SIGNOUT_TXT_FILENAME);
		preLoginPaymentLocationsProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.SCM_PAYMENT_LOCATIONS_TXT_FILENAME);
		preLoginPaymentProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.SCM_PAYMENT_TXT_FILENAME);
		preLoginWaysToSaveProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.SCM_WAYSTOSAVE_TXT_FILENAME);
	}
public void verifyPreLoginPaymentLocationPage(SoftAssert softAssert) throws InterruptedException {
	

	clickPaymentLocation();
	pause(20000);
	ExtentLogger.logInfo("Payment Location page opened successfully");
	isPaymentLocationTitleVisible();
	ExtentLogger.logInfo("Payment Location Label is displayed successfully");
	pause(5000);
	clickSatellite();
	ExtentLogger.logInfo("Satellite option clicked successfully");
	
	clickMap();
	ExtentLogger.logInfo("Map option clicked successfully");
	
	isSelectcityVisible();
	clickSelectcity();
	ExtentLogger.logInfo("Select city field clicked successfully");
	//select city field clicked successfully
	selectlstConnectMeOptions("Chicago");
	selectlstConnectMeOptions("Irvine");
	selectlstConnectMeOptions("Los Angeles");
	selectlstConnectMeOptions("Nanticoke");
	selectlstConnectMeOptions("San Tan Valley");
	selectlstConnectMeOptions("Seattle");
	selectlstConnectMeOptions("Jber");
	clicklistLocation();
	ExtentLogger.logInfo("Address 2 option clicked successfully");
	//clicked Automation payment Location address
	isListnameVisible();
	isListaddVisible();
	isListzipVisible();
	isNameVisible();
	isAddressVisible();
	isCityVisible();
	scrollPageToElement(Addoptn2Mapview);
	ExtentLogger.logInfo("Address 2 option content visible successfully");
	//Automation Payment Location contents is visible 
	clickZoomIn();
	ExtentLogger.logInfo("Zoom In option clicked successfully");
	//clicked Zoom in option
	clickZoomout();
	ExtentLogger.logInfo("Zoom out option clicked successfully");
	//clicked Zoom out option
	clickStreetview();
	ExtentLogger.logInfo("Street view option clicked successfully");
	//clicked Street view option
	clickFullscreenbutton();
	ExtentLogger.logInfo("Full Screen in option clicked successfully");
	//clicked Full Screen option
	clickFullscreenout();
	ExtentLogger.logInfo("Full Screen out option clicked successfully");
	//clicked Full Screen out option
	isHomeiconVisible();
	clickHomebtn();
	ExtentLogger.logInfo("Home button is visible and is redirected to Pre login successfully");
	
}

public Boolean isPaymentLocationPage(String url, String title) {
    Boolean isPaymentLocationPage = false;
    log.info("Checking that the current page is account information page.");
    if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
    	isPaymentLocationPage = true;
    log.info("The current page is account information page {}: " + isPaymentLocationPage);
    return isPaymentLocationPage;
}

public void verifyPostloginPaymentlocation(SoftAssert softAssert) throws InterruptedException {
  
	clickBilling();
	clickPaymntlctn();
    Assert.assertTrue(
            isPaymentLocationPage(preLoginPaymentLocationsProp.getPropValue("PostLoginPageUrl"),
            		preLoginPaymentLocationsProp.getPropValue("PageHeader")),
            "Page URL and Header is not Matching");
    
    // Verifying Payment Information Page Header
    softAssert.assertEquals(isPaymentLocationHeaderVisible(),
    		preLoginPaymentLocationsProp.getPropValue("PageHeader"),
            "Expected Page Title do not match");
    isPaymentLocationHeaderVisible();
    clickSatellitebrn();
    clickMaps();
    isSelectcityVisible();
    clickSelectcity();
    isSelectcityVisible();
	clickSelectcity();
	ExtentLogger.logInfo("Select city field clicked successfully");
	
	//select city field clicked successfully
	selectlstConnectMeOptions("Chicago");
	selectlstConnectMeOptions("Irvine");
	selectlstConnectMeOptions("Los Angeles");
	selectlstConnectMeOptions("Nanticoke");
	selectlstConnectMeOptions("San Tan Valley");
	selectlstConnectMeOptions("Seattle");
	selectlstConnectMeOptions("Jber");
	clickAutomationPaymentlocation();
	isListnameVisible();
	isListaddressVisible();
	isListZipVisible();
	isNamesVisible();
	isAddresVisible();
	isCitycardVisible();
	scrollToElement(Addoptn2Mapview);
	AutomationPaymentLocationContents();
	clickZoomin();
	clickZoomOut();
	clickStreetView();
	clickcancelButton();
	clickFullscreenButton();
	clickFullscreenOutButton();
	ExtentLogger.logInfo("Post login Contact us page validated successfully ");
    
}}


	


            

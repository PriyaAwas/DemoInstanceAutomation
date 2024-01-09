package alectra.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import sew.ai.helpers.exceptions.IOException;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.helpers.reporters.ExtentManager;
import sew.ai.utils.ExcelUtils;
import sew.ai.utils.PropertiesUtil;
import sew.ai.utils.ScreenshotUtils;
import alectra.pageObjects.UsageOverviewPage;


public class ResUsageOverviewSteps extends UsageOverviewPage {
	private static final Logger log = LogManager.getLogger(ResUsageOverviewSteps.class);
	public static PropertiesUtil loginTextProp;

	public static String failStatus = "FAIL";
	public static String passStatus = "PASS";
	public static String path = "";
	public static String excelPath = "C:\\Users\\egeorge013\\Documents\\SCM10SFunctionalTests\\src\\test\\resources\\excel\\AlectraLoginTestData.xlsx";

	public ResUsageOverviewSteps(WebDriver driver) {
		super(driver);
		loginTextProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.LOGIN_TXT_FILENAME);
		}
//	public void verifyTheBillHistoryPageObject(SoftAssert softAssert) {
//        softAssert.assertTrue(isBillDateVisible(), "Bill Date is not present.");
//    }
//	
//	public void verifyThePaymentHistoryPageObject(SoftAssert softAssert) {      
//        softAssert.assertTrue(isTransactionDateVisible(), "Transaction Date is not present.");
//    }
		
	public void ElectricUsageOverview(SoftAssert softAssert) throws InterruptedException {
		waitForPageToLoad();
        clickOnUsageHeader();
        ExtentLogger.logInfo("Click on Usage Dropdown.");
        clickOnUsageOverviewOption();
        ExtentLogger.logInfo("Click on Usage OverView Option.");
        Thread.sleep(10000);
		softAssert.assertEquals(getURLforUsageOverview(), "https://u2-alectra.smartcmobile.net/portal/#/Usages" );
		clickKwhUnitDropdown();
		ExtentLogger.logInfo("Click on KwH Dropdown.");
		clickDollarUnit();
		ExtentLogger.logInfo("Click on Dollar Unit.");
		Thread.sleep(3000);
		softAssert.assertAll();
	}
	
	public void WaterUsageOverview(SoftAssert softAssert) throws InterruptedException {
		waitForPageToLoad();
        clickOnUsageHeader();
        ExtentLogger.logInfo("Click on Usage Dropdown.");
        clickOnUsageOverviewOption();
        ExtentLogger.logInfo("Click on Usage OverView Option.");
        Thread.sleep(10000);
		softAssert.assertEquals(getURLforUsageOverview(), "https://u2-alectra.smartcmobile.net/portal/#/Usages" );
		clickWaterOption();
		ExtentLogger.logInfo("Click on Water Option.");
		Thread.sleep(3000);
		clickM3UnitDropdown();
		ExtentLogger.logInfo("Click on M3 Dropdown.");
		clickDollarUnit();
		ExtentLogger.logInfo("Click on Dollar Unit.");
		Thread.sleep(3000);
		softAssert.assertAll();
	}
	
	public void DerUsageOverview(SoftAssert softAssert) throws InterruptedException {
		waitForPageToLoad();
        clickOnUsageHeader();
        ExtentLogger.logInfo("Click on Usage Dropdown.");
        clickOnUsageOverviewOption();
        ExtentLogger.logInfo("Click on Usage OverView Option.");
        Thread.sleep(10000);
		softAssert.assertEquals(getURLforUsageOverview(), "https://u2-alectra.smartcmobile.net/portal/#/Usages" );
		clickDerOption();
		ExtentLogger.logInfo("Click on Der Option.");
		Thread.sleep(3000);
		clickKwhUnitDropdown();
		ExtentLogger.logInfo("Click on KwH Dropdown.");
		clickDollarUnit();
		ExtentLogger.logInfo("Click on Dollar Unit.");
		Thread.sleep(3000);
		softAssert.assertAll();
	}
	
	public void EnergyRatesUsageOverview(SoftAssert softAssert) throws InterruptedException {
		waitForPageToLoad();
        clickOnUsageHeader();
        ExtentLogger.logInfo("Click on Usage Dropdown.");
        clickOnUsageOverviewOption();
        ExtentLogger.logInfo("Click on Usage OverView Option.");
        Thread.sleep(10000);
		softAssert.assertEquals(getURLforUsageOverview(), "https://u2-alectra.smartcmobile.net/portal/#/Usages" );
		scrollDown();
		Thread.sleep(10000);
		clickRatesButton();
		ExtentLogger.logInfo("Click on Rates Option.");
		Thread.sleep(3000);
		
		String text = getRatesText();
		if ( text == "Energy Rates"  ) {
		    softAssert.assertEquals(getRatesText(), "Energy Rates");
			clickTieredButton();
			ExtentLogger.logInfo("Click on Tiered Option.");
			softAssert.assertEquals(getTieredText(), "Residential Summer (Tier 1 upto 600 kWh per month)");		
			Thread.sleep(3000);
			clickULOButton();
			ExtentLogger.logInfo("Click on ULO Option.");
			Thread.sleep(3000);
			
		} else {
			ExtentLogger.logInfo("Graph Not Present");	
		}
	    
		softAssert.assertAll();
	}
}
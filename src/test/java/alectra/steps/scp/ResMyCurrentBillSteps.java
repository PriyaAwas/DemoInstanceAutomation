package alectra.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.utils.PropertiesUtil;
import alectra.pageObjects.MyCurrentBillPage;


public class ResMyCurrentBillSteps extends MyCurrentBillPage {
	private static final Logger log = LogManager.getLogger(ResMyCurrentBillSteps.class);
	public static PropertiesUtil loginTextProp;

	public static String failStatus = "FAIL";
	public static String passStatus = "PASS";
	public static String path = "";
	public static String excelPath = "C:\\Users\\egeorge013\\Documents\\SCM10SFunctionalTests\\src\\test\\resources\\excel\\AlectraLoginTestData.xlsx";

	public ResMyCurrentBillSteps(WebDriver driver) {
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
		
	public void MyCurrentBill(SoftAssert softAssert) throws InterruptedException {
		waitForPageToLoad();
		clickBillingDropdown();
		ExtentLogger.logInfo("Click on Billing Dropdown. ");
		clickOnMyCurrentBill();
		ExtentLogger.logInfo("Click on My Current Bill. ");
		Thread.sleep(10000);		
		//waitForPageToLoad();
		String URL = driver.getCurrentUrl();
		softAssert.assertEquals(URL, "https://u2-alectra.smartcmobile.net/portal/#/ViewBill" );
		softAssert.assertAll();
	}
}
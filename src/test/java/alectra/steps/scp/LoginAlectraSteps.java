package alectra.steps.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import alectra.pageObjects.LoginPage;
import sew.ai.config.Configuration;
import sew.ai.helpers.exceptions.IOException;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.helpers.reporters.ExtentManager;
import sew.ai.models.RegistrationConfig;
import sew.ai.models.RegistrationMailsConfig;
import sew.ai.models.User;
import sew.ai.pageObjects.scp.RegistrationUGIPage;
import sew.ai.utils.ExcelUtils;
import sew.ai.utils.PropertiesUtil;
import sew.ai.utils.RandomUtil;
import sew.ai.utils.ScreenshotUtils;

import org.testng.Assert;

public class LoginAlectraSteps extends LoginPage {
	private static final Logger log = LogManager.getLogger(LoginAlectraSteps.class);
	public static PropertiesUtil loginTextProp;

	public static String failStatus = "FAIL";
	public static String passStatus = "PASS";
	public static String path = "";
	public static String excelPath = "C:\\Users\\egeorge013\\Documents\\SCM10SFunctionalTests\\src\\test\\resources\\excel\\AlectraLoginTestData.xlsx";

	public LoginAlectraSteps(WebDriver driver) {
		super(driver);
		loginTextProp = new PropertiesUtil(FilePaths.SCP_TEXT_PROPERTIES + Constants.LOGIN_TXT_FILENAME);
		}
	
		
		public void validLogin() throws InterruptedException {
			waitForPageToLoad();
			ExtentLogger.logInfo("Read Input Data From Excel");
			ExcelUtils.openExcelFile(excelPath);
			Sheet sheet = ExcelUtils.getSheetName(0);
			int k = 0;
			for (int i = 1; i <= ExcelUtils.getRowCount(sheet); i++) {
				ExtentLogger.logInfo("Enter User Name. ");
				String UserName = (ExcelUtils.getValueByColName(sheet, i, "UserName"));
				populateUserName(UserName);

				ExtentLogger.logInfo("Enter Password. ");
				String Password = (ExcelUtils.getValueByColName(sheet, i, "Password"));
				populatePassword(Password);

				clickSignInBtn();
				ExtentLogger.logInfo("Click on Sign In Button. ");

				k = k + 1;
			}
			waitForPageToLoad();
	}
}

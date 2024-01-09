package demo.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;

import sew.ai.pageObjects.scp.HomePage;


public class notificationPreferencesPage extends HomePage {
	
	private static final Logger log = LogManager.getLogger(notificationPreferencesPage.class);

	public notificationPreferencesPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath= "//*[@id='username']")
	private WebElement typeUserName;
	
	public void populateUsername(String UserName) {
		log.info("Populating User Name {} :" + UserName);
		sendKeys(typeUserName, UserName);
		log.info("User Username populated successfully.");
	}
 
	@FindBy(xpath = "//*[@id='password']")
	private WebElement txtBoxpassword;
 
	public void populatpassword(String Password) {
		log.info("Populating Password {} :" + Password);
		sendKeys(txtBoxpassword, Password);
		log.info("Password populated successfully.");
	}
	
	@FindBy(xpath = "//*[@id='loginSection']/div[4]/button")
	private WebElement btnLogIn;
 
	public void clickLogInButton() {
		click(btnLogIn);
		log.info("LogIn Button clicked successfully.");
	}
	
	
	@FindBy(xpath = "//*[@id='dtHome']")
	private WebElement home_link;
 
	public boolean isHomeLinkDisplayed() {
		return isElementVisible(home_link);
	}
	
	@FindBy(css = "#dtAccount")
	private WebElement accountDropDown;
 
	public void clickAccountDropDownHome() {
		click(accountDropDown);
		log.info("Account dropdown clicked successfully.");
	}
	
	@FindBy(xpath = "//*[@id='root']/div/nav/div/div/ul[1]/li[2]/div/div/div/ul/li[4]/a")
	private WebElement notipreflnk;

	public void clickNotiPrefHyperLink() {
		click(notipreflnk);
		log.info(" Notification Preference Option Clicked successfully. ");
		
	}
	@FindBy(xpath = "//*[@id='root']/div/div[3]/section/div[2]/div/div[1]/div/div/div[2]/div[1]/div[2]/span[1]")
	private WebElement dailyusgdwnarrw;

	public void clickDailyUsgDwnArrw() {
		clickElementUsingJsExecutor(dailyusgdwnarrw);
		log.info(" Daily Usg Alert Dwn Arrow Clicked successfully. ");
		
	}
	@FindBy(xpath = "//*[@id='checkboxloi6x2v8']")
	private WebElement dailyusgemailchkbox;

	public void clickDailyUsgEmailChkbox() {
		clickElementUsingJsExecutor(dailyusgemailchkbox);
		log.info(" Daily Usg Alert Email Chkbox Clicked successfully. ");
		
	}
	@FindBy(xpath = "//*[@id='ValidateAmiDailyUsage']/div[2]/div/button[2]/span[1]")
	private WebElement dailyusgemailsave;

	public void clickDailyUsgEmailSave() {
		clickElementUsingJsExecutor(dailyusgemailsave);
		log.info(" Daily Usg Alert Email Save Opt Clicked successfully. ");
		
	}
	@FindBy(xpath = "//*[@id='root']/div/div[3]/section/div[2]/div/div[2]/div/div/button/span[1]")
	private WebElement editquthrsbttn;

	public void clickEditQutHrsOpt() {
		clickElementUsingJsExecutor(editquthrsbttn);
		log.info(" Edit Quiet Hrs Button Clicked successfully. ");
		
	}
	@FindBy(xpath = "//*[@id='AuthoriseAccess']")
	private WebElement enblquthrschkbox;

	public void clickEnblQutHrsChkbox() {
		clickElementUsingJsExecutor(enblquthrschkbox);
		log.info(" Enable Quiet Hrs Chkbox Clicked successfully. ");
		
	}
	@FindBy(xpath = "//*[@id='formQuietHours']/div[5]/button[2]/span[1]")
	private WebElement quthrssave;

	public void clickQutHrsSave() {
		clickElementUsingJsExecutor(quthrssave);
		log.info(" Enable Quiet Hrs Save Opt Clicked successfully. ");
	}

}

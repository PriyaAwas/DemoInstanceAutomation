package demo.pageobjects;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.pageObjects.scp.HomePage;
import sew.ai.pageObjects.scp.LoginPage;

public class loginPage extends HomePage {
	
	private static final Logger log = LogManager.getLogger(loginPage.class);
	private static final Object Programspagepageview = null;

	public loginPage(WebDriver driver) {
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
	
	@FindBy(xpath = "//*[@id=\"dtAccount\"]")
	private WebElement AccountDropdown;

	public void clickcacountDropdown() {
		waitForElementToBeVisible(AccountDropdown);
		clickElementUsingJsExecutor(AccountDropdown);
		log.info("Clicked Account module from Header");
	}

	@FindBy(xpath = "//*[contains(text(),'My Alectra')]")
	private WebElement myAlectra_Link1;

	public void clickcMyAlectraLink() {
		waitForElementToBeVisible(myAlectra_Link1);
		clickElementUsingJsExecutor(myAlectra_Link1);
		log.info("Clicked My Alectra submodule successfully");
	}
@FindBy(xpath = "//*[@id=\"root\"]/div/div[3]/section/div[2]/div/div[1]/div[1]/div/form/div[3]/button/span[1]/i")
	protected WebElement pen_icon;

	public void clickonPenIcon() throws InterruptedException {
		scrollPageToElement(pen_icon);
		click(pen_icon);
		log.info("clicked Pen Icon successfully.");

	}

	@FindBy(xpath = "//*[@id=\"emailinput\"]")
	private WebElement type_emailad;

	public void populateemailID(String EmailID) {
		log.info("Populating Email ID{} :" + EmailID);
		sendKeys(type_emailad, EmailID);
		log.info("User Email Id populated successfully.");
	}

	@FindBy(xpath = "//*[@id=\"emailinput\"]")
	private WebElement clear_emailad;

	public void clearemailId() throws InterruptedException {
		clear_emailad.clear();
		log.info("Cleared Email Id Field successfully.");
	}

	@FindBy(xpath = "/html/body/div[2]/div/div[3]/section/div[2]/div/div[1]/div[1]/div/form/div[4]/form/div[1]/div[2]/div/div/input")
	private WebElement type_emailId;

	public void populateemailadd(String EmailAdd) {
		log.info("Populating Email Add{} :" + EmailAdd);
		sendKeys(type_emailId, EmailAdd);
		log.info("User Email Add populated successfully.");
	}

	@FindBy(xpath = "//*[@id='filled-password-input']")
	private WebElement type_crtPswrd;

	public void populatcrctpswrd(String CurrentPassword) {
		log.info("Populating Current Pswrd{} :" + CurrentPassword);
		sendKeys(type_crtPswrd, CurrentPassword);
		log.info("User Current password populated successfully.");
	}

	@FindBy(xpath = "//*[@id=\"emailSubmitBtn\"]/span[1]")
	private WebElement saveBtn;

	public void clickonsavebtn() {
		waitForElementToBeVisible(saveBtn);
		clickElementUsingJsExecutor(saveBtn);
		log.info("Clicked Save Button Successfully");
	}

@FindBy(xpath = "(//*[@id=\"root\"]/div/nav/div/div/ul[1]/li[2]/div/div/div/ul/li[5]/a)")
	private WebElement accInfo_Link;

	public void clickonaccInfo() {
		waitForElementToBeVisible(accInfo_Link);
		clickElementUsingJsExecutor(accInfo_Link);
		log.info("Clicked Account Information link Successfully");
	}

	@FindBy(xpath = "//*[@id='0023826434']")
	private WebElement threedot_Icon;

	public void clickonThreedots_Icon() {
		waitForElementToBeVisible(threedot_Icon);
		clickElementUsingJsExecutor(threedot_Icon);
		log.info("Clicked Three dots icon Successfully");
	}

	@FindBy(xpath = "(//*[@id=\"0\"]/div[3]/ul/li[5])")
	private WebElement Unlink_optn;

	public void clickonunlinkOption() {
		waitForElementToBeVisible(Unlink_optn);
		clickElementUsingJsExecutor(Unlink_optn);
		log.info("Clicked Unlink Option Successfully");
	}

	@FindBy(xpath = "/html/body/div[7]/div[3]/div/div[3]/button[1]/span[2]")
	private WebElement cancelbtn;

	public void clickonCancelButton() {
		waitForElementToBeVisible(cancelbtn);
		clickElementUsingJsExecutor(cancelbtn);
		log.info("Clicked Cancel Button Successfully");
	}

	@FindBy(xpath = "(//*[@id=\"root\"]/div/div[3]/section/div[2]/div/div[1]/div/p[2]/a)")
	private WebElement linkaccount_optn;

	public void clickonLinkacc() {
		waitForElementToBeVisible(linkaccount_optn);
		clickElementUsingJsExecutor(linkaccount_optn);
		log.info("Clicked Link Account option Successfully");
	}

	@FindBy(xpath = "//*[@id=\"firstName\"]")
	private WebElement type_firstName;

	public void populatFirstName(String FirstName) {
		log.info("Populating First Name{} :" + FirstName);
		sendKeys(type_firstName, FirstName);
		log.info("User First Name populated successfully.");
	}

	@FindBy(xpath = "//*[@id=\"lastName\"]")
	private WebElement type_lastName;

	public void populatLastName(String LastName) {
		log.info("Populating Current Pswrd{} :" + LastName);
		sendKeys(type_lastName, LastName);
		log.info("User Last Name populated successfully.");
	}

	@FindBy(xpath = "(/html/body/div[6]/div[3]/div/div[3]/button[1]/span[1])")
	private WebElement Cancelbtn;

	public void clickoncancelButton() {
		waitForElementToBeVisible(Cancelbtn);
		clickElementUsingJsExecutor(Cancelbtn);
		log.info("Clicked Cancel Button Successfully");
	}

@FindBy(xpath = "//*[@id='firstName']")
	protected WebElement type_FirstName;

	public void populatfirstName(String Firstname) throws InterruptedException {
		scrollPageToElement(type_FirstName);
		log.info("Populating First name{} :" + Firstname);
		sendKeys(type_FirstName, Firstname);
		log.info("User First Name populated successfully.");
	}

	@FindBy(xpath = "//*[@id=\"lastName\"]")
	private WebElement type_LastName;

	public void populatLastname(String Lastname) {
		log.info("Populating Last Name{} :" + Lastname);
		sendKeys(type_LastName, Lastname);
		log.info("User Last Name populated successfully.");
	}

	@FindBy(xpath = "//*[@id=\"accountNumber\"]")
	private WebElement type_accNum;

	public void populateAccNum(String accNum) throws InterruptedException {
		log.info("Populating Account Number{} :" + accNum);
		sendKeys(type_accNum, accNum);
		log.info("User Account Number populated successfully.");
	}

	@FindBy(xpath = "//*[@id=\"primaryPhone\"]")
	private WebElement type_PrimPhNo;

	public void populatPrimaryPh(String PrimPhNo) throws InterruptedException {
		log.info("Populating PrimaryPhNo{} :" + PrimPhNo);
		sendKeys(type_PrimPhNo, PrimPhNo);
		log.info("User Primary Phone Number populated successfully.");
	}

	@FindBy(xpath = "(//*[@id=\"root\"]/div/div/div[2]/div/div/div/div[1]/div/div/div/div/div[2]/button[2]/span[1])")
	protected WebElement NextBtn;

	public void clickonNextButton() throws InterruptedException {
		waitForElementToBeVisible(NextBtn);
		clickElementUsingJsExecutor(NextBtn);
		log.info("Clicked  Next Button Successfully");
	}

	@FindBy(xpath = "//*[@id=\"1\"]/div[1]")
	protected WebElement errmsg;

	public String gettextErrorMessage() {
		String ErrorMessage = getText(errmsg);
		log.info("Error Message Dispalyed successfully.");
		return ErrorMessage;

	}

@FindBy(xpath = "(//*[@id=\"root\"]/div/div[3]/section/div[2]/div/div[1]/div[1]/div/div[6]/div[3]/button/span[1]/i)")
				protected WebElement plusicon;

				public void clickonplusicon() throws InterruptedException {
					waitForElementToBeVisible(plusicon);
					clickElementUsingJsExecutor(plusicon);
					log.info("Clicked on plus icon Successfully");
				}
				
				@FindBy(xpath = "//*[@id=\"SecPhoneNumber\"]")
				private WebElement typebusphno ;

				public void populateBusPhNo(String BusinessPhNo) {
					log.info("Populating Business Phone No {} :" + BusinessPhNo);
					sendKeys(typebusphno, BusinessPhNo);
					log.info("User Business pone number populated successfully.");
				}
				
				@FindBy(xpath = "//*[@id=\"AltPhoneSubmitBtn\"]/span[1]")
				protected WebElement savebtn;

				public void clickonSaveButton() throws InterruptedException {
					waitForElementToBeVisible(savebtn);
					clickElementUsingJsExecutor(savebtn);
					log.info("Clicked on save button Successfully");
					
				}	
					@FindBy(xpath = "//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary' and @aria-label='Click Here to Continue']")
					protected WebElement continuebtn;

					public void clickonctnbtn() throws InterruptedException {
						//waitForElementToBeVisible(continuebtn);
						clickElementUsingJsExecutor(continuebtn);
						log.info("Clicked Continue button Successfully");
					
				}
				
				@FindBy(xpath = "(//*[@id=\"root\"]/div/div[3]/section/div[2]/div/div[1]/div[1]/div/div[7]/div[3]/button/span[1]/i)")
				protected WebElement Plusicon;

				public void clickonPlusicon() throws InterruptedException {
					
					waitForElementToBeVisible(Plusicon);
					clickElementUsingJsExecutor(Plusicon);
					log.info("Clicked on plus icon Successfully");
				}
				
				@FindBy(xpath = "//*[@id='SecPhoneNumber']")
				private WebElement clear_cellphno;

				public void clearecellphno() throws InterruptedException {
					clear_cellphno.clear();
					log.info("Cleared Cell Phone Field successfully.");
				}
				
				@FindBy(xpath = "//*[@id=\"SecPhoneNumber\"]")
				private WebElement typecellphno ;

				public void populatecellphno(String cellphno) {
					log.info("Populating cell Phone No {} :" + cellphno);
					sendKeys(typebusphno, cellphno);
					log.info("User Cell pone number populated successfully.");
				}
				
				@FindBy(xpath = "//*[@id=\"AltPhoneSubmitBtn\"]/span[1]")
				protected WebElement Savebtn;

				public void clickonsaveButton() throws InterruptedException {
					waitForElementToBeVisible(Savebtn);
					clickElementUsingJsExecutor(Savebtn);
					log.info("Clicked on save button Successfully");
				}
				
				
				@FindBy(css = "SecPhoneNumberspan")
					private WebElement errormsg;
				 
					public boolean isDefaultTextDisplayed() {
						return isElementVisible(errormsg);
					}
			
			
		
	@FindBy(xpath = "(//*[@id='root']/div/nav/div/div/ul[1]/li[4]/div/div/div[1]/ul/li[1]/a)")
	protected WebElement serviceOverview_Link;

	public String gettextserviceOverview() {
		String ServiceOverviewText = getText(serviceOverview_Link);
		log.info("get text Services Overview successfully.");
		return ServiceOverviewText;

	}

	@FindBy(xpath = "(//*[@id='root']/div/nav/div/div/ul[1]/li[4]/div/div/div[2]/ul/li/a)")
	protected WebElement serviceTransfer_Link;

	public String gettextserviceTransfer() {
		String ServiceTransferText = getText(serviceTransfer_Link);
		log.info("get text Services Overview successfully.");
		return ServiceTransferText;

	}

	@FindBy(xpath = "(//*[@id='root']/div/nav/div/div/ul[1]/li[4]/div/div/div[1]/ul/li[2]/a)")
	protected WebElement startService_Link;

	public String gettextstartService() {
		String StartServiceText = getText(startService_Link);
		log.info("get text Start Services successfully.");
		return StartServiceText;

	}

	@FindBy(xpath = "(//*[@id='root']/div/nav/div/div/ul[1]/li[4]/div/div/div[1]/ul/li[3]/a)")
	protected WebElement stopService_Link;

	public String gettextstopService() {
		String StopServiceText = getText(stopService_Link);
		log.info("get text Stop Services  successfully.");
		return StopServiceText;

	}

	@FindBy(xpath = "//*[@id=\"dtUsage\"]")
	private WebElement usageDropdown;

	public void clickUsageDropdown() {
		waitForElementToBeVisible(usageDropdown);
		clickElementUsingJsExecutor(usageDropdown);
		log.info("Clicked Usage module from Header");

	}

	@FindBy(xpath = "(//*[@id='root']/div/nav/div/div/ul[1]/li[5]/div/div/div/ul/li[1]/a)")
	protected WebElement usageOverview_Link;

	public String gettextusageOverview() {
		String UsageOverviewText = getText(usageOverview_Link);
		log.info("get text Usage Overview  successfully.");
		return UsageOverviewText;

	}

	@FindBy(xpath = "(//*[@id='root']/div/nav/div/div/ul[1]/li[5]/div/div/div/ul/li[2]/a)")
	protected WebElement usageCompare_Link;

	public String gettextusageCompare() {
		String UsageCompareText = getText(usageCompare_Link);
		log.info("get text Usage Overview  successfully.");
		return UsageCompareText;

	}
	

}

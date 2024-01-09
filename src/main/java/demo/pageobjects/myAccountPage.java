package demo.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.pageObjects.scp.HomePage;


public class myAccountPage extends HomePage {
	
	private static final Logger log = LogManager.getLogger(myAccountPage.class);

	public myAccountPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath= "//*[@id='txtLogin']")
	private WebElement typeUserName;
	
	public void populateUsername(String UserName) {
		log.info("Populating User Name {} :" + UserName);
		sendKeys(typeUserName, UserName);
		log.info("User Username populated successfully.");
	}

	@FindBy(xpath = "//*[@id='txtpwd']")
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

	@FindBy (xpath = "//*[@id='dtAccount']")
	private WebElement accountDropDown;
 
	public void clickAccountDropDownHome() {
		waitForElementToBeVisible(accountDropDown);
		clickElementUsingJsExecutor(accountDropDown);
		log.info("Account dropdown clicked successfully.");
	}
	
	
		@FindBy(xpath = "//*[@id='root']/div/nav/div/div/ul[1]/li[2]/div/div/div/ul/li[3]/a")
		private WebElement guestuserhyperlink;
	 
		public void clickGuestUserHyperLink() {
			waitForElementToBeVisible(guestuserhyperlink);
			clickElementUsingJsExecutor(guestuserhyperlink);
			log.info("Guest user hyperlink clicked successfully.");
	 
	}
	@FindBy(xpath = "//*[@id='root']/div/div[3]/section/div[2]/div/div[2]/div/div[1]/div/a/div/p")
	private WebElement invtguestusrlnk;

	public void clickInvtGuestUsrLnk() {
		click(invtguestusrlnk);
		log.info(" Invite Guest User Link clicked successfully.");
		
	}
	@FindBy(xpath = "//*[@id='AccountSelect']")
	private WebElement slctacctarrow;

	public void clickSelectAcctArrow() {
		click(slctacctarrow);
		log.info(" Select Account Down Arrow clicked successfully.");
		
	}
	@FindBy(xpath = "//*[@id='menu-AccountSelect']/div[3]/ul/li/span/span[1]/input")
	private WebElement slctacctavlb;

	public void clickSelectAcctAvlb() {
		clickElementUsingJsExecutor(slctacctavlb);
		log.info(" Account from dropdown selected successfully.");
		
	}
	@FindBy(xpath = "//*[@id=\'GuestRole\']")
	private WebElement slctrolearrow;

	public void clickSelectRoleArrow() {
		Select select = new Select (slctrolearrow);
		select.selectByValue("5");
		select.selectByVisibleText("Guest user");
		log.info(" Select Guest Role Down Arrow clicked successfully. ");
		
		
	}
	@FindBy(xpath = "//*[@id='addGuestUser']/div[6]/div/div/div/div/div/div")
	private WebElement accexpdate;

	public void clickAccExpDate() {
		clickElementUsingJsExecutor(accexpdate);
		log.info(" Access expiration date selected successfully. ");
		
}
	
	
	@FindBy(xpath= "//*[@id='GuestName']")
	private WebElement typeGuestName;
	
	public void populateGuestName(String GuestName) {
		log.info("Populating Guest Name {} :" + GuestName);
		sendKeys(typeGuestName, GuestName);
		log.info("Guest name populated successfully.");
	}

	@FindBy(xpath = "//*[@id='EmailAddress']")
	private WebElement txtBoxguestemailadd;

	public void populateGuestEmailAdd(String GuestEmailAddress) {
		log.info("Populating Guest Email Address {} :" + GuestEmailAddress);
		sendKeys(txtBoxguestemailadd, GuestEmailAddress);
		log.info("Guest Email Address populated successfully.");
		
}
	
	@FindBy(xpath = "//*[@id='termsAndCondition']/div/label/span[1]/span[1]")
	private WebElement tncchkbox;

	public void scrollandclickTnCchkbox() throws InterruptedException {
		waitForElementToBeVisible(tncchkbox);
		scrollPageToElement(tncchkbox);
		clickElementUsingJsExecutor(tncchkbox);
        log.info(" Terms and Conds. checkbox selected successfully." );
        
	}
	@FindBy(xpath = "//*[@id='addGuestUser']/div[8]/button[2]/span[1]")
	private WebElement savegustusrbttn;

	public void clickSaveGustUsrBttn() {
		clickElementUsingJsExecutor(savegustusrbttn);
		log.info(" Save Guest User Button Clicked successfully. ");
		
	}
	@FindBy(xpath = "/html/body/div[2]/div/div[3]/section/div[2]/div/div[2]/div/div[2]/div/div/button")
	private WebElement threedotopt;
 
	public void clickThreeDotOpt() {
		click(threedotopt);
		log.info(" Three Dot Option Clicked successfully. ");
		
	}
	@FindBy(xpath = "//*[@id='simple-menu']/div[3]/ul/li[1]")
	private WebElement editgustusropt;
 
	public void clickEditGustUsrOpt() {
		waitForElementToBeVisible(editgustusropt);
		clickElementUsingJsExecutor(editgustusropt);
		log.info(" Edit Guest Usr Opt Clicked successfully. ");
		
	}
	@FindBy(xpath = "//*[@id='editGuestUser']/div[8]/button[2]/span[1]")
	private WebElement updtgustusropt;
 
	public void scrollandclickUpdateUsrOpt() throws InterruptedException {
		waitForElementToBeVisible(updtgustusropt);
		scrollPageToElement(updtgustusropt);
		clickElementUsingJsExecutor(updtgustusropt);
		log.info(" Update Guest Usr Opt Clicked successfully. ");
		
	}
	@FindBy(xpath = "//*[@id='editGuestUser']/div[8]/button[1]/span[1]")
	private WebElement cancelupdtbttn;
 
	public void clickCancelUpdt() {
		clickElementUsingJsExecutor(resendactlnk);
		log.info(" Cancel Update Bttn Clicked successfully. ");
		
	}
	@FindBy(xpath = "//*[@id='simple-menu']/div[3]/ul/li[3]")
	private WebElement resendactlnk;
 
	public void clickResendActLnk() {
		clickElementUsingJsExecutor(resendactlnk);
		log.info(" Resend Activation Link Opt Clicked successfully. ");
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
	
	@FindBy(xpath = "//*[@id='root']/div/nav/div/div/ul[1]/li[2]/div/div/div/ul/li[2]/a")
	private WebElement paymentinfohyperlink;

	public void clickPaymentInformationHyperLink() {
		click(paymentinfohyperlink);
		log.info("Payment information hyperlink clicked successfully.");
	
	}

	@FindBy(xpath = "//*[@id='root']/div/div[3]/section/div[2]/div/div[1]/div/div[1]/div/a/div/p")
	private WebElement addpayinfo;

	public void clickAddPaymentMethod() {
		click(addpayinfo);
		log.info("Add Payment hyperlink clicked successfully.");
	
	}
	@FindBy(xpath= "//*[@id=\"NameOnCard\"]")
	private WebElement typenameonthecard;
	
	public void populateNameOnCard(String NameOnTheCard) {
		log.info("Populating Name on the card {} :" + NameOnTheCard);
		sendKeys(typenameonthecard, NameOnTheCard);
		log.info("Name on the card populated successfully.");
	}
	@FindBy(xpath= "//*[@id='NameOnCard']")
	private WebElement typenameonthecard1;
	
	public void populateNameOnCard1(String NameOnTheCard1) {
		log.info("Populating Name on the Card {} :" + NameOnTheCard1);
		sendKeys(typenameonthecard1, NameOnTheCard1);
		log.info("Name on the card populated successfully.");
	}
	
	@FindBy(xpath= "//*[@id='cardNumber']")
	private WebElement typecardnumber;
	
	public void populateCardNumber(String CardNumber) {
		log.info("Populating Card number {} :" + CardNumber);
		sendKeys(typecardnumber, CardNumber);
		log.info("Card number populated successfully.");
	}
	
	@FindBy(xpath= "//*[@id='cardNumber']")
	private WebElement typecardnumber1;
	
	public void populateCardNumber1(String CardNumber1) {
		log.info("Populating Card number {} :" + CardNumber1);
		sendKeys(typecardnumber1, CardNumber1);
		log.info("Card number populated successfully.");
	}
	
	@FindBy(xpath= "//*[@id='securityCode']")
	private WebElement typecvv;
	
	public void populateCVV(String CVV) {
		log.info("Populating CVV {} :" + CVV);
		sendKeys(typecvv, CVV);
		log.info("CVV populated successfully.");
	}
	
	@FindBy(xpath= "//*[@id='securityCode']")
	private WebElement typecvv1;
	
	public void populateCVV1(String CVV1) {
		log.info("Populating CVV {} :" + CVV1);
		sendKeys(typecvv1, CVV1);
		log.info("CVV populated successfully.");
	}
	

	@FindBy(xpath= "//*[@id='securityCode']")
	private WebElement typecvv2;
	
	public void populateCVV2(String CVV2) {
		log.info("Populating CVV {} :" + CVV2);
		sendKeys(typecvv2, CVV2);
		log.info("CVV populated successfully.");
	}
	
	@FindBy(xpath = "//*[@id='cardExpiryMonth']")
	private WebElement monthdd;

	public void clickMonthDropDown() {
		click(monthdd);
		log.info("Month clicked successfully.");
	
	}
	
	@FindBy(xpath = "//*[@id='menu-cardExpiryMonth']/div[3]/ul/li[13]")
	private WebElement selectmonth;

	public void clickMonthFromOptions() {
		click(selectmonth);
		log.info("Month selected successfully.");
	
	}
	
	@FindBy(xpath = "//*[@id='cardExpiryYear']")
	private WebElement yeardd;

	public void clickYearDropDown() {
		click(yeardd);
		log.info("Year clicked successfully.");
	
	}
	
	@FindBy(xpath = "//*[@id='menu-cardExpiryYear']/div[3]/ul/li[7]")
	private WebElement selectyear;

	public void clickYearFromOptions() {
		click(selectyear);
		log.info("Year selected successfully.");
	
	}
	
	@FindBy(xpath = "//*[@id='addPaymentMethodBillingInfo']/div[2]/button[2]/span[1]")
	private WebElement btnsave;

	public void clickSaveButton() {
		click(btnsave);
		log.info("Save Button successfully.");
	
	}
	

	@FindBy(xpath = "//*[@id='krc3gldjng']")
	private WebElement addsuccesstoast;

	public boolean isAddSuccessToastDisplayed() {
		return isElementVisible(addsuccesstoast);
	}
	
	@FindBy(xpath = "//*[@id=\"account858\"]/div")
	private WebElement paymentinfo;

	public boolean isPaymentinfoDisplayed() {
		return isElementVisible(paymentinfo);
	}
	
	@FindBy(xpath = "/html/body/div[6]/div/div/div/div[1]")
	private WebElement errornonaccCard;

	public boolean isCardNonAcceptedErrorDisplayed() {
		return isElementVisible(errornonaccCard);
	}
	

	@FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root more-btn jss144' and @aria-label='Click Here to open dropdown']")
	private WebElement btnpaymentthreedot;

	public void clickPaymentThreeDotIcon() {
		click(btnpaymentthreedot);
		log.info("Payment Three dot icon clicked successfully.");
	
	}
	
	@FindBy(css = "body > div.MuiPopover-root > div.MuiPaper-root.MuiMenu-paper.LongMenuWrapper.MuiPopover-paper.MuiPaper-rounded > ul > li:nth-child(1)")
	private WebElement editoption;

	public void clickEditOptionPay() {
		click(editoption);
		log.info("Edit option clicked successfully.");
	
	}
	@FindBy(xpath = "//*[@id='securityCodespan']")
	private WebElement errorcvv;

	public boolean isEnterCVVErrorDisplayed() {
		return isElementVisible(errorcvv);
	}
	
	

	@FindBy(xpath = "//*[@id='addPaymentMethodBillingInfo']/div[2]/button[1]/span[1]")
	private WebElement btncancel;
	public void clickCancelButton() {
		clickElementUsingJsExecutor(btncancel);
		log.info("cancel Button Clicked successfully.");
	
	}
	
	@FindBy(css = "body > div.MuiPopover-root > div.MuiPaper-root.MuiMenu-paper.LongMenuWrapper.MuiPopover-paper.MuiPaper-rounded > ul > li:nth-child(2)")
	private WebElement deleteoption;

	public void clickDeleteOptionPay() {
		click(deleteoption);
		log.info("Delete Option clicked successfully.");
	
	}
	
	@FindBy(css = "body > div.MuiPopover-root > div.MuiPaper-root.MuiMenu-paper.LongMenuWrapper.MuiPopover-paper.MuiPaper-rounded > ul > li:nth-child(3)")
	private WebElement setasdefaultoption;

	public void clickSetAsDefaultOptionPay() {
		click(setasdefaultoption);
		log.info("Delete Option clicked successfully.");
	
	}
	
	@FindBy(css = "#o4b3przjoq > div.Toastify__toast-body")
	private WebElement editsuccesstoast;

	public boolean isEditSuccessToastDisplayed() {
		return isElementVisible(editsuccesstoast);
	}
	
	@FindBy(xpath = "/html/body/div[7]/div[3]/div/div[2]/button[2]/span[1]")
	private WebElement btnremove;

	public void clickRemoveButton() {
		click(btnremove);
		log.info("Remove Button Clicked successfully.");
	
	}
	
	@FindBy(xpath = "/html/body/div[7]/div[3]/div/div[2]/button[2]/span[1]")
	private WebElement btnsadok;
	public void clickOkButton() {
		clickElementUsingJsExecutor(btnsadok);
		log.info("Yes Button Clicked successfully.");
	
	}
	
	@FindBy(xpath = "/html/body/div[2]/div/div[3]/section/div[2]/div/div[1]/div/div[2]/div/div/div[3]/span")
	private WebElement buttontxtdefault;

	public boolean isDefaultButtonTextDisplayed() {
		return isElementVisible(buttontxtdefault);
	}
	
	
	@FindBy(xpath = "//*[@id='hvhhws56yl']/div[1]")
	private WebElement deletesuccesstoast;

	public boolean isDeleteSuccessToastDisplayed() {
		return isElementVisible(deletesuccesstoast);
	}
	
	@FindBy(xpath = "//*[@id='1']/div[1]")
	private WebElement blankerror;

	public boolean isBlankErrorMessageDisplayed() {
		return isElementVisible(blankerror);
	}
	
	@FindBy(xpath = "//*[@id=\"dtAccount\"]")
	private WebElement accountHeader;

	public void clickOnAccountHeader() {
		waitForElementToBeVisible(accountHeader);
		clickElementUsingJsExecutor(accountHeader);
		log.info("Clicked on account");
	}
	
	@FindBy(xpath = "//*[@id='root']/div/nav/div/div/ul[1]/li[2]/div/div/div/ul/li[6]/a")
	private WebElement greenButtonOption;

	public void clickOnGreenButtonOption() {
		waitForElementToBeVisible(greenButtonOption);
		clickElementUsingJsExecutor(greenButtonOption);
		log.info("Clicked green button option");
			}
	
	public String getURLforGreenButton() {
		String URL = driver.getCurrentUrl();
		log.info("Page URL is " + URL);
		ExtentLogger.logInfo("Page header label is " + URL);
		return URL;
	}
	
	@FindBy(css = "#root > div > div.pagewrapper > section > div.MuiGrid-root.pageheading-wrapper > div > div > div:nth-child(1) > div > h1")
	private WebElement greenText;
	
	public String getGreenText() {
		String titleHeader = getText(greenText);
		log.info("header label is " + titleHeader);
		ExtentLogger.logInfo("Page header label is " + titleHeader);
		return titleHeader;
	}

}

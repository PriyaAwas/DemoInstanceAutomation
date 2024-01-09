package demo.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import sew.ai.pageObjects.scp.HomePage;


public class guestUserPage extends HomePage {
	
	private static final Logger log = LogManager.getLogger(guestUserPage.class);

	public guestUserPage(WebDriver driver) {
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
	@FindBy(xpath = "//*[@id='45749']/div/button/span[1]")
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

}

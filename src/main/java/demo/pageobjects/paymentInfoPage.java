package demo.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;

import sew.ai.pageObjects.scp.HomePage;

public class paymentInfoPage extends HomePage {

	private static final Logger log = LogManager.getLogger(paymentInfoPage.class);

	public paymentInfoPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@id='txtLogin']")
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

	@FindBy(xpath = "//*[@id='btnlogin']")
	private WebElement btnLogIn;

	public void clickLogInButton() {
		click(btnLogIn);
		log.info("LogIn Button clicked successfully.");
	}

	@FindBy(xpath = "//*[@id=\"collapseAccount\"]/div/ul/li[2]/a/span")
	private WebElement paymentinfohyperlink;

	public void clickPaymentInformationHyperLink() {
		click(paymentinfohyperlink);
		log.info("Payment information hyperlink clicked successfully.");

	}

	@FindBy(xpath = "//*[@id='module1']/a/span")
	private WebElement accountDropDown;

	public void clickAccountDropDownHome() {
		click(accountDropDown);
		log.info("Account dropdown clicked successfully.");

	}

	@FindBy(xpath = "//*[@id='addnewpayment']")
	private WebElement addpaymentmethod;

	public void clickAddPaymentMethod() {
		click(addpaymentmethod);
		log.info("Add Payment hyperlink clicked successfully.");

	}

	@FindBy(xpath = "//*[@id='control_02']")
	private WebElement selectCC;

	public void selectCCPaymentMethod() {
		click(selectCC);
		log.info("Add Payment hyperlink clicked successfully.");

	}

	@FindBy(xpath = "//*[@id='nmcrd']")
	private WebElement typenameonthecard;

	public void populateNameOnCard(String NameOnTheCard) {
		log.info("Populating Name on the card {} :" + NameOnTheCard);
		sendKeys(typenameonthecard, NameOnTheCard);
		log.info("Name on the card populated successfully.");
	}

	@FindBy(xpath = "//*[@id='NameOnCard']")
	private WebElement typenameonthecard1;

	public void populateNameOnCard1(String NameOnTheCard1) {
		log.info("Populating Name on the Card {} :" + NameOnTheCard1);
		sendKeys(typenameonthecard1, NameOnTheCard1);
		log.info("Name on the card populated successfully.");
	}

	@FindBy(xpath = "//*[@id='crdno']")
	private WebElement typecardnumber;

	public void populateCardNumber(String CardNumber) {
		log.info("Populating Card number {} :" + CardNumber);
		sendKeys(typecardnumber, CardNumber);
		log.info("Card number populated successfully.");
	}

	@FindBy(xpath = "//*[@id='cardNumber']")
	private WebElement typecardnumber1;

	public void populateCardNumber1(String CardNumber1) {
		log.info("Populating Card number {} :" + CardNumber1);
		sendKeys(typecardnumber1, CardNumber1);
		log.info("Card number populated successfully.");
	}

	@FindBy(xpath = "//*[@id='Security']")
	private WebElement typecvv;

	public void populateCVV(String CVV) {
		log.info("Populating CVV {} :" + CVV);
		sendKeys(typecvv, CVV);
		log.info("CVV populated successfully.");
	}

	@FindBy(xpath = "//*[@id='securityCode']")
	private WebElement typecvv1;

	public void populateCVV1(String CVV1) {
		log.info("Populating CVV {} :" + CVV1);
		sendKeys(typecvv1, CVV1);
		log.info("CVV populated successfully.");
	}

	@FindBy(xpath = "//*[@id='securityCode']")
	private WebElement typecvv2;

	public void populateCVV2(String CVV2) {
		log.info("Populating CVV {} :" + CVV2);
		sendKeys(typecvv2, CVV2);
		log.info("CVV populated successfully.");
	}

	@FindBy(xpath = "//*[@id='expMOnth']")
	private WebElement monthdd;

	public void SelectMonthFromDropDown(String month) {
		click(monthdd);
		selectByVisibleText(monthdd,month);
		log.info("Month clicked successfully.");
	}
	
	@FindBy(xpath = "//*[@id='expYear']")
	private WebElement yeardd;
   
	public void selectYearFromDropDown(int Year) {
		click(yeardd);
		selectByIndex(yeardd,Year);
		log.info("Year clicked successfully.");;
	}

	@FindBy(xpath = "//*[@id='menu-cardExpiryMonth']/div[3]/ul/li[13]")
	private WebElement selectmonth;

	public void clickMonthFromOptions() {
		click(selectmonth);
		log.info("Month selected successfully.");
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
	private WebElement txtdefault;

	public boolean isDefaultTextDisplayed() {
		return isElementVisible(txtdefault);
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
	
	@FindBy(xpath = "//*[@id='st2_firstname']")
	private WebElement typefrstname;

	public void populateFirstName(String FirstName) {
		log.info("Type FirstName {} :" + FirstName);
		scrollToElement(typefrstname);
		sendKeys(typefrstname, FirstName);
		log.info("FirstName populated successfully.");
	}
	
	@FindBy(xpath = "//*[@id='st2_lastname']")
	private WebElement typelastname;

	public void populateLastName(String LastName) {
		log.info("Type FirstName {} :" + LastName);
		sendKeys(typelastname, LastName);
		log.info("LastName populated successfully.");
	}
	@FindBy(xpath = "//*[@id='st2_address']")
	private WebElement typeAdd;

	public void populateAddress(String Address) {
		log.info("Type Address {} :" + Address);
		sendKeys(typeAdd, Address);
		log.info("Address populated successfully.");
	}
	
	@FindBy(xpath = "//*[@id='st2_city']")
	private WebElement typecity;

	public void populateCity(String City) {
		log.info("Type City {} :" + City);
		sendKeys(typecity, City);
		log.info("City populated successfully.");
	}
	
	@FindBy(id = "st2_state")
	private WebElement typestate;

	public void populateState(String State) {
		log.info("Type State {} :" + State);
		sendKeys(typestate, State);
		log.info("State populated successfully.");
	}
	@FindBy(xpath = "//*[@id='st2_zipcode']")
	private WebElement typezipcode;

	public void populateZipcode(String Zip) {
		log.info("Type Zipcode {} :" + Zip);
		sendKeys(typezipcode, Zip);
		log.info("Zipcode populated successfully.");
	}
	
	@FindBy(xpath = "//*[@id='submitNext']")
	private WebElement btnadd;

	public void clickOnAddButton() {
		click(btnadd);
		log.info("Add Button Clicked successfully.");

	}
	
	@FindBy(xpath = "//*[@id='toast-container']/div/div")
	private WebElement tostermsg;

	public String VerifySucessMsg() {
		String Sucesmsg = getText(tostermsg);
		log.info("payemnt  added successfully.");
		return Sucesmsg;

		}
		
		@FindBy(xpath = "//*[@id='module2']/a/span")
		private WebElement billdrpdown;
	
		public void clickBiling() {
		  click(billdrpdown);
			log.info("click on billing.");
		}
		
		@FindBy(xpath = "//*[@id='HeaderMenu_billList']/li[1]/a")
		private WebElement curntbilldrpdown;
	
		public void clickCurrentBill() {
		  click(curntbilldrpdown);
			log.info("click on billing.");
		}
	
		@FindBy(id = "btnpaypower")
		
		private WebElement btnMakePayment;

		public void clickMakePaymentButton() {
			click(btnMakePayment);
			log.info("Payment Button clicked successfully.");
		}

		@FindBy(xpath = "//*[@id='option-2']")
		private WebElement otheramountraidiobtn;

		public void ScrollAndSelect() throws InterruptedException {
			waitForElementToBeVisible(otheramountraidiobtn);
			scrollPageToElement(otheramountraidiobtn);
			clickElementUsingJsExecutor(otheramountraidiobtn);

			log.info("Selected Other Ammount ");
		}

		@FindBy(xpath = "//*[@id='otheramount']")
		private WebElement OtheramounttextBox;

		public void EnterOtherAmmount(String amount) {
			sendKeys(OtheramounttextBox, amount);
			log.info("Entered Other Ammount");
		}

@FindBy(xpath = "//*[@id='containerDiv']/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[1]/div/div[2]/section/div[2]/div[2]/label")

		private WebElement ClickPaymentMethodIcon;

		public void ClickOnPaymentMethod() {
			click(ClickPaymentMethodIcon);
		}
		
		
		@FindBy(id = "card_dropdown")
		private WebElement crddrop;

		public void SelectAddedCard(String Card) {
			click(crddrop);
			selectByVisibleText(crddrop,Card);
			log.info("Card selected successfully.");
		}
		
		@FindBy(css = "#tokenize_payment")
		private WebElement ButtonNext;

		public void clickNextPayementButton() {
			click(ButtonNext);
			log.info("Clicked on Payment Next Button");
		}

		@FindBy(xpath = "//*[@id=\"containerDiv\"]/div[2]/div[1]/div[1]/div/div[1]/ol/li[2]/span")
		private WebElement lbl_PaymentStep2;

		public String getPaymentStep2Text() {
			log.info("Fetching Step 2 Text.");
			String stepText = getText(lbl_PaymentStep2);
			log.info("Payemet Step2 Text {}: " + stepText);
			return stepText;
		}

		@FindBy(xpath = ("//input[@type='button' and @class='submit-button stepperbtns stsubmitt' "
				+ "and @value='Submit']"))
		private WebElement btnSubmitPayment;

		public void clickPaymentSubmitBtn() {
			scrollToElement(btnSubmitPayment);
			clickElementUsingJsExecutor(btnSubmitPayment);
			log.info("Submit Button Clicked Sucessfully .");
		}

		@FindBy(xpath = "/html/body/div[10]/div/div[1]")
		private WebElement lbl_Confirmation;

		public String getConfirmationText() {
			log.info("Fetching Confirmation Text.");
			String ConfirmationText = getText(lbl_Confirmation);
			log.info("Confirmation dialogbox Text {}: " + ConfirmationText);
			return ConfirmationText;
		}

		@FindBy(xpath = "//button[@class='dlg-action ok-action' and text()='Yes']")
		private WebElement BtnYes;

		public void clickYesButton() {
			waitForElementToBeVisible(BtnYes);
			click(BtnYes);
			log.info("Clicked on Yes Button");
		}

		@FindBy(xpath = "//a[@class='submit-button st1_ok' and text()='Done']")
		private WebElement done_btn;

		public void clickDoneButton() {
			log.info("click on payment sucessfull done button.");
			scrollToElement(done_btn);
			clickElementUsingJsExecutor(done_btn);
			log.info("Done Button Clicked Sucessfully .");
		}
		
		@FindBy(id = "card_dropdown")
		private WebElement crddrp;

		public void clickOnSelectedCard() {
			click(crddrp);
				log.info("Card selected successfully.");
		}
		
		@FindBy(id = "st3_accountNumber")
		private WebElement accno;
		
		public String getAccountNum() {
			String Accno = getText(accno);
			return Accno;
			}
		
		@FindBy(id = "st3_amount")
		private WebElement billam;
		
		public String getBillAmmount() {
			String Billamt = getText(billam);
			return Billamt;
			}
	

		@FindBy(id = "st3_conFee")
		private WebElement trnfee;
		
		public String getTranFee() {
			String TranFee = getText(trnfee);
			return TranFee;
			}
		
		@FindBy(id = "st3_totalAmount")
		private WebElement totlefee;
		
		public String getTotleFee() throws InterruptedException {
			scrollPageToElement(totlefee);
			String TtlFee = getText(totlefee);
			return TtlFee;
			}
		
		@FindBy(xpath = "//*[@id='UlddlAddress']/li[1]/a/div/span")
		
		private WebElement accountnum;
		
		public String clickAndgetaccountnum() {
			click(accountnum);
			String acnum = getText(accountnum);
			return acnum;
			}
		
		
		@FindBy(xpath = "//*[@id='dLabel']")	
		private WebElement acc;
		
		public void clickAccountdrop() {
			click(accountnum);
			}	
		@FindBy(id = "st3_paymetnDate")
		private WebElement paymentDate;
		
		public String getPayementDate() {
			String PayDate = getText(paymentDate);
			return PayDate;
			}
		
		
		//*[@id="st3_amount"]
		//*[@id="st3_conFee"]
		//*[@id="st3_totalAmount"]
		//*[@id="st3_accountNumber"]
		//Account num Get Text ---//*[@id="UlddlAddress"]/li[1]/a/div/span
		
	
	

}

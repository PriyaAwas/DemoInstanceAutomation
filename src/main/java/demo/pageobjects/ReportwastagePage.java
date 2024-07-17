package demo.pageobjects;

import static sew.ai.helpers.props.FilePaths.FILE_UPLOAD_PATH;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import sew.ai.config.SCPConfiguration;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.models.User;
import sew.ai.pageObjects.scp.HomePage;

public class ReportwastagePage extends HomePage {
	private static final Logger log = LogManager.getLogger(ReportwastagePage.class);

	public ReportwastagePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

@FindBy(css = "#idReportWaterLeak")
private WebElement Preloginlink;

public void clickReportLink() {
	click(Preloginlink);
	log.info("Report Wastage clicked successfully {}.");
}

@FindBy(css = "[globalize=\"ML_Master_lbl_CustName\"]")
private WebElement txtContactUsCustomerName;

public boolean isCustomerNameTxtVisible() {
	log.info("Checking that the Customer Name field is visible on the Contact Us page.");
	return isElementVisible(txtContactUsCustomerName);
}

@FindBy(xpath = "//label[text()='Account Number']/..//input")
private WebElement txtServiceAccNo;

public boolean isServiceAccNoTxtVisible() {
	log.info("Checking that the Service Acc No field is visible on the Contact Us page.");
	return isElementVisible(txtServiceAccNo);
}

@FindBy(css = "[globalize=\"ML_MakeOTP_txt_EmailId\"]")
private WebElement txtEmailAddress;

public boolean isEmailAddressTxtVisible() {
	log.info("Checking that the Email Address field is visible on the Contact Us page.");
	return isElementVisible(txtEmailAddress);
}

@FindBy(css = ".Subject.input_effect")
protected WebElement txtSubject;

public boolean istxtSubjectTxtVisible() {
	log.info("Checking that the Subject field is visible on the Contact Us page.");
	return isElementVisible(txtSubject);
}

@FindBy(xpath = "//label[contains(text(),'Comments')]/..//textarea[@globalize='ML_CONNECTME_Lbl_Comments']")
private WebElement txtComments;

public boolean isCommentsTxtVisible() {
	log.info("Checking that the Comments field is visible on the Contact Us page.");
	return isElementVisible(txtComments);
}

@FindBy(css = "#FileUpload1")
protected WebElement btnChooseFile;

public void btnChooseFileSubmit() {
	click(btnChooseFile);
	log.info("Choose File Button clicked {}.");
}

public boolean isChooseFileBtnVisible() {
	scrollToElement(btnChooseFile);
	log.info("Choose File Button Status :" + btnChooseFile.isDisplayed());
	return btnChooseFile.isDisplayed();
}

@FindBy(css = "#FileUpload11")
protected WebElement btnChoosefile;
@FindBy(css = "#lblCustomer")
private WebElement lbl_Customer;

public boolean isChooseFilebtnVisible() {
	scrollToElement(btnChoosefile);
	log.info("Choose File Button Status :" + btnChoosefile.isDisplayed());
	return btnChoosefile.isDisplayed();
}
@FindBy(css = "[globalize=\"ML_Default_Lbl_Address\"]")
protected WebElement Address;


public boolean isAddressVisible() {
	scrollToElement(Address);
	log.info("Choose File Button Status :" + Address.isDisplayed());
	return Address.isDisplayed();
}

@FindBy(css = "#ML_NCR_DDL_Topic")
protected WebElement lbl_ContactInfo;

public String getReportwastagelbl() {
	scrollToElement(lbl_ContactInfo);
	log.info("Fetching Customer lbl");
	String label = getText(lbl_ContactInfo);
	log.info("Customer lbl is {}: " + label);
	return label;
}

public void populateEmailAddress(String emailAddress) {
	log.info("Email Address {} :" + emailAddress);
	sendKeys(txtEmailAddress, emailAddress);
	log.info("Email Address populated successfully.");
}

public void populateChooseFile(String chooseFile) {
	log.info("Subject {} :" + chooseFile);
	sendKeys(btnChooseFile, chooseFile);
	log.info("choose File populated successfully.");
}

public void addAttachmentToChooseFile(String value) {
	sendKeysWithoutCheckingVisibility(btnChooseFile, value);
	log.info("Choose File Button added successfully with File Value " + value);
	ExtentLogger.logInfo("Choose File Button added successfully with File Value " + value);
}
@FindBy(css = "#btnNext")
protected WebElement btnContactUsNext;

public void btnClickNext() throws InterruptedException {
	scrollPageToElement(btnContactUsNext);
	click(btnContactUsNext);
	log.info("Next Button clicked {}.");
}
@FindBy(css = "[class=\"heading3_formpreview\"]")
private WebElement Reviewpage;

public boolean isReviewPageHeaderVisible() {
	log.info("YouTube Tab Status :" + Reviewpage.isDisplayed());
	return Reviewpage.isDisplayed();
}
@FindBy(css = "#BtnSubmitCommentNew")
protected WebElement Submitbtn;

public void clickSubmit() {
	scrollToElement(Submitbtn);
	click(Submitbtn);
	log.info("Submit Button clicked.");
}
@FindBy(css = "[class=\"info_connect_txt\"]")
private WebElement Confirmationtxt;

public boolean isConfirmationtxtVisible() {
	log.info("YouTube Tab Status :" + Confirmationtxt.isDisplayed());
	return Confirmationtxt.isDisplayed();
}
@FindBy(css = "[class=\"select_effect\"]")
protected WebElement dropdownptns;

public String getSelectedValueInDropbox() {
	WebElement dropdownptns = null;
	String selectedText = null;
	try {
		// WebElement element = driver.findElement(locator);
		if (dropdownptns.isDisplayed()) {
			Select selObj = new Select(dropdownptns);
			dropdownptns = selObj.getFirstSelectedOption();
			selectedText = dropdownptns.getText();
			log.info("User gets the selection as  " + selectedText + " from Dropdown");
		}
	} catch (Exception e) {
		e.printStackTrace();
		System.err.format("No Element Found to perform selection" + e);

	
	return selectedText;
	}
	return selectedText;}
	@FindBy(css = "select#ML_NCR_DDL_Topic")
	protected WebElement dropdown;

	public String getSelectedValueInDropBox() {
		WebElement selectedOption = null;
		String selectedText = null;
		try {
			// WebElement element = driver.findElement(locator);
			if (dropdown.isDisplayed()) {
				Select selObj = new Select(dropdown);
				selectedOption = selObj.getFirstSelectedOption();
				selectedText = selectedOption.getText();
				log.info("User gets the selection as  " + selectedText + " from Dropdown");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.format("No Element Found to perform selection" + e);

		}
		return selectedText;
	}
	public boolean selectlstConnectMeOptions(String option) {
		boolean b = true;
		scrollToElement(dropdown);
		selectByVisibleText(dropdown, option);
		log.info("lstConnectMeOptions populated successfully.");
		return b;
	}
		public boolean isDropdownIsVisible (String option) {
			return isElementVisible(dropdown);	

		
	
}
@FindBy(xpath = "//input[@inputtype='Account']")
private WebElement txt_accNum;

public void populateAccNum(String accountNo) {
	log.info("Populating username {} :" + accountNo);
	sendKeys(txt_accNum, accountNo);
	log.info("Acc num populated successfully.");
}
@FindBy(css = "[class=\"UserName input_effect\"]")
private WebElement txt_CustName;

public void populateCustomername(String CusName) {
	log.info("Populating customerName {} :" + CusName);
	sendKeys(txt_CustName, CusName);
	log.info("Customer Name populated successfully.");
}
@FindBy(xpath = "//input[@inputtype='Email']")
private WebElement txt_Emailadd;

public void populateEmailadd(String emailadd) {
	log.info("Populating emailadd {} :" + emailadd);
	sendKeys(txt_Emailadd, emailadd);
	log.info("Email add populated successfully.");
}
public void populateSubject(String subject) {
	log.info("Subject {} :" + subject);
	sendKeys(txtSubject, subject);
	log.info("Subject populated successfully.");
}

public void populateComments(String Comments) {
	log.info("Subject {} :" + Comments);
	sendKeys(txtComments, Comments);
	log.info("Comments populated successfully.");
}
	
	public void populateaddress(String add) {
		log.info("Populating Address {} :" + add);
		sendKeys(Address, add);
		log.info("Email add populated successfully.");
	}
	@FindBy(css = "#toast-container > div")
	private WebElement ToastErrorMessage;

	public String getToastErrorMessage() {
		log.info("Fetching the label Toast Error message");
		String label = getText(ToastErrorMessage);
		log.info("label Toast Error  message {}: " + label);
		return label;
	}
}


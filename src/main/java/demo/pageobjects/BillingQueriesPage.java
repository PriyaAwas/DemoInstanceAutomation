package demo.pageobjects;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.pageObjects.scp.HomePage;

public class BillingQueriesPage extends HomePage {
	private static final Logger log = LogManager.getLogger(DashboardPage.class);

	public BillingQueriesPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#dropdownMenuButton")
	private WebElement img_profile_icon;

	public void clickImageProfileIco() {
		click(img_profile_icon);
		log.info("Image profile icon clicked {}.");
	}

	public void waitForImageProfileIcon() {
		waitForElementToBeVisible(img_profile_icon);
		log.info("Image profile icon is visible {} on the page.");
	}

	public Boolean isProfileIconVisible() {
		log.info("Profile image visibility status on the dashboard status {}: " + img_profile_icon.isDisplayed());
		return img_profile_icon.isDisplayed();
	}

	@FindBy(xpath = "//*[@id=\"module2\"]")
	private WebElement billing_dropdown;

	public Boolean isBillingdrpdwnVisisble() {
		Boolean isVisible = isElementVisible(billing_dropdown);
		log.info("Postlogin Billing dropdown button visibility status {}" + isVisible);
		return isVisible;
	}

	public void click_Billingdrpdwn() {
		click(billing_dropdown);
		log.info("Billing dropdown is clicked");
	}

	@FindBy(xpath = "//*[@role=\"menuitem\" and @title=\"Click to raise billing queries\"]")
	private WebElement BillingQueries;

	public Boolean isBillingqueriesVisisble() {
		Boolean isVisible = isElementVisible(BillingQueries);
		log.info("Billing Queries button visibility status {}" + isVisible);
		return isVisible;
	}

	public void click_Billingqueries() {
		click(BillingQueries);
		log.info("Billing Queries is clicked");
	}

	@FindBy(css = "[id=\"lnk_connectme\"]")
	private WebElement ContactUs;

	public Boolean isContactUsVisible() {
		Boolean isVisible = isElementVisible(ContactUs);
		log.info("ContactUS visibility status {}" + isVisible);
		return isVisible;
	}

	public void clickContactUS() {
		click(ContactUs);
	}

	@FindBy(xpath = "//*[contains(@id,\"_DDL_Topic\")]")
	private WebElement Topic;

	public Boolean isfieldTopicNotEmpty() {
		String topictext = getText(Topic);
		log.info("Topic fields value is: ", topictext);
		boolean isNotEmpty = !topictext.isEmpty();
		log.info("Toipc field status is" + isNotEmpty);
		return isNotEmpty;
	}

	@FindBy(css = ".Email.input_effect")
	private WebElement txtEmail;

	public boolean istxtEmailFieldVisible() throws InterruptedException {
		log.info("Checking that the Email field is visible on the Contact Us page.");
		pause(5000);
		return isElementVisible(txtEmail);
	}

	public String getEmailAddressMaxLength() {
		String maxLen = getAttribute(txtEmail, "maxlength");
		log.info("Max length of Email address field is {} " + maxLen);
		return maxLen;
	}

	public String getEmailAddressMinLength() {
		String minLen = getAttribute(txtEmail, "minlength");
		log.info("Min length of Email address field is {} " + minLen);
		return minLen;
	}

	public String getEmailAddressLabel() {
		log.info("Fetching the Email lAddress label.");
		String label = getText(txtEmail);
		log.info("Email Address label is {}: " + label);
		return label;
	}

	public void clearEmailField() {
		clear(txtEmail);
		log.info("Email field cleared {}");
	}

	public void populateEmail(String email) {
		log.info("Email {} :" + email);
		sendKeys(txtEmail, email);

	}

	@FindBy(css = ".Subject.input_effect")
	private WebElement txtSubject;

	public boolean istxtSubjectFieldVisible() throws InterruptedException {
		log.info("Checking that the Subject field is visible on the Contact Us page.");
		pause(5000);
		return isElementVisible(txtSubject);
	}

	public String gettxtSubjectMaxLength() {
		String maxLen = getAttribute(txtSubject, "maxlength");
		log.info("Max length of Subject field is {} " + maxLen);
		return maxLen;
	}

	public void clearSubjectField() {
		clear(txtSubject);
		log.info("Subject field cleared {}");
	}

	public String populateSubject(String subject) {
		log.info("Subject {} :" + subject);
		sendKeys(txtSubject, subject);
		log.info("Subject populated successfully.");
		return subject;
	}

	@FindBy(xpath = "//label[contains(text(),'Comments')]/..//textarea[@globalize='ML_CONNECTME_Lbl_Comments']")
	private WebElement txtComments;

	public boolean isCommentsFieldVisible() {
		log.info("Checking that the Comments field is visible on the Contact Us page.");
		return isElementVisible(txtComments);
	}

	public String gettxtCommentsMaxLength() {
		String maxLen = getAttribute(txtComments, "maxlength");
		log.info("Max length of Comments field is {} " + maxLen);
		return maxLen;
	}

	public void clearCommentsField() {
		clear(txtComments);
		log.info("Comments field cleared {}");
	}

	public String populateComments(String Comments) {
		log.info("Subject {} :" + Comments);
		sendKeys(txtComments, Comments);
		log.info("Comments populated successfully.");
		return Comments;

	}

	@FindBy(css = "input[globalize='ML_CONNECTME_Lbl_AddAttach']")
	private WebElement btnChooseFile;

	public void btnChooseFileSubmit() {
		click(btnChooseFile);
		log.info("Choose File Button clicked {}.");
	}

	public boolean isChooseFileBtnVisible() throws InterruptedException {
		scrollPageToElement(btnChooseFile);
		log.info("Choose File Button Status :" + btnChooseFile.isDisplayed());
		return btnChooseFile.isDisplayed();
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

	@FindBy(css = "[globalize=\"ML_SrvcRqust_Date\"]")
	private WebElement date;

	public String getcurrentdate() {
		log.info("Fetching the Current date.");
		String currdate = getAttribute(date, "value");
		log.info("Current date is." + currdate);
		return currdate;
	}

	public void validatecurrentdate() {
		LocalDate localdate = LocalDate.now();
		log.info("Systems local date is" + localdate);
		String currdate = getAttribute(date, "value");
		if (localdate.equals(currdate)) {
			log.info("Current date validated");
		} else {
			log.info("Current date isnt validated");
		}
	}

	@FindBy(css = "[class='error_messagecommon']")
	private WebElement lblGenericErrorMessage;

	public String getlblGenericErrorMessage() {
		log.info("Fetching the label Generic Error message");
		String label = getText(lblGenericErrorMessage);
		log.info("label Generic Error message {}: " + label);
		return label;
	}

	public boolean islblGenericErrorMessageVisible() {
		log.info("Generic Error message visibility Status :" + lblGenericErrorMessage.isDisplayed());
		return lblGenericErrorMessage.isDisplayed();

	}

	@FindBy(css = "input[globalize='ML_ContactUs_Next']")
	private WebElement buttonNext;

	public void clickNext() {
		click(buttonNext);
	}

	@FindBy(css = "input[globalize='ML_ContactUs_Submit']")
	private WebElement buttonSubmit;

	public void clickSubmit() {
		click(buttonSubmit);
	}

	@FindBy(xpath = "(//*[@id='btnok'])[2]")
	private WebElement buttonOK;

	public void clickOK() {
		click(buttonOK);
	}

	@FindBy(xpath = "//*[@id='dvPreviewForm']/div[1]")
	private WebElement CompTopic;

	public String ReviewTopicVal() {
		log.info("Fetching the Topic Val");
		String label = getText(CompTopic);
		log.info("Topic val is: " + label);
		return label;
	}

	@FindBy(xpath = "//*[@id='dvPreviewForm']/div[2]")
	private WebElement CompAcc;

	public String ReviewAccVal() {
		log.info("Fetching the Acc Val");
		String label = getText(CompAcc);
		log.info("Acc val is: " + label);
		return label;
	}

	@FindBy(xpath = "//*[@id='dvPreviewForm']/div[3]")
	private WebElement CompCustname;

	public String ReviewCustname() {
		log.info("Fetching the Customer name");
		String label = getText(CompCustname);
		log.info("Cust name is: " + label);
		return label;
	}

	@FindBy(xpath = "//*[@id='dvPreviewForm']/div[4]")
	private WebElement CompEmail;

	public String ReviewEmail() {
		log.info("Fetching the Email");
		String label = getText(CompEmail);
		log.info("Email is: " + label);
		return label;
	}

	@FindBy(xpath = "//*[@id='dvPreviewForm']/div[5]")
	private WebElement CompSub;

	public String ReviewSub() {
		log.info("Fetching the Sub");
		String label = getText(CompSub);
		log.info("Sub is: " + label);
		return label;
	}

	@FindBy(xpath = "//*[@id='dvPreviewForm']/div[6]")
	private WebElement CompComments;

	public String ReviewComments() {
		log.info("Fetching the Comments");
		String label = getText(CompComments);
		log.info("Comments is: " + label);
		return label;
	}

	@FindBy(xpath = "//*[@id='dvPreviewForm']/div[7]")
	private WebElement RevAttachment;

	public String ReviewAttachment() {
		log.info("Fetching the Attachment detail");
		String label = getText(RevAttachment);
		log.info("Attachement is: " + label);
		return label;
	}

	@FindBys(@FindBy(css = "#dvPreviewForm .column"))
	private List<WebElement> lbl_PreviewForm;

	public List<WebElement> getListPreviewFormColumn() {

		return lbl_PreviewForm;
	}

	@FindBys(@FindBy(css = "	#dvPreviewForm .value"))
	private List<WebElement> lbl_PreviewFormValue;

	public List<WebElement> getListPreviewFormValue() {

		return lbl_PreviewForm;
	}

	@FindBy(xpath = "//*[@id='txtBody']")
	private WebElement submissionmsg;

	public String getSubmsnMsg() {
		log.info("Fetching the Submsion message");
		String msg = getText(submissionmsg);

		log.info("Submssn msg is: " + msg);
//		String labelmsg = msg.split("ID:")[0].trim() + "ID:";
		String[] parts = msg.split("ID:");
		String labelmsg = parts[0].trim() + " ID:";
		return labelmsg;
	}

	public String ReqId() {
		log.info("Fetching the Submsion message");
		String msg = getText(submissionmsg);
		String reqId = msg.split(": ")[1];
		log.info("Req id is :" + reqId);
		return reqId;
	}

	@FindBy(xpath = "//*[@role='tab' and @globalize='ML_Submitted_Forms']")
	private WebElement tabTrackreq;

	public void clickTrackReq() {
		click(tabTrackreq);
	}

	@FindBy(xpath = "//*[@id='tblSubmittedForms_filter']/label/input")
	private WebElement Trackreqfield;

	public boolean issearchTrackreqfieldVisible() throws InterruptedException {
		log.info("Checking that the TrackReq field is visible on the Contact Us page.");
		pause(5000);
		return isElementVisible(Trackreqfield);
	}

	public void clearTrackReqfield() {
		clear(Trackreqfield);
		log.info("TrackReq field cleared {}");
	}

	public void populateTrackReq(String refId) {
		Trackreqfield.sendKeys(refId);
	}

	@FindBy(xpath = "(//*[@class='odd'])[1]")
	private WebElement TrackreqRow1;

	public void clickTrackreq() {
		log.info("Clicking on the selected Track req");
		click(TrackreqRow1);
	}
}

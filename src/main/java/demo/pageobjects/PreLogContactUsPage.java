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

public class PreLogContactUsPage extends HomePage {
	private static final Logger log = LogManager.getLogger(PostLogConnectUsPage.class);

	public PreLogContactUsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#PreLoginContactUs")
	private WebElement lnk_contactus;

	public void clickContactusLink() {
		click(lnk_contactus);
		log.info("Contact Us link clicked successfully {}.");
	}
	
	@FindBy(css = "#TrackingBtn")
	private WebElement lnk_Track;

	public void clickTrack() {
		click(lnk_Track);
		log.info("Track clicked successfully {}.");
	}


	@FindBy(css = "#trackRequest > a")
	private WebElement lnk_TrackRequestoptn;

	public void clickTrackRequestptn() {
		click(lnk_TrackRequestoptn);
		log.info("Track Request clicked.");
	}

	public boolean isTrackRequestVisible() {
		log.info("Track Request Status :" + lnk_TrackRequestoptn.isDisplayed());
		return lnk_TrackRequestoptn.isDisplayed();
	}

	@FindBy(css = "#idSaveFormtab > a")
	private WebElement lnk_SavedForms;

	public void clickSavedForms() {
		click(lnk_SavedForms);
		log.info("Saved Forms clicked.");
	}

	public boolean isSavedformVisible() {
		log.info("Page Active Status :" + lnk_SavedForms.isDisplayed());
		return lnk_SavedForms.isDisplayed();
	}

	@FindBy(css = "input#BtnSubmitCommentNew")
	private WebElement btnContactUsSubmit;

	public void btnClickSubmit() {
		click(btnContactUsSubmit);
		log.info("Submit Button clicked {}.");
	}

	public boolean isSubmitBtnVisible() {
		log.info("Submit Button Status :" + btnContactUsSubmit.isDisplayed());
		return btnContactUsSubmit.isDisplayed();
	}

	@FindBy(css = ".icon_SavedForms a")
	private WebElement lnk_Savedforms;

	public void clickSavedforms() {
		click(lnk_Savedforms);
		log.info("Saved Forms clicked.");
	}

	public boolean isSavedFormVisible() {
		log.info("Page Active Status :" + lnk_Savedforms.isDisplayed());
		return lnk_Savedforms.isDisplayed();

	}

	@FindBy(xpath = "//*[@id=\"showDiv\"]/div[1]/div/div[2]/div[2]/ul/li[1]/a")
	private WebElement lnk_Facebook;

	public void clickFacebook() {
		click(lnk_Facebook);
		log.info("Facebook link clicked.");
	}

	@FindBy(xpath = "//*[@id=\"MyAccountUsage\"]/a")
	private WebElement lnk_Twitter;

	public void clickTwitter() {
		click(lnk_Twitter);
		log.info("Twitter link clicked.");
	}

	@FindBy(xpath = "//*[@id=\"showDiv\"]/div[1]/div/div[2]/div[2]/ul/li[3]/a")
	private WebElement lnk_Youtube;

	public void clickYoutube() {
		click(lnk_Youtube);
		log.info("Youtube link clicked.");
	}

	@FindBy(xpath = "//*[@id=\"showDiv\"]/div[1]/div/div[2]/div[2]/ul/li[4]/a")
	private WebElement lnk_Instagram;

	public void clickInstagram() {
		click(lnk_Instagram);
		log.info("InstagramCmp link clicked.");
	}

	@FindBy(css = "#contctus_newtb")
	private WebElement ContactusTab;

	public void clickContactUsTab() {
		click(ContactusTab);
		log.info("Contact Us Tab clicked.");
	}

	public boolean isContactusVisible() {
		log.info("Contact Us visibility Status :" + ContactusTab.isDisplayed());
		return ContactusTab.isDisplayed();
	}

	@FindBy(css = "#social_newtb")
	private WebElement SocialmediaTab;

	public void clickSociallMedia() {
		click(SocialmediaTab);
		log.info("Social Tab clicked.");
	}

	public boolean isSocialmediaVisible() {
		log.info("Social Media visibility Status :" + SocialmediaTab.isDisplayed());
		return SocialmediaTab.isDisplayed();
	}

	@FindBy(css = "#btnNext")
	protected WebElement btnContactUsNext;

	public void btnClickNext() throws InterruptedException {
		scrollPageToElement(btnContactUsNext);
		click(btnContactUsNext);
		log.info("Next Button clicked {}.");
	}

	public boolean isNextBtnVisible() {
		log.info("Next Button Status :" + btnContactUsNext.isDisplayed());
		return btnContactUsNext.isDisplayed();
	}

	@FindBy(css = "[class=\"choosetopictxt\"]")
	protected WebElement Topiclbl;

	public boolean istopicvisible() throws InterruptedException {
		scrollToElement(Topiclbl);
		log.info("Checking that the Topc lbl is visible on the Contact Us page.");
		pause(5000);
		return isElementVisible(Topiclbl);
	}
	@FindBy(css = "[class=\"choosetopictxt\"]")
	protected WebElement TopiclbL;

	public boolean isTopicvisible() throws InterruptedException {
		scrollToElement(TopiclbL);
		log.info("Checking that the Topc lbl is visible on the Contact Us page.");
		pause(5000);
		return isElementVisible(Topiclbl);
	}


	@FindBy(css = "[class=\"Subject input_effect\"]")
	protected WebElement subject;
	

	public void clearSubject() {
		clear(TxtSubject);
		log.info("Subject field cleared {}");
	}

	public void repopulateSubject(String Subject) {
		log.info("subject {} :" + Subject);
		sendKeys(TxtSubject, Subject);
		log.info("Subject populated successfully.");
	}
	@FindBy(css = ".Subject.input_effect")
	private WebElement TxtSubject;

	public boolean istxtSubjecttxtVisible() throws InterruptedException {
		log.info("Checking that the Subject field is visible on the Contact Us page.");
		pause(5000);
		return isElementVisible(TxtSubject);
	}

	public String gettxtSubjectMaxLength() {
		String maxLen = getAttribute(TxtSubject, "maxlength");
		log.info("Max length of Subject field is {} " + maxLen);
		return maxLen;
	}

	public void clearSubjectfield() {
		clear(TxtSubject);
		log.info("Subject field cleared {}");
	}

	public void populateSubject(String subject) {
		log.info("Subject {} :" + subject);
		sendKeys(TxtSubject, subject);
		log.info("Subject populated successfully.");
	}

	

	@FindBy(css = "#toast-container > div")
	private WebElement ToastErrorMessage;

	public String getToastErrorMessage() {
		log.info("Fetching the label Toast Error message");
		String label = getText(ToastErrorMessage);
		log.info("label Toast Error  message {}: " + label);
		return label;
	}

	public void clearSubjectField() {
		clear(txtSubject);
		log.info("Subject field cleared {}");
	}

	@FindBy(css = "[class='contac_hdng_txt']")
	protected WebElement lbl_PageHeaderPost;

	public String isPageHeaderPostVisible() throws InterruptedException {
		scrollToTheTopOfPage();
//	    	scrollPageToElement(lbl_PageHeaderPost);
		log.info("Fetching Page Header Post");
		String label = getText(lbl_PageHeaderPost);
		log.info("Page Header Post is {}: " + label);
		return label;
	}

	@FindBy(css = "[globalize=\"ML_CONNECTME_Lbl_FName\"]")
	private WebElement txtFirstName;

	public boolean isFirstNameTxtVisible() {
		log.info("Checking that the Customer Name field is visible on the Contact Us page.");
		return isElementVisible(txtFirstName);
	}
	
	@FindBy(css = "[globalize=\"ML_SrvcRqust_txtbx_Contact\"]")
	private WebElement Primaryno;

	public boolean isPrimaryNoVisible() {
		log.info("Checking that the Customer Name field is visible on the Contact Us page.");
		return isElementVisible(Primaryno);
	}
	@FindBy(css = "[globalize=\"ML_LoginSupport_txtbx_Other_Email_ID\"]")
	private WebElement emailadd;

	public boolean isEmailaddVisible() {
		log.info("Checking that the Customer Name field is visible on the Contact Us page.");
		return isElementVisible(emailadd);
	}
	@FindBy(css = "[globalize=\"ML_Default_Lbl_Address\"]")
	private WebElement Address;

	public boolean isAddressVisible() {
		log.info("Checking that the Customer Name field is visible on the Contact Us page.");
		return isElementVisible(Address);
	}
	@FindBy(css = "[globalize=\"ML_Msg_AdditionalInformations\"]")
	private WebElement Addtionalcmts ;

	public boolean isAddtionalCommentsVisible() {
		log.info("Checking that the Customer Name field is visible on the Contact Us page.");
		return isElementVisible(Addtionalcmts);
	}
	@FindBy(css = "[globalize=\"ML_Register_Lbl_LastName\"]")
	private WebElement txtLastName;

	public boolean isLastNameTxtVisible() {
		log.info("Checking that the Customer Name field is visible on the Contact Us page.");
		return isElementVisible(txtLastName);
	}
	@FindBy(css = "[globalize=\"ML_Register_Lbl_LastName\"]")
	private WebElement txtLastNames;

	public boolean isLastNametxtVisible() {
		log.info("Checking that the Customer Name field is visible on the Contact Us page.");
		return isElementVisible(txtLastNames);
	}
	@FindBy(css = "[class=\"UserName input_effect\"]")
	private WebElement txtContactUsCustomerName;

	public boolean isCustomerNameTxtVisible() {
		log.info("Checking that the Customer Name field is visible on the Contact Us page.");
		return isElementVisible(txtContactUsCustomerName);
	}

	@FindBy(xpath = "//input[@type='text' and @inputtype='Account' and @title='Click to enter Account Number']")
	private WebElement txtServiceAccNo;

	public boolean isServiceAccNoTxtVisible() {
		log.info("Checking that the Service Acc No field is visible on the Contact Us page.");
		return isElementVisible(txtServiceAccNo);
	}

	@FindBy(xpath = "//input[@inputtype='Email']")
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
	@FindBy(xpath = "//span[@class='newbutton_outer file-input btn btn-primary btn-file ieBtn']")
	private WebElement btnChoosefile;

	public boolean isChooseFilebtnVisible() {
		log.info("Checking that the Email Address field is visible on the Contact Us page.");
		return isElementVisible(btnChoosefile);
	}

//	@FindBy(css = "[globalize=\"ML_CONNECTME_Lbl_AddAttach\"]")
//	private WebElement btnChoosefile;
//	
//	
//	public boolean isChooseFilebtnVisible() {
//		scrollToElement(btnChoosefile);
//		log.info("Choose File Button Status :" + btnChoosefile.isDisplayed());
//		return btnChoosefile.isDisplayed();
//	}
	
	
	@FindBy(css = "#lblCustomer")
	private WebElement lbl_Customer;

	public String getCustomerlbl() {
		log.info("Fetching Customer lbl");
		String label = getText(lbl_Customer);
		log.info("Customer lbl is {}: " + label);
		return label;
	}
	
	@FindBy(xpath = "//*[@id=\"formfieldlist\"]/div[5]/div/div")
	protected WebElement lbl_ContactInfo;
	
	public String getContactInfolbl() {
		scrollToElement(lbl_ContactInfo);
		log.info("Fetching Customer lbl");
		String label = getText(lbl_ContactInfo);
		log.info("Customer lbl is {}: " + label);
		return label;
	}
	@FindBy(xpath = "//*[@id=\"formfieldlist\"]/div[13]/div/div")
	protected WebElement txtlbl;
	
	public String getTxtlbl() {
		scrollToElement(txtlbl);
		log.info("Fetching Customer lbl");
		String label = getText(txtlbl);
		log.info("Customer lbl is {}: " + label);
		return label;
	}


	public boolean isCustomerlblVisible() {
		log.info("Customer lbl Status :" + lbl_Customer.isDisplayed());
		return lbl_Customer.isDisplayed();
	}

	@FindBys(@FindBy(css = "[class=\"select_effect\"]"))
	private WebElement dropdownOptions;

	public List<String> getDropdownOptions() {
		log.info("Fetching Post login Customer Service Contact No");
		List<String> dropdownList = getAllOptionsInListBox(dropdownOptions);
		log.info("Post login Customer Service Contact No is {}: " + dropdownList);
		return dropdownList;
	}
	@FindBys(@FindBy(css = "select#ML_NCR_DDL_Topic"))
	private WebElement dropDownOptions;

	public List<String> getdropdownOptions() {
		log.info("Fetching Smart city requests Contact No");
		List<String> dropdownList = getAllOptionsInListBox(dropdownOptions);
		log.info("Smart city requests {}: " + dropdownList);
		return dropdownList;
	}

	@FindBy(css = "#tblSavedForms .dataTables_empty")
	private WebElement td_NoMatchingResultFound;

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
	@FindBy(css = "[class=\"select_effect\"]")
	protected WebElement dropdownptns;

	public String getSelectedValueInDropbox() {
		WebElement dropdownptns = null;
		String selectedText = null;
		try {
			// WebElement element = driver.findElement(locator);
			if (dropdown.isDisplayed()) {
				Select selObj = new Select(dropdownptns);
				dropdownptns = selObj.getFirstSelectedOption();
				selectedText = dropdownptns.getText();
				log.info("User gets the selection as  " + selectedText + " from Dropdown");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.format("No Element Found to perform selection" + e);

		}
		return selectedText;
	}
	public boolean selectReqtypeOptions(String option) {
		boolean b = true;
		scrollToElement(dropdownptns);
		selectByVisibleText(dropdownptns, option);
		log.info("lstConnectMeOptions populated successfully.");
		return b;

	}
	
	public boolean isDropdownIsVisible (String option) {
		return isElementVisible(dropdownptns);	

	}
	
	@FindBy(css = "[globalize=\"ML_CONNECTME_Lbl_Outage\"]")
	protected WebElement Outageptns;

	public String getSelectedValueInDropdown() {
		WebElement dropdownptns = null;
		String selectedText = null;
		try {
			// WebElement element = driver.findElement(locator);
			if (Outageptns.isDisplayed()) {
				Select selObj = new Select(Outageptns);
				Outageptns = selObj.getFirstSelectedOption();
				selectedText = Outageptns.getText();
				log.info("User gets the selection as  " + selectedText + " from Outageptns");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.format("No Element Found to perform selection" + e);

		}
		return selectedText;
	}
	public boolean selectOutageOptions(String option) {
		boolean b = true;
		scrollToElement(Outageptns);
		selectByVisibleText(Outageptns, option);
		log.info("lstConnectMeOptions populated successfully.");
		return b;

	}

	@FindBy(css = "[globalize=\"ML_CONNECTME_Lbl_Street\"]")
	protected WebElement txtStreetno;

	public boolean isStreetNoTxtVisible() {
		scrollToElement(txtStreetno);
		log.info("Checking that the Service Acc No field is visible on the Contact Us page.");
		return isElementVisible(txtStreetno);
	}
	@FindBy(css = "[globalize=\"ML_SrvcRqust_txtbx_StreetN\"]")
	private WebElement txtStreetname;

	public boolean isStreetNameTxtVisible() {
		log.info("Checking that the Service Acc No field is visible on the Contact Us page.");
		return isElementVisible(txtStreetname);
	}
	@FindBy(css = "[globalize=\"ML_CONNECTME_Lbl_Apt\"]")
	private WebElement txtappt;

	public boolean isApptUnitTxtVisible() {
		log.info("Checking that the Service Acc No field is visible on the Contact Us page.");
		return isElementVisible(txtappt);
	}
	@FindBy(css = "[globalize=\"ML_CONNECTME_Lbl_Zip\"]")
	private WebElement Zip;

	public boolean isZipTxtVisible() {
		log.info("Checking that the Service Acc No field is visible on the Contact Us page.");
		return isElementVisible(Zip);
	}
	@FindBy(css = "[globalize=\"ML_SrvcRqust_txtbx_City5\"]")
	private WebElement city;

	public boolean isCityTxtVisible() {
		log.info("Checking that the Service Acc No field is visible on the Contact Us page.");
		return isElementVisible(city);
	}
	@FindBy(css = "[globalize=\"ML_SrvcRqust_txtbx_MState\"]")
	protected WebElement State;

	public boolean isStateTxtVisible() {
		scrollToElement(State);
		log.info("Checking that the Service Acc No field is visible on the Contact Us page.");
		return isElementVisible(State);
	}
	@FindBy(css = "[globalize=\"ML_CONNECTME_Lbl_NearestStreet\"]")
	private WebElement nearestcrss;

	public boolean isNearestcrssTxtVisible() {
		scrollToElement(nearestcrss);
		log.info("Checking that the Service Acc No field is visible on the Contact Us page.");
		return isElementVisible(nearestcrss);
	}
	@FindBy(css = "[title=\"Description%20Of%20Location\"]")
	private WebElement Descrpbox;

	public boolean isDescriptionTxtVisible() {
		scrollToElement(Descrpbox);
		log.info("Checking that the Service Acc No field is visible on the Contact Us page.");
		return isElementVisible(Descrpbox);
	}
	@FindBy(css = "[globalize=\"ML_CONNECTME_Lbl_Desc\"]")
	private WebElement Descrption;

	public boolean isDescriptionboxVisible() {
		scrollToElement(Descrption);
		log.info("Checking that the Service Acc No field is visible on the Contact Us page.");
		return isElementVisible(Descrption);
	}
	@FindBy(css = "[globalize=\"ML_SrvcRqust_Date\"]")
	private WebElement txtdate;

	public boolean isDateTxtVisible() {
		log.info("Checking that the Service Acc No field is visible on the Contact Us page.");
		return isElementVisible(txtdate);
	}
	@FindBy(xpath = "//label[text()='Account Number']/..//input")
	private WebElement txtServiceaccNo;

	public boolean isServiceaccNoTxtVisible() {
		log.info("Checking that the Service Acc No field is visible on the Contact Us page.");
		return isElementVisible(txtServiceaccNo);
	}

	public String getServiceAccNoMaxLength() {
		String maxLen = getAttribute(txtServiceAccNo, "maxlength");
		log.info("Max length of Service Acc No field is {} " + maxLen);
		return maxLen;
	}

	public String getCustomerNameLabel() {
		log.info("Fetching the Customer Name label.");
		String label = getText(txtContactUsCustomerName);
		log.info("Customer Name label is {}: " + label);
		return label;
	}

	public void populateCustomerName(String customerName) {
		log.info("Customer {} :" + customerName);
		sendKeys(txtContactUsCustomerName, customerName);
		log.info("Customer populated successfully.");
	}

	public String getServiceAccNoLabel() {
		log.info("Fetching the Forget Username page header.");
		String label = getText(txtServiceAccNo);
		log.info("Service Acc No page header is {}: " + label);
		return label;
	}

	public void populateServiceAccNo(String scmAccountNumber) {
		log.info("Subject {} :" + scmAccountNumber);
		sendKeys(txtServiceAccNo, scmAccountNumber);
		log.info("Subject populated successfully.");
	}

	public String getEmailAddressMaxLength() {
		String maxLen = getAttribute(txtEmailAddress, "maxlength");
		log.info("Max length of Email address field is {} " + maxLen);
		return maxLen;
	}

	public String getEmailAddressMinLength() {
		String minLen = getAttribute(txtEmailAddress, "minlength");
		log.info("Min length of Email address field is {} " + minLen);
		return minLen;
	}

	public String getEmailAddressLabel() {
		log.info("Fetching the Email lAddress label.");
		String label = getText(txtEmailAddress);
		log.info("Email Address label is {}: " + label);
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

	@FindBy(css = "#BtnBackCommentNew")
	private WebElement btn_ContactUsBack;

	@FindBy(css = "[id='btnok']")
	private WebElement btnContactUsPopupOk;

	public void clickContactUsPopupOk() {
		click(btnContactUsPopupOk);
		log.info("Ok Button clicked {}.");
	}

	public boolean isPreLogConnectMePage(String url, String title) {
		Boolean isForgetPasswordPage = false;
		log.info("Checking that the current page is ForgetPassword Page");
		if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
			isForgetPasswordPage = true;
		log.info("The current page is ForgetPassword Page {}: " + isForgetPasswordPage);
		return isForgetPasswordPage;
	}
	@FindBy(css = "[class=\"toast-message\"]")
	private WebElement Errormsg;

	public boolean isErrVisible() {
		log.info("Checking that the Error message visible on the Contact Us page.");
		return isElementVisible(Errormsg);

	}

	@FindBy(css = "[class='myaccount_help commonmtr_dgn']")
	private WebElement faqAccountTab;

	public boolean isfaqAccountTabVisible() {
		log.info("FAQ Account Tab visibility Status :" + faqAccountTab.isDisplayed());
		return faqAccountTab.isDisplayed();
	}


	public void clickfaqAccountTab() {
		click(faqAccountTab);
		log.info("FAQ Account Tab clicked {}.");
	}

	@FindBy(css = "[class='billingpmt_help commonmtr_dgn']")
	private WebElement faqBillingTab;

	public boolean isfaqBillingTabVisible() {
		log.info("FAQ Billing Tab visibility Status :" + faqBillingTab.isDisplayed());
		return faqBillingTab.isDisplayed();
	}

	public void clickfaqBillingTab() {
		click(faqBillingTab);
		log.info("FAQ Billing Tab clicked {}.");
	}

	@FindBy(css = "[class='custo_registration commonmtr_dgn']")
	private WebElement faqregistrationTab;

	public boolean isfaqregistrationTabVisible() {
		log.info("FAQ registration Tab visibility Status :" + faqregistrationTab.isDisplayed());
		return faqregistrationTab.isDisplayed();
	}

	public void clickfaqregistrationTab() {
		click(faqregistrationTab);
		log.info("FAQ registration Tab clicked {}.");
	}

	@FindBy(css = "[class='gnrlsupprt_help commonmtr_dgn']")
	private WebElement faqHomeTab;

	public boolean isfaqHomeTabVisible() {
		log.info("FAQ registration Tab visibility Status :" + faqHomeTab.isDisplayed());
		return faqHomeTab.isDisplayed();
	}

	public void clickfaqHomeTab() {
		click(faqHomeTab);
		log.info("FAQ registration Tab clicked {}.");
	}

	@FindBy(css = "[class='outage_related_help commonmtr_dgn']")
	private WebElement faqOutageTab;

	public boolean isfaqOutageTabVisible() {
		log.info("FAQ Outage Tab visibility Status :" + faqOutageTab.isDisplayed());
		return faqOutageTab.isDisplayed();
	}

	public void clickfaqOutageTab() {
		click(faqOutageTab);
		log.info("FAQ Outage Tab clicked {}.");
	}

	@FindBy(css = "[class='top10faq commonmtr_dgn']")
	private WebElement faqTopTab;

	public boolean isfaqTopTabVisible() {
		log.info("FAQ Top Tab visibility Status :" + faqTopTab.isDisplayed());
		return faqTopTab.isDisplayed();
	}

	public void clickfaqTopTab() {
		click(faqTopTab);
		log.info("FAQ Top Tab clicked {}.");
	}

	@FindBy(css = "[class='usagecard_help commonmtr_dgn']")
	private WebElement faqUsageTab;

	public boolean isfaqUsageTabVisible() {
		log.info("FAQ Usage Tab visibility Status :" + faqUsageTab.isDisplayed());
		return faqUsageTab.isDisplayed();
	}

	public void clickfaqUsageTab() {
		click(faqUsageTab);
		log.info("FAQ Usage Tab clicked {}.");
	}

	@FindBy(css = "[id='topic']")
	private WebElement faqPageTopic;

	public String getFAQPageTopic() {
		log.info("FAQ Page Topic");
		String label = getText(faqPageTopic);
		log.info("FAQ Page Topic is {}: " + label);
		return label;
	}

	public boolean isFAQPageTopicVisible() {
		log.info("FAQ Page Topic visibility Status :" + faqPageTopic.isDisplayed());
		return faqPageTopic.isDisplayed();
	}

	@FindBy(css = ".breadcrumb li a")
	private WebElement lnkFAQPageHelp;

	public void clickFAQPageHelp() {
		click(lnkFAQPageHelp);
		log.info("FAQ Help tab clicked {}.");
	}

	@FindBy(css = "[id='ML_NCR_DDL_Topic']")
	private WebElement dropDownTopic;

	public void clickdropDownTopic() {
		click(dropDownTopic);
		log.info("Topoc Drop Down Click clicked {}.");
	}

	@FindBy(xpath = "//input[@inputtype='Account']")
	private WebElement txtAccNo;

	public String gettxtAccountNum() {
		log.info("Fetching the Account No");

		String label = getAttribute(txtAccNo, "value");
		log.info("Service Acc No page header is {}: " + label);
		return label;
	}

	@FindBy(xpath = "//input[@globalize='ML_Master_lbl_CustName']")
	private WebElement txtCustomerNameValue;

	public String getCustomerNameValue() {
		log.info("Fetching Customer Name");
		String label = getAttribute(txtCustomerNameValue, "value");

		// String label = getText(txt_CustomerName);
		log.info("Customer Name is {}: " + label);
		return label;
	}

	@FindBy(xpath = "//input[@inputtype='Email']")
	private WebElement txtCustomerEmailValue;

	public String getCustomerEmailValue() {
		log.info("Fetching Customer email");
		String label = getAttribute(txtCustomerEmailValue, "value");
		log.info("Customer  is {}: " + label);
		return label;
	}

	@FindBys(@FindBy(css = "#dvPreviewForm .column"))
	private List<WebElement> lbl_PreviewYourFormColumn;

	public List<WebElement> listPreviewYourFormColumn() {
		return lbl_PreviewYourFormColumn;
	}

	@FindBy(css = "ul #social_newtb")
	private WebElement lnkSocialMedia;

	public void clickSocialMedia() {
		click(lnkSocialMedia);
		log.info("Social Media clicked.");
	}

	@FindBy(css = "[globalize='ML_ConnectMe_FB']")
	private WebElement tabFacebook;

	public void clickFacebookTab() {
		click(tabFacebook);
		log.info("Facebook Tab clicked.");
	}

	public boolean isFacebookTabVisible() {
		log.info("Facebook Tab Status :" + tabFacebook.isDisplayed());
		return tabFacebook.isDisplayed();
	}

	@FindBy(css = "[globalize='ML_ConnectMe_Youtube']")
	private WebElement tabYouTube;

	public void clickYouTubeTab() {
		click(tabYouTube);
		log.info("YouTube Side Menu link clicked.");
	}

	public boolean isYouTubeTabVisible() {
		log.info("YouTube Tab Status :" + tabYouTube.isDisplayed());
		return tabYouTube.isDisplayed();
	}
	
	@FindBy(css = "[class=\"heading3_formpreview\"]")
	private WebElement Reviewpage;
	
	public boolean isReviewPageHeaderVisible() {
		log.info("YouTube Tab Status :" + Reviewpage.isDisplayed());
		return Reviewpage.isDisplayed();
	}
	
	@FindBy(css = "#fixedinfo_Preview > h3")
	private WebElement ReviewPage;
	
	public boolean isReviewPageheaderVisible() {
		log.info("YouTube Tab Status :" + ReviewPage.isDisplayed());
		return Reviewpage.isDisplayed();
	}
	
	@FindBy(css = "#txtBody")
	private WebElement confirmSave;
	
	public boolean isSavedconfirmationVisible() {
		log.info("YouTube Tab Status :" + confirmSave.isDisplayed());
		return confirmSave.isDisplayed();
	}
	@FindBy(css = "#txtBody")
	private WebElement confirmmsg;
	
	public boolean isconfirmationmsgVisible() {
		log.info("YouTube Tab Status :" + confirmmsg.isDisplayed());
		return confirmmsg.isDisplayed();
	}
	@FindBy(css = "#btnok']")
	private WebElement Okbutton;

	public void clickokbtn() {
		click(Okbutton);
		log.info("ok clicked.");
	}
	@FindBy(css = "[class=\"info_connect_txt\"]")
	private WebElement Confirmationtxt;
	
	public boolean isConfirmationtxtVisible() {
		log.info("YouTube Tab Status :" + Confirmationtxt.isDisplayed());
		return Confirmationtxt.isDisplayed();
	}

	@FindBy(css = "[globalize='ML_ConnectMe_tw']")
	private WebElement tabTwitter;

	public void clickTwitterTab() {
		click(tabTwitter);
		log.info("Twitter tab clicked.");
	}
	@FindBy(css = "#btnok")
	private WebElement Okbtn;

	public void clickOkBtn() {
		click(Okbtn);
		log.info("Okay clicked.");
	}
	public boolean isTwitterTabVisible() {
		log.info("Twitter Tab Status :" + tabTwitter.isDisplayed());
		return tabTwitter.isDisplayed();
	}
	@FindBy(css = "#SubmitBtn")
	private WebElement submit;

	public void clickSubmitBtn() {
		click(submit);
		log.info("Submit clicked.");
	}
	@FindBy(xpath = "//*[@id=\"idSaveFormtab\"]/a")
	private WebElement savedform;

	public void clickSavedform() {
		click(savedform);
		log.info("Saved form clicked.");
	}
	

	@FindBy(css = "[globalize='ML_ConnectMe_Instagram']")
	private WebElement tabInstagram;

	public void clickInstagramTab() {
		click(tabInstagram);
		log.info("Instagram tab clicked.");
	}
	
	@FindBy(css = "#BtnSubmitCommentNew")
	protected WebElement Submitbtn;

	public void clickSubmit() {
		scrollToElement(Submitbtn);
		click(Submitbtn);
		log.info("Submit Button clicked.");
	}

	public boolean isInstagramTabVisible() {
		log.info("Twitter Tab Status :" + tabTwitter.isDisplayed());
		return tabTwitter.isDisplayed();
	}
	@FindBy(css = "[class=\"hdngsidepopup\"]")
	private WebElement TrackReqHeader;
	
	public boolean isTrackReqVisible() {
		log.info("Header Status :" + TrackReqHeader.isDisplayed());
		return TrackReqHeader.isDisplayed();
	}
	@FindBy(css = "#caseNumber")
	private WebElement CaseNum;
	
	public String getCasenum() {
		log.info("Text Status :" + CaseNum.isDisplayed());
		String lable = getText(CaseNum);
		return lable;
	}
	
	@FindBy(css = "#accordionOne > div:nth-child(1) > div.panel-heading > h4 > a > div")
	private WebElement Programsdrpdwn;

	public void clickPrograms() {
		click(Programsdrpdwn);
		log.info("Programs Drop down clicked.");
	}
	
	@FindBy(xpath = "//*[@id=\"collapse1\"]/div/div[1]/strong")
	private WebElement Subjecttxt;
	
	public boolean isSubjectVisible() {
		log.info("Text Status :" + Subjecttxt.isDisplayed());
		return Subjecttxt.isDisplayed();
	}
	
	@FindBy(xpath = "//*[@id=\"collapse1\"]/div/div[2]/strong")
	private WebElement Desctxt;
	
	public boolean isDescriptionVisible() {
		log.info("Text Status :" + Desctxt.isDisplayed());
		return Desctxt.isDisplayed();
	}
	@FindBy(css = "#txtChat")
	private WebElement txtbox;
	
	public boolean isTextboxVisible() {
		log.info("Text box Status :" + txtbox.isDisplayed());
		return txtbox.isDisplayed();
	}
	
	@FindBy(css = "#Attachment-Dropdown")
	private WebElement attchdrpdwn;

	public void clickAttachments() {
		click(attchdrpdwn);
		log.info("Attachments Drop down clicked.");
	}
	@FindBy(css = "#closeRequestbox > a")
	private WebElement closeIcon;

	public void clickCloseIcon() {
		click(closeIcon);
		log.info("Close button clicked.");
	}

	@FindBy(css = ".icon_SubmittedForms a")
	private WebElement lnk_TrackRequestCmp;

	public void clickTrackRequest() {
		click(lnk_TrackRequestCmp);
		log.info("Track Request clicked.");
	}

	@FindBys(@FindBy(css = "#trackRequest > a"))
	private List<WebElement> th_TrackReqGridHeader;

	public List<WebElement> listTrackReqGridHeader() {
		return th_TrackReqGridHeader;
	}

	public List<String> getSubmittedFormsHeaders() {
		return getAllElementsTextInList(th_TrackReqGridHeader);
	}

	@FindBys(@FindBy(css = "#tblSavedForms th"))
	private List<WebElement> th_SavedFormsHeaders;

	public List<WebElement> listSavedFormsHeaders() {
		return th_SavedFormsHeaders;
	}

	public List<String> getSavedFormsHeaders() {
		return getAllElementsTextInList(th_SavedFormsHeaders);
	}

	@FindBy(css = "//input[@inputtype='Account']")
	private WebElement txt_AccNum;

	public void populateAcc(String userName) {
		log.info("Populating username {} :" + userName);
		sendKeys(txt_AccNum, userName);
		log.info("Username populated successfully.");
	}

	@FindBy(xpath = "//input[@inputtype='Account']")
	private WebElement txt_accNum;

	public void populateAccNum(String accountNo) {
		log.info("Populating username {} :" + accountNo);
		sendKeys(txt_accNum, accountNo);
		log.info("Acc num populated successfully.");
	}

	@FindBy(css = "[globalize='ML_Track_Request']")
	private WebElement SearchTrackReq;

	public void populateSearchReqIdSavedForms(String requestID) {
		log.info("Request ID {} :" + requestID);
		sendKeys(SearchTrackReq, requestID);
		log.info("Request ID populated successfully.");
	}

	@FindBys(@FindBy(css = "#tblSubmittedForms tbody tr > td:nth-child(1) a"))
	private List<WebElement> td_PostLoginTrackReqReqId;

	public List<WebElement> listTrackReqID() {
		return td_PostLoginTrackReqReqId;
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

	@FindBy(xpath = "//*[@id=\"faqsdata\"]/div[1]/div/div[1]")
	private WebElement paneltitles;

	public void clickPaneldropdown() {
		click(paneltitles);
		log.info("Panel Titles clicked.");
	}
	
	@FindBy(css = "#btnSave")
	private WebElement savebtn;

	public void clickSave() {
		click(savebtn);
		log.info("Save clicked.");
	}

	@FindBy(xpath = "//input[@inputtype='Account']")
	private WebElement TrackReqheader;

	public String gettxttrackreq() {
		log.info("track req");

		String label = getAttribute(TrackReqheader, "value");
		log.info("Service Acc No page header is {}: " + label);
		return label;
	}

	@FindBy(xpath = "//*[@id=\"faqsdata\"]/div[1]/div/div[1]/h4/a")
	private WebElement Accpaneltitles;

	public void clickAccPaneldropdown() {
		click(Accpaneltitles);
		log.info("Panel Titles clicked.");
	}
	
	@FindBy(xpath = "//*[@id=\"faqsdata\"]/div[1]/div/div[1]/h4/a")
	private WebElement Billpaneltitles;

	public void clickBillingPaneldropdown() {
		click(Billpaneltitles);
		log.info("Panel Titles clicked.");
	}
	@FindBy(xpath = "//*[@id=\"faqsdata\"]/div[1]/div/div[1]/h4/a")
	private WebElement TopFAQspaneltitles;

	public void clickFQAPaneldropdown() {
		click(TopFAQspaneltitles);
		log.info("Panel Titles clicked.");
	}
	
	@FindBy(xpath = "//*[@id=\"faqsdata\"]/div/div/div[1]/h4/a")
	private WebElement Outagepaneltitles;

	public void clickOutagedropdown() {
		click(Outagepaneltitles);
		log.info("Panel Titles clicked.");
	}
	
	@FindBy(xpath = "//*[@id=\"faqsdata\"]/div[1]/div/div[1]/h4/a")
	private WebElement Cusregpaneltitles;

	public void clickCustRegdropdown() {
		click(Cusregpaneltitles);
		log.info("Panel Titles clicked.");
	}
	@FindBy(xpath = "//*[@id=\"faqsdata\"]/div[1]/div/div[1]/h4/a")
	private WebElement Usgpaneltitles;

	public void clickUsagePaneldropdown() {
		click(Usgpaneltitles);
		log.info("Panel Titles clicked.");
	}
	
	@FindBy(xpath = "#txtSaveId#txtSaveId")
	private WebElement submitBtn;

	public void clickSubmitbtn() {
		click(submitBtn);
		log.info("Submit clicked.");
	}

	@FindBy(xpath = "//input[@inputtype='Account']")
	private WebElement CustomerservHeader;

	public String getCustServ() {
		scrollToElement(CustomerservHeader);
		log.info("CustomerservHeader");

		String label = getAttribute(CustomerservHeader, "value");
		log.info("Customer Service page header is {}: " + label);
		return label;
	}
		
		@FindBy(xpath = "//*[@id=\"showDiv\"]/div[2]/div[3]/div[2]/div/ul[1]/li[1]/b")
		protected WebElement CustomerServHeader;

		public boolean isCustomerservVisible() {
			scrollToElement(CustomerservHeader);
			log.info("Customer Service header visibility Status :" + CustomerServHeader.isDisplayed());
			return faqAccountTab.isDisplayed();
		}
		
		@FindBy(xpath = "//*[@id=\"liCService\"]/div[2]/p/span")
		private WebElement helplineNo;

		public boolean isHelplineNoVisible() {
			log.info("Helpline No visibility Status :" + helplineNo.isDisplayed());
			return faqAccountTab.isDisplayed();
		}
		
		@FindBy(xpath = "//*[@id=\"liemail1\"]/div[2]/p/span")
		private WebElement helplinemailadd;

		public boolean isHelplineemailaddVisible() {
			log.info("Helpine Email add visibility Status :" + helplinemailadd.isDisplayed());
			return faqAccountTab.isDisplayed();
		}
		
		@FindBy(css = "#txtTrackingId")
		private WebElement TxtReqID;

		public boolean istxtRequestIDVisible() throws InterruptedException {
			log.info("Checking that the Request ID is visible on the Contact Us page.");
			pause(5000);
			return isElementVisible(TxtReqID);
		}
		
		@FindBy(xpath = "//*[@id=\"collapse2\"]/div/div/div/div[1]")
		private WebElement attachment1;

		public boolean isattachment1Visible() throws InterruptedException {
			log.info("Checking that the attachment is visible on the Contact Us page.");
			pause(5000);
			return isElementVisible(attachment1);
		}
		@FindBy(xpath = "//*[@id=\"collapse2\"]/div/div/div/div[2]")
		private WebElement attachment2;

		public boolean isattachment2Visible() throws InterruptedException {
			log.info("Checking that the attachment is visible on the Contact Us page.");
			pause(5000);
			return isElementVisible(attachment2);
		}


		public String gettxtREQIDMaxLength() {
			String maxLen = getAttribute(TxtReqID, "maxlength");
			log.info("Max length of Req ID field is {} " + maxLen);
			return maxLen;
		}

		public void clearReqIDfield() {
			clear(TxtReqID);
			log.info("Req ID cleared {}");
		}

		public void populateReqID(String ReqID) {
			log.info("ReqId {} :" + ReqID);
			sendKeys(TxtReqID, ReqID);
			log.info("Subject populated successfully.");
		}

		public void populateComments(String Comments) {
			log.info("Subject {} :" + Comments);
			sendKeys(txtComments, Comments);
			log.info("Comments populated successfully.");
		}
		
		@FindBy(css = "#txtSaveId")
		private WebElement reqIdField;

		public void populateRequestID(String ReqId) {
			log.info("Populating username {} :" + ReqId);
			sendKeys(reqIdField, ReqId);
			log.info("Username populated successfully.");
		}
	
	}

	
	
	


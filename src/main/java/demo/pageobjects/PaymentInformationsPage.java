package demo.pageobjects;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sew.ai.pageObjects.scp.HomePage;

public class PaymentInformationsPage extends HomePage {
    private static final Logger log = LogManager.getLogger(PaymentInformationsPage.class);

    public PaymentInformationsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    ///////////// PAGE TITLE //////////

    @FindBy(id = "PageTitle")
    private WebElement lblPaymentInfoPageHeading;

    public String getLblPaymentInfoPageHeadingText() {
        log.info("Getting the text of Payment page Heading Text.");
        waitForElementToBeVisible(lblPaymentInfoPageHeading);
        String pageHeading = getText(lblPaymentInfoPageHeading);
        log.info("Page Title Heading Text Visiblity Staus :" + lblPaymentInfoPageHeading.isDisplayed());
        return pageHeading;
    }

    @FindBy(id = "page_loader")
    private WebElement pageLoader;

    public void waitUntilCompletePageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.attributeContains(pageLoader, "style", "display: none;"));
    }

    ///////////// ADD NEW PAYMENT OBJECTS AND METHODS /////////////

    @FindBy(id = "addnewpayment")
    private WebElement lnkAddPaymentMethod;

    public String getLnkAddPaymentMethodText() {
        log.info("Waiting for Visiblity of Add Payment.");
        waitForElementToBeClickable(lnkAddPaymentMethod);
        log.info("Add Payment Method Text Visiblity Staus :" + lnkAddPaymentMethod.isDisplayed());
        String text = lnkAddPaymentMethod.getText();
        return text;
    }

    public void clickLnkAddPaymentMethod() {
        log.info("Clicking Add Payment Method Button ");
        clickWithJSExecutor(lnkAddPaymentMethod);
    }

    @FindBy(css = "[id='btnAddUpdate']")
    private WebElement btnAddPaymentMethod;

    public void clickBtnAddPaymentMethod() {
        log.info("Clicking Add Payment Method Button ");
        clickWithJSExecutor(btnAddPaymentMethod);
    }

    @FindBy(css = "li[id='SetasDefault_1'] a")
    private WebElement btnSetDefault;

    public void clickBtnSetDefault() {
        log.info("Clicking Set Default Payment Method Button");
        clickWithJSExecutor(btnSetDefault);
    }

    @FindBy(css = "button[id='btnDefaultpopupsave']")
    private WebElement btnMakeDefault;

    public void clickBtnMakeDefault() {
        log.info("Clicking Confirm Make Default Payment Method Button ");
        clickWithJSExecutor(btnMakeDefault);
    }

    @FindBy(css = ".defautl_btn")
    private WebElement lblDefault;

    public boolean isLblDefaultVisible() {
        log.info("Waiting For Visiblity status of Default Label");
        waitForElementToBeVisible(lblDefault);
        log.info("Visiblity status of Default Label is:" + lblDefault.isDisplayed());
        return lblDefault.isDisplayed();
    }

    @FindBy(css = "i[class*='exclamation-circle']")
    private WebElement iconCardExp;

    public boolean isIconCardExpVisible() {
        log.info("Waiting For Visiblity status of Icon Card Exp");
        waitForElementToBeVisible(iconCardExp);
        log.info("Visiblity status of Icon Card Exp is:" + iconCardExp.isDisplayed());
        return iconCardExp.isDisplayed();
    }

    public String clrIconCardExp() {
        log.info("Waiting For Visiblity status of Icon Card Exp");
        waitForElementToBeVisible(iconCardExp);
        log.info("Visiblity status of Icon Card Exp is:" + iconCardExp.isDisplayed());
        return getCssValue(iconCardExp, "color");
    }
///////////// BANK PAYMENT OBJECTS AND METHODS /////////////

    @FindBy(css = "input[radio_btn_name='Bank']")
    private WebElement rdoBtnBank;

    public boolean isRdobtnBankVisible() {
        scrollToElement(rdoBtnBank);
        log.info("Waiting For Visiblity status of Bank Radio Button");
        waitForElementToBeVisible(rdoBtnBank);
        log.info("Visiblity status of Bank Radio Button is:" + rdoBtnBank.isDisplayed());
        return rdoBtnBank.isDisplayed();
    }

    public void clickRdoBtnBank() {
        log.info("Clicking Add Bank Radio Button ");
        clickWithJSExecutor(rdoBtnBank);
        pause(3000);
    }

    @FindBy(xpath = "//*[@id=\"profile_widget\"]/section/div/div[2]/div/div/div/div/div/div[1]/div/div/section/div[1]/label/span")
    private WebElement optnBankAcc;

    public void clickBnkOpt() {
        log.info("Clicking Bank Account Option ");
        clickWithJSExecutor(optnBankAcc);
        pause(3000);
    }

    //@FindBy(css = "div[class*='crdbt_crd'] #accholrnm")
    @FindBy(id = "accholrnm")
    private WebElement txtBoxAccountHolderName;

    public boolean isTxtBoxAccountHolderNameVisible() {
        scrollToElement(txtBoxAccountHolderName);
        log.info("Waiting For Visiblity status of Account Holder Name Text Feild");
        waitForElementToBeVisible(txtBoxAccountHolderName);
        log.info("Visiblity status of Account Holder Name Text Feild:" + txtBoxAccountHolderName.isDisplayed());
        return txtBoxAccountHolderName.isDisplayed();
    }

    public void populateTxtBoxAccountHolderName(String Text) {
        log.info("Waiting For Visiblity status of Account Holder Name Text Feild");
        waitForElementToBeVisible(txtBoxAccountHolderName);
        log.info("Visiblity status of Account Holder Name Text Feild:" + txtBoxAccountHolderName.isDisplayed());
        log.info("Populating Account Holder Name with" + Text);
        clear(txtBoxAccountHolderName);
        sendKeys(txtBoxAccountHolderName, Text);
    }

    public WebElement elementAccountHolderName() {
        log.info("Waiting For Visiblity status of Account Holder Name Text Feild");
        waitForElementToBeVisible(txtBoxAccountHolderName);
        log.info("Visiblity status of Account Holder Name Text Feild:" + txtBoxAccountHolderName.isDisplayed());
        return txtBoxAccountHolderName;
    }

    @FindBy(css = "label[for='accholrnm']")
    private WebElement lblTxtBoxAccountHolderName;

    public String getLblTxtBoxAccountHolderName() {
        log.info("Waiting For Visiblity status of Account Holder Name Text Feild Label");
        waitForElementToBeVisible(lblTxtBoxAccountHolderName);
        log.info("Visiblity status of label Account Holder Name Text Feild:" + lblTxtBoxAccountHolderName.isDisplayed());
        return getText(lblTxtBoxAccountHolderName);
    }

    @FindBy(css = "label[for='accholrnm']+span")
    private WebElement errMsgAccountHolderName;

    public String getErrMsgAccountHolderName() throws InterruptedException {
        log.info("Waiting For Visiblity status of Account Holder Name Text Feild Error Message");
        scrollPageToElement(errMsgAccountHolderName);
        waitForElementToBeVisible(errMsgAccountHolderName);
        log.info("Visiblity status of label Account Holder Name Error Message:" + errMsgAccountHolderName.isDisplayed());
        return getText(errMsgAccountHolderName);
    }

    @FindBy(id = "rtno")
    private WebElement txtBoxRoutingNumber;

    public boolean isTxtBoxRoutingNumberVisible() {
        scrollToElement(txtBoxRoutingNumber);
        log.info("Waiting For Visiblity status of Routing No Text Feild");
        waitForElementToBeVisible(txtBoxRoutingNumber);
        log.info("Visiblity status of Routing No Text Feild:" + txtBoxRoutingNumber.isDisplayed());
        return txtBoxRoutingNumber.isDisplayed();
    }

    public void populateTxtBoxRoutingNumber(String Text) {
        log.info("Waiting For Visiblity status of Routing Number Text Feild");
        waitForElementToBeVisible(txtBoxRoutingNumber);
        log.info("Visiblity status of Routing No Text Feild:" + txtBoxRoutingNumber.isDisplayed());
        log.info("Populating Routing Number feild with" + Text);
        clear(txtBoxRoutingNumber);
        sendKeys(txtBoxRoutingNumber, Text);
    }

    public WebElement elementRoutingNumber() {
        log.info("Waiting For Visiblity status of Routing Number Text Feild");
        waitForElementToBeVisible(txtBoxRoutingNumber);
        log.info("Visiblity status of Routing Number Text Feild:" + txtBoxRoutingNumber.isDisplayed());
        return txtBoxRoutingNumber;
    }

    @FindBy(css = "#rtno+span+label[for=rtno]")
    private WebElement lblTxtBoxRoutingNumber;

    public String getLblTxtBoxRoutingNumber() {
        log.info("Waiting For Visiblity status of Routing Number Text Feild Label");
        waitForElementToBeVisible(lblTxtBoxRoutingNumber);
        log.info("Visiblity status of label Routing Number Text Feild:" + lblTxtBoxRoutingNumber.isDisplayed());
        return getText(lblTxtBoxRoutingNumber);
    }

    @FindBy(css = ".rtnoInfo+span")
    private WebElement errMsgRoutingNumber;

    public String getErrMsgRoutingNumber() {
        log.info("Waiting For Visiblity status of Routing Number Text Feild Error Message");
        waitForElementToBeVisible(errMsgRoutingNumber);
        log.info("Visiblity status of label Routing Number Error Message:" + errMsgRoutingNumber.isDisplayed());
        return getText(errMsgRoutingNumber);
    }

    @FindBy(id = "crtno")
    private WebElement txtBoxConfirmRoutingNumber;

    public boolean isTxtBoxConfirmRoutingNumberVisible() {
        scrollToElement(txtBoxConfirmRoutingNumber);
        log.info("Waiting For Visiblity status of Confirm Routing No Text Feild");
        waitForElementToBeVisible(txtBoxConfirmRoutingNumber);
        log.info("Visiblity status of Confirm Routing No Text Feild:" + txtBoxRoutingNumber.isDisplayed());
        return txtBoxConfirmRoutingNumber.isDisplayed();
    }

    public void populateTxtBoxConfirmRoutingNumber(String Text) {
        log.info("Waiting For Visiblity status of Confirm Routing Number Text Feild");
        waitForElementToBeVisible(txtBoxConfirmRoutingNumber);
        log.info("Visiblity status of Confirm Routing No Text Feild:" + txtBoxConfirmRoutingNumber.isDisplayed());
        log.info("Populating Confirm Routing No feild with" + Text);
        clear(txtBoxConfirmRoutingNumber);
        sendKeys(txtBoxConfirmRoutingNumber, Text);
    }

    public WebElement elementConfirmRoutingNumber() {
        log.info("Waiting For Visiblity status of Confirm Routing Number Text Feild");
        waitForElementToBeVisible(txtBoxConfirmRoutingNumber);
        log.info("Visiblity status of Confirm Routing Number Text Feild:" + txtBoxConfirmRoutingNumber.isDisplayed());
        return txtBoxConfirmRoutingNumber;
    }

    @FindBy(css = "#crtno+span+label[for=rtno]")
    private WebElement lblTxtBoxConfirmRoutingNumber;

    public String getLblTxtBoxConfirmRoutingNumber() {
        log.info("Waiting For Visiblity status of Confirm Routing No Text Feild Label");
        waitForElementToBeVisible(lblTxtBoxConfirmRoutingNumber);
        log.info("Visiblity status of label Confirm Routing No Text Feild:" + lblTxtBoxConfirmRoutingNumber.isDisplayed());
        return getText(lblTxtBoxConfirmRoutingNumber);
    }

    @FindBy(css = "#crtno+span+label[for=rtno]+span")
    private WebElement errMsgConfirmRoutingNumber;

    public String getErrMsgConfirmRoutingNumber() {
        log.info("Waiting For Visiblity status of Confirm Routing Number Text Feild Error Message");
        waitForElementToBeVisible(errMsgConfirmRoutingNumber);
        log.info("Visiblity status of label confirm Routing Number Error Message:" + errMsgConfirmRoutingNumber.isDisplayed());
        return getText(errMsgConfirmRoutingNumber);
    }

    @FindBy(id = "bnkname")
    private WebElement txtBoxBankName;

    public boolean isTxtBoxBankNameVisible() {
        scrollToElement(txtBoxBankName);
        log.info("Waiting For Visiblity status of Bank Name Text Feild");
        waitForElementToBeVisible(txtBoxBankName);
        log.info("Visiblity status of Bank Name Text Feild:" + txtBoxBankName.isDisplayed());
        return txtBoxBankName.isDisplayed();
    }

    public void populateTxtBoxBankName(String Text) {
        log.info("Waiting For Visiblity status of Bank Name Text Feild");
        waitForElementToBeVisible(txtBoxBankName);
        log.info("Visiblity status of Bank Name Text Feild:" + txtBoxBankName.isDisplayed());
        log.info("Populating Bank Name feild with" + Text);
        clear(txtBoxBankName);
        sendKeys(txtBoxBankName, Text);
    }

    public WebElement elementBankName() {
        log.info("Waiting For Visiblity status of Bank Name Feild");
        waitForElementToBeVisible(txtBoxBankName);
        log.info("Visiblity status of Bank Name Text Feild:" + txtBoxBankName.isDisplayed());
        return txtBoxBankName;
    }

    @FindBy(css = "#bnkname+label[for='bnkname']")
    private WebElement lblTxtBoxBankName;

    public String getLblTxtBankName() {
        log.info("Waiting For Visiblity status of Bank Name Text Feild Label");
        waitForElementToBeVisible(lblTxtBoxBankName);
        log.info("Visiblity status of label Bank Name Text Feild:" + lblTxtBoxBankName.isDisplayed());
        return getText(lblTxtBoxBankName);
    }

    @FindBy(id = "bnkaccno")
    private WebElement txtBoxBankAccountNo;

    public boolean isTxtBoxBankAccountNoVisible() {
        scrollToElement(txtBoxBankAccountNo);
        log.info("Waiting For Visiblity status of Bank Account Number Text Feild");
        waitForElementToBeVisible(txtBoxBankAccountNo);
        log.info("Visiblity status of Bank Account Number Text Feild:" + txtBoxBankAccountNo.isDisplayed());
        return txtBoxBankAccountNo.isDisplayed();
    }

    public void populateTxtBoxBankAccountNo(String Text) {
        log.info("Waiting For Visiblity status of Bank Account Number Text Feild");
        waitForElementToBeVisible(txtBoxBankAccountNo);
        log.info("Visiblity status of Bank Account Number Text Feild:" + txtBoxBankAccountNo.isDisplayed());
        log.info("Populating Bank Name feild with" + Text);
        clear(txtBoxBankAccountNo);
        sendKeys(txtBoxBankAccountNo, Text);
    }

    public WebElement elementAccountNumber() {
        log.info("Waiting For Visiblity status of Account Number Text Feild");
        waitForElementToBeVisible(txtBoxBankAccountNo);
        log.info("Visiblity status of Account Number Text Feild:" + txtBoxBankAccountNo.isDisplayed());
        return txtBoxBankAccountNo;
    }

    @FindBy(css = "#bnkaccno+span+label[for='bnkaccno']")
    private WebElement lblTxtBoxBankAccountNo;

    public String getLblTxtBoxBankAccountNo() {
        log.info("Waiting For Visiblity status of Bank Account Number Text Feild Label");
        waitForElementToBeVisible(lblTxtBoxBankAccountNo);
        log.info("Visiblity status of label Bank Account Number Text Feild:" + lblTxtBoxBankAccountNo.isDisplayed());
        return getText(lblTxtBoxBankAccountNo);
    }

    @FindBy(css = "#bnkaccno+span+label[for='bnkaccno']+span")
    private WebElement errMsgAccountNumber;

    public String getErrMsgAccountNumber() {
        log.info("Waiting For Visiblity status of Account Number Text Feild Error Message");
        waitForElementToBeVisible(errMsgAccountNumber);
        log.info("Visiblity status of label Account Number Error Message:" + errMsgAccountNumber.isDisplayed());
        return getText(errMsgAccountNumber);
    }

    @FindBy(id = "bnkaccnomask")
    private WebElement txtBoxConfirmBankAccountNo;

    public boolean isTxtBoxConfirmBankAccountNoVisible() {
        scrollToElement(txtBoxConfirmBankAccountNo);
        log.info("Waiting For Visiblity status of Confirm Bank Account Number Text Feild");
        waitForElementToBeVisible(txtBoxConfirmBankAccountNo);
        log.info("Visiblity status of Confirm Bank Account Number Text Feild:" + txtBoxConfirmBankAccountNo.isDisplayed());
        return txtBoxConfirmBankAccountNo.isDisplayed();
    }

    public void populateTxtBoxConfirmBankAccountNo(String Text) throws InterruptedException {
        scrollPageToElement(txtBoxConfirmBankAccountNo);
        log.info("Waiting For Visiblity status of Confirm Bank Account Number Text Feild");
        waitForElementToBeVisible(txtBoxConfirmBankAccountNo);
        log.info("Visiblity status of Confirm Bank Account Number Text Feild:" + txtBoxConfirmBankAccountNo.isDisplayed());
        log.info("Populating Confirm Bank Name feild with" + Text);
        clear(txtBoxConfirmBankAccountNo);
        sendKeys(txtBoxConfirmBankAccountNo, Text);
    }

    public WebElement elementConfirmAccountNumber() {
        log.info("Waiting For Visiblity status of Confirm Account Number Text Feild");
        waitForElementToBeVisible(txtBoxConfirmBankAccountNo);
        log.info("Visiblity status of Confirm Account Number Text Feild:" + txtBoxConfirmBankAccountNo.isDisplayed());
        return txtBoxConfirmBankAccountNo;
    }

    @FindBy(css = "#bnkaccnomask+span+label[for='bnkaccnomask']")
    private WebElement lblTxtBoxConfirmBankAccountNo;

    public String getLblTxtBoxConfirmBankAccountNo() {
        log.info("Waiting For Visiblity status of Confirm Bank Account Number Text Feild Label");
        waitForElementToBeVisible(lblTxtBoxConfirmBankAccountNo);
        log.info("Visiblity status of label Confirm Bank Account Number Text Feild:" + lblTxtBoxConfirmBankAccountNo.isDisplayed());
        return getText(lblTxtBoxConfirmBankAccountNo);
    }

    @FindBy(css = "#bnkaccnomask+span+label[for='bnkaccnomask']+span")
    private WebElement errMsgConfirmAccountNumber;

    public String getErrMsgConfirmAccountNumber() {
        log.info("Waiting For Visiblity status of confirm Account Number Text Feild Error Message");
        waitForElementToBeVisible(errMsgConfirmAccountNumber);
        log.info("Visiblity status of label Account Number Error Message:" + errMsgConfirmAccountNumber.isDisplayed());
        return getText(errMsgConfirmAccountNumber);
    }

    @FindBy(id = "st2_accountType")
    private WebElement ddBankAccountType;

    public boolean isDDBankAccountTypeVisible() {
        scrollToElement(ddBankAccountType);
        log.info("Waiting For Visiblity status of DD Bank Account Type Text Feild");
        waitForElementToBeVisible(ddBankAccountType);
        log.info("Visiblity status of DD Bank Account Type Text Feild:" + ddBankAccountType.isDisplayed());
        return ddBankAccountType.isDisplayed();
    }

    public WebElement elementDDBankAccountType() {
        log.info("Waiting For Visiblity status of DD Bank Account Type Text Feild");
        waitForElementToBeVisible(txtBoxConfirmBankAccountNo);
        log.info("Visiblity status of Confirm Bank Account Number Text Feild:" + txtBoxConfirmBankAccountNo.isDisplayed());
        log.info("Returning the DD Bank account Type");
        return ddBankAccountType;
    }

    @FindBy(css = ".seperationheading")
    private WebElement txtLblBillingAddress;

    public boolean isTxtLblBillingAddressVisible() {
        scrollToElement(txtLblBillingAddress);
        log.info("Waiting For Visiblity status of Confirm Bank Account Number Text Feild");
        waitForElementToBeVisible(txtLblBillingAddress);
        log.info("Visiblity status of Confirm Bank Account Number Text Feild:" + txtLblBillingAddress.isDisplayed());
        return txtLblBillingAddress.isDisplayed();
    }

    public String getTxtLblBillingAddress() {
        log.info("Waiting For Visiblity status of Confirm Bank Account Number Text Feild Label");
        waitForElementToBeVisible(txtLblBillingAddress);
        log.info("Visiblity status of label Confirm Bank Account Number Text Feild:" + txtLblBillingAddress.isDisplayed());
        return getText(txtLblBillingAddress);
    }

    @FindBy(id = "st2_firstname")
    private WebElement txtBoxFirstName;

    public boolean isTxtBoxFirstNameVisible() {
        scrollToElement(txtBoxFirstName);
        log.info("Waiting For Visiblity status of First Name Text Feild");
        waitForElementToBeVisible(txtBoxFirstName);
        log.info("Visiblity status of First Name Text Feild:" + txtBoxFirstName.isDisplayed());
        return txtBoxFirstName.isDisplayed();
    }

    public void populateTxtBoxFirstName(String Text) {
        log.info("Waiting For Visiblity status of First Name Text Feild");
        waitForElementToBeVisible(txtBoxFirstName);
        log.info("Visiblity status of First Name Text Feild:" + txtBoxFirstName.isDisplayed());
        log.info("Populating First Name feild with" + Text);
        clear(txtBoxFirstName);
        sendKeys(txtBoxFirstName, Text);
    }

    public WebElement elementFirstName() {
        log.info("Waiting For Visiblity status of First Name Text Feild");
        waitForElementToBeVisible(txtBoxFirstName);
        log.info("Visiblity status of First Name Text Feild:" + txtBoxFirstName.isDisplayed());
        return txtBoxFirstName;
    }

    @FindBy(css = "label[for='st2_firstname']")
    private WebElement lblTxtBoxFirstName;

    public String getLblTxtBoxFirstName() {
        log.info("Waiting For Visiblity status of First Name Text Feild Label");
        waitForElementToBeVisible(lblTxtBoxFirstName);
        log.info("Visiblity status of label First Name Text Feild:" + lblTxtBoxFirstName.isDisplayed());
        return getText(lblTxtBoxFirstName);
    }

    @FindBy(id = "st2_lastname")
    private WebElement txtBoxLastName;

    public boolean isTxtBoxLastNameVisible() {
        scrollToElement(txtBoxLastName);
        log.info("Waiting For Visiblity status of Last Name Text Feild");
        waitForElementToBeVisible(txtBoxLastName);
        log.info("Visiblity status of Last Name Text Feild:" + txtBoxLastName.isDisplayed());
        return txtBoxLastName.isDisplayed();
    }

    public void populateTxtBoxLastName(String Text) {
        log.info("Waiting For Visiblity status of Last Name Text Feild");
        waitForElementToBeVisible(txtBoxLastName);
        log.info("Visiblity status of Last Name Text Feild:" + txtBoxLastName.isDisplayed());
        log.info("Populating Last Name feild with" + Text);
        clear(txtBoxLastName);
        sendKeys(txtBoxLastName, Text);
    }

    public WebElement elementLastName() {
        log.info("Waiting For Visiblity status of Last Name Text Feild");
        waitForElementToBeVisible(txtBoxLastName);
        log.info("Visiblity status of Last Name Text Feild:" + txtBoxLastName.isDisplayed());
        return txtBoxLastName;
    }

    @FindBy(css = "label[for='st2_lastname']")
    private WebElement lblTxtBoxLastName;

    public String getLblTxtBoxLastName() {
        log.info("Waiting For Visiblity status of Last Name Text Feild Label");
        waitForElementToBeVisible(lblTxtBoxLastName);
        log.info("Visiblity status of label Last Name Text Feild:" + lblTxtBoxLastName.isDisplayed());
        return getText(lblTxtBoxLastName);
    }

    @FindBy(css = "label[for='st2_lastname']+span")
    private WebElement errMsgLastName;

    public String getErrMsgLastName() {
        log.info("Waiting For Visiblity status of Last Name Text Feild Error Message");
        waitForElementToBeVisible(errMsgLastName);
        log.info("Visiblity status of Last Name Error Message:" + errMsgLastName.isDisplayed());
        return getText(errMsgLastName);
    }

    @FindBy(id = "st2_address")
    private WebElement txtBoxAddress;

    public boolean isTxtBoxAddressVisible() {
        scrollToElement(txtBoxAddress);
        log.info("Waiting For Visiblity status of Address Text Feild");
        waitForElementToBeVisible(txtBoxAddress);
        log.info("Visiblity status of Address Text Feild:" + txtBoxAddress.isDisplayed());
        return txtBoxAddress.isDisplayed();
    }

    public void populateTxtBoxAddress(String Text) {
        log.info("Waiting For Visiblity status of Address Text Feild");
        waitForElementToBeVisible(txtBoxAddress);
        log.info("Visiblity status of Address Text Feild:" + txtBoxAddress.isDisplayed());
        log.info("Populating Address feild with" + Text);
        clear(txtBoxAddress);
        sendKeys(txtBoxAddress, Text);
    }

    public WebElement elementAddress() {
        log.info("Waiting For Visiblity status of Address Text Feild");
        waitForElementToBeVisible(txtBoxAddress);
        log.info("Visiblity status of Address Text Feild:" + txtBoxAddress.isDisplayed());
        return txtBoxAddress;
    }

    @FindBy(css = "label[for='st2_address']")
    private WebElement lblTxtBoxAddress;

    public String getLblTxtBoxAddress() {
        log.info("Waiting For Visiblity status of Address Text Feild Label");
        waitForElementToBeVisible(lblTxtBoxAddress);
        log.info("Visiblity status of label Address Text Feild:" + lblTxtBoxAddress.isDisplayed());
        return getText(lblTxtBoxAddress);
    }

    @FindBy(css = "label[for='st2_address']+span")
    private WebElement errMsgAddress;

    public String getErrMsgAddress() {
        log.info("Waiting For Visiblity status of Address Text Feild Error Message");
        waitForElementToBeVisible(errMsgAddress);
        log.info("Visiblity status of Address Error Message:" + errMsgAddress.isDisplayed());
        return getText(errMsgAddress);
    }

    @FindBy(id = "st2_city")
    private WebElement txtBoxCity;

    public boolean isTxtBoxCityVisible() {
        scrollToElement(txtBoxCity);
        log.info("Waiting For Visiblity status of City Text Feild");
        waitForElementToBeVisible(txtBoxCity);
        log.info("Visiblity status of City Text Feild:" + txtBoxCity.isDisplayed());
        return txtBoxCity.isDisplayed();
    }

    public void populateTxtCity(String Text) {
        log.info("Waiting For Visiblity status of City Text Feild");
        waitForElementToBeVisible(txtBoxCity);
        log.info("Visiblity status of City Text Feild:" + txtBoxCity.isDisplayed());
        log.info("Populating City feild with" + Text);
        clear(txtBoxCity);
        sendKeys(txtBoxCity, Text);
        pause(3000);
    }

    public WebElement elementCity() {
        log.info("Waiting For Visiblity status of City Text Feild");
        waitForElementToBeVisible(txtBoxCity);
        log.info("Visiblity status of City Text Feild:" + txtBoxCity.isDisplayed());
        return txtBoxCity;
    }

    @FindBy(css = "label[for='st2_city']")
    private WebElement lblTxtBoxCity;

    public String getLblTxtBoxCity() {
        log.info("Waiting For Visiblity status of City Text Feild Label");
        waitForElementToBeVisible(lblTxtBoxCity);
        log.info("Visiblity status of label City Text Feild:" + lblTxtBoxCity.isDisplayed());
        return getText(lblTxtBoxCity);
    }

    @FindBy(css = "label[for='st2_city']+span")
    private WebElement errMsgCity;

    public String getErrMsgCity() {
        log.info("Waiting For Visiblity status of City Text Feild Error Message");
        waitForElementToBeVisible(errMsgCity);
        log.info("Visiblity status of City Error Message:" + errMsgCity.isDisplayed());
        return getText(errMsgCity);
    }

    @FindBy(id = "st2_state")
    private WebElement txtBoxState;

    public boolean isTxtBoxStateVisible() {
        scrollToElement(txtBoxState);
        log.info("Waiting For Visiblity status of State Text Feild");
        waitForElementToBeVisible(txtBoxState);
        log.info("Visiblity status of State Text Feild:" + txtBoxState.isDisplayed());
        return txtBoxState.isDisplayed();
    }

    public void populateTxtState(String Text) {
        log.info("Waiting For Visiblity status of State Text Feild");
        waitForElementToBeVisible(txtBoxState);
        log.info("Visiblity status of State Text Feild:" + txtBoxState.isDisplayed());
        log.info("Populating State feild with" + Text);
        clear(txtBoxState);
        sendKeys(txtBoxState, Text);
        pause(3000);
    }

    public WebElement elementState() {
        log.info("Waiting For Visiblity status of State Text Feild");
        waitForElementToBeVisible(txtBoxState);
        log.info("Visiblity status of State Text Feild:" + txtBoxState.isDisplayed());
        return txtBoxState;
    }

    @FindBy(css = "label[for='st2_state']")
    private WebElement lblTxtBoxState;

    public String getLblTxtBoxState() {
        log.info("Waiting For Visiblity status of State Text Feild Label");
        waitForElementToBeVisible(lblTxtBoxState);
        log.info("Visiblity status of label State Text Feild:" + lblTxtBoxState.isDisplayed());
        return getText(lblTxtBoxState);
    }

    @FindBy(css = "label[for='st2_state']+span")
    private WebElement errMsgState;

    public String getErrMsgState() {
        log.info("Waiting For Visiblity status of State Text Feild Error Message");
        waitForElementToBeVisible(errMsgState);
        log.info("Visiblity status of State Error Message:" + errMsgState.isDisplayed());
        return getText(errMsgState);
    }

    @FindBy(id = "st2_zipcode")
    private WebElement txtBoxZip;

    public boolean isTxtBoxZipVisible() {
        scrollToElement(txtBoxZip);
        log.info("Waiting For Visiblity status of Zip Code Text Feild");
        waitForElementToBeVisible(txtBoxZip);
        log.info("Visiblity status of Zip Code Text Feild:" + txtBoxZip.isDisplayed());
        return txtBoxZip.isDisplayed();
    }

    public void populateTxtZip(String Text) {
        log.info("Waiting For Visiblity status of Zip Code Text Feild");
        waitForElementToBeVisible(txtBoxZip);
        log.info("Visiblity status of Zip Code Text Feild:" + txtBoxZip.isDisplayed());
        log.info("Populating Zip Code feild with" + Text);
        clear(txtBoxZip);
        sendKeys(txtBoxZip, Text);
    }

    public WebElement elementZip() {
        log.info("Waiting For Visiblity status of Zip Code Text Feild");
        waitForElementToBeVisible(txtBoxZip);
        log.info("Visiblity status of Zip Code Text Feild:" + txtBoxZip.isDisplayed());
        return txtBoxZip;
    }

    @FindBy(css = "label[for='st2_zipcode']")
    private WebElement lblTxtBoxZip;

    public String getLblTxtBoxZip() {
        log.info("Waiting For Visiblity status of Zip Code Text Feild Label");
        waitForElementToBeVisible(lblTxtBoxZip);
        log.info("Visiblity status of label Zip Code Text Feild:" + lblTxtBoxZip.isDisplayed());
        return getText(lblTxtBoxZip);
    }

    @FindBy(css = "label[for='st2_zipcode']+span")
    private WebElement errMsgZip;

    public String getErrMsgZip() {
        log.info("Waiting For Visiblity status of Zip Code Text Feild Error Message");
        waitForElementToBeVisible(errMsgZip);
        log.info("Visiblity status of Zip Code Error Message:" + errMsgZip.isDisplayed());
        return getText(errMsgZip);
    }

    @FindBy(xpath = "//*[@id='submitNext']")
    private WebElement btnAdd;

    public boolean isBtnAddVisible() {
        scrollToElement(btnAdd);
        log.info("Waiting For Visiblity status of Add button");
        waitForElementToBeVisible(btnAdd);
        log.info("Visiblity status of Add button:" + btnAdd.isDisplayed());
        return btnAdd.isDisplayed();
    }

    public String getTxtBtnAdd() {
        log.info("Waiting For Visiblity status of Add button");
        waitForElementToBeVisible(btnAdd);
        log.info("Visiblity status of Add button:" + btnAdd.isDisplayed());
        return getAttribute(btnAdd, "value");
    }

    public void clickbtnAdd() {
        scrollToElement(btnAdd);
        log.info("Waiting For Add button to be clickable");
        waitForElementToBeClickable(btnAdd);
        pause(5000);
        click(btnAdd);
        log.info("Add button is clicked");
    }

    @FindBy(css = "#submitNext+#btnclosepopup")
    private WebElement btnCancel;

    public boolean isBtnCancelVisible() {
        scrollToElement(btnCancel);
        log.info("Waiting For Visiblity status of Cancel button");
        waitForElementToBeVisible(btnCancel);
        log.info("Visiblity status of Cancel button:" + btnCancel.isDisplayed());
        return btnCancel.isDisplayed();
    }

    public String getTxtBtnCancel() {
        log.info("Waiting For Visiblity status of Add button");
        waitForElementToBeVisible(btnCancel);
        log.info("Visiblity status of Add button:" + btnAdd.isDisplayed());
        return getAttribute(btnCancel, "value");
    }

    public void clickbtnCancel() {
        log.info("Waiting For Cancel button to be clickable");
        waitForElementToBeClickable(btnCancel);
        click(btnCancel);
        log.info("Add button is clicked");
    }
    ///////////// CARD PAYMENT OBEJECTS AND METHODS /////////////

    @FindBy(css = "input[radio_btn_name='Credit']")
    private WebElement rdoBtnCard;

    public boolean isrdoBtnCardVisible() {
        log.info("Waiting For Visiblity status of Card Radio Button");
        waitForElementToBeVisible(rdoBtnCard);
        log.info("Visiblity status of Card Radio Button is:" + rdoBtnCard.isDisplayed());
        return rdoBtnCard.isDisplayed();
    }

    public void clickrdoBtnCard() {
        log.info("Clicking Add Card Radio Button ");
        clickWithJSExecutor(rdoBtnCard);
    }

    @FindBy(id = "nmcrd")
    private WebElement txtBoxCardHolderName;

    public boolean isTxtBoxCardHolderNameVisible() {
        log.info("Waiting For Visiblity status of Card Holder Name Text Field");
        waitForElementToBeVisible(txtBoxCardHolderName);
        log.info("Visiblity status of Card Holder Name Text Field:" + txtBoxCardHolderName.isDisplayed());
        return txtBoxCardHolderName.isDisplayed();
    }

    public void populateTxtBoxCardHolderName(String Text) {
        //scrollPageToElement(txtBoxCardHolderName);
        log.info("Waiting For Visiblity status of Account Holder Name Text Feild");
        waitForElementToBeVisible(txtBoxCardHolderName);
        log.info("Visiblity status of Account Holder Name Text Feild:" + txtBoxCardHolderName.isDisplayed());
        log.info("Populating Account Holder Name feild with" + Text);
        clear(txtBoxCardHolderName);
        sendKeys(txtBoxCardHolderName, Text);
    }

    public WebElement elementCardHolderName() {
        log.info("Waiting For Visiblity status of Card Holder Name Text Feild");
        waitForElementToBeVisible(txtBoxCardHolderName);
        log.info("Visiblity status of Account Holder Name Text Feild:" + txtBoxCardHolderName.isDisplayed());
        return txtBoxCardHolderName;
    }

    @FindBy(css = "label[for='nmcrd']")
    private WebElement lblTxtBoxCardHolderName;

    public String getLblTxtBoxCardHolderName() {
        log.info("Waiting For Visiblity status of Card Holder Name Text Feild Label");
        waitForElementToBeVisible(lblTxtBoxCardHolderName);
        log.info("Visiblity status of label Card Holder Name Text Feild:" + lblTxtBoxCardHolderName.isDisplayed());
        return getText(lblTxtBoxCardHolderName);
    }

    @FindBy(css = "label[for='nmcrd']+span")
    private WebElement errMsgCardHolderName;

    public String getErrMsgCardHolderName() {
        log.info("Waiting For Visiblity status of Card Holder Name Text Feild Error Message");
        waitForElementToBeVisible(errMsgCardHolderName);
        log.info("Visiblity status of label Card Holder Name Error Message:" + errMsgAccountHolderName.isDisplayed());
        return getText(errMsgCardHolderName);
    }

    @FindBy(id = "crdno")
    private WebElement txtBoxCardNumber;

    public boolean isTxtBoxCardNumberVisible() {
        log.info("Waiting For Visiblity status of Card Holder Name Text Field");
        waitForElementToBeVisible(txtBoxCardNumber);
        log.info("Visiblity status of Card Holder Name Text Field:" + txtBoxCardNumber.isDisplayed());
        return txtBoxCardNumber.isDisplayed();
    }

    public void populateTxtBoxCardNumber(String Text) {
        log.info("Waiting For Visiblity status of Card Number Text Feild");
        waitForElementToBeVisible(txtBoxCardNumber);
        log.info("Visiblity status of Card Number Text Feild:" + txtBoxCardNumber.isDisplayed());
        log.info("Populating Card Number feild with" + Text);
        clear(txtBoxCardNumber);
        sendKeys(txtBoxCardNumber, Text);
    }

    public void clearCardNumberInTheField() {
        log.info("clear the payment amount.");
        clear(txtBoxCardNumber);
    }

    public WebElement elementCardNumber() {
        log.info("Waiting For Visiblity status of Card Number Text Feild");
        waitForElementToBeVisible(txtBoxCardNumber);
        log.info("Visiblity status of Card Number Text Feild:" + txtBoxCardNumber.isDisplayed());
        return txtBoxCardNumber;
    }

    @FindBy(css = "label[for='crdno']")
    private WebElement lblTxtBoxCardNumber;

    public String getLblTxtBoxCardNumber() {
        log.info("Waiting For Visiblity status of Card Number Text Feild Label");
        waitForElementToBeVisible(lblTxtBoxCardNumber);
        log.info("Visiblity status of label Card Number Text Feild:" + lblTxtBoxCardNumber.isDisplayed());
        return getText(lblTxtBoxCardNumber);
    }

    @FindBy(css = "label[for='crdno']+span")
    private WebElement errMsgCardNumber;

    public String getErrMsgCardNumber() {
        log.info("Waiting For Visiblity status of Card Number Text Feild Error Message");
        waitForElementToBeVisible(errMsgCardNumber);
        log.info("Visiblity status of label Card Number Error Message:" + errMsgCardNumber.isDisplayed());
        return getText(errMsgCardNumber);
    }

    @FindBy(id = "expMOnth")
    private WebElement ddExpMonth;

    public boolean isDDExpMonth() {
        log.info("Waiting For Visiblity status of Exp Month DD Field");
        waitForElementToBeVisible(ddExpMonth);
        log.info("Visiblity status of Exp Month DD Field:" + ddExpMonth.isDisplayed());
        return ddExpMonth.isDisplayed();
    }

    public WebElement elementDDExpMonth() {
        log.info("Waiting For Visiblity status of Exp Month DD Feild");
        waitForElementToBeClickable(ddExpMonth);
        log.info("Visiblity status of Exp Month DD Feild:" + ddExpMonth.isDisplayed());
        return ddExpMonth;
    }

    @FindBy(css = "label[for='expMOnth']")
    private WebElement lblExpMonth;

    public String getLblExpMonth() {
        log.info("Waiting For Visiblity status of Exp Month DD Feild Label");
        waitForElementToBeVisible(lblExpMonth);
        log.info("Visiblity status of label Exp Month DD Feild:" + lblExpMonth.isDisplayed());
        return getText(lblExpMonth);
    }

    @FindBy(css = "label[for='expMOnth']+span")
    private WebElement errMsgExpMonth;

    public String getErrMsgExpMonth() {
        log.info("Waiting For Visiblity status of Exp Month DD Feild Error Message");
        waitForElementToBeVisible(errMsgExpMonth);
        log.info("Visiblity status of Exp Month DD Error Message:" + errMsgExpMonth.isDisplayed());
        return getText(errMsgExpMonth);
    }

    @FindBy(id = "expYear")
    private WebElement ddExpYear;

    public boolean isDDExpYear() {
        log.info("Waiting For Visiblity status of Exp Year DD Field");
        waitForElementToBeVisible(ddExpYear);
        log.info("Visiblity status of Exp Year DD Field:" + ddExpYear.isDisplayed());
        return ddExpYear.isDisplayed();
    }

    public WebElement elementDDExpYear() {
        log.info("Waiting For Visiblity status of Exp Year DD Feild");
        waitForElementToBeVisible(ddExpYear);
        log.info("Visiblity status of Exp Year DD Feild:" + ddExpYear.isDisplayed());
        return ddExpYear;
    }

    @FindBy(css = "label[for='expYear']")
    private WebElement lblExpYear;

    public String getLblExpYear() {
        log.info("Waiting For Visiblity status of Exp Year DD Feild Label");
        waitForElementToBeVisible(lblExpYear);
        log.info("Visiblity status of label Exp Year DD Feild:" + lblExpYear.isDisplayed());
        return getText(lblExpYear);
    }

    @FindBy(css = "label[for='expYear']+span")
    private WebElement errMsgExpYear;

    public String getErrMsgExpYear() {
        log.info("Waiting For Visiblity status of Exp Year DD Feild Error Message");
        waitForElementToBeVisible(errMsgExpYear);
        log.info("Visiblity status of Exp Year DD Error Message:" + errMsgExpYear.isDisplayed());
        return getText(errMsgExpYear);
    }

    @FindBy(id = "Security")
    private WebElement txtBoxSecurityPin;

    public boolean isTxtBoxSecurityPin() {
        log.info("Waiting For Visiblity status of Security pin Text Field");
        waitForElementToBeVisible(txtBoxSecurityPin);
        log.info("Visiblity status of Security pin Text Field:" + txtBoxSecurityPin.isDisplayed());
        return txtBoxSecurityPin.isDisplayed();
    }

    public void populateTxtBoxSecurityPin(String Text) {
        log.info("Waiting For Visiblity status of Security pin Text Feild");
        waitForElementToBeVisible(txtBoxSecurityPin);
        log.info("Visiblity status of Security pin Text Feild:" + txtBoxSecurityPin.isDisplayed());
        log.info("Populating Security pin feild with" + Text);
        clear(txtBoxSecurityPin);
        sendKeys(txtBoxSecurityPin, Text);
    }

    public WebElement elementSecurityPin() {
        log.info("Waiting For Visiblity status of Security pin Text Feild");
        waitForElementToBeVisible(txtBoxSecurityPin);
        log.info("Visiblity status of Security pin Text Feild:" + txtBoxSecurityPin.isDisplayed());
        return txtBoxSecurityPin;
    }

    @FindBy(css = "label[for='Security']")
    private WebElement lblTxtBoxSecurityPin;

    public String getLblTxtBoxSecurityPin() {
        log.info("Waiting For Visiblity status of Security pin Text Feild Label");
        waitForElementToBeVisible(lblTxtBoxSecurityPin);
        log.info("Visiblity status of label Security pin Text Feild:" + lblTxtBoxSecurityPin.isDisplayed());
        return getText(lblTxtBoxSecurityPin);
    }

    @FindBy(css = "label[for='Security']+span")
    private WebElement errMsgSecurityPin;

    public String getErrMsgSecurityPin() {
        log.info("Waiting For Visiblity status of Security pin Text Feild Error Message");
        waitForElementToBeVisible(errMsgSecurityPin);
        log.info("Visiblity status of label Security pin Error Message:" + errMsgSecurityPin.isDisplayed());
        return getText(errMsgSecurityPin);
    }

    @FindBy(id = "ImgVisa")
    private WebElement imgVisaCard;

    public boolean isImgVisaVisible() {
        log.info("Waiting For Visiblity status of Visa Card Image");
        waitForElementToBeVisible(imgVisaCard);
        log.info("Visiblity status of Visa Card Image:" + imgVisaCard.isDisplayed());
        return imgVisaCard.isDisplayed();
    }

    @FindBy(id = "ImgMaster")
    private WebElement imgMasterCard;

    public boolean isImgMasterCard() {
        log.info("Waiting For Visiblity status of Master Card Image");
        waitForElementToBeVisible(imgMasterCard);
        log.info("Visiblity status of Master Card Image:" + imgMasterCard.isDisplayed());
        return imgMasterCard.isDisplayed();
    }

    @FindBy(id = "ImgDiscov")
    private WebElement imgDiscoverCard;

    public boolean isImgDiscoverCard() {
        log.info("Waiting For Visiblity status of Discover Card Image");
        waitForElementToBeVisible(imgDiscoverCard);
        log.info("Visiblity status of Discover Card Image:" + imgDiscoverCard.isDisplayed());
        return imgDiscoverCard.isDisplayed();
    }

    @FindBy(id = "Imgamex")
    private WebElement imgAmexCard;

    public boolean isImgAmexCard() {
        log.info("Waiting For Visiblity status of Amex Card Image");
        waitForElementToBeVisible(imgAmexCard);
        log.info("Visiblity status of Amex Card Image:" + imgAmexCard.isDisplayed());
        return imgAmexCard.isDisplayed();
    }

    @FindBy(id = "ImgJcb")
    private WebElement imgJcbCard;

    public boolean isImgJcbCard() {
        log.info("Waiting For Visiblity status of Jcb Card Image");
        waitForElementToBeVisible(imgJcbCard);
        log.info("Visiblity status of Jcb Card Image:" + imgJcbCard.isDisplayed());
        return imgJcbCard.isDisplayed();
    }

    @FindBy(id = "ImgCup")
    private WebElement imgUnionPayCard;

    public boolean isImgUnionPayCard() {
        log.info("Waiting For Visiblity status of Union Pay Card Image");
        waitForElementToBeVisible(imgUnionPayCard);
        log.info("Visiblity status of Union Pay Card Image:" + imgUnionPayCard.isDisplayed());
        return imgUnionPayCard.isDisplayed();
    }

    @FindBy(id = "CardName_1")
    private WebElement lblCardName;

    public String getLblCardName() {
        return getText(lblCardName);
    }

    ////////// PAY-PAL OBEJECTS AND METHODS /////////////

    @FindBy(css = "input[radio_btn_name='Paypal']")
    private WebElement rdoBtnPaypal;

    public boolean isrdoBtnPaypalVisible() {
        log.info("Waiting For Visiblity status of Paypal Radio Button");
        waitForElementToBeVisible(rdoBtnPaypal);
        log.info("Visiblity status of Paypal Radio Button is:" + rdoBtnPaypal.isDisplayed());
        return rdoBtnPaypal.isDisplayed();
    }

    public void clickrdoBtnPaypal() {
        log.info("Clicking Add Paypal Radio Button ");
        clickWithJSExecutor(rdoBtnPaypal);
    }

    @FindBy(css = "#buttons-container")
    private WebElement btnPayPal;

    public boolean isBtnPaypalVisible() {
        log.info("Waiting For Visiblity status of Paypal Radio Button");
        waitForElementToBeVisible(btnPayPal);
        log.info("Visiblity status of Paypal Radio Button is:" + btnPayPal.isDisplayed());
        return btnPayPal.isDisplayed();
    }

    @FindBy(css = "iframe[id*='jsx-iframe']")
    private WebElement iframePayPal;

    public void clickBtnPaypal() {
        driver.switchTo().frame(iframePayPal);
        log.info("Clicking Add Paypal Radio Button ");
        click(btnPayPal);
    }

    @FindBy(id = "email")
    private WebElement txtBoxEmailPayPalWnd;

    public boolean isTxtBoxEmailPayPalWnd() {
        log.info("Waiting For Visiblity status of Email text box feild");
        waitForElementToBeVisible(txtBoxEmailPayPalWnd);
        log.info("Visiblity status of Email text box feild is:" + txtBoxEmailPayPalWnd.isDisplayed());
        return txtBoxEmailPayPalWnd.isDisplayed();
    }

    public void populateTxtBoxEmailPayPalWnd(String text) {
        log.info("Waiting For Visiblity status of Email text box feild");
        waitForElementToBeVisible(txtBoxEmailPayPalWnd);
        log.info("Visiblity status of Email text box feild is:" + txtBoxEmailPayPalWnd.isDisplayed());
        clear(txtBoxEmailPayPalWnd);
        sendKeys(txtBoxEmailPayPalWnd, text);
    }

    @FindBy(id = "headerText")
    private WebElement lblPayWithPayPal;

    public boolean isLblPayWithPayPalVisible() {
        log.info("Waiting For Visiblity status of pay with Paypal Label");
        waitForElementToBeVisible(lblPayWithPayPal);
        log.info("Visiblity status of pay with Paypal label is:" + lblPayWithPayPal.isDisplayed());
        return lblPayWithPayPal.isDisplayed();
    }

    @FindBy(id = "emailSubTagLine")
    private WebElement lblEnterEmail;

    public String getTxtlblEnterEmail() {
        log.info("Waiting For Visiblity status of Paypal Sub Tag");
        waitForElementToBeVisible(lblEnterEmail);
        log.info("Visiblity status of pay with Paypal Sub Tag is:" + lblEnterEmail.isDisplayed());
        return lblEnterEmail.getText();
    }

    @FindBy(css = "#header p[aria-label='PayPal Logo']")
    private WebElement payPalLogo;

    public boolean isPayPalLogoVisible() {
        log.info("Waiting For Visiblity status of Paypal Logo");
        waitForElementToBeVisible(payPalLogo);
        log.info("Visiblity status of Paypal Logo is:" + payPalLogo.isDisplayed());
        return payPalLogo.isDisplayed();
    }

    @FindBy(id = "forgotEmail")
    private WebElement linkForgotEmail;

    public boolean isLinkForgotEmailVisible() {
        log.info("Waiting For Visiblity status of Link Forgot Email Id");
        waitForElementToBeVisible(linkForgotEmail);
        log.info("Visiblity status of Link Forgot Email Id is:" + linkForgotEmail.isDisplayed());
        return linkForgotEmail.isDisplayed();
    }

    @FindBy(id = "btnNext")
    private WebElement btnPayPalNext;

    public boolean isBtnPayPalNext() {
        log.info("Waiting For Visiblity status of Link Forgot Email Id");
        waitForElementToBeVisible(linkForgotEmail);
        log.info("Visiblity status of Link Forgot Email Id is:" + linkForgotEmail.isDisplayed());
        return linkForgotEmail.isDisplayed();
    }

    public void clickBtnPayPalNext() {
        log.info("Waiting For Visiblity status of Link Next");
        waitForElementToBeVisible(btnPayPalNext);
        log.info("Visiblity status of Link Next is:" + btnPayPalNext.isDisplayed());
        clickWithJSExecutor(btnPayPalNext);
    }

    @FindBy(id = "startOnboardingFlow")
    private WebElement btnCreateAccount;

    public boolean isBtnCreateAccountVisible() {
        log.info("Waiting For Visiblity status of Create an account button");
        waitForElementToBeVisible(btnCreateAccount);
        log.info("Visiblity status of Create an account:" + btnCreateAccount.isDisplayed());
        return btnCreateAccount.isDisplayed();
    }

    public void clickBtnCreateAccount() {
        log.info("Waiting For Visiblity status of Link Cancel");
        waitForElementToBeVisible(btnCreateAccount);
        log.info("Visiblity status of Link Cancle is:" + btnCreateAccount.isDisplayed());
        click(btnCreateAccount);
    }

    @FindBy(id = "cancelLink")
    private WebElement linkCancel;

    public boolean islinkCancelVisible() {
        log.info("Waiting For Visiblity status of Link Cancel");
        waitForElementToBeVisible(linkCancel);
        log.info("Visiblity status of Link Cancle is:" + linkCancel.isDisplayed());
        return linkCancel.isDisplayed();
    }

    public void clickLinkCancel() {
        log.info("Waiting For Visiblity status of Link Cancel");
        waitForElementToBeVisible(linkCancel);
        log.info("Visiblity status of Link Cancle is:" + linkCancel.isDisplayed());
        click(linkCancel);
    }

    @FindBy(id = "password")
    private WebElement txtBoxPayPalPass;

    public boolean isTxtBoxPayPalPass() {
        log.info("Waiting For Visiblity status of Password text box feild");
        waitForElementToBeVisible(txtBoxPayPalPass);
        log.info("Visiblity status of Password text box feild is:" + txtBoxPayPalPass.isDisplayed());
        return txtBoxPayPalPass.isDisplayed();
    }

    public void populatetxtBoxPayPalPass(String text) {
        log.info("Waiting For Visiblity status of password text box feild");
        waitForElementToBeVisible(txtBoxPayPalPass);
        log.info("Visiblity status of Password text box feild is:" + txtBoxPayPalPass.isDisplayed());
        clear(txtBoxPayPalPass);
        sendKeys(txtBoxPayPalPass, text);
    }

    @FindBy(id = "btnLogin")
    private WebElement btnPayPalLogin;

    public boolean isBtnPayPalLoginVisible() {
        log.info("Waiting For Visiblity status of button Login");
        waitForElementToBeVisible(btnPayPalLogin);
        log.info("Visiblity status of button Login is:" + btnPayPalLogin.isDisplayed());
        return btnPayPalLogin.isDisplayed();
    }

    public void clickBtnPayPalLogin() {
        log.info("Waiting For Visiblity status of button Login");
        waitForElementToBeVisible(btnPayPalLogin);
        log.info("Visiblity status of button Login is:" + btnPayPalLogin.isDisplayed());
        click(btnPayPalLogin);
    }

    @FindBy(id = "show-more-fi-toggle")
    private WebElement btnMore;

    public void clickBtnMore() {
        log.info("Waiting For Visiblity status of button More");
        waitForElementToBeVisible(btnMore);
        log.info("Visiblity status of button More is:" + btnMore.isDisplayed());
        click(btnMore);
    }

    @FindBy(css = "div[class='hagrid-1vydhhl'] button")
    private List<WebElement> listCards;

    public List<WebElement> getListCards() {
        return listCards;
    }

    @FindBy(id = "consentButton")
    private WebElement btnSavePayPal;

    public boolean isBtnSavePayPalVisible() {
        log.info("Waiting For Visiblity status of button Save");
        waitForElementToBeVisible(btnSavePayPal);
        log.info("Visiblity status of button Save is:" + btnSavePayPal.isDisplayed());
        return btnSavePayPal.isDisplayed();
    }

    public void clickBtnSavePayPal() {
        log.info("Waiting For Visiblity status of Save Login");
        waitForElementToBeVisible(btnSavePayPal);
        log.info("Visiblity status of button Save is:" + btnSavePayPal.isDisplayed());
        click(btnSavePayPal);
    }

    @FindBy(id = "paypal_email")
    private WebElement txtBoxPayPalToken;

    public String getTxtBoxPayPalToken() {
        return getAttribute(txtBoxPayPalToken, "value");
    }

    public boolean isPayPalPaymentprofileVisible(String email) {
        log.info("Waiting For Visiblity status of PayPal Email");
        WebElement payPalProfile = getWebElement(By.xpath("//span[text()='" + email + "']"));
        waitForElementToBeVisible(payPalProfile);
        log.info("Visiblity status of PayPal Email is:" + payPalProfile.isDisplayed());
        return payPalProfile.isDisplayed();
    }

    public boolean isPayPalLblPaymentprofileVisible() {
        log.info("Waiting For Visiblity status of PayPal Label");
        WebElement lblPayPalProfile = getWebElement(By.xpath("//label[text()='Paypal']"));
        waitForElementToBeVisible(lblPayPalProfile);
        log.info("Visiblity status of PayPal Label is:" + lblPayPalProfile.isDisplayed());
        return lblPayPalProfile.isDisplayed();
    }


    //////// VENMO OBEJECTS AND METHODS /////////

    @FindBy(css = "input[radio_btn_name='Venmo']")
    private WebElement rdoBtnVenmo;

    public boolean isrdoBtnVenmoVisible() {
        log.info("Waiting For Visiblity status of Venmo Radio Button");
        waitForElementToBeVisible(rdoBtnVenmo);
        log.info("Visiblity status of Venmo Radio Button is:" + rdoBtnVenmo.isDisplayed());
        return rdoBtnVenmo.isDisplayed();
    }

    public void clickrdoBtnVenmo() {
        log.info("Clicking Add Venmo Radio Button ");
        clickWithJSExecutor(rdoBtnVenmo);
    }

    @FindBy(id = "venmo-button")
    private WebElement btnVenmo;

    public boolean isBtnVenmoVisible() {
        log.info("Waiting For Visiblity status of Venmo Button");
        waitForElementToBeVisible(btnVenmo);
        log.info("Visiblity status of Venmo Button is:" + btnVenmo.isDisplayed());
        return btnVenmo.isDisplayed();
    }

    public void clickBtnVenmo() {
        log.info("Waiting For Visiblity status of Venmo Button");
        waitForElementToBeClickable(btnVenmo);
        log.info("Visiblity status of Venmo Button is:" + btnVenmo.isDisplayed());
        click(btnVenmo);
    }

    @FindBy(xpath = "//*[name()='path' and contains(@d,'M11.7401 1')]")
    private WebElement logoVenmo;

    public boolean islogoVenmoVisible() {
        log.info("Waiting For Visiblity status of Venmo logo");
        waitForElementToBeVisible(logoVenmo);
        log.info("Visiblity status of Venmo logo is:" + logoVenmo.isDisplayed());
        return logoVenmo.isDisplayed();
    }

    @FindBy(id = "venmo-qr-code-view__code-container")
    private WebElement venmoQRCode;

    public boolean isVenmoQRCodeVisible() {
        log.info("Waiting For Visiblity status of Venmo QR code");
        waitForElementToBeVisible(venmoQRCode);
        log.info("Visiblity status of Venmo QR code is:" + venmoQRCode.isDisplayed());
        return venmoQRCode.isDisplayed();
    }

    @FindBy(id = "venmo-qr-code-view__footer__text")
    private WebElement txtScanQRVenmo;

    public String getTxtScanQRVenmo() {
        log.info("Waiting For Visiblity status of Text Scan Venmo QR code");
        waitForElementToBeVisible(txtScanQRVenmo);
        log.info("Visiblity status of Text Scan Venmo QR code is:" + txtScanQRVenmo.isDisplayed());
        return getText(txtScanQRVenmo);
    }

    ///////////// G-Pay OBEJECTS AND METHODS /////////////

    @FindBy(css = "input[radio_btn_name='google_pay']")
    private WebElement rdoBtnGpay;

    public boolean isrdoBtnGpayVisible() {
        log.info("Waiting For Visiblity status of G-pay Radio Button");
        waitForElementToBeVisible(rdoBtnGpay);
        log.info("Visiblity status of G-pay Radio Button is:" + rdoBtnGpay.isDisplayed());
        return rdoBtnGpay.isDisplayed();
    }

    public void clickrdoBtnGpay() {
        log.info("Clicking Add G-pay Radio Button ");
        clickWithJSExecutor(rdoBtnGpay);
    }

    @FindBy(id = "google-pay-button")
    private WebElement btnPayWithGpay;

    public boolean isBtnPayWithGpayVisible() {
        log.info("Waiting For Visiblity status of pay with G-pay Button");
        waitForElementToBeVisible(btnPayWithGpay);
        log.info("Visiblity status of pay with G-pay Button is:" + btnPayWithGpay.isDisplayed());
        return btnPayWithGpay.isDisplayed();
    }

    ////////// FAQ OBEJECTS AND METHODS ///////////

    @FindBy(css = ".faq_right")
    private WebElement faqField;

    public boolean isFaqFieldVisible() {
        log.info("Waiting For Visiblity status of FAQ field");
        waitForElementToBeVisible(faqField);
        log.info("Visiblity status of AQ field:" + faqField.isDisplayed());
        return faqField.isDisplayed();
    }

    //////// PAYMENT PROFILES //////////

//    @FindBy(css = "div[ng-repeat*='paymentInfo']")
//    private List<WebElement> listPaymentProfile;

    @FindBy(css = ".pmt_cardbox")
    private List<WebElement> listPaymentProfile;

    public List<WebElement> listPaymentProfile() {
        return listPaymentProfile;
    }

    @FindBy(css = "button[id*='navbar']")
    private WebElement btnThreeDots;

    public void clickBtnThreeDots() {
        waitForElementToBeClickable(btnThreeDots);
        log.info("Clicking Three dot Button ");
        clickWithJSExecutor(btnThreeDots);
    }

    //@FindBy(css="li[class='deleterow'] a")
    @FindBy(css = "li[id='removePaymnetType_1']")
    private WebElement btnRemove;

    public void clickBtnRemove() {
        log.info("Clicking Three dot Button ");
        clickWithJSExecutor(btnRemove);
    }

    @FindBy(xpath = "//button[text()='Remove']")
    private WebElement btnConfirmRemove;

    public void clickBtnConfirmRemove() {
        log.info("Clicking Three dot Button ");
        clickWithJSExecutor(btnConfirmRemove);
    }

    public boolean isBankPaymentProfileVisible(String num) {
        By bnkPaymentProfile = By.xpath("//label[contains(text(),'" + num + "')]");
        return driver.findElement(bnkPaymentProfile).isDisplayed();
    }

    public String getBankNameofPaymentProfile(String num) {
        By bnkNamePaymentProfile = By.xpath("//label[contains(text(),'" + num + "')]/preceding-sibling::span");
        return driver.findElement(bnkNamePaymentProfile).getText();
    }

    ////////// AUTO PAY ///////

    @FindBy(css = "a[class=\"submit-button enrolautopayclick\"]")
    private WebElement btnEnrollAutoPay;

    public void clickBtnEnrollAutoPay() {
        log.info("Waiting For Visiblity status of Enroll Auto Pay button");
        waitForElementToBeVisible(btnEnrollAutoPay);
        log.info("Visiblity status of Enroll Auto Pay button:" + btnEnrollAutoPay.isDisplayed());
        log.info("Clicking Enroll Auto Pay button");
        click(btnEnrollAutoPay);
    }

    @FindBy(id = "firxt_nextbtn")
    private WebElement btnNext;

    public void clickBtnNext() {
        log.info("Waiting For Visiblity status of Next button");
        waitForElementToBeVisible(btnNext);
        log.info("Visiblity status of Next button:" + btnNext.isDisplayed());
        log.info("Clicking Next button");
        click(btnNext);
    }

    @FindBy(css = "label[for='chkterm'] span[class='mdl-checkbox__ripple-container mdl-js-ripple-effect mdl-ripple--center']")
    private WebElement checkBoxTandC;

    public void clickCheckBoxTandC() {
        log.info("Waiting For Visiblity status of Check Box button");
        waitForElementToBeVisible(checkBoxTandC);
        log.info("Visiblity status of Check Box button:" + checkBoxTandC.isDisplayed());
        log.info("Clicking Check box button");
        click(checkBoxTandC);
    }

    @FindBy(id = "btnSaveRecurring")
    private WebElement btnSaveEnroll;

    public void clickBtnSaveEnroll() {
        log.info("Waiting For Visiblity status of Check Box button");
        waitForElementToBeVisible(btnSaveEnroll);
        log.info("Visiblity status of Check Box button:" + btnSaveEnroll.isDisplayed());
        log.info("Clicking Check box button");
        click(btnSaveEnroll);
    }

    @FindBy(id = "bank_dropdown")
    private WebElement ddBankAccount;

    public WebElement elementDDBankAccount() {
        log.info("Waiting For Visiblity status of DD Bank Account");
        waitForElementToBeVisible(ddBankAccount);
        log.info("Visiblity status of DD Bank Account:" + ddBankAccount.isDisplayed());
        return ddBankAccount;
    }

    @FindBy(css = ".material-icons.pmt_card")
    private WebElement cardRdoBtn;

    public void clickcardRdoBtn() {
        log.info("Waiting For Visiblity status of Card Radio button");
        waitForElementToBeClickable(cardRdoBtn);
        log.info("Visiblity status of Card Raido button:" + cardRdoBtn.isDisplayed());
        log.info("Clicking Enroll Auto Pay button");
        clickWithJSExecutor(cardRdoBtn);
    }

    @FindBy(id = "card_dropdown")
    private WebElement ddCardAccount;

    public WebElement elementDDCardAccount() {
        log.info("Waiting For Visiblity status of DD Card Account");
        waitForElementToBeVisible(ddCardAccount);
        log.info("Visiblity status of DD Card Account:" + ddCardAccount.isDisplayed());
        return ddCardAccount;
    }

    @FindBy(css = ".enrolledbtn")
    private WebElement lblAutoPayEnrollmentStatus;

    public String getLblAutoPayEnrollmentStatus() {
        waitForElementToBeInVisible(lblAutoPayEnrollmentStatus);
        return getText(lblAutoPayEnrollmentStatus);
    }

    public boolean isLblAutoPayEnrollmentStatusVisible() {
        return lblAutoPayEnrollmentStatus.isDisplayed();
    }

    @FindBy(id = "navbarDropdown")
    private WebElement btnThreeDot;

    public void clickBtnThreeDot() {
        waitForElementToBeClickable(btnThreeDot);
        clickWithJSExecutor(btnThreeDot);
    }

    @FindBy(css = ".deleterow")
    private WebElement btnUnEnroll;

    public void clickBtnUnEnroll() {
        waitForElementToBeClickable(btnUnEnroll);
        clickWithJSExecutor(btnUnEnroll);
    }

    @FindBy(id = "btnUnEntrollAutoPayAccount")
    private WebElement btnUpdate;

    public void clickBtnUpdate() {
        waitForElementToBeClickable(btnUpdate);
        clickWithJSExecutor(btnUpdate);
    }


    ////////// Current Bill ////////

    @FindBy(id = "btnpaypower")
    private WebElement btnMakePayment;

    public void clickBtnMakpayment() {
        log.info("Waiting For Visiblity status of Make Payment button");
        waitForElementToBeVisible(btnMakePayment);
        log.info("Visiblity status of Make Payment button:" + btnMakePayment.isDisplayed());
        log.info("Clicking Make paymnet button");
        click(btnMakePayment);
    }

    @FindBy(css = "label[for='controlS_01'] span[class='material-icons']")
    private WebElement rdoBtnBankCBill;

    public void clickrdoBtnBankCBill() {
        scrollToElement(rdoBtnBankCBill);
        log.info("Waiting For Visiblity status of bank radio button");
        waitForElementToBeClickable(rdoBtnBankCBill);
        log.info("Visiblity status of bank radio button:" + rdoBtnBankCBill.isDisplayed());
        log.info("Clicking bank radio button");
        clickWithJSExecutor(rdoBtnBankCBill);
    }

    @FindBy(css = "label[for='control_01'] span[class='material-icons']")
    private WebElement rdoBtnBankC2Bill;

    public void clickrdoBtnBankC2Bill() {
        scrollToElement(rdoBtnBankC2Bill);
        log.info("Waiting For Visiblity status of bank radio button");
        waitForElementToBeClickable(rdoBtnBankC2Bill);
        log.info("Visiblity status of bank radio button:" + rdoBtnBankC2Bill.isDisplayed());
        log.info("Clicking bank radio button");
        clickWithJSExecutor(rdoBtnBankC2Bill);
    }

    @FindBy(css = "#otherPaymentMethod")
    private WebElement newMethod;

    public void clickNewMethod() {
        scrollToElement(newMethod);
        log.info("Waiting For Visiblity status of bank radio button");
        waitForElementToBeClickable(newMethod);
        log.info("Visiblity status of bank radio button:" + newMethod.isDisplayed());
        log.info("Clicking bank radio button");
        clickWithJSExecutor(newMethod);
    }

    @FindBy(css = "#selectPaymentMethod")
    private WebElement savedMethod;

    public void clickSavedMethod() {
        scrollToElement(savedMethod);
        log.info("Waiting For Visiblity status of bank radio button");
        waitForElementToBeClickable(savedMethod);
        log.info("Visiblity status of bank radio button:" + savedMethod.isDisplayed());
        log.info("Clicking bank radio button");
        clickWithJSExecutor(savedMethod);
    }

    @FindBy(id = "bank_dropdown")
    private WebElement ddBankAccountCurrentBill;

    public WebElement elementDDBankAccountCurrentBill() {
        log.info("Waiting For Clickable status of DD Bank Acount");
        waitForElementToBeClickable(ddBankAccountCurrentBill);
        log.info("Visiblity status of DD Bank Acount:" + ddBankAccountCurrentBill.isDisplayed());
        return ddBankAccountCurrentBill;
    }

    @FindBy(id = "controlS_02")
    private WebElement rdoBtnCardCBill;

    public void clickrdoBtnCardCBill() {
        scrollToElement(rdoBtnBankCBill);
        log.info("Waiting For Visiblity status of Card radio button");
        waitForElementToBeClickable(rdoBtnCardCBill);
        log.info("Visiblity status of Card radio button:" + rdoBtnCardCBill.isDisplayed());
        log.info("Clicking Card radio button");
        clickWithJSExecutor(rdoBtnCardCBill);
    }

    @FindBy(id = "control_02")
    private WebElement rdoBtnCardC2Bill;

    public void clickrdoBtnCardC2Bill() {
        scrollToElement(rdoBtnCardC2Bill);
        log.info("Waiting For Visiblity status of Card radio button");
        waitForElementToBeClickable(rdoBtnCardC2Bill);
        log.info("Visiblity status of Card radio button:" + rdoBtnCardC2Bill.isDisplayed());
        log.info("Clicking Card radio button");
        clickWithJSExecutor(rdoBtnCardC2Bill);
    }

    @FindBy(css = "#ImgVisa")
    private WebElement cardVisa;

    public boolean isCardNumberVisa() {
        log.info("Checking that Payment Amt field is visible on the PayBill Step 2 page."
                + cardVisa.isDisplayed());
        return isElementVisible(cardVisa);
    }

    @FindBy(css = "#ImgMaster")
    private WebElement cardMc;

    public boolean isCardNumberMc() {
        log.info("Checking that Payment Amt field is visible on the PayBill Step 2 page."
                + cardMc.isDisplayed());
        return isElementVisible(cardMc);
    }

    @FindBy(css = "#ImgDiscov")
    private WebElement cardDiscover;

    public boolean isCardNumberDiscover() {
        log.info("Checking that Payment Amt field is visible on the PayBill Step 2 page."
                + cardDiscover.isDisplayed());
        return isElementVisible(cardDiscover);
    }

    @FindBy(css = "#Imgamex")
    private WebElement cardAmex;

    public boolean isCardNumberAmex() {
        log.info("Checking that Payment Amt field is visible on the PayBill Step 2 page."
                + cardAmex.isDisplayed());
        return isElementVisible(cardAmex);
    }

    @FindBy(css = "#ImgJcb")
    private WebElement cardJcb;

    public boolean isCardNumberJcb() {
        log.info("Checking that Payment Amt field is visible on the PayBill Step 2 page."
                + cardJcb.isDisplayed());
        return isElementVisible(cardJcb);
    }

    @FindBy(css = "#ImgCup")
    private WebElement cardCup;

    public boolean isCardNumberCup() {
        log.info("Checking that Payment Amt field is visible on the PayBill Step 2 page."
                + cardCup.isDisplayed());
        return isElementVisible(cardCup);
    }

    @FindBy(id = "card_dropdown")
    private WebElement ddCardAccountCurrentBill;

    public WebElement elementDDCardAccountCurrentBill() {
        log.info("Waiting For Clickable status of DD Card Acount");
        waitForElementToBeClickable(ddCardAccountCurrentBill);
        log.info("Visiblity status of DD Card Acount:" + ddCardAccountCurrentBill.isDisplayed());
        return ddCardAccountCurrentBill;
    }
    ////////////// TEXT TO PAY //////////

    @FindBy(css = "a[class=\"submit-button enrolTexttopayclick\"]")
    private WebElement btnEnrollTextToPay;

    public void clickBtnEnrollTextToPay() {
        log.info("Waiting For Visiblity status of Enroll Text to Pay button");
        waitForElementToBeVisible(btnEnrollTextToPay);
        log.info("Visiblity status of Enroll Text to Pay button:" + btnEnrollTextToPay.isDisplayed());
        log.info("Clicking Enroll Text to Pay button");
        click(btnEnrollTextToPay);
    }

    @FindBy(css = "#bank_dropdown")
    private WebElement ddBankAccountTextToPay;

    public WebElement elementDDBankAccountTextToPay() {
        log.info("Waiting For Clickable status of DD Bank Acount");
        waitForElementToBeClickable(ddBankAccountTextToPay);
        log.info("Visiblity status of DD Bank Acount:" + ddBankAccountTextToPay.isDisplayed());
        return ddBankAccountTextToPay;
    }

    @FindBy(id = "controlS_02")
    private WebElement rdoBtnCardTextToPay;

    public void clickrdoBtnCardTextToPay() {
        scrollToElement(rdoBtnCardTextToPay);
        log.info("Waiting For Visiblity status of Card radio button");
        waitForElementToBeClickable(rdoBtnCardTextToPay);
        log.info("Visiblity status of Card radio button:" + rdoBtnCardTextToPay.isDisplayed());
        log.info("Clicking Card radio button");
        clickWithJSExecutor(rdoBtnCardTextToPay);
    }

    @FindBy(css = "#card_dropdown")
    private WebElement ddCardAccountTextToPay;

    public WebElement elementDDCardAccountTextToPay() {
        log.info("Waiting For Clickable status of DD Card Acount");
        waitForElementToBeClickable(ddCardAccountTextToPay);
        log.info("Visiblity status of DD Card Acount:" + ddCardAccountTextToPay.isDisplayed());
        return ddCardAccountTextToPay;
    }


    @FindBy(xpath = "//*[@id='option-2']")
    private WebElement otheramountraidiobtn;

    public void ScrollAndSelect() throws InterruptedException {
        waitForElementToBeVisible(otheramountraidiobtn);
        scrollPageToElement(otheramountraidiobtn);
        clickElementUsingJsExecutor(otheramountraidiobtn);

        log.info("Selected Other Ammount ");
    }

    public void clickPayAmtFld() {
        scrollToElement(otheramountraidiobtn);
        click(otheramountraidiobtn);
        log.info("Click on Payment Amount Field Successfully .");
    }

    @FindBy(css = "#otheramount")
    private WebElement OtheramounttextBox;

    public void EnterOtherAmmount(String amount) {
        sendKeys(OtheramounttextBox, amount);
        log.info("Entered Other Ammount");
    }

    @FindBy(css = "#tokenize_payment")
    private WebElement ButtonNext;

    public void clickNextPayementButton() {
        click(ButtonNext);
        log.info("Clicked on Payment Next Button");
    }

    @FindBy(css = "#submitNext")
    private WebElement ButtonNewNext;

    public void clickNewNextPayementButton() {
        click(ButtonNewNext);
        log.info("Clicked on Payment Next Button");
    }

    @FindBy(xpath = "//*[@id='containerDiv']/div[3]/div[1]/div[1]/div/div[1]/ol/li[2]/span")
    private WebElement lbl_PaymentStep2;

    public String getPaymentStep2Text() {
        log.info("Fetching Step 2 Text.");
        String stepText = getText(lbl_PaymentStep2);
        log.info("Payemet Step2 Text {}: " + stepText);
        return stepText;
    }

    @FindBy(css = ".error")
    private WebElement lbl_RoutingNumber;

    public String getRoutingNumberError() {
        log.info("Fetching Step 2 Text.");
        String stepText = getText(lbl_RoutingNumber);
        log.info("Payemet Step2 Text {}: " + stepText);
        return stepText;
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

    @FindBy(id = "st3_paymetnDate")
    private WebElement paymentDate;

    public String getPayementDate() {
        String PayDate = getText(paymentDate);
        return PayDate;
    }

    @FindBy(xpath = ("//input[@type='button' and @class='submit-button stepperbtns stsubmitt' "
            + "and @value='Submit']"))
    private WebElement btnSubmitPayment;

    public void clickPaymentSubmitBtn() {
        scrollToElement(btnSubmitPayment);
        clickElementUsingJsExecutor(btnSubmitPayment);
        log.info("Submit Button Clicked Sucessfully .");
    }

    @FindBy(css = "#accholrnm")
    private WebElement accountHolderNameField;

    public void enterAccountHolderNameInTheField(String accountNumber) {
        sendKeys(accountHolderNameField, accountNumber);
        log.info("Entered account no in the field.");
    }

    @FindBy(css = "#rtno")
    private WebElement routingField;

    public void enterRoutingNumberInTheField(String accountNumber) {
        sendKeys(routingField, accountNumber);
        log.info("Entered account no in the field.");
    }

    @FindBy(css = "#crtno")
    private WebElement routingConfirmField;

    public void enterConfirmRoutingNumberInTheField(String accountNumber) {
        sendKeys(routingConfirmField, accountNumber);
        log.info("Entered account no in the field.");
    }

    @FindBy(css = "#bnkname")
    private WebElement bankNameField;

    public void enterBankNameInTheField(String accountNumber) {
        sendKeys(bankNameField, accountNumber);
        log.info("Entered account no in the field.");
    }

    public String getBankName() {
        log.info("Fetching Step 2 Text.");
        String stepText = getText(bankNameField);
        log.info("Payemet Step2 Text {}: " + stepText);
        return stepText;
    }

    @FindBy(css = "#bnkaccno")
    private WebElement bankAccountNumberField;

    public void enterBankAccountNumberInTheField(String accountNumber) {
        sendKeys(bankAccountNumberField, accountNumber);
        log.info("Entered account no in the field.");
    }

    public void clearBankAccountNumberInTheField() {
        log.info("clear the payment amount.");
        clear(bankAccountNumberField);
    }

    @FindBy(css = "#bnkaccnomask")
    private WebElement bankConfirmAccountNumberField;

    public void enterConfirmBankAccountNumberInTheField(String accountNumber) {
        sendKeys(bankConfirmAccountNumberField, accountNumber);
        log.info("Entered account no in the field.");
    }

    @FindBy(css = "#st2_accountType")
    private WebElement accountTypeField;

    public void clickAccountTypeFld() {
        scrollToElement(accountTypeField);
        click(accountTypeField);
        log.info("Click on Payment Amount Field Successfully .");
    }

    @FindBy(xpath = "//*[@id='st2_accountType']/option[5]")
    private WebElement lnkaccountype;

    public void clickAccountTypeOption() {
        click(lnkaccountype);
        log.info("Spanish language option clicked {}.");
    }

    @FindBy(css = "#st2_firstname")
    private WebElement firstNameField;

    public boolean isFirstNameFieldVisible() {
        log.info("Checking that Payment Amt field is visible on the PayBill Step 2 page."
                + firstNameField.isDisplayed());
        return isElementVisible(firstNameField);
    }

    public void enterFirstNameInTheField(String accountNumber) {
        sendKeys(firstNameField, accountNumber);
        log.info("Entered account no in the field.");
    }

    @FindBy(css = "#st2_lastname")
    private WebElement lastNameField;

    public boolean isLastNameFieldVisible() {
        log.info("Checking that Payment Amt field is visible on the PayBill Step 2 page."
                + lastNameField.isDisplayed());
        return isElementVisible(lastNameField);
    }

    public void enterLastNameInTheField(String accountNumber) {
        sendKeys(lastNameField, accountNumber);
        log.info("Entered account no in the field.");
    }

    public void clearLastNameInTheField() {
        log.info("clear the payment amount.");
        clear(lastNameField);
    }

    @FindBy(css = "#st2_address")
    private WebElement addressField;

    public boolean isAddressFieldVisible() {
        log.info("Checking that Payment Amt field is visible on the PayBill Step 2 page."
                + addressField.isDisplayed());
        return isElementVisible(addressField);
    }

    public void enterAddressInTheField(String accountNumber) {
        sendKeys(addressField, accountNumber);
        log.info("Entered account no in the field.");
    }

    public void clearAddressInTheField() {
        log.info("clear the payment amount.");
        clear(addressField);
    }

    @FindBy(css = "#st2_city")
    private WebElement cityField;

    public boolean isCityFieldVisible() {
        log.info("Checking that Payment Amt field is visible on the PayBill Step 2 page."
                + cityField.isDisplayed());
        return isElementVisible(cityField);
    }

    public void enterCityInTheField(String accountNumber) {
        sendKeys(cityField, accountNumber);
        log.info("Entered account no in the field.");
    }

    public void clearCityInTheField() {
        log.info("clear the payment amount.");
        clear(cityField);
    }

    @FindBy(css = "#st2_state")
    private WebElement stateField;

    public boolean isStateFieldVisible() {
        log.info("Checking that Payment Amt field is visible on the PayBill Step 2 page."
                + stateField.isDisplayed());
        return isElementVisible(stateField);
    }

    public void enterStateInTheField(String accountNumber) {
        sendKeys(stateField, accountNumber);
        log.info("Entered account no in the field.");
    }

    public void clearStateInTheField() {
        log.info("clear the payment amount.");
        clear(stateField);
    }

    @FindBy(css = "#st2_zipcode")
    private WebElement zipField;

    public boolean isZipCodeFieldVisible() {
        log.info("Checking that Payment Amt field is visible on the PayBill Step 2 page."
                + zipField.isDisplayed());
        return isElementVisible(zipField);
    }

    public void enterZipCodeInTheField(String accountNumber) {
        sendKeys(zipField, accountNumber);
        log.info("Entered account no in the field.");
    }

    public void clearZipCodeInTheField() {
        log.info("clear the payment amount.");
        clear(zipField);
    }
}

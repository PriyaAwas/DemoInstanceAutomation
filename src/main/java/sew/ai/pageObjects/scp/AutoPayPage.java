package sew.ai.pageObjects.scp;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AutoPayPage extends HomePage {
    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(AutoPayPage.class);

    public AutoPayPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#btnSaveRecurring")
    private WebElement btnEnroll;
    @FindBy(css = "span.head_icon_flat.icon_notif-trash")
    private WebElement btnDeleteAutoPay;
    @FindBy(css = "span.head_icon_flat.icon_edit")
    private WebElement btnEditAutoPay;
    @FindBy(css = "#Yes")
    private WebElement btnYesDeletePopUp;
    @FindBy(css = "#No")
    private WebElement btnNoDeletePopUp;
    @FindBy(css = "div.w2ui-msg-title")
    private WebElement popUpDeleteConfirmation;
    @FindBy(css = "div.w2ui-centered")
    private WebElement lblMsgDeleteConfirmation;
    @FindBy(css = "[id='ddlPayment']")
    private WebElement lstBoxPaymentMethod;
    @FindBy(css = "[id='ddlPaymentDate']")
    private WebElement lstBoxPaymentDate;
    @FindBy(css = "span.mdl-checkbox__ripple-container.mdl-js-ripple-effect.mdl-ripple--center")
    private WebElement chkBoxTerms;
    @FindBy(css = "#topHead")
    private WebElement lblEditDeleteAutoPayment;
    @FindBy(css = "input#btnSaveRecurring")
    private WebElement btnAlreadyEnrolled;
    @FindBy(css = "input#rdbCard.mdl-radio__button")
    private WebElement rdoBtnCreditDebitCard;
    @FindBy(css = "#SelectCard span[title='Click to select credit card']")
    private WebElement lblRdoBtnCreditDebitCard;
    @FindBy(css = "#SelectBank span[title='Click to select Bank Account']")
    private WebElement lblRdoBtnBankAcc;
    @FindBy(css = "input#rdbBank.mdl-radio__button")
    private WebElement rdoBtnBankAccount;
    @FindBy(css = "+Add Payment Method")
    private WebElement lnkAddNewPayment;
    @FindBy(css = "[id='div_disclaim']")
    private WebElement lblAlreadyEnrolledDisclaimer;
    @FindBy(css = "div.w2ui-tag-body")
    private WebElement lblGenericErrorMessage;
    @FindBy(css = "[id='lblrdoCredit']")
    private WebElement lblRdoCreditCard;
    @FindBy(css = "[id='lblrdoBank']")
    private WebElement lblRdoBtnBankAccount;
    @FindBy(css = "[id='rdoCredit']")
    private WebElement rdoBtnCreditCardPaymentModePopUp;
    @FindBy(css = "[id='rdoBank']")
    private WebElement rdoBtnBankAccountPaymentModePopUp;
    @FindBy(css = "[id='txtCardName']")
    private WebElement txtBoxNameOnCard;
    @FindBy(css = "[id='txtCardNumber']")
    private WebElement txtBoxCardNumber;
    @FindBy(css = "[id='ddlMonth']")
    private WebElement lstBoxCardExpiryMonth;
    @FindBy(css = "[id='ddlYear']")
    private WebElement lstBoxCardExpiryYear;
    @FindBy(css = "[id='txtSecurityCode']")
    private WebElement txtBoxSecurityCode;
    @FindBy(css = "[id='btnCancel']")
    private WebElement btnClearAddPayment;
    @FindBy(css = "[id='btnAddUpdate']")
    private WebElement btnAddAddPayment;
    @FindBy(css = "[id='txtAccountHolderName']")
    private WebElement txtBoxAccountHolderName;
    @FindBy(css = "[id='txtRoutingNumber']")
    private WebElement txtBoxRoutingNumber;
    @FindBy(css = "[id='txtBankName']")
    private WebElement txtBoxBankName;
    @FindBy(css = "[id='txtBankAccount']")
    private WebElement txtBoxBankAccountNumber;
    @FindBy(css = "[id='myModalLabelheadertext']")
    private WebElement lblAddPaymentMethodModal;
    @FindBy(css = ".toast-message")
    private WebElement lblMsgOnHeader;
    @FindBy(css = ".toast.toast-success")
    private WebElement lblSuccessMsgOnHeader;
    @FindBy(css = "#autopayheadertxt span")
    private WebElement lblDisclaimerOnAutopay;
    @FindBy(css = ".previewamount")
    private WebElement lblAccNotEnrolled;
    @FindBy(css = ".enrolautopayclick")
    private WebElement btnEnrollInAutopay;
    @FindBy (xpath="//*[@id='ddlCardBank']/h2)[1]")
    private WebElement lblHeaderPaymentDate;
    @FindBy (xpath="//*[@id='ddlCardBank']/h2)[2]")
    private WebElement lblHeaderPaymentMethod;
    @FindBy(xpath= "//*[@class='progtrckrs-doing']/span")
    private WebElement lblStep1PaymentDetails;
    @FindBy(css = ".progtrckrs-doing.todoremovedone span")
    private WebElement lblStep2ConfirmPayment;
    @FindBy(css = "#firxt_nextbtn")
    private WebElement btnNextPaymentPage;
    @FindBy(css = ".stp_second.stp_common .common_heading_stepper")
    private WebElement lblHederPaymentCnfrmtionPagePage;
    @FindBy (xpath= "//*[@id='SelectedPaymetDate']/../label")
    private WebElement lblPymntDateCnfrmtionPage;
    @FindBy (xpath= "//*[@id='SelectedPayMethod']/../label")
    private WebElement lblPymntMethodCnfrmtionPage;
    @FindBy (xpath= "//*[@id='SelectedPayMethod']")
    private WebElement valuePymntMethodCnfrmtionPage;
    @FindBy(css = ".setting_save_box #bkp_step_btn")
    private WebElement btnBackConfirmationPage;
    @FindBy(css = ".enrolledbtn")
    private WebElement lblEnrolledInAutoPay;
    @FindBy(css = ".stp_first.stp_common .common_heading_stepper")
    private WebElement lblHederPaymentPage;
    @FindBy(css = "#navbarDropdown")
    private WebElement btnToggle;
    @FindBy(css = "a[globalize='ML_Billing_Span_Delete']")
    private WebElement btnUnEnroll;
    @FindBy(css = "#btnUnEntrollAutoPayAccount")
    private WebElement btnContinueConfirmPopUp;
    @FindBy (xpath= "//button[@id='btnUnEntrollAutoPayAccount']/../../div[2]")
    private WebElement lblConfirmMsgPopUp;
    @FindBy(css = "a[class='editrow']")
    private WebElement btnEdit;
    @FindBy (xpath= "//button[text()='Cancel']")
    private WebElement btnCancelConfirmationPopUp;
    @FindBy(css = ".stp_success.stp_common .titlehead")
    private WebElement txtEnrollSucess;
    @FindBy(css = ".mp_head p")
    private WebElement txtEnrollSuccessMessage;
}

package sew.ai.pageObjects.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PreLogBillPaymentPage extends HomePage {
    private static final Logger log = LogManager.getLogger(PreLogBillPaymentPage.class);

    public PreLogBillPaymentPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#txtAccountNumber")
    private WebElement txtBoxAccountNumber;
    @FindBy(css = "#txtPhoneNumber")
    private WebElement txtBoxPhoneNumber;
    @FindBy(css = "#txtphone1")
    private WebElement txtBoxPrimaryContactNumber;
    @FindBy(css = "button#btnSubmitPayment")
    private WebElement btnNext;
    @FindBy(css = "#btnCancelPayment")
    private WebElement btnCancel;
    @FindBy(css = "#btnAgree")
    private WebElement btnAgree;
    @FindBy(css = "#btnDisagree")
    private WebElement btnDisAgree;
    @FindBy(css = "[id='rdoCredit']")
    private WebElement rdoBtnCreditCard;
    @FindBy(css = "[id='rdoBank']")
    private WebElement rdoBtnBankAcc;
    @FindBy(css = "#txtTotlal")
    private WebElement txtBoxTotalAmount;
    @FindBy(css = "#txtNameOnCard")
    private WebElement txtBoxNameOnCard;
    @FindBy(css = "#txtCardNumber")
    private WebElement txtBoxCardNumber;
    @FindBy(css = "#txtSecurityCode")
    private WebElement txtBoxSecurityCode;
    @FindBy(css = "[id='txtAccountName']")
    private WebElement txtBoxAccountName;
    @FindBy(css = "[id='txtRoutingNmbr']")
    private WebElement txtBoxRoutingNumber;
    @FindBy(css = "#txtConfRoutingNumber")
    private WebElement txtBoxConfirmRoutingNumber;
    @FindBy(css = "[id='txtBankName']")
    private WebElement txtBoxBankName;
    @FindBy(css = "[id='txtBankAccNumber']")
    private WebElement txtBoxBankAccNumber;
    @FindBy(css = "[id='txtConfBankAccount']")
    private WebElement txtBoxConfirmBankAccNumber;
    @FindBy(css = "input#txtCustomerName")
    private WebElement txtBoxCustomerName;
    @FindBy(css = "input#btnsubmitpreview")
    private WebElement btnsubmit;
    @FindBy(css = "[id='btnCancel']")
    private WebElement btnCancelPayment;
    @FindBy(css = "input#btnOK")
    private WebElement btnOk;
    @FindBy(css = "#lblMessage")
    private WebElement lblPaymentSuccessfull;
    @FindBy(css = ".page_load_container")
    private WebElement lblPaymentLoader;
    @FindBy(css = "[id='txtfirtname']")
    private WebElement txtBoxFirstName;
    @FindBy(css = "[id='txtlastname']")
    private WebElement txtBoxLastName;
    @FindBy(css = "[id='txtAddress']")
    private WebElement txtBoxAccountAddress;
    @FindBy(css = "[id='txtCity']")
    private WebElement txtBoxCity;
    @FindBy(css = "[id='txtState']")
    private WebElement txtBoxState;
    @FindBy(css = "[id='txtZipCode']")
    private WebElement txtBoxZipCode;
    @FindBy(css = "input#txtphone1")
    private WebElement txtBoxMobile;
    @FindBy(css = "[id='txtemail']")
    private WebElement txtBoxEmail;
    @FindBy(css = ".toast-message")
    private WebElement lblMsgOnHeader;
    @FindBy(css = "span.error_messagecommon")
    private WebElement lblErrorMsgInvalidEntry;
    @FindBy(css = ".w2ui-centered")
    private WebElement lblMsgPopUp;
    @FindBy(css = ".w2ui-popup-btn.w2ui-btn")
    private WebElement btnOkMsgPopUp;
    @FindBy(css = ".option_card_select .mdl-ripple--center")
    private WebElement rdoBtnPayment;
    @FindBy(css = ".toast.toast-warning")
    private WebElement lblErrorMsgHeader;
    @FindBy(css = "#labelheader[globalize='ML_DASHBOARD_Lbl_PayBill']")
    private WebElement iconBillPaymentHeader;
    @FindBy(css = "h4[globalize='ML_OTP_Welcome']")
    private WebElement lblWelcomeScm;
    @FindBy(css = "span [globalize='ML_OTP_Note']")
    private WebElement lblEnterAcntContactNumber;
    @FindBy(css = ".toast-message")
    private WebElement lblEnterMandatoryInfo;
    @FindBy(css = "img#ImgMaster")
    private WebElement imgMasterCard;
    @FindBy(css = "img#ImgVisa")
    private WebElement imgVisaCard;
    @FindBy(css = "img#ImgDiscov")
    private WebElement imgDiscoverCard;
    @FindBy(css = "img#Imgamex")
    private WebElement imgAmexCard;
    @FindBy(css = "div.page_load_container strong")
    private WebElement lblPaymentProcess;
    @FindBy(css = "div.page_load_container p")
    private WebElement lblPaymentProcessOne;
    @FindBy(css = "#ddlMonth")
    private WebElement lstBoxExpiryMonth;
    @FindBy(css = "#ddlYear")
    private WebElement lstBoxExpiryYear;
    @FindBy(css = "#lblEmailMessage")
    private WebElement lblEmailSent;
    @FindBy(xpath = "//*[@id='lblAmount']/../../b")
    private WebElement lblTransactionAmount;
    @FindBy(xpath = "//*[@id='lblTranDate']/../../b")
    private WebElement lblTransactionDate;
    @FindBy(xpath = "//*[@id='lblTranId']/../../b")
    private WebElement lblTransactionId;
    @FindBy(css = "#lblAmount")
    private WebElement lblValTransactionAmount;
    @FindBy(css = "#lblTranDate")
    private WebElement lblValTransactionDate;
    @FindBy(css = "#lblTranId")
    private WebElement lblValTransactionId;
    @FindBy(css = "#txtRemainingBalance strong")
    private WebElement lblRemainingBalance;
    @FindBy(css = "#lblRemainingBalance")
    private WebElement lblValRemainingBalance;
    @FindBy(css = "img[src='images/ok-icon.png']")
    private WebElement imgOkPaymentSuccess;
    @FindBy(css = "select#AccountType")
    private WebElement lstBoxAccountType;
    @FindBy(css = ".w2ui-popup")
    private WebElement popUpConfirmation;
    @FindBy(css = ".w2ui-popup-title")
    private WebElement lblPopUpConfirmTitle;
    @FindBy(css = ".w2ui-popup-btn.w2ui-btn[id='Yes'']")
    private WebElement btnYesPopUpConfirm;
    @FindBy(css = ".w2ui-popup-btn.w2ui-btn[id='No'']")
    private WebElement btnNoPopUpConfirm;
    @FindBy(css = ".w2ui-centered.w2ui-confirm-msg")
    private WebElement lblPopUpConfirmMesage;
    @FindBy(css = "#labelheader")
    private WebElement lblHeaderPaymentPage;
    @FindBy(css = "#liStepPay span")
    private WebElement lblStep1PaymentPage;
    @FindBy(css = "#liStepPay2 span")
    private WebElement lblStep2PaymentPage;
    @FindBy(css = "#liStepPay3 span")
    private WebElement lblStep3PaymentPage;
    @FindBy(css = "#txttotalshow")
    private WebElement txtBoxAmountDue;
    @FindBy(css = "input[id='txtTotlal']")
    private WebElement txtBoxPayingAmount;
    @FindBy(css = "[id='alertMsg']")
    private WebElement lblTransactionFeeMsg;
    @FindBy(css = "#btnsubmit")
    private WebElement btnMakeAPaymentPreviewPage;
    @FindBy(css = "#bodyMessage")
    private WebElement lblConfirmationPopupMsg;
    @FindBy(css = "#btnBillPayContinue")
    private WebElement btnContinueConfirmationMsg;
    @FindBy(xpath = "//label[@globalize='ML_LBL_BusinessName']")
    private WebElement lblTxtBusinessName;
    @FindBy(xpath = "//label[@id='txtFnamePreview']/../label)[2]")
    private WebElement lblFirstNamePaymentPreviewPage;
    @FindBy(css = "#txtFnamePreview")
    private WebElement valueFirstNamePaymentPreviewPage;
    @FindBy(xpath = "//label[@id='txtLnamePreview']/../label)[2]")
    private WebElement lblLastNamePaymentPreviewPage;
    @FindBy(css = "#txtLnamePreview")
    private WebElement valueLastNamePaymentPreviewPage;
    @FindBy(css = ".effect_lbl[for='txtaddressPreview']")
    private WebElement lblAccntAddrsPaymentPreviewPage;
    @FindBy(css = "#txtaddressPreview")
    private WebElement valueAccntAddrsPaymentPreviewPage;
    @FindBy(css = ".effect_lbl[for='txtCityPreview']")
    private WebElement lblCityPaymentPreviewPage;
    @FindBy(css = "#txtCityPreview")
    private WebElement valueCityPaymentPreviewPage;
    @FindBy(css = ".effect_lbl[for=txtStatePreview']")
    private WebElement lblStatePaymentPreviewPage;
    @FindBy(css = " #txtStatePreview")
    private WebElement valueStatePaymentPreviewPage;
    @FindBy(css = ".effect_lbl[for='txtZipPreview']")
    private WebElement lblZipCodePaymentPreviewPage;
    @FindBy(css = " #txtZipPreview")
    private WebElement valueZipCodePaymentPreviewPage;
    @FindBy(css = ".effect_lbl[for='txtPrimaryContactPreview']")
    private WebElement lblPrimaryPhonePaymentPreviewPage;
    @FindBy(css = " #txtPrimaryContactPreview")
    private WebElement valuePrimaryPhonePaymentPreviewPage;
    @FindBy(css = ".effect_lbl[for='txtMailPreview']")
    private WebElement lblEmailAddrssPaymentPreviewPage;
    @FindBy(css = " #txtMailPreview")
    private WebElement valueEmailAddrssPaymentPreviewPage;
    @FindBy(css = ".effect_lbl[for='txttotalPayPreview']")
    private WebElement lblTotalAmountPaymentPreviewPage;
    @FindBy(css = " #txttotalPayPreview")
    private WebElement valueTotalAmountPaymentPreviewPage;
    @FindBy(css = ".effect_lbl[for='txtBillAmountPreview']")
    private WebElement lblBillAmountPaymentPreviewPage;
    @FindBy(css = " #txtBillAmountPreview")
    private WebElement valueBillAmountPaymentPreviewPage;
    @FindBy(css = ".effect_lbl[for='txttotalTransactionFeePreview']")
    private WebElement lblTranFeePaymentPreviewPage;
    @FindBy(css = " #txttotalTransactionFeePreview")
    private WebElement valueTranFeePaymentPreviewPage;
    @FindBy(css = ".effect_lbl[for='txtPayModePreview']")
    private WebElement lblPaymentModePaymentPreviewPage;
    @FindBy(css = " #txtPayModePreview")
    private WebElement valuePaymentModePaymentPreviewPage;
    @FindBy(css = " #btnCancelpreview")
    private WebElement btnCancelPaymentPreviewPage;
    @FindBy(xpath = "//label[@globalize='ML_HeaderMenu_span_MyAccount'][contains(text(), 'Account')]")
    private WebElement lblAccount;
    @FindBy(css = " #txtaccount")
    private WebElement valLblAccount;
    @FindBy(xpath = "//label[@globalize='ML_lbl_Name'][contains(text(), 'Name')]")
    private WebElement lblName;
    @FindBy(css = " #txtname")
    private WebElement valLblName;
    @FindBy(xpath = "//label[@globalize='ML_LBL_BusinessName'][contains(text(), 'Business Name')]")
    private WebElement lblBusinessName;
    @FindBy(css = " #txtCustomerName")
    private WebElement valLblBusinessName;
    @FindBy(xpath = "//label[@globalize='ML_Paybill_Lbl_TotalAmount'][contains(text(), 'Amount Due')]")
    private WebElement lblTotalAmount;
    @FindBy(xpath = "//label[@globalize='ML_Paybill_Lbl_TotalAmount'][contains(text(), 'Amount Due')]")
    private WebElement lblTotalAmountDue;

    @FindBy(css = "#txttotalshow")
    private WebElement valLblTotalAmountDue;
    @FindBy(xpath = "//*[@id='submitteddetailvalue']//h2[contains(text(), 'Contact Information')]")
    private WebElement lblContactInformation;
    @FindBy(xpath = "//*[@id='submitteddetailvalue']//h2[contains(text(), 'Payment Amount')]")
    private WebElement lblPaymentAmount;
    @FindBy(xpath = "//*[@id='submitteddetailvalue'']//h2[contains(text(), 'Payment Information')]")
    private WebElement lblPaymentInformation;
    @FindBy(xpath = "//*[@id='submitteddetailvalue'']//h2[contains(text(), 'Billing Address')]")
    private WebElement lblBillingAddress;

    @FindBy(xpath = "//label[@globalize='ML_MakeOTP_txt_AmtPay'][contains(text(), 'Amount You Want to Pay ($)')]")
    private WebElement  wtrMarkAmountYouWantToPay;
    @FindBy(xpath = "//label[@class='effect_lbl'][contains(text(), 'First Name')]")
    private WebElement wtrMarkFirstName;
    @FindBy(xpath = "//label[@class='effect_lbl'][contains(text(), 'Last Name')]")
    private WebElement wtrMarkLastName;
    @FindBy(xpath = "//div[@class='billing_add_details']//label[@class='effect_lbl'][contains(text(), 'Address')]")
    private WebElement wtrMarkAddress;
    @FindBy(xpath = "//div[@class='billing_add_details']//label[@class='effect_lbl'][contains(text(), 'City')]")
    private WebElement wtrMarkCity;
    @FindBy(xpath = "//div[@class='billing_add_details']//label[@class='effect_lbl'][contains(text(), 'State')]")
    private WebElement wtrMarkState;
    @FindBy(xpath = "//div[@class='billing_add_details']//label[@class='effect_lbl'][contains(text(), 'Zip Code')]")
    private WebElement wtrMarkZipCode;
    @FindBy(xpath = "//div[@id='PaymentInfo']//label[@class='effect_lbl'][contains(text(), 'Name on Card')]")
    private WebElement wtrMarkNameOnCard;
    @FindBy(xpath = "//div[@id='PaymentInfo']//label[@class='effect_lbl'][contains(text(), 'Card Number')]")
    private WebElement wtrMarkCardNumber;
    @FindBy(xpath = "//div[@id='PaymentInfo']//label[@class='effect_lbl'][contains(text(), 'Month')]")
    private WebElement wtrMarkMonth;
    @FindBy(xpath = "//div[@id='PaymentInfo']//label[@class='effect_lbl'][contains(text(), 'Year')]")
    private WebElement wtrMarkYear;
    @FindBy(xpath = "//div[@id='PaymentInfo']//label[@class='effect_lbl'][contains(text(), 'Security Code')]")
    private WebElement wtrMarkSecurityCode;
    @FindBy(xpath = "//div[@class='bank']//label[@class='effect_lbl'][contains(text(), 'Account Holder Name')]")
    private WebElement wtrMarkAccountHolderName;
    @FindBy(xpath = "//div[@class='bank']//label[@class='effect_lbl'][contains(text(), 'Routing Number')]")
    private WebElement wtrMarkRoutingNumber;
    @FindBy(xpath = "//div[@class='bank']//label[@class='effect_lbl'][contains(text(), 'Confirm Routing Number')]")
    private WebElement wtrMarkConfirmRoutingNumber;
    @FindBy(xpath = "//div[@class='bank']//label[@class='effect_lbl'][contains(text(), 'Bank Name')]")
    private WebElement wtrMarkBankName;
    @FindBy(xpath = "//div[@class='bank']//label[@class='effect_lbl'][contains(text(), 'Bank Account Number')]")
    private WebElement wtrMarkBankAccountNumber;
    @FindBy(xpath = "//div[@class='bank']//label[@class='effect_lbl'][contains(text(), 'Confirm Bank Account Number')]")
    private WebElement wtrMarkConfirmBankAccountNumber;
    @FindBy(xpath = "//div[@class='bank']//label[@class='effect_lbl'][contains(text(), 'Account Type')]")
    private WebElement wtrMarkAccountType;
    @FindBy(xpath = "//div[@id='submitteddetailvalue']//label[@class='effect_lbl'][contains(text(), 'Primary Phone (Optional)')]")
    private WebElement wtrMarkPhoneNumber;
    @FindBy(xpath = "//div[@id='submitteddetailvalue']//label[@class='effect_lbl'][contains(text(), 'Email Address')]")
    private WebElement wtrMarkEmailAddress;
    @FindBy(css = "#txtNamePreview")
    private WebElement valueNamePaymentPreviewPage;
    @FindBy(xpath = "//div[@id='Res_NamePreview']//label[@for='txtNamePreview'][contains(text(), 'Name ')]")
    private WebElement lblNamePaymentPreviewPage;
    @FindBy(xpath = "//div//label[@for='txtAccountPreview'][contains(text(), 'Account')]")
    private WebElement lblAccountPaymentPreviewPage;
    @FindBy(css = "#txtAccountPreview")
    private WebElement valueAccountPaymentPreviewPage;
    @FindBy(xpath = "//div//label[@for='txtAmntDuePreview'][contains(text(), 'Amount Due ($)')]")
    private WebElement lblAmountDuePaymentPreviewPage;
    @FindBy(css = "#txtAmntDuePreview")
    private WebElement valueAmountDuePaymentPreviewPage;
    @FindBy(css = "#txtCustomerNamePreview")
    private WebElement valueBusinessNamePaymentPreviewPage;
    @FindBy(xpath = "//div[@id='Comm_NamePreview']//label[@for='txtCustomerNamePreview'][contains(text(), 'Business Name')]")
    private WebElement lblBusinessNamePaymentPreviewPage;
    @FindBy(xpath = "//div[@id='DivbillCommercialName']//label[@class='effect_lbl'][contains(text(), 'Name')]")
    private WebElement wtrMarkFullName;
    @FindBy(css = "input#txtFullName")
    private WebElement txtBoxFullName;
    @FindBy(css = "#ConfirmStep1 div.modal-content")
    private WebElement popUpZeroBalance;
    @FindBy(xpath = "//*[@id='ConfirmStep1']//div//h4[contains(text(),'Confirmation')]")
    private WebElement lblTitlePopUpZeroBalance;
    @FindBy(css = "#ConfirmStep1 .modal-body #bodyMessage1")
    private WebElement lblMessagePopUpZeroBalance;
    @FindBy(css = "#ConfirmStep1 #btnBillCancel")
    private WebElement btnCancelPopUpZeroBalance;
    @FindBy(css = "#ConfirmStep1 input#btnBillContinue")
    private WebElement btnContinuePopUpZeroBalance;
}
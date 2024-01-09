package sew.ai.pageObjects.scp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentInformationPage extends HomePage {
    private static final Logger log = LogManager.getLogger(PaymentInformationPage.class);

    public PaymentInformationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".MuiGrid-root.pageheading-box #PageTitle")
    private WebElement lblPaymentInfoPageHeading;
    @FindBy(css = "[id='myModalLabelheadertext1']")
    private WebElement lblPasswordWindow;
    @FindBy(css = "[id='txtPassword']")
    private WebElement txtBoxPassword;
    @FindBy(css = "#btnCancel")
    private WebElement btnCancel;
    @FindBy(css = "input#btnValidateLogin.submit-button")
    private WebElement btnSubmit;
    @FindBy(css = ".addpmtmethod .add-card a")
    private WebElement lnkAddPaymentMethod;
    @FindBy(css = "[id='btnSaveAll']")
    private WebElement btnSave;
    @FindBy(css = "[id='lblrdoCredit']")
    private WebElement lblRdoCreditCard;
    @FindBy(css = "#rdoCredit")
    private WebElement rdoCreditCard;
    @FindBy(css = "#rdoBank")
    private WebElement rdoBankAccount;
    @FindBy(css = "[id='lblrdoBank']")
    private WebElement lblRdoBtnBankAccount;
    @FindBy(css = "#divcreditrdobtn .mdl-radio__ripple-container.mdl-js-ripple-effect.mdl-ripple--center")
    private WebElement rdoBtnCreditCard;
    @FindBy(css = "#divbankrdobtn .mdl-radio__ripple-container.mdl-js-ripple-effect.mdl-ripple--center")
    private WebElement rdoBtnBankAccount;
    @FindBy(css = "[id='txtCardName']")
    private WebElement txtBoxNameOnCard;
    @FindBy(css = "input#txtCardNumber.input_effect")
    private WebElement txtBoxCardNumber;
    @FindBy(css = "[id='ddlMonth']")
    private WebElement lstBoxCardExpiryMonth;
    @FindBy(css = "[id='ddlYear']")
    private WebElement lstBoxCardExpiryYear;
    @FindBy(css = "AccountType")
    private WebElement lstBoxAccountTypeId;
    @FindBy(css = "label[globalize=ML_MakeOTP_ddl_AccountType]")
    private WebElement lblAccountTypeCss;
    @FindBy(css = "#txtfirtname")
    private WebElement txtFirstName;
    @FindBy(css = "#txtlastname")
    private WebElement txtLastName;
    @FindBy(css = "#txtAddress")
    private WebElement txtAddress;
    @FindBy(css = "#txtCity")
    private WebElement txtCity;
    @FindBy(css = "#txtState")
    private WebElement txtState;
    @FindBy(css = "#txtZipCode")
    private WebElement txtZipCode;
    @FindBy(css = "[id='txtSecurityCode']")
    private WebElement txtBoxSecurityCode;
    @FindBy(css = "[id='btnCancel']")
    private WebElement btnClearAddPayment;
    @FindBy(css = "[id='btnAddUpdate']")
    private WebElement btnAddPaymentMethod;
    @FindBy(css = "[id='txtAccountHolderName']")
    private WebElement txtBoxAccountHolderName;
    @FindBy(css = "[id='txtRoutingNumber']")
    private WebElement txtBoxRoutingNumber;
    @FindBy(css = "#txtConfRoutingNumber")
    private WebElement txtBoxConfirmRoutingNo;
    @FindBy(css = "[id='txtBankName']")
    private WebElement txtBoxBankName;
    @FindBy(css = "[id='txtBankAccount']")
    private WebElement txtBoxBankAccountNumber;
    @FindBy(css = "#txtConfBankAccount")
    private WebElement txtConfirmBankAccNo;
    @FindBy(css = ".deleterow[title='Delete'] img")
    private WebElement btnDeletePaymentMethod;
    @FindBy(css = ".edit_del_btn .edit img[src='images/icon_mark.png']")
    private WebElement btnEditPaymentMethod;
    @FindBy(css = ".address-button-billing.rdbdefault")
    private WebElement rdoBtnFirstDefaultPaymentMethod;
    @FindBy(css = ".address-button-billing.rdbdefault[checked='checked']")
    private WebElement rdoBtnDefaultPaymentMethodSelected;
    @FindBy(css = ".inner-address")
    private WebElement lblPaymentInformationTable;
    @FindBy(css = "span[title='Card Type/Bank Account']")
    private WebElement lblCardTypeBankAccount;
    @FindBy(css = "span[title='Card/Bank Account Number'']")
    private WebElement lblCardBankAccountNumber;
    @FindBy(css = "span[title='Expiry Date']")
    private WebElement lblExpiryDate;
    @FindBy(css = "span[title='Default']")
    private WebElement lblDefault;
    @FindBy(css = "span[title='Edit/Delete']")
    private WebElement lblEditDelete;
    @FindBy(css = "[id='myModalLabelheadertext']")
    private WebElement lblAddPaymentMethodModal;
    @FindBy(css = ".w2ui-centered")
    private WebElement lblMsgPopUpDeletePaymentMethod;
    @FindBy(css = "button#Yes.w2ui-popup-btn.w2ui-btn")
    private WebElement btnYesDeletePaymentMethodPopUp;
    @FindBy(css = ".w2ui-msg-buttonsbutton#No")
    private WebElement btnNoDeletePaymentMethodPopUp;
    @FindBy(css = ".error_messagecommon")
    private WebElement lblErrorMsgInvalidEntry;
    @FindBy(css = ".toast-message")
    private WebElement lblMsgOnHeader;
    @FindBy(css = "button.toast-close-button")
    private WebElement btnCloseMsgOnHeader;
    @FindBy(css = "#divPopup #btnclosepopup")
    private WebElement btnCloseModalEditPaymentInfo;
    @FindBy(css = "#divPopup #btnclosepopup")
    private WebElement btnCloseAddPaymentMethodPopup;
    @FindBy(css = ".ng-binding")
    private WebElement accNumberDigits;
    @FindBy(css = "#lblutilitynumber_")
    private WebElement lblExpiryDateValue;
    @FindBy(css = "#PaymentTable [class='ng-scope']")
    private WebElement rowPaymentInfo;
    @FindBy(css = "span.outer_radio")
    private WebElement rdoBtnPaymentMethod;
    @FindBy(css = "div.card_type_img_popup")
    private WebElement imgCardType;
    @FindBy(css = "div.popup_left_content_area_home")
    private WebElement lblCardExpiryDate;
    @FindBy(css = "label[globalize='ML_ACCOUNT_lbl_HolderName']")
    private WebElement lblTxtBoxBankAccountHolderName;
    @FindBy(css = "label[globalize='ML_ACCOUNT_lbl_Routing']")
    private WebElement lblTxtBoxBankRoutingNumber;
    @FindBy(css = "label[globalize='ML_ACCOUNT_lbl_BAnkName']")
    private WebElement lblTxtBoxBankName;
    @FindBy(css = "label[globalize='ML_ACCOUNT_lbl_BAnkAccount']")
    private WebElement lblTxtBoxBankAccountNumber;
    @FindBy(css = "label[globalize='ML_ADDCARDBANKDETAIL_Txt_NameonCard']")
    private WebElement lblTxtBoxNameOnCard;
    @FindBy(css = "label[globalize='ML_Billing_lbl_CrdNum']")
    private WebElement lblTxtBoxCardNumber;
    @FindBy(css = "div[globalize='ML_BILLING_Lbl_ExpryDate']")
    private WebElement lblLstBoxBoxExpiryDate;
    @FindBy(css = "label[globalize='ML_ACCOUNT_DDL_Month']")
    private WebElement lblLstBoxCardMonth;
    @FindBy(css = "label[globalize='ML_ACCOUNT_DDL_Year']")
    private WebElement lblLstBoxCardYear;
    @FindBy(css = "label[globalize='ML_ACCOUNT_Lbl_Code']")
    private WebElement lblTxtBoxSecurityCode;
    @FindBy(css = "label[globalize='ML_ACCOUNT_Lbl_CardType']")
    private WebElement lblImgCardType;
    @FindBy (xpath=".//*[@id='PaymentTable']/table//tr[1]")
    private WebElement rowPaymentInfoHeader;
    @FindBy (xpath=".//*[@id='PaymentTable'']/table//tr[@class='ng-scope']")
            private WebElement rowsPaymentInfo;
    @FindBy (xpath=".//*[@id='PaymentTable']/table//tr/th/span")
    private WebElement columnsPaymentInfo;
    @FindBy (xpath=".//*[@id='PaymentTable']/table//tr[@class='ng-scope'][1]/td[1]")
            private WebElement cellsPaymentInfo;
    @FindBy(css = "#PaymentTable .card_txt_sec label[ng-bind*='x.BankAccount']")
    private WebElement cellBankAccountNumber;
    @FindBy(css = "#PaymentTable .card_txt_sec label[ng-bind*='x.BankName']")
    private WebElement cellBankAccountName;
    @FindBy(css = "#btnAddUpdate")
    private WebElement btnUpdatePaymentInformation;
    @FindBy(css = ".card_txt_sec label[ng-bind='x.CardNumber|maskcardNumber']")
    private WebElement cellCardNumber;
    @FindBy(css = ".card_txt_sec #lblutilitynumber_")
    private WebElement cellCardExpiryDate;
    @FindBy(css = ".deleterow a")
    private WebElement cellDeleteIconsOnGrid;
    @FindBy (xpath=" //div[@class = 'noti_wrapper_box ng-scope' and not(@style)]/i")
    private WebElement icoExpirySoonCardIcon;
    @FindBy(css = ".nav-item.dropdown i")
    private WebElement btnToggleAllPaymentMethod;
    @FindBy (xpath=" //input[contains(@id, 'rdobtnProperty') and @checked]/../../preceding-sibling::td[2]/label")
    private WebElement lblDefaultPaymentACNum;
    @FindBy (xpath=" //button[text()='Remove']")
            private WebElement btnRemoveConfirmationMsg;
    @FindBy(xpath=" //*[@class='listcard']//a[contains(text(),'Edit')]")
    private WebElement optionEditPaymentMethod;
    @FindBy (xpath=" //*[@class='listcard']//a[contains(text(),'Remove')]")
            private WebElement optionDeletePaymentMethod;
    @FindBy (xpath=" //label[@class='crd_nopmt ng-binding']")
    private WebElement lblAddedCardNo;
    @FindBy(css = "label[ng-bind='x.BankAccount|maskcardNumber']")
    private WebElement lblAddedBankAccountNo;
}

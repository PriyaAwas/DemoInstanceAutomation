package sew.ai.pageObjects.csp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sew.ai.pageObjects.scp.HomePage;

import java.util.List;

public class WaysToSavePage extends HomePage {

    private static final Logger log = LogManager.getLogger(WaysToSavePage.class);

    public WaysToSavePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#filter")
    private WebElement ddWaysToSaveFilter;

    public Boolean isDdWaysToSaveFilterVisible() {
        boolean isVisible = isElementVisible(ddWaysToSaveFilter);
        log.info("Ways to Save Dropdown visibility status {}" + isVisible);
        return isVisible;
    }

    public String getSelectedOptionWaysToSaveFilter() {
        String optionSelected =getSelectedValueInDropBox(ddWaysToSaveFilter);
        log.info("Selected option in the Ways To Save Filter is "+optionSelected);
        return optionSelected;
    }

    public void selectWaysToSaveFilterDropdown(String categoryName) {
        selectByVisibleText(ddWaysToSaveFilter, categoryName);
        log.info("Ways To Save Filter Dropdwon has been selected with the value "+categoryName);
    }


    public List<String> getSelectOptionsWaysToSaveFilter(){

        List<String> selectOptionsAccountDropdown = getAllOptionsInListBox(ddWaysToSaveFilter);
        log.info("The Select Options in Ways To Save Filter are "+selectOptionsAccountDropdown);
        return selectOptionsAccountDropdown;
    }


    @FindBy(css = "#lblAddTopic")
    private WebElement btnAddEfficiency;

    public Boolean isBtnAddEfficiencyVisible() {
        boolean isVisible = isElementVisible(btnAddEfficiency);
        log.info("Add Ways to Save Button visibility status {}" + isVisible);
        return isVisible;
    }
    public String getLblBtnAddEfficiency() {
        String label = getText(btnAddEfficiency);
        log.info("Add Ways to Save Button label Text is " + label);
        return label;
    }

    public void clickBtnAddEfficiency() {
        clickElementUsingJsExecutor(btnAddEfficiency);
        log.info("Add Ways To Save Button on Ways To Save Dashboard Clicked successfully.");
    }

    @FindBy(css ="a[aria-label='Click to kebab menu']")
    private List<WebElement> lstThreeDotsKebabOptions;
    public Boolean isthreeDotsKebabOptionsVisible() {
        boolean isVisible = isAllElementVisible(lstThreeDotsKebabOptions);
        log.info("Three Dots Kebab Options visibility Status on Ways To Save Grid is " + isVisible);
        return isVisible;
    }

    public void clickLstThreeDotsKebabOptions(int index) {
        click(lstThreeDotsKebabOptions.get(index));
        log.info("Three Dots Kebab Options on Ways To Save Grid is clicked successfully at the Index "+index);
    }
    @FindBy(css ="[aria-label='Click to Edit']")
    private WebElement editAction;

    public Boolean isEditActionVisible() {
        boolean isVisible = isElementVisible(editAction);
        log.info("Edit Action visibility Status on Ways To Save Grid is " + isVisible);
        return isVisible;
    }

    public String getLblEditAction() {
        String label = getText(editAction);
        log.info("Edit Action Label on Ways to Save Grid is " + label);
        return label;
    }

    @FindBy(css ="[aria-label='Click to Delete']")
    private WebElement deleteAction;

    public Boolean isDeleteActionVisible() {
        boolean isVisible = isElementVisible(editAction);
        log.info("Delete Action visibility Status on Ways To Save Grid is " + isVisible);
        return isVisible;
    }

    public String getLblDeleteAction() {
        String label = getText(deleteAction);
        log.info("Delete Action Label on Ways to Save Grid is " + label);
        return label;
    }

    @FindBy(css ="#WaysToSaveReport tbody tr td:nth-child(2)>a")
    private List<WebElement> lstStatusColumnData;

    public List<String> getLstStatusColumnData() {
        List<String> label = getAllElementsTextInList(lstStatusColumnData);
        log.info("Status Column Data on Ways To Save Grid is " + label);
        return label;
    }

    @FindBy(css ="#WaysToSaveReport tbody tr td:nth-child(4)>div>a")
    private List<WebElement> lstWebEleWaysToSaveName;

    public List<WebElement> getLstWebEleWaysToSaveName() {
        return lstWebEleWaysToSaveName;
    }


    public void clickKebabOptionForWaysToSaveName(String name){
        WebElement ele =getWebElement(By.xpath("//a[text()='"+name+"']/preceding::a[@aria-label='Click to kebab menu'][1]"));
        clickElementUsingJsExecutor(ele);
    }

    public void clickEditOptionForWaysToSaveName(String name){
        WebElement ele =getWebElement(By.xpath("//a[text()='"+name+"']/preceding::a[@aria-label='Click to Edit'][1]"));
        clickElementUsingJsExecutor(ele);
    }

    public void clickDeleteOptionForWaysToSaveName(String name){
        WebElement ele =getWebElement(By.xpath("//a[text()='"+name+"']/preceding::i[@aria-label='Click to Delete'][1]"));
        clickElementUsingJsExecutor(ele);
    }

    public void clickNameForWaysToSave(String name){
        WebElement ele =getWebElement(By.xpath("//table[@id='WaysToSaveReport']/tbody/tr/td[4]/div/a[contains(text(),'"+name+"')]"));
        clickElementUsingJsExecutor(ele);
    }

    public WebElement getWebElementNameForWaysToSave(String name){
        WebElement ele =getWebElement(By.xpath("//table[@id='WaysToSaveReport']/tbody/tr/td[4]/div/a[contains(text(),'"+name+"')]"));
        return ele;
    }

    public void clickLnkStatusForWaysToSaveName(String name){
        WebElement ele =getWebElement(By.xpath("//a[text()='"+name+"']/preceding::a[1]"));
        clickElementUsingJsExecutor(ele);
        log.info("Clicked on the Status Link for the Topic Name "+name+" on Ways To Save Grid.");
    }

    public String getStatusForWaysToSaveName(String name){
        WebElement ele =getWebElement(By.xpath("//a[text()='"+name+"']/preceding::a[1]"));
        String status = getText(ele);
        log.info("Topic Name has the "+status+" status on the Ways To Save Grid.");
        return status;
    }

    public String getColorForInactiveStatusWaysToSaveName(String name){
        WebElement ele =getWebElement(By.xpath("//a[text()='"+name+"']/preceding::a[1]"));
        String value = ele.getCssValue("background-color");
        return value;
    }

    public String getColorForActiveStatusWaysToSaveName(String name){
        WebElement ele =getWebElement(By.xpath("//a[text()='"+name+"']/preceding::a[1]/span"));
        String value = ele.getCssValue("background-color");
        return value;
    }

    @FindBy(css = "#popuptitle")
    private WebElement addWaysToSavePopupTitle;
    public Boolean isAddWaysToSavePopupTitleVisible() {
        boolean isVisible = isElementVisible(addWaysToSavePopupTitle);
        log.info("Add Ways To Save Popup Title visibility status is " + isVisible);
        return isVisible;
    }

    public String getLblAddWaysToSavePopupTitle() {
        String label = getText(addWaysToSavePopupTitle);
        log.info("Add Ways To Save Popup Title Label is" + label);
        return label;
    }


    @FindBy(xpath="(//div[@class='eff_cat_space']/label)[1]")
    private WebElement lblCategory;
    public Boolean isLblCategoryVisible() {
        boolean isVisible = isElementVisible(lblCategory);
        log.info("Category Label on Add Ways To Save Popup visibility status is " + isVisible);
        return isVisible;
    }

    public String getLblCategory() {
        String label = getText(lblCategory);
        log.info("Category Label Text on Add Ways To Save Popup is " + label);
        return label;
    }

    @FindBy(css = "#ddlCategory")
    private WebElement ddlCategory;

    public Boolean isDdlCategoryVisible() {
        boolean isVisible = isElementVisible(ddlCategory);
        log.info("Category Dropdown on Add Ways To Save Popup visibility status is " + isVisible);
        return isVisible;
    }
    public List<String> getSelectOptionsCategory(){
        List<String> selectOptions = getAllOptionsInListBox(ddlCategory);
        log.info("The Select Options in Category Dropdown Add Ways To Save Popup are "+selectOptions);
        return selectOptions;
    }
    public String getSelectedOptionCategory() {
        String optionSelected =getSelectedValueInDropBox(ddlCategory);
        log.info("Selected option in the Ways To Save Filter is "+optionSelected);
        return optionSelected;
    }

    public void selectCategoryOnAddWaysToSavePopup(String categoryName) {
        selectByVisibleText(ddlCategory, categoryName);
        pause(500);
        log.info("Category dropdown on Add Ways To Save Popup is selected with the value "+categoryName);
    }

    @FindBy(xpath="(//div[@class='eff_cat_space']/label)[2]")
    private WebElement lblMeterType;
    public Boolean isLblMeterTypeVisible() {
        boolean isVisible = isElementVisible(lblMeterType);
        log.info("Meter Type Label on Add Ways To Save Popup visibility status is " + isVisible);
        return isVisible;
    }

    public String getLblMeterType() {
        String label = getText(lblMeterType);
        log.info("Meter Type Label Text on Add Ways To Save Popup is " + label);
        return label;
    }

    @FindBy(css = "#ddlServiceType")
    private WebElement ddlMeterType;

    public Boolean isDdlMeterTypeVisible() {
        boolean isVisible = isElementVisible(ddlMeterType);
        log.info("MeterType Dropdown on Add Ways To Save Popup visibility status is " + isVisible);
        return isVisible;
    }
    public List<String> getSelectOptionsMeterType(){
        List<String> selectOptions = getAllOptionsInListBox(ddlMeterType);
        log.info("The Select Options in MeterType Dropdown Add Ways To Save Popup are "+selectOptions);
        return selectOptions;
    }
    public String getSelectedOptionMeterType() {
        String optionSelected =getSelectedValueInDropBox(ddlMeterType);
        log.info("Selected option in the MeterType Filter is "+optionSelected);
        return optionSelected;
    }

    public void selectMeterTypeOnAddWaysToSavePopup(String categoryName) {
        selectByVisibleText(ddlMeterType, categoryName);
        pause(500);
        log.info("MeterType dropdown on Add Ways To Save Popup is selected with the value "+categoryName);
    }
    @FindBy(xpath="(//div[@class='eff_cat_space']/label)[3]")
    private WebElement lblAccountType;

    public Boolean isLblAccountTypeVisible() {
        boolean isVisible = isElementVisible(lblAccountType);
        log.info("Account Type Label on Add Ways To Save Popup visibility status is " + isVisible);
        return isVisible;
    }

    public String getLblAccountType() {
        String label = getText(lblAccountType);
        log.info("Account Type Label Text on Add Ways To Save Popup is " + label);
        return label;
    }

    @FindBy(css = ".modal-body button.multiselect")
    private WebElement ddlAccountType;
    public Boolean isDdlAccountTypeVisible() {
        boolean isVisible = isElementVisible(ddlAccountType);
        log.info("AccountType Dropdown on Add Ways To Save Popup visibility status is " + isVisible);
        return isVisible;
    }

    public String getSelectedOptionAccountType() {
        String optionSelected =getText(ddlAccountType);
        log.info("Selected option in the AccountType Filter is "+optionSelected);
        return optionSelected;
    }

    public void clickAccountTypeDropdown(){
        clickElementUsingJsExecutor(ddlAccountType);
        log.info("Clicked on AccountType Dropdown on Add Ways To Save Popup");
    }

    public String getAttributeDdlAccountType() {
        String value = getAttribute(ddlAccountType,"title");
        log.info("AccountType Dropdown Attribute Value on Ways To Save Popup " + value);
        return value;
    }




    @FindBy(xpath = "(//li/a/label/input)[1]")
    private WebElement ddlAcctType;
    @FindBy(xpath = "//ul[contains(@class,'multiselect')]/li/a/label")
    private WebElement ddlAllAccountTypes;
    @FindBy(css = ".modal-body ul.multiselect-container li:nth-child(3) a label")
    private WebElement optionResidentialAccountTyp;

    public Boolean isOptionResidentialAccountTypVisible() {
        boolean isVisible = isElementVisible(optionResidentialAccountTyp);
        log.info("Residential AccountType Dropdown value on Add Ways To Save Popup visibility status is " + isVisible);
        return isVisible;
    }
    public String getLblOptionResidentialAccountTyp() {
        String label = getText(optionResidentialAccountTyp);
        log.info("Residential AccountType Dropdown Label Value on Add Ways To Save Popup " + label);
        return label;
    }

    public void selectOptResAccountType(){
        click(optionResidentialAccountTyp);
        pause(500);
        log.info("Clicked on Residential AccountType Dropdown on Add Ways To Save Popup");
    }
    @FindBy(css = ".modal-body ul.multiselect-container li:nth-child(1) a label")
    private WebElement optionCommercialAccountTyp;
    public Boolean isOptionCommercialAccountTypVisible() {
        boolean isVisible = isElementVisible(optionCommercialAccountTyp);
        log.info("Commercial AccountType Dropdown value on Add Ways To Save Popup visibility status is " + isVisible);
        return isVisible;
    }
    public String getLblOptionCommercialAccountTyp() {
        String label = getText(optionCommercialAccountTyp);
        log.info("Commercial AccountType Dropdown Label Value on Add Ways To Save Popup " + label);
        return label;
    }

    public void selectOptCommAccountType(){
        click(optionCommercialAccountTyp);
        pause(500);
        log.info("Clicked on Commercial AccountType Dropdown on Add Ways To Save Popup");
    }
    @FindBy(css = ".modal-body ul.multiselect-container li:nth-child(2) a label")
    private WebElement optionMultifamilyAccountTyp;
    public Boolean isOptionMultiFamilyAccountTypVisible() {
        boolean isVisible = isElementVisible(optionMultifamilyAccountTyp);
        log.info("Multi-Family AccountType Dropdown value on Add Ways To Save Popup visibility status is " + isVisible);
        return isVisible;
    }
    public String getLblOptionMultiFamilyAccountTyp() {
        String label = getText(optionMultifamilyAccountTyp);
        log.info("Multi-Family AccountType Dropdown Label Value on Add Ways To Save Popup " + label);
        return label;
    }

    public void selectOptMultiFamilyAccountType(){
        click(optionMultifamilyAccountTyp);
        pause(500);
        log.info("Clicked on MultiFamily AccountType Dropdown on Add Ways To Save Popup");
    }
    @FindBy(css = "#txtSavingTips")
    private WebElement txtSavingsTips;
    @FindBy(css = "#fileUpload")
    private List<WebElement> btnUploadFile;

    public Boolean isbtnUploadFileVisible() {
        boolean isVisible = isElementVisibleAlt(btnUploadFile);
        log.info("Upload File Button on Add Ways To Save Popup visibility status is " + isVisible);
        return isVisible;
    }

    public void addAttachmentToChooseFile(String value) {
        sendKeysWithoutCheckingVisibility(btnUploadFile.get(0), value);
        log.info("Choose File Button added successfully with File Value " + value);
    }
    @FindBy(css = "#lblFileupload")
    private WebElement lblBtnUploadFile;

    public String getLblBtnUploadFile() {
        String label = getText(lblBtnUploadFile);
        log.info("Upload File Button Label on Add Ways To Save Popup " + label);
        return label;
    }


    @FindBy(xpath = "//label[text()='Description']/following::div[@class='note-editing-area']/div[@class='note-editable']")
    private WebElement txtArea;
    @FindBy(css = "#BtnAdd")
    private WebElement btnAddOnAddWaysToSavePopup;

    public void waitForBtnAddOnAddWaysToSavePopupToBeVisible(){
        waitForElementToBeVisible(btnAddOnAddWaysToSavePopup);
        log.info("Add Button is Visible on the Add Ways To Save Popup page.");
    }
    public Boolean isBtnAddOnAddWaysToSavePopupVisible() {
        boolean isVisible = isElementVisible(btnAddOnAddWaysToSavePopup);
        log.info("Add Button on Add Ways To Save Popup visibility status is " + isVisible);
        return isVisible;
    }

    @FindBy(xpath = "//div[@id='PopupAddTopic']/div/div[@class='modal-content']")
    private WebElement addOrUpdateWaysToSavePopup;

    public void waitForAddOrUpdatePopupToInvisible() {
        pause(500);
        expWaitForEleAttributeToChange(addOrUpdateWaysToSavePopup,"aria-hidden","true");
        log.info("Ways To Save Add or Update Popup is now disappeared");
    }

    public void waitForAddOrUpdateWaysToSavePopupToVisible() {
        pause(500);
        expWaitForEleAttributeToChange(addOrUpdateWaysToSavePopup,"aria-modal","true");
        log.info("Ways To Save Add or Update Popup Popup is now Appeared");
    }



    public void clickBtnAddOnAddWaysToSavePopup() {
        clickElementUsingJsExecutor(btnAddOnAddWaysToSavePopup);
        log.info("Add Button on Add Ways To Save Popup clicked successfully.");
    }


    @FindBy(xpath="(//div[@class='eff_cat_space']/label)[4]")
    private WebElement lblInternal;

    public Boolean isLblInternalVisible() {
        boolean isVisible = isElementVisible(lblInternal);
        log.info("Internal Label on Add Ways To Save Popup visibility status is " + isVisible);
        return isVisible;
    }

    public String getLblInternal() {
        String label = getText(lblInternal);
        log.info("Internal Label Text on Add Ways To Save Popup is " + label);
        return label;
    }

    @FindBy(css = "#chk_internal")
    private WebElement chkbxInternal;

    public Boolean isCheckboxInternalVisible() {
        boolean isVisible = isElementVisible(chkbxInternal);
        log.info("Internal Checkbox on Add Ways To Save Popup visibility status is " + isVisible);
        return isVisible;
    }
    public void chkCheckBoxInternal() {
        check(chkbxInternal);
        log.info("Internal checkbox is checked successfully.");
    }

    public void unchkCheckBoxInternal() {
        unCheck(chkbxInternal);
        log.info("Internal checkbox is unchecked successfully.");
    }

    @FindBy(css="#lblExtrnalLnks")
    private WebElement lblExternalLink;

    public Boolean isLblExternalLinkVisible() {
        boolean isVisible = isElementVisible(lblExternalLink);
        log.info("External Link Label on Add Ways To Save Popup visibility status is " + isVisible);
        return isVisible;
    }

    public String getLblExternalLink() {
        String label = getText(lblExternalLink);
        log.info("External Link Label Text on Add Ways To Save Popup is " + label);
        return label;
    }

    @FindBy(css = "#txtExtrnalLnks")
    private WebElement txtBoxExternalLink;

    public Boolean isTxtBoxExternalLinkVisible() {
        boolean isVisible = isElementVisible(txtBoxExternalLink);
        log.info("External Link Textbox on Add Ways To Save Popup visibility status is " + isVisible);
        return isVisible;
    }

    public void clearTxtBoxExternalLink() {
        clear(txtBoxExternalLink);
        log.info("External Link Textbox field is cleared.");
    }

    public void populateTxtBoxExternalLink(String value) {
        sendKeys(txtBoxExternalLink, value);
        log.info("External Link TextBox populated successfully with Value " + value);
    }

    @FindBy(css = "#lblAmountsaving")
    private WebElement lblAnnualSaving;

    public Boolean isLblAnnualSavingVisible() {
        boolean isVisible = isElementVisible(lblAnnualSaving);
        log.info("Annual Saving Label on Add Ways To Save Popup visibility status is " + isVisible);
        return isVisible;
    }

    public String getLblAnnualSaving() {
        String label = getText(lblAnnualSaving);
        log.info("Annual Saving Label Text on Add Ways To Save Popup is " + label);
        return label;
    }


    @FindBy(css = "input.txtAmount")
    private WebElement txtBoxAnnualSaving;

    public WebElement getWebEleTxtBoxAnnualSaving(){
        return txtBoxAnnualSaving;
    }

    public Boolean isTxtBoxAnnualSavingVisible() {
        boolean isVisible = isElementVisible(txtBoxAnnualSaving);
        log.info("Annual Saving TextBox on Add Ways To Save Popup visibility status is " + isVisible);
        return isVisible;
    }

    public void clearTxtBoxAnnualSaving() {
        clear(txtBoxAnnualSaving);
        log.info("Annual Saving TextBox field is cleared.");
    }

    public void populateTxtBoxAnnualSaving(String value) {
        sendKeys(txtBoxAnnualSaving, value);
        log.info("Annual Saving TextBox populated successfully with Value " + value);
    }

    public String getValueTxtBoxAnnualSaving() {
        String label = getAttribute(txtBoxAnnualSaving,"value");
        log.info("Annual Saving TextBox Value on Add Ways To Save Popup is " + label);
        return label;
    }


    @FindBy(css = "input[title='Incentive Rate']")
    private WebElement txtIncentiveRate;
    @FindBy(css = "#WaysToSaveReport th")
    private List<WebElement> waysToSaveTableHeaders;

    public List<WebElement> getWebElementWaysToSaveTableHeaders(){
        return waysToSaveTableHeaders;
    }

    public Boolean isWaysToSaveTableHeadersVisible() {
        boolean isVisible = isAllElementVisible(waysToSaveTableHeaders);
        log.info("Choose Button visibility status {}" + isVisible);
        return isVisible;
    }

    public List<String> getWaysToSaveGridHeaders(){
        List<String> allElementsTextInList = getAllElementsTextInList(waysToSaveTableHeaders);
        log.info("Ways To Save Grid Headers are " + allElementsTextInList);
        return allElementsTextInList;
    }

    @FindBy(css = ".dataTables_info")
    private WebElement lblShowingEntries;
    public Boolean isLblShowingEntriesVisible() {
        boolean isVisible = isElementVisible(lblShowingEntries);
        log.info("Showing Entries Label visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLblShowingEntries() {
        String label = getText(lblShowingEntries);
        log.info("Showing Entries Label Text is " + label);
        return label;
    }

    @FindBy(xpath = "//div[@class='w2ui-box']")
    private WebElement alertsuccessfullyBody;
    @FindBy(xpath = "//div[@class='w2ui-popup-buttons']")
    private WebElement alertsuccessfullyOkBtn;
    @FindBy(css = "#ContentPlaceHolder1_rightpanel_txtAmount")
    private WebElement txtSavingTipsValue;
    @FindBy(xpath = "//*[@id='tbDraggable']/tr")
    private WebElement rowsEfficiencyTable;
    @FindBy(xpath = "//*[@id='tbDraggable']/tr/td[4]")
    private WebElement efficiencyName;
    @FindBy(css = "#PopupAddTopic > div > div > div.modal-header > button")
    private WebElement closePopupEfficiency;
    @FindBy(css = "input[aria-label='Amount saving listed/Yrs']")
    private WebElement txtAnnualSavingOnUpdatePage;

    @FindBy(css = "div#WaystosavePopup")
    private WebElement waysToSaveViewDetailsPopup;
    public void waitForWaysToSaveViewPopupToInvisible() {
        pause(500);
        expWaitForEleAttributeToChange(waysToSaveViewDetailsPopup,"aria-hidden","true");
        log.info("Ways To Save View Details Popup is now disappeared");
    }

    public void waitForWaysToSaveViewPopupToVisible() {
        pause(500);
        expWaitForEleAttributeToChange(waysToSaveViewDetailsPopup,"aria-modal","true");
        log.info("Ways To Save View Details Popup is now Appeared");
    }


    @FindBy(css = "#WaystosavePopup div.modal-dialog.popup_area h4")
    private WebElement waysToSaveTitleViewPopup;


    public Boolean isWaysToSaveTitleViewPopupVisible() {
        boolean isVisible = isElementVisible(waysToSaveTitleViewPopup);
        log.info("Ways To Save Title on View Popup visibility status {}" + isVisible);
        return isVisible;
    }

    public String getWaysToSaveTitleViewPopup() {
        String label = getText(waysToSaveTitleViewPopup);
        log.info("Ways To Save Title on View Popup is " + label);
        return label;
    }

    @FindBy(css = "#lblAdded")
    private WebElement lblAddedWaysToSaveViewPopup;

    public Boolean isLblAddedWaysToSaveViewPopupVisible() {
        boolean isVisible = isElementVisible(lblAddedWaysToSaveViewPopup);
        log.info("Added Count Label on View Popup visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLblAddedWaysToSaveViewPopup() {
        String label = getText(lblAddedWaysToSaveViewPopup);
        log.info("Added Count Label on View Popup is " + label);
        return label;
    }


    @FindBy(css = "#lbl_added")
    private WebElement valueAddedWaysToSaveViewPopup;

    public Boolean isValueAddedWaysToSaveViewPopupVisible() {
        boolean isVisible = isElementVisible(valueAddedWaysToSaveViewPopup);
        log.info("Added Count Value on View Popup visibility status {}" + isVisible);
        return isVisible;
    }

    public String getValueAddedWaysToSaveViewPopup() {
        String label = getText(valueAddedWaysToSaveViewPopup);
        log.info("Added Count Value on View Popup is " + label);
        return label;
    }

    @FindBy(css = "#lblView")
    private WebElement lblViewedWaysToSaveViewPopup;

    public Boolean isLblViewedWaysToSaveViewPopupVisible() {
        boolean isVisible = isElementVisible(lblViewedWaysToSaveViewPopup);
        log.info("Viewed Count Label on View Popup visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLblViewedWaysToSaveViewPopup() {
        String label = getText(lblViewedWaysToSaveViewPopup);
        log.info("Viewed Count Label on View Popup is " + label);
        return label;
    }

    @FindBy(css = "#lbl_viewed")
    private WebElement valueViewedWaysToSaveViewPopup;

    public Boolean isValueViewedWaysToSaveViewPopupVisible() {
        boolean isVisible = isElementVisible(valueViewedWaysToSaveViewPopup);
        log.info("Viewed Count Value on View Popup visibility status {}" + isVisible);
        return isVisible;
    }

    public String getValueViewedWaysToSaveViewPopup() {
        String label = getText(valueViewedWaysToSaveViewPopup);
        log.info("Viewed Count Value on View Popup is " + label);
        return label;
    }

    @FindBy(css = "#lblType12")
    private WebElement lblSaveUptoWaysToSaveViewPopup;

    public Boolean isLblSaveUptoWaysToSaveViewPopupVisible() {
        boolean isVisible = isElementVisible(lblSaveUptoWaysToSaveViewPopup);
        log.info("SaveUpto Count Label on View Popup visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLblSaveUptoWaysToSaveViewPopup() {
        String label = getText(lblSaveUptoWaysToSaveViewPopup);
        log.info("SaveUpto Count Label on View Popup is " + label);
        return label;
    }


    @FindBy(css = "span[id*='lbl_type']")
    private WebElement valueSaveUptoWaysToSaveViewPopup;
    public Boolean isValueSaveUptoWaysToSaveViewPopupVisible() {
        boolean isVisible = isElementVisible(valueSaveUptoWaysToSaveViewPopup);
        log.info("SaveUpto Count Value on View Popup visibility status {}" + isVisible);
        return isVisible;
    }

    public String getValueSaveUptoWaysToSaveViewPopup() {
        String label = getText(valueSaveUptoWaysToSaveViewPopup);
        log.info("SaveUpto Count Value on View Popup is " + label);
        return label;
    }
    @FindBy(css = "p#lblDescription")
    private WebElement valueDescriptionWaysToSaveViewPopup;

    public Boolean isValueDescriptionWaysToSaveViewPopupVisible() {
        boolean isVisible = isElementVisible(valueDescriptionWaysToSaveViewPopup);
        log.info("Description Value on View Popup visibility status {}" + isVisible);
        return isVisible;
    }

    public String getValueDescriptionWaysToSaveViewPopup() {
        String label = getText(valueDescriptionWaysToSaveViewPopup);
        log.info("Description Value on View Popup is " + label);
        return label;
    }

    public void waitForValueDescriptionWaysToSaveViewPopupToBeVisible(){
        waitForElementToBeVisible(valueDescriptionWaysToSaveViewPopup);
        log.info("Description Value on View Popup is is visible");
    }

    @FindBy(css = "#WaystosavePopup div.modal-dialog.popup_area #btnClose")
    private WebElement btnCloseXWaysToSaveViewPopup;

    public Boolean isBtnCloseXWaysToSaveViewPopupVisible() {
        boolean isVisible = isElementVisible(btnCloseXWaysToSaveViewPopup);
        log.info("Close X button on View Popup visibility status {}" + isVisible);
        return isVisible;
    }

    public void clickBtnCloseXWaysToSaveViewPopup() {
        clickElementUsingJsExecutor(btnCloseXWaysToSaveViewPopup);
        log.info("Close X Button on View Popup clicked successfully.");
    }

    @FindBy(css = "#WaystosavePopup #img_popimage")
    private WebElement imageWaysToSaveViewPopup;

    public Boolean isImageWaysToSaveViewPopupVisible() {
        boolean isVisible = isElementVisible(imageWaysToSaveViewPopup);
        log.info("Image on View Popup visibility status {}" + isVisible);
        return isVisible;
    }
    @FindBy(css = "#chk_drprogram")
    private WebElement chkbxDRProgram;
    @FindBy(css = ".toast-message")
    private WebElement lblToastMessage;

    @FindBy(xpath = "//label[text()='Image']")
    private WebElement lblImage;
    public Boolean isLblImageVisible() {
        boolean isVisible = isElementVisible(lblImage);
        log.info("Image Field on Add Ways To Save visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLblImage() {
        String label = getText(lblImage);
        log.info("Image Field on Add Ways To Save Label Text is " + label);
        return label;
    }

    @FindBy(css = "#nofile")
    private WebElement attachedFileName;
    public Boolean isAttachedFileNameVisible() {
        boolean isVisible = isElementVisible(attachedFileName);
        log.info("Attached File Name on Add Ways To Save visibility status {}" + isVisible);
        return isVisible;
    }

    public String getAttachedFileName() {
        String label = getText(attachedFileName);
        log.info("Attached File Name on Add Ways To Save is " + label);
        return label;
    }


    @FindBy(css = "#blahimg")
    private WebElement lblUploadFilePreview;
    @FindBy(css = "#lblFileAllowExtension")
    private WebElement lblAllowedFileTypes;
    public Boolean isLblAllowedFileTypesVisible() {
        boolean isVisible = isElementVisible(lblAllowedFileTypes);
        log.info("Allowed File Types Label on Add Ways To Save visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLblAllowedFileTypes() {
        String label = getText(lblAllowedFileTypes);
        log.info("Allowed File Types Label on Add Ways To Save is " + label);
        return label;
    }


    @FindBy(css = "#btnRemoveFile")
    private WebElement btnRemoveOnAddUpdatePopup;

    public Boolean isBtnRemoveOnAddUpdatePopupVisible() {
        boolean isVisible = isElementVisible(btnRemoveOnAddUpdatePopup);
        log.info("Remove Button for attachment on AddUpdate Ways To Save Popup visibility status {}" + isVisible);
        return isVisible;
    }
    public void clickBtnRemoveOnAddUpdatePopup() {
        clickElementUsingJsExecutor(btnCloseAddWaysToSavePopup);
        log.info("Remove Button for attachment on AddUpdate Ways To Save Popup clicked successfully.");
    }


    @FindBy(css = "#PopupAddTopic button.close[aria-label='Click to Close']")
    private WebElement btnCloseAddWaysToSavePopup;
    public Boolean isBtnCloseAddWaysToSavePopupVisible() {
        boolean isVisible = isElementVisible(btnCloseAddWaysToSavePopup);
        log.info("Close Button on Add Ways To Save Popup visibility status {}" + isVisible);
        return isVisible;
    }
    public void clickBtnCloseAddWaysToSavePopup() {
        clickElementUsingJsExecutor(btnCloseAddWaysToSavePopup);
        log.info("Close Button on Add Ways To Save Popup clicked successfully.");
    }
    @FindBy(css = "input#btnProceedTest")
    private WebElement btnYesConfirmationPopup;
    @FindBy(css = "#btnDeleteUser1")
    private WebElement btnYesDeleteConfirmationPopup;

    public void waitForBtnYesDeleteConfirmationPopupToBeVisible(){
        waitForElementToBeVisible(btnYesDeleteConfirmationPopup);
        log.info("Yes button on Delete Confirmation Popup is visible");
    }

    public String getLblBtnYesDeleteConfirmationPopup() {
        String label = getAttribute(btnYesDeleteConfirmationPopup,"value");
        log.info("Label for Yes button on Delete Confirmation Popup is " + label);
        return label;
    }

    public Boolean isBtnYesDeleteConfirmationPopupVisible() {
        boolean isVisible = isElementVisible(btnYesDeleteConfirmationPopup);
        log.info("Yes button on Delete Confirmation Popup visibility status is " + isVisible);
        return isVisible;
    }
    public void clickBtnYesDeleteConfirmationPopup() {
        clickElementUsingJsExecutor(btnYesDeleteConfirmationPopup);
        log.info("Yes button on Delete Confirmation Popup clicked successfully.");
    }

    @FindBy(css = "#PopupDeleteUser_FAQ1 div.modal-body button[class*='back_btn']")
    private WebElement btnCloseDeleteConfirmationPopup;

    public Boolean isBtnCloseDeleteConfirmationPopupVisible() {
        boolean isVisible = isElementVisible(btnCloseDeleteConfirmationPopup);
        log.info("Close button on Delete Confirmation Popup visibility status is " + isVisible);
        return isVisible;
    }
    public String getLblBtnCloseDeleteConfirmationPopup() {
        String label = getText(btnCloseDeleteConfirmationPopup);
        log.info("Label for Yes button on Close Confirmation Popup is " + label);
        return label;
    }
    public void clickBtnCloseDeleteConfirmationPopup() {
        clickElementUsingJsExecutor(btnCloseDeleteConfirmationPopup);
        log.info("Close button on Delete Confirmation Popup clicked successfully.");
    }

    @FindBy(css = "#PopupDeleteUser_FAQ1 div.modal-body p")
    private WebElement messageDeleteConfirmationPopup;

    public Boolean isMessageDeleteConfirmationPopupVisible() {
        boolean isVisible = isElementVisible(messageDeleteConfirmationPopup);
        log.info("Confirmation Message on Delete Confirmation Popup visibility status is " + isVisible);
        return isVisible;
    }
    public String getMessageDeleteConfirmationPopup() {
        String label = getText(messageDeleteConfirmationPopup);
        log.info("Confirmation Message on Close Confirmation Popup is " + label);
        return label;
    }


    @FindBy(css = "#PopupDeleteUser_FAQ1 h4.modal-title")
    private WebElement headingDeleteConfirmationPopup;

    public Boolean isHeadingDeleteConfirmationPopupVisible() {
        boolean isVisible = isElementVisible(headingDeleteConfirmationPopup);
        log.info("Heading on Delete Confirmation Popup visibility status is " + isVisible);
        return isVisible;
    }
    public String getHeadingDeleteConfirmationPopup() {
        String label = getText(headingDeleteConfirmationPopup);
        log.info("Heading Label on Delete Confirmation Popup is " + label);
        return label;
    }


    @FindBy(css="#PopupChangeStatus_Wts")
    private WebElement changeStatusConfirmationPopup;

    public WebElement getWebEleChangeStatusConfirmationPopup(){
        return changeStatusConfirmationPopup;
    }

    public Boolean isChangeStatusConfirmationPopupVisible() {
        boolean isVisible = isElementVisible(changeStatusConfirmationPopup);
        log.info("Change Status Confirmation Popup visibility status is " + isVisible);
        return isVisible;
    }

    @FindBy(css="#PopupChangeStatus_Wts .modal-dialog h4")
    private WebElement headingChangeStatusConfirmationPopup;

    public Boolean isHeadingChangeStatusConfirmationPopupVisible() {
        boolean isVisible = isElementVisible(headingChangeStatusConfirmationPopup);
        log.info("Heading on Change Status Confirmation Popup visibility status is " + isVisible);
        return isVisible;
    }
    public String getHeadingChangeStatusConfirmationPopup() {
        String label = getText(headingChangeStatusConfirmationPopup);
        log.info("Heading Label on Change Status Confirmation Popup is " + label);
        return label;
    }
    @FindBy(css="#PopupChangeStatus_Wts .modal-dialog button.close")
    private WebElement closeXButtonChangeStatusConfirmationPopup;

    public Boolean isCloseXButtonChangeStatusConfirmationPopupVisible() {
        boolean isVisible = isElementVisible(closeXButtonChangeStatusConfirmationPopup);
        log.info("Close X Button on Change Status Confirmation Popup visibility status is " + isVisible);
        return isVisible;
    }
    public String getLblCloseXButtonChangeStatusConfirmationPopup() {
        String label = getText(closeXButtonChangeStatusConfirmationPopup);
        log.info("Label for Close X Button on Change Status Confirmation Popup is " + label);
        return label;
    }

    @FindBy(css="#PopupChangeStatus_Wts .modal-body p")
    private WebElement messageChangeStatusConfirmationPopup;

    public Boolean isMessageChangeStatusConfirmationPopupVisible() {
        boolean isVisible = isElementVisible(messageChangeStatusConfirmationPopup);
        log.info("Confirmation Message on Change Status Confirmation Popup visibility status is " + isVisible);
        return isVisible;
    }
    public String getMessageChangeStatusConfirmationPopupPopup() {
        String label = getText(messageChangeStatusConfirmationPopup);
        log.info("Confirmation Message on Change Status Confirmation Popup is " + label);
        return label;
    }

    @FindBy(css="#PopupChangeStatus_Wts .modal-body button[class*='back_btn']")
    private WebElement btnCloseChangeStatusConfirmationPopup;

    public Boolean isBtnCloseChangeStatusConfirmationPopupVisible() {
        boolean isVisible = isElementVisible(btnCloseChangeStatusConfirmationPopup);
        log.info("Close Button on Change Status Confirmation Popup visibility status is " + isVisible);
        return isVisible;
    }
    public String getLblCloseBtnChangeStatusConfirmationPopup() {
        String label = getText(btnCloseChangeStatusConfirmationPopup);
        log.info("Label for Close Button on Change Status Confirmation Popup is " + label);
        return label;
    }

    public void clickBtnCloseChangeStatusConfirmationPopup() {
        clickElementUsingJsExecutor(btnCloseChangeStatusConfirmationPopup);
        log.info("Close button on Change Status Confirmation Popup clicked successfully.");
    }

    @FindBy(css="#PopupChangeStatus_Wts .modal-body #btnProceedTest")
    private WebElement btnYesChangeStatusConfirmationPopup;

    public Boolean isBtnYesChangeStatusConfirmationPopupVisible() {
        boolean isVisible = isElementVisible(btnYesChangeStatusConfirmationPopup);
        log.info("Yes Button on Change Status Confirmation Popup visibility status is " + isVisible);
        return isVisible;
    }
    public String getLblYesBtnCloseChangeStatusConfirmationPopup() {
        String label = getAttribute(btnYesChangeStatusConfirmationPopup,"value");
        log.info("Label for Yes Button on Change Status Confirmation Popup is " + label);
        return label;
    }

    public void waitForBtnYesChangeStatusConfirmationPopupToBeVisible(){
        waitForElementToBeVisible(btnYesChangeStatusConfirmationPopup);
        log.info("Yes button on Change Status Confirmation Popup is visible");
    }

    public void clickBtnYesChangeStatusConfirmationPopup() {
        clickElementUsingJsExecutor(btnYesChangeStatusConfirmationPopup);
        log.info("Yes button on Change Status Confirmation Popup clicked successfully.");
    }



    @FindBy(css = ".masterHdng")
    private WebElement waysToSavePageHeading;

    public Boolean isWaysToSavePageHeadingVisible() {
        boolean isVisible = isElementVisible(waysToSavePageHeading);
        log.info("Ways To Save page Heading visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLblWaysToSavePageHeading() {
        String label = getText(waysToSavePageHeading);
        log.info("Ways To Save page Heading label is " + label);
        return label;
    }

    @FindBy(css = "#PopupAddTopic .tip_title_wrapper_box > label.AddUserContentLabel:nth-of-type(1)")
    private WebElement lblTopicNameEnglish;
    public Boolean isLblTopicNameEnglishVisible() {
        boolean isVisible = isElementVisible(lblTopicNameEnglish);
        log.info("Topic Name English Label visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLblTopicNameEnglish() {
        String label = getText(lblTopicNameEnglish);
        log.info("Topic Name English Label Text is " + label);
        return label;
    }
    @FindBy(css = "#PopupAddTopic .tip_title_wrapper_box > label.AddUserContentLabel:nth-of-type(2)")
    private WebElement lblDescEnglish;

    public Boolean isLblDescEnglishVisible() {
        boolean isVisible = isElementVisible(lblDescEnglish);
        log.info("Description Name English Label visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLblDescEnglish() {
        String label = getText(lblDescEnglish);
        log.info("Description Name English Label Text is " + label);
        return label;
    }
    @FindBy(css = "#PopupAddTopic .tip_title_wrapper_box > label.AddUserContentLabel:nth-of-type(3)")
    private WebElement lblTopicNameSpanish;

    public Boolean isLblTopicNameSpanishVisible() {
        boolean isVisible = isElementVisible(lblTopicNameSpanish);
        log.info("Topic Name Spanish Label visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLblTopicNameSpanish() {
        String label = getText(lblTopicNameSpanish);
        log.info("Topic Name Spanish Label Text is " + label);
        return label;
    }
    @FindBy(css = "#PopupAddTopic .tip_title_wrapper_box > label.AddUserContentLabel:nth-of-type(4)")
    private WebElement lblDescSpanish;

    public Boolean isLblDescSpanishVisible() {
        boolean isVisible = isElementVisible(lblDescSpanish);
        log.info("Description Name Spanish Label visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLblDescSpanish() {
        String label = getText(lblDescSpanish);
        log.info("Description Name Spanish Label Text is " + label);
        return label;
    }
    @FindBy(css = "#PopupAddTopic .tip_title_wrapper_box > label.AddUserContentLabel:nth-of-type(5)")
    private WebElement lblTopicNameFrench;
    public Boolean isLblTopicNameFrenchVisible() {
        boolean isVisible = isElementVisible(lblTopicNameFrench);
        log.info("Topic Name French Label visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLblTopicNameFrench() {
        String label = getText(lblTopicNameFrench);
        log.info("Topic Name French Label Text is " + label);
        return label;
    }
    @FindBy(css = "#PopupAddTopic .tip_title_wrapper_box > label.AddUserContentLabel:nth-of-type(6)")
    private WebElement lblDescFrench;

    public Boolean isLblDescFrenchVisible() {
        boolean isVisible = isElementVisible(lblDescFrench);
        log.info("Description Name French Label visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLblDescFrench() {
        String label = getText(lblDescFrench);
        log.info("Description Name French Label Text is " + label);
        return label;
    }

    @FindBy(css = "#txtQuestionEN")
    private WebElement txtBoxTopicNameEnglish;
    public Boolean isTxtBoxTopicNameEnglishVisible() {
        boolean isVisible = isElementVisible(txtBoxTopicNameEnglish);
        log.info("Topic Name English TextBox visibility status {}" + isVisible);
        return isVisible;
    }

    public void clearTxtBoxTopicNameEnglish() {
        clear(txtBoxTopicNameEnglish);
        log.info("Topic Name English TextBox is cleared.");
    }
    public void populateTxtBoxTopicNameEnglish(String value) {
        sendKeys(txtBoxTopicNameEnglish, value);
        log.info("Topic Name English TextBox populated successfully with Value " + value);
    }

    public String getValueTxtBoxTopicNameEnglish() {
        String label = getAttribute(txtBoxTopicNameEnglish,"value");
        log.info("Topic Name English TextBox Value on Add Ways To Save Popup is " + label);
        return label;
    }


    @FindBy(xpath = "//div[@id='summernoteEnglish']/following::div[1]/div[@class='note-editing-area']/div[@class='note-editable']")
    private WebElement txtBoxDescEnglish;

    public Boolean isTxtBoxDescEnglishVisible() {
        boolean isVisible = isElementVisible(txtBoxDescEnglish);
        log.info("Description English TextBox visibility status {}" + isVisible);
        return isVisible;
    }

    public void clearTxtBoxDescEnglish() {
        clear(txtBoxDescEnglish);
        log.info("Description English TextBox is cleared.");
    }
    public void populateTxtBoxDescEnglish(String value) {
        sendKeys(txtBoxDescEnglish, value);
        log.info("Description English TextBox populated successfully with Value " + value);
    }

    public String getValueTxtBoxDescEnglish() {
        String label = getText(txtBoxDescEnglish);
        log.info("Description English TextBox Value on Add Ways To Save Popup is " + label);
        return label;
    }


    @FindBy(css = "#txtQuestionSP")
    private WebElement txtBoxTopicNameSpanish;

    public Boolean isTxtBoxTopicNameSpanishVisible() {
        boolean isVisible = isElementVisible(txtBoxTopicNameSpanish);
        log.info("Topic Name Spanish TextBox visibility status {}" + isVisible);
        return isVisible;
    }

    public void clearTxtBoxTopicNameSpanish() {
        clear(txtBoxTopicNameSpanish);
        log.info("Topic Name Spanish TextBox is cleared.");
    }
    public void populateTxtBoxTopicNameSpanish(String value) {
        sendKeys(txtBoxTopicNameSpanish, value);
        log.info("Topic Name Spanish TextBox populated successfully with Value " + value);
    }
    @FindBy(xpath = "//div[@id='summernoteSpanise']/following::div[1]/div[@class='note-editing-area']/div[@class='note-editable']")
    private WebElement txtBoxDescSpanish;

    public Boolean isTxtBoxDescSpanishVisible() {
        boolean isVisible = isElementVisible(txtBoxDescSpanish);
        log.info("Description Spanish TextBox visibility status {}" + isVisible);
        return isVisible;
    }

    public void clearTxtBoxDescSpanish() {
        clear(txtBoxDescSpanish);
        log.info("Description Spanish TextBox is cleared.");
    }
    public void populateTxtBoxDescSpanish(String value) {
        sendKeys(txtBoxDescSpanish, value);
        log.info("Description Spanish TextBox populated successfully with Value " + value);
    }
    @FindBy(css = "#spanTxt1")
    private WebElement lblMaxCharLengthSpanish;
    public Boolean isLblMaxCharLengthSpanishVisible() {
        boolean isVisible = isElementVisible(lblMaxCharLengthSpanish);
        log.info("Max Character Length Label Spanish visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLblMaxCharLengthSpanish() {
        String label = getText(lblMaxCharLengthSpanish);
        log.info("Max Character Length Label Spanish Label Text is " + label);
        return label;
    }
    @FindBy(css = "#txtQuestionFR")
    private WebElement txtBoxTopicNameFrench;

    public Boolean isTxtBoxTopicNameFrenchVisible() {
        boolean isVisible = isElementVisible(txtBoxTopicNameFrench);
        log.info("Topic Name French TextBox visibility status {}" + isVisible);
        return isVisible;
    }

    public void clearTxtBoxTopicNameFrench() {
        clear(txtBoxTopicNameFrench);
        log.info("Topic Name French TextBox is cleared.");
    }
    public void populateTxtBoxTopicNameFrench(String value) {
        sendKeys(txtBoxTopicNameFrench, value);
        log.info("Topic Name French TextBox populated successfully with Value " + value);
    }
    @FindBy(xpath = "//div[@id='SummernoteFrench']/following::div[1]/div[@class='note-editing-area']/div[@class='note-editable']")
    private WebElement txtBoxDescFrench;
    public Boolean isTxtBoxDescFrenchVisible() {
        boolean isVisible = isElementVisible(txtBoxDescFrench);
        log.info("Description French TextBox visibility status {}" + isVisible);
        return isVisible;
    }

    public void clearTxtBoxDescFrench() {
        clear(txtBoxDescFrench);
        log.info("Description French TextBox is cleared.");
    }
    public void populateTxtBoxDescFrench(String value) {
        sendKeys(txtBoxDescFrench, value);
        log.info("Description French TextBox populated successfully with Value " + value);
    }
    @FindBy(css = "#spanTxt2")
    private WebElement lblMaxCharLengthFrench;
    public Boolean isLblMaxCharLengthFrenchVisible() {
        boolean isVisible = isElementVisible(lblMaxCharLengthFrench);
        log.info("Max Character Length Label French visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLblMaxCharLengthFrench() {
        String label = getText(lblMaxCharLengthFrench);
        log.info("Max Character Length Label French Label Text is " + label);
        return label;
    }


    @FindBy(css = "#spanTxt")
    private WebElement lblMaxCharLengthEnglish;
    public Boolean isLblMaxCharLengthEnglishVisible() {
        boolean isVisible = isElementVisible(lblMaxCharLengthEnglish);
        log.info("Max Character Length Label English visibility status {}" + isVisible);
        return isVisible;
    }

    public String getLblMaxCharLengthEnglish() {
        String label = getText(lblMaxCharLengthEnglish);
        log.info("Max Character Length Label English Label Text is " + label);
        return label;
    }


    @FindBy(css = "#WaysToSaveReport tbody td:nth-child(4) div a")
    private List<WebElement> lstWaysToSaveName;

    public Boolean isLstWaysToSaveNameVisible() {
        boolean isVisible = isAllElementVisible(lstWaysToSaveName);
        log.info("Ways To Save Name on Grid Table visibility status {}" + isVisible);
        return isVisible;
    }

    public String getFirstWaysToSaveNameOnGrid(){
        String label = getText(lstWaysToSaveName.get(0));
        log.info("Name for the First Ways To Save present on Grid is " + label);
        return label;
    }

    @FindBy(css = "#WaysToSaveReport_previous")
    private WebElement btnPreviousPagination;

    public Boolean isBtnPreviousPaginationVisible() {
        boolean isVisible = isElementVisible(btnPreviousPagination);
        log.info("Previous Pagination Button visibility status " + isVisible);
        return isVisible;
    }

    public String getLblBtnPreviousPagination() {
        String label = getText(btnPreviousPagination);
        log.info("Previous Pagination Button Label is " + label);
        return label;
    }

    public void clickBtnPreviousPagination() {
        clickElementUsingJsExecutor(btnPreviousPagination);
        log.info("Previous Pagination Button clicked successfully.");
    }

    @FindBy(css = "#WaysToSaveReport_next")
    private WebElement btnNextPagination;

    public Boolean isBtnNextPaginationVisible() {
        boolean isVisible = isElementVisible(btnNextPagination);
        log.info("Next Pagination Button visibility status " + isVisible);
        return isVisible;
    }

    public String getLblBtnNextPagination() {
        String label = getText(btnNextPagination);
        log.info("Next Pagination Button Label is " + label);
        return label;
    }

    public void clickBtnNextPagination() {
        clickElementUsingJsExecutor(btnNextPagination);
        log.info("Next Pagination Button clicked successfully.");
    }

    public void waitForBtnNextPaginationToBeVisible(){
        waitForElementToBeVisible(btnNextPagination);
        log.info("Next Button is Visible on the Ways To Save page.");
    }

}

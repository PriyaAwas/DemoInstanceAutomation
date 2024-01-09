package sew.ai.steps.csp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import sew.ai.config.Configuration;
import sew.ai.driver.DriverFactory;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.models.AdminWaysToSave;
import sew.ai.pageObjects.csp.WaysToSavePage;
import sew.ai.pageObjects.scp.DashboardPage;
import sew.ai.pageObjects.scp.PostLogEfficiencyPage;
import sew.ai.steps.scp.LoginSteps;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.PropertiesUtil;
import sew.ai.utils.RandomUtil;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static sew.ai.helpers.props.FilePaths.FILE_UPLOAD_PATH;

public class WaysToSaveSteps extends WaysToSavePage {

    private static final Logger log = LogManager.getLogger(WaysToSaveSteps.class);
    public static PropertiesUtil waysToSaveTextProp;

    public WaysToSaveSteps(WebDriver driver) {
        super(driver);
        waysToSaveTextProp = new PropertiesUtil(FilePaths.CSP_TEXT_PROPERTIES + Constants.ADMIN_WAYS_TO_SAVE_TXT_FILENAME);
    }

    public void verifyWayToSaveObjects(SoftAssert softAssert) {

        //Page Heading
        softAssert.assertTrue(isWaysToSavePageHeadingVisible(), "Ways To Save Page Heading is not visible on Ways To Save Dashboard.");
        softAssert.assertTrue(getLblWaysToSavePageHeading().equals(waysToSaveTextProp.getPropValue("waysToSavePageTitle").toUpperCase()),
                "Ways To Save Page Heading Label is not as Expected on Ways To Save Dashboard.");

        //Ways To Save Dropdown
        softAssert.assertTrue(isDdWaysToSaveFilterVisible(), "Ways To Save Filter is not visible on Ways To Save Dashboard.");
        softAssert.assertTrue(getSelectOptionsWaysToSaveFilter().containsAll(getExpectedElementsTextList(waysToSaveTextProp.getPropValue("optionsWaysToSaveFilter"))),
                "Ways To Save Page Filter Options is not as Expected on Ways To Save Dashboard.");
        softAssert.assertTrue(getSelectedOptionWaysToSaveFilter().equals(waysToSaveTextProp.getPropValue("txtLblEducationalTips")),
                "Ways To Save Page Default Filter Selected is not as Expected on Ways To Save Dashboard.");

        //Add Ways To Save button
        softAssert.assertTrue(isBtnAddEfficiencyVisible(), "Add Ways To Save Button is not visible on Ways To Save Dashboard.");
        softAssert.assertTrue(getLblBtnAddEfficiency().equals("ADD WAYS TO SAVE"),
                "Add Ways To Save Button Label is not as Expected on Ways To Save Dashboard.");

        //Ways To Save Grid Headers
        softAssert.assertTrue(isWaysToSaveTableHeadersVisible(), "Ways To Save Table Headers are not visible on Ways To Save Dashboard.");
        softAssert.assertTrue(getWaysToSaveGridHeaders().containsAll(getExpectedElementsTextList(waysToSaveTextProp.getPropValue("waysToSaveGridHeaders"))),
                "Ways To Save Grid Headers Displayed is not as Expected on Ways To Save Dashboard.");

        //Grid Actions
        softAssert.assertTrue(isthreeDotsKebabOptionsVisible(), "Three dots Kebab Options is not visible on Ways To Save Grid.");
        clickLstThreeDotsKebabOptions(0);

        //Edit Action
        softAssert.assertTrue(isEditActionVisible(), "Edit Action is not displayed on Ways To Save Grid.");
        softAssert.assertTrue(getLblEditAction().equals("Edit"), "Edit Action Label Text displayed is not as Expected on Ways To Save Grid.");

        //Delete Action
        softAssert.assertTrue(isDeleteActionVisible(), "Delete Action is not displayed on Ways To Save Grid.");
        softAssert.assertTrue(getLblDeleteAction().equals("Delete"), "Delete Action Label Text displayed is not as Expected on Ways To Save Grid.");

        //Status Column Data
        boolean validStatusColData = true;
        List<String> lstStatusColumnData = getLstStatusColumnData();
        for (String e : lstStatusColumnData) {
            if (!e.contains("Active")) {
                if (!e.contains("Inactive")) {
                    validStatusColData = false;
                    break;
                }
            }
        }
        softAssert.assertTrue(validStatusColData, "Status Column Values displayed is not as Expected on Ways To Save Grid.");

        //Showing Entries Label
        softAssert.assertTrue(isLblShowingEntriesVisible(), "Showing Entries Label is not visible on Ways To Save Dashboard.");
        softAssert.assertTrue(getLblShowingEntries().contains("Showing"), "Showing Entries Label Label is not as Expected" + //todo modify using Pattern Matcher
                " on Ways To Save Dashboard.");

        //Previous Button
        softAssert.assertTrue(isBtnPreviousPaginationVisible(), "Previous Button Pagination is not visible on Ways To Save Dashboard.");
        softAssert.assertTrue(getLblBtnPreviousPagination().equals("Previous"), "Previous Button Pagination Label is not as Expected on Ways To Save Dashboard.");

        //Next Button
        softAssert.assertTrue(isBtnNextPaginationVisible(), "Next Button Pagination is not visible on Ways To Save Dashboard.");
        softAssert.assertTrue(getLblBtnNextPagination().equals("Next"), "Next Button Pagination Label is not as Expected on Ways To Save Dashboard.");


    }

    public void verifyWayToSaveGridTotalCountWithDB(SoftAssert softAssert, String categoryName) {
        String countDB = null, countUI;
        int categoryId = getCategoryId(categoryName);
        selectWaysToSaveFilter(categoryName);
        try {
            ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.getTotalEfficiencyCount(String.valueOf(categoryId)));
            while (rs.next()) {
                countDB = rs.getString("Count");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        countUI = getLblShowingEntries().split("of")[1].replace("entries", "").trim();
        softAssert.assertTrue(countUI.equals(countDB), "The Total Ways To Save Count from the UI is not matching with the DB for the Category " + categoryName);


    }

    public void verifyAddWaysToSavePopupObjects(SoftAssert softAssert, String categoryName) {
        navigateAddWaysToSavePopup();
        waitForAddOrUpdateWaysToSavePopupToVisible();
        //Category Default Selection
        softAssert.assertTrue(getSelectedOptionCategory().equals("--Select--"), "Category Default Selected Option is not as Expected on Add Ways To Save Popup.");
        //Select Category Field Dropdown
        selectCategoryOnAddWaysToSavePopup(categoryName);

        //Title
        softAssert.assertTrue(isAddWaysToSavePopupTitleVisible(), "Add Ways To Save Title is not displayed on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblAddWaysToSavePopupTitle().equals("Add Ways To Save"), "Add Ways To Save Title displayed is not as Expected on Add Ways To Save Popup " +
                "for" + categoryName + " category.");

        //Category
        softAssert.assertTrue(isLblCategoryVisible(), "Category Field Label is not displayed on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblCategory().equals("Category"), "Category Field Label Text is not as Expected on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(isDdlCategoryVisible(), "Category Dropdown is not displayed on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getSelectOptionsCategory().containsAll(getExpectedElementsTextList(waysToSaveTextProp.getPropValue("optionsWaysToSaveFilter"))),
                "Category Dropdown options are not as Expected on Add Ways To Save Popup for " + categoryName + " category.");


        //Meter Type
        softAssert.assertTrue(isLblMeterTypeVisible(), "MeterType Field Label is not displayed on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblMeterType().equals("Meter Type"), "MeterType Field Label Text is not as Expected on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(isDdlMeterTypeVisible(), "MeterType Dropdown is not displayed on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getSelectOptionsMeterType().containsAll(getExpectedElementsTextList(waysToSaveTextProp.getPropValue("optionsMetertypeAddEfficiency"))),
                "MeterType Dropdown options are not as Expected on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getSelectedOptionMeterType().equals("--Select--"), "MeterType Default Selected Option is not as Expected on Add Ways To Save Popup for " + categoryName + " category.");

        //Account Type
        softAssert.assertTrue(isLblAccountTypeVisible(), "AccountType Field Label is not displayed on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblAccountType().equals("Account Type"), "AccountType Field Label Text is not as Expected on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(isDdlAccountTypeVisible(), "AccountType Dropdown is not displayed on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getSelectedOptionAccountType().equals("--Select--"), "AccountType Default Selected Option is not as Expected on Add Ways To Save Popup for " + categoryName + " category.");
        clickAccountTypeDropdown();

        //Residential AccountType
        softAssert.assertTrue(isOptionResidentialAccountTypVisible(), "Residential AccountType Dropdown option is not displayed on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblOptionResidentialAccountTyp().equals("Residential"), "Residential AccountType Dropdown option Label is not as Expected on Add Ways To Save Popup for " + categoryName + " category.");

        //Commercial AccountType
        softAssert.assertTrue(isOptionCommercialAccountTypVisible(), "Commercial AccountType Dropdown option is not displayed on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblOptionCommercialAccountTyp().equals("Commercial"), "Commercial AccountType Dropdown option Label is not as Expected on Add Ways To Save Popup for " + categoryName + " category.");

        //Residential AccountType
        softAssert.assertTrue(isOptionMultiFamilyAccountTypVisible(), "Multi-Family AccountType Dropdown option is not displayed on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblOptionMultiFamilyAccountTyp().equals("Multi-Family"), "Multi-Family AccountType Dropdown option Label is not as Expected on Add Ways To Save Popup for " + categoryName + " category.");
        clickAccountTypeDropdown();

        //Internal
        softAssert.assertTrue(isLblInternalVisible(), "Internal Field Label is not displayed on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblInternal().equals("Internal"), "Internal Field Label Text is not as Expected on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(isCheckboxInternalVisible(), "Internal Field Checkbox is not displayed on Add Ways To Save Popup for " + categoryName + " category.");

        //External
        unchkCheckBoxInternal();
        softAssert.assertTrue(isLblExternalLinkVisible(), "External Link Field Label is not displayed on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblExternalLink().equals("External Link"), "Internal Field Label Text is not as Expected on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(isTxtBoxExternalLinkVisible(), "External Link Field Textbox is not displayed on Add Ways To Save Popup for " + categoryName + " category.");

        //Annual Savings
        if (categoryName.equals("Saving Tips")) {
            softAssert.assertTrue(isLblAnnualSavingVisible(), "Annual Savings Field Label is not displayed on Add Ways To Save Popup for " + categoryName + " category.");
            softAssert.assertTrue(getLblAnnualSaving().equals("Annual Savings ($)"), "Annual Savings Field Label Text is not as Expected on Add Ways To Save Popup for " + categoryName + " category.");
            softAssert.assertTrue(isTxtBoxAnnualSavingVisible(), "Annual Savings Field Textbox is not displayed on Add Ways To Save Popup for " + categoryName + " category.");
        }

        //Incentive Rate
        if (categoryName.equals("Rebates")) {
            softAssert.assertTrue(isLblAnnualSavingVisible(), "Incentive Rate Field Label is not displayed on Add Ways To Save Popup for " + categoryName + " category.");
            softAssert.assertTrue(getLblAnnualSaving().equals("Incentive Rate ($)"), "Incentive Rate Field Label Text is not as Expected on Add Ways To Save Popup for " + categoryName + " category.");
            softAssert.assertTrue(isTxtBoxAnnualSavingVisible(), "Incentive Rate Field Textbox is not displayed on Add Ways To Save Popup for " + categoryName + " category.");
        }

        //Topic Name(English)
        softAssert.assertTrue(isLblTopicNameEnglishVisible(), "Topic Name (English) Field Label is not displayed on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblTopicNameEnglish().equals("Topic Name (English)"), "Topic Name (English) Field Label Text is not as Expected on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(isTxtBoxTopicNameEnglishVisible(), "Topic Name (English) Field TextBox is not as Expected on Add Ways To Save Popup for " + categoryName + " category.");

        //Description Name(English)
        softAssert.assertTrue(isLblDescEnglishVisible(), "Description (English) Field Label is not displayed on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblDescEnglish().equals("Description (English)"), "Description (English) Field Label Text is not as Expected on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(isTxtBoxDescEnglishVisible(), "Description (English) Field TextBox is not as Expected on Add Ways To Save Popup for " + categoryName + " category.");

        //Max Character Length English
        softAssert.assertTrue(isLblMaxCharLengthEnglishVisible(), "Max Characters Length Label English is not displayed on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblMaxCharLengthEnglish().equals(waysToSaveTextProp.getPropValue("txtLblMaxCharactersLimitDesc")), "Max Characters Length Label Text English is not as Expected on " +
                "Add Ways To Save Popup for " + categoryName + " category.");

        //Topic Name(Spanish)
        softAssert.assertTrue(isLblTopicNameSpanishVisible(), "Topic Name (Spanish) Field Label is not displayed on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblTopicNameSpanish().equals("Topic Name (Spanish)"), "Topic Name (Spanish) Field Label Text is not as Expected on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(isTxtBoxTopicNameSpanishVisible(), "Topic Name (Spanish) Field TextBox is not as Expected on Add Ways To Save Popup for " + categoryName + " category.");

        //Description Name(Spanish)
        softAssert.assertTrue(isLblDescSpanishVisible(), "Description (Spanish) Field Label is not displayed on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblDescSpanish().equals("Description (Spanish)"), "Description (Spanish) Field Label Text is not as Expected on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(isTxtBoxDescSpanishVisible(), "Description (Spanish) Field TextBox is not as Expected on Add Ways To Save Popup for " + categoryName + " category.");

        //Max Character Length Spanish
        softAssert.assertTrue(isLblMaxCharLengthSpanishVisible(), "Max Characters Length Spanish Label is not displayed on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblMaxCharLengthSpanish().equals(waysToSaveTextProp.getPropValue("txtLblMaxCharactersLimitDesc")), "Max Characters Length Label Text Spanish  is not " +
                "as Expected on Add Ways To Save Popup for " + categoryName + " category.");

        //Topic Name(French)
        softAssert.assertTrue(isLblTopicNameFrenchVisible(), "Topic Name (French) Field Label is not displayed on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblTopicNameFrench().equals("Topic Name (French)"), "Topic Name (French) Field Label Text is not as Expected on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(isTxtBoxTopicNameFrenchVisible(), "Topic Name (French) Field TextBox is not as Expected on Add Ways To Save Popup for " + categoryName + " category.");

        //Description Name(French)
        softAssert.assertTrue(isLblDescFrenchVisible(), "Description (French) Field Label is not displayed on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblDescFrench().equals("Description (French)"), "Description (French) Field Label Text is not as Expected on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(isTxtBoxDescFrenchVisible(), "Description (French) Field TextBox is not as Expected on Add Ways To Save Popup for " + categoryName + " category.");

        //Max Character Length French
        softAssert.assertTrue(isLblMaxCharLengthFrenchVisible(), "Max Characters Length Spanish Label is not displayed on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblMaxCharLengthFrench().equals(waysToSaveTextProp.getPropValue("txtLblMaxCharactersLimitDesc")), "Max Characters Length Label Text Spanish  is not as " +
                "Expected on Add Ways To Save Popup for " + categoryName + " category.");

        //Image
        softAssert.assertTrue(isLblImageVisible(), "Image Field Label is not displayed on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblImage().equals("Image"), "Image Field Label Text is not as Expected on Add Ways To Save Popup for " + categoryName + " category.");

        //Choose File Button
        softAssert.assertTrue(isbtnUploadFileVisible(), "Upload File Button is not displayed on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblBtnUploadFile().contains("Choose File"), "Upload File Button Label Text is not as Expected on Add Ways To Save Popup for " + categoryName + " category.");

        //File Name Attached
        softAssert.assertTrue(isAttachedFileNameVisible(), "Attached File Name Label is not displayed on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getAttachedFileName().contains(waysToSaveTextProp.getPropValue("lblNoFileChosen")),
                "Attached File Name Label Text is not as Expected on Add Ways To Save Popup for " + categoryName + " category.");

        //Allowed File Types
        softAssert.assertTrue(isLblAllowedFileTypesVisible(), "Allowed File Types Label is not displayed on Add Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblAllowedFileTypes().contains(waysToSaveTextProp.getPropValue("lblAllowedFileTypes")), "Attached File Name Label Text is not " +
                "as Expected on Add Ways To Save Popup for " + categoryName + " category.");

        //Save Button
        softAssert.assertTrue(isBtnAddOnAddWaysToSavePopupVisible(), "Add Button is not displayed on Add Ways To Save Popup for " + categoryName + " category.");

        //Close Button
        softAssert.assertTrue(isBtnCloseAddWaysToSavePopupVisible(), "Close Button is not displayed on Add Ways To Save Popup for " + categoryName + " category.");

        clickBtnCloseAddWaysToSavePopup();
        waitForAddOrUpdatePopupToInvisible();

    }


    public void verifyAddWaysToSavePopupFieldValidations(SoftAssert softAssert) {
        navigateAddWaysToSavePopup();
        waitForAddOrUpdateWaysToSavePopupToVisible();
        String toastMsg;
        String topicNameEnglish = "topicEnglish" + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC);
        String descEnglish = "descEnglish" + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC);
        //All Field Validation Message.
        clickBtnAddOnAddWaysToSavePopup();
        toastMsg = getToastMessage();
        softAssert.assertEquals(toastMsg, waysToSaveTextProp.getPropValue("txtAlertEnterMandatoryInfo"),
                "All Fields Mandatory Field Toast Message is not as Expected.");
        waitForToastMessageInvisibility();

        //Fill all the mandatory fields.
        selectCategoryOnAddWaysToSavePopup("Educational Tips");
        selectMeterTypeOnAddWaysToSavePopup("Power");
        selectAccountTypeDropdown("Residential");
        populateTxtBoxTopicNameEnglish(topicNameEnglish);
        populateTxtBoxDescEnglish(descEnglish);

        //Category Mandatory Validation Message.
        selectCategoryOnAddWaysToSavePopup(waysToSaveTextProp.getPropValue("defaultSelectDropdownValue"));
        populateTxtBoxAnnualSaving("34");//todo defect to be reported.
        clickBtnAddOnAddWaysToSavePopup();
        toastMsg = getToastMessage();
        softAssert.assertEquals(toastMsg, waysToSaveTextProp.getPropValue("txtSelectCategoryMessage"),
                "Mandatory Field Toast Message for Category is not as Expected.");
        waitForToastMessageInvisibility();
        selectCategoryOnAddWaysToSavePopup("Educational Tips");

        //MeterType Mandatory Validation Message.
        selectMeterTypeOnAddWaysToSavePopup(waysToSaveTextProp.getPropValue("defaultSelectDropdownValue"));
        clickBtnAddOnAddWaysToSavePopup();
        toastMsg = getToastMessage();
        softAssert.assertEquals(toastMsg, waysToSaveTextProp.getPropValue("txtSelectMeterTypeMessage"),
                "Mandatory Field Toast Message for  Meter Type  is not as Expected.");
        waitForToastMessageInvisibility();
        selectMeterTypeOnAddWaysToSavePopup("Power");

        //Account Mandatory Validation Message.
        selectAccountTypeDropdown("Residential");
        clickBtnAddOnAddWaysToSavePopup();
        toastMsg = getToastMessage();
        softAssert.assertEquals(toastMsg, waysToSaveTextProp.getPropValue("txtSelectAccountTypeMessage"),
                "Mandatory Field Toast Message for Account Type is not as Expected.");
        waitForToastMessageInvisibility();
        selectAccountTypeDropdown("Residential");

        //Topic Name English Mandatory Validation Message.
        clearTxtBoxTopicNameEnglish();
        clickBtnAddOnAddWaysToSavePopup();
        toastMsg = getToastMessage();
        softAssert.assertEquals(toastMsg, waysToSaveTextProp.getPropValue("txtEnterTopicNameEnglishMessage"),
                "Mandatory Field Toast Message for Topic Name English is not as Expected.");
        waitForToastMessageInvisibility();
        populateTxtBoxTopicNameEnglish(topicNameEnglish);

        //Description Name English Mandatory Validation Message.
        clearTxtBoxDescEnglish();
        clickBtnAddOnAddWaysToSavePopup();
        toastMsg = getToastMessage();
        softAssert.assertEquals(toastMsg, waysToSaveTextProp.getPropValue("txtEnterDescriptionEnglishMessage"),
                "Mandatory Field Toast Message for Description English is not as Expected.");
        waitForToastMessageInvisibility();

        //Description Name English Max Char limit Validation Message.
        populateTxtBoxDescEnglish(RandomUtil.generateRandomString(8001, RandomUtil.Mode.ALPHANUMERIC));
        clickBtnAddOnAddWaysToSavePopup();
        toastMsg = getToastMessage();
        softAssert.assertEquals(toastMsg, waysToSaveTextProp.getPropValue("txtMaxCharsDescEnglishValidationMessage"),
                "Validation Toast Message for Max Character Description English Field is not as Expected.");
        waitForToastMessageInvisibility();
        populateTxtBoxDescEnglish(RandomUtil.generateRandomString(200, RandomUtil.Mode.ALPHANUMERIC));

        //External Link Mandatory Validation Message.
        unchkCheckBoxInternal();
        clearTxtBoxExternalLink();
        clickBtnAddOnAddWaysToSavePopup();
        toastMsg = getToastMessage();
        softAssert.assertEquals(toastMsg, waysToSaveTextProp.getPropValue("txtBlankExternalLink"),
                "Validation Toast Message for External Link Field is not as Expected.");
        waitForToastMessageInvisibility();

        //Invalid External Link Validation Message.
        populateTxtBoxExternalLink("http://");
        clickBtnAddOnAddWaysToSavePopup();
        toastMsg = getToastMessage();
        softAssert.assertEquals(toastMsg, waysToSaveTextProp.getPropValue("txtInvalidExternalLink"),
                "Validation Toast Message for Invalid External Link Field value is not as Expected.");
        waitForToastMessageInvisibility();

        clickBtnCloseAddWaysToSavePopup();
        waitForAddOrUpdatePopupToInvisible();
    }

    public void addEfficiencyForCategory(AdminWaysToSave adminWaysToSave) {
        String toastMsg;
        selectWaysToSaveFilter(adminWaysToSave.getCategory());
        navigateAddWaysToSavePopup();
        switch (adminWaysToSave.getCategory()) {
            case "Saving Tips": {
                selectCategoryOnAddWaysToSavePopup(adminWaysToSave.getCategory());
                selectMeterTypeOnAddWaysToSavePopup(adminWaysToSave.getMeterType());
                selectAccountTypeDropdown(adminWaysToSave.getAccountType());
                if(adminWaysToSave.getIsInternal())
                    chkCheckBoxInternal();
                else {
                    unchkCheckBoxInternal();
                    populateTxtBoxExternalLink(adminWaysToSave.getExternalLink());
                }
                populateTxtBoxTopicNameEnglish(adminWaysToSave.getTopicNameEnglish());
                populateTxtBoxDescEnglish(adminWaysToSave.getDescEnglish());
                populateTxtBoxAnnualSaving(adminWaysToSave.getAnnualSavings());
                addAttachmentToChooseFile(FILE_UPLOAD_PATH + adminWaysToSave.getAttachFileName());
                clickBtnAddOnAddWaysToSavePopup();
                toastMsg = getToastSuccessMessageWithoutCloseBtn();
                Assert.assertEquals(toastMsg, waysToSaveTextProp.getPropValue("txtSavingTipAddedSucessMsg"),
                        "Saving Tips Added Success Message is not as Expected.");
                waitForToastMessageInvisibility();
                ExtentLogger.logPass("Ways to Save for Saving Tips has been created with the Topic name " + adminWaysToSave.getTopicNameEnglish());
                break;
            }
            case "Educational Tips": {
                selectCategoryOnAddWaysToSavePopup(adminWaysToSave.getCategory());
                selectMeterTypeOnAddWaysToSavePopup(adminWaysToSave.getMeterType());
                selectAccountTypeDropdown(adminWaysToSave.getAccountType());
                if(adminWaysToSave.getIsInternal())
                     chkCheckBoxInternal();
                else {
                    unchkCheckBoxInternal();
                    populateTxtBoxExternalLink(adminWaysToSave.getExternalLink());
                }
                populateTxtBoxTopicNameEnglish(adminWaysToSave.getTopicNameEnglish());
                populateTxtBoxDescEnglish(adminWaysToSave.getDescEnglish());
                addAttachmentToChooseFile(FILE_UPLOAD_PATH + adminWaysToSave.getAttachFileName());
                clickBtnAddOnAddWaysToSavePopup();
                toastMsg = getToastSuccessMessageWithoutCloseBtn();
                Assert.assertEquals(toastMsg, waysToSaveTextProp.getPropValue("txtEducationalTipAddedSucessMsg"),
                        "Educational Tips Added Success Message is not as Expected.");
                waitForToastMessageInvisibility();
                ExtentLogger.logPass("Ways to Save for Educational Tips has been created with the Topic name " + adminWaysToSave.getTopicNameEnglish());
                break;
            }
            case "Programs": {
                selectCategoryOnAddWaysToSavePopup(adminWaysToSave.getCategory());
                selectMeterTypeOnAddWaysToSavePopup(adminWaysToSave.getMeterType());
                selectAccountTypeDropdown(adminWaysToSave.getAccountType());
                if(adminWaysToSave.getIsInternal())
                    chkCheckBoxInternal();
                else {
                    unchkCheckBoxInternal();
                    populateTxtBoxExternalLink(adminWaysToSave.getExternalLink());
                }
                populateTxtBoxTopicNameEnglish(adminWaysToSave.getTopicNameEnglish());
                populateTxtBoxDescEnglish(adminWaysToSave.getDescEnglish());
                addAttachmentToChooseFile(FILE_UPLOAD_PATH + adminWaysToSave.getAttachFileName());
                clickBtnAddOnAddWaysToSavePopup();
                toastMsg = getToastSuccessMessageWithoutCloseBtn();
                Assert.assertEquals(toastMsg, waysToSaveTextProp.getPropValue("txtProgramAddedSucessMsg"),
                        "Programs Added Success Message is not as Expected.");
                waitForToastMessageInvisibility();
                ExtentLogger.logPass("Ways to Save for Programs has been created with the Topic name " + adminWaysToSave.getTopicNameEnglish());
                break;
            }
            case "Rebates": {
                selectCategoryOnAddWaysToSavePopup(adminWaysToSave.getCategory());
                selectMeterTypeOnAddWaysToSavePopup(adminWaysToSave.getMeterType());
                selectAccountTypeDropdown(adminWaysToSave.getAccountType());
                if(adminWaysToSave.getIsInternal())
                    chkCheckBoxInternal();
                else {
                    unchkCheckBoxInternal();
                    populateTxtBoxExternalLink(adminWaysToSave.getExternalLink());
                }
                populateTxtBoxTopicNameEnglish(adminWaysToSave.getTopicNameEnglish());
                populateTxtBoxDescEnglish(adminWaysToSave.getDescEnglish());
                populateTxtBoxAnnualSaving(adminWaysToSave.getIncentiveRate());
                addAttachmentToChooseFile(FILE_UPLOAD_PATH + adminWaysToSave.getAttachFileName());
                clickBtnAddOnAddWaysToSavePopup();
                toastMsg = getToastSuccessMessageWithoutCloseBtn();
                Assert.assertEquals(toastMsg, waysToSaveTextProp.getPropValue("txtRebateAddedSucessMsg"),
                        "Rebates Added Success Message is not as Expected.");
                waitForToastMessageInvisibility();
                ExtentLogger.logPass("Ways to Save for Rebates has been created with the Topic name " + adminWaysToSave.getTopicNameEnglish());
                break;
            }
        }
    }

    public void addEfficiencyMultiAcctForCategory(AdminWaysToSave adminWaysToSave) {
        String toastMsg;
        selectWaysToSaveFilter(adminWaysToSave.getCategory());
        navigateAddWaysToSavePopup();
        switch (adminWaysToSave.getCategory()) {
            case "Saving Tips": {
                selectCategoryOnAddWaysToSavePopup(adminWaysToSave.getCategory());
                selectMeterTypeOnAddWaysToSavePopup(adminWaysToSave.getMeterType());
                selectAccountTypeDropdown(adminWaysToSave.getAccountType());
                selectAccountTypeDropdown(adminWaysToSave.getAccountType1());
                chkCheckBoxInternal();
                populateTxtBoxTopicNameEnglish(adminWaysToSave.getTopicNameEnglish());
                populateTxtBoxDescEnglish(adminWaysToSave.getDescEnglish());
                populateTxtBoxAnnualSaving(adminWaysToSave.getAnnualSavings());
                addAttachmentToChooseFile(FILE_UPLOAD_PATH + adminWaysToSave.getAttachFileName());
                clickBtnAddOnAddWaysToSavePopup();
                toastMsg = getToastSuccessMessageWithoutCloseBtn();
                Assert.assertEquals(toastMsg, waysToSaveTextProp.getPropValue("txtSavingTipAddedSucessMsg"),
                        "Saving Tips Added Success Message is not as Expected.");
                waitForToastMessageInvisibility();
                ExtentLogger.logPass("Ways to Save for Saving Tips has been created with the Topic name " + adminWaysToSave.getTopicNameEnglish());
                break;
            }
            case "Educational Tips": {
                selectCategoryOnAddWaysToSavePopup(adminWaysToSave.getCategory());
                selectMeterTypeOnAddWaysToSavePopup(adminWaysToSave.getMeterType());
                selectAccountTypeDropdown(adminWaysToSave.getAccountType());
                selectAccountTypeDropdown(adminWaysToSave.getAccountType1());
                chkCheckBoxInternal();
                populateTxtBoxTopicNameEnglish(adminWaysToSave.getTopicNameEnglish());
                populateTxtBoxDescEnglish(adminWaysToSave.getDescEnglish());
                addAttachmentToChooseFile(FILE_UPLOAD_PATH + adminWaysToSave.getAttachFileName());
                clickBtnAddOnAddWaysToSavePopup();
                toastMsg = getToastSuccessMessageWithoutCloseBtn();
                Assert.assertEquals(toastMsg, waysToSaveTextProp.getPropValue("txtEducationalTipAddedSucessMsg"),
                        "Educational Tips Added Success Message is not as Expected.");
                waitForToastMessageInvisibility();
                ExtentLogger.logPass("Ways to Save for Educational Tips has been created with the Topic name " + adminWaysToSave.getTopicNameEnglish());
                break;
            }
            case "Programs": {
                selectCategoryOnAddWaysToSavePopup(adminWaysToSave.getCategory());
                selectMeterTypeOnAddWaysToSavePopup(adminWaysToSave.getMeterType());
                selectAccountTypeDropdown(adminWaysToSave.getAccountType());
                selectAccountTypeDropdown(adminWaysToSave.getAccountType1());
                chkCheckBoxInternal();
                populateTxtBoxTopicNameEnglish(adminWaysToSave.getTopicNameEnglish());
                populateTxtBoxDescEnglish(adminWaysToSave.getDescEnglish());
                addAttachmentToChooseFile(FILE_UPLOAD_PATH + adminWaysToSave.getAttachFileName());
                clickBtnAddOnAddWaysToSavePopup();
                toastMsg = getToastSuccessMessageWithoutCloseBtn();
                Assert.assertEquals(toastMsg, waysToSaveTextProp.getPropValue("txtProgramAddedSucessMsg"),
                        "Programs Added Success Message is not as Expected.");
                waitForToastMessageInvisibility();
                ExtentLogger.logPass("Ways to Save for Programs has been created with the Topic name " + adminWaysToSave.getTopicNameEnglish());
                break;
            }
            case "Rebates": {
                selectCategoryOnAddWaysToSavePopup(adminWaysToSave.getCategory());
                selectMeterTypeOnAddWaysToSavePopup(adminWaysToSave.getMeterType());
                selectAccountTypeDropdown(adminWaysToSave.getAccountType());
                selectAccountTypeDropdown(adminWaysToSave.getAccountType1());
                chkCheckBoxInternal();
                populateTxtBoxTopicNameEnglish(adminWaysToSave.getTopicNameEnglish());
                populateTxtBoxDescEnglish(adminWaysToSave.getDescEnglish());
                populateTxtBoxAnnualSaving(adminWaysToSave.getIncentiveRate());
                addAttachmentToChooseFile(FILE_UPLOAD_PATH + adminWaysToSave.getAttachFileName());
                clickBtnAddOnAddWaysToSavePopup();
                toastMsg = getToastSuccessMessageWithoutCloseBtn();
                Assert.assertEquals(toastMsg, waysToSaveTextProp.getPropValue("txtRebateAddedSucessMsg"),
                        "Rebates Added Success Message is not as Expected.");
                waitForToastMessageInvisibility();
                ExtentLogger.logPass("Ways to Save for Rebates has been created with the Topic name " + adminWaysToSave.getTopicNameEnglish());
                break;
            }
        }
    }

    public void verifyWaysToSaveCreatedPresentOnTopGrid(SoftAssert softAssert, AdminWaysToSave adminWaysToSave) {
        softAssert.assertEquals(getFirstWaysToSaveNameOnGrid(), adminWaysToSave.getTopicNameEnglish(),
                "First Ways to Save Name present on Grid Table is not as Expected.");
    }

    public void verifyWaysToSaveDataWithDB(SoftAssert softAssert, AdminWaysToSave adminWaysToSave) {
        String categoryTypeId = String.valueOf(getCategoryId(adminWaysToSave.getCategory()));
        String meterTypeId = String.valueOf(getMeterTypeId(adminWaysToSave.getMeterType()));
        String accountTypeId = String.valueOf(getAccountTypeId(adminWaysToSave.getAccountType()));
        String waysToSaveTopicName = adminWaysToSave.getTopicNameEnglish();

        try {
            ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.getWaysToSaveDetails(categoryTypeId, waysToSaveTopicName));
            rs.next();
            softAssert.assertEquals(categoryTypeId, rs.getString("CategoryId"),
                    "The CategoryType ID field for " + waysToSaveTopicName + " with Category ID as " + categoryTypeId + " is not matching with the DB.");
            softAssert.assertEquals(meterTypeId, rs.getString("ServiceTypeId"),
                    "The ServiceType ID field for " + waysToSaveTopicName + " with Category ID as " + categoryTypeId + " is not matching with the DB.");
            softAssert.assertEquals(accountTypeId, rs.getString("AccountType"),
                    "The AccountType ID field for " + waysToSaveTopicName + " with Category ID as " + categoryTypeId + " is not matching with the DB.");
            softAssert.assertEquals(waysToSaveTopicName, rs.getString("title"),
                    "The Title field for " + waysToSaveTopicName + " with Category ID as " + categoryTypeId + " is not matching with the DB.");
            softAssert.assertEquals(adminWaysToSave.getDescEnglish(), rs.getString("Description"),
                    "The Description field for " + waysToSaveTopicName + " with Category ID as " + categoryTypeId + " is not matching with the DB.");
            if (adminWaysToSave.getCategory().equals("Saving Tips")) {
                softAssert.assertEquals(adminWaysToSave.getAnnualSavings(), rs.getString("SavingValue"),
                        "The savingAmount field for " + waysToSaveTopicName + " with Category ID as " + categoryTypeId + " is not matching with the DB.");
            }
            if (adminWaysToSave.getCategory().equals("Rebates")) {
                softAssert.assertEquals(adminWaysToSave.getIncentiveRate(), rs.getString("SavingValue"),
                        "The Incentive Rate field for " + waysToSaveTopicName + " with Category ID as " + categoryTypeId + " is not matching with the DB.");
            }
            softAssert.assertTrue(rs.getString("ImageURL").contains(adminWaysToSave.getAttachFileName().replace(".jpg", "")),
                    "The ImageUrl field for " + waysToSaveTopicName + " with Category ID as " + categoryTypeId + " doesnot contain the expected value Test.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void verifyUpdateWaysToSavePopupFields(SoftAssert softAssert, String categoryName, String waysToSaveName) {
        selectWaysToSaveFilter(categoryName);
        navUpdateWaysToSavePopupPageOfName(waysToSaveName);
        selectCategoryOnAddWaysToSavePopup(categoryName);

        //Title
        softAssert.assertTrue(isAddWaysToSavePopupTitleVisible(), "Update Ways To Save Title is not displayed on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblAddWaysToSavePopupTitle().equals("Update Ways To Save"), "Update Ways To Save Title displayed is not as Expected on Update Ways To Save Popup " +
                "for" + categoryName + " category.");

        //Category
        softAssert.assertTrue(isLblCategoryVisible(), "Category Field Label is not displayed on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblCategory().equals("Category"), "Category Field Label Text is not as Expected on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(isDdlCategoryVisible(), "Category Dropdown is not displayed on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getSelectOptionsCategory().containsAll(getExpectedElementsTextList(waysToSaveTextProp.getPropValue("optionsWaysToSaveFilter"))),
                "Category Dropdown options are not as Expected on Update Ways To Save Popup for " + categoryName + " category.");


        //Meter Type
        softAssert.assertTrue(isLblMeterTypeVisible(), "MeterType Field Label is not displayed on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblMeterType().equals("Meter Type"), "MeterType Field Label Text is not as Expected on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(isDdlMeterTypeVisible(), "MeterType Dropdown is not displayed on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getSelectOptionsMeterType().containsAll(getExpectedElementsTextList(waysToSaveTextProp.getPropValue("optionsMetertypeAddEfficiency"))),
                "MeterType Dropdown options are not as Expected on Update Ways To Save Popup for " + categoryName + " category.");


        //Account Type
        softAssert.assertTrue(isLblAccountTypeVisible(), "AccountType Field Label is not displayed on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblAccountType().equals("Account Type"), "AccountType Field Label Text is not as Expected on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(isDdlAccountTypeVisible(), "AccountType Dropdown is not displayed on Update Ways To Save Popup for " + categoryName + " category.");
        clickAccountTypeDropdown();

        //Residential AccountType
        softAssert.assertTrue(isOptionResidentialAccountTypVisible(), "Residential AccountType Dropdown option is not displayed on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblOptionResidentialAccountTyp().equals("Residential"), "Residential AccountType Dropdown option Label is not as Expected on Update Ways To Save Popup for " + categoryName + " category.");

        //Commercial AccountType
        softAssert.assertTrue(isOptionCommercialAccountTypVisible(), "Commercial AccountType Dropdown option is not displayed on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblOptionCommercialAccountTyp().equals("Commercial"), "Commercial AccountType Dropdown option Label is not as Expected on Update Ways To Save Popup for " + categoryName + " category.");

        //Residential AccountType
        softAssert.assertTrue(isOptionMultiFamilyAccountTypVisible(), "Multi-Family AccountType Dropdown option is not displayed on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblOptionMultiFamilyAccountTyp().equals("Multi-Family"), "Multi-Family AccountType Dropdown option Label is not as Expected on Update Ways To Save Popup for " + categoryName + " category.");
        clickAccountTypeDropdown();

        //Internal
        softAssert.assertTrue(isLblInternalVisible(), "Internal Field Label is not displayed on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblInternal().equals("Internal"), "Internal Field Label Text is not as Expected on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(isCheckboxInternalVisible(), "Internal Field Checkbox is not displayed on Update Ways To Save Popup for " + categoryName + " category.");

        //External
        unchkCheckBoxInternal();
        softAssert.assertTrue(isLblExternalLinkVisible(), "External Link Field Label is not displayed on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblExternalLink().equals("External Link"), "Internal Field Label Text is not as Expected on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(isTxtBoxExternalLinkVisible(), "External Link Field Textbox is not displayed on Update Ways To Save Popup for " + categoryName + " category.");

        //Topic Name(English)
        softAssert.assertTrue(isLblTopicNameEnglishVisible(), "Topic Name (English) Field Label is not displayed on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblTopicNameEnglish().equals("Topic Name (English)"), "Topic Name (English) Field Label Text is not as Expected on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(isTxtBoxTopicNameEnglishVisible(), "Topic Name (English) Field TextBox is not as Expected on Update Ways To Save Popup for " + categoryName + " category.");

        //Description Name(English)
        softAssert.assertTrue(isLblDescEnglishVisible(), "Description (English) Field Label is not displayed on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblDescEnglish().equals("Description (English)"), "Description (English) Field Label Text is not as Expected on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(isTxtBoxDescEnglishVisible(), "Description (English) Field TextBox is not as Expected on Update Ways To Save Popup for " + categoryName + " category.");

        //Max Character Length English
        softAssert.assertTrue(isLblMaxCharLengthEnglishVisible(), "Max Characters Length Label English is not displayed on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblMaxCharLengthEnglish().equals(waysToSaveTextProp.getPropValue("txtLblMaxCharactersLimitDesc")), "Max Characters Length Label Text English is not as Expected on " +
                "Update Ways To Save Popup for " + categoryName + " category.");

        //Topic Name(Spanish)
        softAssert.assertTrue(isLblTopicNameSpanishVisible(), "Topic Name (Spanish) Field Label is not displayed on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblTopicNameSpanish().equals("Topic Name (Spanish)"), "Topic Name (Spanish) Field Label Text is not as Expected on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(isTxtBoxTopicNameSpanishVisible(), "Topic Name (Spanish) Field TextBox is not as Expected on Update Ways To Save Popup for " + categoryName + " category.");

        //Description Name(Spanish)
        softAssert.assertTrue(isLblDescSpanishVisible(), "Description (Spanish) Field Label is not displayed on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblDescSpanish().equals("Description (Spanish)"), "Description (Spanish) Field Label Text is not as Expected on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(isTxtBoxDescSpanishVisible(), "Description (Spanish) Field TextBox is not as Expected on Update Ways To Save Popup for " + categoryName + " category.");

        //Max Character Length Spanish
        softAssert.assertTrue(isLblMaxCharLengthSpanishVisible(), "Max Characters Length Spanish Label is not displayed on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblMaxCharLengthSpanish().equals(waysToSaveTextProp.getPropValue("txtLblMaxCharactersLimitDesc")), "Max Characters Length Label Text Spanish  is not " +
                "as Expected on Update Ways To Save Popup for " + categoryName + " category.");

        //Topic Name(French)
        softAssert.assertTrue(isLblTopicNameFrenchVisible(), "Topic Name (French) Field Label is not displayed on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblTopicNameFrench().equals("Topic Name (French)"), "Topic Name (French) Field Label Text is not as Expected on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(isTxtBoxTopicNameFrenchVisible(), "Topic Name (French) Field TextBox is not as Expected on Update Ways To Save Popup for " + categoryName + " category.");

        //Description Name(French)
        softAssert.assertTrue(isLblDescFrenchVisible(), "Description (French) Field Label is not displayed on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblDescFrench().equals("Description (French)"), "Description (French) Field Label Text is not as Expected on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(isTxtBoxDescFrenchVisible(), "Description (French) Field TextBox is not as Expected on Update Ways To Save Popup for " + categoryName + " category.");

        //Max Character Length French
        softAssert.assertTrue(isLblMaxCharLengthFrenchVisible(), "Max Characters Length Spanish Label is not displayed on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblMaxCharLengthFrench().equals(waysToSaveTextProp.getPropValue("txtLblMaxCharactersLimitDesc")), "Max Characters Length Label Text Spanish  is not as " +
                "Expected on Update Ways To Save Popup for " + categoryName + " category.");

        //Annual Savings
        if (categoryName.equals("Saving Tips")) {
            softAssert.assertTrue(isLblAnnualSavingVisible(), "Annual Savings Field Label is not displayed on Update Ways To Save Popup for " + categoryName + " category.");
            softAssert.assertTrue(getLblAnnualSaving().equals("Annual Savings ($)"), "Annual Savings Field Label Text is not as Expected on Update Ways To Save Popup for " + categoryName + " category.");
            softAssert.assertTrue(isTxtBoxAnnualSavingVisible(), "Annual Savings Field Textbox is not displayed on Update Ways To Save Popup for " + categoryName + " category.");
        }

        //Incentive Rate
        if (categoryName.equals("Rebates")) {
            softAssert.assertTrue(isLblAnnualSavingVisible(), "Incentive Rate Field Label is not displayed on Update Ways To Save Popup for " + categoryName + " category.");
            softAssert.assertTrue(getLblAnnualSaving().equals("Incentive Rate ($)"), "Incentive Rate Field Label Text is not as Expected on Update Ways To Save Popup for " + categoryName + " category.");
            softAssert.assertTrue(isTxtBoxAnnualSavingVisible(), "Incentive Rate Field Textbox is not displayed on Update Ways To Save Popup for " + categoryName + " category.");
        }

        //Image
        softAssert.assertTrue(isLblImageVisible(), "Image Field Label is not displayed on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblImage().equals("Image"), "Image Field Label Text is not as Expected on Update Ways To Save Popup for " + categoryName + " category.");

        //Choose File Button
        softAssert.assertTrue(isbtnUploadFileVisible(), "Upload File Button is not displayed on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblBtnUploadFile().contains("Choose File"), "Upload File Button Label Text is not as Expected on Update Ways To Save Popup for " + categoryName + " category.");

        //File Name Attached
        softAssert.assertTrue(isAttachedFileNameVisible(), "Attached File Name Label is not displayed on Update Ways To Save Popup for " + categoryName + " category.");


        //Allowed File Types
        softAssert.assertTrue(isLblAllowedFileTypesVisible(), "Allowed File Types Label is not displayed on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getLblAllowedFileTypes().contains(waysToSaveTextProp.getPropValue("lblAllowedFileTypes")), "Attached File Name Label Text is not " +
                "as Expected on Update Ways To Save Popup for " + categoryName + " category.");

        //Save Button
        softAssert.assertTrue(isBtnAddOnAddWaysToSavePopupVisible(), "Add Button is not displayed on Update Ways To Save Popup for " + categoryName + " category.");

        //Close Button
        softAssert.assertTrue(isBtnCloseAddWaysToSavePopupVisible(), "Close Button is not displayed on Update Ways To Save Popup for " + categoryName + " category.");

        clickBtnCloseAddWaysToSavePopup();


    }

    public void updateWaysToSavePopupFieldValues(AdminWaysToSave adminWaysToSave) {
        String categoryName = adminWaysToSave.getCategory();
        selectWaysToSaveFilter(categoryName);
        navUpdateWaysToSavePopupPageOfName(adminWaysToSave.getTopicNameEnglish());
        adminWaysToSave.setTopicNameEnglish(categoryName + " Updated Topic " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
        adminWaysToSave.setDescEnglish(categoryName + " Updated Description " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
        adminWaysToSave.setAnnualSavings(RandomUtil.generateRandomString(2, RandomUtil.Mode.NUMERIC));

        populateTxtBoxTopicNameEnglish(adminWaysToSave.getTopicNameEnglish());
        populateTxtBoxDescEnglish(adminWaysToSave.getDescEnglish());

        if (categoryName.equals("Saving Tips") || categoryName.equals("Rebates")) {
            populateTxtBoxAnnualSaving(adminWaysToSave.getAnnualSavings());
        }
        clickBtnAddOnAddWaysToSavePopup();
        verifyUpdateWaysToSaveSuccessTstMsg(adminWaysToSave.getCategory());
        ExtentLogger.logPass("Ways to Save for " + categoryName + "has been Updated with the Topic Name " + adminWaysToSave.getTopicNameEnglish());
    }

    public void updateWaysToSavePopupFieldValuesForMultiAcct(AdminWaysToSave adminWaysToSave) {
        String categoryName = adminWaysToSave.getCategory();
        adminWaysToSave.setTopicNameEnglish(categoryName + " Updated Topic " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
        adminWaysToSave.setDescEnglish(categoryName + " Updated Description " + RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC));
        adminWaysToSave.setAnnualSavings(RandomUtil.generateRandomString(2, RandomUtil.Mode.NUMERIC));

        populateTxtBoxTopicNameEnglish(adminWaysToSave.getTopicNameEnglish());
        populateTxtBoxDescEnglish(adminWaysToSave.getDescEnglish());
        clearAccountTypeDropdownSelection();
        selectAccountTypeDropdown(adminWaysToSave.getAccountType());
        selectAccountTypeDropdown(adminWaysToSave.getAccountType1());

        if (categoryName.equals("Saving Tips") || categoryName.equals("Rebates")) {
            populateTxtBoxAnnualSaving(adminWaysToSave.getAnnualSavings());
        }
        clickBtnAddOnAddWaysToSavePopup();
        verifyUpdateWaysToSaveSuccessTstMsg(adminWaysToSave.getCategory());
        ExtentLogger.logPass("Ways to Save for " + categoryName + "has been Updated with the Topic Name " + adminWaysToSave.getTopicNameEnglish());
    }

    public void verifyUpdatedWaysToSavePopupFieldValuesWithDB(SoftAssert softAssert, AdminWaysToSave adminWaysToSave) {
        String categoryName = adminWaysToSave.getCategory();
        selectWaysToSaveFilter(categoryName);
        navUpdateWaysToSavePopupPageOfName(adminWaysToSave.getTopicNameEnglish());
        Map<String, String> waysToSaveDetailsFromDB = getWaysToSaveDetailsFromDB(adminWaysToSave);
        softAssert.assertTrue(getSelectedOptionCategory().equals(getCategoryName(waysToSaveDetailsFromDB.get("CategoryId"))),
                "Category Selected Option is not as Expected on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getSelectedOptionMeterType().equals(getMeterTypeName(waysToSaveDetailsFromDB.get("ServiceTypeId"))),
                "MeterType Selected Option is not as Expected on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getSelectedOptionAccountType().equals(getAccountTypeName(waysToSaveDetailsFromDB.get("AccountType"))),
                "AccountType Selected Option is not as Expected on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getValueTxtBoxTopicNameEnglish().equals(waysToSaveDetailsFromDB.get("Title")),
                "Topic Name (English) Field TextBox Value is not as Expected on Update Ways To Save Popup for " + categoryName + " category.");
        softAssert.assertTrue(getValueTxtBoxDescEnglish().equals(waysToSaveDetailsFromDB.get("Description")),
                "Description (English) Field TextBox Value is not as Expected on Update Ways To Save Popup for " + categoryName + " category.");
        if (categoryName.equals("Saving Tips")) {
            scrollToElement(getWebEleTxtBoxAnnualSaving());
            softAssert.assertTrue(getValueTxtBoxAnnualSaving().equals(waysToSaveDetailsFromDB.get("SavingValue")),
                    "Annual Savings Field TextBox Value is not as Expected on Update Ways To Save Popup for " + categoryName + " category.");
        }
        if (categoryName.equals("Rebates")) {
            scrollToElement(getWebEleTxtBoxAnnualSaving());
            softAssert.assertTrue(getValueTxtBoxAnnualSaving().equals(waysToSaveDetailsFromDB.get("SavingValue")),
                    "Incentive Rate Field TextBox Value is not as Expected on Update Ways To Save Popup for " + categoryName + " category.");
        }
//        softAssert.assertTrue(getAttachedFileName().contains(adminWaysToSave.getAttachFileName()), //todo defect to be reported.
//                "Attached File Name Label Text is not as Expected on Update Ways To Save Popup for " + categoryName + " category.");
        clickBtnCloseAddWaysToSavePopup();
    }

    public void deleteWaysToSaveName(SoftAssert softAssert, AdminWaysToSave adminWaysToSave) {
        int countBeforeDeletion = getTotalEntriesOnWaysToSavePage();
        clickKebabOptionForWaysToSaveName(adminWaysToSave.getTopicNameEnglish());
        clickDeleteOptionForWaysToSaveName(adminWaysToSave.getTopicNameEnglish());
        verifyDeleteConfirmationPopup(softAssert);
        waitForBtnYesDeleteConfirmationPopupToBeVisible();
        clickBtnYesDeleteConfirmationPopup();
        verifyDeleteWaysToSaveSuccessTstMsg(adminWaysToSave.getCategory());
        int countAfterDeletion = getTotalEntriesOnWaysToSavePage();
        Assert.assertEquals(countAfterDeletion, countBeforeDeletion - 1,
                "Deleted Ways To Save is not removed from the Ways To Save Folder.");
        ExtentLogger.logPass("Ways to Save for " + adminWaysToSave.getCategory() + "has been deleted with the Topic Name " + adminWaysToSave.getTopicNameEnglish());
    }

    public void verifyValidationMsgDeleteEnrolledWaysToSave(SoftAssert softAssert, AdminWaysToSave adminWaysToSave) {
        String categoryTypeId = String.valueOf(getCategoryId(adminWaysToSave.getCategory()));
        String signedUpWaysToSaveName;
        try {
            ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.getSignedUpEfficiencyTitle(categoryTypeId));
            rs.next();
            signedUpWaysToSaveName = rs.getString("title");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        navigateToSpecificWaysToSavePage(signedUpWaysToSaveName);
        clickKebabOptionForWaysToSaveName(signedUpWaysToSaveName);
        clickDeleteOptionForWaysToSaveName(signedUpWaysToSaveName);
        switch (categoryTypeId) {
            case "1" -> {
                softAssert.assertEquals(getToastSuccessMessageWithoutCloseBtn(),
                        waysToSaveTextProp.getPropValue("txtValidationMsgDeleteEnrolledSavingTip"),
                        "Validation Toast Message for Delete Enrolled Saving Tips is not as Expected.");
                waitForToastMessageInvisibility();
            }
            case "2" -> {
                softAssert.assertEquals(getToastSuccessMessageWithoutCloseBtn(),
                        waysToSaveTextProp.getPropValue("txtValidationMsgDeleteEnrolledEducationalTip"),
                        "Validation Toast Message for Delete Enrolled Educational Tips is not as Expected.");
                waitForToastMessageInvisibility();
            }
            case "3" -> {
                softAssert.assertEquals(getToastSuccessMessageWithoutCloseBtn(),
                        waysToSaveTextProp.getPropValue("txtValidationMsgDeleteEnrolledRebates"),
                        "Validation Toast Message for Delete Enrolled Rebates is not as Expected.");
                waitForToastMessageInvisibility();
            }
            case "4" -> {
                softAssert.assertEquals(getToastSuccessMessageWithoutCloseBtn(),
                        waysToSaveTextProp.getPropValue("txtValidationMsgDeleteEnrolledPrograms"),
                        "Validation Toast Message for Delete Enrolled Programs is not as Expected.");
                waitForToastMessageInvisibility();
            }
            default -> {
            }
        }
    }

    public int navigateToSpecificWaysToSavePage(String efficiencyName) {
        int page =1;
        while(!getAllElementsTextInList(getLstWebEleWaysToSaveName()).contains(efficiencyName)) {
//		List<String> effNameList = pageUtil.getAllElementsTextInList(adminEfficiencyPage.getLstEfficiencyNameAep());
//		for(String e:effNameList ) {
//			if(e.contains(efficiencyName)) {
//				break;
//			}
//		}
            page++;
            clickBtnNextPagination();
            pause(500);
        }
        return page;
    }

    public void verifyDeletedWaysToSaveOnDB(AdminWaysToSave adminWaysToSave) {
        String categoryTypeId = String.valueOf(getCategoryId(adminWaysToSave.getCategory()));
        String waysToSaveName = adminWaysToSave.getTopicNameEnglish();
        try {
            ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.getEfficiencyCount(categoryTypeId, waysToSaveName));
            rs.next();
            Assert.assertTrue(rs.getInt("Count") == 1, "Deleted Ways To Save for " + waysToSaveName +
                    "is not marked as Deleted on the DB.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void verifyDeleteConfirmationPopup(SoftAssert softAssert) {
        waitForBtnYesDeleteConfirmationPopupToBeVisible();
        //Heading
        softAssert.assertTrue(isHeadingDeleteConfirmationPopupVisible(), "Heading on Delete Confirmation Popup is not visible.");
        softAssert.assertEquals(getHeadingDeleteConfirmationPopup(), waysToSaveTextProp.getPropValue("txtConfirmationHeadingPopup"),
                "Heading displayed on Delete Confirmation Popup is not as Expected.");

        //Message
        softAssert.assertTrue(isMessageDeleteConfirmationPopupVisible(), "Confirmation Message on Delete Confirmation Popup is not visible.");
        softAssert.assertEquals(getMessageDeleteConfirmationPopup(), waysToSaveTextProp.getPropValue("txtAlertDeleteMsg"),
                "Confirmation Message displayed on Delete Confirmation Popup is not as Expected.");

        //Close Button
        softAssert.assertTrue(isBtnCloseDeleteConfirmationPopupVisible(), "Close Button on Delete Confirmation Popup is not visible.");
        softAssert.assertTrue(getLblBtnCloseDeleteConfirmationPopup().equals("Close"),
                "Close Button Label displayed on Delete Confirmation Popup is not as Expected.");
        //Yes Button
        softAssert.assertTrue(isBtnYesDeleteConfirmationPopupVisible(), "Yes Button on Delete Confirmation Popup is not visible.");
        softAssert.assertTrue(getLblBtnYesDeleteConfirmationPopup().equals("Yes"),
                "Yes Button Label displayed on Delete Confirmation Popup is not as Expected.");

    }

    public void verifyUpdateWaysToSavePopUpBlankSubmitValidation(SoftAssert softAssert, String categoryName, String waysToSaveName) {
        selectWaysToSaveFilter(categoryName);
        navUpdateWaysToSavePopupPageOfName(waysToSaveName);
        selectCategoryOnAddWaysToSavePopup(categoryName);
        clearAddOrUpdateWaysToSavePopUpFieldValues();
        clickBtnAddOnAddWaysToSavePopup();
        String toastMsg = getToastSuccessMessageWithoutCloseBtn();
        softAssert.assertEquals(toastMsg, waysToSaveTextProp.getPropValue("txtAlertEnterMandatoryInfo"),
                "All Fields mandatory Toast Message for Blank Submission Fields on Update Ways To Save Popup is not as Expected.");
        waitForToastMessageInvisibility();
    }

    public void dragAndDropWaysToSave(String waysToSaveName, int index) {
        WebElement source = getWebElementNameForWaysToSave(waysToSaveName);
        WebElement destination = getLstWebEleWaysToSaveName().get(index);
        dragAndDropElement(source,destination);
    }

    public void getWaysToSaveNameOnIndex(){

    }

    public int getIndexOfWaysToSaveNameOnGridCSP(String waysToSaveName){
        int indexCSP = 0;
        List<WebElement> lstWebEleWaysToSaveName = getLstWebEleWaysToSaveName();
        for(int i=0;i<lstWebEleWaysToSaveName.size();i++){
            if(lstWebEleWaysToSaveName.get(i).getText().contains(waysToSaveName)){
                indexCSP = i;
                break;
            }
        }
        return indexCSP;
    }

    public int getIndexOfWaysToSaveNameFromSCP(String waysToSaveName){
        PostLogEfficiencyPage postLogEfficiencyPage = new PostLogEfficiencyPage(driver);
        List<String> lstHeadingText = postLogEfficiencyPage.getLstPostLoginEfficiencyHeading();
        int indexSCP = 0;
        for(int i=0;i<lstHeadingText.size();i++){
            if(lstHeadingText.contains(waysToSaveName)){
                indexSCP = i;
                break;
            }
        }
        return indexSCP;
    }


    public boolean isWaysToSaveNamePresentOnScp(String waysToSaveName) {
        PostLogEfficiencyPage postLogEfficiencyPage = new PostLogEfficiencyPage(driver);
        List<String> lstHeadingText = postLogEfficiencyPage.getLstPostLoginEfficiencyHeading();
        return lstHeadingText.contains(waysToSaveName);
    }

    public void verifyCloseButtonChangeStatusConfirmationPopup(String waysToSaveName) {
        WaysToSavePage waysToSavePage = new WaysToSavePage(driver);
        clickLnkStatusForWaysToSaveName(waysToSaveName);
        waitForBtnYesChangeStatusConfirmationPopupToBeVisible();
        clickBtnCloseChangeStatusConfirmationPopup();
        expWaitForEleAttributeToChange(waysToSavePage.getWebEleChangeStatusConfirmationPopup(), "style", "display: none;");
        Assert.assertFalse(isChangeStatusConfirmationPopupVisible(), "Change Status Confirmation popup visible after clicking Close button on the Popup.");
    }

    public void updateWaysToSaveStatus(String waysToSaveName) {
        clickLnkStatusForWaysToSaveName(waysToSaveName);
        waitForBtnYesChangeStatusConfirmationPopupToBeVisible();
        clickBtnYesChangeStatusConfirmationPopup();
        String toastMsg = getToastSuccessMessageWithoutCloseBtn();
        Assert.assertEquals(toastMsg, waysToSaveTextProp.getPropValue("txtStatusChangeSuccessMsg"),
                "Change Status Success Message for Ways To Save is not as Expected.");
        waitForToastMessageInvisibility();
        ExtentLogger.logPass(waysToSaveName + " waysToSave has been updated with the status.");
    }

    public void verifyStatusOfWaysToSave(String waysToSaveName, String expectedStatus) {
        String status = getStatusForWaysToSaveName(waysToSaveName);
        String inactiveStatusRgb = "rgba(222, 227, 226, 1)";
        String activeStatusRgb = "rgba(33, 94, 217, 1)";
        if (expectedStatus.equals("Inactive")) {
            Assert.assertEquals(status, expectedStatus, "Status is not marked as " + expectedStatus + " on the Grid for Topic Name " + waysToSaveName);
            Assert.assertEquals(getColorForInactiveStatusWaysToSaveName(waysToSaveName), inactiveStatusRgb, "Inactive Status Color on the Grid is not as Expected for Topic Name " + waysToSaveName);
            ExtentLogger.logPass(waysToSaveName + " waysToSave has been successfully verified with its status value and color.");
        } else if (expectedStatus.equals("Active")) {
            Assert.assertEquals(status, expectedStatus, "Status is not marked as " + expectedStatus + " on the Grid for Topic Name " + waysToSaveName);
            Assert.assertEquals(getColorForActiveStatusWaysToSaveName(waysToSaveName), activeStatusRgb, "Active Status Color on the Grid is not as Expected for Topic Name " + waysToSaveName);
            ExtentLogger.logPass(waysToSaveName + " waysToSave has been successfully verified with its status value and color.");
        }

    }

    public void verifyChangeStatusConfirmationPopupObjects(SoftAssert softAssert, String waysToSaveName) {
        clickLnkStatusForWaysToSaveName(waysToSaveName);
        waitForBtnYesChangeStatusConfirmationPopupToBeVisible();
        //Heading
        softAssert.assertTrue(isHeadingChangeStatusConfirmationPopupVisible(), "Heading on Change Status Confirmation Popup is not Visible.");
        softAssert.assertTrue(getHeadingChangeStatusConfirmationPopup().equals(waysToSaveTextProp.getPropValue("txtConfirmationHeadingPopup")),
                "Heading Label displayed on Change Status Confirmation Popup is not as Expected.");
        //Close X button
        softAssert.assertTrue(isCloseXButtonChangeStatusConfirmationPopupVisible(), "Close X Button on Change Status Confirmation Popup is not Visible.");
//        softAssert.assertTrue(getLblCloseXButtonChangeStatusConfirmationPopup().equals("X"),
//                "Close X Button Label displayed on Change Status Confirmation Popup is not as Expected.");
        //Confirmation Message
        softAssert.assertTrue(isMessageChangeStatusConfirmationPopupVisible(), "Confirmation Message on Change Status Confirmation Popup is not Visible.");
        softAssert.assertTrue(getMessageChangeStatusConfirmationPopupPopup().equals(waysToSaveTextProp.getPropValue("txtAlertStatusChangeMsg")),
                "Confirmation Message displayed on Change Status Confirmation Popup is not as Expected.");
        //Close Button
        softAssert.assertTrue(isBtnCloseChangeStatusConfirmationPopupVisible(), "Close Button on Change Status Confirmation Popup is not Visible.");
        softAssert.assertTrue(getLblCloseBtnChangeStatusConfirmationPopup().equals("Close"),
                "Close Button Label displayed on Change Status Confirmation Popup is not as Expected.");
        //Yes Button
        softAssert.assertTrue(isBtnYesChangeStatusConfirmationPopupVisible(), "Yes Button on Change Status Confirmation Popup is not Visible.");
        softAssert.assertTrue(getLblYesBtnCloseChangeStatusConfirmationPopup().equals("Yes"),
                "Yes Button Label displayed on Change Status Confirmation Popup is not as Expected.");
        clickBtnCloseChangeStatusConfirmationPopup();
    }

    public void verifyUploadFileUIObjectsOnPopup(SoftAssert softAssert) {
        //Image
        softAssert.assertTrue(isLblImageVisible(), "Image Field Label is not displayed on Add Ways To Save Popup for.");
        softAssert.assertTrue(getLblImage().equals("Image"), "Image Field Label Text is not as Expected on Add Ways To Save Popup for.");

        //Choose File Button
        softAssert.assertTrue(isbtnUploadFileVisible(), "Upload File Button is not displayed on Add Ways To Save Popup for.");
        softAssert.assertTrue(getLblBtnUploadFile().contains("Choose File"), "Upload File Button Label Text is not as Expected on Add Ways To Save Popup for.");

        //File Name Attached
        softAssert.assertTrue(isAttachedFileNameVisible(), "Attached File Name Label is not displayed on Add Ways To Save Popup for.");
        softAssert.assertTrue(getAttachedFileName().contains(waysToSaveTextProp.getPropValue("lblNoFileChosen")),
                "Attached File Name Label Text is not as Expected on Add Ways To Save Popup for.");

        //Allowed File Types
        softAssert.assertTrue(isLblAllowedFileTypesVisible(), "Allowed File Types Label is not displayed on Add Ways To Save Popup for.");
        softAssert.assertTrue(getLblAllowedFileTypes().contains(waysToSaveTextProp.getPropValue("lblAllowedFileTypes")), "Attached File Name Label Text is not " +
                "as Expected on Add Ways To Save Popup for.");
    }

    public void verifyUploadFileValidationsAddPopup(SoftAssert softAssert) {
        String toastMsg;

        //Verify Validation for Max. Size Limit
        addAttachmentToChooseFile(FILE_UPLOAD_PATH + "Flower 6MB.gif");
        toastMsg = getToastSuccessMessageWithoutCloseBtn();
        softAssert.assertEquals(toastMsg, waysToSaveTextProp.getPropValue("txtTstErrMsgMaxFileSize"),
                "Toast Error Message for Maximum File Size Attached is not as Expected.");
        waitForToastMessageInvisibility();
        clickBtnRemoveOnAddUpdatePopup();

        //Verify Validation for File Type Attached
        addAttachmentToChooseFile(FILE_UPLOAD_PATH + "index.html");
        toastMsg = getToastSuccessMessageWithoutCloseBtn();
        softAssert.assertEquals(toastMsg, waysToSaveTextProp.getPropValue("txtTstErrMsgInvalidFileType"),
                "Toast Error Message for Invalid File Type Attached is not as Expected.");
        waitForToastMessageInvisibility();
        clickBtnRemoveOnAddUpdatePopup();


        //Verify Validation for Multiple File Attached
        String fileName = FILE_UPLOAD_PATH + "meter-reading.jpg" + "\n" + FILE_UPLOAD_PATH + "Test.jpg";
        addAttachmentToChooseFile(fileName);
        toastMsg = getToastSuccessMessageWithoutCloseBtn();
        System.out.println("Toast Message for multiple files attached is " + toastMsg);
//        softAssert.assertEquals(toastMsg, waysToSaveTextProp.getPropValue("txtTstErrMsgInvalidFileType"),
//                "Toast Error Message for Invalid File Type Attached is not as Expected.");
//        waitForToastMessageInvisibility();
//        clickBtnRemoveOnAddUpdatePopup();


    }

    public void navigateViewPopupWaysToSave(SoftAssert softAssert,AdminWaysToSave adminWaysToSave){
        String topicName = adminWaysToSave.getTopicNameEnglish();
        clickNameForWaysToSave(topicName);
        waitForWaysToSaveViewPopupToVisible();
        waitForValueDescriptionWaysToSaveViewPopupToBeVisible();

//        softAssert.assertEquals(getWaysToSaveTitleViewPopup(),adminWaysToSave.getTopicNameEnglish(),
//                "Ways To Save Title displayed is not as Expected on the View Popup for the Topic "+topicName);
//
//
//
//        softAssert.assertTrue(getValueAddedWaysToSaveViewPopup().contains("0"),
//                "Added Count Value displayed is not as Expected on the View Popup for the Topic "+topicName);
//
//        softAssert.assertTrue(getValueViewedWaysToSaveViewPopup().contains("3"),
//                "Viewed Count value displayed is not as Expected on the View Popup for the Topic "+topicName);
//
//
//        softAssert.assertTrue(getValueSaveUptoWaysToSaveViewPopup().contains("45"),
//                "Save Upto value displayed is not as Expected on the View Popup for the Topic "+topicName);
//
//
//        softAssert.assertTrue(getValueDescriptionWaysToSaveViewPopup().contains("45"),
//                "Description Value displayed is not as Expected on the View Popup for the Topic "+topicName);

        clickBtnCloseXWaysToSaveViewPopup();
        waitForWaysToSaveViewPopupToInvisible();

    }

    public void verifyVewPopupUIObjects(SoftAssert softAssert,AdminWaysToSave adminWaysToSave)
    {
        String topicName = adminWaysToSave.getTopicNameEnglish();
        String category = adminWaysToSave.getCategory();

        clickNameForWaysToSave(topicName);
        waitForWaysToSaveViewPopupToVisible();
        waitForValueDescriptionWaysToSaveViewPopupToBeVisible();

        //Title
        softAssert.assertTrue(isWaysToSaveTitleViewPopupVisible(),"Ways To Save Title is not visible on the View Popup for the Topic "+topicName+" with Category as "+category);
        //Close Button
        softAssert.assertTrue(isBtnCloseXWaysToSaveViewPopupVisible(),"Close X button is not visible on the View Popup for the Topic "+topicName+" with Category as "+category);
        //Image
        softAssert.assertTrue(isImageWaysToSaveViewPopupVisible(),"Image is not visible on the View Popup for the Topic "+topicName+" with Category as "+category);

        //Added Count
        if(category.equals("Saving Tips")) {
            softAssert.assertTrue(isLblAddedWaysToSaveViewPopupVisible(), "Added Count Label is not visible on the View Popup for the Topic " + topicName + " with Category as " + category);
            softAssert.assertTrue(getLblAddedWaysToSaveViewPopup().contains("Added"),
                    "Added Count Label displayed is not as Expected on the View Popup for the Topic " + topicName + " with Category as " + category);
            softAssert.assertTrue(isValueAddedWaysToSaveViewPopupVisible(), "Added Count Value is not visible on the View Popup for the Topic " + topicName + " with Category as " + category);
        }
        //Viewed Count
        softAssert.assertTrue(isLblViewedWaysToSaveViewPopupVisible(),"Viewed Count Label is not visible on the View Popup for the Topic "+topicName+" with Category as "+category);
        softAssert.assertTrue(getLblViewedWaysToSaveViewPopup().contains("Viewed"),
                "Viewed Count Label displayed is not as Expected on the View Popup for the Topic "+topicName+" with Category as "+category);
        softAssert.assertTrue(isValueViewedWaysToSaveViewPopupVisible(),"Viewed Count value is not visible on the View Popup for the Topic "+topicName+" with Category as "+category);
        //Save Upto
        if(category.equals("Saving Tips") || category.equals("Rebates")) {
            softAssert.assertTrue(isLblSaveUptoWaysToSaveViewPopupVisible(), "Save Upto Label is not visible on the View Popup for the Topic " + topicName + " with Category as " + category);
            softAssert.assertTrue(getLblSaveUptoWaysToSaveViewPopup().contains("Save Up to"),
                    "Save Up to Label displayed is not as Expected on the View Popup for the Topic " + topicName + " with Category as " + category);
            softAssert.assertTrue(isValueSaveUptoWaysToSaveViewPopupVisible(), "Save Upto Value is not visible on the View Popup for the Topic " + topicName + " with Category as " + category);
        }
        //Description
        softAssert.assertTrue(isValueDescriptionWaysToSaveViewPopupVisible(),"Description Value is not visible on the View Popup for the Topic "+topicName+" with Category as "+category );

        clickBtnCloseXWaysToSaveViewPopup();
        waitForWaysToSaveViewPopupToInvisible();
    }

    public void verifyReadMoreExternalLinkFromScp(String waysToSaveName){
        PostLogEfficiencyPage postLogEfficiencyPage = new PostLogEfficiencyPage(driver);
        postLogEfficiencyPage.clickReadMoreForWaysToSaveName(waysToSaveName);
        waitForPageToLoad();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        switchToWindow(tabs.get(1));
        waitForPageToLoad();
        String[] split = getCurrentUrl().split("://");
        Assert.assertEquals("www.sew.ai/", split[1],"Read More Link from SCP Ways To Save Page is not re-directed to the right URL");
        switchToWindow(tabs.get(0));
        closeAllOtherWindows(driver, driver.getWindowHandle());
    }



    /****************************************************************************************************
     * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> REUSABLE METHODS <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< *
     *****************************************************************************************************/

    public Boolean isWaysToSavePage(String url, String title) {
        Boolean isWaysToSavePage = false;
        log.info("Checking that the current page is Ways To Save page.");
        if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
            isWaysToSavePage = true;
        log.info("The current page is Ways To Save page {}: " + isWaysToSavePage);
        return isWaysToSavePage;
    }

    public void clearAccountTypeDropdownSelection() {
        String selectedValue = getSelectedOptionAccountType();
        clickAccountTypeDropdown();
        switch (selectedValue) {
            case "Residential":
                selectOptResAccountType();
                clickAccountTypeDropdown();
                break;
            case "Commercial":
                selectOptCommAccountType();
                clickAccountTypeDropdown();
                break;
            case "Multi-Family":
                selectOptMultiFamilyAccountType();
                clickAccountTypeDropdown();
                break;
            default:
                System.out.println("The Account Type Dropdown value has not been cleared for selectedValue " + selectedValue);
        }
    }

    public void selectAccountTypeDropdown(String selectValue) {
        clickAccountTypeDropdown();
        switch (selectValue) {            case "Residential":
                selectOptResAccountType();
                clickAccountTypeDropdown();
                break;
            case "Commercial":
                selectOptCommAccountType();
                clickAccountTypeDropdown();
                break;
            case "Multi-Family":
                selectOptMultiFamilyAccountType();
                clickAccountTypeDropdown();
                break;
            default:
                System.out.println("There is no valid Account Dropdown present with the provide value " + selectValue);
        }
    }

    public void clearAddOrUpdateWaysToSavePopUpFieldValues() {
        selectCategoryOnAddWaysToSavePopup(waysToSaveTextProp.getPropValue("defaultSelectDropdownValue"));
        selectMeterTypeOnAddWaysToSavePopup(waysToSaveTextProp.getPropValue("defaultSelectDropdownValue"));
        clearAccountTypeDropdownSelection();
//        unchkCheckBoxInternal();
        clearTxtBoxTopicNameEnglish();
        clearTxtBoxDescEnglish();
        if (isBtnRemoveOnAddUpdatePopupVisible())
            clickBtnRemoveOnAddUpdatePopup();
        if (isTxtBoxExternalLinkVisible())
            clearTxtBoxExternalLink();
        if (isTxtBoxAnnualSavingVisible())
            clearTxtBoxAnnualSaving();
    }

    public void navUpdateWaysToSavePopupPageOfName(String waysToSaveName) {
        clickKebabOptionForWaysToSaveName(waysToSaveName);
        clickEditOptionForWaysToSaveName(waysToSaveName);
        waitForBtnAddOnAddWaysToSavePopupToBeVisible();
    }

    public void navDeleteWaysToSavePopup(String waysToSaveName) {
        clickKebabOptionForWaysToSaveName(waysToSaveName);
        clickDeleteOptionForWaysToSaveName(waysToSaveName);
        waitForBtnYesDeleteConfirmationPopupToBeVisible();
    }

    public void navigateAddWaysToSavePopup() {
        clickBtnAddEfficiency();
        waitForBtnAddOnAddWaysToSavePopupToBeVisible();
    }

    public static int getCategoryId(String categoryName) {
        int categoryId = 0;
        switch (categoryName) {
            case "Saving Tips":
                categoryId = 1;
                break;
            case "Educational Tips":
                categoryId = 2;
                break;
            case "Rebates":
                categoryId = 3;
                break;
            case "Programs":
                categoryId = 4;
                break;
            default:
                System.out.println(categoryName + "is not a valid category Name to get the Category ID.");

        }
        return categoryId;
    }

    public static String getCategoryName(String categoryId) {
        String categoryName = null;
        switch (categoryId) {
            case "1":
                categoryName = "Saving Tips";
                break;
            case "2":
                categoryName = "Educational Tips";
                break;
            case "3":
                categoryName = "Rebates";
                break;
            case "4":
                categoryName = "Programs";
                break;
            default:
                System.out.println(categoryId + "is not a valid category ID to get the Category Name.");

        }
        return categoryName;
    }

    public int getAccountTypeId(String accountType) {
        int accountTypeId = 0;
        switch (accountType) {
            case "Residential":
                accountTypeId = 1;
                break;
            case "Commercial":
                accountTypeId = 2;
                break;
            case "Multi-Family":
                accountTypeId = 3;
                break;
            default:
                System.out.println(accountType + "is not a valid AccountType to get the Account Type ID.");
        }
        return accountTypeId;
    }

    public String getAccountTypeName(String accountTypeId) {
        String accountTypeName = null;
        switch (accountTypeId) {
            case "1":
                accountTypeName = "Residential";
                break;
            case "2":
                accountTypeName = "Commercial";
                break;
            case "3":
                accountTypeName = "Multi-Family";
                break;
            default:
                System.out.println(accountTypeId + "is not a valid AccountTypeID to get the Account Type Name.");
        }
        return accountTypeName;
    }

    public int getMeterTypeId(String meterType) {
        int meterTypeId = 0;
        switch (meterType) {
            case "Power":
                meterTypeId = 1;
                break;
            case "Water":
                meterTypeId = 2;
                break;
            case "Gas":
                meterTypeId = 3;
                break;
            case "Solar":
                meterTypeId = 4;
                break;
            default:
                System.out.println(meterType + "is not a valid MeterType to get the Meter Type ID.");
        }
        return meterTypeId;
    }

    public String getMeterTypeName(String meterTypeId) {
        String meterTypeName = null;
        switch (meterTypeId) {
            case "1":
                meterTypeName = "Power";
                break;
            case "2":
                meterTypeName = "Water";
                break;
            case "3":
                meterTypeName = "Gas";
                break;
            case "4":
                meterTypeName = "Solar";
                break;
            default:
                System.out.println(meterTypeId + "is not a valid MeterTypeId to get the Meter Type Name.");
        }
        return meterTypeName;
    }

    public void selectWaysToSaveFilter(String categoryName) {
        selectWaysToSaveFilterDropdown(categoryName);
        pause(2000);
    }

    public static Map<String, String> getWaysToSaveDetailsFromDB(AdminWaysToSave adminWaysToSave) {
        String categoryTypeId = String.valueOf(getCategoryId(adminWaysToSave.getCategory()));
        String waysToSaveTopicName = adminWaysToSave.getTopicNameEnglish();

        Map<String, String> waysToSaveDetailsDB = null;
        try {
            ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.getWaysToSaveDetails(categoryTypeId, waysToSaveTopicName));
            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();
            ArrayList<String> columns = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metadata.getColumnName(i);
                columns.add(columnName);
            }
            waysToSaveDetailsDB = new LinkedHashMap<>();
            while (rs.next()) {
                for (String columnName : columns) {
                    String value = rs.getString(columnName);
                    waysToSaveDetailsDB.put(columnName, value);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return waysToSaveDetailsDB;

    }

    public int getTotalEntriesOnWaysToSavePage() {
        int totalEntries = Integer.parseInt(getLblShowingEntries().split("of")[1].replace("entries", "").trim());
        return totalEntries;
    }

    public void verifyUpdateWaysToSaveSuccessTstMsg(String categoryName) {
        String toastMsg;
        if (categoryName.equals("Saving Tips")) {
            toastMsg = getToastSuccessMessageWithoutCloseBtn();
            Assert.assertEquals(toastMsg, waysToSaveTextProp.getPropValue("txtAlertSavingTipUpdatedSucessMsg"),
                    "Saving Tips Updated Success Toast Message is not as Expected.");
            waitForToastMessageInvisibility();
        } else if (categoryName.equals("Educational Tips")) {
            toastMsg = getToastSuccessMessageWithoutCloseBtn();
            Assert.assertEquals(toastMsg, waysToSaveTextProp.getPropValue("txtAlertEducationalTipUpdatedSucessMsg"),
                    "Educational Tips Updated Success Toast Message is not as Expected.");
            waitForToastMessageInvisibility();
        } else if (categoryName.equals("Rebates")) {
            toastMsg = getToastSuccessMessageWithoutCloseBtn();
            Assert.assertEquals(toastMsg, waysToSaveTextProp.getPropValue("txtAlertRebateupdatedSucessMsg"),
                    "Rebates Updated Success Toast Message is not as Expected.");
            waitForToastMessageInvisibility();
        } else if (categoryName.equals("Programs")) {
            toastMsg = getToastSuccessMessageWithoutCloseBtn();
            Assert.assertEquals(toastMsg, waysToSaveTextProp.getPropValue("txtAlertProgramUpdatedSucessMsg"),
                    "Programs Updated Success Toast Message is not as Expected.");
            waitForToastMessageInvisibility();
        }

    }

    public void verifyDeleteWaysToSaveSuccessTstMsg(String categoryName) {
        String toastMsg;
        if (categoryName.equals("Saving Tips")) {
            toastMsg = getToastSuccessMessageWithoutCloseBtn();
            Assert.assertEquals(toastMsg, waysToSaveTextProp.getPropValue("txtSuccessMsgDeleteSavingTip"),
                    "Saving Tips Deleted Success Toast Message is not as Expected.");
            waitForToastMessageInvisibility();
        } else if (categoryName.equals("Educational Tips")) {
            toastMsg = getToastSuccessMessageWithoutCloseBtn();
            Assert.assertEquals(toastMsg, waysToSaveTextProp.getPropValue("txtSuccessMsgDeleteEducationalTip"),
                    "Educational Tips Deleted Success Toast Message is not as Expected.");
            waitForToastMessageInvisibility();
        } else if (categoryName.equals("Rebates")) {
            toastMsg = getToastSuccessMessageWithoutCloseBtn();
            Assert.assertEquals(toastMsg, waysToSaveTextProp.getPropValue("txtSuccessMsgDeleteRebate"),
                    "Rebates Deleted Success Toast Message is not as Expected.");
            waitForToastMessageInvisibility();
        } else if (categoryName.equals("Programs")) {
            toastMsg = getToastSuccessMessageWithoutCloseBtn();
            Assert.assertEquals(toastMsg, waysToSaveTextProp.getPropValue("txtSuccessMsgDeleteProgram"),
                    "Programs Deleted Success Toast Message is not as Expected.");
            waitForToastMessageInvisibility();
        }

    }

    public String getFirstWaysToSaveRecordFromDB(String categoryId) {
        String title = null;
        try {
            ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.getFirstWaysToSaveRecordFromDb(categoryId));
            while (rs.next()) {
                title = rs.getString("Title");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return title;

    }

    public void loginToScpAndNavigateToWaysToSave(String userName, String password) {
        deleteCookies();
        DriverFactory.goToPage(Configuration.toString("scp.url"));
        LoginSteps loginSteps = new LoginSteps(driver);
        loginSteps.loginIntoTheApplication(userName, password);
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.clickLnkWaysToSave();
        dashboardPage.waitForLnkWaysToSaveOverviewToBeVisible();
        dashboardPage.clickLnkWaysToSaveOverview();
        waitForPageLoaderInvisibility();
    }

    public void navToScpPostLogWaysToSaveTab(String tabName) {
        PostLogEfficiencyPage postLogEfficiencyPage = new PostLogEfficiencyPage(driver);
        switch (tabName) {
            case "Saving Tips":
                postLogEfficiencyPage.clickLnkPostLoginEfficiencySavingTipsTabIcon();
                waitForPageLoaderInvisibility();
                break;
            case "Educational Tips":
                postLogEfficiencyPage.clickLnkPostLoginEfficiencyEducationalTipsTab();
                waitForPageLoaderInvisibility();
                break;
            case "Programs":
                postLogEfficiencyPage.clicklnkPostLoginEfficiencyProgramsTab();
                waitForPageLoaderInvisibility();
                break;
            case "Rebates":
                postLogEfficiencyPage.clickLnkPostLoginEfficiencyRebatesTab();
                waitForPageLoaderInvisibility();
                break;
            default:
                System.out.println(tabName + " tab Name is not available on Ways to Save page to navigate.");
        }

    }

    public void tearDown(String waysToSaveName) {
        DataBaseUtils.executeUpdateDeleteSQLQuery(SQLQueries.deleteWaysToSave(waysToSaveName));
    }


}

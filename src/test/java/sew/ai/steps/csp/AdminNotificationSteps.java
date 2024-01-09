package sew.ai.steps.csp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import sew.ai.api.endpoints.auth.LoginSupportEndpoint;
import sew.ai.api.endpoints.connectme.ConnectMeEndpoints;
import sew.ai.api.endpoints.services.ServiceEndpoint;
import sew.ai.api.pojos.loginHelp.ProblemSignInPayload;
import sew.ai.config.Configuration;
import sew.ai.driver.DriverFactory;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.models.ConnectMe;
import sew.ai.models.Services;
import sew.ai.models.User;
import sew.ai.pageObjects.csp.AdminCustomerPage;
import sew.ai.pageObjects.csp.AdminNotificationPage;
import sew.ai.pageObjects.scp.DashboardPage;
import sew.ai.pageObjects.scp.NotificationPage;
import sew.ai.utils.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static sew.ai.helpers.props.FilePaths.FILE_UPLOAD_PATH;

public class AdminNotificationSteps extends AdminNotificationPage {
    private static final Logger log = LogManager.getLogger(AdminNotificationSteps.class);
    public static PropertiesUtil adminNotificationTextProp;

    public AdminNotificationSteps(WebDriver driver) {
        super(driver);
        adminNotificationTextProp = new PropertiesUtil(FilePaths.CSP_TEXT_PROPERTIES +
                Constants.ADMIN_NOTIFICATION_TXT_FILENAME);
    }

    public void verifyNotificationSubTabs(SoftAssert softAssert) {
        softAssert.assertTrue(isLnkInboxVisible(), "Inbox Link is not visible on the Notification Page.");
        softAssert.assertTrue(isLnkConnectMeVisible(), "ConnectMe Link is not visible on the Notification Page.");
        softAssert.assertTrue(isLnkBillingVisible(), "Billing Link is not visible on the Notification Page.");
        softAssert.assertTrue(isLnkOutageVisible(), "Outage Link is not visible on the Notification Page.");
        softAssert.assertTrue(isLnkServicesVisible(), "Services Link is not visible on the Notification Page.");
        softAssert.assertTrue(isLnkDemandResponseVisible(), "DemandResponse Link is not visible on the Notification Page.");
        softAssert.assertTrue(isLnkLeakAlertVisible(), "LeakAlert Link is not visible on the Notification Page.");
        softAssert.assertTrue(isLnkLoginIssuesVisible(), "LoginIssues Link is not visible on the Notification Page.");
        softAssert.assertTrue(isLnkSentVisible(), "Sent Link is not visible on the Notification Page.");
        softAssert.assertTrue(isLnkCompleteVisible(), "Complete Link is not visible on the Notification Page.");
        softAssert.assertTrue(isLnkSavedMailVisible(), "SavedMail Link is not visible on the Notification Page.");
        softAssert.assertTrue(isLnkTrashVisible(), "Trash Link is not visible on the Notification Page.");
        softAssert.assertTrue(isLnkAutoResponseVisible(), "Auto-Response Link is not visible on the Notification Page.");
        softAssert.assertTrue(isLnkSendNotificationVisible(), "Trash Link is not visible on the Notification Page.");
    }

    public void verifyNotificationTableHeaders(String moduleName, SoftAssert softAssert) {
        navigateSubModule(moduleName);
        waitForLstSubjectToBeVisible();
        softAssert.assertTrue(isFromGridHeaderVisible(), "From Grid Header Label is not visible on the sub-module " + moduleName);
        softAssert.assertTrue(isSubjectGridHeaderVisible(), "Subject Grid Header Label is not visible on the sub-module " + moduleName);
        softAssert.assertTrue(isDateGridHeaderVisible(), "Date Grid Header Label is not visible on the sub-module " + moduleName);
        softAssert.assertEquals(getLblFromGridHeader(), "FROM", "From Grid Header Label is not as expected on the sub-module " + moduleName);
        softAssert.assertEquals(getLblSubjectGridHeader(), "SUBJECT", "Subject Grid Header Label is not as expected on the sub-module " + moduleName);
        softAssert.assertEquals(getLblDateGridHeader(), "DATE", "Date Grid Header Label is not as expected on the sub-module " + moduleName);
    }

    public void verifyMessageDetailsNotificationObjects(SoftAssert softAssert) {
        softAssert.assertTrue(isLblFromMessageDetailsVisible(), "From label is not displayed in msg details");
        softAssert.assertTrue(getLblValueFromMessageDetails().equalsIgnoreCase("From:"), "From label Text is not as Expected in msg details");
        softAssert.assertTrue(isFromFieldValueMsgDetailVisible(), "From Value is not displayed in msg details");
        softAssert.assertTrue(isLblSubjectMessageDetailsVisible(), "Subject label is not displayed in msg details");
        softAssert.assertTrue(getLblSubjectMessageDetails().equalsIgnoreCase("Subject:"), "Subject label Text is not as Expected in msg details");
        softAssert.assertTrue(isSubjectFieldValueMsgDetailVisible(), "Subject value is not displayed in msg details");
        softAssert.assertTrue(isLblMessageMsgDetailsVisible(), "Message label is not displayed in msg details");
        softAssert.assertTrue(getLblMessageMsgDetails().equalsIgnoreCase("Message:"), "Message label Text is not as Expected in msg details");
        softAssert.assertTrue(isMessageFieldValueMsgDetailVisible(), "Message value is not displayed in msg details");
        softAssert.assertTrue(isLblDateMessageDetailsVisible(), "Date label is not displayed in msg details");
        softAssert.assertTrue(getLblDateMessageDetails().equalsIgnoreCase("Date:"), "Date label Text is not as Expected in msg details");
        softAssert.assertTrue(isDateFieldValueMsgDetailVisible(), "Date value is not displayed in msg details");
    }

    public void verifyMessageDetailsValues(SoftAssert softAssert, String notificationID, String emailID) {
        softAssert.assertTrue(getFromFieldValueMsgDetail().equals(emailID), "From Field Value is not as Expected in msg details");
        softAssert.assertTrue(getSubjectFieldValueMsgDetail().contains(notificationID), "Subject Field Value is not having the request ID in msg details");
        softAssert.assertTrue(getMessageFieldValueMsgDetail().contains(notificationID), "Message Field Value is not having the request ID in msg details");
        System.out.println("The Current Date Time " + DateUtil.getCurrentSystemDate("MMM dd, yyyy"));
    }

    public void verifyReadUnreadPropertiesOfNotification(SoftAssert softAssert) {
        Integer connectMeUnreadBeforeClick = Integer.parseInt(getConnectMeLinkLbl().split("\\R")[1]);
        List<WebElement> lstUnreadNotifications = getLstUnreadNotifications();
        WebElement e = lstUnreadNotifications.get(0);
        String fontFamilyUnread = e.getCssValue("font-family");
        softAssert.assertTrue(fontFamilyUnread.contains("Semibold"),
                "New Unread message is not highlighted in Bold Font");
        clickLstUnreadNotifications(0);
        waitForImgLoadingInvisibility();
        clickBtnBackMail();
        String fontFamilyread = e.getCssValue("font-family");
        softAssert.assertTrue(fontFamilyread.contains("Regular"), "Old Read message is not in Regular Non-Bold Font");
        Integer connectMeUnreadAfterClick = Integer.parseInt(getConnectMeLinkLbl().split("\\R")[1]);
        softAssert.assertTrue(connectMeUnreadAfterClick.equals(connectMeUnreadBeforeClick - 1), "Unread msg count is not reduced after read");
    }

    public void verifyCheckBoxOnNotifications(SoftAssert softAssert, String moduleName) {
        navigateSubModule(moduleName);
        if (!isLblNoDataInModuleVisible()) {
            scrollToTheBottomOfPage();
            int pageMailCount = Integer.parseInt(getLblNotificationCountPagination().split("of")[0].trim().split("-")[1].trim());
            int noOfCheckboxes = getLstCheckboxesNotifications().size();
            softAssert.assertEquals(noOfCheckboxes, pageMailCount,
                    "Checkbox count is not as per pagination on the Submodule " + moduleName);
            softAssert.assertTrue(isChkboxCheckAllVisible(), "CheckBox on Header is not present on the Submodule");
        }
        else
            log.info("There is no Data present on the the Submodule " + moduleName);
    }

    public void verifyNotificationSortingGridHeaders(SoftAssert softAssert) {
        //Sortable Grid Headers.
        List<WebElement> notificationSortGridHeaders = getLstNotificationSortGridHeaders();
        for (int i = 0; i < notificationSortGridHeaders.size(); i++) {
            if (i != 0) {
                clickLstNotificationSortGridHeaders(i);
                waitForImgLoadingInvisibility();
            }
            softAssert.assertTrue(getAttribute(notificationSortGridHeaders.get(i), "class").contains("asc"),
                    "Ascending Sort is not working on Notification Page for header " + getAttribute(notificationSortGridHeaders.get(i), "id"));
            clickLstNotificationSortGridHeaders(i);
            waitForImgLoadingInvisibility();
            softAssert.assertTrue(getAttribute(notificationSortGridHeaders.get(i), "class").contains("desc"),
                    "Descending Sort is not working on Notification Page for header " + getAttribute(notificationSortGridHeaders.get(i), "id"));
        }
    }

    public void verifyFromColumnNotifications(SoftAssert softAssert, String moduleName) {
        navigateSubModule(moduleName);
        if (!isLblNoDataInModuleVisible()) {
            boolean isFromColHavingEmailID = true;
            boolean isFromColNotBlank = true;
//            List<WebElement> fromDataList = getLstFromColumnData();
//            for (WebElement ele : fromDataList) {
//                if (ele.getText().equals("")) {
//                    isFromColNotBlank = false;
//                    break;
//                }
//                if (!ele.getText().contains("@")) {
//                    isFromColHavingEmailID = false;
//                    break;
//                }
//            }
            isFromColNotBlank = getLstFromColumnData().stream().noneMatch(s -> s.getText().equals(""));
            isFromColHavingEmailID = getLstFromColumnData().stream().allMatch(s -> s.getText().contains("@"));
            softAssert.assertTrue(isFromColHavingEmailID, "From column data for the " + moduleName + " submodule is not containing the Email ID.");
            softAssert.assertTrue(isFromColNotBlank, "From column data for the " + moduleName + " submodule is blank.");
        }
        else
            log.info("There is no Data present on the the Submodule " + moduleName);
    }

    public void verifyNoDataLabelDisplayOnNotifySubModule(SoftAssert softAssert, String moduleName) {
        navigateSubModule(moduleName);
        if (isLblNoDataInModuleVisible()) {
            String lblNoDataInModuleDetail = getLblNoDataInModuleDetail();
            System.out.println("The No Data Label Text displayed is " + lblNoDataInModuleDetail);
//            softAssert.assertEquals(lblNoDataInModuleDetail,adminNotificationTextProp.getPropValue("txtNoDataInModule"), //todo Uncomment this after the text encoding issue resolves.
//                    "Label Text Displayed for No Data available in "+moduleName+"submodule is not as Expected.");
            softAssert.assertEquals(lblNoDataInModuleDetail, "We didn’t find anything to show here.",
                    "Label Text Displayed for No Data available in " + moduleName + "submodule is not as Expected.");
        }
        else
            log.info("There is some Data present on the the Submodule " + moduleName);
    }

    public void verifyCustomerInformationHeaderObjects(SoftAssert softAssert) {
        softAssert.assertTrue(isLblCustomerNameNotifyHeaderVisible(),
                "Customer Name Label is not visible on Customer Information section of Notifications");
        softAssert.assertTrue(isLblServiceAccountNotifyHeaderVisible(),
                "Service Account Number Label is not visible on Customer Information section of Notifications");
        softAssert.assertTrue(isLblMobileNumberNotifyHeaderVisible(),
                "Mobile Number Label is not visible on Customer Information section of Notifications");
        softAssert.assertTrue(getLblCustomerNameNotifyHeaderLbl().contains(adminNotificationTextProp.getPropValue("txtLblCustomerNameInformationHeader")),
                "Customer Name Label is not visible on Customer Information section of Notifications");
        softAssert.assertTrue(getLblServiceAccountNotifyHeaderLbl().contains(adminNotificationTextProp.getPropValue("txtLblServiceAcctInformationHeader")),
                "Service Account Number Label is not visible on Customer Information section of Notifications");
        softAssert.assertTrue(getLblMobileNumberNotifyHeaderLbl().contains(adminNotificationTextProp.getPropValue("txtLblMobileNumberInformationHeader")),
                "Mobile Number Label is not visible on Customer Information section of Notifications");
    }

    public void verifyCustomerNameMobileHeaderFieldValuesNotClickable(SoftAssert softAssert, String moduleName, String notificationID) {
        navigateSubModule(moduleName);
        navigateToNotificationByRequestId(notificationID);
        verifyCustomerInformationHeaderObjects(softAssert);
        softAssert.assertTrue(getAttribute(getWebElementTxtCustomerNameNotifyHeader(), "data-backdrop").contains("static"));
        softAssert.assertTrue(getAttribute(getWebElementTxtMobileNotifyHeader(), "style").contains("cursor: default"));
    }

    public void verifySearchByObjects(String subModuleName) {
        navigateSubModule(subModuleName);
        Assert.assertTrue(isDropdownSearchByVisible(), "Search By Dropdown is not visible on the " + subModuleName + " submodule.");
        Assert.assertTrue(isSearchByTextBoxVisible(), "Search By TextBox is not visible on the " + subModuleName + " submodule.");
        Assert.assertTrue(isBtnSearchVisible(), "Search Button is not visible on the " + subModuleName + " submodule.");
        Assert.assertTrue(isBtnClearVisible(), "Clear Button is not visible on the " + subModuleName + " submodule.");
        List<String> searchByDropdownValues = getLstSearchByDropdownOptions();
        Assert.assertTrue(searchByDropdownValues.contains("Email"), "Email Dropdown Value is not present in SearchBy dropdown");
        Assert.assertTrue(searchByDropdownValues.contains("Service Account"), "Email Dropdown Value is not present in SearchBy dropdown");
    }

    public void verifySearchByEmailSearchFunctionality(SoftAssert softAssert, String searchValue) {
        selectSearchByDropdown("Email");
        populateSearchByTextBox(searchValue);
        clickBtnSearch();
        waitForImgLoadingInvisibility();
        boolean searchResults = true;
//        List<WebElement> lstFromColumnData = getLstFromColumnData();
//        for (WebElement e : lstFromColumnData) {
//            if (!e.getText().contains(searchValue)) {
//                searchResults = false;
//                break;
//            }
//        }
        searchResults = getLstFromColumnData().stream().allMatch(s -> s.getText().contains(searchValue));
        softAssert.assertTrue(searchResults, "Search Results not containing the Email Searched.");
    }

    public void verifySearchByServiceAcctSearchFunctionality(SoftAssert softAssert, String searchValue, String notificationID) {
        selectSearchByDropdown("Service Account");
        populateSearchByTextBox(searchValue);
        clickBtnSearch();
        waitForImgLoadingInvisibility();
        navigateToNotificationByRequestId(notificationID);
        softAssert.assertTrue(getTxtServiceAccountNotifyHeaderLbl().contains(searchValue), "Search Results not containing the Account Number Searched.");
    }

    public void verifySearchByValidationMessages(SoftAssert softAssert) {
        String toastMsg;
        //Verify the Toast Message without selecting the SearchBy dropdown
        clickBtnSearch();
        toastMsg = getToastSuccessMessageWithoutCloseBtn();
        softAssert.assertEquals(toastMsg, adminNotificationTextProp.getPropValue("txtToastMsgSearchByMandatory"),
                "Search By Field Mandatory Toast Message is not as Expected.");
        waitForToastMessageInvisibility();
        //Verify the Toast Message with blank SearchBy Email TextBox
        selectSearchByDropdown("Email");
        clearSearchByTextBox();
        clickBtnSearch();
        toastMsg = getToastSuccessMessageWithoutCloseBtn();
        softAssert.assertEquals(toastMsg, adminNotificationTextProp.getPropValue("txtToastMsgEmailMandatory"),
                "SearchBy Email for Blank Text Box Toast Message is not as Expected.");
        waitForToastMessageInvisibility();
        //Verify the Toast Message with blank SearchBy Service Account TextBox
        clearSearchResults();
        selectSearchByDropdown("Service Account");
        clearSearchByTextBox();
        clickBtnSearch();
        toastMsg = getToastSuccessMessageWithoutCloseBtn();
        softAssert.assertEquals(toastMsg, adminNotificationTextProp.getPropValue("txtToastServiceAccountMandatoryMsg"),
                "SearchBy ServiceAccount for Blank Text Box Toast Message is not as Expected.");
        waitForToastMessageInvisibility();
        //Verify the Toast Message with Invalid data for SearchBy Email TextBox
        clearSearchResults();
        selectSearchByDropdown("Email");
        clearSearchByTextBox();
        populateSearchByTextBox("xyz@abc.in/com");
        clickBtnSearch();
        toastMsg = getToastSuccessMessageWithoutCloseBtn();
        softAssert.assertEquals(toastMsg, adminNotificationTextProp.getPropValue("txtToastMsgInvalidEmail"),
                "SearchBy Invalid Email Toast Message is not as Expected.");
        waitForToastMessageInvisibility();
        //Verify the Toast Message with Invalid data for SearchBy Service Account TextBox
        clearSearchResults();
        selectSearchByDropdown("Service Account");
        clearSearchByTextBox();
        populateSearchByTextBox("xyz@abc.in/com");
        clickBtnSearch();
        toastMsg = getToastSuccessMessageWithoutCloseBtn();
        softAssert.assertEquals(toastMsg, adminNotificationTextProp.getPropValue("txtToastInvalidServiceAccountMsg"),
                "SearchBy Invalid Service Account Toast Message is not as Expected.");
        waitForToastMessageInvisibility();
        //Verify the Toast Message for SearchBy Email with no Data Available for Email searched.
        clearSearchResults();
        selectSearchByDropdown("Email");
        clearSearchByTextBox();
        populateSearchByTextBox("xyz123@abc.in");
        clickBtnSearch();
        waitForImgLoadingInvisibility();
//        softAssert.assertEquals(getLblNoDataInModuleDetail(), adminNotificationTextProp.getPropValue("txtNoDataInModule"),
//                "SearchBy Email having no search results Message is not as Expected.");
        softAssert.assertEquals(getLblNoDataInModuleDetail(), "We didn’t find anything to show here.", //todo remove this hardcoded after encoding issue resolves.
                "SearchBy Email having no search results Message is not as Expected.");
        //Verify the Toast Message for SearchBy Service Account with no Data Available for Service Account Number searched.
        clearSearchResults();
        selectSearchByDropdown("Service Account");
        clearSearchByTextBox();
        populateSearchByTextBox("1234567890");
        clickBtnSearch();
        waitForImgLoadingInvisibility();
//        softAssert.assertEquals(getLblNoDataInModuleDetail(), adminNotificationTextProp.getPropValue("txtNoDataInModule"),
//                "SearchBy Email having no search results Message is not as Expected.");//todo SCM10-11387
        waitForToastMessageInvisibility();
    }

    public void verifyClearBtnFunctionality(SoftAssert softAssert) {
        //SearchBy Email - Clear Button
        selectSearchByDropdown("Email");
        populateSearchByTextBox("abc@gmail.com");
        clearSearchResults();
        softAssert.assertTrue(getSelectOptionSearchBy().equals("--Select--"), "Clicking clear button not clearing Search By Email dropdown value.");
        softAssert.assertTrue(getSearchByTextBoxValue().equals(""), "Clicking clear button not clearing Search By Email TextBox value.");
        //SearchBy Service Account - Clear Button
        selectSearchByDropdown("Service Account");
        populateSearchByTextBox(Configuration.toString("utilityAccountNumber"));
        clearSearchResults();
        softAssert.assertTrue(getSelectOptionSearchBy().equals("--Select--"), "Clicking clear button not clearing Search By Service Account dropdown value.");
        softAssert.assertTrue(getSearchByTextBoxValue().equals(""), "Clicking clear button not clearing Search By Service Account TextBox value.");
    }

    public void verifyNotifyCustHeadersInfoWithC360Popup(SoftAssert softAssert, String moduleName, String notificationID) {
        AdminCustomerPage adminCustomerPage;
        adminCustomerPage = new AdminCustomerPage(driver);
        navigateSubModule(moduleName);
        navigateToNotificationByRequestId(notificationID);
        String custNameNotiHeader = getTxtCustomerNameNotifyHeaderLbl();
        String serviceAcctNotiHeader = getTxtServiceAccountNotifyHeaderLbl();
        String mobileNoNotiHeader = getTxtMobileNoMailLbl().replaceAll("-", "");
        System.out.println("The Customer Information on Notification message Header is " + custNameNotiHeader + "\t" + serviceAcctNotiHeader + "\t" + mobileNoNotiHeader);
        clickTxtServiceAccountNoMail();
        switchToFrame(0);
        String custNameC360Page = adminCustomerPage.getTxtNameCustomerDetailsPt();
        String serviceAcctC360Page = adminCustomerPage.getTxtCustomerAcctNoCustomerDetailsPt();
        String mobileNoC360Page = adminCustomerPage.getTxtCustomerEmailPhoneCustomerDetailsPt().split("/")[1].replaceAll("-", "").trim();
        System.out.println("The Customer Information on Customer360 Page is " + custNameC360Page + "\t" + serviceAcctC360Page + "\t" + mobileNoC360Page);
        softAssert.assertEquals(custNameC360Page, custNameNotiHeader,
                "Customer Name from Notification Info Header is not matching with C360 Page details.");
        softAssert.assertEquals(serviceAcctC360Page, serviceAcctNotiHeader,
                "Service Account from Notification Info Header is not matching with C360 Page details.");
        softAssert.assertEquals(mobileNoC360Page, mobileNoNotiHeader,
                "Mobile Number from Notification Info Header is not matching with C360 Page details.");
        adminCustomerPage.clickLnkBackToCSRSearch();
        switchToDefaultContent();
    }

    public void verifySearchResultsRefreshForSearchData(SoftAssert softAssert, String moduleName) {
        navigateSubModule(moduleName);
        List<WebElement> lstFromColumnData = getLstFromColumnData();
        Set<String> fromEmail = new HashSet<String>();
        for (WebElement e : lstFromColumnData) {
            if (!e.getText().equals("") || !e.getText().equals("N/A")) {
                fromEmail.add(e.getText().trim().replace("(1)", "").replace("(2)", ""));
            }
        }
        List<String> finalLstFrom = new ArrayList<>(fromEmail);
        int previousSearchResultSize = 0;
        for (int i = 0; i < finalLstFrom.size(); i++) {
            selectSearchByDropdown("Email");
            populateSearchByTextBox(finalLstFrom.get(i).trim());
            clickBtnSearch();
            waitForImgLoadingInvisibility();
            waitForLblNotificationCountPaginationToBeVisible();
            int searchResultSize = Integer.parseInt(getLblNotificationCountPagination().split("of")[1].trim());
            softAssert.assertFalse(previousSearchResultSize == searchResultSize, "Search Result Count is matching with previous Search Results count");
            previousSearchResultSize = searchResultSize;
            if (i == 2)
                break;
        }
    }

    public void verifyNewUnreadNotificationsFontProperty(SoftAssert softAssert, String moduleName) {
        navigateSubModule(moduleName);
        boolean unreadAndFontCheckFlag = true;
//        List<WebElement> lstUnreadNotificationsRows = getLstUnreadNotificationsRows();
//        for (WebElement e : lstUnreadNotificationsRows) {
//            if (!e.getAttribute("class").equals("unread") && !e.getCssValue("font-family").contains("Semibold")) {
//                unreadAndFontCheckFlag = false;
//                break;
//            }
//        }
        unreadAndFontCheckFlag = getLstUnreadNotificationsRows().stream().allMatch(s -> s.getAttribute("class").equals("unread") &&
                s.getCssValue("font-family").contains("Semibold"));
        softAssert.assertTrue(unreadAndFontCheckFlag, "Read notifications are present on the Unread default notifications view" +
                "of the " + moduleName + "submodule.");
    }

    public void verifyReadNotificationFontProperty(SoftAssert softAssert, String moduleName) {
        navigateSubModule(moduleName);
        boolean readAndFontCheckFlag = false;
//        List<WebElement> lstReadNotificationsRows = getLstReadNotificationsRows();
//        for (WebElement e : lstReadNotificationsRows) {
//            if (!e.getAttribute("class").equals("unread") && e.getCssValue("font-family").contains("Regular")) {
//                readAndFontCheckFlag = true;
//                break;
//            }
//        }
        readAndFontCheckFlag = getLstReadNotificationsRows().stream().anyMatch(s -> !s.getAttribute("class").equals("unread") &&
                s.getCssValue("font-family").contains("Regular"));
        softAssert.assertTrue(readAndFontCheckFlag, "Read notifications are not present on the All notifications view" +
                "of the " + moduleName + "submodule.");
    }

    public void verifyRepliedNotificationPresent(SoftAssert softAssert, String moduleName) {
        navigateSubModule(moduleName);
        boolean isRepliedNotificationsPresent = false;
//        List<WebElement> lstFromColumnData = getLstFromColumnData();
//        for (WebElement e : lstFromColumnData) {
//            if (e.getText().contains("(1)")) {
//                isRepliedNotificationsPresent = true;
//                break;
//            }
//        }
        isRepliedNotificationsPresent = getLstFromColumnData().stream().anyMatch(s -> s.getText().contains("(1)"));
        softAssert.assertTrue(isRepliedNotificationsPresent, "Replied notifications was not present on the " + moduleName + "sub-module.");
    }

    public void sendReplyToFirstNotifications(SoftAssert softAssert, String moduleName, String replyText) {
        navigateSubModule(moduleName);
        clickLstUnreadNotifications(0);
        waitForImgLoadingInvisibility();
        clickBtnReplyNotification();
        clearReplyTextBoxNotification();
        waitForSendReplyButtonToBeVisibile();
        populateReplyTextBoxNotification(replyText);
        clickBtnSendReplyNotification();
        String toastMsg = getToastSuccessMessageWithoutCloseBtn();
        softAssert.assertEquals(toastMsg, adminNotificationTextProp.getPropValue("txtAlertAutoResponseSaveMsg"),
                "Toast message for successful Reply sent notification is not as Expected.");
        waitForToastMessageInvisibility();
    }

    public void verifySendReplyTextPresentOnReadNotifications(SoftAssert softAssert, String requestID, String replyText) {
        softAssert.assertTrue(getFromFieldValueForRepliedRequestID(requestID).contains("(1)"),
                "Replied Notification is not captured as (1) in From Column of the Notification");
        clickFromFieldValueForRepliedRequestID(requestID);
        waitForImgLoadingInvisibility();
        softAssert.assertEquals(getMessageFieldValueMsgDetail(), replyText,
                "Replied Text was not present on the Replied Notification.");
    }

    public void sendReplyToNotification(SoftAssert softAssert, String replyText) {
        clickBtnReplyNotification();
        waitForSendReplyButtonToBeVisibile();
        clearReplyTextBoxNotification();
        populateReplyTextBoxNotification(replyText);
        clickBtnSendReplyNotification();
        String toastMsg = getToastSuccessMessageWithoutCloseBtn();
        softAssert.assertEquals(toastMsg, adminNotificationTextProp.getPropValue("txtAlertAutoResponseSaveMsg"),
                "Toast message for successful Reply sent notification is not as Expected.");
        waitForToastMessageInvisibility();
    }

    public void verifyLblAllowedFileTypesSendReplyPage(SoftAssert softAssert) {
        clickBtnReplyNotification();
        waitForSendReplyButtonToBeVisibile();
        waitForLblAllowedFileTypesToBeVisible();
        softAssert.assertTrue(isLblAllowedFileTypesVisible(), "Allowed File Types Label is not Visible on Send Reply Notification Page.");
        softAssert.assertTrue(getLblAllowedFileTypes().contains("Allowed file types:"), "Allowed File Types Label is not as Expected on Send Reply Notification Page.");
        softAssert.assertTrue(getLblAllowedFileTypes().contains(adminNotificationTextProp.getPropValue("txtLblSendNotifiAllowedFileType")),
                "Allowed File Types Label is not as Expected on Send Reply Notification Page.");
        clickBtnDiscardSendReply();
        waitForDateFieldValueMsgDetailToBeVisible();
    }

    public void verifyBlankReplyNotificationValidation(SoftAssert softAssert, String moduleName) {
        navigateSubModule(moduleName);
        clickLstSubject(0);
        waitForImgLoadingInvisibility();
        clickBtnReplyNotification();
        waitForSendReplyButtonToBeVisibile();
        clearReplyTextBoxNotification();
        clickBtnSendReplyNotification();
        String toastMsg = getToastSuccessMessageWithoutCloseBtn();
        softAssert.assertTrue(toastMsg.contains(adminNotificationTextProp.getPropValue("txtAlertMsgBlankReplySend")),
                "Toast message for Blank Message on Send Reply is not matched.");
        waitForToastMessageInvisibility();
    }

    public void verifyThreadModules(SoftAssert softAssert, String moduleName, String replyText) {
        String fromFieldValueMsgDetail = getFromFieldValueMsgDetail();
        sendReplyToNotification(softAssert, replyText);
        DriverFactory.refreshBrowser();
        waitForPageLoaderInvisibility();
        navigateSubModule(moduleName);
        enableALlNotificationsView();
        List<WebElement> lstFromColumnData = getLstFromColumnData();
        for (WebElement e : lstFromColumnData) {
            if (e.getText().contains(fromFieldValueMsgDetail)) {
                softAssert.assertTrue(e.getText().contains("(1)"), "Replied Notification is not captured as (1) in From Column of the ConnectMe Notification");
                e.click();
                waitForImgLoadingInvisibility();
                String messageFieldValueMsgDetail = getMessageFieldValueMsgDetail();
                softAssert.assertTrue(messageFieldValueMsgDetail.equals(replyText), "Replied Text was not present on the Replied Notification.");
            }
        }
    }

    public void verifyInboxMoveInServiceNotifications(SoftAssert softAssert, Services services, User user) {
        List<String> subListText = new ArrayList<String>();
//        List<WebElement> lstSubject = getLstSubject();
//        for (WebElement e : lstSubject) {
//            if (e.getText().contains("Smart Energy Water") && e.getText().contains("request"))
//                subListText.add(e.getText().split("-")[0].trim());
//        }
        getLstSubject().stream().filter(s -> s.getText().contains("Smart Energy Water") && s.getText().contains("request"))
                .forEach(s -> subListText.add(s.getText().split("-")[0].trim()));
        softAssert.assertTrue(subListText.contains("Smart Energy Water Connect Me"),
                "Inbox doesn't contains the Connect Me Request.");
        softAssert.assertTrue(subListText.contains("Smart Energy Water Service"),
                "Inbox doesn't contains the Services Request.");
        softAssert.assertFalse(subListText.contains("Smart Energy Water - Message Received"),
                "Inbox Contains the Customer notifications.");
        navigateToNotificationByRequestId("Move In");
        softAssert.assertTrue(getFromFieldValueMsgDetail().contains("SCMNotifications@smartcmobile.com"),
                "From value is not as Expected in Inbox folder");
        softAssert.assertTrue(getSubjectFieldValueMsgDetail().contains("Smart Energy Water Service - Move In request"),
                "Subject Line is not as Expected in Inbox folder");
        softAssert.assertTrue(getMessageFieldValueMsgDetail().contains("Dear Admin,"), "Dear Value is not as Expected in Inbox folder");
        softAssert.assertTrue(getMessageFieldValueMsgDetail().contains("Following Service Request has been sent by -"),
                "MessageBody Line 2 is not as Expected in Inbox folder");
        softAssert.assertTrue(getMessageFieldValueMsgDetail().contains("Name: " + services.getCustomerName()),
                "Name value is not as Expected in Inbox folder");
        softAssert.assertTrue(getMessageFieldValueMsgDetail().contains("Account Number: " + user.getDefaultUtilityAccNum()),
                "Account Number is not as Expected in Inbox folder");
        softAssert.assertTrue(getMessageFieldValueMsgDetail().contains("Comments: " + services.getMessageBody()),
                "Comments value is not as Expected in Inbox folder");
//        softAssert.assertTrue(getMessageFieldValueMsgDetail().contains("Please Click here for further details."), //Todo uncomment this after this issue confirmation
//                "Click Here line is not as Expected in Inbox folder");
        softAssert.assertTrue(getMessageFieldValueMsgDetail().contains("Thank you."), "Thank you line is not as Expected in Inbox folder");
        softAssert.assertTrue(isDateFieldValueMsgDetailVisible(), "Date Value on the message is not present in Inbox folder");
    }

    public void verifyInboxConnectMeProgramsNotifications(SoftAssert softAssert, ConnectMe connectMe, User user) {
        List<String> subListText = new ArrayList<String>();
//        List<WebElement> lstSubject = getLstSubject();
//        for (WebElement e : lstSubject) {
//            if (e.getText().contains("Smart Energy Water") && e.getText().contains("request"))
//                subListText.add(e.getText().split("-")[0].trim());
//        }
        getLstSubject().stream().filter(s -> s.getText().contains("Smart Energy Water") && s.getText().contains("request"))
                .forEach(s -> subListText.add(s.getText().split("-")[0].trim()));
        softAssert.assertTrue(subListText.contains("Smart Energy Water Connect Me"),
                "Inbox doesn't contains the Connect Me Request.");
        softAssert.assertTrue(subListText.contains("Smart Energy Water Service"),
                "Inbox doesn't contains the Services Request.");
        softAssert.assertFalse(subListText.contains("Smart Energy Water - Message Received"),
                "Inbox Contains the Customer notifications.");
        navigateToNotificationByRequestId("Connect Me - programs");
        softAssert.assertTrue(getFromFieldValueMsgDetail().contains("SCMNotifications@smartcmobile.com"),
                "From value is not as Expected in Inbox folder");
        softAssert.assertTrue(getSubjectFieldValueMsgDetail().contains("Smart Energy Water Connect Me - programs request"),
                "Subject Line is not as Expected in Inbox folder");
        softAssert.assertTrue(getMessageFieldValueMsgDetail().contains("Dear Admin,"), "Dear Value is not as Expected in Inbox folder");
        softAssert.assertTrue(getMessageFieldValueMsgDetail().contains("Following Connect Me request has been sent by -"),
                "MessageBody Line 2 is not as Expected in Inbox folder");
        softAssert.assertTrue(getMessageFieldValueMsgDetail().contains("Name: " + connectMe.getCustomerName()),
                "Name value is not as Expected in Inbox folder");
        softAssert.assertTrue(getMessageFieldValueMsgDetail().contains("Account Number: " + user.getDefaultUtilityAccNum()),
                "Account Number is not as Expected in Inbox folder");
        softAssert.assertTrue(getMessageFieldValueMsgDetail().contains("Comments: " + connectMe.getMessageBody()),
                "Comments value is not as Expected in Inbox folder");
//        softAssert.assertTrue(getMessageFieldValueMsgDetail().contains("Please Click here for further details."), //Todo uncomment this after this issue confirmation
//                "Click Here line is not as Expected in Inbox folder");
        softAssert.assertTrue(getMessageFieldValueMsgDetail().contains("Thank you."), "Thank you line is not as Expected in Inbox folder");
        softAssert.assertTrue(getMessageFieldValueMsgDetail().contains("Smart Energy Water"),
                "Company name line is not as Expected in Inbox folder");
        softAssert.assertTrue(
                getMessageFieldValueMsgDetail().contains("This is an automatically generated email. Please do not reply to this message."),
                "Automatically generated mail line is not as Expected in Inbox folder");
    }

    public void verifyServicesMoveInNotification(SoftAssert softAssert, String serviceRequestID, Services services) {
        navigateSubModule("Services");
        List<String> subListServicesText = new ArrayList<>();
//        List<WebElement> subListServices = getLstSubject();
//        for (WebElement ele : subListServices) {
//            subListServicesText.add(ele.getText());
//        }
        getLstSubject().stream().forEach(s -> subListServicesText.add(s.getText()));
        softAssert.assertFalse(subListServicesText.contains("Smart Energy Water Connect Me"),
                "ConnectMe Admin Notification is present on Services Submodule");
        softAssert.assertFalse(subListServicesText.contains("Smart Energy Water Service"),
                "Services Admin Notification is present on Services Submodule");
        navigateToNotificationByRequestId(serviceRequestID);
        String customerServiceNumber = getCustomerServiceNumber();
        String customerServiceEmail = getCustomerServiceEmail();
        // Verifying the message body.
        softAssert.assertTrue(getFromFieldValueMsgDetail().contains("SCMNotifications@smartcmobile.com"), "From value is not as Expected in Services Folder");
        softAssert.assertTrue(getSubjectFieldValueMsgDetail().contains("Smart Energy Water Message Received (Move In), (Request Id: " + serviceRequestID + ")"),
                "Subject Line is not as Expected in Services Folder");
        String messageBody = getMessageFieldValueMsgDetail();
        System.out.println(messageBody);
        softAssert.assertTrue(messageBody.contains("Dear " + services.getCustomerName() + ","),
                "MessageBody Line 1 is not as Expected in Services Folder");
        softAssert.assertTrue(
                messageBody.contains("Your Move In request has been received and we will contact you shortly."),
                "MessageBody Line 2 is not as Expected in Services Folder");
        softAssert.assertTrue(messageBody.contains("Please note your Request ID: " + serviceRequestID + "."),
                "MessageBody Line 3 is not as Expected in Services Folder");
//        softAssert.assertTrue(messageBody.contains("Please Click here for further details."), //Todo need to report bug and uncomment after its fixed.
//                "MessageBody Line 4 is not as Expected in Services Folder");
//		softAssert.assertTrue(messageBody.contains("If you need further assistance please contact our customer service at "+customerServiceNumber+" or email us at "+customerServiceEmail+"."),
//				"MessageBody Line 5 is not as Expected in Services Folder");
        softAssert.assertTrue(
                messageBody.contains("If you need further assistance please contact our customer service at "
                        + customerServiceNumber + " or email us at " + customerServiceEmail + "."),
                "MessageBody Line 5 is not as Expected in ConnectMe Folder. Actual Text is "
                        + messageBody.split("\\R")[8] + " and Expected Text is "
                        + "If you need further assistance, please contact our customer service at "
                        + customerServiceNumber + " or email us at " + customerServiceEmail + ".");
        softAssert.assertTrue(messageBody.contains("Thank you,"),
                "MessageBody Line 6 is not as Expected in Services Folder");
        softAssert.assertTrue(messageBody.contains("Smart Energy Water"),
                "MessageBody Line 7 is not as Expected in Services Folder");
        softAssert.assertTrue(messageBody.contains("This is an automatically generated email. Please do not reply to this message."),
                "MessageBody Line 8 is not as Expected in Services Folder");
        softAssert.assertTrue(isDateFieldValueMsgDetailVisible(), "Date Value on the message is not present.");
    }

    public void verifyConnectMeProgramsNotifications(SoftAssert softAssert, String programsReqID, ConnectMe connectMe) {
        navigateSubModule("Connect Me");
        List<String> subListServicesText = new ArrayList<>();
//        List<WebElement> subListServices = getLstSubject();
//        for (WebElement ele : subListServices) {
//            subListServicesText.add(ele.getText());
//        }
        getLstSubject().stream().forEach(s -> subListServicesText.add(s.getText()));
        softAssert.assertFalse(subListServicesText.contains("Smart Energy Water Connect Me"),
                "ConnectMe Admin Notification is present on ConnectMe Submodule");
        softAssert.assertFalse(subListServicesText.contains("Smart Energy Water Service"),
                "Services Admin Notification is present on ConnectMe Submodule");
        navigateToNotificationByRequestId(programsReqID);
        String customerServiceNumber = getCustomerServiceNumber();
        String customerServiceEmail = getCustomerServiceEmail();
        // Verifying the message body.
        softAssert.assertTrue(getFromFieldValueMsgDetail().contains(connectMe.getFromEMail()), "From value is not as Expected in ConnectMe Folder");
        softAssert.assertTrue(getSubjectFieldValueMsgDetail().contains("Smart Energy Water - Message Received (programs), (Request Id: " + programsReqID + ")"),
                "Subject Line is not as Expected in Connect Me Folder");
        String messageBody = getMessageFieldValueMsgDetail();
        System.out.println(messageBody);
        softAssert.assertTrue(messageBody.contains("Dear " + connectMe.getCustomerName() + ","),
                "MessageBody Line 1 is not as Expected in ConnectMe Folder");
        softAssert.assertTrue(messageBody.contains("Your programs - related message has been received and we will contact you shortly."),
                "MessageBody Line 2 is not as Expected");
        softAssert.assertTrue(messageBody.contains("Your request ID is: " + programsReqID + "."),
                "MessageBody Line 3 is not as Expected in ConnectMe Folder");
        softAssert.assertTrue(messageBody.contains("Please Click here for further details."),
                "MessageBody Line 4 is not as Expected in ConnectMe Folder");
        softAssert.assertTrue(messageBody.contains("If you need further assistance, please contact our customer service at "
                        + customerServiceNumber + " or email us at " + customerServiceEmail + "."),
                "MessageBody Line 5 is not as Expected in ConnectMe Folder. Actual Text is "
                        + messageBody.split("\\R")[8] + " and Expected Text is "
                        + "If you need further assistance, please contact our customer service at "
                        + customerServiceNumber + " or email us at " + customerServiceEmail + ".");
        softAssert.assertTrue(messageBody.contains("Thank you,"),
                "MessageBody Line 6 is not as Expected in ConnectMe Folder");
        softAssert.assertTrue(messageBody.contains("Smart Energy Water"),
                "MessageBody Line 7 is not as Expected in ConnectMe Folder");
        softAssert.assertTrue(messageBody.contains("This is an automatically generated email. Please do not reply to this message."),
                "MessageBody Line 8 is not as Expected in ConnectMe Folder");
        softAssert.assertTrue(isDateFieldValueMsgDetailVisible(), "Date Value on the message is not present in ConnectMe Folder");
    }

    public void verifySendNotificationPageObjects(SoftAssert softAssert) {
        navigateSubModule("Send Notification");
        Assert.assertTrue(isAdminNotificationOutboxPage(adminNotificationTextProp.getPropValue("adminNotificationOutboxPageUrl"), adminNotificationTextProp.getPropValue("adminNotificationOutboxPageTitle")),
                "Notification Outbox page is not landed after navigating to Send Notification module.");
        //Notification Inbox
        softAssert.assertTrue(isBtnNotificationInboxOnSendNotificationVisible(), "Notification Inbox Button is not visible on Send Notification Page.");
        softAssert.assertTrue(getLblBtnNotificationInboxOnSendNotification().equals("Notification Inbox"),
                "Notification Inbox Button Label is not as Expected on Send Notification Page.");
        //Message Type
        waitForDdTypeOfMessageSendNotiToBeVisible();
        softAssert.assertTrue(isLblTypeOfMessageVisible(), "Type of Message Label is not visible on Send Notification Page.");
        softAssert.assertTrue(getLblTypeOfMessage().equals("Type of Message:"), "Type of Message  Field Label is not as Expected on Send Notification Page.");
        softAssert.assertTrue(isDdTypeOfMessageSendNotiVisible(), "Type of Message Dropdown is not visible on Send Notification Page.");
        softAssert.assertTrue(getSelectedValueInDropBox(ddWebEleTypeOfMessageSendNoti()).equals("--Message Type--"), "Type of Message Dropdown is not having default Selection on Send Notification Page.");
        softAssert.assertTrue(getSelectOptionsMessageDropdown().containsAll(getExpectedElementsTextList(adminNotificationTextProp.getPropValue("ddOptionsMessageType"))),
                "Message Type Dropdown Options on Send Notification page is not as Expected.");
        //Account Type
        waitForDdAccountTypeSendNotiToBeVisible();
        softAssert.assertTrue(isLblAccountTypeVisible(), "AccountType Label is not visible on Send Notification Page.");
        softAssert.assertTrue(getLblAccountType().equals("Account Type:"), "AccountType Field Label is not as Expected on Send Notification Page.");
        softAssert.assertTrue(isDdAccountTypeSendNotiVisible(), "AccountType Dropdown is not visible on Send Notification Page.");
        softAssert.assertTrue(getSelectedValueInDropBox(ddWebEleAccountTypeSendNoti()).equals("--Account Type--"), "AccountType Dropdown is not having default Selection on Send Notification Page.");
        softAssert.assertTrue(getSelectOptionsAccountDropdown().containsAll(getExpectedElementsTextList(adminNotificationTextProp.getPropValue("ddOptionsAccountType"))),
                "Account Type Dropdown Options on Send Notification page is not as Expected.");
        //Location
        softAssert.assertTrue(isLblLocationVisible(), "Location Label is not visible on Send Notification Page.");
        softAssert.assertTrue(getLblLocation().equals("Location:"), "Location Field Label is not as Expected on Send Notification Page.");
        softAssert.assertTrue(isDdLocationSendNotiVisible(), "Location Dropdown is not visible on Send Notification Page.");
        softAssert.assertTrue(getSelectedValueInDropBox(ddWebEleLocationSendNoti()).equals("--Location--"), "Location Dropdown is not having default Selection on Send Notification Page.");
        //Mode Of Message
        waitForDdModeOfMessageSendNotiToBeVisible();
        softAssert.assertTrue(isLblModeOfMessageVisible(), "ModeOfMessage Label is not visible on Send Notification Page.");
        softAssert.assertTrue(getLblModeOfMessage().equals("Mode Of Message:"), "ModeOfMessage Field Label is not as Expected on Send Notification Page.");
        softAssert.assertTrue(isDdModeOfMessageSendNotiVisible(), "ModeOfMessage Dropdown is not visible on Send Notification Page.");
        softAssert.assertTrue(getSelectedValueInDropBox(ddWebEleModeOfMessageSendNoti()).equals("--Mode Of Message--"), "ModeOfMessage Dropdown is not having default Selection on Send Notification Page.");
        softAssert.assertTrue(getSelectOptionsModeOfMessageDropdown().containsAll(getExpectedElementsTextList(adminNotificationTextProp.getPropValue("ddOptionsModeOfMessage"))),
                "Mode Of Message Dropdown Options on Send Notification page is not as Expected.");
        //Customer Name
        softAssert.assertTrue(isLblCustomerNameVisible(), "CustomerName Label is not visible on Send Notification Page.");
        softAssert.assertTrue(getLblCustomerName().equals("Customer Name:"), "CustomerName Field Label is not as Expected on Send Notification Page.");
        softAssert.assertTrue(isTxtBoxCustomerNameVisible(), "CustomerName TextBox is not Visible on Send Notification Page.");
        //Email
        selectModeOfMessageDropdown("Email");
        softAssert.assertTrue(isLblSubjectVisible(), "Subject Label is not visible for Email on Send Notification Page.");
        softAssert.assertTrue(getLblSubject().equals("Subject:"), "Subject Field Label is not as Expected  for Email  on Send Notification Page.");
        softAssert.assertTrue(isTxtBoxSubjectSendNotiVisible(), "Subject TextBox is not Visible  for Email on Send Notification Page.");
        softAssert.assertTrue(isTxtBoxForEmailBodySendNotiVisible(), "Email Body TextBox is not Visible for Email  on Send Notification Page.");
        softAssert.assertTrue(islstBtnChooseFileMailVisible(), "Choose File Button is not Visible  for Email on Send Notification Page.");
        softAssert.assertTrue(isLblAllowedFileTypesVisible(), "Allowed File Types Label is not Visible for Email  on Send Notification Page.");
        softAssert.assertTrue(getLblAllowedFileTypes().contains("Allowed file types:"), "Allowed File Types Label is not as Expected for Email on Send Notification Page.");
        softAssert.assertTrue(getLblAllowedFileTypes().contains(adminNotificationTextProp.getPropValue("txtLblSendNotifiAllowedFileType")),
                "Allowed File Types Label is not as Expected for Email on Send Notification Page.");
        softAssert.assertTrue(getlblFileName().equals("No File Chosen"), "No File Chosen Label Text is not as Expected for Email on Send Notification Page.");
        //Send Button
        softAssert.assertTrue(isLstBtnSendOnSendNotiVisible(), "Send Button is not visible on Send Notification Page.");
        softAssert.assertTrue(getLblBtnSendOnSendNoti().equals("Send"), "Send Button Label is not as Expected on Send Notification Page.");
        //Text
        selectModeOfMessageDropdown("Text");
        softAssert.assertFalse(isLblSubjectVisible(), "Subject Label is visible for Text on Send Notification Page.");
        softAssert.assertFalse(isTxtBoxSubjectSendNotiVisible(), "Subject TextBox is Visible  for Text on Send Notification Page.");
        softAssert.assertTrue(isTxtBoxForNonEmailBodySendNotiVisible(), "Text Body TextBox is not Visible for Text on Send Notification Page.");
//        softAssert.assertFalse(islstBtnChooseFileMailVisible(),"Choose File Button is Visible for Text on Send Notification Page."); //todo
        softAssert.assertFalse(isLblAllowedFileTypesVisible(), "Allowed File Types Label is Visible for Text on Send Notification Page.");
        //Push
        selectModeOfMessageDropdown("Push Notification");
        softAssert.assertFalse(isLblSubjectVisible(), "Subject Label is visible for Push Notification on Send Notification Page.");
        softAssert.assertFalse(isTxtBoxSubjectSendNotiVisible(), "Subject TextBox is Visible  for Push Notification on Send Notification Page.");
        softAssert.assertTrue(isTxtBoxForNonEmailBodySendNotiVisible(), "Text Body TextBox is not Visible for Push Notification on Send Notification Page.");
//        softAssert.assertFalse(islstBtnChooseFileMailVisible(),"Choose File Button is Visible for Push Notification on Send Notification Page."); //todo
        softAssert.assertFalse(isLblAllowedFileTypesVisible(), "Allowed File Types Label is Visible for Push Notification on Send Notification Page.");
        //Robo Call
        selectModeOfMessageDropdown("Robo Call");
        softAssert.assertFalse(isLblSubjectVisible(), "Subject Label is visible for Robo Call on Send Notification Page.");
        softAssert.assertFalse(isTxtBoxSubjectSendNotiVisible(), "Subject TextBox is Visible  for Robo Call on Send Notification Page.");
        softAssert.assertTrue(isTxtBoxForNonEmailBodySendNotiVisible(), "Text Body TextBox is not Visible for Robo Call on Send Notification Page.");
//        softAssert.assertFalse(islstBtnChooseFileMailVisible(),"Choose File Button is Visible for Robo Call on Send Notification Page.");//todo
        softAssert.assertFalse(isLblAllowedFileTypesVisible(), "Allowed File Types Label is Visible for Robo Call on Send Notification Page.");
        //WhatsApp
        selectModeOfMessageDropdown("WhatsApp");
        softAssert.assertFalse(isLblSubjectVisible(), "Subject Label is visible for WhatsApp on Send Notification Page.");
        softAssert.assertFalse(isTxtBoxSubjectSendNotiVisible(), "Subject TextBox is Visible  for WhatsApp on Send Notification Page.");
        softAssert.assertTrue(isTxtBoxForNonEmailBodySendNotiVisible(), "Text Body TextBox is not Visible for WhatsApp on Send Notification Page.");
//        softAssert.assertFalse(islstBtnChooseFileMailVisible(),"Choose File Button is Visible for WhatsApp on Send Notification Page.");//todo
        softAssert.assertFalse(isLblAllowedFileTypesVisible(), "Allowed File Types Label is Visible for WhatsApp on Send Notification Page.");
    }

    public void clickBtnNotificationInboxAndNavToInbox() {
        clickBtnNotificationInboxOnSendNotification();
        waitForPageLoaderInvisibility();
        waitForImgLoadingInvisibility();
    }

    public boolean isNotificationInboxPageLanded() {
        if (getAttrLeftNavigationPanel().equals("height: 2498.83px;"))
            return true;
        else
            return false;
    }

    public void verifySendNotificationPageFieldValidations(SoftAssert softAssert) {
        navigateSubModule("Send Notification");
        String actToastMsg;
        //All Fields Mandatory validation message.
        selectModeOfMessageDropdown("Email");
        waitForSendReplyButtonToBeVisibile();
        clickBtnSendOnSendNoti();
        actToastMsg = getToastMessageWithoutWait();
        softAssert.assertEquals(actToastMsg, adminNotificationTextProp.getPropValue("txtToastMsgAllMandatoryField"),
                "All fields Mandatory  message is not correct for send notification");
        waitForToastMessageInvisibility();
        //Populating all fields
        selectTypeOfMessageDropdown("Billing");
        selectAccountTypeDropdown("Residential");
        selectLocationDropdown("92602");
        selectModeOfMessageDropdown("Email");
        waitForBtnSendOnSendNotiToBeVisible();
        //Type of Message Mandatory Field Validation
        selectDefaultTypeOfMessageDropdown();
        clickBtnSendOnSendNoti();
        actToastMsg = getToastMessageWithoutWait();
        softAssert.assertEquals(actToastMsg, adminNotificationTextProp.getPropValue("txtAlertTypeOfMessageMandatoryField"),
                "Type of Message Mandatory Field message is not correct for send notification");
        waitForToastMessageInvisibility();
        selectTypeOfMessageDropdown("Billing");
        //Account Type Mandatory Field Validation
        selectDefaultAccountTypeDropdown();
        clickBtnSendOnSendNoti();
        actToastMsg = getToastMessageWithoutWait();
        softAssert.assertEquals(actToastMsg, adminNotificationTextProp.getPropValue("txtAlertAccountTypeMandatoryField"),
                "Account Type Mandatory Field message is not correct for send notification");
        waitForToastMessageInvisibility();
        selectAccountTypeDropdown("Residential");
        //Location Mandatory Field Validation
        selectDefaultLocationDropdown();
        clickBtnSendOnSendNoti();
        actToastMsg = getToastMessageWithoutWait();
        softAssert.assertEquals(actToastMsg, adminNotificationTextProp.getPropValue("txtAlertLocationMandatoryField"),
                "Location Mandatory Field message is not correct for send notification");
        waitForToastMessageInvisibility();
        selectLocationDropdown("92602");
        //Mode Of Message Mandatory Field Validation
        selectDefaultModeOfMessageDropdown();
        clickBtnSendOnSendNoti();
        actToastMsg = getToastMessageWithoutWait();
        softAssert.assertEquals(actToastMsg, adminNotificationTextProp.getPropValue("txtAlertModeOfMessageMandatoryField"),
                "Mode of Message Mandatory Field message is not correct for send notification");
        waitForToastMessageInvisibility();
        selectModeOfMessageDropdown("Email");
        waitForBtnSendOnSendNotiToBeVisible();
        //Email Message Body Mandatory Field Validation
        clearTxtBoxForEmailBodySendNoti();
        clickBtnSendOnSendNoti();
        actToastMsg = getToastMessageWithoutWait();
        softAssert.assertEquals(actToastMsg, adminNotificationTextProp.getPropValue("txtAlertEmailBodyMandatoryField"),
                "Email Message Body Mandatory Field message is not correct for send notification");
        waitForToastMessageInvisibility();
        //Text Message Body Mandatory Field Validation
        selectModeOfMessageDropdown("Text");
        waitForBtnSendOnSendNotiToBeVisible();
        clearTxtBoxForNonEmailBodySendNoti();
        clickBtnSendOnSendNoti();
        actToastMsg = getToastMessageWithoutWait();
        softAssert.assertEquals(actToastMsg, adminNotificationTextProp.getPropValue("txtAlertTextBodyMandatoryField"),
                "Text Message Body Mandatory Field message is not correct for send notification");
        waitForToastMessageInvisibility();
        //Push Notification Message Body Mandatory Field Validation
        selectModeOfMessageDropdown("Push Notification");
        waitForBtnSendOnSendNotiToBeVisible();
        clearTxtBoxForNonEmailBodySendNoti();
        clickBtnSendOnSendNoti();
        actToastMsg = getToastMessageWithoutWait();
        softAssert.assertEquals(actToastMsg, adminNotificationTextProp.getPropValue("txtAlertPushBodyMandatoryField"),
                "Push Notification Message Body Mandatory Field message is not correct for send notification");
        waitForToastMessageInvisibility();
        //RoboCall Notification Message Body Mandatory Field Validation
        selectModeOfMessageDropdown("Robo Call");
        waitForBtnSendOnSendNotiToBeVisible();
        clearTxtBoxForNonEmailBodySendNoti();
        clickBtnSendOnSendNoti();
        actToastMsg = getToastMessageWithoutWait();
        softAssert.assertEquals(actToastMsg, adminNotificationTextProp.getPropValue("txtAlertRoboCallBodyMandatoryField"),
                "RoboCall Notification Message Body Mandatory Field message is not correct for send notification");
        waitForToastMessageInvisibility();
        //WhatsApp Notification Message Body Mandatory Field Validation
        selectModeOfMessageDropdown("WhatsApp");
        waitForBtnSendOnSendNotiToBeVisible();
        clearTxtBoxForNonEmailBodySendNoti();
        clickBtnSendOnSendNoti();
        actToastMsg = getToastMessageWithoutWait();
        softAssert.assertEquals(actToastMsg, adminNotificationTextProp.getPropValue("txtAlertWhatsAppBodyMandatoryField"),
                "WhatsApp Notification Message Body Mandatory Field message is not correct for send notification");
        waitForToastMessageInvisibility();
    }

    public void verifyNotificationBodyCharacterLimit(SoftAssert softAssert, String modeOfMessage) {
        selectTypeOfMessageDropdown("Billing");
        selectAccountTypeDropdown("Residential");
        selectLocationDropdown("92602");
        selectModeOfMessageDropdown(modeOfMessage);
        pause(2000);
        String randomStr;
        String actToastMsg;
        if (modeOfMessage.equals("Email")) {
            waitForLblAllowedFileTypesToBeVisible();
            softAssert.assertTrue(getLblMaxCharsBodyAllowedLbl().equals(adminNotificationTextProp.getPropValue("txtLblMaxCharsBodyAllowedEmail")),
                    "For Email, Maximum Characters Allowed on EmailMessage Body is not as Expected.");
            randomStr = RandomUtil.generateRandomString(1001, RandomUtil.Mode.ALPHANUMERIC);
            clearTxtBoxForEmailBodySendNoti();
            populateTxtBoxForEmailBodySendNoti(randomStr);
            clickBtnSendOnSendNoti();
            actToastMsg = getToastMessageWithoutWait();
            softAssert.assertEquals(actToastMsg, adminNotificationTextProp.getPropValue("txtToastMsgOverLimitCharEmail"),
                    "Email character limit is not correct for send notification");
            waitForToastMessageInvisibility();
        }
        else if (modeOfMessage.equals("Text")) {
            softAssert.assertTrue(getLblMaxCharsBodyAllowedLbl().equals(adminNotificationTextProp.getPropValue("txtLblMaxCharsBodyAllowedText")),
                    "For Text, Maximum Characters Allowed on TextMessage Body is not as Expected.");
            randomStr = RandomUtil.generateRandomString(145, RandomUtil.Mode.ALPHANUMERIC);
            waitForTxtBoxForNonEmailBodySendNotiToBeVisible();
            clearTxtBoxForNonEmailBodySendNoti();
            populateTxtBoxForNonEmailBodySendNoti(randomStr);
//            actToastMsg = getToastMessageWithoutWait();
//            softAssert.assertEquals(actToastMsg, adminNotificationTextProp.getPropValue("txtAlertTextMsgLimit"),
//                    "Text character limit is not correct for send notification");
//            waitForToastMessageInvisibility();
            softAssert.assertEquals(getTxtForNonEmailBodySendNoti().length(), 140, "Text character limit is not correct for send notification");
        }
        else if (modeOfMessage.equals("Push Notification")) {
            softAssert.assertTrue(getLblMaxCharsBodyAllowedLbl().equals(adminNotificationTextProp.getPropValue("txtLblMaxCharsBodyAllowedPush")),
                    "For Push, Maximum Characters Allowed on PushMessage Body is not as Expected.");
            randomStr = RandomUtil.generateRandomString(205, RandomUtil.Mode.ALPHANUMERIC);
            waitForTxtBoxForNonEmailBodySendNotiToBeVisible();
            clearTxtBoxForNonEmailBodySendNoti();
            populateTxtBoxForNonEmailBodySendNoti(randomStr);
//            pause(2000);
//            actToastMsg = getToastMessageWithoutWait();
//            softAssert.assertEquals(actToastMsg, adminNotificationTextProp.getPropValue("txtAlertPushNotiLimit"),
//                    "Push character limit is not correct for send notification");
//            waitForToastMessageInvisibility();
            softAssert.assertEquals(getTxtForNonEmailBodySendNoti().length(),200,"Push character limit is not correct for send notification");
        }
        else if (modeOfMessage.equals("Robo Call")) {
            softAssert.assertTrue(getLblMaxCharsBodyAllowedLbl().equals(adminNotificationTextProp.getPropValue("txtLblMaxCharsBodyAllowedRoboCall")),
                    "For RoboCall, Maximum Characters Allowed on RoboCall Message Body is not as Expected.");
            randomStr = RandomUtil.generateRandomString(145, RandomUtil.Mode.ALPHANUMERIC);
            waitForTxtBoxForNonEmailBodySendNotiToBeVisible();
            clearTxtBoxForNonEmailBodySendNoti();
            populateTxtBoxForNonEmailBodySendNoti(randomStr);
//            actToastMsg = getToastMessageWithoutWait();
//            softAssert.assertEquals(actToastMsg, adminNotificationTextProp.getPropValue("txtAlertRoboCallMsgLimit"),
//                    "RoboCall character limit is not correct for send notification");
//            waitForToastMessageInvisibility();
            softAssert.assertEquals(getTxtForNonEmailBodySendNoti().length(), 140, "RoboCall character limit is not correct for send notification");
        }
        else if (modeOfMessage.equals("WhatsApp")) {
            softAssert.assertTrue(getLblMaxCharsBodyAllowedLbl().equals(adminNotificationTextProp.getPropValue("txtLblMaxCharsBodyAllowedWhatsApp")),
                    "For WhatsApp, Maximum Characters Allowed on WhatsApp Message Body is not as Expected.");
            randomStr = RandomUtil.generateRandomString(145, RandomUtil.Mode.ALPHANUMERIC);
            waitForTxtBoxForNonEmailBodySendNotiToBeVisible();
            clearTxtBoxForNonEmailBodySendNoti();
            populateTxtBoxForNonEmailBodySendNoti(randomStr);
//            actToastMsg = getToastMessageWithoutWait();
//            softAssert.assertEquals(actToastMsg, adminNotificationTextProp.getPropValue("txtAlertWhatsAppNotiLimit"),
//                    "WhatsApp character limit is not correct for send notification");
            waitForToastMessageInvisibility();
            softAssert.assertEquals(getTxtForNonEmailBodySendNoti().length(), 140, "WhatsApp character limit is not correct for send notification");
        }
    }

    public void sendNotificationFileFormatValidation(SoftAssert softAssert) {
        selectModeOfMessageDropdown("Email");
        String actToastMsg;
        //Invalid File Format Validation
        addAttachmentToChooseFile(FILE_UPLOAD_PATH + "index.html");
        actToastMsg = getToastMessageWithoutWait();
        softAssert.assertTrue(actToastMsg.contains(adminNotificationTextProp.getPropValue("txtToastInvalidFormatFileUpload")),
                "Toast for invalid file type upload is not as Expected.");
        waitForToastMessageInvisibility();
        //File Size  Validation
        addAttachmentToChooseFile(FILE_UPLOAD_PATH + "Flower 6MB.gif");
        actToastMsg = getToastMessageWithoutWait();
        softAssert.assertTrue(actToastMsg.contains(adminNotificationTextProp.getPropValue("txtToastMoreThan5MBUpload")),
                "Toast for attachment size more then 5 MB not matched.");
        waitForToastMessageInvisibility();
    }

    public void addValidAttachmentToSendNotification(SoftAssert softAssert) {
        String validAttachmentFileName = "meter-reading.jpg";
        addAttachmentToChooseFile(FILE_UPLOAD_PATH + validAttachmentFileName);
        softAssert.assertTrue(isIconRemoveFileVisible() && getlblFileName().contains(validAttachmentFileName),
                "The Valid Attachment added is not attached.");
    }

    public void verifyCommentsFieldOnInboxNotification(SoftAssert softAssert, String expectedCommentValue) {
        softAssert.assertTrue(getMessageFieldValueMsgDetail().contains(expectedCommentValue),
                "Inbox Outage Notification doesnot contain the expected comment.");
    }

    public void deleteNotificationSubModuleByRequestID(SoftAssert softAssert, String moduleName, String requestID) {
        navigateSubModule(moduleName);
        navigateToNotificationByRequestId(requestID);
        clickBtnDeleteHeader();
        explicitWaitForAlertPopUp(driver);
        if (isAlertPresent()) {
            verifyAlertMessage(adminNotificationTextProp.getPropValue("txtAlertMsgForAskDelete"));
            acceptAlert();
        }
        String toastMsg = getToastSuccessMessageWithoutCloseBtn();
        softAssert.assertEquals(toastMsg, adminNotificationTextProp.getPropValue("txtAlertMsgDeleteSuccess"),
                "Toast message for deleted notification to Trash is not as Expected.");
        waitForToastMessageInvisibility();
    }

    public void deleteMultipleNotificationSubModuleByRequestID(SoftAssert softAssert, String moduleName, List requestID) {
        navigateSubModule(moduleName);
        for (int i = 0; i < requestID.size(); i++) {
            for (int j = 0; j < getLstSubject().size(); j++) {
                if (getLstSubject().get(j).getText().contains(requestID.get(i).toString())) {
                    clickCheckBoxForRequestID(requestID.get(i).toString());
                    pause(500);
                }
            }
        }
        clickBtnDeleteHeader();
        explicitWaitForAlertPopUp(driver);
        if (isAlertPresent()) {
            verifyAlertMessage(adminNotificationTextProp.getPropValue("txtAlertMsgForAskDelete"));
            acceptAlert();
        }
        waitForImgLoadingInvisibility();
        String toastMsg = getToastSuccessMessageWithoutCloseBtn();
        softAssert.assertEquals(toastMsg, requestID.size() + " Messages moved to Trash",
                "Toast Message for multiple deleted notifications to Trash is not Matched.");
        waitForToastMessageInvisibility();
    }

    public void deleteNotificationFromTrashByRequestID(SoftAssert softAssert, String requestID) {
        navigateSubModule("Trash");
        int countBeforeDeletion = Integer.parseInt(getLblNotificationCountPagination().split("of")[1].trim());
        navigateToNotificationByRequestId(requestID);
        clickBtnDeleteHeader();
        explicitWaitForAlertPopUp(driver);
        if (isAlertPresent()) {
            verifyAlertMessage(adminNotificationTextProp.getPropValue("txtAlertMsgDeleteFromTrash"));
            acceptAlert();
        }
        waitForImgLoadingInvisibility();
        String toastMsg = getToastSuccessMessageWithoutCloseBtn();
        softAssert.assertEquals(toastMsg, adminNotificationTextProp.getPropValue("txtAlertMsgDeleteSuccessFromTrash"),
                "Toast message for deleted permanently notification from Trash is not as Expected.");
        waitForToastMessageInvisibility();
        int countAfterDeletion = Integer.parseInt(getLblNotificationCountPagination().split("of")[1].trim());
        softAssert.assertEquals(countAfterDeletion, countBeforeDeletion - 1,
                "Deleted Notification is not removed from the Trash Folder.");
    }

    public void deleteMultipleNotificationFromTrashByRequestID(SoftAssert softAssert, List<String> requestIDs) {
        navigateSubModule("Trash");
        int countBeforeDeletion = Integer.parseInt(getLblNotificationCountPagination().split("of")[1].trim());
        for (int i = 0; i < requestIDs.size() - 3; i++) {
            for (int j = 0; j < getLstSubject().size(); j++) {
                if (getLstSubject().get(j).getText().contains(requestIDs.get(i).toString())) {
                    clickCheckBoxForRequestID(requestIDs.get(i).toString());
                    pause(500);
                }
            }
        }
        clickBtnDeleteHeader();
        explicitWaitForAlertPopUp(driver);
        if (isAlertPresent()) {
            verifyAlertMessage(adminNotificationTextProp.getPropValue("txtAlertMsgDeleteFromTrash"));
            acceptAlert();
        }
        waitForImgLoadingInvisibility();
        String toastMsg = getToastSuccessMessageWithoutCloseBtn();
        softAssert.assertEquals(toastMsg, (requestIDs.size() - 3) + " Messages deleted successfully.",
                "Toast message for multiple permanently deleted messages from Trash not matched.");
        waitForToastMessageInvisibility();
        int countAfterDeletion = Integer.parseInt(getLblNotificationCountPagination().split("of")[1].trim());
        softAssert.assertEquals(countAfterDeletion, countBeforeDeletion - 3,
                "Deleted Notification is not removed from the Trash Folder.");
    }

    public void putBackTrashDeletedNotificationToSource(SoftAssert softAssert, String requestID) {
        navigateSubModule("Trash");
        Assert.assertTrue(isBtnPutBackVisible(), "PutBack Button is not visible on Trash Page.");
        int countBeforePutBack = Integer.parseInt(getLblNotificationCountPagination().split("of")[1].trim());
        navigateToNotificationByRequestId(requestID);
        clickBtnPutBack();
        String toastMsg = getToastSuccessMessageWithoutCloseBtn();
        softAssert.assertEquals(toastMsg, adminNotificationTextProp.getPropValue("txtAlertSuccessMsgPutBackFromTrash"),
                "Put Back Success Toast message for notification from Trash is not as Expected.");
        waitForToastMessageInvisibility();
        int countAfterPutBack = Integer.parseInt(getLblNotificationCountPagination().split("of")[1].trim());
        softAssert.assertEquals(countAfterPutBack, countBeforePutBack - 1,
                "PutBack Notification is not removed from the Trash Folder.");
    }

    public void putBackTrashDeletedMultipleNotificationToSource(SoftAssert softAssert, List<String> requestIDs) {
        navigateSubModule("Trash");
        Assert.assertTrue(isBtnPutBackVisible(), "PutBack Button is not visible on Trash Page.");
        int countBeforePutBack = Integer.parseInt(getLblNotificationCountPagination().split("of")[1].trim());
        for (int i = 0; i < requestIDs.size() - 3; i++) {
            for (int j = 0; j < getLstSubject().size(); j++) {
                if (getLstSubject().get(j).getText().contains(requestIDs.get(i).toString())) {
                    clickCheckBoxForRequestID(requestIDs.get(i).toString());
                    pause(500);
                }
            }
        }
        clickBtnPutBack();
        String toastMsg = getToastSuccessMessageWithoutCloseBtn();
        softAssert.assertEquals(toastMsg, (requestIDs.size() - 3) + " Messages restored successfully.",
                "Toast message for multiple restored messages from Trash not matched.");
        waitForToastMessageInvisibility();
        int countAfterPutBack = Integer.parseInt(getLblNotificationCountPagination().split("of")[1].trim());
        softAssert.assertEquals(countAfterPutBack, countBeforePutBack - 1,
                "PutBack Notification is not removed from the Trash Folder.");
    }

    public void clickServiceAccountNavtoC360Page() {
        AdminCustomerPage adminCustomerPage;
        adminCustomerPage = new AdminCustomerPage(driver);
        clickTxtServiceAccountNoMail();
        adminCustomerPage.waitForImgCustomerDetailsLoadingToInvisible();
        switchToFrame(0);
        adminCustomerPage.waitForImgCustomerDetailsLoadingToInvisible();
        waitForPageToLoad();
    }

    public void clickLoginToCustomerPortalNavToSCP(SoftAssert softAssert) {
        AdminCustomerPage adminCustomerPage;
        adminCustomerPage = new AdminCustomerPage(driver);
        Assert.assertTrue(adminCustomerPage.isBtnLoginToCustomerDisplayed(), "Login to customer portal is not appearing");
        adminCustomerPage.clickBtnLoginToCustomer();
        waitForPageToLoad();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        String handleName = tabs.get(1);
        switchToWindow(handleName);
        waitForPageToLoad();
        String scpURL = driver.getCurrentUrl();
        Assert.assertTrue(scpURL.contains("SCP"), "Login to Customer portal is not redirecting in correct url ");
        // pageUtil.closeChildWindow();
        // driver.close();
        // switchToWindow(tabs.get(0));
        // closeAllOtherWindows(driver, driver.getWindowHandle());
    }

    public void verifyRepliedTxtPresentOnSCPNotificationInbox(SoftAssert softAssert, String requestID, String replyText) {
        DashboardPage dashboardPage;
        dashboardPage = new DashboardPage(driver);
        dashboardPage.clickNotificationLink();
        waitForPageLoaderInvisibility();
        waitForImgLoadingInvisibility();
        NotificationPage notificationPage;
        notificationPage = new NotificationPage(driver);
        notificationPage.clickSubjectOfRequestId(requestID);
        waitForImgLoadingInvisibility();
        notificationPage.waitForTxtRepliedNotificationBodyToBeVisible();
        softAssert.assertTrue(notificationPage.getTxtRepliedNotificationBody().equals(replyText),
                "Replied Notification Text from CSP is not present on SCP Notification Inbox.");
    }

    public void verifyConnectMePreLogOutageRequest(SoftAssert softAssert, String moduleName, String outageRequestID) {
        navigateToNotificationByRequestId(moduleName, outageRequestID);
        softAssert.assertEquals(getTxtCustomerNameNotifyHeaderLbl(), "NA",
                "PreLogin ConnectMe Request Notification for CustomerName is not Marked as NA");
        softAssert.assertEquals(getTxtServiceAccountNotifyHeaderLbl(),
                "NA", "PreLogin ConnectMe Request Notification for ServiceAccountNumber is not Marked as NA");
        softAssert.assertEquals(getTxtMobileNoMailLbl(), "NA",
                "PreLogin ConnectMe Request Notification for MobileNumber is not Marked as NA");
    }

    public void verifyServicesPreLogMoveInRequest(SoftAssert softAssert, String moduleName, String serviceRequestID) {
        navigateToNotificationByRequestId(moduleName, serviceRequestID);
        softAssert.assertEquals(getTxtCustomerNameNotifyHeaderLbl(), "NA",
                "PreLogin MoveIn Services Request Notification for CustomerName is not Marked as NA");
        softAssert.assertEquals(getTxtServiceAccountNotifyHeaderLbl(),
                "NA", "PreLogin MoveIn Services Request Notification for ServiceAccountNumber is not Marked as NA");
        softAssert.assertEquals(getTxtMobileNoMailLbl(), "NA",
                "PreLogin MoveIn Services Request Notification for MobileNumber is not Marked as NA");
    }

    public void verifyInboxConnectMeServiceTemplateIdFromDB(SoftAssert softAssert) {
        softAssert.assertEquals(getTemplateIdForTemplateName("Connect Me Admin"), "15",
                "Admin ConnectMe Template is not Configured correctly as 15 on DB");
        softAssert.assertEquals(getTemplateIdForTemplateName("Service Admin"), "17",
                "Admin Services Template is not Configured correctly as 17 on DB.");
    }

    public void verifyPaginationFunctionality(SoftAssert softAssert, String moduleName, String utilityAcctNo, String requestID) {
        navigateSubModule(moduleName);
        if (moduleName.equals("Connect Me")) {
            verifySearchByServiceAcctSearchFunctionality(softAssert, utilityAcctNo, requestID);
            clickBtnBackMail();
        }
        scrollToPageSizeDropdown();
        List<String> pageSizes = getAllOptionsinPageSizeDropdown();
        List<String> sizeList = Arrays.asList("10", "20", "30", "40", "50", "100", "200");
        softAssert.assertEquals(sizeList, pageSizes);
        // Page size test for connect me tab
        List<WebElement> noOfRowsDefault = getLstSubject();
        // Verify mail counts as per pagination count.
        String paginationLabel = getLblNotificationCountPagination();
        String totalMailCount = paginationLabel.split("of")[1].trim();
        String pageMailCount = paginationLabel.split("of")[0].trim().split("-")[1].trim();
        softAssert.assertEquals(noOfRowsDefault.size(), Integer.parseInt(pageMailCount),
                "Page size is not correct by default");
        // Verifying data are displaying as per the selection of count in pagination
        // list box
        verifyPaginationForPageSize(softAssert, 10);
        verifyPaginationForPageSize(softAssert, 20);
        verifyPaginationForPageSize(softAssert, 30);
        verifyPaginationForPageSize(softAssert, 40);
        verifyPaginationForPageSize(softAssert, 50);
        verifyPaginationForPageSize(softAssert, 100);
        verifyPaginationForPageSize(softAssert, 200);
    }

    public void setPageSizeOfNotifications(int pageSize) {
        try {
            scrollPageToElement(getWebElementPageSize());
            selectPageSizeDropdown(String.valueOf(pageSize));
            waitForImgLoadingInvisibility();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void verifyPaginationForPageSize(SoftAssert softAssert, int pageSize) {
        String paginationLabel = getLblNotificationCountPagination();
        String totalMailCount = paginationLabel.split("of")[1].trim();
        scrollToPageSizeDropdown();
        if (Integer.parseInt(totalMailCount) > pageSize) {
            selectPageSizeDropdown(String.valueOf(pageSize));
            waitForImgLoadingInvisibility();
            List<WebElement> noOfRows1 = getLstSubject();
            softAssert.assertEquals(noOfRows1.size(), pageSize, "Page size is not working for PageSize " + pageSize);
            if (isNextButtonVisible()) {
                clickNextButton();
                waitForImgLoadingInvisibility();
                softAssert.assertTrue(isPreviousButtonVisible(), "Previous btn is not displayed for PageSize " + pageSize);
            }
            if (isPreviousButtonVisible()) {
                clickPreviousButton();
                waitForImgLoadingInvisibility();
                softAssert.assertTrue(isNextButtonVisible(), "Previous btn is not displayed for PageSize " + pageSize);
            }
        }
        if (Integer.parseInt(totalMailCount) <= 10) {
            List<WebElement> noOfRows1 = getLstSubject();
            softAssert.assertEquals(noOfRows1.size(), pageSize, "Page size is not working for records less than 10");
            softAssert.assertFalse(isNextButtonVisible(), "Next btn is displayed for records less than 10");
        }
    }

    public void verifyProblemSignInLoginIssuesNotification(SoftAssert softAssert, String email, String messageBody) {
        getLstSubject().get(0).click();
        waitForImgLoadingInvisibility();
        waitForDateFieldValueMsgDetailToBeVisible();
//        softAssert.assertTrue(getFromFieldValueMsgDetail().equalsIgnoreCase(email),
//                "From Email value present on ProblemsSignIn UI Notification is not as Expected."); //todo check this from Email Functionality later
        softAssert.assertTrue(getFromFieldValueMsgDetail().equalsIgnoreCase(adminNotificationTextProp.getPropValue("txtLblDefaultFromNotification")),
                "From Email value present on ProblemsSignIn UI Notification is not as Expected.");
        softAssert.assertTrue(getSubjectFieldValueMsgDetail().equalsIgnoreCase(adminNotificationTextProp.getPropValue("txtLblSubjectProblemsSigningIn")),
                "Subject value present on ProblemsSignIn UI Notification is not as Expected.");
        softAssert.assertTrue(getMessageFieldValueMsgDetail().contains(messageBody),
                "Message Body value present on ProblemsSignIn UI Notification is not as Expected.");
    }

    public void verifyProblemSigningInEmailNotification(SoftAssert softAssert, String email, String messageBody) {
        try {
            ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.getProblemsSigningInEmailNotifications(email));
            rs.next();
            softAssert.assertTrue(rs.getString("EmailID").equalsIgnoreCase(email),
                    "From Email value present on ProblemsSignIn DB Notification is not as Expected.");
            softAssert.assertTrue(rs.getString("Subject").equalsIgnoreCase(adminNotificationTextProp.getPropValue("txtLblSubjectProblemsSigningIn")),
                    "Subject value present on ProblemsSignIn DB Notification is not as Expected.");
            softAssert.assertTrue(rs.getString("Message").contains(messageBody),
                    "Message Body value present on ProblemsSignIn DB Notification is not as Expected.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void verifyValidationMsgSaveUnselectedNotification(SoftAssert softAssert, String moduleName) {
        navigateSubModule(moduleName);
        clickBtnSaveHeader();
        String actTstMsg = getToastSuccessMessageWithoutCloseBtn();
        softAssert.assertEquals(actTstMsg, adminNotificationTextProp.getPropValue("txtAlertSaveMsgNoSelection"),
                "Toast Validation message for unselected notification for save header icon Clicked is not as Expected.");
        waitForToastMessageInvisibility();
    }

    public void verifyValidationMsgCompleteUnselectedNotification(SoftAssert softAssert, String moduleName) {
        navigateSubModule(moduleName);
        waitForLstSubjectToBeVisible();
        clickBtnCompleteMessageHeader();
        String actTstMsg = getToastSuccessMessageWithoutCloseBtn();
        softAssert.assertEquals(actTstMsg, adminNotificationTextProp.getPropValue("txtAlertCompleteMsgNoSelection"),
                "Toast Validation message for unselected notification for Complete header icon Clicked is not as Expected.");
        waitForToastMessageInvisibility();
    }

    public void verifySubFolderNotificationHeaderIconsPresent(String moduleName) {
        navigateSubModule(moduleName);
        Assert.assertTrue(isbtnDeleteHeaderVisible(), "Delete button on the Header is not Visible for the " + moduleName + " submodule.");
        Assert.assertTrue(isBtnSaveHeaderVisible(), "Save button on the Header is not Visible for the " + moduleName + " submodule.");
        Assert.assertTrue(isBtnCompleteMessageHeaderVisible(), "Complete button on the Header is not Visible for the " + moduleName + " submodule.");
    }

    public void saveNotificationByRequestID(String moduleName, String requestID) {
        navigateSubModule(moduleName);
        clickSaveIconForRequestID(requestID);
        waitForImgLoadingInvisibility();
    }

    public void completeNotificationByRequestID(SoftAssert softAssert, String moduleName, String requestID) {
        navigateSubModule(moduleName);
        waitForLstSubjectToBeVisible();
        clickDoneIconForRequestID(requestID);
        waitForImgLoadingInvisibility();
        String toastMsg = getToastSuccessMessageWithoutCloseBtn();
        softAssert.assertTrue(toastMsg.contains(adminNotificationTextProp.getPropValue("txtToastCompleteMessage")),
                "Toast message for Completed Message is not Matched.");
        waitForToastMessageInvisibility();
    }

    public boolean isNotificationSavedFromSaveIconByRequestID(String moduleName, String requestID) {
        boolean saveFlag;
        navigateSubModule(moduleName);
        waitForLstSubjectToBeVisible();
        Integer saveIconAttributeForRequestID = getSaveIconAttributeForRequestID(requestID);
        if (saveIconAttributeForRequestID == 0)
            saveFlag = false;
        else
            saveFlag = true;
        return saveFlag;
    }

    public void verifyDoneSymbolOnTrashFolder(SoftAssert softAssert, String moduleName, String requestID) {
        navigateSubModule(moduleName);
        waitForLstSubjectToBeVisible();
        softAssert.assertFalse(isBtnCompleteMessageHeaderVisible(), "Complete or Done Symbol is displayed on the Trash Header Row.");
        softAssert.assertFalse(isLstDoneSymbolNotificationGridColumnVisible(), "Complete or Done Symbol is displayed on the Trash Grid Table.");
//        navigateToNotificationByRequestId(requestID); //todo open Defect SCM10-11286
        getLstSubject().stream().limit(1).forEach(s -> s.click());
        waitForImgLoadingInvisibility();
        softAssert.assertFalse(isBtnCompleteMessageHeaderVisible(), "Complete or Done Symbol is displayed on the Trash Notification Details Header Row.");
    }

    public void completeMultipleNotifications(SoftAssert softAssert, String moduleName, List<String> requestIds) {
        navigateSubModule(moduleName);
        requestIds.stream().forEach(s -> clickCheckBoxForRequestID(s));
        clickBtnCompleteMessageHeader();
        String toastMsg = getToastSuccessMessageWithoutCloseBtn();
        softAssert.assertTrue(toastMsg.contains(adminNotificationTextProp.getPropValue("txtToastCompleteMessage")),
                "Toast message for Completed Messages is not Matched.");
        waitForImgLoadingInvisibility();
        waitForToastMessageInvisibility();
    }

    public void saveMultipleNotifications(SoftAssert softAssert, String moduleName, List<String> requestIds) {
        navigateSubModule(moduleName);
        requestIds.stream().forEach(s -> clickCheckBoxForRequestID(s));
        clickBtnSaveHeader();
        String toastMsg = getToastSuccessMessageWithoutCloseBtn();
        softAssert.assertTrue(toastMsg.contains(adminNotificationTextProp.getPropValue("txtToastSaveMessage")),
                "Toast message for Save Messages is not Matched.");
        waitForImgLoadingInvisibility();
        waitForToastMessageInvisibility();
    }

    public boolean isMultipleNotificationsPresent(String moduleName, List<String> requestIds) {
        navigateSubModule(moduleName);
        waitForLstSubjectToBeVisible();
        List<WebElement> lstEleSubject = getLstSubject();
        List<String> lstSubjectText = new ArrayList<String>();
        for (WebElement ele : lstEleSubject) {
            lstSubjectText.add(ele.getText().split(": ")[1].replace(")", ""));
        }
        return lstSubjectText.containsAll(requestIds);
    }

    public void verifyMouseHoverMessageForDoneBtn(SoftAssert softAssert, String moduleName) {
        navigateSubModule(moduleName);
        softAssert.assertEquals(getBtnCompleteMessageHeaderMouseHoverText(), adminNotificationTextProp.getPropValue("txtMouseHoverDoneButtonHeader"),
                "Mouse Hover Text for Done button on the header is not Expected for the " + moduleName + " submodule.");
    }

    public void verifyAutoResponseUIObjects(SoftAssert softAssert) {
        navigateSubModule("Auto Response");
        waitForAutoRespSaveButtonToBeVisible();
        //Checking the visibility of Objects
        Assert.assertTrue(isLblAutoReponseSettingHeadingVisible(), "AutoResponse Setting Heading is not visible on Auto-Response Editor page.");
        Assert.assertTrue(isLblEnableAutoResponseVisible(), "Enable AutoResponse Label is not visible on Auto-Response Editor page.");
        Assert.assertTrue(isChkBoxEnableAutoResponseVisible(), "Enable AutoResponse checkbox is not visible on Auto-Response Editor page.");
        Assert.assertTrue(isTxtBoxHTMLEditorAutoResponseVisible(), "AutoResponse Editor TextBox is not visible on Auto-Response Editor page.");
        Assert.assertTrue(isBtnClearAutoResponseVisible(), "Clear Button is not visible on Auto-Response Editor page.");
        Assert.assertTrue(isBtnSaveAutoResponseVisible(), "Save Button is not visible on Auto-Response Editor page.");
        //Checking the label of the Objects
        softAssert.assertTrue(getLblAutoReponseSettingHeading().equalsIgnoreCase("Auto Response Settings"),
                "Auto Response Setting Heading is not as Expected on Auto Response Editor page.");
        softAssert.assertTrue(getLblEnableAutoResponse().equalsIgnoreCase("Enable Auto Response"),
                "Enable Auto Response Label is not as Expected on Auto Response Editor page.");
        softAssert.assertTrue(getLblBtnClearAutoResponse().equalsIgnoreCase("Clear"),
                "Clear Button Label is not as Expected on Auto Response Editor page.");
        softAssert.assertTrue(getLblBtnSaveAutoResponse().equalsIgnoreCase("Save"),
                "Save Button Label is not as Expected on Auto Response Editor page.");
    }

    public void enableCheckBoxAutoResponse() {
        if (!getEnableCheckBoxStatusAutoResponse().equals("checked"))
            clickChkBoxEnableAutoResponse();
    }

    public void disableCheckBoxAutoResponse() {
        if (getEnableCheckBoxStatusAutoResponse().equals("checked"))
            clickChkBoxEnableAutoResponse();
    }

    public boolean isAutoResponseCheckBoxEnabled() {
        waitForAutoRespSaveButtonToBeVisible();
        if (getEnableCheckBoxStatusAutoResponse().equals("checked"))
            return true;
        else
            return false;
    }

    public void enableAutoResponseAndSubmitWithText(SoftAssert softAssert, String autoResponseText) {
        if (!isAutoResponseCheckBoxEnabled()) {
            enableCheckBoxAutoResponse();
            clearTxtBoxAutoResponse();
            populatetxtBoxAutoResponse(autoResponseText);
            clickBtnSaveAutoResponse();
            String toastMsg = getToastSuccessMessageWithoutCloseBtn();
            softAssert.assertTrue(toastMsg.contains(adminNotificationTextProp.getPropValue("txtAlertAutoResponseSave")),
                    "Toast message not matched for enable auto response sucesss submission.");
            waitForToastMessageInvisibility();
        }
        else
            log.info("AutoResponse has been already enabled.");
    }

    public void disableAutoResponseAndSubmitWithText(SoftAssert softAssert, String autoResponseText) {
        if (isAutoResponseCheckBoxEnabled()) {
            disableCheckBoxAutoResponse();
            clearTxtBoxAutoResponse();
            populatetxtBoxAutoResponse(autoResponseText);
            clickBtnSaveAutoResponse();
            String toastMsg = getToastSuccessMessageWithoutCloseBtn();
            softAssert.assertTrue(toastMsg.contains(adminNotificationTextProp.getPropValue("txtAlertAutoResponseSave")),
                    "Toast message not matched for disable auto response sucesss submission.");
            waitForToastMessageInvisibility();
        }
        else
            log.info("AutoResponse has been already disabled.");
    }

    /****************************************************************************************************
     * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> REUSABLE METHODS <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< *
     *****************************************************************************************************/

    public Boolean isAdminNotificationInboxPage(String url, String title) {
        Boolean isAdminNotificationInboxPage = false;
        log.info("Checking that the current page is Admin Notification Inbox page.");
        if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
            isAdminNotificationInboxPage = true;
        log.info("The current page is Admin Notification Inbox page {}: " + isAdminNotificationInboxPage);
        return isAdminNotificationInboxPage;
    }

    public Boolean isAdminNotificationOutboxPage(String url, String title) {
        Boolean isAdminNotificationOutboxPage = false;
        log.info("Checking that the current page is Admin Notification Outbox page.");
        if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
            isAdminNotificationOutboxPage = true;
        log.info("The current page is Admin Notification Inbox page {}: " + isAdminNotificationOutboxPage);
        return isAdminNotificationOutboxPage;
    }

    public void navigateToNotificationByRequestId(String notificationID) {
//        List<WebElement> subConnect = getLstSubject();
//        for (int i = 0; i < subConnect.size(); i++) {
//            if (subConnect.get(i).getText().contains(notificationID)) {
//                clickLstSubject(i);
//                waitForImgLoadingInvisibility();
//                break;
//            }
//        }
        getLstSubject().stream().filter(s -> s.getText().contains(notificationID)).forEach(s -> {
            s.click();
            waitForImgLoadingInvisibility();
        });
    }

    public void navigateToNotificationSpecificPageByRequestId(String notificationID) {
        boolean notificationReqIDPageLanded;
        do {
            notificationReqIDPageLanded = getLstSubject().stream().anyMatch(s -> s.getText().contains(notificationID));
            if (!notificationReqIDPageLanded) {
                scrollToElement(getWebEleBtnNext());
                clickNextButton();
                waitForImgLoadingInvisibility();
            }
        } while (!notificationReqIDPageLanded);
        if (notificationReqIDPageLanded)
            clickSubjectForRequestID(notificationID);
    }

    public boolean isNotificationPresentByRequestId(String moduleName, String requestID) {
        navigateSubModule(moduleName);
        waitForLstSubjectToBeVisible();
        boolean presentFlag = getLstSubject().stream().anyMatch(s -> s.getText().contains(requestID));
        return presentFlag;
    }

    public void assertNotificationPresentByRequestId(String moduleName, String requestID) {
        navigateSubModule(moduleName);
        Assert.assertTrue(getLstSubject().stream().anyMatch(s -> s.getText().contains(requestID)),
                "The Request ID " + requestID + " is not present on the " + moduleName + " sub-module of Notifications.");
    }

    public void navigateToNotificationByRequestId(String moduleName, String notificationID) {
        navigateSubModule(moduleName);
//        List<WebElement> subConnect = getLstSubject();
//        for (int i = 0; i < subConnect.size(); i++) {
//            if (subConnect.get(i).getText().contains(notificationID)) {
//                clickLstSubject(i);
//                waitForImgLoadingInvisibility();
//                break;
//            }
//        }
        getLstSubject().stream().filter(s -> s.getText().contains(notificationID)).forEach(s -> {
            s.click();
            waitForImgLoadingInvisibility();
        });
    }

    public void enableALlNotificationsView() {
        clickTglBtnUnReadAllNotify();
        waitForImgLoadingInvisibility();
    }

    public void navigateSubModule(String moduleName) {
        switch (moduleName) {
            case "Inbox":
                clickLnkInbox();
                waitForImgLoadingInvisibility();
                break;
            case "Connect_Me":
                clickLinkConnectMe();
                waitForImgLoadingInvisibility();
                break;
            case "Billing":
                clickLinkBilling();
                waitForImgLoadingInvisibility();
                break;
            case "Outage":
                clickLinkOutage();
                waitForImgLoadingInvisibility();
                break;
            case "Services":
                clickLnkServices();
                waitForImgLoadingInvisibility();
                break;
            case "Demand_Response":
                clickLnkDemandResponse();
                waitForImgLoadingInvisibility();
                break;
            case "Leak_Alert":
                clickLnkLeakAlert();
                waitForImgLoadingInvisibility();
                break;
            case "Login_Issues":
                clickLnkLoginIssues();
                waitForImgLoadingInvisibility();
                break;
            case "Sent":
                clickLnkSent();
                waitForImgLoadingInvisibility();
                break;
            case "Complete":
                clickLnkComplete();
                waitForImgLoadingInvisibility();
                break;
            case "Saved_Mail":
                clickLnkSavedMail();
                waitForImgLoadingInvisibility();
                break;
            case "Trash":
                clickLnkTrash();
                waitForImgLoadingInvisibility();
                break;
            case "Auto_Response":
                clickLnkAutoResponse();
                waitForImgLoadingInvisibility();
                break;
            case "Send_Notification":
                clickLnkSendNotification();
                waitForImgLoadingInvisibility();
                break;
            default:
                System.out.println(moduleName + "submodule is not available on notifications page.");
        }
    }

    public HashMap generateConnectMeOutageAPIRequest() {
        ConnectMeEndpoints connectMeEndpoints;
        connectMeEndpoints = new ConnectMeEndpoints();
        HashMap responseData = connectMeEndpoints.reportOutageConnectMeRequest();
        String outageRequestID = responseData.get("OutageRequestID").toString();
        System.out.println("The Outage Request ID Created through API is " + outageRequestID);
        return responseData;
    }

    public HashMap generatePreLogConnectMeOutageAPIRequest() {
        ConnectMeEndpoints connectMeEndpoints;
        connectMeEndpoints = new ConnectMeEndpoints();
        HashMap responseData = connectMeEndpoints.reportPreLogOutageConnectMeRequest();
        String outageRequestID = responseData.get("OutageRequestID").toString();
        System.out.println("The Outage Request ID Created through API is " + outageRequestID);
        return responseData;
    }

    public String generateConnectMeProgramsAPIRequest() {
        ConnectMeEndpoints connectMeEndpoints;
        connectMeEndpoints = new ConnectMeEndpoints();
        String programsRequestID = connectMeEndpoints.reportProgramsConnectMeRequest();
        System.out.println("The Programs Request ID Created through ConnectMeAPI is " + programsRequestID);
        return programsRequestID;
    }

    public String createMoveInServicesAPIRequest() {
        ServiceEndpoint serviceEndpoint;
        serviceEndpoint = new ServiceEndpoint();
        String serviceReqID = serviceEndpoint.createMoveInServicesRequest();
        return serviceReqID;
    }

    public ProblemSignInPayload generateProblemSigningInAPIRequest() {
        LoginSupportEndpoint loginSupportEndpoint;
        loginSupportEndpoint = new LoginSupportEndpoint();
        return loginSupportEndpoint.generateProblemsSigningInSCPRequest();
    }

    public String createPreLogMoveInServicesAPIRequest() {
        ServiceEndpoint serviceEndpoint;
        serviceEndpoint = new ServiceEndpoint();
        String serviceReqID = serviceEndpoint.createPreLogMoveInServicesRequest();
        return serviceReqID;
    }

    public void clearSearchResults() {
        clickBtnClear();
        waitForImgLoadingInvisibility();
    }

    public String getCustomerServiceNumber() {
        String rawPhoneNo = null;
        try {
            ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.sAllUtilitySettingsQuery);
            while (rs.next()) {
                rawPhoneNo = rs.getString("CustomerServiceNumber");
                System.out.println("The raw Phone Number from the DB is " + rawPhoneNo + " !!!!");
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        String formattedPhoneNo = PhoneNoUtil.convertStringToPhoneNumberFormat(rawPhoneNo);
//        String formattedPhoneNo = PhoneNoUtil.convertStringToPhoneNumberFormat1(rawPhoneNo);
        System.out.println("The Formatted Phone Number from the phoneNumber function is " + formattedPhoneNo + " !!!!");
        return formattedPhoneNo;
    }

    public String getCustomerServiceEmail() {
        String customerServiceEmail = null;
        try {
            ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.sAllUtilitySettingsQuery);
            while (rs.next()) {
                customerServiceEmail = rs.getString("CustomerServiceEmail");
                System.out.println("The CustomerServiceEmail from the DB is " + customerServiceEmail + " !!!!");
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return customerServiceEmail;
    }

    public String getTemplateIdForTemplateName(String templateName) {
        String templateId = null;
        try {
            ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.getEmailTemplate(templateName));
            while (rs.next()) {
                templateId = rs.getString("TemplateId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return templateId;
    }
}

package sew.ai.steps.scp;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import sew.ai.config.SCPConfiguration;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.models.User;
import sew.ai.pageObjects.scp.NotificationPage;
import sew.ai.pageObjects.scp.PreLogConnectMePage;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.PropertiesUtil;

public class NotificationSteps extends NotificationPage {
    private static final Logger log = LogManager.getLogger(NotificationSteps.class);

    public static PropertiesUtil NotificationTextProp;

    public NotificationSteps(WebDriver driver) {
        super(driver);       
        NotificationTextProp = new PropertiesUtil(
                FilePaths.SCP_TEXT_PROPERTIES + Constants.NOTIFICATION_TXT_FILENAME
                );               
  }

    public void verifyNotificationObject(SoftAssert softAssert) throws SQLException {  
    	 User user = SCPConfiguration.user;
    	 ResultSet rs;
    	 rs = DataBaseUtils.getResultSet(SQLQueries.getUserId(user.getUserName()));
         rs.next();
         String userid = rs.getString("UserID");
         int userID = Integer.parseInt(userid);
         rs = DataBaseUtils.getResultSet(SQLQueries.getDefaultAccountNumber(user.getUserName()));
         rs.next();
         String accountNum = rs.getString("AccountNumber");
         int defaultAccount = Integer.parseInt(accountNum);
    	clickBtnNotification();
        pause(5000);
        softAssert.assertTrue(isNotificationPage(NotificationTextProp.getPropValue("NotificationInboxPageUrl"),
        		 (NotificationTextProp.getPropValue("NotificationInboxPageTitle"))),"Page Title & URL does not Match");
        softAssert.assertTrue(isPageTitleVisible(), "Notifcation page Header is not visible"); 
        softAssert.assertTrue(isStarSaveVisible(), "Start button is not visible"); 
        softAssert.assertTrue(isDeleteButtonVisible(), "Delete button button is not visible");
        softAssert.assertTrue(isRefreshButtonVisible(), "Refresh button button is not visible");
        softAssert.assertTrue(isAllDropdownLstVisible(), "All Dropdown List button button is not visible");
        softAssert.assertTrue(isInboxSideFolderVisible(), "Inbox button button is not visible");
        softAssert.assertTrue(isSentSideFolderVisible(), "Inbox button is not visible");
        softAssert.assertTrue(isSavedSideFolderVisible(), "Saved button is not visible");
        softAssert.assertTrue(isTrashSideFolderVisible(), "Trash button button is not visible");
        softAssert.assertTrue(isAllMailSideFolderVisible(), "All Mail button is not visible");
        softAssert.assertTrue(isbtnCategoriesNotificationDropDownVisible(), "Categories button is not visible");
       
//        int inboxCount = mongoDataBaseUtil.getInboxMailCount("scm10_s", "message",
//                defaultAccount, userID);
        if(isInboxSideFolderVisible()) {
        	 isNoMsgAvailableVisible();
             String noMsgAvailable = getlblNoMsgAvailable();
             Assert.assertEquals(noMsgAvailable , NotificationTextProp.getPropValue("txtLblMsgNoMessages"));
             // Verify mail-option tools not present
             isStarSaveNotVisible();
             isDeleteButtonNotVisible();
             isRefreshButtonNotVisible();
             isAllDropdownLstNotVisible();
        }
             else {
            	 softAssert.assertTrue(isStarSaveVisible(), "Start button is not visible"); 
                 softAssert.assertTrue(isDeleteButtonVisible(), "Delete button button is not visible");
                 softAssert.assertTrue(isRefreshButtonVisible(), "Refresh button button is not visible");
                 softAssert.assertTrue(isAllDropdownLstVisible(), "All Dropdown List button button is not visible"); 
             }
             // Sent
             if(isSentSideFolderVisible()) {
            	 isSentSideFolderVisible();
            	 String saveMsg = getlblSentItem();
                 Assert.assertEquals(saveMsg , NotificationTextProp.getPropValue("txtSentFolderName"));
             }
            // Trash
             if(isTrashSideFolderVisible()) {
            	 isTrashSideFolderVisible();
            	 String trashMsg = getlblTrashSideFolder();
                 Assert.assertEquals(trashMsg , NotificationTextProp.getPropValue("txtTrashFolderName"));
             }
             // Saved
             if(isSavedSideFolderVisible()) {
            	 isSavedSideFolderVisible();
            	 String trashMsg = getlblSavedSideFolder();
                 Assert.assertEquals(trashMsg , NotificationTextProp.getPropValue("txtSavedFolderName"));
             }
             // All Mail
             if(isSavedSideFolderVisible()) {
            	 isSavedSideFolderVisible();
            	 String trashMsg = getlblAllMailFolder();
                 Assert.assertEquals(trashMsg , NotificationTextProp.getPropValue("txtAllMailFolderName"));
             }
             else {
            	 log.info("My Notifications link is not available on the header");
             }
    log.info("To verify Navigation, UI And Objects of Notifications page");
}
   
    public boolean isNotificationPage(String url, String title) {
        Boolean isNotificationPage = false;
        log.info("Checking that the current page is ForgetPassword Page");
        if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
        	isNotificationPage = true;
        log.info("The current page is ForgetPassword Page {}: " + isNotificationPage);
        return isNotificationPage;
    }
    
//    public void verifyOutageNotificationMailAttributes() {
//    	clickbtnCategoriesNotificationDropDown();
//    	String Class = getDropdownClass();
//    	if(Class.contains("active")) {
//    	clickchkBoxAllCategory();
//    	clickchkBoxOutageCat();		
//    	}
////    	 outageTotalCount = mongoDataBaseUtil.getCategoryWiseMailCount(dbname, collectionName, accountNumber,
////                 userID, outageCategoryId);
//        //	outageTotalCount == 0
//    	 if(isInboxSideFolderVisible()) {
//        	 isNoMsgAvailableVisible();
//             String noMsgAvailable = getlblNoMsgAvailable();
//             Assert.assertEquals(noMsgAvailable , NotificationTextProp.getPropValue("txtLblMsgNoMessages"));
//             // Verify mail-option tools not present
//             isStarSaveNotVisible();
//             isDeleteButtonNotVisible();
//             isRefreshButtonNotVisible();
//             isAllDropdownLstNotVisible();
//        } else {
//        	 if(outageTotalCount > 50){
//             
//    	         
//       }
//    }
}

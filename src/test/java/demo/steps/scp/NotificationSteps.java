package demo.steps.scp;

import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import demo.pageobjects.NotificationsPage;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.helpers.reporters.ExtentManager;
import sew.ai.utils.PropertiesUtil;
import sew.ai.utils.ScreenshotUtils;


public class NotificationSteps extends NotificationsPage {
    private static final Logger log = LogManager.getLogger(NotificationSteps.class);

    public static PropertiesUtil NotificationTextProp;

    public NotificationSteps(WebDriver driver) {
        super(driver);       
        NotificationTextProp = new PropertiesUtil(
                FilePaths.SCP_TEXT_PROPERTIES + Constants.NOTIFICATION_TXT_FILENAME
                );               
  }
    
    public void navtigateToNotifications() throws InterruptedException {
	    ExtentLogger.logInfo("Click Notification Button.");
	        clickBtnNotification();
 }

    public void verifyNotificationObject(SoftAssert softAssert) throws SQLException {  
        pause(5000);
        softAssert.assertTrue(isNotificationPage(NotificationTextProp.getPropValue("NotificationInboxPageUrl"),
       		 (NotificationTextProp.getPropValue("NotificationInboxPageTitle"))),"Page Title & URL does not Match");
        ExtentLogger.logInfo("Check Notification numbers matches.");
        if (getCountInbox()== getCountNotifications());
        {
        ExtentLogger.logInfo("Notification Page.");
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
        }
        if(isInboxSideFolderVisible()) {
        
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
            
            clickNotification();
            pause(5000);
            softAssert.assertTrue(isReplyButtonDisplayed(), "Reply Button is not visible");
            softAssert.assertTrue(isNotificationDeleteButtonVisible(), "Notification Delete Button is not visible");
            softAssert.assertTrue(isSaveButtonVisible(), "Save Button is not visible");
            softAssert.assertTrue(isBackButtonVisible(), "Button Button is not visible");
            softAssert.assertTrue(isSubjectVisible(), "Subject is not visible");
            softAssert.assertTrue(isServiceAccountNumVisible(), "Service Account Number is not visible");
            softAssert.assertTrue(isAccountNumVisible(), "Account Number is not visible");
            softAssert.assertTrue(isFromOptionVisible(), "From is not visible");
            softAssert.assertTrue(isFromValueVisible(), " From Value is not visible");
            softAssert.assertTrue(isDateVisible(), "Date is not visible");
            softAssert.assertTrue(isMailBodyVisible(), "Mail Body is not visible");       
            
}
   
    public boolean isNotificationPage(String url, String title) {
        Boolean isNotificationPage = false;
        log.info("Checking that the current page is Notification Page");
        if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
        	isNotificationPage = true;
        log.info("The current page is Notification Page {}: " + isNotificationPage);
        return isNotificationPage;
    }
    
    public void deleteMiltipleNotification (SoftAssert softAssert) throws SQLException {
    	   
		    ExtentLogger.logInfo("Click Multiple checkboxes.");
		    clickCheckBox();
		    clickCheckBox1();
		    clickCheckBox2();
		    
		    ExtentLogger.logInfo("Click delete Icon.");
		    clickDeleteButton();
		    
		    ExtentLogger.logInfo("Click Continue Button.");
		    clickDeleteContinueButton();
		    waitForPageToLoad();
		    
		    isDeleteSuccessToastDisplayed();
		    softAssert.assertEquals(getSuccessMessage(),NotificationTextProp.getPropValue("txtDeleteSuccessMessage"),
					"Delete Successs message do not match");
		    	    		    			    
}
    
    public void deleteNotification (SoftAssert softAssert) throws SQLException {
    	    
		    ExtentLogger.logInfo("Click one of the notification from the list.");
		    clickNotification();
		    
		    ExtentLogger.logInfo("Click Trash Icon.");
		    clickTrashIcon();
		    waitForPageToLoad();
		    
		    ExtentLogger.logInfo("Click Continue Button.");
		    clickDeleteContinueButton1();
		    waitForPageToLoad();
		    
		    isDeleteSuccessToastDisplayed();
		    softAssert.assertEquals(getSuccessMessage(),NotificationTextProp.getPropValue("txtDeleteSuccessMessage"),
					"Delete Successs message do not match");
		    
		    			    
}
    
    public void saveMultipleNotifications() throws InterruptedException {
	    
	    ExtentLogger.logInfo("Click Multiple checkboxes.");
	    clickCheckBox();
	    clickCheckBox1();
	    clickCheckBox2();
	    
	 //"Click save Icon.
	    clickSaveIcon();
	    
	    pause(3000);
	    	    
      //"Click save Folder.
	    clickSavedLink();
	    
	    pause(3000);
	    
	    ExtentManager.getExtentTest().addScreenCaptureFromBase64String(ScreenshotUtils.getScreenshot(driver), "Screen Capture of Saved messages in Saved Folder- Save Notifications in Saved Folder" );
	    ExtentLogger.logInfo("Saved messages in Saved Folder  Screenshot is captured. ");
	    			    	    			    
}
    
    public void unsaveAllNotifications(SoftAssert softAssert) throws SQLException {
	    
	    //"Click save Folder.
	    clickSavedLink();
	    pause(5000);
	    	    
	    //Click All checkboxes.
	    clickAllCheckBox();
        
        //Click save Icon.
	    clickSaveIcon();
	    
	    ExtentLogger.logInfo("Verify There are no messages in this Folder Text.");
	    isNoMsgAvailableVisible();
	    softAssert.assertEquals(getNoMessage(),NotificationTextProp.getPropValue("txtLblMsgNoMessages"),
				"No Messages Text do not match");		    			    
}
    public void deleteNotificationsPermn(SoftAssert softAssert) throws SQLException {
	   
	    //Click Trash Folder
	    clickTrashFolder();
	    waitForPageToLoad();
	    
	    //Click Multiple checkboxes.
	    pause(2000);
	    clickCheckBox();
	    clickCheckBox1();
	    clickCheckBox2();
	    
	   //Click delete Icon.
	    clickDeleteButton();
	    
	  //Click Continue Button.
	    clickDeleteContinueButton();
	    waitForPageToLoad();
	    
	    isDeleteSuccessToastDisplayed();
	    softAssert.assertEquals(getSuccessMessage(),NotificationTextProp.getPropValue("txtDeleteSuccessMessage"),
				"Delete Successs message do not match");		    			    
}
    
    public void putBackNotifications(SoftAssert softAssert) throws SQLException {
	    
	   //Click Trash Folder
	    clickTrashFolder();
	    
	   //("Click Multiple checkboxes.");
	    pause(2000);
	    clickCheckBox();
	    //clickCheckBox1();
	    
	 //("Click put back Icon.");
	    clickPutBackIcon();
	    
	    isPutBackSuccessToastDisplayed();
	    softAssert.assertEquals(getPutBackSuccessMessage(),NotificationTextProp.getPropValue("txtDeleteSuccessMessage"),
				"Delete Successs message do not match");
	  
	        		    			    
}
    
    
    public void replyToNotification(SoftAssert softAssert) throws SQLException {
	   
	         ExtentLogger.logInfo("Click one of the notification from the list.");
	         clickNotification();
	    
	         ExtentLogger.logInfo("Click reply Button.");
	         clickReplyButton();
	    
	         ExtentLogger.logInfo("Enter message in the message body.");
	         isTxtBoxMessageBodyVisible();
	         populateTxtMessageBody("Testing purpose");
	         elementTextMessageBody().sendKeys(Keys.TAB);
			
			 ExtentLogger.logInfo("Click reply Button.");
			 clickReplySubmitButton();
		     
			 isReplySuccessToastDisplayed();
			 softAssert.assertEquals(getReplySuccessMessage(),NotificationTextProp.getPropValue("txtReplySuccessMessage"),
						"Reply Successs message do not match");
			 
			 pause(4000);
			 
			 softAssert.assertTrue(isReplyMailBodyVisible(), "Reply Mail Body is not visible");
			 clickSentLink();
			 ExtentManager.getExtentTest().addScreenCaptureFromBase64String(ScreenshotUtils.getScreenshot(driver), "Screen Capture of Replied messages in Sent Folder- Replied message in Sent Folder" );
			    ExtentLogger.logInfo("Replied messages in sent Folder  Screenshot is captured. ");
			 
			 
		    		    		    			    
}
    
    public void negativesceNotifications(SoftAssert softAssert) throws SQLException {
	    
	    ExtentLogger.logInfo("Click delete Icon.");
	    clickDeleteButton();
	    ExtentLogger.logInfo("Verify Warning Toast.");
	    isWarningToastDisplayed();
		 softAssert.assertEquals(getWarningMessage(),NotificationTextProp.getPropValue("txtWarningMessage"),
					"Warning message do not match");
	    
	    ExtentLogger.logInfo("Click save Icon.");
	    clickSaveIcon();
	    ExtentLogger.logInfo("Verify Warning Toast.");
	    isWarningToastDisplayed();
		 softAssert.assertEquals(getWarningMessage(),NotificationTextProp.getPropValue("txtWarningMessage"),
					"Warning message do not match");
	    
	    ExtentLogger.logInfo("Click one of the notification from the list.");
	    clickNotification();
	    clickReplyButton();
	    clickReplySubmitButton();
	    
	    isEnterMessageToastDisplayed();
		 softAssert.assertEquals(getEnterMessage(),NotificationTextProp.getPropValue("txtWarningMessage"),
					"Warning message do not match");	
		 
		 
	
	}
    
    
}
    


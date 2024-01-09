package sew.ai.steps.scp;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static sew.ai.steps.scp.LoginSteps.loginTextProp;

import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.runner.Runner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;
import sew.ai.helpers.props.FilePaths;

import sew.ai.config.SCPConfiguration;
import sew.ai.driver.Page;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.models.User;
import sew.ai.pageObjects.scp.PostLogConnectMePage;
import sew.ai.pageObjects.scp.ProblemSignInPage;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.DateUtil;
import sew.ai.utils.PropertiesUtil;
import static sew.ai.helpers.props.FilePaths.FILE_UPLOAD_PATH;

public class PostLogConnectMeSteps extends PostLogConnectMePage {
    private static final Logger log = LogManager.getLogger(ProblemSigningInSteps.class);

    public static PropertiesUtil PostLogConnectMeTextProp;

    public PostLogConnectMeSteps(WebDriver driver) {
        super(driver);       
        PostLogConnectMeTextProp = new PropertiesUtil(
                FilePaths.SCP_TEXT_PROPERTIES + Constants.POST_LOG_CONNECT_ME_TXT_FILENAME
                );
               
  }
  
   public void verifyConnectMeObject(SoftAssert softAssert) { 
	pause(5000);
    clickConnectMeLink();
    pause(5000);
    selectlstConnectMeOptions("Rebates");	   
	   if(selectlstConnectMeOptions("Rebates")) {		   		   
	   }
    softAssert.assertTrue(isPostLogConnectMePage(PostLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
     		 (PostLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),"Page Title & URL does not Match");    
    softAssert.assertTrue(isPageHeaderPostVisible(), "Contact Us Page Header is not visible"); 
    softAssert.assertTrue(isConnectMeVisible(), "Contact Us Tab is not visibility");
    softAssert.assertTrue(isSocialMediaVisible(), "Social Media Tab is not visibility");
    softAssert.assertTrue(isContactusVisible(), "Contact Us Tab is not visibility"); 
    softAssert.assertTrue(isTrackRequestVisible(), "Track Request Tab is not visibility");
    softAssert.assertTrue(isSavedFormVisible(), "Saved Form tab is not visibility");
    softAssert.assertTrue(isSubmitBtnVisible(), "Submit button is not visibility"); 
    softAssert.assertTrue(isNextBtnVisible(), "Next button is not visibility");
    softAssert.assertTrue(isCustomerNameTxtVisible(), "Customer Name text Box is not visibility");
    //softAssert.assertTrue(isServiceAccNoTxtVisible(), "Service Acc No text Box is not visibility");
    softAssert.assertTrue(isEmailAddressTxtVisible(), "Email Address text Box is not visibility");
    softAssert.assertTrue(istxtSubjectTxtVisible(), "Subject button is text Box visibility");
    softAssert.assertTrue(isCommentsTxtVisible(), "Comments text Box is not visibility");
    softAssert.assertTrue(isChooseFileBtnVisible(), "Choose File text Box is not visibility");
    //softAssert.assertTrue(isBillingEnquiresVisible(), "Instagram Tab is not visibility");
	softAssert.assertTrue(isCustomerlblVisible(), "Instagram Tab is not visibility");
  }
   
   public void verifySubmitblankForm() {
	   btnClickNext();
	   String SuccessToasterMsg = getToastMessage();
       Assert.assertEquals(SuccessToasterMsg , PostLogConnectMeTextProp.getPropValue("toasterMsgBlankForm"));
       populateSubject("Testing");
       btnClickNext();
       String errMsgComment = getlblGenericErrorMessage();
       Assert.assertEquals(errMsgComment , PostLogConnectMeTextProp.getPropValue("txtCommentBoxErrorMsg"));
       clearSubjectField();
       populateComments("Testing");
       btnClickNext();
       String errMsgSubject = getlblGenericErrorMessage();
       Assert.assertEquals(errMsgSubject , PostLogConnectMeTextProp.getPropValue("txtSubjectBoxErrorMsg"));	   
   }
   
   public void verifyCreateContactRequest(SoftAssert softAssert) { 
	   User user = SCPConfiguration.user;
	   selectlstConnectMeOptions("Rebates");	   
	   if(selectlstConnectMeOptions("Rebates")) {		   		   
	   }
	   if(isServiceAccNoTxtVisible()) {
	 		 //  String accountNo = getServiceAccNoLabel();
	 		  String accountNo = gettxtAccountNo();
	 		// Assert.assertEquals(user.getDefaultUtilityAccNum(), accountNo);
	 	   }
	 	   if(isCustomerNameTxtVisible()) {
	 			// Assert.assertEquals(user.getFullName(), getTextCustomerName());
	 	   }
	 	   if(isEmailAddressTxtVisible()) {
	 		//	 Assert.assertEquals(user.getEmailId(), getEmailAddressLabel());
	 	   }
	 	   if(isChooseFileBtnVisible()) {
	 		   String validAttachmentFileName = "meter-reading.jpg";
	 	        addAttachmentToChooseFile(FILE_UPLOAD_PATH + validAttachmentFileName);
	 	   }
	       if(istxtSubjectTxtVisible()) {
		   populateSubject("Testing");
	       }
	       if(isCommentsTxtVisible()) {
		   populateComments("Testing");
	       }
	       btnClickNext();
   }
   
   /**
    * Test 2.4: This test method verifies below tests:
    * 1). This test method is used to verify the preview your forms details.
    *
    */
   public void verifyPreviewYourFormDetails(SoftAssert softAssert) {      
       log.info("Test Case execution for - verifyPreviewYourFormDetails - is Initiated");
       User user = SCPConfiguration.user;
       List<WebElement> previewYourFormColumn = listPreviewYourFormColumn();
       List<WebElement> previewYourFormValue = listPreviewYourFormValue();
       int counter = 0;
       for (WebElement columnLabelEle : previewYourFormColumn) {
           String key = columnLabelEle.getText().trim();
           String value = previewYourFormValue.get(counter).getText().trim();
           switch (key) {
               case "Topic":
                   softAssert.assertEquals(value, "Rebates", "Topic value not matched on preview your form.");
                   pause(1000);
                   break;
               case "Customer Name":
                   //assertEquals(value, user.getFullName(), "Customer name not matched on preview your form.");
                   assertEquals(value, "PATRICIA TURNER", "Customer name not matched on preview your form.");
                   pause(1000);
                   break;
               case "Email Address":
                   //softAssert.assertEquals(value, user.getEmailId(), "Email address not matched on preview your form");
                   softAssert.assertEquals(value, "patricturner@yopmail.com", "Email address not matched on preview your form");
                   pause(1000);
                   break;
               case "Subject":
                   softAssert.assertEquals(value, "Testing" , "Subject not matched on preview your form");
                   pause(1000);
                   break;
               case "Attachment":
                  // softAssert.assertEquals(value, fileName, "Attachment name is not matched on preview your form");
            	   pause(1000);
            	   break;
               case "Comments (Optional)":
                   softAssert.assertEquals(value, "Testing", "Comments not matched on preview your form page.");
                   pause(1000);
                   break;
               default:
           }
           counter++;
       }
   }
   
   public void verifySubmitForm(SoftAssert softAssert) {	   
	   isSubmitBtnVisible();
	   btnClickSubmit();
	   isLblPopupThankYouVisible();
	   String popupContent = getlblPopupThankYou().trim();
       String referenceId = popupContent.substring(popupContent.indexOf(":") + 1).trim();
//	   Assert.assertEquals(referenceId , PostLogConnectMeTextProp.getPropValue("lblThankYouForWriting"));	  
       clickContactUsPopupOk();
//	   String referenceIdFetchedFromMail = fetchRequestIDFromEmail(referenceId);	   
	   pause(1000);
//	   softAssert.assertTrue(isPostLogConnectMePage(PostLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
//	     		 (PostLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),"Page Title & URL does not Match");	   
   }

   public boolean isPostLogConnectMePage(String url, String title) {
       Boolean isForgetPasswordPage = false;
       log.info("Checking that the current page is ForgetPassword Page");
       if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
       	isForgetPasswordPage = true;
       log.info("The current page is ForgetPassword Page {}: " + isForgetPasswordPage);
       return isForgetPasswordPage;
   }
   
   public List<String> getAllOptionsInListBox(By locator) {
		List<String> options = new ArrayList<String>();
		for (WebElement option : new Select(driver.findElement(locator)).getOptions()) {
			pause(100);
			if (option.getAttribute("value") != "")
				options.add(option.getText());
		}		
		return options;
	}
   
   public void verifySocialMediaObject(SoftAssert softAssert) {   
	    clickConnectMeLink();
	    softAssert.assertTrue(isPostLogConnectMePage(PostLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
	     		 (PostLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),"Page Title & URL does not Match");    
	    softAssert.assertTrue(isPageHeaderPostVisible(), "Contact Us Page Header is not visible"); 
	   clickSocialMedia();
	   softAssert.assertTrue(isFacebookTabVisible(), "Facebook Tab is not visible"); 
	   softAssert.assertTrue(isYouTubeTabVisible(), "YouTube Tab is not visibility");
	   softAssert.assertTrue(isTwitterTabVisible(), "Twitter Tab is not visibility");
	   softAssert.assertTrue(isInstagramTabVisible(), "Instagram Tab is not visibility"); 
	   softAssert.assertTrue(isBillingEnquiresVisible(), "Instagram Tab is not visibility");
	   softAssert.assertTrue(isCustomerlblVisible(), "Instagram Tab is not visibility");	  
	  //Click on Facebook Tab
	   clickFacebookTab();	   
	  //Click on Twitter Tab
	   clickTwitterTab();	   
	  //Click on Youtube Tab
	   clickYouTubeTab();	   
	  //Click on Instagram Tab
	   clickInstagramTab();	   
	   scrollToTheTopOfPage();
	   softAssert.assertTrue(isfaqAccountTabVisible(), "Account Tab is not visible"); 
	   softAssert.assertTrue(isfaqBillingTabVisible(), "Billing Tab is not visibility");
	   softAssert.assertTrue(isfaqregistrationTabVisible(), "Registration Tab is not visibility");
	   softAssert.assertTrue(isfaqHomeTabVisible(), "Home Tab is not visibility"); 
	   softAssert.assertTrue(isfaqOutageTabVisible(), "Outage Tab is not visibility");
	   softAssert.assertTrue(isfaqServiceTabVisible(), "Service Tab is not visibility");
	   softAssert.assertTrue(isfaqTopTabVisible(), "Top Tab is not visibility");
	   softAssert.assertTrue(isfaqUsageTabVisible(), "Usage Tab is not visibility");
	   softAssert.assertTrue(isfaqWaysToSaveTabVisible(), "Ways To Save Tab is not visibility");	   
	   // Click on Account FAQ
	   clickfaqAccountTab();
	   pause(1000);
	   isFAQPageTopicVisible();
	   String lblaccount = getFAQPageTopic();
       Assert.assertEquals(lblaccount , "Account");
       clickFAQPageHelp();
	   pause(1000);
	   softAssert.assertTrue(isPostLogConnectMePage(PostLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
	     		 (PostLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),"Page Title & URL does not Match");
	  // Click on Blling FAQ
	   clickfaqBillingTab();
	   pause(1000);
	   isFAQPageTopicVisible();
	   String lblBilling = getFAQPageTopic();
       Assert.assertEquals(lblBilling , "Billing");
       clickFAQPageHelp();
	   pause(1000);
	   softAssert.assertTrue(isPostLogConnectMePage(PostLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
	     		 (PostLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),"Page Title & URL does not Match");
	   // Click on Registration FAQ
	   clickfaqregistrationTab();
	   pause(1000);
	   isFAQPageTopicVisible();
	   String lblRegistration = getFAQPageTopic();
       Assert.assertEquals(lblRegistration , "Customer Registration");
       clickFAQPageHelp();
	   pause(1000);
	   softAssert.assertTrue(isPostLogConnectMePage(PostLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
	     		 (PostLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),"Page Title & URL does not Match");
	  // Click on Home FAQ
	   clickfaqHomeTab();
	   pause(1000);
	   isFAQPageTopicVisible();
	   String lblHome = getFAQPageTopic();
       Assert.assertEquals(lblHome , "Home");
       clickFAQPageHelp();
	   pause(1000);
	   softAssert.assertTrue(isPostLogConnectMePage(PostLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
	     		 (PostLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),"Page Title & URL does not Match");
	  // Click on Outage FAQ
	   clickfaqOutageTab();
	   pause(1000);
	   isFAQPageTopicVisible();
	   String lblOutage = getFAQPageTopic();
       Assert.assertEquals(lblOutage , "Outage");
       clickFAQPageHelp();
	   pause(1000);
	   softAssert.assertTrue(isPostLogConnectMePage(PostLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
	     		 (PostLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),"Page Title & URL does not Match");
	  // Click on Outage FAQ
	   clickfaqServiceTab();
	   pause(1000);
	   isFAQPageTopicVisible();
	   String lblService = getFAQPageTopic();
       Assert.assertEquals(lblService , "Service");
       clickFAQPageHelp();
	   pause(1000);
	   softAssert.assertTrue(isPostLogConnectMePage(PostLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
	     		 (PostLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),"Page Title & URL does not Match");
	 // Click on Top FAQ
	   clickfaqTopTab();
	   pause(1000);
	   isFAQPageTopicVisible();
	   String lblTop = getFAQPageTopic();
       Assert.assertEquals(lblTop , "Top FAQs");
       clickFAQPageHelp();
	   pause(1000);
	   softAssert.assertTrue(isPostLogConnectMePage(PostLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
	     		 (PostLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),"Page Title & URL does not Match");
	// Click on Usage FAQ
	   clickfaqUsageTab();
	   pause(1000);
	   isFAQPageTopicVisible();
	   String lblUsage = getFAQPageTopic();
       Assert.assertEquals(lblUsage , "Usage");
       clickFAQPageHelp();
	   pause(1000);
	   softAssert.assertTrue(isPostLogConnectMePage(PostLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
	     		 (PostLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),"Page Title & URL does not Match");
	// Click on ways to save FAQ
	   clickfaqWaysToSaveTab();
	   pause(1000);
	   isFAQPageTopicVisible();
	   String lblWaysToSave = getFAQPageTopic();
       Assert.assertEquals(lblWaysToSave , "Ways To Save");
       clickFAQPageHelp();
	   pause(1000);
	   softAssert.assertTrue(isPostLogConnectMePage(PostLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
	     		 (PostLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),"Page Title & URL does not Match");  
	  }
   
    public void verifyTrackRequestForm() { 
    	clickConnectMeLink();
    	clickTrackRequest();
    	List<WebElement> trackRequestGridHeaders = listTrackReqGridHeader();   
        for (WebElement trackReqGridHeader: trackRequestGridHeaders){
        String actualHeaderName = trackReqGridHeader.getText().trim();
        String expHeaderName = PostLogConnectMeTextProp.getPropValue("trackRequestHeaderName").trim();
   //     assertEquals(actualHeaderName, expHeaderName.toUpperCase(), "Track request grid header not matched");
              
          }    	
    }
    
    public void verifyTrackContactRequests(String referenceID) {
    	
    	pause(2000);
    	populateSearchReqIdSavedForms(referenceID);
    	 List<WebElement> requestIdElements = listTrackReqID();
         for (WebElement requestIdEle: requestIdElements){
             String requestId = requestIdEle.getText().trim();
             assertEquals(requestId, referenceID, "Filtered requests are not matching with the searched request");    	
    }
         // Verifying the date format as per the csp config.
         String expDateFormat = null;
         Locale locale = new Locale("en", "US");
         List<WebElement> reqRaisedOnElements = listRaisedOnDate();
         expDateFormat = getDateFormatAsCspConfigDB();
         for (WebElement reqRaisedOn : reqRaisedOnElements) {
             String actDate = reqRaisedOn.getText().trim().split(" ")[0].trim();
             assertTrue(DateUtil.isValidDateFormat(expDateFormat, actDate, locale), "Date format is not valid as per csp config.");   
    }
  }
    
    public void verifySavedFormObject(SoftAssert softAssert) {   
    	clickSavedForms();
  	 List<WebElement> savedFormsGridHeadersElements = listSavedFormsHeaders();
     int counter = 0;
     for (WebElement gridHeaderEle : savedFormsGridHeadersElements){
         String actualHeader = gridHeaderEle.getText().toUpperCase().trim();                   
      //   String expHeader = expSavedFormsGridHeaders.get(counter).toUpperCase().trim();
         String expHeader =  PostLogConnectMeTextProp.getPropValue("savedFormsGridHeaders").trim().toUpperCase();
         assertEquals(actualHeader, expHeader, "Saved forms grid header not matched.");
         counter++;
     }
     // Verifying sorting functionality.
     List<WebElement> gridHeaderTextElements = listSavedFormsHeaders();
     for (WebElement gridHeaderElement: gridHeaderTextElements){
         String actGridHeaderClass = gridHeaderElement.getAttribute("class").trim();
         if (!actGridHeaderClass.equals("sorting_disabled")) {
             assertTrue(actGridHeaderClass.equals("sorting") || actGridHeaderClass.equals("sorting_asc") || actGridHeaderClass.equals("sorting_desc"));
             if (actGridHeaderClass.equals("sorting")){
                click(gridHeaderElement);
                 pause(500);
                 actGridHeaderClass = gridHeaderElement.getAttribute("class").trim();
                 assertTrue(actGridHeaderClass.equals("sorting_asc") || actGridHeaderClass.equals("sorting_desc"));
             }
             if (actGridHeaderClass.equals("sorting_desc")){
                 click(gridHeaderElement);
                 pause(200);
                 actGridHeaderClass = gridHeaderElement.getAttribute("class").trim();
                 assertTrue(actGridHeaderClass.equals("sorting_asc"));
                 click(gridHeaderElement);
                 pause(200);
                 actGridHeaderClass = gridHeaderElement.getAttribute("class").trim();
                 assertTrue(actGridHeaderClass.equals("sorting_desc"));
             } else if (actGridHeaderClass.equals("sorting_asc")) {
                 click(gridHeaderElement);
                 pause(200);
                 actGridHeaderClass = gridHeaderElement.getAttribute("class").trim();
                 assertTrue(actGridHeaderClass.equals("sorting_desc"));
                 click(gridHeaderElement);
                 pause(200);
                 actGridHeaderClass = gridHeaderElement.getAttribute("class").trim();
                 assertTrue(actGridHeaderClass.equals("sorting_asc"));
             } else {
                 assertTrue(false, "Sorting is not working on column : " + gridHeaderElement.getText().trim());
             }
         }
     }
    	
    }
    
    /**
     * This method is used to get the date format as per csp config using database.
     */
    public String getDateFormatAsCspConfigDB() {
    	String dateFormatAsCspConfig = null;
    	ResultSet dateFormatRS;
        String expFormat = null;
        dateFormatAsCspConfig = SQLQueries.getDateFormatAsCspConfig();
        try {
            dateFormatRS = DataBaseUtils.getResultSet(dateFormatAsCspConfig);
            while (dateFormatRS.next()) {
                expFormat = dateFormatRS.getString("FormatName");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return expFormat;
    } 
    
    public void verifyCreateBillingRequestWithAttachment(SoftAssert softAssert) { 
 	   User user = SCPConfiguration.user;
 	   selectlstConnectMeOptions("Billing");	   
 	   if(selectlstConnectMeOptions("Billing")) {		   		   
 	   }
 	   if(isServiceAccNoTxtVisible()) {
 		 //  String accountNo = getServiceAccNoLabel();
 		  String accountNo = gettxtAccountNo();
 		 Assert.assertEquals(user.getDefaultUtilityAccNum(), accountNo);
 	   }
 	   if(isCustomerNameTxtVisible()) {
 			 Assert.assertEquals(user.getCustomerNum(), getTextCustomerName());
 	   }
 	   if(isEmailAddressTxtVisible()) {
 		//	 Assert.assertEquals(user.getEmailId(), getEmailAddressLabel());
 	   }
 	   if(isChooseFileBtnVisible()) {
 		   String validAttachmentFileName = "meter-reading.jpg";
 	        addAttachmentToChooseFile(FILE_UPLOAD_PATH + validAttachmentFileName);
 	   }
 	   if(istxtSubjectTxtVisible()) {
 		   populateSubject("Testing");
 	   }
 	   if(isCommentsTxtVisible()) {
 		   populateComments("Testing");
 	   }
 	   btnClickNext();
 	  
    }
    
    /**
     * This method is to fetch the username from username assistance mail.
     *
     * @param sEmailAddress
     * @return
     */
    
    public String fetchRequestIDFromEmail(String sEmailAddress) {
        String requestID = null;
        User user = SCPConfiguration.user;	
        // Exporting the reset Username link from the database.
        try {
            String sSubjectLine = PostLogConnectMeTextProp.getPropValue("expectedForgotUsernameEmailSubject");
            String sResetPasswordEmailMsgQuery = SQLQueries.getUsernameAssistanceEmailMsg(sSubjectLine,
                    sEmailAddress);
            ResultSet resultSet = DataBaseUtils.getResultSet(sResetPasswordEmailMsgQuery);
            resultSet.next();
           
            String sUsernameAssistanceMsg = resultSet.getString("Message");
            System.out.println(sUsernameAssistanceMsg);
            String sIsEmailSent = resultSet.getString("IsNotify");
            
            // Check email is linked with single user or multiple users
            String query = "select * from [User] where EmailID = '"+sEmailAddress+"'";
            ResultSet resultSet1 = DataBaseUtils.getResultSet(query);
            int count = 0;
            while(resultSet1.next()){
            	count = count+1;
            	if(count >1){
            		break;
            	}
            }                                    
            	String sStartingString = "Your request ID is: ";
                int length = sStartingString.length();
                String sEndingString = "</p>\r\n" + 
                		"<p><br>\r\n" + 
                		"</p>\r\n" + 
                		"<p><a href='https:";

                requestID = sUsernameAssistanceMsg.substring(sUsernameAssistanceMsg.indexOf(sStartingString) + length,
                        sUsernameAssistanceMsg.indexOf(sEndingString));
                requestID = requestID.replace("</p>", "").replace("\n", "");
                
            System.out.println("Request ID---==========>" + requestID);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return requestID;
    }
    
    public void verifyCreateRequestsWithAttachments(SoftAssert softAssert) { 
  	   User user = SCPConfiguration.user;
  	   selectlstConnectMeOptions("Billing");	   
  	   if(selectlstConnectMeOptions("Billing")) {		   		   
  	   }
  	   if(isServiceAccNoTxtVisible()) {
  		 //  String accountNo = getServiceAccNoLabel();
  		  String accountNo = gettxtAccountNo();
  		 Assert.assertEquals(user.getDefaultUtilityAccNum(), accountNo);
  	   }
  	   if(isCustomerNameTxtVisible()) {
  			 Assert.assertEquals(user.getCustomerNum(), getTextCustomerName());
  	   }
  	   if(isEmailAddressTxtVisible()) {
  		//	 Assert.assertEquals(user.getEmailId(), getEmailAddressLabel());   
  	   }
  	   if(istxtSubjectTxtVisible()) {
  		   populateSubject("Testing");
  	   }
  	   if(isCommentsTxtVisible()) {
  		   populateComments("Testing");
  	   }
  	   if(isChooseFileBtnVisible()) {
  		   // Upload ivalid File
  		   String validAttachmentFileName = "meter-reading.jpg" + "\n"+ "meter-reading.jpg"+ "\n"+ "meter-reading.jpg"+ "\n"+ "meter-reading.jpg"
  				                             + "\n"+ "meter-reading.jpg" + "\n"+ "meter-reading.jpg";
  	    addAttachmentToChooseFile(FILE_UPLOAD_PATH + validAttachmentFileName);
  	    btnClickNext();
  	    String uploadMorethenFiveFiles = getToastMessage();
     	Assert.assertEquals(uploadMorethenFiveFiles , PostLogConnectMeTextProp.getPropValue("invalidFileType"));
     	waitForToastMessageInvisibility();
  	   }
     }
}


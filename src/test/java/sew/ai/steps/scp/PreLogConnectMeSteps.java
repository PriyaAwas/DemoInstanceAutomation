package sew.ai.steps.scp;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static sew.ai.helpers.props.FilePaths.FILE_UPLOAD_PATH;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import sew.ai.config.SCPConfiguration;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.models.User;
import sew.ai.pageObjects.scp.PostLogConnectMePage;
import sew.ai.pageObjects.scp.PreLogConnectMePage;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.DateUtil;
import sew.ai.utils.PropertiesUtil;

public class PreLogConnectMeSteps extends PreLogConnectMePage {
    private static final Logger log = LogManager.getLogger(ProblemSigningInSteps.class);

    public static PropertiesUtil PreLogConnectMeTextProp;

    public PreLogConnectMeSteps(WebDriver driver) {
        super(driver);       
        PreLogConnectMeTextProp = new PropertiesUtil(
                FilePaths.SCP_TEXT_PROPERTIES + Constants.PRE_LOG_CONNECT_ME_TXT_FILENAME
                );
               
  }
    
    public void verifyPreLogConnectMeObject(SoftAssert softAssert) {   
        clickConnectMeLink();
        pause(1000);
        selectlstConnectMeOptions("Rebates");	   
  	   if(selectlstConnectMeOptions("Rebates")) {		   		   
  	   }        
        softAssert.assertTrue(isPreLogConnectMePage(PreLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
         		 (PreLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),"Page Title & URL does not Match");    
        softAssert.assertTrue(isPageHeaderPostVisible(), "Contact Us Page Header is not visible"); 
        softAssert.assertTrue(isSocialMediaVisible(), "Social Media Tab is not visibility");
        softAssert.assertTrue(isContactusVisible(), "Contact Us Tab is not visibility"); 
        softAssert.assertTrue(isTrackRequestVisible(), "Track Request Tab is not visibility");
        softAssert.assertTrue(isSavedFormVisible(), "Saved Form tab is not visibility");
        softAssert.assertTrue(isSubmitBtnVisible(), "Submit button is not visibility"); 
        softAssert.assertTrue(isNextBtnVisible(), "Next button is not visibility");
        softAssert.assertTrue(isCustomerNameTxtVisible(), "Customer Name text Box is not visibility");
        softAssert.assertTrue(isServiceAccNoTxtVisible(), "Service Acc No text Box is not visibility");
        softAssert.assertTrue(isEmailAddressTxtVisible(), "Email Address text Box is not visibility");
        softAssert.assertTrue(istxtSubjectTxtVisible(), "Subject button is text Box visibility");
        softAssert.assertTrue(isCommentsTxtVisible(), "Comments text Box is not visibility");
        softAssert.assertTrue(isChooseFileBtnVisible(), "Choose File text Box is not visibility");
    	softAssert.assertTrue(isCustomerlblVisible(), "Instagram Tab is not visibility");    	
    	List<String> list = getdropdownOptions();  	
      }
    
    public boolean isPreLogConnectMePage(String url, String title) {
        Boolean isForgetPasswordPage = false;
        log.info("Checking that the current page is ForgetPassword Page");
        if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
        	isForgetPasswordPage = true;
        log.info("The current page is ForgetPassword Page {}: " + isForgetPasswordPage);
        return isForgetPasswordPage;
    }
    
    public void verifyPreConnectMeSubmitblankForm() {
    User user = SCPConfiguration.user;
 	   btnClickNext();
 	   String SuccessToasterMsg = getToastMessage();
        Assert.assertEquals(SuccessToasterMsg , PreLogConnectMeTextProp.getPropValue("toasterMsgBlankForm"));
        populateSubject("Testing");
        populateServiceAccNo(user.getDefaultUtilityAccNum());
        populateCustomerName(user.getFullName());
        populateEmailAddress(user.getEmailId());       
        btnClickNext();
        String errMsgComment = getlblGenericErrorMessage();
        Assert.assertEquals(errMsgComment , PreLogConnectMeTextProp.getPropValue("txtCommentBoxErrorMsg"));
        clearSubjectField();
        populateComments("Testing");
        btnClickNext();
        String errMsgSubject = getlblGenericErrorMessage();
        Assert.assertEquals(errMsgSubject , PreLogConnectMeTextProp.getPropValue("txtSubjectBoxErrorMsg"));
        populateSubject("Testing");
        btnClickNext();
    }
    
    public void verifyCreatePreContactRequest(SoftAssert softAssert) { 
 	   User user = SCPConfiguration.user;
 	   selectlstConnectMeOptions("Billing");	   
 	   if(selectlstConnectMeOptions("Billing")) {		   		   
 	   }
 	   if(isServiceAccNoTxtVisible()) {
 		  populateServiceAccNo(user.getDefaultUtilityAccNum());
 		  pause(500);
 	 	   }
 	 	   if(isCustomerNameTxtVisible()) {
 	 		 populateCustomerName(user.getFullName());
 	 		pause(500);
 	 	   }
 	 	   if(isEmailAddressTxtVisible()) {
 	 		 populateEmailAddress(user.getEmailId());
 	 		pause(500);
 	 	   }
 	 	   if(isChooseFileBtnVisible()) {
 	 		   String validAttachmentFileName = "meter-reading.jpg";
 	 	        addAttachmentToChooseFile(FILE_UPLOAD_PATH + validAttachmentFileName);
 	 	      pause(500);
 	 	   }
 	       if(istxtSubjectTxtVisible()) {
 		   populateSubject("Testing");
 		  pause(500);
 	       }
 	       if(isCommentsTxtVisible()) {
 		   populateComments("Testing");
 		  pause(500);
 	   }
 	   btnClickNext();
    }
     
   public void verifyPreConnectMePreviewYourFormDetails(SoftAssert softAssert) {      
       log.info("Test Case execution for - verifyPreviewYourFormDetails - is Initiated");
       User user = SCPConfiguration.user;
       String validAttachmentFileName = "meter-reading.jpg";
       List<WebElement> previewYourFormColumn = listPreviewYourFormColumn();
       List<WebElement> previewYourFormValue = listPreviewYourFormValue();
       int counter = 0;
       for (WebElement columnLabelEle : previewYourFormColumn) {
           String key = columnLabelEle.getText().trim();
           String value = previewYourFormValue.get(counter).getText().trim();
           switch (key) {
               case "Topic":
                   softAssert.assertEquals(value, "Billing", "Topic value not matched on preview your form.");
                   pause(1000);
                   break;
               case "Customer Name":
                   assertEquals(value, user.getFullName(), "Customer name not matched on preview your form.");
                   pause(1000);
                   break;
               case "Email Address":
                   softAssert.assertEquals(value, user.getEmailId(), "Email address not matched on preview your form");
                   pause(1000);
                   break;
               case "Subject":
                   softAssert.assertEquals(value, "Testing" , "Subject not matched on preview your form");
                   pause(1000);
                   break;
               case "Attachment":
                   softAssert.assertEquals(value, validAttachmentFileName, "Attachment name is not matched on preview your form");
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
   
   public String verifyPreConnectMeSubmitForm(SoftAssert softAssert) {	   
	   isSubmitBtnVisible();
	   btnClickSubmit();
	   isLblPopupThankYouVisible();
	   String popupContent = getlblPopupThankYou().trim();
       String referenceId = popupContent.substring(popupContent.indexOf(":") + 1).trim();	  
	   clickContactUsPopupOk();
	   pause(5000);
	   return referenceId;
   }
   
   public void verifyPreConnectMeSocialMediaObject(SoftAssert softAssert) {   
	    clickConnectMeLink();
	    softAssert.assertTrue(isPreLogConnectMePage(PreLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
	     		 (PreLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),"Page Title & URL does not Match");    
	    softAssert.assertTrue(isPageHeaderPostVisible(), "Contact Us Page Header is not visible"); 
	   clickSocialMedia();
	   softAssert.assertTrue(isFacebookTabVisible(), "Facebook Tab is not visible"); 
	   softAssert.assertTrue(isYouTubeTabVisible(), "YouTube Tab is not visibility");
	   softAssert.assertTrue(isTwitterTabVisible(), "Twitter Tab is not visibility");
	   softAssert.assertTrue(isInstagramTabVisible(), "Instagram Tab is not visibility"); 
	   softAssert.assertTrue(isCustomerlblVisible(), "Customer lbl Tab is not visibility");	  
	  //Click on Facebook Tab
	   clickFacebookTab();	   
	  //Click on Twitter Tab
	   clickTwitterTab();	   
	  //Click on Youtube Tab
	   clickYouTubeTab();	   
	  //Click on Instagram Tab
	   clickInstagramTab();
   }
   
        public void verifyPreConnectMeFAQObject(SoftAssert softAssert) {   
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
	   softAssert.assertTrue(isPreLogConnectMePage(PreLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
	     		 (PreLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),"Page Title & URL does not Match");
	  // Click on Blling FAQ
	   clickfaqBillingTab();
	   pause(1000);
	   isFAQPageTopicVisible();
	   String lblBilling = getFAQPageTopic();
      Assert.assertEquals(lblBilling , "Billing");
      clickFAQPageHelp();
	   pause(1000);
	   softAssert.assertTrue(isPreLogConnectMePage(PreLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
	     		 (PreLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),"Page Title & URL does not Match");
	   // Click on Registration FAQ
	   clickfaqregistrationTab();
	   pause(1000);
	   isFAQPageTopicVisible();
	   String lblRegistration = getFAQPageTopic();
      Assert.assertEquals(lblRegistration , "Customer Registration");
      clickFAQPageHelp();
	   pause(1000);
	   softAssert.assertTrue(isPreLogConnectMePage(PreLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
	     		 (PreLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),"Page Title & URL does not Match");
	  // Click on Home FAQ
	   clickfaqHomeTab();
	   pause(1000);
	   isFAQPageTopicVisible();
	   String lblHome = getFAQPageTopic();
      Assert.assertEquals(lblHome , "Home");
      clickFAQPageHelp();
	   pause(1000);
	   softAssert.assertTrue(isPreLogConnectMePage(PreLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
	     		 (PreLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),"Page Title & URL does not Match");
	  // Click on Outage FAQ
	   clickfaqOutageTab();
	   pause(1000);
	   isFAQPageTopicVisible();
	   String lblOutage = getFAQPageTopic();
      Assert.assertEquals(lblOutage , "Outage");
      clickFAQPageHelp();
	   pause(1000);
	   softAssert.assertTrue(isPreLogConnectMePage(PreLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
	     		 (PreLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),"Page Title & URL does not Match");
	  // Click on Outage FAQ
	   clickfaqServiceTab();
	   pause(1000);
	   isFAQPageTopicVisible();
	   String lblService = getFAQPageTopic();
      Assert.assertEquals(lblService , "Service");
      clickFAQPageHelp();
	   pause(1000);
	   softAssert.assertTrue(isPreLogConnectMePage(PreLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
	     		 (PreLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),"Page Title & URL does not Match");
	 // Click on Top FAQ
	   clickfaqTopTab();
	   pause(1000);
	   isFAQPageTopicVisible();
	   String lblTop = getFAQPageTopic();
      Assert.assertEquals(lblTop , "Top FAQs");
      clickFAQPageHelp();
	   pause(1000);
	   softAssert.assertTrue(isPreLogConnectMePage(PreLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
	     		 (PreLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),"Page Title & URL does not Match");
	// Click on Usage FAQ
	   clickfaqUsageTab();
	   pause(1000);
	   isFAQPageTopicVisible();
	   String lblUsage = getFAQPageTopic();
      Assert.assertEquals(lblUsage , "Usage");
      clickFAQPageHelp();
	   pause(1000);
	   softAssert.assertTrue(isPreLogConnectMePage(PreLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
	     		 (PreLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),"Page Title & URL does not Match");
	// Click on ways to save FAQ
	   clickfaqWaysToSaveTab();
	   pause(1000);
	   isFAQPageTopicVisible();
	   String lblWaysToSave = getFAQPageTopic();
      Assert.assertEquals(lblWaysToSave , "Ways To Save");
      clickFAQPageHelp();
	   pause(1000);
	   softAssert.assertTrue(isPreLogConnectMePage(PreLogConnectMeTextProp.getPropValue("ConnectMePageUrl"),
	     		 (PreLogConnectMeTextProp.getPropValue("ConnectMePageTitle"))),"Page Title & URL does not Match");  
	  }
   
   public void verifyPreTrackRequestForm() {         
   	clickTrackRequest();
   	pause(1000);
   	isBtnTrackReqVisible();
   	clickBtnTrackReq();
    String blankTrackReqIdMsg = getToastMessage();
    Assert.assertEquals(blankTrackReqIdMsg , PreLogConnectMeTextProp.getPropValue("blankTrackRequest"));
    waitForToastMessageInvisibility();
    pause(1000);
    populatetxtTrackReqSearch("1234567");
    clickBtnTrackReq();
    String invalidTrackReqIdMsg = getToastMessage();
    Assert.assertEquals(invalidTrackReqIdMsg , PreLogConnectMeTextProp.getPropValue("invalidTrackRequest"));
    waitForToastMessageInvisibility();
    pause(1000);
           
         } 
         
       public void verifyPreTrackRequestForm(String referenceID) {
    	   ResultSet resultSet;
    	   ArrayList OtpList = new ArrayList<>();
	       pause(1000);
	       populatetxtTrackReqSearch(referenceID);
	       clickBtnTrackReq();
	       pause(1000);
	       isbtnSubmitTrackRequestOTPVisible();
	       isbtnCancelTrackRequestOTPVisible();
	       // Click on Cancel button
	       clickbtnCancelTrackRequestOTP();
	       populatetxtTrackReqSearch(referenceID);
	       clickBtnTrackReq();
	       for(int maxotp=0;maxotp<5;maxotp++) {
	       pause(5000);
	       populateRequestIdBoxOTP("000000");
	       clickbtnSubmitTrackRequestOTP();
	       pause(5000);
	       if(isTrackLinkVisible()) {
	    	   Assert.assertEquals(getlblAuthHeaderMsg(), "Authentication Failed");
	    	   Assert.assertEquals( getlblAuthmsg(), "You have exceeded the maximum number of verification attempts.");
	    	   break;
	       } 
   }
	       clickTrackLink();
	       populatetxtTrackReqSearch(referenceID);
	       clickBtnTrackReq();
	       populateRequestIdBoxOTP("123456");
	       clickbtnSubmitTrackRequestOTP();	       
	       String invalidOTP = getToastMessage();
	       Assert.assertEquals(invalidOTP , PreLogConnectMeTextProp.getPropValue("enterInvalidOTP"));
	       waitForToastMessageInvisibility();
	       pause(5000);
	       
	       while(!getTxtOtpTimer().equals("0")) {
//	    	   String value = getResendOtpTrackRequestClass();
//	    	   assertEquals(value, "isDisabled");
	       }
	       try {
				resultSet = DataBaseUtils.getResultSet(getPreLoginConnectmeTrackRequestOtp());
				while(resultSet.next())
				{
					OtpList.add(resultSet.getString("otp"));									
				}	
			} catch (Exception e) {
				e.printStackTrace();
			}
	       
	       populateRequestIdBoxOTP(OtpList.get(0).toString());
	       clickbtnSubmitTrackRequestOTP();
	       pause(1000);
	       // Verify Track Request side bar labels
	       isTrackReqSidebarHeaderLblVisible();
	       String actualSidebarHeading = getTrackReqSidebarHeaderLbl();
	       Assert.assertEquals(actualSidebarHeading, "Track Request");
	      // Verify Request ID labels	       
	       String actualRequestIdLabel = getTrackReqSidebarRequestId();
	       Assert.assertEquals(actualRequestIdLabel, "CASE NUMBER");
	      // Verify Request ID value	       
	       String actualRequestId = getTrackReqSidebarRequestId();
	       Assert.assertEquals(actualRequestId, referenceID);
	      // Verifying date format as per csp config.
	        String expDateFormat = null;
	        expDateFormat = getDateFormatAsCspConfigDB();
	        String actDate = getTrackRequestDate().trim();
	        String actDateFormat = actDate.split(" ")[0].trim();
	        Locale locale = new Locale("en", "US");
	    //   assertTrue(DateUtil.isValidDateFormat(expDateFormat, actDateFormat, locale), "Date format is not valid as per the csp config.");	       
     }
       
       public void verifyPreSavedFormObject(SoftAssert softAssert) {          
        clickSavedForms();
     	 List<WebElement> savedFormsGridHeadersElements = listSavedFormsHeaders();
        int counter = 0;
        for (WebElement gridHeaderEle : savedFormsGridHeadersElements){
            String actualHeader = gridHeaderEle.getText().toUpperCase().trim();                   
         //   String expHeader = expSavedFormsGridHeaders.get(counter).toUpperCase().trim();
            String expHeader =  PreLogConnectMeTextProp.getPropValue("savedFormsGridHeaders").trim().toUpperCase();
            assertEquals(actualHeader, expHeader, "Saved forms grid header not matched.");
            counter++;
        }
       }
      
       public static String getPreLoginConnectmeTrackRequestOtp(){
   		//String regDataQuery = "select module_name FROM MODULE WHERE STATUS = 1";
   		String regDataQuery = "Select top 1 * from Otp_request Order by created_date DESC";
   		return regDataQuery;
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
}

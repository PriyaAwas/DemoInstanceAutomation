package demo.steps.scp;

import static org.testng.Assert.assertEquals;
import static sew.ai.steps.scp.DashboardSteps.dashboardTextProp;

import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import demo.pageobjects.PreLoginPage;
import sew.ai.config.Configuration;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.steps.scp.DashboardSteps;
import sew.ai.steps.scp.SignOutSteps;
import sew.ai.utils.PropertiesUtil;

public class PreLoginSteps extends PreLoginPage {

	private static final Logger log = LogManager.getLogger(PreLoginSteps.class);
	public static PropertiesUtil loginTextProp;
	public static PropertiesUtil preLoginConnectMeProp;

	public PreLoginSteps(WebDriver driver) {
		super(driver);
		loginTextProp = new PropertiesUtil(
				FilePaths.SCP_TEXT_PROPERTIES + Constants.LOGIN_TXT_FILENAME);
		preLoginConnectMeProp = new PropertiesUtil(
				FilePaths.SCP_TEXT_PROPERTIES + Constants.PRE_LOG_CONNECT_ME_TXT_FILENAME);
	}
	
	public void populateLoginForm(String userName, String password) {
        populateUserName(userName);
        populatePassword(password);
    }
	
	public String loginWithBlankCreds() {
        clickSignInBtn();
        String errMsg = getToastMessageWithoutWait();
        clickToastCloseBtn();
        return errMsg;
    }
	
	public String loginWithInvalidCreds(String userName, String password) {
		populateLoginForm(userName, password);
		clickSignInBtn();
		String errMsg = getToastMessage();
		return errMsg;
	}
	
	public String loginWithBlankUsername(String password) {
        clearUsernameField();
        populatePassword(password);
        clickSignInBtn();
        String errMsg = getCommonValidationMsg();
        return errMsg;
    }

    public String loginWithWrongUsername(String userName, String password) {
        populateLoginForm(userName, password);
        clearUsernameField();
        populateUserName("invalid@username.com");
        clickSignInBtn();
        String errMsg = getToastMessage();
        return errMsg;
    }

    public String loginWithWrongPassword(String userName, String password) {
        populateLoginForm(userName, password);
        clearPasswordField();
        populatePassword("Invalid@Pass123");
        clickSignInBtn();
        String errMsg = getToastMessage();
        return errMsg;
    }

    public String loginWithInvalidCreds() {
        populateUserName("Invalid@Username");
        populatePassword("Invalid@Password");
        clickSignInBtn();
        String errMsg = getToastMessage();
        return errMsg;
    }

    public String loginWithBlankPassword(String userName) {
        populateUserName(userName);
        clearPasswordField();
        clickSignInBtn();
        String errMsg = getCommonValidationMsg();
        return errMsg;
    }

    public String loginWithDeactiveUser(String userName, String password) {
        populateLoginForm(userName, password);
        clickSignInBtn();
        String errMsg = getToastMessage();
        return errMsg;
    }
	
	public DashboardSteps loginIntoTheApplication(String userName, String password) {
        ExtentLogger.logInfo("Logging into the application.");
        DashboardSteps dashboardSteps;
        waitForPageToLoad();
        populateLoginForm(userName, password);
        clickSignInBtn();
        waitForPageLoader();
        dashboardSteps = new DashboardSteps(driver);
        ExtentLogger.logInfo("Dashboard page url : " + dashboardTextProp.getPropValue("dashboardPageUrl"));
        ExtentLogger.logInfo("Dashboard page title : " + dashboardTextProp.getPropValue("dashboardPageHeader"));
        Assert.assertTrue(dashboardSteps.isDashboardPage(
                        dashboardTextProp.getPropValue("dashboardPageUrl"),
                        dashboardTextProp.getPropValue("dashboardPageHeader")),
                "Not navigated to dashboard page."
        );
        ExtentLogger.logPass("Logged into the application successfully.");
        return dashboardSteps;
    }
	
	public void loginIntoTheApplicationWrongCreds(SoftAssert softAssert) {
        // Init user model
        String user = Configuration.toString("userName");
        String password = Configuration.toString("password");
        // Verify login with blank creds
        softAssert.assertEquals(loginWithBlankCreds(), 
        		loginTextProp.getPropValue("loginWithBlankCredsErrMsg"),
                "Login with Blank creds error message not matched.");
        // Verify login with blank password
        softAssert.assertEquals(loginWithBlankPassword(user), 
        		loginTextProp.getPropValue("loginWithBlankPasswordErrMsg"),
                "Blank username field validation not correct.");
        // Verify login with blank username
        softAssert.assertEquals(loginWithBlankUsername(password), 
        		loginTextProp.getPropValue("loginWithBlankUsernameErrMsg"),
                "Blank username field validation not correct."
        );
        // Verify login with wrong username
        softAssert.assertEquals(loginWithWrongUsername(user, password),
                loginTextProp.getPropValue("invalidCredentialsErrMsg"),
                "Wrong toast when login using the wrong username.");
        // Verify login with wrong password
        softAssert.assertEquals(loginWithWrongPassword(password, user),
                loginTextProp.getPropValue("invalidCredentialsErrMsg"),
                "Wrong toast when login using the wrong password.");
        // Verify login with wrong username and password.
        softAssert.assertEquals(loginWithInvalidCreds(), 
        		loginTextProp.getPropValue("invalidCredentialsErrMsg"),
                "Wrong toast when login using invalid creds.");
    }
	
	public void verifyTheLoginPageObject(SoftAssert softAssert) {
        Assert.assertTrue(isLoginPage(loginTextProp.getPropValue("loginPageUrl"), loginTextProp
                .getPropValue("loginPageTitle")));
        softAssert.assertTrue(isCompanyLogoVisible(), "Company logo is not present.");
        softAssert.assertTrue(isLanguageDropdownVisible(), "Language dropdown option is not visible.");
        softAssert.assertEquals(getLanguageDropdownSelectedOption(), loginTextProp.getPropValue("optionInLanguageDropdown"),
                "Option selected in the language drop-down not matched.");
        softAssert.assertEquals(getLoginPageHeader(), loginTextProp.getPropValue("loginPageHeader"),
                "Login page header not matched.");
        softAssert.assertTrue(isUsernameTxtVisible(), "Username text field is not visible.");
        softAssert.assertEquals(getUsernameLabel(), loginTextProp.getPropValue("lblUsername"),
                "Label for username is not matched.");
        softAssert.assertTrue(isPasswordTxtVisible(), "Password text field is not visible.");
        softAssert.assertEquals(getPasswordLabel(), loginTextProp.getPropValue("lblPassword"),
                "Label for password is not matched.");
        softAssert.assertTrue(isRememberMeChbVisible(), "Remember me check box is not visible.");
        softAssert.assertEquals(getSignInBtnLabel(), loginTextProp.getPropValue("lblSignInBtn"),
                "Label for sign in button is not matched.");
        softAssert.assertEquals(getForgotUsernameLabel(), loginTextProp.getPropValue("lblForgotUsername"),
                "Label for forgot username is not matched.");
        softAssert.assertEquals(getForgotPasswordLabel(), loginTextProp.getPropValue("lblForgotPassword"),
                "Label for forgot password is not matched.");
        softAssert.assertEquals(getProblemSigningInLabel(), loginTextProp.getPropValue("lblProblemSignIn"),
                "Label for problem sign in is not matched.");
        softAssert.assertEquals(getRegisterLinkLabel(), loginTextProp.getPropValue("lblRegisterLnk"),
                "Label for register link is not matched.");
        softAssert.assertEquals(getAdvancedServicesLinkLabel(), loginTextProp.getPropValue("lnkAdvancedServices"),
                "Label for advanced service link is not matched.");
        softAssert.assertEquals(getPayBillLinkLabel(), loginTextProp.getPropValue("lnkPayBill"),
                "Label for pay bill link is not matched.");
        softAssert.assertEquals(getOutagesLinkLabel(), loginTextProp.getPropValue("lnkOutages"),
                "Label for outages link is not matched.");
        softAssert.assertEquals(getWaysToSaveLinkLabel(), loginTextProp.getPropValue("lnkWaysToSave"),
                "Label for ways to save link is not matched.");
        softAssert.assertEquals(getPaymentLocationsLinkLabel(), loginTextProp.getPropValue("lnkPaymentLocations"),
                "Label for payment locations link is not matched.");
        softAssert.assertEquals(getContactUsLinkLabel(), loginTextProp.getPropValue("lnkContactUs"),
                "Label for contact us link is not matched.");
        softAssert.assertEquals(getReportLeaksLinkLabel(), loginTextProp.getPropValue("lnkReportLeaks"),
                "Label for report leaks link is not matched.");
        softAssert.assertEquals(getFaqsLinkLabel(), loginTextProp.getPropValue("lnkFaqs"),
                "Label for ways to faqs link is not matched.");
        softAssert.assertEquals(getFooterContactUsLinkLabel(), loginTextProp.getPropValue("lnkFooterContactUs"),
                "Label for footer contact us link is not matched.");
        softAssert.assertEquals(getTermsAndConditionsLinkLabel(), loginTextProp.getPropValue("lnkTermsAndConditions"),
                "Label for terms and conditions link is not matched.");
        softAssert.assertEquals(getPrivacyPolicyLinkLabel(), loginTextProp.getPropValue("lnkPrivacyPolicy"),
                "Label for privacy policy link is not matched.");
    }
	
	public DashboardSteps loginIntoTheAppByCheckingRememberMe(String userName, String password) {
        DashboardSteps dashboardSteps;
        populateLoginForm(userName, password);
        checkRememberMe();
        clickSignInBtn();
        waitForPageLoader();
        dashboardSteps = new DashboardSteps(driver);
        Assert.assertTrue(dashboardSteps.isDashboardPage(dashboardTextProp.getPropValue("dashboardPageUrl"),
                dashboardTextProp.getPropValue("dashboardPageHeader")));
        dashboardSteps.waitForImageProfileIcon();
        return dashboardSteps;
    }
	
	public Boolean verifyUsernameAndRememberMeCheckboxStatus(DashboardSteps dashboardSteps,
            String userName) {
		SignOutSteps signOutSteps;
		dashboardSteps.clickImageProfileIco();
		dashboardSteps.bypassTheAboutMyHomePage();
		dashboardSteps.clickSignOutLnk();
		signOutSteps = new SignOutSteps(driver);
		signOutSteps.waitForSignOutSuccessLbl();
		signOutSteps.clickSignInAgainBtn();
		waitForUserNameFieldVisibility();
		Assert.assertEquals(getPopulatedUsernameValue(), userName,
		"Username is not auto populating on checking remember me.");
		return isRememberMeChbChecked();
	}
	
	
	public boolean isPreLogConnectMePage(String url, String title) {
        Boolean isForgetPasswordPage = false;
        log.info("Checking that the current page is ForgetPassword Page");
        if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
        	isForgetPasswordPage = true;
        log.info("The current page is ForgetPassword Page {}: " + isForgetPasswordPage);
        return isForgetPasswordPage;
    }
	
	
	public void verifyPreLogConnectMeObject(SoftAssert softAssert) {   
		clickContactUsLnk();
        pause(1000);
        selectlstConnectMeOptions("Rebates");	   
  	   if(selectlstConnectMeOptions("Rebates")) {		   		   
  	   }        
        softAssert.assertTrue(isPreLogConnectMePage(preLoginConnectMeProp.getPropValue("ConnectMePageUrl"),
         		 (preLoginConnectMeProp.getPropValue("ConnectMePageTitle"))),"Page Title & URL does not Match");    
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
    	@SuppressWarnings("unused")
		List<String> list = getdropdownOptions();
      }
	
	public void verifyPreLogConnectMeObjectForWaterLeaks(SoftAssert softAssert) {   
		clickWaterLeakLnk();
        pause(1000);
        selectlstConnectMeOptions("Rebates");	   
  	   if(selectlstConnectMeOptions("Rebates")) {		   		   
  	   }        
//        softAssert.assertTrue(isPreLogConnectMePage(preLoginConnectMeProp.getPropValue("ConnectMePageUrl"),
//         		 (preLoginConnectMeProp.getPropValue("ConnectMePageTitle"))),"Page Title & URL does not Match");    
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
    	@SuppressWarnings("unused")
		List<String> list = getdropdownOptions();
      }
	
	public void verifyCreatePreContactRequest(SoftAssert softAssert) {
	      String AccNum = Configuration.toString("utilityAccountNumber");
	      String user = Configuration.toString("userName");
	 	   selectlstConnectMeOptions("Billing");	
	 	  waitForPageToLoad();
	 	   if(selectlstConnectMeOptions("Billing")) {		   		   
	 	   }
	 	   if(isServiceAccNoTxtVisible()) {
	 		  populateServiceAccNo(AccNum);
	 		  pause(500);
	 	 	   }
	 	 	   if(isCustomerNameTxtVisible()) {
	 	 		 populateCustomerName(user);
	 	 		pause(500);
	 	 	   }
	 	 	   if(isEmailAddressTxtVisible()) {
	 	 		 populateEmailAddress("test@test.com");
	 	 		pause(500);
	 	 	   }
//	 	 	   if(isChooseFileBtnVisible()) {
//	 	 		   String validAttachmentFileName = "meter-reading.jpg";
//	 	 	        addAttachmentToChooseFile(FILE_UPLOAD_PATH + validAttachmentFileName);
//	 	 	      pause(500);
//	 	 	   }
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
	
	public void verifyCreatePreContactRequestForWaterLeaks(SoftAssert softAssert) {
		clickWaterLeakLnk();
	      String AccNum = Configuration.toString("utilityAccountNumber");
	      String user = Configuration.toString("userName");
	 	   selectlstConnectMeOptions("Report Water Waste");
	 	  waitForPageToLoad();
	 	   if(selectlstConnectMeOptions("Report Water Waste")) {		   		   
	 	   }
	 	   if(isServiceAccNoTxtVisible()) {
	 		  populateServiceAccNo(AccNum);
	 		  pause(500);
	 	 	   }
	 	 	   if(isCustomerNameTxtVisible()) {
	 	 		 populateCustomerName(user);
	 	 		pause(500);
	 	 	   }
	 	 	   if(isEmailAddressTxtVisible()) {
	 	 		 populateEmailAddress("test@test.com");
	 	 		pause(500);
	 	 	   }
//	 	 	   if(isChooseFileBtnVisible()) {
//	 	 		   String validAttachmentFileName = "meter-reading.jpg";
//	 	 	        addAttachmentToChooseFile(FILE_UPLOAD_PATH + validAttachmentFileName);
//	 	 	      pause(500);
//	 	 	   }
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
		   String user = Configuration.toString("userName");
	       //String validAttachmentFileName = "meter-reading.jpg";
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
	                   assertEquals(value, user, "Customer name not matched on preview your form.");
	                   pause(1000);
	                   break;
	               case "Email Address":
	                   softAssert.assertEquals(value, "test@test.com", "Email address not matched on preview your form");
	                   pause(1000);
	                   break;
	               case "Subject":
	                   softAssert.assertEquals(value, "Testing" , "Subject not matched on preview your form");
	                   pause(1000);
	                   break;
//	               case "Attachment":
//	                   softAssert.assertEquals(value, validAttachmentFileName, "Attachment name is not matched on preview your form");
//	                   pause(1000);
//	                   break;
	               case "Comments (Optional)":
	                   softAssert.assertEquals(value, "Testing", "Comments not matched on preview your form page.");
	                   pause(1000);
	                   break;                   
	               default:
	           }
	           counter++;
	       }
	   }
	   
	   public void verifyPreConnectMePreviewYourFormDetailsForWaterLeaks(SoftAssert softAssert) {      
	       log.info("Test Case execution for - verifyPreviewYourFormDetailsForWaterLeaks - is Initiated");
		   String user = Configuration.toString("userName");
	       //String validAttachmentFileName = "meter-reading.jpg";
	       List<WebElement> previewYourFormColumn = listPreviewYourFormColumn();
	       List<WebElement> previewYourFormValue = listPreviewYourFormValue();
	       int counter = 0;
	       for (WebElement columnLabelEle : previewYourFormColumn) {
	           String key = columnLabelEle.getText().trim();
	           String value = previewYourFormValue.get(counter).getText().trim();
	           switch (key) {
	               case "Topic":
	                   softAssert.assertEquals(value, "Report Water Waste", "Topic value not matched on preview your form.");
	                   pause(1000);
	                   break;
	               case "Customer Name":
	                   assertEquals(value, user, "Customer name not matched on preview your form.");
	                   pause(1000);
	                   break;
	               case "Email Address":
	                   softAssert.assertEquals(value, "test@test.com", "Email address not matched on preview your form");
	                   pause(1000);
	                   break;
	               case "Subject":
	                   softAssert.assertEquals(value, "Testing" , "Subject not matched on preview your form");
	                   pause(1000);
	                   break;
//	               case "Attachment":
//	                   softAssert.assertEquals(value, validAttachmentFileName, "Attachment name is not matched on preview your form");
//	                   pause(1000);
//	                   break;
	               case "Comments":
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

	
}

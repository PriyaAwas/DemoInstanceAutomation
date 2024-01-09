package sew.ai.steps.scp;

import static sew.ai.steps.scp.DashboardSteps.dashboardTextProp;
import static sew.ai.steps.scp.LoginSteps.loginTextProp;

import java.sql.ResultSet;
import java.util.HashMap;

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
import sew.ai.pageObjects.scp.ProblemSignInPage;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.PropertiesUtil;

public class ProblemSigningInSteps extends ProblemSignInPage {
    private static final Logger log = LogManager.getLogger(ProblemSigningInSteps.class);

    public static PropertiesUtil ProblemSigningInTextProp;

    public ProblemSigningInSteps(WebDriver driver) {
        super(driver);       
        ProblemSigningInTextProp = new PropertiesUtil(
                FilePaths.SCP_TEXT_PROPERTIES + Constants.PROBLEM_SIGNING_IN_TXT_FILENAME
                );
               
  }
    
    /**
	  *To verify that upon clicking on Problem Signing In  link user 
	   is redirected to Problem Signing In page
   **/   
   public void verifyProblemSigningInObject(SoftAssert softAssert) { 
   	LoginSteps loginSteps;    
   	loginSteps = new LoginSteps(driver);
    Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"), loginTextProp
            .getPropValue("loginPageTitle")));    
   	loginSteps.clickProblemSignInLnk();
   	softAssert.assertTrue(isProblemSigningInPage(ProblemSigningInTextProp.getPropValue("ProblemSignInPageUrl"),
      		 (ProblemSigningInTextProp.getPropValue("ProblemSignInPageTitle"))),"Page Title & URL does not Match");
   	softAssert.assertTrue(istxtBoxAccountNumberVisible(), "Account Number Text Box is not visibility");
	softAssert.assertTrue(istxtBoxEmailAddressVisible(), "Email Address Text Box is not visibility");
	softAssert.assertTrue(istxtBoxCommentsVisible(), "Comments Text Box is not visibility");
	softAssert.assertTrue(isCancelBtnVisible(), "Cancel Button is not visible");
	softAssert.assertTrue(isSubmitBtnVisible(), "Next Button is not visible");   	
  }
   
   public void verifyProblemSigningInForm(){	  
	    LoginSteps loginSteps;    
	   	loginSteps = new LoginSteps(driver);
	    User user = SCPConfiguration.user;
     // Verify the message displayed for blank Email address field.
	    enterAccountNumberTxtBox("R002002003");
		enterCommentTxtBoxTxtBox("Test Comments");
		isclickSubmitBtn();
		String errMsgEmail = getlblGenericErrorMessage();
	    Assert.assertEquals(errMsgEmail , ProblemSigningInTextProp.getPropValue("txtMsgBlankEmailAddress"));
     // Verify the message displayed for blank Account number field.
	    cleartxtBoxAccountNumber();
	    enterEmailAddressTxtBox("testemail@sus.com");
	    enterCommentTxtBoxTxtBox("Test Comments");
	    isclickSubmitBtn();
	    String errMsgAccountnumber = getlblGenericErrorMessage();
		Assert.assertEquals(errMsgAccountnumber , ProblemSigningInTextProp.getPropValue("txtEnterValidAccountNo"));
	 // Verify the message displayed for blank Comments.
		cleartxtBoxComment();
		enterAccountNumberTxtBox("R002002003");
		enterEmailAddressTxtBox("testemail@sus.com");
		isclickSubmitBtn();
		String errMsgComments = getlblGenericErrorMessage();
		Assert.assertEquals(errMsgComments , ProblemSigningInTextProp.getPropValue("txtMsgBlankComments"));
	 // To verify that application will not allow user to submit request with INVALID Account Number 
		 enterAccountNumberTxtBox("Z002002003");
		 enterEmailAddressTxtBox("testemail@sus.com");
		 enterCommentTxtBoxTxtBox("Test Comments");
		 isclickSubmitBtn();
		 String toasterMsgInvalidAccountNo = getToastMessage();
		 Assert.assertEquals(toasterMsgInvalidAccountNo , ProblemSigningInTextProp.getPropValue("txtLblErrorMsgInvalidServiceAccount"));
     // To verify that the 'Home' icon is displaying next to language drop-down on the top right corner of the Problem Sign In page. 
		 isicoHomeVisible();
		 String iconHomeTitle = geticoHomeTitle();
	     Assert.assertEquals(iconHomeTitle , ProblemSigningInTextProp.getPropValue("txtTitleHomeIcon"));
	     clickIconHome();
	     loginSteps.waitForUserNameFieldVisibility();
	     Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"), loginTextProp.getPropValue("loginPageTitle"))); 
	 // Submit blank form   
	       loginSteps.clickProblemSignInLnk();
	       isclickSubmitBtn();
		   String toasterMsg = getToastMessage();
		   Assert.assertEquals(toasterMsg , ProblemSigningInTextProp .getPropValue("txtLblMandatoryErrorMessage"));
		   waitForCancelBtnclickable();
	       isclickCancelBtn();
	       Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"), loginTextProp.getPropValue("loginPageTitle")));	       
	  // Submit with invalid accont Number form   
	       enterAccountNumberTxtBox("Z002002003");
			 enterEmailAddressTxtBox(user.getEmailId());
			 enterCommentTxtBoxTxtBox("Test Comments");
			 isclickSubmitBtn();
			 String toasterSuccessMsg = getToastMessage();
			 Assert.assertEquals(toasterSuccessMsg , ProblemSigningInTextProp.getPropValue("txtSuccessToasterMsg"));
			 pause(1000);			 
			 loginSteps.waitForUserNameFieldVisibility();
			 Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"), loginTextProp.getPropValue("loginPageTitle")));  
   }
   
   public boolean isProblemSigningInPage(String url, String title) {
       Boolean isForgetPasswordPage = false;
       log.info("Checking that the current page is ForgetPassword Page");
       if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
       	isForgetPasswordPage = true;
       log.info("The current page is ForgetPassword Page {}: " + isForgetPasswordPage);
       return isForgetPasswordPage;
   }
      
   public void verifyProblemSigningInFormwithValidData(){
	    User user = SCPConfiguration.user;
	    String sComments = "Not able to login with my credentials.";
	   // Verify the message displayed for blank Email address field.
	    enterAccountNumberTxtBox(user.getDefaultUtilityAccNum());
		enterCommentTxtBoxTxtBox(sComments);
		isclickSubmitBtn();
		String errMsgEmail = getlblGenericErrorMessage();
	    Assert.assertEquals(errMsgEmail , ProblemSigningInTextProp.getPropValue("txtMsgBlankEmailAddress"));
     // Verify the message displayed for blank Account number field.
	    cleartxtBoxAccountNumber();
	    enterEmailAddressTxtBox(user.getEmailId());
	    enterCommentTxtBoxTxtBox(sComments);
	    isclickSubmitBtn();
	    String errMsgAccountnumber = getlblGenericErrorMessage();
		Assert.assertEquals(errMsgAccountnumber , ProblemSigningInTextProp.getPropValue("txtEnterValidAccountNo"));
	 // Verify the message displayed for blank Comments.
		cleartxtBoxComment();
		enterAccountNumberTxtBox(user.getDefaultUtilityAccNum());
		enterEmailAddressTxtBox(user.getEmailId());
		isclickSubmitBtn();
		String errMsgComments = getlblGenericErrorMessage();
		Assert.assertEquals(errMsgComments , ProblemSigningInTextProp.getPropValue("txtMsgBlankComments"));
	 // To verify that after providing all valid details user is able to submit request. 
		 enterAccountNumberTxtBox(user.getDefaultUtilityAccNum());
		 enterEmailAddressTxtBox(user.getEmailId());
		 enterCommentTxtBoxTxtBox("Test Comments");
		 isclickSubmitBtn();
		 String toasterSuccessMsg = getToastMessage();
		 Assert.assertEquals(toasterSuccessMsg , ProblemSigningInTextProp.getPropValue("txtSuccessToasterMsg"));
		 pause(1000);
		 LoginSteps loginSteps;		 
		 loginSteps = new LoginSteps(driver);
		 loginSteps.waitForUserNameFieldVisibility();
		 Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"), loginTextProp.getPropValue("loginPageTitle")));  
    
     }
   
   public static HashMap<String, String> getProblemSignInForInactiveAccInfo() {
       String query = SQLQueries.getProblemSignInInactiveAccDetails();
       HashMap<String, String> inactiveAccountInfo = new HashMap<>();
       try {
           ResultSet rs = DataBaseUtils.getResultSet(query);
           while (rs.next()) {
//           	inactiveAccountInfo.put("UserName", rs
//                       .getString("username"));
               inactiveAccountInfo.put("UtilityAccountNumber", rs
                       .getString("utilityaccountnumber"));
               inactiveAccountInfo.put("ZipCode", rs.getString("zipcode"));
               inactiveAccountInfo.put("EmailID", rs.getString("emailid"));
               inactiveAccountInfo.put("PhoneNo", rs.getString("mobilephone"));
               break;
           }
           return inactiveAccountInfo;
       } catch (Exception e) {
           e.printStackTrace();
           return inactiveAccountInfo;
       }
   }
   
   public void verirySubmitInactiveAccountInfo() {	   
	   HashMap<String, String> registeredInactiveAccountInfo = getProblemSignInForInactiveAccInfo();
       System.out.println("registeredInactiveAccountInfo is :"+registeredInactiveAccountInfo);
         enterAccountNumberTxtBox(registeredInactiveAccountInfo.get("UtilityAccountNumber"));
		 enterEmailAddressTxtBox(registeredInactiveAccountInfo.get("EmailID"));
		 enterCommentTxtBoxTxtBox("Problem for Inactive account");
		 isclickSubmitBtn();
		 String toasterSuccessMsg = getToastMessage();
		 Assert.assertEquals(toasterSuccessMsg , ProblemSigningInTextProp.getPropValue("txtSuccessToasterMsg"));
		 pause(1000);
		 LoginSteps loginSteps;    
		 loginSteps = new LoginSteps(driver);
		 Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"), loginTextProp.getPropValue("loginPageTitle")));  	   
   }
   
   public void verifyMinMaxValueOfTextBox(SoftAssert softAssert){
	     User user = SCPConfiguration.user;
	     String accountNumberMaxLength = getTxtBoxAccountNumberMaxLength();
	     String accountNumberMinLength = getTxtBoxAccountNumberMinLength();
	     String emailAdressMaxLength = getTxtBoxEmailAddressMaxLength();
	     softAssert.assertEquals(accountNumberMaxLength, "12",
	               "Max length username field not matched.");
	     softAssert.assertEquals(accountNumberMinLength, "5",
	               "Max length password field not matched.");
	     softAssert.assertEquals(emailAdressMaxLength, "50",
	               "Max length password field not matched.");	     
	     // Enter Minimum length  Account Number
	     enterAccountNumberTxtBox("Z0");
		 enterEmailAddressTxtBox(user.getEmailId());
		 enterCommentTxtBoxTxtBox("Test Comments");
		 isclickSubmitBtn();
		 String enterValidAccountNo = getlblGenericErrorMessage();
		 Assert.assertEquals(enterValidAccountNo , ProblemSigningInTextProp.getPropValue("txtEnterValidAccountNo"));
		 
		// Verify Invalid Error Message for Account Number
		 enterAccountNumberTxtBox("0000000000");
		 enterEmailAddressTxtBox(user.getEmailId());
		 enterCommentTxtBoxTxtBox("Test Comments");
		 isclickSubmitBtn();
		 String toasterSuccessMsg = getlblGenericErrorMessage();
		 Assert.assertEquals(toasterSuccessMsg , ProblemSigningInTextProp.getPropValue("txtEnterValidAccountNo"));
		 
		// Verify valid Error Message for Email
		 enterAccountNumberTxtBox(user.getDefaultUtilityAccNum());
		 enterEmailAddressTxtBox("Test");
		 enterCommentTxtBoxTxtBox("Test Comments");
		 isclickSubmitBtn();
		 String InvalidEmailMsg = getlblGenericErrorMessage();
		 Assert.assertEquals(InvalidEmailMsg , ProblemSigningInTextProp.getPropValue("txtInvalidEmail"));	   
   }
   
}

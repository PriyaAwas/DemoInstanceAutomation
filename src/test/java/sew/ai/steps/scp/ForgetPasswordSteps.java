package sew.ai.steps.scp;

import static org.junit.Assert.assertTrue;
import static sew.ai.steps.scp.DashboardSteps.dashboardTextProp;
import static sew.ai.steps.scp.LoginSteps.loginTextProp;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import sew.ai.config.SCPConfiguration;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.models.User;
import sew.ai.pageObjects.scp.ForgotPasswordPage;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.PropertiesUtil;

public class ForgetPasswordSteps extends ForgotPasswordPage {
    private static final Logger log = LogManager.getLogger(ForgetPasswordSteps.class);

    public static PropertiesUtil ForgotPasswordTextProp;

    public ForgetPasswordSteps(WebDriver driver) {
        super(driver);       
        ForgotPasswordTextProp = new PropertiesUtil(
                FilePaths.SCP_TEXT_PROPERTIES + Constants.FORGET_PASSWORD_TXT_FILENAME
                );               
  }
    
    /**
	  *To verify that upon clicking on Forgot Password link user 
	   is redirected to Forgot Password page
    **/
    
    public void verifyForgetPasswordObject(SoftAssert softAssert) { 
    	LoginSteps loginSteps;    
    	loginSteps = new LoginSteps(driver);
    	 Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"), loginTextProp
    	            .getPropValue("loginPageTitle")));   
    	clickForgotPasswordLnk();
    	softAssert.assertTrue(isForgetPasswordPage(ForgotPasswordTextProp.getPropValue("ForgetPasswordPageUrl"),
       		 (ForgotPasswordTextProp.getPropValue("ForgetPasswordPageTitle"))),"Page Title & URL does not Match");
    	softAssert.assertTrue(isUserNameTxtVisible(), "Username Text Box is not visibility");
    	softAssert.assertTrue(isPagelabelHeaderVisible(), "Forget Password Page header label is not visibility");
    	softAssert.assertTrue(isSubmitBtnVisible(), "Submit Button is not visible");
    	softAssert.assertTrue(isCancelBtnVisible(), "Cancel Button is not visible");   	
   }
    
    public void verifyResetForgetPasswordObject(SoftAssert softAssert) {
   	softAssert.assertTrue(istxtNewPasswordVisible(), "New Password Text Box is not visibility");
   	softAssert.assertTrue(isConfirmPasswordVisible(), "Confirm Password  Text Box is not visibility");   	
   }
       
    public String submitBlankResetForgetPassword() {    	
    	clickbtnSubmitNewPwd();
    	String toasterdMsg = getToastMessage();
        return toasterdMsg;
    }
    
    public LoginSteps verifyPreLoginUrlTitle(SoftAssert softAssert) {
     LoginSteps loginSteps;    
     loginSteps = new LoginSteps(driver);
     loginSteps.verifyTheLoginPageObject(softAssert);
     return loginSteps;   
   	
   }
   
    public boolean isForgetPasswordPage(String url, String title) {
        Boolean isForgetPasswordPage = false;
        log.info("Checking that the current page is ForgetPassword Page");
        if (getCurrentUrl().contains(url.toLowerCase()) && getCurrentTitle().equalsIgnoreCase(title))
        	isForgetPasswordPage = true;
        log.info("The current page is ForgetPassword Page {}: " + isForgetPasswordPage);
        return isForgetPasswordPage;
    }
        
    /**
     * 	To verify the validation message if user left Mandatory field blank
     *  To verify the functionality of Cancel button.
     */    
    public void verifySubmitBlankUserName(SoftAssert softAssert) {    	
    	clickSubmitBtn();
        String errMsg = getlblErrorMsgEnterUsername();
        Assert.assertEquals(errMsg , ForgotPasswordTextProp
                .getPropValue("submitBlankCredsErrMsg"));
        clickCancelBtn();
        LoginSteps loginSteps;   
        loginSteps = new LoginSteps(driver);
        loginSteps.waitForUserNameFieldVisibility();
        Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"), loginTextProp
                .getPropValue("loginPageTitle")));   
        
    }
    
    public String verifyInvalidUserName(SoftAssert softAssert) {
    	clickForgotPasswordLnk();
        populateUserName("Invalid@Username");
        clickSubmitBtn();
        String errMsg = getToastMessage();     
        Assert.assertEquals(errMsg , ForgotPasswordTextProp
                .getPropValue("InvalidUserNameToastMsg")); 
        
        LoginSteps loginSteps;   
        loginSteps = new LoginSteps(driver);
        loginSteps.waitForUserNameFieldVisibility();
        Assert.assertTrue(loginSteps.isLoginPage(loginTextProp.getPropValue("loginPageUrl"), loginTextProp
                .getPropValue("loginPageTitle")));   
        return errMsg;
        // If an account exists for Invalid@Username, you will get an email with instructions on resetting your password. If it doesn't arrive, be sure to check your spam folder.
    }
    
    public DashboardSteps loginIntoTheApplication(String userName, String password) {
        DashboardSteps dashboardSteps;
        waitForPageLoader();
        dashboardSteps = new DashboardSteps(driver);
        Assert.assertTrue(dashboardSteps.isDashboardPage(dashboardTextProp.getPropValue("dashboardPageUrl"),
                dashboardTextProp.getPropValue("dashboardPageHeader")));
        return dashboardSteps;
    }
    
    /**
     * This test method verify below test cases:
     * This method verify the max length attribute of the username and password field.
     *
     * softAssert
     */
    public void verifyMaxLenNewAndConfirmPassword(SoftAssert softAssert) {
        String sMaxLengthNewPassword  = gettxtNewPasswordMaxLength();
        String sMinLengthNewPassword  = gettxtNewPasswordMinLength();
        String sMaxLenConfirmPassword = gettxtConfirmPasswordMaxLength();
        String sMinLenConfirmPassword = gettxtConfirmPasswordMinLength();
        softAssert.assertEquals(sMaxLengthNewPassword, "32",
                "New password max length attribute value not as per the acceptance criteria.");
        softAssert.assertEquals(sMinLengthNewPassword, "8",
                "New password min length is not as per the acceptance criteria.");
        softAssert.assertEquals(sMaxLenConfirmPassword, "32",
                "Confirm password max length attribute value not as per the acceptance criteria.");    
    }
        
    public String enterValidUserName(String UserName)  {    	
    	waitForUserNameVisibility();
        populateUserName(UserName);
        clickSubmitBtn();       
        String toastMsg = getToastMessage();        
        clickForgotPasswordLnk();
        return toastMsg;
        // If an account exists for Invalid@Username, you will get an email with instructions on resetting your password. If it doesn't arrive, be sure to check your spam folder.
    }
    
    /**
	 * This method is used to hit the given url.
	 */
	public void navigateToUrl(String url) {
		try {
			driver.get(url);
		} catch (Exception exception) {
			System.err.format("No Element Found: " + exception);			
		} 		
		 Assert.assertTrue(isForgetPasswordPage(ForgotPasswordTextProp.getPropValue("expectedResetPwdPageUrl"),
	    		 (ForgotPasswordTextProp.getPropValue("expectedResetPwdPageTitle"))));  
	}
		
		public void SwitchToNewtab () {			
			 ((JavascriptExecutor) driver).executeScript("window.open()");
		        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		        driver.switchTo().window(tabs.get(1));
		        navigateToUrl(getForgetPasswordEmailLink());
		        driver.close();
	            driver.switchTo().window(tabs.get(0));        
    }
				
		public void submitForgetPasswordWithInactiveAccountInfo() {
			
			 HashMap<String, String> inactiveAccountInfo = DataBaseUtils.getInactiveAccInfo();
		        if (inactiveAccountInfo.size() > 0) {		        	
		        	 populateUserName(inactiveAccountInfo.get("UserName"));		        	 
		        	 clickSubmitBtn();		        
		        	 String toastMsg = enterValidUserName("UserName");
		        	 Assert.assertEquals(toastMsg , ForgotPasswordTextProp
		                     .getPropValue("ValidUsernameToastMsg").replace("*", "UserName"),
		             "toaster message not matched.");
			
	}
}
				        
		public void validationPasswordstrength() {
			
			 
					
		}
		
		 public void verifyPasswordMaxLengthCriteria(SoftAssert softAssert) {
		        String maxLenPassword = getlblPasswordStrength();
		        softAssert.assertEquals(maxLenPassword, "50",
		                "Max length password field not matched.");
		}
		 
		 public void populateLoginForm(String userName, String password) {
		        populateUserNameSignIn(userName);
		        populatePassword(password);
		}
		 
		 public DashboardSteps loginIntotheApplication(String userName, String password) {
		        DashboardSteps dashboardSteps;
//		        Assert.assertTrue(isLoginPage(loginTextProp.getPropValue("loginPageUrl"), loginTextProp
//		                .getPropValue("loginPageTitle")));
		        populateLoginForm(userName, password);
		        clickSignInBtn();
		        waitForPageLoader();
		        dashboardSteps = new DashboardSteps(driver);
		        Assert.assertTrue(dashboardSteps.isDashboardPage(dashboardTextProp.getPropValue("dashboardPageUrl"),
		                dashboardTextProp.getPropValue("dashboardPageHeader")));
		        return dashboardSteps;
		    }
		 
		 public void settingNewPassword(String NewPassword, String Confpassword) {
			 entertxtNewPassword(NewPassword);
			 entertxtConfirmPassword(Confpassword);
			 clickbtnSubmitNewPwd();
			 waitForPageLoader();
		 }
    
		 public static String getForgetPasswordEmailLink() {
		        String url = null;
		        User user = SCPConfiguration.user;
		        try {
		            String emailBodyQuery = SQLQueries.getForgetPasswordEmailQuery(user.getUserId());
		            ResultSet rs = DataBaseUtils.getResultSet(emailBodyQuery);
		            rs.next();
		            String str1 = rs.getString("Message");
		            String s1 = "href='";
		            String s2 = "'>Click here";
		            url = str1.substring(str1.indexOf(s1) + s1.length(), str1.indexOf(s2));
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        return url;
		    }
		 
		 public static HashMap<String, String> getInactiveAccInfo() {
		        String query = SQLQueries.getInactiveAccDetails();
		        HashMap<String, String> inactiveAccountInfo = new HashMap<>();
		        try {
		            ResultSet rs = DataBaseUtils.getResultSet(query);
		            while (rs.next()) {
		            	inactiveAccountInfo.put("UserName", rs
		                        .getString("username"));
//		                inactiveAccountInfo.put("UtilityAccountNumber", rs
//		                        .getString("utilityaccountnumber"));
//		                inactiveAccountInfo.put("ZipCode", rs.getString("zipcode"));
		                inactiveAccountInfo.put("EmailID", rs.getString("emailid"));
//		                inactiveAccountInfo.put("PhoneNo", rs.getString("mobilephone"));
		                break;
		            }
		            return inactiveAccountInfo;
		        } catch (Exception e) {
		            e.printStackTrace();
		            return inactiveAccountInfo;
		        }
		    }
		    
		    public static void makeUserAccountTempLock(String username) {
		    	String query1 = SQLQueries.getQueryUpdateUserStatus("5", username);
		    	String query2 = SQLQueries.getQueryUpdateUserIsLockedValue("1", username);  	
		    	DataBaseUtils.executeUpdateDeleteSQLQuery(query1);
		    	DataBaseUtils.executeUpdateDeleteSQLQuery(query2);   	
		    }
 
}

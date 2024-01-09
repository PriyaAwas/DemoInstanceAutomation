package sew.ai.utils;

import java.sql.ResultSet;

import sew.ai.helpers.props.SQLQueries;

public class MFAUtil {
	
	public static String getMFAEmailOTP(String sEmail) {
		String sEmailBody = getMFAEmailBody(sEmail, "%Authentication Code%");
		String sStartString;
		String sEndString;
		sStartString = "</p><p>Please use the following code:";
		sEndString = "This code will expire in 2 minutes and can be used only once";
		
		int lengthOfStartString = sStartString.length();
		String mfaCode = sEmailBody.substring(sEmailBody.indexOf(sStartString) + lengthOfStartString,
				sEmailBody.indexOf(sEndString));
		mfaCode = mfaCode.replaceAll("[^0-9]","");
		// MFA code.
		System.out.println("MFA code=> " + mfaCode);
				
		return mfaCode;
	}
	
	
	public static String getMFAEmailBody(String sEmail, String emailSubject) {
		//pageUtil.pause(2000);
		String sEmailBody = null;
		String sIsEmailSent = null;
		String sEmailBodyQuery = SQLQueries.getGuestUserInviteMailContentQuery(sEmail, emailSubject);
		ResultSet rsEmailBody = null;
		try {
			rsEmailBody = DataBaseUtils.getResultSet(sEmailBodyQuery);
			while (rsEmailBody.next()) {
				sEmailBody = rsEmailBody.getString("Message");
				sIsEmailSent = rsEmailBody.getString("IsNotify");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sEmailBody;
	}
	
	public static String getMFATextOTP(String mobileNum) {
		String sSMSBody = getMFATextBody(mobileNum);
		String sStartString;
		String sEndString;
		sStartString = "SCM\r\n" + 
				"Authentication code ";
		sEndString = "Msg&data rates may apply";
		
		int lengthOfStartString = sStartString.length();
		String mfaCode = sSMSBody.substring(sSMSBody.indexOf(sStartString) + lengthOfStartString,
				sSMSBody.indexOf(sEndString));
		mfaCode = mfaCode.replaceAll("[^0-9]","");
		// MFA code.
		System.out.println("MFA code=> " + mfaCode);
				
		return mfaCode;
	}
	
	
	public static String getMFATextBody(String pMobileNum) {
		//pageUtil.pause(2000);
		String sTextBody = null;
		String sIsTextSent = null;
		String sEmailBodyQuery = SQLQueries.getMFATextMsgOTP(pMobileNum);
		ResultSet rsEmailBody = null;
		try {
			rsEmailBody = DataBaseUtils.getResultSet(sEmailBodyQuery);
			while (rsEmailBody.next()) {
				sTextBody = rsEmailBody.getString("Text");
				sIsTextSent = rsEmailBody.getString("IsNotify");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sTextBody;
	}
	

}

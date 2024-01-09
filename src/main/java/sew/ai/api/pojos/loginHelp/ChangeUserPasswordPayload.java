package sew.ai.api.pojos.loginHelp;

import sew.ai.models.User;

public class ChangeUserPasswordPayload {
	String UserID;
	String oldpassword;
	String newpassword;
	String LanguageCode;
	String AccountNumber;

	public ChangeUserPasswordPayload(User user, String pwd) {
		UserID = user.getUserId();
		oldpassword = user.getPassword();
		newpassword = pwd;
		LanguageCode = "EN";
		AccountNumber = user.getAccountNumber();
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getLanguageCode() {
		return LanguageCode;
	}

	public void setLanguageCode(String languageCode) {
		LanguageCode = languageCode;
	}

	public String getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}
}

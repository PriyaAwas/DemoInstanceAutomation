package sew.ai.api.pojos.loginHelp;

import sew.ai.models.User;

public class ForgetPasswordPayload {
    String Email;
    int IsForgotPassword;
    String LanguageCode;
    int UtilityId;
    String UtilityAccountNumber;
    String UserName;
    int Type;
    String Reason;
    boolean IsShow;
    String PhoneNumber;

    public ForgetPasswordPayload(User user) {
        Email = null;
        IsForgotPassword = 0;
        LanguageCode = "EN";
        UtilityId = 0;
        UtilityAccountNumber = null;
        UserName = user.getUserName();
        Type = 2;
        Reason = null;
        IsShow = false;
        PhoneNumber = null;
//    	Email = null;
//    	IsForgotPassword = 1;
//    	LanguageCode = "EN";
//    	UtilityId = 0;
//    	UtilityAccountNumber =null;
//    	UserName = "Varad0770";
//    	Type = 1;
//    	Reason = null;
//    	IsShow = false;
//    	PhoneNumber = null;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Integer getIsForgotPassword() {
        return IsForgotPassword;
    }

    public void setIsForgotPassword(Integer isForgotPassword) {
        IsForgotPassword = isForgotPassword;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }

    public Integer getUtilityId() {
        return UtilityId;
    }

    public void setUtilityId(Integer utilityId) {
        UtilityId = utilityId;
    }

    public String getUtilityAccountNumber() {
        return UtilityAccountNumber;
    }

    public void setUtilityAccountNumber(String utilityAccountNumber) {
        UtilityAccountNumber = utilityAccountNumber;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public Integer getType() {
        return Type;
    }

    public void setType(Integer type) {
        Type = type;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public boolean isIsShow() {
        return IsShow;
    }

    public void setIsShow(boolean isShow) {
        IsShow = isShow;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }
}

package sew.ai.api.pojos.loginHelp;

import sew.ai.models.User;

public class ForgotEmailAsUsernamePayload {
    String Email;
    String IsForgotPassword;
    String LanguageCode;
    String UtilityId;
    String UtilityAccountNumber;
    String UserName;
    int Type;
    String Reason;
    boolean IsShow;
    int ForgotUsernameAuthType;
    String PhoneNumber;
    String SSNNumber;
    String StreetAddress;
    String ZipCode;
    String TINNumber;

    public ForgotEmailAsUsernamePayload(User user) {
        this.Email = user.getEmailId();
        this.IsForgotPassword = "0";
        this.LanguageCode = "EN";
        this.UtilityId = "0";
        this.UtilityAccountNumber = user.getAccountNumber();
        this.UserName = "";
        this.Type = 4;
        this.Reason = null;
        this.IsShow = false;
        this.ForgotUsernameAuthType = 2;
        this.PhoneNumber = "";
        this.SSNNumber = "";
        this.StreetAddress = "";
        this.ZipCode = "";
        this.TINNumber = null;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getIsForgotPassword() {
        return IsForgotPassword;
    }

    public void setIsForgotPassword(String isForgotPassword) {
        IsForgotPassword = isForgotPassword;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }

    public String getUtilityId() {
        return UtilityId;
    }

    public void setUtilityId(String utilityId) {
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

    public int getType() {
        return Type;
    }

    public void setType(int type) {
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

    public int getForgotUsernameAuthType() {
        return ForgotUsernameAuthType;
    }

    public void setForgotUsernameAuthType(int forgotUsernameAuthType) {
        ForgotUsernameAuthType = forgotUsernameAuthType;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getSSNNumber() {
        return SSNNumber;
    }

    public void setSSNNumber(String sSNNumber) {
        SSNNumber = sSNNumber;
    }

    public String getStreetAddress() {
        return StreetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        StreetAddress = streetAddress;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
    }

    public String getTINNumber() {
        return TINNumber;
    }

    public void setTINNumber(String tINNumber) {
        TINNumber = tINNumber;
    }
}
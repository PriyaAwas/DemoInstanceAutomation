package sew.ai.api.pojos.myaccount.profile;

import sew.ai.models.User;

public class UpdateMyProfileInfoPayload {
    private int UserID;
    private String EmailId;
    private String HomePhone;
    private int AccountNumber;
    private String MobilePhone;
    private String SessionCode;
    private String Token;
    private String AlternateEmailID;
    private int EmailIdUpdate;
    private int UtilityID;
    private String LanguageCode;
    private int HomePhoneType;
    private int MobilePhoneType;
    private int GroupId;
    private String Password;
    private String chkboxVar;
    private Boolean chkboxVal;

    public UpdateMyProfileInfoPayload() {
    }

    public UpdateMyProfileInfoPayload(User user) {
        this.UserID = Integer.parseInt(user.getUserId());
        this.EmailId = user.getEmailId();
        this.HomePhone = user.getHomePhone();
        this.MobilePhone = user.getMobilePhone();
        this.AlternateEmailID = user.getAlternateEmailID();
        this.HomePhoneType = Integer.parseInt(user.getHomePhoneType());
        this.MobilePhoneType = Integer.parseInt(user.getMobilePhoneType());
        this.Password = "";
        this.AccountNumber = 0;
        this.SessionCode = "";
        this.Token = "";
        this.EmailIdUpdate = 0;
        this.UtilityID = 0;
        this.LanguageCode = "EN";
        this.HomePhoneType = 2;
        this.MobilePhoneType = 2;
        this.GroupId = 0;
        this.chkboxVar = "";
        this.chkboxVal = true;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }

    public String getHomePhone() {
        return HomePhone;
    }

    public void setHomePhone(String homePhone) {
        HomePhone = homePhone;
    }

    public int getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        AccountNumber = accountNumber;
    }

    public String getMobilePhone() {
        return MobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        MobilePhone = mobilePhone;
    }

    public String getSessionCode() {
        return SessionCode;
    }

    public void setSessionCode(String sessionCode) {
        SessionCode = sessionCode;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getAlternateEmailID() {
        return AlternateEmailID;
    }

    public void setAlternateEmailID(String alternateEmailID) {
        AlternateEmailID = alternateEmailID;
    }

    public int getEmailIdUpdate() {
        return EmailIdUpdate;
    }

    public void setEmailIdUpdate(int emailIdUpdate) {
        EmailIdUpdate = emailIdUpdate;
    }

    public int getUtilityID() {
        return UtilityID;
    }

    public void setUtilityID(int utilityID) {
        UtilityID = utilityID;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }

    public int getHomePhoneType() {
        return HomePhoneType;
    }

    public void setHomePhoneType(int homePhoneType) {
        HomePhoneType = homePhoneType;
    }

    public int getMobilePhoneType() {
        return MobilePhoneType;
    }

    public void setMobilePhoneType(int mobilePhoneType) {
        MobilePhoneType = mobilePhoneType;
    }

    public int getGroupId() {
        return GroupId;
    }

    public void setGroupId(int groupId) {
        GroupId = groupId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getChkboxVar() {
        return chkboxVar;
    }

    public void setChkboxVar(String chkboxVar) {
        this.chkboxVar = chkboxVar;
    }

    public Boolean getChkboxVal() {
        return chkboxVal;
    }

    public void setChkboxVal(Boolean chkboxVal) {
        this.chkboxVal = chkboxVal;
    }
}

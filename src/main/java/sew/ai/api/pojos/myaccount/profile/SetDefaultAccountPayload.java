package sew.ai.api.pojos.myaccount.profile;

import sew.ai.models.UserAccount;

public class SetDefaultAccountPayload {
    private int UserID;
    private int AccountNumber;
    private String LanguageCode;
    private String UtilityAccountNumber;
    private int Mode;
    private String EmailId;
    private String MobilePhone;
    private Boolean AutoSyncChk;

    public SetDefaultAccountPayload() {
    }

    public SetDefaultAccountPayload(UserAccount userAccount) {
        this.UserID = Integer.parseInt(userAccount.getUserId());
        this.AccountNumber = Integer.parseInt(userAccount.getAccountNumber());
        this.UtilityAccountNumber = userAccount.getUtilityAccNum();
        this.LanguageCode = "EN";
        this.Mode = 0;
        this.EmailId = "";
        this.MobilePhone = "";
        this.AutoSyncChk = true;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        AccountNumber = accountNumber;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }

    public String getUtilityAccountNumber() {
        return UtilityAccountNumber;
    }

    public void setUtilityAccountNumber(String utilityAccountNumber) {
        UtilityAccountNumber = utilityAccountNumber;
    }

    public int getMode() {
        return Mode;
    }

    public void setMode(int mode) {
        Mode = mode;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }

    public String getMobilePhone() {
        return MobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        MobilePhone = mobilePhone;
    }

    public Boolean getAutoSyncChk() {
        return AutoSyncChk;
    }

    public void setAutoSyncChk(Boolean autoSyncChk) {
        AutoSyncChk = autoSyncChk;
    }
}

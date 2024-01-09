package sew.ai.api.pojos.auth;

import sew.ai.models.User;

public class UpdateUserNamePayLoad {
    int userId;
    String OldUserId;
    String NewUserId;
    String Password;
    int UtilityId;
    String UtilityAccountNumber;
    String LanguageCode;
    int AccountNumber;

    public UpdateUserNamePayLoad(User user, String updateUserName) {
        userId = Integer.parseInt(user.getUserId());
        // userId = 11531;
        OldUserId = user.getUserName();
        NewUserId = updateUserName;
        Password = user.getPassword();
        UtilityId = 0;
        UtilityAccountNumber = user.getDefaultUtilityAccNum();
        LanguageCode = "EN";
        AccountNumber = Integer.parseInt(user.getAccountNumber());
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOldUserId() {
        return OldUserId;
    }

    public void setOldUserId(String oldUserId) {
        OldUserId = oldUserId;
    }

    public String getNewUserId() {
        return NewUserId;
    }

    public void setNewUserId(String newUserId) {
        NewUserId = newUserId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getUtilityId() {
        return UtilityId;
    }

    public void setUtilityId(int utilityId) {
        UtilityId = utilityId;
    }

    public String getUtilityAccountNumber() {
        return UtilityAccountNumber;
    }

    public void setUtilityAccountNumber(String utilityAccountNumber) {
        UtilityAccountNumber = utilityAccountNumber;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }

    public int getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        AccountNumber = accountNumber;
    }
}

package sew.ai.api.pojos.myaccount.profile;

import sew.ai.models.User;

public class MyProfilePayload {
    private int AccountNumber;
    private int UserID;
    private String LanguageCode;
    private Boolean Ismobile;
    private String UtilityAccountNumber;
    private int GroupId;

    public MyProfilePayload() {
    }

    public MyProfilePayload(User user) {
        this.UserID = Integer.parseInt(user.getUserId());
        this.AccountNumber = 0;
        this.UtilityAccountNumber = "";
        this.LanguageCode = "EN";
        this.Ismobile = true;
        this.GroupId = 0;
    }

    public int getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        AccountNumber = accountNumber;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }

    public Boolean getIsmobile() {
        return Ismobile;
    }

    public void setIsmobile(Boolean ismobile) {
        Ismobile = ismobile;
    }

    public String getUtilityAccountNumber() {
        return UtilityAccountNumber;
    }

    public void setUtilityAccountNumber(String utilityAccountNumber) {
        UtilityAccountNumber = utilityAccountNumber;
    }

    public int getGroupId() {
        return GroupId;
    }

    public void setGroupId(int groupId) {
        GroupId = groupId;
    }
}

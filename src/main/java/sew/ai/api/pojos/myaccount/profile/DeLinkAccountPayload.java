package sew.ai.api.pojos.myaccount.profile;

import sew.ai.config.SCPConfiguration;
import sew.ai.models.User;
import sew.ai.models.UserAccount;

public class DeLinkAccountPayload {
    private int UserID;
    private int AccountNumber;
    private int UserType;
    private String HomePhone;
    private String MobilePhone;
    private String EmailID;
    private String AlternateEmailID;
    private String UtilityAccountNumber;
    private int GroupId;
    private String LanguageCode;
    private Boolean IsRegistration;
    private int PersonaId;
    User user;

    public DeLinkAccountPayload() {
    }

    public DeLinkAccountPayload(UserAccount userAccount) {
        user = SCPConfiguration.user;
        this.UserID = Integer.parseInt(userAccount.getUserId());
        this.AccountNumber = Integer.parseInt(userAccount.getAccountNumber());
        this.UserType = 0;
        this.HomePhone = "";
        this.MobilePhone = "";
        this.EmailID = user.getEmailId();
        this.AlternateEmailID = user.getEmailId();
        this.UtilityAccountNumber = userAccount.getUtilityAccNum();
        this.GroupId = 0;
        this.LanguageCode = "EN";
        this.IsRegistration = true;
        this.PersonaId = 0;
    }

    public int getUserID() {
        return UserID;
    }

    public int getAccountNumber() {
        return AccountNumber;
    }

    public int getUserType() {
        return UserType;
    }

    public String getHomePhone() {
        return HomePhone;
    }

    public String getMobilePhone() {
        return MobilePhone;
    }

    public String getEmailID() {
        return EmailID;
    }

    public String getAlternateEmailID() {
        return AlternateEmailID;
    }

    public String getUtilityAccountNumber() {
        return UtilityAccountNumber;
    }

    public int getGroupId() {
        return GroupId;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public Boolean getIsRegistration() {
        return IsRegistration;
    }

    public int getPersonaId() {
        return PersonaId;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public void setAccountNumber(int accountNumber) {
        AccountNumber = accountNumber;
    }

    public void setUserType(int userType) {
        UserType = userType;
    }

    public void setHomePhone(String homePhone) {
        HomePhone = homePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        MobilePhone = mobilePhone;
    }

    public void setEmailID(String emailID) {
        EmailID = emailID;
    }

    public void setAlternateEmailID(String alternateEmailID) {
        AlternateEmailID = alternateEmailID;
    }

    public void setUtilityAccountNumber(String utilityAccountNumber) {
        UtilityAccountNumber = utilityAccountNumber;
    }

    public void setGroupId(int groupId) {
        GroupId = groupId;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }

    public void setIsRegistration(Boolean isRegistration) {
        IsRegistration = isRegistration;
    }

    public void setPersonaId(int personaId) {
        PersonaId = personaId;
    }
}

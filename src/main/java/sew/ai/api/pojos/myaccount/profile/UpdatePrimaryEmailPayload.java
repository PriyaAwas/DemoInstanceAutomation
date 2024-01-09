package sew.ai.api.pojos.myaccount.profile;

import sew.ai.models.User;

public class UpdatePrimaryEmailPayload {
    private int AccountNumber;
    private int UserId;
    private String LanguageCode;
    private String EmailId;
    private String Password;

    public UpdatePrimaryEmailPayload(User user, String emailId) {
        AccountNumber = Integer.parseInt(user.getAccountNumber());
        UserId = Integer.parseInt(user.getUserId());
        LanguageCode = "EN";
        EmailId = emailId;
        Password = user.getPassword();
    }

    public int getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        AccountNumber = accountNumber;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

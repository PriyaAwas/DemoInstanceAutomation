package sew.ai.api.pojos.myaccount.profile;

import sew.ai.models.User;

public class ValidatePasswordPayload {
    private String UserName;
    private String LanguageCode;
    private String Password;

    public ValidatePasswordPayload() {
    }

    public ValidatePasswordPayload(User user, String status) {
        this.UserName = user.getUserName();
        this.LanguageCode = "EN";
        if (status.equals("Correct")) {
            this.Password = user.getPassword();
        }
        else {
            this.Password = user.getPassword() + "abc";
        }
    }

    public String getUserName() {
        return UserName;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public String getPassword() {
        return Password;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

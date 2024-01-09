package sew.ai.api.pojos.loginHelp;

import sew.ai.models.User;

public class PasswordResetTokenPayload {
    String UserName;
    String Token;
    String IsCancel;
    int Mode;
    String UtilityId;
    String LanguageCode;

    public PasswordResetTokenPayload(User user) {
        UserName = user.getUserName();
        Token = user.getJwtToken();
        IsCancel = "0";
        Mode = 1;
        UtilityId = user.getUtilityId();
        LanguageCode = "EN";
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getIsCancel() {
        return IsCancel;
    }

    public void setIsCancel(String isCancel) {
        IsCancel = isCancel;
    }

    public int getMode() {
        return Mode;
    }

    public void setMode(int mode) {
        Mode = mode;
    }

    public String getUtilityId() {
        return UtilityId;
    }

    public void setUtilityId(String utilityId) {
        UtilityId = utilityId;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }
}

package sew.ai.api.pojos.myaccount.guestuser;

import sew.ai.models.User;

public class IsGuestAccessPayload {
    private String UtilityAccountNumber;
    private String LanguageCode;
    private int UserId;

    public IsGuestAccessPayload(User user) {
        UtilityAccountNumber = user.getDefaultUtilityAccNum();
        LanguageCode = "EN";
        UserId = Integer.parseInt(user.getUserId());
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

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }
}

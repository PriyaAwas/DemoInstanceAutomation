package sew.ai.api.pojos.myaccount.profile;

public class MarketingPreferencePayload {
    int UserID;
    String PreferenceId;
    String LanguageCode;
    String UtilityAccountNumber;

    public MarketingPreferencePayload(int userID, String preferenceId) {
        UserID = userID;
        PreferenceId = preferenceId;
        LanguageCode = "EN";
        UtilityAccountNumber = "";
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getPreferenceId() {
        return PreferenceId;
    }

    public void setPreferenceId(String preferenceId) {
        PreferenceId = preferenceId;
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
}

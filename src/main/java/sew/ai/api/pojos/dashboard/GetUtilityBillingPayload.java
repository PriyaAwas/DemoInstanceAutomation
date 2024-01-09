package sew.ai.api.pojos.dashboard;

import sew.ai.models.User;

public class GetUtilityBillingPayload {
    private int AccountNumber;
    private int IsBillPDF;
    private int IsPreLogin;
    private String BillingDate;
    private int UserID;
    private String LanguageCode;
    private String UtilityAccountNumber;
    private int IsDashboard;
    private String DateFormats;
    private String CurrencySymbols;
    private String TimeZoneName;
    private int UptoDecimalPlaces;

    public GetUtilityBillingPayload(User user) {
        this.AccountNumber = Integer.parseInt(user.getAccountNumber());
        this.IsBillPDF = 0;
        this.IsPreLogin = 0;
        this.BillingDate = "/Date(-62135596800000)/";
        this.UserID = Integer.parseInt(user.getUserId());
        this.LanguageCode = "EN";
        this.UtilityAccountNumber = user.getDefaultUtilityAccNum();
        this.IsDashboard = 0;
        this.DateFormats = "MMMM d, yyyy";
        this.CurrencySymbols = "$";
        this.TimeZoneName = "Pacific Standard Time (US and Canada)";
        this.UptoDecimalPlaces = 0;
    }

    public int getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        AccountNumber = accountNumber;
    }

    public int getIsBillPDF() {
        return IsBillPDF;
    }

    public void setIsBillPDF(int isBillPDF) {
        IsBillPDF = isBillPDF;
    }

    public int getIsPreLogin() {
        return IsPreLogin;
    }

    public void setIsPreLogin(int isPreLogin) {
        IsPreLogin = isPreLogin;
    }

    public String getBillingDate() {
        return BillingDate;
    }

    public void setBillingDate(String billingDate) {
        BillingDate = billingDate;
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

    public String getUtilityAccountNumber() {
        return UtilityAccountNumber;
    }

    public void setUtilityAccountNumber(String utilityAccountNumber) {
        UtilityAccountNumber = utilityAccountNumber;
    }

    public int getIsDashboard() {
        return IsDashboard;
    }

    public void setIsDashboard(int isDashboard) {
        IsDashboard = isDashboard;
    }

    public String getDateFormats() {
        return DateFormats;
    }

    public void setDateFormats(String dateFormats) {
        DateFormats = dateFormats;
    }

    public String getCurrencySymbols() {
        return CurrencySymbols;
    }

    public void setCurrencySymbols(String currencySymbols) {
        CurrencySymbols = currencySymbols;
    }

    public String getTimeZoneName() {
        return TimeZoneName;
    }

    public void setTimeZoneName(String timeZoneName) {
        TimeZoneName = timeZoneName;
    }

    public int getUptoDecimalPlaces() {
        return UptoDecimalPlaces;
    }

    public void setUptoDecimalPlaces(int uptoDecimalPlaces) {
        UptoDecimalPlaces = uptoDecimalPlaces;
    }
}

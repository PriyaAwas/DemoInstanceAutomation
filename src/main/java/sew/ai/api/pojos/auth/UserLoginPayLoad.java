package sew.ai.api.pojos.auth;

import sew.ai.models.User;

public class UserLoginPayLoad {
    private String UserId;
    private String password;
    private String LanguageCode;
    private String ExternalLoginId;
    private int IsCSRUser;
    private String PushToken;
    private String Deviceid;
    private String UtilityAccountNumber;
    private String UpdatedDate;
    private String LUpdHideShow;
    private int TimeOffSet;
    private String deviceType;
    private String OperatingSystem;
    private String Browser;
    private String Country;
    private String key;
    boolean IsEmailasUserName;
    private String TFADeviceid;
    private String OSVersion;
    private Double CaptchaScore;

    public UserLoginPayLoad(User user) {
        UserId = user.getUserName();
        password = user.getPassword();
        LanguageCode = "EN";
        ExternalLoginId = "";
        IsCSRUser = 0;
        PushToken = null;
        Deviceid = null;
        UtilityAccountNumber = "";
        UpdatedDate = "0001-01-01T00:00:00";
        LUpdHideShow = "2023-01-09T07:20:19.9817735Z";
        TimeOffSet = 0;
        deviceType = "Web";
        OperatingSystem = "Windows10";
        Browser = "Chrome";
        Country = "US";
        key = null;
        IsEmailasUserName = false;
        TFADeviceid = null;
        OSVersion = "10";
        CaptchaScore = 0.9;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }

    public String getExternalLoginId() {
        return ExternalLoginId;
    }

    public void setExternalLoginId(String externalLoginId) {
        ExternalLoginId = externalLoginId;
    }

    public int getIsCSRUser() {
        return IsCSRUser;
    }

    public void setIsCSRUser(int isCSRUser) {
        IsCSRUser = isCSRUser;
    }

    public String getPushToken() {
        return PushToken;
    }

    public void setPushToken(String pushToken) {
        PushToken = pushToken;
    }

    public String getDeviceid() {
        return Deviceid;
    }

    public void setDeviceid(String deviceid) {
        Deviceid = deviceid;
    }

    public String getUtilityAccountNumber() {
        return UtilityAccountNumber;
    }

    public void setUtilityAccountNumber(String utilityAccountNumber) {
        UtilityAccountNumber = utilityAccountNumber;
    }

    public String getUpdatedDate() {
        return UpdatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        UpdatedDate = updatedDate;
    }

    public String getLUpdHideShow() {
        return LUpdHideShow;
    }

    public void setLUpdHideShow(String lUpdHideShow) {
        LUpdHideShow = lUpdHideShow;
    }

    public int getTimeOffSet() {
        return TimeOffSet;
    }

    public void setTimeOffSet(int timeOffSet) {
        TimeOffSet = timeOffSet;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getOperatingSystem() {
        return OperatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        OperatingSystem = operatingSystem;
    }

    public String getBrowser() {
        return Browser;
    }

    public void setBrowser(String browser) {
        Browser = browser;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isIsEmailasUserName() {
        return IsEmailasUserName;
    }

    public void setIsEmailasUserName(boolean isEmailasUserName) {
        IsEmailasUserName = isEmailasUserName;
    }

    public String getTFADeviceid() {
        return TFADeviceid;
    }

    public void setTFADeviceid(String tFADeviceid) {
        TFADeviceid = tFADeviceid;
    }

    public String getOSVersion() {
        return OSVersion;
    }

    public void setOSVersion(String oSVersion) {
        OSVersion = oSVersion;
    }

    public Double getCaptchaScore() {
        return CaptchaScore;
    }

    public void setCaptchaScore(Double captchaScore) {
        CaptchaScore = captchaScore;
    }
}

package sew.ai.api.pojos.auth;

public class LoginPayload {
    String UserId;
    String password;
    String LanguageCode;
    String ExternalLoginId;
    Integer IsCSRUser;
    String PushToken;
    String Deviceid;
    String UtilityAccountNumber;
    String UpdatedDate;
    String LUpdHideShow;
    Integer TimeOffSet;
    String deviceType;
    String OperatingSystem;
    String Browser;
    String Country;
    String key;
    Boolean IsEmailasUserName;
    String TFADeviceid;
    String OSVersion;

    /*
     * private String Key; private String IsEmail as UserName;
     */
    public LoginPayload(String userId, String pass, String utilityAccNum) {
        setUserId(userId);
        setPassword(pass);
        setUtilityAccountNumber(utilityAccNum);
        setLanguageCode("EN");
        setExternalLoginId("");
        setIsCSRUser(0);
        setPushToken("");
        setUpdatedDate("01-01-0001 12:00:00 AM");
        setLUpdHideShow("");
        setTimeOffSet(0);
        setDeviceType("Web");
        setDeviceid("");
        setTFADeviceid("");
        setOSVersion("");
        setOperatingSystem("WinNT");
        setBrowser("Chrome");
        setCountry("US");
        setKey("");
        setEmailasUserName(false);
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

    public Integer getIsCSRUser() {
        return IsCSRUser;
    }

    public void setIsCSRUser(Integer isCSRUser) {
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

    public void setLUpdHideShow(String LUpdHideShow) {
        this.LUpdHideShow = LUpdHideShow;
    }

    public Integer getTimeOffSet() {
        return TimeOffSet;
    }

    public void setTimeOffSet(Integer timeOffSet) {
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

    public Boolean getEmailasUserName() {
        return IsEmailasUserName;
    }

    public void setEmailasUserName(Boolean emailasUserName) {
        IsEmailasUserName = emailasUserName;
    }

    public String getTFADeviceid() {
        return TFADeviceid;
    }

    public void setTFADeviceid(String TFADeviceid) {
        this.TFADeviceid = TFADeviceid;
    }

    public String getOSVersion() {
        return OSVersion;
    }

    public void setOSVersion(String OSVersion) {
        this.OSVersion = OSVersion;
    }
}

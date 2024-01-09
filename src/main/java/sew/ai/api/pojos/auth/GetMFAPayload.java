package sew.ai.api.pojos.auth;

public class GetMFAPayload {
    String UserId;
    String LanguageCode;
    String OperatingSystem;
    String Browser;
    String Country;
    String Client;
    String deviceID;
    String OSVersion;
    Boolean IsRegistration;

    public GetMFAPayload(String userName, Boolean isRegistration) {
        setUserId(userName);
        setLanguageCode("EN");
        setOperatingSystem("WinNT");
        setBrowser("Chrome");
        setCountry("US");
        setClient("1");
        setDeviceID("");
        setOSVersion("");
        setIsRegistration(isRegistration);
    }

    public GetMFAPayload(String userName, String client,  String operatingSystem, String browser, String country,
                  Boolean isRegistration) {
        setUserId(userName);
        setLanguageCode("EN");
        setOperatingSystem(operatingSystem);
        setBrowser(browser);
        setCountry(country);
        if (client.equalsIgnoreCase("Web")) {
            setClient("1");
        } else if (client.equalsIgnoreCase("Android")) {
            setClient("2");
        } else if (client.equalsIgnoreCase("iOS")) {
            setClient("3");
        }
        setDeviceID("");
        setOSVersion("");
        setIsRegistration(isRegistration);
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
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

    public String getClient() {
        return Client;
    }

    public void setClient(String client) {
        Client = client;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getOSVersion() {
        return OSVersion;
    }

    public void setOSVersion(String OSVersion) {
        this.OSVersion = OSVersion;
    }

    public Boolean getIsRegistration() {
        return IsRegistration;
    }

    public void setIsRegistration(Boolean isRegistration) {
        IsRegistration = isRegistration;
    }
}

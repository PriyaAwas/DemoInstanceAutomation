package sew.ai.api.pojos.auth;

public class CustomAttributesMFAPayload {
    String Ip;
    String Browser;
    String Client;
    String Version;
    String DeviceId;
    String DeviceName;
    String Os;

    public CustomAttributesMFAPayload(String browser, String operatingSystem) {
        setIp("127.0.0.1");
        setBrowser(browser);
        setOs(operatingSystem);
        setClient("Web");
        setVersion("");
        setDeviceId("");
        setDeviceName("");
    }

    public String getIp() {
        return Ip;
    }

    public void setIp(String ip) {
        Ip = ip;
    }

    public String getBrowser() {
        return Browser;
    }

    public void setBrowser(String browser) {
        Browser = browser;
    }

    public String getClient() {
        return Client;
    }

    public void setClient(String client) {
        Client = client;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }

    public String getDeviceName() {
        return DeviceName;
    }

    public void setDeviceName(String deviceName) {
        DeviceName = deviceName;
    }

    public String getOs() {
        return Os;
    }

    public void setOs(String os) {
        Os = os;
    }
}

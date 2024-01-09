package sew.ai.api.pojos.auth;

public class SinglePageRegistrationPayLoad {
    private int AccountNumber;
    private String FirstName;
    private String MiddleName;
    private String LastName;
    private String EmailId;
    private String DateOfBirth;
    private String MobileNumber;
    private String HomePhone;
    private String Address1;
    private String Address2;
    private int IsPasswordRequest;
    private int CityId;
    private String PostalCode;
    private String UserName;
    private String Password;
    private String ConfirmPassword;
    private String MeterNumber;
    private String EmailNotify;
    private String BudgetNotify;
    private String BillingAddress;
    private String ServiceAccount;
    private String CustomerUtilityId;
    private int AddressPowerPlanID;
    private int EVPowerPlanID;
    private int DefaultUsageView;
    private int DefaultpaymentType;
    private int UtilityID;
    private double Latitude;
    private double Longitude;
    private String SSNNumber;
    private String UtilityAccountNumber;
    private int IsVerfication;
    private String SessionCode;
    private String AlternateEmailID;
    private String BPNumber;
    private String StreetNumber;
    private String DrivingLicence;
    private int IsCSRUser;
    private String LanguageCode;
    private String CustomerNo;
    private int CustomerType;
    private String ExternalLoginId;
    private int LoginMode;
    private int ContactType;
    private String NotificationPreference;
    private String PaperlessBill;
    private String OSType;
    private String Browser;
    private String deviceType;
    private String TFADeviceid;
    private String OSVersion;
    private double CaptchaScore;
    private boolean IsForgotUsername;
    private int AuthenticationType;
    private boolean CreateAuthCode;
    private boolean ResendAuthCode;
    private String Token;
    private boolean VerifyAuthCode;

    public SinglePageRegistrationPayLoad() {
        AccountNumber = 0;
        FirstName = "";
        MiddleName = null;
        LastName = "";
        EmailId = "";
        DateOfBirth = "0001-01-01T00:00:00";
        MobileNumber = "";
        HomePhone = null;
        Address1 = null;
        Address2 = null;
        IsPasswordRequest = 0;
        CityId = 0;
        PostalCode = "18034";
        UserName = "";
        Password = "";
        ConfirmPassword = null;
        MeterNumber = "";
        EmailNotify = null;
        BudgetNotify = null;
        BillingAddress = null;
        ServiceAccount = null;
        CustomerUtilityId = null;
        AddressPowerPlanID = 0;
        EVPowerPlanID = 0;
        DefaultUsageView = 0;
        DefaultpaymentType = 0;
        UtilityID = 0;
        Latitude = 0.0;
        Longitude = 0.0;
        SSNNumber = "";
        UtilityAccountNumber = "411002659463";
        IsVerfication = 1;
        SessionCode = null;
        AlternateEmailID = null;
        BPNumber = null;
        StreetNumber = "";
        DrivingLicence = "";
        IsCSRUser = 0;
        LanguageCode = "EN";
        CustomerNo = "";
        CustomerType = 1;
        ExternalLoginId = null;
        LoginMode = 0;
        ContactType = 0;
        NotificationPreference = "1";
        PaperlessBill = "1";
        OSType = "Windows";
        Browser = "Chrome";
        deviceType = "Web";
        TFADeviceid = null;
        OSVersion = "10";
        CaptchaScore = 0.0;
        IsForgotUsername = false;
        AuthenticationType = 1;
        CreateAuthCode = false;
        ResendAuthCode = true;
        Token = "";
        VerifyAuthCode = false;
    }

    public int getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        AccountNumber = accountNumber;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getHomePhone() {
        return HomePhone;
    }

    public void setHomePhone(String homePhone) {
        HomePhone = homePhone;
    }

    public String getAddress1() {
        return Address1;
    }

    public void setAddress1(String address1) {
        Address1 = address1;
    }

    public String getAddress2() {
        return Address2;
    }

    public void setAddress2(String address2) {
        Address2 = address2;
    }

    public int getIsPasswordRequest() {
        return IsPasswordRequest;
    }

    public void setIsPasswordRequest(int isPasswordRequest) {
        IsPasswordRequest = isPasswordRequest;
    }

    public int getCityId() {
        return CityId;
    }

    public void setCityId(int cityId) {
        CityId = cityId;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        ConfirmPassword = confirmPassword;
    }

    public String getMeterNumber() {
        return MeterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        MeterNumber = meterNumber;
    }

    public String getEmailNotify() {
        return EmailNotify;
    }

    public void setEmailNotify(String emailNotify) {
        EmailNotify = emailNotify;
    }

    public String getBudgetNotify() {
        return BudgetNotify;
    }

    public void setBudgetNotify(String budgetNotify) {
        BudgetNotify = budgetNotify;
    }

    public String getBillingAddress() {
        return BillingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        BillingAddress = billingAddress;
    }

    public String getServiceAccount() {
        return ServiceAccount;
    }

    public void setServiceAccount(String serviceAccount) {
        ServiceAccount = serviceAccount;
    }

    public String getCustomerUtilityId() {
        return CustomerUtilityId;
    }

    public void setCustomerUtilityId(String customerUtilityId) {
        CustomerUtilityId = customerUtilityId;
    }

    public int getAddressPowerPlanID() {
        return AddressPowerPlanID;
    }

    public void setAddressPowerPlanID(int addressPowerPlanID) {
        AddressPowerPlanID = addressPowerPlanID;
    }

    public int getEVPowerPlanID() {
        return EVPowerPlanID;
    }

    public void setEVPowerPlanID(int eVPowerPlanID) {
        EVPowerPlanID = eVPowerPlanID;
    }

    public int getDefaultUsageView() {
        return DefaultUsageView;
    }

    public void setDefaultUsageView(int defaultUsageView) {
        DefaultUsageView = defaultUsageView;
    }

    public int getDefaultpaymentType() {
        return DefaultpaymentType;
    }

    public void setDefaultpaymentType(int defaultpaymentType) {
        DefaultpaymentType = defaultpaymentType;
    }

    public int getUtilityID() {
        return UtilityID;
    }

    public void setUtilityID(int utilityID) {
        UtilityID = utilityID;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public String getSSNNumber() {
        return SSNNumber;
    }

    public void setSSNNumber(String sSNNumber) {
        SSNNumber = sSNNumber;
    }

    public String getUtilityAccountNumber() {
        return UtilityAccountNumber;
    }

    public void setUtilityAccountNumber(String utilityAccountNumber) {
        UtilityAccountNumber = utilityAccountNumber;
    }

    public int getIsVerfication() {
        return IsVerfication;
    }

    public void setIsVerfication(int isVerfication) {
        IsVerfication = isVerfication;
    }

    public String getSessionCode() {
        return SessionCode;
    }

    public void setSessionCode(String sessionCode) {
        SessionCode = sessionCode;
    }

    public String getAlternateEmailID() {
        return AlternateEmailID;
    }

    public void setAlternateEmailID(String alternateEmailID) {
        AlternateEmailID = alternateEmailID;
    }

    public String getBPNumber() {
        return BPNumber;
    }

    public void setBPNumber(String bPNumber) {
        BPNumber = bPNumber;
    }

    public String getStreetNumber() {
        return StreetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        StreetNumber = streetNumber;
    }

    public String getDrivingLicence() {
        return DrivingLicence;
    }

    public void setDrivingLicence(String drivingLicence) {
        DrivingLicence = drivingLicence;
    }

    public int getIsCSRUser() {
        return IsCSRUser;
    }

    public void setIsCSRUser(int isCSRUser) {
        IsCSRUser = isCSRUser;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }

    public String getCustomerNo() {
        return CustomerNo;
    }

    public void setCustomerNo(String customerNo) {
        CustomerNo = customerNo;
    }

    public int getCustomerType() {
        return CustomerType;
    }

    public void setCustomerType(int customerType) {
        CustomerType = customerType;
    }

    public String getExternalLoginId() {
        return ExternalLoginId;
    }

    public void setExternalLoginId(String externalLoginId) {
        ExternalLoginId = externalLoginId;
    }

    public int getLoginMode() {
        return LoginMode;
    }

    public void setLoginMode(int loginMode) {
        LoginMode = loginMode;
    }

    public int getContactType() {
        return ContactType;
    }

    public void setContactType(int contactType) {
        ContactType = contactType;
    }

    public String getNotificationPreference() {
        return NotificationPreference;
    }

    public void setNotificationPreference(String notificationPreference) {
        NotificationPreference = notificationPreference;
    }

    public String getPaperlessBill() {
        return PaperlessBill;
    }

    public void setPaperlessBill(String paperlessBill) {
        PaperlessBill = paperlessBill;
    }

    public String getOSType() {
        return OSType;
    }

    public void setOSType(String oSType) {
        OSType = oSType;
    }

    public String getBrowser() {
        return Browser;
    }

    public void setBrowser(String browser) {
        Browser = browser;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
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

    public double getCaptchaScore() {
        return CaptchaScore;
    }

    public void setCaptchaScore(double captchaScore) {
        CaptchaScore = captchaScore;
    }

    public boolean isIsForgotUsername() {
        return IsForgotUsername;
    }

    public void setIsForgotUsername(boolean isForgotUsername) {
        IsForgotUsername = isForgotUsername;
    }

    public int getAuthenticationType() {
        return AuthenticationType;
    }

    public void setAuthenticationType(int authenticationType) {
        AuthenticationType = authenticationType;
    }

    public boolean isCreateAuthCode() {
        return CreateAuthCode;
    }

    public void setCreateAuthCode(boolean createAuthCode) {
        CreateAuthCode = createAuthCode;
    }

    public boolean isResendAuthCode() {
        return ResendAuthCode;
    }

    public void setResendAuthCode(boolean resendAuthCode) {
        ResendAuthCode = resendAuthCode;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public boolean isVerifyAuthCode() {
        return VerifyAuthCode;
    }

    public void setVerifyAuthCode(boolean verifyAuthCode) {
        VerifyAuthCode = verifyAuthCode;
    }
}

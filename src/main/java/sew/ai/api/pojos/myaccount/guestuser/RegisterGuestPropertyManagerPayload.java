package sew.ai.api.pojos.myaccount.guestuser;

import sew.ai.utils.RandomUtil;

public class RegisterGuestPropertyManagerPayload {
    private String Userid;
    private String Password;
    private String EmailID;
    private String MobileNumber;
    private String ActivationKey;
    private String CustomerType;
    private String ContactType;
    private String IPAddress;
    private String LanguageCode;
    private String UserName;
    private String FirstName;
    private String LastName;
    private boolean IsRegistration;
    private int PersonaId;

    public RegisterGuestPropertyManagerPayload(String emailID, String activationKey, String userName) {
        String random = RandomUtil.generateRandomString(3, RandomUtil.Mode.NUMERIC);
        Userid = "0";
        Password = "Demo@123";
        EmailID = emailID;
        MobileNumber = "2888888888";
        ActivationKey = activationKey;
        CustomerType = "2";
        ContactType = "2";
        IPAddress = "::1";
        LanguageCode = "EN";
        UserName = userName;
        FirstName = random + userName;
        LastName = random + "last";
        IsRegistration = false;
        PersonaId = 0;
    }

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmailID() {
        return EmailID;
    }

    public void setEmailID(String emailID) {
        EmailID = emailID;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getActivationKey() {
        return ActivationKey;
    }

    public void setActivationKey(String activationKey) {
        ActivationKey = activationKey;
    }

    public String getCustomerType() {
        return CustomerType;
    }

    public void setCustomerType(String customerType) {
        CustomerType = customerType;
    }

    public String getContactType() {
        return ContactType;
    }

    public void setContactType(String contactType) {
        ContactType = contactType;
    }

    public String getIPAddress() {
        return IPAddress;
    }

    public void setIPAddress(String iPAddress) {
        IPAddress = iPAddress;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public boolean isIsRegistration() {
        return IsRegistration;
    }

    public void setIsRegistration(boolean isRegistration) {
        IsRegistration = isRegistration;
    }

    public int getPersonaId() {
        return PersonaId;
    }

    public void setPersonaId(int personaId) {
        PersonaId = personaId;
    }
}

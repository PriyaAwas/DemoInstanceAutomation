package sew.ai.models;

import java.util.Arrays;

public class Customer {
    private String customerName;
    private String utilityAccountNumber;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String userName;
    private String password;
    private String zipCode;
    private String meterNumber;
    private String drivingLicenseNo;
    private String ssn;
    private String fidTin;
    private String contactType;
    private String phoneNumber;
    private String streetAddress;
    private int authenticationType;
    private Boolean createAuthCode;
    private Boolean resendAuthCode;
    private String token;
    private Boolean verifyAuthCode;
    private Customer[] customers;

    public Customer() {
    }

    public Customer(Customer[] customers) {
        this.customers = customers;
    }

    public Customer getCustomerByName(String customerName) {
        return Arrays.stream(customers).filter(customer -> customer.getCustomerName()
                .equalsIgnoreCase(customerName)).findFirst().get();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getUtilityAccountNumber() {
        return utilityAccountNumber;
    }

    public void setUtilityAccountNumber(String utilityAccountNumber) {
        this.utilityAccountNumber = utilityAccountNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        this.meterNumber = meterNumber;
    }

    public String getDrivingLicenseNo() {
        return drivingLicenseNo;
    }

    public void setDrivingLicenseNo(String drivingLicenseNo) {
        this.drivingLicenseNo = drivingLicenseNo;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getFidTin() {
        return fidTin;
    }

    public void setFidTin(String fidTin) {
        this.fidTin = fidTin;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public int getAuthenticationType() {
        return authenticationType;
    }

    public void setAuthenticationType(int authenticationType) {
        this.authenticationType = authenticationType;
    }

    public Boolean getCreateAuthCode() {
        return createAuthCode;
    }

    public void setCreateAuthCode(Boolean createAuthCode) {
        this.createAuthCode = createAuthCode;
    }

    public Boolean getResendAuthCode() {
        return resendAuthCode;
    }

    public void setResendAuthCode(Boolean resendAuthCode) {
        this.resendAuthCode = resendAuthCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getVerifyAuthCode() {
        return verifyAuthCode;
    }

    public void setVerifyAuthCode(Boolean verifyAuthCode) {
        this.verifyAuthCode = verifyAuthCode;
    }

    public Customer[] getCustomers() {
        return customers;
    }

    public void setCustomers(Customer[] customers) {
        this.customers = customers;
    }
}

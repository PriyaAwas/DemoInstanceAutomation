package sew.ai.api.pojos.billing.applepay;

import sew.ai.helpers.props.SQLQueries;
import sew.ai.models.AppleWallet;
import sew.ai.utils.DataBaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplePayPayload {
    private String serviceAccountNumber;
    private String bpNumber;
    private String accountNumber;
    private int userID;
    private String accounType;
    private String firstName;
    private String lastName;
    private String customerName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zip;
    private String countryCode;
    private String email;
    private String phone;
    private int paymentMethodType;
    private String customerRefNum;
    private String aPayCryptogram;
    private String aPayECommerceIndicator;
    private String languageCode;
    private String channeltype;
    
    public ApplePayPayload() {}
    
    public ApplePayPayload(String userName, AppleWallet appleWallet) {
        ResultSet resultSet = DataBaseUtils.getResultSet(SQLQueries.getUserAccountDetails(userName));
        try {
            resultSet.next();
            this.serviceAccountNumber = resultSet.getString("utilityaccountnumber");
            this.accountNumber = resultSet.getString("accountnumber");
            this.bpNumber = resultSet.getString("bpnumber");
            this.userID = resultSet.getInt("userid");
            this.email = resultSet.getString("emailid");
            this.phone = resultSet.getString("mobilephone");
            this.accounType = resultSet.getString("addresstype");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.firstName = appleWallet.getFirstName();
        this.lastName = appleWallet.getLastName();
        this.customerName = appleWallet.getCustomerName();
        this.addressLine1 = appleWallet.getAddressLine1();
        this.addressLine2 = appleWallet.getAddressLine2();
        this.city = appleWallet.getCity();
        this.state = appleWallet.getState();
        this.zip = appleWallet.getZip();
        this.countryCode = appleWallet.getCountryCode();
        this.paymentMethodType = 8;
        this.customerRefNum = appleWallet.getNonceToken();
        this.aPayCryptogram = appleWallet.getaPayCryptogram();
        this.aPayECommerceIndicator = appleWallet.getaPayECommerceIndicator();
        this.channeltype = "Web";
        this.languageCode = "EN";
    }

    public String getServiceAccountNumber() {
        return serviceAccountNumber;
    }

    public void setServiceAccountNumber(String serviceAccountNumber) {
        this.serviceAccountNumber = serviceAccountNumber;
    }

    public String getBpNumber() {
        return bpNumber;
    }

    public void setBpNumber(String bpNumber) {
        this.bpNumber = bpNumber;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getChanneltype() {
        return channeltype;
    }

    public void setChanneltype(String channeltype) {
        this.channeltype = channeltype;
    }

    public String getAccounType() {
        return accounType;
    }

    public void setAccounType(String accounType) {
        this.accounType = accounType;
    }

    public String getCustomerRefNum() {
        return customerRefNum;
    }

    public void setCustomerRefNum(String customerRefNum) {
        this.customerRefNum = customerRefNum;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPaymentMethodType() {
        return paymentMethodType;
    }

    public void setPaymentMethodType(int paymentMethodType) {
        this.paymentMethodType = paymentMethodType;
    }

    public String getaPayCryptogram() {
        return aPayCryptogram;
    }

    public void setaPayCryptogram(String aPayCryptogram) {
        this.aPayCryptogram = aPayCryptogram;
    }

    public String getaPayECommerceIndicator() {
        return aPayECommerceIndicator;
    }

    public void setaPayECommerceIndicator(String aPayECommerceIndicator) {
        this.aPayECommerceIndicator = aPayECommerceIndicator;
    }
}

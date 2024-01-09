package sew.ai.api.pojos.billing.gpay;

import sew.ai.helpers.props.SQLQueries;
import sew.ai.models.GPayWallet;
import sew.ai.utils.DataBaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GPayWalletPayload {
    private String serviceAccountNumber;
    private String bpNumber;
    private int userID;
    private String accountNumber;
    private String accounType;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private String customerName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zip;
    private String countryCode;
    private int paymentMethodType;
    private String customerRefNum;
    private String channeltype;
    private String languageCode;
    
    public GPayWalletPayload() {}

    public GPayWalletPayload(String userName, GPayWallet gPayWallet) {
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
        this.firstName = gPayWallet.getFirstName();
        this.lastName = gPayWallet.getLastName();
        this.customerName = gPayWallet.getCustomerName();
        this.addressLine1 = gPayWallet.getAddressLine1();
        this.addressLine2 = gPayWallet.getAddressLine2();
        this.city = gPayWallet.getCity();
        this.state = gPayWallet.getState();
        this.zip = gPayWallet.getZip();
        this.countryCode = gPayWallet.getCountryCode();
        this.paymentMethodType = 7;
        this.customerRefNum = gPayWallet.getNonceToken();
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccounType() {
        return accounType;
    }

    public void setAccounType(String accounType) {
        this.accounType = accounType;
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

    public int getPaymentMethodType() {
        return paymentMethodType;
    }

    public void setPaymentMethodType(int paymentMethodType) {
        this.paymentMethodType = paymentMethodType;
    }

    public String getCustomerRefNum() {
        return customerRefNum;
    }

    public void setCustomerRefNum(String customerRefNum) {
        this.customerRefNum = customerRefNum;
    }

    public String getChanneltype() {
        return channeltype;
    }

    public void setChanneltype(String channeltype) {
        this.channeltype = channeltype;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }
}

package sew.ai.api.pojos.billing.paypal;

import sew.ai.helpers.props.SQLQueries;
import sew.ai.models.Card;
import sew.ai.models.TokenizePayment;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.DateUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TokenizePaymentCard {
    private String serviceAccountNumber;
    private String bpNumber;
    private int userID;
    private String accountNumber;
    private String accountUtilityID;
    private String accounType;
    private String customerName;
    private String email;
    private String phone;
    private int vendorID;
    private String customerRefNum;
    private String amount;
    private String convenienceFee;
    private String orderId;
    private int paymentMethodType;
    private int paymentMethodSubType;
    private String firstName;
    private String lastName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zip;
    private String countryCode;
    private Boolean saveProfileAfterPayment;
    private Boolean isLoggedIn;
    private Boolean isAutoPay;
    private String companyCode;
    private String channeltype;
    private String languageCode;
    private String ip;
    private String requestTime;

    public TokenizePaymentCard() {}

    public TokenizePaymentCard(String userName, Card card, TokenizePayment tokenizePaymentObj) {
        ResultSet resultSet = DataBaseUtils.getResultSet(SQLQueries.getUserAccountDetails(userName));
        try {
            resultSet.next();
            this.customerName = resultSet.getString("lastname");
            this.bpNumber = resultSet.getString("bpnumber");
            this.serviceAccountNumber = resultSet.getString("utilityaccountnumber");
            this.accountNumber = resultSet.getString("accountnumber");
            this.userID = resultSet.getInt("userid");
            this.email = resultSet.getString("emailid");
            this.phone = resultSet.getString("mobilephone");
            this.accounType = resultSet.getString("addresstype");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.accountUtilityID = "1";
        this.vendorID = 0;
        this.paymentMethodType = 1;
        this.paymentMethodSubType = 0;
        this.amount = String.valueOf(tokenizePaymentObj.getAmount());
        this.customerRefNum = tokenizePaymentObj.getToken();
        this.isAutoPay = tokenizePaymentObj.getIsAutoPay();
        this.isLoggedIn = tokenizePaymentObj.getIsLoggedIn();
        this.saveProfileAfterPayment = tokenizePaymentObj.getSaveProfileAfterPayment();
        this.firstName = card.getFirstName();
        this.lastName = card.getLastName();
        this.addressLine1 = card.getAddress();
        this.addressLine2 = "";
        this.city = card.getCity();
        this.state = card.getState();
        this.zip = card.getZipCode();
        this.state = card.getState();
        this.countryCode = "US";
        this.channeltype = "Web";
        this.orderId = "";
        this.ip = "";
        this.languageCode = "EN";
        this.requestTime = DateUtil.getCurrentDateInFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

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

    public String getCustomerRefNum() {
        return customerRefNum;
    }

    public void setCustomerRefNum(String customerRefNum) {
        this.customerRefNum = customerRefNum;
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

    public String getAccountUtilityID() {
        return accountUtilityID;
    }

    public void setAccountUtilityID(String accountUtilityID) {
        this.accountUtilityID = accountUtilityID;
    }

    public String getAccounType() {
        return accounType;
    }

    public void setAccounType(String accounType) {
        this.accounType = accounType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public int getVendorID() {
        return vendorID;
    }

    public void setVendorID(int vendorID) {
        this.vendorID = vendorID;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getConvenienceFee() {
        return convenienceFee;
    }

    public void setConvenienceFee(String convenienceFee) {
        this.convenienceFee = convenienceFee;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getPaymentMethodType() {
        return paymentMethodType;
    }

    public void setPaymentMethodType(int paymentMethodType) {
        this.paymentMethodType = paymentMethodType;
    }

    public int getPaymentMethodSubType() {
        return paymentMethodSubType;
    }

    public void setPaymentMethodSubType(int paymentMethodSubType) {
        this.paymentMethodSubType = paymentMethodSubType;
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

    public Boolean getSaveProfileAfterPayment() {
        return saveProfileAfterPayment;
    }

    public void setSaveProfileAfterPayment(Boolean saveProfileAfterPayment) {
        this.saveProfileAfterPayment = saveProfileAfterPayment;
    }

    public Boolean getLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public Boolean getAutoPay() {
        return isAutoPay;
    }

    public void setAutoPay(Boolean autoPay) {
        isAutoPay = autoPay;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }
}

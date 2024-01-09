package sew.ai.api.pojos.billing.paypal;

import sew.ai.helpers.props.SQLQueries;
import sew.ai.models.PayPalWallet;
import sew.ai.models.TokenizePayment;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.DateUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TokenizePaymentWallet {
    private String customerName;
    private int userID;
    private String bpNumber;
    private String serviceAccountNumber;
    private String accountNumber;
    private String accountUtilityID;
    private String accounType;
    private String email;
    private String phone;
    private int vendorID;
    private int paymentMethodType;
    private String amount;
    private String customerRefNum;
    private Boolean isLoggedIn;
    private Boolean isAutoPay;
    private Boolean saveProfileAfterPayment;
    private String ccAccountNum;
    private String bankAccountNum;
    private String routingNumber;
    private String bankName;
    private int achType;
    private String firstName;
    private String lastName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zip;
    private String countryCode;
    private String retryTrace;
    private String orderId;
    private String channeltype;
    private String companyCode;
    private String requestTime;
    private String languageCode;
    private String ip;

    public TokenizePaymentWallet() {
    }

    public TokenizePaymentWallet(String userName, PayPalWallet payPalWallet, TokenizePayment tokenizePaymentObj) {
        ResultSet resultSet = DataBaseUtils.getResultSet(SQLQueries.getUserAccountDetails(userName));
        try {
            resultSet.next();
            this.customerName = resultSet.getString("fullname");
            this.bpNumber = resultSet.getString("bpnumber");
            this.userID = resultSet.getInt("userid");
            this.serviceAccountNumber = resultSet.getString("utilityaccountnumber");
            this.accountNumber = resultSet.getString("accountnumber");
            this.email = resultSet.getString("emailid");
            this.phone = resultSet.getString("mobilephone");
            this.accounType = resultSet.getString("addresstype");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.accountUtilityID = "1";
        this.vendorID = 0;
        this.paymentMethodType = 5;
        this.amount = String.valueOf(tokenizePaymentObj.getAmount());
        this.customerRefNum = tokenizePaymentObj.getToken();
        this.isAutoPay = tokenizePaymentObj.getIsAutoPay();
        this.isLoggedIn = tokenizePaymentObj.getIsLoggedIn();
        this.saveProfileAfterPayment = tokenizePaymentObj.getSaveProfileAfterPayment();
        this.ccAccountNum = "";
        this.bankAccountNum = "";
        this.routingNumber = "";
        this.bankName = "";
        this.achType = 1;
        this.firstName = payPalWallet.getFirstName();
        this.lastName = payPalWallet.getLastName();
        this.addressLine1 = payPalWallet.getAddressLine1();
        this.addressLine2 = payPalWallet.getAddressLine2();
        this.city = payPalWallet.getCity();
        this.state = payPalWallet.getState();
        this.zip = payPalWallet.getZip();
        this.state = payPalWallet.getState();
        this.countryCode = "US";
        this.channeltype = "Web";
        this.orderId = "";
        this.retryTrace = "";
        this.companyCode = "";
        this.ip = "";
        this.languageCode = "EN";
        this.requestTime = DateUtil.getCurrentDateInFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getBpNumber() {
        return bpNumber;
    }

    public void setBpNumber(String bpNumber) {
        this.bpNumber = bpNumber;
    }

    public String getServiceAccountNumber() {
        return serviceAccountNumber;
    }

    public void setServiceAccountNumber(String serviceAccountNumber) {
        this.serviceAccountNumber = serviceAccountNumber;
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

    public int getPaymentMethodType() {
        return paymentMethodType;
    }

    public void setPaymentMethodType(int paymentMethodType) {
        this.paymentMethodType = paymentMethodType;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCustomerRefNum() {
        return customerRefNum;
    }

    public void setCustomerRefNum(String customerRefNum) {
        this.customerRefNum = customerRefNum;
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

    public Boolean getSaveProfileAfterPayment() {
        return saveProfileAfterPayment;
    }

    public void setSaveProfileAfterPayment(Boolean saveProfileAfterPayment) {
        this.saveProfileAfterPayment = saveProfileAfterPayment;
    }

    public String getCcAccountNum() {
        return ccAccountNum;
    }

    public void setCcAccountNum(String ccAccountNum) {
        this.ccAccountNum = ccAccountNum;
    }

    public String getBankAccountNum() {
        return bankAccountNum;
    }

    public void setBankAccountNum(String bankAccountNum) {
        this.bankAccountNum = bankAccountNum;
    }

    public String getRoutingNumber() {
        return routingNumber;
    }

    public void setRoutingNumber(String routingNumber) {
        this.routingNumber = routingNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public int getAchType() {
        return achType;
    }

    public void setAchType(int achType) {
        this.achType = achType;
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

    public String getRetryTrace() {
        return retryTrace;
    }

    public void setRetryTrace(String retryTrace) {
        this.retryTrace = retryTrace;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getChanneltype() {
        return channeltype;
    }

    public void setChanneltype(String channeltype) {
        this.channeltype = channeltype;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
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
}

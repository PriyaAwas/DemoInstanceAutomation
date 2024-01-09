package sew.ai.api.pojos.billing.paypal;

import sew.ai.helpers.props.SQLQueries;
import sew.ai.models.OneTimePayment;
import sew.ai.models.PayPalWallet;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.DateUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OneTimePaypalWallet {
    private String serviceAccountNumber;
    private String BPNumber;
    private int userID;
    private String accountNumber;
    private int accounType;
    private String accountUtilityID;
    private String email;
    private String phone;
    private String customerName;
    private String amount;
    private String ConvenienceFee;
    private String customerRefNum;
    private int paymentMethodType;
    private String firstName;
    private String lastName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zip;
    private String countryCode;
    private boolean isLoggedIn;
    private boolean isAutoPay;
    private boolean saveProfileAfterPayment;
    private String retryTrace;
    private String channeltype;
    private String ip;
    private String languageCode;
    private String requestTime;

    public OneTimePaypalWallet() {
    }

    public OneTimePaypalWallet(String userName, PayPalWallet paypalWallet, OneTimePayment oneTimePaymentObj) {
        ResultSet resultSet = DataBaseUtils.getResultSet(SQLQueries.getUserAccountDetails(userName));
        try {
            resultSet.next();
            this.serviceAccountNumber = resultSet.getString("utilityaccountnumber");
            this.BPNumber = resultSet.getString("bpnumber");
            this.customerName = resultSet.getString("fullname");
            this.accountNumber = resultSet.getString("accountnumber");
            this.userID = resultSet.getInt("userid");
            this.email = resultSet.getString("emailid");
            this.phone = resultSet.getString("mobilephone");
            this.accounType = resultSet.getInt("addresstypeid");
            this.accountUtilityID = "9383";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.paymentMethodType = 5;
        this.customerRefNum = paypalWallet.getNonceToken();
        this.firstName = paypalWallet.getFirstName();
        this.lastName = paypalWallet.getLastName();
        this.addressLine1 = paypalWallet.getAddressLine1();
        this.addressLine2 = paypalWallet.getAddressLine2();
        this.city = paypalWallet.getCity();
        this.state = paypalWallet.getState();
        this.zip = paypalWallet.getZip();
        this.state = paypalWallet.getState();
        this.countryCode = "US";
        this.channeltype = "Web";
        this.amount = String.valueOf(oneTimePaymentObj.getAmount());
        this.ConvenienceFee = String.valueOf(oneTimePaymentObj.getConvenienceFee());
        this.isLoggedIn = oneTimePaymentObj.getIsLoggedIn();
        this.isAutoPay = oneTimePaymentObj.getIsAutoPay();
        this.saveProfileAfterPayment = oneTimePaymentObj.getSaveProfileAfterPayment();
        this.requestTime = DateUtil.getCurrentDateInFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        this.retryTrace = "";
        this.ip = "";
        this.languageCode = "EN";
    }

    public String getServiceAccountNumber() {
        return serviceAccountNumber;
    }

    public void setServiceAccountNumber(String serviceAccountNumber) {
        this.serviceAccountNumber = serviceAccountNumber;
    }

    public String getBPNumber() {
        return BPNumber;
    }

    public void setBPNumber(String BPNumber) {
        this.BPNumber = BPNumber;
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

    public int getAccounType() {
        return accounType;
    }

    public void setAccounType(int accounType) {
        this.accounType = accounType;
    }

    public String getAccountUtilityID() {
        return accountUtilityID;
    }

    public void setAccountUtilityID(String accountUtilityID) {
        this.accountUtilityID = accountUtilityID;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getConvenienceFee() {
        return ConvenienceFee;
    }

    public void setConvenienceFee(String convenienceFee) {
        ConvenienceFee = convenienceFee;
    }

    public String getCustomerRefNum() {
        return customerRefNum;
    }

    public void setCustomerRefNum(String customerRefNum) {
        this.customerRefNum = customerRefNum;
    }

    public int getPaymentMethodType() {
        return paymentMethodType;
    }

    public void setPaymentMethodType(int paymentMethodType) {
        this.paymentMethodType = paymentMethodType;
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

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public boolean isAutoPay() {
        return isAutoPay;
    }

    public void setAutoPay(boolean autoPay) {
        isAutoPay = autoPay;
    }

    public boolean isSaveProfileAfterPayment() {
        return saveProfileAfterPayment;
    }

    public void setSaveProfileAfterPayment(boolean saveProfileAfterPayment) {
        this.saveProfileAfterPayment = saveProfileAfterPayment;
    }

    public String getRetryTrace() {
        return retryTrace;
    }

    public void setRetryTrace(String retryTrace) {
        this.retryTrace = retryTrace;
    }

    public String getChanneltype() {
        return channeltype;
    }

    public void setChanneltype(String channeltype) {
        this.channeltype = channeltype;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }
}

package sew.ai.api.pojos.billing.venmo;

import sew.ai.helpers.props.SQLQueries;
import sew.ai.models.OneTimePayment;
import sew.ai.models.VenmoWallet;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.DateUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OneTimePaymentVenmo {
    private String serviceAccountNumber;
    private String bpNumber;
    private int userID;
    private int accounType;
    private String accountNumber;
    private String customerName;
    private String email;
    private String phone;
    private String customerRefNum;
    private String amount;
    private String ConvenienceFee;
    private int paymentMethodType;
    private String firstName;
    private String lastName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zip;
    private String countryCode;
    private String orderId;
    private Boolean isLoggedIn;
    private Boolean isAutoPay;
    private Boolean saveProfileAfterPayment;
    private String retryTrace;
    private String languageCode;
    private String ip;
    private String channeltype;
    private String requestTime;

    public OneTimePaymentVenmo() {
    }

    public OneTimePaymentVenmo(String userName, VenmoWallet venmoWallet, OneTimePayment oneTimePaymentObj) {
        ResultSet resultSet = DataBaseUtils.getResultSet(SQLQueries.getUserAccountDetails(userName));
        try {
            resultSet.next();
            this.serviceAccountNumber = resultSet.getString("utilityaccountnumber");
            this.bpNumber = resultSet.getString("bpnumber");
            this.customerName = resultSet.getString("fullname");
            this.accountNumber = resultSet.getString("accountnumber");
            this.userID = resultSet.getInt("userid");
            this.email = resultSet.getString("emailid");
            this.phone = resultSet.getString("mobilephone");
            this.accounType = resultSet.getInt("addresstypeid");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.paymentMethodType = 7;
        this.customerRefNum = venmoWallet.getNonceToken();
        this.firstName = venmoWallet.getFirstName();
        this.lastName = venmoWallet.getLastName();
        this.addressLine1 = venmoWallet.getAddressLine1();
        this.addressLine2 = venmoWallet.getAddressLine2();
        this.city = venmoWallet.getCity();
        this.state = venmoWallet.getState();
        this.zip = venmoWallet.getZip();
        this.state = venmoWallet.getState();
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

    public int getAccounType() {
        return accounType;
    }

    public void setAccounType(int accounType) {
        this.accounType = accounType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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

    public String getCustomerRefNum() {
        return customerRefNum;
    }

    public void setCustomerRefNum(String customerRefNum) {
        this.customerRefNum = customerRefNum;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public String getRetryTrace() {
        return retryTrace;
    }

    public void setRetryTrace(String retryTrace) {
        this.retryTrace = retryTrace;
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

    public String getChanneltype() {
        return channeltype;
    }

    public void setChanneltype(String channeltype) {
        this.channeltype = channeltype;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }
}

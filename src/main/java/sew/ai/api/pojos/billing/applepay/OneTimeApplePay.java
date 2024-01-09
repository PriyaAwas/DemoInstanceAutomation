package sew.ai.api.pojos.billing.applepay;

import sew.ai.helpers.props.SQLQueries;
import sew.ai.models.AppleWallet;
import sew.ai.models.OneTimePayment;
import sew.ai.utils.DataBaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OneTimeApplePay {
    private String serviceAccountNumber;
    private String BPNumber;
    private int userID;
    private String accountNumber;
    private int accounType;
    private String accountUtilityID;
    private String customerName;
    private String email;
    private String phone;
    private int paymentMethodType;
    private String customerRefNum;
    private String aPayCryptogram;
    private String aPayECommerceIndicator;
    private String amount;
    private String ConvenienceFee;
    private String firstName;
    private String lastName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zip;
    private String countryCode;
    private Boolean isLoggedIn;
    private Boolean isAutoPay;
    private Boolean saveProfileAfterPayment;
    private String channeltype;
    private String languageCode;
    
    public OneTimeApplePay() {}

    public OneTimeApplePay(String userName, AppleWallet appleWallet, OneTimePayment oneTimePaymentObj) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.paymentMethodType = 8;
        this.customerRefNum = appleWallet.getNonceToken();
        this.aPayCryptogram = appleWallet.getaPayCryptogram();
        this.aPayECommerceIndicator = appleWallet.getaPayECommerceIndicator();
        this.firstName = appleWallet.getFirstName();
        this.lastName = appleWallet.getLastName();
        this.addressLine1 = appleWallet.getAddressLine1();
        this.addressLine2 = appleWallet.getAddressLine2();
        this.city = appleWallet.getCity();
        this.state = appleWallet.getState();
        this.zip = appleWallet.getZip();
        this.state = appleWallet.getState();
        this.countryCode = "US";
        this.channeltype = "Web";
        this.amount = String.valueOf(oneTimePaymentObj.getAmount());
        this.ConvenienceFee = String.valueOf(oneTimePaymentObj.getConvenienceFee());
        this.isLoggedIn = oneTimePaymentObj.getIsLoggedIn();
        this.isAutoPay = oneTimePaymentObj.getIsAutoPay();
        this.saveProfileAfterPayment = oneTimePaymentObj.getSaveProfileAfterPayment();
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

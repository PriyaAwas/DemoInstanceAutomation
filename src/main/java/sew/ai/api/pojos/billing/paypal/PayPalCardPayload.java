package sew.ai.api.pojos.billing.paypal;

import sew.ai.helpers.props.SQLQueries;
import sew.ai.models.Card;
import sew.ai.utils.DataBaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PayPalCardPayload {
    String serviceAccountNumber;
    String bpNumber;
    String accountNumber;
    String email;
    String phone;
    int userID;
    int vendorID;
    int accounType;
    String ccAccountNum;
    String ccExp;
    String cardCVV;
    String firstName;
    String lastName;
    String customerName;
    String addressLine1;
    String addressLine2;
    String city;
    String state;
    String zip;
    String countryCode;
    int paymentMethodSubType;
    String customerRefNum;
    String channeltype;
    String companyCode;
    String ip;
    String languageCode;

    public PayPalCardPayload() {}

    public PayPalCardPayload(String userName, Card card) {
        ResultSet resultSet = DataBaseUtils.getResultSet(SQLQueries.getUserAccountDetails(userName));
        try {
            resultSet.next();
            this.serviceAccountNumber = resultSet.getString("utilityaccountnumber");
            this.accountNumber = resultSet.getString("accountnumber");
            this.bpNumber = resultSet.getString("bpnumber");
            this.userID = resultSet.getInt("userid");
            this.email = resultSet.getString("emailid");
            this.phone = resultSet.getString("mobilephone");
            this.accounType = resultSet.getInt("addresstypeid");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.vendorID = 0;
        this.ccAccountNum = card.getCardNumber();
        this.ccExp = card.getExpiry();
        this.cardCVV = card.getCvv();
        this.customerName = card.getFirstName() + " " + card.getLastName();
        this.firstName = card.getFirstName();
        this.lastName = card.getLastName();
        this.addressLine1 = card.getAddress();
        this.addressLine2 = "";
        this.city = card.getCity();
        this.state = card.getState();
        this.zip = card.getZipCode();
        this.countryCode = "US";
        this.paymentMethodSubType = 4;
        this.customerRefNum = null;
        this.channeltype = "WEB";
        this.companyCode = "";
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getVendorID() {
        return vendorID;
    }

    public void setVendorID(int vendorID) {
        this.vendorID = vendorID;
    }

    public int getAccounType() {
        return accounType;
    }

    public void setAccounType(int accounType) {
        this.accounType = accounType;
    }

    public String getCcAccountNum() {
        return ccAccountNum;
    }

    public void setCcAccountNum(String ccAccountNum) {
        this.ccAccountNum = ccAccountNum;
    }

    public String getCcExp() {
        return ccExp;
    }

    public void setCcExp(String ccExp) {
        this.ccExp = ccExp;
    }

    public String getCardCVV() {
        return cardCVV;
    }

    public void setCardCVV(String cardCVV) {
        this.cardCVV = cardCVV;
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

    public int getPaymentMethodSubType() {
        return paymentMethodSubType;
    }

    public void setPaymentMethodSubType(int paymentMethodSubType) {
        this.paymentMethodSubType = paymentMethodSubType;
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

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
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
}

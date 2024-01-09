package sew.ai.api.pojos.billing.paypal;

import sew.ai.helpers.props.SQLQueries;
import sew.ai.models.Bank;
import sew.ai.utils.DataBaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PayPalBankPayload {
    String serviceAccountNumber;
    String bpNumber;
    String accountNumber;
    int userID;
    String email;
    String phone;
    String accounType;
    String customerName;
    String routingNumber;
    String bankAccountNum;
    String bankName;
    String addressLine1;
    String addressLine2;
    String firstName;
    String lastName;
    String countryCode;
    String state;
    String city;
    String zip;
    int achType;
    int ownershipType;
    String channeltype;
    String languageCode;
    String personalFirstName;
    String personalLastname;
    String businessName;

    public PayPalBankPayload() {}

    public PayPalBankPayload(String userName, Bank bank) {
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
        this.customerName = bank.getFirstName() + " " + bank.getLastName();
        this.routingNumber = bank.getRoutingNumber();
        this.bankAccountNum = bank.getBankAccountNumber();
        this.bankName = bank.getBankName();
        this.achType = 2;
        this.firstName = bank.getFirstName();
        this.lastName = bank.getLastName();
        this.city = bank.getCity();
        this.state = bank.getState();
        this.zip = bank.getZipCode();
        this.addressLine1 = bank.getAddress();
        this.addressLine2 = "";
        this.countryCode = "US";
        this.channeltype = "WEB";
        this.languageCode = "EN";
        this.personalFirstName = bank.getFirstName();
        this.personalLastname = bank.getLastName();
        this.businessName = "";
        this.ownershipType = 1;
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    public String getRoutingNumber() {
        return routingNumber;
    }

    public void setRoutingNumber(String routingNumber) {
        this.routingNumber = routingNumber;
    }

    public String getBankAccountNum() {
        return bankAccountNum;
    }

    public void setBankAccountNum(String bankAccountNum) {
        this.bankAccountNum = bankAccountNum;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
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

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public int getAchType() {
        return achType;
    }

    public void setAchType(int achType) {
        this.achType = achType;
    }

    public int getOwnershipType() {
        return ownershipType;
    }

    public void setOwnershipType(int ownershipType) {
        this.ownershipType = ownershipType;
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

    public String getPersonalFirstName() {
        return personalFirstName;
    }

    public void setPersonalFirstName(String personalFirstName) {
        this.personalFirstName = personalFirstName;
    }

    public String getPersonalLastname() {
        return personalLastname;
    }

    public void setPersonalLastname(String personalLastname) {
        this.personalLastname = personalLastname;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
}

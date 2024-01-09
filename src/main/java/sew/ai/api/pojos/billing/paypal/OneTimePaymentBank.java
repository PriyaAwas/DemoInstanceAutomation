package sew.ai.api.pojos.billing.paypal;

import sew.ai.helpers.props.SQLQueries;
import sew.ai.models.Bank;
import sew.ai.models.OneTimePayment;
import sew.ai.utils.DataBaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OneTimePaymentBank {
    private String customerName;
    private String AccountNumber;
    private String BPNumber;
    private int UserID;
    private String email;
    private String phone;
    private String AccounType;
    private int ownershipType;
    private String businessName;
    private String BankAccountNum;
    private String RoutingNumber;
    private String BankName;
    private int achType;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zip;
    private String countryCode;
    private String Amount;
    private String ConvenienceFee;
    private String AccountUtilityId;
    private boolean isLoggedIn;
    private boolean saveProfileAfterPayment;
    private boolean setDefaultPayment;
    private String ChannelType;

    public OneTimePaymentBank() {
    }

    public OneTimePaymentBank(String userName, Bank bank, OneTimePayment oneTimePaymentObj) {
        ResultSet resultSet = DataBaseUtils.getResultSet(SQLQueries.getUserAccountDetails(userName));
        try {
            resultSet.next();
            this.customerName = resultSet.getString("lastname");
            this.BPNumber = resultSet.getString("bpnumber");
            this.AccountNumber = resultSet.getString("utilityaccountnumber");
            this.UserID = resultSet.getInt("userid");
            this.email = resultSet.getString("emailid");
            this.phone = resultSet.getString("mobilephone");
            this.AccounType = resultSet.getString("addresstype");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.ownershipType = 2;
        this.businessName = bank.getAccountHolderName();
        this.BankAccountNum = bank.getBankAccountNumber();
        this.RoutingNumber = bank.getRoutingNumber();
        this.BankName = bank.getBankName();
        this.achType = 1;
        this.addressLine1 = bank.getAddress();
        this.addressLine2 = "";
        this.city = bank.getCity();
        this.state = bank.getState();
        this.zip = bank.getZipCode();
        this.countryCode = "US";
        this.Amount = String.valueOf(oneTimePaymentObj.getAmount());
        this.ConvenienceFee = String.valueOf(oneTimePaymentObj.getConvenienceFee());
        this.AccountUtilityId = "1";
        this.ChannelType = "WEB";
        this.isLoggedIn = oneTimePaymentObj.getIsLoggedIn();
        this.setDefaultPayment = oneTimePaymentObj.isSetDefaultPayment();
        this.saveProfileAfterPayment = oneTimePaymentObj.getSaveProfileAfterPayment();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }

    public String getBPNumber() {
        return BPNumber;
    }

    public void setBPNumber(String BPNumber) {
        this.BPNumber = BPNumber;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
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
        return AccounType;
    }

    public void setAccounType(String accounType) {
        AccounType = accounType;
    }

    public int getOwnershipType() {
        return ownershipType;
    }

    public void setOwnershipType(int ownershipType) {
        this.ownershipType = ownershipType;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBankAccountNum() {
        return BankAccountNum;
    }

    public void setBankAccountNum(String bankAccountNum) {
        BankAccountNum = bankAccountNum;
    }

    public String getRoutingNumber() {
        return RoutingNumber;
    }

    public void setRoutingNumber(String routingNumber) {
        RoutingNumber = routingNumber;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public int getAchType() {
        return achType;
    }

    public void setAchType(int achType) {
        this.achType = achType;
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

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getConvenienceFee() {
        return ConvenienceFee;
    }

    public void setConvenienceFee(String convenienceFee) {
        ConvenienceFee = convenienceFee;
    }

    public String getAccountUtilityId() {
        return AccountUtilityId;
    }

    public void setAccountUtilityId(String accountUtilityId) {
        AccountUtilityId = accountUtilityId;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public boolean isSaveProfileAfterPayment() {
        return saveProfileAfterPayment;
    }

    public void setSaveProfileAfterPayment(boolean saveProfileAfterPayment) {
        this.saveProfileAfterPayment = saveProfileAfterPayment;
    }

    public boolean isSetDefaultPayment() {
        return setDefaultPayment;
    }

    public void setSetDefaultPayment(boolean setDefaultPayment) {
        this.setDefaultPayment = setDefaultPayment;
    }

    public String getChannelType() {
        return ChannelType;
    }

    public void setChannelType(String channelType) {
        ChannelType = channelType;
    }
}

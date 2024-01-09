package sew.ai.api.pojos.billing.paypal;

import sew.ai.helpers.props.SQLQueries;
import sew.ai.models.Card;
import sew.ai.models.OneTimePayment;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.DateUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OneTimePaymentCard {
    private String CustomerName;
    private int UserID;
    private String AccountNumber;
    private String email;
    private String phone;
    private String AccounType;
    private String ccAccountNum;
    private String ccExp;
    private String cardCVV;
    private String FirstName;
    private String LastName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zip;
    private String countryCode;
    private String ChannelType;
    private String Amount;
    private String ConvenienceFee;
    private String AccountUtilityId;
    private Boolean isLoggedIn;
    private Boolean isAutoPay;
    private Boolean saveProfileAfterPayment;
    private Boolean isDuplicatePayment;
    private String requestTime;

    public OneTimePaymentCard() {}

    public OneTimePaymentCard(String userName, Card card, OneTimePayment oneTimePaymentObj) {
        ResultSet resultSet = DataBaseUtils.getResultSet(SQLQueries.getUserAccountDetails(userName));
        try {
            resultSet.next();
            this.CustomerName = resultSet.getString("lastname");
            this.AccountNumber = resultSet.getString("utilityaccountnumber");
            this.UserID = resultSet.getInt("userid");
            this.email = resultSet.getString("emailid");
            this.phone = resultSet.getString("mobilephone");
            this.AccounType = resultSet.getString("addresstype");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.ccAccountNum = card.getCardNumber();
        this.ccExp = card.getExpiry();
        this.cardCVV = card.getCvv();
        this.FirstName = card.getFirstName();
        this.LastName = card.getLastName();
        this.addressLine1 = card.getAddress();
        this.addressLine2 = "";
        this.city = card.getCity();
        this.state = card.getState();
        this.zip = card.getZipCode();
        this.state = card.getState();
        this.countryCode = "US";
        this.ChannelType = "Web";
        this.Amount = String.valueOf(oneTimePaymentObj.getAmount());
        this.ConvenienceFee = String.valueOf(oneTimePaymentObj.getConvenienceFee());
        this.AccountUtilityId = "1";
        this.isLoggedIn = oneTimePaymentObj.getIsLoggedIn();
        this.isAutoPay = oneTimePaymentObj.getIsAutoPay();
        this.saveProfileAfterPayment = oneTimePaymentObj.getSaveProfileAfterPayment();
        this.isDuplicatePayment = oneTimePaymentObj.getIsDuplicatePayment();
        this.requestTime = DateUtil.getCurrentDateInFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
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
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
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

    public String getChannelType() {
        return ChannelType;
    }

    public void setChannelType(String channelType) {
        ChannelType = channelType;
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

    public Boolean getDuplicatePayment() {
        return isDuplicatePayment;
    }

    public void setDuplicatePayment(Boolean duplicatePayment) {
        isDuplicatePayment = duplicatePayment;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }
}

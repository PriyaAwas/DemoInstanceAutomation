package sew.ai.models;

import sew.ai.helpers.props.SQLQueries;
import sew.ai.utils.DataBaseUtils;

import java.sql.ResultSet;

public class User {
    private String userId;
    private String userName;
    private String password;
    private String status;
    private String emailId;
    private String alternateEmailID;
    private String mobilePhone;
    private String mobilePhoneType;
    private String homePhone;
    private String HomePhoneType;
    private String firstName;
    private String middleName;
    private String lastName;
    private String fullName;
    private String cityName;
    private String stateName;
    private String zipCode;
    private String accountNumber;
    private String defaultUtilityAccNum;
    private String customerNum;
    private String utilityId;
    private String loginToken;
    private String jwtToken;
    private User[] users;

    public User(User[] users) {
        this.users = users;
    }

    public User() {
    }

    public User(String userName, String password) {
        ResultSet resultSet = DataBaseUtils.getResultSet(SQLQueries.getUserInfo(userName));
        try {
            resultSet.next();
            setUserId(resultSet.getString("userid"));
            setCustomerNum(resultSet.getString("customerno"));
            setAccountNumber(resultSet.getString("accountnumber"));
            setDefaultUtilityAccNum(resultSet.getString("utilityaccountnumber"));
            setUtilityId(resultSet.getString("utilityid"));
            setUserName(userName);
            setPassword(password);
            setEmailId(resultSet.getString("emailid"));
            setAlternateEmailID(resultSet.getString("alternateemailID"));
            setMobilePhone(resultSet.getString("mobilephone"));
            setMobilePhoneType(resultSet.getString("mobilePhoneType"));
            setHomePhone(resultSet.getString("homephone"));
            setHomePhoneType(resultSet.getString("HomePhoneType"));
            setStatus(resultSet.getString("status"));
            setFirstName(resultSet.getString("firstname"));
            setMiddleName(resultSet.getString("middlename"));
            setLastName(resultSet.getString("lastname"));
            setFullName(resultSet.getString("fullname"));
            setCityName(resultSet.getString("cityname"));
            setStateName(resultSet.getString("statename"));
            setZipCode(resultSet.getString("zipcode"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAlternateEmailID() {
        return alternateEmailID;
    }

    public void setAlternateEmailID(String alternateEmailID) {
        this.alternateEmailID = alternateEmailID;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getMobilePhoneType() {
        return mobilePhoneType;
    }

    public void setMobilePhoneType(String mobilePhoneType) {
        this.mobilePhoneType = mobilePhoneType;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getHomePhoneType() {
        return HomePhoneType;
    }

    public void setHomePhoneType(String homePhoneType) {
        HomePhoneType = homePhoneType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getDefaultUtilityAccNum() {
        return defaultUtilityAccNum;
    }

    public void setDefaultUtilityAccNum(String defaultUtilityAccNum) {
        this.defaultUtilityAccNum = defaultUtilityAccNum;
    }

    public String getUtilityId() {
        return utilityId;
    }

    public void setUtilityId(String utilityId) {
        this.utilityId = utilityId;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }
}

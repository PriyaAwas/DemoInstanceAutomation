package sew.ai.models;

import sew.ai.helpers.props.SQLQueries;
import sew.ai.utils.DataBaseUtils;

import java.sql.ResultSet;
import java.util.Arrays;

public class UserAccount {
    private String userId;
    private String addressId;
    private String accountNumber;
    private String roleId;
    private String isDefaultAccNum;
    private String utilityAccNum;
    private String bpNumber;
    private String customerId;
    private String isPaperlessOpted;
    private String isEmailNotifyOpted;
    private String serviceAddress;
    private String mailingAddress;
    private UserAccount[] userAccounts;

    public UserAccount() {
    }

    public UserAccount(String userName) {
        ResultSet resultSet, resultSet1;
        int count = 0;
        try {
            resultSet = DataBaseUtils.getResultSet(SQLQueries.getAccountLinkedToUser(userName));
            resultSet.next();
            userAccounts = new UserAccount[resultSet.getInt("accountlinked")];
            resultSet = DataBaseUtils.getResultSet(SQLQueries.getUserAccountInfo(userName));
            while (resultSet.next()) {
                userAccounts[count] = new UserAccount();
                userAccounts[count].setUserId(resultSet.getString("userid"));
                userAccounts[count].setAddressId(resultSet.getString("addressid"));
                userAccounts[count].setAccountNumber(resultSet.getString("accountnumber"));
                userAccounts[count].setUtilityAccNum(resultSet.getString("utilityaccountnumber"));
                userAccounts[count].setBpNumber(resultSet.getString("bpnumber"));
                userAccounts[count].setCustomerId(resultSet.getString("customerid"));
                userAccounts[count].setRoleId(resultSet.getString("roleid"));
                userAccounts[count].setIsDefaultAccNum(resultSet.getString("isdefaultaccount"));
                userAccounts[count].setIsPaperlessOpted(resultSet.getString("paperless"));
                userAccounts[count].setIsEmailNotifyOpted(resultSet.getString("emailnotify"));
                String service = resultSet.getString("address1") + ", ";
                if (!resultSet.getString("address2").equals(""))
                    service = service + resultSet.getString("address2") + ", ";
                service = service + resultSet.getString("cityname") + ", ";
                service = service + resultSet.getString("zipcode");
                userAccounts[count].setServiceAddress(service);
                resultSet1 = DataBaseUtils.getResultSet(SQLQueries.getCurrentMailingAddressInfo(
                        userAccounts[count].getAddressId(),
                        userAccounts[count].getUserId())
                );
                resultSet1.next();
                String mailing = resultSet1.getString("address1") + ", ";
                if (!resultSet.getString("address2").equals(""))
                    mailing = mailing + resultSet.getString("address2") + ", ";
                mailing = mailing + resultSet.getString("cityname") + ", ";
                mailing = mailing + resultSet.getString("zipcode");
                userAccounts[count].setMailingAddress(mailing);
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserAccount(UserAccount[] userAccounts) {
        this.userAccounts = userAccounts;
    }

    public UserAccount getUserAccountByIsDefaultFlag(String isDefaultAccNum) {
        return Arrays.stream(userAccounts).filter(userAccount -> userAccount.getIsDefaultAccNum()
                .equalsIgnoreCase(isDefaultAccNum)).findFirst().get();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getIsDefaultAccNum() {
        return isDefaultAccNum;
    }

    public void setIsDefaultAccNum(String isDefaultAccNum) {
        this.isDefaultAccNum = isDefaultAccNum;
    }

    public String getUtilityAccNum() {
        return utilityAccNum;
    }

    public void setUtilityAccNum(String utilityAccNum) {
        this.utilityAccNum = utilityAccNum;
    }

    public String getBpNumber() {
        return bpNumber;
    }

    public void setBpNumber(String bpNumber) {
        this.bpNumber = bpNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getIsPaperlessOpted() {
        return isPaperlessOpted;
    }

    public void setIsPaperlessOpted(String isPaperlessOpted) {
        this.isPaperlessOpted = isPaperlessOpted;
    }

    public String getIsEmailNotifyOpted() {
        return isEmailNotifyOpted;
    }

    public void setIsEmailNotifyOpted(String isEmailNotifyOpted) {
        this.isEmailNotifyOpted = isEmailNotifyOpted;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public UserAccount[] getUserAccounts() {
        return userAccounts;
    }

    public void setUserAccounts(UserAccount[] userAccounts) {
        this.userAccounts = userAccounts;
    }
}

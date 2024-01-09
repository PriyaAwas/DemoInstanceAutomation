package sew.ai.api.pojos.myaccount.profile;

import sew.ai.models.User;
import sew.ai.utils.DateUtil;

public class LinkAccountPayload {
    private int UserAccountID;
    private int UserID;
    private int AccountNumber;
    private int RoleID;
    private String CreatedDate;
    private String LastUpdated;
    private Boolean IsOwner;
    private int CustomerType;
    private Boolean IsDefaultAccount;
    private String UtilityAccountNumber;
    private String CustomerNumber;
    private String BPNumber;
    private int CustomerID;
    private Boolean Paperless;
    private Boolean EmailNotify;
    private Boolean BudgetNotify;
    private int DefaultPaymentType;
    private int RequestType;
    private Boolean IsPledge;
    private Boolean Status;
    private String LanguageCode;
    private String PostalCode;
    private String EmailID;
    private String MobileNumber;
    private String DrivingLicence;
    private String MeterNumber;
    private String SSNNumber;
    private String StreetNumber;
    private int GroupId;

    public LinkAccountPayload() {
    }

    public LinkAccountPayload(User user) {
        String _date = DateUtil.getCurrentDateTimeInAPIFormat();
        this.UserID = Integer.parseInt(user.getUserId());
        this.AccountNumber = Integer.parseInt(user.getAccountNumber());
        this.CreatedDate = _date;
        this.LastUpdated = _date;
        this.UtilityAccountNumber = "";
        this.CustomerNumber = "";
        this.CustomerID = 0;
        this.PostalCode = "";
        this.MeterNumber = "";
        this.DrivingLicence = "";
        this.SSNNumber = "";
        this.StreetNumber = "";
        this.CustomerType = 0;
        this.UserAccountID = 0;
        this.RoleID = 3;
        this.IsOwner = true;
        this.IsDefaultAccount = true;
        this.Paperless = true;
        this.EmailNotify = true;
        this.BudgetNotify = true;
        this.DefaultPaymentType = 0;
        this.RequestType = 0;
        this.IsPledge = true;
        this.Status = true;
        this.EmailID = "";
        this.MobileNumber = "";
        this.BPNumber = "";
        this.LanguageCode = "EN";
        this.GroupId = 0;
    }

    public int getUserAccountID() {
        return UserAccountID;
    }

    public void setUserAccountID(int userAccountID) {
        UserAccountID = userAccountID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        AccountNumber = accountNumber;
    }

    public int getRoleID() {
        return RoleID;
    }

    public void setRoleID(int roleID) {
        RoleID = roleID;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getLastUpdated() {
        return LastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        LastUpdated = lastUpdated;
    }

    public Boolean getIsOwner() {
        return IsOwner;
    }

    public void setIsOwner(Boolean isOwner) {
        IsOwner = isOwner;
    }

    public int getCustomerType() {
        return CustomerType;
    }

    public void setCustomerType(int customerType) {
        CustomerType = customerType;
    }

    public Boolean getIsDefaultAccount() {
        return IsDefaultAccount;
    }

    public void setIsDefaultAccount(Boolean isDefaultAccount) {
        IsDefaultAccount = isDefaultAccount;
    }

    public String getUtilityAccountNumber() {
        return UtilityAccountNumber;
    }

    public void setUtilityAccountNumber(String utilityAccountNumber) {
        UtilityAccountNumber = utilityAccountNumber;
    }

    public String getCustomerNumber() {
        return CustomerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        CustomerNumber = customerNumber;
    }

    public String getBPNumber() {
        return BPNumber;
    }

    public void setBPNumber(String bPNumber) {
        BPNumber = bPNumber;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public Boolean getPaperless() {
        return Paperless;
    }

    public void setPaperless(Boolean paperless) {
        Paperless = paperless;
    }

    public Boolean getEmailNotify() {
        return EmailNotify;
    }

    public void setEmailNotify(Boolean emailNotify) {
        EmailNotify = emailNotify;
    }

    public Boolean getBudgetNotify() {
        return BudgetNotify;
    }

    public void setBudgetNotify(Boolean budgetNotify) {
        BudgetNotify = budgetNotify;
    }

    public int getDefaultPaymentType() {
        return DefaultPaymentType;
    }

    public void setDefaultPaymentType(int defaultPaymentType) {
        DefaultPaymentType = defaultPaymentType;
    }

    public int getRequestType() {
        return RequestType;
    }

    public void setRequestType(int requestType) {
        RequestType = requestType;
    }

    public Boolean getIsPledge() {
        return IsPledge;
    }

    public void setIsPledge(Boolean isPledge) {
        IsPledge = isPledge;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }

    public String getEmailID() {
        return EmailID;
    }

    public void setEmailID(String emailID) {
        EmailID = emailID;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getDrivingLicence() {
        return DrivingLicence;
    }

    public void setDrivingLicence(String drivingLicence) {
        DrivingLicence = drivingLicence;
    }

    public String getMeterNumber() {
        return MeterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        MeterNumber = meterNumber;
    }

    public String getSSNNumber() {
        return SSNNumber;
    }

    public void setSSNNumber(String sSNNumber) {
        SSNNumber = sSNNumber;
    }

    public String getStreetNumber() {
        return StreetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        StreetNumber = streetNumber;
    }

    public int getGroupId() {
        return GroupId;
    }

    public void setGroupId(int groupId) {
        GroupId = groupId;
    }
}

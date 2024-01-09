package sew.ai.api.pojos.billing.autopay;

import sew.ai.models.Autopay;
import sew.ai.models.User;
import sew.ai.utils.DateUtil;

public class AutopayPayload {
    Integer Id;
    Integer AccountNumber;
    Integer PayTypeId;
    String  PayId;
    Integer RecPaymentDate;
    Integer IsDeleted;
    String LanguageCode;
    Integer UserID;
    Integer PaymentConfigID;
    Integer PaymentFrequencyID;
    Integer PaymentDay;
    Double PaymentAmount;
    String StartDate;
    Integer PaymentSubType;
    Integer GroupId;
    String SelectedAccounts;
    String UtilityAccountNumber;
    String UserProfileId;

    public AutopayPayload(Autopay autopay, User user) {
        setAccountNumber(Integer.parseInt(autopay.getAccountNumber()));
        setSelectedAccounts(null);
        setUtilityAccountNumber(autopay.getUtilityAccountNumber());
        setUserID(Integer.parseInt(user.getUserId()));
        setUserProfileId(null);
        setId(autopay.getAutoPayId());
        setPayTypeId(autopay.getPayTypeId());
        setPaymentSubType(autopay.getPaymentSubType());
        setPayId(autopay.getPaymentProfileId());
        setIsDeleted(autopay.getIsDeleted());
        setRecPaymentDate(autopay.getRecPaymentDate());
        setPaymentConfigID(autopay.getPaymentConfigID());
        setPaymentFrequencyID(autopay.getPaymentFrequencyID());
        setPaymentDay(autopay.getPaymentDay());
        setPaymentAmount(autopay.getPaymentAmount());
        setStartDate(DateUtil.getCurrentDateInFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
        setGroupId(0);
        setLanguageCode("EN");
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        AccountNumber = accountNumber;
    }

    public Integer getPayTypeId() {
        return PayTypeId;
    }

    public void setPayTypeId(Integer payTypeId) {
        PayTypeId = payTypeId;
    }

    public String getPayId() {
        return PayId;
    }

    public void setPayId(String payId) {
        PayId = payId;
    }

    public Integer getRecPaymentDate() {
        return RecPaymentDate;
    }

    public void setRecPaymentDate(Integer recPaymentDate) {
        RecPaymentDate = recPaymentDate;
    }

    public Integer getIsDeleted() {
        return IsDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        IsDeleted = isDeleted;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    public Integer getPaymentConfigID() {
        return PaymentConfigID;
    }

    public void setPaymentConfigID(Integer paymentConfigID) {
        PaymentConfigID = paymentConfigID;
    }

    public Integer getPaymentFrequencyID() {
        return PaymentFrequencyID;
    }

    public void setPaymentFrequencyID(Integer paymentFrequencyID) {
        PaymentFrequencyID = paymentFrequencyID;
    }

    public Integer getPaymentDay() {
        return PaymentDay;
    }

    public void setPaymentDay(Integer paymentDay) {
        PaymentDay = paymentDay;
    }

    public Double getPaymentAmount() {
        return PaymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        PaymentAmount = paymentAmount;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public Integer getPaymentSubType() {
        return PaymentSubType;
    }

    public void setPaymentSubType(Integer paymentSubType) {
        PaymentSubType = paymentSubType;
    }

    public Integer getGroupId() {
        return GroupId;
    }

    public void setGroupId(Integer groupId) {
        GroupId = groupId;
    }

    public String getSelectedAccounts() {
        return SelectedAccounts;
    }

    public void setSelectedAccounts(String selectedAccounts) {
        SelectedAccounts = selectedAccounts;
    }

    public String getUtilityAccountNumber() {
        return UtilityAccountNumber;
    }

    public void setUtilityAccountNumber(String utilityAccountNumber) {
        UtilityAccountNumber = utilityAccountNumber;
    }

    public String getUserProfileId() {
        return UserProfileId;
    }

    public void setUserProfileId(String userProfileId) {
        UserProfileId = userProfileId;
    }
}

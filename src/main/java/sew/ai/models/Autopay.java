package sew.ai.models;

import java.util.Arrays;

public class Autopay {
    private String autopayScenario;
    private String accountNumber;
    private String utilityAccountNumber;
    private int autoPayId;
    private int payTypeId;
    private String paymentProfileId;
    private int isDeleted;
    private int paymentSubType;
    private double paymentAmount;
    private String recPaymentType;
    private int recPaymentDate;
    private int paymentConfigID;
    private int paymentFrequencyID;
    private int paymentDay;
    private String startDate;
    private Autopay [] autopays;

    public Autopay() {}

    public Autopay(Autopay[] autopays) {
        this.autopays = autopays;
    }

    public Autopay getAutopayObjByScenarioName(String id) {
        return Arrays.stream(autopays).filter(autopay -> autopay.getAutopayScenario()
                .equalsIgnoreCase(id)).findFirst().get();
    }

    public String getAutopayScenario() {
        return autopayScenario;
    }

    public void setAutopayScenario(String autopayScenario) {
        this.autopayScenario = autopayScenario;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getUtilityAccountNumber() {
        return utilityAccountNumber;
    }

    public void setUtilityAccountNumber(String utilityAccountNumber) {
        this.utilityAccountNumber = utilityAccountNumber;
    }

    public int getAutoPayId() {
        return autoPayId;
    }

    public void setAutoPayId(int autoPayId) {
        this.autoPayId = autoPayId;
    }

    public String getPaymentProfileId() {
        return paymentProfileId;
    }

    public void setPaymentProfileId(String paymentProfileId) {
        this.paymentProfileId = paymentProfileId;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getPaymentSubType() {
        return paymentSubType;
    }

    public void setPaymentSubType(int paymentSubType) {
        this.paymentSubType = paymentSubType;
    }

    public int getPayTypeId() {
        return payTypeId;
    }

    public void setPayTypeId(int payTypeId) {
        this.payTypeId = payTypeId;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getRecPaymentType() {
        return recPaymentType;
    }

    public void setRecPaymentType(String recPaymentType) {
        this.recPaymentType = recPaymentType;
    }

    public int getRecPaymentDate() {
        return recPaymentDate;
    }

    public void setRecPaymentDate(int recPaymentDate) {
        this.recPaymentDate = recPaymentDate;
    }

    public int getPaymentConfigID() {
        return paymentConfigID;
    }

    public void setPaymentConfigID(int paymentConfigID) {
        this.paymentConfigID = paymentConfigID;
    }

    public int getPaymentFrequencyID() {
        return paymentFrequencyID;
    }

    public void setPaymentFrequencyID(int paymentFrequencyID) {
        this.paymentFrequencyID = paymentFrequencyID;
    }

    public int getPaymentDay() {
        return paymentDay;
    }

    public void setPaymentDay(int paymentDay) {
        this.paymentDay = paymentDay;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Autopay[] getAutopays() {
        return autopays;
    }

    public void setAutopays(Autopay[] autopays) {
        this.autopays = autopays;
    }
}

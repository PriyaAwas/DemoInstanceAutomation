package sew.ai.api.pojos.usage;

import java.util.Arrays;

public class CompareGasResponse {
    private String accountNumber;
    private double amount;
    private double consumption;
    private String mod;
    private String yod;
    private String readingFrom;
    private String readingTo;
    private String billDate;
    private String compareAttribute1;
    private String compareAttribute2;
    private CompareGasResponse[] compareGasResponses;

    public CompareGasResponse() {
    }

    public CompareGasResponse getCompareGasDataOnBillDate(String billDate) {
        return Arrays.stream(compareGasResponses).filter(compareGasResponse -> compareGasResponse
                .getBillDate()
                .equalsIgnoreCase(billDate)).findFirst().get();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    public String getYod() {
        return yod;
    }

    public void setYod(String yod) {
        this.yod = yod;
    }

    public String getReadingFrom() {
        return readingFrom;
    }

    public void setReadingFrom(String readingFrom) {
        this.readingFrom = readingFrom;
    }

    public String getReadingTo() {
        return readingTo;
    }

    public void setReadingTo(String readingTo) {
        this.readingTo = readingTo;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getCompareAttribute1() {
        return compareAttribute1;
    }

    public void setCompareAttribute1(String compareAttribute1) {
        this.compareAttribute1 = compareAttribute1;
    }

    public String getCompareAttribute2() {
        return compareAttribute2;
    }

    public void setCompareAttribute2(String compareAttribute2) {
        this.compareAttribute2 = compareAttribute2;
    }

    public CompareGasResponse[] getCompareGasResponses() {
        return compareGasResponses;
    }

    public void setCompareGasResponses(CompareGasResponse[] compareGasResponses) {
        this.compareGasResponses = compareGasResponses;
    }
}

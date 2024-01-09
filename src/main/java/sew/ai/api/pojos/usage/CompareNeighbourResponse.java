package sew.ai.api.pojos.usage;

import java.util.Arrays;

public class CompareNeighbourResponse {
    private String postalCode;
    private double amount;
    private double consumption;
    private String mod;
    private String yod;
    private String billDate;
    private int segmentId;
    private String compareAttribute1;
    private String compareAttribute2;

    private CompareNeighbourResponse[] compareNeighbourResponses;

    public CompareNeighbourResponse() {
    }

    public CompareNeighbourResponse getCompareNeighbourDataOnBillDate(String billDate) {
        return Arrays.stream(compareNeighbourResponses).filter(compareNeighbourResponse -> compareNeighbourResponse
                .getBillDate()
                .equalsIgnoreCase(billDate)).findFirst().get();
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public int getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(int segmentId) {
        this.segmentId = segmentId;
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

    public CompareNeighbourResponse[] getCompareNeighbourResponses() {
        return compareNeighbourResponses;
    }

    public void setCompareNeighbourResponses(CompareNeighbourResponse[] compareNeighbourResponses) {
        this.compareNeighbourResponses = compareNeighbourResponses;
    }
}

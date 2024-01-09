package sew.ai.api.pojos.usage;

public class UsageParams {
    String accountNumber;
    String meterNumber;
    String from;
    String to;
    String uom;
    String periodicity;

    public UsageParams(String accountNumber, String meterNumber, String from, String to, String uom,
                      String periodicity) {
        this.accountNumber = accountNumber;
        this.meterNumber = meterNumber;
        this.from = from;
        this.to = to;
        this.uom = uom;
        this.periodicity = periodicity;
    }

    public UsageParams(String accountNumber, String from, String to, String uom, String periodicity) {
        this.accountNumber = accountNumber;
        this.from = from;
        this.to = to;
        this.uom = uom;
        this.periodicity = periodicity;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        this.meterNumber = meterNumber;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(String periodicity) {
        this.periodicity = periodicity;
    }
}

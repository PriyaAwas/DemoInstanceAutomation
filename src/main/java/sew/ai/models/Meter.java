package sew.ai.models;

import sew.ai.helpers.props.SQLQueries;
import sew.ai.utils.DataBaseUtils;

import java.sql.ResultSet;
import java.util.Arrays;

public class Meter {
    private String meterId;
    private String accountNumber;
    private String meterNumber;
    private String meterType;
    private int isAmi;
    private int status;
    private int ratePlanId;
    private Meter[] meters;

    public Meter() {
    }

    public Meter(String accountNumber) {
        ResultSet resultSet, resultSet1;
        resultSet = DataBaseUtils.getResultSet(SQLQueries.getMetersCountLinkedToAccount(accountNumber));
        try {
            resultSet.next();
            meters = new Meter[resultSet.getInt("metercount")];
            resultSet1 = DataBaseUtils.getResultSet(SQLQueries.getMeterInfoLinkedToAccount(accountNumber));
            int count = 0;
            while (resultSet1.next()) {
                Meter meter = new Meter();
                meter.setMeterId(resultSet1.getString("meterid"));
                meter.setAccountNumber(resultSet1.getString("accountnumber"));
                meter.setMeterNumber(resultSet1.getString("meternumber"));
                meter.setMeterType(resultSet1.getString("metertype"));
                meter.setIsAmi(resultSet1.getInt("isami"));
                meter.setStatus(resultSet1.getInt("status"));
                meter.setRatePlanId(resultSet1.getInt("rateplanid"));
                meters[count] = meter;
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Meter(Meter[] meters) {
        this.meters = meters;
    }

    public Meter getMeterByNumber(String meterNumber) {
        return Arrays.stream(meters).filter(meter -> meter.getMeterNumber()
                .equalsIgnoreCase(meterNumber)).findFirst().get();
    }

    public Meter getMeterByType(String meterType) {
        return Arrays.stream(meters).filter(meter -> meter.getMeterType()
                .equalsIgnoreCase(meterType)).findFirst().get();
    }

    public String getMeterId() {
        return meterId;
    }

    public void setMeterId(String meterId) {
        this.meterId = meterId;
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

    public String getMeterType() {
        return meterType;
    }

    public void setMeterType(String meterType) {
        this.meterType = meterType;
    }

    public int getIsAmi() {
        return isAmi;
    }

    public void setIsAmi(int isAmi) {
        this.isAmi = isAmi;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRatePlanId() {
        return ratePlanId;
    }

    public void setRatePlanId(int ratePlanId) {
        this.ratePlanId = ratePlanId;
    }

    public Meter[] getMeters() {
        return meters;
    }

    public void setMeters(Meter[] meters) {
        this.meters = meters;
    }
}

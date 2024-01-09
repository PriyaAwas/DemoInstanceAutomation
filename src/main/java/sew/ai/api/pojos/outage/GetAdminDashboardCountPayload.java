package sew.ai.api.pojos.outage;

import sew.ai.models.Outage;

public class GetAdminDashboardCountPayload {
    String FromDate;
    String ToDate;
    Integer AccountNumber;
    Integer Offset;

    public GetAdminDashboardCountPayload(Outage outage) {
        FromDate = outage.getStartTime();
        ToDate = outage.getEndTime();
        AccountNumber = 0;
        Offset = 330;
    }

    public String getFromDate() {
        return FromDate;
    }

    public void setFromDate(String fromDate) {
        FromDate = fromDate;
    }

    public String getToDate() {
        return ToDate;
    }

    public void setToDate(String toDate) {
        ToDate = toDate;
    }

    public Integer getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        AccountNumber = accountNumber;
    }

    public Integer getOffset() {
        return Offset;
    }

    public void setOffset(Integer offset) {
        Offset = offset;
    }
}

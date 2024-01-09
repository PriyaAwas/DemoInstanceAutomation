package sew.ai.models;

import java.util.Arrays;

public class Outage {
    private String outageScenario;
    private Integer serviceTypeID;
    private String cause;
    private String outageMessage;
    private String isResolved;
    private Integer isPlanned;
    private String outageReportInfo;
    private String startTime;
    private String endTime;
    private Outage[] outages;

    public Outage(Outage[] outages) {
        this.outages = outages;
    }

    public Outage() {
    }

    public Outage getOutageByOutageScenario(String outageScenarioId) {
        return Arrays.stream(outages).filter(outage -> outage.getOutageScenario()
                .equalsIgnoreCase(outageScenarioId)).findFirst().get();
    }

    public String getOutageScenario() {
        return outageScenario;
    }

    public void setOutageScenario(String outageScenario) {
        this.outageScenario = outageScenario;
    }

    public Integer getServiceTypeID() {
        return serviceTypeID;
    }

    public void setServiceTypeID(Integer serviceTypeID) {
        this.serviceTypeID = serviceTypeID;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getOutageMessage() {
        return outageMessage;
    }

    public void setOutageMessage(String outageMessage) {
        this.outageMessage = outageMessage;
    }

    public String getIsResolved() {
        return isResolved;
    }

    public void setIsResolved(String isResolved) {
        this.isResolved = isResolved;
    }

    public Integer getIsPlanned() {
        return isPlanned;
    }

    public void setIsPlanned(Integer isPlanned) {
        this.isPlanned = isPlanned;
    }

    public String getOutageReportInfo() {
        return outageReportInfo;
    }

    public void setOutageReportInfo(String outageReportInfo) {
        this.outageReportInfo = outageReportInfo;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Outage[] getOutages() {
        return outages;
    }

    public void setOutages(Outage[] outages) {
        this.outages = outages;
    }
}

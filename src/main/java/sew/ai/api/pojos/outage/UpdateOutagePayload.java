package sew.ai.api.pojos.outage;

import sew.ai.helpers.props.SQLQueries;
import sew.ai.models.Outage;
import sew.ai.models.User;
import sew.ai.utils.DataBaseUtils;

import java.sql.ResultSet;

public class UpdateOutagePayload {
    String Type;
    String Cause;
    String StartTime;
    String Circuit;
    String EndTime;
    String OutageLongitude;
    String OutageLatitude;
    String Zipcode;
    String OutageMessage;
    String OutageReportInfo;
    String xmldata;
    String offset;
    String OutageId;
    String IsResolved;
    String City;
    Integer ServiceTypeID;
    Integer IsPalnned;

    public UpdateOutagePayload(User user, Outage outage, String OutageID) {
        Type = "0";
        Cause = outage.getCause();
        StartTime = outage.getStartTime();
        Circuit = "";
        EndTime = outage.getEndTime();
        ;
        OutageLongitude = "42.029180";
        OutageLatitude = "-88.000647";
        Zipcode = user.getZipCode();
        OutageMessage = outage.getOutageMessage();
        OutageReportInfo = outage.getOutageReportInfo();
        this.xmldata = "{'Outage':[{\"Area\":[{\"x\":42.0964703508054,\"y\":-88.08716394878319,\"zipcode\":\"" + Zipcode + "\"},{\"x\":42.0317304950797,\"y\":-87.83104517436912,\"zipcode\":\"" + Zipcode + "\"},{\"x\":41.97049832837995,\"y\":-88.09952356792381,\"zipcode\":\"" + Zipcode + "\"}]},{\"points\":[{\"x\":42.02918033127186,\"y\":-88.00064661479881,\"zipcode\":\"" + Zipcode + "\"}]}]}";
        this.offset = "330";
        OutageId = OutageID;
        IsResolved = outage.getIsResolved();
        try {
            ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.getCityIDOfUser(user.getUserName()));
            while (rs.next()) {
                City = rs.getString("CityID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ServiceTypeID = outage.getServiceTypeID();
        IsPalnned = outage.getIsPlanned();
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getCause() {
        return Cause;
    }

    public void setCause(String cause) {
        Cause = cause;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getCircuit() {
        return Circuit;
    }

    public void setCircuit(String circuit) {
        Circuit = circuit;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getOutageLongitude() {
        return OutageLongitude;
    }

    public void setOutageLongitude(String outageLongitude) {
        OutageLongitude = outageLongitude;
    }

    public String getOutageLatitude() {
        return OutageLatitude;
    }

    public void setOutageLatitude(String outageLatitude) {
        OutageLatitude = outageLatitude;
    }

    public String getZipcode() {
        return Zipcode;
    }

    public void setZipcode(String zipcode) {
        Zipcode = zipcode;
    }

    public String getOutageMessage() {
        return OutageMessage;
    }

    public void setOutageMessage(String outageMessage) {
        OutageMessage = outageMessage;
    }

    public String getOutageReportInfo() {
        return OutageReportInfo;
    }

    public void setOutageReportInfo(String outageReportInfo) {
        OutageReportInfo = outageReportInfo;
    }

    public String getXmldata() {
        return xmldata;
    }

    public void setXmldata(String xmldata) {
        this.xmldata = xmldata;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getOutageId() {
        return OutageId;
    }

    public void setOutageId(String outageId) {
        OutageId = outageId;
    }

    public String getIsResolved() {
        return IsResolved;
    }

    public void setIsResolved(String isResolved) {
        IsResolved = isResolved;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public Integer getServiceTypeID() {
        return ServiceTypeID;
    }

    public void setServiceTypeID(Integer serviceTypeID) {
        ServiceTypeID = serviceTypeID;
    }

    public Integer getIsPalnned() {
        return IsPalnned;
    }

    public void setIsPalnned(Integer isPalnned) {
        IsPalnned = isPalnned;
    }
}

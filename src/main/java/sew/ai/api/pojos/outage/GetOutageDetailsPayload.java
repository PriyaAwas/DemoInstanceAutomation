package sew.ai.api.pojos.outage;

public class GetOutageDetailsPayload {
    String OutageId;
    Integer Offset;

    public GetOutageDetailsPayload(String outageId) {
        OutageId = outageId;
        Offset = 330;
    }

    public String getOutageId() {
        return OutageId;
    }

    public void setOutageId(String outageId) {
        OutageId = outageId;
    }

    public Integer getOffset() {
        return Offset;
    }

    public void setOffset(Integer offset) {
        Offset = offset;
    }
}

package sew.ai.api.pojos.billing.applepay;

public class OriginDetails {
    private String origin;

    public OriginDetails() {}

    public OriginDetails(String origin) {
        this.origin = origin;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}

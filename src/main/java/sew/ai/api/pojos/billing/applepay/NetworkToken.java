package sew.ai.api.pojos.billing.applepay;

import sew.ai.models.Token;

public class NetworkToken {
    private String number;
    private String expirationYear;
    private String expirationMonth;
    private String cryptogram;
    private String eCommerceIndicator;
    private OriginDetails originDetails;

    public NetworkToken() {}

    public NetworkToken(Token token) {
        this.number = token.getNumber();
        this.expirationYear = token.getExpirationYear();
        this.expirationMonth = token.getExpirationMonth();
        this.cryptogram = token.getCryptogram();
        this.eCommerceIndicator = token.geteCommerceIndicator();
        this.originDetails = new OriginDetails(token.getOrigin());
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(String expirationYear) {
        this.expirationYear = expirationYear;
    }

    public String getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public String getCryptogram() {
        return cryptogram;
    }

    public void setCryptogram(String cryptogram) {
        this.cryptogram = cryptogram;
    }

    public String geteCommerceIndicator() {
        return eCommerceIndicator;
    }

    public void seteCommerceIndicator(String eCommerceIndicator) {
        this.eCommerceIndicator = eCommerceIndicator;
    }

    public OriginDetails getOriginDetails() {
        return originDetails;
    }

    public void setOriginDetails(OriginDetails originDetails) {
        this.originDetails = originDetails;
    }
}

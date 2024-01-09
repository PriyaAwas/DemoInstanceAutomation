package sew.ai.models;

import java.util.Arrays;

public class Token {
    private String tokenId;
    private String number;
    private String expirationYear;
    private String expirationMonth;
    private String cryptogram;
    private String eCommerceIndicator;
    private String origin;
    private Token[] tokens;

    public Token() {
    }

    public Token(Token[] tokens) {
        this.tokens = tokens;
    }

    public Token getTokenByTokenId(String tokenId) {
        return Arrays.stream(tokens).filter(token -> token.getTokenId()
                .equalsIgnoreCase(tokenId)).findFirst().get();
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Token[] getTokens() {
        return tokens;
    }

    public void setTokens(Token[] tokens) {
        this.tokens = tokens;
    }
}

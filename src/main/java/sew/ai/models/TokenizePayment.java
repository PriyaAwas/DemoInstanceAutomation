package sew.ai.models;

import java.util.Arrays;

public class TokenizePayment {
    private String id;
    private double amount;
    private double convenienceFee;
    private boolean isLoggedIn;
    private boolean isAutoPay;
    private boolean saveProfileAfterPayment;
    private String token;
    private TokenizePayment [] tokenizePayments;

    public TokenizePayment() {}

    public TokenizePayment(TokenizePayment[] tokenizePayments) {
        this.tokenizePayments = tokenizePayments;
    }

    public TokenizePayment getTokenizePaymentObjById(String id) {
        return Arrays.stream(tokenizePayments).filter(tokenizePayment -> tokenizePayment.getId()
                .equalsIgnoreCase(id)).findFirst().get();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getConvenienceFee() {
        return convenienceFee;
    }

    public void setConvenienceFee(double convenienceFee) {
        this.convenienceFee = convenienceFee;
    }

    public boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public boolean getIsAutoPay() {
        return isAutoPay;
    }

    public void setIsAutoPay(boolean isAutoPay) {
        this.isAutoPay = isAutoPay;
    }

    public boolean getSaveProfileAfterPayment() {
        return saveProfileAfterPayment;
    }

    public void setSaveProfileAfterPayment(boolean saveProfileAfterPayment) {
        this.saveProfileAfterPayment = saveProfileAfterPayment;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TokenizePayment[] getTokenizePayments() {
        return tokenizePayments;
    }

    public void setTokenizePayments(TokenizePayment[] tokenizePayments) {
        this.tokenizePayments = tokenizePayments;
    }
}

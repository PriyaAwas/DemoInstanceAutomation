package sew.ai.models;

import java.util.Arrays;

public class OneTimePayment {
    private String id;
    private double amount;
    private double convenienceFee;
    private boolean isLoggedIn;
    private boolean isAutoPay;
    private boolean saveProfileAfterPayment;
    private boolean isDuplicatePayment;
    private boolean setDefaultPayment;
    private OneTimePayment[] oneTimePaymentObjects;

    public OneTimePayment() {
    }

    public OneTimePayment(OneTimePayment[] oneTimePaymentObjects) {
        this.oneTimePaymentObjects = oneTimePaymentObjects;
    }

    public OneTimePayment getOneTimePaymentObjById(String id) {
        return Arrays.stream(oneTimePaymentObjects).filter(oneTimePaymentObj -> oneTimePaymentObj.getId()
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

    public boolean getIsDuplicatePayment() {
        return isDuplicatePayment;
    }

    public void setIsDuplicatePayment(boolean isDuplicatePayment) {
        this.isDuplicatePayment = isDuplicatePayment;
    }

    public OneTimePayment[] getOneTimePaymentObjById() {
        return oneTimePaymentObjects;
    }

    public void setOneTimePaymentObjects(OneTimePayment[] oneTimePaymentObjects) {
        this.oneTimePaymentObjects = oneTimePaymentObjects;
    }

    public boolean isSetDefaultPayment() {
        return setDefaultPayment;
    }

    public void setSetDefaultPayment(boolean setDefaultPayment) {
        this.setDefaultPayment = setDefaultPayment;
    }
}

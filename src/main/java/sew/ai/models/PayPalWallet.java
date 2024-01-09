package sew.ai.models;

import java.util.Arrays;

public class PayPalWallet {
    private String firstName;
    private String lastName;
    private String customerName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zip;
    private String countryCode;
    private String nonceToken;
    private PayPalWallet payPalWallets [];

    public PayPalWallet() {}

    public PayPalWallet(PayPalWallet[] payPalWallets) {
        this.payPalWallets = payPalWallets;
    }


    public PayPalWallet getPayPalWalletByCustomerName(String customerName) {
        return Arrays.stream(payPalWallets).filter(payPalWallet -> payPalWallet.getCustomerName()
                .equalsIgnoreCase(customerName)).findFirst().get();
    }

    public PayPalWallet[] getPayPalWallets() {
        return payPalWallets;
    }

    public void setPayPalWallets(PayPalWallet[] payPalWallets) {
        this.payPalWallets = payPalWallets;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getNonceToken() {
        return nonceToken;
    }

    public void setNonceToken(String nonceToken) {
        this.nonceToken = nonceToken;
    }
}

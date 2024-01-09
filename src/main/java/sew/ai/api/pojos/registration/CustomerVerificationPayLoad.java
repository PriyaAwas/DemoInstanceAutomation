package sew.ai.api.pojos.registration;

public class CustomerVerificationPayLoad {

    String CustomerVerificationID;
    String LanguageCode;
    int AccountNumber;

    public CustomerVerificationPayLoad(String customerVerification) {
        CustomerVerificationID = customerVerification;
        LanguageCode = "EN";
        AccountNumber = 0;
    }

    public String getCustomerVerificationID() {
        return CustomerVerificationID;
    }

    public void setCustomerVerificationID(String customerVerificationID) {
        CustomerVerificationID = customerVerificationID;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }

    public int getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        AccountNumber = accountNumber;
    }
}

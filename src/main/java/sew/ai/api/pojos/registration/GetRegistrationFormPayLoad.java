package sew.ai.api.pojos.registration;

public class GetRegistrationFormPayLoad {

    private String LanguageCode;
    private boolean IsAccount;
    private int CustomerType;

    public GetRegistrationFormPayLoad() {
        LanguageCode = "EN";
        IsAccount = false;
        CustomerType = 1;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }

    public boolean isIsAccount() {
        return IsAccount;
    }

    public void setIsAccount(boolean isAccount) {
        IsAccount = isAccount;
    }

    public int getCustomerType() {
        return CustomerType;
    }

    public void setCustomerType(int customerType) {
        CustomerType = customerType;
    }
}

package sew.ai.api.pojos.auth;

public class ValidateMFAPayload {
    String UserId;
    Integer AuthenticationType;
    String Token;
    String LanguageCode;
    Integer Type;
    Boolean IsRegistration;
    Object CustomAttributes;

    public ValidateMFAPayload(String userName, String token, Boolean isRegistration, Object customAttributes) {
        setUserId(userName);
        setAuthenticationType(2);
        setToken(token);
        setLanguageCode("EN");
        setType(0);
        setRegistration(isRegistration);
        setCustomAttributes(customAttributes);
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public Integer getAuthenticationType() {
        return AuthenticationType;
    }

    public void setAuthenticationType(Integer authenticationType) {
        AuthenticationType = authenticationType;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }

    public Integer getType() {
        return Type;
    }

    public void setType(Integer type) {
        Type = type;
    }

    public Boolean getRegistration() {
        return IsRegistration;
    }

    public void setRegistration(Boolean registration) {
        IsRegistration = registration;
    }

    public Object getCustomAttributes() {
        return CustomAttributes;
    }

    public void setCustomAttributes(Object customAttributes) {
        CustomAttributes = customAttributes;
    }
}

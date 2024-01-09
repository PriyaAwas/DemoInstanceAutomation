package sew.ai.api.pojos.myaccount.guestuser;

public class GuestUserRolePayload {
    private String LanguageCode;
    private Boolean IsRegistration;
    private int PersonaId;

    public GuestUserRolePayload() {
        this.LanguageCode = "EN";
        this.IsRegistration = true;
        this.PersonaId = 0;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public Boolean getIsRegistration() {
        return IsRegistration;
    }

    public int getPersonaId() {
        return PersonaId;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }

    public void setIsRegistration(Boolean isRegistration) {
        IsRegistration = isRegistration;
    }

    public void setPersonaId(int personaId) {
        PersonaId = personaId;
    }
}

package sew.ai.api.pojos.connectme;

public class ConnectMeDetailsTemplatePayload {
    int TemplateTypeId;
    String LanguageCode;

    public ConnectMeDetailsTemplatePayload(int templateTypeId) {
        this.TemplateTypeId = templateTypeId;
        this.LanguageCode = "EN";
    }

    public int getTemplateTypeId() {
        return TemplateTypeId;
    }

    public void setTemplateTypeId(int templateTypeId) {
        TemplateTypeId = templateTypeId;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }
}

package sew.ai.models;

import java.util.Arrays;

public class AdminWaysToSave {

    private String category;
    private String meterType;
    private String accountType;

    private String accountType1;
    private boolean isInternal;

    private String externalLink;
    private String topicNameEnglish;
    private String descEnglish;

    private String annualSavings;
    private String incentiveRate;
    private String attachFileName;
    private String scenario;

    private AdminWaysToSave[] adminWaysToSave;

    public AdminWaysToSave() {
    }

    public AdminWaysToSave(AdminWaysToSave[] adminWaysToSave) {
        this.adminWaysToSave = adminWaysToSave;
    }

    public AdminWaysToSave getAdminWaysToSaveByScenario(String scenario) {
        return Arrays.stream(adminWaysToSave).filter(s -> s.getScenario()
                .equalsIgnoreCase(scenario)).findFirst().get();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMeterType() {
        return meterType;
    }

    public void setMeterType(String meterType) {
        this.meterType = meterType;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountType1() {
        return accountType1;
    }

    public void setAccountType1(String accountType1) {
        this.accountType1 = accountType1;
    }

    public String getExternalLink() {
        return externalLink;
    }

    public void setExternalLink(String externalLink) {
        this.externalLink = externalLink;
    }

    public Boolean getIsInternal() {
        return isInternal;
    }

    public void setInternal(Boolean isInternal) {
        this.isInternal = isInternal;
    }

    public String getTopicNameEnglish() {
        return topicNameEnglish;
    }

    public void setTopicNameEnglish(String topicNameEnglish) {
        this.topicNameEnglish = topicNameEnglish;
    }

    public String getDescEnglish() {
        return descEnglish;
    }

    public void setDescEnglish(String descEnglish) {
        this.descEnglish = descEnglish;
    }

    public String getAnnualSavings() {
        return annualSavings;
    }

    public void setAnnualSavings(String annualSavings) {
        this.annualSavings = annualSavings;
    }

    public String getIncentiveRate() {
        return incentiveRate;
    }

    public void setIncentiveRate(String incentiveRate) {
        this.incentiveRate = incentiveRate;
    }

    public String getAttachFileName() {
        return attachFileName;
    }

    public void setAttachFileName(String attachFileName) {
        this.attachFileName = attachFileName;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }
}

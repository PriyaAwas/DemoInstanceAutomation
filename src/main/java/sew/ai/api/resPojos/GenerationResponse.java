package sew.ai.api.resPojos;

public class GenerationResponse {
    private String accountNumber;
    private String meterNumber;
    private String usageAttribute1;
    private String usageAttribute2;
    private Double amount;
    private String tierTou;
    private String uom;
    private Double generation;
    private String readingFrom;
    private String readingTo;
    private String readDate;
    private String ratePlan;
    private GenerationResponse[] generationResponses;

    public GenerationResponse() {
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        this.meterNumber = meterNumber;
    }

    public String getUsageAttribute1() {
        return usageAttribute1;
    }

    public void setUsageAttribute1(String usageAttribute1) {
        this.usageAttribute1 = usageAttribute1;
    }

    public String getUsageAttribute2() {
        return usageAttribute2;
    }

    public void setUsageAttribute2(String usageAttribute2) {
        this.usageAttribute2 = usageAttribute2;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTierTou() {
        return tierTou;
    }

    public void setTierTou(String tierTou) {
        this.tierTou = tierTou;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public Double getGeneration() {
        return generation;
    }

    public void setGeneration(Double generation) {
        this.generation = generation;
    }

    public String getReadingFrom() {
        return readingFrom;
    }

    public void setReadingFrom(String readingFrom) {
        this.readingFrom = readingFrom;
    }

    public String getReadingTo() {
        return readingTo;
    }

    public void setReadingTo(String readingTo) {
        this.readingTo = readingTo;
    }

    public String getReadDate() {
        return readDate;
    }

    public void setReadDate(String readDate) {
        this.readDate = readDate;
    }

    public String getRatePlan() {
        return ratePlan;
    }

    public void setRatePlan(String ratePlan) {
        this.ratePlan = ratePlan;
    }

    public GenerationResponse[] getGenerationResponses() {
        return generationResponses;
    }

    public void setGenerationResponses(GenerationResponse[] generationResponses) {
        this.generationResponses = generationResponses;
    }
}

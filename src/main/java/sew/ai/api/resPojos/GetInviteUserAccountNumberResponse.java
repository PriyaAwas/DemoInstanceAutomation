package sew.ai.api.resPojos;

public class GetInviteUserAccountNumberResponse {
    private String UtilityAccountNumber;
    private String BPNumber;
    private String CustomerTypeDesc;
    private GetInviteUserAccountNumberResponse[] getInviteUserAccountNumberResponses;

    public GetInviteUserAccountNumberResponse() {
    }

    public String getUtilityAccountNumber() {
        return UtilityAccountNumber;
    }

    public void setUtilityAccountNumber(String utilityAccountNumber) {
        UtilityAccountNumber = utilityAccountNumber;
    }

    public String getBPNumber() {
        return BPNumber;
    }

    public void setBPNumber(String bPNumber) {
        BPNumber = bPNumber;
    }

    public String getCustomerTypeDesc() {
        return CustomerTypeDesc;
    }

    public void setCustomerTypeDesc(String customerTypeDesc) {
        CustomerTypeDesc = customerTypeDesc;
    }

    public GetInviteUserAccountNumberResponse[] getGetInviteUserAccountNumberResponses() {
        return getInviteUserAccountNumberResponses;
    }

    public void setGetInviteUserAccountNumberResponses(
            GetInviteUserAccountNumberResponse[] getInviteUserAccountNumberResponses) {
        this.getInviteUserAccountNumberResponses = getInviteUserAccountNumberResponses;
    }
}

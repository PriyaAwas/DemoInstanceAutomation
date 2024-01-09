package sew.ai.api.pojos.myaccount.guestuser;

public class ResendGuestUserEmailPayload {
    private int RequestID;
    private String LanguageCode;
    private int RoleID;
    private int GroupId;

    public ResendGuestUserEmailPayload() {
    }

    public ResendGuestUserEmailPayload(String requestId) {
        this.RequestID = Integer.parseInt(requestId);
        this.LanguageCode = "EN";
        this.RoleID = 0;
        this.GroupId = 0;
    }

    public int getRequestID() {
        return RequestID;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public int getRoleID() {
        return RoleID;
    }

    public int getGroupId() {
        return GroupId;
    }

    public void setRequestID(int requestID) {
        RequestID = requestID;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }

    public void setRoleID(int roleID) {
        RoleID = roleID;
    }

    public void setGroupId(int groupId) {
        GroupId = groupId;
    }
}

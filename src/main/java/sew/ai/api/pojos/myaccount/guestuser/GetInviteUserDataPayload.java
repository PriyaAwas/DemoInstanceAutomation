package sew.ai.api.pojos.myaccount.guestuser;

import sew.ai.models.User;

public class GetInviteUserDataPayload {
    private String LanguageCode;
    private int UserId;
    private int GroupId;

    public GetInviteUserDataPayload(User user) {
        LanguageCode = "EN";
        UserId = Integer.parseInt(user.getUserId());
        GroupId = 0;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getGroupId() {
        return GroupId;
    }

    public void setGroupId(int groupId) {
        GroupId = groupId;
    }
}

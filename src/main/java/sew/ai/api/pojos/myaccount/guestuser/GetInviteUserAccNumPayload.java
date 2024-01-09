package sew.ai.api.pojos.myaccount.guestuser;

import sew.ai.models.User;

public class GetInviteUserAccNumPayload {
    private int UserID;

    public GetInviteUserAccNumPayload() {
    }

    public GetInviteUserAccNumPayload(User user) {
        this.UserID = Integer.parseInt(user.getUserId());
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }
}

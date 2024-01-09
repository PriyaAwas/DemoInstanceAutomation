package sew.ai.api.pojos.myaccount.guestuser;

import sew.ai.models.User;

public class RevokeGuestUserAccessPayload {
	private int RequestID;
	private String LanguageCode;
	private int UserID;
	private int RoleID;

	public RevokeGuestUserAccessPayload() {
	}

	public RevokeGuestUserAccessPayload(User user, String requestId) {
		this.RequestID = Integer.parseInt(requestId);
		this.LanguageCode = "EN";
		this.UserID = Integer.parseInt(user.getUserId());
		this.RoleID = 0;
	}

	public int getRequestID() {
		return RequestID;
	}

	public String getLanguageCode() {
		return LanguageCode;
	}

	public int getUserID() {
		return UserID;
	}

	public int getRoleID() {
		return RoleID;
	}

	public void setRequestID(int requestID) {
		RequestID = requestID;
	}

	public void setLanguageCode(String languageCode) {
		LanguageCode = languageCode;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public void setRoleID(int roleID) {
		RoleID = roleID;
	}
}

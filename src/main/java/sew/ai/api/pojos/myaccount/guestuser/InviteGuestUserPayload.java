package sew.ai.api.pojos.myaccount.guestuser;

import sew.ai.models.GuestUser;
import sew.ai.models.User;
import sew.ai.utils.DateUtil;
import sew.ai.utils.RandomUtil;

public class InviteGuestUserPayload {
    private int RequestID;
    private int UserID_To;
    private String UtilityAccountNumber;
    private String BPNumber;
    private int RoleID;
    private String CreatedDate;
    private int RequestStatus;
    private String LastUpdated;
    private String GuestName;
    private String GuestEmailID;
    private String AccessExpiryDate;
    private String AccessExpiryDateStr;
    private String ActivationKey;
    private String LanguageCode;
    private int UserID;
    private String UserType;
    private String Name;
    private String JobTitle;
    private String PhoneNumber;
    private Boolean IsRegistration;
    private int PersonaId;

    public InviteGuestUserPayload() {
    }

    public InviteGuestUserPayload(User user, GuestUser guestUser) {
        String _date = DateUtil.getCurrentDateTimeInAPIFormat();
        String random = RandomUtil.generateRandomString(3, RandomUtil.Mode.NUMERIC);
        this.UtilityAccountNumber = user.getDefaultUtilityAccNum();
        this.BPNumber = user.getCustomerNum();
        this.UserID = Integer.parseInt(user.getUserId());
        this.Name = user.getFullName();
        this.RequestID = 0;
        this.UserID_To = 0;
        if (guestUser.getGuestRole().equals("Guest")) {
            this.RoleID = 1;
        }
        else if (guestUser.getGuestRole().equals("PropertyManager")) {
            this.RoleID = 2;
        }
        else {
            this.RoleID = 1;
        }
        this.CreatedDate = _date;
        this.RequestStatus = 0;
        this.LastUpdated = _date;
        this.GuestName = random + guestUser.getGuestName();
        ;
        this.GuestEmailID = random + guestUser.getGuestEmail();
        this.AccessExpiryDate = "";
        this.AccessExpiryDateStr = "";
        this.ActivationKey = "00000000-0000-0000-0000-000000000000";
        this.LanguageCode = "EN";
        this.UserType = "";
        this.JobTitle = "";
        this.PhoneNumber = "";
        this.IsRegistration = true;
        this.PersonaId = 0;
    }

    public int getRequestID() {
        return RequestID;
    }

    public void setRequestID(int requestID) {
        RequestID = requestID;
    }

    public int getUserID_To() {
        return UserID_To;
    }

    public void setUserID_To(int userID_To) {
        UserID_To = userID_To;
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

    public int getRoleID() {
        return RoleID;
    }

    public void setRoleID(int roleID) {
        RoleID = roleID;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public int getRequestStatus() {
        return RequestStatus;
    }

    public void setRequestStatus(int requestStatus) {
        RequestStatus = requestStatus;
    }

    public String getLastUpdated() {
        return LastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        LastUpdated = lastUpdated;
    }

    public String getGuestName() {
        return GuestName;
    }

    public void setGuestName(String guestName) {
        GuestName = guestName;
    }

    public String getGuestEmailID() {
        return GuestEmailID;
    }

    public void setGuestEmailID(String guestEmailID) {
        GuestEmailID = guestEmailID;
    }

    public String getAccessExpiryDate() {
        return AccessExpiryDate;
    }

    public void setAccessExpiryDate(String accessExpiryDate) {
        AccessExpiryDate = accessExpiryDate;
    }

    public String getAccessExpiryDateStr() {
        return AccessExpiryDateStr;
    }

    public void setAccessExpiryDateStr(String accessExpiryDateStr) {
        AccessExpiryDateStr = accessExpiryDateStr;
    }

    public String getActivationKey() {
        return ActivationKey;
    }

    public void setActivationKey(String activationKey) {
        ActivationKey = activationKey;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getJobTitle() {
        return JobTitle;
    }

    public void setJobTitle(String jobTitle) {
        JobTitle = jobTitle;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public Boolean getIsRegistration() {
        return IsRegistration;
    }

    public void setIsRegistration(Boolean isRegistration) {
        IsRegistration = isRegistration;
    }

    public int getPersonaId() {
        return PersonaId;
    }

    public void setPersonaId(int personaId) {
        PersonaId = personaId;
    }
}

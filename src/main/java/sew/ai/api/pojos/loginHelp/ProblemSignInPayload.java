package sew.ai.api.pojos.loginHelp;

import sew.ai.models.User;
import sew.ai.utils.RandomUtil;

public class ProblemSignInPayload {

    Integer PlaceHolderId;
    Integer RequestType;
    String Reason;
    String Subject;
    Integer AccountNumber;
    String MessageBody;
    String Latitude;
    String Longitude;
    Boolean IsShow;
    String Email;
    String FirstName;
    String LastName;
    String PhoneNumber;
    String LanguageCode;
    Integer UserID;
    String UtilityAccountNumber;

    public ProblemSignInPayload(User user) {
        PlaceHolderId = 6;
        RequestType = 3;
        Reason = null;
        Subject = "PreLoginProblemsSigningInRequestSubject";
        AccountNumber = 0;
        MessageBody = "ProblemsSigningInBody"+" "+ RandomUtil.generateRandomString(5, RandomUtil.Mode.NUMERIC);
        Latitude = null;
        Longitude = null;
        IsShow = false;
        Email = user.getEmailId();
        FirstName = null;
        LastName = null;
        PhoneNumber = null;
        LanguageCode = "EN";
        UserID = Integer.parseInt(user.getUserId());
        UtilityAccountNumber = user.getDefaultUtilityAccNum();
    }

    public Integer getPlaceHolderId() {
        return PlaceHolderId;
    }

    public void setPlaceHolderId(Integer placeHolderId) {
        PlaceHolderId = placeHolderId;
    }

    public Integer getRequestType() {
        return RequestType;
    }

    public void setRequestType(Integer requestType) {
        RequestType = requestType;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public Integer getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        AccountNumber = accountNumber;
    }

    public String getMessageBody() {
        return MessageBody;
    }

    public void setMessageBody(String messageBody) {
        MessageBody = messageBody;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public Boolean getShow() {
        return IsShow;
    }

    public void setShow(Boolean show) {
        IsShow = show;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    public String getUtilityAccountNumber() {
        return UtilityAccountNumber;
    }

    public void setUtilityAccountNumber(String utilityAccountNumber) {
        UtilityAccountNumber = utilityAccountNumber;
    }
}


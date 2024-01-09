package sew.ai.api.pojos.notifications;

import sew.ai.models.Notifications;
import sew.ai.models.User;

public class GetMessageBodyPayload {
    Integer AccountNumber;
    String MessageId;
    Integer timeoffset;
    Integer UserID;
    String LanguageCode;
    Integer GroupId;

    public GetMessageBodyPayload(User user, Notifications notifications) {
        AccountNumber = notifications.getAccountNumber();
        MessageId = notifications.getMessageID();
        this.timeoffset = notifications.getTimeOffset();
        ;
        UserID = Integer.parseInt(user.getUserId());
        LanguageCode = notifications.getLanguageCode();
        ;
        GroupId = 0;
    }

    public Integer getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        AccountNumber = accountNumber;
    }

    public String getMessageId() {
        return MessageId;
    }

    public void setMessageId(String messageId) {
        MessageId = messageId;
    }

    public Integer getTimeoffset() {
        return timeoffset;
    }

    public void setTimeoffset(Integer timeoffset) {
        this.timeoffset = timeoffset;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }

    public Integer getGroupId() {
        return GroupId;
    }

    public void setGroupId(Integer groupId) {
        GroupId = groupId;
    }
}

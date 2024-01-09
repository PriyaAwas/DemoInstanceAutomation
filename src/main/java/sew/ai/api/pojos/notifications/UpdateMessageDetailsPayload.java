package sew.ai.api.pojos.notifications;

import sew.ai.models.Notifications;
import sew.ai.models.User;

public class UpdateMessageDetailsPayload {
    Integer AccountNumber;
    String[] MessageID;
    String MessageDetailId;
    Integer IsRead;
    Integer IsSaved;
    Integer IsTrashed;
    Integer IsDelete;
    Integer UserId;
    String LanguageCode;

    public UpdateMessageDetailsPayload(User user, Notifications notifications) {
        AccountNumber = notifications.getAccountNumber();
        MessageID = new String[]{notifications.getMessageID()};
        MessageDetailId = "00000000-0000-0000-0000-000000000000";
        IsRead = notifications.getIsRead();
        IsSaved = notifications.getIsSaved();
        IsTrashed = notifications.getIsTrashed();
        IsDelete = notifications.getIsDelete();
        UserId = Integer.parseInt(user.getUserId());
        LanguageCode = notifications.getLanguageCode();
    }

    public Integer getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        AccountNumber = accountNumber;
    }

    public String[] getMessageID() {
        return MessageID;
    }

    public void setMessageID(String[] messageID) {
        MessageID = messageID;
    }

    public String getMessageDetailId() {
        return MessageDetailId;
    }

    public void setMessageDetailId(String messageDetailId) {
        MessageDetailId = messageDetailId;
    }

    public Integer getIsRead() {
        return IsRead;
    }

    public void setIsRead(Integer isRead) {
        IsRead = isRead;
    }

    public Integer getIsSaved() {
        return IsSaved;
    }

    public void setIsSaved(Integer isSaved) {
        IsSaved = isSaved;
    }

    public Integer getIsTrashed() {
        return IsTrashed;
    }

    public void setIsTrashed(Integer isTrashed) {
        IsTrashed = isTrashed;
    }

    public Integer getIsDelete() {
        return IsDelete;
    }

    public void setIsDelete(Integer isDelete) {
        IsDelete = isDelete;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }
}

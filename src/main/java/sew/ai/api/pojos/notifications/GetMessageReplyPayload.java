package sew.ai.api.pojos.notifications;

import sew.ai.models.Notifications;
import sew.ai.models.User;

public class GetMessageReplyPayload {
    String MessageId;
    String MessageBody;
    Integer CreatedBy;
    String AttachmentPath;
    String AttachmentName;
    Integer UserId;
    String LanguageCode;

    public GetMessageReplyPayload(User user, Notifications notifications) {
        MessageId = notifications.getMessageID();
        MessageBody = notifications.getMessageBody();
        CreatedBy = notifications.getAccountNumber();
        AttachmentPath = notifications.getAttachmentPath();
        AttachmentName = notifications.getAttachmentName();
        UserId = Integer.parseInt(user.getUserId());
        LanguageCode = notifications.getLanguageCode();
    }

    public String getMessageId() {
        return MessageId;
    }

    public void setMessageId(String messageId) {
        MessageId = messageId;
    }

    public String getMessageBody() {
        return MessageBody;
    }

    public void setMessageBody(String messageBody) {
        MessageBody = messageBody;
    }

    public Integer getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(Integer createdBy) {
        CreatedBy = createdBy;
    }

    public String getAttachmentPath() {
        return AttachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        AttachmentPath = attachmentPath;
    }

    public String getAttachmentName() {
        return AttachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        AttachmentName = attachmentName;
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

package sew.ai.api.pojos.notifications;

import sew.ai.models.Notifications;
import sew.ai.models.User;

public class GetNotificationCountPayload {
    Integer AccountNumber;
    Integer MessageId;
    Integer Mode;
    Integer UserId;
    String LanguageCode;
    String UtilityAccountNumber;
    Integer GroupId;
    Boolean IsEnterpriseUser;
    Integer IsDashboard;

    public GetNotificationCountPayload(User user, Notifications notifications) {
        AccountNumber = notifications.getAccountNumber();
        MessageId = 0;//static not changing
        Mode = 1;//used for enterprise
        UserId = Integer.parseInt(user.getUserId());
        LanguageCode = notifications.getLanguageCode();
        UtilityAccountNumber = user.getDefaultUtilityAccNum();
        GroupId = 0;//used for enterprise
        IsEnterpriseUser = notifications.getEnterprise();
        IsDashboard = notifications.getIsDashboard();
    }

    public Integer getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        AccountNumber = accountNumber;
    }

    public Integer getMessageId() {
        return MessageId;
    }

    public void setMessageId(Integer messageId) {
        MessageId = messageId;
    }

    public Integer getMode() {
        return Mode;
    }

    public void setMode(Integer mode) {
        Mode = mode;
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

    public String getUtilityAccountNumber() {
        return UtilityAccountNumber;
    }

    public void setUtilityAccountNumber(String utilityAccountNumber) {
        UtilityAccountNumber = utilityAccountNumber;
    }

    public Integer getGroupId() {
        return GroupId;
    }

    public void setGroupId(Integer groupId) {
        GroupId = groupId;
    }

    public Boolean getEnterpriseUser() {
        return IsEnterpriseUser;
    }

    public void setEnterpriseUser(Boolean enterpriseUser) {
        IsEnterpriseUser = enterpriseUser;
    }

    public Integer getIsDashboard() {
        return IsDashboard;
    }

    public void setIsDashboard(Integer isDashboard) {
        IsDashboard = isDashboard;
    }
}

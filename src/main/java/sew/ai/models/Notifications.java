package sew.ai.models;

import java.util.Arrays;

public class Notifications {

    private String categoryName;
    private int accountNumber;
    private int placeHolderID;
    private int pageIndex;
    private int pageSize;
    private String languageCode;
    private int groupId;
    private int timeOffset;
    private Boolean isEnterprise;
    private String placeHolderIDs;
    private Integer IsDashboard;
    private String messageID;

    private Integer isRead;
    private Integer isSaved;
    private Integer isTrashed;
    private Integer isDelete;

    private String messageBody;
    private String AttachmentPath;
    private String AttachmentName;

    private Notifications[] notifications;

    public Notifications(Notifications[] notifications) {
        this.notifications = notifications;
    }

    public Notifications() {
    }

    public Notifications getNotificationsByCategoryName(String categoryName) {
        return Arrays.stream(notifications).filter(notifications -> notifications.getCategoryName()
                .equalsIgnoreCase(categoryName)).findFirst().get();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getPlaceHolderID() {
        return placeHolderID;
    }

    public void setPlaceHolderID(int placeHolderID) {
        this.placeHolderID = placeHolderID;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getTimeOffset() {
        return timeOffset;
    }

    public void setTimeOffset(int timeOffset) {
        this.timeOffset = timeOffset;
    }

    public Boolean getEnterprise() {
        return isEnterprise;
    }

    public void setEnterprise(Boolean enterprise) {
        isEnterprise = enterprise;
    }

    public String getPlaceHolderIDs() {
        return placeHolderIDs;
    }

    public void setPlaceHolderIDs(String placeHolderIDs) {
        this.placeHolderIDs = placeHolderIDs;
    }

    public Integer getIsDashboard() {
        return IsDashboard;
    }

    public void setIsDashboard(Integer isDashboard) {
        IsDashboard = isDashboard;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public Integer getIsSaved() {
        return isSaved;
    }

    public void setIsSaved(Integer isSaved) {
        this.isSaved = isSaved;
    }

    public Integer getIsTrashed() {
        return isTrashed;
    }

    public void setIsTrashed(Integer isTrashed) {
        this.isTrashed = isTrashed;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
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

    public Notifications[] getNotifications() {
        return notifications;
    }

    public void setNotifications(Notifications[] notifications) {
        this.notifications = notifications;
    }
}

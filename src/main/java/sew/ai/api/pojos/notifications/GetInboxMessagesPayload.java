package sew.ai.api.pojos.notifications;

import sew.ai.models.User;
import sew.ai.models.Notifications;

public class GetInboxMessagesPayload {
    int AccountNumber;
    int PlaceHolderID;
    int PageIndex;
    int PageSize;
    int Userid;
    String LanguageCode;
    int GroupId;
    int Timeoffset;
    Boolean IsEnterprise;
    String PlaceHolderIDs;

    public GetInboxMessagesPayload(Notifications notifications, User user) {
        AccountNumber = notifications.getAccountNumber();
        PlaceHolderID = notifications.getPlaceHolderID();
        PageIndex = notifications.getPageIndex();
        PageSize = notifications.getPageSize();
        Userid = Integer.parseInt(user.getUserId());
        LanguageCode = notifications.getLanguageCode();
        GroupId = notifications.getGroupId();
        Timeoffset = notifications.getTimeOffset();
        IsEnterprise = notifications.getEnterprise();
        PlaceHolderIDs = notifications.getPlaceHolderIDs();
    }

    public int getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        AccountNumber = accountNumber;
    }

    public int getPlaceHolderID() {
        return PlaceHolderID;
    }

    public void setPlaceHolderID(int placeHolderID) {
        PlaceHolderID = placeHolderID;
    }

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int pageIndex) {
        PageIndex = pageIndex;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }

    public int getUserid() {
        return Userid;
    }

    public void setUserid(int userid) {
        Userid = userid;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }

    public int getGroupId() {
        return GroupId;
    }

    public void setGroupId(int groupId) {
        GroupId = groupId;
    }

    public int getTimeoffset() {
        return Timeoffset;
    }

    public void setTimeoffset(int timeoffset) {
        Timeoffset = timeoffset;
    }

    public Boolean getEnterprise() {
        return IsEnterprise;
    }

    public void setEnterprise(Boolean enterprise) {
        IsEnterprise = enterprise;
    }

    public String getPlaceHolderIDs() {
        return PlaceHolderIDs;
    }

    public void setPlaceHolderIDs(String placeHolderIDs) {
        PlaceHolderIDs = placeHolderIDs;
    }
}

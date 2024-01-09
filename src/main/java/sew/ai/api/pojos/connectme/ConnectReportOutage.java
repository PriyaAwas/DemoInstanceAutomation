package sew.ai.api.pojos.connectme;

import sew.ai.models.User;
import sew.ai.models.ConnectMe;

public class ConnectReportOutage {
    String Reason;
    String Subject;
    Integer AccountNumber;
    String MessageBody;
    String Latitude;
    String Longitude;
    Integer TopicId;
    String CustomerName;
    String UserSignature;
    Integer PromotionId;
    Integer IsDrMode;
    String AttachmentName;
    Boolean IsPreLogin;
    String UtilityAccountNumber;
    String FromEMail;
    String LanguageCode;
    Integer Offset;
    String ResponseXML;
    Integer TemplateTypeId;
    String AttachmentXML;
    Integer UserID;
    Integer IsBillingQuery;
    Boolean IsUpdate;
    String SaveId;
    Boolean isEmailFromControl;
    Integer GroupId;
    Boolean IsMobile;
    String trackingId;
    String comments;


    public ConnectReportOutage(User user, ConnectMe connectMe) {
        Reason = connectMe.getReason();
        Subject = connectMe.getSubject();
        AccountNumber = Integer.parseInt(user.getAccountNumber());
        MessageBody = connectMe.getMessageBody();
        Latitude = "";
        Longitude = "";
        TopicId = 56;
        CustomerName = connectMe.getCustomerName();
        UserSignature = "";
        PromotionId = 0;
        IsDrMode = 0;
        AttachmentName = null;
        IsPreLogin = connectMe.getIsPreLogin();
        UtilityAccountNumber = user.getDefaultUtilityAccNum();
        FromEMail = connectMe.getFromEMail();
        LanguageCode = "EN";
        Offset = -480;
        ResponseXML = "<TemplateResponse><Template><TemplateTypeID> 261</TemplateTypeID><IsParent>1</IsParent><ResponseDetail><Response><TCollectionID>1156</TCollectionID><TCollectionValue>actual-site-photo.jpg</TCollectionValue></Response><Response><TCollectionID>1152</TCollectionID><TCollectionValue>Rahul Rana</TCollectionValue></Response><Response><TCollectionID>2596</TCollectionID><TCollectionValue>3411800000</TCollectionValue></Response><Response><TCollectionID>1154</TCollectionID><TCollectionValue>rahul003@mailinator.com</TCollectionValue></Response><Response><TCollectionID>1155</TCollectionID><TCollectionValue>Report Outage</TCollectionValue></Response><Response><TCollectionID>1162</TCollectionID><TCollectionValue>(999) 999-9999</TCollectionValue></Response><Response><TCollectionID>1163</TCollectionID><TCollectionValue>Select Type Of Outage</TCollectionValue></Response><Response><TCollectionID>1171</TCollectionID><TCollectionValue>E Street</TCollectionValue></Response><Response><TCollectionID>1172</TCollectionID><TCollectionValue>8787</TCollectionValue></Response><Response><TCollectionID>1173</TCollectionID><TCollectionValue>Irvine</TCollectionValue></Response><Response><TCollectionID>1174</TCollectionID><TCollectionValue>92602</TCollectionValue></Response><Response><TCollectionID>1175</TCollectionID><TCollectionValue>C Street</TCollectionValue></Response><Response><TCollectionID>2627</TCollectionID><TCollectionValue>November 17, 2021</TCollectionValue></Response></ResponseDetail></Template></TemplateResponse>";
        TemplateTypeId = 261;
        AttachmentXML = null;
        UserID = Integer.parseInt(user.getUserId());
        IsBillingQuery = 0;
        IsUpdate = false;
        SaveId = null;
        this.isEmailFromControl = true;
        GroupId = 0;
        IsMobile = false;
        this.trackingId = null;
        this.comments = null;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String Reason) {
        this.Reason = Reason;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
    }

    public Integer getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(Integer AccountNumber) {
        this.AccountNumber = AccountNumber;
    }

    public String getMessageBody() {
        return MessageBody;
    }

    public void setMessageBody(String MessageBody) {
        this.MessageBody = MessageBody;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String Latitude) {
        this.Latitude = Latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String Longitude) {
        this.Longitude = Longitude;
    }

    public Integer getTopicId() {
        return TopicId;
    }

    public void setTopicId(Integer TopicId) {
        this.TopicId = TopicId;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public String getUserSignature() {
        return UserSignature;
    }

    public void setUserSignature(String UserSignature) {
        this.UserSignature = UserSignature;
    }

    public Integer getPromotionId() {
        return PromotionId;
    }

    public void setPromotionId(Integer PromotionId) {
        this.PromotionId = PromotionId;
    }

    public Integer getIsDrMode() {
        return IsDrMode;
    }

    public void setIsDrMode(Integer IsDrMode) {
        this.IsDrMode = IsDrMode;
    }

    public String getAttachmentName() {
        return AttachmentName;
    }

    public void setAttachmentName(String AttachmentName) {
        this.AttachmentName = AttachmentName;
    }

    public Boolean getIsPreLogin() {
        return IsPreLogin;
    }

    public void setIsPreLogin(Boolean IsPreLogin) {
        this.IsPreLogin = IsPreLogin;
    }

    public String getUtilityAccountNumber() {
        return UtilityAccountNumber;
    }

    public void setUtilityAccountNumber(String UtilityAccountNumber) {
        this.UtilityAccountNumber = UtilityAccountNumber;
    }

    public String getFromEMail() {
        return FromEMail;
    }

    public void setFromEMail(String FromEMail) {
        this.FromEMail = FromEMail;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String LanguageCode) {
        this.LanguageCode = LanguageCode;
    }

    public Integer getOffset() {
        return Offset;
    }

    public void setOffset(Integer Offset) {
        this.Offset = Offset;
    }

    public String getResponseXML() {
        return ResponseXML;
    }

    public void setResponseXML(String ResponseXML) {
        this.ResponseXML = ResponseXML;
    }

    public Integer getTemplateTypeId() {
        return TemplateTypeId;
    }

    public void setTemplateTypeId(Integer TemplateTypeId) {
        this.TemplateTypeId = TemplateTypeId;
    }

    public String getAttachmentXML() {
        return AttachmentXML;
    }

    public void setAttachmentXML(String AttachmentXML) {
        this.AttachmentXML = AttachmentXML;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    public Integer getIsBillingQuery() {
        return IsBillingQuery;
    }

    public void setIsBillingQuery(Integer IsBillingQuery) {
        this.IsBillingQuery = IsBillingQuery;
    }

    public Boolean getUpdate() {
        return IsUpdate;
    }

    public void setIsUpdate(Boolean IsUpdate) {
        this.IsUpdate = IsUpdate;
    }

    public String getSaveId() {
        return SaveId;
    }

    public void setSaveId(String SaveId) {
        this.SaveId = SaveId;
    }

    public Boolean getIsEmailFromControl() {
        return isEmailFromControl;
    }

    public void setIsEmailFromControl(Boolean isEmailFromControl) {
        this.isEmailFromControl = isEmailFromControl;
    }

    public Integer getGroupId() {
        return GroupId;
    }

    public void setGroupId(Integer GroupId) {
        this.GroupId = GroupId;
    }

    public Boolean getIsMobile() {
        return IsMobile;
    }

    public void setIsMobile(Boolean IsMobile) {
        this.IsMobile = IsMobile;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}

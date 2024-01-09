package sew.ai.api.pojos.connectme;

import sew.ai.models.ConnectMe;
import sew.ai.models.User;

public class ConnectMeProgramsPayload {

    String Reason;
    String Subject;
    int AccountNumber;
    String MessageBody;
    String Latitude;
    String Longitude;
    int TopicId;
    String CustomerName;
    String UserSignature;
    int PromotionId;
    int IsDrMode;
    String AttachmentName;
    Boolean IsPreLogin;
    String UtilityAccountNumber;
    String FromEMail;
    String LanguageCode;
    int Offset;
    String ResponseXML;
    int TemplateTypeId;
    String AttachmentXML;
    int UserID;
    int IsBillingQuery;
    boolean IsUpdate;
    String SaveId;
    boolean isEmailFromControl;
    int GroupId;
    boolean IsMobile;
    String trackingId;
    String comments;
    String AccountType;

    public ConnectMeProgramsPayload(User user, ConnectMe connectMe) {
        Reason = connectMe.getReason();
        Subject = connectMe.getSubject();
        AccountNumber = Integer.parseInt(user.getAccountNumber());
        MessageBody = connectMe.getMessageBody();
        Latitude = "";
        Longitude = "";
        TopicId = 47;
        CustomerName = connectMe.getCustomerName();
        UserSignature = "";
        PromotionId = 0;
        IsDrMode = 0;
        AttachmentName = null;
        IsPreLogin = connectMe.getIsPreLogin();
        UtilityAccountNumber = user.getDefaultUtilityAccNum();
        FromEMail = connectMe.getFromEMail();
        LanguageCode = "EN";
        Offset = 330;
        ResponseXML = "<TemplateResponse><Template><TemplateTypeID> 255</TemplateTypeID><IsParent>1</IsParent><ResponseDetail><Response><TCollectionID>2588</TCollectionID><TCollectionValue>411007037392</TCollectionValue></Response><Response><TCollectionID>3016</TCollectionID><TCollectionValue>Bodhit%20Test</TCollectionValue></Response><Response><TCollectionID>2909</TCollectionID><TCollectionValue>testscm11%40mailinator.com</TCollectionValue></Response><Response><TCollectionID>1099</TCollectionID><TCollectionValue>hi</TCollectionValue></Response><Response><TCollectionID>1101</TCollectionID><TCollectionValue>hi</TCollectionValue></Response><Response><TCollectionID>2851</TCollectionID><TCollectionValue>May%2011%2C%202022</TCollectionValue></Response></ResponseDetail></Template></TemplateResponse>";
        TemplateTypeId = 255;
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
        AccountType = null;
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

    public int getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(int accountNumber) {
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

    public int getTopicId() {
        return TopicId;
    }

    public void setTopicId(int topicId) {
        TopicId = topicId;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getUserSignature() {
        return UserSignature;
    }

    public void setUserSignature(String userSignature) {
        UserSignature = userSignature;
    }

    public int getPromotionId() {
        return PromotionId;
    }

    public void setPromotionId(int promotionId) {
        PromotionId = promotionId;
    }

    public int getIsDrMode() {
        return IsDrMode;
    }

    public void setIsDrMode(int isDrMode) {
        IsDrMode = isDrMode;
    }

    public String getAttachmentName() {
        return AttachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        AttachmentName = attachmentName;
    }

    public boolean isPreLogin() {
        return IsPreLogin;
    }

    public void setPreLogin(boolean preLogin) {
        IsPreLogin = preLogin;
    }

    public String getUtilityAccountNumber() {
        return UtilityAccountNumber;
    }

    public void setUtilityAccountNumber(String utilityAccountNumber) {
        UtilityAccountNumber = utilityAccountNumber;
    }

    public String getFromEMail() {
        return FromEMail;
    }

    public void setFromEMail(String fromEMail) {
        FromEMail = fromEMail;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }

    public int getOffset() {
        return Offset;
    }

    public void setOffset(int offset) {
        Offset = offset;
    }

    public String getResponseXML() {
        return ResponseXML;
    }

    public void setResponseXML(String responseXML) {
        ResponseXML = responseXML;
    }

    public int getTemplateTypeId() {
        return TemplateTypeId;
    }

    public void setTemplateTypeId(int templateTypeId) {
        TemplateTypeId = templateTypeId;
    }

    public String getAttachmentXML() {
        return AttachmentXML;
    }

    public void setAttachmentXML(String attachmentXML) {
        AttachmentXML = attachmentXML;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getIsBillingQuery() {
        return IsBillingQuery;
    }

    public void setIsBillingQuery(int isBillingQuery) {
        IsBillingQuery = isBillingQuery;
    }

    public boolean isUpdate() {
        return IsUpdate;
    }

    public void setUpdate(boolean update) {
        IsUpdate = update;
    }

    public String getSaveId() {
        return SaveId;
    }

    public void setSaveId(String saveId) {
        SaveId = saveId;
    }

    public boolean isEmailFromControl() {
        return isEmailFromControl;
    }

    public void setEmailFromControl(boolean emailFromControl) {
        isEmailFromControl = emailFromControl;
    }

    public int getGroupId() {
        return GroupId;
    }

    public void setGroupId(int groupId) {
        GroupId = groupId;
    }

    public boolean isMobile() {
        return IsMobile;
    }

    public void setMobile(boolean mobile) {
        IsMobile = mobile;
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

    public String getAccountType() {
        return AccountType;
    }

    public void setAccountType(String accountType) {
        AccountType = accountType;
    }
}

package sew.ai.api.pojos.service;

public class ServiceMoveOutPayload {
	
	
	int PlaceHolderID;
	String Reason;
	String Subject;
	int AccountNumber;
	String MessageBody;
	String Latitude;
	String Longitude;
	boolean IsPreLogin;
	String UtilityAccountNumber;
	int UtilityId;
	String FromEMail;
	String LanguageCode;
	String ResponseXML;
	int TemplateTypeId;
	String AttachmentXML;
	int UserID;
	String LocationAddress;
	int serviceid;
	String SaveId;
	boolean isEmailFromControl;
	int GroupId;
	String serviceRequestId;
	String CustomerName;
	String comments;
	
	public ServiceMoveOutPayload(int AccountNumber,String UtilityAccountNumber,String FromEMail,int UserID )
	{
		this.PlaceHolderID=3;
		this.Reason ="Move Out";
		this.Subject ="Move Out";
		this.AccountNumber =AccountNumber;
		this.MessageBody="";
		this.Latitude ="37.4224764";
		this.Longitude ="-122.0842499";
		this.IsPreLogin =false;
		this.UtilityAccountNumber = UtilityAccountNumber;
		this.UtilityId =0;
		this.FromEMail =FromEMail;
		this.LanguageCode ="EN";
		this.ResponseXML="<TemplateResponse><Template><TemplateTypeID> 310</TemplateTypeID><IsParent>1</IsParent>"
				+ "<ResponseDetail><Response><TCollectionID>2591</TCollectionID><TCollectionValue>2244567890"
				+ "</TCollectionValue></Response><Response><TCollectionID>1798</TCollectionID><TCollectionValue>"
				+ "August%2029%2C%202022</TCollectionValue></Response><Response><TCollectionID>1800</TCollectionID>"
				+ "<TCollectionValue>Sep%2013%2C2022</TCollectionValue></Response><Response><TCollectionID>3011"
				+ "</TCollectionID><TCollectionValue>test</TCollectionValue></Response><Response><TCollectionID>3014"
				+ "</TCollectionID><TCollectionValue>test</TCollectionValue></Response><Response><TCollectionID>1803"
				+ "</TCollectionID><TCollectionValue>(999)%20999-9999</TCollectionValue></Response><Response>"
				+ "<TCollectionID>1804</TCollectionID><TCollectionValue>(888)%20888-8888</TCollectionValue>"
				+ "</Response><Response><TCollectionID>1805</TCollectionID><TCollectionValue>swati.kumari%40smartenergywater.in"
				+ "</TCollectionValue></Response><Response><TCollectionID>2912</TCollectionID><TCollectionValue>Home</TCollectionValue>"
				+ "</Response><Response><TCollectionID>1808</TCollectionID><TCollectionValue>DASD</TCollectionValue></Response>"
				+ "<Response><TCollectionID>1810</TCollectionID><TCollectionValue>CHINO%20HILLS</TCollectionValue></Response>"
				+ "<Response><TCollectionID>2570</TCollectionID><TCollectionValue>s_1</TCollectionValue></Response><Response>"
				+ "<TCollectionID>1812</TCollectionID><TCollectionValue>91709</TCollectionValue></Response></ResponseDetail>"
				+ "</Template></TemplateResponse>";
		this.TemplateTypeId =310;
		this.AttachmentXML =null;
		this.UserID = UserID;
		this.LocationAddress ="19900 MACARTHUR BLVD.";
		this.serviceid =31;
		this.SaveId ="";
		this.isEmailFromControl =true;
		this.GroupId =0;
		this.serviceRequestId =null;
		this.CustomerName ="test test";
		this.comments =null;
	}
	
	public int getPlaceHolderID() {
		return PlaceHolderID;
	}
	public void setPlaceHolderID(int placeHolderID) {
		PlaceHolderID = placeHolderID;
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
	public boolean isIsPreLogin() {
		return IsPreLogin;
	}
	public void setIsPreLogin(boolean isPreLogin) {
		IsPreLogin = isPreLogin;
	}
	public String getUtilityAccountNumber() {
		return UtilityAccountNumber;
	}
	public void setUtilityAccountNumber(String utilityAccountNumber) {
		UtilityAccountNumber = utilityAccountNumber;
	}
	public int getUtilityId() {
		return UtilityId;
	}
	public void setUtilityId(int utilityId) {
		UtilityId = utilityId;
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
	public String getLocationAddress() {
		return LocationAddress;
	}
	public void setLocationAddress(String locationAddress) {
		LocationAddress = locationAddress;
	}
	public int getServiceid() {
		return serviceid;
	}
	public void setServiceid(int serviceid) {
		this.serviceid = serviceid;
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
	public void setEmailFromControl(boolean isEmailFromControl) {
		this.isEmailFromControl = isEmailFromControl;
	}
	public int getGroupId() {
		return GroupId;
	}
	public void setGroupId(int groupId) {
		GroupId = groupId;
	}
	public String getServiceRequestId() {
		return serviceRequestId;
	}
	public void setServiceRequestId(String serviceRequestId) {
		this.serviceRequestId = serviceRequestId;
	}
	public String getCustomerName() {
		return CustomerName;
	}
	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
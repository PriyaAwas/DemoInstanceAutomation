package sew.ai.api.pojos.service;

public class ServicePayload {

	int   PlaceHolderID =3;
	String    Reason= "Others";
	String  Subject ="Others";
	int  AccountNumber =6473;
	String   MessageBody ="";
	String Latitude ="42.331429";
	String Longitude = "-83.045753";
	boolean IsPreLogin =false;
	String UtilityAccountNumber ="411002248606";
	int UtilityId =0;
	String FromEMail = "nikhil.test@mailinator.com";
	String LanguageCode ="EN";
	String ResponseXML ="<TemplateResponse><Template><TemplateTypeID>308</TemplateTypeID>" 
			+ "<IsParent>1</IsParent><ResponseDetail><Response>"
			+"<TCollectionID>2680</TCollectionID><TCollectionValue>411002248606</TCollectionValue>"
			+ "</Response><Response><TCollectionID>1760</TCollectionID><TCollectionValue>August%2022%2C%202022"
			+" </TCollectionValue></Response><Response><TCollectionID>1761</TCollectionID><TCollectionValue>Aug%2024%2C2022"
			+"</TCollectionValue></Response><Response><TCollectionID>1763</TCollectionID><TCollectionValue>2"
			+"</TCollectionValue></Response><Response><TCollectionID>1764</TCollectionID><TCollectionValue>false"
			+"</TCollectionValue></Response><Response><TCollectionID>1765</TCollectionID><TCollectionValue>(790)%20656-7903"
			+"</TCollectionValue></Response><Response><TCollectionID>1766</TCollectionID><TCollectionValue>false"
			+"</TCollectionValue></Response></ResponseDetail></Template></TemplateResponse>";
	int   TemplateTypeId = 308;
	String 	AttachmentXML=null;
	int 	UserID =3301;
	String	LocationAddress ="255 SAINT MARK RD";
	int	serviceid =514;
	String	SaveId ="";
	boolean	isEmailFromControl =false;
	int	GroupId =0;
	String	serviceRequestId =null;
	String	CustomerName ="undefined undefined";
	String	comments = null;
	
	public ServicePayload(int AccountNumber,String UtilityAccountNumber,String FromEMail,int UserID )
	{
		
		  PlaceHolderID =3;
		  Reason= "Others";
		 Subject ="Others";
		// AccountNumber =6473;
		 this.AccountNumber = AccountNumber;
		  MessageBody ="";
		 Latitude ="42.331429";
		 Longitude = "-83.045753";
		 IsPreLogin =false;
		// UtilityAccountNumber ="411002248606";
		 this.UtilityAccountNumber = UtilityAccountNumber;
		 UtilityId =0;
		// FromEMail = "nikhil.test@mailinator.com";
		 this.FromEMail = FromEMail;
		 LanguageCode ="EN";
		 ResponseXML ="<TemplateResponse><Template><TemplateTypeID>308</TemplateTypeID>" 
				+ "<IsParent>1</IsParent><ResponseDetail><Response>"
				+"<TCollectionID>2680</TCollectionID><TCollectionValue>"+UtilityAccountNumber+"</TCollectionValue>"
				+ "</Response><Response><TCollectionID>1760</TCollectionID><TCollectionValue>August%2022%2C%202022"
				+" </TCollectionValue></Response><Response><TCollectionID>1761</TCollectionID><TCollectionValue>Aug%2024%2C2022"
				+"</TCollectionValue></Response><Response><TCollectionID>1763</TCollectionID><TCollectionValue>2"
				+"</TCollectionValue></Response><Response><TCollectionID>1764</TCollectionID><TCollectionValue>false"
				+"</TCollectionValue></Response><Response><TCollectionID>1765</TCollectionID><TCollectionValue>(790)%20656-7903"
				+"</TCollectionValue></Response><Response><TCollectionID>1766</TCollectionID><TCollectionValue>false"
				+"</TCollectionValue></Response></ResponseDetail></Template></TemplateResponse>";
		   	TemplateTypeId = 308;
		 	AttachmentXML=null;
			//UserID =3301;
			this.UserID = UserID;
			LocationAddress ="255 SAINT MARK RD";
			serviceid =514;
			SaveId ="";
			isEmailFromControl =false;
			GroupId =0;
			serviceRequestId =null;
			CustomerName ="undefined undefined";
			comments = null;
		
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

package sew.ai.api.pojos.services;

import sew.ai.models.Services;
import sew.ai.models.User;

public class ServicePayload {

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
	
	public ServicePayload(User user,Services service)
	{
		
		  PlaceHolderID =3;
		  Reason= service.getReason();
		 Subject =service.getSubject();
		// AccountNumber =6473;
		 this.AccountNumber = Integer.parseInt(user.getAccountNumber());
		  MessageBody ="";
		 Latitude ="42.331429";
		 Longitude = "-83.045753";
		 IsPreLogin = service.getIsPreLogin();
		// UtilityAccountNumber ="411002248606";
		 this.UtilityAccountNumber = user.getDefaultUtilityAccNum();
		 UtilityId =0;
		// FromEMail = "nikhil.test@mailinator.com";
		 this.FromEMail = user.getEmailId();
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
			this.UserID = Integer.parseInt(user.getUserId());
			LocationAddress =service.getLocationAddress();
			serviceid =514;
			SaveId ="";
			isEmailFromControl =false;
			GroupId =0;
			serviceRequestId =null;
			CustomerName =service.getCustomerName();
			comments = "comment";
		
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

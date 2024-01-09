package sew.ai.api.pojos.services;

import sew.ai.models.Services;
import sew.ai.models.User;

public class ServiceMoveInPayload {

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

	public ServiceMoveInPayload(User user,Services service) {
		this.PlaceHolderID = 3;
		this.Reason = service.getReason();
		this.Subject = service.getSubject();
		this.AccountNumber = Integer.parseInt(user.getAccountNumber());
		this.MessageBody = service.getMessageBody();
		this.Latitude = "37.4224764";
		this.Longitude = "-122.0842499";
		this.IsPreLogin = service.getIsPreLogin();
		this.UtilityAccountNumber = user.getDefaultUtilityAccNum();
		this.UtilityId = 0;
		this.FromEMail = user.getEmailId();
		this.LanguageCode = "EN";
		this.ResponseXML = "<TemplateResponse><Template><TemplateTypeID> 262</TemplateTypeID><IsParent>1</IsParent><ResponseDetail><Response><TCollectionID>2981</TCollectionID><TCollectionValue>2244567890</TCollectionValue></Response><Response><TCollectionID>1179</TCollectionID><TCollectionValue>August%2029%2C%202022</TCollectionValue></Response><Response><TCollectionID>1182</TCollectionID><TCollectionValue>test"
				+ "</TCollectionValue></Response><Response><TCollectionID>2982</TCollectionID><TCollectionValue>test</TCollectionValue>"
				+ "</Response><Response><TCollectionID>1184</TCollectionID><TCollectionValue>swati.kumari%40smartenergywater.in"
				+ "</TCollectionValue></Response><Response><TCollectionID>1185</TCollectionID><TCollectionValue>1234"
				+ "</TCollectionValue></Response><Response><TCollectionID>3082</TCollectionID><TCollectionValue>Home"
				+ "</TCollectionValue></Response><Response><TCollectionID>1186</TCollectionID><TCollectionValue>Mar%2012%2C2002"
				+ "</TCollectionValue></Response><Response><TCollectionID>2579</TCollectionID><TCollectionValue>(999)%20999-9999"
				+ "</TCollectionValue></Response><Response><TCollectionID>1192</TCollectionID><TCollectionValue>19900"
				+ "</TCollectionValue></Response><Response><TCollectionID>1193</TCollectionID><TCollectionValue>MACARTHUR%20BLVD."
				+ "</TCollectionValue></Response><Response><TCollectionID>1194</TCollectionID><TCollectionValue>Apartment"
				+ "</TCollectionValue></Response><Response><TCollectionID>1196</TCollectionID><TCollectionValue>Irvine"
				+ "</TCollectionValue></Response><Response><TCollectionID>1197</TCollectionID><TCollectionValue>s_1"
				+ "</TCollectionValue></Response><Response><TCollectionID>1198</TCollectionID><TCollectionValue>"
				+ "</TCollectionValue></Response><Response><TCollectionID>1201</TCollectionID><TCollectionValue>Aug%2029%2C2022"
				+ "</TCollectionValue></Response><Response><TCollectionID>1203</TCollectionID><TCollectionValue>qwrljd"
				+ "</TCollectionValue></Response><Response><TCollectionID>1204</TCollectionID><TCollectionValue>Apartment"
				+ "</TCollectionValue></Response><Response><TCollectionID>1206</TCollectionID><TCollectionValue>chino%20hills"
				+ "</TCollectionValue></Response><Response><TCollectionID>2598</TCollectionID><TCollectionValue>s_1"
				+ "</TCollectionValue></Response><Response><TCollectionID>1208</TCollectionID><TCollectionValue>91709"
				+ "</TCollectionValue></Response><Response><TCollectionID>1211</TCollectionID><TCollectionValue>Yes"
				+ "</TCollectionValue></Response><Response><TCollectionID>1213</TCollectionID><TCollectionValue>Street%20Address"
				+ "</TCollectionValue></Response><Response><TCollectionID>2603</TCollectionID><TCollectionValue>qwrljd"
				+ "</TCollectionValue></Response><Response><TCollectionID>2605</TCollectionID><TCollectionValue>Apartment"
				+ "</TCollectionValue></Response><Response><TCollectionID>1219</TCollectionID><TCollectionValue>chino%20hills"
				+ "</TCollectionValue></Response><Response><TCollectionID>1218</TCollectionID><TCollectionValue>s_1"
				+ "</TCollectionValue></Response><Response><TCollectionID>2568</TCollectionID><TCollectionValue>91709"
				+ "</TCollectionValue></Response><Response><TCollectionID>1224</TCollectionID><TCollectionValue>sas"
				+ "</TCollectionValue></Response></ResponseDetail></Template><Template><TemplateTypeID>391</TemplateTypeID>"
				+ "<IsParent>0</IsParent><ResponseDetail><Response><TCollectionID>2603</TCollectionID><TCollectionValue>qwrljd"
				+ "</TCollectionValue></Response><Response><TCollectionID>2605</TCollectionID><TCollectionValue>Apartment"
				+ "</TCollectionValue></Response></ResponseDetail></Template></TemplateResponse>";
		this.TemplateTypeId = 262;
		this.AttachmentXML = null;
		this.UserID = Integer.parseInt(user.getUserId());
		this.LocationAddress = service.getLocationAddress();
		this.serviceid = 30;
		this.SaveId = "";
		this.isEmailFromControl = true;
		this.GroupId = 0;
		this.serviceRequestId = null;
		this.CustomerName = service.getCustomerName();
		this.comments = "comment";

	}

	public String getResponseXML() {
		return ResponseXML;
	}

	public void setResponseXML(String responseXML) {
		ResponseXML = responseXML;
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
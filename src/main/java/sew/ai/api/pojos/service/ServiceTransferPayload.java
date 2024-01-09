package sew.ai.api.pojos.service;

public class ServiceTransferPayload {
	
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
		String ResponseXML ;
		int  TemplateTypeId;
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
		public ServiceTransferPayload(int AccountNumber,String UtilityAccountNumber,String FromEMail,int UserID)
		{
			
			this.PlaceHolderID =3;
			this.Reason ="Service Transfer";
			this.Subject ="Service Transfer";
			this.AccountNumber =AccountNumber;
			this.MessageBody ="";
			this.Latitude ="37.4224764";
			this.Longitude ="-122.0842499";
			this.IsPreLogin =false;
			this.UtilityAccountNumber = UtilityAccountNumber;
			this.UtilityId =0;
			this.FromEMail = FromEMail;
			this.LanguageCode="EN";
			this.ResponseXML = "<TemplateResponse><Template><TemplateTypeID> 309</TemplateTypeID><IsParent>1</IsParent><ResponseDetail><Response><TCollectionID>2682</TCollectionID>"
					+ "<TCollectionValue>"+UtilityAccountNumber+"</TCollectionValue></Response><Response><TCollectionID>1771</TCollectionID><TCollectionValue>"
					+ "August%2029%2C%202022</TCollectionValue></Response><Response><TCollectionID>1773</TCollectionID><TCollectionValue>"
					+ "Aug%2031%2C2022</TCollectionValue></Response><Response><TCollectionID>1777</TCollectionID><TCollectionValue>csd"
					+ "</TCollectionValue></Response><Response><TCollectionID>1776</TCollectionID><TCollectionValue>32324</TCollectionValue>"
					+ "</Response><Response><TCollectionID>1779</TCollectionID><TCollectionValue>chino%20hills</TCollectionValue></Response>"
					+ "<Response><TCollectionID>2581</TCollectionID><TCollectionValue>s_1</TCollectionValue></Response><Response><TCollectionID>"
					+ "1780</TCollectionID><TCollectionValue>Sep%2005%2C2022</TCollectionValue></Response><Response><TCollectionID>1781"
					+ "</TCollectionID><TCollectionValue>91709</TCollectionValue></Response><Response><TCollectionID>3005</TCollectionID>"
					+ "<TCollectionValue>test</TCollectionValue></Response><Response><TCollectionID>3008</TCollectionID><TCollectionValue>test"
					+ "</TCollectionValue></Response><Response><TCollectionID>1784</TCollectionID><TCollectionValue>(999)%20999-9999"
					+ "</TCollectionValue></Response><Response><TCollectionID>1786</TCollectionID><TCollectionValue>swati.kumari%40smartenergywater.in"
					+ "</TCollectionValue></Response><Response><TCollectionID>2913</TCollectionID><TCollectionValue>Home</TCollectionValue></Response><Response>"
					+ "<TCollectionID>2345</TCollectionID><TCollectionValue>false</TCollectionValue></Response><Response><TCollectionID>1790</TCollectionID>"
					+ "<TCollectionValue>sasa</TCollectionValue></Response><Response><TCollectionID>1791</TCollectionID><TCollectionValue>3123</TCollectionValue>"
					+ "</Response><Response><TCollectionID>1792</TCollectionID><TCollectionValue>chino%20hills</TCollectionValue></Response><Response><TCollectionID>"
					+ "1794</TCollectionID><TCollectionValue>91709</TCollectionValue></Response><Response><TCollectionID>2582</TCollectionID><TCollectionValue>s_1"
					+ "</TCollectionValue></Response></ResponseDetail></Template></TemplateResponse>";
					this.TemplateTypeId =309;
					this.AttachmentXML =null;
					this.UserID =UserID;
					this.LocationAddress ="19900 MACARTHUR BLVD.";
					this.serviceid =34;
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
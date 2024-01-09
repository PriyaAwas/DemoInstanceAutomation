package sew.ai.helpers.props;

import sew.ai.utils.DateUtil;

public class SQLQueries {
	/*******************************************************
	 * >>>>>>>>>>>>> SCP RELATED SQL QUERIES <<<<<<<<<<<<<<*
	 *******************************************************/

	/*******************************************************
	 * >>>>>>>>>>>>>>>> LOGIN PAGE QUERIES <<<<<<<<<<<<<<< *
	 *******************************************************/
	public static String getDeActiveAccount() {
		String deActiveAccount = "select Top 1 username from [user] where status='2' order by 1 desc";
		return deActiveAccount;
	}

	public static String getPassword(String userName) {
		String password = "select password from [user] where username='" + userName + "'";
		return password;
	}

	public static String updatePassword(String userName, String password) {
		String updatePassword = "update [user] set Password='" + password + "' where username='" + userName + "'";
		return updatePassword;
	}

	public static String updateUsernameQuery(String userId, String changeUsername) {
		String sQuery = "UPDATE [User] SET UserName = '" + changeUsername + "'\n" + "WHERE UserID = '" + userId + "'";
		return sQuery;
	}

	public static String getUserLinkedAccounts(String userName) {
		return "select userid, accountnumber, utilityaccountnumber, isdefaultaccount\n" + "from useraccount\n"
				+ "where userid = (select userid from [user] \n" + "where username = '" + userName + "')";
	}

	/*********************************************************
	 * >>>>>>>>>>>>> LOGIN SUPPORT PAGE QUERIES <<<<<<<<<<<<<<*
	 *********************************************************/
	public static String registeredInactiveAccount = "SELECT UtilityAccountNumber, EmailID "
			+ "FROM CustomerInfo(NOLOCK) " + "WHERE UtilityAccountNumber IN (SELECT UtilityAccountNumber "
			+ "FROM CustomerInfo(NOLOCK) " + "WHERE AccountStatusID=2 " + "AND AccountNumber NOT IN (-1,2))";

	public static String getForgetPasswordEmailQuery(String userName) {
		String query = "SELECT TOP 1 Message FROM ContractAccountNotifyEmail "
				+ "WHERE EmailID = (SELECT EmailID FROM [User] " + "WHERE UserName = '" + userName + "') "
				+ "AND Subject = 'Smart Energy Water Password Reset Link' ORDER BY CreateDate DESC";
		return query;
	}

	public static String getSecurityQue1Query(String userName) {
		String query = "SELECT ControlText FROM multilingualmaster WHERE LanguageCode = 'EN' "
				+ "AND ControlId=(SELECT ControlId FROM SecurityQuestion WHERE QuestionId = "
				+ "(SELECT SecurityQuestionId FROM [user] WHERE UserName ='" + userName + "'))";
		return query;
	}

	public static String getSecurityQue2Query(String userName) {
		String query = "SELECT ControlText FROM multilingualmaster WHERE LanguageCode = 'EN' "
				+ "AND ControlId=(Select ControlId from SecurityQuestion "
				+ "WHERE QuestionId = (SELECT SecurityQuestionId2 FROM [user] WHERE UserName ='" + userName + "'))";
		return query;
	}

	public static String getRegNotActivatedUserQuery() {
		String query = "Select  CI.UtilityAccountNumber , U.EmailID, U.UserName "
				+ "from [User] U Join useraccount UA on U.Userid=UA.Userid \r\n"
				+ "join CustomerInfo CI on UA.UtilityAccountNumber = CI.UtilityAccountNumber "
				+ "where U.status=0 and CI.AddressType in  ('Residential', 'Commercial') "
				+ "and convert(date,U.LinkSentDate)>=convert(date,getdate()-3)\r\n" + "";
		return query;
	}

	public static String sSecurityLeveValue = "Select [Value] from TemplateDetail"
			+ " where ControlName = 'Security Level'";

	public static String getPasswordResetLinkEmailMsg(String emailId) {
		String sQuery = "SELECT TOP 1 ID, AccountNumber, EmailID, Subject, Message\n"
				+ "FROM ContractAccountNotifyEmail\n" + "WHERE EmailID = '" + emailId + "'\n"
				+ "AND Subject = 'SCM Password Reset Link'\n" + "ORDER BY ID DESC";
		return sQuery;
	}

	public static String getUsernameAssistanceEmailMsg(String subjectLine, String emailAddress) {
		String sQuery = "SELECT TOP 1 Message, IsNotify\n" + "FROM ContractAccountNotifyEmail\n" + "WHERE EmailID = '"
				+ emailAddress + "' " + "AND Subject = '" + subjectLine + "'\n" + "ORDER BY ID DESC";
		return sQuery;
	}

	public static String getProblemSignInInactiveAccDetails() {
		String query = "select distinct(ci.utilityaccountnumber), ci.accountstatusid, ci.zipcode, ci.emailid,\n"
				+ "ci.addresstype, ci.address1, ci.firstname, ci.lastname, ci.mobilephone,\n"
				+ "ci.drivinglicence, ci.accountstatus, ci.addresstype, amm.meternumber\n" + "from customerinfo as ci\n"
				+ "left join accountmetermapping as amm on ci.accountnumber = amm.accountnumber\n"
				+ "left join useraccount as ua on ua.utilityaccountnumber != ci.utilityaccountnumber\n"
				+ "where ci.accountstatusid = '2'";
		return query;
	}

	public static String getInactiveAccDetails() {
		String query = "select distinct u.username, u.emailid, ua.utilityaccountnumber, ua.userid, '2' as accountstatusid from useraccount ua \n"
				+ "inner join [user] u on u.userid = ua.userid \n" + "where ua.utilityaccountnumber in ( \n"
				+ "select distinct x.utilityaccountnumber from \n"
				+ "(select distinct(ci.utilityaccountnumber), ci.accountstatusid, ci.zipcode, ci.emailid, ci.address1, ci.firstname, ci.lastname, ci.mobilephone,\n"
				+ "ci.drivinglicence, ci.accountstatus, ci.addresstype, amm.meternumber from customerinfo as ci \n"
				+ "left join accountmetermapping as amm on ci.accountnumber = amm.accountnumber \n"
				+ "left join useraccount as ua on ua.utilityaccountnumber != ci.utilityaccountnumber \n"
				+ "where ci.accountstatusid = '2'\n" + ") x \n" + ")";
		return query;
	}

	public static String getLinkedAccountNumberCount(String userName) {
		return "select count(utilityaccountnumber) as count from useraccount \n"
				+ "where userid = ((select userid from [user]\n" + "where username = '" + userName + "'))";
	}

	public static String getCustomerAddressDetails(String utilityAccNum) {
		return "select cityname, statename, country \n" + "from customerinfo \n" + "where utilityaccountnumber = '"
				+ utilityAccNum + "'";
	}

	/*****************************************************
	 * >>>>>>> CUSTOMER REGISTRATION PAGE QUERIES <<<<<<< *
	 *****************************************************/
	public static String residentialInactiveUserIDQuery = "DECLARE @ExpirationDays SMALLINT, @ExpirationMinutes INT "
			+ "SELECT @ExpirationDays = REPLACE(CM.Name,' day(s)','') " + "FROM TemplateDetail T(NOLOCK) "
			+ "JOIN CommonMaster CM(NOLOCK) ON (CM.MasterCode = T.Value AND CM.MasterType = 'TempValidity') "
			+ "WHERE TempDetailID = 51 SELECT U.UserID FROM [User] U (NOLOCK) "
			+ "JOIN UserAccount UA(NOLOCK) ON U.UserID=UA.UserID "
			+ "JOIN Account IA(NOLOCK) ON IA.AccountNumber=UA.AccountNumber "
			+ "JOIN CustomerAddress CA(NOLOCK) ON CA.AddressId=IA.AddressId " + "WHERE U.Status=0  "
			+ "AND CA.AddressType=2 "
			+ "AND DATEDIFF(DAY,ISNULL(ReminderDate,U.CreatedDate),GETDATE())<=@ExpirationDays  ";

	public static String inactiveDetailsQuery = "SELECT EmailID, UtilityAccountNumber, ZipCode, MeterNumber "
			+ "FROM VCustomer WHERE UserID= ";

	public static String userNameFromQuery = "Select Top 1 UserName From [User] where UserName is NOT NULL "
			+ "AND UserId NOT IN (1,-1) AND UserName <> ''";

	public static String residentialAccountRegistered = "" + "SELECT Top 1 A.UtilityAccountNumber,A.AccountNumber "
			+ ",(CASE CA.AddressType WHEN 1 THEN 'Residential' ELSE 'Commercial' END) AS AddressType " + " "
			+ "FROM Account A(NOLOCK) " + "JOIN CustomerAddress CA(NOLOCK) ON A.AddressID=CA.AddressID "
			+ "WHERE A.Status=1 AND CA.AddressType=1";

	public static String commercialAccountRegistered = "" + "SELECT Top 1 A.UtilityAccountNumber,A.AccountNumber "
			+ ",(CASE CA.AddressType WHEN 1 THEN 'Residential' ELSE 'Commercial' END) AS AddressType " + " "
			+ "FROM Account A(NOLOCK) " + "JOIN CustomerAddress CA(NOLOCK) ON A.AddressID=CA.AddressID "
			+ "WHERE A.Status=1 AND CA.AddressType=2";

	/**
	 * This getRegistrationTemplateConfig query fetches the Min,Max, Mandatory
	 * Status, Validation against CIS related information form the Database Primary
	 * Contact Number 10 10 true true
	 *
	 * @return Field Name-Primary Contact Number, MinLegth-10, MaxLegth-10,
	 *         Mandatory -True, Validation Against CIS-true
	 */
	public static String getRegistrationTemplateConfig() {
		String sRegistrationTemplateConfig = "" + " SELECT * from( "
				+ "select a.ControlName AS ParentHead,b.ControlName,b.value, b.ScpStatus from TemplateDetail a "
				+ "left join TemplateDetail b on a.TempDetailID=b.Parentid "
				+ "where b.ControlName IN ('Min Length','Max Length','Mandatory','Validation Against CIS', 'Type') "
				+ " ) AS s " + " PIVOT " + "(max(value) "
				+ "	for ControlName IN ([Min Length],[Max Length],[Mandatory],[Validation Against CIS], [Type]) )as pvt";
		return sRegistrationTemplateConfig;
	}

	/**
	 * This getRegistrationData query fetches the information required to do a
	 * successful registration
	 *
	 * @param addType - 1 for Residential User, 2 For Business User
	 * @return CustomerID MobilePhone DrivingLicence customerNo UtilityAccountNumber
	 *         ZipCode Address1 SSNNumber CustomerType MeterNumber
	 */
	public static String getRegistrationData(String addType, String accStatus) {
		String sRegistrationData = "SELECT Top 1 c.CustomerID, c.MobilePhone, c.DrivingLicence, C.customerNo, "
				+ "CA.UtilityAccountNumber,CA.ZipCode,CA.Address1,1234 AS SSNNumber, "
				+ "(CASE WHEN CA.AddressType=1 THEN 'Residential' ELSE 'Commercial' END) AS CustomerType, "
				+ "MAX(AMM.MeterNumber) AS MeterNumber " + "FROM Customer c(NOLOCK) "
				+ "JOIN CustomerAddress CA(NOLOCK) ON c.CustomerID=ca.CustomerID "
				+ "JOIN Account a(NOLOCK) ON ca.AddressID = a.AddressID "
				+ "JOIN AccountMeterMapping AMM(NOLOCK) ON A.AccountNumber=AMM.AccountNumber "
				+ "LEFT JOIN (SELECT DISTINCT IA.AccountNumber "
				+ "FROM Customer IC(NOLOCK) JOIN CustomerAddress ICA(NOLOCK) ON IC.CustomerID = ICA.CustomerID "
				+ "JOIN Account IA(NOLOCK) ON IA.AddressID = ICA.AddressID "
				+ "JOIN UserAccount IUA(NOLOCK) ON IUA.AccountNumber=IA.AccountNumber) R ON A.AccountNumber = "
				+ "R.AccountNumber " + "WHERE R.AccountNumber IS NULL "
				+ "AND c.CustomerID NOT IN (1,-1, 10417, 10615, 10931) " + "AND CA.AddressType=" + addType + " "
				+ "AND a.Status='" + accStatus + "' " + "AND CA.PortalAccessType = 0 "
				+ "GROUP BY c.CustomerID, c.MobilePhone, C.customerNo, "
				+ "(CASE WHEN CA.AddressType = 1 THEN 'Residential' ELSE 'Commercial' END), "
				+ "c.DrivingLicence, CA.ZipCode, CA.Address1,C.SSNNumber,CA.UtilityAccountNumber";
		return sRegistrationData;
	}

	/*
	 * Get Residential account Registration data of a GIVEN ACCOUNT for
	 * registration.
	 *
	 */
	public static String getRegData(String addType, String accStatus) {
		if (addType.equals("Residential"))
			addType = "1";
		else if (addType.equals("Commercial"))
			addType = "2";
		String query = "select top 1 c.customerid, c.customerno, a.bpnumber, ca.utilityaccountnumber, ca.zipcode, \n"
				+ "c.mobilephone, c.drivinglicence, ca.address1, 1234 as ssnnumber, \n"
				+ "(case when ca.addresstype = 1 then 'Residential' else 'Commercial' end) \n"
				+ "as customertype, max(amm.meternumber) as meternumber\n" + "from customer c(nolock) \n"
				+ "join customeraddress ca (nolock) on c.customerid = ca.customerid\n"
				+ "join account a(nolock) on ca.addressid = a.addressid \n"
				+ "join accountmetermapping amm(nolock) on a.accountnumber = amm.accountnumber\n"
				+ "left join useraccount iua(nolock) on iua.customerid = c.customerid\n"
				+ "where iua.customerid is null and c.customerid not in (1, -1)\n" + "and ca.addresstype = " + addType
				+ " \n" + "and a.status = " + accStatus + " \n" + "and ca.portalaccesstype = 0\n"
				+ "group by c.customerid, c.mobilephone, c.customerno, \n"
				+ "(case when ca.addresstype = 1 then 'Residential' else 'Commercial' end),\n"
				+ "c.drivinglicence, ca.zipcode, ca.address1, c.ssnnumber, ca.utilityaccountnumber, a.bpnumber";
		return query;
	}

	public static String getRegisteredAccount() {
		String query = "select top 1 ua.utilityaccountnumber, ua.roleid, ua.userid, u.status, u.emailid \n"
				+ "from useraccount as ua\n" + "left join [user] as u on u.userid = ua.userid\n"
				+ "where ua.roleid = '3' and u.status = '1'";
		return query;
	}

	public static String getCustomerDetails(String utilityAccNo) {
		String query = "select top 1 ci.accountnumber, ci.utilityaccountnumber, ci.zipcode, ci.address1,\n"
				+ "ci.accountstatusid, ci.addresstype, ci.firstname, ci.lastname, ci.mobilephone, ci.emailid,\n"
				+ "ci.drivinglicence, ci.accountstatus, ci.addresstype, ci.addressid, ci.customerid,\n"
				+ "amm.meternumber, ca.homeinfostatus \n" + "from customerinfo as ci\n"
				+ "left join accountmetermapping as amm on ci.accountnumber = amm.accountnumber \n"
				+ "left join customeraddress as ca on ci.addressid = ca.addressid\n"
				+ "where ci.utilityaccountnumber = '" + utilityAccNo + "'";
		return query;
	}

	public static String getBillTypeCustomerDetails(String utilityAccNo) {
		String query = "select  * from useraccount where utilityAccountNumber = '" + utilityAccNo + "' "
				+ "and roleid='3';";
		return query;
	}

	public static String getBillingForAccount(String accountNumber) {
		String query = "select top 1 * from billing where accountnumber=" + accountNumber + " order by 1 desc";
		return query;
	}

	public static String getBillingDetailsForBillingID(String billingID) {
		String query = "select * from billingdetail where billingid=" + billingID + " and headid=25";
		return query;
	}

	public static String updateBillingDateForBillingID(String billingID, String newDate) {
		String query = "update billingdetail set value='" + newDate + "' where billingid=" + billingID
				+ " and headid=25";
		return query;
	}

	public static String getServiceAccountBillTypeData(String paperlessValue) {
		String query = "select top 1 * from account where Paperless = '" + paperlessValue + "' and "
				+ "UtilityAccountNumber NOT IN('R001001002','R0-10-10-1','C015058059') and status='1'";
		return query;
	}

	/**
	 * This getUserId is to get the user id for a username select PaymentAccount,
	 * CCExpMonth, CCExpYear, FirstName, BankName from PaymentProfiles where
	 * ExternalId= '431' and ProfileStatus=1 order by 1 desc.
	 */
	public static String getUserId(String username) {
		String userIDQuery = "select userid from [user] where username='" + username + "'";
		return userIDQuery;
	}

	public static String sCommercialInactiveUserIDQuery = "DECLARE @ExpirationDays SMALLINT, @ExpirationMinutes "
			+ "INT SELECT @ExpirationDays = REPLACE(CM.Name,' day(s)','') FROM TemplateDetail T(NOLOCK) "
			+ "JOIN CommonMaster CM(NOLOCK) ON(CM.MasterCode = T.Value AND CM.MasterType = 'TempValidity') "
			+ "WHERE TempDetailID = 51 SELECT U.UserID FROM [User] U (NOLOCK) "
			+ "JOIN UserAccount UA(NOLOCK) ON U.UserID = UA.UserID "
			+ "JOIN Account IA(NOLOCK) ON IA.AccountNumber = UA.AccountNumber "
			+ "JOIN CustomerAddress CA(NOLOCK) ON CA.AddressId = IA.AddressId "
			+ "WHERE U.Status = 0 AND CA.AddressType = 2 "
			+ "AND DATEDIFF(DAY, ISNULL(ReminderDate, U.CreatedDate), GETDATE())<=@ExpirationDays";

	public static String getContactTypeQuery = "select Name from commonmaster where mastertype='PhoneNumberType'";

	/**
	 * This method is to return the query for getting user id of the given user
	 * name.
	 *
	 * @param userName
	 * @return
	 */
	public static String getUserIdOfGivenUsernameQuery(String userName) {
		String sQuery = "SELECT UserID FROM [User] WHERE UserName = '" + userName + "'";
		return sQuery;
	}

	/**
	 * This method is to return stored procedure for deleting user with user id.
	 *
	 * @param userId
	 * @return
	 */
	public static String getStoredProcedureForDeletingUserWithUserId(String userId) {
		String sQuery = "EXEC SetUnRegisterUser @UserID=" + userId;
		return sQuery;
	}

	/**
	 * This method is to get the registered user data query.
	 *
	 * @param userName
	 * @return
	 */
	public static String getRegisteredUserDataQuery(String userName) {
		String sQuery = "SELECT UserID, UserName, EmailID, [Status], SecurityQuestionId, SecurityQuestionId2, "
				+ "Zipcode, MobilePhone, FirstName, LastName FROM [User] WHERE UserName = '" + userName + "'";
		return sQuery;
	}

	public static String getRegistrationEmailContent(String emailId) {
		return "select top 1 ID,EmailID,Subject,Message,IsNotify,* from contractaccountnotifyEmail\n"
				+ "where emailid ='" + emailId + "'\n" + "order by 1 desc";
	}

	public static String getRegistrationEmailContentResendActivationLink(String emailId) {
		return "select top 1 * from contractaccountnotifyEmail\r\n" + "where emailid ='" + emailId
				+ "' and Subject = 'SCM - Account Activation'";
	}

	public static String getRegistrationEmailContentResendActivationLinkCSP(String emailId, String account) {
		String query = "select top 1 * from contractaccountnotifyEmail where emailid ='" + emailId
				+ "' and UtilityAccountNumber ='" + account
				+ "' and Subject = 'Smart Energy Water - Account Activation'";
		return query;
	}

	public static String getProblemsSigningInEmailNotifications(String emailId) {
		return "select  top 1 * from ContractaccountNotifyEmail where EmailID = '" + emailId
				+ "'  and Subject = 'Smart Energy Water Other Login Problems' " + "order by 1 desc";
	}

	public static String getAccountStatus(String sUsername) {
		String sQuery = "SELECT Status, UserName \n" + "FROM [User] \n" + "WHERE UserName ='" + sUsername + "'";
		return sQuery;
	}

	public static String getCISDataFromCustomerInfo(String sUtilityAccountNumber) {
		String sQuery = "SELECT MobilePhone, EmailID FROM Customer \n"
				+ "WHERE CustomerId = (SELECT CustomerID FROM UserAccount " + "WHERE UtilityAccountNumber = '"
				+ sUtilityAccountNumber + "')";
		return sQuery;
	}

	public static String getCISDataFromAccountTable(String sUtilityAccountNumber) {
		String sQuery = "SELECT Paperless FROM Account\n" + "WHERE UtilityAccountNumber = '" + sUtilityAccountNumber
				+ "'";
		return sQuery;
	}

	public static String getAlreadyTakenUsername() {
		String sQuery = "select top 1 username from [user] order by userid desc";
		return sQuery;
	}

	public static String getDefaultAccQuery(String username) {
		String query = "select utilityaccountnumber \n" + "from useraccount \n"
				+ "where userid = (select userid from [user] where username = '" + username + "')\n"
				+ "and isdefaultaccount = '1'";
		return query;
	}

	public static String getEmailIdWithUsername(String username) {
		String query = "select emailid from [user]\n" + "where username = '" + username + "'";
		return query;
	}

	/***********************************************************
	 * >>>>>>>>>>> ONE TIME PAYMENT PAGE QUERIES <<<<<<<<<<<<< *
	 ***********************************************************/
	// This query is for accounts with -ve balance
	public static String getPostPaidResidentialAccountNegativeBalanceAmount = ";" + "DECLARE @AddressType TINYINT=1 "
			+ "IF OBJECT_ID('TempDB..#LatestBill','U') IS NOT NULL " + "DROP TABLE #LatestBill "
			+ "IF OBJECT_ID('TempDB..#LatestBill1','U') IS NOT NULL " + "DROP TABLE #LatestBill1 "
			+ "SELECT AccountNumber,MAX(BillingDate) AS BillingDate " + "INTO #LatestBill " + "FROM Billing(NOLOCK) "
			+ "GROUP BY AccountNumber " + "SELECT LB.AccountNumber,B.BillingID " + "INTO #LatestBill1 "
			+ "FROM #LatestBill LB "
			+ "JOIN Billing B(NOLOCK) ON LB.AccountNumber=B.AccountNumber AND LB.BillingDate=B.BillingDate "
			+ "SELECT TOP 1 CI.FullName,CI.Address1,CI.AddressType,CI.CityName,CI.StateName,CI.ZipCode, "
			+ "CI.MobilePhone,CI.EmailID,CI.UtilityAccountNumber,LB.AccountNumber,CONVERT(NUMERIC(18,2),Value) "
			+ "AS RemainingBalance " + "FROM #LatestBill1 LB "
			+ "JOIN BillingDetail BD(NOLOCK) ON LB.BillingID=BD.BillingID "
			+ "JOIN CustomerInfo CI(NOLOCK) ON LB.AccountNumber=CI.AccountNumber "
			+ "JOIN Account A With(NOLOCK) ON A.AccountNumber = CI.AccountNumber and A.Defaultpaymenttype=1 "
			+ "WHERE BD.HeadID=24 AND CONVERT(NUMERIC(18,2),Value)<=0 AND CI.AddressTypeID=@AddressType";

	public static String getPrePaidResidentialAccountNegativeBalanceAmount = ";" + "DECLARE @AddressType TINYINT=1 "
			+ "IF OBJECT_ID('TempDB..#LatestBill','U') IS NOT NULL " + "DROP TABLE #LatestBill "
			+ "IF OBJECT_ID('TempDB..#LatestBill1','U') IS NOT NULL " + "DROP TABLE #LatestBill1 "
			+ "SELECT AccountNumber,MAX(BillingDate) AS BillingDate " + "INTO #LatestBill " + "FROM Billing(NOLOCK) "
			+ "GROUP BY AccountNumber " + "SELECT LB.AccountNumber,B.BillingID " + "INTO #LatestBill1 "
			+ "FROM #LatestBill LB "
			+ "JOIN Billing B(NOLOCK) ON LB.AccountNumber=B.AccountNumber AND LB.BillingDate=B.BillingDate "
			+ "SELECT TOP 1 CI.FullName,CI.Address1,CI.AddressType,CI.CityName,CI.StateName,CI.ZipCode, "
			+ "CI.MobilePhone,CI.EmailID,CI.UtilityAccountNumber,LB.AccountNumber,CONVERT(NUMERIC(18,2),Value) "
			+ "AS RemainingBalance " + "FROM #LatestBill1 LB "
			+ "JOIN BillingDetail BD(NOLOCK) ON LB.BillingID=BD.BillingID "
			+ "JOIN CustomerInfo CI(NOLOCK) ON LB.AccountNumber=CI.AccountNumber "
			+ "JOIN Account A With(NOLOCK) ON A.AccountNumber = CI.AccountNumber and A.Defaultpaymenttype=1 "
			+ "WHERE BD.HeadID=24 AND CONVERT(NUMERIC(18,2),Value)<=0 AND CI.AddressTypeID=@AddressType";

	public static String getPostPaidCommercialAccountNegativeBalanceAmountQuery = "DECLARE @AddressType TINYINT=2 "
			+ "IF OBJECT_ID('TempDB..#LatestBill','U') IS NOT NULL " + "DROP TABLE #LatestBill "
			+ "IF OBJECT_ID('TempDB..#LatestBill1','U') IS NOT NULL " + "DROP TABLE #LatestBill1 "
			+ "SELECT AccountNumber,MAX(BillingDate) AS BillingDate " + "INTO #LatestBill " + "FROM Billing(NOLOCK) "
			+ "GROUP BY AccountNumber " + "SELECT LB.AccountNumber,B.BillingID " + "INTO #LatestBill1 "
			+ "FROM #LatestBill LB "
			+ "JOIN Billing B(NOLOCK) ON LB.AccountNumber=B.AccountNumber AND LB.BillingDate=B.BillingDate "
			+ "SELECT TOP 1 CI.FullName,CI.Address1,CI.AddressType,CI.CityName,CI.StateName,CI.ZipCode, "
			+ "CI.MobilePhone,CI.EmailID,CI.UtilityAccountNumber,LB.AccountNumber,CONVERT(NUMERIC(18,2),Value) "
			+ "AS RemainingBalance " + "FROM #LatestBill1 LB "
			+ "JOIN BillingDetail BD(NOLOCK) ON LB.BillingID=BD.BillingID "
			+ "JOIN CustomerInfo CI(NOLOCK) ON LB.AccountNumber=CI.AccountNumber "
			+ "JOIN Account A With(NOLOCK) ON A.AccountNumber = CI.AccountNumber and A.Defaultpaymenttype=1 "
			+ "WHERE BD.HeadID=24 AND CONVERT(NUMERIC(18,2),Value)<=0 AND CI.AddressTypeID=@AddressType";

	public static String getPrePaidCommercialAccountNegativeBalanceAmount = ";" + "DECLARE @AddressType TINYINT=2 "
			+ "IF OBJECT_ID('TempDB..#LatestBill','U') IS NOT NULL " + "DROP TABLE #LatestBill "
			+ "IF OBJECT_ID('TempDB..#LatestBill1','U') IS NOT NULL " + "DROP TABLE #LatestBill1 "
			+ "SELECT AccountNumber,MAX(BillingDate) AS BillingDate " + "INTO #LatestBill " + "FROM Billing(NOLOCK) "
			+ "GROUP BY AccountNumber " + "SELECT LB.AccountNumber,B.BillingID " + "INTO #LatestBill1 "
			+ "FROM #LatestBill LB "
			+ "JOIN Billing B(NOLOCK) ON LB.AccountNumber=B.AccountNumber AND LB.BillingDate=B.BillingDate "
			+ "SELECT TOP 1 CI.FullName,CI.Address1,CI.AddressType,CI.CityName,CI.StateName,CI.ZipCode, "
			+ "CI.MobilePhone,CI.EmailID,CI.UtilityAccountNumber,LB.AccountNumber,CONVERT(NUMERIC(18,2),Value) "
			+ "AS RemainingBalance " + "FROM #LatestBill1 LB "
			+ "JOIN BillingDetail BD(NOLOCK) ON LB.BillingID=BD.BillingID "
			+ "JOIN CustomerInfo CI(NOLOCK) ON LB.AccountNumber=CI.AccountNumber "
			+ "JOIN Account A With(NOLOCK) ON A.AccountNumber = CI.AccountNumber and A.Defaultpaymenttype=0 "
			+ "WHERE BD.HeadID=24 AND CONVERT(NUMERIC(18,2),Value)<=0 AND CI.AddressTypeID=@AddressType";

	public static String getUserBillWhoseNotGenerated = ""
			+ "SELECT TOP 1 CI.FullName, CI.Address1, CI.AddressType, CI.CityName, CI.StateName, CI.ZipCode, "
			+ "CI.MobilePhone,CI.EmailID,CI.UtilityAccountNumber,CI.AccountNumber" + " FROM CustomerInfo CI(NOLOCK)"
			+ " LEFT JOIN" + "( " + "SELECT DISTINCT AccountNumber " + "FROM Billing(NOLOCK) "
			+ ")B ON CI.AccountNumber=B.AccountNumber " + "WHERE CI.AccountStatusID=1 AND B.AccountNumber IS NULL";

	/**
	 * This method returns payment received mail query.
	 *
	 * @param userId
	 * @param mailSub
	 * @return
	 */
	public static String getPaymentMailsQuery(String userId, String mailSub) {
		String query = "SELECT TOP 1 Message, IsNotify, CreateDate FROM ContractAccountNotifyEmail\n"
				+ "WHERE UserID = '" + userId + "'\n" + "AND Subject = '" + mailSub + "'\n" + "ORDER BY ID DESC";
		return query;
	}

	/***********************************************************
	 * >>>>>>>>>>>>>>>> SIGN OUT PAGE QUERIES <<<<<<<<<<<<<<<< *
	 ***********************************************************/

	/***********************************************************
	 * >>>>>>>>>>>>>>>> DASHBOARD PAGE QUERIES <<<<<<<<<<<<<<<< *
	 ***********************************************************/
	public static String getCompareDataForDashboardWidget(String utilityAccountNumber) {
		String query = "SELECT UtilityAccountNumber, Usagedate, [Value], Consumed, FromDate, ToDate "
				+ "FROM CompareDataLanding\n" + "WHERE UtilityAccountNumber = '" + utilityAccountNumber + "'\n"
				+ "ORDER BY UsageDate DESC";
		return query;
	}

	public static String getExistingEncryptedPasswordQuery(String userName) {
		String query = "SELECT Password\n" + "FROM [User]\n" + "WHERE UserName = '" + userName + "'";
		return query;
	}

	public static String updateExistingPassGivenPass(String password, String userName) {
		String query = "UPDATE [User]\n" + "SET Password = '" + password + "'\n" + "WHERE UserName = '" + userName
				+ "'";
		return query;
	}

	public static String getUserFullName(String sUserName) {
		String query = "SELECT FirstName, LastName  FROM [user] WHERE username='" + sUserName + "'";
		return query;
	}

	public static String getAutoPayEnrollStatus(String accountNumber) {
		String status = "SELECT PayTypeId  FROM AccountRecurringPayment "
				+ "WHERE AccountNumber=(SELECT AccountNumber FROM Account WHERE UtilityAccountNumber='" + accountNumber
				+ "')";
		return status;
	}

	public static String getProjectedUsageForAMI(String accountNumber) {
		String query = "SELECT PowerExpectedUsage,WaterExpectedUsage,GasExpectedUsage,SolarExpectedUsage "
				+ "FROM usagegenerationsummary WHERE AccountNumber='" + accountNumber + "'";
		return query;
	}

	public static String getAccountNumber(String utilityAccountNumber) {
		String query = "SELECT AccountNumber FROM Account WHERE UtilityAccountNumber='" + utilityAccountNumber + "'";
		return query;
	}

	public static String getBillingDate(String utilityAccountNumber) {
		String query = "SELECT BillingDate FROM Billing "
				+ "WHERE AccountNumber= (SELECT AccountNumber FROM Account WHERE UtilityAccountNumber='"
				+ utilityAccountNumber + "' )";
		return query;
	}

	public static String getPaymentMethod(String utilityAccountNumber, String UserName) {
		String query = "SELECT DefaultPaymentType FROM  UserAccount WHERE UtilityAccountNumber='" + utilityAccountNumber
				+ "' AND UserID=(Select UserID from [User] where UserName ='" + UserName + "')";
		return query;
	}

	public static String getIsAMIStatus(String utilityAccountNumber) {
		String query = "SELECT	IsAMI FROM AccountMeterMapping "
				+ "WHERE AccountNumber = (SELECT AccountNumber FROM Account WHERE UtilityAccountNumber='"
				+ utilityAccountNumber + "')";
		return query;
	}

	/***********************************************************
	 * >>>>>>>>>>> ACCOUNTS PROFILE PAGE QUERIES <<<<<<<<<<<<< *
	 ***********************************************************/
	/**
	 * Getting the profile information for a user
	 *
	 * @param Username
	 * @return sProfileInfo query which brings Username, email id, Mobile number
	 *         etc.
	 */
	public static String getMyAccountProfileInfo(String Username) {
		String sProfileInfo = "Select * from [User] where UserName ='" + Username + "'";
		return sProfileInfo;
	}

	/**
	 * This query brings the default account type whether commercial(2) or
	 * residential(1).
	 */
	public static String sDefaultUserAccountType = ""
			+ "SELECT distinct AddressType FROM VCustomer WHERE IsDefaultAccount=1 and UserName='";

	public static String getDefaultAccAddressType(String userName) {
		return "select addresstype from customerinfo where utilityaccountnumber = (\n"
				+ "select utilityaccountnumber from useraccount \n"
				+ "where userid = (select userid from [user] where username = '" + userName + "')\n"
				+ "and isdefaultaccount = '1')";
	}

	/**
	 * This query brings the default account and address in the Adress list in the
	 * header in the below format. 3456, NorthDakota Ave (C002002003)
	 *
	 * @param sUserName
	 * @return
	 */
	public static String getDefaultAccountAddressHeader(String sUserName) {
		String sDefaultAccountAddressHeaderQuery = "DECLARE @AccountNo INT=(SELECT UserId FROM [User] WHERE UserName='"
				+ sUserName + "')"
				+ " SELECT C.Address1+' ('+ UA.UtilityAccountNumber+')' as PropertyAddress FROM  [User] U (NOLOCK)"
				+ " JOIN  UserAccount UA (NOLOCK) ON UA.UserID=U.UserID"
				+ " JOIN  CustomerInfo C (NOLOCK) ON UA.Accountnumber = C.AccountNumber WHERE U.userid=@AccountNo"
				+ " AND NOT EXISTS (SELECT 1 from GuestAccessRequest ga WHERE AccessExpiryDate < getdate()"
				+ " AND ua.RequestID=ga.RequestID) and UA.ISDefaultAccount=1 ORDER BY UA.IsDefaultAccount DESC";
		return sDefaultAccountAddressHeaderQuery;
	}

	public static String getGuestUserDetails(String utilityAccountNumber) {
		String query = "SELECT * from GuestAccessRequest where utilityaccountnumber = '" + utilityAccountNumber
				+ "' and RequestStatus =1";
		return query;
	}

	/**
	 * This query brings the default account and address in the Account Widget in
	 * Dashboard Page the header in the below format. 3456, NorthDakota Ave,Chino
	 * Hills,CA,-92602 (C002002003)
	 *
	 * @param sUserName
	 * @return
	 */
	public static String getDefaultAddressAccountWidget(String sUserName) {
		String sDefaultAccountAddressHeaderQuery = "SELECT DISTINCT (Address1 +', '+ Address2 +', '+CityName+', '+(Select  StateCode from statemaster where StateName=(select Top 1 StateName from VCustomer WHERE IsDefaultAccount=1 and UserName='"
				+ sUserName
				+ "'))+' - '+ZipCode+' ('+ UtilityAccountNumber+')') AS AddressAccountNumber FROM VCustomer WHERE IsDefaultAccount=1 and UserName='"
				+ sUserName + "'";
		return sDefaultAccountAddressHeaderQuery;
	}

	/**
	 * This method is to get the query to get all the accounts having owner access.
	 *
	 * @param userName
	 * @return
	 */
	public static String getAccountsWithOwnerAccess(String userName) {
		String sAccountsHavingOwnerAccessQuery = "SELECT [UserAccount].UtilityAccountNumber FROM [UserAccount] "
				+ "Full OUTER JOIN [User] on [UserAccount].UserID=[User].UserID where [User].username='" + userName
				+ "' " + "and [UserAccount].RoleId='3'";
		return sAccountsHavingOwnerAccessQuery;
	}

	/**
	 * This query brings the all account and address in the Address list in the
	 * header in the below format. 3456, NorthDakota Ave (C002002003)
	 *
	 * @param sUserName
	 * @return
	 */
	public static String getAllAccountAddressHeader(String sUserName) {
		String sAllAccountAddressHeader = "DECLARE @AccountNo INT=(SELECT UserId FROM [User] WHERE UserName='"
				+ sUserName + "') "
				+ "SELECT C.Address1+' ('+ UA.UtilityAccountNumber+')' as PropertyAddress FROM  [User] U (NOLOCK) "
				+ "JOIN  UserAccount UA (NOLOCK) ON UA.UserID=U.UserID "
				+ "JOIN  CustomerInfo C (NOLOCK) ON UA.Accountnumber = C.AccountNumber "
				+ "JOIN Account ac ON UA.Accountnumber = ac.AccountNumber "
				+ " WHERE ac.status <> 3 and U.userid=@AccountNo "
				+ "AND NOT EXISTS (SELECT 1 from GuestAccessRequest ga "
				+ "WHERE AccessExpiryDate < getdate() AND ua.RequestID=ga.RequestID)  ";
		return sAllAccountAddressHeader;
	}

	/**
	 * This query brings the default account in the header in the below format
	 * C002002003
	 *
	 * @param userName
	 * @return
	 */
	public static String getDefaultAccount(String userName) {
		String defaultAccQuery = "select distinct UtilityAccountNumber, userid, accountnumber\n" + "from useraccount\n"
				+ "where userid = (select userid from [user]\n" + "where username = '" + userName
				+ "' and isdefaultaccount = '1')";
		return defaultAccQuery;
	}

	public static String getUserAccountDetails(String userName) {
		return "select u.userid, u.username, ci.utilityaccountnumber, ci.bpnumber, ci.lastname, ci.fullname,\n"
				+ "ci.emailid, ci.mobilephone, ci.addresstypeid, ci.addresstype, ci.accountnumber\n"
				+ "from [user] as u\n" + "left join useraccount as ua (nolock) on ua.userid = u.userid\n"
				+ "left join customerinfo as ci (nolock) on ci.utilityaccountnumber = ua.utilityaccountnumber\n"
				+ "where u.username = '" + userName + "' \n" + "and ua.isdefaultaccount = '1'";
	}

	public static String getUserAccountInfo(String userName) {
		return "select u.userid, u.username, ua.utilityaccountnumber, ci.bpnumber, ua.customerid, ua.roleid, \n"
				+ "ua.isdefaultaccount, ua.paperless, ua.emailnotify, ua.accountnumber, ci.addressid, ci.address1, \n"
				+ "ci.address2, ci.cityname, ci.zipcode\n" + "from [user] as u\n"
				+ "left join useraccount as ua (nolock) on ua.userid = u.userid\n"
				+ "left join customerinfo as ci (nolock) on ci.utilityaccountnumber = ua.utilityaccountnumber\n"
				+ "where ua.userid = (select userid from [user] where username = '" + userName + "')\n"
				+ "and ua.roleid = '3'";
	}

	public static String getCurrentMailingAddressInfo(String addressId, String userId) {
		return "select top 1 addressid, address1, address2, cityname, statename, zipcode,\n"
				+ "userid, mailaddresstype, expirydate \n" + "from usercommunicationaddress\n" + "where addressid = '"
				+ addressId + "' and userid = '" + userId + "'\n"
				+ "and (cast(expirydate as date)>getdate() or expirydate is null) \n" + "order By updateddate desc";
	}

	public static String getAccountLinkedToUser(String userName) {
		return "select count(*) as accountlinked from useraccount\n"
				+ "where userid = (select userid from [user] where username = '" + userName + "')";
	}

	public static String getMetersCountLinkedToAccount(String accountNumber) {
		return "select count(*) as metercount \n" + "from accountmetermapping\n" + "where accountnumber = '"
				+ accountNumber + "'";
	}

	/**
	 * This query brings the default UtilityAccountNumber and RoleID of the user
	 *
	 * @param userName
	 * @return
	 */
	public static String getDefaultAccountAndRoleId(String userName) {
		String defaultAccountQuery = "SELECT DISTINCT UtilityAccountNumber, RoleID  " + "FROM UserAccount "
				+ "WHERE UserID = (SELECT UserID FROM [User] WHERE UserName = '" + userName + "') "
				+ "AND IsDefaultAccount = '1'";
		return defaultAccountQuery;
	}

	public static String getAllAccountsLinkedToTheUserQuery(String userName) {
		String query = "SELECT DISTINCT UtilityAccountNumber\r\n" + "FROM UserAccount\r\n"
				+ "WHERE UserID = (SELECT UserID FROM [User] WHERE UserName = '" + userName + "')";
		// System.out.println(sQuery);
		return query;
	}

	public static String getAddressTypeQuery(String utilityAccountNumber) {
		String query = "SELECT AddressType " + "FROM CustomerInfo " + "WHERE UtilityAccountNumber = '"
				+ utilityAccountNumber + "'";
		return query;
	}

	public static String getZipCodeOfAccount(String utilityAccNumber) {
		return "select zipcode from customerinfo where utilityaccountnumber = '" + utilityAccNumber + "'";
	}

	/**
	 * This query brings the default
	 *
	 * @param userName
	 * @return
	 */
	public static String getDefaultAddressAccountNumber(String userName) {
		String defaultAddressAccount = "SELECT DISTINCT (Address1 +' ('+ UtilityAccountNumber+')') AS AddressAccountNumber FROM VCustomer \n"
				+ "WHERE IsDefaultAccount=1 AND UserName='" + userName + "'";
		return defaultAddressAccount;
	}

	/**
	 * This query brings all the accounts associated with a user login.
	 *
	 * @param userName
	 * @return
	 */
	public static String getAllUtilityAccountByUsername(String userName) {
		String sAllUtilityAccountByUsername = "SELECT UA.UtilityAccountNumber FROM UserAccount UA JOIN [User] U ON UA.UserID=U.UserID WHERE U.UserName='"
				+ userName + "'";
		return sAllUtilityAccountByUsername;
	}

	/**
	 * @param userName
	 * @param accountNumber
	 * @return
	 */
	public static String setDefaultAccount(String userName, String accountNumber) {
		String defaultAccount = "DECLARE @UserName NVARCHAR(100) DECLARE @UtilityAccountNumber VARCHAR(100)\n"
				+ "SET @UserName='" + userName + "' SET @UtilityAccountNumber='" + accountNumber + "'\n"
				+ "BEGIN TRANSACTION \n" + "UPDATE UA\n"
				+ "SET IsDefaultAccount = 0 FROM UserAccount UA JOIN [User] U ON UA.UserID = U.UserID\n"
				+ "WHERE U.UserName = @UserName \n" + "UPDATE UA SET IsDefaultAccount = 1\n"
				+ "FROM UserAccount UA JOIN [User] U ON UA.UserID=U.UserID\n"
				+ "WHERE U.UserName = @UserName AND UA.UtilityAccountNumber = @UtilityAccountNumber\n"
				+ "IF (SELECT COUNT(1) FROM UserAccount UA(NOLOCK) JOIN [User] U(NOLOCK) ON UA.UserID = U.UserID \n"
				+ "WHERE U.UserName = @UserName AND UA.IsDefaultAccount = 1) = 1\n" + "BEGIN  COMMIT TRANSACTION \n"
				+ "SELECT 'Update was successful.' AS [Message]\n" + "END\n" + "ELSE\n" + "BEGIN ROLLBACK TRANSACTION\n"
				+ "SELECT 'Update was not successful due to some mismatch in data.' AS [Message] END";
		return defaultAccount;
	}

	/**
	 * Getting all plan associated with a utility account
	 *
	 * @param utilityAccountNumber
	 * @return sAllAssociatedPlan query which brings all associated plans value with
	 *         utility account
	 */
	public static String getAllAssociatedPlans(String utilityAccountNumber) {
		String allAssociatedPlan = "SELECT AddressPowerPlanId, WaterPlanId, GasPlanId, PVPowerPlanId FROM Account WHERE UtilityAccountNumber='"
				+ utilityAccountNumber + "'";
		return allAssociatedPlan;
	}

	/**
	 * This minMaxLengthSecurityAns will give the result of Min and Max length of
	 * Security Answers
	 */
	public static String minMaxLengthSecurityAns = "SELECT ParentHead, [Min Length],[Max Length] from( select a.ControlName AS ParentHead,b.ControlName,b.value from TemplateDetail a left join TemplateDetail b on a.TempDetailID=b.Parentid where b.ControlName IN ('Min Length','Max Length')  ) AS s  PIVOT (max(value)	for ControlName IN ([Min Length],[Max Length]) )as pvt where ParentHead in ('Security Question 1','Security Question 2')";

	/**
	 * This minMaxLenZipCode will give the result of Min and Max length of Security
	 * Answers
	 */
	public static String minMaxLenZipCode = "SELECT ParentHead, [Min Length],[Max Length] from( select a.ControlName AS ParentHead,b.ControlName,b.value from TemplateDetail a left join TemplateDetail b on a.TempDetailID=b.Parentid where b.ControlName IN ('Min Length','Max Length')  ) AS s  PIVOT (max(value)	for ControlName IN ([Min Length],[Max Length]) )as pvt where ParentHead = 'Zip Code'";

	/**
	 * This mailingAddress will give the query for mailing Address of a user
	 */
	public static String getMailingAddress(String userName) {
		String mailingAddrQuery = "Select convert(varchar(30),ExpiryDate,101) as ExpDate, * from UserCommunicationAddress where MailAddressType = '1' and UserID= (select UserId from [User] where UserName='"
				+ userName + "')";
		return mailingAddrQuery;
	}

	/**
	 * This temporaryMailingAddress will give the query for temporary mailing
	 * Address of a user
	 */
	public static String getTemporaryMailingAddress(String userName) {
		String tempMailingAddrQuery = "Select convert(varchar(30),ExpiryDate,101) as ExpDate, * from UserCommunicationAddress where MailAddressType = '2' and UserID= (select UserId from [User] where UserName='"
				+ userName + "')";
		return tempMailingAddrQuery;
	}

	/**
	 * This method will give the query for current set mailing Address of a user
	 */
	public static String getCurrentMailingAddress(String userName) {
		String currentMailingAddrQuery = "Select top 1 convert(varchar(30),ExpiryDate,101) as ExpDate, * from UserCommunicationAddress "
				+ "where UserID= (select UserId from [User] where UserName='" + userName
				+ "') and (cast(ExpiryDate as date)>getdate() or ExpiryDate is null) order By UpdatedDate Desc";
		return currentMailingAddrQuery;
	}

	/**
	 * This method will give the query for current set mailing Address of an Account
	 */
	public static String getCurrentMailingAddressOfAnAccount(String accountNumber) {
		String currentMailingAddrQuery = "select top 1 convert(varchar(30),ExpiryDate,101) as ExpDate, * from UserCommunicationAddress where AddressId = (Select AddressId from Account where UtilityAccountNumber = '"
				+ accountNumber
				+ "') And (cast(ExpiryDate as date)>getdate() or ExpiryDate is null) order By UpdatedDate Desc";
		return currentMailingAddrQuery;
	}

	/**
	 * This method is to get all Accounts(Owner/Guest) of a User, takes Username as
	 * input and give all Accounts of this user
	 */
	public static String getAllLinkedAccountAccounts(String username) {
		String sQuery = "SELECT UtilityAccountNumber, RoleID FROM UserAccount where UserID = (Select UserID FROM [User] where UserName='"
				+ username + "')";
		return sQuery;
	}
	// Accounts

	/*
	 * Get Security Question by Question ID
	 */
	public static String getSecurityQueByItsId(String questionID) {
		String sQuery = "select ControlText from multilingualmaster where LanguageCode = 'EN' and ControlId=(Select ControlId from SecurityQuestion WHERE QuestionId = '"
				+ questionID + "')";
		return sQuery;
	}

	/*
	 * Query to get AddressID of Given Utility Account
	 */
	public static String getAddressIdOfUtilityAccount(String uAccount) {
		String sQuery = "Select AddressID from Account where UtilityAccountNumber = '" + uAccount + "'";
		return sQuery;
	}

	// Query for API
	public static String getInviteGuestUserAPIDataQuesy(String userName) {
		String query = "select u.userid, u.username, ua.RoleID, ci.utilityaccountnumber, ci.CustomerNo, ci.lastname, ci.fullname, ci.emailid, ci.mobilephone, \r\n"
				+ "ci.addresstypeid, ci.addresstype, ci.accountnumber from [user] as u left join useraccount as ua (nolock) \r\n"
				+ "on ua.userid = u.userid left join customerinfo as ci (nolock) on ci.utilityaccountnumber = ua.utilityaccountnumber \r\n"
				+ "where u.username = '" + userName + "' and ua.RoleID = '3'";
		return query;
	}

	// Query for API
	public static String getSetDefaultAccountAPIDataQuery(String userName) {
		String query = "select u.userid, u.username, ua.RoleID, ci.utilityaccountnumber, ci.CustomerNo, ci.lastname, ci.fullname, ci.emailid, ci.mobilephone, \r\n"
				+ "ci.addresstypeid, ci.addresstype, ci.accountnumber from [user] as u left join useraccount as ua (nolock) \r\n"
				+ "on ua.userid = u.userid left join customerinfo as ci (nolock) on ci.utilityaccountnumber = ua.utilityaccountnumber \r\n"
				+ "where u.username = '" + userName + "' and ua.IsDefaultAccount = '0'";
		return query;
	}

	/*******************************************
	 * >>>>> ACCOUNTS SETTING PAGE QUERIES <<<<<*
	 *******************************************/
	/**
	 * This query brings the my account setting info's saved by the account.
	 *
	 * @param UtilityAccountNumber
	 * @param Username
	 * @return
	 */
	public static String getMyAccountSettingConfig(String UtilityAccountNumber, String Username) {
		String sAccountSetting = "SELECT * FROM [UserAccount]  WHERE UtilityAccountNumber = '" + UtilityAccountNumber
				+ "'" + " AND UserID=(Select UserID from [User] where UserName ='" + Username + "'" + ")";
		// System.out.println("query : " + sAccountSetting);
		return sAccountSetting;
	}

	/**
	 * This query brings the my account setting info's saved by the account.
	 *
	 * @param Username
	 * @return
	 */
	public static String getMyAccountSettingLanguageConfig(String Username) {
		String sUserLanguageSetting = "Select * from LanguageMaster where LanguageGuId = (Select LanguageGuId from UserLanguage WHERE UserID =(Select UserID from [User] where UserName ='"
				+ Username + "'" + ")" + ")";
		return sUserLanguageSetting;
	}

	/**
	 * This method is to get the query to update the default payment method.
	 *
	 * @param sUsername
	 * @param sAccountNum
	 * @param sPaymentType |=> For 'Pay as you Go' = '0' and 'Monthly' = '1'
	 * @return
	 */
	public static String getQueryToUpdatePaymentMethodForAcc(String sAccountNum, String sUsername,
			String sPaymentType) {
		String sQuery = "UPDATE [UserAccount] SET DefaultPaymentType = '" + sPaymentType
				+ "' WHERE  UtilityAccountNumber = '" + sAccountNum
				+ "' AND UserID=(Select UserID from [User] where UserName ='" + sUsername + "');";
		return sQuery;
	}

	/**
	 * This method is to get the query to update the default payment method.
	 *
	 * @param sUsername
	 * @param sLanguageCode + Englist = EN, French = FR, Spanish = ES
	 * @return
	 */
	public static String getQueryToUpdateLanguageForUser(String sLanguageCode, String sUsername) {
		String sQuery = "UPDATE UserLanguage set LanguageGuId = (Select LanguageGuId from LanguageMaster where LanguageCode = '"
				+ sLanguageCode + "') WHERE UserID =(Select UserID from [User] where UserName ='" + sUsername + "')";
		return sQuery;
	}

	/**
	 * This method is to get the query to update the default payment method Monthly
	 * for all accounts associated with user.
	 *
	 * @param sUsername |=> For 'Pay as you Go' = '0' and 'Monthly' = '1'
	 * @return
	 */
	public static String getQueryToUpdateMonthlyPaymentMethodForAllOwnerAccounts(String sUsername) {
		String sQuery = "UPDATE [UserAccount] SET DefaultPaymentType = '1' WHERE DefaultPaymentType = '0' And RoleID ='3' And UserID=(Select UserID from [User] where UserName ='"
				+ sUsername + "');";
		return sQuery;
	}
	/************************************************************
	 * >>>>>>>> NOTIFICATION PREFERENCE PAGE QUERIES <<<<<<<<<< *
	 ************************************************************/
	/************************************************************
	 * >>>>>>>>>>>>> PAYMENT INFO PAGE QUERIES <<<<<<<<<<<<<<<< *
	 ************************************************************/
	/**
	 * This query brings the my account setting info's saved by the account.
	 *
	 * @param UtilityAccountNumber
	 * @param Username
	 * @return
	 */
	/*
	 * public static String getDefaultPayId(String UserId, String PaymentMethodType)
	 * { String sPaymentInfo =
	 * "select Top 1 PaymentAccount, CCExpMonth, CCExpYear, BankRTE, FirstName, BankName from PaymentProfiles where ExternalId= '"
	 * + UserId + "' and ProfileStatus=1 and PaymentMethodType=" + PaymentMethodType
	 * + " order by CreatedAt desc";
	 *
	 * return sPaymentInfo; }
	 */

	/**
	 * This query brings all the Payment info for a user id.
	 *
	 * @param UserId
	 * @return It comes from the Payment Database
	 */
	public static String getAllPaymentInfo(String UserId) {
		String sPaymentInfo = "Select FirstName,BankRTE, BankName , PaymentAccount, CCExpMonth, CCExpYear "
				+ "from PaymentProfiles where ProfileStatus =1 and ( (CCExpMonth>=month(getdate()) and CCExpYear=RIGHT(year(getdate()),2))OR "
				+ "( CCExpMonth>=1 and CCExpYear>RIGHT(year(getdate()),2))OR ACHType = 1 AND ISNULL(FirstName,'')!='') AND ExternalId= '"
				+ UserId + "' order by CreatedAt desc";
		return sPaymentInfo;
	}

	/**
	 * This method returns the latest payment method added for the user
	 *
	 * @param UserId
	 * @return
	 */
	public static String getLatestAddedPaymentInfo(String UserId) {
		String sPaymentInfo = "Select Top 1 FirstName,BankRTE, BankName , PaymentAccount, CCExpMonth, CCExpYear, ProfileStatus "
				+ "from PaymentProfiles where ProfileStatus =1 and ( (CCExpMonth>=month(getdate()) and CCExpYear=RIGHT(year(getdate()),2))OR "
				+ "( CCExpMonth>=1 and CCExpYear>RIGHT(year(getdate()),2))OR ACHType = 1 AND ISNULL(FirstName,'')!='') AND ExternalId= '"
				+ UserId + "' order by CreatedAt desc";
		return sPaymentInfo;
	}

	/**
	 * This method returns the latest payment method added for the user in Chase
	 * Database
	 *
	 * @param sAccountNo
	 * @return
	 */
	public static String getLatestAddedPaymentInfoChase(String sAccountNo) {
		String sPaymentInfo = "SELECT TOP 1 FirstName, BankName, BankRouting, BankAccountNumber, "
				+ "CCAccountNumber, CcExp, Cvv, PaymentType, ProfileStatus, IsDeleted, ACHType \n"
				+ "FROM PaymentProfiles\n" + "WHERE ServiceAccountNumber = '" + sAccountNo + "'\n"
				+ "ORDER BY CreatedDate DESC";
		return sPaymentInfo;
	}

	/**
	 * This query brings all the Payment info for a user id.
	 *
	 * @param UserId
	 * @return It comes from the Payment Database
	 */
	public static String getAllPaymentInfoChase(String UserId) {
		String sPaymentInfo = " Select FirstName, BankName, BankRouting, BankAccountNumber, "
				+ " CCAccountNumber, CcExp, Cvv, PaymentType, ProfileStatus, IsDeleted, ACHType "
				+ " FROM PaymentProfiles  where UserId in(select UserId from [SCM10_S].[dbo].[User] where UserName='"
				+ UserId + "') and IsDeleted=0 and ProfileStatus=3";
		return sPaymentInfo;
	}

	/**
	 * This query brings all the Payment info for a user id.
	 *
	 * @param UserId
	 * @return It comes from the Payment Database
	 */
	public static String getCountAllPaymentInfoChase(String UserId) {
		String sPaymentInfo = " Select count(*) as PaymentMethods"
				+ " FROM PaymentProfiles  where UserId in(select UserId from [SCM10_S].[dbo].[User] where UserName='"
				+ UserId + "') and IsDeleted=0 and ProfileStatus=3";
		return sPaymentInfo;
	}

	/**
	 * This method returns the latest payment method added for the user in API
	 * Payment Database
	 *
	 * @param sAccountNo
	 * @return
	 */
	public static String getStatusPaymentInfo(String sAccountNo) {
		String sPaymentInfo = "select top 1 PaymentProfileId, FirstName, BankName, BankRouting, BankAccountNumber, "
				+ "CCAccountNumber, CcExp, Cvv, PaymentType, ProfileStatus, IsDeleted, ACHType \n"
				+ "from paymentprofiles\n" + "where ServiceAccountNumber = '" + sAccountNo + "'\n"
				+ "order by CreatedDate desc";
		return sPaymentInfo;
	}

	public static String getDeletedCountPaymentInfo(String accountNo, String paymentProfileId) {
		String query = "select count(paymentprofileid) as count\n" + "from paymentprofiles\n"
				+ "where serviceaccountnumber = '" + accountNo + "'\n" + "and paymentprofileid = '" + paymentProfileId
				+ "'";
		return query;
	}

	/**
	 * This method returns the Defualt Payid or Token which is by the
	 * getDefaultPaymentInfo method for getting the Default Payment Info
	 *
	 * @param Username
	 * @return
	 */
	public static String getDefaultPayToken(String Username) {
		String sPaymentToken = "SELECT DefaultPayId FROM defaultpayment acc "
				+ "WHERE UserId = (Select UserId from [USER] WHERE UserName='" + Username + "')";
		return sPaymentToken;
	}

	/**
	 * This method returns the external id which is by the getDefaultPaymentInfo
	 * method for getting the Default Payment Info
	 *
	 * @param Username
	 * @return
	 */
	public static String getExternalId(String Username) {
		String sExternalId = "Select UserId from [USER] WHERE UserName='" + Username + "'";
		return sExternalId;
	}

	/**
	 * This method returns the Default Payment Info for a User
	 *
	 * @param PaymentToken
	 * @return
	 */
	public static String getDefaultPaymentInfo(String PaymentToken, String ExternalId) {
		String sPaymentInfo = "select Top 1 FirstName,BankRTE, BankName , PaymentAccount, CCExpMonth, "
				+ "CCExpYear from PaymentProfiles where PaymentToken='" + PaymentToken + "'" + "  AND ExternalId='"
				+ ExternalId + "'" + "and FirstName IS NOT NULL order by CreatedAt desc";
		return sPaymentInfo;
	}

	/**
	 * This method deletes all the payment info for the user
	 *
	 * @return
	 */
	public static String getDeletePaymentInfo(String Username) {
		String sPaymentInfoDelete = "update [SCM10_S_API_Payment].[dbo].PaymentProfiles set profilestatus=2 "
				+ "where UserId in(select UserId from [SCM10_S].[dbo].[User] where UserName='" + Username + "')";
		return sPaymentInfoDelete;
	}

	/*****************************************************
	 * >>>> ACCOUNT MARKETING PREFERENCE PAGE QUERIES <<<<*
	 *****************************************************/
	public static String getMarketingPrefQuery(String username) {
		String marketingPrefQuery = "Select PreferenceId from UserMarketingPreferenceSetting "
				+ "where UserID = (Select UserID from [User] where UserName = '" + username + "')";
		return marketingPrefQuery;
	}

	public static String getMarketingPrefEmailQuery(String email, String subject) {
		String marketingPrefEmailQuery = "SELECT TOP 1 * FROM dbo.ContractAccountNotifyEmail WHERE EmailId = '" + email
				+ "' and subject like '" + subject + "%' ORDER BY 1 desc";
		return marketingPrefEmailQuery;
	}

	/*************************************
	 * >>>>> GUEST USER PAGE QUERIES <<<<<*
	 *************************************/
	/**
	 * This method is to get the query to get the guest user invite mail content and
	 * sent status.
	 *
	 * @param emailId
	 * @param subject
	 * @return
	 */
	public static String getGuestUserInviteMailContentQuery(String emailId, String subject) {
		String inviteMailContentQuery = "SELECT TOP 1 Message, IsNotify  FROM ContractAccountNotifyEmail\r\n"
				+ "WHERE EmailId='" + emailId + "'\r\n" + "AND Subject LIKE '" + subject + "%'\r\n"
				+ "ORDER BY ID DESC";
		return inviteMailContentQuery;
	}

	/**
	 * This method is to get all the linked accounts to the user with there role id,
	 * address and address type.
	 *
	 * @param sUsername
	 * @return
	 */
	public static String getAllLinkedAccountsWithRoleId(String sUsername) {
		String sQuery = "DECLARE @AccountNo INT=(SELECT UserId FROM [User] WHERE UserName='" + sUsername + "') "
				+ "SELECT UA.UtilityAccountNumber, UA.RoleID, C.Address1, C.AddressType  FROM  [User] U (NOLOCK) "
				+ "JOIN  UserAccount UA (NOLOCK) ON UA.UserID=U.UserID "
				+ "JOIN  CustomerInfo C (NOLOCK) ON UA.Accountnumber = C.AccountNumber WHERE U.userid=@AccountNo "
				+ "AND NOT EXISTS (SELECT 1 from GuestAccessRequest ga WHERE AccessExpiryDate < getdate() "
				+ "AND ua.RequestID=ga.RequestID) ORDER BY UA.IsDefaultAccount DESC";
		return sQuery;
	}

	/**
	 * This method is to get RoleID, Address and AddressType of a UtilityAccount
	 * Number with the User
	 *
	 * @param sUsername
	 * @param utilityAccountNum
	 * @return
	 */
	public static String getAcountNumberDetilsWithUser(String sUsername, String utilityAccountNum) {
		String sQuery = "DECLARE @AccountNo INT=(SELECT UserId FROM [User] WHERE UserName='" + sUsername + "') "
				+ "SELECT UA.UtilityAccountNumber, UA.RoleID, C.Address1, C.AddressType  FROM  [User] U (NOLOCK) "
				+ "JOIN  UserAccount UA (NOLOCK) ON UA.UserID=U.UserID "
				+ "JOIN  CustomerInfo C (NOLOCK) ON UA.Accountnumber = C.AccountNumber WHERE U.userid=@AccountNo "
				+ "AND NOT EXISTS (SELECT 1 from GuestAccessRequest ga WHERE AccessExpiryDate < getdate() "
				+ "AND ua.RequestID=ga.RequestID) and UA.UtilityAccountNumber ='" + utilityAccountNum
				+ "' ORDER BY UA.IsDefaultAccount DESC";
		return sQuery;
	}

	/**
	 * This method is to get the user to whom email id is linked.
	 *
	 * @param sEmail
	 * @param sUsername
	 * @return
	 */
	public static String getUserToWhomEmailIdIsLinked(String sEmail, String sUsername) {
		String sQuery = "SELECT TOP 1 UserName, Password FROM [User] WHERE EmailID = '" + sEmail + "' AND Status = '1'"
				+ " AND UserName!='" + sUsername + "'";
		// System.out.println(sQuery);
		return sQuery;
	}

	/**
	 * This method is to get the encrypted password.
	 *
	 * @param sUsername
	 * @return
	 */
	public static String getEncryptedPasswordOfUser(String sUsername) {
		String sQuery = "SELECT UserName, Password FROM [User] WHERE UserName = '" + sUsername + "'";
		return sQuery;
	}

	/**
	 * This method is to get the query to update the password for user.
	 *
	 * @param sUsername
	 * @param sPassword
	 * @return
	 */
	public static String getQueryToUpdatePasswordForUser(String sUsername, String sPassword) {
		String sQuery = "UPDATE [User]" + "SET Password = '" + sPassword + "' " + "WHERE UserName = '" + sUsername
				+ "'";
		return sQuery;
	}

	/**
	 * This method is to get the query to update the Primary Email Address for user.
	 *
	 * @param sUsername
	 * @param sEmailID
	 * @return
	 */
	public static String getQueryToUpdatePrimaryEmailAddrForUser(String sUsername, String sEmailID) {
		String sQuery = "UPDATE [User]" + "SET EmailID = '" + sEmailID + "' " + "WHERE UserName = '" + sUsername + "'";
		return sQuery;
	}

	/**
	 * This method is to get the csp guest user configuration.
	 *
	 * @return
	 */
	public static String getCspGuestUserConfig() {
		String sQuery = "SELECT ConfigOption, ConfigValue FROM UtilityConfig WHERE "
				+ "ModuleName= 'GuestUserConfiguration'";
		return sQuery;
	}

	public static String getLoggedInUsersEmail(String sUsername) {
		String sQuery = "SELECT EmailID\n" + "FROM [User]\n" + "WHERE UserName='" + sUsername + "'";
		return sQuery;
	}

	/****************************************
	 * >>>> ABOUT MY HOME PAGE QUERIES <<<< *
	 ****************************************/
	/**
	 * This method is to get the Residential Accounts of a User, takes Username as
	 * input and gives all Residential Owner Accounts of this user
	 */
	public static String getResidentialOwnerAccounts(String username) {
		String sQuery = "Select CustomerAddress.UtilityAccountNumber FROM CustomerAddress "
				+ "join (Select [User].UserID, [UserAccount].UtilityAccountNumber FROM [UserAccount] "
				+ "join [User] on [UserAccount].UserID = [User].UserID  WHERE RoleID = '3' and UserName='" + username
				+ "') as A on CustomerAddress.UtilityAccountNumber = A.UtilityAccountNumber where AddressType= '1'";
		return sQuery;
	}

	/**
	 * This method is to get the Business/Commercial Accounts of a User, takes
	 * Username as input and gives all Business/Commercial Owner Accounts of this
	 * user
	 */
	public static String getCommercialOwnerAccounts(String username) {
		String sQuery = "Select CustomerAddress.UtilityAccountNumber FROM CustomerAddress "
				+ "join (Select [User].UserID, [UserAccount].UtilityAccountNumber FROM [UserAccount] "
				+ "join [User] on [UserAccount].UserID = [User].UserID  WHERE RoleID = '3' and UserName='" + username
				+ "') as A on CustomerAddress.UtilityAccountNumber = A.UtilityAccountNumber where AddressType= '2'";
		return sQuery;
	}

	/*********************************************
	 * >>>>> BILLING DASHBOARD PAGE QUERIES <<<<<<*
	 *********************************************/
	/**
	 * @param utilityAccountNumber
	 * @return
	 */
	public static String getOneTimePaymentDetails(String utilityAccountNumber) {
		String getOneTimePaymentDetails = "SELECT  Top 1 [User].FirstName, [User].LastName, [User].EmailID, "
				+ "[User].MobilePhone, CI.Address1, CI.CityName, CI.StateName, CI.ZipCode, BD.Value AS [RemainingBalance]"
				+ " FROM [User], CustomerInfo AS CI "
				+ " Inner Join Account ON CI.AccountNumber = Account.AccountNumber "
				+ " Inner Join (SELECT max(billingid) AS billingid,Accountnumber FROM Billing GROUP BY Accountnumber) AS [Extent1] "
				+ " ON Account.Accountnumber=[Extent1].Accountnumber "
				+ " INNER JOIN [dbo].[billingdetail] AS BD ON [Extent1].billingId=BD.billingId "
				+ " WHERE BD.Headid=24 And CI.UtilityAccountNumber = '" + utilityAccountNumber + "' "
				+ " AND [User].UserID=(SELECT UserID FROM [UserAccount]  WHERE UtilityAccountNumber = '"
				+ utilityAccountNumber + "'" + " and RoleID=" + "'" + "3" + "'" + ")";
		return getOneTimePaymentDetails;
	}

	public static String getDetailsUtilityBillPage(String UtilityAccountNumber, String sLanguage) {
		String detailsUtilityBillPage = " DECLARE @utilityaccountnumber VARCHAR(100) = '" + UtilityAccountNumber + "', "
				+ " @LanguageCode VARCHAR(10) = 'EN' " + " ,@BillingDate DATETIME = NULL " + " DECLARE @BillingId INT ,"
				+ " @FromDate DATE " + " ,@ToDate DATE " + " ,@DefaultPaymentType BIT "
				+ " , @LastRechargeAmount NUMERIC(12, 2), @CustomerId INT, @AccountNumber INT "
				+ " SELECT TOP 1 @CustomerId = CA.CustomerId	,@DefaultPaymentType = DefaultPaymentType "
				+ " FROM CustomerAddress CA WITH (NOLOCK) "
				+ " JOIN Account A WITH (NOLOCK) ON (A.AddressId = CA.AddressId) WHERE A.AccountNumber = @AccountNumber "
				+ " SELECT @AccountNumber = AccountNumber FROM account WHERE utilityaccountnumber = @utilityaccountnumber "
				+ " IF @BillingDate IS NULL SELECT TOP 1 @BillingId = BillingId, @FromDate = PeriodFrom, @ToDate = PeriodTo"
				+ " FROM Billing WITH (NOLOCK) WHERE AccountNumber = @AccountNumber ORDER BY BillingDate DESC ELSE "
				+ " SELECT TOP 1 @BillingId = BillingId, @FromDate = PeriodFrom, @ToDate = PeriodTo "
				+ " FROM Billing WITH (NOLOCK) WHERE AccountNumber = @AccountNumber AND BillingDate = @BillingDate "
				+ " BEGIN SELECT TOP 1 @LastRechargeAmount = TransactionAmount FROM BillingTransaction WITH (NOLOCK) "
				+ " WHERE AccountNumber = @AccountNumber" + " AND IsPrepay = 1 ORDER BY TransactionDate DESC"
				+ " SELECT dbo.getMultilingualMessage(BHM.HeadControlId, @LanguageCode, 'N', DEFAULT) AS [HeaderName],"
				+ " CASE WHEN BHM.HeadId = 24 "
				+ " AND ISNUMERIC(BD.Value) = 1 AND CAST(BD.Value AS NUMERIC(13, 2)) < 0 AND BHM.HeadId = 24 "
				+ " AND @DefaultPaymentType = 1 THEN CAST(ABS(CAST(BD.Value AS NUMERIC(13, 2))) AS VARCHAR(12)) + ' CR'"
				+ " WHEN BHM.HeadId = 24 	AND ISNUMERIC(BD.Value) = 1 AND CAST(BD.Value AS NUMERIC(13, 2)) > 0 "
				+ " AND BHM.HeadId = 24 "
				+ " AND @DefaultPaymentType = 1 THEN CAST(ABS(CAST(BD.Value AS NUMERIC(13, 2))) AS VARCHAR(12)) "
				+ " WHEN BHM.HeadId = 5 THEN CAST(ABS(CAST(( "
				+ " CASE WHEN CHARINDEX('E', BD.Value) > 0 THEN CAST(BD.Value AS FLOAT) ELSE BD.Value END"
				+ " ) AS NUMERIC(15, 2))) AS VARCHAR(12)) + ' kWh' WHEN BHM.HeadId = 8 "
				+ " THEN cast(cast(BD.Value AS NUMERIC(10, 2)) AS VARCHAR) + ' HCF' "
				+ " WHEN BHM.HeadId = 11 THEN CAST(ABS(CAST(BD.Value AS NUMERIC(15, 2))) AS VARCHAR(12)) + ' CCF'"
				+ " WHEN BHM.HeadId IN (9,19,20,22,23,24,6) THEN cast(cast(BD.Value AS DECIMAL(10, 2)) AS VARCHAR)"
				+ " ELSE BD.Value END Value ,BHM.ColorCode" + " FROM BillingHeadMaster BHM WITH (NOLOCK) "
				+ " LEFT OUTER JOIN BillingDetail BD WITH (NOLOCK) ON ( BD.HeadId = BHM.HeadId "
				+ " AND BD.BillingId = @BillingId ) "
				+ " WHERE BHM.IsActive = 1 	AND ( BD.Value IS NOT NULL 	OR BHM.HeaderType = 1 ) AND BHM.IsPDFHead = 0 "
				+ " AND ( BHM.Section IN ( SELECT FeatureName FROM FeatureSettings FS WITH (NOLOCK) WHERE FS.[Status] = 1)"
				+ " OR BHM.Section = 'BillDetails') UNION"
				+ " SELECT dbo.getMultilingualMessage('ML_Payment_Receive', 'EN', 'N', DEFAULT) AS [HeaderName], "
				+ " CONVERT(VARCHAR, cast(X.transactiondate as date)) AS Value, '#fff' AS ColorCode FROM( "
				+ " select top 1 transactiondate from billingtransaction where accountnumber = @accountnumber "
				+ " and billingid = @billingid order by 1 desc ) X UNION "
				+ " SELECT dbo.getMultilingualMessage('ML_LastPayment', 'EN', 'N', DEFAULT) AS [HeaderName], "
				+ " CONVERT(VARCHAR, X.transactionamount) AS Value, '#fff' AS ColorCode FROM( "
				+ " select top 1 transactionamount from billingtransaction where accountnumber = @accountnumber "
				+ " and billingid = @billingid order by 1 desc ) X END";
		return detailsUtilityBillPage;
	}

	/**
	 * This method is to get the payments details query.
	 *
	 * @param sAccountNo
	 * @return
	 */
	public static String getPaymentsDetailsQuery(String sAccountNo) {
		String sQuery = null;
		sQuery = "SELECT TOP 1 * FROM Payments\n" + "WHERE ServiceAccountNumber = '" + sAccountNo + "'\n"
				+ "ORDER BY 1 DESC";
		// System.out.println(sQuery);
		return sQuery;
	}

	public static String getPaymentProfileDetails(String customerRefNum) {
		return "select top 1 customerrefnum, serviceaccountnumber, ccaccountnumber, ccexp, bankaccountnumber, \n"
				+ "bankrouting, bankname, profilestatus, isdeleted, createddate, firstname, lastname, email \n"
				+ "from paymentprofiles \n" + "where customerrefnum = '" + customerRefNum + "'\n"
				+ "order by paymentprofileid desc";
	}

	public static String getAllPaymentProfileDetails(String userId) {
		return "select paymentprofileid, customerrefnum, ccaccountnumber, bankaccountnumber, \n"
				+ "email, profilestatus, isdeleted, userid, accountnumber, \n" + "serviceaccountnumber \n"
				+ "from paymentprofiles \n" + "where userid = '" + userId + "' and isdeleted = '0'";
	}

	public static String getPaymentProfileDetailsByCustomerRef(String userId, String customerRefNum) {
		return "select paymentprofileid, customerrefnum, ccaccountnumber, bankaccountnumber,\n"
				+ "email, profilestatus, isdeleted, userid, accountnumber, serviceaccountnumber,\n"
				+ "walletemail, paymenttokenguid\n" + "from paymentprofiles\n" + "where userid = '" + userId
				+ "' and customerrefnum = '" + customerRefNum + "'";
	}

	public static String getPaymentDetailsByTxnRefNum(String txnRefNum) {
		return "select top 10 userid, amount, totalamount, email, statusmessage, \n"
				+ "txid, txrefnum, orderid, retrytrace, paymentaccount, isautopay, \n" + "isregistered, paymenttoken\n"
				+ "from payments\n" + "where txrefnum = '" + txnRefNum + "'\n" + "order by 1 desc";
	}

	/**
	 * This method is to get the payment extension eligibility of an account query.
	 *
	 * @return
	 */
	public static String getPaymentExtension(String sUtilityAccountNumber) {
		String sQuery = "declare @utilityaccountnumber varchar(40) ='" + sUtilityAccountNumber + "'"
				+ " select distinct a.AccountNumber, b.BillingDate, bd.value ,a.utilityaccountnumber from billing b "
				+ " inner join billingdetail bd on b.billingid=bd.billingid "
				+ "inner join account a on a.accountnumber=b.accountnumber "
				+ "inner join accountaveragebilling ab on ab.accountnumber=a.accountnumber "
				+ "where bd.BillingId in (Select Top 1 BillingId from Billing where AccountNumber in "
				+ "(Select distinct accountNumber from account where UtilityAccountNumber =  @utilityaccountnumber) "
				+ "order By BillingDate Desc) and "
				+ "bd.headid=24 and convert(varchar,getdate(),1)<(select bd.value from billingdetail bd "
				+ "where bd.billingid in (Select Top 1 BillingId from Billing where AccountNumber in "
				+ "(Select distinct accountNumber from account where UtilityAccountNumber =  @utilityaccountnumber) "
				+ "order by billingdate desc)and bd.headid=25)";
		return sQuery;
	}

	/*****************************************************************************************
	 * BILLING PAYMENT PAGE QUERIES *
	 *****************************************************************************************/
	public static String getLatestPaymentProfile(String sAccountNumber) {
		String sQuery = "SELECT TOP 1 CustomerRefNum, PaymentType, CCAccountNumber, BankAccountNumber, "
				+ "CcExp, PaymentType, FirstName, ProfileStatus, BankRouting, BankName \n" + "FROM PaymentProfiles\n"
				+ "WHERE ServiceAccountNumber = '" + sAccountNumber + "'\n" + "ORDER BY PaymentProfileId DESC";
		return sQuery;
	}

	/************************************
	 * >>>>>> PAYMENT PAGE QUERIES <<<<<<*
	 ************************************/
	/******************************************
	 * >>>> RECURRING PAYMENT PAGE QUERIES <<<<*
	 ******************************************/
	/**
	 * This method is to get the query to get the non ami meters count linked to the
	 * given utility account number.
	 *
	 * @param sUtilityAccountNumber
	 * @return
	 */
	public static String getQueryCheckAccEnrollForAutoPay(String sUtilityAccountNumber) {
		String sAccEnrollForAutoPay = "Select COUNT(*) AS AutoPayEnrolledCount FROM AccountRecurringPayment WHERE "
				+ "AccountNumber=(SELECT AccountNumber FROM Account WHERE UtilityAccountNumber='"
				+ sUtilityAccountNumber + "')";
		return sAccEnrollForAutoPay;
	}

	/**
	 * This method is to get the query to get the accounts enrolled for Auto Pay.
	 *
	 * @param sUsername
	 * @return
	 */
	public static String getQueryAccEnrollForAutoPay(String sUsername) {
		String sAccountsEnrollForAutoPay = "SELECT UtilityAccountNumber FROM Account " + "WHERE AccountNumber "
				+ "IN (SELECT AccountNumber FROM AccountRecurringPayment " + "WHERE AccountNumber "
				+ "IN (SELECT AccountNumber FROM UserAccount "
				+ "WHERE UserID = (SELECT UserID FROM [User] WHERE UserName = '" + sUsername + "') "
				+ "AND RoleID in (3)))";
		return sAccountsEnrollForAutoPay;
	}

	public static String getQueryCountAccEnrolledForAutopay(String sUsername) {
		String sQuery = "SELECT COUNT (UtilityAccountNumber) AS UtilityAccountNumber FROM Account "
				+ "WHERE AccountNumber " + "IN (SELECT AccountNumber FROM AccountRecurringPayment "
				+ "WHERE AccountNumber " + "IN (SELECT AccountNumber FROM UserAccount "
				+ "WHERE UserID = (SELECT UserID FROM [User] " + "WHERE UserName = '" + sUsername + "') "
				+ "AND RoleID IN (3)))";
		return sQuery;
	}

	/**
	 * This method is to get the autopay enrollment confirmation email.
	 *
	 * @param email
	 * @param subject
	 * @return
	 */
	public static String getAutoPayEnrollConfirmationEmail(String email, String subject) {
		return "select top 100 emailid, subject, message, createdate, isnotify\n" + "from contractaccountnotifyemail\n"
				+ "where subject = '" + subject + "'\n" + "and emailid = '" + email + "'\n" + "order by id desc";
	}

	/*******************************************
	 * >>>>> BILLING HISTORY PAGE QUERIES <<<<<*
	 *******************************************/
	/**
	 * This method will return query to get Bill Statement of a given Utility
	 * Account
	 *
	 * @param sUtilityAccountNumber
	 * @return sQueryBillingStatements
	 */
	public static String getBillStatementQuery(String sUtilityAccountNumber) {
		String sQueryBillingStatements = "SELECT Billing.BillingDate, BillingDetail.value "
				+ "from BillingDetail join Billing on BillingDetail.BillingID=Billing.BillingID "
				+ "where BillingDetail.HeadId='22' and Billing.AccountNumber=(select AccountNumber "
				+ "from account where utilityAccountNumber='" + sUtilityAccountNumber + "')  order by 1 desc";
		return sQueryBillingStatements;
	}

	/**
	 * This method will return query to get Payments of a given Utility Account.
	 *
	 * @param sUtilityAccountNumber
	 * @return sQueryBillingStatements
	 */
	public static String getPaymentsQuery(String sUtilityAccountNumber) {
		String sQueryPayments = "SELECT TransactionDate, TransactionAmount FROM BillingTransaction "
				+ "where accountnumber=(select AccountNumber from account where utilityAccountNumber='"
				+ sUtilityAccountNumber + "' and TransactionStatus ='1')  order by 1 desc";
		return sQueryPayments;
	}

	/*******************************************
	 * >>>>>>> BUDGET BILL PAGE QUERIES <<<<<<<<*
	 *******************************************/
	/**
	 * This method is to get the query to get the non ami meters count linked to the
	 * given utility account number.
	 *
	 * @param sUtilityAccountNumber
	 * @return
	 */
	public static String getNonAMIMetersCountLinkedToAccount(String sUtilityAccountNumber) {
		String sNonAMIMeterCounts = "SELECT COUNT(*) AS NonAMIMeterCount FROM AccountMeterMapping WHERE "
				+ "AccountNumber=(SELECT AccountNumber FROM Account WHERE UtilityAccountNumber='"
				+ sUtilityAccountNumber + "') AND ISAMI=0";
		return sNonAMIMeterCounts;
	}

	/**
	 * This method is to get the maximum monthly budget limit for residential and
	 * commercial accounts.
	 *
	 * @return
	 */
	public static String getMaxMonthlyBudgetLimit() {
		String sQuery = "SELECT MonthlyBudgetMaxLimit, IMonthlyBudgetMaxLimit FROM UtilitySettings";
		return sQuery;
	}

	/**
	 * This method is to get the Budget Bill for a service account
	 *
	 * @return
	 */
	public static String getMyBudgetForAccount(String serviceAccount) {
		String sQuery = "select top 12 * from budgetbill where accountnumber=(Select distinct accountNumber "
				+ "from account where UtilityAccountNumber = '" + serviceAccount + "') order by 1 desc";
		return sQuery;
	}

	/*****************************************
	 * >>>>> RATE ANALYSIS PAGE QUERIES <<<<<<*
	 *****************************************/

	/***************************************
	 * >>>>>> LEVEL PAY PAGE QUERIES <<<<<<<*
	 ***************************************/
	public static String getLevelPayStatus(String utilityAccountNumber) {
		String sQuery = "select RecurringPayStatus from Account where UtilityAccountNumber='" + utilityAccountNumber
				+ "'";
		return sQuery;
	}

	public static String getTwelveMonthBillHistoryForLevelPay(String utilityAccountNumber) {
		String sQuery = "Select value from BillingDetail where BillingId in (Select top 12 BillingId "
				+ "from Billing where AccountNumber = (select distinct a.AccountNumber from billing b\r\n"
				+ "inner join billingdetail bd on b.billingid=bd.billingid\r\n"
				+ "inner join account a on a.accountnumber=b.accountnumber\r\n"
				// + "inner join accountaveragebilling ab on
				// ab.accountnumber=a.accountnumber\r\n"
				+ "where bd.BillingId in (Select Top 1 BillingId from Billing where AccountNumber in \r\n"
				+ "(Select distinct accountNumber from account where UtilityAccountNumber = '" + utilityAccountNumber
				+ "') order By BillingDate Desc) and\r\n" + " bd.headid=24) order By 1 Desc) And HeadId = 19";
		return sQuery;
	}

	public static String getCurrentDueAmountForLevelPay(String utilityAccountNumber) {
		String sQuery = "select distinct a.AccountNumber, b.BillingDate, bd.value ,a.utilityaccountnumber from billing b\r\n"
				+ "inner join billingdetail bd on b.billingid=bd.billingid\r\n"
				+ "inner join account a on a.accountnumber=b.accountnumber\r\n"
				+ "inner join accountaveragebilling ab on ab.accountnumber=a.accountnumber\r\n"
				+ "where bd.BillingId in (Select Top 1 BillingId from Billing where AccountNumber in \r\n"
				+ "(Select distinct accountNumber from account where UtilityAccountNumber = '" + utilityAccountNumber
				+ "') order By BillingDate Desc) and\r\n" + " bd.headid=24 ";
		return sQuery;
	}

	public static String getLevelPayEnrolmentStatus(String utilityAccountNumber) {
		String sQuery = "Select * from accountaveragebilling where AccountNumber in \r\n"
				+ "(Select distinct accountNumber from account where UtilityAccountNumber = '" + utilityAccountNumber
				+ "')";
		return sQuery;
	}

	/**
	 * If this query returns Result set and Value column give Zero or less amount
	 * then account is Eligible for level pay
	 */
	public static String getAccountEligibleLevelPayOrNot(String utilityAccountNumber) {
		String sQuery = "select distinct a.AccountNumber, b.BillingDate, bd.value ,a.utilityaccountnumber from billing b\r\n"
				+ "inner join billingdetail bd on b.billingid=bd.billingid\r\n"
				+ "inner join account a on a.accountnumber=b.accountnumber\r\n"
				+ "inner join accountaveragebilling ab on ab.accountnumber=a.accountnumber\r\n"
				+ "where bd.BillingId in (Select Top 1 BillingId from Billing where AccountNumber in \r\n"
				+ "(Select distinct accountNumber from account where UtilityAccountNumber = '" + utilityAccountNumber
				+ "') order By BillingDate Desc) and\r\n" + " bd.headid=24";
		return sQuery;
	}

	/****************************************
	 * >>>>>> PAYMENT LOCATION QUERIES <<<<<<*
	 ****************************************/
	public static String getPaymentLocationListQuery() {
		String sQueryPaymentLocationList = "SELECT LocationName, Address1, CityName, PaymentLocWebsite, PaymentToDay, "
				+ "PayTimeFrom, PayTimeTo, ContactNo, Emailid FROM PaymentLocation "
				+ "LEFT JOIN CityMaster ON PaymentLocation.CityID = CityMaster.CityID where IsDeleted='0'and CityName != 'null'";
		return sQueryPaymentLocationList;
	}

	/******************************************
	 * >>>>>>> USAGE PAY PAGE QUERIES <<<<<<<<<*
	 ******************************************/
	/**
	 * This query method is to get all meter counts for a utility account
	 * sUtilityAccountNumber= C002002003
	 *
	 * @param sUtilityAccountNumber
	 * @return
	 */
	public static String getAllMeterTypesCount(String sUtilityAccountNumber, String Username) {
		String allMeterTypeCount = "SELECT Metertype, count(metertype) AS MeterCount " + "FROM accountmetermapping "
				+ "WHERE accountnumber = (SELECT accountnumber " + "FROM UserAccount " + "WHERE UtilityAccountNumber ='"
				+ sUtilityAccountNumber + "' " + "AND UserID = (SELECT UserID FROM [User] WHERE UserName='" + Username
				+ "')) and Status !=0 " + "GROUP BY metertype";
		return allMeterTypeCount;
	}

	/**
	 * This query method is to get specific meter counts for a utillity account
	 * sUtilityAccountNumber= C002002003 sMeterType = P, W, G, PV(Power, Water, Gas,
	 * Solar) iMeterStatus = 0,1(Non AMI-0, AMI-1)
	 *
	 * @param utilityAccNum
	 * @param meterType
	 * @param isAMI
	 * @return
	 */
	public static String getCountSpecificMeterQuery(String utilityAccNum, String meterType, int isAMI) {
		String querySpecificMeters = "SELECT Count (DISTINCT MeterNumber) as MeterNumber FROM AccountMeterMapping"
				+ " WHERE AccountNumber = (Select AccountNumber from Account where UtilityAccountNumber='"
				+ utilityAccNum + "') and MeterType='" + meterType + "' and IsAMI=" + isAMI + " and Status !=0";
		return querySpecificMeters;
	}

	public static String getMetersWithAccount(String utilityAccNo) {
		return "select meterid, accountnumber, meternumber, metertype, isami, status  \n"
				+ "from accountmetermapping \n" + "where accountnumber in (select accountnumber from account \n"
				+ "where utilityaccountnumber = '" + utilityAccNo + "')";
	}

	public static String getMetersWithAccount(String utilityAccNo, String meterType) {
		return "select meterid, accountnumber, meternumber, metertype, isami, status  \n"
				+ "from accountmetermapping \n" + "where accountnumber in (select accountnumber from account \n"
				+ "where utilityaccountnumber = '" + utilityAccNo + "')\n" + "and metertype = '" + meterType + "'";
	}

	public static String getMetersCountWithAccount(String utilityAccNo) {
		return "select count(distinct meterid) as metercount\n" + "from accountmetermapping\n"
				+ "where accountnumber in (select accountnumber\n" + "from account\n" + "where utilityaccountnumber = '"
				+ utilityAccNo + "')";
	}

	public static String getMetersCountWithAccount(String utilityAccNo, String meterType) {
		return "select count(distinct meterid) as metercount\n" + "from accountmetermapping\n"
				+ "where accountnumber in (select accountnumber\n" + "from account\n" + "where utilityaccountnumber = '"
				+ utilityAccNo + "')\n" + "and metertype = '" + meterType + "'";
	}

	/**
	 * This query method is to get all the meter counts for a utility account
	 * sUtilityAccountNumber= C002002003 sMeterType = P, W, G, PV(Power, Water, Gas,
	 * Solar) iMeterStatus = 0,1(Non AMI-0, AMI-1)
	 *
	 * @param utilityAccNum
	 * @param meterType
	 * @return
	 */
	public static String getCountAllMetersQuery(String utilityAccNum, String meterType) {
		String querySpecificMeters = "SELECT Count (DISTINCT MeterNumber) as MeterNumber FROM AccountMeterMapping"
				+ " WHERE AccountNumber=(Select AccountNumber from Account where UtilityAccountNumber='" + utilityAccNum
				+ "'" + ") and MeterType='" + meterType + "'" + " and Status !=0";
		return querySpecificMeters;
	}

	/**
	 * This query method is to get all the meter names for a utillity account
	 * sUtilityAccountNumber= C002002003 sMeterType = P, W, G, PV(Power, Water, Gas,
	 * Solar) iMeterStatus = 0,1(Non AMI-0, AMI-1)
	 *
	 * @param utilityAccNum
	 * @param meterType
	 * @return
	 */
	public static String getUtilityAllMeterNameQuery(String utilityAccNum, String meterType) {
		String querySpecificMeters = "SELECT MeterNumber FROM AccountMeterMapping "
				+ "WHERE AccountNumber=(Select AccountNumber from Account where UtilityAccountNumber='" + utilityAccNum
				+ "') and MeterType='" + meterType + "'" + " and Status='1'";
		return querySpecificMeters;
	}

	/**
	 * This query method is to get all the meter names for a utillity account
	 * sUtilityAccountNumber= C002002003 sMeterType = P, W, G, PV(Power, Water, Gas,
	 * Solar) iMeterStatus = 0,1(Non AMI-0, AMI-1).
	 *
	 * @param utilityAccNum
	 * @param meterType
	 * @param meterStatus
	 * @return
	 */
	public static String getUtilitySpecificMeterNameQuery(String utilityAccNum, String meterType, int meterStatus) {
		String querySpecificMeters = "SELECT MeterNumber FROM AccountMeterMapping "
				+ "WHERE AccountNumber=(Select AccountNumber from Account where UtilityAccountNumber='" + utilityAccNum
				+ "') and MeterType='" + meterType + "' and IsAMI=" + meterStatus + " and Status !=0";
		return querySpecificMeters;
	}

	/**
	 * This query method is to get the 1st Solar meter name for a utillity account
	 * sUtilityAccountNumber= C002002003.
	 *
	 * @param utilityAccNum
	 */
	public static String getUtilitySolarMeterNameQuery(String utilityAccNum) {
		String sQuerySpecificMeters = "SELECT Top 1 MeterNumber FROM AccountMeterMapping "
				+ "WHERE AccountNumber=(Select AccountNumber from Account where UtilityAccountNumber='" + utilityAccNum
				+ "') and MeterType='PV'" + " and Status !=0";
		return sQuerySpecificMeters;
	}

	/**
	 * @param utilityAccNum
	 * @param meterNum
	 * @param tableName
	 * @param cspConfiguredMonths
	 * @return
	 */
	public static String getUsageAvailableMonthsCount(String utilityAccNum, String meterNum, String tableName,
			int cspConfiguredMonths) {
		String countDataAvailableUsageMonths = ";" + "declare @utilityaccountnumber varchar(100) ='" + utilityAccNum
				+ "' " + ",@MeterNumber varchar(100)='" + meterNum + "' " + ",@AccountNumber bigint, @Meterid bigint "
				+ "select @AccountNumber=AccountNumber from account where utilityaccountnumber = @utilityaccountnumber "
				+ "select @Meterid = Meterid from AccountMeterMapping "
				+ "where AccountNumber = (select AccountNumber from account where utilityaccountnumber=@utilityaccountnumber) "
				+ "and MeterNumber=@MeterNumber " + "select count(1) from " + tableName
				+ " where AccountNumber=@AccountNumber and Meterid=@Meterid and usagedate>=dateadd(mm,-"
				+ cspConfiguredMonths + ",getdate()) and rateplandetailid=1";
		return countDataAvailableUsageMonths;
	}

	public static String getMonthlyUsageForUtillityMeters(String sTableName, String sMeterType,
			String sUtilityAccountNumber, String sMeterNumer, int iMonthConfigured) {
		String sMeter = null;
		if (sMeterNumer == null) {
			sMeter = null;
		} else {
			sMeter = "'" + sMeterNumer + "'";
		}
		String sMonthlyUsageQuery = ";" + "; DECLARE @UtilityAccountNumber VARCHAR(100) = '" + sUtilityAccountNumber
				+ "', @MeterNumber VARCHAR(100) = " + sMeter
				+ " DECLARE @FromDate DATE ,@ToDate DATE, @IsAMI TINYINT, @month TinyInt"
				+ " IF @MeterNumber IS NULL BEGIN"
				+ " if Exists (Select 1 from Account A Inner join AccountMeterMapping AMM on A.AccountNumber=AMM.AccountNumber where"
				+ " A.UtilityAccountNumber=@UtilityAccountNumber and isami=0 and MeterType='" + sMeterType + "')"
				+ " Begin set @month=1 End Else  Set @month=0"
				+ " SET @ToDate = DATEADD(MM,  -@month, DATEADD(month, DATEDIFF(month, 0, getdate()), 0))"
				+ " SET @FromDate = DATEADD(MM, -" + iMonthConfigured + "+ 1, @ToDate) END"
				+ " IF ISNULL(@MeterNumber, '') <> ''" + " BEGIN SELECT @IsAMI = ISAMI FROM account a "
				+ " JOIN AccountMeterMapping amm ON a.AccountNumber = amm.AccountNumber"
				+ " WHERE a.UtilityAccountNumber = @UtilityAccountNumber AND amm.meternumber = @MeterNumber"
				+ " IF @IsAMI = 0  BEGIN"
				+ " SET @ToDate = DATEADD(MM, - 1, DATEADD(month, DATEDIFF(month, 0, getdate()), 0))"
				+ " SET @FromDate = DATEADD(MM, - " + iMonthConfigured + "+ 1, @ToDate) END" + " IF @IsAMI = 1  BEGIN"
				+ " SET @ToDate = DATEADD(month, DATEDIFF(month, 0, getdate()), 0)" + " SET @FromDate = DATEADD(MM, -"
				+ iMonthConfigured + " + 1, @ToDate) END END"
				+ " SELECT DATEADD(MONTH, DATEDIFF(MONTH, 0, p.UsageDate), 0) AS [UsageMonth]"
				+ " ,sum(P.Value) AS kWhValue ,SUM(p.Consumed) AS DollarValue "
				+ " FROM account a JOIN AccountMeterMapping amm ON a.AccountNumber = amm.AccountNumber" + " JOIN "
				+ sTableName + " p ON a.AccountNumber = p.AccountNumber"
				+ " AND amm.meterid = p.meterid JOIN RatePlanDetail RPD ON P.RatePlanDetailID = RPD.RatePlanDetailID"
				+ " WHERE a.UtilityAccountNumber = @UtilityAccountNumber "
				+ " AND RPD.UsageType = 'Usage' AND amm.meternumber = isnull(@MeterNumber, amm.meternumber) "
				+ " AND amm.STATUS = 1  AND (p.UsageDate >= @FromDate AND p.UsageDate <= @ToDate)"
				+ " GROUP BY UtilityAccountNumber, p.UsageDate ORDER BY 1;";
		return sMonthlyUsageQuery;
	}

	public static String getAllMetersOnAccount(String utilityAccNum, String meterType) {
		return "select meterid, accountnumber, meternumber, metertype, isami, status from accountmetermapping\n"
				+ "where accountnumber = (select accountnumber from account where utilityaccountnumber = '"
				+ utilityAccNum + "') \n" + "and metertype = '" + meterType + "' and status = '1'";
	}

	/**
	 * @param sTableName
	 * @param sUtilityAccountNumber
	 * @param sMeterNumer
	 * @param iMonthConfigured
	 * @return
	 */
	public static String getSeasonalUsageForUtillityMeters(String sTableName, String sUtilityAccountNumber,
			String sMeterNumer, int iMonthConfigured) {
		String sMeter = null;
		if (sMeterNumer == null) {
			sMeter = null;
		} else {
			sMeter = "'" + sMeterNumer + "'";
		}
		String sSeasonUsageQuery = ";" + "DECLARE   @UtilityAccountNumber varchar(100)='" + sUtilityAccountNumber + "'"
				+ " ,@MeterNumber varchar(100)= " + sMeter + " ,@FromDate        DATETIME "
				+ " ,@ToDate            DATETIME " + " IF  (Month(getdate()) in (12,1,2)) " + " BEGIN "
				+ " SET @ToDate = CONVERT(VARCHAR(4),YEAR(GETDATE())) + '-' + '03' + '-' + '01' "
				+ " SET @FromDate= DATEADD(YEAR,-1,@ToDate); " + " END      " + "IF  (Month(getdate()) in (3,4,5)) "
				+ " BEGIN" + "   SET @ToDate = CONVERT(VARCHAR(4),YEAR(GETDATE())) + '-' + '06' + '-' + '01'  "
				+ "  SET @FromDate= DATEADD(YEAR,-1,@ToDate); " + " END   " + " IF  (Month(getdate()) in (6,7,8)) "
				+ " BEGIN " + "  SET @ToDate = CONVERT(VARCHAR(4),YEAR(GETDATE())) + '-' + '09' + '-' + '01'   "
				+ "  SET @FromDate= DATEADD(YEAR,-1,@ToDate); " + " END " + " else " + " BEGIN "
				+ "  SET @ToDate = CONVERT(VARCHAR(4),YEAR(GETDATE())) + '-' + '12' + '-' + '01'  "
				+ " SET @FromDate= DATEADD(YEAR,-1,@ToDate); " + " END " + " SELECT SeasonName, sum(P.Value) "
				+ " kWh, sum(p.Consumed) DollarValue from account a join AccountMeterMapping "
				+ " amm on a.AccountNumber=amm.AccountNumber join " + sTableName + " p on "
				+ " a.AccountNumber=p.AccountNumber and amm.meterid=p.meterid join "
				+ " SeasonDuration sd on sd.seasonmonth=datepart(MM,p.Usagedate) "
				+ " JOIN RatePlanDetail RPD ON P.RatePlanDetailID=RPD.RatePlanDetailID "
				+ " join SeasonMaster sm on sm.seasonid=sd.seasonid where a.UtilityAccountNumber=@UtilityAccountNumber AND RPD.UsageType= "
				+ " 'Usage' and amm.meternumber=isnull(@MeterNumber,amm.meternumber) and amm.Status=1 and p.UsageDate>=@FromDate "
				+ " and p.UsageDate< @ToDate group by UtilityAccountNumber, SeasonName order by 1";
		// System.out.println(sSeasonUsageQuery);
		return sSeasonUsageQuery;
	}

	/**
	 * This query is for getting the daily usage for any meter/Utility/Account.
	 *
	 * @param sTableName
	 * @param sUtilityAccountNumber
	 * @param sMeterNumer
	 * @return
	 */
	public static String getDailyUsageForUtillityMeters(String sTableName, String sUtilityAccountNumber,
			String sMeterNumer) {
		String sMeter = null;
		if (sMeterNumer == null) {
			sMeter = null;
		} else {
			sMeter = "'" + sMeterNumer + "'";
		}
		String sDailyUsageQuery = ";" + "declare @UtilityAccountNumber varchar(100)='" + sUtilityAccountNumber + "'"
				+ "  ,@MeterNumber varchar(100)=" + sMeter + " select UtilityAccountNumber"
				+ ", p.UsageDate AS UsageDate" + ", sum(P.Value) kWh, sum(p.Consumed) DollarValue " + "from account a "
				+ "join AccountMeterMapping amm on a.AccountNumber=amm.AccountNumber " + "join " + sTableName
				+ " p JOIN RatePlanDetail RPD ON P.RatePlanDetailID=RPD.RatePlanDetailID "
				+ " on a.AccountNumber=p.AccountNumber and amm.meterid=p.meterid"
				+ " where a.UtilityAccountNumber=@UtilityAccountNumber " + "AND RPD.UsageType='Usage' "
				+ " and amm.meternumber=isnull(@MeterNumber,amm.meternumber)" + " and amm.Status=1"
				+ " and p.UsageDate>=dateadd(day,-31,getdate())"
				+ " group by UtilityAccountNumber, p.UsageDate order by 1,2";
		return sDailyUsageQuery;
	}

	/**
	 * This query is for getting the Hourly usage for any meter/Utility/Account
	 *
	 * @param sTableName
	 * @param sUtilityAccountNumber
	 * @param sMeterNumer
	 * @return
	 */
	public static String getHourlyUsageForUtillityMeters(String sTableName, String sUtilityAccountNumber,
			String sMeterNumer) {
		String sMeter = null;
		if (sMeterNumer == null) {
			sMeter = null;
		} else {
			sMeter = "'" + sMeterNumer + "'";
		}
		String sHourlyUsageQuery = ";" + "declare @UtilityAccountNumber varchar(100)='" + sUtilityAccountNumber + "'"
				+ ",@MeterNumber varchar(100)=" + sMeter + " select UtilityAccountNumber" + ", p.UsageDate AS UsageDate"
				+ ", sum(P.Value) kWh, sum(p.Consumed) DollarValue " + "from account a "
				+ "join AccountMeterMapping amm on a.AccountNumber=amm.AccountNumber " + "join " + sTableName
				+ " p JOIN RatePlanDetail RPD ON P.RatePlanDetailID=RPD.RatePlanDetailID "
				+ " on a.AccountNumber=p.AccountNumber and amm.meterid=p.meterid "
				+ "where a.UtilityAccountNumber=@UtilityAccountNumber " + "AND RPD.UsageType='Usage' "
				+ "and amm.meternumber=isnull(@MeterNumber,amm.meternumber) " + "and amm.Status=1 "
				+ "and CONVERT(DATE,p.UsageDate)=CONVERT(DATE,GETDATE()-1) "
				+ "group by UtilityAccountNumber, p.UsageDate order by 1,2 ";
		return sHourlyUsageQuery;
	}

	/**
	 * This query is for getting the 15 Min usage for any meter/Utility/Account.
	 *
	 * @param sTableName
	 * @param sUtilityAccountNumber
	 * @param sMeterNumer
	 * @return
	 */
	public static String getQuarterlyUsageForUtillityMeters(String sTableName, String sUtilityAccountNumber,
			String sMeterNumer) {
		String sMeter = null;
		if (sMeterNumer == null) {
			sMeter = null;
		} else {
			sMeter = "'" + sMeterNumer + "'";
		}
		String sQuarterlyUsageQuery = ";" + "declare @UtilityAccountNumber varchar(100)='" + sUtilityAccountNumber + "'"
				+ ",@MeterNumber varchar(100)=" + sMeter + " select UtilityAccountNumber" + ", p.UsageDate AS UsageDate"
				+ ", sum(P.Value) kWh, sum(p.Consumed) DollarValue " + "from account a "
				+ "join AccountMeterMapping amm on a.AccountNumber=amm.AccountNumber " + "join " + sTableName
				+ " p  JOIN RatePlanDetail RPD ON P.RatePlanDetailID=RPD.RatePlanDetailID "
				+ "on a.AccountNumber=p.AccountNumber and amm.meterid=p.meterid "
				+ "where a.UtilityAccountNumber=@UtilityAccountNumber " + "AND RPD.UsageType='Usage' "
				+ "and amm.meternumber=isnull(@MeterNumber,amm.meternumber) " + "and amm.Status=1 "
				+ "and CONVERT(DATE,p.UsageDate)=CONVERT(DATE,GETDATE()-1) "
				+ "group by UtilityAccountNumber, p.UsageDate order by 1,2 ";
		return sQuarterlyUsageQuery;
	}

	/**
	 * This query is to fetch the monthly solar generation for the account
	 *
	 * @param sUtilityAccountNumber
	 * @param sMeterNumer
	 * @return
	 */
	public static String getMonthlySolarGeneration(String sUtilityAccountNumber, String sMeterNumer,
			int iConfigMonths) {
		String sMeter = null;
		if (sMeterNumer == null) {
			sMeter = null;
		} else {
			sMeter = "'" + sMeterNumer + "'";
		}
		String sSolarMonthlyUsageQuery = ";" + "  declare @UtilityAccountNumber varchar(100)='" + sUtilityAccountNumber
				+ "'" + ",@MeterNumber varchar(100)=" + sMeter + " select UtilityAccountNumber "
				+ ", DATEADD(MONTH, DATEDIFF(MONTH, 0, p.UsageDate), 0) AS [GenerationDate]"
				+ ", sum(P.Value) kWh, sum(p.Consumed) DollarValue " + "from account a "
				+ "join AccountMeterMapping amm on a.AccountNumber=amm.AccountNumber "
				+ "join SolarMonthlyUsage p on a.AccountNumber=p.AccountNumber and amm.meterid=p.meterid "
				+ "where a.UtilityAccountNumber=@UtilityAccountNumber "
				+ "and amm.meternumber=isnull(@MeterNumber,amm.meternumber) " + "and amm.Status=1 "
				+ "and p.UsageDate>=dateadd(mm,-" + (iConfigMonths + 1) + ",getdate()) "
				+ " and p.UsageDate<=dateadd(mm,-1,getdate()) "
				+ "group by UtilityAccountNumber, p.UsageDate order by 1 ";
		return sSolarMonthlyUsageQuery;
	}

	/**
	 * This method gives all the daily solar generation result
	 *
	 * @param sUtilityAccountNumber
	 * @param sMeterNumer
	 * @param iDays
	 * @return
	 */
	public static String getDailySolarGeneration(String sUtilityAccountNumber, String sMeterNumer, int iDays) {
		String sMeter = null;
		if (sMeterNumer == null) {
			sMeter = null;
		} else {
			sMeter = "'" + sMeterNumer + "'";
		}
		String sSolarDailyUsageQuery = ";" + "  declare @UtilityAccountNumber varchar(100)='" + sUtilityAccountNumber
				+ "'" + " ,@MeterNumber varchar(100)=" + sMeter + " select UtilityAccountNumber"
				+ " , p.UsageDate AS GenerationDate" + ", sum(P.Value) kWh, sum(p.Consumed) DollarValue "
				+ "from account a " + "join AccountMeterMapping amm on a.AccountNumber=amm.AccountNumber "
				+ "join SolarDailyUsage p on a.AccountNumber=p.AccountNumber and amm.meterid=p.meterid and amm.IsAMI=1 "
				+ "where a.UtilityAccountNumber=@UtilityAccountNumber "
				+ "and amm.meternumber=isnull(@MeterNumber,amm.meternumber) " + "and amm.Status=1 "
				+ "and p.UsageDate>CONVERT(DATE,dateadd(day,-" + iDays + ",getdate())) "
				+ "and p.UsageDate<CONVERT(DATE,dateadd(day,0,getdate())) "
				+ "group by UtilityAccountNumber, p.UsageDate order by 1  ";
		return sSolarDailyUsageQuery;
	}

	/**
	 * This method gives all the Hourly solar generation result
	 *
	 * @param utilityAccNumber
	 * @param meterNumber
	 * @return
	 */
	public static String getHourlySolarGeneration(String utilityAccNumber, String meterNumber) {
		String meter;
		if (meterNumber == null) {
			meter = null;
		} else {
			meter = "'" + meterNumber + "'";
		}
		String query = ";" + "  declare @UtilityAccountNumber varchar(100)='" + utilityAccNumber + "'"
				+ "   ,@MeterNumber varchar(100)=" + meter + " select UtilityAccountNumber "
				+ ", p.UsageDate AS GenerationDate " + ", sum(P.Value) kWh, sum(p.Consumed) DollarValue "
				+ "from account a " + "join AccountMeterMapping amm on a.AccountNumber=amm.AccountNumber "
				+ "join SolarHourlyUsage p on a.AccountNumber=p.AccountNumber and amm.meterid=p.meterid "
				+ "where a.UtilityAccountNumber=@UtilityAccountNumber "
				+ "and amm.meternumber=isnull(@MeterNumber,amm.meternumber) " + "and amm.Status=1 "
				+ "and CONVERT(DATE,p.UsageDate)=CONVERT(DATE,GETDATE()-1) "
				+ "group by UtilityAccountNumber, p.UsageDate order by 1,2  ";
		return query;
	}

	/*********************************************
	 * >>>>> COMPARE SPENDING PAGE QUERIES <<<<< *
	 *********************************************/
	/**
	 * @param sUtilityAccountNumber
	 * @return
	 */
	public static String getCompareSofarAndProjectedUsage(String sUtilityAccountNumber) {
		String sSolarHourlyUsageQuery = "SELECT * FROM "
				+ "UsageGenerationSummary where AccountNumber=(select AccountNumber from account where UtilityAccountNumber ='"
				+ sUtilityAccountNumber + "')";
		return sSolarHourlyUsageQuery;
	}

	/**
	 * @param sUtilityAccountNumber
	 * @param sUsageTypeKwh
	 * @param sUsageTypeDollar
	 * @param iConfiguredCspMonth
	 * @return
	 */
	public static String getCompareMePreviousCurrentUsage(String sUtilityAccountNumber, String sUsageTypeKwh,
			String sUsageTypeDollar, int iConfiguredCspMonth) {
		String sCompareMePreviousCurrentUsageQuery = ";" + "declare @UtilityAccountNumber varchar(100)='"
				+ sUtilityAccountNumber + "'"
				+ " select curr.UtilityAccountNumber,[CurrentYearMonth],[CurrentKwhValue],[CurrentDollarValue], "
				+ "[PreviousYearMonth],[PreviousKwhValue],[PreviousDollarValue] from "
				+ "(select UtilityAccountNumber , DATEADD(MONTH, DATEDIFF(MONTH, 0, p.UsageDate), 0) AS [CurrentYearMonth] , sum(P."
				+ sUsageTypeKwh + ") AS [CurrentKwhValue] , sum(p." + sUsageTypeDollar
				+ ") AS [CurrentDollarValue] from account a "
				+ "join AccountCompareSpending p on a.AccountNumber=p.AccountNumber "
				+ "where a.UtilityAccountNumber=@UtilityAccountNumber and p.UsageDate>=dateadd(mm,-1-"
				+ iConfiguredCspMonth + ",getdate()) " + " AND p.UsageDate <= dateadd(mm, - 1, getdate()) "
				+ "group by UtilityAccountNumber, p.UsageDate ) curr join "
				+ "(select UtilityAccountNumber , DATEADD(MONTH, DATEDIFF(MONTH, 0, p.UsageDate), 0) AS [PreviousYearMonth] "
				+ ", sum(P." + sUsageTypeKwh + ") AS [PreviousKwhValue], sum(p." + sUsageTypeDollar
				+ ") AS [PreviousDollarValue] from account a "
				+ "join AccountCompareSpending p on a.AccountNumber=p.AccountNumber where a.UtilityAccountNumber=@UtilityAccountNumber "
				+ "and p.UsageDate>=dateadd(mm,-13-" + iConfiguredCspMonth
				+ ",getdate()) and  p.UsageDate<=dateadd(mm,-1-" + iConfiguredCspMonth + ",getdate()) "
				+ " group by UtilityAccountNumber, p.UsageDate "
				+ ") pre on curr.UtilityAccountNumber=pre.UtilityAccountNumber  and month(CurrentYearMonth) =month(PreviousYearMonth)";
		return sCompareMePreviousCurrentUsageQuery;
	}

	/**
	 * This query brings zipusage data for the account and all the users in the zip.
	 *
	 * @param sUtilityAccountNumber
	 * @param sUsageTypeKwh
	 * @param sUsageTypeDollar
	 * @param iConfiguredCspMonth
	 * @return
	 */
	public static String getCompareZipUsage(String sUtilityAccountNumber, String sUsageTypeKwh, String sUsageTypeDollar,
			int iConfiguredCspMonth) {
		String sCompareZipUsageQuery = ";" + "declare @UtilityAccountNumber varchar(100)='" + sUtilityAccountNumber
				+ "'" + " DECLARE @ZipCode VARCHAR(20),@AddressType TINYINT"
				+ " SELECT @ZipCode=CA.ZipCode,@AddressType=CA.AddressType FROM CustomerAddress CA "
				+ "WHERE CA.UtilityAccountNumber=@UtilityAccountNumber "
				+ "select  case when [CurrentYearMonth] is not null then  [CurrentYearMonth]"
				+ " when [CurrentYearMonth] is null then [previousYearMonth]  end as [CurrentYearMonth],[MyUsageUnit], "
				+ "[MyUsageValue] ,[ZipUsageUnit],[ZipUsageValue] " + "from  ( "
				+ "select    CA.ZipCode , DATEADD(MONTH, DATEDIFF(MONTH, 0, p.UsageDate), 0) AS [CurrentYearMonth] "
				+ ", sum(P." + sUsageTypeKwh + ") AS [MyUsageUnit], sum(p." + sUsageTypeDollar + ") AS [MyUsageValue] "
				+ "FROM CustomerAddress CA " + "JOIN Account A ON CA.AddressID=A.AddressID "
				+ "JOIN AccountCompareSpending p on a.AccountNumber=p.AccountNumber "
				+ "where a.UtilityAccountNumber=@UtilityAccountNumber " + "and p.UsageDate>=dateadd(mm,-1-"
				+ iConfiguredCspMonth + ",getdate()) " + " AND p.UsageDate <= dateadd(mm, - 1, getdate()) "
				+ "group by CA.ZipCode, DATEADD(MONTH, DATEDIFF(MONTH, 0, p.UsageDate), 0)) curr full outer join "
				+ "(	select p.ZipCode, DATEADD(MONTH, DATEDIFF(MONTH, 0, p.UsageDate), 0) AS [PreviousYearMonth] "
				+ " , sum(P." + sUsageTypeKwh + ") AS [ZipUsageUnit] , sum(p." + sUsageTypeDollar
				+ ") AS [ZipUsageValue]  "
				+ "from ZipCompareSpending p where ZipCode=@ZipCode 	and p.UsageDate>=dateadd(mm,-1-"
				+ iConfiguredCspMonth + ",getdate()) "
				+ "AND P.AddressType=@AddressType group by p.ZipCode,DATEADD(MONTH, DATEDIFF(MONTH, 0, p.UsageDate), 0) "
				+ ") pre on curr.ZipCode=pre.ZipCode and month(CurrentYearMonth) =month(PreviousYearMonth)    order by (CurrentYearMonth)";
		return sCompareZipUsageQuery;
	}

	/**
	 * This query brings Utility Usage data for the account and all the users in the
	 * zip.
	 *
	 * @param sUtilityAccountNumber
	 * @param sUsageTypeKwh
	 * @param sUsageTypeDollar
	 * @param iConfiguredCspMonth
	 * @return
	 */
	public static String getCompareUtilityUsage(String sUtilityAccountNumber, String sUsageTypeKwh,
			String sUsageTypeDollar, int iConfiguredCspMonth) {
		String sCompareUtilityUsageQuery = ";" + "declare @UtilityAccountNumber varchar(100)='" + sUtilityAccountNumber
				+ "' DECLARE @AddressType TINYINT "
				+ "SELECT @AddressType=CA.AddressType FROM CustomerAddress CA WHERE CA.UtilityAccountNumber=@UtilityAccountNumber "
				+ "select  case when [CurrentYearMonth] is not null then  [CurrentYearMonth]"
				+ "when [CurrentYearMonth] is null then [previousYearMonth]  end as [CurrentYearMonth],[MyUsageUnit], "
				+ "[MyUsageValue] ,[UtilityUsageUnit],[UtilityUsageValue] "
				+ "from ( select     DATEADD(MONTH, DATEDIFF(MONTH, 0, p.UsageDate), 0) AS [CurrentYearMonth] "
				+ ", sum(P." + sUsageTypeKwh + ") AS [MyUsageUnit], sum(p." + sUsageTypeDollar + ") AS [MyUsageValue] "
				+ " FROM CustomerAddress CA JOIN Account A ON CA.AddressID=A.AddressID JOIN AccountCompareSpending p "
				+ "on a.AccountNumber=p.AccountNumber "
				+ " where a.UtilityAccountNumber=@UtilityAccountNumber  and p.UsageDate>=dateadd(mm,-1-"
				+ iConfiguredCspMonth + ",getdate())" + " AND p.UsageDate <= dateadd(mm, - 1, getdate()) "
				+ "      group by DATEADD(MONTH, DATEDIFF(MONTH, 0, p.UsageDate), 0) " + ") curr full outer join "
				+ "(select    DATEADD(MONTH, DATEDIFF(MONTH, 0, p.UsageDate), 0) AS [PreviousYearMonth]  " + ", sum(P."
				+ sUsageTypeKwh + ") AS [UtilityUsageUnit] , sum(p." + sUsageTypeDollar + ") AS [UtilityUsageValue]  "
				+ "     from UtilityCompareSpending  p   where p.UsageDate>=dateadd(mm,-13,getdate())  "
				+ "    AND P.AddressType=@AddressType   group by DATEADD(MONTH, DATEDIFF(MONTH, 0, p.UsageDate), 0) "
				+ ") pre on  month(CurrentYearMonth) =month(PreviousYearMonth) order by (CurrentYearMonth)";
		return sCompareUtilityUsageQuery;
	}

	/**
	 * This query brings All Usage data for the account and all the users in the
	 * zip.
	 *
	 * @param sUtilityAccountNumber
	 * @param sUsageTypeKwh
	 * @param sUsageTypeDollar
	 * @param iConfiguredCspMonth
	 * @return
	 */
	public static String getCompareAllUsage(String sUtilityAccountNumber, String sUsageTypeKwh, String sUsageTypeDollar,
			int iConfiguredCspMonth) {
		String sCompareAllUsageQuery = ";" + "declare @UtilityAccountNumber varchar(100)='" + sUtilityAccountNumber
				+ "'"
				+ " DECLARE @ZipCode VARCHAR(20),@AddressType TINYINT SELECT @ZipCode=CA.ZipCode,@AddressType=CA.AddressType "
				+ " FROM CustomerAddress CA WHERE CA.UtilityAccountNumber=@UtilityAccountNumber "
				+ " select b.[PreviousYearMonth] as CurrentYearMonth, e.kWh as MyUsageUnit, e.DollarValue as MyUsageValue , "
				+ " dateadd(mm,-12,b.[PreviousYearMonth]) as PreviousYearMonth, e.prekWh as PreviousUsageUnit, e.preDollarValue as PreviousUsageValue, "
				+ " b.[ZipUsageUnit], b.[ZipUsageValue], c.[UtilityUsageUnit], c.[UtilityUsageValue] FROM (select [CurrentYearMonth], "
				+ " curr.kWh, curr.DollarValue, [PreviousYearMonth], pre.kWh as prekwh, pre.DollarValue AS preDollarValue from "
				+ " (select UtilityAccountNumber , DATEADD(MONTH, DATEDIFF(MONTH, 0, p.UsageDate), 0) AS [CurrentYearMonth] "
				+ " , sum(P." + sUsageTypeKwh + ") kWh, sum(p." + sUsageTypeDollar
				+ ") DollarValue from account a join AccountCompareSpending p on a.AccountNumber=p.AccountNumber "
				+ " where a.UtilityAccountNumber=@UtilityAccountNumber and p.UsageDate>=dateadd(mm,-1-"
				+ iConfiguredCspMonth + ",getdate()) group by UtilityAccountNumber, p.UsageDate "
				+ " ) curr full outer join ( select UtilityAccountNumber , DATEADD(MONTH, DATEDIFF(MONTH, 0, p.UsageDate), 0) AS [PreviousYearMonth] "
				+ " , sum(P." + sUsageTypeKwh + ") kWh, sum(p." + sUsageTypeDollar
				+ ") DollarValue  from account a join AccountCompareSpending p on a.AccountNumber=p.AccountNumber "
				+ " where a.UtilityAccountNumber=@UtilityAccountNumber and p.UsageDate>=dateadd(mm,-13-"
				+ iConfiguredCspMonth + ",getdate()) and p.UsageDate<=dateadd(mm,-1-" + iConfiguredCspMonth
				+ ",getdate()) " + " group by UtilityAccountNumber, p.UsageDate "
				+ " ) pre on curr.UtilityAccountNumber=pre.UtilityAccountNumber and month(CurrentYearMonth) =month(PreviousYearMonth)) e "
				+ "full outer JOIN(select [CurrentYearMonth],[MyUsageUnit],[MyUsageValue] ,[PreviousYearMonth],[ZipUsageUnit],[ZipUsageValue] "
				+ " from (select    CA.ZipCode, DATEADD(MONTH, DATEDIFF(MONTH, 0, p.UsageDate), 0) AS [CurrentYearMonth] "
				+ " , sum(P." + sUsageTypeKwh + ") AS [MyUsageUnit], sum(p." + sUsageTypeDollar
				+ ") AS [MyUsageValue] FROM CustomerAddress CA "
				+ "  JOIN Account A ON CA.AddressID=A.AddressID JOIN AccountCompareSpending p on a.AccountNumber=p.AccountNumber  "
				+ "  where a.UtilityAccountNumber=@UtilityAccountNumber and p.UsageDate>=dateadd(mm,-1-"
				+ iConfiguredCspMonth + ",getdate()) "
				+ "  group by CA.ZipCode, DATEADD(MONTH, DATEDIFF(MONTH, 0, p.UsageDate), 0) "
				+ " ) curr full outer join ( select   p.ZipCode , DATEADD(MONTH, DATEDIFF(MONTH, 0, p.UsageDate), 0) AS [PreviousYearMonth] "
				+ "  , sum(P." + sUsageTypeKwh + ") AS [ZipUsageUnit] , sum(p." + sUsageTypeDollar
				+ ") AS [ZipUsageValue]  "
				+ " from ZipCompareSpending p where ZipCode=@ZipCode and p.UsageDate>=dateadd(mm,-1-"
				+ iConfiguredCspMonth + ",getdate())  "
				+ "  AND P.AddressType=@AddressType group by p.ZipCode,DATEADD(MONTH, DATEDIFF(MONTH, 0, p.UsageDate), 0) "
				+ " ) pre on curr.ZipCode=pre.ZipCode and month(CurrentYearMonth) =month(PreviousYearMonth))b ON e.currentyearmonth = b.currentyearmonth "
				+ " full outer JOIN(select [CurrentYearMonth],[PreviousYearMonth],[MyUsageUnit],[MyUsageValue],[UtilityUsageUnit],[UtilityUsageValue] "
				+ " from ( select     DATEADD(MONTH, DATEDIFF(MONTH, 0, p.UsageDate), 0) AS [CurrentYearMonth] "
				+ "  , sum(P." + sUsageTypeKwh + ") AS [MyUsageUnit], sum(p." + sUsageTypeDollar
				+ ") AS [MyUsageValue] FROM CustomerAddress CA "
				+ " JOIN Account A ON CA.AddressID=A.AddressID JOIN AccountCompareSpending p on a.AccountNumber=p.AccountNumber "
				+ "  where a.UtilityAccountNumber=@UtilityAccountNumber and p.UsageDate>=dateadd(mm,-1-"
				+ iConfiguredCspMonth + ",getdate()) " + " AND p.UsageDate <= dateadd(mm, - 1, getdate()) "
				+ "  group by DATEADD(MONTH, DATEDIFF(MONTH, 0, p.UsageDate), 0) "
				+ " ) curr full outer join (select    DATEADD(MONTH, DATEDIFF(MONTH, 0, p.UsageDate), 0) AS [PreviousYearMonth] "
				+ "  , sum(P." + sUsageTypeKwh + ") AS [UtilityUsageUnit] , sum(p." + sUsageTypeDollar
				+ ") AS [UtilityUsageValue]  " + " from UtilityCompareSpending  p  where p.UsageDate>=dateadd(mm,-1-"
				+ iConfiguredCspMonth + ",getdate())  AND P.AddressType=@AddressType "
				+ " group by DATEADD(MONTH, DATEDIFF(MONTH, 0, p.UsageDate), 0) "
				+ ") pre on  month(CurrentYearMonth) =month(PreviousYearMonth))c "
				+ "  ON b.previousyearmonth = c.previousyearmonth order by c.previousyearmonth;";
		return sCompareAllUsageQuery;
	}

	/*****************************************
	 * >>>>> OUTAGES PAGE QUERIES (Osp) <<<<<<*
	 *****************************************/
	/**
	 * This method is to get the outage id for given outage message.
	 *
	 * @param outageMsg
	 * @return
	 */
	public static String getOutageIdQuery(String outageMsg) {
		String sOutageIdQuery = ";" + "SELECT OutageId FROM outageNotification WHERE OutageMessage='" + outageMsg + "'"
				+ " and IsResolved='0'";
		return sOutageIdQuery;
	}

	public static String getCityIDOfUser(String UserName) {
		String query = "select CityID from CityMaster where CityName in (Select CityName from [User] where UserName='"
				+ UserName + "')";
		return query;
	}

	public static String getOutageDetails(String outageId) {
		return "select * from outagenotification where outageid = '" + outageId + "'";
	}

	/**
	 * This method used to get the customer effected in given outage.
	 *
	 * @param outageId
	 * @return
	 */
	public static String getCustomerAffected(String outageId) {
		String sCustomerAffected = ";"
				+ "SELECT COUNT(AccountNumber) as AccountNumber from customerOutage where OutageID='" + outageId + "'";
		return sCustomerAffected;
	}

	public static String getPrePopulatedOutageDetails(String userName) {
		String query = "select u.fullname, u.mobilephone, u.emailid, ua.isdefaultaccount, ua.utilityaccountnumber\n"
				+ "from [user] as u\n" + "left join useraccount as ua on ua.userid = u.userid\n"
				+ "where u.username = '" + userName + "'\n" + "and ua.isdefaultaccount = '1'";
		return query;
	}

	/*******************************************
	 * >>>>> CONNECT ME PAGE QUERIES (Cmp) <<<<<*
	 *******************************************/
	/**
	 * This method is to get the Service Account Number in Random
	 *
	 * @return String
	 */
	public static String getConnectMeFeatures() {
		String sServiceAccountNumber = ";"
				+ "SELECT FeatureName, Status FROM dbo.FeatureSettings WHERE FeatureName LIKE 'ConnectMe.%'";
		return sServiceAccountNumber;
	}

	/******************************************
	 * >>>>> SERVICE REQUEST PAGE QUERIES <<<<<*
	 ******************************************/
	/**
	 * This method is to get the query for fetching service request reason name from
	 * the DB.
	 *
	 * @return
	 */
	public static String getPreLoginReasonNameList() {
		String sServiceReasonName = "SELECT ReasonName FROM SRReasonMaster WHERE isActive=1 AND isPreLogin = 1";
		return sServiceReasonName;
	}

	/**
	 * This method is to get the query for fetching service request reason name from
	 * the DB.
	 *
	 * @return
	 */
	public static String getPostLoginReasonNameList() {
		String sServiceReasonName = ";" + "SELECT ReasonName FROM SRReasonMaster WHERE isActive=1";
		return sServiceReasonName;
	}

	/*******************************************
	 * >>>>> ELECTRIC VEHICLE PAGE QUERIES <<<<<*
	 *******************************************/
	/**
	 * This method is to get the EV assigned to user on given address.
	 *
	 * @param userAccount
	 * @param accountId
	 * @return
	 */
	public static String getEVAssignedToUserOnGivenAccount(String userAccount, String accountId) {
		String eVAssignedToUserOnGivenAddress = ";" + "SELECT evm.Name FROM  [dbo].[UserElectricVehicleDetail] AS UEVD"
				+ " INNER JOIN [dbo].[UserElectricVehicle] AS UEV ON UEVD.[UserEVId] = UEV.[UserEVId] AND UEV.status=1"
				+ " INNER JOIN [dbo].[ElectronicVehicleMaster] AS EVM ON UEV.[EVId] = EVM.[EVId]"
				+ " INNER JOIN [dbo].[CustomerInfo] AS CI ON UEVD.[AddressId] = CI.[AddressId]"
				+ " WHERE UEV.userid=(SELECT UserID FROM [User] WHERE UserName = '" + userAccount
				+ "') AND CI.UtilityAccountNumber='" + accountId + "' order by Name Asc";
		return eVAssignedToUserOnGivenAddress;
	}

	/**
	 * This method is to get the complete electric vehicle list.
	 *
	 * @return completeEV
	 */
	public static String getCompleteEVList() {
		String completeEV = "SELECT Name FROM dbo.ElectronicVehicleMaster where IsDeleted = 0";
		return completeEV;
	}

	/**
	 * This method is to get the complete electric vehicle list.
	 *
	 * @return completeEV
	 */
	public static String unlinkElectricVehicleForAccount(String AccountNumber, String UserName) {
		String unlinkQuery = "Update [dbo].[UserElectricVehicle] set Status=0 where AddressId=(select AddressId from [CustomerInfo] where UtilityAccountNumber='"
				+ AccountNumber + "') and UserId=(SELECT UserID FROM [User] WHERE UserName = '" + UserName + "') ";
		return unlinkQuery;
	}

	/*******************************************
	 * >>>>> CHARGING STATION PAGE QUERIES <<<<<*
	 *******************************************/
	/**
	 * This method is to get the query for fetching charging station info from the
	 * DB.
	 *
	 * @return
	 */
	public static String getChargingStationsInfo() {
		String chargingStationInfo = "SELECT LocationID, LocationName, Address1, Zipcode FROM ChargingStation "
				+ "where IsActive = 1";
		return chargingStationInfo;
	}

	/**
	 * This method is to get the query for fetching charging station info from the
	 * DB.
	 *
	 * @return
	 */
	public static String getChargingStationsNameAndRate() {
		String chargingStationInfo = "SELECT LocationName, Rate FROM ChargingStation WHERE IsActive = 1 "
				+ "ORDER BY Rate ASC";
		return chargingStationInfo;
	}

	/***************************************
	 * >>>>> NOTIFICATION PAGE QUERIES <<<<<*
	 ***************************************/
	public static String getNotificationQuery(String userId) {
		String notificationQuery = "SELECT [Filter1].[MessageId1] AS [MessageId], [Filter1].[PlaceHolderName], [Filter1].[CreatedDate1] AS [CreatedDate], [Filter1].[MessageBody] AS [MessageBody], [Filter1].[Reason] AS [Reason], [Filter1].[Subject] AS [Subject], [Filter1].[EmailId] AS [EmailId], [Filter1].[ControlText] AS [ControlText], [Filter1].[IsRead] AS [IsRead], [Filter1].[IsSaved] AS [IsSaved], [Filter1].[IsTrashed] AS [IsTrashed], [Filter1].[IsReply] AS [IsReply], CASE WHEN ([Filter1].[AttachmentId] IS NULL) THEN cast(0 as bigint) ELSE [Filter1].[AttachmentId] END AS [C1], [Filter1].[ServiceID] AS [ServiceID], [Extent7].[UtilityAccountNumber] AS [UtilityAccountNumber] FROM "
				+ "(SELECT [Extent1].[AccountNumber] AS [AccountNumber], [Extent1].[IsRead] AS [IsRead], [Extent1].[IsSaved] AS [IsSaved], [Extent1].[IsTrashed] AS [IsTrashed], [Extent1].[IsReply] AS [IsReply], [Extent1].[EmailId] AS [EmailId], [Extent1].[UserID] AS [UserID1], [Extent2].[MessageDetailId] AS [MessageDetailId1], [Extent2].[MessageId] AS [MessageId2], [Extent2].[MessageBody] AS [MessageBody], [Extent2].[CreatedDate] AS [CreatedDate1], [Extent3].[MessageId] AS [MessageId1], [Extent3].[Reason] AS [Reason], [Extent3].[Subject] AS [Subject], [Extent3].[ServiceID] AS [ServiceID], [Extent4].[AttachmentId] AS [AttachmentId], [Extent5].[PlaceHolderId] "
				+ "AS [PlaceHolderId1], [Extent6].[LanguageCode] AS [LanguageCode], [Extent6].[ControlText] AS [ControlText], [Extent5].[PlaceHolderName] FROM [dbo].[UserMessages] AS [Extent1] INNER JOIN [dbo].[MessageDetail] AS [Extent2] ON [Extent1].[MessageDetailId] = [Extent2].[MessageDetailId] INNER JOIN [dbo].[MessageMaster] AS [Extent3] ON [Extent2].[MessageId] = [Extent3].[MessageId] LEFT OUTER JOIN [dbo].[MessageAttachments] AS [Extent4] ON [Extent2].[MessageDetailId] = [Extent4].[MessageDetailID] INNER JOIN [dbo].[MessagePlaceHolders] AS [Extent5] ON [Extent3].[PlaceHolderId] = [Extent5].[PlaceHolderId] INNER JOIN [dbo]. [MultiLingualMasterLookUp] AS "
				+ "[Extent6] ON [Extent5].[LookUpID] = [Extent6].[LookUpID]) AS [Filter1] INNER JOIN [dbo].[UserAccount] AS [Extent7] ON ([Filter1].[UserID1] = [Extent7].[UserID]) AND (([Filter1].[AccountNumber] = [Extent7].[AccountNumber]) OR (([Filter1].[AccountNumber] IS NULL) AND ([Extent7].[AccountNumber] IS NULL))) WHERE ([Extent7].[UserID] =  (select UserID from [User] where username = '"
				+ userId + "')) AND ([Filter1].[LanguageCode] = 'EN')\r\n" + "";
		return notificationQuery;
	}

	/*****************************************************************************************
	 * EFFICIENCY PAGE QUERIES *
	 *****************************************************************************************/

	/*****************************************************************************************
	 * FOOTPRINT PAGE QUERIES *
	 *****************************************************************************************/
	public static String getLocationId() {
		String category = "select Distinct LocationTypeId from Greenfootprintlocations";
		return category;
	}

	public static String getZipCodeForCategory(String category) {
		String ZipCode = "select ZipCode  from Greenfootprintlocations where LocationTypeId ='" + category + "'";
		return ZipCode;
	}

	public static String getNameOfFootPrintForZip(String zip) {
		String name = "select Name,Address1 from Greenfootprintlocations where ZipCode='" + zip + "'";
		return name;
	}

	public static String getAddressOfFootPrintForZip(String zip) {
		String address = "select Name,Address1 from Greenfootprintlocations where ZipCode='" + zip + "'";
		return address;
	}

	/*****************************************************************************************
	 * CSP RELATED SQL QUERIES *
	 *****************************************************************************************/
	// This query brings the number of Usage Months configured in CSP
	public static String usageMonthSettingsCspQuery = ""
			+ "select configOption, ConfigValue from utilityconfig  where configid=21";

	public static String allUtilitySettingsQuery = ""
			+ "SELECT * FROM UtilitySettings WITH(NOLOCK) WHERE UtilityId = 0";

	// This query brings the number of solar days to be displayed and is
	// configured in CSP
	public static String sConfiguredSolarDays = ""
			+ "SELECT SolarDays FROM UtilitySettings WITH(NOLOCK) WHERE UtilityId = 0";

	// This query brings the number of Usage Months configured in CSP
	public static String decimalPlaceAdminSettingsCspQuery = "" + "SELECT UptoDecimalPlaces FROM UtilitySettings";

	// This query brings the Date format settings in the CSP
	public static String dateFormatConfigSettingsCspQuery = ""
			+ "select FormatName from MetricSystemMaster WHERE MetricSystemMaster.RID in(Select DateFormats from MetricSystemSettings)";

	// This query brings the Currency settings in the CSP
	public static String sCurrencyConfigSettingsCspQuery = ""
			+ "select DisplayName from MetricSystemMaster WHERE MetricSystemMaster.RID in(Select CurrencySymbols from MetricSystemSettings)";

	public static String defaultLanguageQuery = "" + "SELECT LanguageDescription FROM LanguageMaster where IsDefault=1";

	public static String getDefaultLanguage() {
		return "SELECT languagedescription FROM LanguageMaster where IsDefault=1";
	}

	public static String configuredLanguageQuery = "" + "SELECT LanguageDescription FROM LanguageMaster where Status=1";

	/**
	 * This method used to get query to fetch the date format as per csp config.
	 *
	 * @return
	 */
	public static String getDateFormatAsCspConfig() {
		String sDateFormatConfigSettingsCspQuery = ""
				+ "SELECT FormatName FROM MetricSystemMaster WHERE MetricSystemMaster.RID IN(SELECT DateFormats FROM MetricSystemSettings)";
		return sDateFormatConfigSettingsCspQuery;
	}
	/*	*//**
			 * This query is for getting the Payment Options Value in CSP(Like Maximum
			 * Payment Amount, Processing Fee etc.)
			 *//*
				 * public static String getCspPaymentOptionDetails(String sAccountNumber) {
				 * String sCspPaymentOption = ";" +
				 * " Select distinct case when po.AccountType=1 then '" + "Residential'" +
				 * " else 'Commercial" + "' end as AccountType," +
				 * " case when po.PaymentType=1 then '" + "Credit Card' else" + " 'Bank Account"
				 * + "' end as PaymentType, pf.FromValue, pf.ToValue," +
				 * " po.MaxAmount, pf.Charge" +
				 * " from [SCM10_S].[dbo].[Customerinfo]c (NOLOCK) join " +
				 * " [SCM10_S_API_Payment].[dbo].[PaymentConfigurationSettingOptions] po (NOLOCK) "
				 * + "on c.AddressTypeid=po.AccountType and  po.VendorConfigurationId = 1 " +
				 * "join  [SCM10_S_API_Payment].[dbo].[PaymentConfigurationConvenienceFees] pf (NOLOCK) "
				 * + "on po.id=pf.ConfigurationId and pf.isactive=1 " +
				 * "where c.utilityaccountnumber in ('" + sAccountNumber + "')"; return
				 * sCspPaymentOption; }
				 */

	/**
	 * This query is for getting the Payment Options Value in CSP(Like Maximum
	 * Payment Amount, Processing Fee etc.)
	 */
	public static String getCspPaymentOptionDetails(String sPaymentProvider, String sAccountState,
			String sAccountNumber) {
		String sCspPaymentOption = ";" + " declare @AccountState varchar(20)='" + sAccountState + "'"
				+ " declare @PaymentProvider varchar(20)='" + sPaymentProvider + "'"
				+ " declare @utilityaccountnumber varchar(40)='" + sAccountNumber + "'"
				+ "      Select distinct case when po.AccountType=1 then '" + "Residential'"
				+ " else 'Commercial' end as AccountType, "
				+ " case when po.PaymentType=1 then 'Credit Card' else 'Bank Account' end as PaymentType, pf.FromValue, "
				+ " pf.ToValue, po.MaxAmount, pf.Charge from [SCM10_S].[dbo].[Customerinfo]c "
				+ " (NOLOCK)  join  [SCM10_S_API_Payment].[dbo].[PaymentConfigurationSettingOptions] po (NOLOCK) "
				+ " on c.AddressTypeid=po.AccountType "
				+ " left Join  [SCM10_S_API_Payment].[dbo].[paymentVendorUtilityConfigurations] pvu on po.vendorconfigurationid=pvu.id "
				+ " left join  [SCM10_S_API_Payment].[dbo].[vendors] v on pvu.vendorid=v.id "
				+ " left join  [SCM10_S_API_Payment].[dbo].[PaymentConfigurationConvenienceFees] pf (NOLOCK) "
				+ " on po.id=pf.ConfigurationId and pf.isactive=1 where c.utilityaccountnumber in (@utilityaccountnumber) "
				+ " and pvu.Accountstatename in(@AccountState) and v.paymentprovider in (@PaymentProvider)";
		return sCspPaymentOption;
	}

	/**
	 * This method brings the sql query for Date Format, Currency, Temperature, Time
	 * format and Time Zone settings
	 *
	 * @return
	 */
	public static String getDateFormatMetricSettings() {
		String sDateFormatMetricSettings = ""
				+ "select 'Time Zone' AS Name ,'('+UTCOffset+') '+TimeZoneName as DisplayName from [TimeZone] TZ "
				+ " JOIN MetricSystemSettings MSS ON TZ.TimeZoneID=MSS.TimeZoneID UNION "
				+ "select 'Date format' AS Name ,DisplayName from MetricSystemMaster MS "
				+ "JOIN MetricSystemSettings MSS ON MS.RID=MSS.dateformats UNION "
				+ "select 'Time format' AS Name ,DisplayName from MetricSystemMaster MS "
				+ "JOIN MetricSystemSettings MSS ON MS.RID=MSS.Timeformats UNION "
				+ "select 'Temprature' AS Name ,DisplayName from MetricSystemMaster MS "
				+ "JOIN MetricSystemSettings MSS ON MS.RID=MSS.Tempratureormats UNION "
				+ "select 'Currency' AS Name ,DisplayName from MetricSystemMaster MS "
				+ "JOIN MetricSystemSettings MSS ON MS.RID=MSS.CurrencySymbols "
				+ "UNION select 'Distance' AS Name ,DisplayName from MetricSystemMaster MS "
				+ "JOIN MetricSystemSettings MSS ON MS.RID=MSS.DistanceFormats ";
		return sDateFormatMetricSettings;
	}

	/**
	 * This query will fetch the configured value for an option
	 *
	 * @param sModuleName   = Compare
	 * @param sConfigOption = Current Usage Colour in CSP
	 * @return #00f2ef
	 */
	public static String getConfigValue(String sModuleName, String sConfigOption) {
		String sConfiguredOption = ";" + "select ConfigOption, ConfigValue from utilityconfig where ModuleName = '"
				+ sModuleName + "'" + " and ConfigOption ='" + sConfigOption + "'";
		return sConfiguredOption;
	}

	/**
	 * This query will fetch the configured value for an option
	 *
	 * @param sModuleName = Compare = Current Usage Colour in CSP
	 * @return #00f2ef
	 */
	public static String getConfigValue(String sModuleName) {
		String sConfiguredOption = ";" + "select ConfigOption, ConfigValue from utilityconfig where ModuleName = '"
				+ sModuleName + "'";
		return sConfiguredOption;
	}

	/**
	 * This query will validate the module configured or not in CSP
	 *
	 * @return 1 = Available, 0- Not available
	 */
	public static String getFeatureConfigured(String sFeatureName) {
		String sFeatureStatus = ";" + "SELECT Status FROM dbo.FeatureSettings WHERE FeatureName ='" + sFeatureName
				+ "'";
		return sFeatureStatus;
	}

	/**
	 * This query will validate the SCP module configured or not in CSP
	 *
	 * @return 1 = Available, 0 - Not available
	 */
	public static String getAllFeatureConfigured() {
		String sFeatureStatus = "SELECT FeatureName, Status FROM dbo.FeatureSettings";
		return sFeatureStatus;
	}

	/**
	 * This query will validate the CSP module configured or not in CSP
	 *
	 * @return 1 = Available, 0 - Not available
	 */
	public static String getAllCspModules() {
		String sCspModules = ";" + "SELECT RightName, IsActive FROM [right] WHERE ParentID=0";
		return sCspModules;
	}

	public static String getUserCount(String sOperator, int iStatus) {
		String sGetUserCount = ";"
				+ "select  count(AccountNumber) as AccountNumber from Account WHERE AccountNumber NOT IN (-1,2) AND Status"
				+ sOperator + "'" + iStatus + "'";
		return sGetUserCount;
	}

	public static String getUtilityAccountNo(String sOperator, int iStatus) {
		String sUtilityNumber = ";"
				+ "Select utilityAccountnumber from Account where AccountNumber not in (-1,2) and status" + sOperator
				+ "'" + iStatus + "'";
		return sUtilityNumber;
	}

	public static String getUserInfo(String sOperator, int iStatus, String sUtilityAccountNumber) {
		String sUserDetails = ";"
				+ "SELECT * FROM Customer FULL OUTER JOIN CustomerAddress ON Customer.customerid = CustomerAddress.customerid where CustomerAddress.AddressID=(select AddressId from Account Where AccountNumber Not in (-1,2) and Status"
				+ sOperator + "'" + iStatus + "'" + "and utilityAccountNumber='" + sUtilityAccountNumber + "')";
		return sUserDetails;
	}

	public static String getUserMeterNumber(String sOperator, int iStatus, String sUtilityAccountNumber) {
		String sUserDetails = ";" + "SELECT meternumber FROM AccountMeterMapping WHERE AccountNumber='"
				+ sUtilityAccountNumber + "'";
		return sUserDetails;
	}

	public static String getUserStatus(String sUtilityAccountNumber) {
		String sUserDetails = ";" + "select status from Account Where AccountNumber Not in (-1,2)"
				+ "and utilityAccountNumber='" + sUtilityAccountNumber + "'";
		return sUserDetails;
	}

	public static String getUserIsLockStatus(String sUtilityAccountNumber) {
		String sUserIsLockStatus = ";"
				+ "SELECT top 1 * FROM [UserAccount] Full OUTER JOIN [User] on [UserAccount].UserID=[User].UserID where [UserAccount].UtilityAccountNumber='"
				+ sUtilityAccountNumber + "'";
		return sUserIsLockStatus;
	}

	public static String getUserMessage(String reason) {
		String sUserIsLockStatus = ";" + "Select * from MessageMaster where Reason='" + reason
				+ "'order by messageID desc";
		return sUserIsLockStatus;
	}

	public static String getUserDetailsFromDB() {
		String sUserDetails = ";"
				+ "SELECT Top 1 c.FirstName,c.LastName,c.CustomerID,c.MobilePhone,c.DrivingLicence,C.customerNo,CA.UtilityAccountNumber AS UtilityAccountNumber ,MAX(AMM.MeterNumber) AS MeterNumber,CA.ZipCode,CA.Address1,C.SSNNumber "
				+ "  ,(CASE WHEN CA.AddressType=1 THEN 'Residential' ELSE 'Commercial' END) AS CustomerType " + "  "
				+ "FROM Customer c(NOLOCK)   JOIN CustomerAddress CA(NOLOCK) ON c.CustomerID=ca.CustomerID "
				+ "JOIN Account a(NOLOCK) ON ca.AddressID=a.AddressID "
				+ "JOIN AccountMeterMapping AMM(NOLOCK) ON A.AccountNumber=AMM.AccountNumber " + "LEFT JOIN  " + "( "
				+ "SELECT DISTINCT IA.AccountNumber          FROM Customer IC(NOLOCK) "
				+ "JOIN CustomerAddress ICA(NOLOCK) ON IC.CustomerID=ICA.CustomerID "
				+ "JOIN Account IA(NOLOCK) ON IA.AddressID=ICA.AddressID "
				+ "JOIN UserAccount IUA(NOLOCK) ON IUA.AccountNumber=IA.AccountNumber "
				+ ")R ON A.AccountNumber=R.AccountNumber   WHERE R.AccountNumber IS NULL "
				+ "AND c.CustomerID NOT IN (1,-1)" + "AND CA.AddressType=1"
				+ "GROUP BY c.FirstName,c.LastName,c.CustomerID,c.MobilePhone,C.customerNo,(CASE WHEN CA.AddressType=1 THEN 'Residential' ELSE 'Commercial' END) "
				+ ",c.DrivingLicence,CA.ZipCode,CA.Address1,C.SSNNumber,CA.UtilityAccountNumber";
		return sUserDetails;
	}

	/**
	 * This method returns query which return user details for the given username.
	 *
	 * @param sUserName
	 * @return
	 */
	public static String getUserDetailsQuery(String sUserName) {
		String sQuery = "SELECT * FROM [User] " + "WHERE UserName = '" + sUserName + "'";
		return sQuery;
	}

	public static String getAdminUserDetailsWithUsername(String adminUserName) {
		String Query = "select * from adminuser where UserName='" + adminUserName + "';";
		return Query;
	}

	public static String updateAdminUserPasswordWithGivenPassword(String adminUsername, String password) {
		String Query = "Update adminuser set Password = '" + password + "' where Username = '" + adminUsername + "';";
		return Query;
	}

	public static String updateAdminUsertoInactiveWithUsername(String adminUserName) {
		String Query = "Update adminuser set Status = '2' where username = '" + adminUserName + "';";
		return Query;
	}

	public static String getUserDetailsWithUsername(String userName) {
		return "select u.userid, ua.utilityaccountnumber, ua.accountnumber \n" + "from useraccount as ua\n"
				+ "left join [user] as u on u.userid = ua.userid \n" + "where u.username = '" + userName + "'";
	}

	public static String getTopLatestUpdatedActiveUser() {
		String query = "select top 1 userid, username, password, emailid, status from [user]\n" + "where status = 1\n"
				+ "order by userid desc";
		return query;
	}

	public static String updatePasswordWithUserId(String userId, String password) {
		String query = "update [user]\n" + "set password = '" + password + "'\n" + "where userid = '" + userId + "'";
		return query;
	}

	public static String getEmailContentResetPwdCsp(String emailId) {
		String query = "select top 1 * from contractaccountnotifyemail\n" + "where emailid = '" + emailId + "' \n"
				+ "and subject = 'SCM Password Reset Link'\n" + "and module = 'Admin'\n" + "order by id desc";
		return query;
	}

	public static String getEmailContentResetPwdCSP(String emailId) {
		String query = "select top 1 * from contractaccountnotifyemail\n" + "where emailid = '" + emailId + "' \n"
				+ "and subject = 'Smart Energy Water Password Reset Link'\n" + "order by id desc";
		return query;
	}

	public static String getUserIsArchiveStatus(String userIP) {
		String sUserIsLockStatus = ";"
				+ "SELECT top 1  IsArchive FROM UserActivityTrail WHERE ActivityStatus<>1 and IPAddress='" + userIP
				+ "' order by activitydatetime desc";
		return sUserIsLockStatus;
	}

	public static String getUserBillingAmount(String sBillingdate) {
		String sUserIsLockStatus = ";"
				+ "SELECT Value FROM BillingDetail WHERE BillingID=( SELECT BillingId from Billing WHERE AccountNumber=16 and billingDate= '"
				+ sBillingdate + "') and Headid='22'";
		return sUserIsLockStatus;
	}

	public static String getUserBillingAmount(String sBillingdate, String sUtilityNum) {
		String sUserIsLockStatus = ";"
				+ " SELECT Value FROM BillingDetail WHERE BillingID=( SELECT BillingId from Billing WHERE AccountNumber=(select Accountnumber from Account Full outer join CustomerAddress on Account.AddressId=customerAddress.AddressId where CustomerAddress.utilityAccountnumber='"
				+ sUtilityNum + "') and billingDate= '" + sBillingdate + "') and Headid='22'";
		return sUserIsLockStatus;
	}

	public static String getUserPaymentAmount(String sPaymentDate, String sUtilityNum) {
		String sUserIsLockStatus = ";"
				+ "  SELECT * FROM BillingTransaction  where AccountNumber=(select Accountnumber from Account Full outer join CustomerAddress on Account.AddressId=customerAddress.AddressId where CustomerAddress.utilityAccountnumber='"
				+ sUtilityNum + "') and TransactionDate='" + sPaymentDate + "'";
		return sUserIsLockStatus;
	}

	public static String getUserMeterDetails(String sUtilityNumber) {
		String sUserMeterDetails = ";"
				+ "SELECT * FROM AccountMeterMapping where AccountNumber=(select Accountnumber from Account Full outer join CustomerAddress on Account.AddressId=customerAddress.AddressId where CustomerAddress.utilityAccountnumber='"
				+ sUtilityNumber + "') and Status='1'";
		return sUserMeterDetails;
	}

	public static String getUserUtilityNumberByMeterType(String sMeterType, String sStatus, String sOperator) {
		String utilityNum = ";"
				+ "select UtilityAccountNumber from Account where AccountNumber=(SELECT Top 1 AccountNumber FROM AccountMeterMapping WHERE Status = '"
				+ sStatus + "' and MeterType = '" + sMeterType
				+ "' and accountnumber not in (-1,2) GROUP BY AccountNumber HAVING COUNT(MeterType)" + sOperator + "1)";
		return utilityNum;
	}

	public static String getMeterNumberWithStatus(String sMeterType, String sUtilityNumber, String sStatus) {
		String meterNumber = ";"
				+ "SELECT MeterNumber FROM AccountMeterMapping where AccountNumber=(select Accountnumber from Account Full outer join CustomerAddress on Account.AddressId=customerAddress.AddressId where CustomerAddress.utilityAccountnumber='"
				+ sUtilityNumber + "') and Status='" + sStatus + "' and MeterType='" + sMeterType + "'";
		return meterNumber;
	}

	public static String getAllMeterCount(String sUtilityNumber, String sStatus, String sMeterType) {
		String meterCount = ";" + "SELECT Count (DISTINCT MeterNumber) as MeterNumber FROM vcustomer WHERE status='"
				+ sStatus + "' and metertype='" + sMeterType + "' AND UtilityAccountNumber='" + sUtilityNumber + "'";
		return meterCount;
	}

	public static String getAllAMIMeterCount(String sUtilityNumber, String iSAMI, String sStatus, String sMeterType) {
		String meterCount = ";" + "SELECT Count (DISTINCT MeterNumber) as MeterNumber FROM vcustomer WHERE IsAMI='"
				+ iSAMI + "' and status='" + sStatus + "' AND metertype='" + sMeterType + "' AND UtilityAccountNumber='"
				+ sUtilityNumber + "'";
		return meterCount;
	}

	public static String getUtilityNumberWithOneAMIMeter(String iSAMI, String sStatus, String sMeterType) {
		String utilityAccountNo = ";"
				+ "select UtilityAccountNumber from Account where AccountNumber=(SELECT Top 1 AccountNumber FROM AccountMeterMapping WHERE IsAMI='"
				+ iSAMI + "' and status='" + sStatus + "' AND metertype='" + sMeterType
				+ "'and accountnumber not in (-1,2) GROUP BY AccountNumber HAVING COUNT(IsAMI)=1)";
		return utilityAccountNo;
	}
	// This query brings the disclaimer text for a specific language.

	/**
	 * @param ControlId-ML_Compare_CompareZip_NoData
	 * @param sLangauge-En
	 * @return sControlTextQuery
	 */
	public static String getControlText(String ControlId, String sLangauge) {
		String sControlTextQuery = ";" + "select ControlText from multilingualmaster where ControlId ='" + ControlId
				+ "' and LanguageCode ='" + sLangauge + "'";
		return sControlTextQuery;
	}

	/**
	 * This query is for getting the Payment Options Value in CSP(Like Maximum
	 * Payment Amount, Processing Fee etc.)
	 */
	public static String getCspPaymentOption() {
		String sCspPaymentOption = ";" + "select ConfigType, ConfigValue from [Config].[ConfigurationSetting]";
		return sCspPaymentOption;
	}

	/**
	 * This method is to get the Service Account Number in Random
	 *
	 * @return String
	 */
	public static String getServiceAccountNumber(String op, int value) {
		String sServiceAccountNumber = ";"
				+ "select UtilityAccountNumber from Account WHERE AccountNumber NOT IN (-1,2) AND Status" + op + value;
		return sServiceAccountNumber;
	}

	public static String getAdvanceSearchCustomer(String serviceAccountNumber, String accountType, String status,
			String customerNumber, String zipCode, String city, String firstName, String lastName, String email) {
		String query = ";"
				+ "SELECT DISTINCT A.customerid,A.customerNo,A.FirstName,A.LastName,A.FullName,A.EmailID,A.MobilePhone"
				+ "," + "case when b.customerid IS NOT NULL THEN 'Active' ELSE 'Inactive' END AS 'CustomerStatus'"
				+ "FROM customerinfo (NOLOCK) A "
				+ "left join customerinfo (NOLOCK) b on A.customerid=b.customerid and b.AccountStatusID IN (0,1,3)"
				+ "WHERE A.CustomerID NOT IN (1,-1) and ";
		boolean flag = false;
		if (!serviceAccountNumber.equalsIgnoreCase("")) {
			query = query + "A." + "UtilityAccountNumber='" + serviceAccountNumber + "'";
			flag = true;
		}
		if (!accountType.equalsIgnoreCase("")) {
			if (flag) {
				query = query + " and ";
			}
			query = query + "A." + "AddressType='" + accountType + "'";
			flag = true;
		}
		if (!status.equalsIgnoreCase("")) {
			if (flag) {
				query = query + " and ";
			}
			// query = query + "A." + "AccountStatus='" + status + "'";
			if (status.equalsIgnoreCase("Active")) {
				query = query + "A." + "AccountStatusID IN (0,1,3) ";
			} else {
				query = query + "A." + "AccountStatusID NOT IN (0,1,3) ";
			}
			flag = true;
		}
		if (!customerNumber.equalsIgnoreCase("")) {
			if (flag) {
				query = query + " and ";
			}
			query = query + " A." + "customerNo='" + customerNumber + "'";
			flag = true;
		}
		if (!zipCode.equalsIgnoreCase("")) {
			if (flag) {
				query = query + " and ";
			}
			query = query + " A." + "ZipCode='" + zipCode + "'";
			flag = true;
		}
		if (!city.equalsIgnoreCase("")) {
			if (flag) {
				query = query + " and ";
			}
			query = query + " A." + "CityName='" + city + "'";
			flag = true;
		}
		if (!firstName.equalsIgnoreCase("")) {
			if (flag) {
				query = query + " and ";
			}
			query = query + " A." + "FirstName='" + firstName + "'";
			flag = true;
		}
		if (!lastName.equalsIgnoreCase("")) {
			if (flag) {
				query = query + " and ";
			}
			query = query + "A." + "LastName='" + lastName + "'";
			flag = true;
		}
		if (!email.equalsIgnoreCase("")) {
			if (flag) {
				query = query + " and ";
			}
			query = query + "A." + "EmailID='" + email + "'";
			flag = true;
		}
		query = query + " ORDER BY A.FullName ASC";
		return query;
	}

	public static String getAdvanceSearchServiceAccountNumber(String serviceAccountNumber, String accountType,
			String status, String customerNumber, String zipCode, String city) {
		String query = ";"
				+ "SELECT DISTINCT A.customerid,A.customerNo,A.FirstName,A.LastName,A.FullName,A.EmailID,A.MobilePhone"
				+ "," + "case when b.customerid IS NOT NULL THEN 'Active' ELSE 'Inactive' END AS 'CustomerStatus'"
				+ "FROM customerinfo (NOLOCK) A "
				+ "left join customerinfo (NOLOCK) b on A.customerid=b.customerid and b.AccountStatusID IN (0,1,3)"
				+ "WHERE A.CustomerID NOT IN (1,-1) and ";
		String que = "SELECT cust.UtilityAccountNumber AS 'ServiceAccountnumber'"
				+ " ,cust.customerNo AS 'CustomerNumber',"
				+ " cust.FullName AS 'CustomerName',cust.EmailID AS 'Customersemail',cust.MobilePhone AS 'PrimaryPhone' "
				+ " ,cust.AddressType AS 'AccountType',cust.AccountStatus,LinkedUser"
				+ " ,replace(isnull(cust.address1,'')+','+isnull(cust.address2,'')+','+isnull(cust.cityname,'')+','+isnull(cust.StateCode,'')+','+isnull(cust.ZipCode,''),',,',',') as 'Premise'"
				+ " FROM customerinfo cust"
				+ " left JOIN (SELECT AccountNumber,isnull(count(distinct userid),0) AS LinkedUser"
				+ "   FROM useraccount (NOLOCK)" + "  GROUP BY AccountNumber"
				+ " )AS linkeduser ON cust.AccountNumber=linkeduser.AccountNumber" + " WHERE ";
		boolean flag = false;
		if (!serviceAccountNumber.equalsIgnoreCase("")) {
			que = que + "cust." + "UtilityAccountNumber='" + serviceAccountNumber + "'";
			flag = true;
		}
		if (!accountType.equalsIgnoreCase("")) {
			if (flag) {
				que = que + " and ";
			}
			que = que + "cust." + "AddressType='" + accountType + "'" + " and cust.portalAccessType='standard' ";
			flag = true;
		}
		if (!status.equalsIgnoreCase("")) {
			if (flag) {
				que = que + " and ";
			}
			que = que + "cust." + "AccountStatus='" + status + "'";
			flag = true;
		}
		if (!customerNumber.equalsIgnoreCase("")) {
			if (flag) {
				que = que + " and ";
			}
			que = que + " cust." + "customerNo='" + customerNumber + "'";
			flag = true;
		}
		if (!zipCode.equalsIgnoreCase("")) {
			if (flag) {
				que = que + " and ";
			}
			que = que + " cust." + "ZipCode='" + zipCode + "'";
			flag = true;
		}
		if (!city.equalsIgnoreCase("")) {
			if (flag) {
				que = que + " and ";
			}
			que = que + " cust." + "CityName='" + city + "'";
			flag = true;
		}
		que = que + " ORDER BY cust.UtilityAccountNumber ASC";
		return que;
	}

	public static String getLinkAccountByCustomerNumber(String utilityAccountNumber) {
		String query = ";"
				+ "SELECT DISTINCT A.customerid,A.customerNo,A.FirstName,A.LastName,A.FullName,A.EmailID,A.MobilePhone,b.UtilityAccountNumber"
				+ "," + "case when b.customerid IS NOT NULL THEN 'Active' ELSE 'Inactive' END AS 'CustomerStatus'"
				+ "FROM customerinfo (NOLOCK) A "
				+ "left join customerinfo (NOLOCK) b on A.customerid=b.customerid and b.AccountStatusID IN (0,1,3)"
				+ "WHERE A.CustomerID NOT IN (1,-1) and A.UtilityAccountNumber='" + utilityAccountNumber + "'";
		return query;
	}

	public static String getCustomerCountByStatus(String status) {
		String query = ";"
				+ "SELECT DISTINCT A.customerid,A.customerNo,A.FirstName,A.LastName,A.FullName,A.EmailID,A.MobilePhone"
				+ "," + "case when b.customerid IS NOT NULL THEN 'Active' ELSE 'Inactive' END AS 'CustomerStatus'"
				+ "FROM customerinfo (NOLOCK) A "
				+ "left join customerinfo (NOLOCK) b on A.customerid=b.customerid and b.AccountStatusID IN (0,1,3)"
				+ "WHERE A.CustomerID NOT IN (1,-1) and A.AccountStatus='" + status + "'";
		return query;
	}

	public static String getCustomerDetailsByName(String customerName) {
		String query = ";"
				+ "SELECT DISTINCT A.customerid,A.customerNo,A.FirstName,A.LastName,A.FullName,A.EmailID,A.MobilePhone"
				+ "," + "case when b.customerid IS NOT NULL THEN 'Active' ELSE 'Inactive' END AS 'CustomerStatus'"
				+ "FROM customerinfo (NOLOCK) A "
				+ "left join customerinfo (NOLOCK) b on A.customerid=b.customerid and b.AccountStatusID IN (0,1,3)"
				+ "WHERE A.CustomerID NOT IN (1,-1) and A.FullName='" + customerName + "'";
		// System.out.println(query);
		return query;
	}

	/**
	 * This Method is to get the link account number of the service account number
	 *
	 * @param utilityNumber
	 * @param roleType
	 * @return
	 */
	public static String getLinkAccountCount(String utilityNumber, String roleType) {
		String query = null;
		if (roleType.equalsIgnoreCase("Property Managers")) {
			query = "SELECT * FROM [UserAccount] Full OUTER JOIN [User] on [UserAccount].UserID=[User].UserID where [UserAccount].utilityAccountNumber='"
					+ utilityNumber + "' and [UserAccount].RoleID='" + 2 + "'";
		} else if (roleType.equalsIgnoreCase("Guest Users")) {
			query = "SELECT * FROM [UserAccount] Full OUTER JOIN [User] on [UserAccount].UserID=[User].UserID where [UserAccount].utilityAccountNumber='"
					+ utilityNumber + "' and [UserAccount].RoleID='" + 1 + "'";
		} else if (roleType.equalsIgnoreCase("Total")) {
			query = "SELECT * FROM [UserAccount] Full OUTER JOIN [User] on [UserAccount].UserID=[User].UserID where [UserAccount].utilityAccountNumber='"
					+ utilityNumber + "'";
		}
		// System.out.println(query);
		return query;
	}

	/**
	 * This method is to get info of Commercial/ Residential 'Active', 'Not
	 * Registered', 'Registered' and 'Inactive' for Link Account Popup
	 *
	 * @param accountType   = Residential | Commercial
	 * @param accountStatus = 'Not Registered' | 'Registered' | 'Active' |
	 *                      'Inactive'
	 * @return a query having value for UtilityAccountNumber, ZipCode,
	 *         DrivingLicence, MeterNumber, Address1, AddressType
	 */
	public static String getInfoForLinkingAccountFromDB(String accountType, String accountStatus) {
		String query = null;
		/*
		 * query =
		 * "select top 1 CustomerInfo.UtilityAccountNumber, CustomerInfo.ZipCode, CustomerInfo.DrivingLicence, CustomerInfo.Address1, CustomerInfo.AddressType, AccountMeterMapping.MeterNumber  from CustomerInfo Inner JOIN  AccountMeterMapping on CustomerInfo.AccountNumber= AccountMeterMapping.AccountNumber WHERE AccountStatus = '"
		 * + accountStatus + "' AND CustomerInfo.AddressType = '" + accountType +
		 * "' AND CustomerInfo.UtilityAccountNumber not like 'R%' AND CustomerInfo.UtilityAccountNumber not like 'C%' And CustomerInfo.PortalAccessType = 'Standard' "
		 * ;
		 */
		// System.out.println(query);
		query = "SELECT Top 1 c.CustomerID, c.MobilePhone, c.DrivingLicence, C.customerNo, CA.UtilityAccountNumber,CA.ZipCode,CA.Address1,1234 AS SSNNumber,\r\n"
				+ "(CASE WHEN CA.AddressType=1 THEN 'Residential' ELSE 'Commercial' END) AS CustomerType,\r\n"
				+ "MAX(AMM.MeterNumber) AS MeterNumber  FROM Customer c(NOLOCK) JOIN CustomerAddress CA(NOLOCK) ON c.CustomerID=ca.CustomerID\r\n"
				+ "JOIN Account a(NOLOCK) ON ca.AddressID = a.AddressID JOIN AccountMeterMapping AMM(NOLOCK) ON A.AccountNumber=AMM.AccountNumber\r\n"
				+ "LEFT JOIN UserAccount IUA(NOLOCK) ON IUA.CustomerID=C.CustomerID\r\n"
				+ "INNER JOIN ( SELECT  CI.CustomerId\r\n" + "FROM CustomerInfo CI WITH (NOLOCK)\r\n"
				+ "group by CI.customerid having (count(UtilityAccountNumber)>1))R\r\n"
				+ "ON R.CustomerID=C.CustomerID\r\n" + "WHERE IUA.CustomerID IS NULL AND c.CustomerID NOT IN (1,-1)\r\n"
				+ "AND CA.AddressType= " + accountType + " AND a.Status=" + accountStatus
				+ " AND CA.PortalAccessType = 0 GROUP BY c.CustomerID, c.MobilePhone, C.customerNo,\r\n"
				+ "(CASE WHEN CA.AddressType = 1 THEN 'Residential' ELSE 'Commercial' END),\r\n"
				+ "c.DrivingLicence, CA.ZipCode, CA.Address1,C.SSNNumber,CA.UtilityAccountNumber\r\n" + "";
		return query;

	}

	public static String getInfoForLinkingAccountFromDB(String accountType, String accountStatus, String userId) {
		String query = null;
		query = "select Top 1 CustomerInfo.UtilityAccountNumber, CustomerInfo.ZipCode, CustomerInfo.DrivingLicence, CustomerInfo.Address1, CustomerInfo.AddressType, AccountMeterMapping.MeterNumber  from CustomerInfo Inner JOIN  AccountMeterMapping on CustomerInfo.AccountNumber= AccountMeterMapping.AccountNumber WHERE AccountStatus = '"
				+ accountStatus + "' And CustomerInfo.PortalAccessType = 'Standard' AND CustomerInfo.AddressType = '"
				+ accountType
				+ "' and CustomerInfo.accountnumber not in (SELECT AccountNumber FROM [UserAccount]  WHERE UserID=(Select UserID from [User]  where UserName ='"
				+ userId + "'))";
		// System.out.println(query);
		return query;
	}

	/**
	 * This method is to get info of Commercial/ Residential Registered-NotActivated
	 * and Activation Expired for Link Account Popup
	 *
	 * @param accountType = Residential | Commercial
	 * @return a query having value for UtilityAccountNumber, ZipCode,
	 *         DrivingLicence, MeterNumber, Address1, AddressType
	 */
	public static String getInfoForLinkAccountRegisteredExpiredActivationFromDB(String accountType) {
		String query = null;
		query = "select top 1 CI.UtilityAccountNumber, CI.ZipCode, CI.DrivingLicence, CI.Address1, CI.AddressType, AMM.MeterNumber\r\n"
				+ "from [User] U\r\n" + "Join useraccount UA on U.Userid=UA.Userid\r\n"
				+ "join CustomerInfo CI on UA.UtilityAccountNumber = CI.UtilityAccountNumber\r\n"
				+ "join AccountMeterMapping AMM on CI.AccountNumber= AMM.AccountNumber\r\n"
				+ "Join Account A on UA.AccountNumber=A.AccountNumber\r\n"
				+ "where A.status=0 and U.status=0 and CI.AddressType= '" + accountType + "'\r\n"
				+ "and convert(date,U.LinkSentDate)<=convert(date,getdate()-3)";
		// System.out.println(query);
		return query;
	}

	public static String getOptedEfficiencyByAccountNumber(String accountNumber, String categoty) {
		String q = "SELECT * FROM EEPromotion LEFT JOIN EEPromotionUsers ON EEPromotion.PromotionId = EEPromotionUsers.PromotionId where EEPromotionUsers.AccountNumber=(select AccountNumber from customerinfo where UtilityAccountNumber="
				+ "'" + accountNumber + "') and EEPromotion.CategoryId=" + "'" + categoty
				+ "' and EEPromotion.IsActive='1' and EEPromotion.IsDeleted='0' ORDER BY EEPromotion.CreatedDate DESC";
		// System.out.println(q);
		return q;
	}

	public static String getEfficiencyDescription(String efficiencyName, String categoty) {
		String q = "Select Description from EEPromotion where Title='" + efficiencyName + "'"
				+ "and IsActive='1' and CategoryId='" + categoty + "' and AccountType in ('1','2')"
				+ " and  IsDeleted='0'";
		// System.out.println(q);
		return q;
	}

	public static String getEfficiencyAdded(String efficiencyName, String categoty) {
		String q = "SELECT count(*) as Added FROM EEPromotionUsers where promotionId=(Select promotionId from EEPromotion where Title='"
				+ efficiencyName + "'and IsActive='1' and CategoryId='" + categoty
				+ "'and AccountType in ('1','2') and  IsDeleted='0')";
		return q;
	}

	public static String getEfficiencyViews(String efficiencyName, String categoty) {
		String q = "Select views from EEPromotion where Title='" + efficiencyName + "'and IsActive='1' and CategoryId='"
				+ categoty + "'and IsActive='1' and AccountType in ('1','2') and  IsDeleted='0'";
		return q;
	}

	public static String getEfficiencyLikes(String efficiencyName, String categoty) {
		String q = "SELECT count(*) as LikeCount FROM EEPromotionLike where promotionId=(Select promotionId from EEPromotion where Title='"
				+ efficiencyName + "' and IsActive='1' and CategoryId='" + categoty
				+ "' and AccountType in ('1','2')  and  IsDeleted='0')";
		return q;
	}

	public static String getAccountNotificationDetail(String accountNumber) {
		String q = "SELECT AccountNotificationType,EmailOrPhone FROM AccountNotificationType Join AccountNotificationDetail on AccountNotificationType.AccountNotificationTypeID=AccountNotificationDetail.AccountNotificationTypeID where"
				+ " AccountNumber=(Select top 1 AccountNumber from userAccount where utilityAccountNumber='"
				+ accountNumber
				+ "') and UserID=(SELECT [UserAccount].UserID FROM [UserAccount] Full OUTER JOIN [User] on [UserAccount].UserID=[User].UserID where [UserAccount].utilityAccountNumber='"
				+ accountNumber + "'and [UserAccount].RoleID=3)";
		return q;
	}

	public static String getMeterNumberForAccount(String accountNumber, String meterType) {
		String q = null;
		q = "SELECT MeterNumber  FROM AccountMeterMapping where AccountNumber=(select Accountnumber from Account Full outer join CustomerAddress on Account.AddressId=customerAddress.AddressId where CustomerAddress.utilityAccountnumber='"
				+ accountNumber + "') and MeterType='" + meterType + "' and Status='1'";
		return q;
	}

	public static String getMarketPreference(String userName) {
		String q = null;
		q = "SELECT MarketingPreferenceSetting.PreferenceName FROM UserMarketingPreferenceSetting join MarketingPreferenceSetting on  UserMarketingPreferenceSetting.PreferenceId=MarketingPreferenceSetting.PreferenceId    where UserMarketingPreferenceSetting.UserId=(select UserID from [User] where UserName='"
				+ userName + "')";
		return q;
	}

	public static String getCustomerPropertyDetails(String accountNumber) {
		String q = null;
		q = "select UtilityAccountNumber,AddressType,CityName,Zipcode from customerinfo where UtilityAccountNumber='"
				+ accountNumber + "'";
		return q;
	}

	public static String getInteractionCount(String userName) {
		String q = null;
		q = "SELECT count(*) as EventCount from SCPModuleEventLog where userId=(select UserID from [User] where UserName='"
				+ userName + "') order by 1 desc";
		return q;
	}

	public static String getGuestCount(String accountNumber) {
		String q = null;
		q = "SELECT count(*) as GuestCount FROM GuestAccessRequest where UtilityAccountNumber='" + accountNumber + "'";
		return q;
	}

	public static String getGuestDetails(String accountNumber) {
		String q = null;
		q = "SELECT GuestName,GuestEmailID FROM GuestAccessRequest where UtilityAccountNumber='" + accountNumber + "'";
		return q;
	}

	public static String getBillingDetails(String accountNumber) {
		String q = null;
		q = "SELECT Top 10 Billing.BillingDate,BillingDetail.value from BillingDetail join Billing on  BillingDetail.BillingID=Billing.BillingID where BillingDetail.HeadId='22' and Billing.AccountNumber=(select AccountNumber from account where utilityAccountNumber='"
				+ accountNumber + "')  order by BillingDate desc ";
		return q;
	}

	public static String getPaymentDetails(String accountNumber) {
		String q = null;
		q = "SELECT top 10 * FROM BillingTransaction where accountnumber=(select AccountNumber from account where utilityAccountNumber='"
				+ accountNumber + "') order by Transactiondate desc";
		return q;
	}

	// New query for CSRs
	public static String getServiceAccountNumberCount() {
		String sGetServiceAccountNumberCount = ";"
				+ "SELECT COUNT(DISTINCT AccountNumber) as TotalServiceAccountNumberCount\r\n"
				+ "FROM CustomerInfo WITH (NOLOCK)\r\n" + "WHERE CustomerID > 1";
		return sGetServiceAccountNumberCount;
	}

	public static String getServiceAccountNumberCountActive() {
		// String sGetServiceAccountNumberCountActive = ";"
		// + "select count(AccountNumber) as ActiveServiceAccountNumber from
		// Account WHERE AccountNumber NOT IN (-1,2) AND Status in (0,1,3);";
		// return sGetServiceAccountNumberCountActive;
		String sGetServiceAccountNumberCountActive = ";"
				+ "SELECT COUNT(AccountNumber) ActiveServiceAccountNumberCount FROM customerinfo   WHERE AccountStatusID IN (0,1,3) AND CustomerID NOT IN (1,-1)";
		return sGetServiceAccountNumberCountActive;
	}

	public static String getServiceAccountNumberCountOthers() {
		String sGetServiceAccountNumberCountOthers = ";"
				+ "SELECT count(distinct AccountNumber) as OtherServiceAccountNumberCount\r\n"
				+ "FROM CustomerInfo WITH (NOLOCK)\r\n" + "WHERE AccountStatusID IN (2,4,5)\r\n" + "AND CustomerID > 1";
		return sGetServiceAccountNumberCountOthers;
	}

	public static String getCustomerCount() {
		String sGetCustomerCount = ";"
				+ "SELECT COUNT(DISTINCT customerid) as TotalCustomerNumber FROM CustomerInfo(NOLOCK) WHERE CustomerID NOT IN (1,-1)";
		return sGetCustomerCount;
	}

	public static String getCustomerCountActive() {
		String sGetCustomerCountActive = ";"
				+ "SELECT COUNT(DISTINCT customerid) as TotalCustomerNumberActive FROM CustomerInfo(NOLOCK) WHERE CustomerID NOT IN (1,-1) AND AccountStatusID IN (0,1,3)";
		return sGetCustomerCountActive;
	}

	public static String getTotalUsersCount() {
		String sGetTotalUsersCount = ";" + "SELECT count(DISTINCT U.userid) as TotalUsers\r\n"
				+ "FROM [user] U WITH (NOLOCK)\r\n" + "JOIN (\r\n" + "SELECT accountNumber\r\n" + ",Userid\r\n"
				+ "FROM UserAccount WITH (NOLOCK)\r\n" + "\r\n" + "UNION ALL\r\n" + "\r\n"
				+ "SELECT PA.accountNumber\r\n" + ",PU.Userid\r\n" + "FROM PortfolioAccount PA WITH (NOLOCK)\r\n"
				+ "INNER JOIN PortfolioUser PU WITH (NOLOCK) ON PA.GroupID = PU.GroupID\r\n" + "AND PU.IsActive = 1\r\n"
				+ "\r\n" + "WHERE\r\n" + "PA.IsActive = 1\r\n" + ") UA ON U.userid = UA.userid\r\n"
				+ "WHERE U.userid NOT IN (\r\n" + "- 1\r\n" + ",1\r\n" + ")\r\n" + "AND U.[STATUS] NOT IN (3)\r\n"
				+ "AND U.UserType NOT IN (3)";
		return sGetTotalUsersCount;
	}

	public static String getTotalActiveUsersCount() {
		String sGetTotalActiveUsersCount = ";" + "SELECT count(DISTINCT U.userid) as TotalActiveUsers\r\n"
				+ "FROM [user] U WITH (NOLOCK)\r\n" + "JOIN (\r\n" + "SELECT accountNumber\r\n" + ",Userid\r\n"
				+ "FROM UserAccount WITH (NOLOCK)\r\n" + "\r\n" + "UNION ALL\r\n" + "\r\n"
				+ "SELECT PA.accountNumber\r\n" + ",PU.Userid\r\n" + "FROM PortfolioAccount PA WITH (NOLOCK)\r\n"
				+ "INNER JOIN PortfolioUser PU WITH (NOLOCK) ON PA.GroupID = PU.GroupID\r\n" + "AND PU.IsActive = 1\r\n"
				+ "\r\n" + "WHERE PA.IsActive = 1\r\n" + "\r\n" + ") UA ON U.userid = UA.userid\r\n"
				+ "WHERE U.STATUS = 1\r\n" + "AND isnull(U.islocked, 0) = 0\r\n" + "AND U.userid NOT IN (\r\n"
				+ "- 1\r\n" + ",1\r\n" + ")\r\n" + "AND U.UserType NOT IN (\r\n" + "3\r\n" + ",5\r\n" + ")";
		return sGetTotalActiveUsersCount;
	}

	public static String getTotalOtherUsersCount() {
		String sGetTotalOtherUsersCount = ";" + "SELECT count(DISTINCT U.userid) as TotalOtherUsers\r\n"
				+ "FROM [user] U WITH (NOLOCK)\r\n" + "JOIN (\r\n" + "SELECT accountNumber\r\n" + ",Userid\r\n"
				+ "FROM UserAccount WITH (NOLOCK)\r\n" + "\r\n" + "UNION ALL\r\n" + "\r\n"
				+ "SELECT PA.accountNumber\r\n" + ",PU.Userid\r\n" + "FROM PortfolioAccount PA WITH (NOLOCK)\r\n"
				+ "INNER JOIN PortfolioUser PU WITH (NOLOCK) ON PA.GroupID = PU.GroupID\r\n" + "AND PU.IsActive = 1\r\n"
				+ "\r\n" + "WHERE PA.IsActive = 1\r\n" + "\r\n" + ") UA ON U.userid = UA.userid\r\n"
				+ "WHERE U.STATUS = 1\r\n" + "AND isnull(U.islocked, 0) = 0\r\n" + "AND U.userid NOT IN (\r\n"
				+ "- 1\r\n" + ",1\r\n" + ")\r\n" + "AND U.UserType IN (\r\n" + "3\r\n" + ",5\r\n" + ")";
		return sGetTotalOtherUsersCount;
	}

	/**
	 * This Method is to get the Utility Number by the meter type and customer type
	 *
	 * @param meterType    E,W,G
	 * @param customerType Enterprise,Mass-market
	 * @return
	 */
	public static String getUtilityNumberByMeterAndCustomerType(String meterType, String customerType) {
		String query = "Declare @MeterType Varchar(500)='" + meterType + "',@CustomerType Varchar(200)='" + customerType
				+ "'" + " If(@CustomerType='Enterprise')" + " BEGIN"
				+ " select  CI.AccountNumber,CI.UtilityAccountnUmber from AccountMeterMapping AMM"
				+ " Inner Join CustomerInfo CI On AMM.AccountNumber=CI.AccountNumber"
				+ " where CI.AddressTypeID=2 And CI.PortalAccessTypeID=1 and CI.AccountStatusID=0"
				+ " And AMM.MeterType in (Select Item from dbo.SplitString(@MeterType,','))" + " END" + " Else"
				+ " BEGIN" + " select  CI.AccountNumber,CI.UtilityAccountnUmber from AccountMeterMapping AMM"
				+ " Inner Join CustomerInfo CI On AMM.AccountNumber=CI.AccountNumber"
				+ " where CI.AddressTypeID=1 And CI.PortalAccessTypeID=0 and CI.AccountStatusID=0"
				+ " And AMM.MeterType in (Select Item from dbo.SplitString(@MeterType,','))" + " END";
		return query;
	}

	// sql query for connect Me Template
	public static String getConnectMetemplate() {
		String sConnectMeTemplate = "SELECT top 1 * FROM TemplateType where status=1 and moduleid=2";
		return sConnectMeTemplate;
	}

	// sql query for Service Template
	public static String getServicetemplate() {
		String sServiceTemplate = ";" + "SELECT top 1 * FROM TemplateType where status=1 and moduleid=1";
		return sServiceTemplate;
	}

	public static String getConnectMeTopics() {
		String sConnectMeTopic = ";" + "SELECT  * FROM ConnectTopicMaster where IsActive=1";
		return sConnectMeTopic;
	}

	public static String getConnectMeAllTopicDetail(String topicName) {
		String sConnectMeTopicDetail = ";" + "SELECT  * FROM ConnectTopicMaster where TopicName ='" + topicName + "';";
		return sConnectMeTopicDetail;
	}

	public static String getConnectMeActiveTopicDetails(String topicName) {
		String sConnectMeTopicDetails = "SELECT  * FROM ConnectTopicMaster where IsActive=1 and TopicName='" + topicName
				+ "';";
		return sConnectMeTopicDetails;
	}

	public static String updateConnectMeDefaultTopic(String topicName) {
		String setDefaultTopicConnectMe = "update ConnectTopicMaster set isDefault='1' where TopicName='" + topicName
				+ "';";
		return setDefaultTopicConnectMe;
	}

	public static String deleteConnectMeTopic(String topicName) {
		String deleteConnectMeTopic = "delete from ConnectTopicMaster where TopicName='" + topicName + "';";
		return deleteConnectMeTopic;
	}

	public static String getTotalCountAdminConnectMeTopics() {
		String totalCount = "select count(1) as TotalCount from(\r\n" + "SELECT DISTINCT\r\n" + "CTM.TopicID\r\n"
				+ ",CTM.TopicName\r\n" + ",CTM.IsActive\r\n" + ",CTM.ImageUrl\r\n" + ",CTM.ServiceType\r\n"
				+ ",CTM.PlaceHolderId\r\n" + ",CTM.IsReadOnly\r\n" + ",CTM.TemplateTypeId\r\n"
				+ ",TT.TemplateTypeName\r\n" + ",MLControlId\r\n"
				+ ",DBO.GETMULTILINGUALMESSAGE(MLControlId, 'EN', 'N', DEFAULT) AS [English]\r\n"
				+ ",DBO.GETMULTILINGUALMESSAGE(MLControlId, 'ES', 'N', DEFAULT) AS [Spanish]\r\n"
				+ ",DBO.GETMULTILINGUALMESSAGE(MLControlId, 'FR', 'N', DEFAULT) AS [French]\r\n" + ",CTM.IsDefault\r\n"
				+ "FROM ConnectTopicMaster CTM WITH(NOLOCK)\r\n"
				+ "LEFT JOIN TemplateType TT WITH(NOLOCK) ON CTM.TemplateTypeId = TT.TemplateTypeId\r\n"
				+ "JOIN MultiLingualMaster MM WITH(NOLOCK) ON MM.ControlId = CTM.MLControlId\r\n"
				+ "WHERE (TT.moduleid = 2 OR ISNULL(TT.moduleid,'') = '')\r\n" + ")temp";
		return totalCount;
	}

	public static String getServiceTopics() {
		String sServiveTopic = ";" + "SELECT * FROM SRReasonMaster where IsActive=1";
		return sServiveTopic;
	}
	public static String deleteServiceType(String ServiceName) {
		String deleteService = "delete from SRReasonMaster where ReasonName='" + ServiceName + "';";
		return deleteService;
	}

	public static String getBanners() {
		String sBanners = ";" + "select * from BannerMaster where IsActive=1";
		return sBanners;
	}

	public static String getCountOfBannersMassMarket() {
		String countOfBannerMassMarket = "declare\r\n" + "@ExperienceId int = 1\r\n" + ",@ModuleId int = Null\r\n"
				+ ",@SubModuleId int = Null\r\n" + "select count(1) as TotalCount from\r\n" + "(\r\n" + "select\r\n"
				+ "u.Banner_Id,\r\n" + "u.Banner_Name,\r\n" + "u.Keywords,\r\n" + "--u.Banner_Type,\r\n"
				+ "u.Banner_Link,\r\n" + "u.Updated_By_User,\r\n" + "u.Language_Code,u.Module_Id,\r\n"
				+ "m.Module_Name,\r\n" + "u.[Status],\r\n" + "isnull(u.Sub_Module_Id,'')Sub_Module_Id,\r\n"
				+ "isnull(sm.Sub_Module_Name,'') Sub_Module_Name,\r\n" + "u.Nav_Mode,\r\n" + "u.Utility_Id,\r\n"
				+ "u.Created_By,\r\n" + "u.Experience_Id,\r\n" + "isnull(u.video_url,'') as video_urlEN\r\n"
				+ "from Banner u\r\n" + "join Module m on u.Module_Id = m.Module_Id\r\n"
				+ "left join Sub_Module sm on u.Sub_Module_Id = sm.Sub_Module_Id\r\n"
				+ "where u.Experience_ID=@ExperienceID AND Language_Code='EN' and m.[status]=1\r\n" + ")temp";
		return countOfBannerMassMarket;
	}

	public static String getBannerDetailsMassMarket(String bannerName, String bannerLink) {
		String bannerDetailsMassMarket = "declare\r\n" + "@ExperienceId int = 1\r\n" + ",@ModuleId int = Null\r\n"
				+ ",@SubModuleId int = Null\r\n" + ",@banner_name varchar(200)='" + bannerName + "'\r\n"
				+ ",@banner_Link varchar(200)='" + bannerLink + "'\r\n" + "select\r\n" + "u.Banner_Id,\r\n"
				+ "u.Banner_Name,\r\n" + "u.Keywords,\r\n" + "--u.Banner_Type,\r\n" + "u.Banner_Link,\r\n"
				+ "u.banner_type,\r\n" + "u.attachment1_name,\r\n" + "u.Updated_By_User,\r\n"
				+ "u.Language_Code,u.Module_Id,\r\n" + "m.Module_Name,\r\n" + "u.[Status],\r\n"
				+ "isnull(u.Sub_Module_Id,'')Sub_Module_Id,\r\n" + "isnull(sm.Sub_Module_Name,'') Sub_Module_Name,\r\n"
				+ "u.Nav_Mode,\r\n" + "u.Utility_Id,\r\n" + "u.Created_By,\r\n" + "u.Experience_Id,\r\n"
				+ "isnull(u.video_url,'') as video_urlEN\r\n" + "from Banner u\r\n"
				+ "join Module m on u.Module_Id = m.Module_Id\r\n"
				+ "left join Sub_Module sm on u.Sub_Module_Id = sm.Sub_Module_Id\r\n"
				+ "where u.Experience_ID=@ExperienceID AND Language_Code='EN' and m.[status]=1\r\n"
				+ "and banner_name =@banner_name\r\n" + "and banner_link =@banner_Link\r\n" + "\r\n" + "\r\n" + "\r\n"
				+ "";
		return bannerDetailsMassMarket;
	}

	public static String getRegistrationTemplates() {
		String stempReg = ";" + "select * from TemplateMaster";
		return stempReg;
	}

	public static String getRegistrationTemplatesActive() {
		String stempRegActive = ";" + "select * from TemplateMaster where isActive=1";
		return stempRegActive;
	}

	public static String getBannersDetails(String att) {
		String stempRegActive = ";" + "select * from BannerMaster where IsActive=1 and BannerName='" + att + "'";
		return stempRegActive;
	}

	public static String getAllEmailTemplates() {
		String sEmailTemplate = ";" + "select * from EMailTemplate";
		return sEmailTemplate;
	}

	public static String getEmailTemplateDetails(String tempName) {
		String stempEmail = ";" + "select * from EMailTemplate where TemplateName='" + tempName + "'";
		return stempEmail;
	}

	public static String getFieldValueUniversalSearch(String customerName, String zipCode, String accountNumber,
			String emailId, String contactNo, String address) {
		String q = "Declare @ZipCode  VARCHAR(100) = '" + zipCode + "'" + ", @CustName  VARCHAR(122)= '" + customerName
				+ "'" + ", @AccountNumber VARCHAR(100) = '" + accountNumber + "'" + ", @TimeOffSet INT   = -360 "
				+ ", @SortColumn NVARCHAR(50) = 'FullName' " + ", @SortOrder  NVARCHAR(4) = 'ASC' "
				+ ", @PageIndex  INT   = 1 " + ", @PageSize  INT   = 10 " + ", @MobilePhone VARCHAR(100) = '"
				+ contactNo + "' " + ", @EmailID  VARCHAR(100)= '" + emailId + "'" + ", @address1      VARCHAR(255)= '"
				+ address + "'" + ", @SearchString VARCHAR(100)= NULL " + ", @FilterMode TINYINT  = 2 "
				+ "  DECLARE @StatusCount  TABLE([Status] VARCHAR(20),Cnt BIGINT DEFAULT 0) "
				+ " DECLARE @CustomerTypeCount TABLE([CustomerType] VARCHAR(20),Cnt BIGINT DEFAULT 0) "
				+ " DECLARE  @ExpirationDays INT=1,@TotalRecords int=0,@RecCount BIGINT=0 "
				+ " SELECT @ExpirationDays = REPLACE(CM.Name,' day(s)','') FROM TemplateDetail T WITH(NOLOCK) "
				+ " JOIN CommonMaster CM WITH(NOLOCK) ON(CM.MasterCode = T.Value AND CM.MasterType = 'TempValidity') "
				+ " WHERE TempDetailID = 51 "
				+ "  SELECT CI.AccountNumber,CI.CustomerId,CI.FullName AS [CustomerName],CI.EmailID as EmailID,CI.ZipCode, "
				+ " CI.MobilePhone as  MobilePhone,Address1,CI.CityName,CI.AddressType  AS [CustomerType],CI.[AccountStatus] AS ActualCIStatus, "
				+ "  Case when CI.[AccountStatus]='Registered' OR CI.[AccountStatus]='Active' Then 'Registered' Else 'Not Registered' End AS [Status2], "
				+ " Case when IsNull(U.UserId,'')='' then 'Not Registered' else 'Registered' End AS [Status],IsNull(U.IsLocked,0) as IsLocked,  "
				+ " Case when CI.[AccountStatus]='Inactive' Then CI.[AccountStatus] when CI.[AccountStatus]='Closed' Then 'Inactive'"
				+ " Else 'Active' End as [AccountStatus], "
				+ "  CI.Latitude,CI.Longitude,CI.UtilityAccountNumber,TY.TemplateTypeId as TemplateTypeId_HomeBusiness,DM.ModuleID as ModuleId_HomeBusiness, "
				+ " U.UserId,CI.FirstName,CI.LastName,CI.AccountStatusId AS AccountStatusCode,ISNULL(u.[Status],3) AS CustomerStatusCode, "
				+ " (CASE WHEN CI.[AccountStatusID]=0 AND DATEDIFF(DAY,ISNULL(U.ReminderDate,U.CreatedDate),GETDATE())<=@ExpirationDays THEN 0 "
				+ "  WHEN CI.[AccountStatusId]=3 OR (CI.[AccountStatusId]=0  "
				+ "  AND DATEDIFF(DAY,ISNULL(U.ReminderDate,U.CreatedDate),GETDATE())>@ExpirationDays) THEN 3 ELSE CI.[AccountStatusID] END) "
				+ "  AS [AccountStatusID], "
				+ "  REPLACE(STUFF(('O'+IsNull((Select Top 1 ', P, ' From UserAccount Where AccountNumber=CI.AccountNumber and IsNull(RoleID,0)=2),'') + "
				+ "  IsNull((Select Top 1 ', G' From UserAccount Where AccountNumber=CI.AccountNumber and IsNull(RoleID,0)=1),'')),1,0,''),', ,',',') as Roles "
				+ " FROM CustomerInfo CI WITH(NOLOCK) "
				+ "   LEFT JOIN UserAccount ua  WITH(NOLOCK) ON ua.AccountNumber  = CI.AccountNumber AND ua.RoleID = 3  and Iscustomerowner=1"
				+ "  LEFT JOIN [User]  U  (Nolock)  ON u.UserId=ua.UserId "
				+ "  JOIN CommonMaster  CMNCS WITH(NOLOCK) ON CMNCS.MasterCode  = ISNULL(U.[Status],3)   AND CMNCS.MasterType= 'CustomerStatus' "
				+ "  JOIN UtilityConfig UC(NOLOCK) on UC.ModuleName=(case CI.AddressTypeId when  1 Then 'AboutMyHome' when 2 then 'AboutMyBusiness' end)"
				+ "	 AND UC.[ConfigOption]='TemplateTypeID' "
				+ "   JOIN TemplateType TY(NOLOCK) ON UC.ConfigValue=TY.TemplateTypeId "
				+ "   JOIN DynamicModules DM on DM.ModuleID=TY.ModuleID " + "  WHERE CI.ID IN(" + "  SELECT CI.ID "
				+ "  FROM CustomerInfo CI WITH(NOLOCK)  " + "  WHERE CI.CustomerId > 1 "
				+ "  AND IsNull(CI.UtilityAccountNumber,'')LIKE '%'+ IsNull(Cast(@AccountNumber as Varchar(32)),IsNull(CI.UtilityAccountNumber,'')) +'%'"
				+ "  AND IsNull(CI.FullName,'') LIKE '%'+ IsNull(@CustName,IsNull(CI.FullName,'')) +'%' "
				+ "  AND IsNull(CI.ZipCode,'')=IsNull(Cast(@ZipCode as Varchar(32)),IsNull(CI.ZipCode,'')) "
				+ "  AND IsNull(CI.MobilePhone,'') LIKE '%'+ IsNull(@MobilePhone,IsNull(CI.MobilePhone,'')) +'%' "
				+ "  AND ISNULL(CI.EmailID,'') LIKE '%'+ IsNull(@EmailID,ISNULL(CI.EmailID,'')) +'%' "
				+ "  AND (IsNull(CI.[address1],'') LIKE '%'+ IsNull(@address1,IsNull(CI.[address1],''))+'%'"
				+ "  OR IsNull(CI.[address2],'') LIKE '%'+ IsNull(@address1,IsNull(CI.[address2],''))+'%') "
				+ "  AND (IsNull(CI.FirstName,'') LIKE '%'+IsNull(@SearchString,IsNull(CI.FirstName,''))+'%'  "
				+ "  OR Isnull(CI.LastName,'') LIKE '%'+IsNull(@SearchString,Isnull(CI.LastName,''))+'%' "
				+ "  OR IsNull(CI.UtilityAccountNumber,'') LIKE '%'+IsNull(@SearchString,IsNull(CI.UtilityAccountNumber,''))+'%' "
				+ "  OR IsNull(CI.EmailID,'') LIKE '%'+IsNull(@SearchString,IsNull(CI.EmailID,''))+'%' "
				+ "  OR IsNull(CI.MobilePhone,'') LIKE '%'+IsNull(@SearchString,IsNull(CI.MobilePhone,''))+'%'  "
				+ "  OR IsNull(CI.FullName,'') LIKE '%'+IsNull(@SearchString,IsNull(CI.FullName,''))+'%'  "
				+ "  OR IsNull(CI.[address1],'') LIKE '%'+IsNull(@SearchString,IsNull(CI.[address1],''))+'%' "
				+ "  OR IsNull(CI.[address2],'') LIKE '%'+IsNull(@SearchString,IsNull(CI.[address2],''))+'%' "
				+ "  OR IsNull(CI.FullName,'') LIKE '%'+ IsNull(@SearchString,IsNull(CI.FullName,'')) +'%' "
				+ "  OR IsNull(Cast(CI.ZipCode as Varchar(32)),'') LIKE '%'+ IsNull(@SearchString,IsNull(Cast(CI.ZipCode as Varchar(32)),'')) +'%' "
				+ "     )" + " ) " + " ORDER BY [CustomerName] " + " print 'end'";
		return q;
	}

	public static String getCountFieldValueUniversalSearch(String customerName, String zipCode, String accountNumber,
			String emailId, String contactNo, String address) {
		String q = "Declare @ZipCode  VARCHAR(100) = '" + zipCode + "'" + ", @CustName  VARCHAR(122)= '" + customerName
				+ "'" + ", @AccountNumber VARCHAR(100) = '" + accountNumber + "'" + ", @TimeOffSet INT   = -360 "
				+ ", @SortColumn NVARCHAR(50) = 'FullName' " + ", @SortOrder  NVARCHAR(4) = 'ASC' "
				+ ", @PageIndex  INT   = 1 " + ", @PageSize  INT   = 10 " + ", @MobilePhone VARCHAR(100) = '"
				+ contactNo + "' " + ", @EmailID  VARCHAR(100)= '" + emailId + "'" + ", @address1      VARCHAR(255)= '"
				+ address + "'" + ", @SearchString VARCHAR(100)= NULL " + ", @FilterMode TINYINT  = 2 "
				+ "  DECLARE @StatusCount  TABLE([Status] VARCHAR(20),Cnt BIGINT DEFAULT 0) "
				+ " DECLARE @CustomerTypeCount TABLE([CustomerType] VARCHAR(20),Cnt BIGINT DEFAULT 0) "
				+ " DECLARE  @ExpirationDays INT=1,@TotalRecords int=0,@RecCount BIGINT=0 "
				+ " SELECT @ExpirationDays = REPLACE(CM.Name,' day(s)','') FROM TemplateDetail T WITH(NOLOCK) "
				+ " JOIN CommonMaster CM WITH(NOLOCK) ON(CM.MasterCode = T.Value AND CM.MasterType = 'TempValidity') "
				+ " WHERE TempDetailID = 51 " + "  SELECT COUNT(CI.CustomerId) AS TotalRecords "
				+ " FROM CustomerInfo CI WITH(NOLOCK) "
				+ "   LEFT JOIN UserAccount ua  WITH(NOLOCK) ON ua.AccountNumber  = CI.AccountNumber AND ua.RoleID = 3  and Iscustomerowner=1"
				+ "  LEFT JOIN [User]  U  (Nolock)  ON u.UserId=ua.UserId "
				+ "  JOIN CommonMaster  CMNCS WITH(NOLOCK) ON CMNCS.MasterCode  = ISNULL(U.[Status],3)   AND CMNCS.MasterType= 'CustomerStatus' "
				+ "  JOIN UtilityConfig UC(NOLOCK) on UC.ModuleName=(case CI.AddressTypeId when  1 Then 'AboutMyHome' when 2 then 'AboutMyBusiness' end)"
				+ "	 AND UC.[ConfigOption]='TemplateTypeID' "
				+ "   JOIN TemplateType TY(NOLOCK) ON UC.ConfigValue=TY.TemplateTypeId "
				+ "   JOIN DynamicModules DM on DM.ModuleID=TY.ModuleID " + "  WHERE CI.ID IN(" + "  SELECT CI.ID "
				+ "  FROM CustomerInfo CI WITH(NOLOCK)  " + "  WHERE CI.CustomerId > 1 "
				+ "  AND IsNull(CI.UtilityAccountNumber,'')LIKE '%'+ IsNull(Cast(@AccountNumber as Varchar(32)),IsNull(CI.UtilityAccountNumber,'')) +'%'"
				+ "  AND IsNull(CI.FullName,'') LIKE '%'+ IsNull(@CustName,IsNull(CI.FullName,'')) +'%' "
				+ "  AND IsNull(CI.ZipCode,'')=IsNull(Cast(@ZipCode as Varchar(32)),IsNull(CI.ZipCode,'')) "
				+ "  AND IsNull(CI.MobilePhone,'') LIKE '%'+ IsNull(@MobilePhone,IsNull(CI.MobilePhone,'')) +'%' "
				+ "  AND ISNULL(CI.EmailID,'') LIKE '%'+ IsNull(@EmailID,ISNULL(CI.EmailID,'')) +'%' "
				+ "  AND (IsNull(CI.[address1],'') LIKE '%'+ IsNull(@address1,IsNull(CI.[address1],''))+'%'"
				+ "  OR IsNull(CI.[address2],'') LIKE '%'+ IsNull(@address1,IsNull(CI.[address2],''))+'%') "
				+ "  AND (IsNull(CI.FirstName,'') LIKE '%'+IsNull(@SearchString,IsNull(CI.FirstName,''))+'%'  "
				+ "  OR Isnull(CI.LastName,'') LIKE '%'+IsNull(@SearchString,Isnull(CI.LastName,''))+'%' "
				+ "  OR IsNull(CI.UtilityAccountNumber,'') LIKE '%'+IsNull(@SearchString,IsNull(CI.UtilityAccountNumber,''))+'%' "
				+ "  OR IsNull(CI.EmailID,'') LIKE '%'+IsNull(@SearchString,IsNull(CI.EmailID,''))+'%' "
				+ "  OR IsNull(CI.MobilePhone,'') LIKE '%'+IsNull(@SearchString,IsNull(CI.MobilePhone,''))+'%'  "
				+ "  OR IsNull(CI.FullName,'') LIKE '%'+IsNull(@SearchString,IsNull(CI.FullName,''))+'%'  "
				+ "  OR IsNull(CI.[address1],'') LIKE '%'+IsNull(@SearchString,IsNull(CI.[address1],''))+'%' "
				+ "  OR IsNull(CI.[address2],'') LIKE '%'+IsNull(@SearchString,IsNull(CI.[address2],''))+'%' "
				+ "  OR IsNull(CI.FullName,'') LIKE '%'+ IsNull(@SearchString,IsNull(CI.FullName,'')) +'%' "
				+ "  OR IsNull(Cast(CI.ZipCode as Varchar(32)),'') LIKE '%'+ IsNull(@SearchString,IsNull(Cast(CI.ZipCode as Varchar(32)),'')) +'%' "
				+ "     )" + " ) ";
		return q;
	}

	public static String getCountEmptyFieldSearchUniversalSearch() {
		String q = "Declare @ZipCode  VARCHAR(100) = null" + ", @CustName  VARCHAR(122)= null"
				+ ", @AccountNumber VARCHAR(100) = null" + ", @TimeOffSet INT   = -360 "
				+ ", @SortColumn NVARCHAR(50) = 'FullName' " + ", @SortOrder  NVARCHAR(4) = 'ASC' "
				+ ", @PageIndex  INT   = 1 " + ", @PageSize  INT   = 10 " + ", @MobilePhone VARCHAR(100) = null"
				+ ", @EmailID  VARCHAR(100)= null" + ", @address1      VARCHAR(255)= null"
				+ ", @SearchString VARCHAR(100)= NULL " + ", @FilterMode TINYINT  = 2 "
				+ "  DECLARE @StatusCount  TABLE([Status] VARCHAR(20),Cnt BIGINT DEFAULT 0) "
				+ " DECLARE @CustomerTypeCount TABLE([CustomerType] VARCHAR(20),Cnt BIGINT DEFAULT 0) "
				+ " DECLARE  @ExpirationDays INT=1,@TotalRecords int=0,@RecCount BIGINT=0 "
				+ " SELECT @ExpirationDays = REPLACE(CM.Name,' day(s)','') FROM TemplateDetail T WITH(NOLOCK) "
				+ " JOIN CommonMaster CM WITH(NOLOCK) ON(CM.MasterCode = T.Value AND CM.MasterType = 'TempValidity') "
				+ " WHERE TempDetailID = 51 " + "  SELECT COUNT(CI.CustomerId) AS TotalRecords "
				+ " FROM CustomerInfo CI WITH(NOLOCK) "
				+ "   LEFT JOIN UserAccount ua  WITH(NOLOCK) ON ua.AccountNumber  = CI.AccountNumber AND ua.RoleID = 3  and Iscustomerowner=1"
				+ "  LEFT JOIN [User]  U  (Nolock)  ON u.UserId=ua.UserId "
				+ "  JOIN CommonMaster  CMNCS WITH(NOLOCK) ON CMNCS.MasterCode  = ISNULL(U.[Status],3)   AND CMNCS.MasterType= 'CustomerStatus' "
				+ "  JOIN UtilityConfig UC(NOLOCK) on UC.ModuleName=(case CI.AddressTypeId when  1 Then 'AboutMyHome' when 2 then 'AboutMyBusiness' end)"
				+ "	 AND UC.[ConfigOption]='TemplateTypeID' "
				+ "   JOIN TemplateType TY(NOLOCK) ON UC.ConfigValue=TY.TemplateTypeId "
				+ "   JOIN DynamicModules DM on DM.ModuleID=TY.ModuleID " + "  WHERE CI.ID IN(" + "  SELECT CI.ID "
				+ "  FROM CustomerInfo CI WITH(NOLOCK)  " + "  WHERE CI.CustomerId > 1 "
				+ "  AND IsNull(CI.UtilityAccountNumber,'')LIKE '%'+ IsNull(Cast(@AccountNumber as Varchar(32)),IsNull(CI.UtilityAccountNumber,'')) +'%'"
				+ "  AND IsNull(CI.FullName,'') LIKE '%'+ IsNull(@CustName,IsNull(CI.FullName,'')) +'%' "
				+ "  AND IsNull(CI.ZipCode,'')=IsNull(Cast(@ZipCode as Varchar(32)),IsNull(CI.ZipCode,'')) "
				+ "  AND IsNull(CI.MobilePhone,'') LIKE '%'+ IsNull(@MobilePhone,IsNull(CI.MobilePhone,'')) +'%' "
				+ "  AND ISNULL(CI.EmailID,'') LIKE '%'+ IsNull(@EmailID,ISNULL(CI.EmailID,'')) +'%' "
				+ "  AND (IsNull(CI.[address1],'') LIKE '%'+ IsNull(@address1,IsNull(CI.[address1],''))+'%'"
				+ "  OR IsNull(CI.[address2],'') LIKE '%'+ IsNull(@address1,IsNull(CI.[address2],''))+'%') "
				+ "  AND (IsNull(CI.FirstName,'') LIKE '%'+IsNull(@SearchString,IsNull(CI.FirstName,''))+'%'  "
				+ "  OR Isnull(CI.LastName,'') LIKE '%'+IsNull(@SearchString,Isnull(CI.LastName,''))+'%' "
				+ "  OR IsNull(CI.UtilityAccountNumber,'') LIKE '%'+IsNull(@SearchString,IsNull(CI.UtilityAccountNumber,''))+'%' "
				+ "  OR IsNull(CI.EmailID,'') LIKE '%'+IsNull(@SearchString,IsNull(CI.EmailID,''))+'%' "
				+ "  OR IsNull(CI.MobilePhone,'') LIKE '%'+IsNull(@SearchString,IsNull(CI.MobilePhone,''))+'%'  "
				+ "  OR IsNull(CI.FullName,'') LIKE '%'+IsNull(@SearchString,IsNull(CI.FullName,''))+'%'  "
				+ "  OR IsNull(CI.[address1],'') LIKE '%'+IsNull(@SearchString,IsNull(CI.[address1],''))+'%' "
				+ "  OR IsNull(CI.[address2],'') LIKE '%'+IsNull(@SearchString,IsNull(CI.[address2],''))+'%' "
				+ "  OR IsNull(CI.FullName,'') LIKE '%'+ IsNull(@SearchString,IsNull(CI.FullName,'')) +'%' "
				+ "  OR IsNull(Cast(CI.ZipCode as Varchar(32)),'') LIKE '%'+ IsNull(@SearchString,IsNull(Cast(CI.ZipCode as Varchar(32)),'')) +'%' "
				+ "     )" + " ) ";
		return q;
	}

	public static String getEmptyFieldSearchUniversalSearch() {
		String q = "Declare @ZipCode  VARCHAR(100) = null" + ", @CustName  VARCHAR(122)= null"
				+ ", @AccountNumber VARCHAR(100) = null" + ", @TimeOffSet INT   = -360 "
				+ ", @SortColumn NVARCHAR(50) = 'FullName' " + ", @SortOrder  NVARCHAR(4) = 'ASC' "
				+ ", @PageIndex  INT   = 1 " + ", @PageSize  INT   = 10 " + ", @MobilePhone VARCHAR(100) = null"
				+ ", @EmailID  VARCHAR(100)= null" + ", @address1      VARCHAR(255)= null"
				+ ", @SearchString VARCHAR(100)= NULL " + ", @FilterMode TINYINT  = 2 "
				+ "  DECLARE @StatusCount  TABLE([Status] VARCHAR(20),Cnt BIGINT DEFAULT 0) "
				+ " DECLARE @CustomerTypeCount TABLE([CustomerType] VARCHAR(20),Cnt BIGINT DEFAULT 0) "
				+ " DECLARE  @ExpirationDays INT=1,@TotalRecords int=0,@RecCount BIGINT=0 "
				+ " SELECT @ExpirationDays = REPLACE(CM.Name,' day(s)','') FROM TemplateDetail T WITH(NOLOCK) "
				+ " JOIN CommonMaster CM WITH(NOLOCK) ON(CM.MasterCode = T.Value AND CM.MasterType = 'TempValidity') "
				+ " WHERE TempDetailID = 51 "
				+ "  SELECT CI.AccountNumber,CI.CustomerId,CI.FullName AS [CustomerName],CI.EmailID as EmailID,CI.ZipCode, "
				+ " CI.MobilePhone as  MobilePhone,Address1,CI.CityName,CI.AddressType  AS [CustomerType],CI.[AccountStatus] AS ActualCIStatus, "
				+ "  Case when CI.[AccountStatus]='Registered' OR CI.[AccountStatus]='Active' Then 'Registered' Else 'Not Registered' End AS [Status2], "
				+ " Case when IsNull(U.UserId,'')='' then 'Not Registered' else 'Registered' End AS [Status],IsNull(U.IsLocked,0) as IsLocked,  "
				+ " Case when CI.[AccountStatus]='Inactive' Then CI.[AccountStatus] when CI.[AccountStatus]='Closed' Then 'Inactive'"
				+ " Else 'Active' End as [AccountStatus], "
				+ "  CI.Latitude,CI.Longitude,CI.UtilityAccountNumber,TY.TemplateTypeId as TemplateTypeId_HomeBusiness,DM.ModuleID as ModuleId_HomeBusiness, "
				+ " U.UserId,CI.FirstName,CI.LastName,CI.AccountStatusId AS AccountStatusCode,ISNULL(u.[Status],3) AS CustomerStatusCode, "
				+ " (CASE WHEN CI.[AccountStatusID]=0 AND DATEDIFF(DAY,ISNULL(U.ReminderDate,U.CreatedDate),GETDATE())<=@ExpirationDays THEN 0 "
				+ "  WHEN CI.[AccountStatusId]=3 OR (CI.[AccountStatusId]=0  "
				+ "  AND DATEDIFF(DAY,ISNULL(U.ReminderDate,U.CreatedDate),GETDATE())>@ExpirationDays) THEN 3 ELSE CI.[AccountStatusID] END) "
				+ "  AS [AccountStatusID], "
				+ "  REPLACE(STUFF(('O'+IsNull((Select Top 1 ', P, ' From UserAccount Where AccountNumber=CI.AccountNumber and IsNull(RoleID,0)=2),'') + "
				+ "  IsNull((Select Top 1 ', G' From UserAccount Where AccountNumber=CI.AccountNumber and IsNull(RoleID,0)=1),'')),1,0,''),', ,',',') as Roles "
				+ " FROM CustomerInfo CI WITH(NOLOCK) "
				+ "   LEFT JOIN UserAccount ua  WITH(NOLOCK) ON ua.AccountNumber  = CI.AccountNumber AND ua.RoleID = 3  and Iscustomerowner=1"
				+ "  LEFT JOIN [User]  U  (Nolock)  ON u.UserId=ua.UserId "
				+ "  JOIN CommonMaster  CMNCS WITH(NOLOCK) ON CMNCS.MasterCode  = ISNULL(U.[Status],3)   AND CMNCS.MasterType= 'CustomerStatus' "
				+ "  JOIN UtilityConfig UC(NOLOCK) on UC.ModuleName=(case CI.AddressTypeId when  1 Then 'AboutMyHome' when 2 then 'AboutMyBusiness' end)"
				+ "	 AND UC.[ConfigOption]='TemplateTypeID' "
				+ "   JOIN TemplateType TY(NOLOCK) ON UC.ConfigValue=TY.TemplateTypeId "
				+ "   JOIN DynamicModules DM on DM.ModuleID=TY.ModuleID " + "  WHERE CI.ID IN(" + "  SELECT CI.ID "
				+ "  FROM CustomerInfo CI WITH(NOLOCK)  " + "  WHERE CI.CustomerId > 1 "
				+ "  AND IsNull(CI.UtilityAccountNumber,'')LIKE '%'+ IsNull(Cast(@AccountNumber as Varchar(32)),IsNull(CI.UtilityAccountNumber,'')) +'%'"
				+ "  AND IsNull(CI.FullName,'') LIKE '%'+ IsNull(@CustName,IsNull(CI.FullName,'')) +'%' "
				+ "  AND IsNull(CI.ZipCode,'')=IsNull(Cast(@ZipCode as Varchar(32)),IsNull(CI.ZipCode,'')) "
				+ "  AND IsNull(CI.MobilePhone,'') LIKE '%'+ IsNull(@MobilePhone,IsNull(CI.MobilePhone,'')) +'%' "
				+ "  AND ISNULL(CI.EmailID,'') LIKE '%'+ IsNull(@EmailID,ISNULL(CI.EmailID,'')) +'%' "
				+ "  AND (IsNull(CI.[address1],'') LIKE '%'+ IsNull(@address1,IsNull(CI.[address1],''))+'%'"
				+ "  OR IsNull(CI.[address2],'') LIKE '%'+ IsNull(@address1,IsNull(CI.[address2],''))+'%') "
				+ "  AND (IsNull(CI.FirstName,'') LIKE '%'+IsNull(@SearchString,IsNull(CI.FirstName,''))+'%'  "
				+ "  OR Isnull(CI.LastName,'') LIKE '%'+IsNull(@SearchString,Isnull(CI.LastName,''))+'%' "
				+ "  OR IsNull(CI.UtilityAccountNumber,'') LIKE '%'+IsNull(@SearchString,IsNull(CI.UtilityAccountNumber,''))+'%' "
				+ "  OR IsNull(CI.EmailID,'') LIKE '%'+IsNull(@SearchString,IsNull(CI.EmailID,''))+'%' "
				+ "  OR IsNull(CI.MobilePhone,'') LIKE '%'+IsNull(@SearchString,IsNull(CI.MobilePhone,''))+'%'  "
				+ "  OR IsNull(CI.FullName,'') LIKE '%'+IsNull(@SearchString,IsNull(CI.FullName,''))+'%'  "
				+ "  OR IsNull(CI.[address1],'') LIKE '%'+IsNull(@SearchString,IsNull(CI.[address1],''))+'%' "
				+ "  OR IsNull(CI.[address2],'') LIKE '%'+IsNull(@SearchString,IsNull(CI.[address2],''))+'%' "
				+ "  OR IsNull(CI.FullName,'') LIKE '%'+ IsNull(@SearchString,IsNull(CI.FullName,'')) +'%' "
				+ "  OR IsNull(Cast(CI.ZipCode as Varchar(32)),'') LIKE '%'+ IsNull(@SearchString,IsNull(Cast(CI.ZipCode as Varchar(32)),'')) +'%' "
				+ "     )" + " ) ";
		return q;
	}

	public static String getFieldValueUniversalSearch() {
		String q = "select top 1 * from Customerinfo  ORDER BY FullName ASC";
		return q;
	}

	public static String getValueByFieldName(String accountNumber, String AddressType, String AccountStatus,
			String customerNumber, String zipCode, String cityName) {
		String q = "select c.UtilityAccountNumber,c.FullName,c.EmailID,c.Address1,c.ZipCode,c.CustomerNo,c.AddressType,c.AccountStatus,c.MobilePhone,c.CityName from customerinfo c Join UserAccount a on c.AccountNumber=a.AccountNumber where ";
		boolean flag = false;
		if (!customerNumber.equalsIgnoreCase("null")) {
			q = q + "CustomerNo like '%" + customerNumber + "%'";
			flag = true;
		}
		if (!accountNumber.equalsIgnoreCase("null")) {
			if (flag) {
				q = q + " and ";
			}
			q = q + "c.UtilityAccountNumber like '%" + accountNumber + "%'";
			flag = true;
		}
		if (!AccountStatus.equalsIgnoreCase("---Select---")) {
			if (flag) {
				q = q + " and ";
			}
			q = q + "AccountStatus like '%" + AccountStatus + "%'";
			flag = true;
		}
		if (!AddressType.equalsIgnoreCase("---Select---")) {
			if (flag) {
				q = q + " and ";
			}
			q = q + "AddressType like '%" + AddressType + "%'";
			flag = true;
		}
		if (!zipCode.equalsIgnoreCase("null")) {
			if (flag) {
				q = q + " and ";
			}
			q = q + "zipCode like '%" + zipCode + "%'";
			flag = true;
		}
		if (!cityName.equalsIgnoreCase("null")) {
			if (flag) {
				q = q + " and ";
			}
			q = q + "cityName like '%" + cityName + "%'";
			flag = true;
		}
		q = q + " ORDER BY FullName ASC";
		return q;
	}

	/*
	 * Query for Fetching the reset password msg
	 */

	public static String getResetPasswordLinkAdmin(String emailID) {
		String q = "SELECT top 1 *  FROM ContractAccountNotifyEmail where emailId='" + emailID
				+ "' AND subject='Smart Energy Water Password Reset Link' ORDER BY 1 DESC";
		return q;
	}

	/*
	 * Query to fetch the Emailid form admin user
	 */

	public static String getUsernameFromAdminUser() {
		String q = "select top 10 username from Adminuser order by username ASC";
		return q;
	}

	public static String getFootPrintData(int zipcode, int locationtype) {
		String q = "select Name from GreenFootPrintLocations where LocationTypeId =" + locationtype + "and ZipCode = "
				+ zipcode + "order by Name ASC";
		return q;
	}

	public static String getFootPrintName(String username, String zipcode) {
		String q = "select Name from GreenFootPrintLocations where username = " + username + " and ZipCode = "
				+ zipcode;
		return q;
	}

	public static String getPaymentLocationsData() {
		String q = "select LocationName from PaymentLocation where IsDeleted=0 and cityId = '12979' order by LocationName ASC";
		return q;
	}

	public static String getPaymentDetailsChaseTransId(String transID) {
		String q = "select * from PaymentRecons where orderid='" + transID + "'";
		return q;
	}

	public static String getCustomerRefNum(String cardName, String userID, String utilityAccNum) {
		String q = "select top 1 * from PaymentProfiles where userid='" + userID + "' and CustomerName='" + cardName
				+ "' and serviceAccountNumber='" + utilityAccNum + "' and IsDeleted=0 order by createdDate desc";
		return q;
	}

	public static String getUserNameFromUserID(String userID) {
		String q = "select * from [User] where UserID='" + userID + "'";
		return q;
	}

	public static String getAlreadyUserUserNameFromUserID(String userID) {
		String q = "Select Top 1 UserName FROM [User] where UserName != '" + userID + "' Order By CreatedDate Desc";
		return q;
	}

	public static String getDefaultPaymentType(String sAccountNumber) {
		String q = "Select DefaultPaymentType from useraccount where UtilityAccountNumber = '" + sAccountNumber + "'";
		return q;
	}

	public static String getAutoPayCount(String utilityNum) {
		String q = "SELECT COUNT(*)  AS autopayCount from AccountRecurringPayment where utilityAccountNumber='"
				+ utilityNum + "' order by 1 desc ";
		return q;
	}

	public static String getAutopayInfo(String utilityAccountNum) {
		return "select id, accountnumber, paytypeid, payid, recpaymentdate, nextpaymentdate, \n"
				+ "paymentconfigid, paymentamount, paymentfrequencyid, paymentsubtype, \n"
				+ "userprofileid, utilityaccountnumber, userid\n" + "from accountrecurringpayment \n"
				+ "where utilityaccountnumber = '" + utilityAccountNum + "' \n" + "order by 1 desc";
	}

	public static String getMeterInfoLinkedToAccount(String accountNumber) {
		return "select meterid, accountnumber, meternumber, metertype, \n"
				+ "isami, status, moveindate, moveoutdate, rateplanid \n" + "from accountmetermapping\n"
				+ "where accountnumber = '" + accountNumber + "' ";
	}

	public static String getTop10AdminRoleName() {
		String q = "select Top 10 RoleName from role order by rolename ASC";
		return q;
	}

	public static String getAllAdminRoleName() {
		String q = "select RoleName from role order by rolename ASC";
		return q;
	}

	public static String getorderIDFromPayments(int postingStatus, String statusMessage) {
		String q = "select top 1 * from PaymentRecons where CisPostingStatus ='" + postingStatus
				+ "' and StatusMessage = '" + statusMessage + "' order by 1 desc";
		return q;
	}

	public static String getorderIDFromPayments(String orderId) {
		String q = "select * from paymentRecons where orderid = '" + orderId + "'";
		return q;
	}

	public static String getEmailSubject(String emailid) {
		String q = "SELECT top 1 * FROM ContractAccountNotify where emailid ='" + emailid + "' order by 1 desc";
		return q;
	}

	public static String getEmailIdNotificationPre(String UtilityAccountNumber) {
		String q = "SELECT * FROM AccountNotificationDetail where AccountNumber=(select AccountNumber from Account where utilityAccountNumber='"
				+ UtilityAccountNumber + "') and AccountNotificationTypeID=6";
		return q;
	}

	public static String getEmailTemplate(String templateName) {
		String q = "select * from EMailTemplate where TemplateName = '" + templateName + "'";
		return q;
	}

	public static String getPaymentOrderIDByCreateDate(String date, String paymentStatus) {
		String q = "select top 1 * from PaymentRecons where CreatedDate like '" + date + "%' and StatusMessage='"
				+ paymentStatus + "'" + "and IsRefunded=0 and IsReversed=0";
		return q;
	}

	public static String getPaymentTransactionID() {
		String q = "SELECT top 1 * FROM PaymentRecons where StatusMessage='Successful'and IsRefunded=0 and IsReversed=0 order by PaymentDate desc";
		return q;
	}

	public static String getEmailExcelReports(String emailId) {
		String q = "select top 1 * from ContractAccountNotifyEmail where EmailId = '" + emailId + "' order by 1 desc ";
		return q;
	}

	public static String getEmailIDFromAdminUser(String username) {
		String q = "select EmailID from Adminuser where username ='" + username + "'";
		return q;
	}

	// Query for notification module
	public static String getNotificationByPlaceHolder(String placeHolderNum) {
		return "exec getinboxmessagesadmin @accountnumber=N'2', @pagesize=N'50', @timeoffset=N'-480', \n"
				+ "@placeholderid=N'" + placeHolderNum + "', @pageindex=N'1', @userid=N'1', @ispageload=N'0'";
	}

	// Query for validate the notification in db
	public static String getSubjectNotification(String subject) {
		String q = "SELECT * FROM ContractAccountNotifyEmail where Subject = '" + subject + "' order by 1 desc";
		return q;
	}

	public static String getEmailIdByUtilityAccNumber(String utilityAccNum) {
		String q = "select EmailID from customerInfo where UtilityAccountNumber = '" + utilityAccNum + "'";
		return q;
	}

	/*
	 * This method is used to get the count of the Customer ref no present for an
	 * userID
	 */
	public static String getCustRefNoCount(String userID) {
		String q = "select  count(CustomerRefNum) as custRefNumber  from paymentprofiles where userId=" + userID
				+ " and isDeleted=0";
		return q;
	}

	public static String getCustRefnumber(String userID) {
		String q = "select CustomerRefNum from paymentprofiles where userId=" + userID + " and isDeleted=0";
		return q;
	}

	/*
	 * //query for validate the notification in db public static String
	 * getSubjectNotification(String subject) { String q =
	 * "SELECT * FROM ContractAccountNotifyEmail where Subject = '"
	 * +subject+"' order by 1 desc"; return q; }
	 */
	// query for Efficiency Module
	public static String getEfficiencyCount(String categoryId) {
		String q = "SELECT COUNT(categoryId) as Count from EEPromotion where categoryId='" + categoryId
				+ "' and IsDeleted=0";
		return q;
	}

	public static String getTotalEfficiencyCount(String categoryId) {
		String q = "DECLARE  @IsPowerEnable BIT\r\n" + "    ,@IsWaterEnable BIT\r\n" + "    ,@IsGasEnable BIT\r\n"
				+ "    ,@IsSolarEnable BIT\r\n"
				+ "  SELECT @IsPowerEnable = [Status] FROM FeatureSettings WITH(NOLOCK) WHERE FeatureName = 'Power'\r\n"
				+ "  SELECT @IsWaterEnable = [Status] FROM FeatureSettings WITH(NOLOCK) WHERE FeatureName = 'Water'\r\n"
				+ "  SELECT @IsGasEnable  = [Status] FROM FeatureSettings WITH(NOLOCK) WHERE FeatureName = 'Gas'\r\n"
				+ "  SELECT @IsSolarEnable = [Status] FROM FeatureSettings WITH(NOLOCK) WHERE FeatureName = 'Solar'\r\n"
				+ "  SELECT\r\n" + "   count(1) as Count\r\n" + "  FROM  EEPromotion   p WITH(NOLOCK)\r\n"
				+ "  INNER JOIN EEPromotionCategory c WITH(NOLOCK) ON p.CategoryId = c.CategoryId\r\n"
				+ "  LEFT JOIN (SELECT COUNT(*) AS Cnt  ,PromotionId FROM EEPromotionUsers WITH(NOLOCK) GROUP BY PromotionId) RR ON RR.PromotionId = p.PromotionId\r\n"
				+ "  LEFT JOIN (SELECT COUNT(*) AS LikeCnt ,PromotionId FROM EEPromotionLike WITH(NOLOCK) GROUP BY PromotionId) PL ON PL.PromotionId = p.PromotionId\r\n"
				+ "  WHERE p.IsDeleted = '0'\r\n" + "   AND (@IsPowerEnable = 1 OR p.ServiceTypeId <> 1)\r\n"
				+ "   AND (@IsWaterEnable = 1 OR p.ServiceTypeId <> 2)\r\n"
				+ "   AND (@IsGasEnable = 1 OR p.ServiceTypeId <> 3)\r\n"
				+ "   AND (@IsSolarEnable = 1 OR p.ServiceTypeId <> 4)\r\n" + "   and p.categoryId='" + categoryId
				+ "';";
		return q;
	}

	public static String getEfficiencyCount(String categoryId, String title) {
		String q = "SELECT COUNT(categoryId) as Count from EEPromotion where categoryId='" + categoryId
				+ "' and IsDeleted=1 and Title = '" + title + "'";
		return q;
	}

	public static String getWaysToSaveDetails(String categoryID, String efficiencyName) {
		String q = "SELECT * from EEPromotion where categoryId='" + categoryID + "'and title = '" + efficiencyName
				+ "'";
		return q;
	}

	public static String getFirstWaysToSaveRecordFromDb(String categoryID) {
		String q = "SELECT top 1 * from EEPromotion where categoryId='" + categoryID
				+ "' and IsDeleted =0 order by UpdatedDate  DESC ";
		return q;
	}

	public static String deleteWaysToSave(String categoryID, String waysToSaveName) {
		String q = "delete from EEPromotion where categoryId='" + categoryID + "'and Title = '" + waysToSaveName + "'";
		return q;
	}

	public static String deleteWaysToSave(String waysToSaveName) {
		String q = "delete from EEPromotion where Title = '" + waysToSaveName + "'";
		return q;
	}

	public static String getWaysToSaveDetails(String categoryId) {
		String q = "SELECT * from EEPromotion where categoryId='" + categoryId + "' and IsDeleted=0";
		return q;
	}

	public static String getSignedUpEfficiencyTitle(String categoryId) {
		String q = "select top 1 title from eepromotion A INNER JOIN eepromotionUSERS B ON A.PromotionId = B.PromotionId where A.isdeleted = 0 and categoryid='"
				+ categoryId + "' order by A.createddate desc";
		return q;
	}

	// query for Reset Password
	public static String getEmailContent(String emailID) {
		String q = "SELECT top 1 *  FROM ContractAccountNotifyEmail where emailId='" + emailID
				+ "' AND subject='SCM Temporary Password' ORDER BY 1 DESC";
		return q;
	}

	public static String setUserPassword(String password, String utilityAccountNumber) {
		String q = "Update [user] Set Password ='" + password
				+ "' where userID = (Select userID from [userAccount] where utilityAccountNumber ='"
				+ utilityAccountNumber + "')";
		return q;
	}

	public static String getPasswordUpdatedEmailContent(String emailID) {
		String q = "SELECT top 1 * FROM ContractAccountNotifyEmail where emailId='" + emailID + "' ORDER BY 1 DESC";
		return q;
	}

	// Query for email count by module wise
	public static String getEmailByMessageType(String toDate, String fromDate, String msgType) {
		String q = "select count(*) as totalEmail from ContractAccountNotifyEmail where (CreateDate	between '" + toDate
				+ "' AND '" + fromDate + "') and Module = '" + msgType + "' order by 1 desc ";
		return q;
	}

	// Query for email count by module wise
	public static String getEmailByStatus(String toDate, String fromDate, String status) {
		String q = "select count(*) as totalEmail from emailnotificationstatus where (CreateDate	between '" + toDate
				+ "' AND '" + fromDate + "') and StatusDescription = '" + status + "' order by 1 desc ";
		return q;
	}

	// Query for getting the status of notification
	public static String getNotificationReceivedStatus(String emailID) {
		String q = "SELECT top 1 * FROM ContractAccountNotifyEmail where emailId='" + emailID + "' ORDER BY 1 DESC";
		return q;
	}

	// Query for getting the status of notification alert
	public static String getNotificationAlertStatus(String accountNum, String emailID) {
		String q = "select * from accountnotificationdetail where AccountNumber = '" + accountNum
				+ "' and EmailORPhone = '" + emailID + "'";
		return q;
	}

	public static String getCountProgramRebatesAnalyticsEfficiency(String startDate, String endDate, int mode) {
		String q = "DECLARE @DateFrom   DATETIME ='" + startDate + "', @DateTo   DATETIME = '" + endDate + "'"
				+ ",@Mode    TINYINT  = " + mode + "    /* Mode=3 For Rebates and Mode=4 For Programs*/"
				+ ",@EECategoryType INT   = NULL, @TimeOffSet int=330 "
				+ "SELECT   sum(ISNULL([Approved Enrollment], 0))         AS [ApprovedEnrollment]   "
				+ ",sum(ISNULL([Pending Enrollment], 0))         AS [PendingEnrollment]   "
				+ ",Sum(ISNULL([Approved Enrollment], 0) + ISNULL([Pending Enrollment], 0)) AS [TotalEnrollment]   "
				+ "FROM (SELECT  epu.CreatedDate            AS Created_Date, "
				+ "CASE epu.UserPromotionStatus WHEN 1 THEN 'Approved Enrollment' ELSE 'Pending Enrollment' END AS EnrollmentType   "
				+ ",COUNT(epu.accountNumber)                  AS PeopleEnrolled   FROM  eepromotion   ep  WITH(NOLOCK)   "
				+ "INNER JOIN eepromotionusers epu  WITH(NOLOCK) ON ep.PromotionId = epu.PromotionId AND ep.IsDeleted = 0   "
				+ "INNER JOIN eepromotioncategory epc  WITH(NOLOCK) ON ep.categoryid = epc.CategoryId   "
				+ "INNER JOIN Account    a  WITH(NOLOCK) ON a.AccountNumber = epu.AccountNumber   "
				+ "INNER JOIN CustomerAddress  ca  WITH(NOLOCK) ON ca.AddressID = a.AddressID   "
				+ "INNER JOIN dbo.Customer  Cust WITH(NOLOCK) ON CA.CustomerID = Cust.CustomerID   "
				+ "WHERE epu.CreatedDate >= @DateFrom AND epu.CreatedDate < @DateTo  AND epc.CategoryId = @Mode  "
				+ "GROUP BY epu.CreatedDate  ,CASE epu.UserPromotionStatus WHEN 1 THEN 'Approved Enrollment' ELSE 'Pending Enrollment' END   "
				+ ") AS src_table   PIVOT(SUM(PeopleEnrolled) FOR EnrollmentType IN ([Approved Enrollment],[Pending Enrollment])) AS pv_table   "
				+ "where CAST(Created_Date As DATE) BETWEEN @DateFrom AND @DateTo";
		return q;
	}

	public static String getRecordDetailsAnalyticsProgramRebates(String createdDate, int Mode) {
		String q = "Declare @EECategoryType INT   = " + Mode
				+ ", @PromotionId  BIGINT = NULL ,@UtilityAccount VARCHAR(100) = NULL "
				+ ",@FullName   VARCHAR(100) = NULL "
				+ "SELECT top 1 CONVERT(VARCHAR(19),epu.CreatedDate,120) AS CreatedDate, A.UtilityAccountNumber,Cust.FullName,"
				+ "ep.Title, ep.PromotionId, Cust.EmailID, ep.SavingValue, ep.Description FROM  eepromotion   ep  WITH(NOLOCK) "
				+ "INNER JOIN eepromotionusers epu  WITH(NOLOCK) ON ep.PromotionId = epu.PromotionId AND ep.IsDeleted = 0 "
				+ "INNER JOIN eepromotioncategory epc  WITH(NOLOCK) ON ep.categoryid = epc.CategoryId "
				+ "INNER JOIN Account    a  WITH(NOLOCK) ON a.AccountNumber = epu.AccountNumber "
				+ "INNER JOIN CustomerAddress  ca  WITH(NOLOCK) ON ca.AddressID = a.AddressID "
				+ "INNER JOIN dbo.Customer  Cust WITH(NOLOCK) ON CA.CustomerID = Cust.CustomerID "
				+ "WHERE convert(nvarchar(50), epu.CreatedDate,121) LIKE '" + createdDate + "%'"
				+ "AND epc.CategoryId = ISNULL(CONVERT(VARCHAR(2), @EECategoryType), epc.CategoryId) "
				+ "/* AND EP.Title LIKE '%'+ISNULL(@Topic,EP.Title)+'%'*/ "
				+ "AND Cust.FullName LIKE '%'+ISNULL(@FullName,Cust.FullName)+'%' "
				+ "AND A.UtilityAccountNumber LIKE '%'+ISNULL(@UtilityAccount,A.UtilityAccountNumber)+'%' ORDER by createdDate Desc";
		return q;
	}

	public static String getTotalRecordsAnalyticsProgramRebates(int noOfRecords, String startDate, String endDate,
			int Mode) {
		String q = "Declare @DateFrom Datetime= '" + startDate + "',@DateTo Datetime ='" + endDate + "'"
				+ ",@EECategoryType INT   = " + Mode
				+ ", @PromotionId  BIGINT = NULL, @UtilityAccount VARCHAR(100) = NULL,"
				+ "@FullName   VARCHAR(100) = NULL " + "SELECT Top " + noOfRecords
				+ " CONVERT(VARCHAR(19),epu.CreatedDate,120)               AS CreatedDate,"
				+ "A.UtilityAccountNumber, Cust.FullName, ep.Title, ep.PromotionId, Cust.EmailID, "
				+ "ep.SavingValue,ep.Description " + "FROM  eepromotion   ep  WITH(NOLOCK) "
				+ "INNER JOIN eepromotionusers epu  WITH(NOLOCK) ON ep.PromotionId = epu.PromotionId AND ep.IsDeleted = 0 "
				+ "INNER JOIN eepromotioncategory epc  WITH(NOLOCK) ON ep.categoryid = epc.CategoryId "
				+ "INNER JOIN Account    a  WITH(NOLOCK) ON a.AccountNumber = epu.AccountNumber "
				+ "INNER JOIN CustomerAddress  ca  WITH(NOLOCK) ON ca.AddressID = a.AddressID "
				+ "INNER JOIN dbo.Customer  Cust WITH(NOLOCK) ON CA.CustomerID = Cust.CustomerID "
				+ "WHERE CAST(epu.CreatedDate As DATE) BETWEEN @DateFrom AND @DateTo "
				+ "AND epc.CategoryId = ISNULL(CONVERT(VARCHAR(2), @EECategoryType), epc.CategoryId) "
				+ "/* AND EP.Title LIKE '%'+ISNULL(@Topic,EP.Title)+'%'*/ "
				+ "AND Cust.FullName LIKE '%'+ISNULL(@FullName,Cust.FullName)+'%' "
				+ "AND A.UtilityAccountNumber LIKE '%'+ISNULL(@UtilityAccount,A.UtilityAccountNumber)+'%' ORDER by createdDate Desc";
		return q;
	}

	// Query for getting the Quiet hours status of notification alert
	public static String getQuietHoursStatus(String accountNumber, String userID) {
		String q = "select * from AccountNotification where AccountNumber = '" + accountNumber + "' and UserID = '"
				+ userID + "'";
		return q;
	}

	// Query for getting the Quiet hours status of notification alert
	public static String getACcountNumberByUtilityAccountNumber(String utilityAccNum, String userID) {
		String q = "select * from AccountNotification where AccountNumber = '" + utilityAccNum + "'";
		return q;
	}

	/**
	 * This query is used for fetching default account number
	 *
	 * @param sUserName
	 * @return
	 */
	public static String getDefaultAccountNumber(String sUserName) {
		String sDefaultAccountQuery = "SELECT DISTINCT AccountNumber, UtilityAccountNumber " + "FROM UserAccount "
				+ "WHERE UserID = (SELECT UserID FROM [User] WHERE UserName = '" + sUserName + "') "
				+ "AND IsDefaultAccount = '1'";
		return sDefaultAccountQuery;
	}

	public static String getServiceAddressOfAccountNumber(String utilityAccountNumber) {
		String serviceAddress = "SELECT UtilityAccountNumber, Address1, Address2, CityName,StateCode,StateName,ZipCode FROM CustomerInfo(NOLOCK) "
				+ "where UtilityAccountNumber ='" + utilityAccountNumber + "';";
		return serviceAddress;
	}

	/*
	 * This query will used for getting the total service accounts
	 */
	public static String getTotalServiceAccounts() {
		String totalServiceAccuntsCount = "SELECT COUNT(DISTINCT AccountNumber) as TotalServiceAccountsCount "
				+ "FROM CustomerInfo(NOLOCK) WHERE CustomerID NOT IN (1,-1)";
		return totalServiceAccuntsCount;
	}

	/*
	 * This query will used for the getting the active accounts
	 */
	public static String getActiveServiceAccounts() {
		String activeAccountCount = "SELECT COUNT(DISTINCT AccountNumber) as ActiveAccounts FROM CustomerInfo(NOLOCK)"
				+ "WHERE AccountStatusID IN (0,1,3) AND CustomerID NOT IN (1,-1)";
		return activeAccountCount;
	}

	/*
	 * This query will get the registered user count
	 */
	public static String getRegisterUsersCount() {
		String registeredUserCount = "SELECT count(DISTINCT U.userid) as TotalUser\r\n"
				+ "        FROM [user] U  WITH(NOLOCK)\r\n"
				+ "        JOIN (select accountNumber,Userid from UserAccount  WITH(NOLOCK)\r\n"
				+ "                Union all\r\n" + " SELECT PA.accountNumber,PU.Userid\r\n"
				+ "                FROM     PortfolioAccount    PA    WITH(NOLOCK)\r\n"
				+ "                INNER JOIN  PortfolioUser    PU    WITH(NOLOCK) ON PA.GroupID=PU.GroupID AND PU.IsActive=1\r\n"
				+ "                where PA.IsActive=1) UA  ON U.userid=UA.userid\r\n"
				+ "        WHERE U.userid NOT IN (-1,1) AND U.[STATUS] not in (3) AND U.UserType Not in (3)";
		return registeredUserCount;
	}

	public static String getRegisterdActiveUserCount() {
		String registerdActiveUserCount = "SELECT count(DISTINCT U.userid)  As ActiveUser " + "FROM [user] U (NOLOCK)"
				+ " JOIN (select accountNumber,Userid from UserAccount (NOLOCK)" + " Union all"
				+ " SELECT PA.accountNumber,PU.Userid" + " FROM     PortfolioAccount    PA    WITH(NOLOCK)"
				+ " INNER JOIN  PortfolioUser    PU    WITH(NOLOCK) ON PA.GroupID=PU.GroupID AND PU.IsActive=1"
				+ " where PA.IsActive=1 )" + " UA ON U.userid=UA.userid"
				+ " WHERE U.STATUS =1 AND isnull(U.islocked,0)=0 AND U.userid NOT IN (-1,1) AND U.UserType Not in (3,5)";
		return registerdActiveUserCount;
	}

	/*
	 * This query will reset the primay email id
	 */
	public static String resetPrimaryEmailID(String username, String emailID) {
		String resetEmail = "UPDATE [User] SET EmailID = '" + username + "' WHERE UserName = '" + emailID + "'";
		return resetEmail;
	}

	/*
	 * This query will return the primary Email id
	 */
	public static String getPrimaryEmailID(String userID) {
		String resetEmail = "select EmailID from [User] where UserID = '" + userID + "'";
		return resetEmail;
	}

	/*
	 * This query returns count of linked account number
	 */
	public static String getCountLinkedAccount(String userID) {
		String NoOfLinkedAccount = "Select count (UserID) as LinkedAccountCount from UserAccount  where UserID = '"
				+ userID + "'";
		return NoOfLinkedAccount;
	}

	/*
	 * This query returns user status
	 */
	public static String getStatusUser(String userName) {
		String statusUser = "SELECT Status FROM [User] WHERE UserName = '" + userName + "'";
		return statusUser;
	}

	/**
	 * This query will return the total Billing Data
	 *
	 * @return
	 */
	public static String getBillingDataCount(String utilityAccNum) {
		String billingCount = "Select count(BillingId) as BillingRecordCount from Billing  where cast (BillingDate as date) > '2018-05-31' and AccountNumber=(select AccountNumber from account where utilityAccountNumber='"
				+ utilityAccNum + "')";
		return billingCount;
	}

	/*
	 * This query returns transaction data count
	 */
	public static String getTransactionDataCount(String utilityAccNum) {
		String transactionCount = "SELECT Distinct count(BillingTransactionId) as TotalTransaction FROM BillingTransaction where cast (TransactionDate as date) > '2018-03-29' and accountnumber=(select AccountNumber from account where utilityAccountNumber='"
				+ utilityAccNum + "') group by AccountNumber";
		return transactionCount;
	}

	public static String getFilerDataForBanners(String toDate, String fromDate, String moduleName) {
		/*
		 * String q1 = "	Declare @DateFrom DATETIME = '" + fromDate + "'" +
		 * " ,@DateTo DATETIME = '" + toDate + "'" + " ,@BannerTitle VARCHAR(100)= NULL"
		 * + " ,@OrderColumn VARCHAR(50) = N'ClickDate'" +
		 * " ,@SortOrder VARCHAR(50) = N'desc'" + " ,@BannerName VARCHAR(100) = '" +
		 * moduleName + "'" + " ,@PageLength INT = 50" + " ,@PageStartFrom INT = 0" +
		 * " ,@TimeOffSet int=330"
		 *
		 * + "  Declare @PageTo INT " + "  set @PageTO= @PageStartFrom+@PageLength"
		 *
		 * + "  IF ISNULL(@OrderColumn, '') = ''" + "  SET @OrderColumn = 'ClickDate'" +
		 * "  ELSE" + "  SET @OrderColumn = @OrderColumn" +
		 * "  IF ISNULL(@OrderColumn, '') = 'BannerTitle'" +
		 * "   SET @OrderColumn = 'Title'" + "  ELSE" +
		 * "   SET @OrderColumn = @OrderColumn" + "  IF ISNULL(@SortOrder, '') = ''" +
		 * "   SET @SortOrder = 'asc'" + "  ELSE" + "   SET @SortOrder = @SortOrder" +
		 * "  IF ISNULL(@DateFrom, '') = ''" +
		 * "   SET @DateFrom = CAST(DATEADD(MM, - 1, GETDATE()) AS DATE)" +
		 * "  IF ISNULL(@DateTo, '') = ''" + "   SET @DateTo = CAST(GETDATE() AS DATE)"
		 * + "   ELSE" + "   SET @DateTo = DATEADD(DD, 0, @DateTo)"
		 *
		 * +
		 * " SELECT  CONVERT(VARCHAR(19),BTC.TraceTime,120) ClickDate,BS.BannerName, Alternatetext AS Title"
		 * + "  ,C.FullName,C.EmailId,CA.UtilityAccountNumber, BS.BannerContent"
		 *
		 * + "     FROM BannerMaster          BS WITH(NOLOCK)" +
		 * "    JOIN BannerTraceClicks     BTC WITH(NOLOCK) ON BTC.BannerID = BS.BannerID"
		 * +
		 * " JOIN CustomerAddress  CA WITH(NOLOCK) ON BTC.UtilityAccountNumber = CA.UtilityAccountNumber"
		 * + " JOIN Customer    C WITH(NOLOCK) ON CA.CustomerId = C.CustomerId where" +
		 * "        	CAST(DATEADD(MI,@TimeOffSet,BTC.TraceTime) As DATE) BETWEEN @DateFrom AND @DateTo "
		 * + "  AND BS.BannerName LIKE '%'+ISNULL(@BannerName,BS.BannerName)+'%'" +
		 * "  AND Alternatetext LIKE '%'+ISNULL(@BannerTitle,Alternatetext)+'%'";
		 */
		String q = "DECLARE @DateFrom DATETIME = '" + fromDate + "'\r\n" + ",@DateTo DATETIME = '" + toDate + "'\r\n"
				+ ",@BannerTitle VARCHAR(100) = NULL\r\n" + ",@OrderColumn VARCHAR(50) = N'ClickDate'\r\n"
				+ ",@SortOrder VARCHAR(50) = N'desc'\r\n" + ",@BannerName VARCHAR(100) = '" + moduleName + "'\r\n"
				+ ",@PageLength INT = 50\r\n" + ",@PageStartFrom INT = 0\r\n" + ",@TimeOffSet INT = 330\r\n"
				+ "DECLARE @PageTo INT\r\n" + "SET @PageTO = @PageStartFrom + @PageLength\r\n"
				+ "IF ISNULL(@OrderColumn, '') = ''\r\n" + "SET @OrderColumn = 'ClickDate'\r\n" + "ELSE\r\n"
				+ "SET @OrderColumn = @OrderColumn\r\n" + "IF ISNULL(@OrderColumn, '') = 'BannerTitle'\r\n"
				+ "SET @OrderColumn = 'Title'\r\n" + "ELSE\r\n" + "SET @OrderColumn = @OrderColumn\r\n"
				+ "IF ISNULL(@SortOrder, '') = ''\r\n" + "SET @SortOrder = 'asc'\r\n" + "ELSE\r\n"
				+ "SET @SortOrder = @SortOrder\r\n" + "IF ISNULL(@DateFrom, '') = ''\r\n"
				+ "SET @DateFrom = CAST(DATEADD(MM, - 1, GETDATE()) AS DATE)IF ISNULL(@DateTo, '') = ''\r\n"
				+ "SET @DateTo = CAST(GETDATE() AS DATE)\r\n" + "ELSE\r\n" + "SET @DateTo = DATEADD(DD, 0, @DateTo)\r\n"
				+ "SELECT CAST(DATEADD(MI, @TimeOffSet, BTC.date_clicked) AS DATETIME) ClickDate--CONVERT(VARCHAR(19), BTC.date_clicked, 120) ClickDate\r\n"
				+ ",BTC.Module_Name\r\n" + ",B.banner_title\r\n" + ",C.FullName\r\n" + ",C.EmailId\r\n"
				+ ",CA.UtilityAccountNumber\r\n" + "FROM banner_tracking BTC WITH (NOLOCK)\r\n"
				+ "JOIN banner b WITH (NOLOCK) ON BTC.Banner_ID=b.banner_id\r\n"
				+ "JOIN CustomerAddress CA WITH (NOLOCK) ON BTC.Account_Number = CA.UtilityAccountNumber\r\n"
				+ "JOIN Customer C WITH (NOLOCK) ON CA.CustomerId = C.CustomerId\r\n"
				+ "WHERE CAST(DATEADD(MI, @TimeOffSet, BTC.date_clicked) AS DATE) BETWEEN @DateFrom\r\n"
				+ "AND @DateTo\r\n" + "AND BTC.MODULE_NAME LIKE '%' + ISNULL(@BannerName, BTC.MODULE_NAME) + '%'\r\n"
				+ "AND b.language_code = 'EN'\r\n" + "order by BTC.date_clicked desc";
		return q;
	}

	public static String getFilerDataForMarketingPreference(String toDate, String fromDate, String email) {
		String q = "Declare\r\n" + "@UserSelection TINYINT  = NULL,\r\n" + "@EmailID  VARCHAR(200) = '" + email
				+ "',\r\n" + "@DateTo  DATETIME = '" + toDate + "',\r\n" + "@DateFrom  DATETIME = '" + fromDate
				+ "',\r\n" + "@CustomerType TINYINT  = 0,    --1 Residential,2 Commercial/Industrial\r\n"
				+ "@Name  VARCHAR(200) = NULL, --customer name\r\n"
				+ "@AccountNumber  VARCHAR(100) = NULL,--utility account number\r\n"
				+ "@PreferenceName VARCHAR(200) = NULL,--Prefernce Name\r\n"
				+ "@OrderColumn  VARCHAR(50)= NULL, --order by column name\r\n"
				+ "   @SortOrder  VARCHAR (50)= NULL, -- order by type\r\n" + "   @PageLength   INT=500,\r\n"
				+ "   @PageStartFrom  INT=0\r\n" + "\r\n" + "  DECLARE    @DynamicQuery   AS NVARCHAR(MAX),\r\n"
				+ "       @param               AS NVARCHAR(MAX),\r\n"
				+ "       @DynamicCountQuery   AS NVARCHAR(MAX)\r\n" + "\r\n"
				+ "  IF ISNULL( LEN( LTRIM(RTRIM( @DateFrom )) ) , 0) = 0 SET @DateFrom  = NULL\r\n"
				+ "  IF ISNULL( LEN( LTRIM(RTRIM( @DateTo )) ) , 0)  = 0 SET @DateTo   = GETDATE()\r\n"
				+ "  IF ISNULL(@OrderColumn, '')= '' SET @OrderColumn='CreatedDate' ELSE SET @OrderColumn=@OrderColumn\r\n"
				+ "  IF ISNULL(@SortOrder, '')= '' SET @SortOrder='asc' ELSE SET @SortOrder=@SortOrder\r\n" + "\r\n"
				+ "\r\n" + "   SET @DynamicQuery =N' \r\n" + "    Select * from \r\n"
				+ "     (SELECT row_number() over (order by '+@OrderColumn+' '+@SortOrder+')''RN'',*' + '\r\n"
				+ "     FROM\r\n" + "     (\r\n" + "     SELECT \r\n" + "     ca.UtilityAccountNumber,\r\n"
				+ "     U.EmailID,\r\n" + "     CONVERT(VARCHAR(19),amp.CreatedDate,120) AS CreatedDate,\r\n"
				+ "     amp.PreferenceId,\r\n" + "     ISNULL(c.FullName,U.FullName) AS Name,\r\n"
				+ "     CASE amp.PreferenceId\r\n" + "      WHEN 1 THEN ''News Releases''\r\n"
				+ "      WHEN 2 THEN ''Service Offerings''\r\n" + "      WHEN 3 THEN ''Newsletters''\r\n"
				+ "      WHEN 4 THEN ''Energy Savings Toolkits''\r\n" + "      WHEN 5 THEN ''Cool Tips Brochures''\r\n"
				+ "      WHEN 6 THEN ''Community Benefit Programs''\r\n" + "      ELSE ''''\r\n"
				+ "     END AS PreferenceName  \r\n" + "     FROM UserMarketingPreferenceSetting amp WITH(NOLOCK)\r\n"
				+ "     JOIN [User] U ON u.userid=amp.userid\r\n"
				+ "       INNER JOIN UserAccount UA ON  UA.userid=U.userID\r\n"
				+ "       INNER JOIN Account A  WITH(NOLOCK) ON A.AccountNumber = UA.AccountNumber \r\n"
				+ "       INNER JOIN CustomerAddress   CA WITH(NOLOCK) ON CA.Addressid = A.Addressid\r\n"
				+ "       INNER JOIN Customer    c WITH(NOLOCK) ON c.customerid = CA.customerid\r\n" + "  \r\n"
				+ "     WHERE   U.EmailID like ISNULL(''%''+@EmailId+''%'',U.EmailId)\r\n"
				+ "       AND C.FullName like ISNULL(''%''+@Name+''%'',C.FullName)\r\n"
				+ "       AND ((@AccountNumber IS NULL AND UA.IsDefaultAccount=1) OR (@AccountNumber IS NOT NULL AND ca.UtilityAccountNumber like ''%''+@AccountNumber+''%''))\r\n"
				+ "       --AND ca.UtilityAccountNumber like ISNULL(''%''+@AccountNumber+''%'',ca.UtilityAccountNumber)\r\n"
				+ "       AND amp.CreatedDate >= ISNULL(CONVERT(VARCHAR(10),@DateFrom,111),amp.CreatedDate)\r\n"
				+ "       AND amp.CreatedDate < DATEADD(DAY,1,ISNULL(CONVERT(VARCHAR(10),@DateTo,111),amp.CreatedDate))\r\n"
				+ "     ) AS SourceTable Where SourceTable.PreferenceName like ISNULL(''%''+@PreferenceName+''%'',SourceTable.PreferenceName)\r\n"
				+ "    ) AS Result\r\n" + "\r\n"
				+ "    where  RN BETWEEN cast((@PageStartFrom+1) AS VARCHAR) AND cast(@PageStartFrom + @PageLength AS VARCHAR) ORDER BY RN'\r\n"
				+ "    --insert into abc values(@DynamicQuery)\r\n"
				+ "   SET @param=N'@DateFrom DATETIME, @DateTo DATETIME, @EmailID VARCHAR(200), @PageStartFrom INT, @PageLength INT, @OrderColumn  VARCHAR(50), @SortOrder VARCHAR(50), @Name VARCHAR(200), @AccountNumber VARCHAR(100), @PreferenceName VARCHAR(200)'\r\n"
				+ "  -- Print @DynamicQuery\r\n"
				+ "            EXEC SP_EXECUTESQL @DynamicQuery, @param, @DateFrom, @DateTo, @EmailID, @PageStartFrom, @PageLength, @OrderColumn, @SortOrder, @Name, @AccountNumber, @PreferenceName\r\n"
				+ "   \r\n" + "   \r\n" + "";
		return q;
	}

	/*
	 * This query returns CSR Profile tab data
	 */
	public static String getCSRPofileDataFromUser(String utilityAccNum) {
		String userData = "select EmailID, AlternateEmailID, UserName,MobilePhone,FullName, FirstName from [User] where UserID = (select top 1 UserID from useraccount where UtilityAccountNumber = '"
				+ utilityAccNum + "')";
		return userData;
	}

	/*
	 * This query returns CSR Profile tab data
	 */
	public static String getCSRPofileDataFromCustomerInfo(String utilityAccNum) {
		String customerData = "select AccountStatus,FullName,CreatedDate, ZipCode,CityName, AddressType from CustomerInfo where UtilityAccountNumber = '"
				+ utilityAccNum + "'";
		return customerData;
	}

	/*
	 * This query returns CSR Profile tab meter data
	 */
	public static String getCSRPofileMeterMapping(String utilityAccNum) {
		String meterData = "SELECT * FROM AccountMeterMapping where AccountNumber= (select Top 1 AccountNumber from  useraccount where UtilityAccountNumber = '"
				+ utilityAccNum + "')";
		return meterData;
	}

	/*
	 * This query returns CSR Profile tab marketing pref data
	 */
	public static String getCSRPofileUserMarketingPreferences(String utilityAccNum) {
		String marketigPrefData = "SELECT * FROM UserMarketingPreferenceSetting where AccountNumber= (select Top 1 AccountNumber from  useraccount where UtilityAccountNumber = '"
				+ utilityAccNum + "')";
		return marketigPrefData;
	}

	/*
	 * This query returns CSR paperless status
	 */
	public static String getpaperLessStatus(String utilityAccNum, String userID) {
		String paperLessStatusDB = "select Paperless from useraccount where UtilityAccountNumber = '" + utilityAccNum
				+ "' and  UserID = '" + userID + "'";
		return paperLessStatusDB;
	}

	public static String getDetailsForAutopayEnrollment(String userName) {
		return "select u.userid, ua.accountnumber, ua.utilityaccountnumber, ci.zipcode\n" + "from [user] as u\n"
				+ "left join useraccount as ua on ua.userid = u.userid\n"
				+ "left join customerinfo as ci on ci.accountnumber = ua.accountnumber\n" + "where u.username = '"
				+ userName + "'\n" + "and ua.isdefaultaccount = '1'";
	}

	public static String getPaymentInfoOnAccount(String customerRefNum) {
		return "select paymentprofileid, serviceaccountnumber, paymenttype, ccaccountnumber, bankaccountnumber, ccexp,\n"
				+ "    firstname, lastname, profilestatus, bankrouting, bankname, userid\n" + "from paymentprofiles\n"
				+ "where customerrefnum = '" + customerRefNum + "'";
	}

	public static String getUserInfo(String userName) {
		return "select ci.customerno, ci.accountnumber, ci.utilityaccountnumber, u.firstname, u.middlename, "
				+ "u.lastname, u.fullname, u.alternateemailID, u.mobilePhoneType, u.HomePhoneType,  u.mobilephone, "
				+ "u.homephone, u.status, u.utilityid, u.emailid, u.password, ci.address1, ci.address2, ci.addresstype, "
				+ "u.zipcode, u.cityname, u.statename, ci.country, ci.statecode, u.userid\n"
				+ "from customerinfo as ci\n"
				+ "left join useraccount as ua on ci.utilityaccountnumber = ua.utilityaccountnumber\n"
				+ "left join [user] as u on ua.userid = u.userid\n" + "where u.username = '" + userName
				+ "' and ua.isdefaultaccount = '1'";
	}

	public static String getPaymentProfilesLinkedToUser(String userId) {
		return "select userid, customerrefnum, profilestatus\n" + "from paymentprofiles\n" + "where userid = '" + userId
				+ "'\n" + "and profilestatus != 2\n" + "order by paymentprofileid desc";
	}

	/**
	 * MFA Related Queries
	 */

	/*
	 * This query returns Token_status from Two_Factor
	 */
	public static String getMfaToken_StatusQuery(String username) {
		String token_Status = "Select top 1 token_status from two_factor where User_Id = (Select UserID from [User] where username = '"
				+ username + "') Order By date_created DESC";
		return token_Status;
	}

	/*
	 * This query returns Two_Factor table data of a user on his username
	 */
	public static String getTwo_FactorTableQuery(String username) {
		String sQuery = "Select top 1 * from two_factor where User_Id = (Select UserID from [User] where username = '"
				+ username + "') Order By date_created DESC";
		return sQuery;
	}

	/*
	 * This query returns Two_Factor table data of a user on his username
	 */
	public static String getTwo_FactorTableQuery(String username, String two_factor_id) {
		String sQuery = "Select top 1 * from two_factor where two_factor_id = '" + two_factor_id
				+ "' and User_Id = (Select UserID from [User] where username = '" + username
				+ "') Order By date_created DESC";
		return sQuery;
	}

	/*
	 * This query returns token from Two_Factor
	 *
	 */
	public static String getTokenMFA(String uniqueKey, String dateUTC) {
		int year = 0, month = 0, date = 0, hour = 0;
		hour = Integer.parseInt(dateUTC.split(" ")[1].trim().split(":")[0]);
		year = Integer.parseInt(dateUTC.split(" ")[0].trim().split("-")[0]);
		month = Integer.parseInt(dateUTC.split(" ")[0].trim().split("-")[1]);
		date = Integer.parseInt(dateUTC.split(" ")[0].trim().split("-")[2]);
		String token = "select top 1 two_factor_id, token_status, token, user_id, client, date_created, date_updated\n"
				+ "from two_factor\n" + "where unique_key like '%" + uniqueKey + "%'\n"
				+ "and (DATEPART(yy, date_created) = " + year + "\n" + "and DATEPART(mm, date_created) = " + month
				+ "\n" + "and DATEPART(dd, date_created) = " + date + "\n" + "and DATEPART(hh, date_created) = " + hour
				+ ")\n" + "order by two_factor_id desc";
		return token;
	}

	public static String getMFATextMsgOTP(String pMobileNum) {
		String queryMFATextMsgOTP = "SELECT TOP 1 IsNotify, Text  FROM ContractAccountNotifyPriorityText where MobileNumber ='"
				+ pMobileNum + "' and Module = 'TwoFactorAuthentication' ORDER BY LastUpdated DESC";
		return queryMFATextMsgOTP;
	}

	// Download my Data
	public static String getAllEmailsSetInNotifycationPref(String username) {
		String sQuery = "select Distinct EmailORPhone from AccountNotificationDetail where AccountNotificationTypeID in (Select AccountNotificationTypeID \r\n"
				+ "from AccountNotificationType where AccountNotificationType like ('%Email%') ) AND UserID = (Select UserID from [User] where UserName ='"
				+ username + "') AND NotifyStatus = 1 And EmailORPhone<>''";
		return sQuery;
	}

	public static String getAllNumbersForTxtSetInNotifycationPref(String username) {
		String sQuery = "select Distinct EmailORPhone from AccountNotificationDetail where AccountNotificationTypeID in (Select AccountNotificationTypeID \r\n"
				+ "from AccountNotificationType where AccountNotificationType like ('%Text%') or  AccountNotificationType like ('%Push%') or AccountNotificationType like ('%Whatsapp%'))\r\n"
				+ "AND UserID = (Select UserID from [User] where UserName ='" + username
				+ "') AND NotifyStatus = 1 And EmailORPhone<>''";
		return sQuery;
	}

	public static String getAllNumbersForIVRSetInNotifycationPref(String username) {
		String sQuery = "select Distinct EmailORPhone from AccountNotificationDetail where AccountNotificationTypeID in (Select AccountNotificationTypeID \r\n"
				+ "from AccountNotificationType where AccountNotificationType like ('%IVR%') ) AND UserID = (Select UserID from [User] where UserName ='"
				+ username + "') AND NotifyStatus = 1 And EmailORPhone<>''";
		return sQuery;
	}

	/**
	 * This method is to return the query for updating user status in [User] table
	 *
	 * @param status   | 0 for Pending Activation |1 for Active | 2 for Inactive |3
	 *                 for Not Registered | 4 for Lock | 5 for TempLock
	 * @param userName
	 * @return
	 */
	public static String getQueryUpdateUserStatus(String status, String userName) {
		String sQuery = "Update [User] set Status = '" + status + "' where username = '" + userName + "'";
		return sQuery;
	}

	/**
	 * This method is to return the query for updating user isLock column value in
	 * [User] table
	 *
	 * @param isLock   | 0 for Not Locked |1 for Locked
	 * @param userName
	 * @return
	 */
	public static String getQueryUpdateUserIsLockedValue(String isLock, String userName) {
		String sQuery = "Update [User] set IsLocked = '" + isLock + "' where username = '" + userName + "'";
		return sQuery;
	}

	/**
	 * This method is to return the query for updating user isLock column value in
	 * [User] table
	 *
	 * @param loginAttempt | 0 for to make login attempt zero | <count> to set
	 *                     loginAttempt count
	 * @param userName
	 * @return
	 */
	public static String getQueryUpdateUserLoginAttemptValue(String loginAttempt, String userName) {
		String sQuery = "Update [User] set LoginAttempt = '" + loginAttempt + "' where username = '" + userName + "'";
		return sQuery;
	}

	// emailValue = "" => no email present in DB
	public static String getQueryUpdateUserPrimaryEmailAddress(String emailValue, String userName) {
		String sQuery = "Update [User] set EmailID = '" + emailValue + "' where username = '" + userName + "'";
		return sQuery;
	}

	// contactNumber = "" => No number present in DB
	public static String getQueryUpdateUserPrimaryContactNumber(String contactNumber, String userName) {
		String sQuery = "Update [User] set MobilePhone = '" + contactNumber + "' where username = '" + userName + "'";
		return sQuery;
	}

	// contactType => MobilePhoneType | 1= Landline, 2= Mobile, 3= Work, 4 =
	// Emergency|
	public static String getQueryUpdateUserPrimaryContactType(String contactType, String userName) {
		String sQuery = "Update [User] set MobilePhoneType = '" + contactType + "' where username = '" + userName + "'";
		return sQuery;
	}

	public static String getBillingHistoryDetails(String sUtilityAccountNumber) {
		String query = "declare @utilityaccountnumber varchar(30)='" + sUtilityAccountNumber + "';\r\n"
				+ "select * into #temp1 from (\r\n"
				+ "select z.billingid, z.statementdate,z.DueDate ,y.totalamountdue as PreviousBalance, y.PaymentReceived, z.currentcharges, z.TotalAmountDue\r\n"
				+ "from (\r\n"
				+ "select distinct top 24 bd1.value as Paymentreceived ,case when day(b.billingdate) <> 5 then dateadd(month, 0, CAST(CONVERT(VARCHAR(6),b.billingdate,112) +'05' AS DATE)) when day(b.billingdate) = 5 then cast(b.BillingDate as date) end as statementDate, bd.value as TotalAmountDue,bdd.value as DueDate,c.Value as PreviousBalance\r\n"
				+ ",d.value as currentcharges, b.billingID\r\n" + "from billing b\r\n"
				+ "inner join billingdetail bd on b.billingid=bd.billingid and bd.headid=22--\r\n"
				+ "inner join billingdetail bdd on bd.billingid=bdd.billingid and bdd.headid=25\r\n"
				+ "inner join billingdetail c on c.billingid=bdd.billingid and c.headid=20\r\n"
				+ "inner join billingdetail d on d.billingid=c.billingid and d.headid=19\r\n"
				+ "inner join billingdetail bd1 on b.billingid=bd1.billingid and bd1.headid=23--\r\n"
				+ "inner join account a on a.accountnumber=b.accountnumber and a.status=1\r\n"
				+ "where a.accountnumber in (Select distinct accountNumber from account where UtilityAccountNumber = @utilityaccountnumber)\r\n"
				+ "order by statementDate desc ) z\r\n" + "inner join (\r\n" + "select * from (\r\n"
				+ "select distinct top 25 '-'+bd1.value as Paymentreceived, bd.value as TotalAmountDue, dateadd(month, 1, CAST(CONVERT(VARCHAR(6),b.billingdate,112) +'05' AS DATE)) as statementDate\r\n"
				+ "from billing b\r\n"
				+ "inner join billingdetail bd on b.billingid=bd.billingid and bd.headid=22--\r\n"
				+ "inner join billingdetail bd1 on b.billingid=bd1.billingid and bd1.headid=23--\r\n"
				+ "inner join account a on a.accountnumber=b.accountnumber and a.status=1\r\n"
				+ "where a.accountnumber in (Select distinct accountNumber from account where UtilityAccountNumber = @utilityaccountnumber)\r\n"
				+ "order by statementDate desc\r\n" + ") x\r\n"
				+ "where x.statementDate <= dateadd(month, 0, CAST(CONVERT(VARCHAR(6),GETDATE(),112) +'05' AS DATE))\r\n"
				+ ") y\r\n" + "on y.statementdate = z.statementdate\r\n" + ")a\r\n" + "\r\n" + "\r\n" + "\r\n"
				+ "select * into #temp2 from (\r\n"
				+ "select z.billingid, z.statementdate,z.DueDate ,y.totalamountdue as PreviousBalance, y.PaymentReceived, z.currentcharges, z.TotalAmountDue\r\n"
				+ "from (\r\n"
				+ "select distinct top 24 bd1.value as Paymentreceived ,cast(b.BillingDate as date) as statementDate, bd.value as TotalAmountDue,bdd.value as DueDate,c.Value as PreviousBalance\r\n"
				+ ",d.value as currentcharges, b.billingID\r\n" + "from billing b\r\n"
				+ "inner join billingdetail bd on b.billingid=bd.billingid and bd.headid=22--\r\n"
				+ "inner join billingdetail bdd on bd.billingid=bdd.billingid and bdd.headid=25\r\n"
				+ "inner join billingdetail c on c.billingid=bdd.billingid and c.headid=20\r\n"
				+ "inner join billingdetail d on d.billingid=c.billingid and d.headid=19\r\n"
				+ "inner join billingdetail bd1 on b.billingid=bd1.billingid and bd1.headid=23--\r\n"
				+ "inner join account a on a.accountnumber=b.accountnumber and a.status=1\r\n"
				+ "where a.accountnumber in (Select distinct accountNumber from account where UtilityAccountNumber = @utilityaccountnumber)\r\n"
				+ "order by statementDate desc ) z\r\n" + "inner join (\r\n" + "select * from (\r\n"
				+ "select distinct top 25 '-'+bd1.value as Paymentreceived, bd.value as TotalAmountDue, CAST(CONVERT(VARCHAR(8),b.BillingDate,112) AS DATE) as statementDate\r\n"
				+ "from billing b\r\n"
				+ "inner join billingdetail bd on b.billingid=bd.billingid and bd.headid=22--\r\n"
				+ "inner join billingdetail bd1 on b.billingid=bd1.billingid and bd1.headid=23--\r\n"
				+ "inner join account a on a.accountnumber=b.accountnumber and a.status=1\r\n"
				+ "where a.accountnumber in (Select distinct accountNumber from account where UtilityAccountNumber = @utilityaccountnumber)\r\n"
				+ "order by statementDate desc\r\n" + ") x\r\n"
				+ "where x.statementDate <= dateadd(month, 0, CAST(CONVERT(VARCHAR(6),GETDATE(),112) +'05' AS DATE))\r\n"
				+ ") y\r\n" + "on y.statementdate = z.statementdate\r\n" + ")b\r\n" + "\r\n" + "\r\n" + "\r\n"
				+ "select a.billingid\r\n"
				+ ",case when a.statementdate <> b.statementdate then b.statementdate when a.statementdate = b.statementdate then a.statementdate end as StatementDate\r\n"
				+ ",a.DueDate\r\n" + ",a.PreviousBalance\r\n" + ",a.PaymentReceived\r\n" + ",a.currentcharges\r\n"
				+ ",a.TotalAmountDue\r\n" + "from #temp1 a\r\n" + "full outer join #temp2 b\r\n"
				+ "on a.billingid = b.billingid";
		return query;
	}

	public static String getBillingDetailsInfo(String accountNumber) {
		String query = "declare @utilityaccountnumber varchar(30)='" + accountNumber + "';\r\n"
				+ "select * into #temp1 from (\r\n"
				+ "select z.billingid, z.statementdate,z.DueDate ,y.totalamountdue as PreviousBalance, y.PaymentReceived, z.currentcharges, z.TotalAmountDue\r\n"
				+ "from (\r\n"
				+ "select distinct top 24 bd1.value as Paymentreceived ,case when day(b.billingdate) <> 5 then dateadd(month, 0, CAST(CONVERT(VARCHAR(6),b.billingdate,112) +'05' AS DATE)) when day(b.billingdate) = 5 then cast(b.BillingDate as date) end as statementDate, bd.value as TotalAmountDue,bdd.value as DueDate,c.Value as PreviousBalance\r\n"
				+ ",d.value as currentcharges, b.billingID\r\n" + "from billing b\r\n"
				+ "inner join billingdetail bd on b.billingid=bd.billingid and bd.headid=22--\r\n"
				+ "inner join billingdetail bdd on bd.billingid=bdd.billingid and bdd.headid=25\r\n"
				+ "inner join billingdetail c on c.billingid=bdd.billingid and c.headid=20\r\n"
				+ "inner join billingdetail d on d.billingid=c.billingid and d.headid=19\r\n"
				+ "inner join billingdetail bd1 on b.billingid=bd1.billingid and bd1.headid=23--\r\n"
				+ "inner join account a on a.accountnumber=b.accountnumber and a.status=1\r\n"
				+ "where a.accountnumber in (Select distinct accountNumber from account where UtilityAccountNumber = @utilityaccountnumber)\r\n"
				+ "order by statementDate desc ) z\r\n" + "inner join (\r\n" + "select * from (\r\n"
				+ "select distinct top 25 '-'+bd1.value as Paymentreceived, bd.value as TotalAmountDue, dateadd(month, 1, CAST(CONVERT(VARCHAR(6),b.billingdate,112) +'05' AS DATE)) as statementDate\r\n"
				+ "from billing b\r\n"
				+ "inner join billingdetail bd on b.billingid=bd.billingid and bd.headid=22--\r\n"
				+ "inner join billingdetail bd1 on b.billingid=bd1.billingid and bd1.headid=23--\r\n"
				+ "inner join account a on a.accountnumber=b.accountnumber and a.status=1\r\n"
				+ "where a.accountnumber in (Select distinct accountNumber from account where UtilityAccountNumber = @utilityaccountnumber)\r\n"
				+ "order by statementDate desc\r\n" + ") x\r\n"
				+ "where x.statementDate <= dateadd(month, 0, CAST(CONVERT(VARCHAR(6),GETDATE(),112) +'05' AS DATE))\r\n"
				+ ") y\r\n" + "on y.statementdate = z.statementdate\r\n" + ")a\r\n" + "\r\n" + "\r\n" + "\r\n"
				+ "select * into #temp2 from (\r\n"
				+ "select z.billingid, z.statementdate,z.DueDate ,y.totalamountdue as PreviousBalance, y.PaymentReceived, z.currentcharges, z.TotalAmountDue\r\n"
				+ "from (\r\n"
				+ "select distinct top 24 bd1.value as Paymentreceived ,cast(b.BillingDate as date) as statementDate, bd.value as TotalAmountDue,bdd.value as DueDate,c.Value as PreviousBalance\r\n"
				+ ",d.value as currentcharges, b.billingID\r\n" + "from billing b\r\n"
				+ "inner join billingdetail bd on b.billingid=bd.billingid and bd.headid=22--\r\n"
				+ "inner join billingdetail bdd on bd.billingid=bdd.billingid and bdd.headid=25\r\n"
				+ "inner join billingdetail c on c.billingid=bdd.billingid and c.headid=20\r\n"
				+ "inner join billingdetail d on d.billingid=c.billingid and d.headid=19\r\n"
				+ "inner join billingdetail bd1 on b.billingid=bd1.billingid and bd1.headid=23--\r\n"
				+ "inner join account a on a.accountnumber=b.accountnumber and a.status=1\r\n"
				+ "where a.accountnumber in (Select distinct accountNumber from account where UtilityAccountNumber = @utilityaccountnumber)\r\n"
				+ "order by statementDate desc ) z\r\n" + "inner join (\r\n" + "select * from (\r\n"
				+ "select distinct top 25 '-'+bd1.value as Paymentreceived, bd.value as TotalAmountDue, CAST(CONVERT(VARCHAR(8),b.BillingDate,112) AS DATE) as statementDate\r\n"
				+ "from billing b\r\n"
				+ "inner join billingdetail bd on b.billingid=bd.billingid and bd.headid=22--\r\n"
				+ "inner join billingdetail bd1 on b.billingid=bd1.billingid and bd1.headid=23--\r\n"
				+ "inner join account a on a.accountnumber=b.accountnumber and a.status=1\r\n"
				+ "where a.accountnumber in (Select distinct accountNumber from account where UtilityAccountNumber = @utilityaccountnumber)\r\n"
				+ "order by statementDate desc\r\n" + ") x\r\n"
				+ "where x.statementDate <= dateadd(month, 0, CAST(CONVERT(VARCHAR(6),GETDATE(),112) +'05' AS DATE))\r\n"
				+ ") y\r\n" + "on y.statementdate = z.statementdate\r\n" + ")b\r\n" + "\r\n" + "\r\n" + "\r\n"
				+ "select a.billingid\r\n"
				+ ",case when a.statementdate <> b.statementdate then b.statementdate when a.statementdate = b.statementdate then a.statementdate end as StatementDate\r\n"
				+ ",a.DueDate\r\n" + ",a.PreviousBalance\r\n" + ",a.PaymentReceived\r\n" + ",a.currentcharges\r\n"
				+ ",a.TotalAmountDue\r\n" + "from #temp1 a\r\n" + "full outer join #temp2 b\r\n"
				+ "on a.billingid = b.billingid";
		return query;
	}

	public static String getPaymentsHistoryDetails(String accountNumber) {
		String query = "declare @utilityaccountnumber varchar(30)='" + accountNumber + "' \r\n" + ";with cte as (\r\n"
				+ "select cast(bt.TransactionDate as date) as [Date]\r\n" + ",bt.TransactionDate as [Date1]\r\n"
				+ ", PaymentAttribute1 as PaymentType,PaymentAttribute4 as Channel,\r\n"
				+ "case when bt.isadjustment=0 then bt.TransactionAmount else\r\n"
				+ "sum(bt.WaterAmount+bt.SolidWasteAmount+bt.CO2Amount)\r\n"
				+ "end as Amount, case when bt.TransactionStatus=1 then 'Successful' else 'Failed' end as status , bt.BillingTransactionId\r\n"
				+ "from BillingTransaction bt\r\n"
				+ "inner join account a on a.accountnumber=bt.accountnumber and a.status=1\r\n"
				+ "where a.accountnumber in (Select distinct accountNumber from account where UtilityAccountNumber = @utilityaccountnumber) And bt.TransactionDate between dateadd(month,-24, getdate()) and getdate()\r\n"
				+ "group by bt.TransactionDate,bt.PaymentAttribute1,bt.PaymentAttribute4,bt.TransactionAmount,bt.isadjustment,bt.TransactionStatus, bt.BillingTransactionId\r\n"
				+ ")\r\n" + "select [Date],PaymentType,Channel\r\n" + ",cast(Amount as numeric(15,2)) as Amount\r\n"
				+ ",[status],BillingTransactionId from cte where Amount > 0\r\n" + "order by [Date1] desc";
		return query;
	}

	public static String getPrePaidOwnerAccountNumber(String username) {
		String query = "SELECT top 1 [UserAccount].UtilityAccountNumber FROM [UserAccount] Full OUTER JOIN [User] on [UserAccount].UserID=[User].UserID \r\n"
				+ "where [User].username='" + username
				+ "' and [UserAccount].RoleId='3' and [UserAccount].DefaultPaymentType = 0 \r\n" + "";
		return query;
	}

	public static String getPrePaidOwnerAccountNumber() {
		String query = "select top 1 * from useraccount where DefaultPaymentType ='0' and roleid='3';";
		return query;
	}

	public static String getPostpaidAccountNumber() {
		String query = "select top 1 * from useraccount where DefaultPaymentType ='1' and roleid='3'";
		return query;
	}

	public static String getBillTypeReceivedEmailMsg(String sSubjectLine, String sEmailAddress) {
		String sQuery = "SELECT TOP 1 Message, IsNotify\n" + "FROM ContractAccountNotifyEmail\n" + "WHERE EmailID = '"
				+ sEmailAddress + "' " + "AND Subject = '" + sSubjectLine + "'\n" + "ORDER BY ID DESC";
		return sQuery;
	}

	/*
	 * Get Residential account Registration data of a GIVEN particular Service
	 * ACCOUNT number for registration.
	 *
	 */
	public static String getRegData(String serviceAccountNumber, String addType, String accStatus) {
		String regDataQuery = "select top 1\n"
				+ "    ci.accountnumber, ci.utilityaccountnumber, ci.zipcode, ci.address1,\n"
				+ "    ci.accountstatusid, ci.addresstype, ci.firstname, ci.lastname, ci.mobilephone,\n"
				+ "    ci.drivinglicence, ci.accountstatus, ci.addresstype, ci.addressid, ci.customerid, \n"
				+ "    ca.homeinfostatus\n" + "from customerinfo as ci\n"
				+ "    left join customeraddress as ca on ci.addressid = ca.addressid\n"
				+ "where ci.accountstatusid = '" + accStatus + "'\n" + "    and ci.addresstype = '" + addType + "'\n"
				+ "    and ci.drivinglicence != 'NULL'\n" + "    and ci.portalaccesstype = 'Standard'\n"
				+ "    and ca.homeinfostatus = '0'\n" + "    and ci.utilityaccountnumber = '" + serviceAccountNumber
				+ "'\n" + "order by ci.createddate desc";
		return regDataQuery;
	}

	public static String getUserinfoDetails(String username) {
		String regDataQuery = "select EmailID,FullName from [user] where username = '" + username + "'";
		return regDataQuery;
	}

	public static String getAccountMeterMapping(String accountNumber) {
		String regDataQuery = "select * from accountmetermapping where accountnumber = " + accountNumber;
		return regDataQuery;
	}

	public static String getPrePaidBillingHistoryDetails(String sUtilityAccountNumber) {
		String query = "DECLARE @UTILITYACCOUNTNUMBER VARCHAR(30) = '" + sUtilityAccountNumber + "'\r\n"
				+ ";WITH CTE AS\r\n" + "(\r\n" + "SELECT DISTINCT--A.ACCOUNTNUMBER\r\n"
				+ "BH.BILLDATE AS [STATEMENT DATE]\r\n" + ",CAST(BH.BILLAMOUNT AS NUMERIC(15,2)) AS [BILL AMOUNT]\r\n"
				+ ",CAST(BH.FIXEDCHARGES AS NUMERIC(15,2)) AS [CURRENT CHARGES]\r\n"
				+ ",LEAD(BH.CURRENTREADING,1) OVER (ORDER BY BH.BILLDATE DESC) AS MYDIFFERENCE\r\n"
				+ ",CAST(BH.FIXEDCHARGES AS NUMERIC(15,2)) AS [FIXED CHARGES]\r\n"
				+ ",CAST(BH.METERRENT AS NUMERIC(15,2)) AS [METER RENT]\r\n"
				+ ",CAST(BH.CURRENTREADING AS INT) AS [CURRENT READING]\r\n"
				+ ",BD.VALUE AS [CREDIT/DEBIT (DEFERMENT)]\r\n"
				+ ",CAST(BH.BILLAMOUNT AS NUMERIC(15,2)) AS [TOTAL AMOUNT DUE]\r\n" + ",BH.BILLINGHISTORYID\r\n"
				+ "FROM ACCOUNT A WITH(NOLOCK)\r\n"
				+ "JOIN PREPAY_BILLINGHISTORY BH WITH(NOLOCK) ON A.ACCOUNTNUMBER = BH.ACCOUNTNUMBER\r\n"
				+ "JOIN PREPAY_BILLINGDETAIL BD WITH(NOLOCK) ON BH.BILLINGHISTORYID = BD.BILLHISTORYID AND HEADID = 44\r\n"
				+ "WHERE A.ACCOUNTNUMBER = (SELECT ACCOUNTNUMBER FROM ACCOUNT WHERE UTILITYACCOUNTNUMBER = @UTILITYACCOUNTNUMBER)\r\n"
				+ ")\r\n" + "SELECT [STATEMENT DATE]\r\n" + ",[BILL AMOUNT]\r\n" + ",[CURRENT CHARGES]\r\n"
				+ ",CAST((CASE WHEN MYDIFFERENCE IS NULL THEN [CURRENT READING] WHEN MYDIFFERENCE IS NOT NULL THEN [CURRENT READING] - MYDIFFERENCE END) AS INT)AS CONSUMPTION\r\n"
				+ ",[FIXED CHARGES]\r\n" + ",[METER RENT]\r\n" + ",[CURRENT READING]\r\n"
				+ ",[CREDIT/DEBIT (DEFERMENT)]\r\n" + ",BD.VALUE AS [ARREARS]\r\n" + ",[TOTAL AMOUNT DUE]\r\n"
				+ "FROM CTE\r\n"
				+ "JOIN PREPAY_BILLINGDETAIL BD WITH(NOLOCK) ON CTE.BILLINGHISTORYID = BD.BILLHISTORYID AND BD.HEADID = 45\r\n"
				+ "ORDER BY [STATEMENT DATE] DESC";
		return query;
	}

	public static String getPrePaidRechargeDetails(String sUtilityAccountNumber) {
		String query = "DECLARE @UTILITYACCOUNTNUMBER VARCHAR(30)= '" + sUtilityAccountNumber + "';\r\n"
				+ ";WITH CTE AS\r\n" + "(\r\n" + "SELECT DISTINCT BT.PAYMENTHISTORYID AS [TRANSACTION ID]\r\n"
				+ ",CAST(BT.RECHARGEDATE AS DATETIME) AS [TRANSACTION DATE 1]\r\n"
				+ ",CAST(BT.RECHARGEDATE AS DATE) AS [TRANSACTION DATE ]\r\n"
				+ ",CAST(BT.RECHARGEAMOUNT AS NUMERIC(15,2)) AS AMOUNT\r\n"
				+ ",CASE WHEN BT.PAYMENTMETHOD = 1 THEN 'CREDIT DEBIT CARD' WHEN BT.PAYMENTMETHOD= 2 THEN 'BANKACCOUNT' END AS PAYMENTMETHODTYPE\r\n"
				+ "FROM ACCOUNT A WITH(NOLOCK)\r\n"
				+ "JOIN PREPAY_PAYMENTHISTORY BT WITH(NOLOCK) ON A.ACCOUNTNUMBER = BT.ACCOUNTNUMBER\r\n"
				+ "WHERE A.DEFAULTPAYMENTTYPE = 0\r\n" + "AND BT.ACCOUNTNUMBER IS NOT NULL\r\n"
				+ "AND A.ACCOUNTNUMBER = (SELECT ACCOUNTNUMBER FROM ACCOUNT WHERE UTILITYACCOUNTNUMBER = @UTILITYACCOUNTNUMBER)\r\n"
				+ ")\r\n" + "SELECT [TRANSACTION ID]\r\n" + ",[TRANSACTION DATE]\r\n" + ",[AMOUNT]\r\n"
				+ ",[PAYMENTMETHODTYPE]\r\n" + "FROM CTE\r\n" + "ORDER BY [TRANSACTION DATE 1] DESC";
		return query;
	}

	public static String getDeleteProfileRequestDetails(String searchParamater) {
		String query = "DECLARE @SEARCHVALUE VARCHAR(100) = '" + searchParamater + "'\r\n" + "SELECT REQUESTID\r\n"
				+ ",DR.USERID\r\n" + ",U.FULLNAME\r\n" + ",U.USERNAME\r\n" + ",U.EMAILID\r\n"
				+ ",DR.CANCELLATIONREASON\r\n" + ",REQUESTDATE\r\n" + "FROM DELETIONREQUEST DR WITH(NOLOCK)\r\n"
				+ "INNER JOIN [USER] U WITH(NOLOCK) ON DR.USERID = U.USERID\r\n" + "WHERE DR.STATUS = 1\r\n"
				+ "AND (ISNULL(@SEARCHVALUE,'" + searchParamater + "')='" + searchParamater + "' \r\n"
				+ "OR (LOWER(U.FULLNAME) LIKE '%' + LOWER(@SEARCHVALUE) + '%') OR ((U.USERNAME) LIKE '%' + (@SEARCHVALUE) + '%') OR ((U.EMAILID) LIKE '%' + (@SEARCHVALUE) + '%'))\r\n"
				+ "ORDER BY REQUESTDATE DESC";
		return query;
	}

	public static String getValueByFieldNameServiceAccount(String accountNumber, String AddressType, String status,
			String customerNumber, String isPaperless, String linkedUsers, String zipCode, String cityName) {
		String q = "declare @utilityAccountNumber varchar(50)='" + accountNumber + "'"
				+ ", @CustomerNo	VARCHAR(100)  = '" + customerNumber + "'" + ", @UserID		VARCHAR(20)  = Null"
				+ ", @AccountType   VARCHAR(100) = '" + AddressType + "'" + ", @Status		VARCHAR(100) = '" + status
				+ "'" + ", @IsPaperLess   BIT			 = '" + isPaperless + "'" + ", @ZipCode		VARCHAR(100) = '"
				+ zipCode + "'" + ", @City			VARCHAR(100) = '" + cityName + "'"
				+ ", @FName			VARCHAR(100) = Null" + ", @LName			VARCHAR(50)  = NULL"
				+ ", @PhoneNo		VARCHAR(20)  = NULL" + ", @EmailID		VARCHAR(100) = NULL"
				+ ", @Locked		VARCHAR(100) = Null" + ", @FromRegDate	date		 = Null"
				+ ", @ToRegDate		date		 = NULL" + ", @SocialLogin	VARCHAR(10)  = NULL"
				+ ", @NoOfLinkedAcc VARCHAR(20)  = NULL" + ", @Role			VARCHAR(30)  = NULL"
				+ ", @IsAutoSuggest BIT			 = 0" + ", @SearchValue   VARCHAR(255) = NULL"
				+ ", @Customerid	VARCHAR(20)  = NULL" + ", @OrderColumn   VARCHAR(50)  = NULL"
				+ ", @SortOrder		VARCHAR(50)  = NULL" + ", @NoOfLinkedUser VARCHAR(20)  = '" + linkedUsers + "'"
				+ ", @UserType		VARCHAR(50)  = NULL" + " If(@status='Active')" + " Begin"
				+ " SELECT RN,[ServiceAccountnumber],[CustomerNumber],[CustomerName],[Customersemail],[PrimaryPhone],[AccountType]"
				+ ", [LinkedUsers],Premise,[AccountStatus],[AccountNumber],[AddressType],[LinkedUserGuest],[LinkedUserPM]"
				+ " from"
				+ " (SELECT row_number() over(ORDER BY cust.FullName desc) AS RN,cust.UtilityAccountNumber AS 'ServiceAccountnumber'"
				+ " , cust.customerNo AS 'CustomerNumber'"
				+ " , cust.FullName AS 'CustomerName',cust.EmailID AS 'Customersemail',cust.MobilePhone AS 'PrimaryPhone'"
				+ " , case when cust.PortalAccessTypeID=1 then cust.PortalAccessType else cust.AddressType End AS 'AccountType'"
				+ " , isnull(linkeduser.LinkedUser,0) as 'LinkedUsers'"
				+ " , replace(isnull(cust.address1,'')+','+isnull(cust.address2,'')+','+isnull(cust.cityname,'')+','+isnull(cust.StateCode,'')+','+isnull(cust.ZipCode,''),',,',',') as 'Premise'"
				+ ", case when cust.AccountStatusID in(0,1,3) then 'Active'  else 'Inactive' end 'AccountStatus'"
				+ ", cust.AccountNumber,cust.AddressType,isnull(linkedGuest.LinkedUserGuest,0) as LinkedUserGuest"
				+ ", isnull(linkedPM.LinkedUserPM,0) as LinkedUserPM" + " FROM customerinfo cust (NOLOCK) "
				+ " left JOIN " + " (SELECT AccountNumber,isnull(count(distinct userid),0) AS LinkedUser"
				+ " FROM useraccount (NOLOCK)" + " GROUP BY AccountNumber" + " Union All"
				+ " SELECT PA.accountNumber,isnull(count(distinct PU.Userid),0)  AS LinkedUser"
				+ " FROM PortfolioAccount PA WITH(NOLOCK) "
				+ " INNER JOIN  PortfolioUser PU WITH(NOLOCK) ON PA.GroupID=PU.GroupID AND PU.IsActive=1"
				+ " where PA.IsActive=1 GROUP BY PA.accountNumber"
				+ " )AS linkeduser ON cust.AccountNumber=linkeduser.AccountNumber"
				+ " LEFT JOIN (SELECT AccountNumber,isnull(count(distinct userid),0) AS LinkedUserGuest"
				+ " FROM useraccount (NOLOCK)" + " where Roleid=1 GROUP BY AccountNumber" + " Union All"
				+ " SELECT PA.accountNumber,isnull(count(distinct PU.Userid),0)  AS LinkedUserGuest"
				+ " FROM PortfolioAccount PA WITH(NOLOCK) "
				+ " INNER JOIN  PortfolioUser PU WITH(NOLOCK) ON PA.GroupID=PU.GroupID AND PU.IsActive=1"
				+ " where PA.IsActive=1 And PU.Roleid=11 GROUP BY PA.accountNumber"
				+ " )AS linkedGuest ON cust.AccountNumber=linkedGuest.AccountNumber"
				+ " LEFT JOIN (SELECT AccountNumber,isnull(count(distinct userid),0) AS LinkedUserPM"
				+ " FROM useraccount (NOLOCK)" + " where Roleid=2 GROUP BY AccountNumber" + " Union All"
				+ " SELECT PA.accountNumber,isnull(count(distinct PU.Userid),0)  AS LinkedUserPM"
				+ " FROM PortfolioAccount PA WITH(NOLOCK) "
				+ " INNER JOIN  PortfolioUser PU WITH(NOLOCK) ON PA.GroupID=PU.GroupID AND PU.IsActive=1"
				+ " where PA.IsActive=1 And PU.Roleid=6"
				+ " GROUP BY PA.accountNumber )AS linkedPM ON cust.AccountNumber=linkedPM.AccountNumber"
				+ " JOIN Account acc ON cust.AccountNumber=acc.AccountNumber WHERE CustomerID NOT IN (1,-1)"
				+ " and cust.[Accountstatusid] in (0,1,3)"
				+ " AND (isnull(@utilityAccountNumber,'') ='' or cust.UtilityAccountNumber=isnull(@utilityAccountNumber,''))"
				+ " AND (isnull(@CustomerNo,'') ='' or cust.customerNo=isnull(@CustomerNo,''))"
				+ " AND (isnull(@AccountType,'') ='' or (cust.PortalAccessTypeID=1 And cust.PortalAccessType=isnull(@AccountType,'')) or (cust.PortalAccessTypeID=0 And cust.AddressType=isnull(@AccountType,'')))"
				+ " AND (isnull(CAST(@IsPaperLess AS VARCHAR),'') = '' or isnull(Acc.Paperless,0)=CAST(@IsPaperLess AS VARCHAR))"
				+ "  AND (isnull(@ZipCode,'') ='' or cust.ZipCode=isnull(@ZipCode,''))"
				+ " AND (isnull(@City,'') ='' or cust.cityname=isnull(@City,''))"
				+ " AND (isnull(@NoOfLinkedUser,'') ='' or  (case when isnull(linkeduser.LinkedUser,0)=0 or linkeduser.LinkedUser<=1 then '0-1' "
				+ " when linkeduser.LinkedUser>1 and linkeduser.LinkedUser<=5 then '2-5'  when linkeduser.LinkedUser>5 and linkeduser.LinkedUser<=10 then '6-10'"
				+ "  when linkeduser.LinkedUser>10 then '10+' end) =isnull(@NoOfLinkedUser,'')) )  TBL" + "	End "
				+ " If(@status='Inactive')" + " Begin"
				+ " SELECT RN,[ServiceAccountnumber],[CustomerNumber],[CustomerName],[Customersemail],[PrimaryPhone],[AccountType]"
				+ ", [LinkedUsers],Premise,[AccountStatus],[AccountNumber],[AddressType],[LinkedUserGuest],[LinkedUserPM]"
				+ " from"
				+ " (SELECT row_number() over(ORDER BY cust.FullName desc) AS RN,cust.UtilityAccountNumber AS 'ServiceAccountnumber'"
				+ " , cust.customerNo AS 'CustomerNumber'"
				+ " , cust.FullName AS 'CustomerName',cust.EmailID AS 'Customersemail',cust.MobilePhone AS 'PrimaryPhone' "
				+ ", case when cust.PortalAccessTypeID=1 then cust.PortalAccessType else cust.AddressType End AS 'AccountType'"
				+ ", isnull(linkeduser.LinkedUser,0) as 'LinkedUsers'"
				+ ", replace(isnull(cust.address1,'')+','+isnull(cust.address2,'')+','+isnull(cust.cityname,'')+','+isnull(cust.StateCode,'')+','+isnull(cust.ZipCode,''),',,',',') as 'Premise'"
				+ ", case when cust.AccountStatusID in(0,1,3) then 'Active'  else 'Inactive' end 'AccountStatus'"
				+ ", cust.AccountNumber,cust.AddressType,isnull(linkedGuest.LinkedUserGuest,0) as LinkedUserGuest"
				+ ", isnull(linkedPM.LinkedUserPM,0) as LinkedUserPM" + " FROM customerinfo cust (NOLOCK) "
				+ " left JOIN " + " (SELECT AccountNumber,isnull(count(distinct userid),0) AS LinkedUser"
				+ " FROM useraccount (NOLOCK)" + " GROUP BY AccountNumber" + " Union All"
				+ " SELECT PA.accountNumber,isnull(count(distinct PU.Userid),0)  AS LinkedUser"
				+ " FROM PortfolioAccount PA WITH(NOLOCK) "
				+ " INNER JOIN  PortfolioUser PU WITH(NOLOCK) ON PA.GroupID=PU.GroupID AND PU.IsActive=1"
				+ " where PA.IsActive=1 GROUP BY PA.accountNumber"
				+ " )AS linkeduser ON cust.AccountNumber=linkeduser.AccountNumber"
				+ " LEFT JOIN (SELECT AccountNumber,isnull(count(distinct userid),0) AS LinkedUserGuest"
				+ " FROM useraccount (NOLOCK)" + " where Roleid=1 GROUP BY AccountNumber" + " Union All"
				+ " SELECT PA.accountNumber,isnull(count(distinct PU.Userid),0)  AS LinkedUserGuest"
				+ " FROM PortfolioAccount PA WITH(NOLOCK) "
				+ " INNER JOIN  PortfolioUser PU WITH(NOLOCK) ON PA.GroupID=PU.GroupID AND PU.IsActive=1"
				+ " where PA.IsActive=1 And PU.Roleid=11 GROUP BY PA.accountNumber"
				+ " )AS linkedGuest ON cust.AccountNumber=linkedGuest.AccountNumber"
				+ " LEFT JOIN (SELECT AccountNumber,isnull(count(distinct userid),0) AS LinkedUserPM"
				+ " FROM useraccount (NOLOCK)" + " where Roleid=2 GROUP BY AccountNumber" + " Union All"
				+ "  SELECT PA.accountNumber,isnull(count(distinct PU.Userid),0)  AS LinkedUserPM"
				+ " FROM PortfolioAccount PA WITH(NOLOCK) "
				+ " INNER JOIN  PortfolioUser PU WITH(NOLOCK) ON PA.GroupID=PU.GroupID AND PU.IsActive=1"
				+ " where PA.IsActive=1 And PU.Roleid=6"
				+ " GROUP BY PA.accountNumber )AS linkedPM ON cust.AccountNumber=linkedPM.AccountNumber"
				+ " JOIN Account acc ON cust.AccountNumber=acc.AccountNumber WHERE CustomerID NOT IN (1,-1)"
				+ " and cust.[Accountstatusid] in (2,4)"
				+ " AND (isnull(@utilityAccountNumber,'') ='' or cust.UtilityAccountNumber=isnull(@utilityAccountNumber,''))"
				+ " AND (isnull(@CustomerNo,'') ='' or cust.customerNo=isnull(@CustomerNo,''))"
				+ " AND (isnull(@AccountType,'') ='' or (cust.PortalAccessTypeID=1 And cust.PortalAccessType=isnull(@AccountType,'')) or (cust.PortalAccessTypeID=0 And cust.AddressType=isnull(@AccountType,'')))"
				+ " AND (isnull(CAST(@IsPaperLess AS VARCHAR),'') = '' or isnull(Acc.Paperless,0)=CAST(@IsPaperLess AS VARCHAR))"
				+ "  AND (isnull(@ZipCode,'') ='' or cust.ZipCode=isnull(@ZipCode,''))"
				+ " AND (isnull(@City,'') ='' or cust.cityname=isnull(@City,''))"
				+ " AND (isnull(@NoOfLinkedUser,'') ='' or  (case when isnull(linkeduser.LinkedUser,0)=0 or linkeduser.LinkedUser<=1 then '0-1' "
				+ "  when linkeduser.LinkedUser>1 and linkeduser.LinkedUser<=5 then '2-5'  when linkeduser.LinkedUser>5 and linkeduser.LinkedUser<=10 then '6-10'"
				+ "  when linkeduser.LinkedUser>10 then '10+' end) =isnull(@NoOfLinkedUser,'')) )  TBL" + " End"
				+ " Else"
				+ " SELECT RN,[ServiceAccountnumber],[CustomerNumber],[CustomerName],[Customersemail],[PrimaryPhone],[AccountType]"
				+ ", [LinkedUsers],Premise,[AccountStatus],[AccountNumber],[AddressType],[LinkedUserGuest],[LinkedUserPM]"
				+ " from"
				+ " (SELECT row_number() over(ORDER BY cust.FullName desc) AS RN,cust.UtilityAccountNumber AS 'ServiceAccountnumber'"
				+ ", cust.customerNo AS 'CustomerNumber'"
				+ ", cust.FullName AS 'CustomerName',cust.EmailID AS 'Customersemail',cust.MobilePhone AS 'PrimaryPhone' "
				+ " , case when cust.PortalAccessTypeID=1 then cust.PortalAccessType else cust.AddressType End AS 'AccountType'"
				+ ", isnull(linkeduser.LinkedUser,0) as 'LinkedUsers'"
				+ ", replace(isnull(cust.address1,'')+','+isnull(cust.address2,'')+','+isnull(cust.cityname,'')+','+isnull(cust.StateCode,'')+','+isnull(cust.ZipCode,''),',,',',') as 'Premise'"
				+ ", case when cust.AccountStatusID in(0,1,3) then 'Active'  else 'Inactive' end 'AccountStatus'"
				+ ", cust.AccountNumber,cust.AddressType,isnull(linkedGuest.LinkedUserGuest,0) as LinkedUserGuest"
				+ ", isnull(linkedPM.LinkedUserPM,0) as LinkedUserPM" + " FROM customerinfo cust (NOLOCK) "
				+ " left JOIN " + " (SELECT AccountNumber,isnull(count(distinct userid),0) AS LinkedUser"
				+ " FROM useraccount (NOLOCK)" + " GROUP BY AccountNumber" + " Union All"
				+ " SELECT PA.accountNumber,isnull(count(distinct PU.Userid),0)  AS LinkedUser"
				+ " FROM PortfolioAccount PA WITH(NOLOCK) "
				+ " INNER JOIN  PortfolioUser PU WITH(NOLOCK) ON PA.GroupID=PU.GroupID AND PU.IsActive=1"
				+ " where PA.IsActive=1 GROUP BY PA.accountNumber"
				+ " )AS linkeduser ON cust.AccountNumber=linkeduser.AccountNumber"
				+ " LEFT JOIN (SELECT AccountNumber,isnull(count(distinct userid),0) AS LinkedUserGuest"
				+ " FROM useraccount (NOLOCK)" + " where Roleid=1 GROUP BY AccountNumber" + " Union All"
				+ " SELECT PA.accountNumber,isnull(count(distinct PU.Userid),0)  AS LinkedUserGuest"
				+ " FROM PortfolioAccount PA WITH(NOLOCK) +"
				+ " INNER JOIN  PortfolioUser PU WITH(NOLOCK) ON PA.GroupID=PU.GroupID AND PU.IsActive=1"
				+ " where PA.IsActive=1 And PU.Roleid=11 GROUP BY PA.accountNumber"
				+ " )AS linkedGuest ON cust.AccountNumber=linkedGuest.AccountNumber"
				+ " LEFT JOIN (SELECT AccountNumber,isnull(count(distinct userid),0) AS LinkedUserPM"
				+ " FROM useraccount (NOLOCK)" + " where Roleid=2 GROUP BY AccountNumber" + " Union All"
				+ " SELECT PA.accountNumber,isnull(count(distinct PU.Userid),0)  AS LinkedUserPM"
				+ " FROM PortfolioAccount PA WITH(NOLOCK) "
				+ " INNER JOIN  PortfolioUser PU WITH(NOLOCK) ON PA.GroupID=PU.GroupID AND PU.IsActive=1"
				+ " where PA.IsActive=1 And PU.Roleid=6"
				+ " GROUP BY PA.accountNumber )AS linkedPM ON cust.AccountNumber=linkedPM.AccountNumber"
				+ " JOIN Account acc ON cust.AccountNumber=acc.AccountNumber WHERE CustomerID NOT IN (1,-1)"
				+ " and cust.[Accountstatusid] in (0,1,3,2,4)"
				+ " (isnull(@utilityAccountNumber,'') ='' or cust.UtilityAccountNumber=isnull(@utilityAccountNumber,''))"
				+ " AND (isnull(@CustomerNo,'') ='' or cust.customerNo=isnull(@CustomerNo,''))"
				+ " AND (isnull(@AccountType,'') ='' or (cust.PortalAccessTypeID=1 And cust.PortalAccessType=isnull(@AccountType,'')) or (cust.PortalAccessTypeID=0 And cust.AddressType=isnull(@AccountType,'')))"
				+ " AND (isnull(CAST(@IsPaperLess AS VARCHAR),'') = '' or isnull(Acc.Paperless,0)=CAST(@IsPaperLess AS VARCHAR))"
				+ " AND (isnull(@ZipCode,'') ='' or cust.ZipCode=isnull(@ZipCode,''))"
				+ " AND (isnull(@City,'') ='' or cust.cityname=isnull(@City,''))"
				+ " AND (isnull(@NoOfLinkedUser,'') ='' or  (case when isnull(linkeduser.LinkedUser,0)=0 or linkeduser.LinkedUser<=1 then '0-1' "
				+ "  when linkeduser.LinkedUser>1 and linkeduser.LinkedUser<=5 then '2-5'  when linkeduser.LinkedUser>5 and linkeduser.LinkedUser<=10 then '6-10'"
				+ "  when linkeduser.LinkedUser>10 then '10+' end) =isnull(@NoOfLinkedUser,'')) )  TBL";
		return q;
	}

	public static String getValueByFieldNameCustomer(String customerNumber, String zipCode, String accountNumber,
			String contactNo, String AddressType, String cityName, String firstName, String lastName, String email) {
		String q = "declare @utilityAccountNumber varchar(50)='" + accountNumber + "'"
				+ ", @CustomerNo	VARCHAR(100)  = '" + customerNumber + "'" + ", @UserID		VARCHAR(20)  = Null"
				+ ", @AccountType   VARCHAR(100) = '" + AddressType + "'" + ", @Status		VARCHAR(100) = Null"
				+ ", @IsPaperLess   BIT			 = Null" + ", @ZipCode		VARCHAR(100) = '" + zipCode + "'"
				+ ", @City			VARCHAR(100) = '" + cityName + "'" + ", @FName			VARCHAR(100) = '"
				+ firstName + "'" + ", @LName			VARCHAR(50)  = '" + lastName + "'"
				+ ", @PhoneNo		VARCHAR(20)  = '" + contactNo + "'" + ", @EmailID		VARCHAR(100) = '" + email
				+ "'" + ", @Locked		VARCHAR(100) = Null" + ", @FromRegDate	date		 = Null"
				+ ", @ToRegDate		date		 = NULL" + ", @SocialLogin	VARCHAR(10)  = NULL"
				+ ", @NoOfLinkedAcc VARCHAR(20)  = NULL" + ", @Role			VARCHAR(30)  = NULL"
				+ ", @IsAutoSuggest BIT			 = 0" + ", @SearchValue   VARCHAR(255) = NULL"
				+ ", @Customerid	VARCHAR(20)  = NULL" + ", @OrderColumn   VARCHAR(50)  = NULL"
				+ ", @SortOrder		VARCHAR(50)  = NULL" + ", @NoOfLinkedUser VARCHAR(20)  = NULL"
				+ ", @UserType		VARCHAR(50)  = NULL" + " IF (@Status IS NULL OR @Status='Active')" + " begin"
				+ " SELECT distinct RN,CustomerID,[CustomerNumber],[CustomerName],[Customersemail],[PrimaryPhone],[LinkedAccount],[MailingAddress],[CustomerStatus]"
				+ " from"
				+ " (SELECT row_number() over(ORDER BY FullName DESC) AS RN,cust.CustomerID,cust.customerNo AS 'CustomerNumber',"
				+ " cust.FullName AS 'CustomerName',cust.EmailID AS 'Customersemail',cust.MobilePhone AS 'PrimaryPhone' "
				+ ", isnull(acclinked.LinkedAccount,0) as 'LinkedAccount'"
				+ ", replace(replace(isnull(CCA.address1,'')+','+isnull(CCA.address2,'')+','+isnull(CCA.cityname,'')+','+isnull(CCA.StateCode,'')+','+isnull(CCA.ZipCode,''),',,',','),',,','') AS 'MailingAddress'"
				+ ", Cust.CustomerStatus	" + " From"
				+ " (SELECT DISTINCT A.customerid,A.customerNo,A.FirstName,A.LastName,A.FullName,A.EmailID,A.MobilePhone"
				+ ", case when b.customerid IS NOT NULL THEN 'Active' ELSE 'Inactive' END AS 'CustomerStatus'"
				+ " FROM customerinfo (NOLOCK) A"
				+ " left join customerinfo (NOLOCK) b on A.customerid=b.customerid and b.AccountStatusID IN (0,1,3)"
				+ " WHERE A.CustomerID NOT IN (1,-1)"
				+ " AND (isnull(@CustomerNo,'')  ='' or A.customerNo=isnull(@CustomerNo,''))"
				+ " AND (isnull(@FName,'')  ='' or A.FirstName like '%'+isnull(@FName,'')+'%')"
				+ " AND (isnull(@LName,'')  ='' or A.LastName like '%'+isnull(@LName,'')+'%')"
				+ " AND (isnull(@Status,'') ='' or (case when A.AccountStatusID in (0,1,3) then 'Active' end)=isnull(@Status,''))"
				+ " AND (isnull(@utilityAccountNumber,'') ='' or A.UtilityAccountNumber=isnull(@utilityAccountNumber,''))"
				+ " AND (isnull(@AccountType,'')  ='' " + " or (case when A.addresstypeid=1 then 'Residential' "
				+ " when A.addresstypeid=2 and A.PortalAccessTypeID=1 Then 'Enterprise' Else 'Commercial' end)=isnull(@AccountType,''))"
				+ " AND (isnull(@PhoneNo,'')  ='' or A.MobilePhone  like '%'+isnull(@PhoneNo,'')+'%')"
				+ " AND (isnull(@EmailId,'')  ='' or A.EmailID like '%'+isnull(@EmailId,'')+'%')"
				+ " AND (isnull(@ZipCode,'') ='' or A.ZipCode=isnull(@ZipCode,''))"
				+ " AND (isnull(@City,'')  ='' or A.cityname=isnull(@City,'')) " + " ) cust"
				+ " left JOIN customercommunicationaddress  CCA (NOLOCK) ON cust.CustomerID=CCA.CustomerID"
				+ " LEFT JOIN (SELECT CustomerID,isnull(count(distinct accountNumber),0) AS LinkedAcc "
				+ " FROM customerinfo (NOLOCK)" + " WHERE CustomerID NOT IN (1,-1) "
				+ " AND (isnull(@utilityAccountNumber,'') ='' or UtilityAccountNumber=isnull(@utilityAccountNumber,''))"
				+ " GROUP BY CustomerID" + " )AS linkedacc ON cust.CustomerID=linkedacc.CustomerID"
				+ " LEFT JOIN (SELECT CustomerID,isnull(count(distinct accountNumber),0) AS LinkedAccount "
				+ " FROM customerinfo (NOLOCK)" + " WHERE CustomerID NOT IN (1,-1) " + " GROUP BY CustomerID"
				+ " )AS acclinked ON cust.CustomerID=acclinked.CustomerID" + " ) TBL" + " END"
				+ " IF (@status='Inactive')" + " begin"
				+ " SELECT distinct RN,CustomerID,[CustomerNumber],[CustomerName],[Customersemail],[PrimaryPhone],[LinkedAccount],[MailingAddress],[CustomerStatus]"
				+ " from"
				+ " (SELECT row_number() over(ORDER BY FullName DESC) AS RN,cust.CustomerID,cust.customerNo AS 'CustomerNumber',"
				+ " cust.FullName AS 'CustomerName',cust.EmailID AS 'Customersemail',cust.MobilePhone AS 'PrimaryPhone' "
				+ ", isnull(acclinked.LinkedAccount,0) as 'LinkedAccount'"
				+ ", replace(replace(isnull(CCA.address1,'')+','+isnull(CCA.address2,'')+','+isnull(CCA.cityname,'')+','+isnull(CCA.StateCode,'')+','+isnull(CCA.ZipCode,''),',,',','),',,','') AS 'MailingAddress'"
				+ ", 'Inactive' as 'CustomerStatus'" + " From"
				+ " (SELECT DISTINCT CI.customerid,CI.customerNo,CI.FirstName,CI.LastName,CI.FullName,CI.EmailID,CI.MobilePhone"
				+ " FROM customerinfo CI (NOLOCK)  " + " Left Join" + " (SELECT DISTINCT customerid "
				+ " FROM CustomerInfo(NOLOCK) " + " WHERE CustomerID NOT IN (1,-1) AND AccountStatusID IN (0,1,3)"
				+ " )CIA on CI.customerid=CIA.customerid"
				+ " WHERE CI.CustomerID NOT IN (1,-1) AND CIA.customerid is null"
				+ " AND (isnull(@CustomerNo,'')  ='' or customerNo=isnull(@CustomerNo,''))"
				+ " AND (isnull(@FName,'')  ='' or FirstName like '%'+isnull(@FName,'')+'%')"
				+ " AND (isnull(@LName,'')  ='' or LastName like '%'+isnull(@LName,'')+'%')"
				+ " AND (isnull(@Status,'') ='' or (case when AccountStatusID not in (0,1,3) then 'Inactive' end)=isnull(@Status,''))"
				+ " AND (isnull(@utilityAccountNumber,'') ='' or UtilityAccountNumber=isnull(@utilityAccountNumber,''))"
				+ " AND (isnull(@AccountType,'')  ='' " + " or (case when CI.addresstypeid=1 then 'Residential' "
				+ " when CI.addresstypeid=2 and CI.PortalAccessTypeID=1 Then 'Enterprise' Else 'Commercial' end)=isnull(@AccountType,''))"
				+ "	AND (isnull(@PhoneNo,'')  ='' or MobilePhone like '%'+isnull(@PhoneNo,'')+'%')"
				+ "	AND (isnull(@EmailId,'')  ='' or EmailID like '%'+isnull(@EmailId,'')+'%')"
				+ "	AND (isnull(@ZipCode,'') ='' or ZipCode=isnull(@ZipCode,''))"
				+ "	AND (isnull(@City,'')  ='' or cityname=isnull(@City,'')) " + " ) cust "
				+ " LEFT JOIN customercommunicationaddress  CCA (NOLOCK) ON cust.CustomerID=CCA.CustomerID"
				+ " LEFT JOIN (SELECT CustomerID,isnull(count(distinct accountNumber),0) AS LinkedAcc"
				+ " FROM customerinfo (NOLOCK)" + " WHERE CustomerID NOT IN (1,-1) "
				+ " AND (isnull(@utilityAccountNumber,'') ='' or UtilityAccountNumber=isnull(@utilityAccountNumber,''))"
				+ " GROUP BY CustomerID" + " )AS linkedacc ON cust.CustomerID=linkedacc.CustomerID"
				+ " LEFT JOIN (SELECT CustomerID,isnull(count(distinct accountNumber),0) AS LinkedAccount"
				+ " FROM customerinfo (NOLOCK)" + " WHERE CustomerID NOT IN (1,-1) " + " GROUP BY CustomerID"
				+ " )AS acclinked ON cust.CustomerID=acclinked.CustomerID" + " ) TBL" + " End";
		return q;
	}

	public static String getValueByFieldNameUser(String accountNumber, String AddressType, String status,
			String userType, String linkedAccount, String role, String isSocial, String customerNumber,
			String contactNo, String zipCode, String cityName, String firstName, String lastName, String email) {
		String q = "declare @utilityAccountNumber varchar(50)='" + accountNumber + "'"
				+ ", @CustomerNo	VARCHAR(100)  = '" + customerNumber + "'" + ", @UserID		VARCHAR(20)  = Null"
				+ ", @AccountType   VARCHAR(100) = '" + AddressType + "'" + ", @StatusID		VARCHAR(100) = '"
				+ status + "'" + ", @IsPaperLess   BIT			 = Null" + ", @ZipCode		VARCHAR(100) = '" + zipCode
				+ "'" + ", @City			VARCHAR(100) = '" + cityName + "'" + ", @FName			VARCHAR(100) = '"
				+ firstName + "'" + ", @LName			VARCHAR(50)  = '" + lastName + "'"
				+ ", @PhoneNo		VARCHAR(20)  = '" + contactNo + "'" + ", @EmailID		VARCHAR(100) = '" + email
				+ "'" + ", @LockedID		VARCHAR(100) = Null" + ", @FromRegDate	date		 = Null"
				+ ", @ToRegDate		date		 = NULL" + ", @SocialLogin	VARCHAR(10)  = '" + isSocial + "'"
				+ ", @NoOfLinkedAcc VARCHAR(20)  = '" + linkedAccount + "'" + ", @Role			VARCHAR(30)  = '" + role
				+ "'" + ", @IsAutoSuggest BIT			 = 0" + ", @SearchValue   VARCHAR(255) = NULL"
				+ ", @Customerid	VARCHAR(20)  = NULL" + ", @OrderColumn   VARCHAR(50)  = NULL"
				+ ", @SortOrder		VARCHAR(50)  = NULL" + ", @NoOfLinkedUser VARCHAR(20)  = NULL"
				+ ", @UserType		VARCHAR(50)  = '" + userType + "'"
				+ " SELECT distinct RN,Userid,Fullname as Name,UserName,EmailID,MobilePhone as [PrimaryPhone],"
				+ " isnull(LinkedAcc,0) as [LinkedAccounts],[MailingAddress],[UserStatus],[LockStatus],[UserType]"
				+ " from" + " (select row_Number() over(ORDER BY  U.FullName DESC) AS RN,U.userid,U.Fullname"
				+ ", U.UserName,U.EmailID,U.MobilePhone ,accLinked.LinkedAcc,linkedacc.LinkedAccount"
				+ ", replace(replace(isnull(UCA.address1,'')+','+isnull(UCA.address2,'')+','+isnull(UCA.cityname,'')+','+isnull(UCA.StateCode,'')+','+isnull(UCA.ZipCode,''),',,',','),',,','') AS 'MailingAddress'"
				+ ", case when U.Status=0 then 'Pending Activation'"
				+ "  when U.[Status]=1 and De.Status is null then 'Active'"
				+ " when U.[Status]=2 or  De.[Status]=1 then 'Inactive'	" + "  when U.[Status]=4 then 'Locked' "
				+ "  when U.Status=5 then 'Temp Locked' end as 'UserStatus'"
				+ ", case when U.IsLocked=0 then 'Unlocked'" + " when U.IsLocked=1 then 'Locked'	"
				+ " when U.IsLocked=2 then 'Locked by Admin'	 end as 'LockStatus'"
				+ ", case when U.UserType =4 then 'Enterprise' else 'Mass Market' end as  'UserType'"
				+ " FROM [user] U (NOLOCK) " + " LEFT JOIN DeletionRequest De (NOLOCK)" + " On U.userid=De.Userid  "
				+ " and De.[Status]=1 and De.[CancelledDate] is null" + " JOIN "
				+ " (SELECT UA.userid,isnull(count(distinct UA.accountNumber),0) AS LinkedAccount " + " FROM "
				+ " (Select UserID,AccountNumber,RoleID from useraccount (NOLOCK)" + " Union all"
				+ "  Select PU.Userid,PA.Accountnumber,case when PU.RoleID=10 then 3 else PU.RoleID end as RoleID"
				+ " FROM PortfolioAccount PA WITH(NOLOCK) "
				+ " INNER JOIN  PortfolioUser PU WITH(NOLOCK) ON PA.GroupID=PU.GroupID " + " where PA.IsActive=1 "
				+ " )UA" + " JOIN Customerinfo CI ON UA.Accountnumber=CI.Accountnumber"
				+ " WHERE CI.CustomerID NOT IN (1,-1)" + " AND (isnull(@Role,'')='' or"
				+ " (case when UA.RoleID=1 then 'Guest' " + "	when UA.RoleID=2 then 'Property Manager'"
				+ "	when UA.RoleID=3 then 'Owner'" + "	when UA.RoleID=6 then 'Portfolio Manager'"
				+ "	when UA.RoleID=11 then 'Portfolio Guest' " + " end)=isnull(@Role,''))"
				+ " AND (isnull(@ZipCode,'') ='' or CI.ZipCode=isnull(@ZipCode,''))"
				+ " AND (isnull(@City,'') ='' or CI.cityname=isnull(@City,''))"
				+ " AND (isnull(@utilityAccountNumber,'') ='' or CI.UtilityAccountNumber=isnull(@utilityAccountNumber,''))"
				+ "  AND (isnull(@AccountType,'') ='' or (CI.PortalAccessTypeID=1 And CI.PortalAccessType=isnull(@AccountType,'')) or (CI.PortalAccessTypeID=0 And CI.AddressType=isnull(@AccountType,'')))"
				+ " GROUP BY UA.userid" + " )AS linkedacc ON U.userid=linkedacc.userid" + "	JOIN "
				+ " (SELECT userid,isnull(count(distinct accountNumber),0) AS LinkedAcc " + "  FROM "
				+ " (Select UserID,AccountNumber from useraccount (NOLOCK)" + " Union all"
				+ " Select PU.Userid,PA.Accountnumber" + " FROM PortfolioAccount PA WITH(NOLOCK) "
				+ " INNER JOIN  PortfolioUser PU WITH(NOLOCK) ON PA.GroupID=PU.GroupID  " + " where PA.IsActive=1"
				+ " )UA" + " GROUP BY userid" + " )AS accLinked ON U.userid=accLinked.userid"
				+ " LEFT JOIN usercommunicationaddress UCA ON U.userid=UCA.userid AND UCA.MailAddressType=1 "
				+ " where U.userid NOT IN (-1,1) AND U.UserType Not In(3,5) AND U.[STATUS]<>3   "
				+ " AND (isnull(@FName,'') ='' or isnull(U.FirstName,'') like '%'+isnull(@FName,'')+'%')"
				+ " AND (isnull(@LName,'') ='' or isnull(U.LastName,'') like '%'+isnull(@LName,'')+'%')"
				+ " AND (isnull(@StatusID,'') ='' or U.Status=isnull(@StatusID,'')) "
				+ " AND (isnull(@LockedID,'') ='' or U.IsLocked=isnull(@LockedID,'')) "
				+ " AND (isnull(@NoOfLinkedAcc,'') ='' or "
				+ " (case when accLinked.LinkedAcc=0 or accLinked.LinkedAcc<=1 then '0-1' "
				+ " when accLinked.LinkedAcc>1 and accLinked.LinkedAcc<=5 then '2-5'"
				+ " when accLinked.LinkedAcc>5 and accLinked.LinkedAcc<=10 then '6-10'"
				+ " when accLinked.LinkedAcc>10 then '10+' end) =isnull(@NoOfLinkedAcc,'')) "
				+ " AND (isnull(@PhoneNo,'') ='' or U.MobilePhone like '%'+isnull(@PhoneNo,'')+'%')"
				+ " AND (isnull(@EmailId,'') ='' or U.EmailID like '%'+isnull(@EmailId,'')+'%')"
				+ " AND (cast(U.CreatedDate as date) between isnull(@FromRegDate,'2014-01-01') and isnull(@ToRegDate,cast(getdate() as date))) "
				+ " AND (isnull(@SocialLogin,'') ='' or (case when U.loginmode=2 then 'Yes' else 'No' end) =isnull(@SocialLogin,''))"
				+ " AND (isnull(@UserType,'') ='' or (Case when U.UserType=4 then 'Enterprise' else 'Mass Market' end) = isnull(@UserType,'')) "
				+ " ) TBL";
		return q;
	}

	public static String getCountOfAdvSearchResultServiceAccount(String customerNumber, String zipCode,
			String accountNumber, String AccountStatus, String AddressType, String cityName, String isPaperless,
			String linkedUsers) {
		String q = "declare @utilityAccountNumber varchar(50)='" + accountNumber + "'" + ", @PageIndex		INT	= 1"
				+ ", @PageSize		INT	= 200" + ", @timeoffset	INT	= 0" + ", @CustomerNo	VARCHAR(100)  = '"
				+ customerNumber + "'" + ", @UserID		VARCHAR(20)  = Null" + ", @AccountType   VARCHAR(100) = '"
				+ AddressType + "'" + ", @Status		VARCHAR(100) = '" + AccountStatus + "'"
				+ ", @IsPaperLess   VARCHAR(100)			 = '" + isPaperless + "'"
				+ ", @ZipCode		VARCHAR(100) = '" + zipCode + "'" + ", @City			VARCHAR(100) = '" + cityName
				+ "'" + ", @FName			VARCHAR(100) = Null" + ", @LName			VARCHAR(50)  = NULL"
				+ ", @PhoneNo		VARCHAR(20)  = NULL" + ", @EmailID		VARCHAR(100) = NULL"
				+ ", @Locked		VARCHAR(100) = Null" + ", @FromRegDate	date		 = Null"
				+ ", @ToRegDate		date		 = NULL" + ", @SocialLogin	VARCHAR(10)  = NULL"
				+ ", @NoOfLinkedAcc VARCHAR(20)  = NULL" + ", @Role			VARCHAR(30)  = NULL"
				+ ", @IsAutoSuggest BIT			 = 0" + ", @SearchValue   VARCHAR(255) = NULL"
				+ ", @Customerid	VARCHAR(20)  = NULL" + ", @OrderColumn   VARCHAR(50)  = NULL"
				+ ", @SortOrder		VARCHAR(50)  = NULL" + ", @NoOfLinkedUser VARCHAR(20)  = '" + linkedUsers + "'"
				+ ", @UserType		VARCHAR(50)  = NULL" + " If(@status='Active')" + " Begin"
				+ " SELECT count(*) as 'TotalRecord'" + " from"
				+ " (SELECT row_number() over(ORDER BY cust.FullName desc) AS RN,cust.UtilityAccountNumber AS 'ServiceAccountnumber' ,cust.customerNo AS 'CustomerNumber'"
				+ ", cust.FullName AS 'CustomerName',cust.EmailID AS 'Customersemail',cust.MobilePhone AS 'PrimaryPhone'"
				+ ", case when cust.PortalAccessTypeID=1 then cust.PortalAccessType else cust.AddressType End AS 'AccountType'"
				+ ", linkeduser.LinkedUser as 'LinkedUsers'"
				+ ", replace(isnull(cust.address1,'')+','+isnull(cust.address2,'')+','+isnull(cust.cityname,'')+','+isnull(cust.StateCode,'')+','+isnull(cust.ZipCode,''),',,',',') as 'Premise'"
				+ ", case 	when cust.AccountStatusID in(0,1,3) then 'Active' else 'Inactive' end 'AccountStatus'"
				+ ", cust.AccountNumber,cust.AddressType,isnull(linkedGuest.LinkedUserGuest,0) as LinkedUserGuest ,isnull(linkedPM.LinkedUserPM,0) as LinkedUserPM"
				+ " FROM customerinfo cust (NOLOCK)  left JOIN  (SELECT AccountNumber,isnull(count(distinct userid),0) AS LinkedUser"
				+ " FROM useraccount (NOLOCK) GROUP BY AccountNumber" + " Union All"
				+ " SELECT PA.accountNumber,isnull(count(distinct PU.Userid),0)  " + " AS LinkedUser"
				+ " FROM PortfolioAccount PA WITH(NOLOCK) "
				+ " INNER JOIN  PortfolioUser PU WITH(NOLOCK) ON PA.GroupID=PU.GroupID AND PU.IsActive=1 where PA.IsActive=1"
				+ " GROUP BY PA.accountNumber" + " )AS linkeduser ON cust.AccountNumber=linkeduser.AccountNumber"
				+ " LEFT JOIN (SELECT AccountNumber, isnull(count(distinct userid),0) AS LinkedUserGuest"
				+ " FROM useraccount (NOLOCK) where Roleid=1 GROUP BY AccountNumber" + " Union All"
				+ " SELECT PA.accountNumber,isnull(count(distinct PU.Userid),0)  AS LinkedUserGuest"
				+ " FROM PortfolioAccount PA WITH(NOLOCK) "
				+ " INNER JOIN  PortfolioUser PU WITH(NOLOCK) ON PA.GroupID=PU.GroupID AND PU.IsActive=1"
				+ " where PA.IsActive=1  GROUP BY PA.accountNumber"
				+ " )AS linkedGuest ON cust.AccountNumber=linkedGuest.AccountNumber"
				+ " LEFT JOIN (SELECT AccountNumber,isnull(count(distinct userid),0)  AS LinkedUserPM"
				+ " FROM useraccount (NOLOCK)" + " where Roleid=2 GROUP BY AccountNumber" + " Union All"
				+ " SELECT PA.accountNumber, isnull(count(distinct PU.Userid),0)  AS LinkedUserPM"
				+ " FROM PortfolioAccount PA WITH(NOLOCK) "
				+ " INNER JOIN  PortfolioUser PU WITH(NOLOCK) ON PA.GroupID=PU.GroupID AND PU.IsActive=1"
				+ " where PA.IsActive=1  GROUP BY PA.accountNumber"
				+ " )AS linkedPM ON cust.AccountNumber=linkedPM.AccountNumber"
				+ " JOIN Account acc ON cust.AccountNumber=acc.AccountNumber"
				+ " WHERE CustomerID NOT IN (1,-1) AND (isnull(@utilityAccountNumber,'') ='' or cust.UtilityAccountNumber=isnull(@utilityAccountNumber,''))"
				+ " and cust.[Accountstatusid] in (0,1,3)"
				+ " AND (isnull(@CustomerNo,'') ='' or cust.customerNo=isnull(@CustomerNo,'')) "
				+ " AND (isnull(@AccountType,'') ='' or (cust.PortalAccessTypeID=1 And cust.PortalAccessType=isnull(@AccountType,''))"
				+ " or (cust.PortalAccessTypeID=0 And cust.AddressType=isnull(@AccountType,'')))"
				+ " AND (isnull(CAST(@IsPaperLess AS VARCHAR),'') = '' or isnull(Acc.Paperless,0)=CAST(@IsPaperLess AS VARCHAR))"
				+ " AND (isnull(@ZipCode,'') ='' or cust.ZipCode=isnull(@ZipCode, '')) AND (isnull(@City,'') ='' or cust.cityname=isnull(@City,'')) AND (isnull(@NoOfLinkedUser,'') ='' or (case when isnull(linkeduser.LinkedUser,0)=0 or "
				+ " linkeduser.LinkedUser<=1 then '0-1' when linkeduser.LinkedUser>1 and linkeduser.LinkedUser<=5 then '2-5' when linkeduser.LinkedUser>5 and linkeduser.LinkedUser<=10 then '6-10' when linkeduser.LinkedUser>10 then '10+' end) =isnull(@NoOfLinkedUser,''))"
				+ " ) TBL" + " End" + " if(@status='Inactive')" + " Begin" + " SELECT count(*) as 'TotalRecord'"
				+ " from"
				+ " (SELECT row_number() over(ORDER BY cust.FullName desc) AS RN,cust.UtilityAccountNumber AS 'ServiceAccountnumber' ,cust.customerNo AS 'CustomerNumber'"
				+ ", cust.FullName AS 'CustomerName',cust.EmailID AS 'Customersemail',cust.MobilePhone AS 'PrimaryPhone' "
				+ ", case when cust.PortalAccessTypeID=1 then cust.PortalAccessType else cust.AddressType End AS 'AccountType'"
				+ ", linkeduser.LinkedUser as 'LinkedUsers'"
				+ ", replace(isnull(cust.address1,'')+','+isnull(cust.address2,'')+','+isnull(cust.cityname,'')+','+isnull(cust.StateCode,'')+','+isnull(cust.ZipCode,''),',,',',') as 'Premise'"
				+ ", case 	when cust.AccountStatusID in(0,1,3) then 'Active' else 'Inactive' end 'AccountStatus'"
				+ ", cust.AccountNumber,cust.AddressType,isnull(linkedGuest.LinkedUserGuest,0) as LinkedUserGuest ,isnull(linkedPM.LinkedUserPM,0) as LinkedUserPM"
				+ " FROM customerinfo cust (NOLOCK)  left JOIN  (SELECT AccountNumber,isnull(count(distinct userid),0) AS LinkedUser"
				+ " FROM useraccount (NOLOCK) GROUP BY AccountNumber" + " Union All"
				+ " SELECT PA.accountNumber,isnull(count(distinct PU.Userid),0)  " + " AS LinkedUser"
				+ " FROM PortfolioAccount PA WITH(NOLOCK) "
				+ " INNER JOIN  PortfolioUser PU WITH(NOLOCK) ON PA.GroupID=PU.GroupID AND PU.IsActive=1 where PA.IsActive=1"
				+ " GROUP BY PA.accountNumber" + " )AS linkeduser ON cust.AccountNumber=linkeduser.AccountNumber"
				+ " LEFT JOIN (SELECT AccountNumber, isnull(count(distinct userid),0) AS LinkedUserGuest"
				+ " FROM useraccount (NOLOCK) where Roleid=1 GROUP BY AccountNumber" + " Union All"
				+ " SELECT PA.accountNumber,isnull(count(distinct PU.Userid),0)  AS LinkedUserGuest"
				+ " FROM PortfolioAccount PA WITH(NOLOCK) "
				+ " INNER JOIN  PortfolioUser PU WITH(NOLOCK) ON PA.GroupID=PU.GroupID AND PU.IsActive=1"
				+ " where PA.IsActive=1  GROUP BY PA.accountNumber"
				+ " )AS linkedGuest ON cust.AccountNumber=linkedGuest.AccountNumber"
				+ " LEFT JOIN (SELECT AccountNumber,isnull(count(distinct userid),0)  AS LinkedUserPM"
				+ " FROM useraccount (NOLOCK)" + " where Roleid=2 GROUP BY AccountNumber" + " Union All"
				+ " SELECT PA.accountNumber, isnull(count(distinct PU.Userid),0)  AS LinkedUserPM"
				+ " FROM PortfolioAccount PA WITH(NOLOCK) "
				+ " INNER JOIN  PortfolioUser PU WITH(NOLOCK) ON PA.GroupID=PU.GroupID AND PU.IsActive=1"
				+ " where PA.IsActive=1  GROUP BY PA.accountNumber"
				+ " )AS linkedPM ON cust.AccountNumber=linkedPM.AccountNumber"
				+ " JOIN Account acc ON cust.AccountNumber=acc.AccountNumber"
				+ " WHERE CustomerID NOT IN (1,-1) AND (isnull(@utilityAccountNumber,'') ='' or cust.UtilityAccountNumber=isnull(@utilityAccountNumber,''))"
				+ " and cust.[Accountstatusid] in (2,4)"
				+ " AND (isnull(@CustomerNo,'') ='' or cust.customerNo=isnull(@CustomerNo,'')) "
				+ " AND (isnull(@AccountType,'') ='' or (cust.PortalAccessTypeID=1 And cust.PortalAccessType=isnull(@AccountType,''))"
				+ " or (cust.PortalAccessTypeID=0 And cust.AddressType=isnull(@AccountType,'')))"
				+ " AND (isnull(CAST(@IsPaperLess AS VARCHAR),'') = '' or isnull(Acc.Paperless,0)=CAST(@IsPaperLess AS VARCHAR))"
				+ " AND (isnull(@ZipCode,'') ='' or cust.ZipCode=isnull(@ZipCode, '')) AND (isnull(@City,'') ='' or cust.cityname=isnull(@City,'')) AND (isnull(@NoOfLinkedUser,'') ='' or (case when isnull(linkeduser.LinkedUser,0)=0 or "
				+ " linkeduser.LinkedUser<=1 then '0-1' when linkeduser.LinkedUser>1 and linkeduser.LinkedUser<=5 then '2-5' when linkeduser.LinkedUser>5 and linkeduser.LinkedUser<=10 then '6-10' when linkeduser.LinkedUser>10 then '10+' end) =isnull(@NoOfLinkedUser,''))"
				+ " ) TBL" + " End" + " if(@status='')" + " Begin" + " SELECT count(*) as 'TotalRecord'" + " from"
				+ " (SELECT row_number() over(ORDER BY cust.FullName desc) AS RN,cust.UtilityAccountNumber AS 'ServiceAccountnumber' ,cust.customerNo AS 'CustomerNumber'"
				+ ", cust.FullName AS 'CustomerName',cust.EmailID AS 'Customersemail',cust.MobilePhone AS 'PrimaryPhone' "
				+ ", case when cust.PortalAccessTypeID=1 then cust.PortalAccessType else cust.AddressType End AS 'AccountType'"
				+ ", linkeduser.LinkedUser as 'LinkedUsers'"
				+ ", replace(isnull(cust.address1,'')+','+isnull(cust.address2,'')+','+isnull(cust.cityname,'')+','+isnull(cust.StateCode,'')+','+isnull(cust.ZipCode,''),',,',',') as 'Premise'"
				+ ", case 	when cust.AccountStatusID in(0,1,3) then 'Active' else 'Inactive' end 'AccountStatus'"
				+ ", cust.AccountNumber,cust.AddressType,isnull(linkedGuest.LinkedUserGuest,0) as LinkedUserGuest ,isnull(linkedPM.LinkedUserPM,0) as LinkedUserPM"
				+ " FROM customerinfo cust (NOLOCK)  left JOIN  (SELECT AccountNumber,isnull(count(distinct userid),0) AS LinkedUser"
				+ " FROM useraccount (NOLOCK) GROUP BY AccountNumber" + " Union All"
				+ " SELECT PA.accountNumber,isnull(count(distinct PU.Userid),0)  " + " AS LinkedUser"
				+ " FROM PortfolioAccount PA WITH(NOLOCK) "
				+ " INNER JOIN  PortfolioUser PU WITH(NOLOCK) ON PA.GroupID=PU.GroupID AND PU.IsActive=1 where PA.IsActive=1"
				+ " GROUP BY PA.accountNumber" + " )AS linkeduser ON cust.AccountNumber=linkeduser.AccountNumber"
				+ " LEFT JOIN (SELECT AccountNumber, isnull(count(distinct userid),0) AS LinkedUserGuest"
				+ " FROM useraccount (NOLOCK) where Roleid=1 GROUP BY AccountNumber" + " Union All"
				+ " SELECT PA.accountNumber,isnull(count(distinct PU.Userid),0)  AS LinkedUserGuest"
				+ " FROM PortfolioAccount PA WITH(NOLOCK) "
				+ " INNER JOIN  PortfolioUser PU WITH(NOLOCK) ON PA.GroupID=PU.GroupID AND PU.IsActive=1"
				+ " where PA.IsActive=1  GROUP BY PA.accountNumber"
				+ " )AS linkedGuest ON cust.AccountNumber=linkedGuest.AccountNumber"
				+ " LEFT JOIN (SELECT AccountNumber,isnull(count(distinct userid),0)  AS LinkedUserPM"
				+ " FROM useraccount (NOLOCK)" + " where Roleid=2 GROUP BY AccountNumber" + " Union All"
				+ " SELECT PA.accountNumber, isnull(count(distinct PU.Userid),0)  AS LinkedUserPM"
				+ " FROM PortfolioAccount PA WITH(NOLOCK) "
				+ " INNER JOIN  PortfolioUser PU WITH(NOLOCK) ON PA.GroupID=PU.GroupID AND PU.IsActive=1"
				+ " where PA.IsActive=1  GROUP BY PA.accountNumber"
				+ " )AS linkedPM ON cust.AccountNumber=linkedPM.AccountNumber"
				+ " JOIN Account acc ON cust.AccountNumber=acc.AccountNumber"
				+ " WHERE CustomerID NOT IN (1,-1) AND (isnull(@utilityAccountNumber,'') ='' or cust.UtilityAccountNumber=isnull(@utilityAccountNumber,''))"
				+ " and cust.[Accountstatusid] in (0,1,2,3,4)"
				+ " AND (isnull(@CustomerNo,'') ='' or cust.customerNo=isnull(@CustomerNo,'')) "
				+ " AND (isnull(@AccountType,'') ='' or (cust.PortalAccessTypeID=1 And cust.PortalAccessType=isnull(@AccountType,''))"
				+ " or (cust.PortalAccessTypeID=0 And cust.AddressType=isnull(@AccountType,'')))"
				+ " AND (isnull(CAST(@IsPaperLess AS VARCHAR),'') = '' or isnull(Acc.Paperless,0)=CAST(@IsPaperLess AS VARCHAR))"
				+ " AND (isnull(@ZipCode,'') ='' or cust.ZipCode=isnull(@ZipCode, '')) AND (isnull(@City,'') ='' or cust.cityname=isnull(@City,'')) AND (isnull(@NoOfLinkedUser,'') ='' or (case when isnull(linkeduser.LinkedUser,0)=0 or "
				+ " linkeduser.LinkedUser<=1 then '0-1' when linkeduser.LinkedUser>1 and linkeduser.LinkedUser<=5 then '2-5' when linkeduser.LinkedUser>5 and linkeduser.LinkedUser<=10 then '6-10' when linkeduser.LinkedUser>10 then '10+' end) =isnull(@NoOfLinkedUser,''))"
				+ " ) TBL" + " End";
		return q;
	}

	public static String getCountOfAdvSearchResultCustomer(String customerNumber, String zipCode, String accountNumber,
			String contactNo, String AddressType, String cityName, String firstName, String lastName, String email) {
		String q = "declare @utilityAccountNumber varchar(50)='" + accountNumber + "'"
				+ ", @CustomerNo	VARCHAR(100)  = '" + customerNumber + "'" + ", @UserID		VARCHAR(20)  = Null"
				+ ", @AccountType   VARCHAR(100) = '" + AddressType + "'" + ", @Status		VARCHAR(100) = Null"
				+ ", @IsPaperLess   BIT			 = Null" + ", @ZipCode		VARCHAR(100) = '" + zipCode + "'"
				+ ", @City			VARCHAR(100) = '" + cityName + "'" + ", @FName			VARCHAR(100) = '"
				+ firstName + "'" + ", @LName			VARCHAR(50)  = '" + lastName + "'"
				+ ", @PhoneNo		VARCHAR(20)  = '" + contactNo + "'" + ", @EmailID		VARCHAR(100) = '" + email
				+ "'" + ", @Locked		VARCHAR(100) = Null" + ", @FromRegDate	date		 = Null"
				+ ", @ToRegDate		date		 = NULL" + ", @SocialLogin	VARCHAR(10)  = NULL"
				+ ", @NoOfLinkedAcc VARCHAR(20)  = NULL" + ", @Role			VARCHAR(30)  = NULL"
				+ ", @IsAutoSuggest BIT			 = 0" + ", @SearchValue   VARCHAR(255) = NULL"
				+ ", @Customerid	VARCHAR(20)  = NULL" + ", @OrderColumn   VARCHAR(50)  = NULL"
				+ ", @SortOrder		VARCHAR(50)  = NULL" + ", @NoOfLinkedUser VARCHAR(20)  = NULL"
				+ ", @UserType		VARCHAR(50)  = NULL" + " SELECT count(distinct CustomerID) as 'TotalRecord'"
				+ " from"
				+ " (SELECT row_number() over(ORDER BY FullName desc) AS RN,cust.CustomerID,cust.customerNo AS 'CustomerNumber',"
				+ " cust.FullName AS 'CustomerName',cust.EmailID AS 'Customersemail',cust.MobilePhone AS 'PrimaryPhone' "
				+ ", isnull(linkedacc.LinkedAccount,0) as 'LinkedAccount'"
				+ ", replace(replace(isnull(CCA.address1,'')+','+isnull(CCA.address2,'')+','+isnull(CCA.cityname,'')+','+isnull(CCA.StateCode,'')+','+isnull(CCA.ZipCode,''),',,',','),',,','') AS 'MailingAddress'"
				+ " From"
				+ " (SELECT DISTINCT CI.customerid,CI.customerNo,CI.FirstName,CI.LastName,CI.FullName,CI.EmailID,CI.MobilePhone"
				+ " FROM customerinfo CI (NOLOCK) " + " Left Join" + " (SELECT DISTINCT customerid "
				+ "	FROM CustomerInfo(NOLOCK) " + "	WHERE CustomerID NOT IN (1,-1) AND AccountStatusID IN (0,1,3)"
				+ " )CIA on CI.customerid=CIA.customerid" + " WHERE CI.CustomerID NOT IN (1,-1) "
				+ " AND (isnull(@CustomerNo,'')  ='' or customerNo=isnull(@CustomerNo,''))"
				+ " AND (isnull(@FName,'')  ='' or FirstName like '%'+isnull(@FName,'')+'%')"
				+ " AND (isnull(@LName,'')  ='' or LastName like '%'+isnull(@LName,'')+'%')"
				+ " AND (isnull(@Status,'') ='' or (case when AccountStatusID not in (0,1,3) then 'Inactive' end)=isnull(@Status,''))"
				+ " AND (isnull(@utilityAccountNumber,'') ='' or UtilityAccountNumber=isnull(@utilityAccountNumber,''))"
				+ " AND (isnull(@AccountType,'')  ='' " + " or (case when CI.addresstypeid=1 then 'Residential' "
				+ " when CI.addresstypeid=2 and CI.PortalAccessTypeID=1 Then 'Enterprise' Else 'Commercial' end)=isnull(@AccountType,''))"
				+ "	AND (isnull(@PhoneNo,'')  ='' or MobilePhone like '%'+isnull(@PhoneNo,'')+'%')"
				+ "	AND (isnull(@EmailId,'')  ='' or EmailID like '%'+isnull(@EmailId,'')+'%')"
				+ "	AND (isnull(@ZipCode,'') ='' or ZipCode=isnull(@ZipCode,''))"
				+ "	AND (isnull(@City,'')  ='' or cityname=isnull(@City,'')) " + " ) cust "
				+ " left JOIN customercommunicationaddress  CCA (NOLOCK) ON cust.CustomerID=CCA.CustomerID"
				+ " LEFT JOIN (SELECT CustomerID,isnull(count(distinct accountNumber),0) AS LinkedAccount "
				+ " FROM customerinfo (NOLOCK)"
				+ " WHERE CustomerID NOT IN (1,-1) AND (isnull(@utilityAccountNumber,'') ='' or UtilityAccountNumber=isnull(@utilityAccountNumber,''))"
				+ " GROUP BY CustomerID" + " )AS linkedacc ON cust.CustomerID=linkedacc.CustomerID" + " ) TBL";
		return q;
	}

	public static String getCountOfAdvSearchResultUser(String accountNumber, String AddressType, String status,
			String userType, String linkedAccount, String role, String isSocial, String customerNumber,
			String contactNo, String zipCode, String cityName, String firstName, String lastName, String email) {
		String q = " declare @utilityAccountNumber varchar(50)='" + accountNumber + "'" + ", @PageIndex		INT	= 1"
				+ ", @PageSize		INT	= 200" + ", @timeoffset	INT	= 0" + ", @CustomerNo	VARCHAR(100)  = '"
				+ customerNumber + "'" + ", @UserID		VARCHAR(20)  = Null" + ", @AccountType   VARCHAR(100) = '"
				+ AddressType + "'" + ", @Status		VARCHAR(100)  = '" + status + "'"
				+ ", @IsPaperLess   BIT			 = Null" + ", @ZipCode		VARCHAR(100) = '" + zipCode + "'"
				+ ", @City			VARCHAR(100) = '" + cityName + "'" + ", @FName			VARCHAR(100) = '"
				+ firstName + "'" + ", @LName			VARCHAR(50)  = '" + lastName + "'"
				+ ", @PhoneNo		VARCHAR(20)  = '" + contactNo + "'" + ", @EmailID		VARCHAR(100) = '" + email
				+ "'" + ", @Locked		VARCHAR(100) = Null" + ", @FromRegDate	date		 = Null"
				+ ", @ToRegDate		date		 = NULL" + ", @SocialLogin	VARCHAR(10)  = '" + isSocial + "'"
				+ ", @NoOfLinkedAcc VARCHAR(20)  = '" + linkedAccount + "'" + ", @Role			VARCHAR(30)  = '" + role
				+ "'" + ", @IsAutoSuggest BIT			 = 0" + ", @SearchValue   VARCHAR(255) = NULL"
				+ ", @Customerid	VARCHAR(20)  = NULL" + ", @OrderColumn   VARCHAR(50)  = NULL"
				+ ", @SortOrder		VARCHAR(50)  = NULL" + ", @NoOfLinkedUser VARCHAR(20)  = Null"
				+ ", @UserType		VARCHAR(50)  = '" + userType + "'" + ", @LockedID     TINYINT       =NULL"
				+ ", @StatusID     TINYINT       =NULL"
				+ " SELECT @LockedID= masterCode FROM commonmaster WHERE mastertype='LockStatus' AND Name=@Locked"
				+ " SELECT @StatusID =masterCode FROM commonmaster WHERE mastertype='CustomerStatus' AND Name=@Status"
				+ " SELECT count(distinct Userid) as 'TotalRecord' " + " from"
				+ " (select row_Number() over(ORDER BY FullName desc) AS RN,U.userid,U.Fullname,U.UserName"
				+ " ,U.EmailID,U.MobilePhone ,linkedacc.LinkedAccount"
				+ ", replace(replace(isnull(UCA.address1,'')+','+isnull(UCA.address2,'')+','+isnull(UCA.cityname,'')+','+isnull(UCA.StateCode,'')+','+isnull(UCA.ZipCode,''),',,',','),',,','') AS 'MailingAddress'"
				+ " FROM [user] U (NOLOCK) "
				+ " JOIN (SELECT UA.userid,isnull(count(distinct UA.accountNumber),0) AS LinkedAccount "
				+ " FROM (Select UserID,AccountNumber,RoleID from useraccount (NOLOCK)" + " Union all"
				+ " Select PU.Userid,PA.Accountnumber,case when PU.RoleID=10 then 3 else PU.RoleID end as RoleID"
				+ " FROM PortfolioAccount PA WITH(NOLOCK) "
				+ " INNER JOIN  PortfolioUser PU WITH(NOLOCK) ON PA.GroupID=PU.GroupID " + " where PA.IsActive=1 "
				+ " )UA" + " JOIN customerinfo CI ON UA.Accountnumber=CI.Accountnumber"
				+ " WHERE CI.CustomerID NOT IN (1,-1)" + " AND (isnull(@Role,'')  ='' or "
				+ " (case when UA.RoleID=1 then 'Guest'" + "	  when UA.RoleID=2 then 'Property Manager'"
				+ "	  when UA.RoleID=3 then 'Owner'" + "	  when UA.RoleID=6 then 'Portfolio Manager'"
				+ "	  when UA.RoleID=11 then 'Portfolio Guest' " + "	end)=isnull(@Role,''))"
				+ " AND (isnull(@ZipCode,'') ='' or CI.ZipCode=isnull(@ZipCode,''))"
				+ " AND (isnull(@City,'')  ='' or CI.cityname=isnull(@City,''))"
				+ " AND (isnull(@utilityAccountNumber,'') ='' or CI.UtilityAccountNumber=isnull(@utilityAccountNumber,''))"
				+ " AND (isnull(@AccountType,'') ='' or (CI.PortalAccessTypeID=1 And CI.PortalAccessType=isnull(@AccountType,'')) or (CI.PortalAccessTypeID=0 And CI.AddressType=isnull(@AccountType,'')))"
				+ " GROUP BY UA.userid" + " )AS linkedacc ON U.userid=linkedacc.userid" + " JOIN "
				+ " (SELECT userid,isnull(count(distinct accountNumber),0) AS LinkedAcc " + " FROM "
				+ " (Select UserID,AccountNumber from useraccount (NOLOCK)" + " Union all"
				+ " Select PU.Userid,PA.Accountnumber" + " FROM PortfolioAccount PA WITH(NOLOCK) "
				+ " INNER JOIN  PortfolioUser PU WITH(NOLOCK) ON PA.GroupID=PU.GroupID " + " where PA.IsActive=1"
				+ " )UA" + " GROUP BY userid" + " )AS accLinked ON U.userid=accLinked.userid"
				+ " LEFT JOIN usercommunicationaddress UCA ON U.userid=UCA.userid AND UCA.MailAddressType=1"
				+ " WHERE U.userid NOT IN (-1,1) AND U.UserType Not In(3,5) AND U.[STATUS]<>3 "
				+ " AND (isnull(@FName,'')  ='' or isnull(U.FirstName,'') like '%'+isnull(@FName,'')+'%')"
				+ " AND (isnull(@LName,'')  ='' or isnull(U.LastName,'') like '%'+isnull(@LName,'')+'%')"
				+ " AND (isnull(@StatusID,'')  ='' or U.Status=isnull(@StatusID,'')) "
				+ " AND (isnull(@LockedID,'')  ='' or U.IsLocked=isnull(@LockedID,'')) "
				+ " AND (isnull(@NoOfLinkedAcc,'')  ='' or"
				+ "  (case when accLinked.LinkedAcc=0 or accLinked.LinkedAcc<=1 then '0-1' "
				+ "	  when accLinked.LinkedAcc>1 and accLinked.LinkedAcc<=5 then '2-5'"
				+ "	  when accLinked.LinkedAcc>5 and accLinked.LinkedAcc<=10 then '6-10'"
				+ "	  when accLinked.LinkedAcc>10 then '10+' end) =isnull(@NoOfLinkedAcc,'')) "
				+ " AND (isnull(@PhoneNo,'')  ='' or U.MobilePhone like '%'+isnull(@PhoneNo,'')+'%')"
				+ " AND (isnull(@EmailId,'')  ='' or U.EmailID like '%'+isnull(@EmailId,'')+'%')"
				+ " AND (cast(U.CreatedDate as date) between isnull(@FromRegDate,'2014-01-01') and isnull(@ToRegDate,cast(getdate() as date)))"
				+ " AND (isnull(@SocialLogin,'')  ='' or (case when U.loginmode=2 then 'Yes' else 'No' end) =isnull(@SocialLogin,'')) "
				+ " AND (isnull(@UserType,'')  ='' or (Case when U.UserType=4 then 'Enterprise' else 'Mass Market' end) = isnull(@UserType,''))"
				+ " ) TBL";
		return q;
	}

	public static String getRegistrationEmailContent(String emailId, String emailSubject) {
		return "select top 1 ID,EmailID,Subject,Message,IsNotify,* from contractaccountnotifyEmail\n"
				+ "where  Subject = '" + emailSubject + "' And emailid ='" + emailId + "'\n" + "order by 1 desc";
	}

	public static String getAccountsDetails(String string) {
		// String query = "SELECT * FROM CustomerInfo where Customerid = '"+string+"'";
		String query = "SELECT CustomerInfo.*,Account.Paperless FROM CustomerInfo\r\n"
				+ "				INNER JOIN Account ON\r\n"
				+ "				CustomerInfo.Accountnumber=Account.Accountnumber\r\n"
				+ "				where CustomerInfo.customerno ='" + string + "'";
		return query;
	}

	public static String getRegistrationMFAOtp(String utilityAccountNumer) {
		// String regDataQuery = "select module_name FROM MODULE WHERE STATUS = 1";
		String regDataQuery = "Select top 1 * from two_factor where account_number ='" + utilityAccountNumer
				+ "' Order By date_created DESC";
		return regDataQuery;
	}

	public static String getUserNameAccountEmailMsg(String subjectLine, String emailAddress) {
		String sQuery = "SELECT TOP 1 Message, IsNotify\n" + "FROM ContractAccountNotifyEmail\n" + "WHERE EmailID = '"
				+ emailAddress + "' " + "AND Subject = '" + subjectLine
				+ "' AND MESSAGE like '%to provide your account number to help us retrieve the username.%'";
		return sQuery;
	}

	public static String sAllUtilitySettingsQuery = ""
			+ "SELECT * FROM UtilitySettings WITH(NOLOCK) WHERE UtilityId = 0";

	public static String getCountOfRegUnRegAccounts(String RegStatus) {
		/*
		 * Registered-> RegStatus=yes UnRegistered-> RegStatus=no
		 */
		String query = "declare @IsRegistered varchar(20)='" + RegStatus + "'\r\n"
				+ "select count (1)  as Count from customerinfo  CI  with (nolock)\r\n" + "left join(\r\n"
				+ "  SELECT  AccountNumber  \r\n" + "  FROM useraccount  WITH(NOLOCK)\r\n" + "  Union\r\n"
				+ "  SELECT  accountNumber\r\n" + "  FROM PortfolioAccount PA WITH(NOLOCK)\r\n"
				+ "  ) account_regi on CI.accountnumber=account_regi.accountnumber\r\n"
				+ "WHERE CustomerID NOT IN (1,-1)\r\n"
				+ "and ((@IsRegistered  ='Yes' and account_regi.accountnumber IS NOT NULL)\r\n"
				+ "OR ( @IsRegistered ='NO' and account_regi.accountnumber IS NULL)\r\n"
				+ "OR (isnull(@IsRegistered,'') =''))";
		return query;
	}

	public static String getCountUniversalSearchResultMode1(String itemToSearch) {
		String q = "declare @utilityAccountNumber varchar(50)=Null" + ", @CustomerNo	VARCHAR(100)  = Null"
				+ ", @UserID		VARCHAR(20)  = Null" + ", @AccountType   VARCHAR(100) = Null"
				+ ", @Status		VARCHAR(100) = Null" + ", @IsPaperLess   BIT			 = Null"
				+ ", @ZipCode		VARCHAR(100) = Null" + ", @City			VARCHAR(100) = Null"
				+ ", @FName			VARCHAR(100) = Null" + ", @LName			VARCHAR(50)  = NULL"
				+ ", @PhoneNo		VARCHAR(20)  = NULL" + ", @EmailID		VARCHAR(100) = NULL"
				+ ", @Locked		VARCHAR(100) = Null" + ", @FromRegDate	date		 = Null"
				+ ", @ToRegDate		date		 = NULL" + ", @SocialLogin	VARCHAR(10)  = NULL"
				+ ", @NoOfLinkedAcc VARCHAR(20)  = NULL" + ", @Role			VARCHAR(30)  = NULL"
				+ ", @SearchValue   VARCHAR(255) = '" + itemToSearch + "'" + ", @Customerid	VARCHAR(20)  = NULL"
				+ ", @OrderColumn   VARCHAR(50)  = NULL" + ", @SortOrder		VARCHAR(50)  = NULL"
				+ ", @NoOfLinkedUser VARCHAR(20)  = NULL" + ", @UserType		VARCHAR(50)  = NULL"

				+ " SELECT COUNT(ServiceAccountnumber) AS Records" + " from"
				+ " (SELECT row_number() over(ORDER BY FULLNAME DESC) AS RN,cust.UtilityAccountNumber AS 'ServiceAccountnumber'"
				+ ", cust.customerNo AS 'CustomerNumber',cust.FullName AS 'CustomerName',cust.EmailID AS 'Customersemail'"
				+ ", cust.MobilePhone AS 'PrimaryPhone' "
				+ ", case when cust.PortalAccessTypeID=1 then cust.PortalAccessType else cust.AddressType END AS 'AccountType'"
				+ ", ISNULL(linkeduser.LinkedUser,0) as 'LinkedUsers'"
				+ ", replace(isnull(cust.address1,'')+','+isnull(cust.address2,'')+','+isnull(cust.cityname,'')+','+isnull(cust.StateCode,'')+','+isnull(cust.ZipCode,''),',,',',') as 'Premise'"
				+ ", case "

				+ " when cust.AccountStatusID in(0,1,3) then 'Active' else 'Inactive' end 'AccountStatus'"
				+ ", cust.AccountNumber,cust.AddressType,isnull(linkedGuest.LinkedUserGuest,0) as LinkedUserGuest"
				+ ", isnull(linkedPM.LinkedUserPM,0) as LinkedUserPM" + ", cust.PortalAccessType"
				+ " FROM customerinfo cust (NOLOCK) "
				+ " LEFT JOIN (SELECT AccountNumber,isnull(count(distinct userid),0) AS LinkedUser"
				+ "	FROM useraccount (NOLOCK)" + "	GROUP BY AccountNumber" + "	Union All"
				+ "	SELECT PA.accountNumber,isnull(count(distinct PU.Userid),0)  AS LinkedUser"
				+ "	FROM	 PortfolioAccount	PA	WITH(NOLOCK) "
				+ "	INNER JOIN  PortfolioUser	PU	WITH(NOLOCK) ON PA.GroupID=PU.GroupID " + "	where PA.IsActive=1"
				+ "	GROUP BY PA.accountNumber" + " )AS linkeduser ON cust.AccountNumber=linkeduser.AccountNumber"
				+ " LEFT JOIN (SELECT AccountNumber,isnull(count(distinct userid),0) AS LinkedUserGuest"
				+ " FROM useraccount (NOLOCK)" + " where Roleid=1" + " GROUP BY AccountNumber" + " Union All"
				+ " SELECT PA.accountNumber,isnull(count(distinct PU.Userid),0)  AS LinkedUserGuest"
				+ " FROM PortfolioAccount PA WITH(NOLOCK) "
				+ " INNER JOIN  PortfolioUser	PU	WITH(NOLOCK) ON PA.GroupID=PU.GroupID "
				+ " where PA.IsActive=1 And PU.Roleid=11" + " GROUP BY PA.accountNumber"
				+ " )AS linkedGuest ON cust.AccountNumber=linkedGuest.AccountNumber"
				+ " LEFT JOIN (SELECT AccountNumber,isnull(count(distinct userid),0) AS LinkedUserPM"
				+ " FROM useraccount (NOLOCK)" + " where Roleid=2" + " GROUP BY AccountNumber" + " Union All"
				+ " SELECT PA.accountNumber,isnull(count(distinct PU.Userid),0)  AS LinkedUserPM"
				+ " FROM  PortfolioAccount PA WITH(NOLOCK) "
				+ " INNER JOIN  PortfolioUser PU WITH(NOLOCK) ON PA.GroupID=PU.GroupID"
				+ " where PA.IsActive=1 And PU.Roleid=6" + " GROUP BY PA.accountNumber"
				+ " )AS linkedPM ON cust.AccountNumber=linkedPM.AccountNumber" + " WHERE CustomerID NOT IN (1,-1) "

				+ " AND (ISNULL(@SearchValue,'')='' OR (cust.UtilityAccountNumber= isnull(@SearchValue,'')"
				+ " OR (cust.FullName = isnull(@SearchValue,'')"
				+ " OR (cust.EmailID = isnull(@SearchValue,'') OR (cust.MobilePhone = isnull(@SearchValue,'') "
				+ " OR (cust.cityname = isnull(@SearchValue,'') or cust.ZipCode=isnull(@SearchValue,'')))))))  "
				+ " )TBL";
		return q;
	}

	public static String getUniversalSearchResultMode1(String itemToSearch) {
		String q = "declare @utilityAccountNumber varchar(50)=Null" + ", @CustomerNo	VARCHAR(100)  = Null"
				+ ", @UserID		VARCHAR(20)  = Null" + ", @AccountType   VARCHAR(100) = Null"
				+ ", @Status		VARCHAR(100) = Null" + ", @IsPaperLess   BIT			 = Null"
				+ ", @ZipCode		VARCHAR(100) = Null" + ", @City			VARCHAR(100) = Null"
				+ ", @FName			VARCHAR(100) = Null" + ", @LName			VARCHAR(50)  = NULL"
				+ ", @PhoneNo		VARCHAR(20)  = NULL" + ", @EmailID		VARCHAR(100) = NULL"
				+ ", @Locked		VARCHAR(100) = Null" + ", @FromRegDate	date		 = Null"
				+ ", @ToRegDate		date		 = NULL" + ", @SocialLogin	VARCHAR(10)  = NULL"
				+ ", @NoOfLinkedAcc VARCHAR(20)  = NULL" + ", @Role			VARCHAR(30)  = NULL"
				+ ", @SearchValue   VARCHAR(255) = '" + itemToSearch + "'" + ", @Customerid	VARCHAR(20)  = NULL"
				+ ", @OrderColumn   VARCHAR(50)  = NULL" + ", @SortOrder		VARCHAR(50)  = NULL"
				+ ", @NoOfLinkedUser VARCHAR(20)  = NULL" + ", @UserType		VARCHAR(50)  = NULL"

				+ " SELECT [ServiceAccountnumber],[CustomerNumber],[CustomerName],[Customersemail],[PrimaryPhone],[AccountType]"
				+ ", [LinkedUsers],Premise,[AccountStatus],[AccountNumber],[AddressType],[LinkedUserGuest],[LinkedUserPM]"
				+ " from"
				+ " (SELECT row_number() over(ORDER BY FULLNAME DESC) AS RN,cust.UtilityAccountNumber AS 'ServiceAccountnumber'"
				+ ", cust.customerNo AS 'CustomerNumber',cust.FullName AS 'CustomerName',cust.EmailID AS 'Customersemail'"
				+ ", cust.MobilePhone AS 'PrimaryPhone' "
				+ ", case when cust.PortalAccessTypeID=1 then cust.PortalAccessType else cust.AddressType END AS 'AccountType'"
				+ ", ISNULL(linkeduser.LinkedUser,0) as 'LinkedUsers'"
				+ ", replace(isnull(cust.address1,'')+','+isnull(cust.address2,'')+','+isnull(cust.cityname,'')+','+isnull(cust.StateCode,'')+','+isnull(cust.ZipCode,''),',,',',') as 'Premise'"
				+ ", case "

				+ " when cust.AccountStatusID in(0,1,3) then 'Active' else 'Inactive' end 'AccountStatus'"
				+ ", cust.AccountNumber,cust.AddressType,isnull(linkedGuest.LinkedUserGuest,0) as LinkedUserGuest"
				+ ", isnull(linkedPM.LinkedUserPM,0) as LinkedUserPM" + ", cust.PortalAccessType"
				+ " FROM customerinfo cust (NOLOCK) "
				+ " LEFT JOIN (SELECT AccountNumber,isnull(count(distinct userid),0) AS LinkedUser"
				+ "	FROM useraccount (NOLOCK)" + "	GROUP BY AccountNumber" + "	Union All"
				+ "	SELECT PA.accountNumber,isnull(count(distinct PU.Userid),0)  AS LinkedUser"
				+ "	FROM	 PortfolioAccount	PA	WITH(NOLOCK) "
				+ "	INNER JOIN  PortfolioUser	PU	WITH(NOLOCK) ON PA.GroupID=PU.GroupID " + "	where PA.IsActive=1"
				+ "	GROUP BY PA.accountNumber" + " )AS linkeduser ON cust.AccountNumber=linkeduser.AccountNumber"
				+ " LEFT JOIN (SELECT AccountNumber,isnull(count(distinct userid),0) AS LinkedUserGuest"
				+ " FROM useraccount (NOLOCK)" + " where Roleid=1" + " GROUP BY AccountNumber" + " Union All"
				+ " SELECT PA.accountNumber,isnull(count(distinct PU.Userid),0)  AS LinkedUserGuest"
				+ " FROM PortfolioAccount PA WITH(NOLOCK) "
				+ " INNER JOIN  PortfolioUser	PU	WITH(NOLOCK) ON PA.GroupID=PU.GroupID "
				+ " where PA.IsActive=1 And PU.Roleid=11" + " GROUP BY PA.accountNumber"
				+ " )AS linkedGuest ON cust.AccountNumber=linkedGuest.AccountNumber"
				+ " LEFT JOIN (SELECT AccountNumber,isnull(count(distinct userid),0) AS LinkedUserPM"
				+ " FROM useraccount (NOLOCK)" + " where Roleid=2" + " GROUP BY AccountNumber" + " Union All"
				+ " SELECT PA.accountNumber,isnull(count(distinct PU.Userid),0)  AS LinkedUserPM"
				+ " FROM  PortfolioAccount PA WITH(NOLOCK) "
				+ " INNER JOIN  PortfolioUser PU WITH(NOLOCK) ON PA.GroupID=PU.GroupID"
				+ " where PA.IsActive=1 And PU.Roleid=6" + " GROUP BY PA.accountNumber"
				+ " )AS linkedPM ON cust.AccountNumber=linkedPM.AccountNumber" + " WHERE CustomerID NOT IN (1,-1) "

				+ " AND (ISNULL(@SearchValue,'')='' OR (cust.UtilityAccountNumber= isnull(@SearchValue,'')"
				+ " OR (cust.FullName = isnull(@SearchValue,'')"
				+ " OR (cust.EmailID = isnull(@SearchValue,'') OR (cust.MobilePhone = isnull(@SearchValue,'') "
				+ " OR (cust.cityname = isnull(@SearchValue,'') or cust.ZipCode=isnull(@SearchValue,'')))))))  "
				+ " )TBL";
		return q;
	}

	public static String getServicePlansofAccountNumber(String utilityAccNo) {
		String query = "declare\r\n" + "@UtilityAccountNumber varchar(30) = '" + utilityAccNo + "'\r\n"
				+ ",@IsQuietHours int = 1\r\n"
				+ ",@AccountNumber INT ---- We are passing the local customer's account number after that we will pass utility Accountnumber\r\n"
				+ ",@SessionCode VARCHAR(250)= NULL\r\n" + ",@TimeOffSet INT = 0\r\n" + ",@EVId INT = NULL\r\n"
				+ ",@userid INT = NULL\r\n" + ",@PageIndex INT = 1\r\n" + ",@PageSize INT = 10\r\n"
				+ ",@LoginDateTime DATETIME\r\n" + ",@LanguageCode VARCHAR(2)\r\n" + ",@IsWaterEnable BIT = 0\r\n"
				+ ",@IsGasEnable BIT = 0\r\n" + ",@IsPowerEnable BIT = 0\r\n" + ",@IsSolidWasteEnable BIT = 0\r\n"
				+ ",@IsEVEnable BIT = 0\r\n" + ",@AddressId INT\r\n"
				+ "SELECT @AccountNumber=AccountNumber from account where Utilityaccountnumber = @UtilityAccountNumber\r\n"
				+ "SELECT @LanguageCode = dbo.UserLanguageSetting(@userid, @SessionCode)\r\n"
				+ "IF NULLIF(@LanguageCode,'') IS NULL SET @LanguageCode = 'EN'\r\n"
				+ "SELECT @IsWaterEnable = [Status] FROM FeatureSettings WITH (NOLOCK) WHERE FeatureName = 'Water'\r\n"
				+ "SELECT @IsGasEnable = [Status] FROM FeatureSettings WITH (NOLOCK) WHERE FeatureName = 'Gas'\r\n"
				+ "SELECT @IsPowerEnable = [Status] FROM FeatureSettings WITH (NOLOCK) WHERE FeatureName = 'Power'\r\n"
				+ "SELECT @IsSolidWasteEnable = [Status] FROM FeatureSettings WITH (NOLOCK) WHERE FeatureName = 'SolidWaste'\r\n"
				+ "SELECT @IsEVEnable = [Status] FROM FeatureSettings WITH (NOLOCK) WHERE FeatureName = 'EV'\r\n"
				+ "SELECT @LoginDateTime = DATEADD(MINUTE,-@TimeOffSet,MAX(ActivityDateTime)),@AddressId = MAX(acc.AddressId)\r\n"
				+ "FROM [user] U WITH(NOLOCK)\r\n" + "JOIN (Select AccountNumber,Userid from UserAccount (NOLOCK)\r\n"
				+ "Union ALL\r\n" + "Select PA.AccountNumber,PU.UserID from PortfolioUser PU (NOLOCK)\r\n"
				+ "JOIN PortfolioAccount PA (NOLOCK) ON PU.GroupID = PA.GroupID And PA.IsActive=1\r\n"
				+ "Where PU.IsActive=1\r\n" + ")UA ON(UA.UserId = U.UserId)\r\n"
				+ "LEFT JOIN UserActivityTrail his WITH(NOLOCK) ON(his.UserId = U.UserId)\r\n"
				+ "JOIN Account acc WITH(NOLOCK) ON Acc.AccountNumber = UA.AccountNumber\r\n"
				+ "JOIN CustomerAddress Custadd WITH(NOLOCK) ON Acc.AddressId = Custadd.AddressId\r\n"
				+ "WHERE UA.AccountNumber = @AccountNumber\r\n" + "SELECT DISTINCT\r\n" + "BudgetFiftyNotify\r\n"
				+ ",BudgetSeventyFiveNotify\r\n" + ",BudgetNinetyNotify\r\n" + ",BudgetOtherNotify\r\n"
				+ ",HoursFrom\r\n" + ",HoursTo\r\n"
				+ ",CASE WHEN @IsPowerEnable = 1 THEN P.PlanName ELSE NULL END AS PowerPlanName\r\n"
				+ ",CASE WHEN @IsWaterEnable = 1 THEN W.PlanName ELSE NULL END AS WaterPlanName\r\n"
				+ ",CASE WHEN @IsGasEnable = 1 THEN G.PlanName ELSE NULL END AS GasPlanName\r\n" + ",IsQuietHours\r\n"
				+ ",U.IPAddress\r\n" + ",@LoginDateTime AS LastLoginDateTime\r\n" + ",b.ZipCode\r\n"
				+ ",LM.City AS CityName\r\n" + ",a.FullName\r\n" + ",COM.Name AS [Status]\r\n"
				+ "/*Added By Rohit Kumar on 11 August 2021 as per the requirement of showing the user status and the Jira ticket SCM10-4779*/\r\n"
				+ ",case when U.[status] =3 Then 'Not Registered User' else 'Registered User' End As UserLabel\r\n"
				+ ",b.AddressType\r\n"
				+ ",case when Ac.Paperless = 1 then 'Opted' when Ac.Paperless = 0 then 'Not Opted' End AS Paperless\r\n"
				+ ",u.EmailId\r\n" + ",u.AlternateEmailId\r\n" + ",u.MobilePhone\r\n" + ",a.CreatedDate\r\n"
				+ ",u.HomePhone\r\n" + ",b.Latitude\r\n" + ",b.Longitude\r\n" + ",b.Address1\r\n" + ",b.Address2\r\n"
				+ ",a.CustomerId\r\n" + ",u.UserName\r\n"
				+ ",U.Zipcode+', '+U.CityName+', '+U.StateName AS LastLoginLocation\r\n"
				+ ",ISNULL(TZ.TimeZoneName,'') AS TimeZoneName\r\n" + "FROM Customer a WITH (NOLOCK)\r\n"
				+ "INNER JOIN CustomerAddress b WITH (NOLOCK) ON b.CustomerID = a.CustomerID\r\n"
				+ "INNER JOIN Account Ac WITH (NOLOCK) ON Ac.AddressID = B.AddressID\r\n" + "LEFT JOIN\r\n"
				+ "(Select AccountNumber,Userid from UserAccount (NOLOCK) where RoleID=3\r\n" + "Union ALL\r\n"
				+ "Select PA.AccountNumber,PU.UserID from PortfolioUser PU (NOLOCK)\r\n"
				+ "JOIN PortfolioAccount PA (NOLOCK) ON PU.GroupID = PA.GroupID And PA.IsActive=1\r\n"
				+ "Where PU.IsActive=1 AND PU.RoleID=10\r\n" + ") UA ON UA.AccountNumber = AC.AccountNumber\r\n"
				+ "LEFT JOIN [User] U WITH (NOLOCK) ON UA.UserID=U.UserID\r\n"
				+ "LEFT JOIN TimeZone TZ(NOLOCK) ON U.TimeZoneID=TZ.TimeZoneID\r\n"
				+ "--INNER JOIN CityMaster CM WITH (NOLOCK) ON CM.CityId = b.CityId\r\n"
				+ "Join LocationMaster LM WITH(NOLOCK) ON B.ID = LM.ID\r\n"
				+ "/*INNER JOIN CommonMaster COM ON COM.MasterCode = ac.[Status] AND COM.MasterType = 'AccountStatus'*/\r\n"
				+ "/*Changed By Rohit Kumar on 11 August 2021 as per the requirement of showing the user status and the Jira ticket SCM10-4779*/\r\n"
				+ "INNER JOIN CommonMaster COM (NOLOCK) ON COM.MasterCode = U.[Status] AND COM.MasterType = 'CustomerStatus'\r\n"
				+ "LEFT JOIN AccountNotification Notify WITH (NOLOCK) ON Notify.AccountNumber = ac.AccountNumber\r\n"
				+ "LEFT OUTER JOIN UsagesPlan p WITH (NOLOCK) ON p.PlanId = Ac.AddressPowerPlanId AND p.PlanUsageType = 'P'\r\n"
				+ "LEFT OUTER JOIN UsagesPlan W WITH (NOLOCK) ON Ac.WaterPlanId = W.PlanId AND W.PlanUsageType = 'W'\r\n"
				+ "LEFT OUTER JOIN UsagesPlan G WITH (NOLOCK) ON Ac.GasPlanId = G.PlanId AND G.PlanUsageType = 'G'\r\n"
				+ "LEFT OUTER JOIN UsagesPlan EV WITH (NOLOCK) ON Ac.EVPowerPlanId = EV.PlanId AND EV.PlanUsageType = 'EV'\r\n"
				+ "LEFT OUTER JOIN PlanDetail Pdet WITH (NOLOCK) ON Pdet.PlanDetailId = p.PlanDetailId AND Pdet.AddressType = 'R' AND Pdet.PlanDetailStatus = 1\r\n"
				+ "LEFT OUTER JOIN PlanDetail Wdet WITH (NOLOCK) ON Wdet.PlanDetailId = W.PlanDetailId AND Wdet.AddressType = 'R' AND Wdet.PlanDetailStatus = 1\r\n"
				+ "LEFT OUTER JOIN PlanDetail Gdet WITH (NOLOCK) ON Gdet.PlanDetailId = G.PlanDetailId AND Gdet.AddressType = 'R' AND Gdet.PlanDetailStatus = 1\r\n"
				+ "LEFT OUTER JOIN PlanDetail EVdet WITH (NOLOCK) ON EVdet.PlanDetailId = EV.PlanDetailId AND EVdet.AddressType = 'R' AND EVdet.PlanDetailStatus= 1\r\n"
				+ "LEFT OUTER JOIN PlanType Ptyp WITH (NOLOCK) ON Ptyp.PlanTypeId = Pdet.PlanTypeId\r\n"
				+ "LEFT OUTER JOIN PlanType Wtyp WITH (NOLOCK) ON Wtyp.PlanTypeId = Wdet.PlanTypeId\r\n"
				+ "LEFT OUTER JOIN PlanType Gtyp WITH (NOLOCK) ON Gtyp.PlanTypeId = Gdet.PlanTypeId\r\n"
				+ "LEFT OUTER JOIN PlanType EVtyp WITH (NOLOCK) ON EVtyp.PlanTypeId = EVdet.PlanTypeId\r\n"
				+ "WHERE ac.AccountNumber = @AccountNumber and IsQuietHours = @IsQuietHours";

		return query;
	}

	public static String getTimeZoneDetailsOfUser(String userName) {
		String query = "select * from TimeZone where TimeZoneId in (select TimeZoneId from [User] where UserName = '"
				+ userName + "')";
		return query;
	}

	public static String getAllCustomerDetails(String utilityAccNo) {
		String query = "select * from customerinfo where UtilityAccountNumber='" + utilityAccNo + "'";
		return query;
	}

	public static String getUserActivityTrailDetailsWithUsername(String userName) {
		String query = "select top 1 * from UserActivityTrail where UserID in (select UserId from [User] where UserName = '"
				+ userName + "') order by 1 desc";
		return query;
	}

	public static String getFootPrintData(int locationtype) {
		String q = "select Name from GreenFootPrintLocations where LocationTypeId =" + locationtype
				+ "order by Name ASC";
		return q;
	}

	public static String getUserNameTempalateDetail(String tempDetailId) {
		String q = "SELECT * FROM templatedetail where TempDetailID = '" + tempDetailId + "' order by 1 desc";
		return q;
	}

	public static String UpdateGuestUserLinkExpire(String email, int days, String accountnumber) {
		String query = " UPDATE GuestAccessRequest SET  CreatedDate = (Select GetDate()-" + days
				+ "),LastUpdated=(Select GetDate()-" + days + "),\r\n" + "AccessExpiryDate= (Select GetDate()+" + days
				+ "),RequestStatus = 0 where GuestEmailID = '" + email + "' and UtilityAccountNumber = '"
				+ accountnumber + "'";
		return query;
	}

	/**
	 * This method used to update the access expiry date of the guest user invite.
	 *
	 * @param sAccountNumber
	 * @param sDate
	 * @return
	 */
	public static String getQueryToUpdateAccessExpDate(String sAccountNumber, String sDate) {
		String sDateFormat = DateUtil.changeStringToDateInFormat(sDate, "yyyy-MM-dd", "yyyy-MM-d");
		String sQuery = "UPDATE GuestAccessRequest " + "SET AccessExpiryDate = '" + sDateFormat + " 00:00:00.000' "
				+ "WHERE UtilityAccountNumber = '" + sAccountNumber + "' AND "
				+ "RequestID = (SELECT Top 1 RequestID FROM GuestAccessRequest " + "WHERE UtilityAccountNumber = '"
				+ sAccountNumber + "' ORDER BY LastUpdated DESC)";
		return sQuery;
	}

	public static String getLoginMFAOtp() {
		// String regDataQuery = "select module_name FROM MODULE WHERE STATUS = 1";
		String regDataQuery = "Select top 1 * from two_factor Order By date_created DESC";
		return regDataQuery;
	}

	public static String getCountPreferenceChangeDetails(String sUtilityAccountNumber, String notificationType) {
		String sQuery = "SELECT Count(AccountNumber) As NoRow FROM AccountNotificationDetail\n"
				+ "WHERE AccountNumber = (SELECT TOP 1 AccountNumber FROM UserAccount \n"
				+ "WHERE UtilityAccountNumber = '" + sUtilityAccountNumber + "')\n"
				+ "AND AccountNotificationTypeID = (SELECT AccountNotificationTypeID FROM AccountNotificationType \n"
				+ "WHERE AccountNotificationType = '" + notificationType + "')\n";
		return sQuery;
	}

	public static String getCSPAdminHomePageCSRCount() {
		String query = "declare    @LockedCustomerCount INT\n" + ",@LockedIPCount   INT\n" + ",@RegIPLockAttempt  INT\n"
				+ ",@RegIPLockDuration  INT\n" + ",@LoginIPLockAttempt INT\n" + ",@LoginIPLockDuration INT\n"
				+ ",@UtilityId    INT=0\n" + "SELECT @LockedCustomerCount= count (DISTINCT U.Userid)\n"
				+ "From [User] U\n"
				+ "JOIN (SELECT UA.userid,isnull(count(distinct UA.accountNumber),0) AS LinkedAccount\n"
				+ "FROM useraccount UA (NOLOCK)\n" + "GROUP BY UA.userid\n" + "Union all\n"
				+ "SELECT PU.Userid,isnull(count(distinct PA.accountNumber),0) AS LinkedAccount\n"
				+ "FROM   PortfolioAccount    PA     WITH(NOLOCK)\n"
				+ "INNER JOIN  PortfolioUser  PU     WITH(NOLOCK) ON PA.GroupID=PU.GroupID AND PU.IsActive=1 /*Uncommented by Rohit Kumas on 23_July_2021 as per to match the conditions as per CSR_Workbench_ENT procedure*/\n"
				+ "where PA.IsActive=1\n" + "GROUP BY PU.userid\n"
				+ ")AS linkedacc ON U.userid=linkedacc.userid and U.islocked=1 and U.userid NOT IN (-1,1) AND U.UserType Not in (3,5)\n"
				+ "\n" + "SELECT @LockedIPCount  = COUNT(DISTINCT IPAddress)\n" + "FROM\n" + "(\n"
				+ "SELECT DISTINCT Fail.IPAddress\n" + "FROM UserActivityTrail Fail WITH(NOLOCK)\n" + "JOIN (\n"
				+ "SELECT IPAddress,MAX(LoginDateTime) AS LoginDateTime\n" + "FROM\n" + "(\n"
				+ "SELECT IPAddress,MAX(ActivityDateTime) AS LoginDateTime FROM UserActivityTrail WITH(NOLOCK) WHERE ActivityStatus = 1 GROUP BY IPAddress\n"
				+ "UNION ALL\n"
				+ "SELECT IPAddress,DATEADD(SS,-1,MIN(ActivityDateTime)) AS AttemptDateTime FROM UserActivityTrail WITH(NOLOCK) WHERE ActivityStatus <>1 GROUP BY IPAddress\n"
				+ ")Suc\n" + "GROUP BY IPAddress\n" + ") AS Success ON(Success.IPAddress = Fail.IPAddress)\n"
				+ "WHERE Fail.ActivityDateTime > Success.LoginDateTime AND Fail.ActivityDateTime > DATEADD(HOUR,-@LoginIPLockDuration,GETDATE()) AND Fail.ActivityStatus <>1 AND Fail.IsArchive = 0\n"
				+ "GROUP BY Fail.IPAddress\n" + "HAVING COUNT(1) >= @LoginIPLockAttempt\n" + ") AS LIP\n"
				+ "SELECT ISNULL(@LockedIPCount,0)+ISNULL(@LockedCustomerCount,0) AS Count\n";
		return query;
	}

	public static String getRoleIdForAccountNumber(String utilityAccNo, String userId) {
		String query = "select  roleId from useraccount where utilityAccountNumber= '" + utilityAccNo + "' "
				+ "and userId='" + userId + "'";
		return query;
	}

	public static String getCSPAdminHomePageOutagesCount() {
		String query = "declare @offset int =-330\r\n" + "    SELECT \r\n"
				+ "  --ISNULL(SUM(CASE WHEN Outage.StartTime <= DateAdd(MI,@offset,GETDATE())    THEN 1 END), 0)\r\n"
				+ "   -- AS [UnplannedOutages] /*Added \"And EndTime > GetDate()\" to handled  unplan outage on recommendtion from Vaibhav shaini   */\r\n"
				+ "   ISNULL(SUM(CASE WHEN (Outage.StartTime <= DateAdd(MI,@offset,GETDATE()) and  outage.EndTime  >= DateAdd(MI,@offset,GETDATE()))   THEN 1 END), 0) AS [CurrentOutages]\r\n"
				+ "  FROM [OutageNotification] Outage \r\n"
				+ "  Inner join CityMaster city  on outage.City = city.CityID\r\n" + "  WHERE   IsResolved=0 ";
		return query;
	}

	public static String sDecimalPlaceAdminSettingsCspQuery = "" + "SELECT UptoDecimalPlaces FROM UtilitySettings";

	public static String getTotalCrashLogCount(String toDate, String fromDate) {

		String query = "declare \r\n" + "	 @MODE						BIT			= 0		--Mode: 0-Get,1-Set\r\n"
				+ "	,@Event						VARCHAR(50)	= NULL\r\n"
				+ "	,@Payload_Type				VARCHAR(50)	= NULL\r\n"
				+ "	,@Display_ID				SMALLINT	= NULL\r\n"
				+ "	,@Title						VARCHAR(50)	= NULL\r\n"
				+ "	,@Method					VARCHAR(50)	= NULL\r\n"
				+ "	,@Impact_Level				TINYINT		= NULL\r\n"
				+ "	,@Crashes_Count				INT			= NULL\r\n"
				+ "	,@Impacted_Devices_Count	INT			= NULL\r\n"
				+ "	,@URL						VARCHAR(255)= NULL\r\n" + "	,@FromDate					DATETIME	= '"
				+ fromDate + "'\r\n" + "	,@ToDate					DATETIME	= '" + toDate + "'\r\n"
				+ "	,@Deviceid VARCHAR(300)=NULL\r\n" + "	,@Userip  VARCHAR(15)=NULL\r\n"
				+ "	,@Devicetype VARCHAR(2)=NULL\r\n" + "	,@Filename VARCHAR(200)=NULL\r\n"
				+ "	,@Customerid INT=NULL\r\n" + "	,@UtilityID SMALLINT=0\r\n"
				+ "	,@AppVersion				VARCHAR(50) = NULL\r\n"
				+ "	,@PhoneModel 				VARCHAR(100) = NULL\r\n"
				+ "	,@UtilityAccountNumber 		VARCHAR(100) = NULL\r\n"
				+ "	,@OSVersion					VARCHAR(100) = NULL\r\n" + "	,@offset int ='+330'\r\n" + "\r\n"
				+ "\r\n" + "	DECLARE @IsExternal BIT\r\n" + "	\r\n"
				+ "		IF LEN(LTRIM(RTRIM(@FromDate))) = 0 SET @FromDate = NULL\r\n"
				+ "		IF ISNULL(@ToDate,'') <> ''\r\n" + "			SET	@ToDate = DATEADD(DD,1,@ToDate)\r\n"
				+ "		ELSE\r\n" + "			SET	@ToDate = NULL\r\n" + "	\r\n" + "		IF @Mode = 0\r\n"
				+ "		BEGIN\r\n" + "			--Crash Log DateWise, DeviceWise Summary BEGIN\r\n"
				+ "			IF EXISTS(SELECT 1 FROM CrashLog WITH(NOLOCK) WHERE CreatedDate >= ISNULL(@FromDate,CreatedDate) AND CreatedDate < ISNULL(@ToDate,CreatedDate))\r\n"
				+ "			BEGIN\r\n" + "				--RS1\r\n"
				+ "				--SELECT CONVERT(VARCHAR(10),dateadd(minute,@offset,CreatedDate),101) AS CreatedDate,COUNT(1) AS [Count]\r\n"
				+ "				--FROM CrashLog WITH(NOLOCK)\r\n"
				+ "				--WHERE CreatedDate >= ISNULL(@FromDate,CreatedDate) AND CreatedDate < ISNULL(@ToDate,DATEADD(SS,1,CreatedDate))\r\n"
				+ "				--GROUP BY CONVERT(VARCHAR(10),dateadd(minute,@offset,CreatedDate),101)\r\n"
				+ "				--RS2\r\n"
				+ "				SELECT COUNT(1) AS [Count]	FROM CrashLog WITH(NOLOCK) WHERE CreatedDate >= ISNULL(@FromDate,CreatedDate) AND CreatedDate < ISNULL(@ToDate,DATEADD(SS,1,CreatedDate))\r\n"
				+ "				--RS3\r\n"
				+ "				--SELECT *					FROM CrashLog WITH(NOLOCK) WHERE CreatedDate >= ISNULL(@FromDate,CreatedDate) AND CreatedDate < ISNULL(@ToDate,DATEADD(SS,1,CreatedDate))\r\n"
				+ "			\r\n" + "			END\r\n" + "			ELSE\r\n" + "			BEGIN\r\n"
				+ "				SELECT '' AS CreatedDate,0 AS [Count] FROM CrashLog WITH(NOLOCK) WHERE 1 = 2\r\n"
				+ "				SELECT 0 AS [Count]\r\n"
				+ "				SELECT * FROM CrashLog WITH(NOLOCK) WHERE 1 = 2\r\n" + "			END	\r\n"
				+ "		END\r\n" + "		ELSE\r\n" + "		BEGIN\r\n"
				+ "			SELECT @IsExternal=IsExternalCrashLog FROM UtilitySettings(NOLOCK) WHERE UtilityID=@UtilityID\r\n"
				+ "			INSERT INTO CrashLog([Event],Payload_Type,Display_ID,Title,Method,Impact_Level,Crashes_Count,Impacted_Devices_Count,URL\r\n"
				+ "								,DeviceID,IPAddress,DeviceType,FileName,CustomerID,IsExternal,AppVersion,PhoneModel,UtilityAccountNumber,OSVersion)\r\n"
				+ "			VALUES(@Event,@Payload_Type,@Display_ID,@Title,@Method,@Impact_Level,@Crashes_Count,@Impacted_Devices_Count,@URL\r\n"
				+ "				   ,@Deviceid,@Userip,@Devicetype,@Filename,@Customerid,@IsExternal,@AppVersion,@PhoneModel,@UtilityAccountNumber,@OSVersion)\r\n"
				+ "		\r\n" + "			IF @@ROWCOUNT > 0\r\n"
				+ "				SELECT  1 AS [Status], 'Record Inserted Successfully' AS [Message]\r\n"
				+ "			ELSE\r\n" + "				SELECT  0 AS [Status], 'No Record Inserted' AS [Message]\r\n"
				+ "		END";

		return query;

	}

	public static String getTotalCrashLogGraphDetails(String toDate, String fromDate) {

		String query = "declare \r\n" + "	 @MODE						BIT			= 0		--Mode: 0-Get,1-Set\r\n"
				+ "	,@Event						VARCHAR(50)	= NULL\r\n"
				+ "	,@Payload_Type				VARCHAR(50)	= NULL\r\n"
				+ "	,@Display_ID				SMALLINT	= NULL\r\n"
				+ "	,@Title						VARCHAR(50)	= NULL\r\n"
				+ "	,@Method					VARCHAR(50)	= NULL\r\n"
				+ "	,@Impact_Level				TINYINT		= NULL\r\n"
				+ "	,@Crashes_Count				INT			= NULL\r\n"
				+ "	,@Impacted_Devices_Count	INT			= NULL\r\n"
				+ "	,@URL						VARCHAR(255)= NULL\r\n" + "	,@FromDate					DATETIME	= '"
				+ fromDate + "'\r\n" + "	,@ToDate					DATETIME	= '" + toDate + "'\r\n"
				+ "	,@Deviceid VARCHAR(300)=NULL\r\n" + "	,@Userip  VARCHAR(15)=NULL\r\n"
				+ "	,@Devicetype VARCHAR(2)=NULL\r\n" + "	,@Filename VARCHAR(200)=NULL\r\n"
				+ "	,@Customerid INT=NULL\r\n" + "	,@UtilityID SMALLINT=0\r\n"
				+ "	,@AppVersion				VARCHAR(50) = NULL\r\n"
				+ "	,@PhoneModel 				VARCHAR(100) = NULL\r\n"
				+ "	,@UtilityAccountNumber 		VARCHAR(100) = NULL\r\n"
				+ "	,@OSVersion					VARCHAR(100) = NULL\r\n" + "	,@offset int ='+330'\r\n" + "\r\n"
				+ "\r\n" + "	DECLARE @IsExternal BIT\r\n" + "	\r\n"
				+ "		IF LEN(LTRIM(RTRIM(@FromDate))) = 0 SET @FromDate = NULL\r\n"
				+ "		IF ISNULL(@ToDate,'') <> ''\r\n" + "			SET	@ToDate = DATEADD(DD,1,@ToDate)\r\n"
				+ "		ELSE\r\n" + "			SET	@ToDate = NULL\r\n" + "	\r\n" + "		IF @Mode = 0\r\n"
				+ "		BEGIN\r\n" + "			--Crash Log DateWise, DeviceWise Summary BEGIN\r\n"
				+ "			IF EXISTS(SELECT 1 FROM CrashLog WITH(NOLOCK) WHERE CreatedDate >= ISNULL(@FromDate,CreatedDate) AND CreatedDate < ISNULL(@ToDate,CreatedDate))\r\n"
				+ "			BEGIN\r\n" + "				--RS1\r\n"
				+ "				SELECT CONVERT(VARCHAR(10),dateadd(minute,@offset,CreatedDate),101) AS CreatedDate,COUNT(1) AS [Count]\r\n"
				+ "				FROM CrashLog WITH(NOLOCK)\r\n"
				+ "				WHERE CreatedDate >= ISNULL(@FromDate,CreatedDate) AND CreatedDate < ISNULL(@ToDate,DATEADD(SS,1,CreatedDate))\r\n"
				+ "				GROUP BY CONVERT(VARCHAR(10),dateadd(minute,@offset,CreatedDate),101)\r\n"
				+ "				--RS2\r\n"
				+ "				--SELECT COUNT(1) AS [Count]	FROM CrashLog WITH(NOLOCK) WHERE CreatedDate >= ISNULL(@FromDate,CreatedDate) AND CreatedDate < ISNULL(@ToDate,DATEADD(SS,1,CreatedDate))\r\n"
				+ "				--RS3\r\n"
				+ "				--SELECT *					FROM CrashLog WITH(NOLOCK) WHERE CreatedDate >= ISNULL(@FromDate,CreatedDate) AND CreatedDate < ISNULL(@ToDate,DATEADD(SS,1,CreatedDate))\r\n"
				+ "			\r\n" + "			END\r\n" + "			ELSE\r\n" + "			BEGIN\r\n"
				+ "				SELECT '' AS CreatedDate,0 AS [Count] FROM CrashLog WITH(NOLOCK) WHERE 1 = 2\r\n"
				+ "				SELECT 0 AS [Count]\r\n"
				+ "				SELECT * FROM CrashLog WITH(NOLOCK) WHERE 1 = 2\r\n" + "			END	\r\n"
				+ "		END\r\n" + "		ELSE\r\n" + "		BEGIN\r\n"
				+ "			SELECT @IsExternal=IsExternalCrashLog FROM UtilitySettings(NOLOCK) WHERE UtilityID=@UtilityID\r\n"
				+ "			INSERT INTO CrashLog([Event],Payload_Type,Display_ID,Title,Method,Impact_Level,Crashes_Count,Impacted_Devices_Count,URL\r\n"
				+ "								,DeviceID,IPAddress,DeviceType,FileName,CustomerID,IsExternal,AppVersion,PhoneModel,UtilityAccountNumber,OSVersion)\r\n"
				+ "			VALUES(@Event,@Payload_Type,@Display_ID,@Title,@Method,@Impact_Level,@Crashes_Count,@Impacted_Devices_Count,@URL\r\n"
				+ "				   ,@Deviceid,@Userip,@Devicetype,@Filename,@Customerid,@IsExternal,@AppVersion,@PhoneModel,@UtilityAccountNumber,@OSVersion)\r\n"
				+ "		\r\n" + "			IF @@ROWCOUNT > 0\r\n"
				+ "				SELECT  1 AS [Status], 'Record Inserted Successfully' AS [Message]\r\n"
				+ "			ELSE\r\n" + "				SELECT  0 AS [Status], 'No Record Inserted' AS [Message]\r\n"
				+ "		END";

		return query;

	}

	public static String getTotalCrashLogPieDetails(String toDate, String fromDate) {

		String query = "declare \r\n" + "	 @MODE						BIT			= 0		--Mode: 0-Get,1-Set\r\n"
				+ "	,@Event						VARCHAR(50)	= NULL\r\n"
				+ "	,@Payload_Type				VARCHAR(50)	= NULL\r\n"
				+ "	,@Display_ID				SMALLINT	= NULL\r\n"
				+ "	,@Title						VARCHAR(50)	= NULL\r\n"
				+ "	,@Method					VARCHAR(50)	= NULL\r\n"
				+ "	,@Impact_Level				TINYINT		= NULL\r\n"
				+ "	,@Crashes_Count				INT			= NULL\r\n"
				+ "	,@Impacted_Devices_Count	INT			= NULL\r\n"
				+ "	,@URL						VARCHAR(255)= NULL\r\n" + "	,@FromDate					DATETIME	= '"
				+ fromDate + "'\r\n" + "	,@ToDate					DATETIME	= '" + toDate + "'\r\n"
				+ "	,@Deviceid VARCHAR(300)=NULL\r\n" + "	,@Userip  VARCHAR(15)=NULL\r\n"
				+ "	,@Devicetype VARCHAR(2)=NULL\r\n" + "	,@Filename VARCHAR(200)=NULL\r\n"
				+ "	,@Customerid INT=NULL\r\n" + "	,@UtilityID SMALLINT=0\r\n"
				+ "	,@AppVersion				VARCHAR(50) = NULL\r\n"
				+ "	,@PhoneModel 				VARCHAR(100) = NULL\r\n"
				+ "	,@UtilityAccountNumber 		VARCHAR(100) = NULL\r\n"
				+ "	,@OSVersion					VARCHAR(100) = NULL\r\n" + "	,@offset int ='+330'\r\n" + "\r\n"
				+ "\r\n" + "	DECLARE @IsExternal BIT\r\n" + "	\r\n"
				+ "		IF LEN(LTRIM(RTRIM(@FromDate))) = 0 SET @FromDate = NULL\r\n"
				+ "		IF ISNULL(@ToDate,'') <> ''\r\n" + "			SET	@ToDate = DATEADD(DD,1,@ToDate)\r\n"
				+ "		ELSE\r\n" + "			SET	@ToDate = NULL\r\n" + "	\r\n" + "		IF @Mode = 0\r\n"
				+ "		BEGIN\r\n" + "			--Crash Log DateWise, DeviceWise Summary BEGIN\r\n"
				+ "			IF EXISTS(SELECT 1 FROM CrashLog WITH(NOLOCK) WHERE CreatedDate >= ISNULL(@FromDate,CreatedDate) AND CreatedDate < ISNULL(@ToDate,CreatedDate))\r\n"
				+ "			BEGIN\r\n" + "				--RS1\r\n"
				+ "				--SELECT CONVERT(VARCHAR(10),dateadd(minute,@offset,CreatedDate),101) AS CreatedDate,COUNT(1) AS [Count]\r\n"
				+ "				--FROM CrashLog WITH(NOLOCK)\r\n"
				+ "				--WHERE CreatedDate >= ISNULL(@FromDate,CreatedDate) AND CreatedDate < ISNULL(@ToDate,DATEADD(SS,1,CreatedDate))\r\n"
				+ "				--GROUP BY CONVERT(VARCHAR(10),dateadd(minute,@offset,CreatedDate),101)\r\n"
				+ "				--RS2\r\n"
				+ "				--SELECT COUNT(1) AS [Count]	FROM CrashLog WITH(NOLOCK) WHERE CreatedDate >= ISNULL(@FromDate,CreatedDate) AND CreatedDate < ISNULL(@ToDate,DATEADD(SS,1,CreatedDate))\r\n"
				+ "				--RS3\r\n"
				+ "				SELECT *					FROM CrashLog WITH(NOLOCK) WHERE CreatedDate >= ISNULL(@FromDate,CreatedDate) AND CreatedDate < ISNULL(@ToDate,DATEADD(SS,1,CreatedDate))\r\n"
				+ "			\r\n" + "			END\r\n" + "			ELSE\r\n" + "			BEGIN\r\n"
				+ "				SELECT '' AS CreatedDate,0 AS [Count] FROM CrashLog WITH(NOLOCK) WHERE 1 = 2\r\n"
				+ "				SELECT 0 AS [Count]\r\n"
				+ "				SELECT * FROM CrashLog WITH(NOLOCK) WHERE 1 = 2\r\n" + "			END	\r\n"
				+ "		END\r\n" + "		ELSE\r\n" + "		BEGIN\r\n"
				+ "			SELECT @IsExternal=IsExternalCrashLog FROM UtilitySettings(NOLOCK) WHERE UtilityID=@UtilityID\r\n"
				+ "			INSERT INTO CrashLog([Event],Payload_Type,Display_ID,Title,Method,Impact_Level,Crashes_Count,Impacted_Devices_Count,URL\r\n"
				+ "								,DeviceID,IPAddress,DeviceType,FileName,CustomerID,IsExternal,AppVersion,PhoneModel,UtilityAccountNumber,OSVersion)\r\n"
				+ "			VALUES(@Event,@Payload_Type,@Display_ID,@Title,@Method,@Impact_Level,@Crashes_Count,@Impacted_Devices_Count,@URL\r\n"
				+ "				   ,@Deviceid,@Userip,@Devicetype,@Filename,@Customerid,@IsExternal,@AppVersion,@PhoneModel,@UtilityAccountNumber,@OSVersion)\r\n"
				+ "		\r\n" + "			IF @@ROWCOUNT > 0\r\n"
				+ "				SELECT  1 AS [Status], 'Record Inserted Successfully' AS [Message]\r\n"
				+ "			ELSE\r\n" + "				SELECT  0 AS [Status], 'No Record Inserted' AS [Message]\r\n"
				+ "		END";

		return query;
	}

	public static String getTotalErrorLogCount(String toDate, String fromDate) {

		String query = "declare \r\n" + "  @Application TINYINT			= NULL\r\n"
				+ " ,@ErrFrom	   VARCHAR(100)	= NULL\r\n" + " ,@DateFrom		DATETIME		= '" + fromDate
				+ "'\r\n" + " ,@DateTo		DATETIME		= '" + toDate + "'\r\n"
				+ " ,@OrderColumn  VARCHAR(MAX)	= 'Logged'\r\n" + " ,@SortOrder  VARCHAR(MAX)		= 'Desc'\r\n"
				+ " ,@Message     VARCHAR(MAX)	= NULL\r\n"
				+ " ,@Mode		   TINYINT			= 0 --0: Master Data,1:Detail\r\n"
				+ " ,@LogId	   INT				= Null\r\n" + " ,@PageStartFrom   INT			= 0\r\n"
				+ " ,@PageLength	   INT			= 1000\r\n" + " ,@ServerAddress   NVARCHAR(200)	= NULL\r\n"
				+ " ,@offset int ='+330'\r\n" + "\r\n" + "  DECLARE  @SQL			 NVARCHAR(MAX)\r\n"
				+ "		  ,@param		 NVARCHAR(MAX)\r\n" + "		  ,@TotalRecords INT\r\n"
				+ "		  ,@PageTo INT = @PageStartFrom+@PageLength\r\n" + "  IF @Mode = 0\r\n" + "  BEGIN \r\n"
				+ "	SET @SQL = N'\r\n"
				+ "		SELECT ROW_NUMBER() OVER (ORDER BY '+@OrderColumn+' '+@SortOrder+') AS RN,* INTO #TempData FROM (\r\n"
				+ "		SELECT \r\n" + "			A.ID\r\n" + "			,Application\r\n"
				+ "			,b.NAME ApplicationName\r\n" + "			,CONVERT(VARCHAR,Logged,120) AS Logged\r\n"
				+ "			--,Logged\r\n" + "			,Message\r\n" + "			,Https\r\n"
				+ "			,ServerAddress\r\n" + "			,RemoteAddress\r\n" + "			,b.NAME ErrFrom\r\n"
				+ "		FROM [Log] A WITH (NOLOCK)\r\n" + "		JOIN CommonMaster B ON A.Application = B.MasterCode\r\n"
				+ "			AND B.MasterType = ''Application''\r\n"
				+ "		--WHERE Logged >= ISNULL(@DateFrom, Logged) AND Logged <= ISNULL(DATEADD(SS,86399,@DateTo), Logged)\r\n"
				+ "		WHERE Logged >= ISNULL(@DateFrom, Logged) AND Logged <= ISNULL(@DateTo, Logged)\r\n"
				+ "			AND Application = ISNULL(@Application, Application)\r\n"
				+ "			AND LEVEL = ''Error''\r\n"
				+ "			AND Message LIKE  ''%''+ISNULL(@Message,substring(Message,1,3950))+''%''\r\n"
				+ "		UNION ALL\r\n" + "		SELECT\r\n" + "			LogId\r\n" + "			,NULL Application\r\n"
				+ "			,NULL ApplicationName\r\n" + "			,CONVERT(VARCHAR,ErrorDateTime,120) AS Logged\r\n"
				+ "			--,ErrorDateTime Logged\r\n" + "			,DBErrorDescription Message\r\n"
				+ "			,NULL Https\r\n"
				+ "			,Convert(NVARCHAR(16),CONNECTIONPROPERTY(''local_net_address'')) AS ServerAddress\r\n"
				+ "			,NULL RemoteAddress\r\n" + "			,''DB'' ErrFrom\r\n"
				+ "		FROM [DBErrorLog] A WITH (NOLOCK)\r\n"
				+ "		--WHERE ErrorDateTime >= ISNULL(@DateFrom, ErrorDateTime) AND ErrorDateTime <= ISNULL(DATEADD(SS,86399,@DateTo), ErrorDateTime)\r\n"
				+ "		WHERE ErrorDateTime >= ISNULL(@DateFrom, ErrorDateTime) AND ErrorDateTime <= ISNULL(@DateTo, ErrorDateTime)\r\n"
				+ "			AND DBErrorDescription LIKE  ''%''+ISNULL(@Message, DBErrorDescription)+''%''\r\n"
				+ "		) X \r\n" + "		WHERE ErrFrom = ISNULL(@ErrFrom, ErrFrom)\r\n"
				+ "			AND ServerAddress LIKE ''%''+ISNULL(@ServerAddress, ServerAddress)+''%''\r\n"
				+ "		--SELECT * FROM #TempData\r\n"
				+ "		--WHERE RN BETWEEN cast((@PageStartFrom+1) AS VARCHAR) AND cast(@PageTo AS VARCHAR) \r\n"
				+ "		--ORDER BY RN\r\n" + "		SELECT COUNT(1) TotalCount FROM #TempData \r\n"
				+ "		/*SELECT Count(1) AS CNT, CONVERT(DATE,Logged,120) Logged FROM #TempData GROUP BY CONVERT(DATE,Logged,120) ORDER BY Logged ASC */\r\n"
				+ "		--SELECT Count(1) AS CNT, CONVERT(DATE,dateadd(minute,@offset,Logged),120) Logged FROM #TempData GROUP BY CONVERT(DATE,dateadd(minute,@offset,Logged),120) ORDER BY Logged ASC\r\n"
				+ "		--SELECT Count(ID) AS CNT, ErrFrom as ApplicationName FROM #TempData GROUP BY ErrFrom \r\n"
				+ "		'   \r\n" + "    PRINT @SQL\r\n"
				+ "   SET @param = '@Application VARCHAR(10),@Message VARCHAR(MAX),@PageStartFrom INT,@PageTo INT , @DateFrom DATETIME, @DateTo DATETIME, @ErrFrom VARCHAR(100),@ServerAddress NVARCHAR(200),@offset INT	'\r\n"
				+ "   EXEC SP_EXECUTESQL @SQL,@param,@Application=@Application,@Message=@Message,@PageStartFrom = @PageStartFrom,@PageTo = @PageTo , @DateFrom=@DateFrom , @DateTo = @DateTo,@ErrFrom = @ErrFrom,@ServerAddress = @ServerAddress,@offset = @offset\r\n"
				+ "  END\r\n" + "  ELSE\r\n" + "  BEGIN\r\n" + "   SET @SQL = N'\r\n"
				+ "   		SELECT ROW_NUMBER() OVER (ORDER BY '+@OrderColumn+' '+@SortOrder+') AS RN,* INTO #TempData FROM (\r\n"
				+ "		SELECT \r\n" + "			A.ID\r\n" + "			,Application\r\n"
				+ "			,b.NAME ApplicationName\r\n" + "			,CONVERT(VARCHAR,Logged,120) Logged\r\n"
				+ "			--,Logged\r\n" + "			,Message\r\n" + "			,Https\r\n"
				+ "			,ServerAddress\r\n" + "			,RemoteAddress\r\n" + "			,b.NAME ErrFrom\r\n"
				+ "		FROM [Log] A WITH (NOLOCK)\r\n" + "		JOIN CommonMaster B ON A.Application = B.MasterCode\r\n"
				+ "			AND B.MasterType = ''Application''\r\n"
				+ "		--WHERE Logged >= ISNULL(@DateFrom, Logged) AND Logged <= ISNULL(DATEADD(SS,86399,@DateTo), Logged)\r\n"
				+ "		WHERE Logged >= ISNULL(@DateFrom, Logged) AND Logged <= ISNULL(@DateTo, Logged)\r\n"
				+ "			AND Application = ISNULL(@Application, Application)\r\n"
				+ "			AND LEVEL = ''Error''\r\n"
				+ "			AND Message LIKE  ''%''+ISNULL(@Message,substring(Message,1,3950))+''%''\r\n"
				+ "		UNION ALL\r\n" + "		SELECT\r\n" + "			LogId\r\n" + "			,NULL Application\r\n"
				+ "			,NULL ApplicationName\r\n" + "			,CONVERT(VARCHAR,ErrorDateTime,120) Logged\r\n"
				+ "			--,ErrorDateTime Logged\r\n" + "			,DBErrorDescription Message\r\n"
				+ "			,NULL Https\r\n"
				+ "			,Convert(NVARCHAR(16),CONNECTIONPROPERTY(''local_net_address'')) AS ServerAddress\r\n"
				+ "			,NULL RemoteAddress\r\n" + "			,''DB'' ErrFrom\r\n"
				+ "		FROM [DBErrorLog] A WITH (NOLOCK)\r\n"
				+ "		--WHERE ErrorDateTime >= ISNULL(@DateFrom, ErrorDateTime) AND ErrorDateTime <= ISNULL(DATEADD(SS,86399,@DateTo), ErrorDateTime)\r\n"
				+ "		WHERE ErrorDateTime >= ISNULL(@DateFrom, ErrorDateTime) AND ErrorDateTime <= ISNULL(@DateTo, ErrorDateTime)\r\n"
				+ "			AND DBErrorDescription LIKE  ''%''+ISNULL(@Message, DBErrorDescription)+''%''\r\n"
				+ "		) X \r\n" + "		WHERE ErrFrom = ISNULL(@ErrFrom, ErrFrom)\r\n"
				+ "			AND ServerAddress LIKE ''%''+ISNULL(@ServerAddress, ServerAddress)+''%''\r\n" + "\r\n"
				+ "		--SELECT RN,ID,Application,ApplicationName,dbo.Fn_ReportDate(Logged) Logged,Message,Https,ServerAddress,RemoteAddress,ErrFrom FROM #TempData ORDER BY RN\r\n"
				+ "		SELECT RN,ID,Application,ApplicationName,Logged,Message,Https,ServerAddress,RemoteAddress,ErrFrom FROM #TempData ORDER BY RN\r\n"
				+ "		SELECT Count(1) AS CNT, CONVERT(DATE,Logged,120) Logged FROM #TempData GROUP BY CONVERT(DATE,Logged,120) ORDER BY Logged DESC\r\n"
				+ "		SELECT Count(ID) AS CNT, ErrFrom as ApplicationName FROM #TempData GROUP BY ErrFrom \r\n"
				+ "		'   \r\n" + "    PRINT @SQL\r\n"
				+ "   SET @param = '@Application VARCHAR(10),@Message NVARCHAR(MAX),@PageStartFrom INT,@PageTo INT , @DateFrom DATETIME, @DateTo DATETIME, @ErrFrom VARCHAR(100),@ServerAddress VARCHAR(200)	'\r\n"
				+ "   EXEC SP_EXECUTESQL @SQL,@param,@Application=@Application,@Message=@Message,@PageStartFrom = @PageStartFrom,@PageTo = @PageTo , @DateFrom=@DateFrom , @DateTo = @DateTo,@ErrFrom = @ErrFrom,@ServerAddress = @ServerAddress\r\n"
				+ "  END";

		return query;
	}

	public static String getTotalErrorLogGraphDetails(String toDate, String fromDate) {

		String query = "\r\n" + "	declare \r\n" + "  @Application TINYINT			= NULL\r\n"
				+ " ,@ErrFrom	   VARCHAR(100)	= NULL\r\n" + " ,@DateFrom		DATETIME		= '" + fromDate
				+ "'\r\n" + " ,@DateTo		DATETIME		= '" + toDate + "'\r\n"
				+ " ,@OrderColumn  VARCHAR(MAX)	= 'Logged'\r\n" + " ,@SortOrder  VARCHAR(MAX)		= 'Desc'\r\n"
				+ " ,@Message     VARCHAR(MAX)	= NULL\r\n"
				+ " ,@Mode		   TINYINT			= 0 --0: Master Data,1:Detail\r\n"
				+ " ,@LogId	   INT				= Null\r\n" + " ,@PageStartFrom   INT			= 0\r\n"
				+ " ,@PageLength	   INT			= 1000\r\n" + " ,@ServerAddress   NVARCHAR(200)	= NULL\r\n"
				+ " ,@offset int ='+330'\r\n" + "\r\n" + "  DECLARE  @SQL			 NVARCHAR(MAX)\r\n"
				+ "		  ,@param		 NVARCHAR(MAX)\r\n" + "		  ,@TotalRecords INT\r\n"
				+ "		  ,@PageTo INT = @PageStartFrom+@PageLength\r\n" + "  IF @Mode = 0\r\n" + "  BEGIN \r\n"
				+ "	SET @SQL = N'\r\n"
				+ "		SELECT ROW_NUMBER() OVER (ORDER BY '+@OrderColumn+' '+@SortOrder+') AS RN,* INTO #TempData FROM (\r\n"
				+ "		SELECT \r\n" + "			A.ID\r\n" + "			,Application\r\n"
				+ "			,b.NAME ApplicationName\r\n" + "			,CONVERT(VARCHAR,Logged,120) AS Logged\r\n"
				+ "			--,Logged\r\n" + "			,Message\r\n" + "			,Https\r\n"
				+ "			,ServerAddress\r\n" + "			,RemoteAddress\r\n" + "			,b.NAME ErrFrom\r\n"
				+ "		FROM [Log] A WITH (NOLOCK)\r\n" + "		JOIN CommonMaster B ON A.Application = B.MasterCode\r\n"
				+ "			AND B.MasterType = ''Application''\r\n"
				+ "		--WHERE Logged >= ISNULL(@DateFrom, Logged) AND Logged <= ISNULL(DATEADD(SS,86399,@DateTo), Logged)\r\n"
				+ "		WHERE Logged >= ISNULL(@DateFrom, Logged) AND Logged <= ISNULL(@DateTo, Logged)\r\n"
				+ "			AND Application = ISNULL(@Application, Application)\r\n"
				+ "			AND LEVEL = ''Error''\r\n"
				+ "			AND Message LIKE  ''%''+ISNULL(@Message,substring(Message,1,3950))+''%''\r\n"
				+ "		UNION ALL\r\n" + "		SELECT\r\n" + "			LogId\r\n" + "			,NULL Application\r\n"
				+ "			,NULL ApplicationName\r\n" + "			,CONVERT(VARCHAR,ErrorDateTime,120) AS Logged\r\n"
				+ "			--,ErrorDateTime Logged\r\n" + "			,DBErrorDescription Message\r\n"
				+ "			,NULL Https\r\n"
				+ "			,Convert(NVARCHAR(16),CONNECTIONPROPERTY(''local_net_address'')) AS ServerAddress\r\n"
				+ "			,NULL RemoteAddress\r\n" + "			,''DB'' ErrFrom\r\n"
				+ "		FROM [DBErrorLog] A WITH (NOLOCK)\r\n"
				+ "		--WHERE ErrorDateTime >= ISNULL(@DateFrom, ErrorDateTime) AND ErrorDateTime <= ISNULL(DATEADD(SS,86399,@DateTo), ErrorDateTime)\r\n"
				+ "		WHERE ErrorDateTime >= ISNULL(@DateFrom, ErrorDateTime) AND ErrorDateTime <= ISNULL(@DateTo, ErrorDateTime)\r\n"
				+ "			AND DBErrorDescription LIKE  ''%''+ISNULL(@Message, DBErrorDescription)+''%''\r\n"
				+ "		) X \r\n" + "		WHERE ErrFrom = ISNULL(@ErrFrom, ErrFrom)\r\n"
				+ "			AND ServerAddress LIKE ''%''+ISNULL(@ServerAddress, ServerAddress)+''%''\r\n"
				+ "		--SELECT * FROM #TempData\r\n"
				+ "		--WHERE RN BETWEEN cast((@PageStartFrom+1) AS VARCHAR) AND cast(@PageTo AS VARCHAR) \r\n"
				+ "		--ORDER BY RN\r\n" + "		--SELECT COUNT(1) TotalCount FROM #TempData \r\n"
				+ "		/*SELECT Count(1) AS CNT, CONVERT(DATE,Logged,120) Logged FROM #TempData GROUP BY CONVERT(DATE,Logged,120) ORDER BY Logged ASC */\r\n"
				+ "		SELECT Count(1) AS CNT, CONVERT(DATE,dateadd(minute,@offset,Logged),120) Logged FROM #TempData GROUP BY CONVERT(DATE,dateadd(minute,@offset,Logged),120) ORDER BY Logged ASC\r\n"
				+ "		--SELECT Count(ID) AS CNT, ErrFrom as ApplicationName FROM #TempData GROUP BY ErrFrom \r\n"
				+ "		'   \r\n" + "    PRINT @SQL\r\n"
				+ "   SET @param = '@Application VARCHAR(10),@Message VARCHAR(MAX),@PageStartFrom INT,@PageTo INT , @DateFrom DATETIME, @DateTo DATETIME, @ErrFrom VARCHAR(100),@ServerAddress NVARCHAR(200),@offset INT	'\r\n"
				+ "   EXEC SP_EXECUTESQL @SQL,@param,@Application=@Application,@Message=@Message,@PageStartFrom = @PageStartFrom,@PageTo = @PageTo , @DateFrom=@DateFrom , @DateTo = @DateTo,@ErrFrom = @ErrFrom,@ServerAddress = @ServerAddress,@offset = @offset\r\n"
				+ "  END\r\n" + "  ELSE\r\n" + "  BEGIN\r\n" + "   SET @SQL = N'\r\n"
				+ "   		SELECT ROW_NUMBER() OVER (ORDER BY '+@OrderColumn+' '+@SortOrder+') AS RN,* INTO #TempData FROM (\r\n"
				+ "		SELECT \r\n" + "			A.ID\r\n" + "			,Application\r\n"
				+ "			,b.NAME ApplicationName\r\n" + "			,CONVERT(VARCHAR,Logged,120) Logged\r\n"
				+ "			--,Logged\r\n" + "			,Message\r\n" + "			,Https\r\n"
				+ "			,ServerAddress\r\n" + "			,RemoteAddress\r\n" + "			,b.NAME ErrFrom\r\n"
				+ "		FROM [Log] A WITH (NOLOCK)\r\n" + "		JOIN CommonMaster B ON A.Application = B.MasterCode\r\n"
				+ "			AND B.MasterType = ''Application''\r\n"
				+ "		--WHERE Logged >= ISNULL(@DateFrom, Logged) AND Logged <= ISNULL(DATEADD(SS,86399,@DateTo), Logged)\r\n"
				+ "		WHERE Logged >= ISNULL(@DateFrom, Logged) AND Logged <= ISNULL(@DateTo, Logged)\r\n"
				+ "			AND Application = ISNULL(@Application, Application)\r\n"
				+ "			AND LEVEL = ''Error''\r\n"
				+ "			AND Message LIKE  ''%''+ISNULL(@Message,substring(Message,1,3950))+''%''\r\n"
				+ "		UNION ALL\r\n" + "		SELECT\r\n" + "			LogId\r\n" + "			,NULL Application\r\n"
				+ "			,NULL ApplicationName\r\n" + "			,CONVERT(VARCHAR,ErrorDateTime,120) Logged\r\n"
				+ "			--,ErrorDateTime Logged\r\n" + "			,DBErrorDescription Message\r\n"
				+ "			,NULL Https\r\n"
				+ "			,Convert(NVARCHAR(16),CONNECTIONPROPERTY(''local_net_address'')) AS ServerAddress\r\n"
				+ "			,NULL RemoteAddress\r\n" + "			,''DB'' ErrFrom\r\n"
				+ "		FROM [DBErrorLog] A WITH (NOLOCK)\r\n"
				+ "		--WHERE ErrorDateTime >= ISNULL(@DateFrom, ErrorDateTime) AND ErrorDateTime <= ISNULL(DATEADD(SS,86399,@DateTo), ErrorDateTime)\r\n"
				+ "		WHERE ErrorDateTime >= ISNULL(@DateFrom, ErrorDateTime) AND ErrorDateTime <= ISNULL(@DateTo, ErrorDateTime)\r\n"
				+ "			AND DBErrorDescription LIKE  ''%''+ISNULL(@Message, DBErrorDescription)+''%''\r\n"
				+ "		) X \r\n" + "		WHERE ErrFrom = ISNULL(@ErrFrom, ErrFrom)\r\n"
				+ "			AND ServerAddress LIKE ''%''+ISNULL(@ServerAddress, ServerAddress)+''%''\r\n" + "\r\n"
				+ "		--SELECT RN,ID,Application,ApplicationName,dbo.Fn_ReportDate(Logged) Logged,Message,Https,ServerAddress,RemoteAddress,ErrFrom FROM #TempData ORDER BY RN\r\n"
				+ "		SELECT RN,ID,Application,ApplicationName,Logged,Message,Https,ServerAddress,RemoteAddress,ErrFrom FROM #TempData ORDER BY RN\r\n"
				+ "		SELECT Count(1) AS CNT, CONVERT(DATE,Logged,120) Logged FROM #TempData GROUP BY CONVERT(DATE,Logged,120) ORDER BY Logged DESC\r\n"
				+ "		SELECT Count(ID) AS CNT, ErrFrom as ApplicationName FROM #TempData GROUP BY ErrFrom \r\n"
				+ "		'   \r\n" + "    PRINT @SQL\r\n"
				+ "   SET @param = '@Application VARCHAR(10),@Message NVARCHAR(MAX),@PageStartFrom INT,@PageTo INT , @DateFrom DATETIME, @DateTo DATETIME, @ErrFrom VARCHAR(100),@ServerAddress VARCHAR(200)	'\r\n"
				+ "   EXEC SP_EXECUTESQL @SQL,@param,@Application=@Application,@Message=@Message,@PageStartFrom = @PageStartFrom,@PageTo = @PageTo , @DateFrom=@DateFrom , @DateTo = @DateTo,@ErrFrom = @ErrFrom,@ServerAddress = @ServerAddress\r\n"
				+ "  END";

		return query;

	}

	public static String getTotalErrorLogPieDetails(String toDate, String fromDate) {

		String query = "declare \r\n" + "  @Application TINYINT			= NULL\r\n"
				+ " ,@ErrFrom	   VARCHAR(100)	= NULL\r\n" + " ,@DateFrom		DATETIME		= '" + fromDate
				+ "'\r\n" + " ,@DateTo		DATETIME		= '" + toDate + "'\r\n"
				+ " ,@OrderColumn  VARCHAR(MAX)	= 'Logged'\r\n" + " ,@SortOrder  VARCHAR(MAX)		= 'Desc'\r\n"
				+ " ,@Message     VARCHAR(MAX)	= NULL\r\n"
				+ " ,@Mode		   TINYINT			= 0 --0: Master Data,1:Detail\r\n"
				+ " ,@LogId	   INT				= Null\r\n" + " ,@PageStartFrom   INT			= 0\r\n"
				+ " ,@PageLength	   INT			= 1000\r\n" + " ,@ServerAddress   NVARCHAR(200)	= NULL\r\n"
				+ " ,@offset int ='+330'\r\n" + "\r\n" + "  DECLARE  @SQL			 NVARCHAR(MAX)\r\n"
				+ "		  ,@param		 NVARCHAR(MAX)\r\n" + "		  ,@TotalRecords INT\r\n"
				+ "		  ,@PageTo INT = @PageStartFrom+@PageLength\r\n" + "  IF @Mode = 0\r\n" + "  BEGIN \r\n"
				+ "	SET @SQL = N'\r\n"
				+ "		SELECT ROW_NUMBER() OVER (ORDER BY '+@OrderColumn+' '+@SortOrder+') AS RN,* INTO #TempData FROM (\r\n"
				+ "		SELECT \r\n" + "			A.ID\r\n" + "			,Application\r\n"
				+ "			,b.NAME ApplicationName\r\n" + "			,CONVERT(VARCHAR,Logged,120) AS Logged\r\n"
				+ "			--,Logged\r\n" + "			,Message\r\n" + "			,Https\r\n"
				+ "			,ServerAddress\r\n" + "			,RemoteAddress\r\n" + "			,b.NAME ErrFrom\r\n"
				+ "		FROM [Log] A WITH (NOLOCK)\r\n" + "		JOIN CommonMaster B ON A.Application = B.MasterCode\r\n"
				+ "			AND B.MasterType = ''Application''\r\n"
				+ "		--WHERE Logged >= ISNULL(@DateFrom, Logged) AND Logged <= ISNULL(DATEADD(SS,86399,@DateTo), Logged)\r\n"
				+ "		WHERE Logged >= ISNULL(@DateFrom, Logged) AND Logged <= ISNULL(@DateTo, Logged)\r\n"
				+ "			AND Application = ISNULL(@Application, Application)\r\n"
				+ "			AND LEVEL = ''Error''\r\n"
				+ "			AND Message LIKE  ''%''+ISNULL(@Message,substring(Message,1,3950))+''%''\r\n"
				+ "		UNION ALL\r\n" + "		SELECT\r\n" + "			LogId\r\n" + "			,NULL Application\r\n"
				+ "			,NULL ApplicationName\r\n" + "			,CONVERT(VARCHAR,ErrorDateTime,120) AS Logged\r\n"
				+ "			--,ErrorDateTime Logged\r\n" + "			,DBErrorDescription Message\r\n"
				+ "			,NULL Https\r\n"
				+ "			,Convert(NVARCHAR(16),CONNECTIONPROPERTY(''local_net_address'')) AS ServerAddress\r\n"
				+ "			,NULL RemoteAddress\r\n" + "			,''DB'' ErrFrom\r\n"
				+ "		FROM [DBErrorLog] A WITH (NOLOCK)\r\n"
				+ "		--WHERE ErrorDateTime >= ISNULL(@DateFrom, ErrorDateTime) AND ErrorDateTime <= ISNULL(DATEADD(SS,86399,@DateTo), ErrorDateTime)\r\n"
				+ "		WHERE ErrorDateTime >= ISNULL(@DateFrom, ErrorDateTime) AND ErrorDateTime <= ISNULL(@DateTo, ErrorDateTime)\r\n"
				+ "			AND DBErrorDescription LIKE  ''%''+ISNULL(@Message, DBErrorDescription)+''%''\r\n"
				+ "		) X \r\n" + "		WHERE ErrFrom = ISNULL(@ErrFrom, ErrFrom)\r\n"
				+ "			AND ServerAddress LIKE ''%''+ISNULL(@ServerAddress, ServerAddress)+''%''\r\n"
				+ "		--SELECT * FROM #TempData\r\n"
				+ "		--WHERE RN BETWEEN cast((@PageStartFrom+1) AS VARCHAR) AND cast(@PageTo AS VARCHAR) \r\n"
				+ "		--ORDER BY RN\r\n" + "		--SELECT COUNT(1) TotalCount FROM #TempData \r\n"
				+ "		/*SELECT Count(1) AS CNT, CONVERT(DATE,Logged,120) Logged FROM #TempData GROUP BY CONVERT(DATE,Logged,120) ORDER BY Logged ASC */\r\n"
				+ "		--SELECT Count(1) AS CNT, CONVERT(DATE,dateadd(minute,@offset,Logged),120) Logged FROM #TempData GROUP BY CONVERT(DATE,dateadd(minute,@offset,Logged),120) ORDER BY Logged ASC\r\n"
				+ "		SELECT Count(ID) AS CNT, ErrFrom as ApplicationName FROM #TempData GROUP BY ErrFrom \r\n"
				+ "		'   \r\n" + "    PRINT @SQL\r\n"
				+ "   SET @param = '@Application VARCHAR(10),@Message VARCHAR(MAX),@PageStartFrom INT,@PageTo INT , @DateFrom DATETIME, @DateTo DATETIME, @ErrFrom VARCHAR(100),@ServerAddress NVARCHAR(200),@offset INT	'\r\n"
				+ "   EXEC SP_EXECUTESQL @SQL,@param,@Application=@Application,@Message=@Message,@PageStartFrom = @PageStartFrom,@PageTo = @PageTo , @DateFrom=@DateFrom , @DateTo = @DateTo,@ErrFrom = @ErrFrom,@ServerAddress = @ServerAddress,@offset = @offset\r\n"
				+ "  END\r\n" + "  ELSE\r\n" + "  BEGIN\r\n" + "   SET @SQL = N'\r\n"
				+ "   		SELECT ROW_NUMBER() OVER (ORDER BY '+@OrderColumn+' '+@SortOrder+') AS RN,* INTO #TempData FROM (\r\n"
				+ "		SELECT \r\n" + "			A.ID\r\n" + "			,Application\r\n"
				+ "			,b.NAME ApplicationName\r\n" + "			,CONVERT(VARCHAR,Logged,120) Logged\r\n"
				+ "			--,Logged\r\n" + "			,Message\r\n" + "			,Https\r\n"
				+ "			,ServerAddress\r\n" + "			,RemoteAddress\r\n" + "			,b.NAME ErrFrom\r\n"
				+ "		FROM [Log] A WITH (NOLOCK)\r\n" + "		JOIN CommonMaster B ON A.Application = B.MasterCode\r\n"
				+ "			AND B.MasterType = ''Application''\r\n"
				+ "		--WHERE Logged >= ISNULL(@DateFrom, Logged) AND Logged <= ISNULL(DATEADD(SS,86399,@DateTo), Logged)\r\n"
				+ "		WHERE Logged >= ISNULL(@DateFrom, Logged) AND Logged <= ISNULL(@DateTo, Logged)\r\n"
				+ "			AND Application = ISNULL(@Application, Application)\r\n"
				+ "			AND LEVEL = ''Error''\r\n"
				+ "			AND Message LIKE  ''%''+ISNULL(@Message,substring(Message,1,3950))+''%''\r\n"
				+ "		UNION ALL\r\n" + "		SELECT\r\n" + "			LogId\r\n" + "			,NULL Application\r\n"
				+ "			,NULL ApplicationName\r\n" + "			,CONVERT(VARCHAR,ErrorDateTime,120) Logged\r\n"
				+ "			--,ErrorDateTime Logged\r\n" + "			,DBErrorDescription Message\r\n"
				+ "			,NULL Https\r\n"
				+ "			,Convert(NVARCHAR(16),CONNECTIONPROPERTY(''local_net_address'')) AS ServerAddress\r\n"
				+ "			,NULL RemoteAddress\r\n" + "			,''DB'' ErrFrom\r\n"
				+ "		FROM [DBErrorLog] A WITH (NOLOCK)\r\n"
				+ "		--WHERE ErrorDateTime >= ISNULL(@DateFrom, ErrorDateTime) AND ErrorDateTime <= ISNULL(DATEADD(SS,86399,@DateTo), ErrorDateTime)\r\n"
				+ "		WHERE ErrorDateTime >= ISNULL(@DateFrom, ErrorDateTime) AND ErrorDateTime <= ISNULL(@DateTo, ErrorDateTime)\r\n"
				+ "			AND DBErrorDescription LIKE  ''%''+ISNULL(@Message, DBErrorDescription)+''%''\r\n"
				+ "		) X \r\n" + "		WHERE ErrFrom = ISNULL(@ErrFrom, ErrFrom)\r\n"
				+ "			AND ServerAddress LIKE ''%''+ISNULL(@ServerAddress, ServerAddress)+''%''\r\n" + "\r\n"
				+ "		--SELECT RN,ID,Application,ApplicationName,dbo.Fn_ReportDate(Logged) Logged,Message,Https,ServerAddress,RemoteAddress,ErrFrom FROM #TempData ORDER BY RN\r\n"
				+ "		SELECT RN,ID,Application,ApplicationName,Logged,Message,Https,ServerAddress,RemoteAddress,ErrFrom FROM #TempData ORDER BY RN\r\n"
				+ "		SELECT Count(1) AS CNT, CONVERT(DATE,Logged,120) Logged FROM #TempData GROUP BY CONVERT(DATE,Logged,120) ORDER BY Logged DESC\r\n"
				+ "		SELECT Count(ID) AS CNT, ErrFrom as ApplicationName FROM #TempData GROUP BY ErrFrom \r\n"
				+ "		'   \r\n" + "    PRINT @SQL\r\n"
				+ "   SET @param = '@Application VARCHAR(10),@Message NVARCHAR(MAX),@PageStartFrom INT,@PageTo INT , @DateFrom DATETIME, @DateTo DATETIME, @ErrFrom VARCHAR(100),@ServerAddress VARCHAR(200)	'\r\n"
				+ "   EXEC SP_EXECUTESQL @SQL,@param,@Application=@Application,@Message=@Message,@PageStartFrom = @PageStartFrom,@PageTo = @PageTo , @DateFrom=@DateFrom , @DateTo = @DateTo,@ErrFrom = @ErrFrom,@ServerAddress = @ServerAddress\r\n"
				+ "  END\r\n" + " \r\n" + "\r\n" + "";

		return query;
	}

	public static String getTotalRegistrationLogCount(String toDate, String fromDate) {

		String query = "declare \r\n" + "	 @DateFrom DATETIME='" + fromDate + "'\r\n" + "	,@DateTo DATETIME='"
				+ toDate + "'\r\n" + "	,@Status TINYINT=NULL\r\n"
				+ "	,@Mode INT = 1	--Mode: 0-Summary,1-Detail ,2- ExportExcel\r\n"
				+ "	,@OrderColumn VARCHAR(50) = 'RegistrationDate'\r\n" + "	,@SortOrder VARCHAR(50) = 'DESC'\r\n"
				+ "	,@PageLength INT = 500\r\n" + "	,@PageStartFrom INT = 0\r\n"
				+ "	,@EmailId			VARCHAR(100) = NULL\r\n" + "	,@UtilityAccount	VARCHAR(100) = NULL\r\n"
				+ "	,@RegistrationDate	DATE = NULL\r\n" + "	,@ActivationDate	DATE = NULL\r\n"
				+ "	,@RoleName			VARCHAR(100) = NULL\r\n" + "	,@Username			VARCHAR(100) = NULL\r\n"
				+ "	,@ZipCode			VARCHAR(100) = NULL\r\n" + "	,@Message			VARCHAR(MAX) = NULL\r\n"
				+ "	,@RegistrationSource Varchar(10) = Null\r\n" + "	 ,@offset int ='+330'\r\n" + "\r\n" + "	\r\n"
				+ "	DECLARE @SQL NVARCHAR(MAX),@param  AS NVARCHAR(MAX),@PageTo INT = @PageStartFrom+@PageLength\r\n"
				+ "\r\n" + "	IF @Mode = 1\r\n" + "	BEGIN\r\n"
				+ "		SET @SQL =N'SELECT row_number() OVER (ORDER BY '+QUOTENAME(@OrderColumn)+' '+@SortOrder +') AS RN,\r\n"
				+ "					 UtilityAccountNumber\r\n"
				+ "					,(CASE IsCSR WHEN 0 THEN ''SCP'' ELSE ''CSP'' END) AS RegistrationSource\r\n"
				+ "					,(CASE \r\n" + "						WHEN STATUS = 1\r\n"
				+ "							THEN '' Success ''	ELSE '' Failed ''\r\n"
				+ "					  END) AS [Status]\r\n" + "					,ISNULL(Message, '''') AS Message\r\n"
				+ "					,RegistrationDate\r\n" + "					,ActivationDate\r\n"
				+ "					,RoleName\r\n" + "					,Username\r\n"
				+ "					,EmailID\r\n" + "					,ZipCode\r\n" + "					,OSType\r\n"
				+ "				INTO #TemptData \r\n" + "				FROM RegistrationLog(NOLOCK)\r\n"
				+ "				--WHERE (@DateFrom IS NULL OR CONVERT(DATE,RegistrationDate) BETWEEN @DateFrom AND @DateTo)\r\n"
				+ "				WHERE (@DateFrom IS NULL OR RegistrationDate BETWEEN @DateFrom AND @DateTo)\r\n"
				+ "					AND Status=ISNULL(@Status,Status)\r\n"
				+ "					AND EmailId LIKE ''%''+ISNULL(@EmailId,EmailId)+''%''\r\n"
				+ "					AND UtilityAccountNumber LIKE ''%''+ISNULL(@UtilityAccount,UtilityAccountNumber)+''%''\r\n"
				+ "					AND CONVERT(DATE,RegistrationDate) = CONVERT(DATE,ISNULL(@RegistrationDate,RegistrationDate))\r\n"
				+ "					AND ISNULL(CONVERT(DATE,ActivationDate),'''') = ISNULL(CONVERT(DATE,ISNULL(@ActivationDate,ActivationDate)),'''')\r\n"
				+ "					AND ISNULL(RoleName,'''') LIKE ''%''+ISNULL(@RoleName,'''')+''%''\r\n"
				+ "					AND ISNULL(Username,'''') LIKE ''%''+ISNULL(@Username,'''')+''%''\r\n"
				+ "					AND ISNULL(ZipCode,'''') LIKE ''%''+ISNULL(@ZipCode,'''')+''%''\r\n"
				+ "					AND ISNULL(Message,'''') LIKE ''%''+ISNULL(@Message,'''')+''%''\r\n"
				+ "					And ((CASE IsCSR WHEN 0 THEN ''SCP'' ELSE ''CSP'' END) = IsNull(@RegistrationSource,IsCSR) or Isnull(@RegistrationSource,'''')='''')\r\n"
				+ "				\r\n" + "			\r\n" + "		/*SELECT RN\r\n"
				+ "			,UtilityAccountNumber\r\n" + "			,RegistrationSource\r\n"
				+ "			,[Status]\r\n" + "			,Message\r\n"
				+ "			,CONVERT(VARCHAR(19),RegistrationDate,120) AS RegistrationDate\r\n"
				+ "			,CONVERT(VARCHAR(19),ActivationDate,120) AS ActivationDate\r\n" + "			,RoleName\r\n"
				+ "			,Username\r\n" + "			,EmailID\r\n" + "			,ZipCode\r\n"
				+ "			,OSType\r\n" + "		FROM #TemptData\r\n"
				+ "		WHERE RN BETWEEN cast((CONVERT(VARCHAR(3),@PageStartFrom)+1) AS VARCHAR) AND cast(CONVERT(VARCHAR(3),@PageTo) AS VARCHAR)\r\n"
				+ "		--And RegistrationSource = IsNull(@RegistrationSource,RegistrationSource)\r\n"
				+ "		ORDER BY RN */\r\n"
				+ "		SELECT COUNT(1) TotalCount FROM #TemptData --Where  RegistrationSource = IsNull(@RegistrationSource,RegistrationSource)\r\n"
				+ "		/* SELECT ISNULL([Status], '' Total '') [Status]\r\n" + "			,COUNT(1) StatCount\r\n"
				+ "		FROM #TemptData --Where RegistrationSource = IsNull(@RegistrationSource,RegistrationSource)\r\n"
				+ "		GROUP BY CUBE(STATUS)\r\n" + "		ORDER BY Status */\r\n"
				+ "		/* SELECT CONVERT(DATE,RegistrationDate) AttemptDate,COUNT(1) Count  FROM #TemptData --Where RegistrationSource = IsNull(@RegistrationSource,RegistrationSource)\r\n"
				+ "		GROUP BY CONVERT(DATE,RegistrationDate) ORDER BY AttemptDate DESC */\r\n"
				+ "		/* SELECT CONVERT(DATE,dateadd(minute,@offset,RegistrationDate),120) AttemptDate,COUNT(1) Count  FROM #TemptData \r\n"
				+ "		GROUP BY CONVERT(DATE,dateadd(minute,@offset,RegistrationDate),120) ORDER BY AttemptDate DESC */\r\n"
				+ "\r\n" + "		'\r\n" + "		PRINT @SQL\r\n"
				+ "		SET @param=N'@DateFrom DATETIME,@DateTo DATETIME,@PageStartFrom INT,@PageTo INT,@Status TINYINT,@EmailId VARCHAR(100),@UtilityAccount VARCHAR(100)\r\n"
				+ "					,@RegistrationDate DATE,@ActivationDate	DATE,@RoleName VARCHAR(100),@Username VARCHAR(100),@ZipCode VARCHAR(100),@Message VARCHAR(MAX),@RegistrationSource Varchar(10),@offset INT '\r\n"
				+ "		EXEC SP_EXECUTESQL @SQL,@param,@DateFrom,@DateTo,@PageStartFrom ,@PageTo ,@Status,@EmailId,@UtilityAccount \r\n"
				+ "					,@RegistrationDate,@ActivationDate,@RoleName,@Username ,@ZipCode,@Message,@RegistrationSource,@offset = @offset\r\n"
				+ "		print @DateFrom\r\n" + "		print @DateTo\r\n" + "	END\r\n" + "	ELSE \r\n"
				+ "	BEGIN\r\n" + "		Set @SQL =N'\r\n"
				+ "		SELECT row_number() OVER (ORDER BY '+QUOTENAME(@OrderColumn)+' '+@SortOrder +') AS RN,\r\n"
				+ "					 UtilityAccountNumber\r\n"
				+ "					,(CASE IsCSR WHEN 0 THEN ''SCP'' ELSE ''CSP'' END) AS RegistrationSource\r\n"
				+ "					,(CASE \r\n" + "						WHEN STATUS = 1\r\n"
				+ "							THEN '' Success ''	ELSE '' Failed ''\r\n"
				+ "					  END) AS [Status]\r\n" + "					,ISNULL(Message, '''') AS Message\r\n"
				+ "					,RegistrationDate\r\n" + "					,ActivationDate\r\n"
				+ "					,RoleName\r\n" + "					,Username\r\n"
				+ "					,EmailID\r\n" + "					,ZipCode\r\n" + "					,OSType\r\n"
				+ "				INTO #TemptData \r\n" + "				FROM RegistrationLog(NOLOCK)\r\n"
				+ "				WHERE (@DateFrom IS NULL OR CONVERT(DATE,RegistrationDate) BETWEEN @DateFrom AND @DateTo)\r\n"
				+ "					AND Status=ISNULL(@Status,Status)\r\n"
				+ "					AND EmailId LIKE ''%''+ISNULL(@EmailId,EmailId)+''%''\r\n"
				+ "					AND UtilityAccountNumber LIKE ''%''+ISNULL(@UtilityAccount,UtilityAccountNumber)+''%''\r\n"
				+ "					AND CONVERT(DATE,RegistrationDate) = CONVERT(DATE,ISNULL(@RegistrationDate,RegistrationDate))\r\n"
				+ "					AND ISNULL(CONVERT(DATE,ActivationDate),'''') = ISNULL(CONVERT(DATE,ISNULL(@ActivationDate,ActivationDate)),'''')\r\n"
				+ "					AND ISNULL(RoleName,'''') LIKE ''%''+ISNULL(@RoleName,'''')+''%''\r\n"
				+ "					AND ISNULL(Username,'''') LIKE ''%''+ISNULL(@Username,'''')+''%''\r\n"
				+ "					AND ISNULL(ZipCode,'''') LIKE ''%''+ISNULL(@ZipCode,'''')+''%''\r\n"
				+ "					AND ISNULL(Message,'''') LIKE ''%''+ISNULL(@Message,'''')+''%''\r\n"
				+ "					And ((CASE IsCSR WHEN 0 THEN ''SCP'' ELSE ''CSP'' END) = IsNull(@RegistrationSource,IsCSR) or Isnull(@RegistrationSource,'''')='''')\r\n"
				+ "			\r\n" + "		SELECT RN\r\n" + "			,UtilityAccountNumber\r\n"
				+ "			,RegistrationSource\r\n" + "			,[Status]\r\n" + "			,Message\r\n"
				+ "			--,dbo.Fn_ReportDate(RegistrationDate) RegistrationDate --\r\n"
				+ "			,CONVERT(VARCHAR(19),RegistrationDate,120) AS RegistrationDate\r\n"
				+ "			--,dbo.Fn_ReportDate(ActivationDate) ActivationDate --\r\n"
				+ "			,CONVERT(VARCHAR(19),ActivationDate,120) AS ActivationDate\r\n" + "			,RoleName\r\n"
				+ "			,Username\r\n" + "			,EmailID\r\n" + "			,ZipCode\r\n"
				+ "			,OSType\r\n" + "		FROM #TemptData\r\n"
				+ "		--Where RegistrationSource = IsNull(@RegistrationSource,RegistrationSource)\r\n"
				+ "		ORDER BY RN\r\n" + "		SELECT ISNULL([Status], '' Total '') [Status]\r\n"
				+ "			,COUNT(1) StatCount\r\n" + "		FROM #TemptData\r\n"
				+ "		--Where RegistrationSource = IsNull(@RegistrationSource,RegistrationSource)\r\n"
				+ "		GROUP BY CUBE(STATUS)\r\n" + "		ORDER BY Status\r\n"
				+ "		SELECT CONVERT(DATE,RegistrationDate) AttemptDate,COUNT(1) Count  FROM #TemptData \r\n"
				+ "		--Where RegistrationSource = IsNull(@RegistrationSource,RegistrationSource)\r\n"
				+ "		GROUP BY CONVERT(DATE,RegistrationDate) ORDER BY AttemptDate DESC'\r\n" + "		PRINT @SQL\r\n"
				+ "		SET @param=N'@DateFrom DATETIME,@DateTo DATETIME,@PageStartFrom INT,@PageTo INT,@Status TINYINT,@EmailId VARCHAR(100),@UtilityAccount VARCHAR(100)\r\n"
				+ "					,@RegistrationDate DATE,@ActivationDate	DATE,@RoleName VARCHAR(100),@Username VARCHAR(100),@ZipCode VARCHAR(100),@Message VARCHAR(MAX),@RegistrationSource Varchar(10) '\r\n"
				+ "		EXEC SP_EXECUTESQL @SQL,@param,@DateFrom,@DateTo,@PageStartFrom ,@PageTo ,@Status,@EmailId,@UtilityAccount\r\n"
				+ "					,@RegistrationDate,@ActivationDate,@RoleName,@Username ,@ZipCode,@Message,@RegistrationSource\r\n"
				+ "	END";

		return query;

	}

	public static String getTotalRegistrationLogGraphDetails(String toDate, String fromDate) {

		String query = "declare \r\n" + "	 @DateFrom DATETIME='" + fromDate + "'\r\n" + "	,@DateTo DATETIME='"
				+ toDate + "'\r\n" + "	,@Status TINYINT=NULL\r\n"
				+ "	,@Mode INT = 1	--Mode: 0-Summary,1-Detail ,2- ExportExcel\r\n"
				+ "	,@OrderColumn VARCHAR(50) = 'RegistrationDate'\r\n" + "	,@SortOrder VARCHAR(50) = 'DESC'\r\n"
				+ "	,@PageLength INT = 500\r\n" + "	,@PageStartFrom INT = 0\r\n"
				+ "	,@EmailId			VARCHAR(100) = NULL\r\n" + "	,@UtilityAccount	VARCHAR(100) = NULL\r\n"
				+ "	,@RegistrationDate	DATE = NULL\r\n" + "	,@ActivationDate	DATE = NULL\r\n"
				+ "	,@RoleName			VARCHAR(100) = NULL\r\n" + "	,@Username			VARCHAR(100) = NULL\r\n"
				+ "	,@ZipCode			VARCHAR(100) = NULL\r\n" + "	,@Message			VARCHAR(MAX) = NULL\r\n"
				+ "	,@RegistrationSource Varchar(10) = Null\r\n" + "	 ,@offset int ='+330'\r\n" + "\r\n" + "	\r\n"
				+ "	DECLARE @SQL NVARCHAR(MAX),@param  AS NVARCHAR(MAX),@PageTo INT = @PageStartFrom+@PageLength\r\n"
				+ "\r\n" + "	IF @Mode = 1\r\n" + "	BEGIN\r\n"
				+ "		SET @SQL =N'SELECT row_number() OVER (ORDER BY '+QUOTENAME(@OrderColumn)+' '+@SortOrder +') AS RN,\r\n"
				+ "					 UtilityAccountNumber\r\n"
				+ "					,(CASE IsCSR WHEN 0 THEN ''SCP'' ELSE ''CSP'' END) AS RegistrationSource\r\n"
				+ "					,(CASE \r\n" + "						WHEN STATUS = 1\r\n"
				+ "							THEN '' Success ''	ELSE '' Failed ''\r\n"
				+ "					  END) AS [Status]\r\n" + "					,ISNULL(Message, '''') AS Message\r\n"
				+ "					,RegistrationDate\r\n" + "					,ActivationDate\r\n"
				+ "					,RoleName\r\n" + "					,Username\r\n"
				+ "					,EmailID\r\n" + "					,ZipCode\r\n" + "					,OSType\r\n"
				+ "				INTO #TemptData \r\n" + "				FROM RegistrationLog(NOLOCK)\r\n"
				+ "				--WHERE (@DateFrom IS NULL OR CONVERT(DATE,RegistrationDate) BETWEEN @DateFrom AND @DateTo)\r\n"
				+ "				WHERE (@DateFrom IS NULL OR RegistrationDate BETWEEN @DateFrom AND @DateTo)\r\n"
				+ "					AND Status=ISNULL(@Status,Status)\r\n"
				+ "					AND EmailId LIKE ''%''+ISNULL(@EmailId,EmailId)+''%''\r\n"
				+ "					AND UtilityAccountNumber LIKE ''%''+ISNULL(@UtilityAccount,UtilityAccountNumber)+''%''\r\n"
				+ "					AND CONVERT(DATE,RegistrationDate) = CONVERT(DATE,ISNULL(@RegistrationDate,RegistrationDate))\r\n"
				+ "					AND ISNULL(CONVERT(DATE,ActivationDate),'''') = ISNULL(CONVERT(DATE,ISNULL(@ActivationDate,ActivationDate)),'''')\r\n"
				+ "					AND ISNULL(RoleName,'''') LIKE ''%''+ISNULL(@RoleName,'''')+''%''\r\n"
				+ "					AND ISNULL(Username,'''') LIKE ''%''+ISNULL(@Username,'''')+''%''\r\n"
				+ "					AND ISNULL(ZipCode,'''') LIKE ''%''+ISNULL(@ZipCode,'''')+''%''\r\n"
				+ "					AND ISNULL(Message,'''') LIKE ''%''+ISNULL(@Message,'''')+''%''\r\n"
				+ "					And ((CASE IsCSR WHEN 0 THEN ''SCP'' ELSE ''CSP'' END) = IsNull(@RegistrationSource,IsCSR) or Isnull(@RegistrationSource,'''')='''')\r\n"
				+ "				\r\n" + "			\r\n" + "		\r\n"
				+ "		SELECT CONVERT(DATE,dateadd(minute,@offset,RegistrationDate),120) AttemptDate,COUNT(1) Count  FROM #TemptData \r\n"
				+ "		GROUP BY CONVERT(DATE,dateadd(minute,@offset,RegistrationDate),120) ORDER BY AttemptDate DESC\r\n"
				+ "\r\n" + "		'\r\n" + "		PRINT @SQL\r\n"
				+ "		SET @param=N'@DateFrom DATETIME,@DateTo DATETIME,@PageStartFrom INT,@PageTo INT,@Status TINYINT,@EmailId VARCHAR(100),@UtilityAccount VARCHAR(100)\r\n"
				+ "					,@RegistrationDate DATE,@ActivationDate	DATE,@RoleName VARCHAR(100),@Username VARCHAR(100),@ZipCode VARCHAR(100),@Message VARCHAR(MAX),@RegistrationSource Varchar(10),@offset INT '\r\n"
				+ "		EXEC SP_EXECUTESQL @SQL,@param,@DateFrom,@DateTo,@PageStartFrom ,@PageTo ,@Status,@EmailId,@UtilityAccount \r\n"
				+ "					,@RegistrationDate,@ActivationDate,@RoleName,@Username ,@ZipCode,@Message,@RegistrationSource,@offset = @offset\r\n"
				+ "		print @DateFrom\r\n" + "		print @DateTo\r\n" + "	END\r\n" + "	ELSE \r\n"
				+ "	BEGIN\r\n" + "		Set @SQL =N'\r\n"
				+ "		SELECT row_number() OVER (ORDER BY '+QUOTENAME(@OrderColumn)+' '+@SortOrder +') AS RN,\r\n"
				+ "					 UtilityAccountNumber\r\n"
				+ "					,(CASE IsCSR WHEN 0 THEN ''SCP'' ELSE ''CSP'' END) AS RegistrationSource\r\n"
				+ "					,(CASE \r\n" + "						WHEN STATUS = 1\r\n"
				+ "							THEN '' Success ''	ELSE '' Failed ''\r\n"
				+ "					  END) AS [Status]\r\n" + "					,ISNULL(Message, '''') AS Message\r\n"
				+ "					,RegistrationDate\r\n" + "					,ActivationDate\r\n"
				+ "					,RoleName\r\n" + "					,Username\r\n"
				+ "					,EmailID\r\n" + "					,ZipCode\r\n" + "					,OSType\r\n"
				+ "				INTO #TemptData \r\n" + "				FROM RegistrationLog(NOLOCK)\r\n"
				+ "				WHERE (@DateFrom IS NULL OR CONVERT(DATE,RegistrationDate) BETWEEN @DateFrom AND @DateTo)\r\n"
				+ "					AND Status=ISNULL(@Status,Status)\r\n"
				+ "					AND EmailId LIKE ''%''+ISNULL(@EmailId,EmailId)+''%''\r\n"
				+ "					AND UtilityAccountNumber LIKE ''%''+ISNULL(@UtilityAccount,UtilityAccountNumber)+''%''\r\n"
				+ "					AND CONVERT(DATE,RegistrationDate) = CONVERT(DATE,ISNULL(@RegistrationDate,RegistrationDate))\r\n"
				+ "					AND ISNULL(CONVERT(DATE,ActivationDate),'''') = ISNULL(CONVERT(DATE,ISNULL(@ActivationDate,ActivationDate)),'''')\r\n"
				+ "					AND ISNULL(RoleName,'''') LIKE ''%''+ISNULL(@RoleName,'''')+''%''\r\n"
				+ "					AND ISNULL(Username,'''') LIKE ''%''+ISNULL(@Username,'''')+''%''\r\n"
				+ "					AND ISNULL(ZipCode,'''') LIKE ''%''+ISNULL(@ZipCode,'''')+''%''\r\n"
				+ "					AND ISNULL(Message,'''') LIKE ''%''+ISNULL(@Message,'''')+''%''\r\n"
				+ "					And ((CASE IsCSR WHEN 0 THEN ''SCP'' ELSE ''CSP'' END) = IsNull(@RegistrationSource,IsCSR) or Isnull(@RegistrationSource,'''')='''')\r\n"
				+ "			\r\n" + "		SELECT RN\r\n" + "			,UtilityAccountNumber\r\n"
				+ "			,RegistrationSource\r\n" + "			,[Status]\r\n" + "			,Message\r\n"
				+ "			--,dbo.Fn_ReportDate(RegistrationDate) RegistrationDate --\r\n"
				+ "			,CONVERT(VARCHAR(19),RegistrationDate,120) AS RegistrationDate\r\n"
				+ "			--,dbo.Fn_ReportDate(ActivationDate) ActivationDate --\r\n"
				+ "			,CONVERT(VARCHAR(19),ActivationDate,120) AS ActivationDate\r\n" + "			,RoleName\r\n"
				+ "			,Username\r\n" + "			,EmailID\r\n" + "			,ZipCode\r\n"
				+ "			,OSType\r\n" + "		FROM #TemptData\r\n"
				+ "		--Where RegistrationSource = IsNull(@RegistrationSource,RegistrationSource)\r\n"
				+ "		ORDER BY RN\r\n" + "		SELECT ISNULL([Status], '' Total '') [Status]\r\n"
				+ "			,COUNT(1) StatCount\r\n" + "		FROM #TemptData\r\n"
				+ "		--Where RegistrationSource = IsNull(@RegistrationSource,RegistrationSource)\r\n"
				+ "		GROUP BY CUBE(STATUS)\r\n" + "		ORDER BY Status\r\n"
				+ "		SELECT CONVERT(DATE,RegistrationDate) AttemptDate,COUNT(1) Count  FROM #TemptData \r\n"
				+ "		--Where RegistrationSource = IsNull(@RegistrationSource,RegistrationSource)\r\n"
				+ "		GROUP BY CONVERT(DATE,RegistrationDate) ORDER BY AttemptDate DESC'\r\n" + "		PRINT @SQL\r\n"
				+ "		SET @param=N'@DateFrom DATETIME,@DateTo DATETIME,@PageStartFrom INT,@PageTo INT,@Status TINYINT,@EmailId VARCHAR(100),@UtilityAccount VARCHAR(100)\r\n"
				+ "					,@RegistrationDate DATE,@ActivationDate	DATE,@RoleName VARCHAR(100),@Username VARCHAR(100),@ZipCode VARCHAR(100),@Message VARCHAR(MAX),@RegistrationSource Varchar(10) '\r\n"
				+ "		EXEC SP_EXECUTESQL @SQL,@param,@DateFrom,@DateTo,@PageStartFrom ,@PageTo ,@Status,@EmailId,@UtilityAccount\r\n"
				+ "					,@RegistrationDate,@ActivationDate,@RoleName,@Username ,@ZipCode,@Message,@RegistrationSource\r\n"
				+ "	END";

		return query;

	}

	public static String getTotalRegistrationLogPieDetails(String toDate, String fromDate) {

		String query = "declare \r\n" + "	 @DateFrom DATETIME='" + fromDate + "'\r\n" + "	,@DateTo DATETIME='"
				+ toDate + "'\r\n" + "	,@Status TINYINT=NULL\r\n"
				+ "	,@Mode INT = 1	--Mode: 0-Summary,1-Detail ,2- ExportExcel\r\n"
				+ "	,@OrderColumn VARCHAR(50) = 'RegistrationDate'\r\n" + "	,@SortOrder VARCHAR(50) = 'DESC'\r\n"
				+ "	,@PageLength INT = 500\r\n" + "	,@PageStartFrom INT = 0\r\n"
				+ "	,@EmailId			VARCHAR(100) = NULL\r\n" + "	,@UtilityAccount	VARCHAR(100) = NULL\r\n"
				+ "	,@RegistrationDate	DATE = NULL\r\n" + "	,@ActivationDate	DATE = NULL\r\n"
				+ "	,@RoleName			VARCHAR(100) = NULL\r\n" + "	,@Username			VARCHAR(100) = NULL\r\n"
				+ "	,@ZipCode			VARCHAR(100) = NULL\r\n" + "	,@Message			VARCHAR(MAX) = NULL\r\n"
				+ "	,@RegistrationSource Varchar(10) = Null\r\n" + "	 ,@offset int ='+330'\r\n" + "\r\n" + "	\r\n"
				+ "	DECLARE @SQL NVARCHAR(MAX),@param  AS NVARCHAR(MAX),@PageTo INT = @PageStartFrom+@PageLength\r\n"
				+ "\r\n" + "	IF @Mode = 1\r\n" + "	BEGIN\r\n"
				+ "		SET @SQL =N'SELECT row_number() OVER (ORDER BY '+QUOTENAME(@OrderColumn)+' '+@SortOrder +') AS RN,\r\n"
				+ "					 UtilityAccountNumber\r\n"
				+ "					,(CASE IsCSR WHEN 0 THEN ''SCP'' ELSE ''CSP'' END) AS RegistrationSource\r\n"
				+ "					,(CASE \r\n" + "						WHEN STATUS = 1\r\n"
				+ "							THEN '' Success ''	ELSE '' Failed ''\r\n"
				+ "					  END) AS [Status]\r\n" + "					,ISNULL(Message, '''') AS Message\r\n"
				+ "					,RegistrationDate\r\n" + "					,ActivationDate\r\n"
				+ "					,RoleName\r\n" + "					,Username\r\n"
				+ "					,EmailID\r\n" + "					,ZipCode\r\n" + "					,OSType\r\n"
				+ "				INTO #TemptData \r\n" + "				FROM RegistrationLog(NOLOCK)\r\n"
				+ "				--WHERE (@DateFrom IS NULL OR CONVERT(DATE,RegistrationDate) BETWEEN @DateFrom AND @DateTo)\r\n"
				+ "				WHERE (@DateFrom IS NULL OR RegistrationDate BETWEEN @DateFrom AND @DateTo)\r\n"
				+ "					AND Status=ISNULL(@Status,Status)\r\n"
				+ "					AND EmailId LIKE ''%''+ISNULL(@EmailId,EmailId)+''%''\r\n"
				+ "					AND UtilityAccountNumber LIKE ''%''+ISNULL(@UtilityAccount,UtilityAccountNumber)+''%''\r\n"
				+ "					AND CONVERT(DATE,RegistrationDate) = CONVERT(DATE,ISNULL(@RegistrationDate,RegistrationDate))\r\n"
				+ "					AND ISNULL(CONVERT(DATE,ActivationDate),'''') = ISNULL(CONVERT(DATE,ISNULL(@ActivationDate,ActivationDate)),'''')\r\n"
				+ "					AND ISNULL(RoleName,'''') LIKE ''%''+ISNULL(@RoleName,'''')+''%''\r\n"
				+ "					AND ISNULL(Username,'''') LIKE ''%''+ISNULL(@Username,'''')+''%''\r\n"
				+ "					AND ISNULL(ZipCode,'''') LIKE ''%''+ISNULL(@ZipCode,'''')+''%''\r\n"
				+ "					AND ISNULL(Message,'''') LIKE ''%''+ISNULL(@Message,'''')+''%''\r\n"
				+ "					And ((CASE IsCSR WHEN 0 THEN ''SCP'' ELSE ''CSP'' END) = IsNull(@RegistrationSource,IsCSR) or Isnull(@RegistrationSource,'''')='''')\r\n"
				+ "				\r\n" + "		\r\n" + "		\r\n"
				+ "		SELECT ISNULL([Status], '' Total '') [Status]\r\n" + "			,COUNT(1) StatCount\r\n"
				+ "		FROM #TemptData --Where RegistrationSource = IsNull(@RegistrationSource,RegistrationSource)\r\n"
				+ "		GROUP BY CUBE(STATUS)\r\n" + "		ORDER BY Status\r\n" + "		\r\n" + "\r\n"
				+ "		'\r\n" + "		PRINT @SQL\r\n"
				+ "		SET @param=N'@DateFrom DATETIME,@DateTo DATETIME,@PageStartFrom INT,@PageTo INT,@Status TINYINT,@EmailId VARCHAR(100),@UtilityAccount VARCHAR(100)\r\n"
				+ "					,@RegistrationDate DATE,@ActivationDate	DATE,@RoleName VARCHAR(100),@Username VARCHAR(100),@ZipCode VARCHAR(100),@Message VARCHAR(MAX),@RegistrationSource Varchar(10),@offset INT '\r\n"
				+ "		EXEC SP_EXECUTESQL @SQL,@param,@DateFrom,@DateTo,@PageStartFrom ,@PageTo ,@Status,@EmailId,@UtilityAccount \r\n"
				+ "					,@RegistrationDate,@ActivationDate,@RoleName,@Username ,@ZipCode,@Message,@RegistrationSource,@offset = @offset\r\n"
				+ "		print @DateFrom\r\n" + "		print @DateTo\r\n" + "	END\r\n" + "	ELSE \r\n"
				+ "	BEGIN\r\n" + "		Set @SQL =N'\r\n"
				+ "		SELECT row_number() OVER (ORDER BY '+QUOTENAME(@OrderColumn)+' '+@SortOrder +') AS RN,\r\n"
				+ "					 UtilityAccountNumber\r\n"
				+ "					,(CASE IsCSR WHEN 0 THEN ''SCP'' ELSE ''CSP'' END) AS RegistrationSource\r\n"
				+ "					,(CASE \r\n" + "						WHEN STATUS = 1\r\n"
				+ "							THEN '' Success ''	ELSE '' Failed ''\r\n"
				+ "					  END) AS [Status]\r\n" + "					,ISNULL(Message, '''') AS Message\r\n"
				+ "					,RegistrationDate\r\n" + "					,ActivationDate\r\n"
				+ "					,RoleName\r\n" + "					,Username\r\n"
				+ "					,EmailID\r\n" + "					,ZipCode\r\n" + "					,OSType\r\n"
				+ "				INTO #TemptData \r\n" + "				FROM RegistrationLog(NOLOCK)\r\n"
				+ "				WHERE (@DateFrom IS NULL OR CONVERT(DATE,RegistrationDate) BETWEEN @DateFrom AND @DateTo)\r\n"
				+ "					AND Status=ISNULL(@Status,Status)\r\n"
				+ "					AND EmailId LIKE ''%''+ISNULL(@EmailId,EmailId)+''%''\r\n"
				+ "					AND UtilityAccountNumber LIKE ''%''+ISNULL(@UtilityAccount,UtilityAccountNumber)+''%''\r\n"
				+ "					AND CONVERT(DATE,RegistrationDate) = CONVERT(DATE,ISNULL(@RegistrationDate,RegistrationDate))\r\n"
				+ "					AND ISNULL(CONVERT(DATE,ActivationDate),'''') = ISNULL(CONVERT(DATE,ISNULL(@ActivationDate,ActivationDate)),'''')\r\n"
				+ "					AND ISNULL(RoleName,'''') LIKE ''%''+ISNULL(@RoleName,'''')+''%''\r\n"
				+ "					AND ISNULL(Username,'''') LIKE ''%''+ISNULL(@Username,'''')+''%''\r\n"
				+ "					AND ISNULL(ZipCode,'''') LIKE ''%''+ISNULL(@ZipCode,'''')+''%''\r\n"
				+ "					AND ISNULL(Message,'''') LIKE ''%''+ISNULL(@Message,'''')+''%''\r\n"
				+ "					And ((CASE IsCSR WHEN 0 THEN ''SCP'' ELSE ''CSP'' END) = IsNull(@RegistrationSource,IsCSR) or Isnull(@RegistrationSource,'''')='''')\r\n"
				+ "			\r\n" + "		SELECT RN\r\n" + "			,UtilityAccountNumber\r\n"
				+ "			,RegistrationSource\r\n" + "			,[Status]\r\n" + "			,Message\r\n"
				+ "			--,dbo.Fn_ReportDate(RegistrationDate) RegistrationDate --\r\n"
				+ "			,CONVERT(VARCHAR(19),RegistrationDate,120) AS RegistrationDate\r\n"
				+ "			--,dbo.Fn_ReportDate(ActivationDate) ActivationDate --\r\n"
				+ "			,CONVERT(VARCHAR(19),ActivationDate,120) AS ActivationDate\r\n" + "			,RoleName\r\n"
				+ "			,Username\r\n" + "			,EmailID\r\n" + "			,ZipCode\r\n"
				+ "			,OSType\r\n" + "		FROM #TemptData\r\n"
				+ "		--Where RegistrationSource = IsNull(@RegistrationSource,RegistrationSource)\r\n"
				+ "		ORDER BY RN\r\n" + "		SELECT ISNULL([Status], '' Total '') [Status]\r\n"
				+ "			,COUNT(1) StatCount\r\n" + "		FROM #TemptData\r\n"
				+ "		--Where RegistrationSource = IsNull(@RegistrationSource,RegistrationSource)\r\n"
				+ "		GROUP BY CUBE(STATUS)\r\n" + "		ORDER BY Status\r\n"
				+ "		SELECT CONVERT(DATE,RegistrationDate) AttemptDate,COUNT(1) Count  FROM #TemptData \r\n"
				+ "		--Where RegistrationSource = IsNull(@RegistrationSource,RegistrationSource)\r\n"
				+ "		GROUP BY CONVERT(DATE,RegistrationDate) ORDER BY AttemptDate DESC'\r\n" + "		PRINT @SQL\r\n"
				+ "		SET @param=N'@DateFrom DATETIME,@DateTo DATETIME,@PageStartFrom INT,@PageTo INT,@Status TINYINT,@EmailId VARCHAR(100),@UtilityAccount VARCHAR(100)\r\n"
				+ "					,@RegistrationDate DATE,@ActivationDate	DATE,@RoleName VARCHAR(100),@Username VARCHAR(100),@ZipCode VARCHAR(100),@Message VARCHAR(MAX),@RegistrationSource Varchar(10) '\r\n"
				+ "		EXEC SP_EXECUTESQL @SQL,@param,@DateFrom,@DateTo,@PageStartFrom ,@PageTo ,@Status,@EmailId,@UtilityAccount\r\n"
				+ "					,@RegistrationDate,@ActivationDate,@RoleName,@Username ,@ZipCode,@Message,@RegistrationSource\r\n"
				+ "	END";

		return query;

	}

	// This query brings the number of Usage Months configured in CSP
	public static String sUsageMonthSettingsCspQuery = ""
			+ "select configOption, ConfigValue from utilityconfig  where configid=21";
	
public static String getFilterDataForRegistrationLog(String toDate, String fromDate, String email) {
		
		String query = "declare \r\n" + 
				"	 @DateFrom DATETIME='"+fromDate+"'\r\n" + 
				"	,@DateTo DATETIME='"+toDate+"'\r\n" + 
				"	,@Status TINYINT=NULL\r\n" + 
				"	,@Mode INT = 1	--Mode: 0-Summary,1-Detail ,2- ExportExcel\r\n" + 
				"	,@OrderColumn VARCHAR(50) = 'RegistrationDate'\r\n" + 
				"	,@SortOrder VARCHAR(50) = 'DESC'\r\n" + 
				"	,@PageLength INT = 200\r\n" + 
				"	,@PageStartFrom INT = 0\r\n" + 
				"	,@EmailId			VARCHAR(100) = '"+email+"'\r\n" + 
				"	,@UtilityAccount	VARCHAR(100) = NULL\r\n" + 
				"	,@RegistrationDate	DATE = NULL\r\n" + 
				"	,@ActivationDate	DATE = NULL\r\n" + 
				"	,@RoleName			VARCHAR(100) = NULL\r\n" + 
				"	,@Username			VARCHAR(100) = NULL\r\n" + 
				"	,@ZipCode			VARCHAR(100) = NULL\r\n" + 
				"	,@Message			VARCHAR(MAX) = NULL\r\n" + 
				"	,@RegistrationSource Varchar(10) = Null\r\n" + 
				"	 ,@offset int ='+330'\r\n" + 
				"\r\n" + 
				"	\r\n" + 
				"	DECLARE @SQL NVARCHAR(MAX),@param  AS NVARCHAR(MAX),@PageTo INT = @PageStartFrom+@PageLength\r\n" + 
				"\r\n" + 
				"	IF @Mode = 1\r\n" + 
				"	BEGIN\r\n" + 
				"		SET @SQL =N'SELECT row_number() OVER (ORDER BY '+QUOTENAME(@OrderColumn)+' '+@SortOrder +') AS RN,\r\n" + 
				"					 UtilityAccountNumber\r\n" + 
				"					,(CASE IsCSR WHEN 0 THEN ''SCP'' ELSE ''CSP'' END) AS RegistrationSource\r\n" + 
				"					,(CASE \r\n" + 
				"						WHEN STATUS = 1\r\n" + 
				"							THEN '' Success ''	ELSE '' Failed ''\r\n" + 
				"					  END) AS [Status]\r\n" + 
				"					,ISNULL(Message, '''') AS Message\r\n" + 
				"					,RegistrationDate\r\n" + 
				"					,ActivationDate\r\n" + 
				"					,RoleName\r\n" + 
				"					,Username\r\n" + 
				"					,EmailID\r\n" + 
				"					,ZipCode\r\n" + 
				"					,OSType\r\n" + 
				"				INTO #TemptData \r\n" + 
				"				FROM RegistrationLog(NOLOCK)\r\n" + 
				"				--WHERE (@DateFrom IS NULL OR CONVERT(DATE,RegistrationDate) BETWEEN @DateFrom AND @DateTo)\r\n" + 
				"				WHERE (@DateFrom IS NULL OR RegistrationDate BETWEEN @DateFrom AND @DateTo)\r\n" + 
				"					AND Status=ISNULL(@Status,Status)\r\n" + 
				"					AND EmailId LIKE ''%''+ISNULL(@EmailId,EmailId)+''%''\r\n" + 
				"					AND UtilityAccountNumber LIKE ''%''+ISNULL(@UtilityAccount,UtilityAccountNumber)+''%''\r\n" + 
				"					AND CONVERT(DATE,RegistrationDate) = CONVERT(DATE,ISNULL(@RegistrationDate,RegistrationDate))\r\n" + 
				"					AND ISNULL(CONVERT(DATE,ActivationDate),'''') = ISNULL(CONVERT(DATE,ISNULL(@ActivationDate,ActivationDate)),'''')\r\n" + 
				"					AND ISNULL(RoleName,'''') LIKE ''%''+ISNULL(@RoleName,'''')+''%''\r\n" + 
				"					AND ISNULL(Username,'''') LIKE ''%''+ISNULL(@Username,'''')+''%''\r\n" + 
				"					AND ISNULL(ZipCode,'''') LIKE ''%''+ISNULL(@ZipCode,'''')+''%''\r\n" + 
				"					AND ISNULL(Message,'''') LIKE ''%''+ISNULL(@Message,'''')+''%''\r\n" + 
				"					And ((CASE IsCSR WHEN 0 THEN ''SCP'' ELSE ''CSP'' END) = IsNull(@RegistrationSource,IsCSR) or Isnull(@RegistrationSource,'''')='''')\r\n" + 
				"				\r\n" + 
				"			\r\n" + 
				"		SELECT RN\r\n" + 
				"			,UtilityAccountNumber\r\n" + 
				"			,RegistrationSource\r\n" + 
				"			,[Status]\r\n" + 
				"			,Message\r\n" + 
				"			,CONVERT(VARCHAR(19),RegistrationDate,120) AS RegistrationDate\r\n" + 
				"			,CONVERT(VARCHAR(19),ActivationDate,120) AS ActivationDate\r\n" + 
				"			,RoleName\r\n" + 
				"			,Username\r\n" + 
				"			,EmailID\r\n" + 
				"			,ZipCode\r\n" + 
				"			,OSType\r\n" + 
				"		FROM #TemptData\r\n" + 
				"		WHERE RN BETWEEN cast((CONVERT(VARCHAR(3),@PageStartFrom)+1) AS VARCHAR) AND cast(CONVERT(VARCHAR(3),@PageTo) AS VARCHAR)\r\n" + 
				"		--And RegistrationSource = IsNull(@RegistrationSource,RegistrationSource)\r\n" + 
				"		ORDER BY RN\r\n" + 
				"		/*SELECT COUNT(1) TotalCount FROM #TemptData --Where  RegistrationSource = IsNull(@RegistrationSource,RegistrationSource)\r\n" + 
				"		SELECT ISNULL([Status], '' Total '') [Status]\r\n" + 
				"			,COUNT(1) StatCount\r\n" + 
				"		FROM #TemptData --Where RegistrationSource = IsNull(@RegistrationSource,RegistrationSource)\r\n" + 
				"		GROUP BY CUBE(STATUS)\r\n" + 
				"		ORDER BY Status\r\n" + 
				"		/* SELECT CONVERT(DATE,RegistrationDate) AttemptDate,COUNT(1) Count  FROM #TemptData --Where RegistrationSource = IsNull(@RegistrationSource,RegistrationSource)\r\n" + 
				"		GROUP BY CONVERT(DATE,RegistrationDate) ORDER BY AttemptDate DESC */\r\n" + 
				"		SELECT CONVERT(DATE,dateadd(minute,@offset,RegistrationDate),120) AttemptDate,COUNT(1) Count  FROM #TemptData \r\n" + 
				"		GROUP BY CONVERT(DATE,dateadd(minute,@offset,RegistrationDate),120) ORDER BY AttemptDate DESC */\r\n" + 
				"\r\n" + 
				"		'\r\n" + 
				"		PRINT @SQL\r\n" + 
				"		SET @param=N'@DateFrom DATETIME,@DateTo DATETIME,@PageStartFrom INT,@PageTo INT,@Status TINYINT,@EmailId VARCHAR(100),@UtilityAccount VARCHAR(100)\r\n" + 
				"					,@RegistrationDate DATE,@ActivationDate	DATE,@RoleName VARCHAR(100),@Username VARCHAR(100),@ZipCode VARCHAR(100),@Message VARCHAR(MAX),@RegistrationSource Varchar(10),@offset INT '\r\n" + 
				"		EXEC SP_EXECUTESQL @SQL,@param,@DateFrom,@DateTo,@PageStartFrom ,@PageTo ,@Status,@EmailId,@UtilityAccount \r\n" + 
				"					,@RegistrationDate,@ActivationDate,@RoleName,@Username ,@ZipCode,@Message,@RegistrationSource,@offset = @offset\r\n" + 
				"		print @DateFrom\r\n" + 
				"		print @DateTo\r\n" + 
				"	END\r\n" + 
				"	ELSE \r\n" + 
				"	BEGIN\r\n" + 
				"		Set @SQL =N'\r\n" + 
				"		SELECT row_number() OVER (ORDER BY '+QUOTENAME(@OrderColumn)+' '+@SortOrder +') AS RN,\r\n" + 
				"					 UtilityAccountNumber\r\n" + 
				"					,(CASE IsCSR WHEN 0 THEN ''SCP'' ELSE ''CSP'' END) AS RegistrationSource\r\n" + 
				"					,(CASE \r\n" + 
				"						WHEN STATUS = 1\r\n" + 
				"							THEN '' Success ''	ELSE '' Failed ''\r\n" + 
				"					  END) AS [Status]\r\n" + 
				"					,ISNULL(Message, '''') AS Message\r\n" + 
				"					,RegistrationDate\r\n" + 
				"					,ActivationDate\r\n" + 
				"					,RoleName\r\n" + 
				"					,Username\r\n" + 
				"					,EmailID\r\n" + 
				"					,ZipCode\r\n" + 
				"					,OSType\r\n" + 
				"				INTO #TemptData \r\n" + 
				"				FROM RegistrationLog(NOLOCK)\r\n" + 
				"				WHERE (@DateFrom IS NULL OR CONVERT(DATE,RegistrationDate) BETWEEN @DateFrom AND @DateTo)\r\n" + 
				"					AND Status=ISNULL(@Status,Status)\r\n" + 
				"					AND EmailId LIKE ''%''+ISNULL(@EmailId,EmailId)+''%''\r\n" + 
				"					AND UtilityAccountNumber LIKE ''%''+ISNULL(@UtilityAccount,UtilityAccountNumber)+''%''\r\n" + 
				"					AND CONVERT(DATE,RegistrationDate) = CONVERT(DATE,ISNULL(@RegistrationDate,RegistrationDate))\r\n" + 
				"					AND ISNULL(CONVERT(DATE,ActivationDate),'''') = ISNULL(CONVERT(DATE,ISNULL(@ActivationDate,ActivationDate)),'''')\r\n" + 
				"					AND ISNULL(RoleName,'''') LIKE ''%''+ISNULL(@RoleName,'''')+''%''\r\n" + 
				"					AND ISNULL(Username,'''') LIKE ''%''+ISNULL(@Username,'''')+''%''\r\n" + 
				"					AND ISNULL(ZipCode,'''') LIKE ''%''+ISNULL(@ZipCode,'''')+''%''\r\n" + 
				"					AND ISNULL(Message,'''') LIKE ''%''+ISNULL(@Message,'''')+''%''\r\n" + 
				"					And ((CASE IsCSR WHEN 0 THEN ''SCP'' ELSE ''CSP'' END) = IsNull(@RegistrationSource,IsCSR) or Isnull(@RegistrationSource,'''')='''')\r\n" + 
				"			\r\n" + 
				"		SELECT RN\r\n" + 
				"			,UtilityAccountNumber\r\n" + 
				"			,RegistrationSource\r\n" + 
				"			,[Status]\r\n" + 
				"			,Message\r\n" + 
				"			--,dbo.Fn_ReportDate(RegistrationDate) RegistrationDate --\r\n" + 
				"			,CONVERT(VARCHAR(19),RegistrationDate,120) AS RegistrationDate\r\n" + 
				"			--,dbo.Fn_ReportDate(ActivationDate) ActivationDate --\r\n" + 
				"			,CONVERT(VARCHAR(19),ActivationDate,120) AS ActivationDate\r\n" + 
				"			,RoleName\r\n" + 
				"			,Username\r\n" + 
				"			,EmailID\r\n" + 
				"			,ZipCode\r\n" + 
				"			,OSType\r\n" + 
				"		FROM #TemptData\r\n" + 
				"		--Where RegistrationSource = IsNull(@RegistrationSource,RegistrationSource)\r\n" + 
				"		ORDER BY RN\r\n" + 
				"		SELECT ISNULL([Status], '' Total '') [Status]\r\n" + 
				"			,COUNT(1) StatCount\r\n" + 
				"		FROM #TemptData\r\n" + 
				"		--Where RegistrationSource = IsNull(@RegistrationSource,RegistrationSource)\r\n" + 
				"		GROUP BY CUBE(STATUS)\r\n" + 
				"		ORDER BY Status\r\n" + 
				"		SELECT CONVERT(DATE,RegistrationDate) AttemptDate,COUNT(1) Count  FROM #TemptData \r\n" + 
				"		--Where RegistrationSource = IsNull(@RegistrationSource,RegistrationSource)\r\n" + 
				"		GROUP BY CONVERT(DATE,RegistrationDate) ORDER BY AttemptDate DESC'\r\n" + 
				"		PRINT @SQL\r\n" + 
				"		SET @param=N'@DateFrom DATETIME,@DateTo DATETIME,@PageStartFrom INT,@PageTo INT,@Status TINYINT,@EmailId VARCHAR(100),@UtilityAccount VARCHAR(100)\r\n" + 
				"					,@RegistrationDate DATE,@ActivationDate	DATE,@RoleName VARCHAR(100),@Username VARCHAR(100),@ZipCode VARCHAR(100),@Message VARCHAR(MAX),@RegistrationSource Varchar(10) '\r\n" + 
				"		EXEC SP_EXECUTESQL @SQL,@param,@DateFrom,@DateTo,@PageStartFrom ,@PageTo ,@Status,@EmailId,@UtilityAccount\r\n" + 
				"					,@RegistrationDate,@ActivationDate,@RoleName,@Username ,@ZipCode,@Message,@RegistrationSource\r\n" + 
				"	END";
		
		return query;
		
	}

	public static String getFilterDataForErrorLog(String toDate, String fromDate, String errorMessage) {

		String query = "declare \r\n" + "  @Application TINYINT			= NULL\r\n"
				+ " ,@ErrFrom	   VARCHAR(100)	= NULL\r\n" + " ,@DateFrom		DATETIME		= '" + fromDate
				+ "'\r\n" + " ,@DateTo		DATETIME		= '" + toDate + "'\r\n"
				+ " ,@OrderColumn  VARCHAR(MAX)	= 'Logged'\r\n" + " ,@SortOrder  VARCHAR(MAX)		= 'Desc'\r\n"
				+ " ,@Message     VARCHAR(MAX)	= '" + errorMessage + "'\r\n"
				+ " ,@Mode		   TINYINT			= 0 --0: Master Data,1:Detail\r\n"
				+ " ,@LogId	   INT				= Null\r\n" + " ,@PageStartFrom   INT			= 0\r\n"
				+ " ,@PageLength	   INT			= 1000\r\n" + " ,@ServerAddress   NVARCHAR(200)	= NULL\r\n"
				+ " ,@offset int ='+330'\r\n" + "\r\n" + "  DECLARE  @SQL			 NVARCHAR(MAX)\r\n"
				+ "		  ,@param		 NVARCHAR(MAX)\r\n" + "		  ,@TotalRecords INT\r\n"
				+ "		  ,@PageTo INT = @PageStartFrom+@PageLength\r\n" + "  IF @Mode = 0\r\n" + "  BEGIN \r\n"
				+ "	SET @SQL = N'\r\n"
				+ "		SELECT ROW_NUMBER() OVER (ORDER BY '+@OrderColumn+' '+@SortOrder+') AS RN,* INTO #TempData FROM (\r\n"
				+ "		SELECT \r\n" + "			A.ID\r\n" + "			,Application\r\n"
				+ "			,b.NAME ApplicationName\r\n" + "			,CONVERT(VARCHAR,Logged,120) AS Logged\r\n"
				+ "			--,Logged\r\n" + "			,Message\r\n" + "			,Https\r\n"
				+ "			,ServerAddress\r\n" + "			,RemoteAddress\r\n" + "			,b.NAME ErrFrom\r\n"
				+ "		FROM [Log] A WITH (NOLOCK)\r\n" + "		JOIN CommonMaster B ON A.Application = B.MasterCode\r\n"
				+ "			AND B.MasterType = ''Application''\r\n"
				+ "		--WHERE Logged >= ISNULL(@DateFrom, Logged) AND Logged <= ISNULL(DATEADD(SS,86399,@DateTo), Logged)\r\n"
				+ "		WHERE Logged >= ISNULL(@DateFrom, Logged) AND Logged <= ISNULL(@DateTo, Logged)\r\n"
				+ "			AND Application = ISNULL(@Application, Application)\r\n"
				+ "			AND LEVEL = ''Error''\r\n"
				+ "			AND Message LIKE  ''%''+ISNULL(@Message,substring(Message,1,3950))+''%''\r\n"
				+ "		UNION ALL\r\n" + "		SELECT\r\n" + "			LogId\r\n" + "			,NULL Application\r\n"
				+ "			,NULL ApplicationName\r\n" + "			,CONVERT(VARCHAR,ErrorDateTime,120) AS Logged\r\n"
				+ "			--,ErrorDateTime Logged\r\n" + "			,DBErrorDescription Message\r\n"
				+ "			,NULL Https\r\n"
				+ "			,Convert(NVARCHAR(16),CONNECTIONPROPERTY(''local_net_address'')) AS ServerAddress\r\n"
				+ "			,NULL RemoteAddress\r\n" + "			,''DB'' ErrFrom\r\n"
				+ "		FROM [DBErrorLog] A WITH (NOLOCK)\r\n"
				+ "		--WHERE ErrorDateTime >= ISNULL(@DateFrom, ErrorDateTime) AND ErrorDateTime <= ISNULL(DATEADD(SS,86399,@DateTo), ErrorDateTime)\r\n"
				+ "		WHERE ErrorDateTime >= ISNULL(@DateFrom, ErrorDateTime) AND ErrorDateTime <= ISNULL(@DateTo, ErrorDateTime)\r\n"
				+ "			AND DBErrorDescription LIKE  ''%''+ISNULL(@Message, DBErrorDescription)+''%''\r\n"
				+ "		) X \r\n" + "		WHERE ErrFrom = ISNULL(@ErrFrom, ErrFrom)\r\n"
				+ "			AND ServerAddress LIKE ''%''+ISNULL(@ServerAddress, ServerAddress)+''%''\r\n"
				+ "		SELECT * FROM #TempData\r\n"
				+ "		WHERE RN BETWEEN cast((@PageStartFrom+1) AS VARCHAR) AND cast(@PageTo AS VARCHAR) \r\n"
				+ "		ORDER BY RN\r\n" + "		--SELECT COUNT(1) TotalCount FROM #TempData \r\n"
				+ "		/*SELECT Count(1) AS CNT, CONVERT(DATE,Logged,120) Logged FROM #TempData GROUP BY CONVERT(DATE,Logged,120) ORDER BY Logged ASC */\r\n"
				+ "		--SELECT Count(1) AS CNT, CONVERT(DATE,dateadd(minute,@offset,Logged),120) Logged FROM #TempData GROUP BY CONVERT(DATE,dateadd(minute,@offset,Logged),120) ORDER BY Logged ASC\r\n"
				+ "		--SELECT Count(ID) AS CNT, ErrFrom as ApplicationName FROM #TempData GROUP BY ErrFrom \r\n"
				+ "		'   \r\n" + "    PRINT @SQL\r\n"
				+ "   SET @param = '@Application VARCHAR(10),@Message VARCHAR(MAX),@PageStartFrom INT,@PageTo INT , @DateFrom DATETIME, @DateTo DATETIME, @ErrFrom VARCHAR(100),@ServerAddress NVARCHAR(200),@offset INT	'\r\n"
				+ "   EXEC SP_EXECUTESQL @SQL,@param,@Application=@Application,@Message=@Message,@PageStartFrom = @PageStartFrom,@PageTo = @PageTo , @DateFrom=@DateFrom , @DateTo = @DateTo,@ErrFrom = @ErrFrom,@ServerAddress = @ServerAddress,@offset = @offset\r\n"
				+ "  END\r\n" + "  ELSE\r\n" + "  BEGIN\r\n" + "   SET @SQL = N'\r\n"
				+ "   		SELECT ROW_NUMBER() OVER (ORDER BY '+@OrderColumn+' '+@SortOrder+') AS RN,* INTO #TempData FROM (\r\n"
				+ "		SELECT \r\n" + "			A.ID\r\n" + "			,Application\r\n"
				+ "			,b.NAME ApplicationName\r\n" + "			,CONVERT(VARCHAR,Logged,120) Logged\r\n"
				+ "			--,Logged\r\n" + "			,Message\r\n" + "			,Https\r\n"
				+ "			,ServerAddress\r\n" + "			,RemoteAddress\r\n" + "			,b.NAME ErrFrom\r\n"
				+ "		FROM [Log] A WITH (NOLOCK)\r\n" + "		JOIN CommonMaster B ON A.Application = B.MasterCode\r\n"
				+ "			AND B.MasterType = ''Application''\r\n"
				+ "		--WHERE Logged >= ISNULL(@DateFrom, Logged) AND Logged <= ISNULL(DATEADD(SS,86399,@DateTo), Logged)\r\n"
				+ "		WHERE Logged >= ISNULL(@DateFrom, Logged) AND Logged <= ISNULL(@DateTo, Logged)\r\n"
				+ "			AND Application = ISNULL(@Application, Application)\r\n"
				+ "			AND LEVEL = ''Error''\r\n"
				+ "			AND Message LIKE  ''%''+ISNULL(@Message,substring(Message,1,3950))+''%''\r\n"
				+ "		UNION ALL\r\n" + "		SELECT\r\n" + "			LogId\r\n" + "			,NULL Application\r\n"
				+ "			,NULL ApplicationName\r\n" + "			,CONVERT(VARCHAR,ErrorDateTime,120) Logged\r\n"
				+ "			--,ErrorDateTime Logged\r\n" + "			,DBErrorDescription Message\r\n"
				+ "			,NULL Https\r\n"
				+ "			,Convert(NVARCHAR(16),CONNECTIONPROPERTY(''local_net_address'')) AS ServerAddress\r\n"
				+ "			,NULL RemoteAddress\r\n" + "			,''DB'' ErrFrom\r\n"
				+ "		FROM [DBErrorLog] A WITH (NOLOCK)\r\n"
				+ "		--WHERE ErrorDateTime >= ISNULL(@DateFrom, ErrorDateTime) AND ErrorDateTime <= ISNULL(DATEADD(SS,86399,@DateTo), ErrorDateTime)\r\n"
				+ "		WHERE ErrorDateTime >= ISNULL(@DateFrom, ErrorDateTime) AND ErrorDateTime <= ISNULL(@DateTo, ErrorDateTime)\r\n"
				+ "			AND DBErrorDescription LIKE  ''%''+ISNULL(@Message, DBErrorDescription)+''%''\r\n"
				+ "		) X \r\n" + "		WHERE ErrFrom = ISNULL(@ErrFrom, ErrFrom)\r\n"
				+ "			AND ServerAddress LIKE ''%''+ISNULL(@ServerAddress, ServerAddress)+''%''\r\n" + "\r\n"
				+ "		--SELECT RN,ID,Application,ApplicationName,dbo.Fn_ReportDate(Logged) Logged,Message,Https,ServerAddress,RemoteAddress,ErrFrom FROM #TempData ORDER BY RN\r\n"
				+ "		SELECT RN,ID,Application,ApplicationName,Logged,Message,Https,ServerAddress,RemoteAddress,ErrFrom FROM #TempData ORDER BY RN\r\n"
				+ "		SELECT Count(1) AS CNT, CONVERT(DATE,Logged,120) Logged FROM #TempData GROUP BY CONVERT(DATE,Logged,120) ORDER BY Logged DESC\r\n"
				+ "		SELECT Count(ID) AS CNT, ErrFrom as ApplicationName FROM #TempData GROUP BY ErrFrom \r\n"
				+ "		'   \r\n" + "    PRINT @SQL\r\n"
				+ "   SET @param = '@Application VARCHAR(10),@Message NVARCHAR(MAX),@PageStartFrom INT,@PageTo INT , @DateFrom DATETIME, @DateTo DATETIME, @ErrFrom VARCHAR(100),@ServerAddress VARCHAR(200)	'\r\n"
				+ "   EXEC SP_EXECUTESQL @SQL,@param,@Application=@Application,@Message=@Message,@PageStartFrom = @PageStartFrom,@PageTo = @PageTo , @DateFrom=@DateFrom , @DateTo = @DateTo,@ErrFrom = @ErrFrom,@ServerAddress = @ServerAddress\r\n"
				+ "  END";

		return query;

	}
	public static String getFootPrintDetails(String footPrintName) {
		String details = "select * from GreenFootPrintLocations where Name = '"+footPrintName+"'";
		return details;
	}
	public static String deleteFootPrint(String footprintName) {
		String q = "delete from GreenFootPrintLocations where Name ='"+footprintName+"'";
		return q;
	}
	public static String getFootPrintFilteredData(int cityID, int locationTypeID) {
		String q = "declare \r\n"
				+ "	 @Mode				SMALLINT	= 0\r\n"
				+ "	,@LocationTypeId	SMALLINT	= "+locationTypeID+"\r\n"
				+ "	,@Name				VARCHAR(200)= NULL\r\n"
				+ "	,@Address1			VARCHAR(50)	= NULL\r\n"
				+ "	,@Address2			VARCHAR(50)	= NULL\r\n"
				+ "	,@CityId			INT			= "+cityID+"\r\n"
				+ "	,@ZipCode			VARCHAR(100)	= NULL\r\n"
				+ "	,@Latitude			FLOAT		= 0.00\r\n"
				+ "	,@Longitude			FLOAT		= 0.00\r\n"
				+ "	,@Comment			VARCHAR(100)= NULL\r\n"
				+ "	,@WebSite			VARCHAR(200)= NULL\r\n"
				+ "	,@PhoneNo			VARCHAR(20)	= NULL\r\n"
				+ "	,@FootPrintID		INT			= NULL\r\n"
				+ "			SELECT	\r\n"
				+ "				G.FootPrintID,G.LocationTypeId,LT.LocationType,G.Rating,G.Name,G.Address1,G.Address2,G.CityID,CM.CityName,G.ZipCode,\r\n"
				+ "				G.Latitude,G.Longitude,G.Comment,G.WebSite,G.PhoneNo\r\n"
				+ "		FROM GreenFootPrintLocations	G  WITH(NOLOCK)\r\n"
				+ "		JOIN CityMaster					CM WITH(NOLOCK) ON(CM.CityID = G.CityId)\r\n"
				+ "		JOIN LocationType				LT WITH(NOLOCK) ON(LT.LocationTypeId = G.LocationTypeId)\r\n"
				+ "		WHERE G.LocationTypeId = ISNULL(@LocationTypeId,G.LocationTypeId) AND G.CityID = ISNULL(@CityId,G.CityId)";
		return q;

	}
	
	
	public static String getServiceAccountsTabCount() {
		
		String query ="Declare @utilityAccountNumber VARCHAR(50) = NULL\n"
				+ "	,@PageIndex INT = 1\n"
				+ "	,@PageSize INT = 200\n"
				+ "	,@timeoffset INT = 0\n"
				+ "	,@CustomerNo VARCHAR(100) = NULL\n"
				+ "	,@UserID VARCHAR(20) = NULL\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "	DECLARE @AccTotal INT\n"
				+ "			,@AccActive INT\n"
				+ "			,@CustTotal INT\n"
				+ "			,@CustActive INT\n"
				+ "			,@UserTotal INT\n"
				+ "			,@UserActive INT\n"
				+ "			,@LinkedUserTotal INT\n"
				+ "			,@LinkedUserGuest INT\n"
				+ "			,@LinkedUserPM INT\n"
				+ "\n"
				+ "SELECT @AccTotal = COUNT(DISTINCT UtilityAccountNumber)\n"
				+ "		FROM CustomerInfo WITH (NOLOCK)\n"
				+ "		WHERE CustomerID > 1\n"
				+ "		SELECT @AccActive = COUNT(DISTINCT UtilityAccountNumber)\n"
				+ "		FROM CustomerInfo WITH (NOLOCK)\n"
				+ "		WHERE /*AccountStatusID IN (1)*/ AccountStatusID IN (\n"
				+ "				0\n"
				+ "				,1\n"
				+ "				,3\n"
				+ "				)\n"
				+ "			AND CustomerID > 1 /*Added By Rohit Kumar on 14_Jan_2021 for Active Account count only Filter*/\n"
				+ "		SELECT @AccTotal AS 'AccTotal'\n"
				+ "			,@AccActive AS 'AccActive'\n"
				+ "			,@AccTotal - @AccActive AS 'AccOthers'\n"
				+ "";
		
		return query;
	}

	
	public static String getCustomersTabCount() {
		String query = "Declare @utilityAccountNumber VARCHAR(50) = NULL\n"
				+ "	,@PageIndex INT = 1\n"
				+ "	,@PageSize INT = 200\n"
				+ "	,@timeoffset INT = 0\n"
				+ "	,@CustomerNo VARCHAR(100) = NULL\n"
				+ "	,@UserID VARCHAR(20) = NULL\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "	DECLARE @AccTotal INT\n"
				+ "			,@AccActive INT\n"
				+ "			,@CustTotal INT\n"
				+ "			,@CustActive INT\n"
				+ "			,@UserTotal INT\n"
				+ "			,@UserActive INT\n"
				+ "			,@LinkedUserTotal INT\n"
				+ "			,@LinkedUserGuest INT\n"
				+ "			,@LinkedUserPM INT\n"
				+ "\n"
				+ "\n"
				+ "		SELECT @CustTotal = COUNT(DISTINCT customerid)\n"
				+ "		FROM CustomerInfo WITH (NOLOCK)\n"
				+ "		WHERE CustomerID > 1\n"
				+ "		SELECT @CustActive = COUNT(DISTINCT customerid)\n"
				+ "		FROM CustomerInfo WITH (NOLOCK)\n"
				+ "		WHERE CustomerID > 1\n"
				+ "			AND AccountStatusID IN (\n"
				+ "				0\n"
				+ "				,1\n"
				+ "				,3\n"
				+ "				)\n"
				+ "\n"
				+ "\n"
				+ "		SELECT @CustTotal AS 'CustTotal'\n"
				+ "			,@CustActive AS 'CustActive'\n"
				+ "			,@CustTotal - @CustActive AS 'CustOthers'\n"
				+ "		SELECT @UserTotal = count(DISTINCT U.userid)\n"
				+ "		FROM [user] U WITH (NOLOCK)\n"
				+ "		JOIN (\n"
				+ "			SELECT accountNumber\n"
				+ "				,Userid\n"
				+ "			FROM UserAccount WITH (NOLOCK)\n"
				+ "			\n"
				+ "			UNION ALL\n"
				+ "			\n"
				+ "			SELECT PA.accountNumber\n"
				+ "				,PU.Userid\n"
				+ "			FROM PortfolioAccount PA WITH (NOLOCK)\n"
				+ "			INNER JOIN PortfolioUser PU WITH (NOLOCK) ON PA.GroupID = PU.GroupID\n"
				+ "				AND PU.IsActive = 1\n"
				+ "			/*PU.IsActive=1 is added By Rohit Kumar on 8 July 2021 to match the conditions as per the user total mode=9*/\n"
				+ "			WHERE --PU.ROLEID=10 AND \n"
				+ "				PA.IsActive = 1 /*Added by abhishek gupta on 240ct2019 to get only owner account from portfolio*/\n"
				+ "			) UA ON U.userid = UA.userid\n"
				+ "		WHERE U.userid NOT IN (\n"
				+ "				- 1\n"
				+ "				,1\n"
				+ "				)\n"
				+ "			AND U.[STATUS] NOT IN (3)\n"
				+ "			AND U.UserType NOT IN (3) --AND UA.IsDefaultAccount=1---Added for the portfolio account\n"
				+ "		SELECT @UserActive = count(DISTINCT U.userid)\n"
				+ "		FROM [user] U WITH (NOLOCK)\n"
				+ "		JOIN (\n"
				+ "			SELECT accountNumber\n"
				+ "				,Userid\n"
				+ "			FROM UserAccount WITH (NOLOCK)\n"
				+ "			\n"
				+ "			UNION ALL\n"
				+ "			\n"
				+ "			SELECT PA.accountNumber\n"
				+ "				,PU.Userid\n"
				+ "			FROM PortfolioAccount PA WITH (NOLOCK)\n"
				+ "			INNER JOIN PortfolioUser PU WITH (NOLOCK) ON PA.GroupID = PU.GroupID\n"
				+ "				AND PU.IsActive = 1\n"
				+ "			/*Commented By Rohit Kumar on 4th May 2021 as per the mismatching in count*/\n"
				+ "			/*PU.isActive=1 comment removed by Chirag Dhawan on 24th November, 2021*/\n"
				+ "			WHERE PA.IsActive = 1\n"
				+ "				--AND PU.ROLEID=10 /*Added by abhishek gupta on 240ct2019 to get only owner account from portfolio*/\n"
				+ "			) UA ON U.userid = UA.userid\n"
				+ "		WHERE U.STATUS = 1\n"
				+ "			AND isnull(U.islocked, 0) = 0\n"
				+ "			AND U.userid NOT IN (\n"
				+ "				- 1\n"
				+ "				,1\n"
				+ "				)\n"
				+ "			AND U.UserType NOT IN (\n"
				+ "				3\n"
				+ "				,5\n"
				+ "				) --AND UA.IsDefaultAccount=1--added for the portfolio account\n"
				+ "";
		
		return query;
	}
	
	public static String getUsersTabCount() {
		String query = "Declare @utilityAccountNumber VARCHAR(50) = NULL\n"
				+ "	,@PageIndex INT = 1\n"
				+ "	,@PageSize INT = 200\n"
				+ "	,@timeoffset INT = 0\n"
				+ "	,@CustomerNo VARCHAR(100) = NULL\n"
				+ "	,@UserID VARCHAR(20) = NULL\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "	DECLARE @AccTotal INT\n"
				+ "			,@AccActive INT\n"
				+ "			,@CustTotal INT\n"
				+ "			,@CustActive INT\n"
				+ "			,@UserTotal INT\n"
				+ "			,@UserActive INT\n"
				+ "			,@LinkedUserTotal INT\n"
				+ "			,@LinkedUserGuest INT\n"
				+ "			,@LinkedUserPM INT\n"
				+ "\n"
				+ "		SELECT @UserTotal = count(DISTINCT U.userid)\n"
				+ "		FROM [user] U WITH (NOLOCK)\n"
				+ "		JOIN (\n"
				+ "			SELECT accountNumber\n"
				+ "				,Userid\n"
				+ "			FROM UserAccount WITH (NOLOCK)\n"
				+ "			\n"
				+ "			UNION ALL\n"
				+ "			\n"
				+ "			SELECT PA.accountNumber\n"
				+ "				,PU.Userid\n"
				+ "			FROM PortfolioAccount PA WITH (NOLOCK)\n"
				+ "			INNER JOIN PortfolioUser PU WITH (NOLOCK) ON PA.GroupID = PU.GroupID\n"
				+ "				AND PU.IsActive = 1\n"
				+ "			/*PU.IsActive=1 is added By Rohit Kumar on 8 July 2021 to match the conditions as per the user total mode=9*/\n"
				+ "			WHERE --PU.ROLEID=10 AND \n"
				+ "				PA.IsActive = 1 /*Added by abhishek gupta on 240ct2019 to get only owner account from portfolio*/\n"
				+ "			) UA ON U.userid = UA.userid\n"
				+ "		WHERE U.userid NOT IN (\n"
				+ "				- 1\n"
				+ "				,1\n"
				+ "				)\n"
				+ "			AND U.[STATUS] NOT IN (3)\n"
				+ "			AND U.UserType NOT IN (3) --AND UA.IsDefaultAccount=1---Added for the portfolio account\n"
				+ "		SELECT @UserActive = count(DISTINCT U.userid)\n"
				+ "		FROM [user] U WITH (NOLOCK)\n"
				+ "		JOIN (\n"
				+ "			SELECT accountNumber\n"
				+ "				,Userid\n"
				+ "			FROM UserAccount WITH (NOLOCK)\n"
				+ "			\n"
				+ "			UNION ALL\n"
				+ "			\n"
				+ "			SELECT PA.accountNumber\n"
				+ "				,PU.Userid\n"
				+ "			FROM PortfolioAccount PA WITH (NOLOCK)\n"
				+ "			INNER JOIN PortfolioUser PU WITH (NOLOCK) ON PA.GroupID = PU.GroupID\n"
				+ "				AND PU.IsActive = 1\n"
				+ "			/*Commented By Rohit Kumar on 4th May 2021 as per the mismatching in count*/\n"
				+ "			/*PU.isActive=1 comment removed by Chirag Dhawan on 24th November, 2021*/\n"
				+ "			WHERE PA.IsActive = 1\n"
				+ "				--AND PU.ROLEID=10 /*Added by abhishek gupta on 240ct2019 to get only owner account from portfolio*/\n"
				+ "			) UA ON U.userid = UA.userid\n"
				+ "		WHERE U.STATUS = 1\n"
				+ "			AND isnull(U.islocked, 0) = 0\n"
				+ "			AND U.userid NOT IN (\n"
				+ "				- 1\n"
				+ "				,1\n"
				+ "				)\n"
				+ "			AND U.UserType NOT IN (\n"
				+ "				3\n"
				+ "				,5\n"
				+ "				) --AND UA.IsDefaultAccount=1--added for the portfolio account\n"
				+ "		SELECT @UserTotal AS SCMUserTotal\n"
				+ "			,@UserActive AS SCMUserActive\n"
				+ "			,@UserTotal - @UserActive AS SCMUserOther\n"
				+ "";
		
		return query;
	}


}

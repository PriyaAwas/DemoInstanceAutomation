package sew.ai.api.enums;

public enum Routes {
    // LOGIN REGISTRATION ENDPOINTS
    GetToken("/UserLogin/GetId"),
    GetAuthHeaderEndpoint("/UserLogin/GetId"),
    GetLoginEndpoint("/UserLogin/ValidateUserLogin"),
    GetLogOut("/UserLogin/Logout"),
    GetLoginHelp("/UserLogin/GetLoginHelp"),
    GetRegisterEndpoint("/Registration/SetCustomerRegistration"),
    GetAuthHeaderCSPEndpoint("/AdminAPI/api/2/Login/GetTokenID"),
    RegisterCustomer("/Registration/SetCustomerRegistration"),

    //LOGIN SUPPORT ENDPOINTS
    GetProblemsSigningInEndpoint("/UserLogin/CreateUserNotification"),

    // JWT TOKEN ENDPOINTS
    GetJwtTokenEndpoint("/api/1/users/GenerateToken"),

    // MFA ENDPOINTS
    GetUserTwoFactorAuthEndpoint("/TwoFactorAuthentication/GetUserTwoFactorAuthentication"),
    CreateUserTwoFactorAuthEndpoint("/TwoFactorAuthentication/CreateUserTwoFactorAuthenticationToken"),
    ValidateUserTwoFactorAuthEndpoint("/TwoFactorAuthentication/VerifyUserTwoFactorAuthenticationTokenAsync"),

    // BILLING ENDPOINTS
    AutopayEnrollUnEnrollEndpoint("/Billing/SetAccountRecurringPayment"),
    BudgetMyBillEndpoint("/Billing/SetBudgetBill"),
    GetRoutingNumber("/Billing/GetBankRouting"),
    AutopayEndpoint("/Billing/SetAccountRecurringPayment"),
    GetBillPdfEndpoint("/Billing/GetBillPDF"),
    GetPaymentLocationEndpoint("/Billing/GetPaymentLocation"),
    AddAutoPayEnrollment("/Billing/SetAccountRecurringPayment"),
    DelAutoPayEnrollment("/Billing/SetAccountRecurringPayment"),
    GetBillingID("/Billing/GetBill"),
    GetBillPayMode("/Billing/GetBillPayMode"),

    // PAYMENTS ENDPOINTS
    GetPaymentProfilesEndpoint("/api/Profile/GetAllProfile/{userId}"),
    CardEndpoint("/api/Profile/Card"),
    BankEndpoint("/api/Profile/Bank"),
    TokenizedPayment("/api/Payment/TokenizedPayment"),
    UpdateCardEndpoint("/api/Profile/Card/{profileToken}"),
    UpdateBankEndpoint("/api/Profile/Bank/{profileToken}"),
    DeleteProfileEndpoint("/api/Profile/{profileToken}/{userId}"),
    OneTimePaymentCC("/api/Payment/Card"),
    OneTimePaymentBank("/api/Payment/Bank"),
    OneTimePaymentCardEndpoint("/api/Payment/Card"),
    OneTimePaymentBankEndpoint("/api/Payment/Bank"),
    TokenizedPaymentEndpoint("/api/Payment/TokenizedPayment"),
    AddPaymentProfileByCard("/api/Profile/Card"),
    DeletePaymentProfile("/api/Profile/{Profile Token}/{UserID}"),
    AddPaymentProfileByBank("/api/Profile/Bank"),

    // PAYPAL AND WALLETS PROFILE ENDPOINTS
    AddPayPalCardToProfile("/Profile/Card"),
    AddPayPalBankToProfile("/Profile/Bank"),
    DeletePayPalProfile("/Profile/{customerRefNum}/{userId}"),
    AddWalletToProfile("/Profile/Wallet"),
    CreateAppleToken("graphql"),

    // PAYPAL AND WALLETS PAYMENTS ENDPOINTS
    OneTimePayByCard("/Payment/Card"),
    OneTimePayByBank("/Payment/Bank"),
    OneTimePayByWallet("/Payment/WalletPayment"),

    // OUTAGE ENDPOINTS
    GetOutageSCPEndpoint("/Outage/GetOutageData"),
    GetOutageCSPEndpoint("/AdminAPI/api/2/Outagecsp/GetOutageData"),
    AddUpdateOutageEndpoint("/AdminAPI/api/2/Outagecsp/InsertOutage"),
    ReportOutageEndpoint("/ContactUs/SetConnectMeRequest"),

    // CONNECT ME ENDPOINTS
    SetConnectMeRequest("/ContactUs/SetConnectMeRequest"),
    GetPaymentSessionToken("/connect/token"),
    SaveConnectMeRequest("/ContactUs/SaveInboxConnectMe"),
    
    // SERVICES ENDPOINTS
    MoveInRequestEndpoint("/Service/SubmitServiceRequest"),
    SubmittedFormsEnpoint("/Service/Service_GetSubmittedForms"),
    SubmitServiceRequestFormEnpoint("/Service/SubmitServiceRequest"),
    SaveServiceRequestFormEnpoint("/Service/SaveServiceRequest"),

    // NOTIFICATIONS ENDPOINTS
    GetMessagesEndpoint("/api/1/Notification/GetMessages"),
    GetMessagesBodyEndpoint("/api/1/Notification/GetMessagesBody"),
    GetMessagesReplyEndpoint("/api/1/Notification/MessageReply"),

    // USAGE ENDPOINTS
    GetElectricUsageEndpoint("/Electric"),
    GetWaterUsageEndpoint("/Water"),
    GetSolarUsageEndpoint("/Generation"),
    GetGasUsageEndpoint("/Gas"),
    GetProjectedElectricEndpoint("/ProjectedElectric"),
    GetProjectedWaterEndpoint("/ProjectedWater"),
    GetProjectedGasEndpoint("/ProjectedGas"),
    GetProjectedAllEndpoint("/ProjectedUsageForAll"),
    GetUsageAndBillGenerationEndpoint("/Usage/GetUsageAndBillGenerationNew"),
    GetWeatherOverlayDataEndpoint("/weather/WeatherData"),

    // COMPARE ENDPOINTS
    GetCompareElectricData("/CompareElectric"),
    GetCompareWaterData("/CompareWater"),
    GetCompareGasData("/CompareGas"),
    GetCompareNeighbourElectricData("/CompareElectricNeighbour"),
    GetCompareNeighbourWaterData("/CompareWaterNeighbour"),
    GetCompareNeighbourGasData("/CompareGasNeighbour"),

    // CUSTOMER ANALYTICS NOTIFICATION ENDPOINTS
    GetEmailNotificationReportEndpoint("/EmailNotification/GetEmailNotificationReport"),
    GetTextMsgNotificationReportEndpoint("/TextMessageNotification/GetTextNotificationReport"),
    GetPushNotificationReportEndpoint("/PushNotification/GetPushNotificationReport"),
    GetRoboCallNotificationReportEndpoint("/IVRNotification/GetIVRNotificationReport"),

    // CUSTOMER ANALYTICS BANNER
    GetBannerReportEndpoint("/AdminAPI/api/2/CustomerEngagement/GetBannerReport"),
    GetDashboardReportEndpoint("/AdminAPI/api/2/CustomerEngagement/GetDashboardReport"),
    GetCustomerEngagementReport("/AdminAPI/api/2/CustomerEngagement/GetCustomerEngagementReport"),


    // CUSTOMER PREFERENCES
    GetCustomerPreferencesDashboardEndpoint("AdminAPI/api/2/CustomerPreferences/GetCustomerPreferencesDashboard"),
    GetPaperlessBillingEndpoint("/AdminAPI/api/2/CustomerPreferences/GetPaperlessBilling"),

    // CUSTOMER BEHAVIOUR
    GetTopBrowsersEndpoint("/AdminAPI/api/2/UserBehaviour/GetTopBrowsers"),
    GetDashboardDataEndpoint("/AdminAPI/api/2/UserBehaviour/GetDashboardData"),
    GetChartDataEndpoint("/AdminAPI/api/2/UserBehaviour/GetChartData"),
    GetIOSVersionsEndpoint("/AdminAPI/api/2/UserBehaviour/GetIOSVersions"),
    GetAndroidVersionsEndpoint("/AdminAPI/api/2/UserBehaviour/GetAndroidVersions"),
    GetScreenResolutionsEndpoint("/AdminAPI/api/2/UserBehaviour/GetScreenResolutions"),
    GetOSVersionsEndpoint("/AdminAPI/api/2/UserBehaviour/GetOSVersions"),
    GetMobileBrowsersEndpoint("/AdminAPI/api/2/UserBehaviour/GetMobileBrowsers"),
    
    // ACCOUNT
 	GetInviteGuestUserEndpoint("/Account/AddUpdateGuestAccessRequestUser"),
 	GetMyProfileInformation("/Account/GetMyAccountProfile"),
 	GetUpdateMyProfileInformation("/Account/SetMyAccountProfile"),
 	GetSetDefaultAccount("/Account/SetDefaultAccount"),
 	GetLinkAccount("/Account/AddUserAccount");
 
    private String resource;

    Routes(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }
}

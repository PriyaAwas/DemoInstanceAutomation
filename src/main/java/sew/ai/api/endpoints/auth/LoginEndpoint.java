package sew.ai.api.endpoints.auth;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import sew.ai.api.enums.HttpVerbs;
import sew.ai.api.enums.Routes;
import sew.ai.api.pojos.auth.CustomAttributesMFAPayload;
import sew.ai.api.pojos.auth.GetMFAPayload;
import sew.ai.api.pojos.auth.LoginPayload;
import sew.ai.api.pojos.auth.ValidateMFAPayload;
import sew.ai.api.utils.AuthUtils;
import sew.ai.api.utils.RestUtils;
import sew.ai.config.Configuration;
import sew.ai.helpers.props.ResourcePaths;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.utils.Base64EncryptionUtil;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.DateUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginEndpoint extends RestUtils {
    private static final Logger log = LogManager.getLogger(LoginEndpoint.class);
    public static final String LOGIN_ROUTE = "GetLoginEndpoint";
    public static final String GET_MFA_ROUTE = "GetUserTwoFactorAuthEndpoint";
    public static final String CREATE_MFA = "CreateUserTwoFactorAuthEndpoint";
    public static final String VALIDATE_MFA = "ValidateUserTwoFactorAuthEndpoint";
    String utilityAccNum = null, userId = null;

    /**
     * Build request specification object for the login API.
     *
     * @param userName        The username for the user to log in.
     * @param password        The password for the user to log in.
     * @param resourcePathURI The resource path URI on which login API collection hosted on the server.
     */
    public void buildRequestSpecForUserLogin(String userName, String password, String resourcePathURI) {
        log.info("Get authorization token to hit the pre-login API.");
        String authToken = AuthUtils.getAuthorizationToken();
        log.info("Initializing the Login API header.");
        Map<String, String> headers = new HashMap<>();
        headers.put("SourceType", "0");
        headers.put("Authorization", authToken);
        log.info("Getting the default utility account number linked to the given user.");

        ResultSet resultSet = DataBaseUtils.getResultSet(SQLQueries.getDefaultAccQuery(userName));
        try {
            resultSet.next();
            utilityAccNum = resultSet.getString("utilityaccountnumber");
            resultSet = DataBaseUtils.getResultSet(SQLQueries.getUserId(userName));
            resultSet.next();
            userId = resultSet.getString("userid");
        } catch (SQLException e) {
            log.info(e.getMessage());
        }
        log.info("Initializing the Login API payload.");
        LoginPayload loginPayload = new LoginPayload(userName, password, utilityAccNum);
        log.info("Initializing the Login API request specification.");
        reqSpec = RestUtils.requestSpecification(
                resourcePathURI,
                headers,
                loginPayload
        );
    }

    /**
     * Execute the login API and initialize the response object.
     */
    public void hitLoginAPI() {
        String endPoint;
        Routes routes = Routes.valueOf(LOGIN_ROUTE);
        endPoint = routes.getResource();
        log.info("Hit the login API with given request specification: " + reqSpec + "\n" +
                "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " +
                HttpVerbs.POST.name());
        response = AuthUtils.getResponse(
                reqSpec,
                HttpVerbs.POST.name(),
                endPoint
        );
        // Print the response
        response.prettyPrint();
        // Change the response to json path.
        jsonPath = RestUtils.rawToJson(response);
    }

    public int getAPIResponseCode() {
        return response.getStatusCode();
    }

    public String getLoginAPIResponseMessage() {
        return jsonPath.getString("result.Message").trim();
    }

    public Boolean isTwoFactorAuthRequired() {
        return jsonPath.getBoolean("result.Data.Table[0].IsTwoFactor");
    }

    public void buildRequestForGetMFA(String userName, String resourcePathURI) {
        log.info("Get authorization token to hit the pre-login API.");
        String authToken = AuthUtils.getAuthorizationToken();
        log.info("Initializing the Login API header.");
        Map<String, String> headers = new HashMap<>();
        headers.put("SourceType", "0");
        headers.put("Authorization", authToken);
        log.info("Initializing the GetMFA API payload.");
        GetMFAPayload getMFAPayload = new GetMFAPayload(userName, false);
        log.info("Initializing the Login API request specification.");
        reqSpec = requestSpecification(
                resourcePathURI,
                headers,
                getMFAPayload
        );
    }

    public void hitGetMFAAPI() {
        String endPoint;
        Routes routes = Routes.valueOf(GET_MFA_ROUTE);
        endPoint = routes.getResource();
        log.info("Hit the login API with given request specification: " + reqSpec + "\n" +
                "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " +
                HttpVerbs.POST.name());
        response = AuthUtils.getResponse(
                reqSpec,
                HttpVerbs.POST.name(),
                endPoint
        );
        // Print the response
        // response.prettyPrint();
        // Change the response to json path.
        jsonPath = RestUtils.rawToJson(response);
    }

    public void buildRequestForCreateMFA(String userName, String resourcePathURI) {
        log.info("Get authorization token to hit the pre-login API.");
        String authToken = AuthUtils.getAuthorizationToken();
        log.info("Initializing the Login API header.");
        Map<String, String> headers = new HashMap<>();
        headers.put("SourceType", "0");
        headers.put("Authorization", authToken);
        log.info("Initializing the GetMFA API payload.");
        log.info("Initializing operating system.");
        String operatingSystem = Configuration.operatingSystem();
        log.info("Initializing browser in test.");
        String browser = Configuration.browser();
        // Initializing custom attributes.
        CustomAttributesMFAPayload customAttributesMFA = new CustomAttributesMFAPayload(
                browser,
                operatingSystem
        );
        // Init payload object for the creation two-factor authentication
        ValidateMFAPayload validateMFA = new ValidateMFAPayload(userName, null, false,
                customAttributesMFA);
        log.info("Initializing the Login API request specification.");
        reqSpec = requestSpecification(
                resourcePathURI,
                headers,
                validateMFA
        );
    }

    public void hitCreateMFAAPI() {
        String endPoint;
        Routes routes = Routes.valueOf(CREATE_MFA);
        endPoint = routes.getResource();
        log.info("Hit the login API with given request specification: " + reqSpec + "\n" +
                "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " +
                HttpVerbs.POST.name());
        response = AuthUtils.getResponse(
                reqSpec,
                HttpVerbs.POST.name(),
                endPoint
        );
        // Print the response
        response.prettyPrint();
        // Change the response to json path.
        jsonPath = RestUtils.rawToJson(response);
    }

    public String getOTP() {
        Assert.assertEquals(response.getStatusCode(), 200);
        String message = RestUtils.getJsonPath(response, "result.Message");
        Assert.assertEquals(message, "Authentication code has been sent.");
        // Get the OTP for MFA
        String uniqueKey = userId + "||";
        String token = null;
        try {
            String dateUTC = DateUtil.getCurrentUtcTime("yyyy-MM-dd HH:mm");
            ResultSet resultSet = DataBaseUtils.getResultSet(SQLQueries.getTokenMFA(uniqueKey, dateUTC));
            resultSet.next();
            token = resultSet.getString("token");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    public void buildRequestForValidateMFA(String userName, String resourcePathURI) {
        log.info("Get authorization token to hit the pre-login API.");
        String authToken = AuthUtils.getAuthorizationToken();
        log.info("Initializing the Login API header.");
        Map<String, String> headers = new HashMap<>();
        headers.put("SourceType", "0");
        headers.put("Authorization", authToken);
        log.info("Initializing the GetMFA API payload.");
        log.info("Initializing operating system.");
        String operatingSystem = Configuration.operatingSystem();
        log.info("Initializing browser in test.");
        String browser = Configuration.browser();
        CustomAttributesMFAPayload customAttributesMFAPayload = new CustomAttributesMFAPayload(
                browser,
                operatingSystem
        );
        ValidateMFAPayload validateMFA = new ValidateMFAPayload(userName, getOTP(), false,
                customAttributesMFAPayload);
        log.info("Initializing the Login API request specification.");
        reqSpec = requestSpecification(
                resourcePathURI,
                headers,
                validateMFA
        );
    }

    public void hitValidateMFAAPI() {
        String endPoint;
        Routes routes = Routes.valueOf(VALIDATE_MFA);
        endPoint = routes.getResource();
        log.info("Hit the login API with given request specification: " + reqSpec + "\n" +
                "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " +
                HttpVerbs.POST.name());
        response = AuthUtils.getResponse(
                reqSpec,
                HttpVerbs.POST.name(),
                endPoint
        );
        // Print the response
        response.prettyPrint();
        // Change the response to json path.
        jsonPath = RestUtils.rawToJson(response);
    }

    public List<Map<String, Object>> getLoginData() {
        List<Map<String, Object>> loginAPIResponse;
        // Init the login response
        loginAPIResponse = jsonPath.getList("result.Data.Table");
        return loginAPIResponse;
    }

    public Map getInvalidLoginResponse() {
        Map loginAPIResponse;
        // Init the login response
        loginAPIResponse = jsonPath.getMap("result.Data");
        return loginAPIResponse;
    }

    public String getUserId() {
        String userId = jsonPath.getString("result.Data.Table[0].UserID");
        return userId;
    }

    public String getSessionToken() {
        String sessionToken = jsonPath.getString("result.Data.Table[0].SessionToken");
        return sessionToken;
    }

    /**
     * This method gives Login token and JWT token for the given UserName and Password
     *
     * @param userName
     * @param password
     * @return
     */
    public String getLoginToken(String userName, String password) {
        Map<String, String> loginAPIResponse = new HashMap<>();
        // Build Request for login
        buildRequestSpecForUserLogin(
                userName,
                password,
                ResourcePaths.SCP_COMMON_PATH_URI
        );
        // Hit login api
        hitLoginAPI();
        // Verify the status code
        Assert.assertEquals(getAPIResponseCode(), 200);
        // Verify the status message
        Assert.assertEquals(getLoginAPIResponseMessage(), "Data");
        // Bypass two-factor auth
        if (!isTwoFactorAuthRequired()) {
            log.info("Get MFA enabled for user.");
            buildRequestForGetMFA(userName, ResourcePaths.SCP_COMMON_PATH_URI);
            log.info("Hit get MFA API.");
            hitGetMFAAPI();
            Assert.assertEquals(getAPIResponseCode(), 200);
            log.info("Generate OTP for the user.");
            buildRequestForCreateMFA(userName, ResourcePaths.SCP_COMMON_PATH_URI);
            log.info("Hit generate OTP API.");
            hitCreateMFAAPI();
            Assert.assertEquals(getAPIResponseCode(), 200);
            log.info("Validate OTP received on the selected medium.");
            buildRequestForValidateMFA(userName, ResourcePaths.SCP_COMMON_PATH_URI);
            log.info("Hit validate OTP API.");
            hitValidateMFAAPI();
            Assert.assertEquals(getAPIResponseCode(), 200);
            log.info("Asserting the response message.");
            Assert.assertEquals(getLoginAPIResponseMessage(), "Success");
        }
        // Get the desired output.
        String base64Encrypted = Base64EncryptionUtil.getBase64EncryptedString(getUserId() + ":"
                + getSessionToken());
        // Init login token
        String loginToken = "Basic" + " " + base64Encrypted;
        return loginToken;
    }

    public String getJwtToken(String userName, String password) {
        AuthUtils authUtils = new AuthUtils();
        // Init Jwt token
        String jwtToken = authUtils.getJwtToken(userName, password);
        // Init login and JWT token
        return jwtToken;
    }
}

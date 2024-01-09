package sew.ai.api.endpoints.auth;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sew.ai.api.enums.HttpVerbs;
import sew.ai.api.enums.Routes;
import sew.ai.api.pojos.auth.UpdateUserNamePayLoad;
import sew.ai.api.pojos.auth.UserLoginPayLoad;
import sew.ai.api.pojos.auth.UserSettingPayLoad;
import sew.ai.api.utils.AuthUtils;
import sew.ai.api.utils.RestUtils;
import sew.ai.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserLoginEndpoint extends RestUtils {
    private static final Logger log = LogManager.getLogger(UserLoginEndpoint.class);
    private final String VALIDATE_USER_LOGIN_ENDPOINT = "GetUserLoginRequestFormEndpoint";
    private final String VALIDATE_USER_SETTING_ENDPOINT = "GetUserSettingRequestFormEndpoint";
    private final String VALIDATE_CHANGE_PASSWORD_ENDPOINT = "GetChangeUsersPassEndpoint";
    private final String VALIDATE_PROBLEM_SIGN_IN_ENDPOINT = "GetProblemSignInEndpoint";
    private final String VALIDATE_UPDATE_USERNAME_ENDPOINT = "GetUpdateUserNameEndpoint";

    public void buildRequestSpecUserLoginAdd(User user, String resourcePathURI) {
        log.info("Initializing the add Other Service payload.");
        UserLoginPayLoad servicePayload = new UserLoginPayLoad(user);
        Map<String, String> headerMap = new HashMap<>();
        // Init header map
        String authToken = AuthUtils.getAuthorizationToken();
        // Init header map
        headerMap.put("Authorization", authToken);
        headerMap.put("SourceType", "0");
        log.info("Initializing the add Move In Service specification.");
        reqSpec = requestSpecification(resourcePathURI, headerMap, servicePayload);
    }

    public void hitAddUserSetting() {
        String endPoint;
        Routes routes = Routes.valueOf(VALIDATE_USER_SETTING_ENDPOINT);
        endPoint = routes.getResource();
        log.info("Hit the add  Service with given request specification: " + reqSpec + "\n" + "and the endpoint is : "
                + endPoint + "\n" + "and with given http verb : " + HttpVerbs.POST.name());
        // Getting the response
        response = RestUtils.getResponse(reqSpec, HttpVerbs.POST.name(), endPoint);
        // Printing the response
        response.prettyPrint();
        // JsonPath path = RestUtils.rawToJson(response);
        jsonPath = RestUtils.rawToJson(response);
    }

    public int getAPIResponseCode() {
        return response.getStatusCode();
    }

    public String getAPIResponseMessage() {
        return jsonPath.getString("result.Message");
    }

    public HashMap<String, Map> getUserInfoFromAPIs() {
        List<Object> data = jsonPath.getList("result.Data.Table");
        HashMap<String, Map> accountInfo = new HashMap<>();
        HashMap<String, Object> map = null;
        for (int i = 0; i < data.size(); i++) {
            HashMap<String, String> map1 = new HashMap<>();
            map1.put("utilityaccountnumber", jsonPath.getString("result.Data.Table[" + i + "].UtilityAccountNumber"));
            map1.put("accountnumber", jsonPath.getString("result.Data.Table[" + i + "].AccountNumber"));
            accountInfo.put(jsonPath.getString("result.Data.Table[" + i + "].AccountNumber"), map1);
        }
        return accountInfo;
    }

    public HashMap<String, Map> getUserDetailsFromAPIs() {
        List<Object> data = jsonPath.getList("result.Data.Table1");
        HashMap<String, Map> accountInfo = new HashMap<>();
        HashMap<String, Object> map = null;
        for (int i = 0; i < data.size(); i++) {
            HashMap<String, String> map1 = new HashMap<>();
            map1.put("utilityaccountnumber", jsonPath.getString("result.Data.Table1[" + i + "].UtilityAccountNumber"));
            map1.put("accountnumber", jsonPath.getString("result.Data.Table1[" + i + "].AccountNumber"));
            accountInfo.put(jsonPath.getString("result.Data.Table1[" + i + "].AccountNumber"), map1);
        }
        return accountInfo;
    }

    public void buildRequestSpecUserSettingAdd(User user, String resourcePathURI) {
        log.info("Initializing the add Other Service payload.");
        UserSettingPayLoad servicePayload = new UserSettingPayLoad(user);
        Map<String, String> headerMap = new HashMap<>();
        // Init header map
        String authToken = AuthUtils.getAuthorizationToken();
        // Init header map
        headerMap.put("Authorization", user.getJwtToken());
        headerMap.put("SourceType", "1");
        log.info("Initializing the add Move In Service specification.");
        reqSpec = requestSpecification(resourcePathURI, headerMap, servicePayload);
    }

    public void buildRequestSpecUpdateUserNameAdd(User user, String resourcePathURI, String updateUserName) {
        log.info("Initializing the Update User name payload.");
        UpdateUserNamePayLoad updateUserNamePayload = new UpdateUserNamePayLoad(user, updateUserName);
        Map<String, String> headerMap = new HashMap<>();
        // Init header map
        String authToken = AuthUtils.getAuthorizationToken();
        // Init header map
        headerMap.put("Authorization", user.getJwtToken());
        headerMap.put("SourceType", "1");
        log.info("Initializing the Update User name specification.");
        reqSpec = requestSpecification(resourcePathURI, headerMap, updateUserNamePayload);
    }

    public void hitAddUserLogin() {
        String endPoint;
        Routes routes = Routes.valueOf(VALIDATE_USER_LOGIN_ENDPOINT);
        endPoint = routes.getResource();
        log.info("Hit the add  Service with given request specification: " + reqSpec + "\n" + "and the endpoint is : "
                + endPoint + "\n" + "and with given http verb : " + HttpVerbs.POST.name());
        // Getting the response
        response = RestUtils.getResponse(reqSpec, HttpVerbs.POST.name(), endPoint);
        // Printing the response
        response.prettyPrint();
        // JsonPath path = RestUtils.rawToJson(response);
        jsonPath = RestUtils.rawToJson(response);
    }

    public void hitsChangeUsersPassword() {
        String endPoint;
        Routes routes = Routes.valueOf(VALIDATE_CHANGE_PASSWORD_ENDPOINT);
        endPoint = routes.getResource();
        log.info("Hit the add  Service with given request specification: " + reqSpec + "\n" + "and the endpoint is : "
                + endPoint + "\n" + "and with given http verb : " + HttpVerbs.POST.name());
        // Getting the response
        response = RestUtils.getResponse(reqSpec, HttpVerbs.POST.name(), endPoint);
        // Printing the response
        response.prettyPrint();
        // JsonPath path = RestUtils.rawToJson(response);
        jsonPath = RestUtils.rawToJson(response);
    }

    public void hitsProblemSignIn() {
        String endPoint;
        Routes routes = Routes.valueOf(VALIDATE_PROBLEM_SIGN_IN_ENDPOINT);
        endPoint = routes.getResource();
        log.info("Hit the add  Service with given request specification: " + reqSpec + "\n" + "and the endpoint is : "
                + endPoint + "\n" + "and with given http verb : " + HttpVerbs.POST.name());
        // Getting the response
        response = RestUtils.getResponse(reqSpec, HttpVerbs.POST.name(), endPoint);
        // Printing the response
        response.prettyPrint();
        // JsonPath path = RestUtils.rawToJson(response);
        jsonPath = RestUtils.rawToJson(response);
    }

    public void hitsUpdateUserName() {
        String endPoint;
        Routes routes = Routes.valueOf(VALIDATE_UPDATE_USERNAME_ENDPOINT);
        endPoint = routes.getResource();
        log.info("Hit the add  Service with given request specification: " + reqSpec + "\n" + "and the endpoint is : "
                + endPoint + "\n" + "and with given http verb : " + HttpVerbs.POST.name());
        // Getting the response
        response = RestUtils.getResponse(reqSpec, HttpVerbs.POST.name(), endPoint);
        // Printing the response
        response.prettyPrint();
        // JsonPath path = RestUtils.rawToJson(response);
        jsonPath = RestUtils.rawToJson(response);
    }
}

package sew.ai.api.endpoints.auth;

import io.restassured.path.json.JsonPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import sew.ai.api.enums.HttpVerbs;
import sew.ai.api.enums.Routes;
import sew.ai.api.pojos.auth.CustomerVerificationPayLoad;
import sew.ai.api.pojos.auth.OnboardingRegistrationPayLoad;
import sew.ai.api.pojos.auth.RegistrationFormPayLoad;
import sew.ai.api.pojos.auth.RegistrationPayLoad;
import sew.ai.api.utils.AuthUtils;
import sew.ai.api.utils.RestUtils;
import sew.ai.config.ModelsConfiguration;
import sew.ai.helpers.props.ResourcePaths;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.models.Customer;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.DateUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RegistrationEndpoints extends RestUtils {
	private static final Logger log = LogManager.getLogger(RegistrationEndpoints.class);
	private final String SET_CUSTOMER_REG_ENDPOINT = "RegisterCustomer";
	private final String VALIDATE_SINGLE_REG_ENDPOINT = "GetSingleRegistration";
	private final String GET_REGISTRATION_ENDPOINT = "GetRegistrationForm";
	private final String CUSTOMER_VERIFICATION_ENDPOINT = "GetCustomerVerificationRegistration";
	private final String NOTIFICATION_PREF_ENDPOINT = "GetNotificationPref";
	RegistrationPayLoad registrationPayload;
	OnboardingRegistrationPayLoad onBoardingRegistrationPayload;
	private String utilityAccNum;

	public HashMap<String, String> buildRequestSetRegistrationAdd(String resourcePathURI, Customer customer) {
		log.info("Initializing the add Registration payload.");
		HashMap<String, String> authMap = new HashMap<>();
		registrationPayload = new RegistrationPayLoad(customer);
		Map<String, String> headerMap = new HashMap<>();
		String authToken = AuthUtils.getAuthorizationToken();
		// Init header map
		headerMap.put("Authorization", authToken);
		headerMap.put("SourceType", "0");
		log.info("Initializing the Registration specification.");
		reqSpec = requestSpecification(resourcePathURI, headerMap, registrationPayload);
		authMap.put("userName", registrationPayload.getUserName());
		authMap.put("password", registrationPayload.getPassword());
		authMap.put("emailID", registrationPayload.getEmailId());
		return authMap;
	}

	public HashMap<String, String> buildRequestSetRegistrationAdd(String resourcePathURI, Customer reg,
			HashMap<String, String> map) {
		log.info("Initializing the add Registration payload.");
		Random random = new Random();
		HashMap<String, String> authMap = new HashMap<>();
		onBoardingRegistrationPayload = new OnboardingRegistrationPayLoad(reg, random, map);
		Map<String, String> headerMap = new HashMap<>();
		String authToken = AuthUtils.getAuthorizationToken();
		// Init header map
		headerMap.put("Authorization", authToken);
		headerMap.put("SourceType", "0");
		log.info("Initializing the Registration specification.");
		reqSpec = requestSpecification(resourcePathURI, headerMap, onBoardingRegistrationPayload);
		authMap.put("userName", onBoardingRegistrationPayload.getUserName());
		authMap.put("password", onBoardingRegistrationPayload.getPassword());
		authMap.put("emailID", onBoardingRegistrationPayload.getEmailId());
		authMap.put("firstName", onBoardingRegistrationPayload.getFirstName());
		authMap.put("lastName", onBoardingRegistrationPayload.getLastName());
		authMap.put("mobileNumber", onBoardingRegistrationPayload.getMobileNumber());
		return authMap;
	}

	public void buildRequestCustomerVerificationAdd(String resourcePathURI, String customerVerification) {
		log.info("Initializing the add Registration payload.");
		CustomerVerificationPayLoad customerVerification1 = new CustomerVerificationPayLoad(customerVerification);
		Map<String, String> headerMap = new HashMap<>();
		String authToken = AuthUtils.getAuthorizationToken();
		// Init header map
		headerMap.put("Authorization", authToken);
		headerMap.put("SourceType", "0");
		log.info("Initializing the Registration specification.");
		reqSpec = requestSpecification(resourcePathURI, headerMap, customerVerification1);
	}

	public void hitSetRegistration() {
		String endPoint;
		Routes routes = Routes.valueOf(SET_CUSTOMER_REG_ENDPOINT);
		endPoint = routes.getResource();
		log.info("Hit the add  Registration with given request specification: " + reqSpec + "\n"
				+ "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " + HttpVerbs.POST.name());
		// Getting the response
		response = RestUtils.getResponse(reqSpec, HttpVerbs.POST.name(), endPoint);
		// Printing the response
		response.prettyPrint();
		// JsonPath path = RestUtils.rawToJson(response);
		jsonPath = RestUtils.rawToJson(response);
	}

	public void hitCustomerVerificationRegistration() {
		String endPoint;
		Routes routes = Routes.valueOf(CUSTOMER_VERIFICATION_ENDPOINT);
		endPoint = routes.getResource();
		log.info("Hit the add  Registration with given request specification: " + reqSpec + "\n"
				+ "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " + HttpVerbs.POST.name());
		// Getting the response
		response = RestUtils.getResponse(reqSpec, HttpVerbs.POST.name(), endPoint);
		// Printing the response
		response.prettyPrint();
		// JsonPath path = RestUtils.rawToJson(response);
		jsonPath = RestUtils.rawToJson(response);
	}

	public void buildRequestValidateRegistrationAdd(String resourcePathURI) {
		log.info("Initializing the add  validate Registration payload.");
		Map<String, String> headerMap = new HashMap<>();
		String authToken = AuthUtils.getAuthorizationToken();
		// Init header map
		headerMap.put("Authorization", authToken);
		headerMap.put("SourceType", "0");
		log.info("Initializing the  validate Registration specification.");
		reqSpec = requestSpecification(resourcePathURI, headerMap, registrationPayload);
	}

	public void hitValidateRegistration() {
		String endPoint;
		Routes routes = Routes.valueOf(VALIDATE_SINGLE_REG_ENDPOINT);
		endPoint = routes.getResource();
		log.info("Hit the add  Registration with given request specification: " + reqSpec + "\n"
				+ "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " + HttpVerbs.POST.name());
		// Getting the response
		response = RestUtils.getResponse(reqSpec, HttpVerbs.POST.name(), endPoint);
		// Printing the response
		response.prettyPrint();
		// JsonPath path = RestUtils.rawToJson(response);
		jsonPath = RestUtils.rawToJson(response);
	}

	public void buildRequestGetRegistrationAdd(String resourcePathURI) {
		log.info("Initializing the add  get Registration payload.");
		RegistrationFormPayLoad registrationPayload = new RegistrationFormPayLoad();
		Map<String, String> headerMap = new HashMap<>();
		String authToken = AuthUtils.getAuthorizationToken();
		// Init header map
		headerMap.put("Authorization", authToken);
		headerMap.put("SourceType", "0");
		log.info("Initializing the  get Registration specification.");
		reqSpec = requestSpecification(resourcePathURI, headerMap, registrationPayload);
	}

	public void hitGetRegistration() {
		String endPoint;
		Routes routes = Routes.valueOf(GET_REGISTRATION_ENDPOINT);
		endPoint = routes.getResource();
		log.info("Hit the add   get Registration with given request specification: " + reqSpec + "\n"
				+ "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " + HttpVerbs.POST.name());
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

	public String getAPIResponseCustomerVerificationId() {
		return jsonPath.getString("result.Data[0].CustomerVerificationID");
	}

	public String getAPIResponseMessageForAllTopics() {
		return jsonPath.getString("result.Message");
	}

	public String getAPIResponseMessageForContactUs() {
		return jsonPath.getString("result.Message");
	}

	public HashMap getRegistrationFormResponse() {
		HashMap<String, Map> map1 = new HashMap<>();
		HashMap<String, String> map = null;
		JsonPath j = new JsonPath(response.asString());
		List<Object> dataCount = j.getList("result.Data.ControlList");
		List<Object> dataCount1 = j.getList("result.Data.ControlList[1].'1'");
		for (int i = 0; i < dataCount.size(); i++) {
			for (int k = 0; k < dataCount1.size(); k++) {
				map = new HashMap<>();
				String str = jsonPath
						.getString("result.Data.ControlList[" + i + "].'" + i + "'[" + k + "].ControlName");
				map.put("ControlName",
						jsonPath.getString("result.Data.ControlList[" + i + "].'" + i + "'[" + k + "].ControlName"));
				map.put("MinLength",
						jsonPath.getString("result.Data.ControlList[" + i + "].'" + i + "'[" + k + "].Min_Length"));
				map.put("MaxLength",
						jsonPath.getString("result.Data.ControlList[" + i + "].'" + i + "'[" + k + "].Max_Length"));
				map.put("Mandatory", jsonPath
						.getString("result.Data.ControlList[" + i + "].'" + i + "'[" + k + "].Validation_Against_CIS"));
				map.put("Type", jsonPath.getString("result.Data.ControlList[" + i + "].'" + i + "'[" + k + "].Type"));
				map1.put(str, map);
			}
		}
		return map1;
	}

	public void buildRequestNotificationPrefAdd(String resourcePathURI, String UtilityAccountNumber, String userID,
			String loginToken) {
		log.info("Initializing the add Registration payload.");
		Map<String, String> headerMap = new HashMap<>();
		HashMap<String, String> params = new HashMap<>();
		String authToken = AuthUtils.getAuthorizationToken();
		// Init header map
		headerMap.put("Authorization", loginToken);
		headerMap.put("SourceType", "1");
		params.put("UtilityAccountNumber", UtilityAccountNumber);
		params.put("UserID", userID);
		params.put("connectMe", "0");
		log.info("Initializing the Registration specification.");
		reqSpec = requestSpecification(resourcePathURI, headerMap, params);
	}

	public void hitNotificationPref() {
		String endPoint;
		Routes routes = Routes.valueOf(NOTIFICATION_PREF_ENDPOINT);
		endPoint = routes.getResource();
		log.info("Hit the add   get Registration with given request specification: " + reqSpec + "\n"
				+ "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " + HttpVerbs.GET.name());
		// Getting the response
		response = RestUtils.getResponse(reqSpec, HttpVerbs.GET.name(), endPoint);
		// Printing the response
		response.prettyPrint();
		// JsonPath path = RestUtils.rawToJson(response);
		jsonPath = RestUtils.rawToJson(response);
	}

	public void buildReqSpecForCreateRegistrationOtp(String resourcePathURI, Customer customer) {
		log.info("Initializing the headers.");
		Map<String, String> headers = new HashMap<>();
		String authToken = AuthUtils.getAuthorizationToken();
		// Init header map
		headers.put("Authorization", authToken);
		headers.put("SourceType", "0");
		// Init payload for create auth code
		customer.setAuthenticationType(1);
		customer.setCreateAuthCode(true);
		customer.setResendAuthCode(false);
		customer.setToken(" ");
		customer.setVerifyAuthCode(false);
		log.info("Initializing the add Registration payload.");
		RegistrationPayLoad registrationPayload = new RegistrationPayLoad(customer);
		log.info("Initializing the Registration request specification.");
		requestSpecification(resourcePathURI, headers, registrationPayload);
	}

	public void hitSingleRegistrationAPI() {
		String endPoint;
		Routes routes = Routes.valueOf(VALIDATE_SINGLE_REG_ENDPOINT);
		endPoint = routes.getResource();
		log.info("Hit the add  Registration with given request specification: " + reqSpec + "\n"
				+ "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " + HttpVerbs.POST.name());
		// Getting the response
		response = RestUtils.getResponse(reqSpec, HttpVerbs.POST.name(), endPoint);
		// Printing the response
		response.prettyPrint();
		// JsonPath path = RestUtils.rawToJson(response);
		jsonPath = RestUtils.rawToJson(response);
	}

	public void buildReqSpecForSubmitRegistrationOtp(String resourcePathURI, Customer customer) {
		log.info("Initializing the headers.");
		Map<String, String> headers = new HashMap<>();
		String authToken = AuthUtils.getAuthorizationToken();
		// Init header map
		headers.put("Authorization", authToken);
		headers.put("SourceType", "0");
		// Init payload for create auth code
		customer.setAuthenticationType(1);
		customer.setCreateAuthCode(false);
		customer.setResendAuthCode(false);
		customer.setToken(getOTP());
		customer.setVerifyAuthCode(true);
		log.info("Initializing the add Registration payload.");
		RegistrationPayLoad registrationPayload = new RegistrationPayLoad(customer);
		log.info("Initializing the Registration request specification.");
		requestSpecification(resourcePathURI, headers, registrationPayload);
	}

	public void registrationBypassMFA() {
		// ToDo - Remove this code implement it in from calling class
		Customer customer = ModelsConfiguration.readCustomerConfig().getCustomerByName("John Doe");
		Random random = new Random();
		int randomInt = random.nextInt(10);
		String addressType = "Residential";
		String accountStatus = "3";
		try {
			ResultSet resultSet = DataBaseUtils.getResultSet(SQLQueries.getRegData(addressType, accountStatus));
			resultSet.next();
			customer.setUtilityAccountNumber(resultSet.getString("utilityaccountnumber"));
			customer.setZipCode(resultSet.getString("zipcode"));
			customer.setMeterNumber(resultSet.getString("meternumber"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		customer.setFirstName(customer.getFirstName() + randomInt);
		customer.setLastName(customer.getLastName() + randomInt);
		customer.setUserName(customer.getUserName() + "@_" + randomInt);
		customer.setEmailAddress(customer.getEmailAddress() + randomInt + "@mailinator.com");
		// Todo - Remove till here
		// Build request specification for creating OTP
		buildReqSpecForCreateRegistrationOtp(ResourcePaths.SCP_COMMON_PATH_URI, customer);
		// Hit create registration API
		hitSingleRegistrationAPI();
		// Build request specification for submitting OTP
		buildReqSpecForSubmitRegistrationOtp(ResourcePaths.SCP_COMMON_PATH_URI, customer);
		// Hit create registration API
		hitSingleRegistrationAPI();
	}

	public String getOTP() {
		Assert.assertEquals(response.getStatusCode(), 200);
		String message = RestUtils.getJsonPath(response, "result.Message");
		Assert.assertEquals(message, "Authentication code has been sent.");
		// Get the OTP for MFA
		String uniqueKey = utilityAccNum + "||";
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

	public void hitCustVerificationRegistration() {
		String endPoint;
		Routes routes = Routes.valueOf(CUSTOMER_VERIFICATION_ENDPOINT);
		endPoint = routes.getResource();
		log.info("Hit the add  Registration with given request specification: " + reqSpec + "\n"
				+ "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " + HttpVerbs.POST.name());
		// Getting the response
		response = RestUtils.getResponse(reqSpec, HttpVerbs.POST.name(), endPoint);
		// Printing the response
		response.prettyPrint();
		// JsonPath path = RestUtils.rawToJson(response);
		jsonPath = RestUtils.rawToJson(response);
	}
}

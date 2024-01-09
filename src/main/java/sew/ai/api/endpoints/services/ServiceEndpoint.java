package sew.ai.api.endpoints.services;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sew.ai.api.enums.HttpVerbs;
import sew.ai.api.enums.Routes;
import sew.ai.api.pojos.services.ServiceMoveInPayload;
import sew.ai.api.utils.AuthUtils;
import sew.ai.api.utils.RestUtils;
import sew.ai.config.ModelsConfiguration;
import sew.ai.config.SCPConfiguration;
import sew.ai.helpers.props.ResourcePaths;
import sew.ai.models.Services;
import sew.ai.models.User;
import sew.ai.utils.RandomUtil;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static sew.ai.config.SCPConfiguration.user;

public class ServiceEndpoint extends RestUtils {

	 private static final Logger log = LogManager.getLogger(ServiceEndpoint.class);
	private final String SERVICE_SUBMITREQUESTFORMS_ENDPOINT = "SubmitServiceRequestFormEnpoint";
	private final String SERVICE_SAVEREQUESTFORMS_ENDPOINT = "SaveServiceRequestFormEnpoint";
	private final String MOVE_IN_REQUEST_ENDPOINT = "MoveInRequestEndpoint";

	String apiRoute = null;
	RequestSpecification reqSpec = null;
	Response response = null;


	public String createMoveInServicesRequest() {
		Services services = ModelsConfiguration.readServices().getServicesByTopicName("MoveIn Services");
		buildRequestSpecToCreateConnectMeMoveInServices(user, ResourcePaths.SCP_COMMON_PATH_URI,services);
		services.setReason("Outage Reason"+ RandomUtil.generateRandomString(4, RandomUtil.Mode.NUMERIC));
		services.setSubject("Test Report Outage Subject"+ RandomUtil.generateRandomString(4, RandomUtil.Mode.NUMERIC));
		services.setMessageBody("test outage messageBody"+ RandomUtil.generateRandomString(4, RandomUtil.Mode.NUMERIC));
		String servicesReqID = hitServicesAPI();
		return servicesReqID;
	}

	public String createPreLogMoveInServicesRequest() {
		User user = SCPConfiguration.user;
		user.setDefaultUtilityAccNum("");
		Services services = ModelsConfiguration.readServices().getServicesByTopicName("MoveIn Services");
		buildRequestSpecToCreateConnectMeMoveInServices(user, ResourcePaths.SCP_COMMON_PATH_URI,services);
		services.setReason("Outage Reason"+ RandomUtil.generateRandomString(4, RandomUtil.Mode.NUMERIC));
		services.setSubject("Test Report Outage Subject"+ RandomUtil.generateRandomString(4, RandomUtil.Mode.NUMERIC));
		services.setMessageBody("test outage messageBody"+ RandomUtil.generateRandomString(4, RandomUtil.Mode.NUMERIC));
		services.setIsPreLogin(true);
		services.setCustomerName("");
		String servicesReqID = hitServicesAPI();
		return servicesReqID;
	}

	public void buildRequestSpecToCreateConnectMeMoveInServices(User user, String resourcePathURI, Services services) {
		log.info("Initializing the MoveIn Services payload.");
		ServiceMoveInPayload serviceMoveInPayload = new ServiceMoveInPayload(user,services);
		Map<String, String> headerMap = new HashMap<>();
		// Init header map
		headerMap.put("Authorization", AuthUtils.getAuthorizationToken());
		headerMap.put("SourceType", "0");
		log.info("Initializing the MoveIn Services BuildRequestSpecification.");
		reqSpec = requestSpecification(resourcePathURI, headerMap,serviceMoveInPayload);
	}

	public String hitServicesAPI() {
		String endPoint;

		Routes routes = Routes.valueOf(MOVE_IN_REQUEST_ENDPOINT);
		endPoint = routes.getResource();

		log.info("Hit the MoveIn ServicesAPI with given request specification: " +
				reqSpec + "\n" +
				"and the endpoint is : " + endPoint + "\n" + "and with given http verb : " +
				HttpVerbs.POST.name());
		// Getting the response
		response = RestUtils.getResponse(reqSpec, HttpVerbs.POST.name(), endPoint);
		// Printing the response
		response.prettyPrint();
		// JsonPath path = RestUtils.rawToJson(response);
		assertEquals(response.getStatusCode(), 200);
		jsonPath = RestUtils.rawToJson(response);
		return jsonPath.getString("result.Data.Table.EncSaveID").replace("[", "").replace("]", "");
	}

//	public void buildRequestSpecPostLoginOthersServiceAdd(String jwtToken, int AccountNumber,
//			String UtilityAccountNumber, String FromEMail, int UserID) {
//
//		 log.info("Initializing the add Other Service payload.");
//		ServicePayload servicePayload = new ServicePayload(AccountNumber, UtilityAccountNumber, FromEMail, UserID);
//
//		Map<String, String> headerMap = new HashMap<>();
//
//		//String authToken = AuthUtils.getAuthorizationToken();
//		String resourcePathURI = ResourcePaths.SCP_COMMON_PATH_URI;
//		// Init header map
//		headerMap.put("Authorization", jwtToken);
//		headerMap.put("SourceType", "1");
//
//		 log.info("Initializing the add Move In Service specification.");
//
//		reqSpec = requestSpecification(resourcePathURI, headerMap, servicePayload);
//
//	}
//
//	public void hitAddServices() {
//
//		String endPoint;
//
//		Routes routes = Routes.valueOf(SERVICE_SUBMITREQUESTFORMS_ENDPOINT);
//		endPoint = routes.getResource();
//
//		 log.info("Hit the add  Service with given request specification: " +
//		 reqSpec + "\n" +
//		 "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " +
//		 HttpVerbs.POST.name());
//		// Getting the response
//		response = RestUtils.getResponse(reqSpec, HttpVerbs.POST.name(), endPoint);
//		// Printing the response
//		response.prettyPrint();
//		// JsonPath path = RestUtils.rawToJson(response);
//		jsonPath = RestUtils.rawToJson(response);
//
//	}
//
//	public int getAPIResponseCode() {
//		return response.getStatusCode();
//	}
//
//	public String getAPIResponseMessage() {
//		return jsonPath.getString("result.Data.Table[0].EncSaveID");
//	}
//
//	public String getAPIResponseMessageForSave() {
//		return jsonPath.getString("result.Data");
//	}
//
//	public void buildRequestSpecPreLoginOtherServiceAdd(int AccountNumber, String UtilityAccountNumber,
//			String FromEMail, int UserID) {
//
//		 log.info("Initializing the add Move In Service payload.");
//		ServicePayload servicePayload = new ServicePayload(AccountNumber, UtilityAccountNumber, FromEMail, UserID);
//
//		Map<String, String> headerMap = new HashMap<>();
//
//		String authToken = AuthUtils.getAuthorizationToken();
//		String resourcePathURI = ResourcePaths.SCP_COMMON_PATH_URI;
//		// Init header map
//		headerMap.put("Authorization", authToken);
//		headerMap.put("SourceType", "0");
//
//		 log.info("Initializing the add Move In Service specification.");
//
//		reqSpec = requestSpecification(resourcePathURI, headerMap, servicePayload);
//
//	}
//
//	public void buildRequestSpecPostLoginOthersSaveServiceAdd(String jwtToken, int AccountNumber,
//			String UtilityAccountNumber, String FromEMail, int UserID) {
//
//		 log.info("Initializing the add Move In Service payload.");
//		ServicePayload servicePayload = new ServicePayload(AccountNumber, UtilityAccountNumber, FromEMail, UserID);
//
//		Map<String, String> headerMap = new HashMap<>();
//		String authToken = AuthUtils.getAuthorizationToken();
//		String resourcePathURI = ResourcePaths.SCP_COMMON_PATH_URI;
//		// Init header map
//		headerMap.put("Authorization", jwtToken);
//		headerMap.put("SourceType", "1");
//
//		log.info("Initializing the add Move In Service specification.");
//
//		reqSpec = requestSpecification(resourcePathURI, headerMap, servicePayload);
//
//	}
//
//	public void hitSaveServices() {
//
//		String endPoint;
//
//		Routes routes = Routes.valueOf(SERVICE_SAVEREQUESTFORMS_ENDPOINT);
//		endPoint = routes.getResource();
//
//		 log.info("Hit the add move in Service with given request specification: " +
//		 reqSpec + "\n" +
//		 "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " +
//		 HttpVerbs.POST.name());
//		// Getting the response
//		response = RestUtils.getResponse(reqSpec, HttpVerbs.POST.name(), endPoint);
//		// Printing the response
//		response.prettyPrint();
//		// JsonPath path = RestUtils.rawToJson(response);
//		jsonPath = RestUtils.rawToJson(response);
//
//	}
//
//	public void buildRequestSpecPreLoginOthersSaveService(int AccountNumber, String UtilityAccountNumber,
//			String FromEMail, int UserID) {
//
//		 log.info("Initializing the add Move In Service payload.");
//		ServicePayload servicePayload = new ServicePayload(AccountNumber, UtilityAccountNumber, FromEMail, UserID);
//
//		Map<String, String> headerMap = new HashMap<>();
//		String authToken = AuthUtils.getAuthorizationToken();
//		String resourcePathURI = ResourcePaths.SCP_COMMON_PATH_URI;
//		// Init header map
//
//		headerMap.put("Authorization", authToken);
//		headerMap.put("SourceType", "0");
//
//		 log.info("Initializing the add Move In Service specification.");
//
//		reqSpec = requestSpecification(resourcePathURI, headerMap, servicePayload);
//
//	}
//
//	public void buildRequestSpecPreLoginMoveInServiceAdd(int AccountNumber, String UtilityAccountNumber,
//			String FromEMail, int UserID) {
//
//		 log.info("Initializing the add Move In Service payload.");
//		ServiceMoveInPayload serviceMoveInPayload = new ServiceMoveInPayload(AccountNumber, UtilityAccountNumber,
//				FromEMail, UserID);
//
//		Map<String, String> headerMap = new HashMap<>();
//
//		String authToken = AuthUtils.getAuthorizationToken();
//		String resourcePathURI = ResourcePaths.SCP_COMMON_PATH_URI;
//		// Init header map
//		headerMap.put("Authorization", authToken);
//		headerMap.put("SourceType", "0");
//
//		 log.info("Initializing the add Move In Service specification.");
//
//		reqSpec = requestSpecification(resourcePathURI, headerMap, serviceMoveInPayload);
//
//	}
//
//	public void hitMoveInAddService() {
//
//		String endPoint;
//
//		Routes routes = Routes.valueOf(SERVICE_SUBMITREQUESTFORMS_ENDPOINT);
//		endPoint = routes.getResource();
//
//		 log.info("Hit the add move in Service with given request specification: " +
//		 reqSpec + "\n" +
//		 "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " +
//		 HttpVerbs.POST.name());
//		// Getting the response
//		response = RestUtils.getResponse(reqSpec, HttpVerbs.POST.name(), endPoint);
//		// Printing the response
//		response.prettyPrint();
//		// JsonPath path = RestUtils.rawToJson(response);
//		jsonPath = RestUtils.rawToJson(response);
//
//	}
//
//	public void buildRequestSpecPostLoginMoveInServiceAdd(String jwtToken, int AccountNumber,
//			String UtilityAccountNumber, String FromEMail, int UserID) {
//
//		 log.info("Initializing the add Move In Service payload.");
//		ServiceMoveInPayload serviceMoveInPayload = new ServiceMoveInPayload(AccountNumber, UtilityAccountNumber,
//				FromEMail, UserID);
//		Map<String, String> headerMap = new HashMap<>();
//
//		String authToken = AuthUtils.getAuthorizationToken();
//		String resourcePathURI = ResourcePaths.SCP_COMMON_PATH_URI;
//		// Init header map
//		headerMap.put("Authorization", jwtToken);
//		headerMap.put("SourceType", "1");
//
//		 log.info("Initializing the add Move In Service specification.");
//
//		reqSpec = requestSpecification(resourcePathURI, headerMap, serviceMoveInPayload);
//
//	}
//
//	public void buildRequestSpecPreLoginMoveInSaveService(int AccountNumber, String UtilityAccountNumber,
//			String FromEMail, int UserID) {
//
//		 log.info("Initializing the add Move In Service payload.");
//		ServiceMoveInPayload serviceMoveInPayload = new ServiceMoveInPayload(AccountNumber, UtilityAccountNumber,
//				FromEMail, UserID);
//		Map<String, String> headerMap = new HashMap<>();
//		String authToken = AuthUtils.getAuthorizationToken();
//		String resourcePathURI = ResourcePaths.SCP_COMMON_PATH_URI;
//		// Init header map
//
//		headerMap.put("Authorization", authToken);
//		headerMap.put("SourceType", "0");
//		 log.info("Initializing the add Move In Service specification.");
//
//		reqSpec = requestSpecification(resourcePathURI, headerMap, serviceMoveInPayload);
//
//	}
//
//	public void buildRequestSpecPostLoginMoveInSaveService(String jwtToken, int AccountNumber,
//			String UtilityAccountNumber, String FromEMail, int UserID) {
//
//		 log.info("Initializing the add Move In Service payload.");
//		ServiceMoveInPayload serviceMoveInPayload = new ServiceMoveInPayload(AccountNumber, UtilityAccountNumber,
//				FromEMail, UserID);
//		Map<String, String> headerMap = new HashMap<>();
//		String authToken = AuthUtils.getAuthorizationToken();
//		String resourcePathURI = ResourcePaths.SCP_COMMON_PATH_URI;
//		// Init header map
//		headerMap.put("Authorization", jwtToken);
//		headerMap.put("SourceType", "1");
//		 log.info("Initializing the add Move In Service specification.");
//
//		reqSpec = requestSpecification(resourcePathURI, headerMap, serviceMoveInPayload);
//
//	}
//
//	public void buildRequestSpecPreLoginMoveOutServiceAdd(int AccountNumber, String UtilityAccountNumber,
//			String FromEMail, int UserID) {
//
//		 log.info("Initializing the add Move In Service payload.");
//		ServiceMoveOutPayload serviceMoveOutPayload = new ServiceMoveOutPayload(AccountNumber, UtilityAccountNumber,
//				FromEMail, UserID);
//		Map<String, String> headerMap = new HashMap<>();
//		String authToken = AuthUtils.getAuthorizationToken();
//		String resourcePathURI = ResourcePaths.SCP_COMMON_PATH_URI;
//		// Init header map
//		headerMap.put("Authorization", authToken);
//		headerMap.put("SourceType", "0");
//		log.info("Initializing the add Move In Service specification.");
//
//		reqSpec = requestSpecification(resourcePathURI, headerMap, serviceMoveOutPayload);
//
//	}
//
//	public void buildRequestSpecPostLoginMoveOutServiceAdd(String jwtToken, int AccountNumber,
//			String UtilityAccountNumber, String FromEMail, int UserID) {
//
//		 log.info("Initializing the add Move In Service payload.");
//		ServiceMoveOutPayload serviceMoveOutPayload = new ServiceMoveOutPayload(AccountNumber, UtilityAccountNumber,
//				FromEMail, UserID);
//		Map<String, String> headerMap = new HashMap<>();
//		String authToken = AuthUtils.getAuthorizationToken();
//		String resourcePathURI = ResourcePaths.SCP_COMMON_PATH_URI;
//		// Init header map
//		headerMap.put("Authorization", jwtToken);
//		headerMap.put("SourceType", "1");
//		 log.info("Initializing the add Move In Service specification.");
//
//		reqSpec = requestSpecification(resourcePathURI, headerMap, serviceMoveOutPayload);
//
//	}
//
//	public void buildRequestSpecPostLoginSaveMoveOutService(String jwtToken, int AccountNumber,
//			String UtilityAccountNumber, String FromEMail, int UserID) {
//
//		 log.info("Initializing the add Move In Service payload.");
//		ServiceMoveOutPayload serviceMoveOutPayload = new ServiceMoveOutPayload(AccountNumber, UtilityAccountNumber,
//				FromEMail, UserID);
//		Map<String, String> headerMap = new HashMap<>();
//		String authToken = AuthUtils.getAuthorizationToken();
//		String resourcePathURI = ResourcePaths.SCP_COMMON_PATH_URI;
//		// Init header map
//		headerMap.put("Authorization", jwtToken);
//		headerMap.put("SourceType", "1");
//
//		 log.info("Initializing the add Move In Service specification.");
//
//		reqSpec = requestSpecification(resourcePathURI, headerMap, serviceMoveOutPayload);
//
//	}
//	public void buildRequestSpecPreLoginSaveMoveOutService(int AccountNumber,
//			String UtilityAccountNumber, String FromEMail, int UserID) {
//
//		 log.info("Initializing the add Move In Service payload.");
//		ServiceMoveOutPayload serviceMoveOutPayload = new ServiceMoveOutPayload(AccountNumber, UtilityAccountNumber,
//				FromEMail, UserID);
//		Map<String, String> headerMap = new HashMap<>();
//		String authToken = AuthUtils.getAuthorizationToken();
//		String resourcePathURI = ResourcePaths.SCP_COMMON_PATH_URI;
//		// Init header map
//		headerMap.put("Authorization", authToken);
//		headerMap.put("SourceType", "0");
//
//		 log.info("Initializing the add Move In Service specification.");
//
//		reqSpec = requestSpecification(resourcePathURI, headerMap, serviceMoveOutPayload);
//
//	}
//
//	public void buildRequestSpecPreLoginServiceTransferServiceAdd(int AccountNumber, String UtilityAccountNumber,
//			String FromEMail, int UserID) {
//
//		 log.info("Initializing the add Move In Service payload.");
//		ServiceTransferPayload serviceMoveOutPayload = new ServiceTransferPayload(AccountNumber, UtilityAccountNumber,
//				FromEMail, UserID);
//
//		Map<String, String> headerMap = new HashMap<>();
//		String authToken = AuthUtils.getAuthorizationToken();
//		String resourcePathURI = ResourcePaths.SCP_COMMON_PATH_URI;
//		// Init header map
//		headerMap.put("Authorization", authToken);
//		headerMap.put("SourceType", "0");
//
//		 log.info("Initializing the add Move In Service specification.");
//
//		reqSpec = requestSpecification(resourcePathURI, headerMap, serviceMoveOutPayload);
//
//	}
//
//	public void buildRequestSpecPostLoginServiceTransferServiceAdd(String jwtToken, int AccountNumber,
//			String UtilityAccountNumber, String FromEMail, int UserID) {
//
//		 log.info("Initializing the add Move In Service payload.");
//		ServiceTransferPayload serviceMoveOutPayload = new ServiceTransferPayload(AccountNumber, UtilityAccountNumber,
//				FromEMail, UserID);
//
//		Map<String, String> headerMap = new HashMap<>();
//		String authToken = AuthUtils.getAuthorizationToken();
//		String resourcePathURI = ResourcePaths.SCP_COMMON_PATH_URI;
//		// Init header map
//		headerMap.put("Authorization", jwtToken);
//		headerMap.put("SourceType", "1");
//
//		log.info("Initializing the add Move In Service specification.");
//
//		reqSpec = requestSpecification(resourcePathURI, headerMap, serviceMoveOutPayload);
//
//	}
//
//	public void buildRequestSpecPreLoginServiceTransferSaveService(int AccountNumber, String UtilityAccountNumber,
//			String FromEMail, int UserID) {
//
//		 log.info("Initializing the add Move In Service payload.");
//		ServiceTransferPayload serviceMoveOutPayload = new ServiceTransferPayload(AccountNumber, UtilityAccountNumber,
//				FromEMail, UserID);
//
//		Map<String, String> headerMap = new HashMap<>();
//		String authToken = AuthUtils.getAuthorizationToken();
//		String resourcePathURI = ResourcePaths.SCP_COMMON_PATH_URI;
//		// Init header map
//
//		headerMap.put("Authorization", authToken);
//		headerMap.put("SourceType", "0");
//		 log.info("Initializing the add Move In Service specification.");
//
//		reqSpec = requestSpecification(resourcePathURI, headerMap, serviceMoveOutPayload);
//
//	}
//
//	public void buildRequestSpecPostLoginServiceTransferSaveService(String jwtToken, int AccountNumber,
//			String UtilityAccountNumber, String FromEMail, int UserID) {
//
//		log.info("Initializing the add Move In Service payload.");
//		ServiceTransferPayload serviceMoveOutPayload = new ServiceTransferPayload(AccountNumber, UtilityAccountNumber,
//				FromEMail, UserID);
//
//		Map<String, String> headerMap = new HashMap<>();
//		String authToken = AuthUtils.getAuthorizationToken();
//		String resourcePathURI = ResourcePaths.SCP_COMMON_PATH_URI;
//		// Init header map
//		headerMap.put("Authorization", jwtToken);
//		headerMap.put("SourceType", "1");
//		 log.info("Initializing the add Move In Service specification.");
//
//		reqSpec = requestSpecification(resourcePathURI, headerMap, serviceMoveOutPayload);
//
//	}

}

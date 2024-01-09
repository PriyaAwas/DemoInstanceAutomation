package sew.ai.api.endpoints.auth;

import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sew.ai.api.enums.HttpVerbs;
import sew.ai.api.enums.Routes;
import sew.ai.api.pojos.services.ServiceMoveInPayload;
import sew.ai.api.pojos.services.ServiceMoveOutPayload;
import sew.ai.api.pojos.services.ServicePayload;
import sew.ai.api.pojos.services.ServiceTransferPayload;
import sew.ai.api.utils.AuthUtils;
import sew.ai.api.utils.RestUtils;
import sew.ai.models.Services;
import sew.ai.models.User;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ServicesEndpoint extends RestUtils {

	private static final Logger log = LogManager.getLogger(ServicesEndpoint.class);
	private final String SERVICE_SUBMITREQUESTFORMS_ENDPOINT = "SubmitServiceRequestFormEnpoint";
	private final String SERVICE_SAVEREQUESTFORMS_ENDPOINT = "SaveServiceRequestFormEnpoint";
	private final String SERVICE_GETSAVEFORMS_ENDPOINT = "GetSaveFormsRequestFormEnpoint";
	private final String SERVICE_DELETESAVEFORMS_ENDPOINT = "DeleteSavedFormsRequestFormEnpoint";
	private final String SERVICE_SUBMITFORMS_ENDPOINT = "GetSubmitFormsRequestFormEnpoint";
	private final String SERVICE_SERVICETOPICS_ENDPOINT = "ServiceTopicsRequestFormEnpoint";

//	POST /Service/GetSavedForms
	String apiRoute = null;
	RequestSpecification reqSpec = null;
	Response response = null;

	public void buildRequestSpecPostLoginOthersServiceAdd(User user, String resourcePathURI, Services service) {

		log.info("Initializing the add Other Service payload.");
		ServicePayload servicePayload = new ServicePayload(user, service);
		Map<String, String> headerMap = new HashMap<>();
		// Init header map
		headerMap.put("Authorization", user.getJwtToken());
		headerMap.put("SourceType", "1");
		log.info("Initializing the add Move In Service specification.");
		reqSpec = requestSpecification(resourcePathURI, headerMap, servicePayload);

	}

	public void hitAddServices() {

		String endPoint;
		Routes routes = Routes.valueOf(SERVICE_SUBMITREQUESTFORMS_ENDPOINT);
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

	public String getAPIResponseMessageForEncSaveID() {
		return jsonPath.getString("result.Data.Table[0].EncSaveID");
	}

	public String getAPIResponseMessageForSaveID() {
		return jsonPath.getString("result.Data");
	}

	public String getAPIResponseTopicName() {
		return jsonPath.getString("result.Data.Table[0].TopicName");
	}

	public String getAPIResponseMessage() {
		return jsonPath.getString("result.Message");
	}

	public String getAPIResponseMessageForSubmit() {
		return jsonPath.getString("result.Data.Table[0].Message");
	}

	public String getAPIResponseMessageForServiceTopics() {
		return jsonPath.getString("result.Message");
	}

	public String getAPIResponseMessageForService() {
		return jsonPath.getString("result.Message");
	}

	public void buildRequestSpecPreLoginOtherServiceAdd(User user, String resourcePathURI, Services service) {

		log.info("Initializing the add Others Service payload.");
		ServicePayload servicePayload = new ServicePayload(user, service);
		Map<String, String> headerMap = new HashMap<>();
		String authToken = AuthUtils.getAuthorizationToken();
		// Init header map
		headerMap.put("Authorization", authToken);
		headerMap.put("SourceType", "0");
		log.info("Initializing the add Others Service specification.");
		reqSpec = requestSpecification(resourcePathURI, headerMap, servicePayload);

	}

	public void buildRequestSpecPostLoginOthersSaveServiceAdd(User user, String resourcePathURI, Services service) {

		log.info("Initializing the add Others Service payload.");
		ServicePayload servicePayload = new ServicePayload(user, service);
		Map<String, String> headerMap = new HashMap<>();
		// Init header map
		headerMap.put("Authorization", user.getJwtToken());
		headerMap.put("SourceType", "1");
		log.info("Initializing the add Move In Service specification.");
		reqSpec = requestSpecification(resourcePathURI, headerMap, servicePayload);

	}

	public void hitSaveServices() {

		String endPoint;
		Routes routes = Routes.valueOf(SERVICE_SAVEREQUESTFORMS_ENDPOINT);
		endPoint = routes.getResource();
		log.info("Hit the add move in Service with given request specification: " + reqSpec + "\n"
				+ "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " + HttpVerbs.POST.name());
		// Getting the response
		response = RestUtils.getResponse(reqSpec, HttpVerbs.POST.name(), endPoint);
		// Printing the response
		response.prettyPrint();
		// JsonPath path = RestUtils.rawToJson(response);
		jsonPath = RestUtils.rawToJson(response);

	}

	public void buildRequestSpecPreLoginOthersSaveService(User user, String resourcePathURI, Services service) {

		log.info("Initializing the add Others Service payload.");
		ServicePayload servicePayload = new ServicePayload(user, service);
		Map<String, String> headerMap = new HashMap<>();
		String authToken = AuthUtils.getAuthorizationToken();
		// Init header map
		headerMap.put("Authorization", authToken);
		headerMap.put("SourceType", "0");
		log.info("Initializing the add Others Service specification.");
		reqSpec = requestSpecification(resourcePathURI, headerMap, servicePayload);

	}

	public void buildRequestSpecPreLoginMoveInServiceAdd(User user, String resourcePathURI, Services service) {

		log.info("Initializing the add Move In Service payload.");
		ServiceMoveInPayload serviceMoveInPayload = new ServiceMoveInPayload(user, service);
		Map<String, String> headerMap = new HashMap<>();
		String authToken = AuthUtils.getAuthorizationToken();
		// Init header map
		headerMap.put("Authorization", authToken);
		headerMap.put("SourceType", "0");
		log.info("Initializing the add Move In Service specification.");
		reqSpec = requestSpecification(resourcePathURI, headerMap, serviceMoveInPayload);

	}

	public void hitMoveInAddService() {

		String endPoint;
		Routes routes = Routes.valueOf(SERVICE_SUBMITREQUESTFORMS_ENDPOINT);
		endPoint = routes.getResource();
		log.info("Hit the add move in Service with given request specification: " + reqSpec + "\n"
				+ "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " + HttpVerbs.POST.name());
		// Getting the response
		response = RestUtils.getResponse(reqSpec, HttpVerbs.POST.name(), endPoint);
		// Printing the response
		response.prettyPrint();
		// JsonPath path = RestUtils.rawToJson(response);
		jsonPath = RestUtils.rawToJson(response);

	}

	public void buildRequestSpecPostLoginMoveInServiceAdd(User user, String resourcePathURI, Services service) {

		log.info("Initializing the add Move In Service payload.");
		ServiceMoveInPayload serviceMoveInPayload = new ServiceMoveInPayload(user, service);
		Map<String, String> headerMap = new HashMap<>();
		// Init header map
		headerMap.put("Authorization", user.getJwtToken());
		headerMap.put("SourceType", "1");
		log.info("Initializing the add Move In Service specification.");
		reqSpec = requestSpecification(resourcePathURI, headerMap, serviceMoveInPayload);

	}

	public void buildRequestSpecPreLoginMoveInSaveService(User user, String resourcePathURI, Services service) {

		log.info("Initializing the add Move In Service payload.");
		ServiceMoveInPayload serviceMoveInPayload = new ServiceMoveInPayload(user, service);
		Map<String, String> headerMap = new HashMap<>();
		String authToken = AuthUtils.getAuthorizationToken();
		// Init header map
		headerMap.put("Authorization", authToken);
		headerMap.put("SourceType", "0");
		log.info("Initializing the add Move In Service specification.");
		reqSpec = requestSpecification(resourcePathURI, headerMap, serviceMoveInPayload);

	}

	public void buildRequestSpecPostLoginMoveInSaveService(User user, String resourcePathURI, Services service) {

		log.info("Initializing the add Move In Service payload.");
		ServiceMoveInPayload serviceMoveInPayload = new ServiceMoveInPayload(user, service);
		Map<String, String> headerMap = new HashMap<>();
		String authToken = AuthUtils.getAuthorizationToken();
		// Init header map
		headerMap.put("Authorization", user.getJwtToken());
		headerMap.put("SourceType", "1");
		log.info("Initializing the add Move In Service specification.");
		reqSpec = requestSpecification(resourcePathURI, headerMap, serviceMoveInPayload);

	}

	public void buildRequestSpecPreLoginMoveOutServiceAdd(User user, String resourcePathURI, Services service) {

		log.info("Initializing the add Move Out Service payload.");
		ServiceMoveOutPayload serviceMoveOutPayload = new ServiceMoveOutPayload(user, service);
		Map<String, String> headerMap = new HashMap<>();
		String authToken = AuthUtils.getAuthorizationToken();
		// Init header map
		headerMap.put("Authorization", authToken);
		headerMap.put("SourceType", "0");
		log.info("Initializing the add Move Out Service specification.");
		reqSpec = requestSpecification(resourcePathURI, headerMap, serviceMoveOutPayload);

	}

	public void buildRequestSpecPostLoginMoveOutServiceAdd(User user, String resourcePathURI, Services service) {

		log.info("Initializing the add Move Out Service payload.");
		ServiceMoveOutPayload serviceMoveOutPayload = new ServiceMoveOutPayload(user, service);
		Map<String, String> headerMap = new HashMap<>();
		String authToken = AuthUtils.getAuthorizationToken();
		// Init header map
		headerMap.put("Authorization", user.getJwtToken());
		headerMap.put("SourceType", "1");
		log.info("Initializing the add Move Out Service specification.");
		reqSpec = requestSpecification(resourcePathURI, headerMap, serviceMoveOutPayload);

	}

	public void buildRequestSpecPostLoginSaveMoveOutService(User user, String resourcePathURI, Services service) {

		log.info("Initializing the add Move Out Service payload.");
		ServiceMoveOutPayload serviceMoveOutPayload = new ServiceMoveOutPayload(user, service);
		Map<String, String> headerMap = new HashMap<>();
		// Init header map
		headerMap.put("Authorization", user.getJwtToken());
		headerMap.put("SourceType", "1");
		log.info("Initializing the add Move Out Service specification.");
		reqSpec = requestSpecification(resourcePathURI, headerMap, serviceMoveOutPayload);

	}

	public void buildRequestSpecPreLoginSaveMoveOutService(User user, String resourcePathURI, Services service) {

		log.info("Initializing the add Save Service payload.");
		ServiceMoveOutPayload servicePayload = new ServiceMoveOutPayload(user, service);
		Map<String, String> headerMap = new HashMap<>();
		String authToken = AuthUtils.getAuthorizationToken();
		// Init header map
		headerMap.put("Authorization", authToken);
		headerMap.put("SourceType", "0");
		log.info("Initializing the add Save Service specification.");
		reqSpec = requestSpecification(resourcePathURI, headerMap, servicePayload);

	}

	public void buildRequestSpecPreLoginServiceTransferServiceAdd(User user, String resourcePathURI, Services service) {

		log.info("Initializing the add Service Transfer Service payload.");
		ServiceTransferPayload servicePayload = new ServiceTransferPayload(user, service);
		Map<String, String> headerMap = new HashMap<>();
		String authToken = AuthUtils.getAuthorizationToken();
		// Init header map
		headerMap.put("Authorization", authToken);
		headerMap.put("SourceType", "0");
		log.info("Initializing the add Service transfer specification.");

		reqSpec = requestSpecification(resourcePathURI, headerMap, servicePayload);

	}

	public void buildRequestSpecPostLoginServiceTransferServiceAdd(User user, String resourcePathURI,
			Services service) {

		log.info("Initializing the add Service Transfer Service payload.");
		ServiceTransferPayload servicePayload = new ServiceTransferPayload(user, service);
		Map<String, String> headerMap = new HashMap<>();
		String authToken = AuthUtils.getAuthorizationToken();
		// Init header map
		headerMap.put("Authorization", user.getJwtToken());
		headerMap.put("SourceType", "1");
		log.info("Initializing the add Move In Service specification.");
		reqSpec = requestSpecification(resourcePathURI, headerMap, servicePayload);

	}

	public void buildRequestSpecPreLoginServiceTransferSaveService(User user, String resourcePathURI,
			Services service) {

		log.info("Initializing the add Move In Service payload.");
		ServiceTransferPayload servicePayload = new ServiceTransferPayload(user, service);
		Map<String, String> headerMap = new HashMap<>();
		String authToken = AuthUtils.getAuthorizationToken();
		// Init header map
		headerMap.put("Authorization", authToken);
		headerMap.put("SourceType", "0");
		log.info("Initializing the add Move In Service specification.");
		reqSpec = requestSpecification(resourcePathURI, headerMap, servicePayload);

	}

	public void buildRequestSpecPostLoginServiceTransferSaveService(User user, String resourcePathURI,
			Services service) {

		log.info("Initializing the Service transfer payload.");
		ServiceTransferPayload servicePayload = new ServiceTransferPayload(user, service);
		Map<String, String> headerMap = new HashMap<>();
		String authToken = AuthUtils.getAuthorizationToken();
		// Init header map
		headerMap.put("Authorization", user.getJwtToken());
		headerMap.put("SourceType", "1");
		log.info("Initializing the add Service transfer specification.");
		reqSpec = requestSpecification(resourcePathURI, headerMap, servicePayload);

	}

	public void hitGetSaveFormsService() {

		String endPoint;
		Routes routes = Routes.valueOf(SERVICE_GETSAVEFORMS_ENDPOINT);
		endPoint = routes.getResource();
		log.info("Hit the add move in Service with given request specification: " + reqSpec + "\n"
				+ "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " + HttpVerbs.POST.name());
		// Getting the response
		response = RestUtils.getResponse(reqSpec, HttpVerbs.POST.name(), endPoint);
		// Printing the response
		response.prettyPrint();
		// JsonPath path = RestUtils.rawToJson(response);
		jsonPath = RestUtils.rawToJson(response);

	}

	public void hitDeleteSaveFormsService() {

		String endPoint;
		Routes routes = Routes.valueOf(SERVICE_DELETESAVEFORMS_ENDPOINT);
		endPoint = routes.getResource();
		log.info("Hit the add move in Service with given request specification: " + reqSpec + "\n"
				+ "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " + HttpVerbs.POST.name());
		// Getting the response
		response = RestUtils.getResponse(reqSpec, HttpVerbs.POST.name(), endPoint);
		// Printing the response
		response.prettyPrint();
		// JsonPath path = RestUtils.rawToJson(response);
		jsonPath = RestUtils.rawToJson(response);

	}

	public void hitSubmitFormsService() {

		String endPoint;
		Routes routes = Routes.valueOf(SERVICE_SUBMITFORMS_ENDPOINT);
		endPoint = routes.getResource();
		log.info("Hit the add move in Service with given request specification: " + reqSpec + "\n"
				+ "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " + HttpVerbs.POST.name());
		// Getting the response
		response = RestUtils.getResponse(reqSpec, HttpVerbs.POST.name(), endPoint);
		// Printing the response
		response.prettyPrint();
		// JsonPath path = RestUtils.rawToJson(response);
		jsonPath = RestUtils.rawToJson(response);

	}

	public void hitServiceTopics() {

		String endPoint;
		Routes routes = Routes.valueOf(SERVICE_SERVICETOPICS_ENDPOINT);
		endPoint = routes.getResource();
		log.info("Hit the add move in Service with given request specification: " + reqSpec + "\n"
				+ "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " + HttpVerbs.POST.name());
		// Getting the response
		response = RestUtils.getResponse(reqSpec, HttpVerbs.POST.name(), endPoint);
		// Printing the response
		response.prettyPrint();
		// JsonPath path = RestUtils.rawToJson(response);
		jsonPath = RestUtils.rawToJson(response);

	}

}

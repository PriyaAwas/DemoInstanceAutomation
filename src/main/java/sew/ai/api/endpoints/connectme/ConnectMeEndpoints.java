package sew.ai.api.endpoints.connectme;

import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sew.ai.api.enums.HttpVerbs;
import sew.ai.api.enums.Routes;
import sew.ai.api.pojos.connectme.ConnectMePayload;
import sew.ai.api.pojos.connectme.ConnectMeProgramsPayload;
import sew.ai.api.pojos.connectme.ConnectReportOutage;
import sew.ai.api.utils.AuthUtils;
import sew.ai.api.utils.RestUtils;
import sew.ai.config.ModelsConfiguration;
import sew.ai.config.SCPConfiguration;
import sew.ai.helpers.props.ResourcePaths;
import sew.ai.models.ConnectMe;
import sew.ai.models.User;
import sew.ai.utils.RandomUtil;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static sew.ai.config.SCPConfiguration.user;

public class ConnectMeEndpoints extends RestUtils {

	 private static final Logger log = LogManager.getLogger(ConnectMeEndpoints.class);
	private final String CONNECTME_SUBMITREQUESTFORMS_ENDPOINT = "SetConnectMeRequest";
	private final String CONNECTME_SAVEREQUESTFORMS_ENDPOINT = "SaveConnectMeRequest";
	public final String REPORT_OUTAGE_ENDPOINT = "ReportOutageEndpoint";

	public static ConnectReportOutage reportOutage = null;

	public RequestSpecification generateReportOutageRequest(Map<String, String> reqHeader,
																   Object payLoad) {
		return given().spec(RestUtils.requestSpecification(ResourcePaths.SCP_COMMON_PATH_URI, reqHeader, payLoad));
	}

	public HashMap reportOutageConnectMeRequest() {
		ConnectMe connectMe = ModelsConfiguration.readConnectme().getConnectMeByTopicName("Outage");
		buildRequestSpecToCreateConnectMeOutage(user,ResourcePaths.SCP_COMMON_PATH_URI,connectMe);
		connectMe.setReason("Outage Reason"+ RandomUtil.generateRandomString(4, RandomUtil.Mode.NUMERIC));
		connectMe.setSubject("Test Report Outage Subject"+ RandomUtil.generateRandomString(4, RandomUtil.Mode.NUMERIC));
		connectMe.setMessageBody("test outage messageBody"+ RandomUtil.generateRandomString(4, RandomUtil.Mode.NUMERIC));
		HashMap responseData = hitConnectMeOutageAPI();
		return responseData;
	}

	public HashMap reportPreLogOutageConnectMeRequest() {
		User user = SCPConfiguration.user;
		user.setDefaultUtilityAccNum("");
		ConnectMe connectMe = ModelsConfiguration.readConnectme().getConnectMeByTopicName("Outage");
		buildRequestSpecToCreateConnectMeOutage(user,ResourcePaths.SCP_COMMON_PATH_URI,connectMe);
		connectMe.setReason("Outage Reason"+ RandomUtil.generateRandomString(4, RandomUtil.Mode.NUMERIC));
		connectMe.setSubject("Test Report Outage Subject"+ RandomUtil.generateRandomString(4, RandomUtil.Mode.NUMERIC));
		connectMe.setMessageBody("test outage messageBody"+ RandomUtil.generateRandomString(4, RandomUtil.Mode.NUMERIC));
		connectMe.setIsPreLogin(true);
		connectMe.setCustomerName("");
		HashMap responseData = hitConnectMeOutageAPI();
		return responseData;
	}

	public HashMap reportOutageConnectMeRequestWithBlankComments() {
		ConnectMe connectMe = ModelsConfiguration.readConnectme().getConnectMeByTopicName("Outage");
		buildRequestSpecToCreateConnectMeOutage(user,ResourcePaths.SCP_COMMON_PATH_URI,connectMe);
		connectMe.setReason("Outage Reason"+ RandomUtil.generateRandomString(4, RandomUtil.Mode.NUMERIC));
		connectMe.setSubject("Test Report Outage Subject"+ RandomUtil.generateRandomString(4, RandomUtil.Mode.NUMERIC));
		connectMe.setMessageBody("");
		HashMap responseData = hitConnectMeOutageAPI();
		return responseData;
	}

	public void buildRequestSpecToCreateConnectMeOutage(User user, String resourcePathURI, ConnectMe connectMe) {
		log.info("Initializing the add Connect me payload.");
		ConnectReportOutage connectReportOutage = new ConnectReportOutage(user,connectMe);
		// Init header map
		Map<String, String> headerMap = new HashMap<>();
		headerMap.put("Authorization", AuthUtils.getAuthorizationToken());
		headerMap.put("SourceType", "0");
		log.info("Initializing the add  Outage connect me specification.");
		reqSpec = requestSpecification(resourcePathURI, headerMap,connectReportOutage);

	}
	public HashMap hitConnectMeOutageAPI() {

		String endPoint;

		Routes routes = Routes.valueOf(CONNECTME_SUBMITREQUESTFORMS_ENDPOINT);
		endPoint = routes.getResource();

		log.info("Hit the add Outage ConnectMe with given request specification: " +
				reqSpec + "\n" +
				"and the endpoint is : " + endPoint + "\n" + "and with given http verb : " +
				HttpVerbs.POST.name());
		// Getting the response
		response = RestUtils.getResponse(reqSpec, HttpVerbs.POST.name(), endPoint);
		// Printing the response
		response.prettyPrint();
		// JsonPath path = RestUtils.rawToJson(response);
		assertEquals(response.getStatusCode(), 200,"The ConnectMe Outage API Success Code is not as Expected.");
		jsonPath = RestUtils.rawToJson(response);
		HashMap responseData = new HashMap();
		responseData.put("AccountNumber", jsonPath.get("result.Data.Table[0].UtilityAccountNumber"));
		responseData.put("OutageRequestID", jsonPath.get("result.Data.Table[0].EncSaveID"));
		return responseData;

	}

	public String reportProgramsConnectMeRequest() {
		ConnectMe connectMe = ModelsConfiguration.readConnectme().getConnectMeByTopicName("Programs");
		connectMe.setReason("ConnectMeProgramsAPI Reason"+ RandomUtil.generateRandomString(4, RandomUtil.Mode.NUMERIC));
		connectMe.setSubject("ConnectMePrograms Subject"+ RandomUtil.generateRandomString(4, RandomUtil.Mode.NUMERIC));
		connectMe.setMessageBody("ConnectMePrograms messageBody"+ RandomUtil.generateRandomString(4, RandomUtil.Mode.NUMERIC));
		connectMe.setIsPreLogin(false);
		buildRequestSpecToCreateConnectMePrograms(user,ResourcePaths.SCP_COMMON_PATH_URI,connectMe);
		String programsRequestID = hitConnectMeProgramsAPI();
		System.out.println("The RequestID for Programs Created through ConnectMeAPI Request is "+programsRequestID);
		return programsRequestID;
	}

	public void buildRequestSpecToCreateConnectMePrograms(User user, String resourcePathURI, ConnectMe connectMe) {

		log.info("Initializing the add Connect me payload.");
		ConnectMeProgramsPayload connectMeProgramsPayload = new ConnectMeProgramsPayload(user,connectMe);

		Map<String, String> headerMap = new HashMap<>();
		// Init header map
		headerMap.put("Authorization", AuthUtils.getAuthorizationToken());
		headerMap.put("SourceType", "0");

		log.info("Initializing the add  Programs connect me specification.");

		reqSpec = requestSpecification(resourcePathURI, headerMap,connectMeProgramsPayload);

	}


	public String hitConnectMeProgramsAPI() {

		String endPoint;

		Routes routes = Routes.valueOf(CONNECTME_SUBMITREQUESTFORMS_ENDPOINT);
		endPoint = routes.getResource();

		log.info("Hit the add Programs ConnectMe with given request specification: " +
				reqSpec + "\n" +
				"and the endpoint is : " + endPoint + "\n" + "and with given http verb : " +
				HttpVerbs.POST.name());
		// Getting the response
		response = RestUtils.getResponse(reqSpec, HttpVerbs.POST.name(), endPoint);
		// Printing the response
		response.prettyPrint();
		assertEquals(response.getStatusCode(), 200,"The ConnectMe Programs API Success Code is not as Expected.");
		jsonPath = RestUtils.rawToJson(response);
		return  jsonPath.getString("result.Data.Table.EncSaveID").replace("[", "").replace("]", "");

	}


	public void buildRequestSpecPostLoginConnectmeAdd(User user, String resourcePathURI, ConnectMe connectMe) {

		log.info("Initializing the add Connect me payload.");
		ConnectMePayload connectmePayload = new ConnectMePayload(user,connectMe);

		Map<String, String> headerMap = new HashMap<>();
		// Init header map
		headerMap.put("Authorization", user.getJwtToken());
		headerMap.put("SourceType", "1");

		 log.info("Initializing the add connect me specification.");

		reqSpec = requestSpecification(resourcePathURI, headerMap,connectmePayload);

	}



	public void hitAddServices() {

		String endPoint;

		Routes routes = Routes.valueOf(CONNECTME_SUBMITREQUESTFORMS_ENDPOINT);
		endPoint = routes.getResource();

		 log.info("Hit the add  Service with given request specification: " +
		 reqSpec + "\n" +
		 "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " +
		 HttpVerbs.POST.name());
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
		return jsonPath.getString("result.Data.Table[0].EncSaveID");
	}

	public String getAPIResponseMessageForSave() {
		return jsonPath.getString("result.Data");
	}

/*	public void buildRequestSpecPreLoginContactusAdd(User user,String resourcePathURI) {

		 log.info("Initializing the add pre login contact us payload.");
		 ConnectmePayload connectmePayload = new ConnectmePayload(user);
		 Map<String, String> headerMap = new HashMap<>();
		 String authToken = AuthUtils.getAuthorizationToken();
		// Init header map
		headerMap.put("Authorization", authToken);
		headerMap.put("SourceType", "0");

		 log.info("Initializing the add pre login contact us specification.");

		reqSpec = requestSpecification(resourcePathURI, headerMap, connectmePayload);

	}

	public void buildRequestSpecPostLoginConnectmeSave(User user,String resourcePathURI) {

		 log.info("Initializing the add Connect me payload.");
		 ConnectmePayload connectmePayload = new ConnectmePayload(user);

		Map<String, String> headerMap = new HashMap<>();
		String authToken = AuthUtils.getAuthorizationToken();
		// Init header map
		headerMap.put("Authorization",user.getJwtToken());
		headerMap.put("SourceType", "1");

		log.info("Initializing the add Move In Service specification.");

		reqSpec = requestSpecification(resourcePathURI, headerMap, connectmePayload);

	}

	public void hitSaveServices() {

		String endPoint;

		Routes routes = Routes.valueOf(CONNECTME_SAVEREQUESTFORMS_ENDPOINT);
		endPoint = routes.getResource();

		 log.info("Hit the add move in Service with given request specification: " +
		 reqSpec + "\n" +
		 "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " +
		 HttpVerbs.POST.name());
		// Getting the response
		response = RestUtils.getResponse(reqSpec, HttpVerbs.POST.name(), endPoint);
		// Printing the response
		response.prettyPrint();
		// JsonPath path = RestUtils.rawToJson(response);
		jsonPath = RestUtils.rawToJson(response);

	}

	public void buildRequestSpecPreLoginContactUsSave(User user,String resourcePathURI) {

		 log.info("Initializing the add PreLogin Contact us payload.");
		 ConnectmePayload connectmePayload = new ConnectmePayload(user);

		Map<String, String> headerMap = new HashMap<>();
		String authToken = AuthUtils.getAuthorizationToken();
		
		// Init header map

		headerMap.put("Authorization", authToken);
		headerMap.put("SourceType", "0");

		 log.info("Initializing the add Move In Service specification.");

		reqSpec = requestSpecification(resourcePathURI, headerMap,connectmePayload);

	}
*/
	
}

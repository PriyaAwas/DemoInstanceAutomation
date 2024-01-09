package sew.ai.api.endpoints.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sew.ai.api.enums.HttpVerbs;
import sew.ai.api.enums.Routes;
import sew.ai.api.pojos.service.ServiceMoveInPayload;
import sew.ai.api.pojos.service.ServiceMoveOutPayload;
import sew.ai.api.pojos.service.ServicePayload;
import sew.ai.api.pojos.service.ServiceTransferPayload;
import sew.ai.api.utils.AuthUtils;
import sew.ai.api.utils.RestUtils;
import sew.ai.config.ModelsConfiguration;
import sew.ai.config.SCPConfiguration;
import sew.ai.helpers.props.ResourcePaths;
import sew.ai.models.Services;
import sew.ai.models.User;
import sew.ai.utils.RandomUtil;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ServiceEndpoint extends RestUtils {
	
	 private final String SERVICE_SUBMITREQUESTFORMS_ENDPOINT = "SubmitServiceRequestFormEnpoint";
	 private final String SERVICE_SAVEREQUESTFORMS_ENDPOINT = "SaveServiceRequestFormEnpoint";
	    
	    String apiRoute = null;
	    RequestSpecification reqSpec = null;
		Response response = null;
		AuthUtils au;

	    public String  getServiceTrackRequestID(String jwtToken,int AccountNumber,String UtilityAccountNumber,String FromEMail,int UserID ) {			
	    	ServicePayload  servicePayload = new
				   ServicePayload(AccountNumber,UtilityAccountNumber,FromEMail,UserID );
			 
	           Map<String, String> headerMap = new HashMap<>();
	          AuthUtils authUtil = new AuthUtils();
	          String authToken = authUtil.getAuthorizationHeader();
	          // Init header map
	          headerMap.put("Authorization",jwtToken);
	          headerMap.put("SourceType", "1");
			  reqSpec = requestSpecification(
						  ResourcePaths.SCP_COMMON_PATH_URI,
						   headerMap, 
						   servicePayload
				  );          
	          // Initializing endpoints
	          Routes endPoint = Routes.valueOf(SERVICE_SUBMITREQUESTFORMS_ENDPOINT);
	          apiRoute = endPoint.getResource();
	          // Getting the response
	          response = RestUtils.getResponse(reqSpec, "POST", apiRoute);
	          // Printing the response
	           response.prettyPrint();
	          JsonPath path = RestUtils.rawToJson(response);
	          Assert.assertEquals(path.getString("status_code"), "200");
	         // Assert.assertEquals(path.getString("Message"), "Request successful.");
	          String data = path.getString("result.Data.Table[0].EncSaveID");
         return data;   
	    	}
	    	    
 public String  getPreLoginServiceTrackRequestID(int AccountNumber,String UtilityAccountNumber,String FromEMail,int UserID ) {			
	    	ServicePayload  servicePayload = new
		    ServicePayload(AccountNumber,UtilityAccountNumber,FromEMail,UserID );
	    	Map<String, String> headerMap = new HashMap<>();
	        AuthUtils authUtil = new AuthUtils();
	        String authToken = authUtil.getAuthorizationHeader();
	          // Init header map
	          headerMap.put("Authorization",authToken);
	          headerMap.put("SourceType", "0");
				  reqSpec = requestSpecification(
						  ResourcePaths.SCP_COMMON_PATH_URI,
						   headerMap, 
						   servicePayload
				  );
	   	      // Initializing endpoints
	          Routes endPoint = Routes.valueOf(SERVICE_SUBMITREQUESTFORMS_ENDPOINT);
	          apiRoute = endPoint.getResource();
	          // Getting the response
	          response = RestUtils.getResponse(reqSpec, "POST", apiRoute);
	          // Printing the response
	           response.prettyPrint();
	          JsonPath path = RestUtils.rawToJson(response);
	          Assert.assertEquals(path.getString("status_code"), "200");       
	          String data = path.getString("result.Data.Table[0].EncSaveID");
       return data;   
	    	}
	    
 			public static void main(String str[]) { 
 				ServiceEndpoint sc = new ServiceEndpoint(); 
 				final AuthUtils authUtil = new AuthUtils();
 			String	 userName = "nikhil.test@mailinator.com";
 	       String     password = "Demo@1234567";
 				String s2 =  sc.getPreLoginSaveMoveOutRequestID(6473,"411002248606","nikhil.test@mailinator.com",3301);
 				String s3=  sc. getPreLoginSaveServiceTrackRequestID(6473,"411002248606","nikhil.test@mailinator.com",3301);
			  }
			 			 	    
public String getSaveServiceTrackRequestID(String jwtToken,int AccountNumber,String UtilityAccountNumber,String FromEMail,int UserID ) {			
	    	  ServicePayload  servicePayload = new
		      ServicePayload(AccountNumber,UtilityAccountNumber,FromEMail,UserID );			 
	          Map<String, String> headerMap = new HashMap<>();
	          AuthUtils authUtil = new AuthUtils();
	          String authToken = authUtil.getAuthorizationHeader();
	          // Init header map
	          headerMap.put("Authorization",jwtToken);
	          headerMap.put("SourceType", "1");			
				  reqSpec = requestSpecification(
						  ResourcePaths.SCP_COMMON_PATH_URI,
						   headerMap, 
						   servicePayload
				  );				 	          
	          // Initializing endpoints
	          Routes endPoint = Routes.valueOf(SERVICE_SAVEREQUESTFORMS_ENDPOINT);
	          apiRoute = endPoint.getResource();
	          // Getting the response
	          response = RestUtils.getResponse(reqSpec, "POST", apiRoute);
	          // Printing the response
	           response.prettyPrint();
	          JsonPath path = RestUtils.rawToJson(response);
	          Assert.assertEquals(path.getString("status_code"), "200");	               
	          String data = path.getString("result.Data");
               return data;   
	    	}


public String getPreLoginSaveServiceTrackRequestID(int AccountNumber,String UtilityAccountNumber,String FromEMail,int UserID ) {
         	ServicePayload  servicePayload = new
		   ServicePayload(AccountNumber,UtilityAccountNumber,FromEMail,UserID );
           Map<String, String> headerMap = new HashMap<>();
           AuthUtils authUtil = new AuthUtils();
           String authToken = authUtil.getAuthorizationHeader();
            // Init header map      
      headerMap.put("Authorization", authToken);
      headerMap.put("SourceType", "0");
	  reqSpec = requestSpecification(
				  ResourcePaths.SCP_COMMON_PATH_URI,
				   headerMap, 
				   servicePayload
		  );
		      
      // Initializing endpoints
      Routes endPoint = Routes.valueOf(SERVICE_SAVEREQUESTFORMS_ENDPOINT);
      apiRoute = endPoint.getResource();
      // Getting the response
      response = RestUtils.getResponse(reqSpec, "POST", apiRoute);
      // Printing the response
       response.prettyPrint();
      JsonPath path = RestUtils.rawToJson(response);
      Assert.assertEquals(path.getString("status_code"), "200");  
      String data = path.getString("result.Data");	
      return data;   
	}

public String getPreLoginMoveInRequestID(int AccountNumber,String UtilityAccountNumber,String FromEMail,int UserID ) {
	ServiceMoveInPayload serviceMoveInPayload = new	ServiceMoveInPayload(AccountNumber,UtilityAccountNumber,FromEMail,UserID);	 
       Map<String, String> headerMap = new HashMap<>();
      AuthUtils authUtil = new AuthUtils();
      String authToken = authUtil.getAuthorizationHeader();
      // Init header map
      headerMap.put("Authorization",authToken);
      headerMap.put("SourceType", "0");
		  reqSpec = requestSpecification(
				  ResourcePaths.SCP_COMMON_PATH_URI,
				   headerMap, 
				   serviceMoveInPayload
		  );
		      
      // Initializing endpoints
      Routes endPoint = Routes.valueOf(SERVICE_SUBMITREQUESTFORMS_ENDPOINT);
      apiRoute = endPoint.getResource();
      // Getting the response
      response = RestUtils.getResponse(reqSpec, "POST", apiRoute);
      // Printing the response
       response.prettyPrint();
      JsonPath path = RestUtils.rawToJson(response);
      Assert.assertEquals(path.getString("status_code"), "200");  
      String data = path.getString("result.Data.Table[0].EncSaveID");	
      return data;   
	}

public String  getPostLoginMoveInRequestID(String jwtToken,int AccountNumber,String UtilityAccountNumber,String FromEMail,int UserID ) {	
	ServiceMoveInPayload serviceMoveInPayload = new	ServiceMoveInPayload(AccountNumber,UtilityAccountNumber,FromEMail,UserID);	 
       Map<String, String> headerMap = new HashMap<>();
      AuthUtils authUtil = new AuthUtils();
      String authToken = authUtil.getAuthorizationHeader();
      // Init header map
      headerMap.put("Authorization",jwtToken);
      headerMap.put("SourceType", "1");
	  reqSpec = requestSpecification(
				  ResourcePaths.SCP_COMMON_PATH_URI,
				   headerMap, 
				   serviceMoveInPayload
		  );     
      // Initializing endpoints
      Routes endPoint = Routes.valueOf(SERVICE_SUBMITREQUESTFORMS_ENDPOINT);
      apiRoute = endPoint.getResource();
      // Getting the response
      response = RestUtils.getResponse(reqSpec, "POST", apiRoute);
      // Printing the response
       response.prettyPrint();
      JsonPath path = RestUtils.rawToJson(response);
      Assert.assertEquals(path.getString("status_code"), "200");    
      String data = path.getString("result.Data.Table[0].EncSaveID");	
      return data;   
	}

public String getPreLoginSaveMoveInRequestID(int AccountNumber,String UtilityAccountNumber,String FromEMail,int UserID ) {	
	ServiceMoveInPayload serviceMoveInPayload = new	ServiceMoveInPayload(AccountNumber,UtilityAccountNumber,FromEMail,UserID);	 
       Map<String, String> headerMap = new HashMap<>();
      AuthUtils authUtil = new AuthUtils();
      String authToken = authUtil.getAuthorizationHeader();
      // Init header map      
      headerMap.put("Authorization", authToken);
      headerMap.put("SourceType", "0");	
		  reqSpec = requestSpecification(
				  ResourcePaths.SCP_COMMON_PATH_URI,
				   headerMap, 
				   serviceMoveInPayload
		  );      
      // Initializing endpoints
      Routes endPoint = Routes.valueOf(SERVICE_SAVEREQUESTFORMS_ENDPOINT);
      apiRoute = endPoint.getResource();
      // Getting the response
      response = RestUtils.getResponse(reqSpec, "POST", apiRoute);
      // Printing the response
       response.prettyPrint();
      JsonPath path = RestUtils.rawToJson(response);
      Assert.assertEquals(path.getString("status_code"), "200");
      String data = path.getString("result.Data");	
      return data;   
	}

public String getPostLoginSaveMoveInRequestID(String jwtToken,int AccountNumber,String UtilityAccountNumber,String FromEMail,int UserID ) {
	ServiceMoveInPayload serviceMoveInPayload = new	ServiceMoveInPayload(AccountNumber,UtilityAccountNumber,FromEMail,UserID);
     Map<String, String> headerMap = new HashMap<>();
      AuthUtils authUtil = new AuthUtils(); 
      String authToken = authUtil.getAuthorizationHeader();
      // Init header map
      headerMap.put("Authorization",jwtToken);
      headerMap.put("SourceType", "1");
		  reqSpec = requestSpecification(
				  ResourcePaths.SCP_COMMON_PATH_URI,
				   headerMap, 
				   serviceMoveInPayload
		  );
		 
      // Initializing endpoints
      Routes endPoint = Routes.valueOf(SERVICE_SAVEREQUESTFORMS_ENDPOINT);
      apiRoute = endPoint.getResource();
      // Getting the response
      response = RestUtils.getResponse(reqSpec, "POST", apiRoute);
      // Printing the response
       response.prettyPrint();
      JsonPath path = RestUtils.rawToJson(response);
      Assert.assertEquals(path.getString("status_code"), "200");   
      String data = path.getString("result.Data");	
      return data;   
	}

public String getPreLoginMoveOutRequestID(int AccountNumber,String UtilityAccountNumber,String FromEMail,int UserID ) {
	ServiceMoveOutPayload serviceMoveOutPayload = new ServiceMoveOutPayload(AccountNumber,UtilityAccountNumber,FromEMail,UserID);	 
       Map<String, String> headerMap = new HashMap<>();
      AuthUtils authUtil = new AuthUtils();
      String authToken = authUtil.getAuthorizationHeader();
      // Init header map
      headerMap.put("Authorization",authToken);
      headerMap.put("SourceType", "0");
	  reqSpec = requestSpecification(
				  ResourcePaths.SCP_COMMON_PATH_URI,
				   headerMap, 
				   serviceMoveOutPayload
		  );      
      // Initializing endpoints
      Routes endPoint = Routes.valueOf(SERVICE_SUBMITREQUESTFORMS_ENDPOINT);
      apiRoute = endPoint.getResource();
      // Getting the response
      response = RestUtils.getResponse(reqSpec, "POST", apiRoute);
      // Printing the response
       response.prettyPrint();
      JsonPath path = RestUtils.rawToJson(response);
      Assert.assertEquals(path.getString("status_code"), "200");
      String data = path.getString("result.Data.Table[0].EncSaveID");
      return data;   
	}

public String getPostLoginMoveOutRequestID(String jwtToken,int AccountNumber,String UtilityAccountNumber,String FromEMail,int UserID ) {
	ServiceMoveOutPayload serviceMoveOutPayload = new ServiceMoveOutPayload(AccountNumber,UtilityAccountNumber,FromEMail,UserID);	 
       Map<String, String> headerMap = new HashMap<>();
      AuthUtils authUtil = new AuthUtils();
      String authToken = authUtil.getAuthorizationHeader();
      // Init header map
      headerMap.put("Authorization",jwtToken);
      headerMap.put("SourceType", "1");
	  reqSpec = requestSpecification(
				  ResourcePaths.SCP_COMMON_PATH_URI,
				   headerMap, 
				   serviceMoveOutPayload
		  );     
      // Initializing endpoints
      Routes endPoint = Routes.valueOf(SERVICE_SUBMITREQUESTFORMS_ENDPOINT);
      apiRoute = endPoint.getResource();
      // Getting the response
      response = RestUtils.getResponse(reqSpec, "POST", apiRoute);
      // Printing the response
       response.prettyPrint();
      JsonPath path = RestUtils.rawToJson(response);
      Assert.assertEquals(path.getString("status_code"), "200");
      String data = path.getString("result.Data.Table[0].EncSaveID");
      return data;   
	}

public String getPreLoginSaveMoveOutRequestID(int AccountNumber,String UtilityAccountNumber,String FromEMail,int UserID ) {
	ServiceMoveOutPayload serviceMoveOutPayload = new ServiceMoveOutPayload(AccountNumber,UtilityAccountNumber,FromEMail,UserID);	 
       Map<String, String> headerMap = new HashMap<>();
      AuthUtils authUtil = new AuthUtils();
      String authToken = authUtil.getAuthorizationHeader();
      // Init header map 
      headerMap.put("Authorization", authToken);
      headerMap.put("SourceType", "0");
		  reqSpec = requestSpecification(
				  ResourcePaths.SCP_COMMON_PATH_URI,
				   headerMap, 
				   serviceMoveOutPayload
		  );      
      // Initializing endpoints
      Routes endPoint = Routes.valueOf(SERVICE_SAVEREQUESTFORMS_ENDPOINT);
      apiRoute = endPoint.getResource();
      // Getting the response
      response = RestUtils.getResponse(reqSpec, "POST", apiRoute);
      // Printing the response
       response.prettyPrint();
      JsonPath path = RestUtils.rawToJson(response);
      Assert.assertEquals(path.getString("status_code"), "200");
     // Assert.assertEquals(path.getString("Message"), "Request successful.");      
      String data = path.getString("result.Data");	
      return data;   
	}

public String getPostLoginSaveMoveOutRequestID(String jwtToken,int AccountNumber,String UtilityAccountNumber,String FromEMail,int UserID ) {
	ServiceMoveOutPayload serviceMoveOutPayload = new ServiceMoveOutPayload(AccountNumber,UtilityAccountNumber,FromEMail,UserID);	 
       Map<String, String> headerMap = new HashMap<>();
      AuthUtils authUtil = new AuthUtils();      
      String authToken = authUtil.getAuthorizationHeader();
      // Init header map
      headerMap.put("Authorization",jwtToken);
      headerMap.put("SourceType", "1");
      reqSpec = requestSpecification(
				  ResourcePaths.SCP_COMMON_PATH_URI,
				   headerMap, 
				   serviceMoveOutPayload
		  );
      // Initializing endpoints
      Routes endPoint = Routes.valueOf(SERVICE_SAVEREQUESTFORMS_ENDPOINT);
      apiRoute = endPoint.getResource();
      // Getting the response
      response = RestUtils.getResponse(reqSpec, "POST", apiRoute);
      // Printing the response
       response.prettyPrint();
      JsonPath path = RestUtils.rawToJson(response);
      Assert.assertEquals(path.getString("status_code"), "200");
     // Assert.assertEquals(path.getString("Message"), "Request successful.");
      String data = path.getString("result.Data");
      return data;   
	}

public String getPreLoginServiceTransferRequestID(int AccountNumber,String UtilityAccountNumber,String FromEMail,int UserID ) {
	ServiceTransferPayload serviceMoveOutPayload = new ServiceTransferPayload(AccountNumber,UtilityAccountNumber,FromEMail,UserID);	 
       Map<String, String> headerMap = new HashMap<>();
      AuthUtils authUtil = new AuthUtils();
      String authToken = authUtil.getAuthorizationHeader();
      // Init header map
      headerMap.put("Authorization",authToken);
      headerMap.put("SourceType", "0");	
		  reqSpec = requestSpecification(
				  ResourcePaths.SCP_COMMON_PATH_URI,
				   headerMap, 
				   serviceMoveOutPayload
		  );
		 
      
      // Initializing endpoints
      Routes endPoint = Routes.valueOf(SERVICE_SUBMITREQUESTFORMS_ENDPOINT);
      apiRoute = endPoint.getResource();
      // Getting the response
      response = RestUtils.getResponse(reqSpec, "POST", apiRoute);
      // Printing the response
       response.prettyPrint();
      JsonPath path = RestUtils.rawToJson(response);
      Assert.assertEquals(path.getString("status_code"), "200");
     // Assert.assertEquals(path.getString("Message"), "Request successful.");     
      String data = path.getString("result.Data.Table[0].EncSaveID");	
      return data;   
	}

public String getPostLoginServiceTransferRequestID(String jwtToken,int AccountNumber,String UtilityAccountNumber,String FromEMail,int UserID ) {
	ServiceTransferPayload serviceMoveOutPayload = new ServiceTransferPayload(AccountNumber,UtilityAccountNumber,FromEMail,UserID);	 
       Map<String, String> headerMap = new HashMap<>();
      AuthUtils authUtil = new AuthUtils();      
      String authToken = authUtil.getAuthorizationHeader();
      // Init header map
      headerMap.put("Authorization",jwtToken);
      headerMap.put("SourceType", "1");
	  reqSpec = requestSpecification(
				  ResourcePaths.SCP_COMMON_PATH_URI,
				   headerMap, 
				   serviceMoveOutPayload
		  );
      // Initializing endpoints
      Routes endPoint = Routes.valueOf(SERVICE_SUBMITREQUESTFORMS_ENDPOINT);
      apiRoute = endPoint.getResource();
      // Getting the response
      response = RestUtils.getResponse(reqSpec, "POST", apiRoute);
      // Printing the response
       response.prettyPrint();
      JsonPath path = RestUtils.rawToJson(response);
      Assert.assertEquals(path.getString("status_code"), "200");
      String data = path.getString("result.Data.Table[0].EncSaveID");
      return data;   
	}

public String getPreLoginSaveServiceTransferRequestID(int AccountNumber,String UtilityAccountNumber,String FromEMail,int UserID ) {
	ServiceTransferPayload serviceMoveOutPayload = new ServiceTransferPayload(AccountNumber,UtilityAccountNumber,FromEMail,UserID);	 
      Map<String, String> headerMap = new HashMap<>();
      AuthUtils authUtil = new AuthUtils();
      String authToken = authUtil.getAuthorizationHeader();
      // Init header map      
      headerMap.put("Authorization", authToken);
      headerMap.put("SourceType", "0");
      reqSpec = requestSpecification(
    		  ResourcePaths.SCP_COMMON_PATH_URI,
				   headerMap, 
				   serviceMoveOutPayload
		  );      
      // Initializing endpoints
      Routes endPoint = Routes.valueOf(SERVICE_SAVEREQUESTFORMS_ENDPOINT);
      apiRoute = endPoint.getResource();
      // Getting the response
      response = RestUtils.getResponse(reqSpec, "POST", apiRoute);
      // Printing the response
       response.prettyPrint();
      JsonPath path = RestUtils.rawToJson(response);
      Assert.assertEquals(path.getString("status_code"), "200");    
      String data = path.getString("result.Data");
      return data;   
	}

public String getPostLoginSaveServiceTransferRequestID(String jwtToken,int AccountNumber,String UtilityAccountNumber,String FromEMail,int UserID ) {
	ServiceTransferPayload serviceMoveOutPayload = new ServiceTransferPayload(AccountNumber,UtilityAccountNumber,FromEMail,UserID);	 
       Map<String, String> headerMap = new HashMap<>();
      AuthUtils authUtil = new AuthUtils();
      String authToken = authUtil.getAuthorizationHeader();
      // Init header map
      headerMap.put("Authorization",jwtToken);
      headerMap.put("SourceType", "1");
      reqSpec = requestSpecification(
    		  ResourcePaths.SCP_COMMON_PATH_URI,
				   headerMap, 
				   serviceMoveOutPayload
		  );
		      
      // Initializing endpoints
      Routes endPoint = Routes.valueOf(SERVICE_SAVEREQUESTFORMS_ENDPOINT);
      apiRoute = endPoint.getResource();
      // Getting the response
      response = RestUtils.getResponse(reqSpec, "POST", apiRoute);
      // Printing the response
       response.prettyPrint();
      JsonPath path = RestUtils.rawToJson(response);
      Assert.assertEquals(path.getString("status_code"), "200");
     // Assert.assertEquals(path.getString("Message"), "Request successful.");      
      String data = path.getString("result.Data");
      return data;   
	}


}

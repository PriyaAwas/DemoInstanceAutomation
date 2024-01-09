package sew.ai.api.endpoints.outage;

import sew.ai.api.enums.HttpVerbs;
import sew.ai.api.enums.Routes;
import sew.ai.api.enums.ServiceTypes;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.models.GetOutagesPayload;
import sew.ai.models.Outage;
import sew.ai.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sew.ai.api.pojos.outage.GetAdminDashboardCountPayload;
import sew.ai.api.pojos.outage.GetOutageDetailsPayload;
import sew.ai.api.pojos.outage.InsertOutagePayload;
import sew.ai.api.pojos.outage.UpdateOutagePayload;
import sew.ai.api.utils.AuthUtils;
import sew.ai.utils.DataBaseUtils;
import sew.ai.api.utils.RestUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OutageEndpoints extends RestUtils {



    private static final Logger log = LogManager.getLogger(OutageEndpoints.class);
    private final String ADD_UPDATE_OUTAGE_ENDPOINT = "AddUpdateOutageEndpoint";
    private final String GET_OUTAGE_DETAILS_CSP_ENDPOINT= "GetOutageDetailsCSPEndpoint";
    private final String GET_ADMIN_OUTAGES_DASHBOARD_COUNT= "GetAdminOutagesDashboardCount";
    private final String GET_OUTAGE_SCP_ENDPOINT = "GetOutageSCPEndpoint";


    public void buildRequestSpecForGettingOutage(int mode, int isPlannedOutage, String searchString, int userID, String resourcePathURI) {
    	
        Map<String, String> headerMap = new HashMap<>();
        AuthUtils authUtil = new AuthUtils();
        String authToken = authUtil.getAuthorizationHeader();
        // Init header map
        headerMap.put("Authorization", authToken);
        headerMap.put("SourceType", "0");
        // Init insert outage payload
        GetOutagesPayload getOutagesPayload = new GetOutagesPayload(mode, isPlannedOutage, searchString, userID);
        // Init request specification
        reqSpec = RestUtils.requestSpecification(
                resourcePathURI,
                headerMap,
                getOutagesPayload
        );
    }
    
    public void hitGetOutageAPI() {
        String endPoint;
        Routes routes = Routes.valueOf(GET_OUTAGE_SCP_ENDPOINT);
        endPoint = routes.getResource();
        log.info("Hit the Create Outage API with given request specification: " + reqSpec + "\n" +
                "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " +
                HttpVerbs.POST.name());
        // Get Response
        response = RestUtils.getResponse(
                reqSpec,
                HttpVerbs.POST.name(),
                endPoint
        );
        // Print the response
        response.prettyPrint();
        // Change the response to json path.
        jsonPath = RestUtils.rawToJson(response);
    }
    
    public List<Map> getOutageDetails(){
    	List<Map> allOutagesList = jsonPath.getList("result.Data.listOutageResultSetTwo");
        return allOutagesList;
    }


    public InsertOutagePayload buildRequestSpecForCreatingOutage(User user, Outage outage, String resourcePathURI) {
        HashMap<String, String> headers = new HashMap<>();
        // Initialize header to post
        headers.put("Authorization", AuthUtils.getAuthorizationHeaderCSP());
        headers.put("SourceType", "0");
        // Initialize payload body
        InsertOutagePayload insertOutagePayload = new InsertOutagePayload(user,outage);
        // Build request specifications
        reqSpec = RestUtils.requestSpecification(
                resourcePathURI,
                headers,
                insertOutagePayload
        );
        return insertOutagePayload;
    }

    public UpdateOutagePayload buildRequestSpecForUpdatingOutage(User user, Outage outage, String resourcePathURI, String outageID) {
        HashMap<String, String> headers = new HashMap<>();
        // Initialize header to post
        headers.put("Authorization", AuthUtils.getAuthorizationHeaderCSP());
        headers.put("SourceType", "0");
        // Initialize payload body
        UpdateOutagePayload updateOutagePayload = new UpdateOutagePayload(user,outage,outageID);
        // Build request specifications
        reqSpec = RestUtils.requestSpecification(
                resourcePathURI,
                headers,
                updateOutagePayload
        );
        return updateOutagePayload;
    }

    public GetOutageDetailsPayload buildRequestSpecForGetOutageDetails(String resourcePathURI,String OutageID) {
        HashMap<String, String> headers = new HashMap<>();
        // Initialize header to post
        headers.put("Authorization", AuthUtils.getAuthorizationHeaderCSP());
        headers.put("SourceType", "0");
        // Initialize payload body
        GetOutageDetailsPayload getOutageDetailsPayload = new GetOutageDetailsPayload(OutageID);
        // Build request specifications
        reqSpec = RestUtils.requestSpecification(
                resourcePathURI,
                headers,
                getOutageDetailsPayload
        );
        return getOutageDetailsPayload;
    }

    public GetAdminDashboardCountPayload buildRequestSpecForGetAdminDashboardCount(String resourcePathURI, Outage outage) {
        HashMap<String, String> headers = new HashMap<>();
        // Initialize header to post
        headers.put("Authorization", AuthUtils.getAuthorizationHeaderCSP());
        headers.put("SourceType", "0");
        // Initialize payload body
        GetAdminDashboardCountPayload getAdminDashboardCountPayload = new GetAdminDashboardCountPayload(outage);
        // Build request specifications
        reqSpec = RestUtils.requestSpecification(
                resourcePathURI,
                headers,
                getAdminDashboardCountPayload
        );
        return getAdminDashboardCountPayload;
    }
        public void hitCreateOutageAPI() {
        String endPoint;
        Routes routes = Routes.valueOf(ADD_UPDATE_OUTAGE_ENDPOINT);
        endPoint = routes.getResource();
        log.info("Hit the Create Outage API with given request specification: " + reqSpec + "\n" +
                "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " +
                HttpVerbs.POST.name());
        // Get Response
        response = RestUtils.getResponse(
                reqSpec,
                HttpVerbs.POST.name(),
                endPoint
        );
        // Print the response
        response.prettyPrint();
        // Change the response to json path.
        jsonPath = RestUtils.rawToJson(response);
    }

    public void hitGetOutageDetailsAPI() {
        String endPoint;
        Routes routes = Routes.valueOf(GET_OUTAGE_DETAILS_CSP_ENDPOINT);
        endPoint = routes.getResource();
        log.info("Hit the Get Outage Details API with given request specification: " + reqSpec + "\n" +
                "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " +
                HttpVerbs.POST.name());
        // Get Response
        response = RestUtils.getResponse(
                reqSpec,
                HttpVerbs.POST.name(),
                endPoint
        );
        // Print the response
        response.prettyPrint();
        // Change the response to json path.
        jsonPath = RestUtils.rawToJson(response);
    }

    public void hitGetAdminOutagesDashboardCountAPI() {
        String endPoint;
        Routes routes = Routes.valueOf(GET_ADMIN_OUTAGES_DASHBOARD_COUNT);
        endPoint = routes.getResource();
        log.info("Hit the Get Outages Dashboard Count API with given request specification: " + reqSpec + "\n" +
                "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " +
                HttpVerbs.POST.name());
        // Get Response
        response = RestUtils.getResponse(
                reqSpec,
                HttpVerbs.POST.name(),
                endPoint
        );
        // Print the response
        response.prettyPrint();
        // Change the response to json path.
        jsonPath = RestUtils.rawToJson(response);
    }

    public String getAPIResponseCode() {
        return String.valueOf(response.getStatusCode());

    }

    public String getAPIResponseStringData(String path) {
        return jsonPath.getString(path).trim();
    }
    public int getAPIResponseIntegerData(String path) {
        return jsonPath.getInt(path);
    }

    public boolean getAPIResponseBooleanData(String path) {
        return jsonPath.getBoolean(path);
    }

    public Integer getAPIResponseOutageID() {
        return jsonPath.getInt("data.OutageID");
    }



    public Map<String, String> getOutageDetailsFromDB(String outageID){
        Map<String, String> outageDetailsDB = null;
        try {
            ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.getOutageDetails(String.valueOf(outageID)));
            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();
            ArrayList<String> columns = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metadata.getColumnName(i);
                columns.add(columnName);
            }
            outageDetailsDB = new LinkedHashMap<>();
            while (rs.next()) {
                for (String columnName : columns) {
                    String value = rs.getString(columnName);
                    outageDetailsDB.put(columnName, value);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return outageDetailsDB;
    }

    public static Integer getServiceTypeID(ServiceTypes serviceTypes){
        int serviceTypeID = 0;

        if(serviceTypes==ServiceTypes.POWER)
            serviceTypeID=1;
        else if(serviceTypes==ServiceTypes.WATER)
            serviceTypeID=2;
        else if(serviceTypes==ServiceTypes.GAS)
            serviceTypeID=3;

        return serviceTypeID;
    }


}

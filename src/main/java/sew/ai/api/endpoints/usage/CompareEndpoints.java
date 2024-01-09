package sew.ai.api.endpoints.usage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sew.ai.api.enums.HttpVerbs;
import sew.ai.api.enums.Routes;
import sew.ai.api.pojos.usage.CompareElectricResponse;
import sew.ai.api.pojos.usage.CompareGasResponse;
import sew.ai.api.pojos.usage.CompareNeighbourResponse;
import sew.ai.api.pojos.usage.CompareWaterResponse;
import sew.ai.api.utils.RestUtils;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.utils.DataBaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CompareEndpoints extends RestUtils {
    private static final Logger log = LogManager.getLogger(CompareEndpoints.class);
    private final String COMPARE_GAS_ENDPOINT = "GetCompareGasData";
    private final String COMPARE_WATER_ENDPOINT = "GetCompareWaterData";
    private final String COMPARE_ELECTRIC_ENDPOINT = "GetCompareElectricData";
    private final String COMPARE_GAS_NEIGHBOUR_ENDPOINT = "GetCompareNeighbourGasData";
    private final String COMPARE_WATER_NEIGHBOUR_ENDPOINT = "GetCompareNeighbourWaterData";
    private final String COMPARE_ELECTRIC_NEIGHBOUR_ENDPOINT = "GetCompareNeighbourElectricData";
    public static String zipCode = null;

    public void buildRequestSpecCompareGasData(String jwtToken, String utilityAccNumber, String resourcePathURI) {
        // Init header map
        /* HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("Authorization", jwtToken);
        headerMap.put("SourceType", "1"); */
        // Init query param
        HashMap<String, Object> params = new HashMap<>();
        params.put("AccountNumber", utilityAccNumber);
        // Initializing request specification
        reqSpec = requestSpecification(
                params,
                resourcePathURI
        );
    }

    public void hitGasCompare() {
        String endPoint;
        Routes routes = Routes.valueOf(COMPARE_GAS_ENDPOINT);
        endPoint = routes.getResource();
        log.info("Hit the gas compare API with given request specification: " + reqSpec + "\n" +
                "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " +
                HttpVerbs.GET.name());
        // Getting the response
        response = RestUtils.getResponse(
                reqSpec,
                HttpVerbs.GET.name(),
                endPoint
        );
        // Printing the response
        response.prettyPrint();
        jsonPath = RestUtils.rawToJson(response);
    }

    public Map<String, CompareGasResponse[]> getCompareGasData() {
        // Deserialize the usage response into the java array object.
        CompareGasResponse[] currCompareDate = jsonPath.getObject("Result.compareGasMe.current",
                CompareGasResponse[].class);
        CompareGasResponse[] prevCompareDate = jsonPath.getObject("Result.compareGasMe.previous",
                CompareGasResponse[].class);
        Map<String, CompareGasResponse[]> compareGasData = new HashMap<>();
        compareGasData.put("Current", currCompareDate);
        compareGasData.put("Previous", prevCompareDate);
        return compareGasData;
    }

    public void buildRequestSpecCompareWaterData(String jwtToken, String utilityAccNumber, String resourcePathURI) {
        // Init header map
        /* HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("Authorization", jwtToken);
        headerMap.put("SourceType", "1"); */
        // Init query param
        HashMap<String, Object> params = new HashMap<>();
        params.put("AccountNumber", utilityAccNumber);
        // Initializing request specification
        reqSpec = requestSpecification(
                params,
                resourcePathURI
        );
    }

    public void hitWaterCompare() {
        String endPoint;
        Routes routes = Routes.valueOf(COMPARE_WATER_ENDPOINT);
        endPoint = routes.getResource();
        log.info("Hit the water compare API with given request specification: " + reqSpec + "\n" +
                "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " +
                HttpVerbs.GET.name());
        // Getting the response
        response = RestUtils.getResponse(
                reqSpec,
                HttpVerbs.GET.name(),
                endPoint
        );
        // Printing the response
        response.prettyPrint();
        jsonPath = RestUtils.rawToJson(response);
    }

    public Map<String, CompareWaterResponse[]> getCompareWaterData() {
        // Deserialize the usage response into the java array object.
        CompareWaterResponse[] currCompareDate = jsonPath.getObject("Result.compareWaterMe.current",
                CompareWaterResponse[].class);
        CompareWaterResponse[] prevCompareDate = jsonPath.getObject("Result.compareWaterMe.previous",
                CompareWaterResponse[].class);
        Map<String, CompareWaterResponse[]> compareWaterData = new HashMap<>();
        compareWaterData.put("Current", currCompareDate);
        compareWaterData.put("Previous", prevCompareDate);
        return compareWaterData;
    }

    public void buildRequestSpecCompareElectricData(String jwtToken, String utilityAccNumber, String resourcePathURI) {
        // Init header map
        /* HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("Authorization", jwtToken);
        headerMap.put("SourceType", "1"); */
        // Init query param
        HashMap<String, Object> params = new HashMap<>();
        params.put("AccountNumber", utilityAccNumber);
        // Initializing request specification
        reqSpec = requestSpecification(
                params,
                resourcePathURI
        );
    }

    public void hitElectricCompare() {
        String endPoint;
        Routes routes = Routes.valueOf(COMPARE_ELECTRIC_ENDPOINT);
        endPoint = routes.getResource();
        log.info("Hit the electric compare API with given request specification: " + reqSpec + "\n" +
                "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " +
                HttpVerbs.GET.name());
        // Getting the response
        response = RestUtils.getResponse(
                reqSpec,
                HttpVerbs.GET.name(),
                endPoint
        );
        // Printing the response
        response.prettyPrint();
        jsonPath = RestUtils.rawToJson(response);
    }

    public int getAPIResponseCode() {
        return response.getStatusCode();
    }

    public String getAPIResponseMessage() {
        return jsonPath.getString("Message").trim();
    }

    public Map<String, CompareElectricResponse[]> getCompareElectricData() {
        // Deserialize the usage response into the java array object.
        CompareElectricResponse[] currCompareDate = jsonPath.getObject("Result.compareElectricMe.current",
                CompareElectricResponse[].class);
        CompareElectricResponse[] prevCompareDate = jsonPath.getObject("Result.compareElectricMe.previous",
                CompareElectricResponse[].class);
        Map<String, CompareElectricResponse[]> compareElectricData = new HashMap();
        compareElectricData.put("Current", currCompareDate);
        compareElectricData.put("Previous", prevCompareDate);
        return compareElectricData;
    }

    public void buildRequestSpecCompareGasNeighbourData(String jwtToken, String utilityAccNumber,
                                                        String type, String resourcePathURI) {
        // Init header map
        /* HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("Authorization", jwtToken);
        headerMap.put("SourceType", "1"); */
        // Init params with database
        //String zipCode = null, addressType = null;
        ResultSet resultSet;
        try {
            resultSet = DataBaseUtils.getResultSet(SQLQueries.getZipCodeOfAccount(utilityAccNumber));
            resultSet.next();
            zipCode = resultSet.getString("zipcode");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Init query param
        HashMap<String, Object> params = new HashMap<>();
        params.put("PostalCode", zipCode);
        params.put("SegmentId", "1");
        params.put("Type", type);
        // Initializing request specification
        reqSpec = requestSpecification(
                params,
                resourcePathURI
        );
    }

    public void hitGasCompareZip() {
        String endPoint;
        Routes routes = Routes.valueOf(COMPARE_GAS_NEIGHBOUR_ENDPOINT);
        endPoint = routes.getResource();
        log.info("Hit the gas compare zip API with given request specification: " + reqSpec + "\n" +
                "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " +
                HttpVerbs.GET.name());
        // Getting the response
        response = RestUtils.getResponse(
                reqSpec,
                HttpVerbs.GET.name(),
                endPoint
        );
        // Printing the response
        response.prettyPrint();
        jsonPath = RestUtils.rawToJson(response);
    }

    public CompareNeighbourResponse[] getCompareZipGasData() {
        // Deserialize the usage response into the java array object.
        CompareNeighbourResponse[] compareNeighbourResponse = jsonPath.getObject("Result.compareGasMaster",
                CompareNeighbourResponse[].class);
        return compareNeighbourResponse;
    }

    public void buildRequestSpecCompareWaterNeighbourData(String jwtToken, String utilityAccNumber,
                                                          String type, String resourcePathURI) {
        // Init header map
        /* HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("Authorization", jwtToken);
        headerMap.put("SourceType", "1"); */
        // Init params with database
        //String zipCode = null;
        ResultSet resultSet;
        try {
            resultSet = DataBaseUtils.getResultSet(SQLQueries.getZipCodeOfAccount(utilityAccNumber));
            resultSet.next();
            zipCode = resultSet.getString("zipcode");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Init query param
        HashMap<String, Object> params = new HashMap<>();
        params.put("PostalCode", zipCode);
        params.put("SegmentId", "1");
        params.put("Type", type);
        // Initializing request specification
        reqSpec = requestSpecification(
                params,
                resourcePathURI
        );
    }

    public void hitWaterCompareZip() {
        String endPoint;
        Routes routes = Routes.valueOf(COMPARE_WATER_NEIGHBOUR_ENDPOINT);
        endPoint = routes.getResource();
        log.info("Hit the water compare zip API with given request specification: " + reqSpec + "\n" +
                "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " +
                HttpVerbs.GET.name());
        // Getting the response
        response = RestUtils.getResponse(
                reqSpec,
                HttpVerbs.GET.name(),
                endPoint
        );
        // Printing the response
        response.prettyPrint();
        jsonPath = RestUtils.rawToJson(response);
    }

    public CompareNeighbourResponse[] getCompareZipWaterData() {
        // Deserialize the usage response into the java array object.
        CompareNeighbourResponse[] compareNeighbourResponse = jsonPath.getObject("Result.compareWaterMaster",
                CompareNeighbourResponse[].class);
        return compareNeighbourResponse;
    }

    public void buildRequestSpecCompareElectricNeighbourData(String jwtToken, String utilityAccNumber,
                                                             String type, String resourcePathURI) {
        // Init header map
        /* HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("Authorization", jwtToken);
        headerMap.put("SourceType", "1"); */
        // Init params with database
        //String zipCode = null;
        ResultSet resultSet;
        try {
            resultSet = DataBaseUtils.getResultSet(SQLQueries.getZipCodeOfAccount(utilityAccNumber));
            resultSet.next();
            zipCode = resultSet.getString("zipcode");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Init query param
        HashMap<String, Object> params = new HashMap<>();
        params.put("PostalCode", zipCode);
        params.put("SegmentId", "1");
        params.put("Type", type);
        // Initializing request specification
        reqSpec = requestSpecification(
                params,
                resourcePathURI
        );
    }

    public void hitElectricCompareZip() {
        String endPoint;
        Routes routes = Routes.valueOf(COMPARE_ELECTRIC_NEIGHBOUR_ENDPOINT);
        endPoint = routes.getResource();
        log.info("Hit the electric compare zip API with given request specification: " + reqSpec + "\n" +
                "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " +
                HttpVerbs.GET.name());
        // Getting the response
        response = RestUtils.getResponse(
                reqSpec,
                HttpVerbs.GET.name(),
                endPoint
        );
        // Printing the response
        response.prettyPrint();
        jsonPath = RestUtils.rawToJson(response);
    }

    public CompareNeighbourResponse[] getCompareZipElectricData() {
        // Deserialize the usage response into the java array object.
        CompareNeighbourResponse[] compareNeighbourResponse = jsonPath.getObject("Result.compareElectricNeighbour",
                CompareNeighbourResponse[].class);
        return compareNeighbourResponse;
    }
}

package sew.ai.api.endpoints.usage;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import sew.ai.api.enums.HttpVerbs;
import sew.ai.api.enums.Routes;
import sew.ai.api.pojos.usage.DashboardUsagePayload;
import sew.ai.api.pojos.usage.UsageParams;
import sew.ai.api.pojos.usage.UsageTilesParam;
import sew.ai.api.pojos.usage.WeatherOverlayParams;
import sew.ai.api.resPojos.GenerationResponse;
import sew.ai.api.resPojos.TilesDetailResponse;
import sew.ai.api.resPojos.UsageResponse;
import sew.ai.api.resPojos.WeatherDataResponse;
import sew.ai.api.utils.RestUtils;
import sew.ai.config.CSPConfiguration;
import sew.ai.enums.Intervals;
import sew.ai.enums.MeterTypes;
import sew.ai.helpers.props.ResourcePaths;
import sew.ai.helpers.reporters.ExtentLogger;
import sew.ai.models.Meter;
import sew.ai.models.User;
import sew.ai.utils.DateUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsageEndpoints extends RestUtils {
    private static final Logger log = LogManager.getLogger(UsageEndpoints.class);
    private final String ELECTRIC_USAGE_ENDPOINT = "GetElectricUsageEndpoint";
    private final String WATER_USAGE_ENDPOINT = "GetWaterUsageEndpoint";
    private final String GAS_USAGE_ENDPOINT = "GetGasUsageEndpoint";
    private final String SOLAR_GENERATION_ENDPOINT = "GetSolarUsageEndpoint";
    private final String PROJECTED_ELECTRIC_ENDPOINT = "GetProjectedElectricEndpoint";
    private final String PROJECTED_WATER_ENDPOINT = "GetProjectedWaterEndpoint";
    private final String PROJECTED_GAS_ENDPOINT = "GetProjectedGasEndpoint";
    private final String PROJECTED_USAGE_ALL_ENDPOINT = "GetProjectedAllEndpoint";
    private final String DASHBOARD_USAGE_ENDPOINT = "GetUsageAndBillGenerationEndpoint";
    private final String WEATHER_OVERLAY_ENDPOINT = "GetWeatherOverlayDataEndpoint";

    public static List<Meter> meters, electricMeters, waterMeters, gasMeters, solarMeters;

    public void buildRequestSpecElectricUsage(User user, UsageParams paramsPojo, String resourcePathURI) {
        log.info("Initializing the get electric usage request specification.");
        /*
         * LoginEndpoint loginEndpoint = new LoginEndpoint(); Map<String, String>
         * headers = new HashMap<>(); headers.put("SourceType", "1");
         * headers.put("Authorization", loginEndpoint.getJwtToken(user.getUserName(),
         * user.getPassword()));
         */
        // Init query param
        HashMap<String, Object> params = new HashMap<>();
        params.put("AccountNumber", paramsPojo.getAccountNumber());
        if (!paramsPojo.getMeterNumber().equals("All"))
            params.put("MeterNumber", paramsPojo.getMeterNumber());
        params.put("From", paramsPojo.getFrom());
        params.put("To", paramsPojo.getTo());
        params.put("Uom", paramsPojo.getUom());
        params.put("Periodicity", paramsPojo.getPeriodicity());
        // Build request specification.
        reqSpec = RestUtils.requestSpecification(params, resourcePathURI);
    }

    public void hitGetElectricUsage() {
        String endPoint;
        Routes routes = Routes.valueOf(ELECTRIC_USAGE_ENDPOINT);
        endPoint = routes.getResource();
        log.info("Hit the get Electric usages API with given request specification: " + reqSpec + "\n"
                + "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " + HttpVerbs.GET.name());
        response = RestUtils.getResponse(reqSpec, HttpVerbs.GET.name(), endPoint);
        // Print the response
        response.prettyPrint();
        // Change the response to json path.
        jsonPath = RestUtils.rawToJson(response);
    }

    public int getAPIResponseCode() {
        return response.getStatusCode();
    }

    public String getAPIResponseMessage() {
        return jsonPath.getString("Message").trim();
    }

    public String getBaseUsageAPIResponseMessage() {
        return jsonPath.getString("result.Message").trim();
    }

    public void buildRequestSpecWaterUsage(User user, UsageParams paramsPojo, String resourcePathURI) {
        log.info("Initializing the get electric usage request specification.");
        /*
         * LoginEndpoint loginEndpoint = new LoginEndpoint(); Map<String, String>
         * headers = new HashMap<>(); headers.put("SourceType", "1");
         * headers.put("Authorization", loginEndpoint.getJwtToken(user.getUserName(),
         * user.getPassword()));
         */
        // Init query param
        HashMap<String, Object> params = new HashMap<>();
        params.put("AccountNumber", paramsPojo.getAccountNumber());
        if (!paramsPojo.getMeterNumber().equals("All"))
            params.put("MeterNumber", paramsPojo.getMeterNumber());
        params.put("From", paramsPojo.getFrom());
        params.put("To", paramsPojo.getTo());
        params.put("Uom", paramsPojo.getUom());
        params.put("Periodicity", paramsPojo.getPeriodicity());
        // Build request specification.
        reqSpec = RestUtils.requestSpecification(params, resourcePathURI);
    }

    public void hitGetWaterUsage() {
        String endPoint;
        Routes routes = Routes.valueOf(WATER_USAGE_ENDPOINT);
        endPoint = routes.getResource();
        log.info("Hit the get Water usage API with given request specification: " + reqSpec + "\n"
                + "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " + HttpVerbs.GET.name());
        response = RestUtils.getResponse(reqSpec, HttpVerbs.GET.name(), endPoint);
        // Print the response
        response.prettyPrint();
        // Change the response to json path.
        jsonPath = RestUtils.rawToJson(response);
    }

    public void buildRequestSpecGasUsage(User user, UsageParams paramsPojo, String resourcePathURI) {
        log.info("Initializing the get electric usage request specification.");
        /*
         * LoginEndpoint loginEndpoint = new LoginEndpoint(); Map<String, String>
         * headers = new HashMap<>(); headers.put("SourceType", "1");
         * headers.put("Authorization", loginEndpoint.getJwtToken(user.getUserName(),
         * user.getPassword()));
         */
        // Init query param
        HashMap<String, Object> params = new HashMap<>();
        params.put("AccountNumber", paramsPojo.getAccountNumber());
        if (!paramsPojo.getMeterNumber().equals("All"))
            params.put("MeterNumber", paramsPojo.getMeterNumber());
        params.put("From", paramsPojo.getFrom());
        params.put("To", paramsPojo.getTo());
        params.put("Uom", paramsPojo.getUom());
        params.put("Periodicity", paramsPojo.getPeriodicity());
        // Build request specification.
        reqSpec = RestUtils.requestSpecification(params, resourcePathURI);
    }

    public void hitGetGasUsage() {
        String endPoint;
        Routes routes = Routes.valueOf(GAS_USAGE_ENDPOINT);
        endPoint = routes.getResource();
        log.info("Hit the get Gas usages API with given request specification: " + reqSpec + "\n"
                + "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " + HttpVerbs.GET.name());
        response = RestUtils.getResponse(reqSpec, HttpVerbs.GET.name(), endPoint);
        // Print the response
        response.prettyPrint();
        // Change the response to json path.
        jsonPath = RestUtils.rawToJson(response);
    }

    public void buildRequestSpecSolarGeneration(User user, UsageParams paramsPojo, String resourcePathURI) {
        log.info("Initializing the get electric usage request specification.");
        /*
         * LoginEndpoint loginEndpoint = new LoginEndpoint(); Map<String, String>
         * headers = new HashMap<>(); headers.put("SourceType", "1");
         * headers.put("Authorization", loginEndpoint.getJwtToken(user.getUserName(),
         * user.getPassword()));
         */
        // Init query param
        HashMap<String, Object> params = new HashMap<>();
        params.put("AccountNumber", paramsPojo.getAccountNumber());
        if (!paramsPojo.getMeterNumber().equals("All"))
            params.put("MeterNumber", paramsPojo.getMeterNumber());
        params.put("From", paramsPojo.getFrom());
        params.put("To", paramsPojo.getTo());
        params.put("Uom", paramsPojo.getUom());
        params.put("Periodicity", paramsPojo.getPeriodicity());
        // Build request specification.
        reqSpec = RestUtils.requestSpecification(params, resourcePathURI);
    }

    public void hitGetSolarGeneration() {
        String endPoint;
        Routes routes = Routes.valueOf(SOLAR_GENERATION_ENDPOINT);
        endPoint = routes.getResource();
        log.info("Hit the get generations API with given request specification: " + reqSpec + "\n"
                + "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " + HttpVerbs.GET.name());
        response = RestUtils.getResponse(reqSpec, HttpVerbs.GET.name(), endPoint);
        // Print the response
        response.prettyPrint();
        // Change the response to json path.
        jsonPath = RestUtils.rawToJson(response);
    }

    public List<UsageResponse> getUsageAPIResponse(String meterTypes) {
        List<UsageResponse> usageResponsesObjMap = new ArrayList<>();
        // Get the response from the API
        List<Map<String, Object>> usageResponsesList = new ArrayList<>();
        if (meterTypes.equals(MeterTypes.E.name())) {
            usageResponsesList = jsonPath.getList("Result.electricUsages");
        }
        else if (meterTypes.equals(MeterTypes.W.name())) {
            usageResponsesList = jsonPath.getList("Result.waterUsages");
        }
        else if (meterTypes.equals(MeterTypes.G.name())) {
            usageResponsesList = jsonPath.getList("Result.gasUsages");
        }
        else if (meterTypes.equals(MeterTypes.PV.name())) {
            usageResponsesList = jsonPath.getList("Result.solarGeneration");
        }
        else {
            log.error(
                    "Invalid meter type. Please double check the meter type and use MeterTypes enum to give meter type.");
        }
        // Todo Implement the logic to sort the list on the basis of meter number:
        /*Collections.sort(usageResponsesList, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                String value1 = (String) o1.get("meterNumber");
                String value2 = (String) o2.get("meterNumber");
                return Integer.valueOf(value1).compareTo(Integer.valueOf(value2));
            }
        });*/
        // Change the map object into the object class
        for (Map<String, Object> responseMap : usageResponsesList) {
            UsageResponse usageResponse = new UsageResponse();
            usageResponse.setAccountNumber((String) responseMap.get("accountNumber"));
            usageResponse.setMeterNumber((String) responseMap.get("meterNumber"));
            usageResponse.setUsageAttribute1((String) responseMap.get("usageAttribute1"));
            usageResponse.setUsageAttribute2((String) responseMap.get("usageAttribute2"));
            usageResponse.setReadingFrom((String) responseMap.get("readingFrom"));
            usageResponse.setReadingTo((String) responseMap.get("readingTo"));
            usageResponse.setAmount(Double.parseDouble(String.valueOf(responseMap.get("amount"))));
            usageResponse.setConsumption(Double.parseDouble(String.valueOf(responseMap.get("consumption"))));
            usageResponse.setTierTou((String) responseMap.get("tierTou"));
            usageResponse.setUom((String) responseMap.get("uom"));
            usageResponse.setReadDate((String) responseMap.get("readDate"));
            usageResponse.setRatePlan((String) responseMap.get("ratePlan"));
            usageResponsesObjMap.add(usageResponse);
        }
        return usageResponsesObjMap;
    }

    public List<UsageResponse> getElectricUsageAPIResponse() {
        List<UsageResponse> usageResponsesObjMap;
        usageResponsesObjMap = getUsageAPIResponse(MeterTypes.E.name());
        return usageResponsesObjMap;
    }

    public List<UsageResponse> getWaterUsageAPIResponse() {
        List<UsageResponse> usageResponsesObjMap;
        usageResponsesObjMap = getUsageAPIResponse(MeterTypes.W.name());
        return usageResponsesObjMap;
    }

    public List<UsageResponse> getGasUsageAPIResponse() {
        List<UsageResponse> usageResponsesObjMap;
        usageResponsesObjMap = getUsageAPIResponse(MeterTypes.G.name());
        return usageResponsesObjMap;
    }

    public List<GenerationResponse> getSolarGenerationAPIResponse() {
        List<GenerationResponse> usageResponsesObjMap = new ArrayList<>();
        List<Map<String, Object>> usageResponsesList;
        // Get the response from the API
        usageResponsesList = jsonPath.getList("Result.generationUsages");
        // Todo Implement the logic to sort the list on the basis of meter number:
        /*Collections.sort(usageResponsesList, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                String value1 = (String) o1.get("meterNumber");
                String value2 = (String) o2.get("meterNumber");
                return Integer.valueOf(value1).compareTo(Integer.valueOf(value2));
            }
        });*/
        // Change the map object into the object class
        for (Map<String, Object> responseMap : usageResponsesList) {
            GenerationResponse generationResponse = new GenerationResponse();
            generationResponse.setAccountNumber((String) responseMap.get("accountNumber"));
            generationResponse.setMeterNumber((String) responseMap.get("meterNumber"));
            generationResponse.setUsageAttribute1((String) responseMap.get("usageAttribute1"));
            generationResponse.setUsageAttribute2((String) responseMap.get("usageAttribute2"));
            generationResponse.setReadingFrom((String) responseMap.get("readingFrom"));
            generationResponse.setReadingTo((String) responseMap.get("readingTo"));
            generationResponse.setAmount(Double.parseDouble(String.valueOf(responseMap.get("amount"))));
            generationResponse.setGeneration(Double.parseDouble(String.valueOf(responseMap.get("generation"))));
            generationResponse.setTierTou((String) responseMap.get("tierTou"));
            generationResponse.setUom((String) responseMap.get("uom"));
            generationResponse.setReadDate((String) responseMap.get("readDate"));
            generationResponse.setRatePlan((String) responseMap.get("ratePlan"));
            usageResponsesObjMap.add(generationResponse);
        }
        return usageResponsesObjMap;
    }

    public List<UsageResponse> getMonthlyElectricData(User user, UsageParams usageParams, String resourcePathURI) {
        List<UsageResponse> usageResponsesObjMap;
        // Building request specification
        buildRequestSpecElectricUsage(
                user,
                usageParams,
                resourcePathURI)
        ;
        // Hit get electric usage API
        hitGetElectricUsage();
        // Verifying the response message.
        log.info("Asserting the response message.");
        Assert.assertEquals(getAPIResponseMessage(), "Request successful.");
        // Init all the data got in the response
        usageResponsesObjMap = getElectricUsageAPIResponse();
        return usageResponsesObjMap;
    }

    public List<UsageResponse> getMonthlyWaterData(User user, UsageParams usageParams, String resourcePathURI) {
        List<UsageResponse> usageResponsesObjMap;
        // Building request specification
        buildRequestSpecWaterUsage(
                user,
                usageParams,
                resourcePathURI)
        ;
        // Hit get electric usage API
        hitGetWaterUsage();
        // Verifying the response message.
        log.info("Asserting the response message.");
        Assert.assertEquals(getAPIResponseMessage(), "Request successful.");
        // Init all the data got in the response
        usageResponsesObjMap = getWaterUsageAPIResponse();
        return usageResponsesObjMap;
    }

    public List<UsageResponse> getMonthlyGasData(User user, UsageParams usageParams, String resourcePathURI) {
        List<UsageResponse> usageResponsesObjMap;
        // Building request specification
        buildRequestSpecGasUsage(
                user,
                usageParams,
                resourcePathURI
        );
        // Hit get gas usage API
        hitGetGasUsage();
        // Verifying the response message.
        log.info("Asserting the response message.");
        Assert.assertEquals(getAPIResponseMessage(), "Request successful.");
        // Init all the data got in the response
        usageResponsesObjMap = getGasUsageAPIResponse();
        return usageResponsesObjMap;
    }

    public List<UsageResponse> getDailyElectricData(User user, UsageParams usageParams, String resourcePathURI) {
        List<UsageResponse> usageResponsesObjMap;
        // Building request specification
        buildRequestSpecElectricUsage(
                user,
                usageParams,
                resourcePathURI)
        ;
        // Hit get electric usage API
        hitGetElectricUsage();
        // Verifying the response message.
        log.info("Asserting the response message.");
        Assert.assertEquals(getAPIResponseMessage(), "Request successful.");
        // Init all the data got in the response
        usageResponsesObjMap = getElectricUsageAPIResponse();
        return usageResponsesObjMap;
    }

    public List<GenerationResponse> getDailyGenerationData(User user, UsageParams usageParams,
                                                           String resourcePathURI) {
        List<GenerationResponse> generationResponsesMap;
        // Building request specification
        buildRequestSpecSolarGeneration(
                user,
                usageParams,
                resourcePathURI
        );
        // Hit get solar usage API
        hitGetSolarGeneration();
        // Verifying the response message.
        log.info("Asserting the response message.");
        Assert.assertEquals(getAPIResponseMessage(), "Request successful.");
        // Init all the data got in the response
        generationResponsesMap = getSolarGenerationAPIResponse();
        return generationResponsesMap;
    }

    public List<UsageResponse> getDailyWaterData(User user, UsageParams usageParams, String resourcePathURI) {
        List<UsageResponse> usageResponsesObjMap;
        // Building request specification
        buildRequestSpecElectricUsage(
                user,
                usageParams,
                resourcePathURI)
        ;
        // Hit get water usage API
        hitGetWaterUsage();
        // Verifying the response message.
        log.info("Asserting the response message.");
        Assert.assertEquals(getAPIResponseMessage(), "Request successful.");
        // Init all the data got in the response
        usageResponsesObjMap = getWaterUsageAPIResponse();
        return usageResponsesObjMap;
    }

    public List<UsageResponse> getDailyGasData(User user, UsageParams usageParams, String resourcePathURI) {
        List<UsageResponse> usageResponsesObjMap;
        // Building request specification
        buildRequestSpecElectricUsage(
                user,
                usageParams,
                resourcePathURI)
        ;
        // Hit get water usage API
        hitGetGasUsage();
        // Verifying the response message.
        log.info("Asserting the response message.");
        Assert.assertEquals(getAPIResponseMessage(), "Request successful.");
        // Init all the data got in the response
        usageResponsesObjMap = getGasUsageAPIResponse();
        return usageResponsesObjMap;
    }

    public List<UsageResponse> getHourlyElectricData(User user, UsageParams usageParams, String resourcePathURI) {
        List<UsageResponse> usageResponsesObjMap;
        // Building request specification
        buildRequestSpecElectricUsage(
                user,
                usageParams,
                resourcePathURI
        );
        // Hit get electric usage API
        hitGetElectricUsage();
        // Verifying the response message.
        log.info("Asserting the response message.");
        Assert.assertEquals(getAPIResponseMessage(), "Request successful.");
        // Init all the data got in the response
        usageResponsesObjMap = getElectricUsageAPIResponse();
        return usageResponsesObjMap;
    }

    public List<UsageResponse> getHourlyWaterData(User user, UsageParams usageParams, String resourcePathURI) {
        List<UsageResponse> usageResponsesObjMap;
        // Building request specification
        buildRequestSpecWaterUsage(
                user,
                usageParams,
                resourcePathURI
        );
        // Hit get water usage API
        hitGetWaterUsage();
        // Verifying the response message.
        log.info("Asserting the response message.");
        Assert.assertEquals(getAPIResponseMessage(), "Request successful.");
        // Init all the data got in the response
        usageResponsesObjMap = getWaterUsageAPIResponse();
        return usageResponsesObjMap;
    }

    public List<UsageResponse> getHourlyGasData(User user, UsageParams usageParams, String resourcePathURI) {
        List<UsageResponse> usageResponsesObjMap;
        // Building request specification
        buildRequestSpecGasUsage(
                user,
                usageParams,
                resourcePathURI
        );
        // Hit get gas usage API
        hitGetGasUsage();
        // Verifying the response message.
        log.info("Asserting the response message.");
        Assert.assertEquals(getAPIResponseMessage(), "Request successful.");
        // Init all the data got in the response
        usageResponsesObjMap = getGasUsageAPIResponse();
        return usageResponsesObjMap;
    }

    public List<UsageResponse> getMinutesElectricData(User user, UsageParams usageParams, String resourcePathURI) {
        List<UsageResponse> usageResponsesObjMap;
        // Building request specification
        buildRequestSpecWaterUsage(
                user,
                usageParams,
                resourcePathURI
        );
        // Hit get electric usage API
        hitGetElectricUsage();
        // Verifying the response message.
        log.info("Asserting the response message.");
        Assert.assertEquals(getAPIResponseMessage(), "Request successful.");
        // Init all the data got in the response
        usageResponsesObjMap = getElectricUsageAPIResponse();
        return usageResponsesObjMap;
    }

    public List<UsageResponse> getMinutesWaterData(User user, UsageParams usageParams, String resourcePathURI) {
        List<UsageResponse> usageResponsesObjMap;
        // Building request specification
        buildRequestSpecWaterUsage(
                user,
                usageParams,
                resourcePathURI
        );
        // Hit get water usage API
        hitGetWaterUsage();
        // Verifying the response message.
        log.info("Asserting the response message.");
        Assert.assertEquals(getAPIResponseMessage(), "Request successful.");
        // Init all the data got in the response
        usageResponsesObjMap = getWaterUsageAPIResponse();
        return usageResponsesObjMap;
    }

    public List<UsageResponse> getMinutesGasData(User user, UsageParams usageParams, String resourcePathURI) {
        List<UsageResponse> usageResponsesObjMap;
        // Building request specification
        buildRequestSpecGasUsage(
                user,
                usageParams,
                resourcePathURI
        );
        // Hit get gas usage API
        hitGetGasUsage();
        // Verifying the response message.
        log.info("Asserting the response message.");
        Assert.assertEquals(getAPIResponseMessage(), "Request successful.");
        // Init all the data got in the response
        usageResponsesObjMap = getGasUsageAPIResponse();
        return usageResponsesObjMap;
    }

    public void segregateElectricMeters(Meter[] meters) {
        ExtentLogger.logInfo("Segregating the electric meters.");
        this.meters = new ArrayList<>();
        for (Meter meter : meters) {
            this.meters.add(meter);
        }
        electricMeters = new ArrayList<>();
        for (Meter meter : this.meters) {
            if (meter.getMeterType().equals(MeterTypes.E.name())) {
                electricMeters.add(meter);
            }
        }
        ExtentLogger.logInfo("Electric meters linked to the account : " + electricMeters.size());
    }

    public void segregateWaterMeters(Meter[] meters) {
        ExtentLogger.logInfo("Segregating the water meters.");
        this.meters = new ArrayList<>();
        for (Meter meter : meters) {
            this.meters.add(meter);
        }
        waterMeters = new ArrayList<>();
        for (Meter meter : this.meters) {
            if (meter.getMeterType().equals(MeterTypes.W.name())) {
                waterMeters.add(meter);
            }
        }
        ExtentLogger.logInfo("Water meters linked to the account : " + waterMeters.size());
    }

    public void segregateGasMeters(Meter[] meters) {
        ExtentLogger.logInfo("Segregating the gas meters.");
        this.meters = new ArrayList<>();
        for (Meter meter : meters) {
            this.meters.add(meter);
        }
        gasMeters = new ArrayList<>();
        for (Meter meter : this.meters) {
            if (meter.getMeterType().equals(MeterTypes.G.name())) {
                gasMeters.add(meter);
            }
        }
        ExtentLogger.logInfo("Gas meters linked to the account : " + gasMeters.size());
    }

    public void segregateSolarMeters(Meter[] meters) {
        ExtentLogger.logInfo("Segregating the solar meters.");
        this.meters = new ArrayList<>();
        for (Meter meter : meters) {
            this.meters.add(meter);
        }
        solarMeters = new ArrayList<>();
        for (Meter meter : this.meters) {
            if (meter.getMeterType().equals(MeterTypes.PV.name())) {
                solarMeters.add(meter);
            }
        }
        ExtentLogger.logInfo("Solar meters linked to the account : " + solarMeters.size());
    }

    public void buildRequestSpecTilesDetailsForElectric(User user, UsageTilesParam tilesDetailsParam,
                                                        String resourcePathURI) {
        // Init header map
        /*
         * HashMap<String, String> headerMap = new HashMap<>();
         * headerMap.put("Authorization", user.getJwtToken());
         * headerMap.put("SourceType", "1");
         */
        // Init query param
        HashMap<String, Object> params = new HashMap<>();
        params.put("AccountNumber", tilesDetailsParam.getAccountNumber());
        params.put("StartDate", tilesDetailsParam.getStartDate());
        params.put("EndDate", tilesDetailsParam.getEndDate());
        params.put("Type", tilesDetailsParam.getType());
        // Getting the request specification
        reqSpec = requestSpecification(params, resourcePathURI);
    }

    public void hitTilesDetailsForElectric() {
        String endPoint;
        Routes routes = Routes.valueOf(PROJECTED_ELECTRIC_ENDPOINT);
        endPoint = routes.getResource();
        log.info("Hit the electric compare zip API with given request specification: " + reqSpec + "\n"
                + "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " + HttpVerbs.GET.name());
        // Getting the response
        response = RestUtils.getResponse(reqSpec, HttpVerbs.GET.name(), endPoint);
        // Printing the response
        response.prettyPrint();
        jsonPath = RestUtils.rawToJson(response);
    }

    public TilesDetailResponse getProjectedElectricData() {
        TilesDetailResponse tilesDetailResponse = jsonPath.getObject("Result.projectedElectricCommerical",
                TilesDetailResponse.class);// Result.projectedElectricCommerical
        return tilesDetailResponse;
    }

    public TilesDetailResponse getTilesDetailsForElectric(User user, UsageTilesParam tilesDetailsParam) {
        // Getting the request specification
        buildRequestSpecTilesDetailsForElectric(
                user,
                tilesDetailsParam,
                ResourcePaths.USAGE_PATH_URI
        );
        // Hit tiles usage data response
        hitTilesDetailsForElectric();
        // Initialize response path
        TilesDetailResponse tilesDetailResponse = getProjectedElectricData();
        return tilesDetailResponse;
    }

    public void buildRequestSpecTilesDetailsForWater(User user, UsageTilesParam tilesDetailsParam,
                                                     String resourcePathURI) {
        /// Init header map
        /*
         * HashMap<String, String> headerMap = new HashMap<>();
         * headerMap.put("Authorization", user.getJwtToken());
         * headerMap.put("SourceType", "1");
         */
        // Init query param
        HashMap<String, Object> params = new HashMap<>();
        params.put("AccountNumber", tilesDetailsParam.getAccountNumber());
        params.put("StartDate", tilesDetailsParam.getStartDate());
        params.put("EndDate", tilesDetailsParam.getEndDate());
        params.put("Type", tilesDetailsParam.getType());
        // Getting the request specification
        reqSpec = requestSpecification(params, resourcePathURI);
    }

    public void hitTilesDetailsForWater() {
        String endPoint;
        Routes routes = Routes.valueOf(PROJECTED_WATER_ENDPOINT);
        endPoint = routes.getResource();
        log.info("Hit the electric compare zip API with given request specification: " + reqSpec + "\n"
                + "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " + HttpVerbs.GET.name());
        // Getting the response
        response = RestUtils.getResponse(reqSpec, HttpVerbs.GET.name(), endPoint);
        // Printing the response
        response.prettyPrint();
        jsonPath = RestUtils.rawToJson(response);
    }

    public TilesDetailResponse getProjectedWaterData() {
        TilesDetailResponse tilesDetailResponse = jsonPath.getObject("Result.projectedWater",
                TilesDetailResponse.class);
        return tilesDetailResponse;
    }

    public TilesDetailResponse getTilesDetailsForWater(User user, UsageTilesParam tilesDetailsParam) {
        // Getting the request specification
        buildRequestSpecTilesDetailsForWater(
                user,
                tilesDetailsParam,
                ResourcePaths.USAGE_PATH_URI
        );
        // Hit tiles usage data response
        hitTilesDetailsForWater();
        // Initialize response path
        TilesDetailResponse tilesDetailResponse = getProjectedWaterData();
        return tilesDetailResponse;
    }

    public void buildRequestSpecTilesDetailsForGas(User user, UsageTilesParam tilesDetailsParam,
                                                   String resourcePathURI) {
        /// Init header map
        /*
         * HashMap<String, String> headerMap = new HashMap<>();
         * headerMap.put("Authorization", user.getJwtToken());
         * headerMap.put("SourceType", "1");
         */
        // Init query param
        HashMap<String, Object> params = new HashMap<>();
        params.put("AccountNumber", tilesDetailsParam.getAccountNumber());
        params.put("StartDate", tilesDetailsParam.getStartDate());
        params.put("EndDate", tilesDetailsParam.getEndDate());
        params.put("Type", tilesDetailsParam.getType());
        // Getting the request specification
        reqSpec = requestSpecification(params, resourcePathURI);
    }

    public void hitTilesDetailsForGas() {
        String endPoint;
        Routes routes = Routes.valueOf(PROJECTED_GAS_ENDPOINT);
        endPoint = routes.getResource();
        log.info("Hit the electric compare zip API with given request specification: " + reqSpec + "\n"
                + "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " + HttpVerbs.GET.name());
        // Getting the response
        response = RestUtils.getResponse(reqSpec, HttpVerbs.GET.name(), endPoint);
        // Printing the response
        response.prettyPrint();
        jsonPath = RestUtils.rawToJson(response);
    }

    public TilesDetailResponse getProjectedGasData() {
        TilesDetailResponse tilesDetailResponse = jsonPath.getObject("Result.projectedGas", TilesDetailResponse.class);
        return tilesDetailResponse;
    }

    public TilesDetailResponse getTilesDetailsForGas(User user, UsageTilesParam tilesDetailsParam) {
        // Getting the request specification
        buildRequestSpecTilesDetailsForGas(
                user,
                tilesDetailsParam,
                ResourcePaths.USAGE_PATH_URI
        );
        // Hit tiles usage data response
        hitTilesDetailsForGas();
        // Initialize response path
        TilesDetailResponse tilesDetailResponse = getProjectedGasData();
        return tilesDetailResponse;
    }

    public void buildRequestSpecProjectedUsageAll(String jwtToken, String utilityAccNum, String startDate,
                                                  String endDate, String resourcePathURI) {
        // Init header map
        /*
         * HashMap<String, String> headerMap = new HashMap<>();
         * headerMap.put("Authorization", jwtToken); headerMap.put("SourceType", "1");
         */
        // Init query param
        HashMap<String, Object> params = new HashMap<>();
        params.put("AccountNumber", utilityAccNum);
        params.put("StartDate", startDate);
        params.put("EndDate", endDate);
        // Getting the request specification
        reqSpec = requestSpecification(params, resourcePathURI);
    }

    public void hitTilesDetailsForProjectedUsageAll() {
        String endPoint;
        Routes routes = Routes.valueOf(PROJECTED_USAGE_ALL_ENDPOINT);
        endPoint = routes.getResource();
        log.info("Hit the electric compare zip API with given request specification: " + reqSpec + "\n"
                + "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " + HttpVerbs.GET.name());
        // Getting the response
        response = RestUtils.getResponse(reqSpec, HttpVerbs.GET.name(), endPoint);
        // Printing the response
        response.prettyPrint();
        jsonPath = RestUtils.rawToJson(response);
    }

    public static Map<String, String> initFromAndToDate(String periodicity, Boolean isAMI) {
        String fromDate;
        String toDate;
        Map<String, String> fromAndToDateConfig = new HashMap<>();
        if (periodicity.equals(Intervals.MO.name())) {
            if (isAMI) {
                // If collection/meter is AMI
                fromDate = DateUtil.getGivenMonthsBackDate("yyyy-MM-dd", CSPConfiguration
                        .monthConfigured - 1);
                fromAndToDateConfig.put("fromDate", fromDate);
                toDate = DateUtil.getCurrentSystemDate("yyyy-MM-dd");
                fromAndToDateConfig.put("toDate", toDate);
            }
            else {
                // If collection/meter is non AMI.
                fromDate = DateUtil.getGivenMonthsBackDate("yyyy-MM-dd", CSPConfiguration
                        .monthConfigured);
                fromAndToDateConfig.put("fromDate", fromDate);
                toDate = DateUtil.getLastMonthLastDate("yyyy-MM-dd");
                fromAndToDateConfig.put("toDate", toDate);
            }
        }
        else if (periodicity.equals(Intervals.DA.name())) {
            // If collection/meter is AMI
            fromDate = DateUtil.getBackDateByGivenDays("yyyy-MM-dd", 30);
            fromAndToDateConfig.put("fromDate", fromDate);
            toDate = DateUtil.getBackDateByGivenDays("yyyy-MM-dd", 1);
            fromAndToDateConfig.put("toDate", toDate);
        }
        return fromAndToDateConfig;
    }

    public Map<String, String> getDashboardUsageData(User user) {
        Map<String, String> dashboardUsageData = new HashMap<>();
        // Init Payload
        DashboardUsagePayload dashboardUsagePayload = new DashboardUsagePayload(user);
        // Build request specification
        buildRequestSpecForDashboardUsage(
                user,
                dashboardUsagePayload,
                ResourcePaths.SCP_COMMON_PATH_URI
        );
        // Hit get electric usage API
        hitGetDashboardUsage();
        // Verifying the response message.
        log.info("Asserting the response message.");
        Assert.assertEquals(jsonPath.getString("status_code"), "200");
        Assert.assertEquals(getBaseUsageAPIResponseMessage(), "Record found");
        // Init Dashboard data
        dashboardUsageData.put("lastBill", jsonPath.getString("result.Data.objNonAMIBillingData[0]" +
                ".LastBill"));
        dashboardUsageData.put("lastBillMonth", jsonPath.getString("result.Data.objNonAMIBillingData[0]" +
                ".LastBillMonth"));
        dashboardUsageData.put("lastBillYear", jsonPath.getString("result.Data.objNonAMIBillingData[0]" +
                ".LastBillYear"));
        dashboardUsageData.put("monthBeforeLast", jsonPath.getString("result.Data.objNonAMIBillingData[0]" +
                ".Last2ndBill"));
        dashboardUsageData.put("monthBeforeLastMonth", jsonPath.getString("result.Data.objNonAMIBillingData[0]" +
                ".Last2ndBillMonth"));
        dashboardUsageData.put("monthBeforeLastYear", jsonPath.getString("result.Data.objNonAMIBillingData[0]" +
                ".Last2ndBillYear"));
        dashboardUsageData.put("lastYear", jsonPath.getString("result.Data.objNonAMIBillingData[0]" +
                ".LastYearBill"));
        dashboardUsageData.put("lastYearBillMonth", jsonPath.getString("result.Data.objNonAMIBillingData[0]" +
                ".LastYearBillMonth"));
        dashboardUsageData.put("lastYearBillYear", jsonPath.getString("result.Data.objNonAMIBillingData[0]" +
                ".LastYearBillYear"));
        return dashboardUsageData;
    }

    public void buildRequestSpecForDashboardUsage(User user, DashboardUsagePayload payload, String resourcePathURI) {
        // Init Headers
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", user.getJwtToken());
        headers.put("SourceType", "1");
        // Init request specification
        reqSpec = requestSpecification(
                resourcePathURI,
                headers,
                payload
        );
    }

    public void hitGetDashboardUsage() {
        String endPoint;
        Routes routes = Routes.valueOf(DASHBOARD_USAGE_ENDPOINT);
        endPoint = routes.getResource();
        log.info("Hit the get dashboard usages API with given request specification: " + reqSpec + "\n" +
                "and the endpoint is : " + endPoint + "\n" +
                "and with given http verb : " + HttpVerbs.POST.name());
        response = RestUtils.getResponse(reqSpec, HttpVerbs.POST.name(), endPoint);
        // Print the response
        response.prettyPrint();
        // Change the response to json path.
        jsonPath = RestUtils.rawToJson(response);
    }

    public Map<String, String> getProjectedAllUsageData(User user, Map<String, String> dashboardUsageData) {
        String fromDate = DateUtil.getStartDayCurrentMonth("MM-dd-yyyy");
        String toDate = DateUtil.getLastMonthLastDate("MM-dd-yyyy");
        // Init Payload
        UsageParams usageParams = new UsageParams(
                user.getDefaultUtilityAccNum(),
                fromDate,
                toDate,
                "",
                ""
        );
        // Build request specification
        buildRequestSpecProjectedAll(
                user,
                usageParams,
                ResourcePaths.SCP_COMMON_PATH_URI
        );
        // Hit get electric usage API
        hitUsagesProjectedAll();
        // Verifying the response message.
        log.info("Asserting the response message.");
        Assert.assertEquals(jsonPath.getString("status_code"), "200");
        Assert.assertEquals(getAPIResponseMessage(), "Request successful.");
        // Init Dashboard data
        dashboardUsageData.put("projectedAmount", jsonPath.getString("Result.projectedUsageForAll" +
                ".projectedAmount"));
        dashboardUsageData.put("soFarThisMonth", jsonPath.getString("Result.projectedUsageForAll" +
                ".soFarThisMonthProjectedAmount"));
        return dashboardUsageData;
    }

    public void buildRequestSpecProjectedAll(User user, UsageParams usageParams, String resourcePathURI) {
        // Init header map
        /*
         * HashMap<String, String> headerMap = new HashMap<>();
         * headerMap.put("Authorization", jwtToken); headerMap.put("SourceType", "1");
         */
        // Init query param
        HashMap<String, Object> params = new HashMap<>();
        params.put("AccountNumber", user.getDefaultUtilityAccNum());
        params.put("StartDate", usageParams.getFrom());
        params.put("EndDate", usageParams.getTo());
        // Getting the request specification
        reqSpec = requestSpecification(params, resourcePathURI);
    }

    public void hitUsagesProjectedAll() {
        String endPoint;
        Routes routes = Routes.valueOf(PROJECTED_USAGE_ALL_ENDPOINT);
        endPoint = routes.getResource();
        log.info("Hit the electric compare zip API with given request specification: " + reqSpec + "\n"
                + "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " + HttpVerbs.GET.name());
        // Getting the response
        response = RestUtils.getResponse(reqSpec, HttpVerbs.GET.name(), endPoint);
        // Printing the response
        response.prettyPrint();
        jsonPath = RestUtils.rawToJson(response);
    }

    public void buildRequestSpecWeatherData(User user, WeatherOverlayParams weatherOverlayParams) {
        // Init header map
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Subscription-Key", ResourcePaths.SUBSCRIPTION_KEY);
        // Init query param
        HashMap<String, Object> params = new HashMap<>();
        params.put("WeatherType", weatherOverlayParams.getWeatherType());
        params.put("WeatherFromDate", weatherOverlayParams.getWeatherFromDate());
        params.put("WeatherToDate", weatherOverlayParams.getWeatherToDate());
        params.put("CityName", weatherOverlayParams.getCityName());
        params.put("StateName", weatherOverlayParams.getStateName());
        params.put("CountryName", weatherOverlayParams.getCountryName());
        // Getting the request specification
        reqSpec = requestSpecification(headers, params);
    }

    public void hitUsagesWeatherData() {
        String endPoint;
        Routes routes = Routes.valueOf(WEATHER_OVERLAY_ENDPOINT);
        endPoint = routes.getResource();
        log.info("Hit the electric compare zip API with given request specification: " + reqSpec + "\n"
                + "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " + HttpVerbs.GET.name());
        // Getting the response
        response = RestUtils.getResponse(reqSpec, HttpVerbs.GET.name(), endPoint);
        // Printing the response
        response.prettyPrint();
        jsonPath = RestUtils.rawToJson(response);
    }

    public WeatherDataResponse[] getWeatherData(User user, WeatherOverlayParams weatherOverlayParams) {
        ObjectMapper mapper = new ObjectMapper();
        JsonParser parser;
        WeatherDataResponse[] weatherDataResponses;
        // Build request specification
        buildRequestSpecWeatherData(user, weatherOverlayParams);
        // Hit usages weather data
        hitUsagesWeatherData();
        // Verify the response
        log.info("Asserting the response message.");
        Assert.assertEquals(response.statusCode(), 200,
                "Response status code not 200");
        // Init response
        try {
            weatherDataResponses = mapper.readValue(getJsonAsString(response), WeatherDataResponse[].class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return weatherDataResponses;
    }
}

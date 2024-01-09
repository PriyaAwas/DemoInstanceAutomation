package sew.ai.api.utils;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.props.ResourcePaths;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

public class RestUtils {
    public static RequestSpecification reqSpec;
    public static Response response;
    public static JsonPath jsonPath;
    private static LocalDateTime resDateTime;
    public static long responseTimeInSeconds;

    public static RequestSpecification requestSpecification(String apiPathUri) {
        try {
            PrintStream log = new PrintStream(new FileOutputStream("logs/api_logs.txt"));
            reqSpec = RestAssured.given()
                    .baseUri(ResourcePaths.BASE_URI + apiPathUri)
                    .header("Content-Type", "application/json")
                    .filter(RequestLoggingFilter.logRequestTo(log))
                    .filter(ResponseLoggingFilter.logResponseTo(log));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reqSpec;
    }

    public static RequestSpecification requestSpecification(String apiPathUri, Map<String, String> headerMap,
                                                            Map<String, String> params) {
        PrintStream log = null;
        try {
            log = new PrintStream(new FileOutputStream("logs/api_logs.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (apiPathUri.equals(ResourcePaths.PAYPAL_PATH_URI)) {
            reqSpec = RestAssured.given()
                    .baseUri(ResourcePaths.PAYPAL_BASE_URI + apiPathUri)
                    .header("Content-Type", "application/json")
                    .headers(headerMap)
                    .queryParams(params)
                    .filter(RequestLoggingFilter.logRequestTo(log))
                    .filter(ResponseLoggingFilter.logResponseTo(log));
        }
        else {
            reqSpec = RestAssured.given()
                    .baseUri(ResourcePaths.BASE_URI + apiPathUri)
                    .header("Content-Type", "application/json")
                    .headers(headerMap)
                    .queryParams(params)
                    .filter(RequestLoggingFilter.logRequestTo(log))
                    .filter(ResponseLoggingFilter.logResponseTo(log));
        }
        return reqSpec;
    }

    public static RequestSpecification requestSpecification(Map<String, Object> queryParams, String apiPathUri) {
        PrintStream log = null;
        try {
            log = new PrintStream(new FileOutputStream("logs/api_logs.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (apiPathUri.equals(ResourcePaths.PAYPAL_PATH_URI)) {
            reqSpec = RestAssured.given()
                    .baseUri(ResourcePaths.PAYPAL_BASE_URI + apiPathUri)
                    .header("Content-Type", "application/json")
                    .queryParams(queryParams)
                    .filter(RequestLoggingFilter.logRequestTo(log))
                    .filter(ResponseLoggingFilter.logResponseTo(log));
        }
        else {
            reqSpec = RestAssured.given()
                    .baseUri(ResourcePaths.BASE_URI + apiPathUri)
                    .header("Content-Type", "application/json")
                    .queryParams(queryParams)
                    .filter(RequestLoggingFilter.logRequestTo(log))
                    .filter(ResponseLoggingFilter.logResponseTo(log));
        }
        return reqSpec;
    }

    public static RequestSpecification requestSpecification(TreeMap<String, Object> pathParams, String apiPathUri) {
        PrintStream log = null;
        try {
            log = new PrintStream(new FileOutputStream("logs/api_logs.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (apiPathUri.equals(ResourcePaths.PAYPAL_PATH_URI)) {
            reqSpec = RestAssured.given()
                    .baseUri(ResourcePaths.PAYPAL_BASE_URI + apiPathUri)
                    .header("Content-Type", "application/json")
                    .pathParams(pathParams)
                    .filter(RequestLoggingFilter.logRequestTo(log))
                    .filter(ResponseLoggingFilter.logResponseTo(log));
        }
        else {
            reqSpec = RestAssured.given()
                    .baseUri(ResourcePaths.BASE_URI + apiPathUri)
                    .header("Content-Type", "application/json")
                    .pathParams(pathParams)
                    .filter(RequestLoggingFilter.logRequestTo(log))
                    .filter(ResponseLoggingFilter.logResponseTo(log));
        }
        return reqSpec;
    }

    public static RequestSpecification requestSpecification(String apiPathUri, Map<String, String> headerMap) {
        PrintStream log = null;
        try {
            log = new PrintStream(new FileOutputStream("logs/api_logs.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        reqSpec = RestAssured.given()
                .baseUri(ResourcePaths.BASE_URI + apiPathUri)
                .header("Content-Type", "application/json")
                .headers(headerMap)
                .filter(RequestLoggingFilter.logRequestTo(log))
                .filter(ResponseLoggingFilter.logResponseTo(log));
        return reqSpec;
    }

    public static RequestSpecification requestSpecification(String apiPathUri, Object payloadObj, String username,
                                                            String password) {
        PrintStream log = null;
        try {
            log = new PrintStream(new FileOutputStream("logs/api_logs.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        reqSpec = RestAssured.given().auth().preemptive().basic(username, password)
                .baseUri(ResourcePaths.BASE_URI + apiPathUri)
                .header("Content-Type", "application/json")
                .body(payloadObj)
                .filter(RequestLoggingFilter.logRequestTo(log))
                .filter(ResponseLoggingFilter.logResponseTo(log));
        return reqSpec;
    }

    public static RequestSpecification requestSpecification(String apiPathUri, Map<String, String> reqHeader,
                                                            Object payloadObj) {
        PrintStream log = null;
        try {
            log = new PrintStream(new FileOutputStream("logs/api_logs.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (apiPathUri.equals(ResourcePaths.PAYPAL_PATH_URI)) {
            reqSpec = RestAssured.given()
                    .baseUri(ResourcePaths.PAYPAL_BASE_URI + apiPathUri)
                    .header("Content-Type", "application/json")
                    .headers(reqHeader)
                    .body(payloadObj)
                    .filter(RequestLoggingFilter.logRequestTo(log))
                    .filter(ResponseLoggingFilter.logResponseTo(log));
        }
        else {
            reqSpec = RestAssured.given()
                    .baseUri(ResourcePaths.BASE_URI + apiPathUri)
                    .header("Content-Type", "application/json")
                    .headers(reqHeader)
                    .body(payloadObj)
                    .filter(RequestLoggingFilter.logRequestTo(log))
                    .filter(ResponseLoggingFilter.logResponseTo(log));
        }
        return reqSpec;
    }

    public static RequestSpecification requestSpecification(String apiPathUri, Object payloadObj) {
        PrintStream log = null;
        try {
            log = new PrintStream(new FileOutputStream("logs/api_logs.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (apiPathUri.equals(ResourcePaths.PAYPAL_PATH_URI)) {
            reqSpec = RestAssured.given()
                    .baseUri(ResourcePaths.PAYPAL_BASE_URI + apiPathUri)
                    .header("Content-Type", "application/json")
                    .body(payloadObj)
                    .filter(RequestLoggingFilter.logRequestTo(log))
                    .filter(ResponseLoggingFilter.logResponseTo(log));
        }
        else {
            reqSpec = RestAssured.given()
                    .baseUri(ResourcePaths.BASE_URI + apiPathUri)
                    .header("Content-Type", "application/json")
                    .body(payloadObj)
                    .filter(RequestLoggingFilter.logRequestTo(log))
                    .filter(ResponseLoggingFilter.logResponseTo(log));
        }
        return reqSpec;
    }

    public static RequestSpecification requestSpecification(Map headers, Map params) {
        PrintStream log = null;
        try {
            log = new PrintStream(new FileOutputStream("logs/api_logs.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
            reqSpec = RestAssured.given()
                    .baseUri(ResourcePaths.WEATHER_OVERLAY_URI)
                    .header("Content-Type", "application/json")
                    .headers(headers)
                    .queryParams(params)
                    .filter(RequestLoggingFilter.logRequestTo(log))
                    .filter(ResponseLoggingFilter.logResponseTo(log));

        return reqSpec;
    }

    public static String getGlobalValue(String key) {
        String value = null;
        try {
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream(FilePaths.API_CONFIG_PROP);
            prop.load(fis);
            value = prop.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String getPropValue(String key) {
        String value = null;
        try {
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream(FilePaths.API_CONFIG_PROP);
            prop.load(fis);
            value = prop.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String getJsonPath(Response response, String key) {
        String value = null;
        try {
            String resp = response.asPrettyString();
            JsonPath js = new JsonPath(resp);
            value = js.get(key).toString();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * This method is used to read data from JSON
     *
     * @param path
     * @return
     * @throws IOException
     * @author Rahul.Rana
     */
    public static String generateStringFromResource(String path) {
        String resource = null;
        try {
            resource = new String(Files.readAllBytes(Paths.get(path)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resource;
    }

    public static JsonPath rawToJson(Response response) {
        String asString = response.asPrettyString();
        JsonPath jsonPath = new JsonPath(asString);
        return jsonPath;
    }

    public static String getJsonAsString(Response response) {
        return response.asPrettyString();
    }

    /**
     * This method is used to get the response of the API
     *
     * @param reqSpec
     * @param methodType
     * @param apiRoute
     * @return
     */
    public static Response getResponse(RequestSpecification reqSpec, String methodType, String apiRoute) {
        Response response = null;
        switch (methodType) {
            case "POST":
                response = RestAssured.given().spec(reqSpec).when().post(apiRoute);
                LocalDateTime now = LocalDateTime.now();
                setDateTimeOfResponse(now);
                ValidatableResponse valRes = response.then();
                responseTimeInSeconds = response.getTimeIn(TimeUnit.SECONDS);
                break;
            case "GET":
                response = RestAssured.given().spec(reqSpec).when().get(apiRoute);
                System.out.println(apiRoute);
                now = LocalDateTime.now();
                setDateTimeOfResponse(now);
                valRes = response.then();
                responseTimeInSeconds = response.getTimeIn(TimeUnit.SECONDS);
                break;
            case "PUT":
                response = RestAssured.given().spec(reqSpec).when().put(apiRoute);
                System.out.println(apiRoute);
                now = LocalDateTime.now();
                setDateTimeOfResponse(now);
                valRes = response.then();
                responseTimeInSeconds = response.getTimeIn(TimeUnit.SECONDS);
                break;
            case "DELETE":
                response = RestAssured.given().spec(reqSpec).when().delete(apiRoute);
                System.out.println(apiRoute);
                now = LocalDateTime.now();
                setDateTimeOfResponse(now);
                valRes = response.then();
                responseTimeInSeconds = response.getTimeIn(TimeUnit.SECONDS);
                break;
        }
        return response;
    }

    protected JSONObject createJSONPayload(Object pojo) {
        return new JSONObject(pojo);
    }

    public static void pause(int miliSec) {
        try {
            Thread.sleep(miliSec);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDateTimeOfResponse() {
        return resDateTime.format(DateTimeFormatter.ofPattern("DbCspConfig.getConfiguredDateFormat()"
                + " hh:mm a"));
    }

    public static void setDateTimeOfResponse(LocalDateTime dateTimeOfResponse) {
        resDateTime = dateTimeOfResponse;
    }
}

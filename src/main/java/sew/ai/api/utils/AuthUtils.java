package sew.ai.api.utils;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import sew.ai.api.enums.Routes;
import sew.ai.api.pojos.auth.JwtPayload;
import sew.ai.helpers.props.ResourcePaths;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.utils.Base64EncryptionUtil;
import sew.ai.utils.DataBaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AuthUtils extends RestUtils {
    public static final String AUTH_TOKEN_ENDPOINT = "GetAuthHeaderEndpoint";
    public static final String AUTH_TOKEN_CSP_ENDPOINT = "GetAuthHeaderCSPEndpoint";
    public final String JWT_TOKEN_ENDPOINT = "GetJwtTokenEndpoint";
    String jwtToken, userId;

    public String getJwtToken(String userName, String password) {
        Response response;
        Routes routes = Routes.valueOf(JWT_TOKEN_ENDPOINT);
        // Getting userid using username
        ResultSet resultSet = DataBaseUtils.getResultSet(SQLQueries.getUserId(userName));
        try {
            resultSet.next();
            userId = resultSet.getString("userid");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Creating request payload for getting Jwt token
        JwtPayload jwtPayload = new JwtPayload(userName, password, false);
        // Creating request specification for Jwt
        RequestSpecification reqSpec = requestSpecification(ResourcePaths.JWT_PATH_URI, jwtPayload);
        // Getting response
        response = RestUtils.getResponse(reqSpec, "POST", routes.getResource());
        // Print response
        response.prettyPrint();
        // Verify response is not null
        // assertNotNull(response, "API returns null as response.");
        // Verifying response
        Assert.assertEquals(response.getStatusCode(), 200);
        // Fetching access token
        String accessToken = RestUtils.getJsonPath(response, "data.accessToken");
        // Encrypting base64 userId:accessToken
        jwtToken = "Basic " + Base64EncryptionUtil.getBase64EncryptedString(
                userId + ":" + accessToken
        );
        return jwtToken;
    }

    public static String getTokenKey() {
        String tokenKey;
        Routes routes = Routes.valueOf(AUTH_TOKEN_ENDPOINT);
        // Init API Endpoint
        String getTokenKeyIdURI = ResourcePaths.BASE_URI + ResourcePaths.SCP_COMMON_PATH_URI + routes.getResource();
        // Get Response
        Response res = RestAssured.given()
                .when()
                .post(getTokenKeyIdURI);
        // Print response
        // res.prettyPrint();
        // Verify status code
        Assert.assertEquals(res.getStatusCode(), 200);
        // Validate response
        JsonPath jsonPath = RestUtils.rawToJson(res);
        Assert.assertEquals(jsonPath.getString("result.Message"), "Successful!!");
        // Generating authorization header.
        String token = jsonPath.getString("result.Data.tokenId").trim();
        String key = jsonPath.getString( "result.Data.key").trim();
        tokenKey = key + ":" + token;
        return tokenKey;
    }

    public static String getTokenKeyForCSP() {
        String tokenKey;
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("Connection", "keep-alive");
        String myJson = "{\"Source\":\"prelogin\",\"CsrUserId\":\"58\"}";
        Routes routes = Routes.valueOf(AUTH_TOKEN_CSP_ENDPOINT);
        String getTokenKeyIdURI = ResourcePaths.BASE_URI + "adminBaseUri" + routes.getResource();
        Response res = RestAssured.given().headers(headers).body(myJson).when().post(getTokenKeyIdURI);
        // res.prettyPrint();
        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(RestUtils.getJsonPath(res, "status.message"), "success");
        String token = getJsonPath(res, "data.Token").trim();
        tokenKey = "58" + ":" + token;
        return tokenKey;
    }

    public static String getEncryptedTokenKey() {
        String tokenKey = getTokenKey();
        String encryptedTokenKey = Base64EncryptionUtil.getBase64EncryptedString(tokenKey);
        return encryptedTokenKey;
    }

    public static String getEncryptedTokenKeyCSP() {
        String tokenKey = getTokenKeyForCSP();
        String encryptedTokenKey = Base64EncryptionUtil.getBase64EncryptedString(tokenKey);
        return encryptedTokenKey;
    }

    public static String getAuthorizationToken() {
        String encryptedTokenKey = getEncryptedTokenKey();
        String authorizationHeader = "Basic " + encryptedTokenKey;
        return authorizationHeader;
    }

    public static String getAuthorizationHeaderCSP() {
        String encryptedTokenKey = getEncryptedTokenKeyCSP();
        String authorizationHeader = "Basic " + encryptedTokenKey;
        return authorizationHeader;
    }
    
    public String getAuthorizationHeader() {
        String encryptedTokenKey = getEncryptedTokenKey();
        String authorizationHeader = "Basic " + encryptedTokenKey;
        return authorizationHeader;
    }

    public static void main(String args[]) {
        AuthUtils tokenUtil = new AuthUtils();
        String token = tokenUtil.getAuthorizationToken();
        System.out.println(token);
    }
}

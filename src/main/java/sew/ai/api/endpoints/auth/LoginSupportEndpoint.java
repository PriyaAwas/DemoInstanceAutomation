package sew.ai.api.endpoints.auth;

import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sew.ai.api.enums.HttpVerbs;
import sew.ai.api.enums.Routes;
import sew.ai.api.pojos.loginHelp.ProblemSignInPayload;
import sew.ai.api.utils.AuthUtils;
import sew.ai.api.utils.RestUtils;
import sew.ai.config.SCPConfiguration;
import sew.ai.helpers.props.ResourcePaths;
import sew.ai.models.User;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class LoginSupportEndpoint extends RestUtils {

    private static final Logger log = LogManager.getLogger(LoginSupportEndpoint.class);

    public final String GET_PROBLEMS_SIGNING_IN_ENDPOINT = "GetProblemsSigningInEndpoint";

    public RequestSpecification generateProblemSigningInRequest(String resourcePathUri,Map<String, String> reqHeader, Object payLoad) {
        return given().spec(RestUtils.requestSpecification(resourcePathUri, reqHeader, payLoad));
    }

    public ProblemSignInPayload generateProblemsSigningInSCPRequest() {
        User user = SCPConfiguration.user;
        ProblemSignInPayload problemSignInPayload = buildRequestSpecToCreateProblemSignInReq(user, ResourcePaths.SCP_COMMON_PATH_URI);
        hitProblemSigningInAPI(user);
        return problemSignInPayload;
    }

    public ProblemSignInPayload buildRequestSpecToCreateProblemSignInReq(User user, String resourcePathURI) {
        log.info("Initializing the payload.");
        ProblemSignInPayload problemsSigningInPayload = new ProblemSignInPayload(user);
        // Init header map
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Authorization", AuthUtils.getAuthorizationToken());
        headerMap.put("SourceType", "0");
        log.info("Initializing the Request specification.");
        reqSpec = requestSpecification(resourcePathURI, headerMap,problemsSigningInPayload);
        return problemsSigningInPayload;
    }
    public void hitProblemSigningInAPI(User user) {
        String endPoint;
        Routes routes = Routes.valueOf(GET_PROBLEMS_SIGNING_IN_ENDPOINT);
        endPoint = routes.getResource();
        log.info("Hit the ProblemSigning In API with given request specification: " +
                reqSpec + "\n" +
                "and the endpoint is : " + endPoint + "\n" + "and with given http verb : " +
                HttpVerbs.POST.name());
        // Getting the response
        response = RestUtils.getResponse(reqSpec, HttpVerbs.POST.name(), endPoint);
        // Printing the response
        response.prettyPrint();
        // JsonPath path = RestUtils.rawToJson(response);
        jsonPath = RestUtils.rawToJson(response);
        assertEquals(response.getStatusCode(), 200,"Create ProblemsSigningIn SCP API Request Success Code is not as Expected.");
        assertEquals(jsonPath.getInt("result.Status"), 1);
        assertEquals(jsonPath.getString("result.Message"),"Thank you for writing in. We will get back to you in 2 working days.");
        assertEquals(jsonPath.getString("result.Data[0].Message"),"Thank you for writing in. We will get back to you in 2 working days.");
        assertEquals(jsonPath.getString("result.Data[0].FromEmail"), user.getEmailId());

    }


}

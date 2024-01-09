package sew.ai.api.endpoints.billing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import sew.ai.api.enums.HttpVerbs;
import sew.ai.api.enums.Routes;
import sew.ai.api.pojos.billing.autopay.AutopayPayload;
import sew.ai.api.utils.RestUtils;
import sew.ai.config.ModelsConfiguration;
import sew.ai.helpers.props.ResourcePaths;
import sew.ai.helpers.props.SQLQueries;
import sew.ai.models.Autopay;
import sew.ai.models.User;
import sew.ai.utils.DataBaseUtils;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutopayEndpoints extends RestUtils {
    private static final Logger log = LogManager.getLogger(AutopayEndpoints.class);
    private static final String AUTOPAY_ENROLL_UNENROLL_ENDPOINT = "AutopayEnrollUnEnrollEndpoint";

    public void buildRequestSpecForEnrollUnEnrollInAutopay(User user, Autopay autopay, String resourcePathURI) {
        HashMap<String, String> headers = new HashMap<>();
        // Initialize header to post
        headers.put("Authorization", user.getJwtToken());
        headers.put("SourceType", "1");
        // Initialize payload body
        AutopayPayload autopayPayload = new AutopayPayload(autopay, user);
        // Build request specifications
        reqSpec = RestUtils.requestSpecification(
                resourcePathURI,
                headers,
                autopayPayload
        );
    }

    public void hitEnrollUnEnrollInAutopayAPI() {
        String endPoint;
        Routes routes = Routes.valueOf(AUTOPAY_ENROLL_UNENROLL_ENDPOINT);
        endPoint = routes.getResource();
        log.info("Hit the Enroll/UnEnroll from Autopay API with given request specification: " + reqSpec + "\n" +
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

    public int getAPIResponseCode() {
        return response.getStatusCode();
    }

    public String getAPIResponseMessage() {
        return jsonPath.getString("result.Message").trim();
    }

    public String getEnrollUnenrollSuccessMessage() {
        return jsonPath.getString("result.Data[0].Message").trim();
    }

    public Boolean isEnrolled() {
        return jsonPath.getBoolean("result.Data[0].IsEnrolled");
    }

    public String getEmail() {
        return jsonPath.getString("result.Data[0].Email").trim();
    }

    public String getFullName() {
        return jsonPath.getString("result.Data[0].Fullname").trim();
    }

    /**
     * This method un-enrolls all the payment method which are enrolled
     * in autopay.
     * @param user
     */
    public void unenrollAccountFromAutopay(User user) {
        ResultSet resultSet;
        try {
            resultSet = DataBaseUtils.getResultSet(SQLQueries.getQueryCountAccEnrolledForAutopay(user.getUserName()));
            resultSet.next();
            String count = resultSet.getString("UtilityAccountNumber");
            if (Integer.parseInt(count) > 0) {
                resultSet = DataBaseUtils.getResultSet(SQLQueries.getQueryAccEnrollForAutoPay(user.getUserName()));
                List<String> utilityAccNumList = new ArrayList<>();
                while (resultSet.next()) {
                    String autoAcc = resultSet.getString("UtilityAccountNumber");
                    utilityAccNumList.add(autoAcc);
                }
                if (utilityAccNumList.size() >= 1) {
                    for (String utilityAccNum : utilityAccNumList) {
                        Map<String, String> autoPayEnrollDetails = getAutoPayInfoDatabase(utilityAccNum);
                        // Init Autopay model
                        Autopay autopay = ModelsConfiguration.readAutoPayByScenario().getAutopayObjByScenarioName(
                                "autopay_scenario_4");
                        autopay.setAutoPayId(Integer.parseInt(autoPayEnrollDetails.get("id")));
                        autopay.setPaymentProfileId(autoPayEnrollDetails.get("payId"));
                        autopay.setPaymentSubType(Integer.parseInt(autoPayEnrollDetails.get("paymentSubType")));
                        autopay.setAccountNumber(autoPayEnrollDetails.get("accountNumber"));
                        autopay.setUtilityAccountNumber(utilityAccNum);
                        // Build request specification
                        buildRequestSpecForEnrollUnEnrollInAutopay(
                                user,
                                autopay,
                                ResourcePaths.SCP_COMMON_PATH_URI
                        );
                        // Hit API
                        hitEnrollUnEnrollInAutopayAPI();
                        // Verify response
                        Assert.assertEquals(getAPIResponseCode(), 200);
                        Assert.assertEquals(getAPIResponseMessage(), "Record found");
                        Assert.assertEquals(getEnrollUnenrollSuccessMessage(),
                                "You have been successfully unenrolled from Automatic Payments.");
                        Assert.assertFalse(isEnrolled());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method get autopay info related to the utility account number.
     *
     * @param utilityAccNum
     * @return
     * @throws Exception
     */
    public HashMap<String, String> getAutoPayInfoDatabase(String utilityAccNum) {
        HashMap<String, String> paymentMapDb = new HashMap<>();
        ResultSet resultSet;
        try {
            resultSet = DataBaseUtils.getResultSet(SQLQueries.getAutopayInfo(utilityAccNum));
            resultSet.next();
            paymentMapDb.put("id", resultSet.getString("id"));
            paymentMapDb.put("paymentSubType", resultSet.getString("paymentsubtype"));
            paymentMapDb.put("accountNumber", resultSet.getString("accountnumber"));
            paymentMapDb.put("payId", resultSet.getString("userprofileid"));
            paymentMapDb.put("recPaymentDate", resultSet.getString("recpaymentdate"));
            paymentMapDb.put("paymentFrequencyId", resultSet.getString("paymentfrequencyid"));
            paymentMapDb.put("paymentAmount", resultSet.getString("paymentamount"));
            paymentMapDb.put("nextPaymentDate", resultSet.getString("nextpaymentdate"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paymentMapDb;
    }
}

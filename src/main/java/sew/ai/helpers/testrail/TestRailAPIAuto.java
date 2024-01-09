package sew.ai.helpers.testrail;

import com.aventstack.extentreports.Status;
import org.json.simple.JSONObject;
import sew.ai.config.Configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static sew.ai.helpers.reporters.ExtentManager.getExtentTest;

public class TestRailAPIAuto {

    public static APIClient client() {
        // TODO make this read from settings/config file
        APIClient client = new APIClient("https://smartenergywater.testrail.io");
        client.setUser("ranjit.biswal@smartenergywater.in");
        client.setPassword("Smart2010%");
        return client;
    }

    public static JSONObject getCase(int caseId) throws Exception {
        APIClient client = TestRailAPIAuto.client();
        JSONObject c = (JSONObject) client.sendGet("get_case/" + caseId);
        return c;
    }

    public static void addResultForTestCase(String testCaseId, int status, String error)
            throws IOException, APIException {
        String testRunId = Configuration.toString("testRailTestRunId");
        String testRailPlatform = Configuration.toString("platform");
        APIClient client = TestRailAPIAuto.client();
        Map data = new HashMap();
        data.put("status_id", status);
        data.put("comment", "Test Executed - Status updated automatically from Selenium test automation.");
        data.put("custom_system", testRailPlatform);
        client.sendPost("add_result_for_case/" + testRunId + "/" + testCaseId + "", data);
        if (status == 5) {
            getExtentTest().log(
                    Status.FAIL,
                    "Test case " + testCaseId + ": Automation test fails as not matching the condition"
            );
        }
    }
}
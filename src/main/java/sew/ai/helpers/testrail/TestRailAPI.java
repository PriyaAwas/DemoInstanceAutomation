package sew.ai.helpers.testrail;

import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TestRailAPI {

    public static APIClient client() {
        // TODO make this read from settings/config file
        //APIClient client = new APIClient("https://YOURACCOUNT.testrail.net");
        APIClient client = new APIClient("https://smartenergywater.testrail.io");
        client.setUser("ranjit.biswal@smartenergywater.in");
        client.setPassword("Smart2010%");
        return client;
    }

    public static JSONObject getCase(int p_caseId) throws Exception {
        APIClient client = TestRailAPI.client();
        JSONObject c = (JSONObject) client.sendGet("get_case/" + p_caseId);
        return c;
    }

    public static JSONObject addResult(int p_statusId, String p_comment, String p_platform, int p_runId,
                                       int p_caseId) throws Exception {
        APIClient client = TestRailAPI.client();
        Map data = new HashMap();
        data.put("status_id", new Integer(p_statusId));
        data.put("comment", p_comment);
        data.put("custom_system", p_platform);
        JSONObject r = (JSONObject) client.sendPost("add_result_for_case/" + p_runId + "/" + p_caseId, data);
        return r;
    }
}
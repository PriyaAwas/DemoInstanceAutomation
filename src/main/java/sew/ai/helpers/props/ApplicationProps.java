package sew.ai.helpers.props;

import sew.ai.utils.PropertiesUtil;

public class ApplicationProps {
    public static String OVERRIDE_REPORTS = PropertiesUtil.getApplicationPropValue(
            "OVERRIDE_REPORTS");
    public static String PASSED_STEPS_SCREENSHOT = PropertiesUtil.getApplicationPropValue(
            "PASSED_STEPS_SCREENSHOT");
    public static String RUN_MODE = PropertiesUtil.getApplicationPropValue(
            "RUN_MODE");
    public static String RETRY_FAILED_TESTS = PropertiesUtil.getApplicationPropValue(
            "RETRY_FAILED_TESTS");
    public static String FAILED_STEPS_SCREENSHOT = PropertiesUtil.getApplicationPropValue(
            "FAILED_STEPS_SCREENSHOT");
    public static String SKIPPED_STEPS_SCREENSHOT = PropertiesUtil.getApplicationPropValue(
            "SKIPPED_STEPS_SCREENSHOT");
    public static String PAY_AS_YOU_GO_CONFIG = PropertiesUtil.getApplicationPropValue("PAY_AS_YOU_GO_CONFIG");
    public static String MONTHLY_PAYMENT_TYPE = PropertiesUtil.getApplicationPropValue("MONTHLY_PAYMENT_TYPE");
}

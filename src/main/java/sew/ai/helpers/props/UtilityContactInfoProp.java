package sew.ai.helpers.props;

import sew.ai.utils.PropertiesUtil;

public class UtilityContactInfoProp {
	
	public static String CUSTOMER_SERVICE_NUMBER = PropertiesUtil.getApplicationPropValue(
            "CUSTOMER_SERVICE_NUMBER");
    public static String CUSTOMER_SERVICE_EMAIL = PropertiesUtil.getApplicationPropValue(
            "CUSTOMER_SERVICE_EMAIL");
    public static String BILLING_ENQUIRY_NUMBER = PropertiesUtil.getApplicationPropValue(
            "BILLING_ENQUIRY_NUMBER");
    public static String BILLING_ENQUIRY_EMAIL = PropertiesUtil.getApplicationPropValue(
            "BILLING_ENQUIRY_EMAIL");
    public static String UTILITY_NAME = PropertiesUtil.getApplicationPropValue(
            "UTILITY_NAME");
    public static String CORPORATE_HEADQUARTERS_ADDRESS = PropertiesUtil.getApplicationPropValue(
            "CORPORATE_HEADQUARTERS_ADDRESS");
}

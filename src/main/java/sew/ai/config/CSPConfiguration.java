package sew.ai.config;

import sew.ai.helpers.props.SQLQueries;
import sew.ai.models.RegistrationConfig;
import sew.ai.utils.DataBaseUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

import static sew.ai.helpers.props.SQLQueries.dateFormatConfigSettingsCspQuery;
import static sew.ai.helpers.props.SQLQueries.decimalPlaceAdminSettingsCspQuery;

public class CSPConfiguration {
    public static int monthConfigured = 0;
    public static ArrayList<String> languageConfig = new ArrayList<>();
    public static Map<String, String> dateMetricsConfig = new LinkedHashMap<>();
    public static Map<String, Integer> cspConfig = new HashMap<>();
    public static Map<String, Integer> allCSPModulesConfig = new HashMap<>();
    public static Map<String, String> adminUserDetails = new HashMap<>();
    public static Map <String ,RegistrationConfig> registrationConfig   = new HashMap<>();
    public static ResultSet rs;


    /**
     * Getting all the metric settings from CSP like currency, date, distance,
     * temperature, time format and time zone.
     *
     * @return
     */
    public static Map<String, String> initDateMetricsConfig() {
        ResultSet resultSet;
        String getDateFormatMetric = SQLQueries.getDateFormatMetricSettings();
        try {
            resultSet = DataBaseUtils.getResultSet(getDateFormatMetric);
            while (resultSet.next()) {
                dateMetricsConfig.put(
                        resultSet.getString("Name"),
                        resultSet.getString("DisplayName")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateMetricsConfig;
    }

    public static int initUsageMonthsConfig() {
        ResultSet resultSet;
        try {
            // Counting the number of months configured in CSP
            resultSet = DataBaseUtils.getResultSet(SQLQueries.usageMonthSettingsCspQuery);
            resultSet.next();
            monthConfigured = Integer.parseInt(resultSet.getString("ConfigValue"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return monthConfigured;
    }

    public static List<String> initLanguageConfig() {
        languageConfig = new ArrayList<>();
        try {
            ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.configuredLanguageQuery);
            while (rs.next()) {
                languageConfig.add(rs.getString("LanguageDescription"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return languageConfig;
    }

    public static Map<String, Integer> initCSPConfig() {
        String getAllModuleConfigQuery = SQLQueries.getAllFeatureConfigured();
        // Getting all the config
        try {
            ResultSet resultSet;
            resultSet = DataBaseUtils.getResultSet(getAllModuleConfigQuery);
            cspConfig.clear();
            while (resultSet.next()) {
                cspConfig.put(
                        resultSet.getString("FeatureName"),
                        Integer.parseInt(resultSet.getString("Status"))
                );
            }
            // Explicitly setting seasonal config to 0 as it is out of scope for now.
            cspConfig.put("Power.Seasonal", 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cspConfig;
    }

    public static Map<String, Integer> initAllCSPModulesConfig() {
        String getAllCspModulesQuery = SQLQueries.getAllCspModules();
        try {
            ResultSet resultSet;
            resultSet = DataBaseUtils.getResultSet(getAllCspModulesQuery);
            allCSPModulesConfig.clear();
            while (resultSet.next()) {
                allCSPModulesConfig.put(resultSet.getString("RightName"),
                        Integer.parseInt(resultSet
                                .getString("IsActive")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allCSPModulesConfig;
    }

    public static Map<String, String> initAdminUserConfig(){
        try {
            ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.getAdminUserDetailsWithUsername(Configuration.toString("adminUsername")));
            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();
            ArrayList<String> columns = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metadata.getColumnName(i);
                columns.add(columnName);
            }
            adminUserDetails = new LinkedHashMap<>();
            while (rs.next()) {
                for (String columnName : columns) {
                    String value = rs.getString(columnName);
                    adminUserDetails.put(columnName, value);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return adminUserDetails;

    }
    public static Map<String, RegistrationConfig> initRegistrationConfig() {
        // This sql query executed to bring the registration config data
    	ResultSet rs;
        try {
            rs = DataBaseUtils.getResultSet(SQLQueries.getRegistrationTemplateConfig());
          
            while (rs.next()) {
                registrationConfig.put(rs.getString("ParentHead"),
                        new RegistrationConfig(
                                rs.getString("ScpStatus"),
                                rs.getString("Min Length"),
                                rs.getString("Max Length"),
                                rs.getString("Mandatory"),
                                rs.getString("Validation Against CIS"),
                                rs.getString("Type"))
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return registrationConfig;
    }
    public static LinkedHashMap<String, Integer> initMCspConfig() {
        String getAllModuleConfigQuery = SQLQueries.getAllFeatureConfigured();
        // Getting all the config
        LinkedHashMap<String, Integer> mCspConfig = new LinkedHashMap<String, Integer>();
        try {
            rs = DataBaseUtils.getResultSet(getAllModuleConfigQuery);
            mCspConfig.clear();
            while (rs.next()) {
                mCspConfig.put(
                        rs.getString("FeatureName"),
                        Integer.parseInt(rs.getString("Status"))
                );
            }
            // Explicitly setting seasonal config to 0 as it is out of scope for now.
            mCspConfig.put("Power.Seasonal", 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mCspConfig;
    }
    public static  Map<String, RegistrationConfig> initMRegistrationData() {
        // This sql query executed to bring the registration config data
        try {
            rs = DataBaseUtils.getResultSet(SQLQueries.getRegistrationTemplateConfig());
            registrationConfig.clear();
            while (rs.next()) {
                registrationConfig.put(rs.getString("ParentHead"),
                        new RegistrationConfig(
                                rs.getString("ScpStatus"),
                                rs.getString("Min Length"),
                                rs.getString("Max Length"),
                                rs.getString("Mandatory"),
                                rs.getString("Validation Against CIS"),
                                rs.getString("Type"))
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return registrationConfig;
    }
    public static Map<String, String> initCspGuestUserConfig() {
		Map<String, String> mCspGuestUserConfig = new HashMap<>();
		String sQuery = SQLQueries.getCspGuestUserConfig();
		try {
			rs = DataBaseUtils.getResultSet(sQuery);
			while (rs.next()) {
				mCspGuestUserConfig.put(rs.getString("ConfigOption"), rs
						.getString("ConfigValue"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mCspGuestUserConfig;
	}

    public static int initDecimalPlaceConfigCsp() {
        int decimalPlaces = 0;
        try {
            // Counting the number of decimal places in CSP
            rs = DataBaseUtils.getResultSet(decimalPlaceAdminSettingsCspQuery);
            rs.next();
            decimalPlaces = Integer.parseInt(rs.getString("UptoDecimalPlaces"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decimalPlaces;
    }

    // This query brings the configured format
    public static String initConfiguredDateFormat() {
        String dateFormat = null;
        try {
            // Counting the number of months configured in CSP
            rs = DataBaseUtils.getResultSet(dateFormatConfigSettingsCspQuery);
            rs.next();
            dateFormat = rs.getString("FormatName");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateFormat;
    }
}

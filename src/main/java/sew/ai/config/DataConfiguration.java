package sew.ai.config;

import java.util.Map;

public class DataConfiguration {
    /**
     * Config file for SEW configuration settings. Users can create a sew.yml file, and we will load
     * configs from this file instead of setting them manually on the command line each time.
     * A user can override a specific setting by passing in a command line arg or using
     * System.getProperty() in the setup method in the test java file.
     */
    private Map<String, Map<String, String>> configurations;

    public DataConfiguration() {}

    public Map<String, Map<String, String>> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(Map<String, Map<String, String>> configurations) {
        this.configurations = configurations;
    }

    /**
     * Returns the configuration value for the given environment and key .
     *
     * @param environment      String the environment in which the test is running (e.g. dev, qa, uat)
     * @param configurationKey String the name of the setting to retrieve
     * @return String the configuration value
     */
    protected String getConfigurationValue(String environment, String configurationKey) {
        String configurationValue = null;
        if (configurations.containsKey(environment)) {
            configurationValue = configurations.get(environment).get(configurationKey);
        }
        // If we did not find a value for the set environment, then we check the default settings
        if (configurationValue == null) {
            configurationValue = configurations.get("qa").get(configurationKey);
        }
        return configurationValue;
    }
}

package sew.ai.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sew.ai.helpers.exceptions.FileException;
import sew.ai.utils.FileUtil;
import sew.ai.utils.StringUtil;

import java.io.File;
import java.util.Arrays;
import java.util.Properties;

public class Configuration {
    private static final Logger log = LogManager.getLogger(Configuration.class);
    private static final String ENV_REPLACE_STRING = "{env}";
    private static String env = null;
    private static Properties appProps = new Properties();
    private static DataConfiguration sewAppDataConfiguration = null;
    private static final File CONFIGURATION_FILE = new File("conf/sew.app.yml");
    private static final String ENV = "qa";
    private static final String LINUX = "linux";
    private static final String MAC = "mac";
    private static final String WINDOWS = "windows";
    private static final String BROWSER = "browser";

    /**
     * Exists only to defeat instantiation.
     */
    private Configuration() {
    }

    /**
     * Returns the configuration value for the given configuration property and the given environment from
     * the ConfigurationData class.
     *
     * @param configurationKey String the key for the requested configuration property
     * @return String the configuration value
     */
    private static String getOrCreateAppConfigurationData(String configurationKey) {
        String data = System.getProperty(configurationKey);
        if (data != null) {
            return data;
        }
        if (sewAppDataConfiguration == null) {
            try {
                File file = new File("conf/sew.app.yml");
                ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
                sewAppDataConfiguration = mapper.readValue(file, DataConfiguration.class);
            } catch (Exception e) {
                String errorMessage = StringUtil.format("Could not load the {} property because" +
                                " of the exception: {}." + System.lineSeparator() + "Please fix the file or " +
                                "pass the property in on the commandline using the -D{}= option.",
                        configurationKey, e.getMessage());
                throw new FileException(errorMessage, e, CONFIGURATION_FILE);
            }
        }
        return sewAppDataConfiguration.getConfigurationValue(environment(), configurationKey);
    }

    /**
     * Returns the configuration for a given property as a String value.
     * It has the following precedence for searching for a value:
     * <ol>
     * <li>A value stored at runtime.</li>
     * <li>A value set on the command line.</li>
     * <li>A value set in the configuration file.</li>
     * </ol>
     * The first time it is run, the value found will be stored for future calls.
     * If no value is found, null is returned.
     *
     * @param property String the requested configuration property
     * @return String the value of the requested configuration property (null if nothing is found)
     */
    public static String toString(String property) {
        String propertyValue = appProps.getProperty(property);
        if (propertyValue == null) {
            try {
                propertyValue = System.getProperty(property);
                if (propertyValue == null) {
                    propertyValue = getOrCreateAppConfigurationData(property);
                }
                else {
                    appProps.setProperty(property, propertyValue);
                }
                return propertyValue;
            } catch (FileException e) {
                log.trace(e.getMessage(), Arrays.toString(e.getStackTrace()));
                return null;
            }
        }
        return propertyValue;
    }

    /**
     * Returns the configuration for a given property as a String value.
     * It has the following precedence for searching for a value:
     * <ol>
     * <li>A value stored at runtime.</li>
     * <li>A value set on the command line.</li>
     * <li>A value set in the configuration file.</li>
     * </ol>
     * The first time it is run, the value found will be stored for future calls.
     * If no value is found, the passed default value is returned.
     *
     * @param property     String the requested configuration property
     * @param defaultValue String the default to return if no value is found
     * @return String the value of the requested configuration property (null if nothing is found)
     */
    public static String toString(String property, String defaultValue) {
        String propertyValue = toString(property);
        if (propertyValue == null) {
            appProps.setProperty(property, defaultValue);
            return defaultValue;
        }
        return propertyValue;
    }

    /**
     * Updates a configuration value once runtime has started. This should never be used in a Cucumber
     * runner as it will mask any values in the configuration file and on the command line.
     *
     * @param property String the property to update
     * @param value    String the value to be used
     */
    public static void update(String property, String value) {
        appProps.setProperty(property, value);
    }

    /**
     * Clears a configuration value once runtime has started. This should never be used in a Cucumber
     * runner as it will mask any values in the configuration file and on the command line.
     *
     * @param property String the property to clear
     */
    public static void clear(String property) {
        appProps.remove(property);
    }

    /**
     * Returns the given configuration value stored in the given property as a Double, or 0.0
     * if nothing is found.
     *
     * @param property String the requested configuration property key
     * @return Double the requested value as a Double or 0.0 if nothing valid is found
     */
    public static Double toDouble(String property) {
        try {
            return Double.valueOf(toString(property));
        } catch (Exception e) {
            log.trace(e.getMessage(), Arrays.toString(e.getStackTrace()));
            return 0.0;
        }
    }

    /**
     * Returns the given configuration value stored in the given property as a boolean, or false
     * if nothing is found.
     *
     * @param property String the requested configuration property key
     * @return boolean the requested value as a boolean or false if nothing valid is found
     */
    public static boolean toBoolean(String property) {
        String prop = toString(property);
        if (prop != null) {
            switch (prop.toLowerCase()) {
                case "true":
                case "":
                    return true;
                default:
                    return false;
            }
        }
        else {
            return false;
        }
    }

    /**
     * Updates a configuration value once runtime has started. This should never be used in a Cucumber
     * runner as it will mask any values in the configuration file and on the command line.
     *
     * @param property String the property to update
     * @param value    Double the value to be used
     */
    public static void update(String property, double value) {
        update(property, String.valueOf(value));
    }

    /**
     * Returns the given configuration value stored in the given property as a Long, or 0L if nothing is
     * found.
     *
     * @param property String the requested configuration property key
     * @return Double the requested value as a Double or 0.0 if nothing valid is found
     */
    public static Long toLong(String property) {
        try {
            return Long.valueOf(toString(property));
        } catch (Exception e) {
            log.trace(e.getMessage(), Arrays.toString(e.getStackTrace()));
            return 0L;
        }
    }

    /**
     * Updates a configuration value once runtime has started. This should never be used in a Cucumber
     * runner as it will mask any values in the configuration file and on the command line.
     *
     * @param property String the property to update
     * @param value    Long the value to be used
     */
    public static void update(String property, long value) {
        update(property, String.valueOf(value));
    }

    /**
     * Returns the system environment.
     * If no environment is set, a warning message is logged and a default value of "localhost" is set.
     *
     * @return String text of system env info
     */
    public static String environment() {
        if (env == null) {
            env = System.getProperty("env");
            if (env == null) {
                env = ENV;
                String warningMessage = "QA env being used by default. " +
                        Configuration.configurationNotFoundErrorMessage("env");
                log.warn(warningMessage);
            }
        }
        return env;
    }

    /**
     * Setter intended only for unit testing. Sets the stored value and also the System Property.
     *
     * @param env String env to set, null to clear
     */
    protected static void environment(String env) {
        Configuration.env = env;
        if (env == null)
            System.clearProperty("env");
        else
            System.setProperty("env", env);
    }

    /**
     * Returns the file path for a given page object.
     *
     * @param pageName String the name of the page object
     * @return File the OS path to the config file
     */
    public static File findPageObjectFilePath(String pageName) {
        return FileUtil.findFilePath(pageName + ".yml");
    }

    private static String configurationNotFoundErrorMessage(String configurationValue) {
        return StringUtil.format("No {} property set. This can be set in the sew.app.yml " +
                        "config file with a '{}=' property or on the command line with the switch '-D{}='.",
                configurationValue);
    }

    /**
     * Returns a sanitized version of the browser set in the config file or on the command line.
     *
     * @return String a sanitized string containing the browser
     */
    public static String browser() {
        String browser;
        browser = Configuration.toString(BROWSER);
        if (browser == null) {
            browser = "Chrome";
            String infoMessage = "Chrome browser being used by default. " +
                    Configuration.configurationNotFoundErrorMessage(BROWSER);
            log.info(infoMessage);
        }
        // Make sure whatever string we are passed is all lower case and all spaces are removed.
        browser = browser.replaceAll("\\s+", "");
        if (browser.equals("ie"))
            browser = "InternetExplorer";
        return browser;
    }

    /**
     * Returns a sanitized version of the operating system set in the config file or on the command line.
     *
     * @return String a sanitized string containing the operating system
     */
    public static String operatingSystem() {
        String operatingSystem = Configuration.toString("os");
        if (operatingSystem == null) {
            operatingSystem = detectOperatingSystem();
        }
        return operatingSystem;
    }

    /**
     * Returns the simple operating system of windows, mac or linux. Returns the full os name if none of
     * those are matched.
     *
     * @return String operating system
     */
    protected static String detectOperatingSystem() {
        String os = System.getProperty("os.name").toLowerCase();
        String infoMessage = StringUtil.format("Operating system auto-detected: \"{}\". ", os) +
                Configuration.configurationNotFoundErrorMessage("os");
        log.info(infoMessage);
        if (os.indexOf("win") >= 0) {
            return WINDOWS;
        }
        if (os.indexOf("mac") >= 0) {
            return MAC;
        }
        if (os.indexOf("nux") >= 0) {
            return LINUX;
        }
        return os;
    }
}

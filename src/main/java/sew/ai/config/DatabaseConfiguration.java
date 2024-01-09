package sew.ai.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sew.ai.helpers.exceptions.FileException;
import sew.ai.utils.StringUtil;

import java.io.File;
import java.util.Arrays;
import java.util.Properties;

public class DatabaseConfiguration {
    private static final Logger log = LogManager.getLogger(DatabaseConfiguration.class);
    private static Properties appProps = new Properties();
    private static String env = null;
    private static DatabaseDrivers sewDataBaseConfig = null;
    private static final String ENV = "qa";
    private static final File CONFIGURATION_FILE = new File("conf/sew.database.yml");

    // To restrict instantiation
    private DatabaseConfiguration() {
    }

    private static DataBaseModel getOrCreteDBConfigurationData(String dbType) {
        String data = System.getProperty(dbType);
        if (sewDataBaseConfig == null) {
            try {
                File file = new File("conf/sew.database.yml");
                ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
                sewDataBaseConfig = mapper.readValue(file, DatabaseDrivers.class);
            } catch (Exception e) {
                String errorMessage = StringUtil.format("Could not load the {} property because" +
                                " of the exception: {}." + System.lineSeparator() + "Please fix the file or " +
                                "pass the property in on the commandline using the -D{}= option.",
                        dbType, e.getMessage());
                throw new FileException(errorMessage, e, CONFIGURATION_FILE);
            }
        }
        return sewDataBaseConfig.getDataBaseConfigurationValue(environment(), dbType);
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
    public static DataBaseModel getDatabaseDrivers(String property) {
        DataBaseModel dataBaseModel = null;
        try {
            if (dataBaseModel == null) {
                dataBaseModel = getOrCreteDBConfigurationData(property);
            }
        } catch (FileException e) {
            log.trace(e.getMessage(), Arrays.toString(e.getStackTrace()));
            return null;
        }
        return dataBaseModel;
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
                        DatabaseConfiguration.configurationNotFoundErrorMessage("env");
                log.warn(warningMessage);
            }
        }
        return env;
    }

    private static String configurationNotFoundErrorMessage(String configurationValue) {
        return StringUtil.format("No {} property set. This can be set in the sentinel.yml " +
                        "config file with a '{}=' property or on the command line with the switch '-D{}='.",
                configurationValue);
    }
}

package sew.ai.runner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import sew.ai.config.CSPConfiguration;
import sew.ai.config.SCPConfiguration;
import sew.ai.driver.DriverFactory;
import sew.ai.enums.PlatformType;
import sew.ai.helpers.appium.AppiumServer;
import sew.ai.helpers.props.FilePaths;
import sew.ai.utils.DataBaseUtils;
import sew.ai.utils.PropertiesUtil;

public class TestRunner {
    private static final Logger log = LogManager.getLogger(TestRunner.class);

    public static WebDriver driver = null;

    @Parameters({"platformType", "platformName"})
    @BeforeTest
    public void testPreRequisites(@Optional String platformType, @Optional String platformName) {
        if (platformType.equalsIgnoreCase(PlatformType.MOBILE.toString())) {
            AppiumServer.killExistingAppiumProcess();
            if (AppiumServer.appium == null || !AppiumServer.appium.isRunning()) {
                AppiumServer.start();
                log.info("Appium server has been started");
            }
        }
        // INIT GLOBAL PROPERTIES
        log.info("Initializing application properties config file.");
        PropertiesUtil.initApplicationProps(FilePaths.APP_CONFIG_PROP);
        // INIT DATABASE CONNECTION
        log.info("Initializing connection to the base database.");
       //DataBaseUtils.getBaseDatabaseConnection();
        // INIT RESOURCE PATH PROPERTIES
        log.info("Initializing resource path config file.");
        PropertiesUtil.initResourcePathProps(FilePaths.API_CONFIG_PROP);
        // INIT MONTH CONFIGURED IN CSP
        log.info("Initializing usage months config from CSP.");
        //CSPConfiguration.initUsageMonthsConfig();
        // INIT DATE CONFIGURED IN
        log.info("Initializing date config from CSP.");
        //CSPConfiguration.initDateMetricsConfig();
        // INIT DATE CONFIGURED IN
        log.info("Initializing language config from CSP.");
        //CSPConfiguration.initLanguageConfig();
        // INIT DATE CONFIGURED IN
        log.info("Initializing language config from CSP.");
        //CSPConfiguration.initCSPConfig();
        // INIT DATE CONFIGURED IN
        log.info("Initializing language config from CSP.");
        //CSPConfiguration.initAllCSPModulesConfig();
        // INIT USER
        log.info("Initializing user model config.");
        //SCPConfiguration.initUserConfiguration();
        // Init Tokens
        log.info("Initializing auth token config.");
        // SCPConfiguration.initAuthTokens();
        //INIT ADMIN USER
        log.info("Initializing Admin User config.");
        //CSPConfiguration.initAdminUserConfig();
    }

    @Parameters({"platformType", "platformName", "model"})
    @BeforeClass
    public void classPreRequisites(String platformType, String platformName, @Optional String model) {
    }

    @Parameters({"platformType", "platformName","portalType", "model"})
    @BeforeMethod
    public void methodsPreRequisites(String platformType, String platformName,String portalType, @Optional String model) {
        if (platformType.equalsIgnoreCase(PlatformType.WEB.toString())) {
            driver = DriverFactory.getWebDriver(platformName,portalType);
        } else if (platformType.equalsIgnoreCase(PlatformType.MOBILE.toString())) {
            driver = DriverFactory.getMobileDriver(platformName, model);
        }
    }

    @AfterMethod
    public void tearDownMethods(ITestResult result) {
    	log.info(result.getMethod().getMethodName() + " is Finished.");  
        DriverFactory.deleteAllCookies();
        DriverFactory.quit();
    }

    @AfterClass
    public void tearDownClasses() {
    }

//    @AfterTest
//    public void tearDownTests() {
//        // Close database connection
//        DataBaseUtils.closeBaseDatabaseConnection();
//    }
}

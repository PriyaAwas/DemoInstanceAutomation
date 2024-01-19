package sew.ai.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import sew.ai.config.Configuration;
import sew.ai.enums.OperatingSystems;
import sew.ai.helpers.managers.DownloadManager;
import sew.ai.utils.StringUtil;

import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WebDriverFactory {
    private static final Logger log = LogManager.getLogger(WebDriverFactory.class);
    private static WebDriver driver = null;
    private static int implicitTimeout = 0;
    private static int pageLoadTimeout = 0;
    private static String platformName;

    private WebDriverFactory() {
        // Exists only to defeat instantiation.
    }

    /**
     * Creates and returns a usable WebDriver.
     * We use this factory method to handle keeping up with driver versions for all
     * browsers. The browser can be set in the config file or a system
     * variable. See the README for more information.
     *
     * @return WebDriver An initialized <a href="https://www.seleniumhq.org/">Selenium
     * WebDriver</a> object for the specified browser and operating system
     * combination.
     */
    protected static WebDriver instantiateWebDriver(String platformName) {
        // Initialize the driver object based on the browser and operating system (OS).
        // Throw an error if the value isn't found.
        switch (platformName) {
            case "chrome":
                driver = createChromeDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            default:
                throw new WebDriverException(StringUtil.format("Invalid browser type '{}' " +
                        "passed to WebDriverFactory. Could not resolve the reference. Check your spelling. " +
                        "Refer to the Javadoc for valid options.", platformName));
        }
        // Set the browser window max size
        setBrowserWindowToMax();
        // Initialize the page load and implicit wait
        setUpImplicitPageLoadWait();
        return driver;
    }

    /**
     * Quits the driver and sets the driver instance back to null.
     */
    protected static void quit() {
        if (exists()) {
            driver.quit();
            driver = null;
        }
        else {
            log.info("Attempted to call quit on a driver that did not exist.");
        }
    }

    public static boolean exists() {
        return driver != null;
    }

    private static ChromeOptions setExperimentalFeatures(ChromeOptions options) {
        options.setExperimentalOption(
                "excludeSwitches",
                Arrays.asList(
                        "enable-automation"//"disable-popup-blocking"

                )
        );
        options.setExperimentalOption("useAutomationExtension", false);
        return options;
    }

    private static Map<String, Object> setChromePreference(Map<String, Object> chromePrefs) {
        // SET CHROME PREFERENCE
        chromePrefs.put("download.prompt_for_download", false);
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", System.getProperty("user.dir") + File.separator +
                DownloadManager.getDownloadDirectory());
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        return chromePrefs;
    }

    private static void setChromeContentSetting(Map<String, Object> chromeProfile, Map<String, Object> contentSettings) {
        // 0 - Default, 1 - Allow, 2 - Block
        contentSettings.put("geolocation", 2);
        chromeProfile.put("managed_default_content_settings", contentSettings);
    }

    private static void setChromeProfile(ChromeOptions chromeOptions, String profileName) {
        // chrome://version/
        String pathToProfile = null;
        String userName = System.getProperty("user.name");
        String operatingSystem = Configuration.operatingSystem();
        if (operatingSystem.contains(OperatingSystems.Mac.name())) {
            pathToProfile = "/Users/" + userName + "/Library/Application Support/Google/Chrome/";
        }
        else if (operatingSystem.contains(OperatingSystems.Windows.name())) {
            pathToProfile = "C:\\Users\\" + userName + "\\AppData\\Local\\Google\\Chrome\\User Data";
        }
        else if (operatingSystem.contains(OperatingSystems.Linux.name())) {
            pathToProfile = "/home/" + userName + "/.config/google-chrome/";
        }
        chromeOptions.addArguments("chrome.switches", "--disable-extensions");
        chromeOptions.addArguments("--user-data-dir=" + pathToProfile);
        chromeOptions.addArguments("--profile-directory=" + profileName);
    }

    /**
     * Creates a ChromeDriver. Makes it headless if the -Dheadless flag is set.
     * Can pass additional arguments with the -DchromeOptions flag,
     * such as -DchromeOptions="start-maximized" to open all browser windows maximized.
     *
     * @return WebDriver ChromeDriver
     */
    private static WebDriver createChromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        Map<String, Object> chromePrefs = new HashMap<>();
        // Suppress the un-necessary logs
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "false");
        // Set Chrome preferences
        chromePrefs = setChromePreference(chromePrefs);
        // Set chrome options
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--remote-allow-origins=*");
        // Set Chrome pref to Chrome options
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        // Set Experimental features
        chromeOptions = setExperimentalFeatures(chromeOptions);
        // Set the headless mode
        String headless = Configuration.toString("headless");
        if (headless != null && !headless.equalsIgnoreCase("false")) {
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--headless");
        }
        // Initializing the chrome driver instance with web driver manager
        DriverManagerType chrome = DriverManagerType.CHROME;
        WebDriverManager.getInstance(chrome).setup();
        return new ChromeDriver(chromeOptions);
    }

    /**
     * Initialize the web driver implicit wait and page load timeout.
     */
    private static void setUpImplicitPageLoadWait() {
        implicitTimeout = Integer.parseInt(Configuration.toString("implicitTimeout"));
        log.info("Initializing the implicit wait timeout for {} " + implicitTimeout);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitTimeout));
        pageLoadTimeout = Integer.parseInt(Configuration.toString("pageTimeout"));
        log.info("Initializing the page load timeout.");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
    }

    /**
     * Set the browser window to its maximum.
     */
    private static void setBrowserWindowToMax() {
        log.info("Setting up the browser window to its max.");
        driver.manage().window().maximize();
    }
}

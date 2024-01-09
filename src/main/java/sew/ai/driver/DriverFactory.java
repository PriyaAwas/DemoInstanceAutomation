package sew.ai.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import sew.ai.config.Configuration;
import sew.ai.enums.PlatformName;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class DriverFactory {
    private static final Logger log = LogManager.getLogger(DriverFactory.class);
    // The web driver
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static WindowList windows;

    // To restrict the multiple object creation
    private DriverFactory() {}

    public static WebDriver getWebDriver(String platformName,String portalType) {
        if (driver.get() == null) {
            driver.set(WebDriverFactory.instantiateWebDriver(platformName));
        }
        deleteAllCookies();
        // Invoke the application URL
        if(portalType.equalsIgnoreCase("SCP"))
            goToPage(Configuration.toString("scp.url"));
        else if(portalType.equalsIgnoreCase("CSP"))
            goToPage(Configuration.toString("csp.url"));
        // Init wrappers class
        // Page pageWrappers = new Page(driver.get());
        return driver.get();
    }

    public static WebDriver getMobileDriver(String platformName, String model) {
        if (platformName.equalsIgnoreCase(PlatformName.ANDROID.toString())) {
            driver.set(AndroidDriverFactory.instantiateAndroidDriver(model));
        } else if (platformName.equalsIgnoreCase(PlatformName.IOS.toString())) {
            driver.set(IOSDriverFactory.instantiateIOSDriver(model));
        }
        log.info(model + " driver has been created for execution");
        return driver.get();
    }

    public static void quit() {
        driver.get().quit();
        driver.remove();
        // windows.clear();
    }

    public static void refreshBrowser() {
        log.info("Refreshing the browser {}.");
        driver.get().navigate().refresh();
        log.info("Browser refreshed successfully.");
    }

    public static void goToPage(String url) {
        log.info("Open the url {} " + url + " using the selenium get() method.");
        try {
        driver.get().get(url);
        Page.pause(5000);
        }catch(Exception e)
        {
        	System.out.println("ex");
        }
        
        
    }

    public static void navigateToPage(String url) {
        log.info("Open the url {} " + url + " using the selenium navigate().to() method.");
        driver.get().navigate().to(url);
    }

    /**
     * Emulate clicking the browser's back button.
     */
    public static void navigateBack() {
        log.info("Navigate back using the selenium navigate().back() method.");
        driver.get().navigate().back();
    }

    /**
     * Emulate clicking the browser's forward button.
     */
    public static void navigateForward() {
        log.info("Navigate forward using the selenium navigate().forward() method.");
        driver.get().navigate().forward();
    }

    public static String getMainWindowHandle() {
        String mainWinHandle = null;
        mainWinHandle = driver.get().getWindowHandle();
        return mainWinHandle;
    }

    public static List<String> getWindowHandles() {
        List<String> mainWinHandle = null;
        mainWinHandle = (List) driver.get().getWindowHandles();
        return mainWinHandle;
    }

    public static void switchToChildWindow() {
        Set<String> s1 = driver.get().getWindowHandles();
        Iterator<String> I1 = s1.iterator();
        while (I1.hasNext()) {
            String child_window = I1.next();
            // Here we will compare if parent window is not equal to child
            // window then we will close
            if (!getMainWindowHandle().equals(child_window)) {
                driver.get().switchTo().window(child_window);
            }
        }
    }

    public static void switchToWindow(String winHandle) {
        driver.get().switchTo().window(winHandle);
    }

    public static void deleteAllCookies() {
        log.info("Clearing all the cookies.");
        driver.get().manage().deleteAllCookies();
    }

    /**
     * Go to next window.
     */
    protected static void goToNextWindow() {
        windows.goToNextWindow();
    }

    /**
     * Wait for, and go to, next window.
     */
    protected static void goToNewWindow() {
        windows.goToNewWindow();
    }

    /**
     * Go to previous window.
     */
    protected static void goToPreviousWindow() {
        windows.goToPreviousWindow();
    }

    /**
     * Emulate clicking the browser's refresh button.
     */
    protected static void refresh() {
        driver.get().navigate().refresh();
    }

    /**
     * Maximize the window.
     */
    protected static void maximizeWindow() {
        driver.get().manage().window().maximize();
    }

    /**
     * Close the current window.
     */
    protected static void close() {
        windows.closeCurrentWindow();
    }
    public boolean isElementPresent(List<WebElement>element) {
		if (element.size() >0) {
			log.info("Element is present on the Page");
			return true;
		} else {
			log.info("Element is not Displayed on the Page");
			return false;
		}
	}
}

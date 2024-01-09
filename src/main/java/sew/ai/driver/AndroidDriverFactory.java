package sew.ai.driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import sew.ai.config.DeviceConfig;
import sew.ai.models.AndroidDeviceModel;
import sew.ai.utils.FileUtil;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AndroidDriverFactory extends DeviceConfig {
    private static final Logger log = LogManager.getLogger(AndroidDriverFactory.class);
    private static AndroidDriver driver;

    private AndroidDriverFactory() {
        // Exists only to defeat instantiation.
    }

    protected static AndroidDriver instantiateAndroidDriver(String model) {
        DesiredCapabilities androidCapabilities = new DesiredCapabilities();
        AndroidDeviceModel device;
        try {
            device = readAndroidDeviceConfig().getAndroidDeviceByName(model);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("Received the " + model + " device configuration for execution");
        androidCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device.getDeviceName());
        androidCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, device.getPlatformName());
        androidCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, device.getPlatformVersion());
        androidCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, device.getAutomationName());
        androidCapabilities.setCapability(MobileCapabilityType.NO_RESET, device.isReset());
        androidCapabilities.setCapability(MobileCapabilityType.APP, FileUtil.getFile(device.getApp())
                .getAbsolutePath());
        androidCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, device.getPackageName());
        androidCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, device.getActivity());
        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), androidCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        log.info("Android driver has been created for the " + model + " device");
        return driver;
    }
}

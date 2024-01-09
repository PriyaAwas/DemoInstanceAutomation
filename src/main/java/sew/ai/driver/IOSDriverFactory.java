package sew.ai.driver;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import sew.ai.config.DeviceConfig;
import sew.ai.models.IOSDeviceModel;
import sew.ai.utils.FileUtil;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class IOSDriverFactory extends DeviceConfig {
    private static final Logger log = LogManager.getLogger(IOSDriverFactory.class);
    private static IOSDriver driver;

    public IOSDriverFactory() {
        // Exists only to defeat instantiation.
    }

    public static IOSDriver instantiateIOSDriver(String model) {
        DesiredCapabilities iosCapabilities = new DesiredCapabilities();
        IOSDeviceModel device;
        try {
            device = readIOSDeviceConfig().getIOSDeviceByName(model);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("Received the " + model + " device configuration for execution");
        iosCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device.getDeviceName());
        iosCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, device.getPlatformName());
        iosCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, device.getPlatformVersion());
        iosCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, device.getAutomationName());
        iosCapabilities.setCapability(MobileCapabilityType.UDID, device.getUdid());
        iosCapabilities.setCapability(MobileCapabilityType.NO_RESET, device.isReset());
        iosCapabilities.setCapability(MobileCapabilityType.APP, FileUtil.getFile(device.getApp())
                .getAbsolutePath());
        try {
            driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), iosCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        log.info("IOS driver has been created for the " + model + " device");
        return driver;
    }
}

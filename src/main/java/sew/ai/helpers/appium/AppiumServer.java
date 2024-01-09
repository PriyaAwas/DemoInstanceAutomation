package sew.ai.helpers.appium;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sew.ai.config.Configuration;

import java.io.File;
import java.io.IOException;

public class AppiumServer {
    private static final Logger log = LogManager.getLogger(AppiumServer.class);
    public static AppiumDriverLocalService appium;

    public static void start() {
        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        appium = builder.build();
        appium.start();
        log.info("Appium server has been started");
    }

    public static void stop() {
        appium.stop();
        log.info("Appium server has been stopped");
    }

    public static void killExistingAppiumProcess() {
        Runtime runtime = Runtime.getRuntime();
        String operatingSystem = Configuration.operatingSystem();
        try {
            if (operatingSystem.contains("Mac OS X")) {
                String[] command = {"/usr/bin/killall", "-9", "node"};
                runtime.exec(command);
            } else if (operatingSystem.contains("Windows")) {
                runtime.exec("taskkill /F /IM node.exe");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("Killing existing appium process");
    }
}
